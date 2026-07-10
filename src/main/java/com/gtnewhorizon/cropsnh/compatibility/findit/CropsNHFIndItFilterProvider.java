package com.gtnewhorizon.cropsnh.compatibility.findit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.crops.CropMigrator;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnh.findit.IStackFilter;
import com.gtnh.findit.service.itemfinder.FindItemRequest;

public class CropsNHFIndItFilterProvider implements IStackFilter.IStackFilterProvider {

    /**
     * Finds seeds of the same kind as the original seed as long as they are analyzed.
     */
    private static class AnalyzedSeedFilter implements IStackFilter {

        private final int id;

        public AnalyzedSeedFilter(int id) {
            this.id = id;
        }

        @Override
        public boolean matches(FindItemRequest request) {
            ISeedData data = CropsNHUtils.getSeedData(request.getStackToFind(), false, true);
            return data != null && this.id == data.getCrop()
                .getNumericId();
        }
    }

    /**
     * Finds other un-analyzed seeds
     */
    private static class UnanalyzedSeedFilter implements IStackFilter {

        public UnanalyzedSeedFilter() {}

        @Override
        public boolean matches(FindItemRequest request) {
            ISeedData data = CropsNHUtils.getSeedData(request.getStackToFind(), false, false);
            return data != null && !data.getStats()
                .isAnalyzed();
        }
    }

    @Override
    public IStackFilter getFilter(EntityPlayer olayer, TileEntity tileEntity) {
        // check if it's a crop stick te
        if (!(tileEntity instanceof ICropStickTile cropTE) || !cropTE.hasCrop() || cropTE.hasWeed()) return null;

        // ensure the crop actually has a seed, previous checks should ensure that but better safe than sorry.
        ISeedData seed = cropTE.getSeed();
        if (seed == null) return null;

        // identify migrated crops too
        ICropCard crop = seed.getCrop();
        if (crop instanceof CropMigrator) {
            if (cropTE.getAdditionalCropData() instanceof CropMigrator.AdditionalData extra) {
                seed = extra.seed;
                crop = seed.getCrop();
            } else {
                // migrator crops without extra data should not exist.
                return null;
            }
        }

        // match un-analyzed seeds together to make getting rid of em easier.
        if (!seed.getStats()
            .isAnalyzed()) return new UnanalyzedSeedFilter();

        // else match seeds of the same type together.
        return new AnalyzedSeedFilter(crop.getNumericId());
    }

    @Override
    public IStackFilter getFilter(EntityPlayer player, ItemStack stack) {
        // check if it's a generic seed
        ISeedData data = CropsNHUtils.getSeedData(stack, false, false);
        if (data == null) return null;

        // match un-analyzed seeds together to make getting rid of em easier.
        if (!data.getStats()
            .isAnalyzed()) return new UnanalyzedSeedFilter();

        // else match seeds of the same type together.
        return new AnalyzedSeedFilter(
            data.getCrop()
                .getNumericId());
    }
}
