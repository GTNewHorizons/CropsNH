package com.gtnewhorizon.cropsnh.crops.definitions;

import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ALIEN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLACK;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BROWN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DANGEROUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DARK;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.DENSE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GRAY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.NETHER;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.OIL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.RED;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.STONE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WATERY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.WHITE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.YELLOW;
import static net.minecraftforge.common.BiomeDictionary.Type.COLD;
import static net.minecraftforge.common.BiomeDictionary.Type.DRY;
import static net.minecraftforge.common.BiomeDictionary.Type.END;
import static net.minecraftforge.common.BiomeDictionary.Type.HILLS;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.MOUNTAIN;
import static net.minecraftforge.common.BiomeDictionary.Type.RIVER;
import static net.minecraftforge.common.BiomeDictionary.Type.SANDY;
import static net.minecraftforge.common.BiomeDictionary.Type.SPOOKY;
import static net.minecraftforge.common.BiomeDictionary.Type.SWAMP;
import static net.minecraftforge.common.BiomeDictionary.Type.WET;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.data.CropFamilies;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.Materials;
import gregtech.api.enums.VoltageIndex;

public final class StoneLilyCrops {

    private StoneLilyCrops() {}

    public static void register() {
        // gotta check if the block was registered since it's a config.
        Block efrStone = GameRegistry.findBlock(ModUtils.EtFuturumRequiem.ID, "stone");
        if (ModUtils.Botania.isModLoaded() || ModUtils.Chisel.isModLoaded() || efrStone != null) {
            CropsNHCrops.AndesiteLily = CropFamilies.stoneLily("andesite", 0x6B6B6A, 0x989896)
                .when(
                    ModUtils.Botania.isModLoaded() || ModUtils.EtFuturumRequiem.isModLoaded(),
                    b -> b.drop("stoneAndesite", 100_00),
                    // gotta check if the block was registered since it's a config.
                    b -> b.when(
                        ModUtils.Chisel.isModLoaded() && efrStone != null,
                        c -> c.drop(new ItemStack(efrStone, 1, 5), 100_00)))
                .subSoil(CropsNHSubSoilTypes.modernAndesite)
                .likes(MOUNTAIN, HILLS)
                .pools(GRAY, STONE)
                .mutation("stoneLily", "clayLily")
                .register();
            CropsNHCrops.DioriteLily = CropFamilies.stoneLily("diorite", 0x909091, 0xCECED0)
                .when(
                    ModUtils.Botania.isModLoaded() || ModUtils.EtFuturumRequiem.isModLoaded(),
                    b -> b.drop("stoneDiorite", 100_00),
                    // gotta check if the block was registered since it's a config.
                    b -> b.when(
                        ModUtils.Chisel.isModLoaded() && efrStone != null,
                        c -> c.drop(new ItemStack(efrStone, 1, 3), 100_00)))
                .subSoil(CropsNHSubSoilTypes.modernDiorite)
                .likes(MOUNTAIN, HILLS)
                .pools(WHITE, STONE)
                .mutation("stoneLily", "marbleLily")
                .register();
            CropsNHCrops.GraniteLily = CropFamilies.stoneLily("granite", 0x82675B, 0xBA9583)
                .when(
                    ModUtils.Chisel.isModLoaded() || ModUtils.Botania.isModLoaded(),
                    b -> b.drop("stoneGranite", 100_00),
                    // gotta check if the block was registered since it's a config.
                    b -> b.when(
                        ModUtils.EtFuturumRequiem.isModLoaded() && efrStone != null,
                        c -> c.drop(new ItemStack(efrStone, 1, 1), 100_00)))
                .subSoil(CropsNHSubSoilTypes.modernGranite)
                .likes(MOUNTAIN, HILLS)
                .pools(RED, STONE)
                .mutation("blackGraniteLily", "redGraniteLily")
                .register();
        }
        if (ModUtils.EtFuturumRequiem.isModLoaded() && ModUtils.NewHorizonsCoreMod.isModLoaded()) {
            CropsNHCrops.DeepslateLily = CropFamilies.stoneLily("deepslate", 0x393939, 0x575757)
                .drop(ModUtils.NewHorizonsCoreMod.getStack("DeepslateDust", 9, 0), 100_00)
                .subSoil(CropsNHSubSoilTypes.deepslate)
                .likes(MOUNTAIN, HILLS)
                .pools(BLACK, DARK, DENSE, STONE)
                .mutation("tuffLily", "blackGraniteLily")
                .register();
            CropsNHCrops.TuffLily = CropFamilies.stoneLily("tuff", 0x57574D, 0x95978D)
                .drop(ModUtils.NewHorizonsCoreMod.getStack("TuffDust", 9, 0), 100_00)
                .subSoil(CropsNHSubSoilTypes.tuff)
                .likes(MOUNTAIN, HILLS)
                .pools(GRAY, DARK, STONE)
                .mutation("blackGraniteLily", "andesiteLily")
                .register();
        }
        CropsNHCrops.StoneLily = CropFamilies.stoneLily("stone", 0x919191, 0xCDCDCD)
            .drop(Materials.Stone.getDust(9), 100_00)
            .subSoil(CropsNHSubSoilTypes.stone)
            .likes(MOUNTAIN, HILLS)
            .pools(GRAY, STONE)
            .mutation("vine", "pumpkin")
            .register();
        CropsNHCrops.SandLily = CropFamilies.stoneLily("sand", 0x87836B, 0xC4BD97)
            .drop(new ItemStack(Blocks.sand, 4), 100_00)
            .subSoil(CropsNHSubSoilTypes.sand)
            .likes(SANDY, DRY, HOT)
            .pools(YELLOW, STONE)
            .mutation("stoneLily", "cactus")
            .register();
        CropsNHCrops.ClayLily = CropFamilies.stoneLily("clay", 0x666B7F, 0xA5A9B9)
            .drop(new ItemStack(Items.clay_ball, 1, 0), 100_00)
            .subSoil(CropsNHSubSoilTypes.clay)
            .duplicationCatalyst("dustClay", 1)
            .duplicationCatalyst("itemClay", 1)
            .likes(WET, RIVER, SWAMP)
            .creator("C0bra5")
            // slower growth because it's used for passive alu in the early game
            .growth(850)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(GRAY, WATERY, STONE)
            .mutation("stoneLily", "waterLily")
            .register();
        CropsNHCrops.NetherStoneLily = CropFamilies.stoneLily("netherStone", 0x911717, 0xC21F1F)
            .drop(Materials.Netherrack.getDust(9), 100_00)
            .subSoil(CropsNHSubSoilTypes.netherrack)
            .likes(BiomeDictionary.Type.NETHER, HOT, DRY)
            .pools(RED, NETHER, STONE)
            .mutation("stoneLily", "netherwart")
            .register();
        CropsNHCrops.SoulSandLily = CropFamilies.stoneLily("soulSand", 0x281A12, 0x534034)
            .drop(new ItemStack(Blocks.soul_sand, 1), 25_00)
            .subSoil(CropsNHSubSoilTypes.soulSand)
            .duplicationCatalyst(new ItemStack(Blocks.soul_sand, 1))
            .likes(BiomeDictionary.Type.NETHER, SPOOKY, SANDY)
            .creator("C0bra5")
            .tier(2)
            .growth(2000)
            .machineBreedingTier(VoltageIndex.HV)
            .pools(BROWN, NETHER, DANGEROUS, STONE, OIL)
            .mutation("netherStoneLily", "sandLily")
            .register();
        CropsNHCrops.EndStoneLily = CropFamilies.stoneLily("endStone", 0xABA67E, 0xDFD9A5)
            .drop(Materials.Endstone.getDust(2), 100_00)
            .subSoil(CropsNHSubSoilTypes.endStone)
            .duplicationCatalyst("dustEndstone", 1)
            .likes(END, DRY, COLD)
            .growth(850)
            .machineBreedingTier(VoltageIndex.MV)
            .pools(YELLOW, ALIEN, STONE)
            .mutation("stoneLily", "enderbloom")
            .register();
        CropsNHCrops.RedGraniteLily = CropFamilies.stoneLily("redGranite", 0x871C52, 0xD42C80)
            .drop(Materials.GraniteRed.getDust(9), 100_00)
            .subSoil(CropsNHSubSoilTypes.redGranite)
            .likes(MOUNTAIN, HILLS)
            .pools(RED, STONE)
            .mutation("stoneLily", "poppy")
            .register();
        CropsNHCrops.BlackGraniteLily = CropFamilies.stoneLily("blackGranite", 0x000000, 0x0A0A0A)
            .drop(Materials.GraniteBlack.getDust(9), 100_00)
            .subSoil(CropsNHSubSoilTypes.blackGranite)
            .likes(MOUNTAIN, HILLS)
            .pools(BLACK, DARK, STONE)
            .mutation("stoneLily", "basaltLily")
            .register();
        CropsNHCrops.BasaltLily = CropFamilies.stoneLily("basalt", 0x080808, 0x181818)
            .drop(Materials.Basalt.getDust(9), 100_00)
            .subSoil(CropsNHSubSoilTypes.basalt)
            .likes(MOUNTAIN, HILLS)
            .pools(BLACK, DARK, STONE)
            .mutation("stoneLily", "inkbloom")
            .register();
        CropsNHCrops.MarbleLily = CropFamilies.stoneLily("marble", 0xC8C8C8, 0xF0F0F0)
            .drop(Materials.Marble.getDust(9), 100_00)
            .subSoil(CropsNHSubSoilTypes.marble)
            .likes(MOUNTAIN, HILLS)
            .pools(WHITE, STONE)
            .mutation("stoneLily", "oxeyeDaisy")
            .register();
    }
}
