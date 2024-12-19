package com.gtnewhorizon.cropsnh.loaders;

import net.minecraft.init.Blocks;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.BlockUnderRequirement;

import gregtech.api.enums.Materials;
import gregtech.api.enums.Mods;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;

public class BlockUnderRequirementLoader {

    public static void init() {
        // spotless:off
        // stones
        BlockUnderRequirement.get("stone").addBlock(new BlockWithMeta(Blocks.stone), new BlockWithMeta(Blocks.cobblestone));
        BlockUnderRequirement.get("sand").addBlock(new BlockWithMeta(Blocks.sand), new BlockWithMeta(Blocks.sandstone));
        BlockUnderRequirement.get("soulSand").addBlock(new BlockWithMeta(Blocks.soul_sand));
        BlockUnderRequirement.get("netherrack").addBlock(new BlockWithMeta(Blocks.netherrack));
        BlockUnderRequirement.get("endStone").addBlock(new BlockWithMeta(Blocks.end_stone));
        BlockUnderRequirement.get("redGranite").addOreDict("stoneGraniteRed");
        BlockUnderRequirement.get("blackGranite").addOreDict("stoneGraniteBlack");
        BlockUnderRequirement.get("marble").addOreDict("stoneMarble");
        BlockUnderRequirement.get("basalt").addOreDict("stoneBasalt");
        BlockUnderRequirement.get("botaniaAndesite").addOreDict("stoneAndesite");
        BlockUnderRequirement.get("botaniaDiorite").addOreDict("stoneDiorite");
        BlockUnderRequirement.get("botaniaGranite").addOreDict("stoneGranite");
        // ores
        BlockUnderRequirement.get("iron").addBlockAndOreDict().addMaterial(Materials.Iron);
        BlockUnderRequirement.get("gold").addBlockAndOreDict().addMaterial(Materials.Gold);
        BlockUnderRequirement.get("diamond").addBlockAndOreDict().addMaterial(Materials.Diamond);
        BlockUnderRequirement.get("redstone").addBlockAndOreDict().addMaterial(Materials.Redstone);
        BlockUnderRequirement.get("lapis").addBlockAndOreDict().addMaterial(Materials.Lapis);
        BlockUnderRequirement.get("aluminium").addBlockAndOreDict().addMaterial(Materials.Aluminium);
        BlockUnderRequirement.get("tin").addBlockAndOreDict().addMaterial(Materials.Tin);
        BlockUnderRequirement.get("copper").addBlockAndOreDict().addMaterial(Materials.Copper);
        BlockUnderRequirement.get("nickel").addBlockAndOreDict().addMaterial(Materials.Nickel);
        BlockUnderRequirement.get("lead").addBlockAndOreDict().addMaterial(Materials.Lead);
        BlockUnderRequirement.get("silver").addBlockAndOreDict().addMaterial(Materials.Silver);
        BlockUnderRequirement.get("garnetGem").addBlockAndOreDict().addMaterial(Materials.GarnetRed, Materials.GarnetYellow);
        BlockUnderRequirement.get("knightmetal").addBlockAndOreDict().addMaterial(Materials.Knightmetal);
        BlockUnderRequirement.get("thaumium").addBlockAndOreDict().addMaterial(Materials.Thaumium);
        BlockUnderRequirement.get("thauminite").addOreDict("blockThauminite");
        BlockUnderRequirement.get("void").addBlockAndOreDict().addMaterial(Materials.Void);
        BlockUnderRequirement.get("cobalt").addBlockAndOreDict().addMaterial(Materials.Cobalt);
        BlockUnderRequirement.get("ardite").addBlockAndOreDict().addMaterial(Materials.Ardite);
        BlockUnderRequirement.get("ichorium").addBlockAndOreDict().addMaterial(Materials.Ichorium);
        // others
        BlockUnderRequirement.get("snow").addOreDict("blockSnow").addBlock(new BlockWithMeta(net.minecraft.init.Blocks.snow));
        BlockUnderRequirement.get("skull").addOreDict("itemSkull").addBlock(new BlockWithMeta(net.minecraft.init.Blocks.skull));
        BlockUnderRequirement.get("glowstone").addOreDict("stoneGlowstone", "glowstone").addBlock(new BlockWithMeta(Blocks.glowstone));
        // galacticraft
        if (Mods.GalacticraftCore.isModLoaded()) {
            BlockUnderRequirement.get("moon").addBlock(new BlockWithMeta(GCBlocks.blockMoon));
        }
        // spotless:on
    }
}
