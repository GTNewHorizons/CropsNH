package com.gtnewhorizon.cropsnh.crops.definitions;

import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ADDICTIVE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ALIEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLACK;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLAZE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BROWN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BUSH;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.CHICKEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.COAL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.COW;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DANGEROUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EDIBLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EVIL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.FIERY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.FISH;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.FLOWER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GREEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.HEALING;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.LEAFY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.MAGICAL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.MUSHROOM;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.NETHER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ORANGE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.POISONOUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.POTION_INGREDIENT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.REED;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SALTPETER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SILK;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.STEM;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.STICKY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SULFUR;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TENDRILLY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TREE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.UNDEAD;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.VOID_TOUCHED;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WATERY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHITE;
import static net.minecraftforge.common.BiomeDictionary.Type.COLD;
import static net.minecraftforge.common.BiomeDictionary.Type.DEAD;
import static net.minecraftforge.common.BiomeDictionary.Type.END;
import static net.minecraftforge.common.BiomeDictionary.Type.FOREST;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.LUSH;
import static net.minecraftforge.common.BiomeDictionary.Type.OCEAN;
import static net.minecraftforge.common.BiomeDictionary.Type.PLAINS;
import static net.minecraftforge.common.BiomeDictionary.Type.RIVER;
import static net.minecraftforge.common.BiomeDictionary.Type.SPOOKY;
import static net.minecraftforge.common.BiomeDictionary.Type.SWAMP;
import static net.minecraftforge.common.BiomeDictionary.Type.WASTELAND;
import static net.minecraftforge.common.BiomeDictionary.Type.WET;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.data.CropBehaviors;
import com.gtnewhorizon.cropsnh.crops.data.CropBuilder;
import com.gtnewhorizon.cropsnh.crops.data.CropFamilies;
import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.util.GTOreDictUnificator;

public final class MobCrops {

    private MobCrops() {}

