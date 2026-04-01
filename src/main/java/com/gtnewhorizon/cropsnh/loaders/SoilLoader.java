package com.gtnewhorizon.cropsnh.loaders;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.farming.registries.SoilTramplingResistanceRegistry;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import tconstruct.tools.TinkerTools;
import thaumcraft.common.config.ConfigBlocks;

public class SoilLoader {

    public static void postInit() {

        // register vanilla soils
        BlockWithMeta farmland = new BlockWithMeta(Blocks.farmland);
        // also includes podzol and that weird non-grass growing dirt
        BlockWithMeta dirt = new BlockWithMeta(Blocks.dirt);
        BlockWithMeta grass = new BlockWithMeta(Blocks.grass);
        BlockWithMeta soulSand = new BlockWithMeta(Blocks.soul_sand);
        BlockWithMeta sand = new BlockWithMeta(Blocks.sand);
        BlockWithMeta mycelium = new BlockWithMeta(Blocks.mycelium);
        BlockWithMeta stone = new BlockWithMeta(Blocks.stone);
        BlockWithMeta cobblestone = new BlockWithMeta(Blocks.cobblestone);
        BlockWithMeta netherrack = new BlockWithMeta(Blocks.netherrack);
        BlockWithMeta endstone = new BlockWithMeta(Blocks.end_stone);
        BlockWithMeta snow = new BlockWithMeta(Blocks.snow);
        BlockWithMeta brick = new BlockWithMeta(Blocks.brick_block);

        CropsNHSoilTypes.stone.registerSoil(
            new BlockWithMeta(Blocks.stone),
            new BlockWithMeta(Blocks.cobblestone),
            new BlockWithMeta(Blocks.stonebrick),
            new BlockWithMeta(Blocks.mossy_cobblestone));
        CropsNHSoilTypes.farmland.registerSoil(farmland);
        CropsNHSoilTypes.sand.registerSoil(sand);
        CropsNHSoilTypes.soulsand.registerSoil(soulSand);
        CropsNHSoilTypes.dirt.registerSoil(dirt, grass);
        CropsNHSoilTypes.mycelium.registerSoil(mycelium);
        CropsNHSoilTypes.nether.registerSoil(soulSand, netherrack);
        CropsNHSoilTypes.end.registerSoil(endstone);
        CropsNHSoilTypes.stone.registerSoil(stone, cobblestone);
        CropsNHSoilTypes.snow.registerSoil(snow);
        CropsNHSoilTypes.netherrack.registerSoil(netherrack);
        CropsNHSoilTypes.brick.registerSoil(brick);

        // modded soils
        if (ModUtils.Botania.isModLoaded()) {
            CropsNHSoilTypes.end.registerSoil(new BlockWithMeta(ModUtils.Botania.getBlock("endStoneBrick")));
        }
        if (ModUtils.Chisel.isModLoaded()) {
            CropsNHSoilTypes.dirt.registerSoil(new BlockWithMeta(ModUtils.Chisel.getBlock("dirt")));
            CropsNHSoilTypes.end.registerSoil(new BlockWithMeta(ModUtils.Chisel.getBlock("end_Stone")));
            CropsNHSoilTypes.netherrack.registerSoil(new BlockWithMeta(ModUtils.Chisel.getBlock("netherrack")));
            CropsNHSoilTypes.stone.registerSoil(
                new BlockWithMeta(ModUtils.Chisel.getBlock("cobblestone")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("mossy_cobblestone")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("stone_snakestone")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("stonebricksmooth")));
        }
        if (ModUtils.EtFuturumRequiem.isModLoaded()) {
            CropsNHSoilTypes.end.registerSoil(new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("end_bricks")));
        }
        if (ModUtils.ExtraUtilities.isModLoaded()) {
            CropsNHSoilTypes.brick.registerSoil(new BlockWithMeta(ModUtils.ExtraUtilities.getBlock("color_brick")));
            CropsNHSoilTypes.stone.registerSoil(
                new BlockWithMeta(ModUtils.ExtraUtilities.getBlock("color_stonebrick")),
                new BlockWithMeta(ModUtils.ExtraUtilities.getBlock("color_stone")));
        }
        if (ModUtils.GalaxySpace.isModLoaded()) {
            CropsNHSoilTypes.snow.registerSoil(new BlockWithMeta(ModUtils.GalaxySpace.getBlock("enceladusblocks"), 0));
        }
        if (ModUtils.MagicBees.isModLoaded()) {
            BlockWithMeta enchantedEarth = new BlockWithMeta(ModUtils.MagicBees.getBlock("magicbees.enchantedEarth"));
            SoilTramplingResistanceRegistry.instance
                .setResistance(enchantedEarth, SoilTramplingResistanceRegistry.IMMUNE);
            CropsNHSoilTypes.farmland.registerSoil(enchantedEarth);
        }
        if (ModUtils.RandomThings.isModLoaded()) {
            BlockWithMeta fertilizedDirt = new BlockWithMeta(ModUtils.RandomThings.getBlock("fertilizedDirt_tilled"));
            SoilTramplingResistanceRegistry.instance
                .setResistance(fertilizedDirt, SoilTramplingResistanceRegistry.IMMUNE);
            CropsNHSoilTypes.farmland.registerSoil(fertilizedDirt);
        }
        if (ModUtils.TinkerConstruct.isModLoaded()) {
            // TiC blocks
            BlockWithMeta graveyardSoil = new BlockWithMeta(TinkerTools.craftedSoil, 3);
            CropsNHSoilTypes.graveyard.registerSoil(graveyardSoil);

            Block craftedSoilBlock = ModUtils.TinkerConstruct.getBlock("CraftedSoil");
            BlockWithMeta greeneSlimyMud = new BlockWithMeta(craftedSoilBlock, 0);
            BlockWithMeta blueSlimyMud = new BlockWithMeta(craftedSoilBlock, 2);
            BlockWithMeta slimeDirt = new BlockWithMeta(craftedSoilBlock, 5);
            BlockWithMeta slimeGrass = new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("slime.grass"), 0);
            CropsNHSoilTypes.slimy.registerSoil(greeneSlimyMud, blueSlimyMud, slimeDirt, slimeGrass);

