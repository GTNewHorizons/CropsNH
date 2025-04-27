package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import gregtech.api.enums.Mods;

public class CropSpaceFlower extends NHCropCard {

    public CropSpaceFlower() {
        super("spaceFlower", new Color(0xFF0000), new Color(0xFFC200));
        this.addDrop(CropsNHItemList.spaceFlower.get(1), 100_00);
        if (Mods.GalacticraftCore.isModLoaded()) {
            this.addBlockUnderRequirement("moon");
        }
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
        return 15_000;
    }

    @Override
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.flower;
    }

    @Override
    public boolean isSeedEnchanted() {
        return true;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
