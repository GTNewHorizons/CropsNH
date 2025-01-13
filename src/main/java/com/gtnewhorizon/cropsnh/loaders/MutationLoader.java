package com.gtnewhorizon.cropsnh.loaders;

import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Allium;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.AluminiumOreBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.AndesiteLily;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.ArditeOreBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Argentia;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Aurelia;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.AzureBluet;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Bamboo;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Barley;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.BasaltLily;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Bauxia;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Belladonna;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.BlackGraniteLily;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Blackberry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Blackthron;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Blazereed;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Blightberry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.BlueGlowshroom;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.BlueOrchid;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Blueberry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.BoPBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.BobsYerUncleRanks;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.BonsaiAcacia;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.BonsaiBirch;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.BonsaiDarkOak;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.BonsaiJungle;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.BonsaiOak;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.BonsaiRubber;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.BonsaiSpruce;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.BrownMushroom;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Cactus;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Canola;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Carrot;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Chilly;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Cinderpearl;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.ClayLily;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.CobaltOreBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Cocoa;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Coffee;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.CopperOreBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Coppon;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Corium;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Corpseplant;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Creeperweed;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Cucumber;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Cyazint;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Cyprium;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Dandelion;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Diareed;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.DioriteLily;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Duskberry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.EggPlant;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.EmberMoss;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.EndStoneLily;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Enderbloom;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.EssenceOreBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.EvilOre;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Eyebulb;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Ferru;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Fertilia;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Flax;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.FloweringVine;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Galvania;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Garlic;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Garnydnia;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.GlintWeed;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Glowflower;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Glowheat;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.GlowingCoral;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Glowshroom;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.GodOfThunder;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.GoldOreBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Goldfish;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.GraniteLily;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Grape;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.GreenGlowshroom;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Hemp;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Hops;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Huckleberry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Indigo;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Iridine;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.IronOreBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Ivy;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.KnightmetalBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Lazulia;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Lemon;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Liveroot;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.MagicalNightshade;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Maloberry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.ManaBean;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Mandrake;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.MarbleLily;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Meatrose;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Melon;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Micadia;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Milkwart;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Moss;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.NetherStoneLily;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Netherwart;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Nickelback;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.OilBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Olivia;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Onion;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.OrangeTulip;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Osmianth;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.OxeyeDaisy;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Papyrus;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.PinkTulip;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Platina;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Plumbilia;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Plumbiscus;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Poppy;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Potato;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.PrimordialBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Pumpkin;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.PurpleGlowshroom;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.PurpleTulip;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Pyrolusium;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Raspberry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Reactoria;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.RedGraniteLily;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.RedMushroom;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.RedTulip;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Redwheat;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.SaguaroCactus;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.SaltyRoot;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.SandLily;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Sapphirum;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Scheelinium;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Shimmerleaf;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Shining;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Skyberry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Slimeplant;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Snowbell;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.SoulSandLily;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.SpaceFlower;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.SpanishMoss;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Spidernip;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Stagnium;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Stargatium;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Starwart;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Steeleafranks;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.StickReed;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Stingberry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.StoneLily;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Strawberry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.SugarBeet;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.SugarCane;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Tea;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Tearstalks;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.ThauminiteOreBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.ThaumiumOreBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Thornvine;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.TinOreBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Tine;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Titania;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Tomato;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Torchberry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Transformium;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Trollplant;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Turnip;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Venomilia;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Vine;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.VoidOreBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.WaterArtichoke;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Waterlily;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Wheat;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.WhiteTulip;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.WildCarrot;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Withereed;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Wolfsbane;
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Zomplant;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.farming.mutation.CropMutation;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;

import gregtech.api.enums.Mods;

public class MutationLoader {

