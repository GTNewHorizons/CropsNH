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
     * Used to register a crop to a breeding pool.
     *
     * Crops within the same breeding pool can breed with each other to create any other crop from that breeding pool as
     * long as the parents aren't all the same species and aren't the ingredients for a deterministic breeding recipe.
     *
     * Crops that cannot be bred using in-world breeding should not be registered to the mutation pools.
     *
     * @param poolId The id of the mutation pool.
     * @param crops  The crops to add to the mutation pool.
     */
    void register(String poolId, ICropCard... crops);

    /**
     * Used to register a crop to a breeding pool.
     *
     * Crops within the same breeding pool can breed with each other to create any other crop from that breeding pool as
     * long as the parents aren't all the same species and aren't the ingredients for a deterministic breeding recipe.
     *
     * Crops that cannot be bred using in-world breeding should not be registered to the mutation pools.
     *
     * @param crop    The crop card to register.
     * @param poolIds The ids of the pools to register the crop to.
     */
    void register(ICropCard crop, String... poolIds);

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
