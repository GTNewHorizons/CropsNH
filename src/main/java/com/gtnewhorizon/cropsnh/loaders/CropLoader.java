package com.gtnewhorizon.cropsnh.loaders;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.cropnh.CropGoldfish;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.CropWeed;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropBamboo;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropBoPBerry;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropEyebulb;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropFloweringVine;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropGlowflower;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropGlowingCoral;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropGlowshroom;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropIvy;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropMoss;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropTurnip;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropWildCarrot;
import com.gtnewhorizon.cropsnh.crops.cropnh.CropBonsai;
import com.gtnewhorizon.cropsnh.crops.gregtech.CropGarnydnia;
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
import com.gtnewhorizon.cropsnh.crops.ic2.CropVenomilia;
import com.gtnewhorizon.cropsnh.crops.natura.CropBarley;
import com.gtnewhorizon.cropsnh.crops.natura.CropBlackberry;
import com.gtnewhorizon.cropsnh.crops.natura.CropBlueberry;
import com.gtnewhorizon.cropsnh.crops.natura.CropCotton;
import com.gtnewhorizon.cropsnh.crops.natura.CropMaloberry;
import com.gtnewhorizon.cropsnh.crops.natura.CropRaspberry;
import com.gtnewhorizon.cropsnh.crops.natura.CropSaguaroCactus;
import com.gtnewhorizon.cropsnh.crops.natura.nether.CropBlightberry;
import com.gtnewhorizon.cropsnh.crops.natura.nether.CropBlueGlowshroom;
import com.gtnewhorizon.cropsnh.crops.natura.nether.CropDuskberry;
import com.gtnewhorizon.cropsnh.crops.natura.nether.CropGreenGlowshroom;
import com.gtnewhorizon.cropsnh.crops.natura.nether.CropPurpleGlowshroom;
import com.gtnewhorizon.cropsnh.crops.natura.nether.CropSkyberry;
import com.gtnewhorizon.cropsnh.crops.natura.nether.CropStingberry;
import com.gtnewhorizon.cropsnh.crops.natura.nether.CropThornvine;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropAluminiumOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropArditeOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropCobaltOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropCopperOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropEssenceOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropGoldOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropIronOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropKnightmetalBerries;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropThauminiteOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropThaumiumOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropTinOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropVoidOreBerry;
import com.gtnewhorizon.cropsnh.crops.stoneilies.CropBasaltLily;
import com.gtnewhorizon.cropsnh.crops.stoneilies.CropBlackGraniteLily;
import com.gtnewhorizon.cropsnh.crops.stoneilies.CropEndStoneLily;
import com.gtnewhorizon.cropsnh.crops.stoneilies.CropMarbleLily;
import com.gtnewhorizon.cropsnh.crops.stoneilies.CropNetherStoneLily;
import com.gtnewhorizon.cropsnh.crops.stoneilies.CropRedGraniteLily;
import com.gtnewhorizon.cropsnh.crops.stoneilies.CropSandLily;
import com.gtnewhorizon.cropsnh.crops.stoneilies.CropSoulSandLily;
import com.gtnewhorizon.cropsnh.crops.stoneilies.CropStoneLily;
import com.gtnewhorizon.cropsnh.crops.stoneilies.botania.CropAndesiteLily;
import com.gtnewhorizon.cropsnh.crops.stoneilies.botania.CropDioriteLily;
import com.gtnewhorizon.cropsnh.crops.stoneilies.botania.CropGraniteLily;
import com.gtnewhorizon.cropsnh.crops.thaumcraft.PrimordialPerlCrop;
import com.gtnewhorizon.cropsnh.crops.twilightforest.CropTorchberry;
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
import com.gtnewhorizon.cropsnh.crops.witchery.CropBelladonna;
import com.gtnewhorizon.cropsnh.crops.witchery.CropEmberMoss;
import com.gtnewhorizon.cropsnh.crops.witchery.CropGarlic;
import com.gtnewhorizon.cropsnh.crops.witchery.CropGlintWeed;
import com.gtnewhorizon.cropsnh.crops.witchery.CropMandrake;
import com.gtnewhorizon.cropsnh.crops.witchery.CropSnowbell;
import com.gtnewhorizon.cropsnh.crops.witchery.CropSpanishMoss;
import com.gtnewhorizon.cropsnh.crops.witchery.CropWaterArtichoke;
import com.gtnewhorizon.cropsnh.crops.witchery.CropWolfsbane;
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
        // terrawart was part of ic2 but is now part of this mod
        CropRegistry.instance.register(new CropTerraWart());
        // Stone Lilies
        CropRegistry.instance.register(new CropStoneLily());
        CropRegistry.instance.register(new CropSandLily());
        CropRegistry.instance.register(new CropNetherStoneLily());
        CropRegistry.instance.register(new CropSoulSandLily());
        CropRegistry.instance.register(new CropEndStoneLily());
        CropRegistry.instance.register(new CropRedGraniteLily());
        CropRegistry.instance.register(new CropBlackGraniteLily());
        CropRegistry.instance.register(new CropBasaltLily());
        CropRegistry.instance.register(new CropMarbleLily());
        if (Mods.Botania.isModLoaded()) {
            CropRegistry.instance.register(new CropAndesiteLily());
            CropRegistry.instance.register(new CropDioriteLily());
            CropRegistry.instance.register(new CropGraniteLily());
        }
        // Crops++ gt crops
        CropRegistry.instance.register(new CropGarnydnia());
        // Ore Berries
        if (Mods.TinkerConstruct.isModLoaded()) {
            CropRegistry.instance.register(new CropAluminiumOreBerry());
            CropRegistry.instance.register(new CropCopperOreBerry());
            CropRegistry.instance.register(new CropEssenceOreBerry());
            CropRegistry.instance.register(new CropGoldOreBerry());
            CropRegistry.instance.register(new CropIronOreBerry());
            CropRegistry.instance.register(new CropTinOreBerry());
            CropRegistry.instance.register(new CropArditeOreBerry());
            CropRegistry.instance.register(new CropCobaltOreBerry());
        }
        if (Mods.Thaumcraft.isModLoaded()) {
            CropRegistry.instance.register(new CropThaumiumOreBerry());
            CropRegistry.instance.register(new CropVoidOreBerry());
        }
        if (Mods.ThaumicBases.isModLoaded()) {
            CropRegistry.instance.register(new CropThauminiteOreBerry());
        }
        // thaumcraft
        if (Mods.Thaumcraft.isModLoaded()) {
            CropRegistry.instance.register(new PrimordialPerlCrop());
        }
        // Crops++ Twilight Forest
        if (Mods.TwilightForest.isModLoaded()) {
            CropRegistry.instance.register(new CropKnightmetalBerries());
            CropRegistry.instance.register(new CropTorchberry());
        }
        if (Mods.TwilightForest.isModLoaded() && Mods.BiomesOPlenty.isModLoaded()) {
            CropRegistry.instance.register(new CropMoss());
        }
        // Crops++ BoP
        if (Mods.BiomesOPlenty.isModLoaded()) {
            CropRegistry.instance.register(new CropBamboo());
            CropRegistry.instance.register(new CropBoPBerry());
            CropRegistry.instance.register(new CropEyebulb());
            CropRegistry.instance.register(new CropFloweringVine());
            CropRegistry.instance.register(new CropGlowflower());
            CropRegistry.instance.register(new CropGlowingCoral());
            CropRegistry.instance.register(new CropGlowshroom());
            CropRegistry.instance.register(new CropIvy());
            CropRegistry.instance.register(new CropTurnip());
            CropRegistry.instance.register(new CropWildCarrot());
        }
        // Crops++ Witchery
        if (Mods.Witchery.isModLoaded()) {
            CropRegistry.instance.register(new CropBelladonna());
            CropRegistry.instance.register(new CropEmberMoss());
            CropRegistry.instance.register(new CropGarlic());
            CropRegistry.instance.register(new CropGlintWeed());
            CropRegistry.instance.register(new CropMandrake());
            CropRegistry.instance.register(new CropSnowbell());
            CropRegistry.instance.register(new CropSpanishMoss());
            CropRegistry.instance.register(new CropWaterArtichoke());
            CropRegistry.instance.register(new CropWolfsbane());
        }
        // Crops++ Natura
        if (Mods.Natura.isModLoaded()) {
            CropRegistry.instance.register(new CropBlightberry());
            CropRegistry.instance.register(new CropBlueGlowshroom());
            CropRegistry.instance.register(new CropDuskberry());
            CropRegistry.instance.register(new CropGreenGlowshroom());
            CropRegistry.instance.register(new CropPurpleGlowshroom());
            CropRegistry.instance.register(new CropSkyberry());
            CropRegistry.instance.register(new CropStingberry());
            CropRegistry.instance.register(new CropThornvine());
            CropRegistry.instance.register(new CropBarley());
            CropRegistry.instance.register(new CropBlackberry());
            CropRegistry.instance.register(new CropBlueberry());
            CropRegistry.instance.register(new CropCotton());
            CropRegistry.instance.register(new CropRaspberry());
            CropRegistry.instance.register(new CropMaloberry());
            CropRegistry.instance.register(new CropSaguaroCactus());
        }
        // Crops++ native
        CropRegistry.instance.register(new CropGoldfish());
    }
}
