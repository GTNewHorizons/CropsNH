package com.gtnewhorizon.cropsnh.crops.natura.nether.berry;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropSkyberry extends NHCropCard {

    private final static ISoilList soilTypes = CropsNHSoilTypes.netherrack;

    public CropSkyberry() {
        super("skyberry", new Color(0x2E8BA7), new Color(0x45E0FF));

        ItemStack skyberry = CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 2, 2);
        this.addDrop(skyberry.copy(), 100_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(skyberry, 1));

        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.DRY);
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
    public int getGrowthDuration() {
        return 300;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
