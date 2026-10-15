package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.ruling_0.materiallib.api.MaterialLibAPI;

import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;

public class CropBasaltLily extends CropBaseStoneLily {

    public CropBasaltLily() {
        super("basalt", new Color(0x080808), new Color(0x181818));

        this.addDrop(MaterialLibAPI.getStack(Materials.Basalt, Shapes.dust, 9), 100_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.basalt);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
