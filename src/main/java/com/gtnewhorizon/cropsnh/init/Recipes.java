package com.gtnewhorizon.cropsnh.init;

import java.util.Arrays;
import java.util.List;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Mods;
import gregtech.api.enums.TierEU;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTRecipe;
import gregtech.loaders.postload.recipes.FluidExtractorRecipes;
import gtPlusPlus.core.item.ModItems;
import gtPlusPlus.core.util.minecraft.ItemUtils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;

import cpw.mods.fml.common.registry.GameRegistry;

import static gregtech.api.recipe.RecipeMaps.chemicalReactorRecipes;
import static gregtech.api.recipe.RecipeMaps.fluidExtractionRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTRecipeBuilder.TICKS;
import static gregtech.api.util.GTRecipeConstants.UniversalChemical;

public class Recipes {

    /** Will be replaced with all the custom woods in CustomWood recipes */
    public static final ItemStack REFERENCE = new ItemStack(net.minecraft.init.Blocks.planks, 1);

    /** Holds all the custom woods for CustomWood items, will get filled on init() */
    private static List<ItemStack> woodList;

    public static void postInit() {
        // crop item
        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(CropsNHBlocks.blockCrop, ConfigurationHandler.cropsPerCraft),
                "ss",
                "ss",
                's',
                "stickWood"));
        // TODO: add magnifying glass recipe (copy plant lens)
        // TODO: add spade recipe (copy crop++ spade)

        // TODO: ALL RECIPE THAT LETS OIL BERRY CONVERT OILSANDS INTO OTHER OIL TYPES INSTEAD OF JUST HEAVY OIL

        // enriched fertilizer
        // ez logistics puzzle where you either flood both with fertilizer items or use a solution with system like
        // function like item conduits
        GTValues.RA.stdBuilder()
            .itemInputs(CropsNHItemList.fertilizer.get(4))
            .fluidInputs(new FluidStack(ModItems.fluidFertBasic, 1000))
            .fluidOutputs(new FluidStack(CropsNHFluids.enrichedFertilizer, 1000))
            .duration(SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(UniversalChemical);
        // cell only recipe
        GTValues.RA.stdBuilder()
            .itemInputs(
                CropsNHItemList.fertilizer.get(4),
                FluidContainerRegistry.fillFluidContainer(new FluidStack(ModItems.fluidFertBasic, 1000), ItemList.Cell_Empty.get(1))
            )
            .itemOutputs(CropsNHItemList.enrichedFertilizerCell.get(1))
            .duration(SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(chemicalReactorRecipes);

        // remove replace gt++ fertilizer extractor recipes
        if (Mods.IndustrialCraft2.isModLoaded()) {
            updateGTPPIC2FertRecipe();
        }
    }
    private static void updateGTPPIC2FertRecipe() {
        ItemStack ic2Fert = GameRegistry.findItemStack(Mods.IndustrialCraft2.ID, "itemFertilizer", 1);
        if (ic2Fert == null || ic2Fert.getItem() == null) return;

        for (GTRecipe recipe : fluidExtractionRecipes.getAllRecipes()) {
            if (recipe.mFluidOutputs == null || recipe.mFluidOutputs.length != 1 || recipe.mFluidOutputs[0] == null || recipe.mFluidOutputs[0].getFluid() != ModItems.fluidFertBasic) continue;
            if (recipe.mInputs == null || recipe.mInputs.length != 1 || recipe.mInputs[0] == null || recipe.mInputs[0].getItem() == null || recipe.mInputs[0].getItem() != ic2Fert.getItem()) continue;
            recipe.reloadOwner();
            recipe.mInputs[0] = CropsNHItemList.fertilizer.get(1);
        }
    }
}
