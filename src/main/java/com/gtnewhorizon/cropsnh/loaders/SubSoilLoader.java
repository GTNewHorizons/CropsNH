package com.gtnewhorizon.cropsnh.loaders;

import java.util.function.Predicate;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.farming.requirements.SubSoilRequirement;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import bartworks.system.material.WerkstoffLoader;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.GregTechAPI;
import gregtech.api.enums.Materials;

public class SubSoilLoader {

    public static void postInit() {
        // spotless:off
        // Vanilla Stone Lilies
        CropsNHSubSoilTypes.stone
            .addBlock(
                new BlockWithMeta(Blocks.stone),
                new BlockWithMeta(Blocks.cobblestone),
                new BlockWithMeta(Blocks.stonebrick),
                new BlockWithMeta(Blocks.mossy_cobblestone)
            )
            .addOreDict("bricksStone");
        CropsNHSubSoilTypes.sand.addBlock(new BlockWithMeta(Blocks.sand), new BlockWithMeta(Blocks.sandstone));
        CropsNHSubSoilTypes.clay.addOreDict("hardenedClay","blockStainedHardenedClay").addBlock(new BlockWithMeta(Blocks.clay), new BlockWithMeta(Blocks.hardened_clay), new BlockWithMeta(Blocks.stained_hardened_clay));
        CropsNHSubSoilTypes.soulSand.addBlock(new BlockWithMeta(Blocks.soul_sand)).addOreDict("soulSand", "soulsand");
        CropsNHSubSoilTypes.netherrack.addBlock(new BlockWithMeta(Blocks.netherrack));
        CropsNHSubSoilTypes.endStone.addBlock(new BlockWithMeta(Blocks.end_stone));
        // GT Stone Lilies
        CropsNHSubSoilTypes.redGranite.addOreDict("stoneGraniteRed");
        CropsNHSubSoilTypes.blackGranite.addOreDict("stoneGraniteBlack");
        CropsNHSubSoilTypes.marble.addOreDict("stoneMarble");
        CropsNHSubSoilTypes.basalt.addOreDict("stoneBasalt");
        // Modern Stone Lilies
        CropsNHSubSoilTypes.modernAndesite.addOreDict("stoneAndesite", "stoneAndesitePolished", "stoneAndesiteBricks", "stoneAndesiteChiseled");
        CropsNHSubSoilTypes.modernDiorite.addOreDict("stoneDiorite", "stoneDioritePolished", "stoneDioriteBricks", "stoneDioriteChiseled");
        CropsNHSubSoilTypes.modernGranite.addOreDict("stoneGranite", "stoneGranitePolished", "stoneGraniteBricks", "stoneGraniteChiseled");
        // Et Futurum Stone Lilies
        if (ModUtils.EtFuturumRequiem.isModLoaded()) {
            CropsNHSubSoilTypes.tuff.addBlock(new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("tuff")));
            CropsNHSubSoilTypes.deepslate
                .addBlock(new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("deepslate")))
                .addBlock(new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("cobbled_deepslate")))
                .addBlock(new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("polished_deepslate")))
                .addBlock(new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("deepslate_bricks")));
        }
        // vanilla ores
        CropsNHSubSoilTypes.coal.addBlockAndOreDict().addMaterial(Materials.Coal);
        CropsNHSubSoilTypes.iron.addBlockAndOreDict().addMaterial(Materials.Iron);
        CropsNHSubSoilTypes.gold.addBlockAndOreDict().addMaterial(Materials.Gold);
        CropsNHSubSoilTypes.diamond.addBlockAndOreDict().addMaterial(Materials.Diamond);
        CropsNHSubSoilTypes.emerald.addBlockAndOreDict().addMaterial(Materials.Emerald);
        CropsNHSubSoilTypes.redstone.addBlockAndOreDict().addMaterial(Materials.Redstone);
        CropsNHSubSoilTypes.lapis.addBlockAndOreDict().addMaterial(Materials.Lapis);
        // modded ores
        CropsNHSubSoilTypes.aluminium.addBlockAndOreDict("aluminium", "aluminum").addMaterial(Materials.Aluminium);
        CropsNHSubSoilTypes.aluminiumBauxite.addBlockAndOreDict("aluminium", "aluminum","bauxite").addMaterial(Materials.Aluminium, Materials.Bauxite);
        CropsNHSubSoilTypes.copper.addBlockAndOreDict().addMaterial(Materials.Copper);
        CropsNHSubSoilTypes.chrome.addBlockAndOreDict().addMaterial(Materials.Chrome);
        CropsNHSubSoilTypes.iridium.addBlockAndOreDict().addMaterial(Materials.Iridium);
        CropsNHSubSoilTypes.lead.addBlockAndOreDict().addMaterial(Materials.Lead, Materials.Galena);
        CropsNHSubSoilTypes.magnesium.addBlockAndOreDict().addMaterial(Materials.Magnesium);
        CropsNHSubSoilTypes.manganese.addBlockAndOreDict().addMaterial(Materials.Manganese, Materials.Pyrolusite);
        CropsNHSubSoilTypes.mica.addBlockAndOreDict().addMaterial(Materials.Mica)
            // Cupronickel coil
            .addBlock(new BlockWithMeta(GregTechAPI.sBlockCasings5, 0));
        CropsNHSubSoilTypes.naquadah.addBlockAndOreDict().addMaterial(Materials.Naquadah, Materials.NaquadahEnriched, Materials.Naquadria);
        CropsNHSubSoilTypes.netherStar.addBlockAndOreDict().addMaterial(Materials.NetherStar);
        CropsNHSubSoilTypes.nickel.addBlockAndOreDict().addMaterial(Materials.Nickel);
        CropsNHSubSoilTypes.osmium.addBlockAndOreDict().addMaterial(Materials.Osmium);
        CropsNHSubSoilTypes.platinum.addBlockAndOreDict().addMaterial(Materials.Platinum, Materials.Cooperite);
        CropsNHSubSoilTypes.silver.addBlockAndOreDict().addMaterial(Materials.Silver);
        CropsNHSubSoilTypes.thorium.addBlockAndOreDict().addMaterial(Materials.Thorium, WerkstoffLoader.Thorianit.getBridgeMaterial());
        CropsNHSubSoilTypes.tin.addBlockAndOreDict().addMaterial(Materials.Tin);
        CropsNHSubSoilTypes.titanium.addBlockAndOreDict().addMaterial(Materials.Titanium, Materials.Ilmenite, Materials.Rutile);
        CropsNHSubSoilTypes.tungsten.addBlockAndOreDict().addMaterial(Materials.Tungsten, Materials.Scheelite, Materials.Tungstate);
        CropsNHSubSoilTypes.uranium.addBlockAndOreDict().addBlockAndOreDict("Uranium235", "Uranium238").addMaterial(Materials.Uranium, Materials.Uranium235, Materials.Uraninite, Materials.Pitchblende);
        CropsNHSubSoilTypes.zinc.addBlockAndOreDict().addMaterial(Materials.Zinc, Materials.Sphalerite);
        CropsNHSubSoilTypes.sulfur.addBlockAndOreDict().addMaterial(Materials.Sulfur, Materials.Sphalerite, Materials.Galena);
        if (ModUtils.Natura.isModLoaded()) {
            CropsNHSubSoilTypes.sulfur.addBlock(new BlockWithMeta(ModUtils.Natura.getBlock("Cloud"), 3));
        }
        // gem ores
        CropsNHSubSoilTypes.garnetGem.addBlockAndOreDict().addMaterial(Materials.GarnetRed, Materials.GarnetYellow, Materials.GarnetSand);
        CropsNHSubSoilTypes.olivine.addBlockAndOreDict().addMaterial(Materials.Olivine);
        CropsNHSubSoilTypes.sapphire.addBlockAndOreDict().addMaterial(Materials.Sapphire);
        CropsNHSubSoilTypes.ruby.addBlockAndOreDict().addMaterial(Materials.Ruby);
        // non-gt ores
        CropsNHSubSoilTypes.knightmetal.addBlockAndOreDict().addMaterial(Materials.Knightmetal);
        CropsNHSubSoilTypes.steeleaf.addBlockAndOreDict().addMaterial(Materials.Steeleaf);
        CropsNHSubSoilTypes.cobalt.addBlockAndOreDict().addMaterial(Materials.Cobalt);
        CropsNHSubSoilTypes.ardite.addBlockAndOreDict().addMaterial(Materials.Ardite);
        // magic ores
        CropsNHSubSoilTypes.$void.addBlockAndOreDict().addMaterial(Materials.Void);
        CropsNHSubSoilTypes.thaumium.addBlockAndOreDict().addMaterial(Materials.Thaumium);
        CropsNHSubSoilTypes.thauminite.addOreDict("blockThauminite");
        CropsNHSubSoilTypes.shadowmetal.addBlockAndOreDict("Shadow").addMaterial(Materials.Shadow);
        // others
        CropsNHSubSoilTypes.snow.addOreDict("blockSnow").addBlock(new BlockWithMeta(net.minecraft.init.Blocks.snow));
        CropsNHSubSoilTypes.skull.addOreDict("itemSkull").addBlock(new BlockWithMeta(net.minecraft.init.Blocks.skull));
        CropsNHSubSoilTypes.glowstone.addOreDict("stoneGlowstone", "glowstone").addBlock(new BlockWithMeta(Blocks.glowstone));
        CropsNHSubSoilTypes.blaze.addOreDict("blockBlaze");
        CropsNHSubSoilTypes.quicksilver.addOreDict("blockQuicksilver");
        if (ModUtils.Thaumcraft.isModLoaded()) {
            CropsNHSubSoilTypes.mixedCrystalCluster.addBlock(new BlockWithMeta(ModUtils.Thaumcraft.getBlock("blockCrystal"), 6));
        }
        // galacticraft
        if (ModUtils.GalacticraftCore.isModLoaded()) {
            CropsNHSubSoilTypes.space.addOreDict("rockSpace");
        }

        // mod-specific extensions
        if (ModUtils.Botania.isModLoaded()) {
            CropsNHSubSoilTypes.endStone
                .addBlock(new BlockWithMeta(ModUtils.Botania.getBlock("endStoneBrick")));
        }
        if (ModUtils.Chisel.isModLoaded()) {
            CropsNHSubSoilTypes.aluminium.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("aluminumblock"))
            );
            CropsNHSubSoilTypes.aluminiumBauxite.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("aluminumblock"))
            );
            CropsNHSubSoilTypes.modernAndesite.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("andesite"))
            );
            CropsNHSubSoilTypes.modernDiorite.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("diorite"))
            );
            CropsNHSubSoilTypes.modernGranite.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("granite"))
            );
            CropsNHSubSoilTypes.clay.addBlock(
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
            CropsNHSubSoilTypes.cobalt.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("cobaltblock"))
            );
            CropsNHSubSoilTypes.copper.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("copperblock"))
            );
            CropsNHSubSoilTypes.diamond.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("diamond_block"))
            );
            CropsNHSubSoilTypes.emerald.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("emerald_block"))
            );
            CropsNHSubSoilTypes.endStone.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("end_Stone"))
            );
            CropsNHSubSoilTypes.glowstone.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("glowstone"))
            );
            CropsNHSubSoilTypes.gold.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("gold_block")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("gold2"))
            );
            CropsNHSubSoilTypes.iron.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("iron_block")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("iron2"))
            );
            CropsNHSubSoilTypes.lapis.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("lapis_block"))
            );
            CropsNHSubSoilTypes.lead.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("leadblock"))
            );
            CropsNHSubSoilTypes.marble.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("marble"))
            );
            CropsNHSubSoilTypes.netherrack.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("netherrack"))
            );
            CropsNHSubSoilTypes.nickel.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("nickelblock"))
            );
            CropsNHSubSoilTypes.platinum.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("platinumblock"))
            );
            CropsNHSubSoilTypes.redstone.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("redstone_block"))
            );
            CropsNHSubSoilTypes.sand.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("sandstone")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("sandstone2")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("sandstone_scribbles")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("sand_snakestone"))
            );
            CropsNHSubSoilTypes.silver.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("silverblock"))
            );
            CropsNHSubSoilTypes.stone.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("cobblestone")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("mossy_cobblestone")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("stone_snakestone")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("stonebricksmooth"))
            );
            CropsNHSubSoilTypes.thaumium.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("thaumium"))
            );
            CropsNHSubSoilTypes.tin.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("tinblock"))
            );
            CropsNHSubSoilTypes.uranium.addBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("uraniumblock"))
            );
        }
        if (ModUtils.EtFuturumRequiem.isModLoaded()) {
            CropsNHSubSoilTypes.clay.addBlock(
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
            CropsNHSubSoilTypes.copper.addBlock(
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("chiseled_copper")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("copper_block"))
            );
            CropsNHSubSoilTypes.endStone.addBlock(
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("end_bricks"))
            );
            CropsNHSubSoilTypes.sand.addBlock(
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("smooth_red_sandstone")),
                new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("red_sandstone"))
            );

            // gotta check if the block was registered since it's a config.
            Block efrStone = GameRegistry.findBlock(ModUtils.EtFuturumRequiem.ID, "stone");
            if (efrStone != null) {
                CropsNHSubSoilTypes.modernGranite.addBlock(
                    new BlockWithMeta(efrStone, 1),
                    new BlockWithMeta(efrStone, 2)
                );
                CropsNHSubSoilTypes.modernDiorite.addBlock(
                    new BlockWithMeta(efrStone, 3),
                    new BlockWithMeta(efrStone, 4)
                );
                CropsNHSubSoilTypes.modernAndesite.addBlock(
                    new BlockWithMeta(efrStone, 5),
                    new BlockWithMeta(efrStone, 6)
                );
            }
        }
        if (ModUtils.ExtraUtilities.isModLoaded()) {
            CropsNHSubSoilTypes.stone.addBlock(
                new BlockWithMeta(ModUtils.ExtraUtilities.getBlock("color_stonebrick")),
                new BlockWithMeta(ModUtils.ExtraUtilities.getBlock("color_stone"))
            );
        }
        if (ModUtils.TinkerConstruct.isModLoaded()) {
            CropsNHSubSoilTypes.ardite.addBlock(
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickmetal"), 1),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickmetal"), 5)
            );
            CropsNHSubSoilTypes.cobalt.addBlock(
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickmetal"), 2),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickmetal"), 6)
            );
            CropsNHSubSoilTypes.endStone.addBlock(
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrick"), 12),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickfancy"), 12)
            );
            CropsNHSubSoilTypes.netherrack.addBlock(
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrick"), 2),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickfancy"), 2)
            );
            CropsNHSubSoilTypes.stone.addBlock(
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrick"), 3),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickfancy"), 3),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickfancy"), 14),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickfancy"), 15)
            );
            // alu gravel gets replaced with zinc gravel when in full pack via a postea transformer. Because of some nei
            // shenanigans all stacks displayed in nei also get hit by postea, so this effectively hides it from nei
            // while still actually allowing it if it somehow doesn't get migrated (eg we add it back in the future)
            if (ModUtils.NewHorizonsCoreMod.isModLoaded()) {
                Block gravelOre = ModUtils.TinkerConstruct.getBlock("GravelOre");
                Predicate<SubSoilRequirement.SubSoilTarget> predicate = (target) -> !(target.isNEI && target.block == gravelOre && target.meta == 4);
                CropsNHSubSoilTypes.aluminiumBauxite.addAndPredicate(predicate);
                CropsNHSubSoilTypes.aluminium.addAndPredicate(predicate);
            }
        }
        if (ModUtils.ThaumicBases.isModLoaded()) {
            CropsNHSubSoilTypes.stone.addBlock(
                new BlockWithMeta(ModUtils.ThaumicBases.getBlock("oldCobble")),
                new BlockWithMeta(ModUtils.ThaumicBases.getBlock("oldCobbleMossy"))
            );
        }
        if (ModUtils.TwilightForest.isModLoaded()) {
            // the TF Steeleaf block doesn't have the blockSteeleaf ore dict for some reason
            CropsNHSubSoilTypes.steeleaf.addBlock(
                new BlockWithMeta(ModUtils.TwilightForest.getBlock("tile.SteeleafBlock"))
            );
        }
        // spotless:on
    }
}
