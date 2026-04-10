package com.gtnewhorizon.cropsnh.crops.oreBerries;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.enums.VoltageIndex;

public class CropGoldOreBerry extends CropOreBerry {

    public CropGoldOreBerry() {
        super("gold", new Color(0xB3B315), new Color(0xFFFF1E));

        ItemStack oreBerries = CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 1);
        this.addDrop(oreBerries.copy(), 100_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(oreBerries, 1));

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.gold);

        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));

        this.addDuplicationCatalyst("nuggetGold", 1);
        // mesa
        this.addLikedBiomes(BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY);
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
