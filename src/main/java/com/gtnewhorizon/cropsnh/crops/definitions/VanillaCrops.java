package com.gtnewhorizon.cropsnh.crops.definitions;

import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ADDICTIVE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BEAN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLUE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BROWN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.CACTUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.CLIMBABLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DANGEROUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EDIBLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.FLOWER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GREEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.HEALING;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.LEAFY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.MUSHROOM;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.NETHER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ORANGE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.POTION_INGREDIENT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.PURPLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.RED;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.REED;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ROOT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.STEM;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TENDRILLY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TREE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TULIP;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WATERY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHEAT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHITE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.YELLOW;
import static net.minecraftforge.common.BiomeDictionary.Type.COLD;
import static net.minecraftforge.common.BiomeDictionary.Type.CONIFEROUS;
import static net.minecraftforge.common.BiomeDictionary.Type.DENSE;
import static net.minecraftforge.common.BiomeDictionary.Type.DRY;
import static net.minecraftforge.common.BiomeDictionary.Type.FOREST;
import static net.minecraftforge.common.BiomeDictionary.Type.HILLS;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.JUNGLE;
import static net.minecraftforge.common.BiomeDictionary.Type.LUSH;
import static net.minecraftforge.common.BiomeDictionary.Type.PLAINS;
import static net.minecraftforge.common.BiomeDictionary.Type.RIVER;
import static net.minecraftforge.common.BiomeDictionary.Type.SANDY;
import static net.minecraftforge.common.BiomeDictionary.Type.SPARSE;
import static net.minecraftforge.common.BiomeDictionary.Type.SWAMP;
import static net.minecraftforge.common.BiomeDictionary.Type.WET;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.data.CropBehaviors;
import com.gtnewhorizon.cropsnh.crops.data.CropBuilder;
import com.gtnewhorizon.cropsnh.crops.data.CropFamilies;
import com.gtnewhorizon.cropsnh.reference.Constants;

public final class VanillaCrops {

    private VanillaCrops() {}

