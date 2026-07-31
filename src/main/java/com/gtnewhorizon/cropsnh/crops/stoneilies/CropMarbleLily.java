package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;

import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.materials.Materials;
import gregtech.api.util.GTOreDictUnificator;

public class CropMarbleLily extends CropBaseStoneLily {

    public CropMarbleLily() {
        super("marble", new Color(0xC8C8C8), new Color(0xF0F0F0));

        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Marble, 9), 100_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.marble);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
