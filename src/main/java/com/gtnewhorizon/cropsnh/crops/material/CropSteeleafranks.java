package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.ruling_0.materiallib.api.MaterialLibAPI;

import gregtech.api.enums.VoltageIndex;
import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;

public class CropSteeleafranks extends NHCropCard {

    public CropSteeleafranks() {
        super("steeleafranks", new Color(0x163916), new Color(0x327F32));

        this.addDrop(MaterialLibAPI.getStack(Materials.Steeleaf, Shapes.dust, 1), 25_00);
        this.addDrop(CropsNHUtils.getModItem(ModUtils.TwilightForest, "item.steeleafIngot", 1, 0), 25_00);

        this.addDuplicationCatalyst("dustSteeleaf", 1);
        this.addDuplicationCatalyst("ingotSteeleaf", 1);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.steeleaf);

        this.addLikedBiomes(BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.DEAD);
    }

    @Override
    public String getCreator() {
        return "Benimatic";
    }

    @Override
    public int getTier() {
        return 10;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 6000;
    }

    @Override
    public ISoilList getSoilTypes() {
        return CropsNHSoilTypes.stone;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
