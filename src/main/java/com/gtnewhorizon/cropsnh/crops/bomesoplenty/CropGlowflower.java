package com.gtnewhorizon.cropsnh.crops.bomesoplenty;

import biomesoplenty.api.content.BOPCBlocks;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import net.minecraft.item.ItemStack;

import java.awt.Color;

public class CropGlowflower extends NHCropCard {
    public CropGlowflower() {
        super("glowflower", new Color(0x004D4C), new Color(0x12A4A2));
        this.addDrop(new ItemStack(BOPCBlocks.flowers, 2, 3), 100_00);
        this.addAlternateSeed(new ItemStack(BOPCBlocks.flowers, 1, 3));
        this.addBlockUnderRequirement("glowstone");
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
    public ISeedShape getSeedShape() {
        return SeedShape.flower;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
