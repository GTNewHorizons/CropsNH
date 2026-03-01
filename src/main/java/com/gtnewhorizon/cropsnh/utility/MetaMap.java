package com.gtnewhorizon.cropsnh.utility;

import java.util.IdentityHashMap;
import java.util.stream.Stream;

import net.minecraftforge.oredict.OreDictionary;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public class MetaMap<K, V> {

    private final IdentityHashMap<K, Int2ObjectOpenHashMap<V>> map = new IdentityHashMap<>();
    private final IdentityHashMap<K, V> wildcards = new IdentityHashMap<>();

    /**
     * Inserts an item into meta map.
     *
     * @param key   The item or block to insert.
     * @param meta  The metadata of the block or item, -1 for any.
     * @param value The value ot insert.
     */
    public void put(final K key, final int meta, final V value) {
        // if adding if a wildcard is already registered
        if (isWildCard(meta)) {
            this.putWildcard(key, value, false);
            return;
        }
        // don't clear wildcards so that wildcards can be used as a base value.
        this.map.computeIfAbsent(key, k -> new Int2ObjectOpenHashMap<>())
            .put(meta, value);
    }

    public void putWildcard(final K key, final V value, final boolean clearNonWildcards) {
        if (clearNonWildcards) {
            this.map.remove(key);
        }
        wildcards.put(key, value);
    }

    /**
     * Inserts an item only if none were already present.
     *
     * @param key   The item or block to insert.
     * @param meta  The metadata of the block or item, -1 for any.
     * @param value The value ot insert.
     */
    public boolean putIfAbsent(final K key, final int meta, final V value, final boolean ignoreExistingWildcard) {
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
     * @param key  The item or block to insert.
     * @param meta The metadata of the block or item.
     * @return The value ot insert.
     */
    public V get(final K key, final int meta) {
        if (isWildCard(meta)) {
            return this.wildcards.get(key);
        }
        // check if the key is
        if (!this.map.containsKey(key)) return null;
        Int2ObjectOpenHashMap<V> metaMap = this.map.get(key);
        if (metaMap.containsKey(meta)) {
            return metaMap.get(meta);
        }
        return this.wildcards.get(key);
    }

    /**
     * Gets the value for an item in the map
     *
     * @param key          The item or block to insert.
     * @param meta         The metadata of the block or item.
     * @param defaultValue The default value if the key isn't set.
     * @return The value ot insert.
     */
    public V getOrDefault(final K key, final int meta, final V defaultValue) {
        // check if the key is
        if (isWildCard(meta)) {
            return this.wildcards.getOrDefault(key, defaultValue);
        }
        // if key not found in meta map, check wildcards
        if (!this.map.containsKey(key)) {
            return this.wildcards.getOrDefault(key, defaultValue);
        }
        // fetch the meta map
        Int2ObjectOpenHashMap<V> metaMap = map.get(key);
        return metaMap.getOrDefault(meta, defaultValue);
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
