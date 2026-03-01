package com.gtnewhorizon.cropsnh.loaders;

import net.minecraft.init.Blocks;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import tconstruct.tools.TinkerTools;
import thaumcraft.common.config.ConfigBlocks;

public class SoilLoader {

    public static void postInit() {

        SoilRegistry registry = SoilRegistry.instance;

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

        registry.register("farmland", farmland);
        registry.register("sugarcane", sand, dirt, grass);
        registry.register("sand", sand);
        registry.register("mushroom", stone, cobblestone, dirt, grass, mycelium);
        registry.register("soulsand", soulSand);
        registry.register("dirt", dirt, grass);
        registry.register("mycelium", mycelium);
        registry.register("nether", soulSand, netherrack);
        registry.register("end", endstone);
        registry.register("stone", stone, cobblestone);
        registry.register("snow", snow);
        registry.register("netherrack", netherrack);
        registry.register("brick", brick);

        // thaumcraft soils
        if (ModUtils.Thaumcraft.isModLoaded()) {
            BlockWithMeta greatwoodLog1 = new BlockWithMeta(ConfigBlocks.blockMagicalLog, 0);
            BlockWithMeta greatwoodLog2 = new BlockWithMeta(ConfigBlocks.blockMagicalLog, 4);
            BlockWithMeta greatwoodLog3 = new BlockWithMeta(ConfigBlocks.blockMagicalLog, 8);
            BlockWithMeta silverwoodLog1 = new BlockWithMeta(ConfigBlocks.blockMagicalLog, 1);
            BlockWithMeta silverwoodLog2 = new BlockWithMeta(ConfigBlocks.blockMagicalLog, 5);
            BlockWithMeta silverwoodLog3 = new BlockWithMeta(ConfigBlocks.blockMagicalLog, 9);

            registry.register(
                "thaumLogs",
                greatwoodLog1,
                greatwoodLog2,
                greatwoodLog3,
                silverwoodLog1,
                silverwoodLog2,
                silverwoodLog3);
            registry.register("silverwoodLog", silverwoodLog1, silverwoodLog2, silverwoodLog3);
        }

        if (ModUtils.TinkerConstruct.isModLoaded()) {
            // TiC blocks
            BlockWithMeta graveyardSoil = new BlockWithMeta(TinkerTools.craftedSoil, 3);
            registry.register("graveyard", graveyardSoil);
        }
    }
}
