package com.gtnewhorizon.cropsnh.crops.definitions;

import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ADDICTIVE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BERRY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLACK;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLUE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BROWN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BUSH;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.COAL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.COPPER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.COTTON;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.COW;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.CRYSTALLINE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DANGEROUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DENSE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EDIBLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EMISSIVE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EVIL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.FIERY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.FLOWER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GOLD;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GRAY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GREEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.HEALING;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.IRON;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.LEAD;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.LEAFY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.METALLIC;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.MUSHROOM;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.NETHER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.OIL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ORANGE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.POISONOUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.POTION_INGREDIENT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.PURPLE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.RED;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.REED;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ROOT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SALTPETER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SHINY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SILK;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SILVER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.STEM;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.STICKY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.STONE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SULFUR;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TENDRILLY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TIN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TULIP;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TWILIGHT_FOREST;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.UNDEAD;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHEAT;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHITE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WOODEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.YELLOW;
import static net.minecraftforge.common.BiomeDictionary.Type.BEACH;
import static net.minecraftforge.common.BiomeDictionary.Type.COLD;
import static net.minecraftforge.common.BiomeDictionary.Type.DEAD;
import static net.minecraftforge.common.BiomeDictionary.Type.DRY;
import static net.minecraftforge.common.BiomeDictionary.Type.END;
import static net.minecraftforge.common.BiomeDictionary.Type.HILLS;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.JUNGLE;
import static net.minecraftforge.common.BiomeDictionary.Type.LUSH;
import static net.minecraftforge.common.BiomeDictionary.Type.MAGICAL;
import static net.minecraftforge.common.BiomeDictionary.Type.MESA;
import static net.minecraftforge.common.BiomeDictionary.Type.MOUNTAIN;
import static net.minecraftforge.common.BiomeDictionary.Type.OCEAN;
import static net.minecraftforge.common.BiomeDictionary.Type.PLAINS;
import static net.minecraftforge.common.BiomeDictionary.Type.RIVER;
import static net.minecraftforge.common.BiomeDictionary.Type.SANDY;
import static net.minecraftforge.common.BiomeDictionary.Type.SAVANNA;
import static net.minecraftforge.common.BiomeDictionary.Type.SNOWY;
import static net.minecraftforge.common.BiomeDictionary.Type.SPARSE;
import static net.minecraftforge.common.BiomeDictionary.Type.SPOOKY;
import static net.minecraftforge.common.BiomeDictionary.Type.SWAMP;
import static net.minecraftforge.common.BiomeDictionary.Type.WASTELAND;
import static net.minecraftforge.common.BiomeDictionary.Type.WET;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.data.CropBehaviors;
import com.gtnewhorizon.cropsnh.crops.data.CropBuilder;
import com.gtnewhorizon.cropsnh.crops.data.CropFamilies;
import com.gtnewhorizon.cropsnh.farming.requirements.breeding.MachineBreedingCatalystRequirement;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MachineOnlyGrowthRequirement;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.util.GTOreDictUnificator;

public final class MaterialCrops {

    private MaterialCrops() {}

