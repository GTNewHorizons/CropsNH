package com.gtnewhorizon.cropsnh.network;

import com.gtnewhorizon.cropsnh.reference.Reference;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public abstract class NetworkWrapperCropsNH {

    public static final int messageSendNEIsettings_ID = 1;

    public static SimpleNetworkWrapper wrapper;

    public static void init() {
        wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());
        initMessages();
    }

    private static void initMessages() {
        wrapper.registerMessage(
            MessageSendNEISetting.MessageHandler.class,
            MessageSendNEISetting.class,
            messageSendNEIsettings_ID,
            Side.CLIENT);
    }
}
