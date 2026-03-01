package com.gtnewhorizon.cropsnh.crops.stoneilies.botania;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropDioriteLily extends CropBaseStoneLily {

    public CropDioriteLily() {
        super("diorite", new Color(0x909091), new Color(0xCECED0));

        this.addDrop(OreDictHelper.getCopiedOreStack("stoneDiorite", 1), 100_00);

        this.addBlockUnderRequirement("botaniaDiorite");

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
