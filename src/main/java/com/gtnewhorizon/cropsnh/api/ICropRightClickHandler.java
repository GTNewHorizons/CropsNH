package com.gtnewhorizon.cropsnh.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ICropRightClickHandler {

    boolean onRightClick(World world, ICropStickTile te, EntityPlayer player, ItemStack heldItem);
}
