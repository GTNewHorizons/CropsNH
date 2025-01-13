package com.gtnewhorizon.cropsnh.crops.bomesoplenty;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

import biomesoplenty.api.content.BOPCBlocks;
import twilightforest.block.TFBlocks;

public class CropMoss extends NHCropCard {

    public CropMoss() {
        super("moss", new Color(0x256319), new Color(0x3A9126));
        this.addDrop(new ItemStack(TFBlocks.plant, 1, 3), 5_00);
        this.addDrop(new ItemStack(BOPCBlocks.moss, 1), 30_00);
        this.addDrop(new ItemStack(BOPCBlocks.treeMoss, 1), 65_00);

        this.addAlternateSeed(new ItemStack(TFBlocks.plant, 1, 3));
        this.addAlternateSeed(new ItemStack(BOPCBlocks.moss, 1));
        this.addAlternateSeed(new ItemStack(BOPCBlocks.treeMoss, 1));

    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.decorative.register(this);
        CropsNHMutationPools.jungle.register(this);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.moss.name";
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 450;
    }
}
