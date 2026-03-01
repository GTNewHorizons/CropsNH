package com.gtnewhorizon.cropsnh.loaders.gtrecipes;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTECropSynthesizer;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.common.items.behaviors.BehaviourDataOrb;

public abstract class CropSynthesizerFakeRecipeLoader extends BaseGTRecipeLoader {

    public static void postInit() {
        // generate base inputs
        ItemStack growthOrb = ItemList.Tool_DataOrb.get(0L);
        BehaviourDataOrb.setDataTitle(growthOrb, Names.DataOrb.growth);
        ItemStack gainOrb = ItemList.Tool_DataOrb.get(0L);
        BehaviourDataOrb.setDataTitle(gainOrb, Names.DataOrb.gain);
        ItemStack resistanceOrb = ItemList.Tool_DataOrb.get(0L);
        BehaviourDataOrb.setDataTitle(resistanceOrb, Names.DataOrb.resistance);

        for (ICropCard cc : CropRegistry.instance.getAllInRegistrationOrder()) {
            if (cc.hideFromNEI()) continue;
            ItemStack specimenOrb = ItemList.Tool_DataOrb.get(0L);
            BehaviourDataOrb.setDataTitle(specimenOrb, Names.DataOrb.resistance);
            BehaviourDataOrb.setDataName(specimenOrb, cc.getId());

            int fluidAmount = MTECropSynthesizer
                .getFluidAmount(cc, Constants.MAX_SEED_STAT, Constants.MAX_SEED_STAT, Constants.MAX_SEED_STAT);
            GTValues.RA.stdBuilder()
                .metadata(CropsNHGTRecipeMaps.CROPSNH_CROP_METADATAKEY, cc)
                .itemInputs(specimenOrb, growthOrb, gainOrb, resistanceOrb)
                .fluidInputs(Materials.UUMatter.getFluid(fluidAmount))
                .itemOutputs(cc.getSeedItem(SeedStats.DEFAULT_ANALYZED))
                .eut(MTECropSynthesizer.getRecipeEUt(cc))
                .duration(MTECropSynthesizer.getRecipeDuration(cc))
                .fake()
                .ignoreCollision()
                .addTo(CropsNHGTRecipeMaps.fakeCropSynthesizerRecipeMap);
        }

    }
}
