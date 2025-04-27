package com.gtnewhorizon.cropsnh.utility;

import java.util.HashMap;
import java.util.stream.Stream;

import net.minecraftforge.oredict.OreDictionary;

public class MetaMap<K, V> {

    private HashMap<K, HashMap<Integer, V>> map = new HashMap<>();
    private HashMap<K, V> wildcards = new HashMap<>();

    /**
     * Inserts an item into meta map.
     *
     * @param key   The item or block to insert.
     * @param meta  The metadata of the block or item, -1 for any.
     * @param value The value ot insert.
     */
    public void put(K key, int meta, V value) {
        // if adding if a wildcard is already registered
        if (isWildCard(meta)) {
            this.putWildcard(key, value, false);
            return;
        }
        // don't clear wildcards so that wildcards can be used as a base value.
        if (!this.map.containsKey(key)) {
            // else ensure that the meta map exists.
            this.map.put(key, new HashMap<>());
        } else {
            this.map.get(key)
                .put(meta, value);
        }
    }

    public void putWildcard(K key, V value, boolean clearNonWildcards) {
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
    public boolean putIfAbsent(K key, int meta, V value, boolean ignoreExistingWildcard) {
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
        if (!map.containsKey(key)) {
            map.put(key, new HashMap<>());
        }
        // skip if the meta already exists
        if (map.containsKey(meta)) return false;
        // else abort
        map.get(key)
            .put(meta, value);
        return true;
    }

    /**
     * Gets the value for an item in the map
     *
     * @param key  The item or block to insert.
     * @param meta The metadata of the block or item.
     * @return The value ot insert.
     */
    public V get(K key, int meta) {
        if (isWildCard(meta)) {
            return this.wildcards.get(key);
        }
        // check if the key is
        if (!this.map.containsKey(key)) return null;
        HashMap<Integer, V> metaMap = this.map.get(key);
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
    public V getOrDefault(K key, int meta, V defaultValue) {
        // check if the key is
        if (isWildCard(meta)) {
            return this.wildcards.getOrDefault(key, defaultValue);
        }
        // if key not found in meta map, check wildcards
        if (!map.containsKey(key)) {
            return this.wildcards.getOrDefault(key, defaultValue);
        }
        // fetch the meta map
        HashMap<Integer, V> metaMap = map.get(key);
        // if meta not found in meta map, check wildcards
        if (!metaMap.containsKey(meta)) {
            return this.wildcards.getOrDefault(meta, defaultValue);
        }
        return metaMap.getOrDefault(meta, defaultValue);
    }

    // both -1 and the ore dict can be used as wildcards for compatibility reasons.
    private static boolean isWildCard(int meta) {
        return meta == -1 || meta == OreDictionary.WILDCARD_VALUE;
    }

    /**
     * Checks if an item is already in the map.
     *
     * @param key  The item or block to insert.
     * @param meta The metadata of the block or item.
     * @return True if the item is set.
     */
    public boolean containsKey(K key, int meta) {
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
