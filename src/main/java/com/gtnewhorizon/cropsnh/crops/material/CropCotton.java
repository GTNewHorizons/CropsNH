package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MinLightLevelGrowthRequirement;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropCotton extends NHCropCard {

    public CropCotton() {
        super("cotton", new Color(0xE366F5), new Color(0xFFC9FF));

        this.addDrop(CropsNHUtils.getModItem(ModUtils.Natura, "barleyFood", 1, 3), 100_00);

        this.addAlternateSeed("cropCotton");
        this.addAlternateSeed("seedCotton");

        this.addGrowthRequirement(new MinLightLevelGrowthRequirement(9));
        // generally likes heavier soils and does not like cold.
        this.addLikedBiomes(BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public int getGrowthDuration() {
        return 900;
    }

    @Override
    protected String getBaseTexturePath() {
        return "natura:cotton_";
    }

    @Override
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
    }

    @Override
    public int getMaxGrowthStage() {
        return 5;
    }
}
