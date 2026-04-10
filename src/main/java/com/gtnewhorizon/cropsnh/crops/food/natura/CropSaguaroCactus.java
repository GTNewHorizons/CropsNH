package com.gtnewhorizon.cropsnh.crops.food.natura;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropSaguaroCactus extends CropFood {

    public CropSaguaroCactus() {
        super("saguaroCactus", new Color(0x3D401B), new Color(0x828839));

        ItemStack saguaro = CropsNHUtils.getModItem(ModUtils.Natura, "Saguaro", 2, 0);
        ItemStack saguaroFruit = CropsNHUtils.getModItem(ModUtils.Natura, "saguaro.fruit", 3, 0);
        this.addDrop(saguaro.copy(), 50_00);
        this.addDrop(saguaroFruit, 50_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(saguaro, 1));
        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(saguaroFruit, 1));
        // likes a well drained soil
        this.addLikedBiomes(BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
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
        return 450;
    }

    @Override
    public ISoilList getSoilTypes() {
        return CropsNHSoilTypes.sand;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.grains;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
