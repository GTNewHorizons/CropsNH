package com.gtnewhorizon.cropsnh.utility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

import net.minecraftforge.oredict.OreDictionary;

public class MetaSet<K> {

    // null keys = wildcard
    private final HashMap<K, HashSet<Integer>> map = new HashMap<>();

    /**
     * Adds an item to the set.
     *
     * @param key  The item or block to insert.
     * @param meta The metadata of the block or item, -1 for any.
     * @return True if the value didn't alrady exist.
     */
    public boolean add(K key, int meta) {
        // wildcard goes first
        if (isWildCard(meta)) {
            map.put(key, null);
            return true;
        }
        // check we need to add a new meta set
        if (!map.containsKey(key)) {
            map.put(key, new HashSet<>());
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
        HashSet<Integer> set = map.getOrDefault(key, UNKNOWN_KEY);
        // check if the key invalid
        if (set == UNKNOWN_KEY) return false;
        // else return true if it's null (wildcard) or if the meta exists in the set
        return set == null || set.contains(meta);
    }

    // both -1 and the ore dict can be used as wildcards for compatibility reasons.
    private static boolean isWildCard(int meta) {
        return meta == -1 || meta == OreDictionary.WILDCARD_VALUE;
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public Stream<Entry<K>> getStream() {
        return map.entrySet()
            .stream()
            .flatMap(e -> {
                if (e.getValue() == null) {
                    return Stream.of(new Entry<K>(e.getKey(), null));
                }
                return e.getValue()
                    .stream()
                    .map(meta -> new Entry<K>(e.getKey(), meta));
            });
    }

    public static class Entry<K> {

        public final K key;
        public final Integer meta;

        Entry(K key, Integer meta) {
            this.key = key;
            this.meta = meta;
        }
    }
}
