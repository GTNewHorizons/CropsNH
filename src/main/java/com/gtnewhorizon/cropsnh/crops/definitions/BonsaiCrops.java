package com.gtnewhorizon.cropsnh.crops.definitions;

import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BROWN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DARK;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EDIBLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.LEAFY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ORANGE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.STICKY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TREE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHITE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WOODEN;
import static net.minecraftforge.common.BiomeDictionary.Type.CONIFEROUS;
import static net.minecraftforge.common.BiomeDictionary.Type.DENSE;
import static net.minecraftforge.common.BiomeDictionary.Type.FOREST;
import static net.minecraftforge.common.BiomeDictionary.Type.HILLS;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.JUNGLE;
import static net.minecraftforge.common.BiomeDictionary.Type.MOUNTAIN;
import static net.minecraftforge.common.BiomeDictionary.Type.PLAINS;
import static net.minecraftforge.common.BiomeDictionary.Type.SAVANNA;
import static net.minecraftforge.common.BiomeDictionary.Type.SPARSE;
import static net.minecraftforge.common.BiomeDictionary.Type.SPOOKY;
import static net.minecraftforge.common.BiomeDictionary.Type.SWAMP;
import static net.minecraftforge.common.BiomeDictionary.Type.WET;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.crops.data.CropFamilies;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.enums.ItemList;

public final class BonsaiCrops {

    private BonsaiCrops() {}

    public static void register() {
        CropsNHCrops.BonsaiOak = CropFamilies
            .bonsai(
                "bonsaiOak",
                0x7F6139,
                0x57AD3F,
                "Notch",
                1,
                new ItemStack(Blocks.sapling, 1, 0),
                new ItemStack(Blocks.log, 1, 0))
            .drop(new ItemStack(Items.apple, 2), 500)
            .likes(PLAINS, FOREST, HILLS)
            .pools(BROWN, TREE, LEAFY, WOODEN, EDIBLE)
            .mutation("wheat", "brownMushroom")
            .register();
        CropsNHCrops.BonsaiSpruce = CropFamilies
            .bonsai(
                "bonsaiSpruce",
                0x50361A,
                0x395A39,
                "Notch",
                1,
                new ItemStack(Blocks.sapling, 1, 1),
                new ItemStack(Blocks.log, 1, 1))
            .likes(FOREST, CONIFEROUS, MOUNTAIN)
            .pools(BROWN, TREE, WOODEN)
            .mutation("bonsaiOak", "pumpkin")
            .register();
        CropsNHCrops.BonsaiBirch = CropFamilies
            .bonsai(
                "bonsaiBirch",
                0xCFE3BA,
                0x648D38,
                "Notch",
                1,
                new ItemStack(Blocks.sapling, 1, 2),
                new ItemStack(Blocks.log, 1, 2))
            .likes(FOREST, HILLS)
            .pools(WHITE, TREE, LEAFY, WOODEN)
            .mutation("bonsaiOak", "sugarCane")
            .register();
        CropsNHCrops.BonsaiJungle = CropFamilies
            .bonsai(
                "bonsaiJungle",
                0x393D0D,
                0x378020,
                "Notch",
                1,
                new ItemStack(Blocks.sapling, 1, 3),
                new ItemStack(Blocks.log, 1, 3))
            .likes(HOT, JUNGLE, WET, DENSE)
            .pools(BROWN, TREE, LEAFY, WOODEN)
            .mutation("bonsaiOak", "vine")
            .register();
        CropsNHCrops.BonsaiAcacia = CropFamilies
            .bonsai(
                "bonsaiAcacia",
                0x795A0D,
                0x71881B,
                "Notch",
                1,
                new ItemStack(Blocks.sapling, 1, 4),
                new ItemStack(Blocks.log2, 1, 0))
            .likes(HOT, SPARSE, SAVANNA, PLAINS)
            .pools(ORANGE, TREE, LEAFY, WOODEN)
            .mutation("bonsaiOak", "cactus")
            .register();
        CropsNHCrops.BonsaiDarkOak = CropFamilies
            .bonsai(
                "bonsaiDarkOak",
                0x684C29,
                0x459633,
                "Notch",
                1,
                new ItemStack(Blocks.sapling, 1, 5),
                new ItemStack(Blocks.log2, 1, 1))
            .drop(new ItemStack(Items.apple, 2), 500)
            // roofed/dark forest
            .likes(DENSE, SPOOKY, FOREST)
            .pools(BROWN, TREE, LEAFY, DARK, WOODEN, EDIBLE)
            .mutation("bonsaiSpruce", "bonsaiOak")
            .register();
        CropsNHCrops.BonsaiRubber = CropFamilies
            .bonsai(
                "bonsaiRubber",
                0x50361A,
                0x6C815D,
                "Alblaka",
                1,
                CropsNHUtils.getModItem(ModUtils.IndustrialCraft2, "blockRubSapling", 1, 0),
                CropsNHUtils.getModItem(ModUtils.IndustrialCraft2, "blockRubWood", 1, 0))
            .drop(ItemList.IC2_Resin.get(2), 5_00)
            .likes(CONIFEROUS, FOREST)
            .pools(BROWN, TREE, WOODEN, STICKY)
            .mutation("bonsaiJungle", "bonsaiSpruce")
            .register();
        if (ModUtils.TinkerConstruct.isModLoaded()) {
            CropsNHCrops.BonsaiSlimy = CropFamilies
                .bonsai(
                    "bonsaiSlimy",
                    0x27C74D,
                    0x96EEED,
                    "C0bra5",
                    1,
                    CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "slime.sapling", 1, 0),
                    CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "slime.gel", 1, 1),
                    4)
                .soil(CropsNHSoilTypes.slimyDirt)
                .duplicationCatalyst("slimeball", 1)
                .likes(WET, SWAMP)
                .mutation(
                    m -> m.parents("slimeplant", "bonsaiJungle", "bonsaiRubber")
                        .machineOnly())
                .register();
        }
    }
}
