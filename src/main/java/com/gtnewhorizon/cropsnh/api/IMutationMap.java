package com.gtnewhorizon.cropsnh.api;

import java.util.List;
import java.util.Set;

public interface IMutationMap {

    /**
     * Registers a crop to the mutation map.
     * 
     * @param parents  The list of parents ordered in order of registration.
     * @param index    The index of the parent currently being examined.
     * @param mutation The mutation to register.
     */
    void register(List<ICropCard> parents, int index, ICropMutation mutation);

    /**
     * Finds all mutation recipes that match a group of parents.
     * 
     * @param parents The list of parents ordered in order of registration.
     * @param index   The index of the parent currently being examined.
     * @param matches The list of crops that match the current parent list.
     */
    void findMatches(List<ICropCard> parents, int index, Set<ICropMutation> matches);
}
