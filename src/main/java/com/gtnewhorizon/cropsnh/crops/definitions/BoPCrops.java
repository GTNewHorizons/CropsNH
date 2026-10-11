package com.gtnewhorizon.cropsnh.crops.definitions;

import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BERRY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLUE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BUSH;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.CLIMBABLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DANGEROUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EDIBLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EMISSIVE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EVIL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.FLOWER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GREEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.LEAFY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.MUSHROOM;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.NETHER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.POISONOUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.POTION_INGREDIENT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.PURPLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.RED;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ROOT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SHINY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.STEM;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TENDRILLY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TWILIGHT_FOREST;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WATERY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHITE;
import static net.minecraftforge.common.BiomeDictionary.Type.COLD;
import static net.minecraftforge.common.BiomeDictionary.Type.DENSE;
import static net.minecraftforge.common.BiomeDictionary.Type.FOREST;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.JUNGLE;
import static net.minecraftforge.common.BiomeDictionary.Type.LUSH;
import static net.minecraftforge.common.BiomeDictionary.Type.MAGICAL;
import static net.minecraftforge.common.BiomeDictionary.Type.OCEAN;
import static net.minecraftforge.common.BiomeDictionary.Type.PLAINS;
import static net.minecraftforge.common.BiomeDictionary.Type.RIVER;
import static net.minecraftforge.common.BiomeDictionary.Type.SANDY;
import static net.minecraftforge.common.BiomeDictionary.Type.SPOOKY;
import static net.minecraftforge.common.BiomeDictionary.Type.SWAMP;
import static net.minecraftforge.common.BiomeDictionary.Type.WET;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.data.CropBuilder;
import com.gtnewhorizon.cropsnh.crops.data.CropFamilies;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public final class BoPCrops {

    private BoPCrops() {}

    public static void register() {
        CropsNHCrops.Moss = CropBuilder.crop("moss", 0x256319, 0x3A9126)
            .when(
                ModUtils.TwilightForest,
                b -> b.drop(CropsNHUtils.getModItem(ModUtils.TwilightForest, "tile.TFPlant", 1, 3), 5_00)
                    .seed(CropsNHUtils.getModItem(ModUtils.TwilightForest, "tile.TFPlant", 1, 3)))
            .when(
                ModUtils.BiomesOPlenty,
                b -> b.drop(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "moss", 1, 0), 30_00)
                    .drop(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "treeMoss", 1, 0), 65_00)
                    .seed(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "moss", 1, 0))
                    .seed(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "treeMoss", 1, 0)))
            // bayou, temperate rainforest
            .likes(WET, SWAMP, LUSH)
            .creator("bartimaeusnek")
            .name("tile.moss.name")
            .tier(4)
            .growth(450)
            // a bit of duplicated code but it works out since moss doesn't care what mod is loaded to load.
            .pools(GREEN, TWILIGHT_FOREST, CLIMBABLE, TENDRILLY)
            .mutation("ivy", "floweringVine")
            .register();
        if (!ModUtils.BiomesOPlenty.isModLoaded()) return;
        CropsNHCrops.Bamboo = CropBuilder.crop("bamboo", 0x83A81A, 0xC7DB72)
            .drop(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "bamboo", 2, 0), 100_00)
            .seed(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "saplings", 1, 2))
            // bamboo forest
            .likes(DENSE, JUNGLE, LUSH, FOREST)
            .creator("Minepolz320")
            .tier(2)
            .growth(250)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.dirtGrass)
            .seedShape(SeedShape.bonsai)
            .entityDamage(1.0F)
            .pools(GREEN, STEM, DANGEROUS, LEAFY)
            .mutation("bonsaiJungle", "vine")
            .register();
        CropsNHCrops.BoPBerry = CropFamilies.food("bopBerry", 0xB33636, 0xFF4C4C)
            .drop(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "food", 3, 0), 100_00)
            .seed(CropsNHUtils.copyStackWithSize(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "food", 3, 0), 1))
            .likes(FOREST, DENSE)
            .creator("bartimaeusnek")
            .tier(2)
            .growth(200)
            .stages(1, 3)
            .pools(RED, BERRY, BUSH, EDIBLE)
            .mutation("poppy", "blackberry")
            .register();
        CropsNHCrops.Eyebulb = CropBuilder.crop("eyebulb", 0x552323, 0x875D5D)
            .drop(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "flowers", 1, 13), 100_00)
            .seed(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "flowers", 1, 13))
            .likes(BiomeDictionary.Type.NETHER, SPOOKY)
            .creator("bartimaeusnek")
            .tier(1)
            .growth(450)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.netherrack)
            .seedShape(SeedShape.flower)
            .pools(RED, NETHER, EVIL, TENDRILLY)
            .mutation("netherStoneLily", "redTulip")
            .register();
        CropsNHCrops.FloweringVine = CropBuilder.crop("floweringVine", 0x2F7D4C, 0xC6C6C6)
            .drop(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "flowerVine", 2, 0), 100_00)
            .seed(
                CropsNHUtils.copyStackWithSize(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "flowerVine", 2, 0), 1))
            .likes(JUNGLE, DENSE)
            .creator("bartimaeusnek")
            .tier(3)
            .growth(675)
            .stages(1, 3)
            .seedShape(SeedShape.flower)
            .pools(GREEN, FLOWER, TENDRILLY, CLIMBABLE)
            .mutation("oxeyeDaisy", "ivy")
            .register();
        CropsNHCrops.Glowflower = CropBuilder.crop("glowflower", 0x004D4C, 0x12A4A2)
            .drop(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "flowers", 2, 3), 100_00)
            .seed(CropsNHUtils.copyStackWithSize(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "flowers", 2, 3), 1))
            .duplicationCatalyst("dustGlowstone", 1)
            // mystic grove tags
            .likes(WET, LUSH, MAGICAL, FOREST)
            .creator("bartimaeusnek")
            .tier(3)
            .growth(2000)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.dirtGrass)
            .seedShape(SeedShape.flower)
            .pools(BLUE, NETHER, EMISSIVE, SHINY, POTION_INGREDIENT)
            .mutation("netherStoneLily", "dandelion")
            .register();
        CropsNHCrops.GlowingCoral = CropBuilder.crop("glowingCoral", 0x7832DC, 0xD6A1FF)
            .drop(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "coral1", 2, 15), 100_00)
            .seed(CropsNHUtils.copyStackWithSize(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "coral1", 2, 15), 1))
            .subSoil(CropsNHSubSoilTypes.glowstone)
            .duplicationCatalyst("dustGlowstone", 1)
            // coral reef tags
            .likes(OCEAN, RIVER)
            .creator("bartimaeusnek")
            .tier(5)
            .growth(450)
            .seedShape(SeedShape.magic)
            .pools(PURPLE, WATERY, EMISSIVE, SHINY, POTION_INGREDIENT)
            .mutation("glowflower", "waterLily")
            .register();
        CropsNHCrops.Glowshroom = CropBuilder.crop("glowshroom", 0x45AD32, 0x63FA48)
            .drop(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "mushrooms", 1, 3), 100_00)
            .seed(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "mushrooms", 1, 3))
            .duplicationCatalyst("dustGlowstone", 1)
            // part of mushroom forest + nether
            .likes(BiomeDictionary.Type.NETHER, MAGICAL, BiomeDictionary.Type.MUSHROOM)
            .creator("bartimaeusnek")
            .tier(3)
            .growth(600)
            .stages(1, 5)
            .soil(CropsNHSoilTypes.netherMushroom)
            .seedShape(SeedShape.spore)
            .renderShape(PlantRenderShape.X)
            .pools(GREEN, NETHER, MUSHROOM, EMISSIVE, POTION_INGREDIENT)
            .mutation("blueGlowshroom", "greenGlowshroom", "purpleGlowshroom")
            .register();
        CropsNHCrops.Ivy = CropBuilder.crop("ivy", 0x1B4509, 0x338011)
            .drop(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "ivy", 2, 0), 100_00)
            .seed(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "ivy", 2, 0))
            // under-garden (nether)
            .likes(BiomeDictionary.Type.NETHER, JUNGLE, MAGICAL, DENSE)
            .creator("bartimaeusnek")
            .tier(2)
            .growth(450)
            .seedShape(SeedShape.flower)
            .pools(GREEN, FLOWER, TENDRILLY, DANGEROUS, POISONOUS, CLIMBABLE)
            .mutation("vine", "bonsaiSpruce")
            .register();
        CropsNHCrops.Turnip = CropFamilies.food("turnip", 0x321F1C, 0x6C3E32)
            .drop("cropTurnip", 100_00)
            .seeds("seedTurnip", "cropTurnip")
            // prefers silty soils, harder to grow in summer
            .likes(PLAINS, WET, COLD)
            .creator("bartimaeusnek")
            .tier(2)
            .growth(450)
            .textureIcons("carrots_stage_0", "carrots_stage_1", "carrots_stage_2", "cropsnh:crops/turnip/3")
            .pools(PURPLE, ROOT, EDIBLE)
            .mutation("potato", "bopBerry")
            .register();
        CropsNHCrops.WildCarrot = CropFamilies.food("wildCarrot", 0x96927E, 0xD9D2B7)
            .drop(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "food", 1, 2), 100_00)
            .seed(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "food", 1, 2))
            // prefers sandier soils
            .likes(PLAINS, HOT, SANDY)
            .creator("bartimaeusnek")
            .tier(2)
            .growth(450)
            .textureIcons("carrots_stage_0", "carrots_stage_1", "carrots_stage_2", "cropsnh:crops/wildCarrot/3")
            .pools(WHITE, ROOT, EDIBLE)
            .mutation("carrot", "bopBerry")
            .register();
    }
}
