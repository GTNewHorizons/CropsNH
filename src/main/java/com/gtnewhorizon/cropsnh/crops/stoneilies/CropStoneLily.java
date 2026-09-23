package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.ruling_0.materiallib.api.MaterialLibAPI;

import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;

public class CropStoneLily extends CropBaseStoneLily {

    public CropStoneLily() {
        super("stone", new Color(0x919191), new Color(0xCDCDCD));

        this.addDrop(MaterialLibAPI.getStack(Materials.Stone, Shapes.dust, 9), 100_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.stone);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
