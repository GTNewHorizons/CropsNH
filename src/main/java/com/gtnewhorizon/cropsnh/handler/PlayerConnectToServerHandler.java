package com.gtnewhorizon.cropsnh.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import com.gtnewhorizon.cropsnh.CropsNH;
import com.gtnewhorizon.cropsnh.api.v1.IMutation;
import com.gtnewhorizon.cropsnh.compatibility.NEI.NEIHelper;
import com.gtnewhorizon.cropsnh.farming.mutation.MutationHandler;
import com.gtnewhorizon.cropsnh.network.MessageSyncMutation;
import com.gtnewhorizon.cropsnh.network.NetworkWrapperCropsNH;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

@SuppressWarnings("unused")
public class PlayerConnectToServerHandler {

    /** Receive mutations from the server when connecting to the server */
    @SubscribeEvent
    public void sendNEIconfig(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.player.worldObj.isRemote) {
            NEIHelper.sendSettingsToClient((EntityPlayerMP) event.player);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void syncMutations(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.player.worldObj.isRemote) {
            if (MinecraftServer.getServer().isDedicatedServer()) {
                // for dedicated server sync to every player
                syncMutations((EntityPlayerMP) event.player);
            } else {
                EntityPlayer connecting = event.player;
                EntityPlayer local = CropsNH.proxy.getClientPlayer();
                if (local != null && local != connecting) {
                    // for local LAN, only sync if the connecting player is not the host, because the host will already
                    // have the correct mutations
                    syncMutations((EntityPlayerMP) event.player);
                }
            }
        }
    }

    private void syncMutations(EntityPlayerMP player) {
        LogHelper.info("Sending mutations to player: " + player.getDisplayName());
        IMutation[] mutations = MutationHandler.getInstance().getMutations();
        for (int i = 0; i < mutations.length; i++) {
            NetworkWrapperCropsNH.wrapper
                    .sendTo(new MessageSyncMutation(mutations[i], i == mutations.length - 1), player);
        }
    }
}
