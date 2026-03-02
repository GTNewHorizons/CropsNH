package com.gtnewhorizon.cropsnh.crops.biomesoplenty;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropFloweringVine extends NHCropCard {

    public CropFloweringVine() {
        super("floweringVine", new Color(0x2F7D4C), new Color(0xC6C6C6));

        ItemStack flowerVine = CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "flowerVine", 2, 0);
        this.addDrop(flowerVine.copy(), 100_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(flowerVine, 1));

        this.addLikedBiomes(BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.DENSE);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public int getGrowthDuration() {
        return 675;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.flower;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
