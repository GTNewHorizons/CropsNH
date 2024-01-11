package com.gtnewhorizon.cropsnh.compatibility.minetweaker;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.farming.CropPlantHandler;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.cropsnh.SpreadChance")
public class SpreadChance {

    @ZenMethod
    public static void override(IItemStack seed, int chance) {
        ItemStack seedToOverride = MineTweakerMC.getItemStack(seed);
        if (CropPlantHandler.isValidSeed(seedToOverride)) {
            if (chance >= 0 && chance <= 100) {
                MineTweakerAPI.apply(new OverrideAction(CropPlantHandler.getPlantFromStack(seedToOverride), chance));
            } else {
                MineTweakerAPI.logError("Spread chance must be between 0 and 100 inclusive.");
            }
        } else {
            MineTweakerAPI.logError("Spread chance can only be overwritten for seed recognized by cropsnh.");
        }
    }

    private static class OverrideAction implements IUndoableAction {

        private final CropPlant plant;
        private final int chance;
        private final int oldChance;

        public OverrideAction(CropPlant plant, int chance) {
            this.plant = plant;
            this.chance = chance;
            this.oldChance = plant.getSpreadChance();
        }

        @Override
        public void apply() {
            plant.setSpreadChance(chance);
        }

        @Override
        public boolean canUndo() {
            return true;
        }

        @Override
        public void undo() {
            plant.setSpreadChance(oldChance);
        }

        @Override
        public String describe() {
            return "Overriding spread chance of " + plant.getSeed().getDisplayName() + " to " + chance;
        }

        @Override
        public String describeUndo() {
            return "Resetting spread chance of " + plant.getSeed().getDisplayName() + " to " + oldChance;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }
    }
}
