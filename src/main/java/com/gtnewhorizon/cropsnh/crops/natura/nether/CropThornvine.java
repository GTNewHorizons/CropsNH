package com.gtnewhorizon.cropsnh.crops.natura.nether;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropThornvine extends NHCropCard {

    public CropThornvine() {
        super("thornvine", new Color(0x987005), new Color(0xDFE485));

        ItemStack thornVines = CropsNHUtils.getModItem(ModUtils.Natura, "Thornvines", 2, 0);
        this.addDrop(thornVines.copy(), 100_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(thornVines, 1));

        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.DRY);
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
        return 450;
    }

    @Override
    public ISoilList getSoilTypes() {
        return CropsNHSoilTypes.netherrack;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
