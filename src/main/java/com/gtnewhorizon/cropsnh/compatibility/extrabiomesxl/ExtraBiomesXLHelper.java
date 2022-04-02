package com.gtnewhorizon.cropsnh.compatibility.extrabiomesxl;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.farming.CropPlantHandler;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.exception.DuplicateCropPlantException;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ExtraBiomesXLHelper extends ModHelper {
    @Override
    protected void initPlants() {
        //strawberry
        Block plant = (Block) Block.blockRegistry.getObject("ExtrabiomesXL:tile.extrabiomes.crop.strawberry");
        Item seed = (Item) Item.itemRegistry.getObject("ExtrabiomesXL:extrabiomes.seed");
        Item fruit = (Item) Item.itemRegistry.getObject("ExtrabiomesXL:extrabiomes.crop");
        if(seed==null || plant==null || fruit==null) {
            return;
        }
        CropPlant strawberry = new CropPlantExtraBiomesXL(seed, plant, fruit);
        try {
            CropPlantHandler.registerPlant(strawberry);
        } catch (DuplicateCropPlantException e) {
            LogHelper.printStackTrace(e);
        }
    }

    @Override
    protected String modId() {
        return "ExtrabiomesXL";
    }
}
