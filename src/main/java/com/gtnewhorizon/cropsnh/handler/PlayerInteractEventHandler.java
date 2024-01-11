package com.gtnewhorizon.cropsnh.handler;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.compatibility.tconstruct.TinkersConstructHelper;
import com.gtnewhorizon.cropsnh.farming.CropPlantHandler;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;
import com.gtnewhorizon.cropsnh.farming.growthrequirement.GrowthRequirementHandler;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings("unused")
public class PlayerInteractEventHandler {

    /** Event handler to disable vanilla farming */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void vanillaSeedPlanting(PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            if (event.entityPlayer.getCurrentEquippedItem() != null
                    && event.entityPlayer.getCurrentEquippedItem().stackSize > 0
                    && event.entityPlayer.getCurrentEquippedItem().getItem() != null
                    && event.entityPlayer.getCurrentEquippedItem().getItem() instanceof IPlantable) {
                if (GrowthRequirementHandler.isSoilValid(event.world, event.x, event.y, event.z)) {
                    if (ConfigurationHandler.disableVanillaFarming) {
                        if (!allowVanillaPlanting(event.entityPlayer.getCurrentEquippedItem())) {
                            this.denyEvent(event, false);
                            return;
                        }
                    }
                    if (event.entityPlayer.getCurrentEquippedItem().hasTagCompound()) {
                        NBTTagCompound tag = (NBTTagCompound) event.entityPlayer.getCurrentEquippedItem()
                                .getTagCompound().copy();
                        if (tag.hasKey(Names.NBT.growth) && tag.hasKey(Names.NBT.gain)
                                && tag.hasKey(Names.NBT.strength)) {
                            // TODO: place a tile entity storing the seed's data
                            this.denyEvent(event, false);
                        }
                    }
                }
            }
        }
    }

    private static boolean allowVanillaPlanting(ItemStack seed) {
        if (seed == null || seed.getItem() == null) {
            return false;
        }
        if (ConfigurationHandler.disableVanillaFarming) {
            if (ignoresVanillaPlantingSetting(seed)) {
                return true;
            }
            if (CropPlantHandler.isValidSeed(seed)) {
                return false;
            }
            if (seed.getItem() == Items.potato) {
                return false;
            }
            if (seed.getItem() == Items.carrot) {
                return false;
            }
            if (seed.getItem() == Items.reeds) {
                return false;
            }
        }
        return true;
    }

    private static boolean ignoresVanillaPlantingSetting(ItemStack seed) {
        CropPlant plant = CropPlantHandler.getPlantFromStack(seed);
        return plant != null && plant.ignoresVanillaPlantingRule();
    }

    /** Event handler to create water pads */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void waterPadCreation(PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            if (event.world.getBlock(event.x, event.y, event.z) != Blocks.farmland) {
                return;
            }
            if (!ConfigurationHandler.onlyCreateWaterPadWhileSneaking || event.entityPlayer.isSneaking()) {
                boolean flag = false;
                if (event.entityPlayer.getCurrentEquippedItem() != null
                        && event.entityPlayer.getCurrentEquippedItem().getItem() != null
                        && event.entityPlayer.getCurrentEquippedItem().getItem() instanceof ItemSpade) {
                    flag = true;
                } else if (ModHelper.allowIntegration(Names.Mods.tconstruct)
                        && TinkersConstructHelper.isShovel(event.entityPlayer.getCurrentEquippedItem())) {
                            flag = true;
                        }
                if (flag) {
                    if (event.world.isRemote) {
                        denyEvent(event, true);
                    }
                    Block block = event.world.getBlock(event.x, event.y, event.z);
                    event.world.setBlock(
                            event.x,
                            event.y,
                            event.z,
                            com.gtnewhorizon.cropsnh.init.Blocks.blockWaterPad,
                            0,
                            3);
                    if (!event.entityPlayer.capabilities.isCreativeMode) {
                        event.entityPlayer.getCurrentEquippedItem().damageItem(1, event.entityPlayer);
                        event.setResult(Event.Result.ALLOW);
                    }
                    event.world.playSoundEffect(
                            (double) ((float) event.x + 0.5F),
                            (double) ((float) event.y + 0.5F),
                            (double) ((float) event.z + 0.5F),
                            block.stepSound.getStepResourcePath(),
                            (block.stepSound.getVolume() + 1.0F) / 2.0F,
                            block.stepSound.getPitch() * 0.8F);
                    denyEvent(event, false);
                }
            }
        }
    }

    /** Event handler to deny bonemeal while sneaking on crops that are not allowed to be bonemealed */
    @SubscribeEvent
    public void denyBonemeal(PlayerInteractEvent event) {
        if (event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (!event.entityPlayer.isSneaking()) {
            return;
        }
        ItemStack heldItem = event.entityPlayer.getHeldItem();
        if (heldItem != null && heldItem.getItem() == net.minecraft.init.Items.dye && heldItem.getItemDamage() == 15) {
            TileEntity te = event.world.getTileEntity(event.x, event.y, event.z);
            if (te instanceof TileEntityCrop) {
                TileEntityCrop crop = (TileEntityCrop) te;
                if (!crop.canBonemeal()) {
                    this.denyEvent(event, false);
                }
            }
        }
    }

    private void denyEvent(PlayerInteractEvent event, boolean sendToServer) {
        // cancel event to prevent the Hunger Overhaul event handler from being called
        event.setResult(Event.Result.DENY);
        event.useItem = Event.Result.DENY;
        event.useBlock = Event.Result.DENY;
        if (sendToServer && event.world.isRemote) {
            // send the right click to the server manually (cancelling the event will prevent the client from telling
            // the server a right click happened, and nothing will happen, but we still want stuff to happen)
            FMLClientHandler.instance().getClientPlayerEntity().sendQueue.addToSendQueue(
                    new C08PacketPlayerBlockPlacement(
                            event.x,
                            event.y,
                            event.z,
                            event.face,
                            event.entityPlayer.inventory.getCurrentItem(),
                            0f,
                            0f,
                            0f));
        }
        event.setCanceled(true);
    }
}
