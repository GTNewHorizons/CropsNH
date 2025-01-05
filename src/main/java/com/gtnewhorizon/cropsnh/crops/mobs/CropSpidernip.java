package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

public class CropSpidernip extends NHCropCard {

    public CropSpidernip() {
        super("spidernip", new Color(0xBFBFBF), new Color(0xF7F7F7));
        this.addDrop(new ItemStack(Items.string, 1, 0), 66_66);
        this.addDrop(new ItemStack(Items.spider_eye, 1, 0), 16_67);
        this.addDrop(new ItemStack(Blocks.web, 1, 0), 16_67);
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.aggresiveMob.register(this);
        CropsNHMutationPools.nether.register(this);
    }

    @Override
    public String getCreator() {
        return "Mr. Kenny";
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 2400;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
