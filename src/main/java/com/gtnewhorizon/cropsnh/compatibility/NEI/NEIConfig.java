package com.gtnewhorizon.cropsnh.compatibility.NEI;

import com.gtnewhorizon.cropsnh.CropsNH;
import com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers.AlternateSeedDumper;
import com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers.CropRegistryDumper;
import com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers.DeterministicMutationRegistryDumper;
import com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers.FertilizerFluidsRegistryDumper;
import com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers.FertilizerItemsRegistryDumper;
import com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers.HydrationFluidsRegistryDumper;
import com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers.MutationPoolRegistryDumper;
import com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers.SoilRegistryDumper;
import com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers.WeedEXFluidsRegistryDumper;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiRecipeTab;
import codechicken.nei.recipe.GuiUsageRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;
import cpw.mods.fml.common.event.FMLInterModComms;

public class NEIConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        if (!ModUtils.NotEnoughItems.isModLoaded()) return;
        // register dumpers
        registerDumpers();
        // register NEI recipe handler
        registerNEITabs();
    }

    private static void registerDumpers() {
        if (!ModUtils.NotEnoughItems.isModLoaded()) return;
        LogHelper.debug("Registering NEI dumpers");
        API.addOption(new AlternateSeedDumper());
        API.addOption(new CropRegistryDumper());
        API.addOption(new DeterministicMutationRegistryDumper());
        API.addOption(new FertilizerFluidsRegistryDumper());
        API.addOption(new FertilizerItemsRegistryDumper());
        API.addOption(new HydrationFluidsRegistryDumper());
        API.addOption(new MutationPoolRegistryDumper());
        API.addOption(new SoilRegistryDumper());
        API.addOption(new WeedEXFluidsRegistryDumper());
    }

    private static void registerNEITabs() {
        if (!ModUtils.NotEnoughItems.isModLoaded()) return;
        LogHelper.debug("Registering NEI recipe tabs");

        // crop product handler
        registerNEIHandler(new NEICropsNHCropHandler());
        // deterministic mutation handler
        registerNEIHandler(new NEICropsNHCropstickBreedingHandler());
        // mutation pool handler
        registerNEIHandler(new NEICropsNHMutationPoolHandler());
        // seed generator handler
        addHandler(
            new CropsNHNEISeedGeneratorHandler(
                CropsNHGTRecipeMaps.fakeSeedGeneratorRecipes.getDefaultRecipeCategory()));
        // crop breeder handler
        addHandler(
            new CropsNHNEICropBreederHandler(CropsNHGTRecipeMaps.fakeCropBreederRecipeMap.getDefaultRecipeCategory()));
        // crop gene extractor handler
        addHandler(
            new CropsNHNEICropGeneExtractorHandler(
                CropsNHGTRecipeMaps.fakeCropGeneExtractorRecipeMap.getDefaultRecipeCategory()));
        // crop synthesizer
        addHandler(
            new CropsNHNEICropSynthesizerHandler(
                CropsNHGTRecipeMaps.fakeCropSynthesizerRecipeMap.getDefaultRecipeCategory()));
    }

    private static void registerNEIHandler(CropsNHNEIHandler handler) {
        GuiRecipeTab.handlerMap.put(handler.getOverlayIdentifier(), handler.getHandlerInfo());
        API.registerRecipeHandler(handler);
        API.registerUsageHandler(handler);
    }

    private static void addHandler(TemplateRecipeHandler handler) {
        FMLInterModComms.sendRuntimeMessage(
            CropsNH.INSTANCE,
            "NEIPlugins",
            "register-crafting-handler",
            Reference.MOD_ID + "@" + handler.getRecipeName() + "@" + handler.getOverlayIdentifier());
        GuiCraftingRecipe.craftinghandlers.add(handler);
        GuiUsageRecipe.usagehandlers.add(handler);
    }

    @Override
    public String getName() {
        return Reference.MOD_ID + "_NEI";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

}