    public static void postInit() {

        // most of this is either:
        // - mild taxonomical relations according to wikipedia
        // - based on bee progression
        // - balance related tier gating
        // - mildly random guesses
        //
        // if you have some good botanical knowledge
        // feel free to update this to be more accurate

        // region bonsais
        new CropMutation(BonsaiOak, Wheat, BrownMushroom).register();
        new CropMutation(BonsaiBirch, BonsaiOak, SugarCane).register();
        new CropMutation(BonsaiSpruce, BonsaiOak, Pumpkin).register();
        new CropMutation(BonsaiDarkOak, BonsaiSpruce, BonsaiOak).register();
        new CropMutation(BonsaiAcacia, BonsaiOak, Cactus).register();
        new CropMutation(BonsaiJungle, BonsaiOak, Vine).register();
        new CropMutation(BonsaiRubber, BonsaiJungle, BonsaiSpruce).register();
        // endregion bonsais

        // region biomes o plenty
        if (Mods.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Bamboo, BonsaiJungle, Vine).register();
            new CropMutation(Eyebulb, NetherStoneLily, RedTulip).register();
            new CropMutation(FloweringVine, OxeyeDaisy, Ivy).register();
            new CropMutation(Glowflower, NetherStoneLily, Dandelion).register();
            new CropMutation(GlowingCoral, Glowflower, Waterlily).register();
            if (Mods.Natura.isModLoaded()) {
                new CropMutation(Glowshroom, BlueGlowshroom, GreenGlowshroom, PurpleGlowshroom).register();
            }
            new CropMutation(Ivy, Vine, BonsaiSpruce).register();
            new CropMutation(Moss, Ivy, FloweringVine).register();
        }
        // endregion biomes o plenty

        // region crops nh
        // TODO ADD PHOSPHORUS CROP AND ADD IT TO THE FERTILIA PARENT LIST
        // Corpse plant because it outputs bones, corium because cow manure
        new CropMutation(Fertilia, Corium, Corpseplant).register();
        new CropMutation(Flax, Wheat, AzureBluet).register();
        new CropMutation(Hemp, Flax, Papyrus).register();
        // endregion cropsnh

        // region food
        if (Mods.BiomesOPlenty.isModLoaded()) {
            new CropMutation(BoPBerry, Poppy, Blackberry).register();
            new CropMutation(Turnip, Potato, BoPBerry).register();
            new CropMutation(WildCarrot, Carrot, BoPBerry).register();
        }
        if (Mods.Natura.isModLoaded()) {
            new CropMutation(Barley, Bamboo, Wheat).register();
            new CropMutation(Blackberry, Strawberry, Blueberry).register();
            new CropMutation(Blueberry, AzureBluet, BonsaiOak).register();
            new CropMutation(Maloberry, OrangeTulip, Blueberry).register();
            new CropMutation(Raspberry, RedTulip, OxeyeDaisy).register();
            new CropMutation(SaguaroCactus, Cactus, SandLily).register();
        }
        if (Mods.PamsHarvestCraft.isModLoaded()) {
            new CropMutation(Strawberry, Wheat, Raspberry).register();
        }
        new CropMutation(Chilly, Cactus, Cocoa).register();
        new CropMutation(Coffee, Cocoa, BonsaiAcacia).register();
        new CropMutation(Cucumber, Melon, Carrot).register();
        new CropMutation(Grape, Blueberry, Blackberry).register();
        new CropMutation(Hops, Hemp, Dandelion).register();
        new CropMutation(Huckleberry, Blackberry, Grape).register();
        new CropMutation(Lemon, BonsaiOak, BonsaiAcacia).register();
        // allium is the genus for onions
        new CropMutation(Onion, Allium, Carrot).register();
        new CropMutation(SugarBeet, SugarCane, Allium).register();
        new CropMutation(Tea, Blueberry, BonsaiJungle).register();
        new CropMutation(Tomato, Onion, RedTulip).register();
        // endregion food

        // region ic2
        new CropMutation(Cyazint, AzureBluet, BlueOrchid).register();
        new CropMutation(Cyprium, OrangeTulip, Pumpkin, StoneLily).register();
        new CropMutation(Plumbiscus, AzureBluet, Cyprium, BlackGraniteLily).register();
        new CropMutation(Shining, Stagnium, Cyprium, MarbleLily).register();
        new CropMutation(Stagnium, Cyprium, StoneLily, StoneLily).register();
        new CropMutation(StickReed, BonsaiJungle, SugarCane).register();
        // terrawart has its own hidden mechanic that requires you to right-click a netherwart
        // with snow until it turns into terrawart
        new CropMutation(PurpleTulip, RedTulip, BlueOrchid).register();
        new CropMutation(Venomilia, Spidernip, PurpleTulip).register();
        // endregion ic2

