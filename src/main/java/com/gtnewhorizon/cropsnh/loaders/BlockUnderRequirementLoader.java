package com.gtnewhorizon.cropsnh.loaders;

import bartworks.system.material.WerkstoffLoader;
import net.minecraft.init.Blocks;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.GregTechAPI;
import gregtech.api.enums.Materials;

public class BlockUnderRequirementLoader {

    public static void postInit() {
        // spotless:off
        // Vanilla Stone Lilies
        CropsNHBlockUnderTypes.stone
            .addBlock(
                new BlockWithMeta(Blocks.stone),
                new BlockWithMeta(Blocks.cobblestone),
                new BlockWithMeta(Blocks.stonebrick),
                new BlockWithMeta(Blocks.mossy_cobblestone)
            )
            .addOreDict("bricksStone");
        CropsNHBlockUnderTypes.sand.addBlock(new BlockWithMeta(Blocks.sand), new BlockWithMeta(Blocks.sandstone));
        CropsNHBlockUnderTypes.clay.addOreDict("hardenedClay","blockStainedHardenedClay").addBlock(new BlockWithMeta(Blocks.clay), new BlockWithMeta(Blocks.hardened_clay), new BlockWithMeta(Blocks.stained_hardened_clay));
        CropsNHBlockUnderTypes.soulSand.addBlock(new BlockWithMeta(Blocks.soul_sand)).addOreDict("soulSand", "soulsand");
        CropsNHBlockUnderTypes.netherrack.addBlock(new BlockWithMeta(Blocks.netherrack));
        CropsNHBlockUnderTypes.endStone.addBlock(new BlockWithMeta(Blocks.end_stone));
        // GT Stone Lilies
        CropsNHBlockUnderTypes.redGranite.addOreDict("stoneGraniteRed");
        CropsNHBlockUnderTypes.blackGranite.addOreDict("stoneGraniteBlack");
        CropsNHBlockUnderTypes.marble.addOreDict("stoneMarble");
        CropsNHBlockUnderTypes.basalt.addOreDict("stoneBasalt");
        // Botania Stone Lilies
        CropsNHBlockUnderTypes.botaniaAndesite.addOreDict("stoneAndesite", "stoneAndesitePolished", "stoneAndesiteBricks", "stoneAndesiteChiseled");
        CropsNHBlockUnderTypes.botaniaDiorite.addOreDict("stoneDiorite", "stoneDioritePolished", "stoneDioriteBricks", "stoneDioriteChiseled");
        CropsNHBlockUnderTypes.botaniaGranite.addOreDict("stoneGranite", "stoneGranitePolished", "stoneGraniteBricks", "stoneGraniteChiseled");
        // Et Futurum Stone Lilies
        if (ModUtils.EtFuturumRequiem.isModLoaded()) {
            CropsNHBlockUnderTypes.tuff.addBlock(new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("tuff")));
            CropsNHBlockUnderTypes.deepslate
                .addBlock(new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("deepslate")))
                .addBlock(new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("cobbled_deepslate")))
                .addBlock(new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("polished_deepslate")))
                .addBlock(new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("deepslate_bricks")));
        }
        // vanilla ores
        CropsNHBlockUnderTypes.coal.addBlockAndOreDict().addMaterial(Materials.Coal);
        CropsNHBlockUnderTypes.iron.addBlockAndOreDict().addMaterial(Materials.Iron);
        CropsNHBlockUnderTypes.gold.addBlockAndOreDict().addMaterial(Materials.Gold);
        CropsNHBlockUnderTypes.diamond.addBlockAndOreDict().addMaterial(Materials.Diamond);
        CropsNHBlockUnderTypes.emerald.addBlockAndOreDict().addMaterial(Materials.Emerald);
        CropsNHBlockUnderTypes.redstone.addBlockAndOreDict().addMaterial(Materials.Redstone);
        CropsNHBlockUnderTypes.lapis.addBlockAndOreDict().addMaterial(Materials.Lapis);
        // modded ores
        CropsNHBlockUnderTypes.aluminium.addBlockAndOreDict("aluminium", "aluminum").addMaterial(Materials.Aluminium);
        CropsNHBlockUnderTypes.aluminiumBauxite.addBlockAndOreDict("aluminium", "aluminum","bauxite").addMaterial(Materials.Aluminium, Materials.Bauxite);
        CropsNHBlockUnderTypes.copper.addBlockAndOreDict().addMaterial(Materials.Copper);
        CropsNHBlockUnderTypes.chrome.addBlockAndOreDict().addMaterial(Materials.Chrome);
        CropsNHBlockUnderTypes.iridium.addBlockAndOreDict().addMaterial(Materials.Iridium);
        CropsNHBlockUnderTypes.lead.addBlockAndOreDict().addMaterial(Materials.Lead, Materials.Galena);
        CropsNHBlockUnderTypes.magnesium.addBlockAndOreDict().addMaterial(Materials.Magnesium);
        CropsNHBlockUnderTypes.manganese.addBlockAndOreDict().addMaterial(Materials.Manganese, Materials.Pyrolusite);
        CropsNHBlockUnderTypes.mica.addBlockAndOreDict().addMaterial(Materials.Mica)
            // Cupronickel coil
            .addBlock(new BlockWithMeta(GregTechAPI.sBlockCasings5, 0));
        CropsNHBlockUnderTypes.naquadah.addBlockAndOreDict().addMaterial(Materials.Naquadah, Materials.NaquadahEnriched, Materials.Naquadria);
        CropsNHBlockUnderTypes.netherStar.addBlockAndOreDict().addMaterial(Materials.NetherStar);
        CropsNHBlockUnderTypes.nickel.addBlockAndOreDict().addMaterial(Materials.Nickel);
        CropsNHBlockUnderTypes.osmium.addBlockAndOreDict().addMaterial(Materials.Osmium);
        CropsNHBlockUnderTypes.platinum.addBlockAndOreDict().addMaterial(Materials.Platinum, Materials.Cooperite);
        CropsNHBlockUnderTypes.silver.addBlockAndOreDict().addMaterial(Materials.Silver);
        CropsNHBlockUnderTypes.thorium.addBlockAndOreDict().addMaterial(Materials.Thorium);
        CropsNHBlockUnderTypes.tin.addBlockAndOreDict().addMaterial(Materials.Tin);
        CropsNHBlockUnderTypes.titanium.addBlockAndOreDict().addMaterial(Materials.Titanium, Materials.Ilmenite, Materials.Rutile);
        CropsNHBlockUnderTypes.tungsten.addBlockAndOreDict().addMaterial(Materials.Tungsten, Materials.Scheelite, Materials.Tungstate);
        CropsNHBlockUnderTypes.uranium.addBlockAndOreDict().addBlockAndOreDict("Uranium235", "Uranium238").addMaterial(Materials.Uranium, Materials.Uranium235);
        CropsNHBlockUnderTypes.zinc.addBlockAndOreDict().addMaterial(Materials.Zinc, Materials.Sphalerite);
        // gem ores
        CropsNHBlockUnderTypes.garnetGem.addBlockAndOreDict().addMaterial(Materials.GarnetRed, Materials.GarnetYellow, Materials.GarnetSand);
        CropsNHBlockUnderTypes.olivine.addBlockAndOreDict().addMaterial(Materials.Olivine);
        CropsNHBlockUnderTypes.sapphire.addBlockAndOreDict().addMaterial(Materials.Sapphire);
        // non-gt ores
        CropsNHBlockUnderTypes.knightmetal.addBlockAndOreDict().addMaterial(Materials.Knightmetal);
        CropsNHBlockUnderTypes.cobalt.addBlockAndOreDict().addMaterial(Materials.Cobalt);
        CropsNHBlockUnderTypes.ardite.addBlockAndOreDict().addMaterial(Materials.Ardite);
        // magic ores
        CropsNHBlockUnderTypes.$void.addBlockAndOreDict().addMaterial(Materials.Void);
        CropsNHBlockUnderTypes.thaumium.addBlockAndOreDict().addMaterial(Materials.Thaumium);
        CropsNHBlockUnderTypes.thauminite.addOreDict("blockThauminite");
        CropsNHBlockUnderTypes.shadowmetal.addBlockAndOreDict("Shadow").addMaterial(Materials.Shadow);
        // others
        CropsNHBlockUnderTypes.snow.addOreDict("blockSnow").addBlock(new BlockWithMeta(net.minecraft.init.Blocks.snow));
        CropsNHBlockUnderTypes.skull.addOreDict("itemSkull").addBlock(new BlockWithMeta(net.minecraft.init.Blocks.skull));
        CropsNHBlockUnderTypes.glowstone.addOreDict("stoneGlowstone", "glowstone").addBlock(new BlockWithMeta(Blocks.glowstone));
        CropsNHBlockUnderTypes.blaze.addOreDict("blockBlaze");
        CropsNHBlockUnderTypes.quicksilver.addOreDict("blockQuicksilver");
        if (ModUtils.Thaumcraft.isModLoaded()) {
            CropsNHBlockUnderTypes.mixedCrystalCluster.addBlock(new BlockWithMeta(ModUtils.Thaumcraft.getBlock("blockCrystal"), 6));
        }
        // galacticraft
        if (ModUtils.GalacticraftCore.isModLoaded()) {
            CropsNHBlockUnderTypes.space.addOreDict("rockSpace");
        }

        // mod-specific extensions
        if (ModUtils.Botania.isModLoaded()) {
            CropsNHBlockUnderTypes.endStone
                .addBlock(new BlockWithMeta(ModUtils.Botania.getBlock("endStoneBrick")));
        }
        if (ModUtils.Chisel.isModLoaded()) {
            CropsNHBlockUnderTypes.aluminium.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("aluminumblock"))
            );
            CropsNHBlockUnderTypes.aluminiumBauxite.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("aluminumblock"))
            );
            CropsNHBlockUnderTypes.botaniaAndesite.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("andesite"))
            );
            CropsNHBlockUnderTypes.botaniaDiorite.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("diorite"))
            );
            CropsNHBlockUnderTypes.botaniaGranite.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("granite"))
            );
            CropsNHBlockUnderTypes.clay.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_white")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_white2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_orange")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_orange2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_magenta")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_magenta2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_lightblue")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_lightblue2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_lime")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_lime2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_yellow")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_yellow2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_pink")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_pink2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_gray")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_gray2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_lightgray")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_lightgray2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_cyan")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_cyan2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_purple")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_purple2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_blue")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_blue2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_brown")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_brown2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_green")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_green2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_red")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_red2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay_black")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("hardened_clay2"))
            );
            CropsNHBlockUnderTypes.cobalt.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("cobaltblock"))
            );
            CropsNHBlockUnderTypes.copper.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("copperblock"))
            );
            CropsNHBlockUnderTypes.diamond.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("diamond_block"))
            );
            CropsNHBlockUnderTypes.emerald.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("emerald_block"))
            );
            CropsNHBlockUnderTypes.endStone.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("end_Stone"))
            );
            CropsNHBlockUnderTypes.glowstone.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("glowstone"))
            );
            CropsNHBlockUnderTypes.gold.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("gold_block")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("gold2"))
            );
            CropsNHBlockUnderTypes.iron.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("iron_block")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("iron2"))
            );
            CropsNHBlockUnderTypes.lapis.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("lapis_block"))
            );
            CropsNHBlockUnderTypes.lead.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("leadblock"))
            );
            CropsNHBlockUnderTypes.marble.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("marble"))
            );
            CropsNHBlockUnderTypes.netherrack.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("netherrack"))
            );
            CropsNHBlockUnderTypes.nickel.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("nickelblock"))
            );
            CropsNHBlockUnderTypes.platinum.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("platinumblock"))
            );
            CropsNHBlockUnderTypes.redstone.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("redstone_block"))
            );
            CropsNHBlockUnderTypes.sand.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("sandstone")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("sandstone2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("sandstone_scribbles")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("sand_snakestone"))
            );
            CropsNHBlockUnderTypes.silver.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("silverblock"))
            );
            CropsNHBlockUnderTypes.stone.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("cobblestone")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("mossy_cobblestone")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("stone_snakestone")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("stonebricksmooth"))
            );
            CropsNHBlockUnderTypes.thaumium.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("thaumium"))
            );
            CropsNHBlockUnderTypes.tin.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("tinblock"))
            );
            CropsNHBlockUnderTypes.uranium.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("uraniumblock"))
            );
        }
        if (ModUtils.EtFuturumRequiem.isModLoaded()) {
            CropsNHBlockUnderTypes.clay.addBlock(
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("orange_glazed_terracotta")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("light_gray_glazed_terracotta")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("gray_glazed_terracotta")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("yellow_glazed_terracotta")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("white_glazed_terracotta")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("cyan_glazed_terracotta")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("red_glazed_terracotta")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("lime_glazed_terracotta")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("black_glazed_terracotta")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("light_blue_glazed_terracotta")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("purple_glazed_terracotta")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("brown_glazed_terracotta")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("pink_glazed_terracotta")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("green_glazed_terracotta")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("magenta_glazed_terracotta")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("blue_glazed_terracotta"))
            );
            CropsNHBlockUnderTypes.copper.addBlock(
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("chiseled_copper")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("copper_block"))
            );
            CropsNHBlockUnderTypes.endStone.addBlock(
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("end_bricks"))
            );
            CropsNHBlockUnderTypes.sand.addBlock(
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("smooth_red_sandstone")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("red_sandstone"))
            );
        }
        if (ModUtils.ExtraUtilities.isModLoaded()) {
            CropsNHBlockUnderTypes.stone.addBlock(
                new BlockWithMeta(ModUtils.ExtraUtilities.getBlock("color_stonebrick")),
                new BlockWithMeta(ModUtils.ExtraUtilities.getBlock("color_stone"))
            );
        }
        if (ModUtils.TinkerConstruct.isModLoaded()) {
            CropsNHBlockUnderTypes.ardite.addBlock(
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickmetal"), 1),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickmetal"), 5)
            );
            CropsNHBlockUnderTypes.cobalt.addBlock(
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickmetal"), 2),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickmetal"), 6)
            );
            CropsNHBlockUnderTypes.endStone.addBlock(
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrick"), 12),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickfancy"), 12)
            );
            CropsNHBlockUnderTypes.netherrack.addBlock(
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrick"), 2),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickfancy"), 2)
            );
            CropsNHBlockUnderTypes.stone.addBlock(
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrick"), 3),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickfancy"), 3),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickfancy"), 14),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickfancy"), 15)
            );
        }
        if (ModUtils.ThaumicBases.isModLoaded()) {
            CropsNHBlockUnderTypes.stone.addBlock(
                new BlockWithMeta(ModUtils.ThaumicBases.getBlock("oldCobble")),
                new BlockWithMeta(ModUtils.ThaumicBases.getBlock("oldCobbleMossy"))
            );
        }
        // spotless:on
    }
}
