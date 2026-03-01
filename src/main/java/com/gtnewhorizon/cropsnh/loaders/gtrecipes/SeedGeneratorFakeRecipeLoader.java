package com.gtnewhorizon.cropsnh.loaders.gtrecipes;

import java.util.Optional;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHFluids;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import cpw.mods.fml.common.FMLLog;
import gregtech.api.util.GTRecipe;
import gregtech.api.util.GTRecipeBuilder;

public abstract class SeedGeneratorFakeRecipeLoader extends BaseGTRecipeLoader {

    public static void postInit() {
        for (ICropCard cc : CropRegistry.instance.getAllInRegistrationOrder()) {
            if (cc.hideFromNEI()) continue;
            ItemStack seedStack = cc.getSeedItem(SeedStats.DEFAULT_ANALYZED);
            seedStack.stackSize = 0;
            seedStack.getTagCompound()
                .removeTag(Names.NBT.gain);
            seedStack.getTagCompound()
                .removeTag(Names.NBT.growth);
            seedStack.getTagCompound()
                .removeTag(Names.NBT.resistance);

            if (cc.getDuplicationCatalystsForNEI()
                .isEmpty()) {
                addFakeDuplicationRecipe(cc, seedStack, null);
            } else {
                for (Object catalyst : cc.getDuplicationCatalystsForNEI()) {
                    addFakeDuplicationRecipe(cc, seedStack, catalyst);
                }
            }
        }
    }

    protected static void addFakeDuplicationRecipe(ICropCard cc, ItemStack seed, Object catalyst) {
        // don't add crops that can't be crossbred
        if (cc.getCrossingThreshold() < 0.0f) return;
        // generate the recipe template
        GTRecipeBuilder recipe = lvRecipe(20, 0).fluidInputs(new FluidStack(CropsNHFluids.enrichedFertilizer, 100))
            .itemOutputs(seed)
            .nbtSensitive()
            .ignoreCollision()
            .metadata(CropsNHGTRecipeMaps.CROPSNH_CROP_METADATAKEY, cc)
            .fake();
        // if we are using a catalyst, the path changes a bit
        if (catalyst instanceof Object[]cat) {
            if (OreDictionary.getOres((String) cat[0], false)
                .isEmpty()) {
                FMLLog.bigWarning("ore dict replication catalyst points to invalid item: " + cat[0]);
            } else {
                Optional<GTRecipe.GTRecipe_WithAlt> opt = recipe.itemInputs(seed, catalyst)
                    .forceOreDictInput()
                    .buildWithAlt();
                if (opt.isPresent()) {
                    CropsNHGTRecipeMaps.fakeSeedGeneratorRecipes.add(opt.get());
                } else {
                    LogHelper.warn("failed to generate recipe with ore-dict catalyst for " + cc.getId());
                }
            }
            return;
        } else if (catalyst instanceof ItemStack) {
            recipe.itemInputs(seed, catalyst);
        } else if (catalyst != null) {
            LogHelper.warn("failed to generate recipe with catalyst for " + cc.getId());
        } else {
            recipe.itemInputs(seed);
        }
        recipe.addTo(CropsNHGTRecipeMaps.fakeSeedGeneratorRecipes);
    }
}
