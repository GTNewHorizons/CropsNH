package com.gtnewhorizon.cropsnh.crops.natura.nether.berry;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropBlightberry extends NHCropCard {

    private final static ISoilList soilTypes = CropsNHSoilTypes.netherrack;

    public CropBlightberry() {
        super("blightberry", new Color(0x3BC956), new Color(0xA3F2A0));

        ItemStack blightberry = CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 2, 0);
        this.addDrop(blightberry.copy(), 100_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(blightberry, 1));

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