            CropsNHSoilTypes.netherrack.registerSoil(
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrick"), 2),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickfancy"), 2));
            CropsNHSoilTypes.stone.registerSoil(
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrick"), 3),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickfancy"), 3),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickfancy"), 14),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickfancy"), 15));
        }
        if (ModUtils.Thaumcraft.isModLoaded()) {
            BlockWithMeta greatwoodLog1 = new BlockWithMeta(ConfigBlocks.blockMagicalLog, 0);
            BlockWithMeta greatwoodLog2 = new BlockWithMeta(ConfigBlocks.blockMagicalLog, 4);
            BlockWithMeta greatwoodLog3 = new BlockWithMeta(ConfigBlocks.blockMagicalLog, 8);
            BlockWithMeta silverwoodLog1 = new BlockWithMeta(ConfigBlocks.blockMagicalLog, 1);
            BlockWithMeta silverwoodLog2 = new BlockWithMeta(ConfigBlocks.blockMagicalLog, 5);
            BlockWithMeta silverwoodLog3 = new BlockWithMeta(ConfigBlocks.blockMagicalLog, 9);

            CropsNHSoilTypes.thaumLogs.registerSoil(
                greatwoodLog1,
                greatwoodLog2,
                greatwoodLog3,
                silverwoodLog1,
                silverwoodLog2,
                silverwoodLog3);
            CropsNHSoilTypes.silverwoodLog.registerSoil(silverwoodLog1, silverwoodLog2, silverwoodLog3);
        }
        if (ModUtils.ThaumicBases.isModLoaded()) {
            CropsNHSoilTypes.brick.registerSoil(new BlockWithMeta(ModUtils.ThaumicBases.getBlock("oldBrick")));
            CropsNHSoilTypes.stone.registerSoil(
                new BlockWithMeta(ModUtils.ThaumicBases.getBlock("oldCobble")),
                new BlockWithMeta(ModUtils.ThaumicBases.getBlock("oldCobbleMossy")));
        }
        if (ModUtils.Ztones.isModLoaded()) {
            BlockWithMeta gardenSoil = new BlockWithMeta(ModUtils.Ztones.getBlock("cleanDirt"));
            SoilTramplingResistanceRegistry.instance.setResistance(gardenSoil, SoilTramplingResistanceRegistry.IMMUNE);
            CropsNHSoilTypes.farmland.registerSoil(gardenSoil);
        }

    }
}
