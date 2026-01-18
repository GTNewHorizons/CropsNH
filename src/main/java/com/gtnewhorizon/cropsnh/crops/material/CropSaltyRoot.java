package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.reference.Reference;

import gregtech.api.enums.VoltageIndex;

public class CropSaltyRoot extends NHCropCard {

    public CropSaltyRoot() {
        super("saltyRoot", new Color(0x8E8E8E), new Color(0x979796));

        this.addDrop(CropsNHItemList.saltyRoot.get(1), 100_00);

        this.addLikedBiomes(BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);

        this.addDuplicationCatalyst("dustSalt", 1);
        this.addDuplicationCatalyst("gemSalt", 1);
        this.addDuplicationCatalyst("dustRockSalt", 1);
        this.addDuplicationCatalyst("gemRockSalt", 1);
        this.addDuplicationCatalyst("dustSaltpeter", 1);

        this.addLikedBiomes(BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD);
    }

    @Override
    public String getFlavourText() {
        return Reference.MOD_ID + "_crops.saltyRoot.flavour";
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
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
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
