package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.enums.VoltageIndex;

public class CropIronOreBerry extends CropOreBerry {

    public CropIronOreBerry() {
        super("iron", new Color(0x7D7D7D), new Color(0xC8C8C8));

        ItemStack oreBerries = CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 0);
        this.addDrop(oreBerries.copy(), 100_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(oreBerries, 1));

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.iron);

        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));

        this.addDuplicationCatalyst("nuggetIron", 1);
        // iron tends to be easily found in mountains
        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
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
