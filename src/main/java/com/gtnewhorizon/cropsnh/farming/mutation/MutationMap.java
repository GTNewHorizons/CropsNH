package com.gtnewhorizon.cropsnh.farming.mutation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropMutation;
import com.gtnewhorizon.cropsnh.api.IMutationMap;

public class MutationMap implements IMutationMap {

    ArrayList<ICropMutation> list = new ArrayList<>(1);
    private final HashMap<ICropCard, MutationMap> map = new HashMap<>();

    /**
     * Registers a crop to the mutation map.
     *
     * @param parents  The list of parents ordered in order of registration.
     * @param index    The index of the parent currently being examined.
     * @param mutation The mutation to register.
     */
    @Override
    public void register(List<ICropCard> parents, int index, ICropMutation mutation) {
        if (index < 0) throw new IllegalArgumentException("parent index cannot be negative");
        if (parents.size() <= index) {
            if (index < 2)
                throw new IllegalArgumentException("Mutation recipes should always at least specify 2 parents");
            list.add(mutation);
            return;
        }
        ICropCard parent = parents.get(index);
        if (!map.containsKey(parent)) {
            map.put(parent, new MutationMap());
        }
        map.get(parent)
            .register(parents, index + 1, mutation);
    }

    /**
     * Finds all mutation recipes that match a group of parents.
     *
     * @param parents The list of parents ordered in order of registration.
     * @param index   The index of the parent currently being examined.
     * @param matches The list of crops that match the current parent list.
     */
    @Override
    public void findMatches(List<ICropCard> parents, int index, Set<ICropMutation> matches) {
        if (index < 0) throw new IllegalArgumentException("parent index cannot be negative");
        // Deterministic mutations should NEVER have less than 2 distinct parents.
        if (index >= 2) matches.addAll(list);
        if (parents.size() <= index) return;
        ICropCard parent = parents.get(index);
        if (!map.containsKey(parent)) return;
        map.get(parent)
            .findMatches(parents, index + 1, matches);

    }
}
