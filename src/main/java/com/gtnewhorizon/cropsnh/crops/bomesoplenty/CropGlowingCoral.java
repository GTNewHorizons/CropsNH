package com.gtnewhorizon.cropsnh.crops.bomesoplenty;

import biomesoplenty.api.content.BOPCBlocks;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import net.minecraft.item.ItemStack;

import java.awt.Color;

public class CropGlowingCoral extends NHCropCard {
    public CropGlowingCoral() {
        super("glowingCoral", new Color(0x7832DC), new Color(0xD6A1FF));
        this.addDrop(new ItemStack(BOPCBlocks.coral1, 2, 15), 100_00);
        this.addAlternateSeed(new ItemStack(BOPCBlocks.coral1, 1, 15));
        this.addBlockUnderRequirement("glowstone");
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getGrowthDuration() {
        return 450;
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
