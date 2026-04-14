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
        CropsNHSoilTypes.stone.registerBlock(
            new BlockWithMeta(Blocks.stone),
            new BlockWithMeta(Blocks.cobblestone),
            new BlockWithMeta(Blocks.stonebrick),
            new BlockWithMeta(Blocks.mossy_cobblestone));
        CropsNHSoilTypes.farmland.registerBlock(new BlockWithMeta(Blocks.farmland));
        CropsNHSoilTypes.sand.registerBlock(new BlockWithMeta(Blocks.sand));
        CropsNHSoilTypes.soulsand.registerBlock(new BlockWithMeta(Blocks.soul_sand));
        // also includes podzol and that weird non-grass growing dirt
        CropsNHSoilTypes.dirtGrass.registerBlock(new BlockWithMeta(Blocks.dirt), new BlockWithMeta(Blocks.grass));
        CropsNHSoilTypes.mycelium.registerBlock(new BlockWithMeta(Blocks.mycelium));
        CropsNHSoilTypes.end.registerBlock(new BlockWithMeta(Blocks.end_stone));
        CropsNHSoilTypes.netherrack.registerBlock(new BlockWithMeta(Blocks.netherrack));
        CropsNHSoilTypes.brick.registerBlock(new BlockWithMeta(Blocks.brick_block));
        CropsNHSoilTypes.gravel.registerBlock(new BlockWithMeta(Blocks.gravel));

        // modded soils
        if (ModUtils.Botania.isModLoaded()) {
            CropsNHSoilTypes.end.registerBlock(new BlockWithMeta(ModUtils.Botania.getBlock("endStoneBrick")));
        }
        if (ModUtils.Chisel.isModLoaded()) {
            CropsNHSoilTypes.dirtGrass.registerBlock(new BlockWithMeta(ModUtils.Chisel.getBlock("dirt")));
            CropsNHSoilTypes.end.registerBlock(new BlockWithMeta(ModUtils.Chisel.getBlock("end_Stone")));
            CropsNHSoilTypes.netherrack.registerBlock(new BlockWithMeta(ModUtils.Chisel.getBlock("netherrack")));
            CropsNHSoilTypes.stone.registerBlock(
                new BlockWithMeta(ModUtils.Chisel.getBlock("cobblestone")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("mossy_cobblestone")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("stone_snakestone")),
                new BlockWithMeta(ModUtils.Chisel.getBlock("stonebricksmooth")));
        }
        if (ModUtils.EtFuturumRequiem.isModLoaded()) {
            CropsNHSoilTypes.end.registerBlock(new BlockWithMeta(ModUtils.EtFuturumRequiem.getBlock("end_bricks")));
        }
        if (ModUtils.ExtraUtilities.isModLoaded()) {
            CropsNHSoilTypes.brick.registerBlock(new BlockWithMeta(ModUtils.ExtraUtilities.getBlock("color_brick")));
            CropsNHSoilTypes.stone.registerBlock(
                new BlockWithMeta(ModUtils.ExtraUtilities.getBlock("color_stonebrick")),
                new BlockWithMeta(ModUtils.ExtraUtilities.getBlock("color_stone")));
        }
        // leaving it as an is mod loaded since one of the longer term goals of CropsNH is to decouple CropsNH from
        // GT5u;
        if (ModUtils.GregTech.isModLoaded()) {
            // We don't need all 3, but all 3 are created by GT5u and GTNH be weird sometimes.
            CropsNHSoilTypes.oilSands.registerOreDict("oreOilsands", "oreNetherrackOilsands", "oreEndstoneOilsands");
        }
        if (ModUtils.MagicBees.isModLoaded()) {
            BlockWithMeta enchantedEarth = new BlockWithMeta(ModUtils.MagicBees.getBlock("magicbees.enchantedEarth"));
            SoilTramplingResistanceRegistry.instance
                .setResistance(enchantedEarth, SoilTramplingResistanceRegistry.IMMUNE);
            CropsNHSoilTypes.farmland.registerBlock(enchantedEarth);
        }
        if (ModUtils.RandomThings.isModLoaded()) {
            BlockWithMeta fertilizedDirt = new BlockWithMeta(ModUtils.RandomThings.getBlock("fertilizedDirt_tilled"));
            SoilTramplingResistanceRegistry.instance
                .setResistance(fertilizedDirt, SoilTramplingResistanceRegistry.IMMUNE);
            CropsNHSoilTypes.farmland.registerBlock(fertilizedDirt);
        }
        if (ModUtils.TinkerConstruct.isModLoaded()) {
            // TiC blocks
            BlockWithMeta graveyardSoil = new BlockWithMeta(TinkerTools.craftedSoil, 3);
            CropsNHSoilTypes.graveyard.registerBlock(graveyardSoil);

            Block craftedSoilBlock = ModUtils.TinkerConstruct.getBlock("CraftedSoil");
            BlockWithMeta greeneSlimyMud = new BlockWithMeta(craftedSoilBlock, 0);
            BlockWithMeta blueSlimyMud = new BlockWithMeta(craftedSoilBlock, 2);
            BlockWithMeta slimeDirt = new BlockWithMeta(craftedSoilBlock, 5);
            BlockWithMeta slimeGrass = new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("slime.grass"), 0);
            CropsNHSoilTypes.slimy.registerBlock(greeneSlimyMud, blueSlimyMud, slimeDirt, slimeGrass);

            CropsNHSoilTypes.netherrack.registerBlock(
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrick"), 2),
                new BlockWithMeta(ModUtils.TinkerConstruct.getBlock("decoration.multibrickfancy"), 2));
            CropsNHSoilTypes.stone.registerBlock(
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

            CropsNHSoilTypes.thaumLogs.registerBlock(
                greatwoodLog1,
                greatwoodLog2,
                greatwoodLog3,
                silverwoodLog1,
                silverwoodLog2,
                silverwoodLog3);
        }
        if (ModUtils.ThaumicBases.isModLoaded()) {
            CropsNHSoilTypes.brick.registerBlock(new BlockWithMeta(ModUtils.ThaumicBases.getBlock("oldBrick")));
            CropsNHSoilTypes.stone.registerBlock(
                new BlockWithMeta(ModUtils.ThaumicBases.getBlock("oldCobble")),
                new BlockWithMeta(ModUtils.ThaumicBases.getBlock("oldCobbleMossy")));
        }
        if (ModUtils.Ztones.isModLoaded()) {
            BlockWithMeta gardenSoil = new BlockWithMeta(ModUtils.Ztones.getBlock("cleanDirt"));
            SoilTramplingResistanceRegistry.instance.setResistance(gardenSoil, SoilTramplingResistanceRegistry.IMMUNE);
            CropsNHSoilTypes.farmland.registerBlock(gardenSoil);
        }

    }
}
