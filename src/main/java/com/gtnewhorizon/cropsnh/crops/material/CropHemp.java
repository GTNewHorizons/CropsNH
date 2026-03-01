package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

public class CropHemp extends NHCropCard {

    public CropHemp() {
        super("hemp", new Color(0x275600), new Color(0xBBB49D));

        this.addDrop(CropsNHItemList.hempStem.get(1), 100_00);

        this.addLikedBiomes(BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
    }

    @Override
    public String getCreator() {
        return "C0bra5";
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getGrowthDuration() {
        return 800;
    }

    @Override
    public int getMaxGrowthStage() {
        return 5;
    }
}
