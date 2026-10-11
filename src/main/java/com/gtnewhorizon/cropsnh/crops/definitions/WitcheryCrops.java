package com.gtnewhorizon.cropsnh.crops.definitions;

import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLUE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BROWN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.CLIMBABLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DANGEROUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EDIBLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EVIL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.FIERY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.FLOWER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GREEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.MAGICAL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ORANGE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.POISONOUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.POTION_INGREDIENT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.PURPLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.RED;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ROOT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TENDRILLY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WATERY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHITE;
import static net.minecraftforge.common.BiomeDictionary.Type.COLD;
import static net.minecraftforge.common.BiomeDictionary.Type.DRY;
import static net.minecraftforge.common.BiomeDictionary.Type.FOREST;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.LUSH;
import static net.minecraftforge.common.BiomeDictionary.Type.OCEAN;
import static net.minecraftforge.common.BiomeDictionary.Type.PLAINS;
import static net.minecraftforge.common.BiomeDictionary.Type.RIVER;
import static net.minecraftforge.common.BiomeDictionary.Type.SANDY;
import static net.minecraftforge.common.BiomeDictionary.Type.SNOWY;
import static net.minecraftforge.common.BiomeDictionary.Type.SPOOKY;
import static net.minecraftforge.common.BiomeDictionary.Type.SWAMP;
import static net.minecraftforge.common.BiomeDictionary.Type.WET;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.data.CropBuilder;
import com.gtnewhorizon.cropsnh.crops.data.CropFamilies;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;

public final class WitcheryCrops {

    private WitcheryCrops() {}

