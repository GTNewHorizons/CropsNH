package com.gtnewhorizon.cropsnh.loaders;

import net.minecraft.init.Blocks;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public class SoilLoader {

    public static void init() {

        SoilRegistry registry = SoilRegistry.instance;
        BlockWithMeta farmland = new BlockWithMeta(Blocks.farmland);
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

        // register vanilla soils
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
    }
}
