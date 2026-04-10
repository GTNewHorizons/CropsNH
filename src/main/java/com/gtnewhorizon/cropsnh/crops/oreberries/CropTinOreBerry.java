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

public class CropTinOreBerry extends CropOreBerry {

    public CropTinOreBerry() {
        super("tin", new Color(0x8F8F8F), new Color(0xDCDCDC));

        ItemStack oreBerries = CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 3);
        this.addDrop(oreBerries.copy(), 100_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(oreBerries, 1));

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.tin);

        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));

        this.addDuplicationCatalyst("nuggetTin", 1);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

}
