package com.gtnewhorizon.cropsnh.crops.flowers;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFlower;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

public class CropAzureBluet extends CropVanillaFlower {

    public CropAzureBluet() {
        super("azureBluet", new Color(0x3D3D4C), new Color(0xE4EAF2));
        this.addDrop(new ItemStack(Items.dye, 1, 7), 10_000);
        this.addAlternateSeed(new ItemStack(Blocks.red_flower, 1, 3));
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.flower.register(this);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.flower2.houstonia.name";
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
