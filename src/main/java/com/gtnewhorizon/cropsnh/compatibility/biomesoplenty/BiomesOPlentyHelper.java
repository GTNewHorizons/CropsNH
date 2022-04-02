package com.gtnewhorizon.cropsnh.compatibility.biomesoplenty;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.farming.CropPlantHandler;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.exception.DuplicateCropPlantException;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BiomesOPlentyHelper extends ModHelper {
    @Override
    protected void initPlants() {
        //turnip
        Block plant = (Block) Block.blockRegistry.getObject("BiomesOPlenty:turnip");
        Item seed = (Item) Item.itemRegistry.getObject("BiomesOPlenty:turnipSeeds");
        Item fruit = (Item) Item.itemRegistry.getObject("BiomesOPlenty:food");
        CropPlant strawberry = new CropPlantBiomesOPlenty(seed, plant, new ItemStack(fruit, 1, 11));
        if(seed==null || plant==null || fruit==null) {
            return;
        }
        try {
            CropPlantHandler.registerPlant(strawberry);
        } catch (DuplicateCropPlantException e) {
            LogHelper.printStackTrace(e);
        }
    }

    @Override
    protected String modId() {
        return "BiomesOPlenty";
    }
}
