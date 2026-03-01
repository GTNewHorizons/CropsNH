package com.gtnewhorizon.cropsnh.crops;

import java.awt.Color;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.api.IAdditionalCropData;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.SeedData;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MigratedCropGrowthRequirement;
import com.gtnewhorizon.cropsnh.reference.Constants;

/**
 * A generally unobtainable crop that can't be obtained under regular circumstances.
 * Used to migrate crops that cannot be migrated without breaking something.
 *
 * It's seed drop is "guaranteed" and always drops 1 of itself.
 */
public class CropMigrator extends NHCropCard {

    public CropMigrator() {
        super("migrator", new Color(255, 0, 255), new Color(138, 2, 138));
        this.addGrowthRequirement(new MigratedCropGrowthRequirement());
    }

    @Override
    public String getCreator() {
        return "C0bra5";
    }

    @Override
    public ISoilList getSoilTypes() {
        return SoilRegistry.instance.allSoils;
    }

    public int getMaxGrowthStage() {
        return 1;
    }

    @Override
    public int getTier() {
        // should allow it to grow no matter the env.
        return 1;
    }

    @Override
    public boolean hideFromNEI() {
        return true;
    }

    @Override
    public int getGrowthDuration() {
        return 1;
    }

    @Override
    public float getCrossingThreshold() {
        return -1.0f;
    }

    @Override
    public float getBreedingThreshold() {
        return -1.0f;
    }

    @Override
    public boolean spreadsWeeds(ICropStickTile te) {
        return false;
    }

    @Override
    public int startsSpreadingWeedsAt() {
        return Constants.MAX_SEED_STAT + 1;
    }

    @Override
    public int getSpriteIndex(ICropStickTile te) {
        return 1;
    }

    @Override
    public IIcon getSprite(ICropStickTile te) {
        if (te.getAdditionalCropData() instanceof AdditionalData additionalData) {
            IIcon[] sprites = additionalData.seed.getCrop()
                .getSprites();
            if (sprites != null && sprites.length >= 1 && sprites[sprites.length - 1] != null) {
                return sprites[sprites.length - 1];
            }
        }
        // fallback
        return super.getSprite(te);
    }

    @Override
    public boolean onRightClick(ICropStickTile te, EntityPlayer player) {
        if (te.getAdditionalCropData() instanceof AdditionalData additionalData) {
            additionalData.drop(te);
        }
        return true;
    }

    @Override
    public void onRemoved(ICropStickTile te) {
        if (te.getAdditionalCropData() instanceof AdditionalData additionalData) {
            additionalData.drop(te);
        }
    }

    public IAdditionalCropData readAdditionalData(NBTTagCompound nbt) {
        return new AdditionalData(nbt);
    }

    public static class AdditionalData implements IAdditionalCropData {

        public @Nonnull final ISeedData seed;

        public AdditionalData(@Nonnull ISeedData stack) {
            this.seed = stack;
        }

        public AdditionalData(NBTTagCompound nbt) {
            this(new SeedData(nbt));
        }

        public void drop(ICropStickTile te) {
            te.dropItem(this.seed.getStack());
            te.setAdditionalCropData(null);
            te.clear();
        }

        @Override
        public NBTTagCompound writeToNBT() {
            return this.seed.writeToNBT();
        }
    }
}
