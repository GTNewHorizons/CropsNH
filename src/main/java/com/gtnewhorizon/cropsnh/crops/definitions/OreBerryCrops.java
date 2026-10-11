package com.gtnewhorizon.cropsnh.crops.definitions;

import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLACK;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLUE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.COPPER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DANGEROUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DENSE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EDIBLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GOLD;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GRAY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GREEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.IRON;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.MAGICAL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.METALLIC;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.NETHER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ORANGE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ORE_BERRY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.PURPLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SHINY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TIN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TWILIGHT_FOREST;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.UNDEAD;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.VOID_TOUCHED;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHITE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.YELLOW;
import static net.minecraftforge.common.BiomeDictionary.Type.COLD;
import static net.minecraftforge.common.BiomeDictionary.Type.DEAD;
import static net.minecraftforge.common.BiomeDictionary.Type.DRY;
import static net.minecraftforge.common.BiomeDictionary.Type.FOREST;
import static net.minecraftforge.common.BiomeDictionary.Type.HILLS;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.MESA;
import static net.minecraftforge.common.BiomeDictionary.Type.MOUNTAIN;
import static net.minecraftforge.common.BiomeDictionary.Type.SANDY;
import static net.minecraftforge.common.BiomeDictionary.Type.SAVANNA;
import static net.minecraftforge.common.BiomeDictionary.Type.SPOOKY;
import static net.minecraftforge.common.BiomeDictionary.Type.WASTELAND;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.data.CropFamilies;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.enums.Materials;
import gregtech.api.enums.VoltageIndex;

public final class OreBerryCrops {

    private OreBerryCrops() {}

