package com.gtnewhorizon.cropsnh.loaders;

import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Allium;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.AluminiumOreBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.AndesiteLily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.ArditeOreBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Argentia;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Auronia;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.AzureBluet;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Bamboo;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Barley;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.BasaltLily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Bauxia;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Belladonna;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.BlackGraniteLily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Blackberry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Blazereed;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Blightberry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.BlueGlowshroom;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.BlueOrchid;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Blueberry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.BoPBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.BobsYerUncleRanks;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.BonsaiAcacia;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.BonsaiBirch;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.BonsaiDarkOak;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.BonsaiJungle;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.BonsaiOak;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.BonsaiRubber;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.BonsaiSlimy;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.BonsaiSpruce;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.BrownMushroom;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Cactus;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Canola;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Carrot;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Cassitine;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Chilly;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Cinderpearl;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.ClayLily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.CobaltOreBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Cocoa;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Coffee;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.CopperOreBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Coppon;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Corium;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Corpseplant;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Cotton;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Creeperweed;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Cucumber;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Dandelion;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Dayflower;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.DeepslateLily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Diareed;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.DioriteLily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Duskberry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.EggPlant;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.EmberMoss;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.EndStoneLily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Enderbloom;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.EssenceOreBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.EvilOre;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Eyebulb;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Ferrofern;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Fertilia;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Flax;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.FloweringVine;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Galvania;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Garlic;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Garnydinia;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.GlintWeed;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Glowflower;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Glowheat;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.GlowingCoral;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Glowshroom;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.GodOfThunder;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.GoldOreBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Goldfish;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.GraniteLily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Grape;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.GreenGlowshroom;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Hemp;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Hops;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Huckleberry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Indigo;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.InkBloom;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Iridine;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.IronOreBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Ivy;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.KnightmetalBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Lazulia;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Lemon;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Liveroot;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.MagicalNightshade;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Malaxia;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Maloberry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.ManaBean;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Mandrake;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.MarbleLily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Meatrose;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Melon;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Micadia;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.MilkWart;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Moss;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Necrobloom;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.NetherStoneLily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Netherwart;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Nickelback;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.OilBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Olivia;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Onion;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.OrangeTulip;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Osmianth;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.OxeyeDaisy;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Papyrus;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.PinkTulip;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Platina;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Plumbilia;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Plumbshade;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Poppy;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Potato;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.PrimordialBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Pumpkin;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.PurpleGlowshroom;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.PurpleTulip;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Pyrolusium;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Raspberry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Reactoria;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.RedGraniteLily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.RedMushroom;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.RedStraw;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.RedTulip;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Rubyne;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.SaguaroCactus;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.SaltyRoot;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.SandLily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Sapphirum;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Scheelinium;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Shimmerleaf;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Silviscus;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Skyberry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Slimeplant;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Snowbell;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.SoulSandLily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.SpaceFlower;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.SpanishMoss;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Spidernip;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.StarWart;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Stargatium;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Steeleafranks;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.StickyCane;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Stingberry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.StoneLily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Strawberry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.SugarBeet;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.SugarCane;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Tea;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Tearstalks;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.ThauminiteOreBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.ThaumiumOreBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Thiosulfine;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Thornvine;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.TinOreBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Tine;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Titania;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Tomato;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Torchberry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Transformium;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Trollplant;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.TuffLily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Turnip;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Vine;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.VoidOreBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.WaterArtichoke;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Waterlily;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Wheat;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.WhiteTulip;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.WildCarrot;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Withereed;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Wolfsbane;
import static com.gtnewhorizon.cropsnh.api.CropsNHCrops.Zomplant;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ADDICTIVE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ALIEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BEAN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BERRY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLACK;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLAZE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLUE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BROWN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BUSH;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.CACTUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.CHICKEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.CLIMBABLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.COAL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.COPPER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.COTTON;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.COW;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.CRYSTALLINE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DANGEROUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DARK;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DENSE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EDIBLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EMISSIVE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EVIL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.FIERY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.FISH;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.FLOWER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GOLD;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GRAY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GREEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.HEALING;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.IRON;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.LEAD;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.LEAFY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.MAGICAL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.METALLIC;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.MUSHROOM;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.NETHER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.OIL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ORANGE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ORE_BERRY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.POISONOUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.POTION_INGREDIENT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.PURPLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.RED;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.REED;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ROOT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SALTPETER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SHINY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SILK;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SILVER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.STEM;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.STICKY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.STONE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SULFUR;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TENDRILLY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TIN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TREE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TULIP;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TWILIGHT_FOREST;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.UNDEAD;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.VOID_TOUCHED;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WATERY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHEAT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHITE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WOODEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.YELLOW;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.farming.mutation.CropMutation;
import com.gtnewhorizon.cropsnh.farming.registries.MutationRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.breeding.MachineBreedingCatalystRequirement;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.Mods;

public class MutationLoader {

