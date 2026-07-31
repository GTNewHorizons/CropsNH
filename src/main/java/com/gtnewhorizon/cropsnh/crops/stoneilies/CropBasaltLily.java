package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;

import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.materials.Materials;
import gregtech.api.util.GTOreDictUnificator;

public class CropBasaltLily extends CropBaseStoneLily {

    public CropBasaltLily() {
        super("basalt", new Color(0x080808), new Color(0x181818));

        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Basalt, 9), 100_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.basalt);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
