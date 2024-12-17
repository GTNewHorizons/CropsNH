package com.gtnewhorizon.cropsnh.crops.bomesoplenty;

import biomesoplenty.api.content.BOPCBlocks;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import net.minecraft.item.ItemStack;

import java.awt.Color;

public class CropFloweringVine extends NHCropCard {
    public CropFloweringVine() {
        super("floweringVine", new Color(0x2F7D4C), new Color(0xC6C6C6));
        this.addDrop(new ItemStack(BOPCBlocks.flowerVine, 2, 0), 100_00);
        this.addAlternateSeed(new ItemStack(BOPCBlocks.flowerVine, 1, 0));
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
        return 675;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.flower;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
