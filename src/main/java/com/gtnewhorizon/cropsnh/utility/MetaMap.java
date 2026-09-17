package com.gtnewhorizon.cropsnh.utility;

import java.util.IdentityHashMap;
import java.util.stream.Stream;

import net.minecraftforge.oredict.OreDictionary;

import org.jetbrains.annotations.Nullable;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public class MetaMap<K, V> {

    private final IdentityHashMap<K, Int2ObjectOpenHashMap<V>> map = new IdentityHashMap<>();
    private final IdentityHashMap<K, V> wildcards = new IdentityHashMap<>();

    /**
     * Inserts an item into meta map.
     *
     * @apiNote When using a meta value that is considered a wildcard, existing set values are not overwritten.
     *
     * @param key   The item or block to insert.
     * @param meta  The metadata of the block or item. Use {@link OreDictionary#WILDCARD_VALUE} or -1 for wildcards.
     * @param value The value ot insert.
     */
    public void put(final @Nullable K key, final int meta, final V value) {
        // if adding if a wildcard is already registered
        if (isWildCard(meta)) {
            this.putWildcard(key, value, false);
            return;
        }
        // don't clear wildcards so that wildcards can be used as a base value.
        this.map.computeIfAbsent(key, k -> new Int2ObjectOpenHashMap<>())
            .put(meta, value);
    }

    /**
     * Inserts a wildcard directly into the meta map.
     *
     * @param key               The item or block to insert.
     * @param value             The value to insert.
     * @param clearNonWildcards Set to true to remove any key-value entries related to the key being inserted.
     */
    public void putWildcard(final @Nullable K key, final V value, final boolean clearNonWildcards) {
        if (clearNonWildcards) {
            this.map.remove(key);
        }
        wildcards.put(key, value);
    }

    /**
     * Inserts an item only if none were already present.
     *
     * @apiNote When using a meta value that is considered a wildcard, existing set values are not overwritten.
     *
     * @param key   The item or block to insert.
     * @param meta  The metadata of the block or item. Use {@link OreDictionary#WILDCARD_VALUE} or -1 for wildcards.
     * @param value The value ot insert.
     * @return True if the value was inserted
     */
    public boolean putIfAbsent(final @Nullable K key, final int meta, final V value,
        final boolean ignoreExistingWildcard) {
        // wildcard goes first
        if (isWildCard(meta)) {
            if (!this.wildcards.containsKey(key)) {
                this.putWildcard(key, value, false);
                return true;
            }
            return false;
        }
        if (!ignoreExistingWildcard && this.wildcards.containsKey(key)) {
            return false;
        }
        // if dest meta map doesn't exist create it.
        Int2ObjectOpenHashMap<V> metaMap = this.map.get(key);
        if (metaMap == null) {
            metaMap = new Int2ObjectOpenHashMap<>();
            metaMap.put(meta, value);
            this.map.put(key, metaMap);
            return true;
        }
        // abort if meta is found
        if (metaMap.containsKey(meta)) return false;
        // else insert
        metaMap.put(meta, value);
        return true;
    }

    /**
     * Gets the value for an item in the map
     *
     * @apiNote returns null if not found.
     *
     * @param key  The item or block to insert.
     * @param meta The metadata of the block or item.
     * @return Either the value set for the key-meta pair, the wild card for the key, or the null if not found.
     */
    public @Nullable V get(final @Nullable K key, final int meta) {
        // if the meta is a wildcard or it just not in the layered registry, check wildcards.
        if (isWildCard(meta) || !this.map.containsKey(key)) {
            return this.wildcards.get(key);
        }
        // check if we have a meta map for this value and that it contains the meta key
        Int2ObjectOpenHashMap<V> metaMap = this.map.get(key);
        if (metaMap.containsKey(meta)) {
            return metaMap.get(meta);
        }
        // else return the wildcard value
        return this.wildcards.get(key);
    }

    /**
     * Gets the value for an item in the map
     *
     * @param key          The item or block to insert.
     * @param meta         The metadata of the block or item.
     * @param defaultValue The default value if the key isn't set.
     * @return Either the value set for the key-meta pair, the wild card for the key, or the default value if not found.
     */
    public @Nullable V getOrDefault(final @Nullable K key, final int meta, final @Nullable V defaultValue) {
        // if the meta is a wildcard or it just not in the layered registry, check wildcards.
        if (isWildCard(meta) || !this.map.containsKey(key)) {
            return this.wildcards.getOrDefault(key, defaultValue);
        }
        // check if we have a meta map for this value and that it contains the meta key
        Int2ObjectOpenHashMap<V> metaMap = this.map.get(key);
        if (metaMap.containsKey(meta)) {
            return metaMap.get(meta);
        }
        // else check wildcards or return default
        return wildcards.getOrDefault(key, defaultValue);
    }

    /**
     * Removes entries from this meta map.
     *
     * @param key                    The key to remove.
     * @param meta                   The metadata of the block or item. Use {@link OreDictionary#WILDCARD_VALUE} or -1
     *                               for wildcards.
     * @param removeValuesIfWildcard Set to true to remove all existing Key-Meta Entries when removing with a wildcard.
     * @return True if something was removed.
     */
    public boolean remove(final @Nullable K key, final int meta, final boolean removeValuesIfWildcard) {
        if (isWildCard(meta)) {
            return this.removeWildcard(key, removeValuesIfWildcard);
        }
        // check if the map contains an entry for this key.
        if (!this.map.containsKey(key)) return false;
        Int2ObjectOpenHashMap<V> metaMap = map.get(key);
        // check if the meta map has a value for this meta.
        if (!metaMap.containsKey(meta)) return false;
        metaMap.remove(meta);
        // remove the meta map if it's now empty.
        if (metaMap.isEmpty()) {
            this.map.remove(key);
        }
        return true;
    }

    /**
     * Removes entries from this meta map.
     *
     * @param key                      The key to remove.
     * @param alsoRemoveExistingValues Set to true to remove all existing Key-Meta entries along with the wildcard.
     * @return True if something was removed.
     */
    public boolean removeWildcard(final @Nullable K key, final boolean alsoRemoveExistingValues) {
        boolean success = false;
        if (this.wildcards.containsKey(key)) {
            this.wildcards.remove(key);
            success = true;
        }
        if (alsoRemoveExistingValues && this.map.containsKey(key)) {
            this.map.remove(key);
            success = true;
        }
        return success;
    }

    /**
     * Trims the maps for to reduce memory footprint and reduce runtime queries.
     */
    public void trim() {
        this.map.forEach((k, v) -> v.trim());
    }

    // both -1 and the ore dict can be used as wildcards for compatibility reasons.
    private static boolean isWildCard(final int meta) {
        return meta == -1 || meta == OreDictionary.WILDCARD_VALUE;
    }

    /**
     * Checks if an item is already in the map.
     *
     * @param key  The item or block to insert.
     * @param meta The metadata of the block or item.
     * @return True if the item is set.
     */
    public boolean containsKey(final K key, final int meta) {
        if (this.wildcards.containsKey(key)) return true;
        if (!this.map.containsKey(key)) return false;
        return this.map.get(key)
            .containsKey(meta);
    }

    public boolean isEmpty() {
        return this.wildcards.isEmpty() && this.map.isEmpty();
    }

    /**
     * Gets a stream of all the values in the meta map.
     *
     * @return A stream of all the values in the map.
     */
    public Stream<Entry<K, V>> getStream() {
        return Stream.concat(this.getWildcardValues(), this.getRegularValues());
    }

    private Stream<Entry<K, V>> getRegularValues() {
        return map.entrySet()
            .stream()
            .flatMap(
                e1 -> e1.getValue()
                    .entrySet()
                    .stream()
                    .map(e2 -> new Entry<>(e1.getKey(), e2.getKey(), e2.getValue())));
    }

    private Stream<Entry<K, V>> getWildcardValues() {
        return this.wildcards.entrySet()
            .stream()
            .map(e -> new Entry<>(e.getKey(), null, e.getValue()));
    }

    public static class Entry<K, V> {

        public final K key;
        public final Integer meta;
        public final V value;

        public Entry(K key, Integer meta, V value) {
            this.key = key;
            this.meta = meta;
            this.value = value;
        }
    }
}