    public static void register() {
        if (ModUtils.TinkerConstruct.isModLoaded()) {
            CropsNHCrops.AluminiumOreBerry = CropFamilies.oreBerry("aluminium", 0x5687A3, 0x80C8F0)
                .drop(CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 4), 100_00)
                .seed(
                    CropsNHUtils
                        .copyStackWithSize(CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 4), 1))
                .subSoil(CropsNHSubSoilTypes.aluminium)
                .maxLight(10)
                .duplicationCatalyst("nuggetAluminium", 1)
                // tundra
                .likes(COLD, DRY)
                .creator("bartimaeusnek")
                .machineBreedingTier(VoltageIndex.MV)
                .pools(WHITE, ORE_BERRY, DANGEROUS, METALLIC)
                .mutation("goldOreBerry", "essenceOreBerry")
                .register();
            CropsNHCrops.CopperOreBerry = CropFamilies.oreBerry("copper", 0xA84D11, 0xF26F18)
                .drop(CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 2), 100_00)
                .seed(
                    CropsNHUtils
                        .copyStackWithSize(CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 2), 1))
                .subSoil(CropsNHSubSoilTypes.copper)
                .maxLight(10)
                .duplicationCatalyst("nuggetCopper", 1)
                .likes(SAVANNA, HOT, SANDY)
                .creator("bartimaeusnek")
                .machineBreedingTier(VoltageIndex.MV)
                .pools(ORANGE, ORE_BERRY, DANGEROUS, METALLIC, SHINY, COPPER)
                .mutation("malaxia", "bopBerry", "stoneLily")
                .register();
            CropsNHCrops.EssenceOreBerry = CropFamilies.oreBerry("essence", 0x6BB324, 0x99FF33)
                .drop(CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 5), 100_00)
                .seed(
                    CropsNHUtils
                        .copyStackWithSize(CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 5), 1))
                .subSoil(CropsNHSubSoilTypes.skull)
                .likes(BiomeDictionary.Type.MAGICAL, DEAD)
                .creator("bartimaeusnek")
                .machineBreedingTier(VoltageIndex.MV)
                .pools(GREEN, ORE_BERRY, DANGEROUS, UNDEAD, EDIBLE)
                .mutation("creeperweed", "zomplant", "spidernip", "tearstalks")
                .register();
            CropsNHCrops.GoldOreBerry = CropFamilies.oreBerry("gold", 0xB3B315, 0xFFFF1E)
                .drop(CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 1), 100_00)
                .seed(
                    CropsNHUtils
                        .copyStackWithSize(CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 1), 1))
                .subSoil(CropsNHSubSoilTypes.gold)
                .maxLight(10)
                .duplicationCatalyst("nuggetGold", 1)
                // mesa
                .likes(MESA, SANDY)
                .creator("bartimaeusnek")
                .machineBreedingTier(VoltageIndex.MV)
                .pools(YELLOW, ORE_BERRY, DENSE, DANGEROUS, METALLIC, GOLD)
                .mutation("copperOreBerry", "ironOreBerry", "tinOreBerry")
                .register();
            CropsNHCrops.IronOreBerry = CropFamilies.oreBerry("iron", 0x7D7D7D, 0xC8C8C8)
                .drop(CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 0), 100_00)
                .seed(
                    CropsNHUtils
                        .copyStackWithSize(CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 0), 1))
                .subSoil(CropsNHSubSoilTypes.iron)
                .maxLight(10)
                .duplicationCatalyst("nuggetIron", 1)
                // iron tends to be easily found in mountains
                .likes(MOUNTAIN, HILLS)
                .creator("bartimaeusnek")
                .machineBreedingTier(VoltageIndex.MV)
                .pools(GRAY, ORE_BERRY, DANGEROUS, METALLIC, IRON)
                .mutation("tinOreBerry", "stoneLily")
                .register();
            CropsNHCrops.TinOreBerry = CropFamilies.oreBerry("tin", 0x8F8F8F, 0xDCDCDC)
                .drop(CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 3), 100_00)
                .seed(
                    CropsNHUtils
                        .copyStackWithSize(CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 3), 1))
                .subSoil(CropsNHSubSoilTypes.tin)
                .maxLight(10)
                .duplicationCatalyst("nuggetTin", 1)
                .likes(MOUNTAIN, HILLS)
                .creator("bartimaeusnek")
                .tier(4)
                .machineBreedingTier(VoltageIndex.MV)
                .pools(WHITE, ORE_BERRY, DANGEROUS, METALLIC, SHINY, TIN)
                .mutation("cassitine", "bopBerry", "stoneLily")
                .register();
            CropsNHCrops.ArditeOreBerry = CropFamilies.oreBerry("ardite", 0xAD5A00, 0xFA8100)
                .drop(Materials.Ardite.getNuggets(1), 100_00)
                .subSoil(CropsNHSubSoilTypes.ardite)
                .maxLight(10)
                .duplicationCatalyst("nuggetArdite", 1)
                .likes(BiomeDictionary.Type.NETHER, HOT)
                .creator("bartimaeusnek")
                .machineBreedingTier(VoltageIndex.MV)
                .pools(ORANGE, ORE_BERRY, DANGEROUS, METALLIC)
                .mutation("netherStoneLily", "coppon", "copperOreBerry", "malaxia")
                .register();
            CropsNHCrops.CobaltOreBerry = CropFamilies.oreBerry("cobalt", 0x3737AD, 0x5050FA)
                .drop(Materials.Cobalt.getNuggets(1), 100_00)
                .subSoil(CropsNHSubSoilTypes.cobalt)
                .maxLight(10)
                .duplicationCatalyst("nuggetCobalt", 1)
                .likes(BiomeDictionary.Type.NETHER, DRY)
                .creator("bartimaeusnek")
                .machineBreedingTier(VoltageIndex.MV)
                .pools(BLUE, NETHER, ORE_BERRY, DANGEROUS, METALLIC)
                .mutation("netherStoneLily", "arditeOreBerry", "lazulia", "goldOreBerry")
                .register();
        }
        if (ModUtils.Thaumcraft.isModLoaded()) {
            CropsNHCrops.ThaumiumOreBerry = CropFamilies.oreBerry("thaumium", 0x67458A, 0x9664C8)
                .drop(Materials.Thaumium.getNuggets(1), 100_00)
                .subSoil(CropsNHSubSoilTypes.thaumium)
                .maxLight(10)
                .duplicationCatalyst("nuggetThaumium", 1)
                // eerie
                .likes(BiomeDictionary.Type.MAGICAL, SPOOKY)
                .creator("bartimaeusnek")
                .tier(7)
                .growth(3000)
                .machineBreedingTier(VoltageIndex.MV)
                .pools(PURPLE, ORE_BERRY, DANGEROUS, MAGICAL, METALLIC, VOID_TOUCHED)
                .mutation("stoneLily", "goldOreBerry")
                .register();
            CropsNHCrops.VoidOreBerry = CropFamilies.oreBerry("void", 0x130426, 0x1C0639)
                .drop(Materials.Void.getNuggets(1), 100_00)
                .seed(CropsNHUtils.getModItem(ModUtils.Thaumcraft, "ItemResource", 1, 17))
                .subSoil(CropsNHSubSoilTypes.$void)
                .maxLight(10)
                .duplicationCatalyst(Materials.Void.getNuggets(1))
                // tainted land (this makes me feel evil)
                .likes(BiomeDictionary.Type.MAGICAL, WASTELAND)
                .creator("bartimaeusnek")
                .tier(7)
                .growth(4500)
                .machineBreedingTier(VoltageIndex.MV)
                .pools(BLACK, ORE_BERRY, DANGEROUS, MAGICAL, METALLIC, VOID_TOUCHED)
                .mutation("thaumiumOreBerry", "goldOreBerry")
                .register();
        }
        if (ModUtils.ThaumicBases.isModLoaded()) {
            CropsNHCrops.ThauminiteOreBerry = CropFamilies.oreBerry("thauminite", 0x515A9C, 0x7581E0)
                .drop("nuggetThauminite", 100_00)
                .subSoil(CropsNHSubSoilTypes.thauminite)
                .maxLight(10)
                .duplicationCatalyst("nuggetThauminite", 1)
                // magical forest
                .likes(BiomeDictionary.Type.MAGICAL, FOREST)
                .creator("bartimaeusnek")
                .tier(7)
                .growth(4500)
                .machineBreedingTier(VoltageIndex.MV)
                .pools(BLUE, ORE_BERRY, DANGEROUS, MAGICAL, METALLIC, VOID_TOUCHED)
                .when(ModUtils.TinkerConstruct, b -> b.mutation("thaumiumOreBerry", "manaBean"))
                .register();
        }
        if (ModUtils.TwilightForest.isModLoaded()) {
            CropsNHCrops.KnightmetalBerry = CropFamilies.oreBerry("knightmetal", 0x90A68A, 0xD2F0C8)
                .drop(CropsNHUtils.getModItem(ModUtils.TwilightForest, "item.armorShards", 4, 0), 100_00)
                .subSoil(CropsNHSubSoilTypes.knightmetal)
                .maxLight(10)
                .duplicationCatalyst("nuggetKnightmetal", 1)
                .likes(SPOOKY, DEAD)
                .creator("bartimaeusnek")
                .tier(8)
                .growth(5500)
                .machineBreedingTier(VoltageIndex.MV)
                .pools(GRAY, TWILIGHT_FOREST, ORE_BERRY, DANGEROUS, METALLIC)
                .mutation("ironOreBerry", "torchberry", "bonsaiDarkOak")
                .register();
        }
    }
}
