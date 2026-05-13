package com.gtnewhorizon.cropsnh.compatibility.backhand;

import static xonin.backhand.api.core.EnumHand.MAIN_HAND;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import com.gtnewhorizon.cropsnh.blocks.BlockCropSticks;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizon.gtnhlib.eventbus.EventBusSubscriber;
import com.rwtema.extrautils.item.ItemWateringCan;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import xonin.backhand.api.core.BackhandUtils;

@EventBusSubscriber
public class BackhandCompat {

    @EventBusSubscriber.Condition
    public static boolean register() {
        return ModUtils.Backhand.isModLoaded() && ModUtils.ExtraUtilities.isModLoaded();
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerInteract(PlayerInteractEvent event) {
        if (event.world == null || event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK
            || !BackhandUtils.isUsingOffhand(event.entityPlayer)) {
            return;
        }

        ItemStack mainHand = MAIN_HAND.getItem(event.entityPlayer);
        if (mainHand == null || !(mainHand.getItem() instanceof ItemWateringCan)) return;
        if (event.world.getBlock(event.x, event.y, event.z) instanceof BlockCropSticks) {
            event.setCanceled(true);
        }
    }
}
