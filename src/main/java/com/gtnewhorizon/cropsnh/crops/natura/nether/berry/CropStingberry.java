package com.gtnewhorizon.cropsnh.crops.natura.nether.berry;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropStingberry extends NHCropCard {

    public CropStingberry() {
        super("stingberry", new Color(0x727F3D), new Color(0x8BDC3C));

        ItemStack stingberry = CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 2, 3);
        this.addDrop(stingberry.copy(), 100_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(stingberry, 1));

        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.HOT);
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
        return CropsNHSoilTypes.netherrack;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
