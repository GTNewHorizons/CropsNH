package com.gtnewhorizon.cropsnh.crops.witchery;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;

public class CropSnowbell extends NHCropCard {

    public CropSnowbell() {
        super("snowbell", new Color(0x33A9FF), new Color(0xA5D9FF));

        this.addAlternateSeed(OreDictHelper.getCopiedOreStack("seedSnowbell", 1));

        this.addDrop(OreDictHelper.getCopiedOreStack("itemSnowbell", 1), 89_00);

        this.addDrop(new ItemStack(Items.snowball, 1, 0), 10_00);

        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Cryolite, 1), 2_50);

        this.addLikedBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.COLD);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.witchery:snowbell.name";
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 1100;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.magic;
    }

    @Override
    protected String getBaseTexturePath() {
        return "witchery:snowbell_stage_";
    }

    @Override
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
    }

    @Override
    public int getMinGrowthStage() {
        return 0;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