    public static void register() {
        if (!ModUtils.Witchery.isModLoaded()) return;
        CropsNHCrops.Belladonna = CropBuilder.crop("belladonna", 0x000000, 0x947999)
            .seeds("seedBelladonna")
            .drop("itemBelladonna", 100_00)
            .likes(WET, SPOOKY)
            .creator("bartimaeusnek")
            .name("tile.witchery:belladonna.name")
            .tier(4)
            .growth(1100)
            .textures("witchery:belladonna_stage_", 0, 4)
            .seedShape(SeedShape.magic)
            .pools(PURPLE, FLOWER, POISONOUS, POTION_INGREDIENT)
            .mutation("purpleTulip", "huckleberry")
            .register();
        CropsNHCrops.EmberMoss = CropBuilder.crop("emberMoss", 0x9C4D2C, 0xE06F3F)
            .seeds("cropEmberMoss")
            .drop("cropEmberMoss", 100_00)
            .likes(HOT, DRY)
            .creator("bartimaeusnek")
            .name("tile.witchery:embermoss.name")
            .tier(7)
            .growth(1100)
            .seedShape(SeedShape.magic)
            .renderShape(PlantRenderShape.X)
            .pools(RED, CLIMBABLE, DANGEROUS, FIERY, TENDRILLY, POTION_INGREDIENT)
            .when(ModUtils.BiomesOPlenty, b -> b.mutation("redTulip", "moss"))
            .register();
        CropsNHCrops.Garlic = CropFamilies.food("garlic", 0xA88F7B, 0xF3E7DC)
            .seeds("cropGarlic")
            .drop("cropGarlic", 100_00)
            .likes(PLAINS, DRY)
            .creator("bartimaeusnek")
            .name("tile.witchery:garlicplant.name")
            .tier(3)
            .growth(450)
            .textures("witchery:garlic_stage_", 0, 5)
            .seedShape(SeedShape.magic)
            .pools(WHITE, ROOT, EDIBLE, POTION_INGREDIENT)
            .mutation("onion", "allium")
            .register();
        CropsNHCrops.GlintWeed = CropBuilder.crop("glintWeed", 0xB3890B, 0xB3890B)
            .seeds("cropGlintWeed")
            .drop("cropGlintWeed", 100_00)
            .likes(BiomeDictionary.Type.MAGICAL, PLAINS, SANDY)
            .creator("bartimaeusnek")
            .name("tile.witchery:glintweed.name")
            .tier(4)
            .growth(1100)
            .seedShape(SeedShape.magic)
            .renderShape(PlantRenderShape.X)
            .pools(ORANGE, FLOWER, MAGICAL)
            .mutation("vine", "emberMoss")
            .register();
        CropsNHCrops.Mandrake = CropBuilder.crop("mandrake", 0x755239, 0xBD9A61)
            .seeds("seedMandrake")
            .drop("itemMandrake", 100_00)
            .likes(BiomeDictionary.Type.MAGICAL, SPOOKY)
            .creator("bartimaeusnek")
            .name("tile.witchery:mandrake.name")
            .tier(4)
            .growth(1100)
            .textures("witchery:mandrake_stage_", 0, 4)
            .seedShape(SeedShape.magic)
            // the default drop chance of a tier 4 crop, times 0.5f
            .dropChance((float) Math.pow(0.95, 4) * 0.5f)
            .pools(BROWN, FLOWER, DANGEROUS, EVIL, MAGICAL, POTION_INGREDIENT)
            .mutation("carrot", "belladonna")
            .register();
        CropsNHCrops.Snowbell = CropBuilder.crop("snowbell", 0x33A9FF, 0xA5D9FF)
            .seeds("seedSnowbell")
            .drop("itemSnowbell", 89_00)
            .drop(new ItemStack(Items.snowball, 1, 0), 10_00)
            .drop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Cryolite, 1), 2_50)
            .likes(SNOWY, COLD)
            .creator("bartimaeusnek")
            .name("tile.witchery:snowbell.name")
            .tier(4)
            .growth(1100)
            .textures("witchery:snowbell_stage_", 0, 4)
            .seedShape(SeedShape.magic)
            .renderShape(PlantRenderShape.X)
            .pools(WHITE, FLOWER, POISONOUS, POTION_INGREDIENT)
            .mutation("belladonna", "glintWeed")
            .register();
        CropsNHCrops.SpanishMoss = CropBuilder.crop("spanishMoss", 0x3F5227, 0xC1CEBB)
            .seeds("cropSpanishMoss")
            .drop("cropSpanishMoss", 100_00)
            .likes(LUSH, WET, FOREST, SWAMP)
            .creator("bartimaeusnek")
            .name("tile.witchery:spanishmoss.name")
            .tier(7)
            .growth(675)
            .seedShape(SeedShape.magic)
            .pools(GREEN, CLIMBABLE, MAGICAL)
            .mutation("moss", "emberMoss")
            .register();
        CropsNHCrops.WaterArtichoke = CropFamilies.food("waterArtichoke", 0x6F644E, 0x968C74)
            .seeds("seedWaterArtichoke")
            .drop("cropWaterArtichoke", 100_00)
            .likes(RIVER, WET, OCEAN)
            .creator("bartimaeusnek")
            .name("tile.witchery:artichoke.name")
            .tier(4)
            .growth(1100)
            .textures("witchery:artichoke_stage_", 0, 4)
            .seedShape(SeedShape.magic)
            .renderShape(PlantRenderShape.X)
            .pools(BLUE, FLOWER, WATERY, EDIBLE, POTION_INGREDIENT)
            .mutation("waterLily", "hops")
            .register();
        CropsNHCrops.Wolfsbane = CropBuilder.crop("wolfsbane", 0x76716E, 0xDAD0CE)
            .seeds("seedWolfsBane")
            .drop("itemWolfsBane", 100_00)
            .likes(BiomeDictionary.Type.MAGICAL, FOREST, SPOOKY)
            .creator("bartimaeusnek")
            .name("tile.witchery:wolfsbane.name")
            .tier(4)
            .growth(1100)
            .textures("witchery:wolfsbane_stage_", 0, 7)
            .seedShape(SeedShape.magic)
            .renderShape(PlantRenderShape.X)
            .pools(PURPLE, FLOWER, POISONOUS, POTION_INGREDIENT)
            .mutation("waterArtichoke", "belladonna")
            .register();
    }
}