    public static void register() {
        CropsNHCrops.Blazereed = CropBuilder.crop("blazereed", 0xB38C00, 0xFFC800)
            .drop(new ItemStack(Items.blaze_powder, 1, 0), 75_00)
            .drop(new ItemStack(Items.blaze_rod, 1, 0), 25_00)
            .duplicationCatalyst(new ItemStack(Items.blaze_powder, 2, 0))
            .duplicationCatalyst(new ItemStack(Items.blaze_rod, 1, 0))
            .likes(BiomeDictionary.Type.NETHER, HOT)
            .creator("Mr. Brain")
            .tier(6)
            .growth(3600)
            .soil(CropsNHSoilTypes.netherrack)
            .seedShape(SeedShape.grains)
            .renderShape(PlantRenderShape.X)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(ORANGE, NETHER, REED, FIERY, EVIL, BLAZE, POTION_INGREDIENT, SULFUR)
            .mutation("sugarCane", "cinderpearl")
            .register();
        CropsNHCrops.Corium = CropBuilder.crop("corium", 0x542716, 0xC65C35)
            .drop(new ItemStack(Items.leather, 1, 0), 100_00)
            .likes(PLAINS, FOREST)
            .creator("Gregorius Techneticies")
            .tier(6)
            .growth(800)
            .pools(BROWN, MUSHROOM, COW)
            .mutation("wheat", "cocoa")
            .register();
        CropsNHCrops.Corpseplant = CropBuilder.crop("corpseplant", 0x551515, 0xE32F2F)
            .drop(new ItemStack(Items.dye, 1, 15), 62_50)
            .drop(new ItemStack(Items.rotten_flesh, 1, 0), 25_00)
            .drop(new ItemStack(Items.bone, 1, 0), 12_50)
            .likes(DEAD, SPOOKY)
            .creator("Mr. Kenny")
            .tier(5)
            .growth(800)
            .soil(CropsNHSoilTypes.graveyard)
            .pools(BROWN, BUSH, EVIL, POISONOUS, UNDEAD, ADDICTIVE, EDIBLE)
            .mutation("zomplant", "eyebulb")
            .register();
        CropsNHCrops.Creeperweed = CropBuilder.crop("creeperweed", 0x396B37, 0x51984D)
            .drop(new ItemStack(Items.gunpowder, 1, 0), 100_00)
            .likes(DEAD, SPOOKY)
            .creator("General Spaz")
            .tier(7)
            .growth(1000)
            // sulfur, charcoal and salt peter
            .pools(GREEN, EVIL, FIERY, TENDRILLY, SULFUR, SALTPETER, COAL, POTION_INGREDIENT)
            .mutation("netherStoneLily", "withereed", "saltyRoot")
            .register();
        CropsNHCrops.EggPlant = CropFamilies.food("eggPlant", 0xB3A57D, 0xDFCE9B)
            // TODO: MOVE ITEM DROP TO CROPS NH OR SOMETHING
            .drop(new ItemStack(Items.egg, 1, 0), 60_00)
            .drop(new ItemStack(Items.feather, 1, 0), 30_00)
            .drop(new ItemStack(Items.chicken, 1, 0), 10_00)
            .seed(new ItemStack(Items.egg, 1, 0))
            .likes(PLAINS, FOREST)
            .creator("Link")
            .flavour(Reference.MOD_ID + "_crops.eggPlant.flavour")
            .tier(6)
            .growth(1200)
            .stages(1, 3)
            .pools(WHITE, MUSHROOM, ADDICTIVE, CHICKEN, EDIBLE)
            .mutation("corium", "oxeyeDaisy")
            .register();
        CropsNHCrops.Enderbloom = CropBuilder.crop("enderbloom", 0x085245, 0x1B7B6B)
            .drop(Materials.EnderPearl.getDust(1), 62_50)
            .drop(new ItemStack(Items.ender_pearl, 1, 0), 37_50)
            .subSoil(CropsNHSubSoilTypes.endStone)
            .duplicationCatalyst(new ItemStack(Items.ender_pearl, 1, 0))
            .duplicationCatalyst(GTOreDictUnificator.get(OrePrefixes.dust, Materials.EnderPearl, 1))
            .likes(END, COLD)
            .creator("RichardG")
            .tier(10)
            .growth(3000)
            .machineBreedingTier(VoltageIndex.HV)
            .pools(BLACK, FLOWER, VOID_TOUCHED, ALIEN, MAGICAL)
            .mutation("endStoneLily", "creeperweed")
            .register();
        CropsNHCrops.Goldfish = CropBuilder.crop("goldfish", 0xF8910F, 0xFAC815)
            .drop(new ItemStack(CropsNHItems.goldfish, 1), 60_00)
            // spooky because existential fish dread.
            .likes(RIVER, WET, SPOOKY)
            .creator("bartimaeusnek")
            .flavour(CropBehaviors::goldfishFlavour)
            .tier(4)
            .growth(600)
            .stages(1, 3)
            .onGrowthTick(CropBehaviors::screamSometimes)
            .onEntityCollision(CropBehaviors::screamWhenSteppedOn)
            // addictive because after a while, you come to love their screechyness
            .pools(ORANGE, NETHER, STEM, DANGEROUS, WATERY, ADDICTIVE, EDIBLE, FISH)
            .mutation("waterLily", "waterArtichoke", "mandrake")
            .register();
        CropsNHCrops.InkBloom = CropBuilder.crop("inkbloom", 0x000000, 0x2E1F27)
            .drop(new ItemStack(Items.dye, 1, 0), 100_00)
            .likes(WET, OCEAN)
            .tier(4)
            .growth(600)
            .soil(CropsNHSoilTypes.dirtGrass)
            .pools(BLACK, FLOWER, WATERY)
            .mutation("blackberry", "goldfish")
            .register();
        CropsNHCrops.Slimeplant = CropBuilder.crop("slimeplant", 0x5A3F1E, 0x9DF28A)
            .drop(new ItemStack(Items.slime_ball, 1, 0), 100_00)
            .duplicationCatalyst("slimeball", 1)
            .likes(WET, SWAMP)
            .creator("Neowulf")
            .tier(6)
            .growth(1200)
            .soil(CropsNHSoilTypes.dirtGrass)
            .seedShape(SeedShape.bonsai)
            .pools(GREEN, TREE, EVIL, LEAFY, STICKY, POTION_INGREDIENT)
            .mutation("blueOrchid", "clayLily")
            .register();
        CropsNHCrops.Spidernip = CropBuilder.crop("spidernip", 0xBFBFBF, 0xF7F7F7)
            .drop(new ItemStack(Items.string, 1, 0), 66_66)
            .drop(new ItemStack(Items.spider_eye, 1, 0), 16_67)
            .drop(new ItemStack(Blocks.web, 1, 0), 16_67)
            .likes(WET, LUSH)
            .creator("Mr. Kenny")
            .tier(4)
            .growth(2400)
            .pools(WHITE, EVIL, POISONOUS, ADDICTIVE, EDIBLE, POTION_INGREDIENT, SILK)
            .mutation("flax", "hemp", "zomplant")
            .register();
        CropsNHCrops.Tearstalks = CropBuilder.crop("tearstalks", 0xBFBFBF, 0xF7F7F7)
            .drop(new ItemStack(Items.ghast_tear, 1, 0), 66_66)
            .duplicationCatalyst(new ItemStack(Items.ghast_tear, 1, 0))
            // Ghasts are spooky ghosts
            .likes(BiomeDictionary.Type.NETHER, SPOOKY)
            .creator("Neowulf")
            .tier(8)
            .growth(4800)
            .seedShape(SeedShape.grains)
            .pools(WHITE, NETHER, STEM, EVIL, HEALING, POTION_INGREDIENT)
            .mutation("goldfish", "soulSandLily", "netherStoneLily")
            .register();
        CropsNHCrops.Zomplant = CropBuilder.crop("zomplant", 0x3A6529, 0x679056)
            .when(
                ModUtils.Thaumcraft.isModLoaded(),
                b -> b.drop(new ItemStack(Items.rotten_flesh, 1, 0), 98_00)
                    // low chance but that's because with high gain you've basically got a 1/25 per harvest
                    .drop(ModUtils.Thaumcraft.getStack("ItemZombieBrain", 1, 0), 1_50),
                b -> b.drop(new ItemStack(Items.rotten_flesh, 1, 0), 99_50))
            // TODO: remove zombie head from drop table when the mob head crop is added.
            .drop(new ItemStack(Items.skull, 1, 2), 50)
            .likes(DEAD, WASTELAND)
            .tier(3)
            .growth(1800)
            .soil(CropsNHSoilTypes.graveyard)
            .renderShape(PlantRenderShape.X)
            .pools(BROWN, EVIL, POISONOUS, UNDEAD, EDIBLE)
            .mutation("withereed", "eyebulb")
            .register();
    }
}
