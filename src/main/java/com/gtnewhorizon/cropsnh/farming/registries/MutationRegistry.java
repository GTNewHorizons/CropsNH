package com.gtnewhorizon.cropsnh.farming.registries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.api.IBreedingRequirement;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropMutation;
import com.gtnewhorizon.cropsnh.api.IMutationPool;
import com.gtnewhorizon.cropsnh.api.IMutationRegistry;
import com.gtnewhorizon.cropsnh.farming.mutation.MutationMap;
import com.gtnewhorizon.cropsnh.farming.mutation.MutationPool;
import com.gtnewhorizon.cropsnh.utility.DebugHelper;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

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
    @Override
    public void register(String poolId, ICropCard... crops) {
        for (ICropCard crop : crops) {
            this.registerInternal(crop, poolId);
        }
    }

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
    @Override
    public void register(ICropCard crop, String... poolIds) {
        for (String poolId : poolIds) {
            this.registerInternal(crop, poolId);
        }
    }

    /**
     * Registers a crop to a mutation pool.
     *
     * @param crop   If the crop
     * @param poolId The id of the pool to register the crop to.
     */
    private void registerInternal(ICropCard crop, String poolId) {
        // register to pool first
        if (!this.pools.containsKey(poolId)) {
            this.pools.putIfAbsent(poolId, new MutationPool(poolId));
        }
        IMutationPool pool = this.pools.get(poolId);
        pool.register(crop);
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

    public void pruneMutationPools() {
        for (Iterator<Map.Entry<String, IMutationPool>> iter = this.pools.entrySet()
            .iterator(); iter.hasNext();) {
            Map.Entry<String, IMutationPool> entry = iter.next();
            // remove all pools that have 2 members or less since spreading would result in the same behaviour.
            if (entry.getValue()
                .getMembers()
                .size() <= 2) {
                LogHelper.debug(
                    "Pruning Mutation Pool (size is " + entry.getValue()
                        .getMembers()
                        .size() + "): " + entry.getKey());
                iter.remove();
            }
        }
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

    /**
     * @return a csv dump of all the registered deterministic mutations
     */
    public String dumpDeterministicMutations() {
        StringBuilder sb = new StringBuilder();
        sb.append(DebugHelper.makeCSVLine("output", "parent1", "parent2", "parent3", "parent4", "conditions"));
        sb.append(System.lineSeparator());
        this.map.dump()
            .forEach(m -> {
                StringBuilder sbm = new StringBuilder();
                sbm.append(
                    DebugHelper.sanitizeCSVString(
                        StatCollector.translateToLocal(
                            m.getOutput()
                                .getUnlocalizedName())));
                int i = 0;
                for (ICropCard cc : m.getParents()) {
                    sbm.append(",");
                    sbm.append(DebugHelper.sanitizeCSVString(StatCollector.translateToLocal(cc.getUnlocalizedName())));
                    i++;
                }
                for (; i < 4; i++) {
                    sbm.append(",");
                }
                Collection<IBreedingRequirement> reqs = m.getRequirements();
                if (reqs != null && !reqs.isEmpty()) {
                    for (IBreedingRequirement req : reqs) {
                        sbm.append(",");
                        sbm.append(DebugHelper.sanitizeCSVString(req.getDescription()));
                    }
                }
                sbm.append(System.lineSeparator());
                sb.append(sbm);
            });
        sb.delete(
            sb.length() - System.lineSeparator()
                .length(),
            sb.length());
        return sb.toString();
    }

    /**
     * @return a text dump of all the registered mutation pools
     */
    public String dumpMutationPools() {
        StringBuilder sb = new StringBuilder();
        List<String> poolIds = new ArrayList<>(this.pools.keySet());
        poolIds.sort(Comparator.comparing(x -> StatCollector.translateToLocal("cropsnh_mutationPool." + x)));
        if (this.pools.isEmpty()) return "Empty";
        sb.append("Crop");
        for (String poolId : poolIds) {
            sb.append(",");
            sb.append(DebugHelper.sanitizeCSVString(StatCollector.translateToLocal("cropsnh_mutationPool." + poolId)));
        }
        if (CropRegistry.instance.getAllInRegistrationOrder()
            .isEmpty()) {
            sb.append(System.lineSeparator());
            sb.append("Empty");
        } else {
            boolean noMutableCrops = true;
            for (ICropCard cc : CropRegistry.instance.getAllInRegistrationOrder()
                .stream()
                .sorted(Comparator.comparing(x -> StatCollector.translateToLocal(x.getUnlocalizedName())))
                .collect(Collectors.toList())) {
                boolean found = false;
                StringBuilder sbm = new StringBuilder();
                for (String poolId : poolIds) {
                    sbm.append(",");
                    if (this.pools.get(poolId)
                        .contains(cc)) {
                        found = true;
                        sbm.append("TRUE");
                    } else {
                        sbm.append("FALSE");
                    }
                }
                // only append if the crop is part of a mutation pool
                if (found) {
                    noMutableCrops = false;
                    sb.append(System.lineSeparator());
                    sb.append(DebugHelper.sanitizeCSVString(StatCollector.translateToLocal(cc.getUnlocalizedName())));
                    sb.append(sbm);
                }
            }
            if (noMutableCrops) {
                sb.append(System.lineSeparator());
                sb.append("Mutation pools are all empty");
            }
        }
        return sb.toString();
    }
}
