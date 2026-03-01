package com.gtnewhorizon.cropsnh.tileentity.multi;

import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.Nullable;

import com.cleanroommc.modularui.utils.item.ItemStackHandler;
import com.gtnewhorizon.cropsnh.api.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.farming.requirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.items.ItemEnvironmentalModule;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import gregtech.api.util.GTUtility;

public class MTEIndustrialFarmItemStackHandler extends ItemStackHandler {

    private final MTEIndustrialFarm multiblock;

    public MTEIndustrialFarmItemStackHandler(MTEIndustrialFarm multiblock) {
        super(4);
        this.multiblock = multiblock;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return super.insertItem(slot, stack, simulate);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        // prevent insertion while the machine is running or if the stack is bad.
        if (multiblock.mMaxProgresstime != 0 || multiblock.isAllowedToWork() || CropsNHUtils.isStackInvalid(stack)) {
            return false;
        }

        switch (slot) {
            case MTEIndustrialFarm.SLOT_SEED -> {
                ISeedData tSeedData = CropsNHUtils.getAnalyzedSeedData(stack);
                if (tSeedData == null) return false;
                if (tSeedData.getCrop()
                    .getMinSeedBedTier() > multiblock.mUpgradeTier) return false;
                // prevent manual insertion of seeds that have underblock requirements.
                for (IGrowthRequirement req : tSeedData.getCrop()
                    .getGrowthRequirements()) {
                    if (req instanceof BlockUnderRequirement blockUnderReq) {
                        return false;
                    }
                }
                // check if the seed can grow in the multi.
                return multiblock.getGrowthSpeedUnscaled(tSeedData) > 0;
            }
            case MTEIndustrialFarm.SLOT_BLOCK_UNDER -> {
                // block insertions of blocks manually for now
                // we'll need a much more complex solution to enable
                // adding blocks and seeds at the same time, and the current
                // frameworks though MUI2 just aren't good enough.
                // We'll probably need to jerry rig something if we ever want that to work.
                return false;
            }
            default -> {
                slot -= MTEIndustrialFarm.SLOT_ENV_CARD_START;
                // check if the slot is unlocked
                if (slot < 0 || multiblock.mEnvironmentalEnhancementUnitCount <= slot) return false;
                // must be an environmental module
                if (!(stack.getItem() instanceof ItemEnvironmentalModule)) return false;
                // the module cannot be blank.
                return ItemEnvironmentalModule.getBiomeTag(CropsNHUtils.getItemMeta(stack)) != null;
            }
        }
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (multiblock.mMaxProgresstime != 0 || multiblock.isAllowedToWork()) return null;
        if (slot < MTEIndustrialFarm.SLOT_ENV_CARD_START) {
            // force to only allow output by output mode if a block under is present.
            ItemStack blockUnderStack = multiblock.getBlockUnderStack();
            if (GTUtility.isStackValid(blockUnderStack) && blockUnderStack.stackSize > 0) return null;
        }
        return super.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return switch (slot) {
            case MTEIndustrialFarm.SLOT_SEED, MTEIndustrialFarm.SLOT_BLOCK_UNDER -> this.multiblock.mSeedCapacity;
            default -> 1;
        };
    }

    @Override
    protected int getStackLimit(int slot, @Nullable ItemStack stack) {
        if (stack == null) {
            return 0;
        }
        return switch (slot) {
            case MTEIndustrialFarm.SLOT_SEED, MTEIndustrialFarm.SLOT_BLOCK_UNDER -> this.multiblock.mSeedCapacity;
            default -> 1;
        };
    }

    @Override
    protected void onContentsChanged(int slot) {
        this.multiblock.onContentsChanged(slot);
    }
}
