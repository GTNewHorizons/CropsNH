package com.gtnewhorizon.cropsnh.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ICropRightClickHandler {

    boolean onRightClick(ICropStickTile te, EntityPlayer player, ItemStack heldItem);
}
