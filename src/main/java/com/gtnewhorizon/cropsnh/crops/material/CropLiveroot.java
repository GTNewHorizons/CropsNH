package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;

public class CropLiveroot extends NHCropCard {

    public CropLiveroot() {
        super("liveroot", new Color(0x877B00), new Color(0xB9DA49));

        this.addDrop(Materials.LiveRoot.getDust(1), 75_00);
        this.addDrop(ItemList.TF_LiveRoot.get(1), 75_00);

        this.addDuplicationCatalyst(Materials.LiveRoot.getDust(1));
        this.addDuplicationCatalyst(ItemList.TF_LiveRoot.get(1));
        // twilight forest scary
        this.addLikedBiomes(BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.DENSE);
    }

    @Override
    public String getCreator() {
        return "Benimatic";
    }

    @Override
    public int getTier() {
        return 8;
    }

    @Override
    public int getGrowthDuration() {
        return 4800;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
