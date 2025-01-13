package com.gtnewhorizon.cropsnh.crops.cropnh;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;

public class CropFertilia extends NHCropCard {

    public CropFertilia() {
        super("fertilia", new Color(0x554516), new Color(0x6E6038));
        this.addDrop(Materials.Calcite.getDust(1), 62_50);
        this.addDrop(Materials.Phosphate.getDust(1), 12_50);
        this.addDrop(Materials.Apatite.getDust(1), 12_50);
        // TODO: MOVE IC2 FERTILIZER TO CROPSNH
        this.addDrop(ItemList.IC2_Fertilizer.get(1), 12_50);
        this.addLikedBiomes(BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WET, BiomeDictionary.Type.HOT);
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public int getGrowthDuration() {
        return 1800;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
