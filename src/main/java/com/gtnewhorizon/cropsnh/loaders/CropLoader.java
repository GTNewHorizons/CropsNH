package com.gtnewhorizon.cropsnh.loaders;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.ic2.CropVenomilia;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.CropWeed;
import com.gtnewhorizon.cropsnh.crops.cropnh.CropBonsai;
import com.gtnewhorizon.cropsnh.crops.ic2.CropAurelia;
import com.gtnewhorizon.cropsnh.crops.ic2.CropCoffee;
import com.gtnewhorizon.cropsnh.crops.ic2.CropCyprium;
import com.gtnewhorizon.cropsnh.crops.ic2.CropFerru;
import com.gtnewhorizon.cropsnh.crops.ic2.CropHops;
import com.gtnewhorizon.cropsnh.crops.ic2.CropPlumbiscus;
import com.gtnewhorizon.cropsnh.crops.ic2.CropRedwheat;
import com.gtnewhorizon.cropsnh.crops.ic2.CropShining;
import com.gtnewhorizon.cropsnh.crops.ic2.CropStagnium;
import com.gtnewhorizon.cropsnh.crops.ic2.CropStickReed;
import com.gtnewhorizon.cropsnh.crops.ic2.CropTerraWart;
import com.gtnewhorizon.cropsnh.crops.thaumcraft.PrimordialPerlCrop;
import com.gtnewhorizon.cropsnh.crops.vanilla.CropCactus;
import com.gtnewhorizon.cropsnh.crops.vanilla.CropNetherwart;
import com.gtnewhorizon.cropsnh.crops.vanilla.CropSugarCane;
import com.gtnewhorizon.cropsnh.crops.vanilla.flowers.CropAllium;
import com.gtnewhorizon.cropsnh.crops.vanilla.flowers.CropAzureBluet;
import com.gtnewhorizon.cropsnh.crops.vanilla.flowers.CropBlueOrchid;
import com.gtnewhorizon.cropsnh.crops.vanilla.flowers.CropDandelion;
import com.gtnewhorizon.cropsnh.crops.vanilla.flowers.CropOrangeTulip;
import com.gtnewhorizon.cropsnh.crops.vanilla.flowers.CropOxeyeDaisy;
import com.gtnewhorizon.cropsnh.crops.vanilla.flowers.CropPinkTulip;
import com.gtnewhorizon.cropsnh.crops.vanilla.flowers.CropPoppy;
import com.gtnewhorizon.cropsnh.crops.vanilla.flowers.CropRedTulip;
import com.gtnewhorizon.cropsnh.crops.vanilla.flowers.CropWhiteTulip;
import com.gtnewhorizon.cropsnh.crops.vanilla.food.CropCarrot;
import com.gtnewhorizon.cropsnh.crops.vanilla.food.CropCocoa;
import com.gtnewhorizon.cropsnh.crops.vanilla.food.CropMelon;
import com.gtnewhorizon.cropsnh.crops.vanilla.food.CropPotato;
import com.gtnewhorizon.cropsnh.crops.vanilla.food.CropPumpkin;
import com.gtnewhorizon.cropsnh.crops.vanilla.food.CropWheat;
import com.gtnewhorizon.cropsnh.crops.vanilla.mushrooms.CropBrownMushroom;
import com.gtnewhorizon.cropsnh.crops.vanilla.mushrooms.CropRedMushroom;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;

import gregtech.api.enums.Mods;

public class CropLoader {

    public static void init() {
        // weeds
        CropRegistry.instance.register(new CropWeed());

        // Vanilla food
        CropRegistry.instance.register(new CropCarrot());
        CropRegistry.instance.register(new CropCocoa());
        CropRegistry.instance.register(new CropMelon());
        CropRegistry.instance.register(new CropPotato());
        CropRegistry.instance.register(new CropPumpkin());
        CropRegistry.instance.register(new CropWheat());
        // vanilla mushrooms
        CropRegistry.instance.register(new CropRedMushroom());
        CropRegistry.instance.register(new CropBrownMushroom());
        // vanilla flowers
        CropRegistry.instance.register(new CropDandelion());
        CropRegistry.instance.register(new CropPoppy());
        CropRegistry.instance.register(new CropBlueOrchid());
        CropRegistry.instance.register(new CropAllium());
        CropRegistry.instance.register(new CropAzureBluet());
        CropRegistry.instance.register(new CropRedTulip());
        CropRegistry.instance.register(new CropOrangeTulip());
        CropRegistry.instance.register(new CropWhiteTulip());
        CropRegistry.instance.register(new CropPinkTulip());
        CropRegistry.instance.register(new CropOxeyeDaisy());
        // vanilla misc
        CropRegistry.instance.register(new CropCactus());
        CropRegistry.instance.register(new CropSugarCane());
        CropRegistry.instance.register(new CropNetherwart());
        // vanilla bonsais
        // spotless:off
        CropRegistry.instance.register(new CropBonsai("bonsaiOak",     new Color(0x7F6139), new Color(0x57AD3F), "Notch", 1, new ItemStack(Blocks.sapling, 1, 0), new ItemStack(Blocks.log, 1, 0)).addDrop(new ItemStack(Items.apple, 2), 500));
        CropRegistry.instance.register(new CropBonsai("bonsaiSpruce",  new Color(0x50361A), new Color(0x395A39), "Notch", 1, new ItemStack(Blocks.sapling, 1, 1), new ItemStack(Blocks.log, 1, 1)));
        CropRegistry.instance.register(new CropBonsai("bonsaiBirch",   new Color(0xCFE3BA), new Color(0x648D38), "Notch", 1, new ItemStack(Blocks.sapling, 1, 2), new ItemStack(Blocks.log, 1, 2)));
        CropRegistry.instance.register(new CropBonsai("bonsaiJungle",  new Color(0x393D0D), new Color(0x378020), "Notch", 1, new ItemStack(Blocks.sapling, 1, 3), new ItemStack(Blocks.log, 1, 3)));
        CropRegistry.instance.register(new CropBonsai("bonsaiAcacia",  new Color(0x795A0D), new Color(0x71881B), "Notch", 1, new ItemStack(Blocks.sapling, 1, 4), new ItemStack(Blocks.log2, 1, 0)));
        CropRegistry.instance.register(new CropBonsai("bonsaiDarkOak", new Color(0x684C29), new Color(0x459633), "Notch", 1, new ItemStack(Blocks.sapling, 1, 5), new ItemStack(Blocks.log2, 1, 1)));
        // spotless:on
        // IC2
        if (Mods.GregTech.isModLoaded()) {
            CropRegistry.instance.register(new CropAurelia());
            CropRegistry.instance.register(new CropCoffee());
            CropRegistry.instance.register(new CropCyprium());
            CropRegistry.instance.register(new CropFerru());
            CropRegistry.instance.register(new CropHops());
            CropRegistry.instance.register(new CropPlumbiscus());
            CropRegistry.instance.register(new CropRedwheat());
            CropRegistry.instance.register(new CropShining());
            CropRegistry.instance.register(new CropStagnium());
            CropRegistry.instance.register(new CropStickReed());
            CropRegistry.instance.register(new CropVenomilia());
        }
        // terrawart was part of ic2 but is now part of this mod
        CropRegistry.instance.register(new CropTerraWart());

        // Thaumcraft
        if (Mods.Thaumcraft.isModLoaded()) {
            CropRegistry.instance.register(new PrimordialPerlCrop());
        }
    }
}
