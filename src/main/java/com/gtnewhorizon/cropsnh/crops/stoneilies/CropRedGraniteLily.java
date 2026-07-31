package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;

import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.materials.Materials;
import gregtech.api.util.GTOreDictUnificator;

public class CropRedGraniteLily extends CropBaseStoneLily {

    public CropRedGraniteLily() {
        super("redGranite", new Color(0x871C52), new Color(0xD42C80));

        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.GraniteRed, 9), 100_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.redGranite);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
