package com.gtnewhorizon.cropsnh.utility;

import java.util.HashMap;

public class MetaMap<K, V> {

    private HashMap<K, HashMap<Integer, V>> map = new HashMap<>();

    /**
     * Inserts an item into meta map.
     *
     * @param key   The item or block to insert.
     * @param meta  The metadata of the block or item, -1 for any.
     * @param value The value ot insert.
     */
    public void put(K key, int meta, V value) {
        if (!map.containsKey(key)) {
            map.put(key, new HashMap<Integer, V>());
        }
        map.get(key)
            .put(meta, value);
    }

    /**
     * Inserts an item only if none were already present.
     *
     * @param key   The item or block to insert.
     * @param meta  The metadata of the block or item, -1 for any.
     * @param value The value ot insert.
     */
    public boolean putIfAbsent(K key, int meta, V value) {
        if (!map.containsKey(key)) {
            map.put(key, new HashMap<Integer, V>());
        }
        // skip if the meta already exists
        if (map.containsKey(-1) || map.containsKey(meta)) return false;
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
        // check if the key is
        if (!map.containsKey(key)) return null;
        HashMap<Integer, V> metaMap = map.get(key);
        // check if wildcard is present
        if (metaMap.containsKey(-1)) {
            return metaMap.get(-1);
        }
        return metaMap.get(meta);
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
        if (!map.containsKey(key)) return defaultValue;
        HashMap<Integer, V> metaMap = map.get(key);
        // check if wildcard is present
        if (metaMap.containsKey(-1)) {
            return metaMap.get(-1);
        }
        return metaMap.getOrDefault(meta, defaultValue);
    }

    /**
     * Checks if an item is already in the map.
     *
     * @param key  The item or block to insert.
     * @param meta The metadata of the block or item.
     * @return True if the item is set.
     */
    public boolean containsKey(K key, int meta) {
        if (!map.containsKey(key)) return false;
        HashMap<Integer, V> metaMap = map.get(key);
        return metaMap.containsKey(-1) || metaMap.containsKey(meta);
    }
}
