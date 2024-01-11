package com.gtnewhorizon.cropsnh.compatibility.harvestthenether;

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

public class HarvestTheNetherHelper extends ModHelper {

    @Override
    protected String modId() {
        return Names.Mods.harvestTheNether;
    }

    @Override
    protected void initPlants() {
        Item bloodSeed = (Item) Item.itemRegistry.getObject("harvestthenether:bloodleafseedItem");
        Item bloodFruit = (Item) Item.itemRegistry.getObject("harvestthenether:bloodleafItem");
        Block bloodPlant = (Block) Block.blockRegistry.getObject("harvestthenether:bloodleafCrop");
        if (bloodSeed != null && bloodFruit != null && bloodPlant != null) {
            try {
                CropPlant plant = new CropPlantHarvestTheNether(bloodSeed, bloodPlant, bloodFruit);
                CropPlantHandler.registerPlant(plant);
                plant.getGrowthRequirement().setSoil(new BlockWithMeta(Blocks.soul_sand));
            } catch (DuplicateCropPlantException e) {
                LogHelper.printStackTrace(e);
            }
        }

        Item fleshRootSeed = (Item) Item.itemRegistry.getObject("harvestthenether:fleshrootseedItem");
        Item fleshRootFruit = (Item) Item.itemRegistry.getObject("harvestthenether:fleshrootItem");
        Block fleshRootPlant = (Block) Block.blockRegistry.getObject("harvestthenether:fleshrootCrop");
        if (fleshRootSeed != null && fleshRootFruit != null && fleshRootPlant != null) {
            try {
                CropPlant plant = new CropPlantHarvestTheNether(fleshRootSeed, fleshRootPlant, fleshRootFruit);
                CropPlantHandler.registerPlant(plant);
                plant.getGrowthRequirement().setSoil(new BlockWithMeta(Blocks.soul_sand));
            } catch (DuplicateCropPlantException e) {
                LogHelper.printStackTrace(e);
            }
        }

        Item marrowBerrySeed = (Item) Item.itemRegistry.getObject("harvestthenether:marrowberryseedItem");
        Item marrowBerryFruit = (Item) Item.itemRegistry.getObject("harvestthenether:marrowberryItem");
        Block marrowBerryPlant = (Block) Block.blockRegistry.getObject("harvestthenether:marrowberryCrop");
        if (marrowBerrySeed != null && marrowBerryFruit != null && marrowBerryPlant != null) {
            try {
                CropPlant plant = new CropPlantHarvestTheNether(marrowBerrySeed, marrowBerryPlant, marrowBerryFruit);
                CropPlantHandler.registerPlant(plant);
                plant.getGrowthRequirement().setSoil(new BlockWithMeta(Blocks.soul_sand));
            } catch (DuplicateCropPlantException e) {
                LogHelper.printStackTrace(e);
            }
        }

        Item glowFlowerSeed = (Item) Item.itemRegistry.getObject("harvestthenether:glowflowerseedItem");
        Item glowFlowerFruit = (Item) Item.itemRegistry.getObject("harvestthenether:glowFlower");
        Block glowFlowerPlant = (Block) Block.blockRegistry.getObject("harvestthenether:glowflowerCrop");
        if (glowFlowerSeed != null && glowFlowerFruit != null && glowFlowerPlant != null) {
            try {
                CropPlant plant = new CropPlantHarvestTheNether(glowFlowerSeed, glowFlowerPlant, glowFlowerFruit);
                CropPlantHandler.registerPlant(plant);
            } catch (DuplicateCropPlantException e) {
                LogHelper.printStackTrace(e);
            }
        }
    }
}
