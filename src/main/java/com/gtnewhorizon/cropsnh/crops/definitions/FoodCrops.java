package com.gtnewhorizon.cropsnh.crops.definitions;

import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ADDICTIVE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BEAN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BERRY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BROWN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BUSH;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.CHICKEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.CLIMBABLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.COW;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EDIBLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.FIERY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.FISH;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.FLOWER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GREEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.LEAFY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.POTION_INGREDIENT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.PURPLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.RED;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ROOT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TENDRILLY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TREE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHEAT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHITE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.YELLOW;
import static net.minecraftforge.common.BiomeDictionary.Type.COLD;
import static net.minecraftforge.common.BiomeDictionary.Type.DRY;
import static net.minecraftforge.common.BiomeDictionary.Type.FOREST;
import static net.minecraftforge.common.BiomeDictionary.Type.HILLS;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.LUSH;
import static net.minecraftforge.common.BiomeDictionary.Type.MAGICAL;
import static net.minecraftforge.common.BiomeDictionary.Type.MOUNTAIN;
import static net.minecraftforge.common.BiomeDictionary.Type.PLAINS;
import static net.minecraftforge.common.BiomeDictionary.Type.SANDY;
import static net.minecraftforge.common.BiomeDictionary.Type.SNOWY;
import static net.minecraftforge.common.BiomeDictionary.Type.SPARSE;
import static net.minecraftforge.common.BiomeDictionary.Type.WET;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.data.CropBuilder;
import com.gtnewhorizon.cropsnh.crops.data.CropFamilies;
import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public final class FoodCrops {

    private FoodCrops() {}

    public static void register() {
        CropsNHCrops.Chilly = CropFamilies.food("chilly", 0x83090B, 0xC8462A)
            // TODO: MOVE ITEM DROP TO CROPS NH OR SOMETHING
            .drop("cropChilipepper", 100_00)
            .seeds("cropChilli", "cropChilipepper", "seedChilipepper")
            // likes warm temperatures
            .likes(HOT, LUSH)
            .tier(4)
            .growth(800)
            .pools(RED, FIERY, EDIBLE)
            .mutation("cactus", "cocoa")
            .register();
        CropsNHCrops.Coffee = CropFamilies.food("coffee", 0x3D1E00, 0x964F05)
            .drop("cropCoffee", 10_000)
            .seeds("cropCoffee")
            // likes warm temperatures, usually found in the same area as rainforests.
            .likes(HOT, LUSH, WET)
            .tier(7)
            .growth(2800)
            .seedShape(SeedShape.grains)
            .pools(BROWN, BEAN, LEAFY, ADDICTIVE, EDIBLE)
            .mutation("cocoa", "bonsaiAcacia")
            .register();
        CropsNHCrops.Cucumber = CropFamilies.food("cucumber", 0x13260D, 0x259038)
            // TODO: MOVE ITEM DROP TO CROPS NH OR SOMETHING
            .drop("cropCucumber", 100_00)
            .seeds("seedCucumber", "cropCucumber")
            // likes warm soils mostly
            .likes(HOT, LUSH)
            .tier(4)
            .growth(800)
            .pools(GREEN, TENDRILLY, EDIBLE)
            .mutation("melon", "carrot")
            .register();
        CropsNHCrops.GaiaWart = CropBuilder.crop(Names.Objects.gaiaWart, 0x0F213A, 0x2664A1)
            .drop(new ItemStack(CropsNHItems.gaiaWart, 1), 100_00)
            .seed(new ItemStack(CropsNHItems.gaiaWart, 1))
            .requirement(CropsNHSubSoilTypes.snow)
            .duplicationCatalyst(new ItemStack(Items.snowball, 1))
            // going to treat it like sugar cane
            .likes(SNOWY, COLD)
            .name("item." + Reference.MOD_ID + ":" + Names.Objects.gaiaWart + ".name")
            .tier(5)
            // half of what it used to be since it was getting 2x the growth on snow and now it requires snow under.
            .growth(1000)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.soulsand)
            .seedShape(SeedShape.spore)
            .dropChance(0.8f)
            .register();
        CropsNHCrops.Grape = CropFamilies.food("grape", 0x4A1041, 0x5D1451)
            .drop("cropGrape", 100_00)
            .seeds("seedGrape", "cropGrape")
            // likes warm soils mostly
            .likes(HOT, DRY, SPARSE)
            .tier(4)
            .growth(800)
            .pools(PURPLE, CLIMBABLE, LEAFY, TENDRILLY, EDIBLE)
            .mutation("blueberry", "blackberry")
            .register();
        CropsNHCrops.Hops = CropFamilies.food("hops", 0x6B5C0E, 0x86BB00)
            .drop(CropsNHItemList.hops.get(1), 100_00)
            .seed(CropsNHItemList.hops.get(1))
            // likes warm areas, growing most effectively around the 48th latitude
            .likes(PLAINS, LUSH)
            .tier(5)
            .growth(2400)
            .stages(1, 6)
            .pools(GREEN, WHEAT, EDIBLE)
            .mutation("hemp", "dandelion")
            .register();
        CropsNHCrops.Huckleberry = CropFamilies.food("huckleberry", 0x0E0116, 0x2A1736)
            .drop(CropsNHItemList.huckleBerry.get(1), 100_00)
            .seeds("cropHuckleberry")
            // mostly appears along the appalachia and neighbouring flats in Canada and the US
            .likes(MOUNTAIN, HILLS, PLAINS, COLD)
            .creator("bartimaeusnek")
            .tier(2)
            .growth(200)
            .stages(1, 3)
            .pools(PURPLE, BUSH, LEAFY, BERRY, EDIBLE)
            .mutation("blackberry", "grape")
            .register();
        CropsNHCrops.Lemon = CropBuilder.crop("lemon", 0xD8950C, 0xFFEF83)
            // TODO: MOVE LEMON ITEM TO CROPS NH
            .drop("cropLemon", 100_00)
            .seeds("cropLemon")
            .when(
                ModUtils.PamsHarvestCraft,
                b -> b.seed(CropsNHUtils.getModItem(ModUtils.PamsHarvestCraft, "pamlemonSapling", 1, 0)))
            // hates cold with a passion
            .likes(HOT, DRY, PLAINS)
            .tier(4)
            .growth(800)
            .soil(CropsNHSoilTypes.dirtGrass)
            .renderShape(PlantRenderShape.X)
            .pools(YELLOW, TREE, LEAFY, EDIBLE)
            .mutation("bonsaiOak", "bonsaiAcacia")
            .register();
        CropsNHCrops.Meatrose = CropFamilies.food("meatrose", 0xDD3830, 0xEF7070)
            // TODO: MOVE ITEM DROP TO CROPS NH OR SOMETHING
            .drop(new ItemStack(Items.dye, 1, 9), 60_00)
            .drop(new ItemStack(Items.chicken, 1, 0), 10_00)
            .drop(new ItemStack(Items.fish, 1, 0), 10_00)
            .drop(new ItemStack(Items.beef, 1, 0), 10_00)
            .drop(new ItemStack(Items.porkchop, 1, 0), 10_00)
            .likes(PLAINS, MAGICAL)
            .creator("VintageBeef")
            .tier(7)
            .growth(4200)
            .soil(CropsNHSoilTypes.dirtGrass)
            .seedShape(SeedShape.flower)
            .pools(RED, FLOWER, CHICKEN, COW, EDIBLE, FISH)
            .mutation("goldfish", "eggPlant", "corium")
            .register();
        CropsNHCrops.Onion = CropFamilies.food("onion", 0x4E1B3F, 0x753C64)
            // TODO: MOVE ONION ITEM TO CROPS NH
            .drop("cropOnion", 100_00)
            .seeds("seedOnion", "cropOnion")
            // Prefers siltier soils
            .likes(DRY, SANDY, SPARSE)
            .tier(4)
            .growth(800)
            .renderShape(PlantRenderShape.X)
            // allium is the genus for onions
            .pools(BROWN, ROOT, EDIBLE)
            .mutation("allium", "carrot")
            .register();
        CropsNHCrops.Strawberry = CropFamilies.food("strawberry", 0x8F0205, 0xCE0609)
            .drop("cropStrawberry", 100_00)
            .seeds("seedStrawberry", "cropStrawberry")
            // not very fussy about it's soil
            .likes(PLAINS, LUSH)
            .creator("bartimaeusnek")
            .tier(2)
            .growth(200)
            .stages(1, 3)
            .pools(RED, BERRY, BUSH, EDIBLE)
            .mutation("wheat", "raspberry")
            .register();
        CropsNHCrops.SugarBeet = CropBuilder.crop("sugarBeet", 0xB3B3B3, 0xFEFEFE)
            .drop(CropsNHItemList.sugarBeet.get(1), 100_00)
            .seed(new ItemStack(CropsNHItems.berry, 1, 1))
            .duplicationCatalyst("dustSugar", 1)
            // tends to prefer cold and wet environments like most of europe.
            // no biome has cold wet, so cold forest it is.
            .likes(COLD, WET, FOREST)
            .creator("bartimaeusnek")
            .tier(4)
            .growth(450)
            // yes it uses the same textures as wild carrots, no idk why
            .textureIcons(
                "carrots_stage_0",
                "carrots_stage_1",
                "carrots_stage_2",
                Reference.MOD_ID + ":crops/wildCarrot/3")
            .pools(WHITE, ROOT, ADDICTIVE, EDIBLE, POTION_INGREDIENT)
            .mutation("sugarCane", "allium")
            .register();
        CropsNHCrops.Tea = CropBuilder.crop("tea", 0x7C8C58, 0xD8C97D)
            .drop("cropTea", 100_00)
            .seeds("seedTea")
            // tends to prefer areas with high rains (min ~45 inches/year) and large hills to flow the cycle nutrients.
            .likes(WET, HILLS, HOT)
            .tier(4)
            .growth(2400)
            .pools(GREEN, LEAFY, EDIBLE)
            .mutation("blueberry", "bonsaiJungle")
            .register();
        CropsNHCrops.Tomato = CropFamilies.food("tomato", 0x91101F, 0xE3321F)
            .drop("cropTomato", 75_00)
            .drop(CropsNHItemList.maxTomato.get(1), 25_00)
            .seeds("seedTomato", "cropTomato")
            // Likes warm weather, and a good dose of water.
            .likes(HOT, WET)
            .tier(4)
            .growth(800)
            .pools(RED, TENDRILLY, LEAFY, EDIBLE)
            .mutation("onion", "redTulip")
            .register();
    }
}