    public static void register() {
        CropsNHCrops.Argentia = CropBuilder.crop("argentia", 0xB2BEC9, 0xFDFEFE)
            .drop(CropsNHItemList.argentiaLeaf.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.silver)
            .duplicationCatalyst("dustSilver", 1)
            // argentina
            .likes(MOUNTAIN, HOT)
            .creator("Eloraam")
            .tier(7)
            .growth(1400)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(WHITE, METALLIC, SHINY, TENDRILLY, SILVER)
            .mutation("plumbilia", "tine")
            .register();
        CropsNHCrops.Auronia = CropBuilder.crop("auronia", 0xABA600, 0xEFE913)
            .drop(CropsNHItemList.auroniaLeaf.get(1), 10_000)
            .subSoil(CropsNHSubSoilTypes.gold)
            .duplicationCatalyst("dustGold", 1)
            // mesa
            .likes(MESA, SANDY)
            .tier(8)
            .growth(3700)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(YELLOW, DENSE, METALLIC, TENDRILLY, GOLD)
            .mutation("plumbilia", "coppon")
            .register();
        CropsNHCrops.Bauxia = CropBuilder.crop("bauxia", 0x460D00, 0xC88E76)
            .drop(CropsNHItemList.bauxiaLeaf.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.aluminiumBauxite)
            .duplicationCatalyst("dustAluminium", 1)
            .duplicationCatalyst("dustBauxite", 2)
            // tundra
            .likes(COLD, DRY)
            .tier(6)
            .growth(1200)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .mutation(
                m -> m.parents("galvania", "nickelback")
                    .machineOnly())
            .register();
        CropsNHCrops.BobsYerUncleRanks = CropBuilder.crop("bobsYerUncleRanks", 0x009529, 0x41F384)
            .drop(CropsNHItemList.bobsYerUncleBerry.get(1), 75_00)
            .drop(new ItemStack(Items.emerald, 1, 0), 25_00)
            .subSoil(CropsNHSubSoilTypes.emerald)
            .duplicationCatalyst("gemEmerald", 1)
            .duplicationCatalyst("dustEmerald", 1)
            // emerald generation
            .likes(MOUNTAIN, HILLS)
            .creator("GenerikB")
            .tier(6)
            .growth(3000)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(GREEN, BERRY, CRYSTALLINE, SHINY, TENDRILLY)
            .mutation("diareed", "olivia")
            .register();
        CropsNHCrops.Canola = CropBuilder.crop("canola", 0x627F40, 0x6E8E48)
            .drop(CropsNHItemList.canolaFlower.get(1), 100_00)
            // canada.jpg
            .likes(COLD, PLAINS, SPARSE)
            .tier(4)
            .growth(900)
            // i wouldn't recommend chugging canola oil, but it's edible
            .pools(YELLOW, WHEAT, EDIBLE, OIL)
            .mutation("oxeyeDaisy", "wheat")
            .register();
        CropsNHCrops.Cassitine = CropBuilder.crop("cassitine", 0x6C6C6C, 0x7F7F7F)
            .drop(Materials.Tin.getDustTiny(1), 100_00)
            .requirement(CropsNHSubSoilTypes.tin)
            .duplicationCatalyst("dustTin", 1)
            // going by the word stagnate, something cold and un-changing.
            .likes(COLD, SPARSE, DRY)
            .tier(6)
            .growth(2800)
            .soil(CropsNHSoilTypes.stone)
            .pools(GRAY, BUSH, LEAFY, METALLIC, SHINY, TIN)
            .mutation("malaxia", "stoneLily", "marbleLily")
            .register();
        CropsNHCrops.Coppon = CropBuilder.crop("coppon", 0xBE7000, 0xD88000)
            .drop(CropsNHItemList.copponFiber.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.copper)
            .duplicationCatalyst("dustCopper", 1)
            // red like the sand
            .likes(SAVANNA, HOT, SANDY)
            .creator("Mr. Brain")
            .tier(6)
            .growth(1200)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(ORANGE, BUSH, METALLIC, SHINY, COTTON, COPPER)
            .mutation("stoneLily", "netherStoneLily")
            .register();
        CropsNHCrops.Dayflower = CropFamilies.vanillaFlower("dayflower", 0x236F8F, 0x3C8EB0)
            .drop(new ItemStack(Items.dye, 1, 6), 10_000)
            // Assuming it's essentially cornflower, invasive species,
            .likes(COLD, HILLS, SPARSE)
            .pools(BLUE, FLOWER, ADDICTIVE)
            .mutation("azureBluet", "blueOrchid")
            .register();
        CropsNHCrops.Diareed = CropBuilder.crop("diareed", 0x34BFA8, 0x8CF4E2)
            .drop(CropsNHItemList.diareed.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.diamond)
            .duplicationCatalyst("dustDiamond", 1)
            .duplicationCatalyst("gemDiamond", 1)
            // Gives you the gold shoulder
            .likes(COLD, SPARSE)
            .creator("Direwolf20")
            .tier(12)
            .growth(10_800)
            .soil(CropsNHSoilTypes.stone)
            .seedShape(SeedShape.grains)
            .renderShape(PlantRenderShape.X)
            .machineBreedingTier(VoltageIndex.MV)
            // coal because diamonds are just carbon
            .pools(BLUE, REED, CRYSTALLINE, FIERY, SHINY, COAL)
            .mutation("withereed", "evilOre")
            .register();
        CropsNHCrops.EvilOre = CropBuilder.crop("evilOre", 0x897B73, 0xEFEBE7)
            // TODO: CHECK IF WE WANT TO KEEP THE RAW QUARTS DROPS
            .drop(Materials.NetherQuartz.getDust(1), 66_66)
            .drop(Materials.CertusQuartz.getDust(1), 16_67)
            .drop(new ItemStack(Items.quartz, 1, 0), 16_67)
            .duplicationCatalyst("gemNetherQuartz", 1)
            .duplicationCatalyst("dustNetherQuartz", 1)
            .duplicationCatalyst("gemCertusQuartz", 1)
            .duplicationCatalyst("dustCertusQuartz", 1)
            // NETHER-quarts
            .likes(HOT, DRY, BiomeDictionary.Type.NETHER)
            .tier(8)
            .growth(1600)
            .soil(CropsNHSoilTypes.netherrack)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(WHITE, NETHER, CRYSTALLINE, EVIL, FIERY)
            .mutation("netherStoneLily", "soulSandLily")
            .register();
        CropsNHCrops.Ferrofern = CropBuilder.crop("ferrofern", 0xA1A1A1, 0xE6E6E6)
            .drop(CropsNHItemList.ferrofernLeaf.get(1), 100_00)
            .requirement(CropsNHSubSoilTypes.iron)
            .duplicationCatalyst("dustIron", 1)
            // iron tends to be easily found in mountains
            .likes(MOUNTAIN, HILLS)
            .tier(6)
            .growth(2800)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(GRAY, METALLIC, TENDRILLY, IRON)
            .mutation("stoneLily", "blackGraniteLily")
            .register();
        CropsNHCrops.Fertilia = CropBuilder.crop("fertilia", 0x554516, 0x6E6038)
            .drop(Materials.Calcite.getDust(1), 62_50)
            .drop(Materials.Phosphate.getDust(1), 12_50)
            .drop(Materials.Apatite.getDust(1), 12_50)
            .drop(CropsNHItemList.fertilizer.get(1), 12_50)
            .duplicationCatalyst(CropsNHItemList.fertilizer.get(1))
            .likes(SWAMP, WET, HOT)
            .tier(3)
            .growth(1800)
            .machineBreedingTier(VoltageIndex.MV)
            // TODO ADD PHOSPHORUS CROP AND ADD IT TO THE FERTILIA PARENT LIST
            // Corpse plant because it outputs bones, corium because cow manure
            .pools(BROWN, MUSHROOM, HEALING)
            .mutation("corium", "corpseplant")
            .register();
        CropsNHCrops.Flax = CropBuilder.crop("flax", 0x804D3E, 0xB76E5A)
            .drop(new ItemStack(Items.string, 1, 0), 100_00)
            .seeds("cropFlax")
            // flax is typically found in sedimentary soils.
            .likes(WET, RIVER, BEACH, SANDY)
            .creator("Eloraam")
            .tier(2)
            .growth(1200)
            .pools(BLUE, WHEAT, ADDICTIVE, SILK)
            .mutation("wheat", "azureBluet")
            .register();
        CropsNHCrops.Galvania = CropBuilder.crop("galvania", 0x6FDC5C, 0x9BE78D)
            .drop(CropsNHItemList.galvaniaLeaf.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.zinc)
            .duplicationCatalyst("dustZinc", 1)
            // probably won't rust
            .likes(DRY, HOT)
            .tier(6)
            .growth(1200)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(WHITE, BUSH, METALLIC)
            .mutation("tine", "ferrofern")
            .register();
        CropsNHCrops.Garnydinia = CropBuilder.crop("garnydinia", 0xA24141, 0xA3A341)
            // 45% garnet dust
            .drop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.GarnetRed, 1L), 22_50)
            .drop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.GarnetYellow, 1L), 22_50)
            // 45% garnet gem dust
            .drop(GTOreDictUnificator.get(OrePrefixes.gem, Materials.GarnetRed, 1L), 22_50)
            .drop(GTOreDictUnificator.get(OrePrefixes.gem, Materials.GarnetYellow, 1L), 22_50)
            // 5% garnet exquisite gem
            .drop(GTOreDictUnificator.get(OrePrefixes.gemExquisite, Materials.GarnetRed, 1L), 2_50)
            .drop(GTOreDictUnificator.get(OrePrefixes.gemExquisite, Materials.GarnetYellow, 1L), 2_50)
            // 5$ crushed pure ore
            .drop(GTOreDictUnificator.get(OrePrefixes.crushedPurified, Materials.GarnetRed, 1L), 2_50)
            .drop(GTOreDictUnificator.get(OrePrefixes.crushedPurified, Materials.GarnetYellow, 1L), 2_50)
            .subSoil(CropsNHSubSoilTypes.garnetGem)
            .duplicationCatalyst("dustGarnetRed", 1)
            .duplicationCatalyst("dustGarnetYellow", 1)
            .duplicationCatalyst("dustGarnetSand", 1)
            .duplicationCatalyst("gemGarnetRed", 1)
            .duplicationCatalyst("gemGarnetYellow", 1)
            .likes(SAVANNA, SANDY, MESA)
            .creator("moronwmachinegun")
            .tier(7)
            .growth(850)
            .soil(CropsNHSoilTypes.stone)
            .renderShape(PlantRenderShape.X)
            .mutation(
                m -> m.parents("diareed", "redstraw")
                    .machineOnly())
            .register();
        CropsNHCrops.Glowheat = CropBuilder.crop("glowheat", 0xABAB00, 0xD2D200)
            .drop(new ItemStack(Items.glowstone_dust, 1, 0), 100_00)
            .subSoil(CropsNHSubSoilTypes.glowstone)
            .duplicationCatalyst("dustGlowstone", 1)
            .likes(PLAINS, HOT, BiomeDictionary.Type.NETHER)
            .tier(4)
            .growth(1500)
            .stages(1, 7)
            .soil(CropsNHSoilTypes.netherrack)
            .dropChance(1.5f)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(YELLOW, NETHER, WHEAT, EMISSIVE, SHINY, CRYSTALLINE, POTION_INGREDIENT)
            .mutation("glowflower", "wheat")
            .register();
        CropsNHCrops.GodOfThunder = CropBuilder.crop("godOfThunder", 0x001E00, 0x0C290C)
            .drop(CropsNHItemList.thunderLeaf.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.thorium)
            .duplicationCatalyst("dustThorium", 1)
            .requirement(new MachineOnlyGrowthRequirement())
            // these plants are the result of one of Zeus's drunken rampages
            .likes(WASTELAND, SPARSE)
            .tier(9)
            .growth(3600)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.HV)
            .minSeedBedTier(VoltageIndex.HV)
            .mutation(
                m -> m.parents("bobsYerUncleRanks", "withereed")
                    .machineOnly())
            .register();
        CropsNHCrops.Hemp = CropBuilder.crop("hemp", 0x275600, 0xBBB49D)
            .drop(CropsNHItemList.hempStem.get(1), 100_00)
            .likes(DRY, SANDY)
            .creator("C0bra5")
            .tier(5)
            .growth(800)
            .stages(1, 5)
            .pools(GREEN, STEM, ADDICTIVE, SILK)
            .mutation("flax", "papyrus")
            .register();
        CropsNHCrops.Indigo = CropBuilder.crop("indigo", 0x6446BD, 0xA285EF)
            .drop(CropsNHItemList.indigoBlossom.get(1), 100_00)
            .seed(CropsNHItemList.indigoBlossom.get(1))
            // mostly rain forest.
            .likes(WET, LUSH)
            .creator("Eloraam")
            .tier(2)
            .growth(1200)
            .soil(CropsNHSoilTypes.dirtGrass)
            .seedShape(SeedShape.flower)
            .pools(BLUE, FLOWER)
            .mutation("dayflower", "blueOrchid")
            .register();
        CropsNHCrops.Iridine = CropBuilder.crop("iridine", 0xB3B3B3, 0xE8E8E8)
            .drop(CropsNHItemList.iridineFlower.get(1), 75_00)
            .subSoil(CropsNHSubSoilTypes.iridium)
            .duplicationCatalyst("dustIridium", 1)
            .requirement(new MachineOnlyGrowthRequirement())
            // white as it's flower
            .likes(SNOWY, COLD)
            .tier(12)
            .growth(7200)
            .soil(CropsNHSoilTypes.stone)
            .seedShape(SeedShape.flower)
            .renderShape(PlantRenderShape.X)
            .machineBreedingTier(VoltageIndex.IV)
            .minSeedBedTier(VoltageIndex.IV)
            .mutation(
                m -> m.parents("scheelinium", "platina")
                    .machineOnly())
            .register();
        CropsNHCrops.Lazulia = CropBuilder.crop("lazulia", 0x142EAF, 0x7497EA)
            .drop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Lapis, 1), 66_67)
            .drop(new ItemStack(Items.dye, 1, 4), 33_33)
            .subSoil(CropsNHSubSoilTypes.lapis)
            .duplicationCatalyst("gemLapis", 1)
            .duplicationCatalyst("dustLapis", 1)
            // used since the ancient times
            .likes(HOT, SANDY)
            .tier(7)
            .growth(2800)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(BLUE, CRYSTALLINE, SHINY, HEALING)
            .mutation("stoneLily", "indigo")
            .register();
        if (ModUtils.TwilightForest.isModLoaded()) {
            CropsNHCrops.Liveroot = CropBuilder.crop("liveroot", 0x877B00, 0xB9DA49)
                .drop(Materials.LiveRoot.getDust(1), 75_00)
                .drop(ItemList.TF_LiveRoot.get(1), 75_00)
                .duplicationCatalyst(Materials.LiveRoot.getDust(1))
                .duplicationCatalyst(ItemList.TF_LiveRoot.get(1))
                // twilight forest scary
                .likes(SPOOKY, BiomeDictionary.Type.DENSE)
                .creator("Benimatic")
                .tier(8)
                .growth(4800)
                .pools(BROWN, TWILIGHT_FOREST, ROOT, TENDRILLY, WOODEN)
                .mutation("torchberry", "bonsaiDarkOak")
                .register();
        }
        CropsNHCrops.MagicalNightshade = CropBuilder.crop("magicalNightshade", 0x20001B, 0xB4009C)
            .drop(CropsNHItemList.magicEssence.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.shadowmetal)
            .duplicationCatalyst(CropsNHItemList.magicEssence.get(1))
            .seed(CropsNHItemList.magicEssence.get(1))
            .requirement(new MachineOnlyGrowthRequirement())
            // this tag combination shouldn't exist.
            .likes(MAGICAL, COLD)
            .creator("bartimaeusnek")
            .tier(13)
            .growth(23436)
            .seedShape(SeedShape.magic)
            .enchantedSeed()
            // the default drop chance of a tier 13 crop, reduced by a quarter
            .dropChance((float) Math.pow(0.95, 13) * 0.75f)
            .machineBreedingTier(VoltageIndex.HV)
            .minSeedBedTier(VoltageIndex.HV)
            .mutation(
                m -> m.parents("primordialBerry", "manaBean", "cinderpearl", "shimmerleaf")
                    .machineOnly())
            .register();
        CropsNHCrops.Malaxia = CropBuilder.crop("malaxia", 0xB26003, 0xD77106)
            .drop(Materials.Copper.getDustTiny(1), 100_00)
            .requirement(CropsNHSubSoilTypes.copper)
            .duplicationCatalyst("dustCopper", 1)
            .likes(PLAINS, LUSH)
            .tier(6)
            .growth(2800)
            .soil(CropsNHSoilTypes.stone)
            .pools(ORANGE, BUSH, LEAFY, METALLIC, COPPER)
            .mutation("orangeTulip", "pumpkin", "stoneLily")
            .register();
        CropsNHCrops.Micadia = CropBuilder.crop("micadia", 0xD5CCC3, 0xF4F2EF)
            .drop(CropsNHItemList.micadiaFlower.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.mica)
            .duplicationCatalyst("dustMica", 1)
            // Often found in gravely hills
            .likes(HILLS, SPARSE)
            .creator("bartimaeusnek")
            .tier(9)
            .growth(1800)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .mutation(
                m -> m.parents("tine", "bauxia")
                    .machineOnly())
            .register();
        CropsNHCrops.MilkWart = CropBuilder.crop("milkWart", 0xA8A8A8, 0xECECEC)
            .drop(CropsNHItemList.milkWart.get(1), 100_00)
            // cows like to eat grass
            .likes(PLAINS, LUSH)
            .creator("Mr. Brain")
            .tier(6)
            .growth(2400)
            .seedShape(SeedShape.spore)
            .pools(WHITE, EDIBLE, COW, HEALING)
            .mutation("corium", "netherwart")
            .register();
        CropsNHCrops.Necrobloom = CropBuilder.crop("necrobloom", 0x1D1225, 0x4D4D4D)
            .drop(CropsNHItemList.poisonPowder.get(1), 95_00)
            .drop(new ItemStack(Items.dye, 1, 5), 5_00)
            // brings ruin
            .likes(DEAD, WASTELAND)
            .tier(3)
            .growth(1800)
            .seedShape(SeedShape.flower)
            .weedsAbove(8)
            .onEntityCollision(CropBehaviors::poisonOnContact)
            .pools(PURPLE, FLOWER, POISONOUS, DANGEROUS)
            .mutation("spidernip", "purpleTulip")
            .register();
        CropsNHCrops.Nickelback = CropBuilder.crop("nickelback", 0x7E81AD, 0xB7B8E9)
            .drop(CropsNHItemList.nickelbackLeaf.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.nickel)
            .duplicationCatalyst("dustNickel", 1)
            // Look at this photograph, every time I do, it makes me cry :(
            .likes(PLAINS, LUSH)
            .flavour(Reference.MOD_ID + "_crops.nickelback.flavour")
            .tier(5)
            .growth(1000)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(GRAY, METALLIC, FIERY)
            .mutation("ferrofern", "coppon")
            .register();
        CropsNHCrops.OilBerry = CropBuilder.crop("oilBerry", 0x0A0A0A, 0x333333)
            .drop(CropsNHItemList.oilBerry.get(2), 100_00)
            // Dinos need to die if we are going to get more of this stuff...
            .likes(HOT, WASTELAND)
            .creator("Spacetoad")
            .tier(8)
            .growth(1200)
            .soil(CropsNHSoilTypes.oil)
            .machineBreedingTier(VoltageIndex.MV)
            .mutation(
                m -> m.parents("soulSandLily", "withereed")
                    .machineOnly())
            .register();
        CropsNHCrops.Olivia = CropBuilder.crop("olivia", 0x79CD79, 0x96FF96)
            .drop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Olivine, 1L), 75_00)
            .drop(GTOreDictUnificator.get(OrePrefixes.gem, Materials.Olivine, 1L), 25_00)
            .subSoil(CropsNHSubSoilTypes.olivine)
            .duplicationCatalyst("gemOlivine", 1)
            .duplicationCatalyst("dustOlivine", 1)
            // olives like hilly climates with usually dry seasons
            .likes(HILLS, DRY)
            .tier(2)
            .growth(400)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .mutation(
                m -> m.parents("endStoneLily", "evilOre")
                    .machineOnly())
            .register();
        CropsNHCrops.Osmianth = CropBuilder.crop("osmianth", 0x2525BC, 0x2E2EEC)
            .drop(CropsNHItemList.osmianthFlower.get(1), 25_00)
            .subSoil(CropsNHSubSoilTypes.osmium)
            .duplicationCatalyst("dustOsmium", 1)
            .requirement(new MachineOnlyGrowthRequirement())
            // da be di da be da, da be di da be da..
            .likes(OCEAN, RIVER)
            .tier(12)
            .growth(7200)
            .soil(CropsNHSoilTypes.stone)
            .seedShape(SeedShape.flower)
            .renderShape(PlantRenderShape.X)
            .machineBreedingTier(VoltageIndex.IV)
            .minSeedBedTier(VoltageIndex.IV)
            .mutation(
                m -> m.parents("platina", "scheelinium")
                    .machineOnly())
            .register();
        CropsNHCrops.Papyrus = CropBuilder.crop("papyrus", 0xA3A3A3, 0xEAEAEA)
            .drop(new ItemStack(Items.paper, 1, 0), 100_00)
            .likes(WET, SWAMP, JUNGLE)
            .creator("bartimaeusnek")
            .tier(5)
            .growth(450)
            .stages(1, 3)
            .renderShape(PlantRenderShape.X)
            // leather bound books, birch has a paper-like bark
            .pools(WHITE, STEM)
            .mutation("corium", "bonsaiBirch")
            .register();
        CropsNHCrops.Platina = CropBuilder.crop("platina", 0xDADAAB, 0xF7F7C7)
            .drop(CropsNHItemList.platinaLeaf.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.platinum)
            .duplicationCatalyst("dustPlatinum", 1)
            .requirement(new MachineOnlyGrowthRequirement())
            // stolen from bees
            .likes(HOT, SANDY)
            .tier(11)
            .growth(6600)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.HV)
            .minSeedBedTier(VoltageIndex.HV)
            .mutation(
                m -> m.parents("diareed", "rubyne")
                    .machineOnly())
            .register();
        CropsNHCrops.Plumbilia = CropBuilder.crop("plumbilia", 0x503950, 0x6D4E6D)
            .drop(CropsNHItemList.plumbiliaLeaf.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.lead)
            .duplicationCatalyst("dustLead", 1)
            // I was going to choose plums as a basis, but the stuff will literally grow everywhere
            // it's actually one of the first domesticated crops in human history.
            .likes(SAVANNA, PLAINS)
            .creator("KingLemming")
            .tier(6)
            .growth(1200)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(PURPLE, METALLIC, LEAD, DENSE, TENDRILLY)
            .mutation("coppon", "withereed")
            .register();
        CropsNHCrops.Plumbshade = CropBuilder.crop("plumbshade", 0x523B52, 0x6D4E6D)
            .drop(Materials.Lead.getDustTiny(1), 100_00)
            .requirement(CropsNHSubSoilTypes.lead)
            .duplicationCatalyst("dustLead", 1)
            // hibiscus likes warm temperate/tropical areas.
            .likes(HOT, WET)
            .tier(6)
            .growth(2800)
            .soil(CropsNHSoilTypes.stone)
            .pools(PURPLE, BUSH, DENSE, LEAFY, METALLIC, LEAD)
            .mutation("azureBluet", "malaxia", "blackGraniteLily")
            .register();
        CropsNHCrops.PurpleTulip = CropFamilies.vanillaFlower("purpleTulip", 0x8230B2, 0xA453CE)
            .drop(new ItemStack(Items.dye, 1, 5), 10_000)
            // netherlands.jpg
            .likes(WET, PLAINS)
            .pools(PURPLE, FLOWER, TULIP)
            .mutation("redTulip", "blueOrchid")
            .register();
        CropsNHCrops.Pyrolusium = CropBuilder.crop("pyrolusium", 0xB51900, 0xCF1E11)
            .drop(CropsNHItemList.pyrolusiumLeaf.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.manganese)
            .duplicationCatalyst("dustManganese", 1)
            // manganese burns very bright
            .likes(BiomeDictionary.Type.NETHER, HOT)
            .flavour(CropBehaviors::pyrolusiumFlavour)
            .tier(12)
            .growth(2400)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(RED, METALLIC, BUSH)
            .mutation("nickelback", "bauxia")
            .register();
        CropsNHCrops.Reactoria = CropBuilder.crop("reactoria", 0x565301, 0x989309)
            .drop(CropsNHItemList.reactoriaLeaf.get(1), 75_00)
            .drop(CropsNHItemList.reactoriaStem.get(1), 25_00)
            .subSoil(CropsNHSubSoilTypes.uranium)
            .duplicationCatalyst("dustUranium", 1)
            .duplicationCatalyst("dustUranium235", 1)
            .duplicationCatalyst("dustUraninite", 2)
            .requirement(new MachineOnlyGrowthRequirement())
            // very "spicy"
            .likes(COLD, HOT)
            .tier(12)
            .growth(4800)
            .soil(CropsNHSoilTypes.stone)
            .seedShape(SeedShape.grains)
            .machineBreedingTier(VoltageIndex.EV)
            .minSeedBedTier(VoltageIndex.EV)
            .mutation(
                m -> m.parents("titania", "godOfThunder")
                    .machineOnly())
            .register();
        CropsNHCrops.RedStraw = CropBuilder.crop("redstraw", 0x6C4B17, 0xD45555)
            .drop(new ItemStack(Items.redstone, 1, 0), 100_00)
            .minLight(5)
            .maxLight(12)
            .subSoil(CropsNHSubSoilTypes.redstone)
            .duplicationCatalyst("dustRedstone", 1)
            .likes(PLAINS, HOT)
            .creator("raa1337")
            .tier(6)
            .growth(1500)
            .stages(1, 7)
            .dropChance(0.85f)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(RED, STONE, WHEAT, EMISSIVE, POTION_INGREDIENT)
            .mutation("netherStoneLily", "wheat")
            .register();
        CropsNHCrops.Rubyne = CropBuilder.crop("rubyne", 0x931212, 0xA64747)
            .drop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Ruby, 1L), 75_00)
            .drop(GTOreDictUnificator.get(OrePrefixes.gem, Materials.Ruby, 1L), 25_00)
            .subSoil(CropsNHSubSoilTypes.ruby)
            .duplicationCatalyst("gemRuby", 1)
            .duplicationCatalyst("dustRuby", 1)
            // Rhubarb is native to the mountains in Central Asia (Tibet, Siberia, etc.). It was originally cutltivated
            // by
            // Russians along the Volga River.
            .likes(MOUNTAIN, RIVER, COLD)
            .tier(8)
            .growth(1200)
            .soil(CropsNHSoilTypes.stone)
            .renderShape(PlantRenderShape.X)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(RED, CRYSTALLINE, SHINY)
            .mutation("redstraw", "diareed")
            .register();
        CropsNHCrops.SaltyRoot = CropBuilder.crop("saltyRoot", 0x707070, 0xC7C7C1)
            .drop(CropsNHItemList.saltyRoot.get(1), 100_00)
            .likes(DRY, SANDY)
            .duplicationCatalyst("dustSalt", 1)
            .duplicationCatalyst("gemSalt", 1)
            .duplicationCatalyst("dustRockSalt", 1)
            .duplicationCatalyst("gemRockSalt", 1)
            .duplicationCatalyst("dustSaltpeter", 1)
            .likes(DRY, DEAD)
            .creator("GlodBlock")
            .flavour(Reference.MOD_ID + "_crops.saltyRoot.flavour")
            .tier(4)
            .growth(1600)
            .stages(1, 3)
            // if this ever gets changed, the ore multiplication recipe needs to also be changed.
            .dropChance(4.0F)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(GRAY, ROOT, SALTPETER)
            .mutation("sugarBeet", "canola")
            .register();
        CropsNHCrops.Sapphirum = CropBuilder.crop("sapphirum", 0x47478F, 0x5959B3)
            .drop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sapphire, 1L), 75_00)
            .drop(GTOreDictUnificator.get(OrePrefixes.gem, Materials.Sapphire, 1L), 25_00)
            .subSoil(CropsNHSubSoilTypes.sapphire)
            .duplicationCatalyst("dustSapphire", 1)
            .duplicationCatalyst("gemSapphire", 1)
            .likes(OCEAN, WET, COLD)
            .tier(4)
            .growth(800)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(BLUE, CRYSTALLINE, SHINY)
            .mutation("evilOre", "lazulia")
            .register();
        CropsNHCrops.Scheelinium = CropBuilder.crop("scheelinium", 0x313CA3, 0x232C9E)
            .drop(CropsNHItemList.scheeliniumLeaf.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.tungsten)
            .duplicationCatalyst("dustTungsten", 1)
            .duplicationCatalyst("dustTungstate", 1)
            .duplicationCatalyst("dustScheelite", 1)
            .requirement(new MachineOnlyGrowthRequirement())
            .likes(OCEAN, WET, COLD)
            .tier(12)
            .growth(2400)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.EV)
            .minSeedBedTier(VoltageIndex.EV)
            .mutation(
                m -> m.parents("titania", "pyrolusium", "endStoneLily")
                    .machineOnly())
            .register();
        CropsNHCrops.Silviscus = CropBuilder.crop("silviscus", 0xB3B3D0, 0xD0D0F1)
            .drop(Materials.Silver.getDustTiny(1), 100_00)
            .requirement(CropsNHSubSoilTypes.silver)
            .duplicationCatalyst("dustSilver", 1)
            // movie reference
            .likes(COLD, SNOWY, SPOOKY)
            .tier(8)
            .growth(3700)
            .soil(CropsNHSoilTypes.stone)
            .pools(WHITE, BUSH, LEAFY, METALLIC, SILVER)
            .mutation("cassitine", "malaxia", "marbleLily")
            .register();
        CropsNHCrops.SpaceFlower = CropBuilder.crop("spaceFlower", 0xFF0000, 0xFFC200)
            .drop(CropsNHItemList.spaceFlower.get(1), 100_00)
            .when(ModUtils.GalacticraftCore, b -> b.subSoil(CropsNHSubSoilTypes.space))
            .requirement(new MachineOnlyGrowthRequirement())
            // moon
            .likes(COLD, DRY, DEAD)
            .creator("bartimaeusnek")
            .tier(13)
            .growth(15_000)
            .seedShape(SeedShape.flower)
            .enchantedSeed()
            .renderShape(PlantRenderShape.X)
            .machineBreedingTier(VoltageIndex.IV)
            .minSeedBedTier(VoltageIndex.IV)
            .mutation(
                m -> m.parents("endStoneLily", "titania")
                    .machineOnly())
            .register();
        CropsNHCrops.StarWart = CropBuilder.crop("starWart", 0x8985BE, 0xFFFFFF)
            .drop(CropsNHItemList.starWart.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.netherStar)
            .duplicationCatalyst("dustNetherStar", 1)
            .duplicationCatalyst("netherStar", 1)
            .duplicationCatalyst(new ItemStack(Items.skull, 3, 1))
            .requirement(new MachineOnlyGrowthRequirement())
            .likes(BiomeDictionary.Type.NETHER, DEAD)
            .tier(12)
            .growth(7200)
            .soil(CropsNHSoilTypes.stone)
            .seedShape(SeedShape.spore)
            .machineBreedingTier(VoltageIndex.EV)
            .minSeedBedTier(VoltageIndex.EV)
            .mutation(
                m -> m.parents("withereed", "titania", "godOfThunder")
                    .machineOnly())
            .register();
        CropsNHCrops.Stargatium = CropBuilder.crop("stargatium", 0x333333, 0x555555)
            .drop(CropsNHItemList.stargatiumLeaf.get(1), 75_00)
            .drop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Endstone, 1L), 25_00)
            .subSoil(CropsNHSubSoilTypes.naquadah)
            .duplicationCatalyst("dustNaquadah", 1)
            .duplicationCatalyst("dustNaquadahOxideMixture", 1)
            .duplicationCatalyst("dustNaquadria", 1)
            .duplicationCatalyst("dustNaquadriaOxideMixture", 1)
            .requirement(new MachineOnlyGrowthRequirement())
            // 404 not found
            .likes(LUSH, WASTELAND)
            .tier(12)
            .growth(7200)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.IV)
            .minSeedBedTier(VoltageIndex.IV)
            .mutation(
                m -> m.parents("iridine", "trollplant")
                    .machineOnly())
            .register();
        if (ModUtils.TwilightForest.isModLoaded()) {
            CropsNHCrops.Steeleafranks = CropBuilder.crop("steeleafranks", 0x163916, 0x327F32)
                .drop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Steeleaf, 1L), 25_00)
                .drop(CropsNHUtils.getModItem(ModUtils.TwilightForest, "item.steeleafIngot", 1, 0), 25_00)
                .duplicationCatalyst("dustSteeleaf", 1)
                .duplicationCatalyst("ingotSteeleaf", 1)
                .subSoil(CropsNHSubSoilTypes.steeleaf)
                .likes(SPOOKY, DEAD)
                .creator("Benimatic")
                .tier(10)
                .growth(6000)
                .soil(CropsNHSoilTypes.stone)
                .machineBreedingTier(VoltageIndex.MV)
                .pools(GREEN, TWILIGHT_FOREST, METALLIC, TENDRILLY, IRON)
                .mutation("torchberry", "knightmetalOreBerry")
                .register();
        }
        CropsNHCrops.StickyCane = CropBuilder.crop("stickyCane", 0x8DB560, 0xD9D891)
            // TODO: REPLACE WITH RESIN REPLACEMENT DOWN THE LINE
            .drop(ItemList.IC2_Resin.get(1L), 10_000)
            .duplicationCatalyst(ItemList.IC2_Resin.get(1L))
            // going to treat it like sugar cane
            .likes(WET, HOT, SANDY)
            .tier(4)
            .growth(200)
            .soil(CropsNHSoilTypes.sugarcane)
            .seedShape(SeedShape.grains)
            .renderShape(PlantRenderShape.X)
            .pools(GREEN, REED, STICKY)
            .mutation("bonsaiJungle", "sugarCane")
            .register();
        CropsNHCrops.Thiosulfine = CropBuilder.crop("thiosulfine", 0xA49035, 0xE1B33D)
            .drop(CropsNHItemList.thiosulfineFlower.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.sulfur)
            .duplicationCatalyst("dustSulfur", 1)
            // volcanic areas tend to be very sulfur rich
            .likes(BiomeDictionary.Type.NETHER, HOT)
            .tier(6)
            .growth(1200)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(YELLOW, FLOWER, DANGEROUS, SULFUR)
            .mutation("galvania", "plumbilia")
            .register();
        CropsNHCrops.Tine = CropBuilder.crop("tine", 0x7D7D7D, 0xACACAC)
            .drop(CropsNHItemList.tineTwig.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.tin)
            .duplicationCatalyst("dustTin", 1)
            .duplicationCatalyst("dustCassiterite", 1)
            .duplicationCatalyst("dustCassiteriteSand", 1)
            .likes(MOUNTAIN, HILLS)
            .creator("Gregorius Techneticies")
            .tier(5)
            .growth(1000)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(GRAY, STEM, METALLIC, SHINY, WOODEN, TIN)
            .mutation("stoneLily", "bonsaiSpruce")
            .register();
        CropsNHCrops.Titania = CropBuilder.crop("titania", 0x815E8D, 0xDCA0F0)
            .drop(CropsNHItemList.titaniaLeaf.get(1), 100_00)
            .subSoil(CropsNHSubSoilTypes.titanium)
            .duplicationCatalyst("dustTitanium", 1)
            .duplicationCatalyst("dustRutile", 2)
            .duplicationCatalyst("dustIlmenite", 2)
            .requirement(new MachineOnlyGrowthRequirement())
            .likes(HOT, SAVANNA)
            .flavour(Reference.MOD_ID + "_crops.titania.flavour")
            .tier(9)
            .growth(1800)
            .soil(CropsNHSoilTypes.stone)
            .machineBreedingTier(VoltageIndex.EV)
            .minSeedBedTier(VoltageIndex.EV)
            .mutation(
                m -> m.parents("bauxia", "redstraw")
                    .machineOnly())
            .register();
        CropsNHCrops.Transformium = CropBuilder.crop("transformium", 0x2A0040, 0x6D1566)
            .drop(CropsNHItemList.uuaBerry.get(1), 90_00)
            .drop(CropsNHItemList.uumBerry.get(1), 10_00)
            .requirement(new MachineOnlyGrowthRequirement())
            .likes(END, COLD)
            .tier(12)
            .growth(7200)
            .machineBreedingTier(VoltageIndex.EV)
            .minSeedBedTier(VoltageIndex.EV)
            .mutation(
                m -> m.parents("trollplant", "bauxia", "titania")
                    .machineOnly())
            .register();
        CropsNHCrops.Trollplant = CropBuilder.crop("trollplant", 0x000000, 0xFFFFFF)
            // TODO: WHEN IC2 GETS REMOVED, UPDATE PLANTBALL AND SCRAP DROPS
            .drop(GTOreDictUnificator.get(OrePrefixes.gem, Materials.Spinel, 1), 62_50)
            .drop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Plutonium241, 1), 12_50)
            .drop(ItemList.IC2_Plantball.get(1), 12_50)
            .drop(ItemList.IC2_Scrap.get(1), 12_50)
            // all of these are intended to be a bit trollish in one way or another
            // just because it requires bricks as a soil
            .duplicationCatalyst(new ItemStack(Items.brick, 2))
            // one more than the extruder number of bolts you get for one extruder recipe
            .duplicationCatalyst("screwFoolsRuby", 5)
            // A somewhat easier "streamed-lined" output since they make a lot o fthis stuff.
            .duplicationCatalyst(ItemList.IC2_Scrap.get(8))
            // a certain ogre really likes this place
            .likes(SWAMP, SPOOKY)
            .flavour(Reference.MOD_ID + "_crops.trollplant.flavour")
            .tier(6)
            .growth(4800)
            .stages(1, 8)
            .soil(CropsNHSoilTypes.brick)
            .seedShape(SeedShape.troll)
            .machineBreedingTier(VoltageIndex.EV)
            .mutation(
                m -> m.parents("goldfish", "reactoria", "fertilia")
                    .requirement(new MachineBreedingCatalystRequirement().addOreDict("dustSmallPlutonium241", 1))
                    .requirement(new MachineBreedingCatalystRequirement().addOreDict("stickRedAlloy", 64))
                    .requirement(new MachineBreedingCatalystRequirement().addItem(new ItemStack(Blocks.brick_block, 5)))
                    .machineOnly())
            .register();
        CropsNHCrops.Withereed = CropBuilder.crop("withereed", 0x161616, 0x2C2C2C)
            .drop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 1), 66_67)
            .drop(new ItemStack(Items.coal, 1, 0), 33_33)
            // wither skull: sqrt(6.66) / 2
            .drop(new ItemStack(Items.skull, 1, 1), 1_29)
            // skeleton skull: sqrt(6.66)
            .drop(new ItemStack(Items.skull, 1, 0), 2_58)
            .subSoil(CropsNHSubSoilTypes.coal)
            // wither skull
            .duplicationCatalyst(new ItemStack(Items.skull, 1, 1))
            .duplicationCatalyst("gemCoal", 1)
            .duplicationCatalyst("gemCoal", 1)
            .likes(DEAD, SPOOKY)
            .creator("CovertJaguar")
            .tier(8)
            .growth(4800)
            .soil(CropsNHSoilTypes.stone)
            .renderShape(PlantRenderShape.X)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(BLACK, REED, FIERY, UNDEAD, COAL, SULFUR)
            .mutation("basaltLily", "blackGraniteLily")
            .register();
    }
}
