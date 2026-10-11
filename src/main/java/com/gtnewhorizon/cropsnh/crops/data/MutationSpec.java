package com.gtnewhorizon.cropsnh.crops.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.gtnewhorizon.cropsnh.api.IBreedingRequirement;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.farming.mutation.CropMutation;
import com.gtnewhorizon.cropsnh.reference.Reference;

/**
 * A deterministic mutation whose parents are crop ids, resolved once every crop is registered.
 */
public final class MutationSpec {

    private String[] parentIds = new String[0];
    private final List<Consumer<CropMutation>> modifiers = new ArrayList<>();

    /**
     * @param ids 2 to 4 crop ids; ids without a mod prefix are CropsNH crops.
     */
    public MutationSpec parents(String... ids) {
        if (ids.length < 2 || ids.length > 4) {
            throw new IllegalArgumentException("A mutation needs 2 to 4 parents, got " + ids.length);
        }
        this.parentIds = Arrays.stream(ids)
            .map(MutationSpec::fullId)
            .toArray(String[]::new);
        return this;
    }

    public MutationSpec machineOnly() {
        this.modifiers.add(CropMutation::machineOnly);
        return this;
    }

    public MutationSpec requirement(IBreedingRequirement requirement) {
        this.modifiers.add(mutation -> mutation.addRequirement(requirement));
        return this;
    }

    String[] getParentIds() {
        return this.parentIds;
    }

    /**
     * @return the parents in order, or null if one of them isn't registered (usually because its mod is missing).
     */
    @Nullable
    ICropCard[] resolveParents(Function<String, ICropCard> lookup) {
        ICropCard[] parents = new ICropCard[this.parentIds.length];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = lookup.apply(this.parentIds[i]);
            if (parents[i] == null) return null;
        }
        return parents;
    }

    CropMutation create(ICropCard output, ICropCard[] parents) {
        CropMutation mutation;
        if (parents.length == 2) {
            mutation = new CropMutation(output, parents[0], parents[1]);
        } else if (parents.length == 3) {
            mutation = new CropMutation(output, parents[0], parents[1], parents[2]);
        } else {
            mutation = new CropMutation(output, parents[0], parents[1], parents[2], parents[3]);
        }
        this.modifiers.forEach(modifier -> modifier.accept(mutation));
        return mutation;
    }

    private static String fullId(String id) {
        return id.indexOf(':') >= 0 ? id : Reference.MOD_ID + ":" + id;
    }
}