    public static void postInit() {

        // Most of this is:
        // - Mild taxonomic relations according to wikipedia
        // - Based on bee progression
        // - Balance related tier gating
        // - Mildly random guesses
        // - Preventing 2 mutations from having the exact same list of parents.
        //
        // If you have some good botanical knowledge; Feel free to update this to be more accurate.

        // turns off spotless because it gets real hard to read when it's on.
        // spotless:off
        // region bonsais

        MutationRegistry.instance.register(BonsaiOak, BROWN, TREE, LEAFY, WOODEN, EDIBLE);
        new CropMutation(BonsaiOak, Wheat, BrownMushroom)
            .register();

        MutationRegistry.instance.register(BonsaiBirch, WHITE, TREE, LEAFY, WOODEN);
        new CropMutation(BonsaiBirch, BonsaiOak, SugarCane)
            .register();

        MutationRegistry.instance.register(BonsaiSpruce, BROWN, TREE, WOODEN);
        new CropMutation(BonsaiSpruce, BonsaiOak, Pumpkin)
            .register();

        MutationRegistry.instance.register(BonsaiDarkOak, BROWN, TREE, LEAFY, DARK, WOODEN, EDIBLE);
        new CropMutation(BonsaiDarkOak, BonsaiSpruce, BonsaiOak)
            .register();

        MutationRegistry.instance.register(BonsaiAcacia, ORANGE, TREE, LEAFY, WOODEN);
        new CropMutation(BonsaiAcacia, BonsaiOak, Cactus)
            .register();

        MutationRegistry.instance.register(BonsaiJungle, BROWN, TREE, LEAFY, WOODEN);
        new CropMutation(BonsaiJungle, BonsaiOak, Vine)
            .register();

        MutationRegistry.instance.register(BonsaiRubber, BROWN, TREE, WOODEN, STICKY);
        new CropMutation(BonsaiRubber, BonsaiJungle, BonsaiSpruce)
            .register();

        if (ModUtils.TinkerConstruct.isModLoaded()) {
            // gating it to lv breeder since otherwize it makes sticky reed and rubber bonsais pointless.
            new CropMutation(BonsaiSlimy, Slimeplant, BonsaiJungle, BonsaiRubber)
                .machineOnly()
                .register();
        }

        // endregion bonsais

        // region biomes o plenty
        if (ModUtils.BiomesOPlenty.isModLoaded()) {

            MutationRegistry.instance.register(Bamboo, GREEN, STEM, DANGEROUS, LEAFY);
            new CropMutation(Bamboo, BonsaiJungle, Vine)
                .register();

            MutationRegistry.instance.register(Eyebulb, RED, NETHER, EVIL, TENDRILLY);
            new CropMutation(Eyebulb, NetherStoneLily, RedTulip)
                .register();

            MutationRegistry.instance.register(FloweringVine, GREEN, FLOWER, TENDRILLY, CLIMBABLE);
            new CropMutation(FloweringVine, OxeyeDaisy, Ivy)
                .register();

            MutationRegistry.instance.register(Glowflower, BLUE, NETHER, EMISSIVE, SHINY, POTION_INGREDIENT);
            new CropMutation(Glowflower, NetherStoneLily, Dandelion)
                .register();

            MutationRegistry.instance.register(GlowingCoral, PURPLE, WATERY, EMISSIVE, SHINY, POTION_INGREDIENT);
            new CropMutation(GlowingCoral, Glowflower, Waterlily)
                .register();

            MutationRegistry.instance.register(Glowshroom, GREEN, NETHER, MUSHROOM, EMISSIVE, POTION_INGREDIENT);
            if (ModUtils.Natura.isModLoaded()) {
                new CropMutation(Glowshroom, BlueGlowshroom, GreenGlowshroom, PurpleGlowshroom)
                    .register();
            }

            MutationRegistry.instance.register(Ivy, GREEN, FLOWER, TENDRILLY, DANGEROUS, POISONOUS, CLIMBABLE);
            new CropMutation(Ivy, Vine, BonsaiSpruce)
                .register();
        }

        // a bit of duplicated code but it works out since moss doesn't care what mod is loaded to load.
        MutationRegistry.instance.register(Moss, GREEN, TWILIGHT_FOREST, CLIMBABLE, TENDRILLY);
        if (ModUtils.BiomesOPlenty.isModLoaded()) {
            // Also findable via bop but i like having it as an in to that pool.
            new CropMutation(Moss, Ivy, FloweringVine)
                .register();
        }
        // endregion biomes o plenty

        // region crops nh

        // TODO ADD PHOSPHORUS CROP AND ADD IT TO THE FERTILIA PARENT LIST
        // Corpse plant because it outputs bones, corium because cow manure
        MutationRegistry.instance.register(Fertilia, BROWN, MUSHROOM, HEALING);
        new CropMutation(Fertilia, Corium, Corpseplant)
            .register();

        MutationRegistry.instance.register(Flax, BLUE, WHEAT, ADDICTIVE, SILK);
        new CropMutation(Flax, Wheat, AzureBluet)
            .register();

        MutationRegistry.instance.register(Hemp, GREEN, STEM, ADDICTIVE, SILK);
        new CropMutation(Hemp, Flax, Papyrus)
            .register();

        // endregion cropsnh

        // region food
        if (ModUtils.BiomesOPlenty.isModLoaded()) {

            MutationRegistry.instance.register(BoPBerry, RED, BERRY, BUSH, EDIBLE);
            if (ModUtils.Natura.isModLoaded()) {
                new CropMutation(BoPBerry, Poppy, Blackberry)
                    .register();
            }

            MutationRegistry.instance.register(Turnip, PURPLE, ROOT, EDIBLE);
            new CropMutation(Turnip, Potato, BoPBerry)
                .register();

            MutationRegistry.instance.register(WildCarrot, WHITE, ROOT, EDIBLE);
            new CropMutation(WildCarrot, Carrot, BoPBerry)
                .register();
        }

        if (ModUtils.Natura.isModLoaded()) {

            MutationRegistry.instance.register(Barley, GREEN, WHEAT, EDIBLE);
            if (ModUtils.BiomesOPlenty.isModLoaded()) {
                new CropMutation(Barley, Bamboo, Wheat)
                    .register();
            }

            MutationRegistry.instance.register(Blueberry, BLUE, BERRY, BUSH, EDIBLE);
            new CropMutation(Blueberry, AzureBluet, BonsaiOak)
                .register();

            MutationRegistry.instance.register(Maloberry, YELLOW, BERRY, BUSH, EDIBLE);
            new CropMutation(Maloberry, OrangeTulip, Blueberry)
                .register();

            MutationRegistry.instance.register(Raspberry, RED, BERRY, BUSH, EDIBLE);
            new CropMutation(Raspberry, RedTulip, OxeyeDaisy)
                .register();

            MutationRegistry.instance.register(SaguaroCactus, GREEN, CACTUS, DANGEROUS, EDIBLE);
            new CropMutation(SaguaroCactus, Cactus, SandLily)
                .register();

            MutationRegistry.instance.register(Blackberry, BLACK, BERRY, BUSH, EDIBLE);
            new CropMutation(Blackberry, Strawberry, Blueberry)
                .register();
        }


        MutationRegistry.instance.register(Chilly, RED, FIERY, EDIBLE);
        new CropMutation(Chilly, Cactus, Cocoa)
            .register();

        MutationRegistry.instance.register(Coffee, BROWN, BEAN, LEAFY, ADDICTIVE, EDIBLE);
        new CropMutation(Coffee, Cocoa, BonsaiAcacia)
            .register();

        MutationRegistry.instance.register(Cucumber, GREEN, TENDRILLY, EDIBLE);
        new CropMutation(Cucumber, Melon, Carrot)
            .register();

        MutationRegistry.instance.register(Grape, PURPLE, CLIMBABLE, LEAFY, TENDRILLY, EDIBLE);
        if (Mods.Natura.isModLoaded()) {
            new CropMutation(Grape, Blueberry, Blackberry)
                .register();
        }

        MutationRegistry.instance.register(Hops, GREEN, WHEAT, EDIBLE);
        new CropMutation(Hops, Hemp, Dandelion)
            .register();

        MutationRegistry.instance.register(Huckleberry, PURPLE, BUSH, LEAFY, BERRY, EDIBLE);
        if (Mods.Natura.isModLoaded()) {
            new CropMutation(Huckleberry, Blackberry, Grape)
                .register();
        }

        MutationRegistry.instance.register(Lemon, YELLOW, TREE, LEAFY, EDIBLE);
        new CropMutation(Lemon, BonsaiOak, BonsaiAcacia)
            .register();

        // allium is the genus for onions
        MutationRegistry.instance.register(Onion, BROWN, ROOT, EDIBLE);
        new CropMutation(Onion, Allium, Carrot)
            .register();

        MutationRegistry.instance.register(Strawberry, RED, BERRY, BUSH, EDIBLE);
        if (ModUtils.Natura.isModLoaded()) {
            new CropMutation(Strawberry, Wheat, Raspberry)
                .register();
        }

        MutationRegistry.instance.register(SugarBeet, WHITE, ROOT, ADDICTIVE, EDIBLE, POTION_INGREDIENT);
        new CropMutation(SugarBeet, SugarCane, Allium)
            .register();

        MutationRegistry.instance.register(Tea, GREEN, LEAFY, EDIBLE);
        if (ModUtils.Natura.isModLoaded()) {
            new CropMutation(Tea, Blueberry, BonsaiJungle)
                .register();
        }

        MutationRegistry.instance.register(Tomato, RED, TENDRILLY, LEAFY, EDIBLE);
        new CropMutation(Tomato, Onion, RedTulip)
            .register();
        // endregion food

        // region material crops

        MutationRegistry.instance.register(Dayflower, BLUE, FLOWER, ADDICTIVE);
        new CropMutation(Dayflower, AzureBluet, BlueOrchid)
            .register();

        MutationRegistry.instance.register(Malaxia, ORANGE, BUSH, LEAFY, METALLIC, COPPER);
        new CropMutation(Malaxia, OrangeTulip, Pumpkin, StoneLily)
            .register();

        MutationRegistry.instance.register(Plumbshade, PURPLE, BUSH, DENSE, LEAFY, METALLIC, LEAD);
        new CropMutation(Plumbshade, AzureBluet, Malaxia, BlackGraniteLily)
            .register();

        MutationRegistry.instance.register(Silviscus, WHITE, BUSH, LEAFY, METALLIC, SILVER);
        new CropMutation(Silviscus, Cassitine, Malaxia, MarbleLily)
            .register();

        MutationRegistry.instance.register(Cassitine, GRAY, BUSH, LEAFY, METALLIC, SHINY, TIN);
        new CropMutation(Cassitine, Malaxia, StoneLily, MarbleLily)
            .register();

        MutationRegistry.instance.register(StickyCane, GREEN, REED, STICKY);
        new CropMutation(StickyCane, BonsaiJungle, SugarCane)
            .register();

        MutationRegistry.instance.register(PurpleTulip, PURPLE, FLOWER, TULIP);
        new CropMutation(PurpleTulip, RedTulip, BlueOrchid)
            .register();

        MutationRegistry.instance.register(Necrobloom, PURPLE, FLOWER, POISONOUS, DANGEROUS);
        new CropMutation(Necrobloom, Spidernip, PurpleTulip)
            .register();

        MutationRegistry.instance.register(Argentia, WHITE, METALLIC, SHINY, TENDRILLY, SILVER);
        new CropMutation(Argentia, Plumbilia, Tine)
            .register();

        MutationRegistry.instance.register(Auronia, YELLOW, DENSE, METALLIC, TENDRILLY, GOLD);
        new CropMutation(Auronia, Plumbilia, Coppon)
            .register();

        new CropMutation(Bauxia, Galvania, Nickelback)
            .machineOnly()
            .register();

        MutationRegistry.instance.register(BobsYerUncleRanks, GREEN, BERRY, CRYSTALLINE, SHINY, TENDRILLY);
        new CropMutation(BobsYerUncleRanks, Diareed, Olivia)
            .register();

        // i wouldn't recommend chugging canola oil, but it's edible
        MutationRegistry.instance.register(Canola, YELLOW, WHEAT, EDIBLE, OIL);
        new CropMutation(Canola, OxeyeDaisy, Wheat)
            .register();

        MutationRegistry.instance.register(Coppon, ORANGE, BUSH, METALLIC, SHINY, COTTON, COPPER);
        new CropMutation(Coppon, StoneLily, NetherStoneLily)
            .register();

        // coal because diamonds are just carbon
        MutationRegistry.instance.register(Diareed, BLUE, REED, CRYSTALLINE, FIERY, SHINY, COAL);
        new CropMutation(Diareed, Withereed, EvilOre)
            .register();

        MutationRegistry.instance.register(EvilOre, WHITE, NETHER, CRYSTALLINE, EVIL, FIERY);
        new CropMutation(EvilOre, NetherStoneLily, SoulSandLily)
            .register();

        MutationRegistry.instance.register(Ferrofern, GRAY, METALLIC, TENDRILLY, IRON);
        new CropMutation(Ferrofern, StoneLily, BlackGraniteLily)
            .register();

        MutationRegistry.instance.register(Galvania, WHITE, BUSH, METALLIC);
        new CropMutation(Galvania, Tine, Ferrofern)
            .register();

        MutationRegistry.instance.register(Thiosulfine, YELLOW, FLOWER, DANGEROUS, SULFUR);
        new CropMutation(Thiosulfine, Galvania, Plumbilia)
            .register();

        new CropMutation(Garnydinia, Diareed, RedStraw)
            .machineOnly()
            .register();

        MutationRegistry.instance.register(Glowheat, YELLOW, NETHER, WHEAT, EMISSIVE, SHINY, CRYSTALLINE, POTION_INGREDIENT);
        if (ModUtils.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Glowheat, Glowflower, Wheat)
                .register();
        }

