package com.gtnewhorizon.cropsnh.compatibility.findit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnh.findit.IStackFilter;
import com.gtnh.findit.service.itemfinder.FindItemRequest;

public class CropsNHHandler implements IStackFilter.IStackFilterProvider {

    private static class CropStackFilter implements IStackFilter {

        String id;

        public CropStackFilter(String id) {
            this.id = id;
        }

        @Override
        public boolean matches(FindItemRequest request) {
            ItemStack stack = request.getStackToFind();
            ICropCard crop = CropRegistry.instance.get(stack);
            return crop != null && id.equals(crop.getId());
        }
    }

    @Override
    public IStackFilter getFilter(EntityPlayer player, TileEntity tileEntity) {
        return null;
    }

    @Override
    public IStackFilter getFilter(EntityPlayer player, ItemStack stack) {
        ICropCard crop = CropRegistry.instance.get(stack);
        return crop == null ? null : new CropStackFilter(crop.getId());
    }
}
