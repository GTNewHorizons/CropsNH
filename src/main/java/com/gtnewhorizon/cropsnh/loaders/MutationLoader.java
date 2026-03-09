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
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aAddictive;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aAlien;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aAlloy;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aAluminium;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aBad;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aBean;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aBlack;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aBlaze;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aBlue;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aBonsai;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aBouncy;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aBrown;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aBush;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aCactus;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aCarrots;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aChicken;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aClean;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aClimbable;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aCoal;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aCopper;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aCotton;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aCow;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aCrystal;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aDanger;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aDark;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aDense;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aEnder;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aEssence;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aEvil;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aFire;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aFish;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aFlower;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aFood;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aGold;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aGray;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aGreen;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aHealing;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aHeavy;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aIngredient;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aIron;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aLead;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aLeafy;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aLeaves;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aLight;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aMagic;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aMetal;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aMilk;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aMushroom;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aNether;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aOil;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aOrange;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aOreBerry;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aPine;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aPointed;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aPoison;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aPrimordial;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aPurple;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aRadioactive;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aRed;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aReed;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aRoot;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aRose;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aRotten;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aSaltpeter;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aShimmer;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aShiny;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aSilk;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aSilver;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aSlime;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aSoulsand;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aStem;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aSticky;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aStone;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aSulfur;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aTendrilly;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aTin;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aToxic;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aTransform;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aTree;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aTroll;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aTulip;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aUndead;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aVoid;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aWater;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aWheat;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aWhite;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aWither;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aWood;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aYellow;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.aZombie;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.farming.mutation.CropMutation;
import com.gtnewhorizon.cropsnh.farming.registries.MutationRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.breeding.MachineBreedingCatalystRequirement;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

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
        new CropMutation(BonsaiOak, Wheat, BrownMushroom)
            .addToMutationPools(aTree, aBonsai, aLeafy, aFood, aWood)
            .register();
        new CropMutation(BonsaiBirch, BonsaiOak, SugarCane)
            .addToMutationPools(aTree, aBonsai, aLeafy, aWood)
            .register();
        new CropMutation(BonsaiSpruce, BonsaiOak, Pumpkin)
            .addToMutationPools(aTree, aBonsai, aLeafy, aWood)
            .register();
        new CropMutation(BonsaiDarkOak, BonsaiSpruce, BonsaiOak)
            .addToMutationPools(aTree, aBonsai, aLeafy, aDark, aWood)
            .register();
        new CropMutation(BonsaiAcacia, BonsaiOak, Cactus)
            .addToMutationPools(aTree, aBonsai, aLeafy, aWood)
            .register();
        new CropMutation(BonsaiJungle, BonsaiOak, Vine)
            .addToMutationPools(aTree, aBonsai, aLeafy, aWood)
            .register();
        new CropMutation(BonsaiRubber, BonsaiJungle, BonsaiSpruce)
            .addToMutationPools(aTree, aBonsai, aLeafy, aWood)
            .register();
        // not adding it to the bonsai and tree pools on purpose, since it's
        // intended to be a bit harder to get your hands on without exploration.
        new CropMutation(BonsaiSlimy, Slimeplant, BonsaiJungle, BonsaiRubber)
            .addToMutationPools(aSlime, aBouncy, aSticky)
            .register();
        // endregion bonsais

        // region biomes o plenty
        if (ModUtils.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Bamboo, BonsaiJungle, Vine)
                .addToMutationPools(aGreen, aPointed, aStem)
                .register();
            new CropMutation(Eyebulb, NetherStoneLily, RedTulip)
                .addToMutationPools(aNether, aEvil, aBad)
                .register();
            new CropMutation(FloweringVine, OxeyeDaisy, Ivy)
                .addToMutationPools(aGreen, aTendrilly, aFlower, aClimbable)
                .register();
            new CropMutation(Glowflower, NetherStoneLily, Dandelion)
                .addToMutationPools(aNether, aLight, aShiny)
                .register();
            new CropMutation(GlowingCoral, Glowflower, Waterlily)
                .addToMutationPools(aWater, aLight, aShiny)
                .register();
            if (ModUtils.Natura.isModLoaded()) {
                new CropMutation(Glowshroom, BlueGlowshroom, GreenGlowshroom, PurpleGlowshroom)
                    .addToMutationPools(aFood, aMushroom, aIngredient, aNether, aLight)
                    .register();
            }
            new CropMutation(Ivy, Vine, BonsaiSpruce)
                .addToMutationPools(aGreen, aTendrilly, aFlower, aBad, aPoison, aClimbable, aPointed)
                .register();
            new CropMutation(Moss, Ivy, FloweringVine)
                .addToMutationPools(aGreen, aClimbable)
                .register();
        }
        // endregion biomes o plenty

        // region crops nh
        // TODO ADD PHOSPHORUS CROP AND ADD IT TO THE FERTILIA PARENT LIST
        // Corpse plant because it outputs bones, corium because cow manure
        new CropMutation(Fertilia, Corium, Corpseplant)
            .addToMutationPools(aHealing, aFlower)
            .register();
        new CropMutation(Flax, Wheat, AzureBluet)
            .addToMutationPools(aSilk, aTendrilly, aAddictive)
            .register();
        new CropMutation(Hemp, Flax, Papyrus)
            .addToMutationPools(aGreen, aOrange)
            .register();
        // endregion cropsnh

        // region food
        if (ModUtils.BiomesOPlenty.isModLoaded()) {
            new CropMutation(BoPBerry, Poppy, Blackberry)
                .addToMutationPools(aBerry, aFood, aRed, aIngredient)
                .register();
            new CropMutation(Turnip, Potato, BoPBerry)
                .addToMutationPools(aFood, aPurple, aCarrots)
                .register();
            new CropMutation(WildCarrot, Carrot, BoPBerry)
                .addToMutationPools(aFood, aWhite, aCarrots)
                .register();
        }
        if (ModUtils.Natura.isModLoaded()) {
            new CropMutation(Barley, Bamboo, Wheat)
                .addToMutationPools(aGreen, aFood, aWheat)
                .register();
            new CropMutation(Blackberry, Strawberry, Blueberry)
                .addToMutationPools(aBerry, aFood, aBlack)
                .register();
            new CropMutation(Blueberry, AzureBluet, BonsaiOak)
                .addToMutationPools(aBerry, aFood, aBlue)
                .register();
            new CropMutation(Maloberry, OrangeTulip, Blueberry)
                .addToMutationPools(aBerry, aFood, aYellow)
                .register();
            new CropMutation(Raspberry, RedTulip, OxeyeDaisy)
                .addToMutationPools(aBerry, aFood, aRed)
                .register();
            new CropMutation(SaguaroCactus, Cactus, SandLily)
                .addToMutationPools(aGreen, aFood, aCactus, aDanger)
                .register();
        }
        if (ModUtils.PamsHarvestCraft.isModLoaded()) {
            new CropMutation(Strawberry, Wheat, Raspberry)
                .addToMutationPools(aBerry, aFood, aRed)
                .register();
        }
        new CropMutation(Chilly, Cactus, Cocoa)
            .addToMutationPools(aFood, aRed)
            .register();
        new CropMutation(Coffee, Cocoa, BonsaiAcacia)
            .addToMutationPools(aLeaves, aIngredient, aBean)
            .register();
        new CropMutation(Cucumber, Melon, Carrot)
            .addToMutationPools(aFood, aGreen)
            .register();
        if (Mods.Natura.isModLoaded()) {
            new CropMutation(Grape, Blueberry, Blackberry)
                .addToMutationPools(aFood, aPurple)
                .register();
        }
        new CropMutation(Hops, Hemp, Dandelion)
            .addToMutationPools(aGreen, aIngredient, aWheat)
            .register();
        if (Mods.Natura.isModLoaded()) {
            new CropMutation(Huckleberry, Blackberry, Grape)
                .addToMutationPools(aBerry, aFood, aPurple, aLeaves)
                .register();
        }
        new CropMutation(Lemon, BonsaiOak, BonsaiAcacia)
            .addToMutationPools(aFood, aYellow, aWood, aLeafy)
            .register();
        // allium is the genus for onions
        new CropMutation(Onion, Allium, Carrot)
            .addToMutationPools(aFood, aBrown, aRoot)
            .register();
        new CropMutation(SugarBeet, SugarCane, Allium)
            .addToMutationPools(aFood, aWhite, aIngredient, aRoot)
            .register();
        if (ModUtils.Natura.isModLoaded()) {
            new CropMutation(Tea, Blueberry, BonsaiJungle)
                .addToMutationPools(aFood, aGreen, aIngredient)
                .register();
        }
        new CropMutation(Tomato, Onion, RedTulip)
            .addToMutationPools(aFood, aRed)
            .register();
        // endregion food

        new CropMutation(Dayflower, AzureBluet, BlueOrchid)
            .addToMutationPools(aBlue, aFlower, aAddictive)
            .register();
        new CropMutation(Malaxia, OrangeTulip, Pumpkin, StoneLily)
            .addToMutationPools(aOrange, aLeaves, aMetal, aCopper)
            .register();
        new CropMutation(Plumbshade, AzureBluet, Malaxia, BlackGraniteLily)
            .addToMutationPools(aDense, aLeaves, aMetal, aLead)
            .register();
        new CropMutation(Silviscus, Cassitine, Malaxia, MarbleLily)
            .addToMutationPools(aSilver, aLeaves, aMetal, aSilver)
            .register();
        new CropMutation(Cassitine, Malaxia, StoneLily, MarbleLily)
            .addToMutationPools(aShiny, aLeaves, aMetal, aTin)
            .register();
        new CropMutation(StickyCane, BonsaiJungle, SugarCane)
            .addToMutationPools(aReed, aSticky)
            .register();
        new CropMutation(PurpleTulip, RedTulip, BlueOrchid)
            .addToMutationPools(aPurple, aFlower, aTulip)
            .register();
        new CropMutation(Necrobloom, Spidernip, PurpleTulip)
            .addToMutationPools(aPurple, aFlower, aTulip, aPoison, aDanger)
            .register();

        // region material crops
        new CropMutation(Argentia, Plumbilia, Tine)
            .addToMutationPools(aShiny, aMetal, aSilver)
            .register();
        new CropMutation(Auronia, Plumbilia, Coppon)
            .addToMutationPools(aGold, aLeaves, aMetal, aDense)
            .register();
        new CropMutation(Bauxia, Galvania, Nickelback)
            .addToMutationPools(aMetal, aAluminium, aReed, aAluminium)
            .machineOnly()
            .register();
        new CropMutation(BobsYerUncleRanks, Diareed, Olivia)
            .addToMutationPools(aShiny, aTendrilly, aCrystal)
            .register();
        new CropMutation(Canola, OxeyeDaisy, Wheat)
            .addToMutationPools(aFood, aYellow, aOil)
            .register();
        new CropMutation(Coppon, StoneLily, NetherStoneLily)
            .addToMutationPools(aShiny, aMetal, aCotton, aCopper, aBush)
            .register();
        new CropMutation(Diareed, Withereed, EvilOre)
            .addToMutationPools(aFire, aShiny, aReed, aCoal, aCrystal)
            .register();
        new CropMutation(EvilOre, NetherStoneLily, SoulSandLily)
            .addToMutationPools(aCrystal, aFire, aNether)
            .register();
        new CropMutation(Ferrofern, StoneLily, BlackGraniteLily)
            .addToMutationPools(aGray, aLeaves, aMetal, aIron)
            .register();
        new CropMutation(Galvania, Tine, Ferrofern)
            .addToMutationPools(aMetal, aAlloy, aBush)
            .register();
        new CropMutation(Garnydinia, Diareed, RedStraw)
            .addToMutationPools(aShiny, aCrystal, aRed, aYellow, aMetal)
            .machineOnly()
            .register();
        if (ModUtils.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Glowheat, Glowflower, Wheat)
                .addToMutationPools(aLight, aShiny, aCrystal, aLight)
                .register();
        }
        new CropMutation(GodOfThunder, BobsYerUncleRanks, Withereed)
            .addToMutationPools(aRadioactive, aMetal, aCoal)
            .machineOnly()
            .register();
        new CropMutation(Indigo, Dayflower, BlueOrchid)
            .addToMutationPools(aFlower, aBlue, aIngredient)
            .register();
        new CropMutation(Iridine, Scheelinium, Platina)
            .addToMutationPools(aMetal, aFlower, aDense)
            .machineOnly()
            .register();
        new CropMutation(Lazulia, StoneLily, Indigo)
            .addToMutationPools(aShiny, aBad, aCrystal, aBlue)
            .register();
        // using dark oak because it's a tree that used to have roots that were used to break the nether ceiling
        // just like how the twilight tree does the same.
        if (ModUtils.TwilightForest.isModLoaded()) {
            new CropMutation(Liveroot, Torchberry, BonsaiDarkOak)
                .addToMutationPools(aWood, aTendrilly)
                .register();
        }
        if (ModUtils.Thaumcraft.isModLoaded() && ModUtils.ThaumicTinkerer.isModLoaded()) {
            new CropMutation(MagicalNightshade, PrimordialBerry, ManaBean, Cinderpearl, Shimmerleaf)
                .addToMutationPools(aBerry, aMagic, aPrimordial)
                .machineOnly()
                .register();
            MutationRegistry.instance.register(PrimordialBerry, aPrimordial);
        }
        new CropMutation(Micadia, Tine, Bauxia)
            .addToMutationPools(aMetal, aPine, aBush)
            .machineOnly()
            .register();
        new CropMutation(MilkWart, Corium, Netherwart)
            .addToMutationPools(aFood, aMilk, aCow)
            .register();
        new CropMutation(Nickelback, Ferrofern, Coppon)
            .addToMutationPools(aMetal, aFire, aAlloy)
            .register();
        new CropMutation(OilBerry, SoulSandLily, Withereed)
            .addToMutationPools(aFire, aDark, aReed, aRotten, aCoal, aOil)
            .machineOnly()
            .register();
        new CropMutation(Olivia, EndStoneLily, EvilOre)
            .addToMutationPools(aCrystal, aShiny)
            .machineOnly()
            .register();
        new CropMutation(Osmianth, Platina, Scheelinium)
            .addToMutationPools(aMetal, aFlower, aDense)
            .machineOnly()
            .register();
        // leather bound books, birch has a paper-like bark
        new CropMutation(Papyrus, Corium, BonsaiBirch)
            .addToMutationPools(aWhite, aStem)
            .register();
        // TODO: ADD CHROME CROP AND REPLACE TITANIA WITH NEW CROP IN PLATINA MUTATION
        new CropMutation(Platina, Diareed, Titania)
            .addToMutationPools(aMetal, aShiny, aReed)
            .machineOnly()
            .register();
        new CropMutation(Plumbilia, Coppon, Withereed)
            .addToMutationPools(aHeavy, aMetal, aLead, aReed, aDense)
            .register();
        new CropMutation(Pyrolusium, Nickelback, Bauxia)
            .addToMutationPools(aMetal, aClean, aBush)
            .register();
        new CropMutation(Reactoria, Titania, GodOfThunder)
            .addToMutationPools(aRadioactive, aMetal, aDanger, aDense)
            .machineOnly()
            .register();
        new CropMutation(RedStraw, NetherStoneLily, Wheat)
            .addToMutationPools(aRed, aWheat)
            .register();
        new CropMutation(SaltyRoot, SugarBeet, Canola)
            .addToMutationPools(aGray, aRoot, aSaltpeter)
            .register();
        new CropMutation(Sapphirum, EvilOre, Lazulia)
            .addToMutationPools(aCrystal, aShiny, aMetal)
            .register();
        new CropMutation(Scheelinium, Titania, Pyrolusium, EndStoneLily)
            .addToMutationPools(aMetal, aBush, aDense)
            .machineOnly()
            .register();
        new CropMutation(SpaceFlower, EndStoneLily, Titania)
            .addToMutationPools(aAlien, aRadioactive, aTransform)
            .machineOnly()
            .register();
        // TODO: ADD PROPPER PLUTONIUM CROP AND REPLACE TROLL PLANT WITH NEW CROP IN STARGATIUM MUTATION
        new CropMutation(Stargatium, Iridine, Trollplant)
            .addToMutationPools(aMetal, aHeavy, aAlien)
            .machineOnly()
            .register();
        // late hv to early hv is where I want star wart to be gated around, might reconsider based on existing metas
        // it can be used in MECs for a pretty hefty amount of eu so gating it up until then is probably a go since
        // 1 nether star dust is like 1.5h of hv power in there.
        new CropMutation(StarWart, Withereed, Titania, GodOfThunder)
            .addToMutationPools(aWither, aNether, aUndead)
            .machineOnly()
            .register();
        if (ModUtils.TwilightForest.isModLoaded()) {
            new CropMutation(Steeleafranks, Torchberry, KnightmetalBerry)
                .addToMutationPools(aMetal, aTendrilly, aIron)
                .register();
        }
        new CropMutation(Tine, StoneLily, BonsaiSpruce)
            .addToMutationPools(aShiny, aMetal, aPine, aTin, aBush)
            .register();
        new CropMutation(Titania, Bauxia, RedStraw)
            .addToMutationPools(aMetal, aHeavy, aReed)
            .machineOnly()
            .register();
        new CropMutation(Transformium, Trollplant, Bauxia, Titania)
            .addToMutationPools(aTransform, aCoal, aReed)
            .machineOnly()
            .register();
        // goldfish is purely here to troll
        new CropMutation(Trollplant, Goldfish, Reactoria, Fertilia)
            .addToMutationPools(aTroll, aBad)
            // small dust because suffer
            .addRequirement(new MachineBreedingCatalystRequirement().addOreDict("dustSmallPlutonium241", 1))
            // to provide whom ever might have actually used the lathe recipe in the past with some good ol ptsd.
            .addRequirement(new MachineBreedingCatalystRequirement().addOreDict("stickRedAlloy", 64))
            // and the customary bricks
            .addRequirement(new MachineBreedingCatalystRequirement().addItem(new ItemStack(Blocks.brick_block, 5)))
            .machineOnly()
            .register();
        new CropMutation(Withereed, BasaltLily, BlackGraniteLily)
            .addToMutationPools(aFire, aUndead, aReed, aCoal, aRotten, aWither)
            .register();
        // endregion material crops

        // region mobs
        if (ModUtils.Natura.isModLoaded()) {
            new CropMutation(InkBloom, Blackberry, Goldfish)
                .addToMutationPools(aBlack, aFlower, aRose)
                .register();
        }
        if (ModUtils.Thaumcraft.isModLoaded()) {
            new CropMutation(Blazereed, SugarCane, Cinderpearl)
                .addToMutationPools(aFire, aBlaze, aReed, aSulfur, aNether)
                .register();
        }
        new CropMutation(Corium, Wheat, Cocoa)
            .addToMutationPools(aCow, aSilk, aTendrilly, aMilk)
            .register();
        if (ModUtils.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Corpseplant, Zomplant, Eyebulb)
                .addToMutationPools(aToxic, aUndead, aTendrilly, aFood, aRotten, aZombie)
                .register();
        }
        // sulfur, charcoal and salt peter
        new CropMutation(Creeperweed, NetherStoneLily, Withereed, SaltyRoot)
            .addToMutationPools(aTendrilly, aFire, aSulfur, aSaltpeter, aCoal)
            .register();
        new CropMutation(EggPlant, Corium, OxeyeDaisy)
            .addToMutationPools(aChicken, aFood, aFlower, aAddictive)
            .register();
        new CropMutation(Enderbloom, EndStoneLily, Creeperweed)
            .addToMutationPools(aEnder, aFlower, aShiny)
            .register();
        if (ModUtils.Witchery.isModLoaded()) {
            new CropMutation(Goldfish, Waterlily, WaterArtichoke, Mandrake)
                .addToMutationPools(aNether, aFish, aFood, aBad, aWater)
                .register();
        }
        new CropMutation(Meatrose, Goldfish, EggPlant, Corium)
            .addToMutationPools(aFood, aFlower, aCow, aFish, aChicken)
            .register();
        new CropMutation(Slimeplant, BlueOrchid, ClayLily)
            .addToMutationPools(aSlime, aBouncy, aSticky, aBush)
            .register();
        new CropMutation(Spidernip, Flax, Hemp, Zomplant)
            .addToMutationPools(aToxic, aSilk, aFlower, aIngredient, aAddictive)
            .register();
        new CropMutation(Tearstalks, Goldfish, SoulSandLily, NetherStoneLily)
            .addToMutationPools(aHealing, aNether, aIngredient, aReed)
            .register();
        if (ModUtils.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Zomplant, Withereed, Eyebulb)
                .addToMutationPools(aZombie, aRotten, aUndead)
                .register();
        }
        // endregion mobs

        // region natura
        if (ModUtils.Natura.isModLoaded()) {
            new CropMutation(Blightberry, Maloberry, Raspberry)
                .addToMutationPools(aBerry, aToxic, aBad, aGreen, aNether, aAddictive)
                .register();
            new CropMutation(Duskberry, InkBloom, Blackberry)
                .addToMutationPools(aBerry, aToxic, aBad, aGray, aNether, aAddictive)
                .register();
            new CropMutation(Skyberry, Dayflower, Blueberry)
                .addToMutationPools(aBerry, aToxic, aBad, aBlue, aNether, aAddictive)
                .register();
            new CropMutation(Stingberry, Thornvine, SaguaroCactus)
                .addToMutationPools(aBerry, aToxic, aBad, aGreen, aNether, aAddictive, aDanger)
                .register();
            new CropMutation(Cotton, Flax, Hemp)
                .addToMutationPools(aWhite, aCotton)
                .register();
            new CropMutation(Thornvine, Vine, Cactus)
                .addToMutationPools(aNether, aClimbable, aBad, aDanger)
                .register();
            if (ModUtils.BiomesOPlenty.isModLoaded()) {
                new CropMutation(BlueGlowshroom, BlueOrchid, Glowflower)
                    .addToMutationPools(aFood, aMushroom, aIngredient, aNether, aLight)
                    .register();
                new CropMutation(GreenGlowshroom, Cactus, Glowflower)
                    .addToMutationPools(aFood, aMushroom, aIngredient, aNether, aLight)
                    .register();
                new CropMutation(PurpleGlowshroom, Indigo, Glowflower)
                    .addToMutationPools(aFood, aMushroom, aIngredient, aNether, aLight)
                    .register();
            }
        }
        // endregion natura

        // region ore berries
        if (ModUtils.TinkerConstruct.isModLoaded()) {
            new CropMutation(AluminiumOreBerry, GoldOreBerry, EssenceOreBerry)
                .addToMutationPools(aOreBerry, aAluminium, aMetal, aAluminium, aDanger)
                .register();
            new CropMutation(ArditeOreBerry, NetherStoneLily, Coppon, CopperOreBerry, Malaxia)
                .addToMutationPools(aOreBerry, aMetal, aOrange, aDanger)
                .register();
            new CropMutation(CobaltOreBerry, NetherStoneLily, ArditeOreBerry, Lazulia, GoldOreBerry)
                .addToMutationPools(aOreBerry, aMetal, aBlue, aDanger)
                .register();
            if (ModUtils.BiomesOPlenty.isModLoaded()) {
                new CropMutation(CopperOreBerry, Malaxia, BoPBerry, StoneLily)
                    .addToMutationPools(aOreBerry, aCopper, aMetal, aShiny, aDanger)
                    .register();
            }
            new CropMutation(EssenceOreBerry, Creeperweed, Zomplant, Spidernip, Tearstalks)
                .addToMutationPools(aOreBerry, aEssence, aUndead, aDanger)
                .register();
            new CropMutation(GoldOreBerry, CopperOreBerry, IronOreBerry, TinOreBerry)
                .addToMutationPools(aOreBerry, aGold, aMetal, aDanger)
                .register();
            new CropMutation(IronOreBerry, TinOreBerry, StoneLily)
                .addToMutationPools(aOreBerry, aGray, aMetal, aDanger, aIron)
                .register();
            if (ModUtils.BiomesOPlenty.isModLoaded()) {
                new CropMutation(TinOreBerry, Cassitine, BoPBerry, StoneLily)
                    .addToMutationPools(aOreBerry, aTin, aMetal, aShiny, aDanger)
                    .register();
            }
            if (ModUtils.TwilightForest.isModLoaded()) {
                new CropMutation(KnightmetalBerry, IronOreBerry, Torchberry, BonsaiDarkOak)
                    .addToMutationPools(aOreBerry, aMetal, aDanger)
                    .register();
            }
            if (ModUtils.Thaumcraft.isModLoaded()) {
                // TODO: ADD GREATWOOD AND SILVER WOOD BONSAIS AND ADD DETERMINISTIC RECIPE FOR THAUMIUM ORE BERRY
                new CropMutation(ThaumiumOreBerry, StoneLily, GoldOreBerry)
                    .addToMutationPools(aOreBerry, aMagic, aMetal, aVoid, aDanger)
                    .register();
                new CropMutation(VoidOreBerry, ThaumiumOreBerry, GoldOreBerry)
                    .addToMutationPools(aOreBerry, aMagic, aMetal, aVoid, aDanger)
                    .register();
                if (ModUtils.ThaumicBases.isModLoaded()) {
                    new CropMutation(ThauminiteOreBerry, ThaumiumOreBerry, ManaBean)
                        .addToMutationPools(aOreBerry, aMagic, aMetal, aVoid, aDanger)
                        .register();
                }
            }
        }
        // endregion ore berries

        // region stone lilies
        if (ModUtils.Botania.isModLoaded()) {
            new CropMutation(AndesiteLily, StoneLily, ClayLily)
                .addToMutationPools(aGray, aStone, aMetal)
                .register();
            new CropMutation(DioriteLily, StoneLily, MarbleLily)
                .addToMutationPools(aWhite, aStone, aShiny)
                .register();
            new CropMutation(GraniteLily, BlackGraniteLily, RedGraniteLily)
                .addToMutationPools(aRed, aStone, aFire)
                .register();
        }
        if (ModUtils.EtFuturumRequiem.isModLoaded()) {
            new CropMutation(TuffLily, BlackGraniteLily, StoneLily)
                .addToMutationPools(aGray, aStone, aDark)
                .register();
            new CropMutation(DeepslateLily,TuffLily, BlackGraniteLily)
                .addToMutationPools(aBlack, aStone, aDark)
                .register();
        }
        new CropMutation(BasaltLily, StoneLily, InkBloom)
            .addToMutationPools(aBlack, aStone, aDark)
            .register();
        new CropMutation(BlackGraniteLily, StoneLily, BasaltLily)
            .addToMutationPools(aBlack, aStone, aDark)
            .register();
        new CropMutation(ClayLily, StoneLily, Waterlily)
            .addToMutationPools(aGray, aStone, aWater)
            .register();
        new CropMutation(EndStoneLily, StoneLily, Enderbloom)
            .addToMutationPools(aYellow, aStone, aAlien, aEnder)
            .register();
        new CropMutation(MarbleLily, StoneLily, OxeyeDaisy)
            .addToMutationPools(aWhite, aStone, aShiny)
            .register();
        new CropMutation(NetherStoneLily, StoneLily, Netherwart)
            .addToMutationPools(aNether, aStone, aEvil)
            .register();
        new CropMutation(RedGraniteLily, StoneLily, Poppy)
            .addToMutationPools(aRed, aStone, aFire)
            .register();
        new CropMutation(SandLily, StoneLily, Cactus)
            .addToMutationPools(aYellow, aStone, aCactus)
            .register();
        new CropMutation(SoulSandLily, NetherStoneLily, SandLily)
            .addToMutationPools(aNether, aStone, aEvil, aSoulsand)
            .register();
        new CropMutation(StoneLily, Vine, Pumpkin)
            .addToMutationPools(aGray, aStone, aMetal)
            .register();
        // endregion stone lilies

        // region thaumcraft
        if (ModUtils.Thaumcraft.isModLoaded()) {
            if (ModUtils.Witchery.isModLoaded()) {
                new CropMutation(Cinderpearl, EmberMoss, GlintWeed)
                    .addToMutationPools(aMagic, aBlaze, aNether)
                    .register();
            }
            new CropMutation(ManaBean, Cocoa, Cinderpearl, Shimmerleaf)
                .addToMutationPools(aBerry, aBean, aMagic)
                .register();
            // prim perl can't be bred, only planted with an actual prim perl
            // redstraw because shimmerleaf is a quickilver/mercury source and redstone is also that
            new CropMutation(Shimmerleaf, WhiteTulip, RedStraw)
                .addToMutationPools(aMagic, aSilver, aToxic, aShimmer)
                .register();
        }
        // endregion thaumcraft

        // region twilight forest
        if (ModUtils.TwilightForest.isModLoaded() && ModUtils.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Torchberry, Glowflower, BoPBerry)
                .addToMutationPools(aBerry, aLight, aShimmer)
                .register();
        }
        // endregion thaumcraft

        // region vanilla
        new CropMutation(Cocoa, Melon, BonsaiJungle)
            .addToMutationPools(aBrown, aFood, aStem)
            .register();
        new CropMutation(Melon, Pumpkin, BonsaiJungle)
            .addToMutationPools(aGreen, aFood, aStem)
            .register();
        new CropMutation(Pumpkin, Carrot, SugarCane)
            .addToMutationPools(aOrange, aStem)
            .register();
        new CropMutation(RedMushroom, Pumpkin, Poppy)
            .addToMutationPools(aRed, aFood, aMushroom)
            .register();
        new CropMutation(BrownMushroom, Cocoa, RedMushroom)
            .addToMutationPools(aBrown, aFood, aMushroom)
            .register();
        MutationRegistry.instance.register(Wheat, aYellow, aFood, aWheat);
        MutationRegistry.instance.register(Carrot, aOrange, aFood, aCarrots, aRoot);
        MutationRegistry.instance.register(Potato, aYellow, aFood, aRoot);
        MutationRegistry.instance.register(Dandelion, aFlower, aYellow);
        MutationRegistry.instance.register(Poppy, aRed, aFlower, aRose);
        new CropMutation(BlueOrchid, Poppy, Waterlily)
            .addToMutationPools(aFlower, aBlue)
            .register();
        new CropMutation(AzureBluet, Poppy, Potato)
            .addToMutationPools(aFlower, aWhite)
            .register();
        new CropMutation(RedTulip, Poppy, WhiteTulip)
            .addToMutationPools(aFlower, aRed, aTulip)
            .register();
        new CropMutation(OrangeTulip, RedTulip, Dandelion)
            .addToMutationPools(aFlower, aOrange, aTulip)
            .register();
        new CropMutation(PinkTulip, RedTulip, WhiteTulip)
            .addToMutationPools(aFlower, aRed, aTulip)
            .register();
        new CropMutation(WhiteTulip, PinkTulip, OxeyeDaisy)
            .addToMutationPools(aFlower, aWhite, aTulip)
            .register();
        new CropMutation(Allium, PinkTulip, PurpleTulip)
            .addToMutationPools(aFlower, aPurple, aRoot)
            .register();
        new CropMutation(OxeyeDaisy, AzureBluet, Dandelion)
            .addToMutationPools(aFlower, aWhite)
            .register();
        new CropMutation(Cactus, SugarCane, Potato)
            .addToMutationPools(aGreen, aCactus, aDanger)
            .register();
        new CropMutation(SugarCane, Carrot, Potato)
            .addToMutationPools(aReed)
            .register();
        new CropMutation(Netherwart, RedMushroom, BrownMushroom)
            .addToMutationPools(aRed, aNether, aIngredient, aSoulsand)
            .register();
        new CropMutation(Vine, Pumpkin, BlueOrchid)
            .addToMutationPools(aGreen, aTendrilly, aClimbable)
            .register();
        new CropMutation(Waterlily, Vine, SugarCane)
            .addToMutationPools(aBlue, aWater, aGreen)
            .register();
        // endregion vanilla

        // region witchery
        if (ModUtils.Witchery.isModLoaded()) {
            new CropMutation(Belladonna, PurpleTulip, PinkTulip)
                .addToMutationPools(aPurple, aFlower, aToxic, aIngredient)
                .register();
            if (ModUtils.BiomesOPlenty.isModLoaded()) {
                new CropMutation(EmberMoss, RedTulip, Moss)
                    .addToMutationPools(aFire, aIngredient, aBad, aClimbable)
                    .register();
            }
            new CropMutation(Garlic, Onion, Allium)
                .addToMutationPools(aFood, aIngredient, aHealing, aRoot)
                .register();
            new CropMutation(GlintWeed, Vine, EmberMoss)
                .addToMutationPools(aOrange, aFlower, aMagic)
                .register();
            new CropMutation(Mandrake, Carrot, Belladonna)
                .addToMutationPools(aFlower, aMagic, aBad, aToxic, aIngredient)
                .register();
            new CropMutation(Snowbell, Belladonna, GlintWeed)
                .addToMutationPools(aWhite, aFlower, aToxic, aIngredient)
                .register();
            new CropMutation(SpanishMoss, Moss, EmberMoss)
                .addToMutationPools(aGreen, aClimbable, aMagic)
                .register();
            new CropMutation(WaterArtichoke, Waterlily, Hops)
                .addToMutationPools(aFlower, aWater, aBlue, aIngredient)
                .register();
            new CropMutation(Wolfsbane, WaterArtichoke, Belladonna)
                .addToMutationPools(aFlower, aToxic, aPurple, aIngredient)
                .register();
        }
        // endregion witchery
        //spotless:on

    }
}
