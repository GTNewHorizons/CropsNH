package com.gtnewhorizon.cropsnh.loaders;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.CropWeed;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropBamboo;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropEyebulb;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropFloweringVine;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropGlowflower;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropGlowingCoral;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropGlowshroom;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropIvy;
import com.gtnewhorizon.cropsnh.crops.bomesoplenty.CropMoss;
import com.gtnewhorizon.cropsnh.crops.cropnh.CropBonsai;
import com.gtnewhorizon.cropsnh.crops.cropnh.CropFertilia;
import com.gtnewhorizon.cropsnh.crops.cropnh.CropFlax;
import com.gtnewhorizon.cropsnh.crops.cropnh.CropHemp;
import com.gtnewhorizon.cropsnh.crops.food.CropChilly;
import com.gtnewhorizon.cropsnh.crops.food.CropCoffee;
import com.gtnewhorizon.cropsnh.crops.food.CropCucumber;
import com.gtnewhorizon.cropsnh.crops.food.CropGrape;
import com.gtnewhorizon.cropsnh.crops.food.CropHops;
import com.gtnewhorizon.cropsnh.crops.food.CropHuckleberry;
import com.gtnewhorizon.cropsnh.crops.food.CropLemon;
import com.gtnewhorizon.cropsnh.crops.food.CropOnion;
import com.gtnewhorizon.cropsnh.crops.food.CropSugarBeet;
import com.gtnewhorizon.cropsnh.crops.food.CropTea;
import com.gtnewhorizon.cropsnh.crops.food.CropTomato;
import com.gtnewhorizon.cropsnh.crops.food.bop.CropBoPBerry;
import com.gtnewhorizon.cropsnh.crops.food.bop.CropTurnip;
import com.gtnewhorizon.cropsnh.crops.food.bop.CropWildCarrot;
import com.gtnewhorizon.cropsnh.crops.food.natura.CropBarley;
import com.gtnewhorizon.cropsnh.crops.food.natura.CropBlackberry;
import com.gtnewhorizon.cropsnh.crops.food.natura.CropBlueberry;
import com.gtnewhorizon.cropsnh.crops.food.natura.CropMaloberry;
import com.gtnewhorizon.cropsnh.crops.food.natura.CropRaspberry;
import com.gtnewhorizon.cropsnh.crops.food.natura.CropSaguaroCactus;
import com.gtnewhorizon.cropsnh.crops.food.pam.CropStrawberry;
import com.gtnewhorizon.cropsnh.crops.food.vanilla.CropCarrot;
import com.gtnewhorizon.cropsnh.crops.food.vanilla.CropCocoa;
import com.gtnewhorizon.cropsnh.crops.food.vanilla.CropMelon;
import com.gtnewhorizon.cropsnh.crops.food.vanilla.CropPotato;
import com.gtnewhorizon.cropsnh.crops.food.vanilla.CropPumpkin;
import com.gtnewhorizon.cropsnh.crops.food.vanilla.CropWheat;
import com.gtnewhorizon.cropsnh.crops.ic2.CropCyazint;
import com.gtnewhorizon.cropsnh.crops.ic2.CropCyprium;
import com.gtnewhorizon.cropsnh.crops.ic2.CropPlumbiscus;
import com.gtnewhorizon.cropsnh.crops.ic2.CropPurpleTulip;
import com.gtnewhorizon.cropsnh.crops.ic2.CropShining;
import com.gtnewhorizon.cropsnh.crops.ic2.CropStagnium;
import com.gtnewhorizon.cropsnh.crops.ic2.CropStickReed;
import com.gtnewhorizon.cropsnh.crops.ic2.CropTerraWart;
import com.gtnewhorizon.cropsnh.crops.ic2.CropVenomilia;
import com.gtnewhorizon.cropsnh.crops.material.CropArgentia;
import com.gtnewhorizon.cropsnh.crops.material.CropAurelia;
import com.gtnewhorizon.cropsnh.crops.material.CropBauxia;
import com.gtnewhorizon.cropsnh.crops.material.CropBobsYerUncleRanks;
import com.gtnewhorizon.cropsnh.crops.material.CropCanola;
import com.gtnewhorizon.cropsnh.crops.material.CropCoppon;
import com.gtnewhorizon.cropsnh.crops.material.CropDiareed;
import com.gtnewhorizon.cropsnh.crops.material.CropEvilOre;
import com.gtnewhorizon.cropsnh.crops.material.CropFerru;
import com.gtnewhorizon.cropsnh.crops.material.CropGalvania;
import com.gtnewhorizon.cropsnh.crops.material.CropGarnydnia;
import com.gtnewhorizon.cropsnh.crops.material.CropGlowheat;
import com.gtnewhorizon.cropsnh.crops.material.CropGodOfThunder;
import com.gtnewhorizon.cropsnh.crops.material.CropIndigo;
import com.gtnewhorizon.cropsnh.crops.material.CropIridine;
import com.gtnewhorizon.cropsnh.crops.material.CropLazulia;
import com.gtnewhorizon.cropsnh.crops.material.CropLiveroot;
import com.gtnewhorizon.cropsnh.crops.material.CropMagicalNightshade;
import com.gtnewhorizon.cropsnh.crops.material.CropMicadia;
import com.gtnewhorizon.cropsnh.crops.material.CropMilkwart;
import com.gtnewhorizon.cropsnh.crops.material.CropNickelback;
import com.gtnewhorizon.cropsnh.crops.material.CropOilBerry;
import com.gtnewhorizon.cropsnh.crops.material.CropOlivia;
import com.gtnewhorizon.cropsnh.crops.material.CropOsmianth;
import com.gtnewhorizon.cropsnh.crops.material.CropPapyrus;
import com.gtnewhorizon.cropsnh.crops.material.CropPlatina;
import com.gtnewhorizon.cropsnh.crops.material.CropPlumbilia;
import com.gtnewhorizon.cropsnh.crops.material.CropPyrolusium;
import com.gtnewhorizon.cropsnh.crops.material.CropReactoria;
import com.gtnewhorizon.cropsnh.crops.material.CropRedwheat;
import com.gtnewhorizon.cropsnh.crops.material.CropSaltyRoot;
import com.gtnewhorizon.cropsnh.crops.material.CropSapphirum;
import com.gtnewhorizon.cropsnh.crops.material.CropScheelinium;
import com.gtnewhorizon.cropsnh.crops.material.CropSpaceFlower;
import com.gtnewhorizon.cropsnh.crops.material.CropStargatium;
import com.gtnewhorizon.cropsnh.crops.material.CropStarwart;
import com.gtnewhorizon.cropsnh.crops.material.CropSteeleafranks;
import com.gtnewhorizon.cropsnh.crops.material.CropTine;
import com.gtnewhorizon.cropsnh.crops.material.CropTitania;
import com.gtnewhorizon.cropsnh.crops.material.CropTransformium;
import com.gtnewhorizon.cropsnh.crops.material.CropTrollplant;
import com.gtnewhorizon.cropsnh.crops.material.CropWithereed;
import com.gtnewhorizon.cropsnh.crops.mobs.CropBlackthorn;
import com.gtnewhorizon.cropsnh.crops.mobs.CropBlazereed;
import com.gtnewhorizon.cropsnh.crops.mobs.CropCorium;
import com.gtnewhorizon.cropsnh.crops.mobs.CropCorpseplant;
import com.gtnewhorizon.cropsnh.crops.mobs.CropCreeperweed;
import com.gtnewhorizon.cropsnh.crops.mobs.CropEggPlant;
import com.gtnewhorizon.cropsnh.crops.mobs.CropEnderbloom;
import com.gtnewhorizon.cropsnh.crops.mobs.CropGoldfish;
import com.gtnewhorizon.cropsnh.crops.mobs.CropMeatrose;
import com.gtnewhorizon.cropsnh.crops.mobs.CropSlimeplant;
import com.gtnewhorizon.cropsnh.crops.mobs.CropSpidernip;
import com.gtnewhorizon.cropsnh.crops.mobs.CropTearstalks;
import com.gtnewhorizon.cropsnh.crops.mobs.CropZomplant;
import com.gtnewhorizon.cropsnh.crops.natura.CropCotton;
import com.gtnewhorizon.cropsnh.crops.natura.nether.CropThornvine;
import com.gtnewhorizon.cropsnh.crops.natura.nether.berry.CropBlightberry;
import com.gtnewhorizon.cropsnh.crops.natura.nether.berry.CropDuskberry;
import com.gtnewhorizon.cropsnh.crops.natura.nether.berry.CropSkyberry;
import com.gtnewhorizon.cropsnh.crops.natura.nether.berry.CropStingberry;
import com.gtnewhorizon.cropsnh.crops.natura.nether.glowshroom.CropBlueGlowshroom;
import com.gtnewhorizon.cropsnh.crops.natura.nether.glowshroom.CropGreenGlowshroom;
import com.gtnewhorizon.cropsnh.crops.natura.nether.glowshroom.CropPurpleGlowshroom;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropAluminiumOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropArditeOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropCobaltOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropCopperOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropEssenceOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropGoldOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropIronOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropKnightmetalBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropThauminiteOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropThaumiumOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropTinOreBerry;
import com.gtnewhorizon.cropsnh.crops.oreberries.CropVoidOreBerry;
import com.gtnewhorizon.cropsnh.crops.stoneilies.CropBasaltLily;
import com.gtnewhorizon.cropsnh.crops.stoneilies.CropBlackGraniteLily;
import com.gtnewhorizon.cropsnh.crops.stoneilies.CropClayLily;
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
import com.gtnewhorizon.cropsnh.crops.thaumcraft.CropCinderpearl;
import com.gtnewhorizon.cropsnh.crops.thaumcraft.CropManaBean;
import com.gtnewhorizon.cropsnh.crops.thaumcraft.CropPrimordialBerry;
import com.gtnewhorizon.cropsnh.crops.thaumcraft.CropShimmerleaf;
import com.gtnewhorizon.cropsnh.crops.twilightforest.CropTorchberry;
import com.gtnewhorizon.cropsnh.crops.vanilla.CropCactus;
import com.gtnewhorizon.cropsnh.crops.vanilla.CropNetherwart;
import com.gtnewhorizon.cropsnh.crops.vanilla.CropSugarCane;
import com.gtnewhorizon.cropsnh.crops.vanilla.CropVine;
import com.gtnewhorizon.cropsnh.crops.vanilla.CropWaterlily;
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
import com.gtnewhorizon.cropsnh.init.CropsNHCrops;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Mods;
import ic2.core.Ic2Items;
import tconstruct.world.TinkerWorld;

