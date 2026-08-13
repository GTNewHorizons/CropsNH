package com.gtnewhorizon.cropsnh.farming.bootprotection;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ICropsNHBootProtectionHandler;
import com.gtnewhorizon.cropsnh.utility.DebugHelper;

/**
 * A boot protection handler that provides a constant protection value.
 */
public class ConstantBootProtectionHandler implements ICropsNHBootProtectionHandler {

    /** The protection provided by the boots. */
    protected final float protection;

    /**
     * @param protection The protection provided by the boots
     */
    public ConstantBootProtectionHandler(float protection) {
        this.protection = protection;
    }

    @Override
    public float getProtection(@NotNull final ItemStack stack, @NotNull final EntityLivingBase entity,
        @NotNull final ICropStickTile cropTE) {
        return this.protection;
    }

    @Override
    public String getDumpText() {
        return DebugHelper.makeCSVLine("Type: Constant", String.format("Protection: %1.2f", this.protection));
    }

}