        new CropMutation(GodOfThunder, BobsYerUncleRanks, Withereed)
            .machineOnly()
            .register();

        MutationRegistry.instance.register(Indigo, BLUE, FLOWER);
        new CropMutation(Indigo, Dayflower, BlueOrchid)
            .register();

        new CropMutation(Iridine, Scheelinium, Platina)
            .machineOnly()
            .register();

        MutationRegistry.instance.register(Lazulia, BLUE, CRYSTALLINE, SHINY, HEALING);
        new CropMutation(Lazulia, StoneLily, Indigo)
            .register();

        // using dark oak because it's a tree that used to have roots that were used to break the nether ceiling
        // just like how the twilight tree does the same.
        if (ModUtils.TwilightForest.isModLoaded()) {
            MutationRegistry.instance.register(Liveroot, BROWN, TWILIGHT_FOREST, ROOT, TENDRILLY, WOODEN);
            new CropMutation(Liveroot, Torchberry, BonsaiDarkOak)
                .register();
        }

        if (ModUtils.Thaumcraft.isModLoaded()) {
            new CropMutation(MagicalNightshade, PrimordialBerry, ManaBean, Cinderpearl, Shimmerleaf)
                .machineOnly()
                .register();
        }

        new CropMutation(Micadia, Tine, Bauxia)
            .machineOnly()
            .register();

