package com.gtnewhorizon.cropsnh.loaders;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class OreDictLoader {

    public static void preInit() {
        // CropsNH items
        register(CropsNHItemList.goldfish.get(1), "listAllfishraw");
        register(CropsNHItemList.huckleBerry.get(1), "cropHuckleberry", "listAllberry", "listAllfruit");
        register(CropsNHItemList.sugarBeet.get(1), "cropSugarbeet", "listAllveggie", "listAllrootveggie");
    }

    public static void init() {
        registerVanilla();
        if (ModUtils.Witchery.isModLoaded()) registerWitchery();
        if (ModUtils.TinkerConstruct.isModLoaded()) registerTinkersConstruct();
        if (ModUtils.ThaumicBases.isModLoaded()) registerThaumicBases();
        if (ModUtils.Thaumcraft.isModLoaded()) registerThaumcraft();
        if (ModUtils.Natura.isModLoaded()) registerNatura();
        if (ModUtils.BiomesOPlenty.isModLoaded()) registerBiomesOPlenty();

        if (!OreDictionary.getOres("cropBlackberry")
            .isEmpty())
            for (int i = 0; i < OreDictionary.getOres("cropBlackberry")
                .size(); i++)
                if (!OreDictionary.getOres("listAllberry")
                    .contains(
                        OreDictionary.getOres("cropBlackberry")
                            .get(i)))
                    OreDictionary.registerOre(
                        "listAllberry",
                        OreDictionary.getOres("cropBlackberry")
                            .get(i));

        if (!OreDictionary.getOres("cropBlueberry")
            .isEmpty())
            for (int i = 0; i < OreDictionary.getOres("cropBlueberry")
                .size(); i++)
                if (!OreDictionary.getOres("listAllberry")
                    .contains(
                        OreDictionary.getOres("cropBlueberry")
                            .get(i)))
                    OreDictionary.registerOre(
                        "listAllberry",
                        OreDictionary.getOres("cropBlueberry")
                            .get(i));

        if (!OreDictionary.getOres("cropGooseberry")
            .isEmpty())
            for (int i = 0; i < OreDictionary.getOres("cropGooseberry")
                .size(); i++)
                if (!OreDictionary.getOres("listAllberry")
                    .contains(
                        OreDictionary.getOres("cropGooseberry")
                            .get(i)))
                    OreDictionary.registerOre(
                        "listAllberry",
                        OreDictionary.getOres("cropGooseberry")
                            .get(i));

        if (!OreDictionary.getOres("cropRaspberry")
            .isEmpty())
            for (int i = 0; i < OreDictionary.getOres("cropRaspberry")
                .size(); i++)
                if (!OreDictionary.getOres("listAllberry")
                    .contains(
                        OreDictionary.getOres("cropRaspberry")
                            .get(i)))
                    OreDictionary.registerOre(
                        "listAllberry",
                        OreDictionary.getOres("cropRaspberry")
                            .get(i));

        if (!OreDictionary.getOres("cropStrawberry")
            .isEmpty())
            for (int i = 0; i < OreDictionary.getOres("cropStrawberry")
                .size(); i++)
                if (!OreDictionary.getOres("listAllberry")
                    .contains(
                        OreDictionary.getOres("cropStrawberry")
                            .get(i)))
                    OreDictionary.registerOre(
                        "listAllberry",
                        OreDictionary.getOres("cropStrawberry")
                            .get(i));
    }

    public static void registerVanilla() {
        register(new ItemStack(Blocks.vine, 1, 0), "cropVine");
        register(new ItemStack(Blocks.deadbush, 1, 0), "cropGrass");
        register(new ItemStack(Blocks.tallgrass, 1, 1), "cropGrass");
        register(new ItemStack(Blocks.tallgrass, 1, 2), "cropGrass");
        register(new ItemStack(Blocks.double_plant, 1, 2), "cropGrass");
        register(new ItemStack(Blocks.double_plant, 1, 3), "cropGrass");
        register(new ItemStack(Blocks.cactus, 1, 0), "cropCacti");
        register(new ItemStack(Blocks.cactus, 1, 0), "cropCactus");
    }

    private static void registerWitchery() {
        register(
            CropsNHUtils.getModItem(ModUtils.Witchery, "seedsartichoke", 1, 0),
            "listAllseed",
            "seedWaterArtichoke");
        register(CropsNHUtils.getModItem(ModUtils.Witchery, "seedswolfsbane", 1, 0), "listAllseed", "seedWolfsBane");
        register(CropsNHUtils.getModItem(ModUtils.Witchery, "seedsmandrake", 1, 0), "listAllseed", "seedMandrake");
        register(CropsNHUtils.getModItem(ModUtils.Witchery, "seedssnowbell", 1, 0), "listAllseed", "seedSnowbell");
        register(CropsNHUtils.getModItem(ModUtils.Witchery, "seedsbelladonna", 1, 0), "listAllseed", "seedBelladonna");
        register(CropsNHUtils.getModItem(ModUtils.Witchery, "garlic", 1, 0), "listAllherb");
        register(CropsNHUtils.getModItem(ModUtils.Witchery, "ingredient", 1, 69), "listAllveggie", "cropArtichoke");
        register(CropsNHUtils.getModItem(ModUtils.Witchery, "ingredient", 1, 156), "itemWolfsBane");
        register(CropsNHUtils.getModItem(ModUtils.Witchery, "ingredient", 1, 22), "itemMandrake");
        register(CropsNHUtils.getModItem(ModUtils.Witchery, "ingredient", 1, 78), "itemSnowbell");
        register(CropsNHUtils.getModItem(ModUtils.Witchery, "ingredient", 1, 21), "itemBelladonna");
        register(CropsNHUtils.getModItem(ModUtils.Witchery, "glintweed", 1, 0), "cropGlintWeed");
        register(CropsNHUtils.getModItem(ModUtils.Witchery, "spanishmoss", 1, 0), "cropSpanishMoss");
        register(CropsNHUtils.getModItem(ModUtils.Witchery, "embermoss", 1, 0), "cropEmberMoss");

    }

    private static void registerTinkersConstruct() {
        register(CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "slime.grass", 1, 0), "cropGrass");
        register(CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "slime.grass.tall", 1, 0), "cropGrass");
    }

    private static void registerThaumicBases() {
        register(CropsNHUtils.getModItem(ModUtils.ThaumicBases, "resource", 1, 0), "nuggetThauminite");
    }

    private static void registerThaumcraft() {
        register(CropsNHUtils.getModItem(ModUtils.Thaumcraft, "blockCustomPlant", 1, 2), "cropShimmerleaf");
        register(CropsNHUtils.getModItem(ModUtils.Thaumcraft, "blockCustomPlant", 1, 3), "cropCinderpearl");
        register(CropsNHUtils.getModItem(ModUtils.Thaumcraft, "blockTaintFibres", 1, 1), "cropGrass");
    }

    private static void registerNatura() {
        register(CropsNHUtils.getModItem(ModUtils.Natura, "saguaro.fruit", 1, 0), "listAllberry", "cropSaguaroBerry");
        register(CropsNHUtils.getModItem(ModUtils.Natura, "berry", 1, 3), "cropGooseberry");
        register(CropsNHUtils.getModItem(ModUtils.Natura, "Saguaro", 1, 0), "cropCacti", "cropSaguaro");
        register(CropsNHUtils.getModItem(ModUtils.Natura, "Thornvines", 1, 0), "cropVine", "cropThornVines");
    }

    private static void registerBiomesOPlenty() {
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "plants", 1, 0), "cropGrass");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "plants", 1, 1), "cropGrass");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "plants", 1, 2), "cropGrass");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "plants", 1, 3), "cropGrass");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "plants", 1, 12), "cropCacti", "cropSmallCactus");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "foliage", 1, 1), "cropGrass");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "foliage", 1, 2), "cropGrass");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "foliage", 1, 3), "cropFlax");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "foliage", 1, 5), "cropGrass");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "foliage", 1, 7), "cropGrass");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "foliage", 1, 9), "cropGrass");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "foliage", 1, 10), "cropGrass");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "foliage", 1, 11), "cropGrass");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "foliage", 1, 12), "cropGrass");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "foliage", 1, 13), "cropGrass");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "flowerVine", 1, 0), "cropVine", "cropFloweringVines");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "ivy", 1, 0), "cropVine", "cropIvy");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "food", 1, 0), "listAllberry", "cropBerry");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "flowers", 1, 13), "cropEyebulb");
        register(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "flowers", 1, 14), "cropEyebulb");
    }

    public static void register(ItemStack stack, String... ores) {
        for (String ore : ores) {
            OreDictionary.registerOre(ore, stack.copy());
        }
    }
}
