package com.gtnewhorizon.cropsnh.farming.mutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
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

import gregtech.api.enums.GTValues;
import gregtech.api.enums.TierEU;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GTRecipeBuilder;

public class CropMutation implements ICropMutation {

    public final ICropCard output;
    public final ArrayList<IBreedingRequirement> requirements = new ArrayList<>();
    public final ICropCard parent1;
    public final ICropCard parent2;
    public final ICropCard parent3;
    public final ICropCard parent4;
    public int breedingMachineEUt = (int) TierEU.RECIPE_LV;
    public int breedingMachineDuration = 400;
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
        HashSet<ICropCard> duplicateParentChecker = new HashSet<>();
        duplicateParentChecker.add(parent1);
        if (!duplicateParentChecker.add(parent2)) {
            throw new IllegalArgumentException(
                "duplicate mutation parent detected for mutation of " + output.getUnlocalizedName()
                    + " @ parent2: "
                    + parent2.getUnlocalizedName());
        }
        if (parent3 != null && !duplicateParentChecker.add(parent3)) {
            throw new IllegalArgumentException(
                "duplicate mutation parent detected for mutation of " + output.getUnlocalizedName()
                    + " @ parent3: "
                    + parent2.getUnlocalizedName());
        }
        if (parent4 != null && !duplicateParentChecker.add(parent4)) {
            throw new IllegalArgumentException(
                "duplicate mutation parent detected for mutation of " + output.getUnlocalizedName()
                    + " @ parent4: "
                    + parent2.getUnlocalizedName());
        }
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

        this.setBreedingMachineRecipeTier(this.output.getMachineBreedingRecipeTier());
    }

    @Override
    public ICropCard getOutput() {
        return this.output;
    }

    @Override
    public int getParentCount() {
        if (parent4 != null) return 4;
        if (parent3 != null) return 3;
        return 2;
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

    /**
     * Makes the mutation only possible with the breeding machine.
     */
    public CropMutation machineOnly() {
        this.requirements.add(new MachineOnlyBreedingRequirement());
        return this;
    }

    /**
     * Adds a breeding requirement to this mutation.
     *
     * @param req The requirement to add.
     */
    public CropMutation addRequirement(IBreedingRequirement req) {
        this.requirements.add(req);
        return this;
    }

    /**
     * Registers the output crop for the given mutation pools.
     *
     * @param mutationPools The mutation pools to add to the output crop.
     */
    public CropMutation addToMutationPools(String... mutationPools) {
        // do not add them directly since we may add the machine only req later.
        this.mutationPools.addAll(Arrays.asList(mutationPools));
        return this;
    }

    /**
     * Registers an underblock requirement via it's category id
     *
     * @param name the name of the underblock category
     */
    public CropMutation addBlockUnderRequirement(String name) {
        this.requirements.add(BlockUnderRequirement.get(name));
        return this;
    }

    /**
     * Removes the existing blockunder requirements
     */
    public CropMutation removeExistingBlockUnderRequirements() {
        this.requirements.removeIf(x -> x instanceof BlockUnderRequirement);
        return this;
    }

    /**
     * Sets the breeding machine recipe eu/t and duration based on a tier value.
     * See code for actual math.
     *
     * @param tier The tier value to use for the calculations.
     */
    public CropMutation setBreedingMachineRecipeTier(int tier) {
        this.breedingMachineEUt = (int) GTValues.VP[Math.max(VoltageIndex.LV, Math.min(VoltageIndex.UMV, tier))];
        this.breedingMachineDuration = (int) (20 * GTRecipeBuilder.SECONDS * Math.pow(1.3, Math.max(0, tier - 1)));
        return this;
    }

    /**
     * Sets the crop breeder eu/t cost to an arbitrary value.
     *
     * @param eut The eu/t of the recipe in the crop breeder.
     */
    public CropMutation setBreedingMachineRecipeEUt(int eut) {
        this.breedingMachineEUt = eut;
        return this;
    }

    /**
     * Sets the crop breeder eu/t cost to an arbitrary value.
     *
     * @param eut The eu/t of the recipe in the crop breeder.
     */
    public CropMutation setBreedingMachineRecipeEUt(long eut) {
        this.breedingMachineEUt = (int) eut;
        return this;
    }

    /**
     * Sets the crop breeder recipe duration to an arbitrary value.
     *
     * @param ticks The eu/t of the recipe in the crop breeder.
     */
    public CropMutation setBreedingMachineRecipeDuration(int ticks) {
        this.breedingMachineDuration = ticks;
        return this;
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
    public @Nullable int[] canBreed(ArrayList<ICropCard> parents, IGregTechTileEntity te, ItemStack[] catalysts) {
        if (!checkParents(parents)) return null;
        int[] consumptionTracker = new int[catalysts.length];
        for (IBreedingRequirement req : this.requirements) {
            if (!(req instanceof IMachineBreedingRequirement)) continue;
            if (!((IMachineBreedingRequirement) req).canBreed(parents, te, catalysts, consumptionTracker)) return null;
        }
        return consumptionTracker;
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

    private List<ItemStack> cachedBlockUnderForNEI = null;

    @Override
    public List<ItemStack> getBlocksUnderForNEI(boolean useCache) {
        // check cache
        if (useCache && this.cachedBlockUnderForNEI != null) return this.cachedBlockUnderForNEI;
        // generate list
        LinkedList<ItemStack> stacks = new LinkedList<>();
        for (IBreedingRequirement req : this.requirements) {
            if (!(req instanceof BlockUnderRequirement blockUnderRequirement)) continue;
            stacks.addAll(blockUnderRequirement.getItemsForNEI());
        }
        CropsNHUtils.deduplicateItemList(stacks);
        // update cache if we didn't hit it
        return this.cachedBlockUnderForNEI = stacks;
    }

    private List<List<ItemStack>> cachedBreedingMachineCatalystsForNEI = null;

    @Override
    public List<List<ItemStack>> getBreedingMachineCatalystsForNEI(boolean useCache) {
        // check cache
        if (useCache && this.cachedBreedingMachineCatalystsForNEI != null)
            return this.cachedBreedingMachineCatalystsForNEI;
        // generate list
        List<List<ItemStack>> ret = new LinkedList<>();
        // add artificial catalyst
        for (IBreedingRequirement req : this.requirements) {
            if (!(req instanceof IMachineBreedingRequirement machineReq)) continue;
            // abort if no catalysts
            List<ItemStack> catalysts = machineReq.getMachineOnlyCatalystsForNEI();
            // abort if no catalyst needed
            if (catalysts == null || catalysts.isEmpty()) continue;
            CropsNHUtils.deduplicateItemList(catalysts);
            // else add the catalysts to the list
            ret.add(catalysts);
        }
        return this.cachedBreedingMachineCatalystsForNEI = ret;
    }

    @Override
    public int getBreedingMachineRecipeEUt() {
        return this.breedingMachineEUt;
    }

    @Override
    public int getBreedingMachineRecipeDuration() {
        return this.breedingMachineDuration;
    }
}
