package com.gtnewhorizon.cropsnh.api;

import java.util.Collection;
import java.util.List;

public interface IMutationRegistry {

    /**
     * Used to register a deterministic breeding mutation.
     *
     * @param mutation The mutation to register
     */
    void register(ICropMutation mutation);

    /**
     * Used to register a crop to a breeding pool, crops within the same breeding pool can breed with each other
     * to create any other crop from that breeding pool as long as all the parents don't match.
     *
     * @param poolName   The name of the pool.
     * @param newMembers The members to add to the mutation pool.
     * @return The mutation pool that was registered.
     */
    IMutationPool register(String poolName, ICropCard... newMembers);

    /**
     * Gets a list of all possible deterministic mutations for a list of parents.
     *
     * @param parents The parents to filter with.
     * @return The list of mutations that can occur.
     */
    List<ICropMutation> getPossibleDeterministicMutations(Collection<ICropCard> parents);

    /**
     * Gets a list of all possible random mutations for a list of parents.
     *
     * @param parents The parents to filter with.
     * @return The list of mutations that can occur.
     */
    List<IMutationPool> getPossiblePoolMutations(Collection<ICropCard> parents);
}
