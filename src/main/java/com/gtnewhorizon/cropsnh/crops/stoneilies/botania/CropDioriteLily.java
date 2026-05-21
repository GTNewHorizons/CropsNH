package com.gtnewhorizon.cropsnh.crops.stoneilies.botania;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropDioriteLily extends CropBaseStoneLily {

    public CropDioriteLily() {
        super("diorite", new Color(0x909091), new Color(0xCECED0));

        final int DROP_COUNT = 1;
        final int DROP_CHANCE = 100_00;
        if (ModUtils.Botania.isModLoaded() || ModUtils.EtFuturumRequiem.isModLoaded()) {
            this.addDrop(OreDictHelper.getCopiedOreStack("stoneDiorite", DROP_COUNT), DROP_CHANCE);
        } else {
            this.addDrop(ModUtils.Chisel.getStack("diorite", DROP_COUNT, 0), DROP_CHANCE);
        }

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.botaniaDiorite);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