public class CropLoader {

    public static void postInit() {
        // weeds
        CropRegistry.instance.register(CropsNHCrops.Weed = new CropWeed());

        // Vanilla food
        CropRegistry.instance.register(CropsNHCrops.Carrot = new CropCarrot());
        CropRegistry.instance.register(CropsNHCrops.Cocoa = new CropCocoa());
        CropRegistry.instance.register(CropsNHCrops.Melon = new CropMelon());
        CropRegistry.instance.register(CropsNHCrops.Potato = new CropPotato());
        CropRegistry.instance.register(CropsNHCrops.Pumpkin = new CropPumpkin());
        CropRegistry.instance.register(CropsNHCrops.Wheat = new CropWheat());
        // vanilla mushrooms
        CropRegistry.instance.register(CropsNHCrops.RedMushroom = new CropRedMushroom());
        CropRegistry.instance.register(CropsNHCrops.BrownMushroom = new CropBrownMushroom());
        // vanilla flowers
        CropRegistry.instance.register(CropsNHCrops.Dandelion = new CropDandelion());
        CropRegistry.instance.register(CropsNHCrops.Poppy = new CropPoppy());
        CropRegistry.instance.register(CropsNHCrops.BlueOrchid = new CropBlueOrchid());
        CropRegistry.instance.register(CropsNHCrops.Allium = new CropAllium());
        CropRegistry.instance.register(CropsNHCrops.AzureBluet = new CropAzureBluet());
        CropRegistry.instance.register(CropsNHCrops.RedTulip = new CropRedTulip());
        CropRegistry.instance.register(CropsNHCrops.OrangeTulip = new CropOrangeTulip());
        CropRegistry.instance.register(CropsNHCrops.WhiteTulip = new CropWhiteTulip());
        CropRegistry.instance.register(CropsNHCrops.PinkTulip = new CropPinkTulip());
        CropRegistry.instance.register(CropsNHCrops.OxeyeDaisy = new CropOxeyeDaisy());
        // vanilla misc
        CropRegistry.instance.register(CropsNHCrops.Cactus = new CropCactus());
        CropRegistry.instance.register(CropsNHCrops.SugarCane = new CropSugarCane());
        CropRegistry.instance.register(CropsNHCrops.Netherwart = new CropNetherwart());
        // vanilla bonsais
        // spotless:off
        CropRegistry.instance.register(CropsNHCrops.BonsaiOak =     new CropBonsai("bonsaiOak",     new Color(0x7F6139), new Color(0x57AD3F), "Notch",   1, new ItemStack(Blocks.sapling, 1, 0), new ItemStack(Blocks.log, 1, 0),  CropsNHMutationPools.wood).addDrop(new ItemStack(Items.apple, 2), 500));
        CropRegistry.instance.register(CropsNHCrops.BonsaiSpruce =  new CropBonsai("bonsaiSpruce",  new Color(0x50361A), new Color(0x395A39), "Notch",   1, new ItemStack(Blocks.sapling, 1, 1), new ItemStack(Blocks.log, 1, 1),  CropsNHMutationPools.wood));
        CropRegistry.instance.register(CropsNHCrops.BonsaiBirch =   new CropBonsai("bonsaiBirch",   new Color(0xCFE3BA), new Color(0x648D38), "Notch",   1, new ItemStack(Blocks.sapling, 1, 2), new ItemStack(Blocks.log, 1, 2),  CropsNHMutationPools.wood));
        CropRegistry.instance.register(CropsNHCrops.BonsaiJungle =  new CropBonsai("bonsaiJungle",  new Color(0x393D0D), new Color(0x378020), "Notch",   1, new ItemStack(Blocks.sapling, 1, 3), new ItemStack(Blocks.log, 1, 3),  CropsNHMutationPools.wood, CropsNHMutationPools.jungle));
        CropRegistry.instance.register(CropsNHCrops.BonsaiAcacia =  new CropBonsai("bonsaiAcacia",  new Color(0x795A0D), new Color(0x71881B), "Notch",   1, new ItemStack(Blocks.sapling, 1, 4), new ItemStack(Blocks.log2, 1, 0), CropsNHMutationPools.wood, CropsNHMutationPools.desert));
        CropRegistry.instance.register(CropsNHCrops.BonsaiDarkOak = new CropBonsai("bonsaiDarkOak", new Color(0x684C29), new Color(0x459633), "Notch",   1, new ItemStack(Blocks.sapling, 1, 5), new ItemStack(Blocks.log2, 1, 1), CropsNHMutationPools.wood));
        // TODO: UPDATE ITEM DEFINITIONS WHEN RUBBER TREE IS MOVED TO GT5U
        CropRegistry.instance.register(CropsNHCrops.BonsaiRubber  = new CropBonsai("bonsaiRubber",  new Color(0x50361A), new Color(0x6C815D), "Alblaka", 1, Ic2Items.rubberSapling.copy(),                         Ic2Items.rubberWood.copy()).addDrop(ItemList.IC2_Resin.get(2), 5_00));
        // spotless:on
        CropRegistry.instance.register(CropsNHCrops.Aurelia = new CropAurelia());
        CropRegistry.instance.register(CropsNHCrops.Coffee = new CropCoffee());
        CropRegistry.instance.register(CropsNHCrops.Cyprium = new CropCyprium());
        CropRegistry.instance.register(CropsNHCrops.Ferru = new CropFerru());
        CropRegistry.instance.register(CropsNHCrops.Hops = new CropHops());
        CropRegistry.instance.register(CropsNHCrops.Plumbiscus = new CropPlumbiscus());
        CropRegistry.instance.register(CropsNHCrops.Redwheat = new CropRedwheat());
        CropRegistry.instance.register(CropsNHCrops.Shining = new CropShining());
        CropRegistry.instance.register(CropsNHCrops.Stagnium = new CropStagnium());
        CropRegistry.instance.register(CropsNHCrops.StickReed = new CropStickReed());
        CropRegistry.instance.register(CropsNHCrops.Venomilia = new CropVenomilia());
        // IC2
        // terrawart was part of ic2 but is now part of this mod
        CropRegistry.instance.register(CropsNHCrops.Cyazint = new CropCyazint());
        CropRegistry.instance.register(CropsNHCrops.TerraWart = new CropTerraWart());
        CropRegistry.instance.register(CropsNHCrops.PurpleTulip = new CropPurpleTulip());
        // Stone Lilies
        CropRegistry.instance.register(CropsNHCrops.StoneLily = new CropStoneLily());
        CropRegistry.instance.register(CropsNHCrops.SandLily = new CropSandLily());
        CropRegistry.instance.register(CropsNHCrops.ClayLily = new CropClayLily());
        CropRegistry.instance.register(CropsNHCrops.NetherStoneLily = new CropNetherStoneLily());
        CropRegistry.instance.register(CropsNHCrops.SoulSandLily = new CropSoulSandLily());
        CropRegistry.instance.register(CropsNHCrops.EndStoneLily = new CropEndStoneLily());
        CropRegistry.instance.register(CropsNHCrops.RedGraniteLily = new CropRedGraniteLily());
        CropRegistry.instance.register(CropsNHCrops.BlackGraniteLily = new CropBlackGraniteLily());
        CropRegistry.instance.register(CropsNHCrops.BasaltLily = new CropBasaltLily());
        CropRegistry.instance.register(CropsNHCrops.MarbleLily = new CropMarbleLily());
        if (Mods.Botania.isModLoaded()) {
            CropRegistry.instance.register(CropsNHCrops.AndesiteLily = new CropAndesiteLily());
            CropRegistry.instance.register(CropsNHCrops.DioriteLily = new CropDioriteLily());
            CropRegistry.instance.register(CropsNHCrops.GraniteLily = new CropGraniteLily());
        }
        // Crops++ gt crops
        // Ore Berries
        if (Mods.TinkerConstruct.isModLoaded()) {
            CropRegistry.instance.register(
                CropsNHCrops.BonsaiSlimy = new CropBonsai(
                    "bonsaiSlimy",
                    new Color(0x27C74D),
                    new Color(0x96EEED),
                    "C0bra5",
                    1,
                    new ItemStack(TinkerWorld.slimeSapling, 1, 0),
                    new ItemStack(TinkerWorld.slimeGel, 1, 0)));
            CropRegistry.instance.register(CropsNHCrops.AluminiumOreBerry = new CropAluminiumOreBerry());
            CropRegistry.instance.register(CropsNHCrops.CopperOreBerry = new CropCopperOreBerry());
            CropRegistry.instance.register(CropsNHCrops.EssenceOreBerry = new CropEssenceOreBerry());
            CropRegistry.instance.register(CropsNHCrops.GoldOreBerry = new CropGoldOreBerry());
            CropRegistry.instance.register(CropsNHCrops.IronOreBerry = new CropIronOreBerry());
            CropRegistry.instance.register(CropsNHCrops.TinOreBerry = new CropTinOreBerry());
            CropRegistry.instance.register(CropsNHCrops.ArditeOreBerry = new CropArditeOreBerry());
            CropRegistry.instance.register(CropsNHCrops.CobaltOreBerry = new CropCobaltOreBerry());
        }
        if (Mods.Thaumcraft.isModLoaded()) {
            CropRegistry.instance.register(CropsNHCrops.ThaumiumOreBerry = new CropThaumiumOreBerry());
            CropRegistry.instance.register(CropsNHCrops.VoidOreBerry = new CropVoidOreBerry());
        }
        if (Mods.ThaumicBases.isModLoaded()) {
            CropRegistry.instance.register(CropsNHCrops.ThauminiteOreBerry = new CropThauminiteOreBerry());
        }
        // thaumcraft
        if (Mods.Thaumcraft.isModLoaded()) {
            CropRegistry.instance.register(CropsNHCrops.Cinderpearl = new CropCinderpearl());
            CropRegistry.instance.register(CropsNHCrops.Shimmerleaf = new CropShimmerleaf());
            CropRegistry.instance.register(CropsNHCrops.ManaBean = new CropManaBean());
            CropRegistry.instance.register(CropsNHCrops.PrimordialBerry = new CropPrimordialBerry());
        }
        // Crops++ Twilight Forest
        if (Mods.TwilightForest.isModLoaded()) {
            CropRegistry.instance.register(CropsNHCrops.KnightmetalBerry = new CropKnightmetalBerry());
            CropRegistry.instance.register(CropsNHCrops.Torchberry = new CropTorchberry());
        }
        if (Mods.TwilightForest.isModLoaded() && Mods.BiomesOPlenty.isModLoaded()) {
            CropRegistry.instance.register(CropsNHCrops.Moss = new CropMoss());
        }
        // Crops++ BoP
        if (Mods.BiomesOPlenty.isModLoaded()) {
            CropRegistry.instance.register(CropsNHCrops.Bamboo = new CropBamboo());
            CropRegistry.instance.register(CropsNHCrops.BoPBerry = new CropBoPBerry());
            CropRegistry.instance.register(CropsNHCrops.Eyebulb = new CropEyebulb());
            CropRegistry.instance.register(CropsNHCrops.FloweringVine = new CropFloweringVine());
            CropRegistry.instance.register(CropsNHCrops.Glowflower = new CropGlowflower());
            CropRegistry.instance.register(CropsNHCrops.GlowingCoral = new CropGlowingCoral());
            CropRegistry.instance.register(CropsNHCrops.Glowshroom = new CropGlowshroom());
            CropRegistry.instance.register(CropsNHCrops.Ivy = new CropIvy());
            CropRegistry.instance.register(CropsNHCrops.Turnip = new CropTurnip());
            CropRegistry.instance.register(CropsNHCrops.WildCarrot = new CropWildCarrot());
        }
        // Crops++ Witchery
        if (Mods.Witchery.isModLoaded()) {
            CropRegistry.instance.register(CropsNHCrops.Belladonna = new CropBelladonna());
            CropRegistry.instance.register(CropsNHCrops.EmberMoss = new CropEmberMoss());
            CropRegistry.instance.register(CropsNHCrops.Garlic = new CropGarlic());
            CropRegistry.instance.register(CropsNHCrops.GlintWeed = new CropGlintWeed());
            CropRegistry.instance.register(CropsNHCrops.Mandrake = new CropMandrake());
            CropRegistry.instance.register(CropsNHCrops.Snowbell = new CropSnowbell());
            CropRegistry.instance.register(CropsNHCrops.SpanishMoss = new CropSpanishMoss());
            CropRegistry.instance.register(CropsNHCrops.WaterArtichoke = new CropWaterArtichoke());
            CropRegistry.instance.register(CropsNHCrops.Wolfsbane = new CropWolfsbane());
        }
        // Crops++ Natura
        if (Mods.Natura.isModLoaded()) {
            CropRegistry.instance.register(CropsNHCrops.Blightberry = new CropBlightberry());
            CropRegistry.instance.register(CropsNHCrops.BlueGlowshroom = new CropBlueGlowshroom());
            CropRegistry.instance.register(CropsNHCrops.Duskberry = new CropDuskberry());
            CropRegistry.instance.register(CropsNHCrops.GreenGlowshroom = new CropGreenGlowshroom());
            CropRegistry.instance.register(CropsNHCrops.PurpleGlowshroom = new CropPurpleGlowshroom());
            CropRegistry.instance.register(CropsNHCrops.Skyberry = new CropSkyberry());
            CropRegistry.instance.register(CropsNHCrops.Stingberry = new CropStingberry());
            CropRegistry.instance.register(CropsNHCrops.Thornvine = new CropThornvine());
            CropRegistry.instance.register(CropsNHCrops.Barley = new CropBarley());
            CropRegistry.instance.register(CropsNHCrops.Blackberry = new CropBlackberry());
            CropRegistry.instance.register(CropsNHCrops.Blueberry = new CropBlueberry());
            CropRegistry.instance.register(CropsNHCrops.Cotton = new CropCotton());
            CropRegistry.instance.register(CropsNHCrops.Raspberry = new CropRaspberry());
            CropRegistry.instance.register(CropsNHCrops.Maloberry = new CropMaloberry());
            CropRegistry.instance.register(CropsNHCrops.SaguaroCactus = new CropSaguaroCactus());
        }
        // Crops++ native
        CropRegistry.instance.register(CropsNHCrops.Cucumber = new CropCucumber());
        CropRegistry.instance.register(CropsNHCrops.Goldfish = new CropGoldfish());
        CropRegistry.instance.register(CropsNHCrops.Huckleberry = new CropHuckleberry());
        CropRegistry.instance.register(CropsNHCrops.MagicalNightshade = new CropMagicalNightshade());
        CropRegistry.instance.register(CropsNHCrops.Papyrus = new CropPapyrus());
        CropRegistry.instance.register(CropsNHCrops.SpaceFlower = new CropSpaceFlower());
        CropRegistry.instance.register(CropsNHCrops.Strawberry = new CropStrawberry());
        CropRegistry.instance.register(CropsNHCrops.SugarBeet = new CropSugarBeet());
        CropRegistry.instance.register(CropsNHCrops.Vine = new CropVine());
        CropRegistry.instance.register(CropsNHCrops.Waterlily = new CropWaterlily());
        // GT crops
        CropRegistry.instance.register(CropsNHCrops.Hemp = new CropHemp());
        CropRegistry.instance.register(CropsNHCrops.SaltyRoot = new CropSaltyRoot());
        CropRegistry.instance.register(CropsNHCrops.Argentia = new CropArgentia());
        CropRegistry.instance.register(CropsNHCrops.Bauxia = new CropBauxia());
        CropRegistry.instance.register(CropsNHCrops.Blackthron = new CropBlackthorn());
        CropRegistry.instance.register(CropsNHCrops.Blazereed = new CropBlazereed());
        CropRegistry.instance.register(CropsNHCrops.BobsYerUncleRanks = new CropBobsYerUncleRanks());
        CropRegistry.instance.register(CropsNHCrops.Canola = new CropCanola());
        CropRegistry.instance.register(CropsNHCrops.Chilly = new CropChilly());
        CropRegistry.instance.register(CropsNHCrops.Coppon = new CropCoppon());
        CropRegistry.instance.register(CropsNHCrops.Corium = new CropCorium());
        CropRegistry.instance.register(CropsNHCrops.Corpseplant = new CropCorpseplant());
        CropRegistry.instance.register(CropsNHCrops.Creeperweed = new CropCreeperweed());
        CropRegistry.instance.register(CropsNHCrops.Diareed = new CropDiareed());
        CropRegistry.instance.register(CropsNHCrops.EggPlant = new CropEggPlant());
        CropRegistry.instance.register(CropsNHCrops.Enderbloom = new CropEnderbloom());
        CropRegistry.instance.register(CropsNHCrops.EvilOre = new CropEvilOre());
        CropRegistry.instance.register(CropsNHCrops.Fertilia = new CropFertilia());
        CropRegistry.instance.register(CropsNHCrops.Flax = new CropFlax());
        CropRegistry.instance.register(CropsNHCrops.Galvania = new CropGalvania());
        CropRegistry.instance.register(CropsNHCrops.Garnydnia = new CropGarnydnia());
        CropRegistry.instance.register(CropsNHCrops.Glowheat = new CropGlowheat());
        CropRegistry.instance.register(CropsNHCrops.GodOfThunder = new CropGodOfThunder());
        CropRegistry.instance.register(CropsNHCrops.Grape = new CropGrape());
        CropRegistry.instance.register(CropsNHCrops.Indigo = new CropIndigo());
        CropRegistry.instance.register(CropsNHCrops.Iridine = new CropIridine());
        CropRegistry.instance.register(CropsNHCrops.Lazulia = new CropLazulia());
        CropRegistry.instance.register(CropsNHCrops.Lemon = new CropLemon());
        CropRegistry.instance.register(CropsNHCrops.Liveroot = new CropLiveroot());
        CropRegistry.instance.register(CropsNHCrops.Meatrose = new CropMeatrose());
        CropRegistry.instance.register(CropsNHCrops.Micadia = new CropMicadia());
        CropRegistry.instance.register(CropsNHCrops.Milkwart = new CropMilkwart());
        CropRegistry.instance.register(CropsNHCrops.Nickelback = new CropNickelback());
        CropRegistry.instance.register(CropsNHCrops.OilBerry = new CropOilBerry());
        CropRegistry.instance.register(CropsNHCrops.Olivia = new CropOlivia());
        CropRegistry.instance.register(CropsNHCrops.Onion = new CropOnion());
        CropRegistry.instance.register(CropsNHCrops.Osmianth = new CropOsmianth());
        CropRegistry.instance.register(CropsNHCrops.Platina = new CropPlatina());
        CropRegistry.instance.register(CropsNHCrops.Plumbilia = new CropPlumbilia());
        CropRegistry.instance.register(CropsNHCrops.Pyrolusium = new CropPyrolusium());
        CropRegistry.instance.register(CropsNHCrops.Reactoria = new CropReactoria());
        CropRegistry.instance.register(CropsNHCrops.Sapphirum = new CropSapphirum());
        CropRegistry.instance.register(CropsNHCrops.Scheelinium = new CropScheelinium());
        CropRegistry.instance.register(CropsNHCrops.Slimeplant = new CropSlimeplant());
        CropRegistry.instance.register(CropsNHCrops.Spidernip = new CropSpidernip());
        CropRegistry.instance.register(CropsNHCrops.Stargatium = new CropStargatium());
        CropRegistry.instance.register(CropsNHCrops.Starwart = new CropStarwart());
        CropRegistry.instance.register(CropsNHCrops.Steeleafranks = new CropSteeleafranks());
        CropRegistry.instance.register(CropsNHCrops.Tea = new CropTea());
        CropRegistry.instance.register(CropsNHCrops.Tearstalks = new CropTearstalks());
        CropRegistry.instance.register(CropsNHCrops.Tine = new CropTine());
        CropRegistry.instance.register(CropsNHCrops.Titania = new CropTitania());
        CropRegistry.instance.register(CropsNHCrops.Tomato = new CropTomato());
        CropRegistry.instance.register(CropsNHCrops.Transformium = new CropTransformium());
        CropRegistry.instance.register(CropsNHCrops.Trollplant = new CropTrollplant());
        CropRegistry.instance.register(CropsNHCrops.Withereed = new CropWithereed());
        CropRegistry.instance.register(CropsNHCrops.Zomplant = new CropZomplant());

    }
}
