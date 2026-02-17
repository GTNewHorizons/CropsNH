package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.enums.VoltageIndex;

public class CropAluminiumOreBerry extends CropOreBerry {

    public CropAluminiumOreBerry() {
        super("aluminium", new Color(0x5687A3), new Color(0x80C8F0));

        ItemStack oreBerries = CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 4);
        this.addDrop(oreBerries.copy(), 100_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(oreBerries, 1));

        this.addBlockUnderRequirement("aluminium");

        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));

        this.addDuplicationCatalyst("nuggetAluminium", 1);

        // tundra
        this.addLikedBiomes(BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }
}