        MutationRegistry.instance.register(MilkWart, WHITE, EDIBLE, COW, HEALING);
        new CropMutation(MilkWart, Corium, Netherwart)
            .register();

        MutationRegistry.instance.register(Nickelback, GRAY, METALLIC, FIERY);
        new CropMutation(Nickelback, Ferrofern, Coppon)
            .register();

        new CropMutation(OilBerry, SoulSandLily, Withereed)
            .machineOnly()
            .register();

        new CropMutation(Olivia, EndStoneLily, EvilOre)
            .machineOnly()
            .register();

        new CropMutation(Osmianth, Platina, Scheelinium)
            .machineOnly()
            .register();

        // leather bound books, birch has a paper-like bark
        MutationRegistry.instance.register(Papyrus, WHITE, STEM);
        new CropMutation(Papyrus, Corium, BonsaiBirch)
            .register();

        // TODO: ADD CHROME CROP AND REPLACE TITANIA WITH NEW CROP IN PLATINA MUTATION
        new CropMutation(Platina, Diareed, Rubyne)
            .machineOnly()
            .register();

        MutationRegistry.instance.register(Plumbilia, PURPLE, METALLIC, LEAD, DENSE, TENDRILLY);
        new CropMutation(Plumbilia, Coppon, Withereed)
            .register();

        MutationRegistry.instance.register(Pyrolusium, RED, METALLIC, BUSH);
        new CropMutation(Pyrolusium, Nickelback, Bauxia)
            .register();

        new CropMutation(Reactoria, Titania, GodOfThunder)
            .machineOnly()
            .register();

        MutationRegistry.instance.register(RedStraw, RED, STONE, WHEAT, EMISSIVE, POTION_INGREDIENT);
        new CropMutation(RedStraw, NetherStoneLily, Wheat)
            .register();

        MutationRegistry.instance.register(Rubyne, RED, CRYSTALLINE, SHINY);
        new CropMutation(Rubyne, RedStraw, Diareed)
            .register();

        MutationRegistry.instance.register(SaltyRoot, GRAY, ROOT, SALTPETER);
        new CropMutation(SaltyRoot, SugarBeet, Canola)
            .register();

        MutationRegistry.instance.register(Sapphirum, BLUE, CRYSTALLINE, SHINY);
        new CropMutation(Sapphirum, EvilOre, Lazulia)
            .register();

        new CropMutation(Scheelinium, Titania, Pyrolusium, EndStoneLily)
            .machineOnly()
            .register();

        new CropMutation(SpaceFlower, EndStoneLily, Titania)
            .machineOnly()
            .register();

