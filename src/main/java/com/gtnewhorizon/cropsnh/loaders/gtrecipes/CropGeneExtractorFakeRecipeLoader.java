package com.gtnewhorizon.cropsnh.loaders.gtrecipes;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTECropGeneExtractor;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.util.GTRecipeBuilder;
import gregtech.api.util.GTUtility;

public abstract class CropGeneExtractorFakeRecipeLoader extends BaseGTRecipeLoader {

    public static class CircuitResult {

        public ItemStack orb;
        public ItemStack circuit;
        public int duration;

        public CircuitResult(int circuitNo) {
            String unlocalizedName;
            switch (circuitNo) {
                case 1:
                    unlocalizedName = Reference.MOD_ID + "_nei.cropGeneExtractor.tooltip.scanSpecimen";
                    break;
                case 2:
                    unlocalizedName = Reference.MOD_ID + "_nei.cropGeneExtractor.tooltip.scanGrowth";
                    break;
                case 3:
                    unlocalizedName = Reference.MOD_ID + "_nei.cropGeneExtractor.tooltip.scanGain";
                    break;
                case 4:
                    unlocalizedName = Reference.MOD_ID + "_nei.cropGeneExtractor.tooltip.scanResistance";
                    break;
                default:
                    unlocalizedName = Reference.MOD_ID + "_nei.cropGeneExtractor.tooltip.error";
                    break;
            }
            this.orb = ItemList.Tool_DataOrb.getWithName(1L, StatCollector.translateToLocal(unlocalizedName));
            this.circuit = GTUtility.getIntegratedCircuit(circuitNo);
            this.duration = MTECropGeneExtractor.getRecipeDuration(circuitNo);
        }
    }

    public static void postInit() {
        CircuitResult[] recipeCache = new CircuitResult[4];
        for (int circuitNo = 1; circuitNo <= 4; circuitNo++) {
            recipeCache[circuitNo - 1] = new CircuitResult(circuitNo);
        }

        for (ICropCard cc : CropRegistry.instance.getAllInRegistrationOrder()) {
            if (cc.hideFromNEI()) continue;
            ItemStack seedStack = cc.getSeedItem(SeedStats.DEFAULT_ANALYZED);

            GTRecipeBuilder template = GTValues.RA.stdBuilder()
                .eut(MTECropGeneExtractor.getRecipeEUt(cc))
                .metadata(CropsNHGTRecipeMaps.CROPSNH_CROP_METADATAKEY, cc)
                .special(ItemList.Tool_DataOrb.get(1L))
                .ignoreCollision()
                .metadata(CropsNHGTRecipeMaps.CROPSNH_CROP_METADATAKEY, cc)
                .fake();

            // generate the sub recipes
            for (CircuitResult cache : recipeCache) {
                template.copy()
                    .itemInputs(seedStack, cache.circuit)
                    .itemOutputs(cache.orb)
                    .duration(cache.duration)
                    .addTo(CropsNHGTRecipeMaps.fakeCropGeneExtractorRecipeMap);
            }
        }
    }
}
