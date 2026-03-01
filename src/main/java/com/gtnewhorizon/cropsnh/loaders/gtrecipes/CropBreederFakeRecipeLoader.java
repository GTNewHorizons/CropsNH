package com.gtnewhorizon.cropsnh.loaders.gtrecipes;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.cropsnh.api.ICropMutation;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.MutationRegistry;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTECropBreeder;

import cpw.mods.fml.common.LoaderException;
import gregtech.api.enums.GTValues;
import gregtech.api.util.GTRecipe;
import gregtech.api.util.GTRecipeBuilder;

public class CropBreederFakeRecipeLoader extends BaseGTRecipeLoader {

    public static void postInit() {
        for (ICropMutation mutation : MutationRegistry.instance.getDeterministicMutations()) {
            if (mutation.getOutput()
                .hideFromNEI()) continue;
            List<Object> inputs = mutation.getParents()
                .stream()
                .map(parent -> {
                    ItemStack seedStack = parent.getSeedItem(SeedStats.DEFAULT_ANALYZED);
                    seedStack.stackSize = 1;
                    seedStack.getTagCompound()
                        .removeTag(Names.NBT.gain);
                    seedStack.getTagCompound()
                        .removeTag(Names.NBT.growth);
                    seedStack.getTagCompound()
                        .removeTag(Names.NBT.resistance);
                    return (Object) seedStack;
                })
                .collect(Collectors.toList());

            int tier = mutation.getOutput()
                .getTier();

            GTRecipeBuilder template = GTValues.RA.stdBuilder()
                .metadata(CropsNHGTRecipeMaps.CROPSNH_CROP_MUTATION_METADATAKEY, mutation)
                .eut(mutation.getBreedingMachineRecipeEUt())
                .duration(mutation.getBreedingMachineRecipeDuration())
                .itemOutputs(
                    mutation.getOutput()
                        .getSeedItem(SeedStats.DEFAULT_ANALYZED))
                .nbtSensitive();

            // find block under stuff
            List<List<ItemStack>> catalysts = mutation.getBreedingMachineCatalystsForNEI(true);
            if (catalysts != null && !catalysts.isEmpty()) {
                for (List<ItemStack> slot : catalysts) {
                    if (slot == null || slot.isEmpty()) continue;
                    inputs.add(slot.toArray(new ItemStack[0]));
                }
            }
            template.itemInputs(inputs.toArray());

            for (Map.Entry<Fluid, Integer> allowedFluid : MTECropBreeder.ALLOWED_LIQUID_FERTILIZER.entrySet()) {
                GTRecipeBuilder builder = template.copy()
                    .fluidInputs(
                        new FluidStack(
                            allowedFluid.getKey(),
                            allowedFluid.getValue() / 2
                                * (mutation.getOutput()
                                    .getTier() * 2 + 3)));

                if (catalysts == null || catalysts.isEmpty()) {
                    builder.addTo(CropsNHGTRecipeMaps.fakeCropBreederRecipeMap);
                } else {
                    Optional<GTRecipe.GTRecipe_WithAlt> opt = builder.forceOreDictInput()
                        .buildWithAlt();
                    if (opt.isPresent()) {
                        CropsNHGTRecipeMaps.fakeCropBreederRecipeMap.add(opt.get());
                    } else {
                        throw new LoaderException("failed to load mutation recipe with underblocks");
                    }
                }
            }
        }
    }
}
