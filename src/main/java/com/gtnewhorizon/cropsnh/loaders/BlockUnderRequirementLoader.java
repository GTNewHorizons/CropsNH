package com.gtnewhorizon.cropsnh.loaders;

import net.minecraft.init.Blocks;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.farming.requirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import ganymedes01.etfuturum.ModBlocks;
import gregtech.api.GregTechAPI;
import gregtech.api.enums.Materials;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import thaumcraft.common.config.ConfigBlocks;

public class BlockUnderRequirementLoader {

    public static void postInit() {
        // spotless:off
        // Vanilla Stone Lilies
        BlockUnderRequirement.get("stone").addBlock(new BlockWithMeta(Blocks.stone), new BlockWithMeta(Blocks.cobblestone));
        BlockUnderRequirement.get("sand").addBlock(new BlockWithMeta(Blocks.sand), new BlockWithMeta(Blocks.sandstone));
        BlockUnderRequirement.get("clay").addOreDict("hardenedClay","blockStainedHardenedClay").addBlock(new BlockWithMeta(Blocks.clay), new BlockWithMeta(Blocks.hardened_clay), new BlockWithMeta(Blocks.stained_hardened_clay));
        BlockUnderRequirement.get("soulSand").addBlock(new BlockWithMeta(Blocks.soul_sand));
        BlockUnderRequirement.get("netherrack").addBlock(new BlockWithMeta(Blocks.netherrack));
        BlockUnderRequirement.get("endStone").addBlock(new BlockWithMeta(Blocks.end_stone));
        // GT Stone Lilies
        BlockUnderRequirement.get("redGranite").addOreDict("stoneGraniteRed");
        BlockUnderRequirement.get("blackGranite").addOreDict("stoneGraniteBlack");
        BlockUnderRequirement.get("marble").addOreDict("stoneMarble");
        BlockUnderRequirement.get("basalt").addOreDict("stoneBasalt");
        // Botania Stone Lilies
        BlockUnderRequirement.get("botaniaAndesite").addOreDict("stoneAndesite");
        BlockUnderRequirement.get("botaniaDiorite").addOreDict("stoneDiorite");
        BlockUnderRequirement.get("botaniaGranite").addOreDict("stoneGranite");
        // Et Futurum Stone Lilies
        if (ModUtils.EtFuturumRequiem.isModLoaded()) {
            BlockUnderRequirement.get("tuff").addBlock(new BlockWithMeta(ModBlocks.TUFF.get(), 0));
            BlockUnderRequirement.get("deepslate").addBlock(new BlockWithMeta(ModBlocks.DEEPSLATE.get()));
        }
        // vanilla ores
        BlockUnderRequirement.get("coal").addBlockAndOreDict().addMaterial(Materials.Coal);
        BlockUnderRequirement.get("iron").addBlockAndOreDict().addMaterial(Materials.Iron);
        BlockUnderRequirement.get("gold").addBlockAndOreDict().addMaterial(Materials.Gold);
        BlockUnderRequirement.get("diamond").addBlockAndOreDict().addMaterial(Materials.Diamond);
        BlockUnderRequirement.get("emerald").addBlockAndOreDict().addMaterial(Materials.Emerald);
        BlockUnderRequirement.get("redstone").addBlockAndOreDict().addMaterial(Materials.Redstone);
        BlockUnderRequirement.get("lapis").addBlockAndOreDict().addMaterial(Materials.Lapis);
        // modded ores
        BlockUnderRequirement.get("aluminium").addBlockAndOreDict("aluminium", "aluminum").addMaterial(Materials.Aluminium);
        BlockUnderRequirement.get("aluminiumBauxite").addBlockAndOreDict("aluminium", "aluminum","bauxite").addMaterial(Materials.Aluminium, Materials.Bauxite);
        BlockUnderRequirement.get("copper").addBlockAndOreDict().addMaterial(Materials.Copper);
        BlockUnderRequirement.get("chrome").addBlockAndOreDict().addMaterial(Materials.Chrome);
        BlockUnderRequirement.get("iridium").addBlockAndOreDict().addMaterial(Materials.Iridium);
        BlockUnderRequirement.get("lead").addBlockAndOreDict().addMaterial(Materials.Lead);
        BlockUnderRequirement.get("magnesium").addBlockAndOreDict().addMaterial(Materials.Magnesium);
        BlockUnderRequirement.get("manganese").addBlockAndOreDict().addMaterial(Materials.Manganese);
        BlockUnderRequirement.get("mica").addBlockAndOreDict().addMaterial(Materials.Mica)
            // Cupronickel coil
            .addBlock(new BlockWithMeta(GregTechAPI.sBlockCasings5, 0));
        BlockUnderRequirement.get("naquadah").addBlockAndOreDict().addMaterial(Materials.Naquadah);
        BlockUnderRequirement.get("netherStar").addBlockAndOreDict().addMaterial(Materials.NetherStar);
        BlockUnderRequirement.get("nickel").addBlockAndOreDict().addMaterial(Materials.Nickel);
        BlockUnderRequirement.get("osmium").addBlockAndOreDict().addMaterial(Materials.Osmium);
        BlockUnderRequirement.get("platinum").addBlockAndOreDict().addMaterial(Materials.Platinum);
        BlockUnderRequirement.get("silver").addBlockAndOreDict().addMaterial(Materials.Silver);
        BlockUnderRequirement.get("thorium").addBlockAndOreDict().addMaterial(Materials.Thorium);
        BlockUnderRequirement.get("tin").addBlockAndOreDict().addMaterial(Materials.Tin);
        BlockUnderRequirement.get("titanium").addBlockAndOreDict().addMaterial(Materials.Titanium);
        BlockUnderRequirement.get("tungsten").addBlockAndOreDict().addMaterial(Materials.Tungsten);
        BlockUnderRequirement.get("uranium").addBlockAndOreDict().addBlockAndOreDict("Uranium235", "Uranium238").addMaterial(Materials.Uranium, Materials.Uranium235);
        BlockUnderRequirement.get("zinc").addBlockAndOreDict().addMaterial(Materials.Zinc);
        // gem ores
        BlockUnderRequirement.get("garnetGem").addBlockAndOreDict().addMaterial(Materials.GarnetRed, Materials.GarnetYellow);
        BlockUnderRequirement.get("olivine").addBlockAndOreDict().addMaterial(Materials.Olivine);
        BlockUnderRequirement.get("sapphire").addBlockAndOreDict().addMaterial(Materials.Sapphire);
        // non-gt ores
        BlockUnderRequirement.get("knightmetal").addBlockAndOreDict().addMaterial(Materials.Knightmetal);
        BlockUnderRequirement.get("cobalt").addBlockAndOreDict().addMaterial(Materials.Cobalt);
        BlockUnderRequirement.get("ardite").addBlockAndOreDict().addMaterial(Materials.Ardite);
        // magic ores
        BlockUnderRequirement.get("void").addBlockAndOreDict().addMaterial(Materials.Void);
        BlockUnderRequirement.get("thaumium").addBlockAndOreDict().addMaterial(Materials.Thaumium);
        BlockUnderRequirement.get("thauminite").addOreDict("blockThauminite");
        BlockUnderRequirement.get("ichorium").addBlockAndOreDict().addMaterial(Materials.Ichorium);
        // others
        BlockUnderRequirement.get("snow").addOreDict("blockSnow").addBlock(new BlockWithMeta(net.minecraft.init.Blocks.snow));
        BlockUnderRequirement.get("skull").addOreDict("itemSkull").addBlock(new BlockWithMeta(net.minecraft.init.Blocks.skull));
        BlockUnderRequirement.get("glowstone").addOreDict("stoneGlowstone", "glowstone").addBlock(new BlockWithMeta(Blocks.glowstone));
        BlockUnderRequirement.get("blaze").addOreDict("blockBlaze");
        BlockUnderRequirement.get("quicksilver").addOreDict("blockQuicksilver");
        if (ModUtils.Thaumcraft.isModLoaded()) {
            BlockUnderRequirement.get("mixedCrystalCluster").addBlock(new BlockWithMeta(ConfigBlocks.blockCrystal, 6));
        }
        // galacticraft
        if (ModUtils.GalacticraftCore.isModLoaded()) {
            BlockUnderRequirement.get("space").addOreDict("rockSpace");
        }
        // spotless:on
    }
}
