package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.ruling_0.materiallib.api.MaterialLibAPI;

import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;

public class CropRedGraniteLily extends CropBaseStoneLily {

    public CropRedGraniteLily() {
        super("redGranite", new Color(0x871C52), new Color(0xD42C80));

        this.addDrop(MaterialLibAPI.getStack(Materials.GraniteRed, Shapes.dust, 9), 100_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.redGranite);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
