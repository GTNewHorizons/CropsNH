package com.gtnewhorizon.cropsnh.crops.thaumcraft;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropShimmerleaf extends NHCropCard {

    private final static ISoilList soilTypes = CropsNHSoilTypes.dirt;

    public CropShimmerleaf() {
        super("shimmerleaf", new Color(0x78A59C), new Color(0xA9C6C1));

        ItemStack silverleaf = CropsNHUtils.getModItem(ModUtils.Thaumcraft, "blockCustomPlant", 1, 2);
        this.addDrop(silverleaf.copy(), 100_00);

        this.addAlternateSeed(silverleaf.copy());

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.quicksilver);

        this.addDuplicationCatalyst("nuggetMercury", 1);

        this.addLikedBiomes(BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.LUSH);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek and DreamMasterXXL";
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getGrowthDuration() {
        return 4000;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
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
