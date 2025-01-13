package com.gtnewhorizon.cropsnh.crops.ic2;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFlower;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

public class CropCyazint extends CropVanillaFlower {

    public CropCyazint() {
        super("cyazint", new Color(0x236F8F), new Color(0x3C8EB0));
        this.addDrop(new ItemStack(Items.dye, 1, 6), 10_000);
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.flower.register(this);
    }

    @Override
    public String getCreator() {
        return "IC2 Team";
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
