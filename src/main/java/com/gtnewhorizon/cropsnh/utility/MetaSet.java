package com.gtnewhorizon.cropsnh.utility;

import java.util.HashMap;
import java.util.HashSet;

public class MetaSet<K> {

    private HashMap<K, HashSet<Integer>> map = new HashMap<>();

    /**
     * Adds an item to the set.
     *
     * @param key  The item or block to insert.
     * @param meta The metadata of the block or item, -1 for any.
     * @return True if the value didn't alrady exist.
     */
    public boolean add(K key, int meta) {
        // wildcard goes first
        if (meta == -1) {
            map.put(key, null);
            return true;
        }
        // check we need to add a new meta set
        if (!map.containsKey(key)) {
            map.put(key, new HashSet<Integer>());
        }
        // set
        return map.get(key)
            .add(meta);
    }

    private static final HashSet<Integer> UNKNOWN_KEY = new HashSet<>();

    /**
     * Gets the value for an item in the map
     *
     * @param key  The item or block to insert.
     * @param meta The metadata of the block or item.
     * @return The value ot insert.
     */
    public boolean contains(K key, int meta) {
        HashSet<Integer> metaSet = map.getOrDefault(key, UNKNOWN_KEY);
        // check if the key invalid
        if (metaSet == UNKNOWN_KEY) return false;
        // else return true if it's null (wildcard) or if the meta exists in the set
        return metaSet == null || metaSet.contains(meta);
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }
}
