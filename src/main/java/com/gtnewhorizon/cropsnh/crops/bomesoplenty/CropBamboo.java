package com.gtnewhorizon.cropsnh.crops.bomesoplenty;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

import biomesoplenty.api.content.BOPCBlocks;

public class CropBamboo extends NHCropCard {

    public CropBamboo() {
        super("bamboo", new Color(0x83A81A), new Color(0xC7DB72));
        this.addDrop(new ItemStack(BOPCBlocks.bamboo, 2), 100_00);
        this.addAlternateSeed(new ItemStack(BOPCBlocks.saplings, 1, 2));
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.wood.register(this);
        CropsNHMutationPools.jungle.register(this);
    }

    @Override
    public String getCreator() {
        return "Minepolz320";
    }

    @Override
    public int getGrowthDuration() {
        return 250;
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.bonsai;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
