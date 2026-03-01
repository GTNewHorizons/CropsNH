package com.gtnewhorizon.cropsnh.loaders.gtrecipes;

import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTRecipeBuilder.WILDCARD;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.TierEU;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTScannerResult;

public class ScannerLoader extends BaseGTRecipeLoader {

    public static final int SEED_SCAN_EUT = (int) TierEU.RECIPE_LV;
    public static final int SEED_SCAN_DURATION = 8 * SECONDS;

    public static void postInit() {
        ItemStack output = CropsNHCrops.Carrot.getSeedItem(SeedStats.DEFAULT_ANALYZED);
        output.stackSize = 1;
        output.setStackDisplayName(StatCollector.translateToLocal(Reference.MOD_ID + "_nei.scanned_seed"));
        // add fake recipe
        GTValues.RA.stdBuilder()
            .eut(SEED_SCAN_EUT)
            .duration(SEED_SCAN_DURATION)
            .itemInputs(new ItemStack(CropsNHItems.genericSeed, 1, WILDCARD))
            .itemOutputs(output)
            .ignoreCollision()
            .fake()
            .addTo(RecipeMaps.scannerFakeRecipes);
        // register handler
        RecipeMaps.scannerHandlers.addFirst(ScannerLoader::scanCropsNHSeed);
    }

    public static @Nullable GTScannerResult scanCropsNHSeed(@Nonnull MetaTileEntity aScanner, @Nonnull ItemStack aInput,
        @Nullable ItemStack aSpecialSlot, @Nullable FluidStack aFluid) {
        if (CropRegistry.instance.get(aInput, false) == null) return null;
        ISeedStats stats = SeedStats.getStatsFromStack(aInput);
        if (stats == null) return null;
        ItemStack output = CropsNHUtils.copyStackWithSize(aInput, 1);
        int eut, duration;
        if (stats.isAnalyzed()) {
            eut = 1;
            duration = 1;
        } else {
            stats.setAnalyzed(true);
            // would not pass the crop registry check if tag is null, so no need to check/default.
            stats.writeToNBT(output.getTagCompound());
            eut = SEED_SCAN_EUT;
            duration = SEED_SCAN_DURATION;
        }
        return new GTScannerResult(eut, duration, 1, 0, 0, output);
    }
}