        // region material crops
        new CropMutation(Argentia, Plumbilia, Tine).register();
        new CropMutation(Aurelia, Plumbilia, Coppon).register();
        new CropMutation(Bauxia, Galvania, Nickelback).machineOnly()
            .register();
        new CropMutation(BobsYerUncleRanks, Diareed, Olivia).register();
        new CropMutation(Canola, OxeyeDaisy, Wheat).register();
        new CropMutation(Coppon, StoneLily, NetherStoneLily).register();
        new CropMutation(Diareed, Withereed, EvilOre).register();
        new CropMutation(EvilOre, NetherStoneLily, SoulSandLily).register();
        new CropMutation(Galvania, Tine, Ferru).register();
        new CropMutation(Garnydnia, Diareed, Redwheat).machineOnly()
            .register();
        if (Mods.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Glowheat, Glowflower, Wheat).register();
        }
        new CropMutation(GodOfThunder, BobsYerUncleRanks, Withereed).machineOnly()
            .register();
        new CropMutation(Indigo, Cyazint, BlueOrchid).register();
        new CropMutation(Iridine, Scheelinium, Platina).machineOnly()
            .register();
        new CropMutation(Lazulia, StoneLily, Indigo).register();
        // using dark oak because it's a tree that used to have roots that were used to break the nether ceiling
        if (Mods.TwilightForest.isModLoaded()) {
            new CropMutation(Liveroot, Torchberry, BonsaiDarkOak).register();
        }
        if (Mods.Thaumcraft.isModLoaded() && Mods.ThaumicTinkerer.isModLoaded()) {
            new CropMutation(MagicalNightshade, PrimordialBerry, ManaBean, Cinderpearl, Shimmerleaf).machineOnly()
                .register();
        }
        new CropMutation(Micadia, Tine, Bauxia).machineOnly()
            .register();
        new CropMutation(Milkwart, Corium, Netherwart).register();
        new CropMutation(Nickelback, Ferru, Coppon).register();
        new CropMutation(OilBerry, SoulSandLily, Withereed).machineOnly()
            .register();
        new CropMutation(Olivia, EndStoneLily, EvilOre).machineOnly()
            .register();
        new CropMutation(Osmianth, Platina, Scheelinium).machineOnly()
            .register();
        // leather bound books, birch has a paper-like bark
        new CropMutation(Papyrus, Corium, BonsaiBirch).register();
        // TODO: ADD CHROME CROP AND REPLACE TITANIA WITH NEW CROP IN PLATINA MUTATION
        new CropMutation(Platina, Diareed, Titania).machineOnly()
            .register();
        new CropMutation(Plumbilia, Coppon, Withereed).register();
        new CropMutation(Pyrolusium, Bauxia, Titania).register();
        new CropMutation(Reactoria, Titania, GodOfThunder).machineOnly()
            .register();
        new CropMutation(Redwheat, NetherStoneLily, Wheat).register();
        new CropMutation(SaltyRoot, SugarBeet, Canola).register();
        new CropMutation(Sapphirum, EvilOre, Lazulia).register();
        new CropMutation(Scheelinium, Titania, Pyrolusium, EndStoneLily).machineOnly()
            .register();
        new CropMutation(SpaceFlower, EndStoneLily, Titania).machineOnly()
            .register();
        // TODO: ADD PLUTONIUM CROP AND REPLACE TROLL PLANT WITH NEW CROP IN STARGATIUM MUTATION
        new CropMutation(Stargatium, Iridine, Trollplant).machineOnly()
            .register();
        // late hv to early hv is where I want starwart to be gated around, might reconsider based on existing metas
        // it can be used in MECs for a pretty hefty amount of eu so gating it up until then is probably a go since
        // 1 nether star dust is like 1.5h of hv power in there.
        new CropMutation(Starwart, Withereed, Titania, GodOfThunder).machineOnly()
            .register();
        if (Mods.TwilightForest.isModLoaded()) {
            new CropMutation(Steeleafranks, Torchberry, KnightmetalBerry).register();
        }
        new CropMutation(Tine, StoneLily, BonsaiSpruce).register();
        new CropMutation(Titania, Bauxia, Redwheat).machineOnly()
            .register();
        new CropMutation(Transformium, Trollplant, Bauxia, Titania).machineOnly()
            .register();
        // goldfish is purely here to troll
        new CropMutation(Trollplant, Goldfish, Reactoria, Fertilia).machineOnly()
            .register();
        new CropMutation(Withereed, BasaltLily, BlackGraniteLily).register();
        // endregion material crops

        // region mobs
        if (Mods.Natura.isModLoaded()) {
            new CropMutation(Blackthron, Blackberry, Goldfish).register();
        }
        if (Mods.Thaumcraft.isModLoaded()) {
            new CropMutation(Blazereed, SugarCane, Cinderpearl).register();
        }
        new CropMutation(Corium, Wheat, Cocoa).register();
        if (Mods.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Corpseplant, Zomplant, Eyebulb).register();
        }
        // sulfur, charcoal and salt peter
        new CropMutation(Creeperweed, NetherStoneLily, Withereed, SaltyRoot).register();
        new CropMutation(EggPlant, Corium, OxeyeDaisy).register();
        new CropMutation(Enderbloom, EndStoneLily, Creeperweed).register();
        if (Mods.Witchery.isModLoaded()) {
            new CropMutation(Goldfish, Waterlily, WaterArtichoke, Mandrake).register();
        }
        new CropMutation(Meatrose, Goldfish, EggPlant, Corium).register();
        new CropMutation(Slimeplant, BlueOrchid, ClayLily).register();
        new CropMutation(Spidernip, Flax, Hemp).register();
        new CropMutation(Tearstalks, Goldfish, SoulSandLily, NetherStoneLily).register();
        if (Mods.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Zomplant, Withereed, Eyebulb).register();
        }
        // endregion mobs

        // region natura
        if (Mods.Natura.isModLoaded()) {
            new CropMutation(Blightberry, Maloberry, Raspberry).register();
            new CropMutation(Duskberry, Blackthron, Blackberry).register();
            new CropMutation(Skyberry, Cyazint, Blueberry).register();
            new CropMutation(Stingberry, Thornvine, SaguaroCactus).register();
            if (Mods.BiomesOPlenty.isModLoaded()) {
                new CropMutation(BlueGlowshroom, BlueOrchid, Glowflower).register();
                new CropMutation(GreenGlowshroom, Cactus, Glowflower).register();
                new CropMutation(PurpleGlowshroom, Indigo, Glowflower).register();
            }
        }
        // endregion natura

        // region ore berries
        if (Mods.TinkerConstruct.isModLoaded()) {
            new CropMutation(AluminiumOreBerry, GoldOreBerry, EssenceOreBerry).register();
            new CropMutation(ArditeOreBerry, NetherStoneLily, Coppon, CopperOreBerry, Cyprium).register();
            new CropMutation(CobaltOreBerry, NetherStoneLily, ArditeOreBerry, Lazulia, GoldOreBerry).register();
            if (Mods.BiomesOPlenty.isModLoaded()) {
                new CropMutation(CopperOreBerry, Cyprium, BoPBerry, StoneLily).register();
            }
            new CropMutation(EssenceOreBerry, Creeperweed, Zomplant, Spidernip, Tearstalks).register();
            new CropMutation(GoldOreBerry, CopperOreBerry, IronOreBerry, TinOreBerry).register();
            new CropMutation(IronOreBerry, TinOreBerry, StoneLily).register();
            if (Mods.BiomesOPlenty.isModLoaded()) {
                new CropMutation(TinOreBerry, Stagnium, BoPBerry, StoneLily).register();
            }
            if (Mods.TwilightForest.isModLoaded()) {
                new CropMutation(KnightmetalBerry, IronOreBerry, Torchberry, BonsaiDarkOak).register();
            }
            if (Mods.Thaumcraft.isModLoaded()) {
                // TODO: ADD GREATWOOD AND SILVER WOOD BONSAIS AND ADD DETERMINISTIC RECIPE FOR THAUMIUM ORE BERRY
                new CropMutation(ThaumiumOreBerry, StoneLily, GoldOreBerry).register();
                new CropMutation(VoidOreBerry, ThaumiumOreBerry, GoldOreBerry).register();
                if (Mods.ThaumicBases.isModLoaded()) {
                    new CropMutation(ThauminiteOreBerry, ThaumiumOreBerry, ManaBean).register();
                }
            }
        }
        // endregion ore berries

        // region stone lilies
        if (Mods.Botania.isModLoaded()) {
            new CropMutation(AndesiteLily, StoneLily, ClayLily).register();
            new CropMutation(DioriteLily, StoneLily, MarbleLily).register();
            new CropMutation(GraniteLily, BlackGraniteLily, RedGraniteLily).register();
        }
        new CropMutation(BasaltLily, StoneLily, Blackthron).register();
        new CropMutation(BlackGraniteLily, StoneLily, BasaltLily).register();
        new CropMutation(ClayLily, StoneLily, Waterlily).register();
        new CropMutation(EndStoneLily, StoneLily, Enderbloom).register();
        new CropMutation(MarbleLily, StoneLily, OxeyeDaisy).register();
        new CropMutation(NetherStoneLily, StoneLily, Netherwart).register();
        new CropMutation(RedGraniteLily, StoneLily, Poppy).register();
        new CropMutation(SandLily, StoneLily, Cactus).register();
        new CropMutation(SoulSandLily, NetherStoneLily, SandLily).register();
        new CropMutation(StoneLily, Vine, Pumpkin).register();
        // endregion stone lilies

        // region thaumcraft
        if (Mods.Thaumcraft.isModLoaded()) {
            if (Mods.Witchery.isModLoaded()) {
                new CropMutation(Cinderpearl, EmberMoss, GlintWeed).register();
            }
            new CropMutation(ManaBean, Cocoa, Cinderpearl, Shimmerleaf).register();
            // prim perl can't be bred, only planted with an actual prim perl
            // redwheat because shimmerleaf is a quickilver/mercury source and redstone is also that
            new CropMutation(Shimmerleaf, WhiteTulip, Redwheat).register();
        }
        // endregion thaumcraft

        // region twilight forest
        if (Mods.TwilightForest.isModLoaded() && Mods.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Torchberry, Glowflower, BoPBerry).register();
        }
        // endregion thaumcraft

        // region vanilla
        new CropMutation(Cocoa, Melon, BonsaiJungle).register();
        new CropMutation(Melon, Pumpkin, BonsaiJungle).register();
        new CropMutation(Pumpkin, Carrot, SugarCane).register();
        new CropMutation(RedMushroom, Pumpkin, Poppy).register();
        new CropMutation(BrownMushroom, Cocoa, RedMushroom).register();
        new CropMutation(BlueOrchid, Poppy, Waterlily).register();
        new CropMutation(AzureBluet, Poppy, Potato).register();
        new CropMutation(RedTulip, Poppy, WhiteTulip).register();
        new CropMutation(OrangeTulip, RedTulip, Dandelion).register();
        new CropMutation(PinkTulip, RedTulip, WhiteTulip).register();
        new CropMutation(WhiteTulip, PinkTulip, OxeyeDaisy).register();
        new CropMutation(OxeyeDaisy, AzureBluet, Dandelion).register();
        new CropMutation(Cactus, SugarCane, Potato).register();
        new CropMutation(SugarCane, Carrot, Potato).register();
        new CropMutation(Netherwart, RedMushroom, BrownMushroom).register();
        new CropMutation(Vine, Pumpkin, BlueOrchid).register();
        new CropMutation(Waterlily, Vine, SugarCane).register();
        // endregion vanilla

        // region witchery
        if (Mods.Witchery.isModLoaded()) {
            new CropMutation(Belladonna, PurpleTulip, PinkTulip).register();
            if (Mods.BiomesOPlenty.isModLoaded()) {
                new CropMutation(EmberMoss, RedTulip, Moss).register();
            }
            new CropMutation(Garlic, Onion, Allium).register();

            new CropMutation(GlintWeed, Vine, EmberMoss).register();
            new CropMutation(Mandrake, Carrot, Belladonna).register();
            new CropMutation(Snowbell, Belladonna, GlintWeed).register();
            new CropMutation(SpanishMoss, Moss, EmberMoss).register();
            new CropMutation(WaterArtichoke, Waterlily, Hops).register();
            new CropMutation(Wolfsbane, WaterArtichoke, Belladonna).register();
        }
        // endregion witchery

        for (ICropCard cc : CropRegistry.instance.getAllInRegistrationOrder()) {
            cc.registerToPools();
        }

    }
}
