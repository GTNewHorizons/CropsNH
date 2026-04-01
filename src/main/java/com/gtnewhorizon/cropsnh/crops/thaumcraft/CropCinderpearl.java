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

public class CropCinderpearl extends NHCropCard {

    private final static ISoilList soilTypes = CropsNHSoilTypes.dirt;

    public CropCinderpearl() {
        super("cinderpearl", new Color(0xFF9611), new Color(0xFFD86F));

        ItemStack cinderpearl = CropsNHUtils.getModItem(ModUtils.Thaumcraft, "blockCustomPlant", 1, 3);
        this.addDrop(cinderpearl, 100_00);

        this.addAlternateSeed(cinderpearl);

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.blaze);

        this.addDuplicationCatalyst("dustBlaze", 2);

        this.addLikedBiomes(BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.MAGICAL);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek and mitchej123";
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
