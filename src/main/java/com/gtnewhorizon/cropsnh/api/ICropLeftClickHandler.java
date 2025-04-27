package com.gtnewhorizon.cropsnh.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ICropLeftClickHandler {

    boolean onLeftClick(ICropStickTile te, EntityPlayer player, ItemStack heldItem);
}