        // TODO: ADD PROPPER PLUTONIUM CROP AND REPLACE TROLL PLANT WITH NEW CROP IN STARGATIUM MUTATION
        new CropMutation(Stargatium, Iridine, Trollplant)
            .machineOnly()
            .register();

        // late hv to early hv is where I want star wart to be gated around, might reconsider based on existing metas
        // it can be used in MECs for a pretty hefty amount of eu so gating it up until then is probably a go since
        // 1 nether star dust is like 1.5h of hv power in there.
        new CropMutation(StarWart, Withereed, Titania, GodOfThunder)
            .machineOnly()
            .register();

        if (ModUtils.TwilightForest.isModLoaded()) {
            MutationRegistry.instance.register(Steeleafranks, GREEN, TWILIGHT_FOREST, METALLIC, TENDRILLY, IRON);
            new CropMutation(Steeleafranks, Torchberry, KnightmetalBerry)
                .register();
        }

        MutationRegistry.instance.register(Tine, GRAY, STEM, METALLIC, SHINY, WOODEN, TIN);
        new CropMutation(Tine, StoneLily, BonsaiSpruce)
            .register();

        new CropMutation(Titania, Bauxia, RedStraw)
            .machineOnly()
            .register();

        new CropMutation(Transformium, Trollplant, Bauxia, Titania)
            .machineOnly()
            .register();

        // goldfish is purely here to troll
        new CropMutation(Trollplant, Goldfish, Reactoria, Fertilia)
            // small dust because suffer
            .addRequirement(new MachineBreedingCatalystRequirement().addOreDict("dustSmallPlutonium241", 1))
            // to provide whom ever might have actually used the lathe recipe in the past with some good ol ptsd.
            .addRequirement(new MachineBreedingCatalystRequirement().addOreDict("stickRedAlloy", 64))
            // and the customary bricks
            .addRequirement(new MachineBreedingCatalystRequirement().addItem(new ItemStack(Blocks.brick_block, 5)))
            .machineOnly()
            .register();

        MutationRegistry.instance.register(Withereed, BLACK, REED, FIERY, UNDEAD, COAL, SULFUR);
        new CropMutation(Withereed, BasaltLily, BlackGraniteLily)
            .register();
        // endregion material crops

        // region mobs
        MutationRegistry.instance.register(InkBloom, BLACK, FLOWER, WATERY);
        if (ModUtils.Natura.isModLoaded()) {
            new CropMutation(InkBloom, Blackberry, Goldfish)
                .register();
        }

        MutationRegistry.instance.register(Blazereed, ORANGE, NETHER, REED, FIERY, EVIL, BLAZE, POTION_INGREDIENT, SULFUR);
        if (ModUtils.Thaumcraft.isModLoaded()) {
            new CropMutation(Blazereed, SugarCane, Cinderpearl)
                .register();
        }

        MutationRegistry.instance.register(Corium, BROWN, MUSHROOM, COW);
        new CropMutation(Corium, Wheat, Cocoa)
            .register();

