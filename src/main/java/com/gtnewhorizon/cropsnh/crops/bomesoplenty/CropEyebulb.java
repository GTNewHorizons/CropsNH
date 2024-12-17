package com.gtnewhorizon.cropsnh.crops.bomesoplenty;

import biomesoplenty.api.content.BOPCBlocks;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import net.minecraft.item.ItemStack;

import java.awt.Color;

public class CropEyebulb extends NHCropCard {

    public CropEyebulb() {
        super("eyebulb", new Color(0x552323), new Color(0x875D5D));
        this.addDrop(new ItemStack(BOPCBlocks.flowers, 1, 13), 100_00);
        this.addAlternateSeed(new ItemStack(BOPCBlocks.flowers, 1, 13));
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 1;
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
