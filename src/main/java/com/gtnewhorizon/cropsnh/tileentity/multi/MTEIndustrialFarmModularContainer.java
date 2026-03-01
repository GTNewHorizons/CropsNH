package com.gtnewhorizon.cropsnh.tileentity.multi;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.cleanroommc.modularui.screen.ModularContainer;

public class MTEIndustrialFarmModularContainer extends ModularContainer {

    @Override
    public @Nullable ItemStack transferStackInSlot(@NotNull EntityPlayer playerIn, int index) {
        return super.transferStackInSlot(playerIn, index);
    }
}
