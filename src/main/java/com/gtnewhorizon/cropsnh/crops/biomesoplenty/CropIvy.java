package com.gtnewhorizon.cropsnh.crops.biomesoplenty;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropIvy extends NHCropCard {

    public CropIvy() {
        super("ivy", new Color(0x1B4509), new Color(0x338011));

        ItemStack ivy = CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "ivy", 2, 0);

        this.addDrop(ivy.copy(), 100_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(ivy, 2));
        // under-garden (nether)
        this.addLikedBiomes(
            BiomeDictionary.Type.NETHER,
            BiomeDictionary.Type.JUNGLE,
            BiomeDictionary.Type.MAGICAL,
            BiomeDictionary.Type.DENSE);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getGrowthDuration() {
        return 450;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.flower;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
