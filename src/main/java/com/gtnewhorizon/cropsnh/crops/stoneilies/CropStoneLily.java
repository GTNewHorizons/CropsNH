package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;

import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.materials.Materials;
import gregtech.api.util.GTOreDictUnificator;

public class CropStoneLily extends CropBaseStoneLily {

    public CropStoneLily() {
        super("stone", new Color(0x919191), new Color(0xCDCDCD));

        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Stone, 9), 100_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.stone);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
