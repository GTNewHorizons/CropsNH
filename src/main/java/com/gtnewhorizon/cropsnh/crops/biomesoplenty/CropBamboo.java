package com.gtnewhorizon.cropsnh.crops.biomesoplenty;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropBamboo extends NHCropCard {

    public CropBamboo() {
        super("bamboo", new Color(0x83A81A), new Color(0xC7DB72));

        this.addDrop(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "bamboo", 2, 0), 100_00);

        this.addAlternateSeed(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "saplings", 1, 2));

        // bamboo forest
        this.addLikedBiomes(
            BiomeDictionary.Type.DENSE,
            BiomeDictionary.Type.JUNGLE,
            BiomeDictionary.Type.LUSH,
            BiomeDictionary.Type.FOREST);
    }

    @Override
    public String getCreator() {
        return "Minepolz320";
    }

    @Override
    public int getGrowthDuration() {
        return 250;
    }

    @Override
    public ISoilList getSoilTypes() {
        return CropsNHSoilTypes.dirtGrass;
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.bonsai;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
