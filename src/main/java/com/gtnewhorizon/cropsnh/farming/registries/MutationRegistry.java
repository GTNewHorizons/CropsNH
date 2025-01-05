package com.gtnewhorizon.cropsnh.farming.registries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropMutation;
import com.gtnewhorizon.cropsnh.api.IMutationPool;
import com.gtnewhorizon.cropsnh.api.IMutationRegistry;
import com.gtnewhorizon.cropsnh.farming.mutation.MutationMap;
import com.gtnewhorizon.cropsnh.farming.mutation.MutationPool;

public class MutationRegistry implements IMutationRegistry {

    public final static MutationRegistry instance = new MutationRegistry();

    private final MutationMap map = new MutationMap();
    private final HashMap<String, IMutationPool> pools = new HashMap<>();

    public MutationRegistry() {

    }

    /**
     * Used to register a deterministic breeding mutation.
     *
     * @param mutation The mutation to register
     */
    @Override
    public void register(ICropMutation mutation) {
        List<ICropCard> parents = createLookupQueue(mutation.getParents());
        if (parents.size() < 2)
            throw new IllegalArgumentException("Crop mutations should not have less than 2 parents");
        map.register(parents, 0, mutation);
    }

    /**
     * Used to register a crop to a breeding pool, crops within the same breeding pool can breed with each other
     * to create any other crop from that breeding pool as long as all the parents don't match.
     *
     * @param poolName   The name of the pool.
     * @param newMembers The members to add to the mutation pool.
     * @return The mutation pool that was registered.
     */
    @Override
    public IMutationPool register(String poolName, ICropCard... newMembers) {
        if (!pools.containsKey(poolName)) {
            pools.putIfAbsent(poolName, new MutationPool(poolName));
        }
        IMutationPool pool = pools.get(poolName);
        pool.register(newMembers);
        return pool;
    }

    /**
     * Gets a list of all possible deterministic mutations for a list of parents.
     *
     * @param parents The parents to filter with.
     * @return The list of mutations that can occur.
     */
    @Override
    public List<ICropMutation> getPossibleDeterministicMutations(Collection<ICropCard> parents) {
        if (parents == null || parents.size() < 2) return null;
        List<ICropCard> sortedParents = createLookupQueue(parents);

        // recheck because we also remove duplicates
        if (sortedParents.size() < 2) return null;
        HashSet<ICropMutation> accumulator = new HashSet<>();
        this.map.findMatches(sortedParents, 0, accumulator);

        // only return non-null if we got something
        if (accumulator.isEmpty()) return null;
        return new ArrayList<>(accumulator);
    }

    /**
     * Gets a list of all possible random mutations for a list of parents.
     *
     * @param parents The parents to filter with.
     * @return The list of mutations that can occur.
     */
    @Override
    public List<IMutationPool> getPossiblePoolMutations(Collection<ICropCard> parents) {
        if (parents == null || parents.size() < 2) return null;
        List<ICropCard> sortedParents = createLookupQueue(parents);

        // recheck because we also remove duplicates
        if (sortedParents.size() < 2) return null;
        List<IMutationPool> validPools = this.pools.values()
            .parallelStream()
            .filter(p -> p.isMatch(sortedParents))
            .collect(Collectors.toList());

        // only return non-null if we got something
        if (validPools.isEmpty()) return null;
        return validPools;
    }

    /**
     * @param parents The parents to use as a lookup table.
     * @return The list of distinct parents sorted by registration order.
     */
    private static List<ICropCard> createLookupQueue(Collection<ICropCard> parents) {
        return parents.stream()
            .sorted(Comparator.comparing(ICropCard::getNumericId))
            .distinct()
            .collect(Collectors.toList());
    }
}
