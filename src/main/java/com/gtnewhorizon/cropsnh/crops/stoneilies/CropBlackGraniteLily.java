package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.ruling_0.materiallib.api.MaterialLibAPI;

import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;

public class CropBlackGraniteLily extends CropBaseStoneLily {

    public CropBlackGraniteLily() {
        super("blackGranite", new Color(0, 0, 0), new Color(10, 10, 10));
        this.addDrop(MaterialLibAPI.getStack(Materials.GraniteBlack, Shapes.dust, 9), 100_00);
        this.addSubSoilRequirement(CropsNHSubSoilTypes.blackGranite);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
