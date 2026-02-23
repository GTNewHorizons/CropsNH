package com.gtnewhorizon.cropsnh.compatibility.fether;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import com.gtnewhorizon.cropsnh.api.v1.BlockWithMeta;
import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.farming.CropPlantHandler;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.exception.DuplicateCropPlantException;

import cpw.mods.fml.common.registry.GameRegistry;

public class FetherHelper extends ModHelper {

    @Override
    protected String modId() {
        return Names.Mods.fether;
    }

    @Override
    protected void initPlants() {
        Item bloodSeed = GameRegistry.findItem(Names.Mods.fether, "blood_leaf_seeds");
        Item bloodFruit = GameRegistry.findItem(Names.Mods.fether, "blood_leaf");
        Block bloodPlant = GameRegistry.findBlock(Names.Mods.fether, "blood_leaf_crop");
        if (bloodSeed != null && bloodFruit != null && bloodPlant != null) {
            try {
                CropPlant plant = new CropPlantFether(bloodSeed, bloodPlant, bloodFruit);
                CropPlantHandler.registerPlant(plant);
                plant.getGrowthRequirement().setSoil(new BlockWithMeta(Blocks.soul_sand));
            } catch (DuplicateCropPlantException e) {
                LogHelper.printStackTrace(e);
            }
        }

        Item fleshRootSeed = GameRegistry.findItem(Names.Mods.fether, "flesh_root_seeds");
        Item fleshRootFruit = GameRegistry.findItem(Names.Mods.fether, "flesh_root");
        Block fleshRootPlant = GameRegistry.findBlock(Names.Mods.fether, "flesh_root_crop");
        if (fleshRootSeed != null && fleshRootFruit != null && fleshRootPlant != null) {
            try {
                CropPlant plant = new CropPlantFether(fleshRootSeed, fleshRootPlant, fleshRootFruit);
                CropPlantHandler.registerPlant(plant);
                plant.getGrowthRequirement().setSoil(new BlockWithMeta(Blocks.soul_sand));
            } catch (DuplicateCropPlantException e) {
                LogHelper.printStackTrace(e);
            }
        }

        Item marrowBerrySeed = GameRegistry.findItem(Names.Mods.fether, "marrow_berry_seeds");
        Item marrowBerryFruit = GameRegistry.findItem(Names.Mods.fether, "marrow_berry");
        Block marrowBerryPlant = GameRegistry.findBlock(Names.Mods.fether, "marrow_berry_crop");
        if (marrowBerrySeed != null && marrowBerryFruit != null && marrowBerryPlant != null) {
            try {
                CropPlant plant = new CropPlantFether(marrowBerrySeed, marrowBerryPlant, marrowBerryFruit);
                CropPlantHandler.registerPlant(plant);
                plant.getGrowthRequirement().setSoil(new BlockWithMeta(Blocks.soul_sand));
            } catch (DuplicateCropPlantException e) {
                LogHelper.printStackTrace(e);
            }
        }

        Item glowFlowerSeed = GameRegistry.findItem(Names.Mods.fether, "glow_flower_seeds");
        Item glowFlowerFruit = GameRegistry.findItem(Names.Mods.fether, "glow_flower");
        Block glowFlowerPlant = GameRegistry.findBlock(Names.Mods.fether, "glow_flower_crop");
        if (glowFlowerSeed != null && glowFlowerFruit != null && glowFlowerPlant != null) {
            try {
                CropPlant plant = new CropPlantFether(glowFlowerSeed, glowFlowerPlant, glowFlowerFruit);
                CropPlantHandler.registerPlant(plant);
            } catch (DuplicateCropPlantException e) {
                LogHelper.printStackTrace(e);
            }
        }
    }
}
