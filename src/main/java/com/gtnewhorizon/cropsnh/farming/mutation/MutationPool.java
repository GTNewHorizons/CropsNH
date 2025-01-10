package com.gtnewhorizon.cropsnh.farming.mutation;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.IMutationPool;

public class MutationPool implements IMutationPool {

    public final String name;
    private final HashSet<ICropCard> pool = new HashSet<>();

    public MutationPool(String name) {
        this.name = name;
    }

    /**
     * Registers new crops to the mutation pool.
     *
     * @param newMembers The crops to add to the pool.
     */
    @Override
    public void register(ICropCard... newMembers) {
        pool.addAll(Arrays.asList(newMembers));
    }

    /**
     * @return The members of the pool
     */
    @Override
    public Collection<ICropCard> getMembers() {
        return this.pool;
    }

    /**
     * Checks if a crop is registered in the pool.
     *
     * @param crop The crop to look for
     * @return True if the crop is registered in the pool.
     */
    @Override
    public boolean contains(ICropCard crop) {
        return this.pool.contains(crop);
    }

    /**
     * Checks if the parents are a match for this collection.
     * In order for the collection to match at least distinct parents must match.
     *
     * @param parents The list of parent crops.
     * @return True if at least 2 of the parents are present in the pool.
     */
    @Override
    public boolean isMatch(Collection<ICropCard> parents) {
        int match = 0;
        for (ICropCard parent : new HashSet<>(parents)) {
            if (pool.contains(parent) && ++match >= 2) return true;
        }
        return false;
    }

    /**
     * Used to dump the contents of the pool at runtime.
     * 
     * @return Something that describes the content of the pool.
     */
    @Override
    public void dump(StringBuilder sb) {
        // note that it's empty instead of not displaying anything
        if (this.pool.isEmpty()) {
            sb.append("# empty");
            return;
        }
        for (ICropCard cc : this.pool) {
            sb.append("- ");
            sb.append(StatCollector.translateToLocal(cc.getUnlocalizedName()));
            sb.append(System.lineSeparator());
        }
        sb.delete(
            sb.length() - System.lineSeparator()
                .length(),
            sb.length());
    }
}
