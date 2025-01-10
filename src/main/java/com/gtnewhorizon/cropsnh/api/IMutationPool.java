package com.gtnewhorizon.cropsnh.api;

import java.util.Collection;

public interface IMutationPool {

    /**
     * Registers new crops to the mutation pool.
     *
     * @param newMembers The crops to add to the pool.
     */
    void register(ICropCard... newMembers);

    /**
     * @return The members of the pool
     */
    Collection<ICropCard> getMembers();

    /**
     * Checks if a crop is registered in the pool.
     *
     * @param crop The crop to look for
     * @return True if the crop is registered in the pool.
     */
    boolean contains(ICropCard crop);

    /**
     * Checks if the parents are a match for this collection.
     * In order for the collection to match at least distinct parents must match.
     *
     * @param parents The list of parent crops.
     * @return True if at least 2 of the parents are present in the pool.
     */
    boolean isMatch(Collection<ICropCard> parents);

    /**
     * Used to dump the contents of the pool at runtime.
     * 
     * @return Something that describes the content of the pool.
     */
    void dump(StringBuilder sb);
}
