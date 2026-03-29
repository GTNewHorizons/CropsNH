package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.requirements.BlockUnderRequirement;

import gregtech.api.enums.Materials;

public class CropSilviscus extends NHCropCard {

    private final static ISoilList soilTypes = CropsNHSoilTypes.stone;

    public CropSilviscus() {
        super("silviscus", new Color(0xB3B3D0), new Color(0xD0D0F1));
        this.addDrop(Materials.Silver.getDustTiny(1), 100_00);
        this.addGrowthRequirement(BlockUnderRequirement.get("silver"));
        this.addDuplicationCatalyst("dustSilver", 1);
        // movie reference
        this.addLikedBiomes(BiomeDictionary.Type.COLD, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.SPOOKY);
    }

    @Override
    public int getTier() {
        return 8;
    }

    @Override
    public float getDropChance() {
        return super.getDropChance();
    }

    @Override
    public int getGrowthDuration() {
        return 3700;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
