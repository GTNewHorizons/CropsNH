package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

public class CropSaltyRoot extends NHCropCard {

    public CropSaltyRoot() {
        super("saltyRoot", new Color(0x8E8E8E), new Color(0x979796));
        this.addDrop(CropsNHItemList.saltyRoot.get(1), 100_00);
        this.addLikedBiomes(BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
    }

    @Override
    public String getFlavourText() {
        return "cropsnh_crops.saltyRoot.flavour";
    }

    @Override
    public String getCreator() {
        return "GlodBlock";
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 1600;
    }

    @Override
    public float getDropChance() {
        return 4.0F;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
