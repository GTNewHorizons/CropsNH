package com.gtnewhorizon.cropsnh.crops.stoneilies.botania;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropGraniteLily extends CropBaseStoneLily {

    public CropGraniteLily() {
        super("granite", new Color(0x82675B), new Color(0xBA9583));

        final int DROP_COUNT = 1;
        final int DROP_CHANCE = 100_00;
        if (ModUtils.Botania.isModLoaded() || ModUtils.EtFuturumRequiem.isModLoaded()) {
            this.addDrop(OreDictHelper.getCopiedOreStack("stoneGranite", DROP_COUNT), DROP_CHANCE);
        } else {
            this.addDrop(ModUtils.Chisel.getStack("granite", DROP_COUNT, 0), DROP_CHANCE);
        }

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.botaniaGranite);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
