package com.gtnewhorizon.cropsnh.crops.biomesoplenty;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropGlowingCoral extends NHCropCard {

    public CropGlowingCoral() {
        super("glowingCoral", new Color(0x7832DC), new Color(0xD6A1FF));

        ItemStack coral = CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "coral1", 2, 15);
        this.addDrop(coral.copy(), 100_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(coral, 1));

        this.addBlockUnderRequirement("glowstone");

        this.addDuplicationCatalyst("dustGlowstone", 1);
        // coral reef tags
        this.addLikedBiomes(BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.RIVER);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getGrowthDuration() {
        return 450;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.magic;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
