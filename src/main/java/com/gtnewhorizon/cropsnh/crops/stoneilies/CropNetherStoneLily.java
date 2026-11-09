package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.ruling_0.materiallib.api.MaterialLibAPI;

import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;

public class CropNetherStoneLily extends CropBaseStoneLily {

    public CropNetherStoneLily() {
        super("netherStone", new Color(0x911717), new Color(0xC21F1F));

        this.addDrop(MaterialLibAPI.getStack(Materials.Netherrack, Shapes.dust, 9), 100_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.netherrack);

        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY);
    }
}
