package com.gtnewhorizon.cropsnh.crops.stoneilies.botania;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropAndesiteLily extends CropBaseStoneLily {

    public CropAndesiteLily() {
        super("andesite", new Color(0x6B6B6A), new Color(0x989896));

        this.addDrop(OreDictHelper.getCopiedOreStack("stoneAndesite", 1), 100_00);

        this.addBlockUnderRequirement("botaniaAndesite");

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
