package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

import gregtech.api.enums.VoltageIndex;

public class CropThauminiteOreBerry extends CropOreBerry {

    public CropThauminiteOreBerry() {
        super("thauminite", new Color(0x515A9C), new Color(0x7581E0));

        this.addDrop(OreDictHelper.getCopiedOreStack("nuggetThauminite", 1), 100_00);

        this.addBlockUnderRequirement("thauminite");

        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));

        this.addDuplicationCatalyst("nuggetThauminite", 1);

        // magical forest
        this.addLikedBiomes(BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.FOREST);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 7;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 4500;
    }

}
