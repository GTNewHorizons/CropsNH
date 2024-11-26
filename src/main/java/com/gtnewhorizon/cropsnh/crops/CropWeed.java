package com.gtnewhorizon.cropsnh.crops;

import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropCard;

/**
 * The generic weed crop that appears when a crop spreads weeds or a crop stick is left sitting for too long.
 */
public class CropWeed extends CropCard {

    public CropWeed() {
        super("weed");
    }

    @Override
    public Map<ItemStack, Float> getDropTable() {
        // no drop table by default, if you want weeds use a trowel
        return null;
    }

    @Override
    public IIcon[] getTextures(IIconRegister register) {
        return getTextures(register, "cropWeedTexture", 4);
    }

    @Override
    public boolean spreadsWeeds(ICropStickTile te) {
        return te.getGrowthProgress() >= 0.5f;
    }
}
