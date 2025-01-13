package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.init.CropsNHItemList;

public class CropMagicalNightshade extends NHCropCard {

    public CropMagicalNightshade() {
        super("magicalNightshade", new Color(0x20001B), new Color(0xB4009C));
        this.addDrop(CropsNHItemList.magicEssence.get(1), 100_00);
        this.addBlockUnderRequirement("ichorium");
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 13;
    }

    @Override
    public int getGrowthDuration() {
        return 23436;
    }

    @Override
    public float getDropChance() {
        return super.getDropChance() * 0.75f;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.magic;
    }

    @Override
    public boolean isSeedEnchanted() {
        return true;
    }
}
