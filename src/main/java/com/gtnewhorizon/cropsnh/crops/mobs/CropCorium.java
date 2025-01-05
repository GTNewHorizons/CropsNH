package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

public class CropCorium extends NHCropCard {

    public CropCorium() {
        super("corium", new Color(0x542716), new Color(0xC65C35));
        this.addDrop(new ItemStack(Items.leather, 1, 0), 100_00);
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.passiveMob.register(this);
    }

    @Override
    public String getCreator() {
        return "Gregorius Techneticies";
    }

    @Override
    public int getTier() {
        return 6;
    }

    @Override
    public int getGrowthDuration() {
        return 800;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
