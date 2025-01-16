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
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Blackthorn;
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
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Cotton;
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
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.Garnydinia;
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
import static com.gtnewhorizon.cropsnh.init.CropsNHCrops.TerraWart;
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
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aAddictive;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aAether;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aAlien;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aAlloy;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aAluminium;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aAluminum;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aArdite;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aBad;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aBean;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aBeans;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aBeryllium;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aBlack;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aBlaze;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aBlue;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aBonsai;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aBouncy;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aBrown;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aBush;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aCactus;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aCarrots;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aChicken;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aClean;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aClimbable;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aCoal;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aCobalt;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aColorful;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aConsumable;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aCopper;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aCotton;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aCow;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aCreeper;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aCrystal;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aDanger;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aDark;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aDecoration;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aDense;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aDiamond;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aEdgy;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aEgg;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aEmerald;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aEnder;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aEssence;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aEvil;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aExplosive;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aFeather;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aFire;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aFish;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aFlower;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aFood;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aGhast;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aGlow;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aGold;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aGray;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aGreen;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aGrowth;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aHard;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aHealing;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aHeavy;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aHydrophobic;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aIce;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aIngredient;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aIridium;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aIron;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aKnightly;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aLapis;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aLead;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aLeafy;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aLeaves;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aLight;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aMagic;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aManganese;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aMetal;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aMica;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aMilk;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aMushroom;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aNaquadah;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aNether;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aNetherstar;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aOil;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aOlivine;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aOrange;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aOreBerry;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aOsmium;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aPaper;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aPig;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aPine;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aPlatinum;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aPointed;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aPoison;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aPotato;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aPrimordial;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aProcessing;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aPurple;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aRadiation;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aRadioactive;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aRed;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aRedstone;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aReed;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aResin;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aRoot;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aRose;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aRotten;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aSalt;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aSaltpeter;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aSapphire;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aScrap;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aShimmer;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aShiny;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aSilk;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aSilver;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aSlime;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aSnow;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aSoil;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aSoulsand;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aSour;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aSpace;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aSpicy;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aSpider;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aStem;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aSticky;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aStone;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aSulfur;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aTendrilly;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aThauminite;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aThaumium;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aThorium;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aTin;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aTitanium;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aToxic;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aTransform;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aTree;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aTroll;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aTulip;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aTungsten;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aUndead;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aUnique;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aUranium;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aVoid;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aWater;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aWheat;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aWhite;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aWither;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aWood;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aYellow;
import static com.gtnewhorizon.cropsnh.init.CropsNHMutationPools.aZombie;

import com.gtnewhorizon.cropsnh.farming.mutation.CropMutation;
import com.gtnewhorizon.cropsnh.farming.registries.MutationRegistry;

import gregtech.api.enums.Mods;

public class MutationLoader {

