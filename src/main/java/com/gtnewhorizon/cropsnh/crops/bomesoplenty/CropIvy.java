package com.gtnewhorizon.cropsnh.crops.bomesoplenty;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import biomesoplenty.api.content.BOPCBlocks;

public class CropIvy extends NHCropCard {

    public CropIvy() {
        super("ivy", new Color(0x1B4509), new Color(0x338011));
        this.addDrop(new ItemStack(BOPCBlocks.ivy, 2, 0), 100_00);
        this.addAlternateSeed(new ItemStack(BOPCBlocks.ivy, 1, 0));
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
        return 3;
    }
}