    public static void register() {
        // food
        CropsNHCrops.Carrot = CropFamilies.food("carrot", 0xE58700, 0xEEAE7D)
            .drop(new ItemStack(Items.carrot, 1), 10_000)
            .seed(new ItemStack(Items.carrot, 1))
            // prefers sandier soils
            .likes(PLAINS, HOT, SANDY)
            .creator("Notch")
            .name("item.carrots.name")
            .tier(2)
            .growth(800)
            .textures("carrots_stage_", 0, 3)
            .pools(ORANGE, ROOT, EDIBLE, POTION_INGREDIENT)
            .register();
        CropsNHCrops.Cocoa = CropFamilies.food("cocoa", 0x734120, 0x976746)
            .drop(new ItemStack(Items.dye, 1, 3), 10_000)
            .seed(new ItemStack(Items.dye, 1, 3))
            // mostly grows in hot-wet tropical areas
            .likes(JUNGLE, WET, DENSE, LUSH)
            .creator("Notch")
            .name("tile.cocoa.name")
            .tier(3)
            .growth(1700)
            .pools(BROWN, BEAN, TREE, LEAFY, ADDICTIVE, EDIBLE)
            .mutation("melon", "bonsaiJungle")
            .register();
        CropsNHCrops.Melon = CropFamilies.food("melon", 0x2A220B, 0x615535)
            .seed(new ItemStack(Items.melon_seeds, 1))
            .drop(new ItemStack(Items.melon, 4), 6666)
            .drop(new ItemStack(Blocks.melon_block, 1), 3333)
            .likes(WET, LUSH)
            .creator("Notch")
            .name("tile.melon.name")
            .tier(2)
            .growth(1200)
            .seedShape(SeedShape.grains)
            .pools(GREEN, STEM, EDIBLE, HEALING, POTION_INGREDIENT)
            .mutation("pumpkin", "bonsaiJungle")
            .register();
        CropsNHCrops.Potato = CropFamilies.food("potato", 0xC0A51B, 0xFBE6BC)
            .drop(new ItemStack(Items.potato, 1), 10_000)
            .seed(new ItemStack(Items.potato, 1))
            // tends to be a staple crop for more northern/cold countries.
            .likes(PLAINS, COLD, CONIFEROUS)
            .creator("Notch")
            .name("item.potato.name")
            .tier(1)
            .growth(800)
            .textures("potatoes_stage_", 0, 3)
            .pools(YELLOW, ROOT, EDIBLE)
            .register();
        CropsNHCrops.Pumpkin = CropFamilies.food("pumpkin", 0xCAB16E, 0xC8D4AA)
            .drop(new ItemStack(Blocks.pumpkin, 1), 10_000)
            .seed(new ItemStack(Items.pumpkin_seeds, 1))
            .likes(PLAINS, WET)
            .creator("Notch")
            .name("tile.pumpkin.name")
            .tier(2)
            .growth(1000)
            .pools(ORANGE, STEM, EDIBLE)
            .mutation("carrot", "sugarCane")
            .register();
        CropsNHCrops.Wheat = CropFamilies.food("wheat", 0xB7BB3F, 0x00E210)
            .drop(new ItemStack(Items.wheat, 1), 10_000)
            .seed(new ItemStack(Items.wheat_seeds, 1))
            // not much to say here
            .likes(PLAINS, LUSH)
            .creator("Notch")
            .name("item.wheat.name")
            .tier(1)
            .growth(1000)
            .textures("wheat_stage_", 0, 7)
            .pools(YELLOW, WHEAT, EDIBLE)
            .register();

        // mushrooms
        CropsNHCrops.RedMushroom = CropFamilies.mushroom("redMushroom", 0xA61313, 0xE21212)
            .drop(new ItemStack(Blocks.red_mushroom, 1), 10_000)
            .seed(new ItemStack(Blocks.red_mushroom, 1))
            .likes(BiomeDictionary.Type.MUSHROOM, WET, SWAMP)
            .creator("Notch")
            .tier(1)
            .growth(400)
            .seedShape(SeedShape.spore)
            .pools(RED, MUSHROOM, EDIBLE)
            .mutation("pumpkin", "poppy")
            .register();
        CropsNHCrops.BrownMushroom = CropFamilies.mushroom("brownMushroom", 0x8F4E1B, 0xCC9978)
            .drop(new ItemStack(Blocks.brown_mushroom, 1), 10_000)
            .seed(new ItemStack(Blocks.brown_mushroom, 1))
            .likes(BiomeDictionary.Type.MUSHROOM, WET, SWAMP)
            .creator("Notch")
            .tier(1)
            .growth(400)
            .seedShape(SeedShape.spore)
            .pools(BROWN, MUSHROOM, EDIBLE)
            .mutation("cocoa", "redMushroom")
            .register();

        // flowers
        CropsNHCrops.Dandelion = CropFamilies.vanillaFlower("dandelion", 0x8E6B13, 0xE7E769)
            .drop(new ItemStack(Items.dye, 1, 11), Constants.FLOWER_DYE_DROP_CHANCE)
            .drop(new ItemStack(Blocks.yellow_flower, 1, 0), Constants.FLOWER_ITEM_DROP_CHANCE)
            .seed(new ItemStack(Blocks.yellow_flower, 1, 0))
            .likes(PLAINS, LUSH, FOREST)
            .creator("Notch")
            .name("tile.flower1.dandelion.name")
            .pools(YELLOW, FLOWER)
            .register();
        CropsNHCrops.Poppy = CropFamilies.vanillaFlower("poppy", 0x3A0102, 0xBA050B)
            .drop(new ItemStack(Items.dye, 1, 1), Constants.FLOWER_DYE_DROP_CHANCE)
            .drop(new ItemStack(Blocks.red_flower, 1, 0), Constants.FLOWER_ITEM_DROP_CHANCE)
            .seed(new ItemStack(Blocks.red_flower, 1, 0))
            // half tempted to add dead, but that just feels like a bad taste joke.
            .likes(PLAINS, LUSH, HILLS)
            .creator("Notch")
            .name("tile.flower2.poppy.name")
            .pools(RED, FLOWER)
            .register();
        CropsNHCrops.BlueOrchid = CropFamilies.vanillaFlower("blueOrchid", 0x2F5488, 0x8FB9F4)
            .drop(new ItemStack(Items.dye, 1, 12), Constants.FLOWER_DYE_DROP_CHANCE)
            .drop(new ItemStack(Blocks.red_flower, 1, 1), Constants.FLOWER_ITEM_DROP_CHANCE)
            .seed(new ItemStack(Blocks.red_flower, 1, 1))
            .likes(WET, LUSH, SWAMP)
            .creator("Notch")
            .name("tile.flower2.blueOrchid.name")
            .renderShape(PlantRenderShape.X)
            .pools(BLUE, FLOWER)
            .mutation("poppy", "waterLily")
            .register();
        CropsNHCrops.Allium = CropFamilies.vanillaFlower("allium", 0x6B2166, 0xDB7AD5)
            .drop(new ItemStack(Items.dye, 1, 13), Constants.FLOWER_DYE_DROP_CHANCE)
            .drop(new ItemStack(Blocks.red_flower, 1, 2), Constants.FLOWER_ITEM_DROP_CHANCE)
            .seed(new ItemStack(Blocks.red_flower, 1, 2))
            // Prefers siltier soils
            .likes(DRY, SANDY, SPARSE)
            .creator("Notch")
            .name("tile.flower2.allium.name")
            // allowed to have 2 types because it's a nod to onions
            .pools(PURPLE, ROOT, FLOWER)
            .mutation("pinkTulip", "purpleTulip")
            .register();
        CropsNHCrops.AzureBluet = CropFamilies.vanillaFlower("azureBluet", 0x3D3D4C, 0xE4EAF2)
            .drop(new ItemStack(Items.dye, 1, 7), Constants.FLOWER_DYE_DROP_CHANCE)
            .drop(new ItemStack(Blocks.red_flower, 1, 3), Constants.FLOWER_ITEM_DROP_CHANCE)
            .seed(new ItemStack(Blocks.red_flower, 1, 3))
            // wild blueberries can often be found forests, usually with dry or gravely soils.
            .likes(HILLS, FOREST, LUSH)
            .creator("Notch")
            .name("tile.flower2.houstonia.name")
            .pools(WHITE, FLOWER)
            .mutation("poppy", "potato")
            .register();
        CropsNHCrops.RedTulip = CropFamilies.vanillaFlower("redTulip", 0x7A0707, 0xD24343)
            .drop(new ItemStack(Items.dye, 1, 1), Constants.FLOWER_DYE_DROP_CHANCE)
            .drop(new ItemStack(Blocks.red_flower, 1, 4), Constants.FLOWER_ITEM_DROP_CHANCE)
            .seed(new ItemStack(Blocks.red_flower, 1, 4))
            .likes(PLAINS, LUSH, HOT)
            .creator("Notch")
            .name("tile.flower2.tulipRed.name")
            .pools(RED, FLOWER, TULIP)
            .mutation("poppy", "whiteTulip")
            .register();
        CropsNHCrops.OrangeTulip = CropFamilies.vanillaFlower("orangeTulip", 0x92460C, 0xF4B33F)
            .drop(new ItemStack(Items.dye, 1, 14), Constants.FLOWER_DYE_DROP_CHANCE)
            .drop(new ItemStack(Blocks.red_flower, 1, 5), Constants.FLOWER_ITEM_DROP_CHANCE)
            .seed(new ItemStack(Blocks.red_flower, 1, 5))
            .likes(PLAINS, LUSH, WET)
            .creator("Notch")
            .name("tile.flower2.tulipOrange.name")
            .pools(ORANGE, FLOWER, TULIP)
            .mutation("redTulip", "dandelion")
            .register();
        CropsNHCrops.WhiteTulip = CropFamilies.vanillaFlower("whiteTulip", 0x3D3D4C, 0xD9D9D9)
            .drop(new ItemStack(Items.dye, 1, 7), Constants.FLOWER_DYE_DROP_CHANCE)
            .drop(new ItemStack(Blocks.red_flower, 1, 6), Constants.FLOWER_ITEM_DROP_CHANCE)
            .seed(new ItemStack(Blocks.red_flower, 1, 6))
            .likes(PLAINS, LUSH, COLD)
            .creator("Notch")
            .name("tile.flower2.tulipWhite.name")
            .pools(WHITE, FLOWER, TULIP)
            .mutation("pinkTulip", "oxeyeDaisy")
            .register();
        CropsNHCrops.PinkTulip = CropFamilies.vanillaFlower("pinkTulip", 0x80365C, 0xF7B4D6)
            .drop(new ItemStack(Items.dye, 1, 9), Constants.FLOWER_DYE_DROP_CHANCE)
            .drop(new ItemStack(Blocks.red_flower, 1, 7), Constants.FLOWER_ITEM_DROP_CHANCE)
            .seed(new ItemStack(Blocks.red_flower, 1, 7))
            .likes(PLAINS, LUSH, WET)
            .creator("Notch")
            .name("tile.flower2.tulipPink.name")
            .pools(RED, FLOWER, TULIP)
            .mutation("redTulip", "whiteTulip")
            .register();
        CropsNHCrops.OxeyeDaisy = CropFamilies.vanillaFlower("oxeyeDaisy", 0x3D3D4C, 0xC8C8C8)
            .drop(new ItemStack(Items.dye, 1, 7), Constants.FLOWER_DYE_DROP_CHANCE)
            .drop(new ItemStack(Blocks.red_flower, 1, 8), Constants.FLOWER_ITEM_DROP_CHANCE)
            .seed(new ItemStack(Blocks.red_flower, 1, 8))
            .likes(PLAINS, LUSH, FOREST)
            .creator("Notch")
            .name("tile.flower2.oxeyeDaisy.name")
            .pools(WHITE, FLOWER)
            .mutation("azureBluet", "dandelion")
            .register();

        // misc
        CropsNHCrops.Cactus = CropBuilder.crop("cactus", 0x094210, 0x117F20)
            .drop(new ItemStack(Blocks.cactus, 1), 10_000)
            .seed(new ItemStack(Blocks.cactus, 1))
            .likes(SANDY, HOT, DRY)
            .creator("Notch")
            .name("tile.cactus.name")
            .tier(3)
            .growth(450)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.sand)
            .seedShape(SeedShape.grains)
            .entityDamage(1.0F)
            .pools(GREEN, CACTUS, DANGEROUS)
            .mutation("sugarCane", "potato")
            .register();
        CropsNHCrops.SugarCane = CropBuilder.crop("sugarCane", 0x698747, 0xAADB74)
            .drop(new ItemStack(Items.reeds, 2), 10_000)
            .seed(new ItemStack(Items.reeds, 1))
            .likes(WET, HOT)
            .creator("Notch")
            .tier(2)
            .growth(400)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.sugarcane)
            .seedShape(SeedShape.grains)
            .renderShape(PlantRenderShape.X)
            .pools(GREEN, REED, EDIBLE)
            .mutation("carrot", "potato")
            .register();
        CropsNHCrops.Netherwart = CropBuilder.crop("netherwart", 0x4D1115, 0xBE3F4A)
            .drop(new ItemStack(Items.nether_wart, 1), 100_00)
            .seed(new ItemStack(Items.nether_wart, 1))
            .likes(BiomeDictionary.Type.NETHER, HOT)
            .creator("Notch")
            .name("item.netherStalkSeeds.name")
            .tier(5)
            .growth(2000)
            .textures("nether_wart_stage_", 0, 2)
            .soil(CropsNHSoilTypes.soulsand)
            .seedShape(SeedShape.spore)
            .dropChance(2.0f)
            // right-clicking with snow blocks may turn it into gaia wart
            .onRightClick(CropBehaviors::turnIntoGaiaWart)
            .additionalData(CropBehaviors::readGaiaWartChance)
            .pools(RED, NETHER, POTION_INGREDIENT)
            .mutation("redMushroom", "brownMushroom")
            .register();
        CropsNHCrops.Vine = CropBuilder.crop("vine", 0x173A0A, 0x2C7310)
            .drop(new ItemStack(Blocks.vine, 2), 100_00)
            .seed(new ItemStack(Blocks.vine, 1, 0))
            .likes(LUSH, JUNGLE, WET)
            .creator("jeb_")
            .tier(2)
            .growth(450)
            .stages(1, 3)
            .seedShape(SeedShape.flower)
            .pools(GREEN, CLIMBABLE, TENDRILLY)
            .mutation("pumpkin", "blueOrchid")
            .register();
        CropsNHCrops.Waterlily = CropBuilder.crop("waterLily", 0x3E89D8, 0xD497D8)
            .drop(new ItemStack(Items.dye, 2, 9), 20_000)
            .drop(new ItemStack(Blocks.waterlily, 2), 80_000)
            .seed(new ItemStack(Blocks.waterlily))
            .likes(WET, SWAMP, RIVER)
            .creator("moronwmachinegun")
            .tier(2)
            .growth(450)
            .stages(1, 3)
            .seedShape(SeedShape.flower)
            .pools(GREEN, WATERY)
            .mutation("vine", "sugarCane")
            .register();
    }
}
