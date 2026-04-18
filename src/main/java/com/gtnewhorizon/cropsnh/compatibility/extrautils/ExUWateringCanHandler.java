package com.gtnewhorizon.cropsnh.compatibility.extrautils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizon.cropsnh.utility.XSTR;

import cpw.mods.fml.common.LoaderException;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ExUWateringCanHandler {

    private static Item EXTRA_UTILS_WATERING_CAN = null;

    public static void postInit() {
        if (!ModUtils.ExtraUtilities.isModLoaded()) return;
        // technically temporary since It's getting replaced with UiE
        // A mixin would work better but, this is a temp impl and i'd rather stay unaware of ExU's source code to
        // help with UiE down the line.
        EXTRA_UTILS_WATERING_CAN = ModUtils.ExtraUtilities.getItem("watering_can");
        if (EXTRA_UTILS_WATERING_CAN == null) {
            throw new LoaderException("Unable to find ExU watering can while ExU is installed!");
        }
    }

    // cancel the event so the watering animation plays
    @SubscribeEvent
    public void wateringCanHook(PlayerInteractEvent event) {
        if (event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) return;
        ItemStack heldItem = event.entityPlayer.getHeldItem();
        if (heldItem == null || heldItem.getItem() != EXTRA_UTILS_WATERING_CAN) return;
        if (event.world.getBlock(event.x, event.y, event.z) != CropsNHBlocks.blockCropSticks) return;
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void whileUsingCan(PlayerUseItemEvent.Tick event) {
        // abort early on client
        World world = event.entityPlayer.getEntityWorld();
        if (world.isRemote) return;
        // check if the player is holding the right item
        ItemStack heldItem = event.entityPlayer.getHeldItem();
        if (heldItem == null || heldItem.getItem() != EXTRA_UTILS_WATERING_CAN) return;
        // get range
        int meta = CropsNHUtils.getItemMeta(heldItem);
        int radius = switch (meta) {
            case 0 -> 1; // basic (3x3x3)
            case 3 -> 2; // reinforced (5x5x5)
            default -> 0;
        };
        if (radius <= 0) return;
        MovingObjectPosition pos = getMovingObjectPositionFromPlayer(world, event.entityPlayer, true);
        if (pos != null) {
            doWateringCanInteraction(world, radius, pos.blockX, pos.blockY, pos.blockZ);
        }
    }

    // taken from the item class since it's usually a protected method
    private MovingObjectPosition getMovingObjectPositionFromPlayer(World worldIn, EntityPlayer player,
        boolean useLiquids) {
        float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f
            + (double) (worldIn.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight()
                : player.getEyeHeight());
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        if (player instanceof EntityPlayerMP) {
            d3 = ((EntityPlayerMP) player).theItemInWorldManager.getBlockReachDistance();
        }
        Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
        return worldIn.func_147447_a(vec3, vec31, useLiquids, !useLiquids, false);
    }

    private static void doWateringCanInteraction(final World world, final int radius, final int srcX, final int srcY,
        final int srcZ) {
        // should mirror the reg shape of crops
        if (radius <= 0) return;
        // idk why it waters in a square area that can be so large but eh it works
        for (int xOff = -radius; xOff <= radius; xOff++) {
            int xTrg = srcX + xOff;
            for (int yOff = -radius; yOff <= radius; yOff++) {
                int yTrg = srcY + yOff;
                for (int zOff = -radius; zOff <= radius; zOff++) {
                    if (XSTR.XSTR_INSTANCE.nextInt(5) != 0) continue;
                    int zTrg = srcZ + zOff;
                    if (world.getTileEntity(xTrg, yTrg, zTrg) instanceof ICropStickTile cropTE) {
                        cropTE.addWater(10, 99, 100, false);
                    }
                }
            }
        }
    }

}
