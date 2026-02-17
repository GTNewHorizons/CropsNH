package com.gtnewhorizon.cropsnh.crops.biomesoplenty;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropMoss extends NHCropCard {

    public CropMoss() {
        super("moss", new Color(0x256319), new Color(0x3A9126));
        if (ModUtils.TwilightForest.isModLoaded()) {
            ItemStack tfMoss = CropsNHUtils.getModItem(ModUtils.TwilightForest, "tile.TFPlant", 1, 3);
            this.addDrop(tfMoss.copy(), 5_00);
            this.addAlternateSeed(tfMoss.copy());
        }
        if (ModUtils.BiomesOPlenty.isModLoaded()) {
            ItemStack moss = CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "moss", 1, 0);
            ItemStack treeMoss = CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "treeMoss", 1, 0);

            this.addDrop(moss.copy(), 30_00);
            this.addDrop(treeMoss.copy(), 65_00);

            this.addAlternateSeed(moss.copy());
            this.addAlternateSeed(treeMoss.copy());
        }

        // bayou, temperate rainforest
        addLikedBiomes(BiomeDictionary.Type.WET, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.LUSH);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.moss.name";
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 450;
    }
}