        MutationRegistry.instance.register(Corpseplant, BROWN, BUSH, EVIL, POISONOUS, UNDEAD, ADDICTIVE, EDIBLE);
        if (ModUtils.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Corpseplant, Zomplant, Eyebulb)
                .register();
        }

        // sulfur, charcoal and salt peter
        MutationRegistry.instance.register(Creeperweed, GREEN, EVIL, FIERY, TENDRILLY, SULFUR, SALTPETER, COAL, POTION_INGREDIENT);
        new CropMutation(Creeperweed, NetherStoneLily, Withereed, SaltyRoot)
            .register();

        MutationRegistry.instance.register(EggPlant, WHITE, MUSHROOM, ADDICTIVE, CHICKEN, EDIBLE);
        new CropMutation(EggPlant, Corium, OxeyeDaisy)
            .register();

        MutationRegistry.instance.register(Enderbloom, BLACK, FLOWER, VOID_TOUCHED, ALIEN, MAGICAL);
        new CropMutation(Enderbloom, EndStoneLily, Creeperweed)
            .register();

        // addictive because after a while, you come to love their screechyness
        MutationRegistry.instance.register(Goldfish, ORANGE, NETHER, STEM, DANGEROUS, WATERY, ADDICTIVE, EDIBLE, FISH);
        if (ModUtils.Witchery.isModLoaded()) {
            new CropMutation(Goldfish, Waterlily, WaterArtichoke, Mandrake)
                .register();
        }

        MutationRegistry.instance.register(Meatrose, RED, FLOWER, CHICKEN, COW, EDIBLE, FISH);
        new CropMutation(Meatrose, Goldfish, EggPlant, Corium)
            .register();

        MutationRegistry.instance.register(Slimeplant, GREEN, TREE, EVIL, LEAFY, STICKY, POTION_INGREDIENT);
        new CropMutation(Slimeplant, BlueOrchid, ClayLily)
            .register();

        MutationRegistry.instance.register(Spidernip, WHITE, EVIL, POISONOUS,  ADDICTIVE, EDIBLE, POTION_INGREDIENT, SILK);
        new CropMutation(Spidernip, Flax, Hemp, Zomplant)
            .register();

        MutationRegistry.instance.register(Tearstalks, WHITE, NETHER, STEM, EVIL, HEALING, POTION_INGREDIENT);
        new CropMutation(Tearstalks, Goldfish, SoulSandLily, NetherStoneLily)
            .register();

        MutationRegistry.instance.register(Zomplant, BROWN, EVIL, POISONOUS, UNDEAD, EDIBLE);
        if (ModUtils.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Zomplant, Withereed, Eyebulb)
                .register();
        }
        // endregion mobs

        // region natura
        if (ModUtils.Natura.isModLoaded()) {

            // giving the 4 natura nether berry the potion ingredient tag because they essentially are potions since
            // they all give potion effects

            // give regeneration
            MutationRegistry.instance.register(Blightberry, GREEN, NETHER, BERRY, BUSH, DANGEROUS, POISONOUS, ADDICTIVE, EDIBLE, HEALING, POTION_INGREDIENT);
            new CropMutation(Blightberry, Maloberry, Raspberry)
                .register();

            // gives night vision
            MutationRegistry.instance.register(Duskberry, GRAY, NETHER, BERRY, BUSH, DANGEROUS, POISONOUS, ADDICTIVE, EDIBLE, POTION_INGREDIENT);
            new CropMutation(Duskberry, InkBloom, Blackberry)
                .register();

            // gives jump boost
            MutationRegistry.instance.register(Skyberry, BLUE, NETHER, BERRY, BUSH, DANGEROUS, POISONOUS, ADDICTIVE, EDIBLE, POTION_INGREDIENT);
            new CropMutation(Skyberry, Dayflower, Blueberry)
                .register();

            // gives strength
            MutationRegistry.instance.register(Stingberry, BROWN, NETHER, BERRY, BUSH, DANGEROUS, POISONOUS, ADDICTIVE, EDIBLE, POTION_INGREDIENT);
            new CropMutation(Stingberry, Thornvine, SaguaroCactus)
                .register();

            MutationRegistry.instance.register(Cotton, WHITE, TENDRILLY, COTTON, SILK);
            new CropMutation(Cotton, Flax, Hemp)
                .register();

            MutationRegistry.instance.register(Thornvine, YELLOW, NETHER, CLIMBABLE, DANGEROUS, EMISSIVE, TENDRILLY);
            new CropMutation(Thornvine, Vine, Cactus)
                .register();

            MutationRegistry.instance.register(BlueGlowshroom, BLUE, NETHER, MUSHROOM, EMISSIVE, EDIBLE, POTION_INGREDIENT);
            MutationRegistry.instance.register(GreenGlowshroom, GREEN, NETHER, MUSHROOM, EMISSIVE, EDIBLE, POTION_INGREDIENT);
            MutationRegistry.instance.register(PurpleGlowshroom, PURPLE, NETHER, MUSHROOM, EMISSIVE, EDIBLE, POTION_INGREDIENT);
            if (ModUtils.BiomesOPlenty.isModLoaded()) {

                new CropMutation(BlueGlowshroom, BlueOrchid, Glowflower)
                    .register();

                new CropMutation(GreenGlowshroom, Cactus, Glowflower)
                    .register();

                new CropMutation(PurpleGlowshroom, Indigo, Glowflower)
                    .register();
            }
        }
        // endregion natura

        // region ore berries
        if (ModUtils.TinkerConstruct.isModLoaded()) {

            MutationRegistry.instance.register(AluminiumOreBerry, WHITE, ORE_BERRY, DANGEROUS, METALLIC);
            new CropMutation(AluminiumOreBerry, GoldOreBerry, EssenceOreBerry)
                .register();

            MutationRegistry.instance.register(CopperOreBerry, ORANGE, ORE_BERRY, DANGEROUS, METALLIC, SHINY, COPPER);
            if (ModUtils.BiomesOPlenty.isModLoaded()) {
                new CropMutation(CopperOreBerry, Malaxia, BoPBerry, StoneLily)
                    .register();
            }

            MutationRegistry.instance.register(EssenceOreBerry, GREEN, ORE_BERRY, DANGEROUS, UNDEAD, EDIBLE);
            new CropMutation(EssenceOreBerry, Creeperweed, Zomplant, Spidernip, Tearstalks)
                .register();

            MutationRegistry.instance.register(GoldOreBerry, YELLOW, ORE_BERRY, DENSE, DANGEROUS, METALLIC, GOLD);
            new CropMutation(GoldOreBerry, CopperOreBerry, IronOreBerry, TinOreBerry)
                .register();

            MutationRegistry.instance.register(IronOreBerry, GRAY, ORE_BERRY, DANGEROUS, METALLIC, IRON);
            new CropMutation(IronOreBerry, TinOreBerry, StoneLily)
                .register();

            MutationRegistry.instance.register(TinOreBerry, WHITE, ORE_BERRY, DANGEROUS, METALLIC, SHINY, TIN);
            if (ModUtils.BiomesOPlenty.isModLoaded()) {
                new CropMutation(TinOreBerry, Cassitine, BoPBerry, StoneLily)
                    .register();
            }

            MutationRegistry.instance.register(ArditeOreBerry, ORANGE, ORE_BERRY, DANGEROUS, METALLIC);
            new CropMutation(ArditeOreBerry, NetherStoneLily, Coppon, CopperOreBerry, Malaxia)
                .register();

            MutationRegistry.instance.register(CobaltOreBerry, BLUE, NETHER, ORE_BERRY, DANGEROUS, METALLIC);
            new CropMutation(CobaltOreBerry, NetherStoneLily, ArditeOreBerry, Lazulia, GoldOreBerry)
                .register();
        }

        if (ModUtils.Thaumcraft.isModLoaded()) {
            MutationRegistry.instance.register(ThaumiumOreBerry, PURPLE, ORE_BERRY, DANGEROUS, MAGICAL, METALLIC, VOID_TOUCHED);
            MutationRegistry.instance.register(VoidOreBerry, BLACK, ORE_BERRY, DANGEROUS, MAGICAL, METALLIC, VOID_TOUCHED);
            if (ModUtils.TinkerConstruct.isModLoaded()) {
                // TODO: ADD GREATWOOD AND SILVER WOOD BONSAIS AND ADD DETERMINISTIC RECIPE FOR THAUMIUM ORE BERRY
                new CropMutation(ThaumiumOreBerry, StoneLily, GoldOreBerry)
                    .register();

                new CropMutation(VoidOreBerry, ThaumiumOreBerry, GoldOreBerry)
                    .register();
            }
        }


        if (ModUtils.ThaumicBases.isModLoaded()) {
            MutationRegistry.instance.register(ThauminiteOreBerry, BLUE, ORE_BERRY, DANGEROUS, MAGICAL, METALLIC, VOID_TOUCHED);
            if (ModUtils.TinkerConstruct.isModLoaded()) {
                new CropMutation(ThauminiteOreBerry, ThaumiumOreBerry, ManaBean)
                    .register();
            }
        }

        if (ModUtils.TwilightForest.isModLoaded()) {
            MutationRegistry.instance.register(KnightmetalBerry, GRAY, TWILIGHT_FOREST, ORE_BERRY, DANGEROUS, METALLIC);
            if (ModUtils.TinkerConstruct.isModLoaded()) {
                new CropMutation(KnightmetalBerry, IronOreBerry, Torchberry, BonsaiDarkOak)
                    .register();
            }
        }
        // endregion ore berries

        // region stone lilies
        // gotta check if the block was registered since it's a config.
        Block efrStone = GameRegistry.findBlock(ModUtils.EtFuturumRequiem.ID, "stone");
        if (ModUtils.Botania.isModLoaded() || ModUtils.Chisel.isModLoaded() || efrStone != null) {

            MutationRegistry.instance.register(AndesiteLily, GRAY, STONE);
            new CropMutation(AndesiteLily, StoneLily, ClayLily)
                .register();

            MutationRegistry.instance.register(DioriteLily, WHITE, STONE);
            new CropMutation(DioriteLily, StoneLily, MarbleLily)
                .register();

            MutationRegistry.instance.register(GraniteLily, RED, STONE);
            new CropMutation(GraniteLily, BlackGraniteLily, RedGraniteLily)
                .register();
        }

        if (ModUtils.EtFuturumRequiem.isModLoaded() && ModUtils.NewHorizonsCoreMod.isModLoaded()) {

            MutationRegistry.instance.register(TuffLily, GRAY, DARK, STONE);
            new CropMutation(TuffLily, BlackGraniteLily, AndesiteLily)
                .register();

            MutationRegistry.instance.register(DeepslateLily, BLACK, DARK, DENSE, STONE);
            new CropMutation(DeepslateLily,TuffLily, BlackGraniteLily)
                .register();
        }

        MutationRegistry.instance.register(BasaltLily, BLACK, DARK, STONE);
        new CropMutation(BasaltLily, StoneLily, InkBloom)
            .register();

        MutationRegistry.instance.register(BlackGraniteLily, BLACK, DARK, STONE);
        new CropMutation(BlackGraniteLily, StoneLily, BasaltLily)
            .register();

        MutationRegistry.instance.register(ClayLily, GRAY, WATERY, STONE);
        new CropMutation(ClayLily, StoneLily, Waterlily)
            .register();

        MutationRegistry.instance.register(EndStoneLily, YELLOW, ALIEN, STONE);
        new CropMutation(EndStoneLily, StoneLily, Enderbloom)
            .register();

        MutationRegistry.instance.register(MarbleLily, WHITE, STONE);
        new CropMutation(MarbleLily, StoneLily, OxeyeDaisy)
            .register();

        MutationRegistry.instance.register(NetherStoneLily, RED, NETHER, STONE);
        new CropMutation(NetherStoneLily, StoneLily, Netherwart)
            .register();

        MutationRegistry.instance.register(RedGraniteLily, RED, STONE);
        new CropMutation(RedGraniteLily, StoneLily, Poppy)
            .register();

        MutationRegistry.instance.register(SandLily, YELLOW, STONE);
        new CropMutation(SandLily, StoneLily, Cactus)
            .register();

        MutationRegistry.instance.register(SoulSandLily, BROWN, NETHER, DANGEROUS, STONE, OIL);
        new CropMutation(SoulSandLily, NetherStoneLily, SandLily)
            .register();

        MutationRegistry.instance.register(StoneLily, GRAY, STONE);
        new CropMutation(StoneLily, Vine, Pumpkin)
            .register();
        // endregion stone lilies

        // region thaumcraft
        if (ModUtils.Thaumcraft.isModLoaded()) {

            MutationRegistry.instance.register(Cinderpearl, ORANGE, MAGICAL, BLAZE);
            if (ModUtils.Witchery.isModLoaded()) {
                new CropMutation(Cinderpearl, EmberMoss, GlintWeed)
                    .register();
            }

            MutationRegistry.instance.register(ManaBean, BROWN, BEAN, MAGICAL);
            new CropMutation(ManaBean, Cocoa, Cinderpearl, Shimmerleaf)
                .register();

            // prim perl can't be bred, only planted with an actual prim perl
            // redstraw because shimmerleaf is a quickilver/mercury source and redstone is also that
            // not really silver dropping, but mercury is close enough™
            MutationRegistry.instance.register(Shimmerleaf, GRAY, EMISSIVE, MAGICAL, POISONOUS, SILVER);
            new CropMutation(Shimmerleaf, WhiteTulip, RedStraw)
                .register();
        }
        // endregion thaumcraft

        // region twilight forest
        if (ModUtils.TwilightForest.isModLoaded()) {
            MutationRegistry.instance.register(Torchberry, YELLOW, TWILIGHT_FOREST, BERRY, EMISSIVE);
            if (ModUtils.BiomesOPlenty.isModLoaded()) {
                new CropMutation(Torchberry, Glowflower, BoPBerry)
                    .register();
            }
        }
        // endregion twilight forest

        // region vanilla
        MutationRegistry.instance.register(Cocoa, BROWN, BEAN, TREE, LEAFY, ADDICTIVE, EDIBLE);
        new CropMutation(Cocoa, Melon, BonsaiJungle)
            .register();

        MutationRegistry.instance.register(Melon, GREEN, STEM, EDIBLE, HEALING, POTION_INGREDIENT);
        new CropMutation(Melon, Pumpkin, BonsaiJungle)
            .register();

        MutationRegistry.instance.register(Pumpkin, ORANGE, STEM, EDIBLE);
        new CropMutation(Pumpkin, Carrot, SugarCane)
            .register();

        MutationRegistry.instance.register(RedMushroom, RED, MUSHROOM, EDIBLE);
        new CropMutation(RedMushroom, Pumpkin, Poppy)
            .register();

        MutationRegistry.instance.register(BrownMushroom, BROWN, MUSHROOM, EDIBLE);
        new CropMutation(BrownMushroom, Cocoa, RedMushroom)
            .register();

        MutationRegistry.instance.register(Wheat, YELLOW, WHEAT, EDIBLE);
        MutationRegistry.instance.register(Carrot, ORANGE, ROOT, EDIBLE, POTION_INGREDIENT);
        MutationRegistry.instance.register(Potato, YELLOW, ROOT, EDIBLE);
        MutationRegistry.instance.register(Dandelion, YELLOW, FLOWER);
        MutationRegistry.instance.register(Poppy, RED, FLOWER);

        MutationRegistry.instance.register(BlueOrchid, BLUE, FLOWER);
        new CropMutation(BlueOrchid, Poppy, Waterlily)
            .register();

        MutationRegistry.instance.register(AzureBluet, WHITE, FLOWER);
        new CropMutation(AzureBluet, Poppy, Potato)
            .register();

        MutationRegistry.instance.register(RedTulip, RED, FLOWER, TULIP);
        new CropMutation(RedTulip, Poppy, WhiteTulip)
            .register();

        MutationRegistry.instance.register(OrangeTulip, ORANGE, FLOWER, TULIP);
        new CropMutation(OrangeTulip, RedTulip, Dandelion)
            .register();

        MutationRegistry.instance.register(PinkTulip, RED, FLOWER, TULIP);
        new CropMutation(PinkTulip, RedTulip, WhiteTulip)
            .register();

        MutationRegistry.instance.register(WhiteTulip, WHITE, FLOWER, TULIP);
        new CropMutation(WhiteTulip, PinkTulip, OxeyeDaisy)
            .register();

        // allowed to have 2 types because it's a nod to onions
        MutationRegistry.instance.register(Allium, PURPLE, ROOT, FLOWER);
        new CropMutation(Allium, PinkTulip, PurpleTulip)
            .register();

        MutationRegistry.instance.register(OxeyeDaisy, WHITE, FLOWER);
        new CropMutation(OxeyeDaisy, AzureBluet, Dandelion)
            .register();

        MutationRegistry.instance.register(Cactus, GREEN, CACTUS, DANGEROUS);
        new CropMutation(Cactus, SugarCane, Potato)
            .register();

        MutationRegistry.instance.register(SugarCane, GREEN, REED, EDIBLE);
        new CropMutation(SugarCane, Carrot, Potato)
            .register();

        MutationRegistry.instance.register(Netherwart, RED, NETHER, POTION_INGREDIENT);
        new CropMutation(Netherwart, RedMushroom, BrownMushroom)
            .register();

        MutationRegistry.instance.register(Vine, GREEN, CLIMBABLE, TENDRILLY);
        new CropMutation(Vine, Pumpkin, BlueOrchid)
            .register();


        MutationRegistry.instance.register(Waterlily, GREEN, WATERY);
        new CropMutation(Waterlily, Vine, SugarCane)
            .register();
        // endregion vanilla

        // region witchery
        if (ModUtils.Witchery.isModLoaded()) {

            MutationRegistry.instance.register(Belladonna, PURPLE, FLOWER, POISONOUS, POTION_INGREDIENT);
            new CropMutation(Belladonna, PurpleTulip, Huckleberry)
                .register();

            MutationRegistry.instance.register(EmberMoss, RED, CLIMBABLE, DANGEROUS, FIERY, TENDRILLY, POTION_INGREDIENT);
            if (ModUtils.BiomesOPlenty.isModLoaded()) {
                new CropMutation(EmberMoss, RedTulip, Moss)
                    .register();
            }

            MutationRegistry.instance.register(Garlic, WHITE, ROOT, EDIBLE, POTION_INGREDIENT);
            new CropMutation(Garlic, Onion, Allium)
                .register();

            MutationRegistry.instance.register(GlintWeed, ORANGE, FLOWER, MAGICAL);
            new CropMutation(GlintWeed, Vine, EmberMoss)
                .register();

            MutationRegistry.instance.register(Mandrake, BROWN, FLOWER, DANGEROUS, EVIL, MAGICAL, POTION_INGREDIENT);
            new CropMutation(Mandrake, Carrot, Belladonna)
                .register();

            MutationRegistry.instance.register(Snowbell, WHITE, FLOWER, POISONOUS, POTION_INGREDIENT);
            new CropMutation(Snowbell, Belladonna, GlintWeed)
                .register();

            MutationRegistry.instance.register(SpanishMoss, GREEN, CLIMBABLE, MAGICAL);
            new CropMutation(SpanishMoss, Moss, EmberMoss)
                .register();

            MutationRegistry.instance.register(WaterArtichoke, BLUE, FLOWER, WATERY, EDIBLE, POTION_INGREDIENT);
            new CropMutation(WaterArtichoke, Waterlily, Hops)
                .register();

            MutationRegistry.instance.register(Wolfsbane, PURPLE, FLOWER, POISONOUS, POTION_INGREDIENT);
            new CropMutation(Wolfsbane, WaterArtichoke, Belladonna)
                .register();
        }
        // endregion witchery
        //spotless:on

    }
}
