package com.gtnewhorizon.cropsnh.farming.mutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.IBreedingRequirement;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropMutation;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.api.IMachineBreedingRequirement;
import com.gtnewhorizon.cropsnh.api.IWorldBreedingRequirement;
import com.gtnewhorizon.cropsnh.farming.registries.MutationRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.farming.requirements.breeding.MachineOnlyBreedingRequirement;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

public class CropMutation implements ICropMutation {

    public final ICropCard output;
    public final ArrayList<IBreedingRequirement> requirements = new ArrayList<>();
    public final ICropCard parent1;
    public final ICropCard parent2;
    public final ICropCard parent3;
    public final ICropCard parent4;
    private final HashSet<String> mutationPools = new HashSet<>();

    public CropMutation(ICropCard output, ICropCard parent1, ICropCard parent2) {
        this(output, parent1, parent2, null, null);
    }

    public CropMutation(ICropCard output, ICropCard parent1, ICropCard parent2, ICropCard parent3) {
        this(output, parent1, parent2, parent3, null);
    }

    public CropMutation(ICropCard output, ICropCard parent1, ICropCard parent2, ICropCard parent3, ICropCard parent4) {
        if (parent1 == null) throw new IllegalArgumentException("parent 1 cannot be null");
        if (parent2 == null) throw new IllegalArgumentException("parent 2 cannot be null");
        if (parent3 == null && parent4 != null)
            throw new IllegalArgumentException("parent 3 is null but parent 4 isn't something has gone wrong.");
        this.output = output;
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.parent3 = parent3;
        this.parent4 = parent4;
        // carry over any compatible growth requirements
        for (IGrowthRequirement req : output.getGrowthRequirements()) {
            if (req instanceof IBreedingRequirement) {
                this.requirements.add((IBreedingRequirement) req);
            }
        }
    }

    @Override
    public ICropCard getOutput() {
        return this.output;
    }

    @Override
    public Collection<ICropCard> getParents() {
        if (this.parent4 != null) {
            return Arrays.asList(this.parent1, this.parent2, this.parent3, this.parent4);
        }
        if (this.parent3 != null) {
            return Arrays.asList(this.parent1, this.parent2, this.parent3);
        }
        return Arrays.asList(this.parent1, this.parent2);
    }

    public CropMutation machineOnly() {
        this.requirements.add(new MachineOnlyBreedingRequirement());
        return this;
    }

    public CropMutation addRequirement(IBreedingRequirement req) {
        this.requirements.add(req);
        return this;
    }

    public CropMutation addToMutationPools(String... mutationPools) {
        // do not add them directly since we may add the machine only req later.
        this.mutationPools.addAll(Arrays.asList(mutationPools));
        return this;
    }

    public void addBlockUnderRequirement(String name) {
        this.requirements.add(BlockUnderRequirement.get(name));
    }

    public void register() {
        // Only register the crop to the mutation pools if the crop can be bred in world.
        if (this.requirements.stream()
            .noneMatch(x -> x instanceof MachineOnlyBreedingRequirement)) {
            for (String poolName : this.mutationPools) {
                MutationRegistry.instance.register(poolName, this.output);
            }
        }
        MutationRegistry.instance.register(this);
    }

    @Override
    public boolean canBreed(ArrayList<ICropCard> parents, World world, ICropStickTile tile, int x, int y, int z) {
        if (!checkParents(parents)) return false;
        for (IBreedingRequirement req : this.requirements) {
            if (!(req instanceof IWorldBreedingRequirement)) continue;
            if (!((IWorldBreedingRequirement) req).canBreed(parents, world, tile, x, y, z)) return false;
        }
        return true;
    }

    @Override
    public boolean canBreed(ArrayList<ICropCard> parents, TileEntity te, ItemStack[] catalysts) {
        if (!checkParents(parents)) return false;
        for (IBreedingRequirement req : this.requirements) {
            if (!(req instanceof IMachineBreedingRequirement)) continue;
            if (!((IMachineBreedingRequirement) req).canBreed(parents, te, catalysts)) return false;
        }
        return true;
    }

    private boolean checkParents(ArrayList<ICropCard> parents) {
        boolean p1 = parents.contains(this.parent1);
        boolean p2 = parents.contains(this.parent2);
        boolean p3 = this.parent3 == null || parents.contains(this.parent3);
        boolean p4 = this.parent4 == null || parents.contains(this.parent4);
        return p1 && p2 && p3 && p4;
    }

    @Override
    public List<IBreedingRequirement> getRequirements() {
        return this.requirements;
    }

    private List<ItemStack> cachedBlockUnder = null;

    @Override
    public List<ItemStack> getBlocksUnderForNEI(boolean useCache) {
        // check cache
        if (useCache && this.cachedBlockUnder != null) return this.cachedBlockUnder;
        // generate list
        LinkedList<ItemStack> stacks = new LinkedList<>();
        for (IBreedingRequirement req : this.requirements) {
            if (!(req instanceof BlockUnderRequirement)) continue;
            stacks.addAll(((BlockUnderRequirement) req).getItemsForNEI());
        }
        CropsNHUtils.deduplicateItemList(stacks);
        // update cache if we didn't hit it
        return this.cachedBlockUnder = stacks;
    }

}
