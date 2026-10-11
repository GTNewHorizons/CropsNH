package com.gtnewhorizon.cropsnh.crops.definitions;

import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ADDICTIVE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BERRY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLACK;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLUE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BROWN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BUSH;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.CACTUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.CLIMBABLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.COTTON;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DANGEROUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EDIBLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EMISSIVE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GRAY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GREEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.HEALING;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.MUSHROOM;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.NETHER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.POISONOUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.POTION_INGREDIENT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.PURPLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.RED;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SILK;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TENDRILLY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHEAT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHITE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.YELLOW;
import static net.minecraftforge.common.BiomeDictionary.Type.COLD;
import static net.minecraftforge.common.BiomeDictionary.Type.DENSE;
import static net.minecraftforge.common.BiomeDictionary.Type.DRY;
import static net.minecraftforge.common.BiomeDictionary.Type.FOREST;
import static net.minecraftforge.common.BiomeDictionary.Type.HILLS;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.LUSH;
import static net.minecraftforge.common.BiomeDictionary.Type.PLAINS;
import static net.minecraftforge.common.BiomeDictionary.Type.SANDY;
import static net.minecraftforge.common.BiomeDictionary.Type.WET;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.data.CropBuilder;
import com.gtnewhorizon.cropsnh.crops.data.CropFamilies;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public final class NaturaCrops {

    private NaturaCrops() {}

    public static void register() {
        if (!ModUtils.Natura.isModLoaded()) return;
        CropsNHCrops.Blightberry = CropBuilder.crop("blightberry", 0x3BC956, 0xA3F2A0)
            .drop(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 2, 0), 100_00)
            .seed(CropsNHUtils.copyStackWithSize(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 2, 0), 1))
            .likes(BiomeDictionary.Type.NETHER, DRY)
            .creator("bartimaeusnek")
            .tier(4)
            .growth(300)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.netherrack)
            // give regeneration
            .pools(GREEN, NETHER, BERRY, BUSH, DANGEROUS, POISONOUS, ADDICTIVE, EDIBLE, HEALING, POTION_INGREDIENT)
            .mutation("maloberry", "raspberry")
            .register();
        CropsNHCrops.BlueGlowshroom = CropFamilies.naturaGlowshroom("blueGlowshroom", 0x2A3DC8, 0x4759E1, 2)
            .pools(BLUE, NETHER, MUSHROOM, EMISSIVE, EDIBLE, POTION_INGREDIENT)
            .mutation("blueOrchid", "glowflower")
            .register();
        CropsNHCrops.Duskberry = CropBuilder.crop("duskberry", 0x595959, 0xB4B4B4)
            .drop(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 2, 1), 100_00)
            .seed(CropsNHUtils.copyStackWithSize(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 2, 1), 1))
            .likes(BiomeDictionary.Type.NETHER, HOT)
            .creator("bartimaeusnek")
            .tier(4)
            .growth(300)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.netherrack)
            // gives night vision
            .pools(GRAY, NETHER, BERRY, BUSH, DANGEROUS, POISONOUS, ADDICTIVE, EDIBLE, POTION_INGREDIENT)
            .mutation("inkbloom", "blackberry")
            .register();
        CropsNHCrops.GreenGlowshroom = CropFamilies.naturaGlowshroom("greenGlowshroom", 0x7CFF1B, 0xC6FF50, 0)
            .pools(GREEN, NETHER, MUSHROOM, EMISSIVE, EDIBLE, POTION_INGREDIENT)
            .mutation("cactus", "glowflower")
            .register();
        CropsNHCrops.PurpleGlowshroom = CropFamilies.naturaGlowshroom("purpleGlowshroom", 0x511FB6, 0x8B2CD2, 1)
            .pools(PURPLE, NETHER, MUSHROOM, EMISSIVE, EDIBLE, POTION_INGREDIENT)
            .mutation("indigo", "glowflower")
            .register();
        CropsNHCrops.Skyberry = CropBuilder.crop("skyberry", 0x2E8BA7, 0x45E0FF)
            .drop(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 2, 2), 100_00)
            .seed(CropsNHUtils.copyStackWithSize(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 2, 2), 1))
            .likes(BiomeDictionary.Type.NETHER, DRY)
            .creator("bartimaeusnek")
            .tier(4)
            .growth(300)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.netherrack)
            // gives jump boost
            .pools(BLUE, NETHER, BERRY, BUSH, DANGEROUS, POISONOUS, ADDICTIVE, EDIBLE, POTION_INGREDIENT)
            .mutation("dayflower", "blueberry")
            .register();
        CropsNHCrops.Stingberry = CropBuilder.crop("stingberry", 0x727F3D, 0x8BDC3C)
            .drop(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 2, 3), 100_00)
            .seed(CropsNHUtils.copyStackWithSize(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 2, 3), 1))
            .likes(BiomeDictionary.Type.NETHER, HOT)
            .creator("bartimaeusnek")
            .tier(4)
            .growth(300)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.netherrack)
            // gives strength
            .pools(BROWN, NETHER, BERRY, BUSH, DANGEROUS, POISONOUS, ADDICTIVE, EDIBLE, POTION_INGREDIENT)
            .mutation("thornvine", "saguaroCactus")
            .register();
        CropsNHCrops.Thornvine = CropBuilder.crop("thornvine", 0x987005, 0xDFE485)
            .drop(CropsNHUtils.getModItem(ModUtils.Natura, "Thornvines", 2, 0), 100_00)
            .seed(CropsNHUtils.copyStackWithSize(CropsNHUtils.getModItem(ModUtils.Natura, "Thornvines", 2, 0), 1))
            .likes(BiomeDictionary.Type.NETHER, DRY)
            .creator("bartimaeusnek")
            .tier(3)
            .growth(450)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.netherrack)
            .entityDamage(1.0F)
            .pools(YELLOW, NETHER, CLIMBABLE, DANGEROUS, EMISSIVE, TENDRILLY)
            .mutation("vine", "cactus")
            .register();
        CropsNHCrops.Barley = CropFamilies.food("barley", 0x285221, 0x84C63D)
            .drop("cropBarley", 100_00)
            .seeds("seedBarley")
            // likes well drained soils
            .likes(PLAINS, DRY)
            .creator("bartimaeusnek")
            .tier(2)
            .growth(675)
            .textures("natura:barley_", 1, 4)
            .pools(GREEN, WHEAT, EDIBLE)
            .mutation("bamboo", "wheat")
            .register();
        CropsNHCrops.Blackberry = CropFamilies.food("blackberry", 0x141C22, 0x4D5A66)
            .drop(CropsNHUtils.getModItem(ModUtils.Natura, "berry", 3, 2), 100_00)
            .seeds("cropBlackberry", "seedBlackberry")
            // needs a good dose of water and loves to grow in very dense bushy areas
            .likes(WET, DENSE, LUSH)
            .creator("bartimaeusnek")
            .tier(2)
            .growth(200)
            .stages(1, 3)
            .pools(BLACK, BERRY, BUSH, EDIBLE)
            .mutation("strawberry", "blueberry")
            .register();
        CropsNHCrops.Blueberry = CropFamilies.food("blueberry", 0x354085, 0x5262B5)
            .drop(CropsNHUtils.getModItem(ModUtils.Natura, "berry", 3, 1), 100_00)
            .seeds("cropBlueberry", "seedBlueberry")
            // wild blueberries can often be found forests, usually with dry or gravely soils.
            .likes(HILLS, FOREST, LUSH)
            .creator("bartimaeusnek")
            .tier(2)
            .growth(200)
            .stages(1, 3)
            .pools(BLUE, BERRY, BUSH, EDIBLE)
            .mutation("azureBluet", "bonsaiOak")
            .register();
        CropsNHCrops.Cotton = CropBuilder.crop("cotton", 0xE366F5, 0xFFC9FF)
            .drop(CropsNHUtils.getModItem(ModUtils.Natura, "barleyFood", 1, 3), 100_00)
            .seeds("cropCotton", "seedCotton")
            .minLight(9)
            // generally likes heavier soils and does not like cold.
            .likes(DRY, HOT)
            .creator("bartimaeusnek")
            .tier(3)
            .growth(900)
            .textures("natura:cotton_", 1, 5)
            .renderShape(PlantRenderShape.X)
            .pools(WHITE, TENDRILLY, COTTON, SILK)
            .mutation("flax", "hemp")
            .register();
        CropsNHCrops.Raspberry = CropFamilies.food("raspberry", 0xA23131, 0xC54F4F)
            .drop(CropsNHUtils.getModItem(ModUtils.Natura, "berry", 3, 0), 100_00)
            .seeds("seedRaspberry", "cropRaspberry")
            // likes a well drained soil
            .likes(PLAINS, DRY)
            .creator("bartimaeusnek")
            .tier(2)
            .growth(200)
            .stages(1, 3)
            .pools(RED, BERRY, BUSH, EDIBLE)
            .mutation("redTulip", "oxeyeDaisy")
            .register();
        CropsNHCrops.Maloberry = CropFamilies.food("maloberry", 0xCE5939, 0xE8B064)
            .drop(CropsNHUtils.getModItem(ModUtils.Natura, "berry", 3, 3), 100_00)
            .seeds("cropMaloberry", "cropGooseberry")
            // likes higher latitudes, but doesn't live in the snow too much.
            .likes(COLD, WET, LUSH)
            .creator("bartimaeusnek")
            .tier(2)
            .growth(200)
            .stages(1, 3)
            .pools(YELLOW, BERRY, BUSH, EDIBLE)
            .mutation("orangeTulip", "blueberry")
            .register();
        CropsNHCrops.SaguaroCactus = CropFamilies.food("saguaroCactus", 0x3D401B, 0x828839)
            .drop(CropsNHUtils.getModItem(ModUtils.Natura, "Saguaro", 2, 0), 50_00)
            .drop(CropsNHUtils.getModItem(ModUtils.Natura, "saguaro.fruit", 3, 0), 50_00)
            .seed(CropsNHUtils.copyStackWithSize(CropsNHUtils.getModItem(ModUtils.Natura, "Saguaro", 2, 0), 1))
            .seed(CropsNHUtils.copyStackWithSize(CropsNHUtils.getModItem(ModUtils.Natura, "saguaro.fruit", 3, 0), 1))
            // likes a well drained soil
            .likes(HOT, DRY, SANDY)
            .creator("bartimaeusnek")
            .tier(4)
            .growth(450)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.sand)
            .seedShape(SeedShape.grains)
            .pools(GREEN, CACTUS, DANGEROUS, EDIBLE)
            .mutation("cactus", "sandLily")
            .register();
    }
}
