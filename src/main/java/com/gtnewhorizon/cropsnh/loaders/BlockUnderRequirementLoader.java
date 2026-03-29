package com.gtnewhorizon.cropsnh.loaders;

import net.minecraft.init.Blocks;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.GregTechAPI;
import gregtech.api.enums.Materials;

public class BlockUnderRequirementLoader {

    public static void postInit() {
        // spotless:off
        // Vanilla Stone Lilies
        CropsNHBlockUnderTypes.stone.addBlock( new BlockWithMeta(Blocks.stone), new BlockWithMeta(Blocks.cobblestone));
        CropsNHBlockUnderTypes.sand.addBlock(new BlockWithMeta(Blocks.sand), new BlockWithMeta(Blocks.sandstone));
        CropsNHBlockUnderTypes.clay.addOreDict("hardenedClay","blockStainedHardenedClay").addBlock(new BlockWithMeta(Blocks.clay), new BlockWithMeta(Blocks.hardened_clay), new BlockWithMeta(Blocks.stained_hardened_clay));
        CropsNHBlockUnderTypes.soulSand.addBlock(new BlockWithMeta(Blocks.soul_sand));
        CropsNHBlockUnderTypes.netherrack.addBlock(new BlockWithMeta(Blocks.netherrack));
        CropsNHBlockUnderTypes.endStone.addBlock(new BlockWithMeta(Blocks.end_stone));
        // GT Stone Lilies
        CropsNHBlockUnderTypes.redGranite.addOreDict("stoneGraniteRed");
        CropsNHBlockUnderTypes.blackGranite.addOreDict("stoneGraniteBlack");
        CropsNHBlockUnderTypes.marble.addOreDict("stoneMarble");
        CropsNHBlockUnderTypes.basalt.addOreDict("stoneBasalt");
        // Botania Stone Lilies
        CropsNHBlockUnderTypes.botaniaAndesite.addOreDict("stoneAndesite");
        CropsNHBlockUnderTypes.botaniaDiorite.addOreDict("stoneDiorite");
        CropsNHBlockUnderTypes.botaniaGranite.addOreDict("stoneGranite");
        // Et Futurum Stone Lilies
        if (ModUtils.EtFuturumRequiem.isModLoaded()) {
            CropsNHBlockUnderTypes.tuff.addBlock(new BlockWithMeta(GameRegistry.findBlock(ModUtils.EtFuturumRequiem.ID, "tuff"), 0));
            CropsNHBlockUnderTypes.deepslate
                .addBlock(new BlockWithMeta(GameRegistry.findBlock(ModUtils.EtFuturumRequiem.ID, "deepslate")));
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
        CropsNHBlockUnderTypes.lead.addBlockAndOreDict().addMaterial(Materials.Lead);
        CropsNHBlockUnderTypes.magnesium.addBlockAndOreDict().addMaterial(Materials.Magnesium);
        CropsNHBlockUnderTypes.manganese.addBlockAndOreDict().addMaterial(Materials.Manganese);
        CropsNHBlockUnderTypes.mica.addBlockAndOreDict().addMaterial(Materials.Mica)
            // Cupronickel coil
            .addBlock(new BlockWithMeta(GregTechAPI.sBlockCasings5, 0));
        CropsNHBlockUnderTypes.naquadah.addBlockAndOreDict().addMaterial(Materials.Naquadah);
        CropsNHBlockUnderTypes.netherStar.addBlockAndOreDict().addMaterial(Materials.NetherStar);
        CropsNHBlockUnderTypes.nickel.addBlockAndOreDict().addMaterial(Materials.Nickel);
        CropsNHBlockUnderTypes.osmium.addBlockAndOreDict().addMaterial(Materials.Osmium);
        CropsNHBlockUnderTypes.platinum.addBlockAndOreDict().addMaterial(Materials.Platinum);
        CropsNHBlockUnderTypes.silver.addBlockAndOreDict().addMaterial(Materials.Silver);
        CropsNHBlockUnderTypes.thorium.addBlockAndOreDict().addMaterial(Materials.Thorium);
        CropsNHBlockUnderTypes.tin.addBlockAndOreDict().addMaterial(Materials.Tin);
        CropsNHBlockUnderTypes.titanium.addBlockAndOreDict().addMaterial(Materials.Titanium);
        CropsNHBlockUnderTypes.tungsten.addBlockAndOreDict().addMaterial(Materials.Tungsten);
        CropsNHBlockUnderTypes.uranium.addBlockAndOreDict().addBlockAndOreDict("Uranium235", "Uranium238").addMaterial(Materials.Uranium, Materials.Uranium235);
        CropsNHBlockUnderTypes.zinc.addBlockAndOreDict().addMaterial(Materials.Zinc);
        // gem ores
        CropsNHBlockUnderTypes.garnetGem.addBlockAndOreDict().addMaterial(Materials.GarnetRed, Materials.GarnetYellow);
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
            CropsNHBlockUnderTypes.mixedCrystalCluster.addBlock(new BlockWithMeta(GameRegistry.findBlock(ModUtils.Thaumcraft.ID, "blockCrystal"), 6));
        }
        // galacticraft
        if (ModUtils.GalacticraftCore.isModLoaded()) {
            CropsNHBlockUnderTypes.space.addOreDict("rockSpace");
        }
        // spotless:on
    }
}
