package com.gtnewhorizon.cropsnh.loaders;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.CropWeed;
import com.gtnewhorizon.cropsnh.crops.cropnh.CropBonsai;
import com.gtnewhorizon.cropsnh.crops.gregtech.CropTerraWart;
import com.gtnewhorizon.cropsnh.crops.thaumcraft.PrimordialPerlCrop;
import com.gtnewhorizon.cropsnh.crops.vanilla.CropNetherwart;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;

public class CropLoader {

    public static void init() {
        // weeds
        CropRegistry.instance.register(new CropWeed());

        // Vanilla
        CropRegistry.instance.register(new CropNetherwart());
        // spotless:off
        CropRegistry.instance.register(new CropBonsai("bonsaiOak",     new Color(0x7F6139), new Color(0x57AD3F), "Notch", 1, new ItemStack(Blocks.sapling, 1, 0), new ItemStack(Blocks.log, 1, 0)).addDrop(new ItemStack(Items.apple, 2), 500));
        CropRegistry.instance.register(new CropBonsai("bonsaiSpruce",  new Color(0x50361A), new Color(0x395A39), "Notch", 1, new ItemStack(Blocks.sapling, 1, 1), new ItemStack(Blocks.log, 1, 1)));
        CropRegistry.instance.register(new CropBonsai("bonsaiBirch",   new Color(0xCFE3BA), new Color(0x648D38), "Notch", 1, new ItemStack(Blocks.sapling, 1, 2), new ItemStack(Blocks.log, 1, 2)));
        CropRegistry.instance.register(new CropBonsai("bonsaiJungle",  new Color(0x393D0D), new Color(0x378020), "Notch", 1, new ItemStack(Blocks.sapling, 1, 3), new ItemStack(Blocks.log, 1, 3)));
        CropRegistry.instance.register(new CropBonsai("bonsaiAcacia",  new Color(0x795A0D), new Color(0x71881B), "Notch", 1, new ItemStack(Blocks.sapling, 1, 4), new ItemStack(Blocks.log2, 1, 0)));
        CropRegistry.instance.register(new CropBonsai("bonsaiDarkOak", new Color(0x684C29), new Color(0x459633), "Notch", 1, new ItemStack(Blocks.sapling, 1, 5), new ItemStack(Blocks.log2, 1, 1)));
        // spotless:on

        // CropNH
        CropRegistry.instance.register(new CropTerraWart());

        // Thaum
        CropRegistry.instance.register(new PrimordialPerlCrop());
    }
}
