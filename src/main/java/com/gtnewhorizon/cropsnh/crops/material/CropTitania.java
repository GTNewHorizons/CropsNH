package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MachineOnlyGrowthRequirement;
import com.gtnewhorizon.cropsnh.reference.Reference;

import gregtech.api.enums.VoltageIndex;

public class CropTitania extends NHCropCard {

    private final static ISoilList soilTypes = CropsNHSoilTypes.stone;

    public CropTitania() {
        super("titania", new Color(0x815E8D), new Color(0xDCA0F0));

        this.addDrop(CropsNHItemList.titaniaLeaf.get(1), 100_00);

        this.addBlockUnderRequirement("titanium");

        this.addDuplicationCatalyst("dustTitanium", 1);
        this.addDuplicationCatalyst("dustRutile", 2);
        this.addDuplicationCatalyst("dustIlmenite", 2);

        this.addGrowthRequirement(new MachineOnlyGrowthRequirement());

        this.addLikedBiomes(BiomeDictionary.Type.HOT, BiomeDictionary.Type.SAVANNA);
    }

    @Override
    public String getFlavourText() {
        return Reference.MOD_ID + "_crops.titania.flavour";
    }

    @Override
    public int getTier() {
        return 9;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.EV;
    }

    @Override
    public int getMinSeedBedTier() {
        return VoltageIndex.EV;
    }

    @Override
    public int getGrowthDuration() {
        return 1800;
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