    public static void postInit() {

        // most of this is either:
        // - mild taxonomic relations according to wikipedia
        // - based on bee progression
        // - balance related tier gating
        // - mildly random guesses
        //
        // if you have some good botanical knowledge
        // feel free to update this to be more accurate

        // turns off spotless because otherwise the mutations pools get cutoff offloaded to a new line.
        // spotless:off
        // region bonsais
        new CropMutation(BonsaiOak, Wheat, BrownMushroom)
            .addToMutationPools(aTree, aBonsai, aLeafy, aFood)
            .register();
        new CropMutation(BonsaiBirch, BonsaiOak, SugarCane)
            .addToMutationPools(aTree, aBonsai, aLeafy)
            .register();
        new CropMutation(BonsaiSpruce, BonsaiOak, Pumpkin)
            .addToMutationPools(aTree, aBonsai, aLeafy)
            .register();
        new CropMutation(BonsaiDarkOak, BonsaiSpruce, BonsaiOak)
            .addToMutationPools(aTree, aBonsai, aLeafy, aDark)
            .register();
        new CropMutation(BonsaiAcacia, BonsaiOak, Cactus)
            .addToMutationPools(aTree, aBonsai, aLeafy)
            .register();
        new CropMutation(BonsaiJungle, BonsaiOak, Vine)
            .addToMutationPools(aTree, aBonsai, aLeafy)
            .register();
        new CropMutation(BonsaiRubber, BonsaiJungle, BonsaiSpruce)
            .addToMutationPools(aTree, aBonsai, aLeafy)
            .register();
        // endregion bonsais

        // region biomes o plenty
        if (Mods.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Bamboo, BonsaiJungle, Vine)
                .addToMutationPools(aGreen, aPointed, aEdgy)
                .register();
            new CropMutation(Eyebulb, NetherStoneLily, RedTulip)
                .addToMutationPools(aNether, aEvil, aBad)
                .register();
            new CropMutation(FloweringVine, OxeyeDaisy, Ivy)
                .addToMutationPools(aGreen, aTendrilly, aFlower)
                .register();
            new CropMutation(Glowflower, NetherStoneLily, Dandelion)
                .addToMutationPools(aNether, aLight, aShiny)
                .register();
            new CropMutation(GlowingCoral, Glowflower, Waterlily)
                .addToMutationPools(aWater, aLight, aShiny)
                .register();
            if (Mods.Natura.isModLoaded()) {
                new CropMutation(Glowshroom, BlueGlowshroom, GreenGlowshroom, PurpleGlowshroom)
                    .addToMutationPools(aFood, aMushroom, aIngredient, aNether)
                    .register();
            }
            new CropMutation(Ivy, Vine, BonsaiSpruce)
                .addToMutationPools(aGreen, aTendrilly, aFlower, aBad, aPoison)
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
            .addToMutationPools(aGrowth, aHealing, aFlower)
            .register();
        new CropMutation(Flax, Wheat, AzureBluet)
            .addToMutationPools(aSilk, aTendrilly, aAddictive)
            .register();
        new CropMutation(Hemp, Flax, Papyrus)
            .addToMutationPools(aGreen, aSoil, aOrange)
            .register();
        // endregion cropsnh

        // region food
        if (Mods.BiomesOPlenty.isModLoaded()) {
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
        if (Mods.Natura.isModLoaded()) {
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
                .addToMutationPools(aGreen, aFood, aCactus)
                .register();
        }
        if (Mods.PamsHarvestCraft.isModLoaded()) {
            new CropMutation(Strawberry, Wheat, Raspberry)
                .addToMutationPools(aBerry, aFood, aRed)
                .register();
        }
        new CropMutation(Chilly, Cactus, Cocoa)
            .addToMutationPools(aFood, aRed, aSpicy)
            .register();
        new CropMutation(Coffee, Cocoa, BonsaiAcacia)
            .addToMutationPools(aLeaves, aIngredient, aBeans)
            .register();
        new CropMutation(Cucumber, Melon, Carrot)
            .addToMutationPools(aFood, aGreen)
            .register();
        new CropMutation(Grape, Blueberry, Blackberry)
            .addToMutationPools(aFood, aPurple)
            .register();
        new CropMutation(Hops, Hemp, Dandelion)
            .addToMutationPools(aGreen, aIngredient, aWheat)
            .register();
        new CropMutation(Huckleberry, Blackberry, Grape)
            .addToMutationPools(aBerry, aFood, aPurple, aLeaves)
            .register();
        new CropMutation(Lemon, BonsaiOak, BonsaiAcacia)
            .addToMutationPools(aFood, aYellow, aWood, aLeafy, aSour)
            .register();
        // allium is the genus for onions
        new CropMutation(Onion, Allium, Carrot)
            .addToMutationPools(aFood, aBrown)
            .register();
        new CropMutation(SugarBeet, SugarCane, Allium)
            .addToMutationPools(aFood, aWhite, aIngredient)
            .register();
        new CropMutation(Tea, Blueberry, BonsaiJungle)
            .addToMutationPools(aFood, aGreen, aIngredient)
            .register();
        new CropMutation(Tomato, Onion, RedTulip)
            .addToMutationPools(aFood, aRed)
            .register();
        // endregion food

        // region ic2
        new CropMutation(Cyazint, AzureBluet, BlueOrchid)
            .addToMutationPools(aBlue, aFlower)
            .register();
        new CropMutation(Cyprium, OrangeTulip, Pumpkin, StoneLily)
            .addToMutationPools(aOrange, aLeaves, aMetal)
            .register();
        new CropMutation(Plumbiscus, AzureBluet, Cyprium, BlackGraniteLily)
            .addToMutationPools(aDense, aLeaves, aMetal)
            .register();
        new CropMutation(Shining, Stagnium, Cyprium, MarbleLily)
            .addToMutationPools(aSilver, aLeaves, aMetal)
            .register();
        new CropMutation(Stagnium, Cyprium, StoneLily, StoneLily)
            .addToMutationPools(aShiny, aLeaves, aMetal)
            .register();
        new CropMutation(StickReed, BonsaiJungle, SugarCane)
            .addToMutationPools(aReed, aResin)
            .register();
        // terrawart has its own hidden mechanic that requires you to right-click a netherwart with snow until it turns
        // into terrawart, the mutation pool only exists as a fallback for challenge runs that may not have access to
        // snow early on.
        MutationRegistry.instance.register(TerraWart, aBlue, aAether, aConsumable, aSnow);
        new CropMutation(PurpleTulip, RedTulip, BlueOrchid)
            .addToMutationPools(aPurple, aFlower, aTulip)
            .register();
        new CropMutation(Venomilia, Spidernip, PurpleTulip)
            .addToMutationPools(aPurple, aFlower, aTulip, aPoison)
            .register();
        // endregion ic2

        // region material crops
        new CropMutation(Argentia, Plumbilia, Tine)
            .addToMutationPools(aShiny, aMetal, aSilver, aReed)
            .register();
        new CropMutation(Aurelia, Plumbilia, Coppon)
            .addToMutationPools(aGold, aLeaves, aMetal)
            .register();
        new CropMutation(Bauxia, Galvania, Nickelback)
            .machineOnly()
            .addToMutationPools(aMetal, aAluminium, aReed, aAluminium)
            .register();
        new CropMutation(BobsYerUncleRanks, Diareed, Olivia)
            .addToMutationPools(aShiny, aTendrilly, aEmerald, aBeryllium, aCrystal)
            .register();
        new CropMutation(Canola, OxeyeDaisy, Wheat)
            .addToMutationPools(aFood, aYellow, aOil)
            .register();
        new CropMutation(Coppon, StoneLily, NetherStoneLily)
            .addToMutationPools(aShiny, aMetal, aCotton, aCopper, aBush)
            .register();
        new CropMutation(Diareed, Withereed, EvilOre)
            .addToMutationPools(aFire, aShiny, aReed, aCoal, aDiamond, aCrystal)
            .register();
        new CropMutation(EvilOre, NetherStoneLily, SoulSandLily)
            .addToMutationPools(aCrystal, aFire, aNether)
            .register();
        new CropMutation(Ferru, StoneLily, BlackGraniteLily)
            .addToMutationPools(aGray, aLeaves, aMetal)
            .register();
        new CropMutation(Galvania, Tine, Ferru)
            .addToMutationPools(aMetal, aAlloy, aBush)
            .register();
        new CropMutation(Garnydinia, Diareed, Redwheat)
            .machineOnly()
            .addToMutationPools(aShiny, aCrystal, aRed, aYellow, aMetal)
            .register();
        if (Mods.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Glowheat, Glowflower, Wheat)
                .addToMutationPools(aLight, aShiny, aCrystal)
                .register();
        }
        new CropMutation(GodOfThunder, BobsYerUncleRanks, Withereed)
            .machineOnly()
            .addToMutationPools(aRadioactive, aMetal, aCoal, aThorium)
            .register();
        new CropMutation(Indigo, Cyazint, BlueOrchid)
            .addToMutationPools(aFlower, aBlue, aIngredient)
            .register();
        new CropMutation(Iridine, Scheelinium, Platina)
            .addToMutationPools(aMetal, aIridium, aFlower)
            .machineOnly()
            .register();
        new CropMutation(Lazulia, StoneLily, Indigo)
            .addToMutationPools(aShiny, aBad, aCrystal, aLapis)
            .register();
        // using dark oak because it's a tree that used to have roots that were used to break the nether ceiling
        if (Mods.TwilightForest.isModLoaded()) {
            new CropMutation(Liveroot, Torchberry, BonsaiDarkOak)
                .addToMutationPools(aWood, aTendrilly)
                .register();
        }
        if (Mods.Thaumcraft.isModLoaded() && Mods.ThaumicTinkerer.isModLoaded()) {
            new CropMutation(MagicalNightshade, PrimordialBerry, ManaBean, Cinderpearl, Shimmerleaf)
                .addToMutationPools(aBerry, aPrimordial, aMagic, aUnique)
                .machineOnly()
                .register();
        }
        new CropMutation(Micadia, Tine, Bauxia)
            .addToMutationPools(aMetal, aPine, aMica, aBush)
            .machineOnly()
            .register();
        new CropMutation(Milkwart, Corium, Netherwart)
            .addToMutationPools(aFood, aMilk, aCow)
            .register();
        new CropMutation(Nickelback, Ferru, Coppon)
            .addToMutationPools(aMetal, aFire, aAlloy)
            .register();
        new CropMutation(OilBerry, SoulSandLily, Withereed)
            .addToMutationPools(aFire, aDark, aReed, aRotten, aCoal, aOil)
            .machineOnly()
            .register();
        new CropMutation(Olivia, EndStoneLily, EvilOre)
            .machineOnly()
            .addToMutationPools(aCrystal, aShiny, aProcessing, aOlivine)
            .register();
        new CropMutation(Osmianth, Platina, Scheelinium)
            .addToMutationPools(aMetal, aOsmium, aFlower)
            .machineOnly()
            .register();
        // leather bound books, birch has a paper-like bark
        new CropMutation(Papyrus, Corium, BonsaiBirch)
            .addToMutationPools(aWhite, aPaper)
            .register();
        // TODO: ADD CHROME CROP AND REPLACE TITANIA WITH NEW CROP IN PLATINA MUTATION
        new CropMutation(Platina, Diareed, Titania)
            .addToMutationPools(aMetal, aShiny, aReed, aPlatinum)
            .machineOnly()
            .register();
        new CropMutation(Plumbilia, Coppon, Withereed)
            .addToMutationPools(aHeavy, aMetal, aLead, aReed)
            .register();
        new CropMutation(Pyrolusium, Bauxia, Titania)
            .addToMutationPools(aMetal, aClean, aBush, aManganese)
            .register();
        new CropMutation(Reactoria, Titania, GodOfThunder)
            .addToMutationPools(aRadioactive, aMetal, aDanger, aUranium)
            .machineOnly()
            .register();
        new CropMutation(Redwheat, NetherStoneLily, Wheat)
            .addToMutationPools(aRed, aRedstone, aWheat)
            .register();
        new CropMutation(SaltyRoot, SugarBeet, Canola)
            .addToMutationPools(aSalt, aGray, aRoot, aHydrophobic)
            .register();
        new CropMutation(Sapphirum, EvilOre, Lazulia)
            .addToMutationPools(aCrystal, aShiny, aMetal, aSapphire)
            .register();
        new CropMutation(Scheelinium, Titania, Pyrolusium, EndStoneLily)
            .addToMutationPools(aMetal, aHard, aBush, aTungsten)
            .machineOnly()
            .register();
        new CropMutation(SpaceFlower, EndStoneLily, Titania)
            .addToMutationPools(aAlien, aSpace, aRadiation, aTransform)
            .machineOnly()
            .register();
        // TODO: ADD PLUTONIUM CROP AND REPLACE TROLL PLANT WITH NEW CROP IN STARGATIUM MUTATION
        new CropMutation(Stargatium, Iridine, Trollplant)
            .addToMutationPools(aMetal, aHeavy, aAlien, aNaquadah)
            .machineOnly()
            .register();
        // late hv to early hv is where I want starwart to be gated around, might reconsider based on existing metas
        // it can be used in MECs for a pretty hefty amount of eu so gating it up until then is probably a go since
        // 1 nether star dust is like 1.5h of hv power in there.
        new CropMutation(Starwart, Withereed, Titania, GodOfThunder)
            .addToMutationPools(aWither, aNether, aUndead, aNetherstar)
            .machineOnly()
            .register();
        if (Mods.TwilightForest.isModLoaded()) {
            new CropMutation(Steeleafranks, Torchberry, KnightmetalBerry)
                .addToMutationPools(aMetal, aTendrilly, aIron)
                .register();
        }
        new CropMutation(Tine, StoneLily, BonsaiSpruce)
            .addToMutationPools(aShiny, aMetal, aPine, aTin, aBush)
            .register();
        new CropMutation(Titania, Bauxia, Redwheat)
            .addToMutationPools(aMetal, aHeavy, aReed, aTitanium)
            .machineOnly()
            .register();
        new CropMutation(Transformium, Trollplant, Bauxia, Titania)
            .addToMutationPools(aTransform, aCoal, aReed)
            .machineOnly()
            .register();
        // goldfish is purely here to troll
        new CropMutation(Trollplant, Goldfish, Reactoria, Fertilia)
            .addToMutationPools(aTroll, aBad, aScrap)
            .machineOnly()
            .register();
        new CropMutation(Withereed, BasaltLily, BlackGraniteLily)
            .addToMutationPools(aFire, aUndead, aReed, aCoal, aRotten, aWither)
            .register();
        // endregion material crops

        // region mobs
        if (Mods.Natura.isModLoaded()) {
            new CropMutation(Blackthorn, Blackberry, Goldfish)
                .addToMutationPools(aBlack, aFlower, aRose)
                .register();
        }
        if (Mods.Thaumcraft.isModLoaded()) {
            new CropMutation(Blazereed, SugarCane, Cinderpearl)
                .addToMutationPools(aFire, aBlaze, aReed, aSulfur)
                .register();
        }
        new CropMutation(Corium, Wheat, Cocoa)
            .addToMutationPools(aCow, aSilk, aTendrilly)
            .register();
        if (Mods.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Corpseplant, Zomplant, Eyebulb)
                .addToMutationPools(aToxic, aUndead, aTendrilly, aFood, aRotten)
                .register();
        }
        // sulfur, charcoal and salt peter
        new CropMutation(Creeperweed, NetherStoneLily, Withereed, SaltyRoot)
            .addToMutationPools(aCreeper, aTendrilly, aExplosive, aFire, aSulfur, aSaltpeter, aCoal)
            .register();
        new CropMutation(EggPlant, Corium, OxeyeDaisy)
            .addToMutationPools(aChicken, aEgg, aFood, aFeather, aFlower, aAddictive)
            .register();
        new CropMutation(Enderbloom, EndStoneLily, Creeperweed)
            .addToMutationPools(aEnder, aFlower, aShiny)
            .register();
        if (Mods.Witchery.isModLoaded()) {
            new CropMutation(Goldfish, Waterlily, WaterArtichoke, Mandrake)
                .addToMutationPools(aNether, aFish, aFood, aBad, aWater)
                .register();
        }
        new CropMutation(Meatrose, Goldfish, EggPlant, Corium)
            .addToMutationPools(aFood, aFlower, aCow, aFish, aChicken, aPig)
            .register();
        new CropMutation(Slimeplant, BlueOrchid, ClayLily)
            .addToMutationPools(aSlime, aBouncy, aSticky, aBush)
            .register();
        new CropMutation(Spidernip, Flax, Hemp, Zomplant)
            .addToMutationPools(aToxic, aSilk, aSpider, aFlower, aIngredient, aAddictive)
            .register();
        new CropMutation(Tearstalks, Goldfish, SoulSandLily, NetherStoneLily)
            .addToMutationPools(aHealing, aNether, aIngredient, aReed, aGhast)
            .register();
        if (Mods.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Zomplant, Withereed, Eyebulb)
                .addToMutationPools(aZombie, aRotten, aUndead)
                .register();
        }
        // endregion mobs

        // region natura
        if (Mods.Natura.isModLoaded()) {
            new CropMutation(Blightberry, Maloberry, Raspberry)
                .addToMutationPools(aBerry, aToxic, aBad, aGreen, aNether, aAddictive)
                .register();
            new CropMutation(Duskberry, Blackthorn, Blackberry)
                .addToMutationPools(aBerry, aToxic, aBad, aGray, aNether, aAddictive)
                .register();
            new CropMutation(Skyberry, Cyazint, Blueberry)
                .addToMutationPools(aBerry, aToxic, aBad, aBlue, aNether, aAddictive)
                .register();
            new CropMutation(Stingberry, Thornvine, SaguaroCactus)
                .addToMutationPools(aBerry, aToxic, aBad, aGreen, aNether, aAddictive)
                .register();
            new CropMutation(Cotton, Flax, Hemp)
                .addToMutationPools(aWhite, aCotton)
                .register();
            new CropMutation(Thornvine, Vine, Cactus)
                .addToMutationPools(aNether, aClimbable, aBad)
                .register();
            if (Mods.BiomesOPlenty.isModLoaded()) {
                new CropMutation(BlueGlowshroom, BlueOrchid, Glowflower)
                    .addToMutationPools(aFood, aMushroom, aIngredient, aNether)
                    .register();
                new CropMutation(GreenGlowshroom, Cactus, Glowflower)
                    .addToMutationPools(aFood, aMushroom, aIngredient, aNether)
                    .register();
                new CropMutation(PurpleGlowshroom, Indigo, Glowflower)
                    .addToMutationPools(aFood, aMushroom, aIngredient, aNether)
                    .register();
            }
        }
        // endregion natura

        // region ore berries
        if (Mods.TinkerConstruct.isModLoaded()) {
            new CropMutation(AluminiumOreBerry, GoldOreBerry, EssenceOreBerry)
                .addToMutationPools(aOreBerry, aAluminium, aMetal, aAluminum)
                .register();
            new CropMutation(ArditeOreBerry, NetherStoneLily, Coppon, CopperOreBerry, Cyprium)
                .addToMutationPools(aOreBerry, aArdite, aMetal, aOrange)
                .register();
            new CropMutation(CobaltOreBerry, NetherStoneLily, ArditeOreBerry, Lazulia, GoldOreBerry)
                .addToMutationPools(aOreBerry, aCobalt, aMetal, aBlue)
                .register();
            if (Mods.BiomesOPlenty.isModLoaded()) {
                new CropMutation(CopperOreBerry, Cyprium, BoPBerry, StoneLily)
                    .addToMutationPools(aOreBerry, aCopper, aMetal, aShiny)
                    .register();
            }
            new CropMutation(EssenceOreBerry, Creeperweed, Zomplant, Spidernip, Tearstalks)
                .addToMutationPools(aOreBerry, aEssence, aUndead)
                .register();
            new CropMutation(GoldOreBerry, CopperOreBerry, IronOreBerry, TinOreBerry)
                .addToMutationPools(aOreBerry, aGold, aMetal)
                .register();
            new CropMutation(IronOreBerry, TinOreBerry, StoneLily)
                .addToMutationPools(aOreBerry, aGray, aMetal)
                .register();
            if (Mods.BiomesOPlenty.isModLoaded()) {
                new CropMutation(TinOreBerry, Stagnium, BoPBerry, StoneLily)
                    .addToMutationPools(aOreBerry, aTin, aMetal, aShiny)
                    .register();
            }
            if (Mods.TwilightForest.isModLoaded()) {
                new CropMutation(KnightmetalBerry, IronOreBerry, Torchberry, BonsaiDarkOak)
                    .addToMutationPools(aOreBerry, aKnightly, aMetal)
                    .register();
            }
            if (Mods.Thaumcraft.isModLoaded()) {
                // TODO: ADD GREATWOOD AND SILVER WOOD BONSAIS AND ADD DETERMINISTIC RECIPE FOR THAUMIUM ORE BERRY
                new CropMutation(ThaumiumOreBerry, StoneLily, GoldOreBerry)
                    .addToMutationPools(aOreBerry, aMagic, aMetal, aThaumium, aVoid)
                    .register();
                new CropMutation(VoidOreBerry, ThaumiumOreBerry, GoldOreBerry)
                    .addToMutationPools(aOreBerry, aMagic, aMetal, aVoid)
                    .register();
                if (Mods.ThaumicBases.isModLoaded()) {
                    new CropMutation(ThauminiteOreBerry, ThaumiumOreBerry, ManaBean)
                        .addToMutationPools(aOreBerry, aMagic, aMetal, aThauminite, aVoid)
                        .register();
                }
            }
        }
        // endregion ore berries

        // region stone lilies
        if (Mods.Botania.isModLoaded()) {
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
        new CropMutation(BasaltLily, StoneLily, Blackthorn)
            .addToMutationPools(aBlack, aStone, aDark)
            .register();
        new CropMutation(BlackGraniteLily, StoneLily, BasaltLily)
            .addToMutationPools(aBlack, aStone, aDark)
            .register();
        new CropMutation(ClayLily, StoneLily, Waterlily)
            .addToMutationPools(aGray, aStone, aWater)
            .register();
        new CropMutation(EndStoneLily, StoneLily, Enderbloom)
            .addToMutationPools(aYellow, aStone, aAlien)
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
        if (Mods.Thaumcraft.isModLoaded()) {
            if (Mods.Witchery.isModLoaded()) {
                new CropMutation(Cinderpearl, EmberMoss, GlintWeed)
                    .addToMutationPools(aMagic, aBlaze, aNether)
                    .register();
            }
            new CropMutation(ManaBean, Cocoa, Cinderpearl, Shimmerleaf)
                .addToMutationPools(aBerry, aBean, aMagic, aColorful)
                .register();
            // prim perl can't be bred, only planted with an actual prim perl
            // redwheat because shimmerleaf is a quickilver/mercury source and redstone is also that
            new CropMutation(Shimmerleaf, WhiteTulip, Redwheat)
                .addToMutationPools(aMagic, aSilver, aToxic)
                .register();
        }
        // endregion thaumcraft

        // region twilight forest
        if (Mods.TwilightForest.isModLoaded() && Mods.BiomesOPlenty.isModLoaded()) {
            new CropMutation(Torchberry, Glowflower, BoPBerry)
                .addToMutationPools(aBerry, aGlow, aShimmer)
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
            .addToMutationPools(aOrange, aDecoration, aStem)
            .register();
        new CropMutation(RedMushroom, Pumpkin, Poppy)
            .addToMutationPools(aRed, aFood, aMushroom)
            .register();
        new CropMutation(BrownMushroom, Cocoa, RedMushroom)
            .addToMutationPools(aBrown, aFood, aMushroom)
            .register();
        MutationRegistry.instance.register(Wheat, aYellow, aFood, aWheat);
        MutationRegistry.instance.register(Carrot, aOrange, aFood, aCarrots);
        MutationRegistry.instance.register(Potato, aYellow, aFood, aPotato);
        MutationRegistry.instance.register(Dandelion, aFlower, aYellow);
        MutationRegistry.instance.register(Poppy, aRed, aFlower, aRose);
        new CropMutation(BlueOrchid, Poppy, Waterlily)
            .addToMutationPools(aFlower, aBlue)
            .register();
        new CropMutation(AzureBluet, Poppy, Potato)
            .addToMutationPools(aFlower, aWhite)
            .register();
        new CropMutation(RedTulip, Poppy, WhiteTulip)
            .addToMutationPools(aFlower, aRed)
            .register();
        new CropMutation(OrangeTulip, RedTulip, Dandelion)
            .addToMutationPools(aFlower, aOrange)
            .register();
        new CropMutation(PinkTulip, RedTulip, WhiteTulip)
            .addToMutationPools(aFlower, aRed)
            .register();
        new CropMutation(WhiteTulip, PinkTulip, OxeyeDaisy)
            .addToMutationPools(aFlower, aWhite)
            .register();
        new CropMutation(Allium, PinkTulip, PurpleTulip)
            .addToMutationPools(aFlower, aPurple)
            .register();
        new CropMutation(OxeyeDaisy, AzureBluet, Dandelion)
            .addToMutationPools(aFlower, aWhite)
            .register();
        new CropMutation(Cactus, SugarCane, Potato)
            .addToMutationPools(aGreen, aCactus)
            .register();
        new CropMutation(SugarCane, Carrot, Potato)
            .addToMutationPools(aReed)
            .register();
        new CropMutation(Netherwart, RedMushroom, BrownMushroom)
            .addToMutationPools(aRed, aNether, aIngredient, aSoulsand)
            .register();
        new CropMutation(Vine, Pumpkin, BlueOrchid)
            .addToMutationPools(aGreen, aTendrilly)
            .register();
        new CropMutation(Waterlily, Vine, SugarCane)
            .addToMutationPools(aBlue, aWater, aGreen)
            .register();
        // endregion vanilla

        // region witchery
        if (Mods.Witchery.isModLoaded()) {
            new CropMutation(Belladonna, PurpleTulip, PinkTulip)
                .addToMutationPools(aPurple, aFlower, aToxic, aIngredient)
                .register();
            if (Mods.BiomesOPlenty.isModLoaded()) {
                new CropMutation(EmberMoss, RedTulip, Moss)
                    .addToMutationPools(aFire, aIngredient, aBad, aClimbable)
                    .register();
            }
            new CropMutation(Garlic, Onion, Allium)
                .addToMutationPools(aFood, aIngredient, aHealing)
                .register();
            new CropMutation(GlintWeed, Vine, EmberMoss)
                .addToMutationPools(aOrange, aFlower, aMagic)
                .register();
            new CropMutation(Mandrake, Carrot, Belladonna)
                .addToMutationPools(aFlower, aMagic, aBad, aToxic, aIngredient)
                .register();
            new CropMutation(Snowbell, Belladonna, GlintWeed)
                .addToMutationPools(aWhite, aFlower, aIce, aToxic, aIngredient)
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
