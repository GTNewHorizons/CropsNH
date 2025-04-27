package com.gtnewhorizon.cropsnh.compatibility.NEI;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayerMP;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.Mods;

public class NEIHelper extends ModHelper {

    private static final HashMap<String, Boolean> handlerStatuses = new HashMap<>();

    @Override
    protected String modId() {
        return Mods.NotEnoughItems.ID;
    }

    @Override
    protected void onPostInit() {}

    public static void setServerConfigs() {
        if (!ModHelper.allowIntegration(Mods.NotEnoughItems.ID)) return;
        // boolean enableMutationHandler = ConfigurationHandler.config.getBoolean(
        // "NEI Mutations",
        // ConfigurationHandler.Categories.CATEGORY_COMPATIBILITY,
        // true,
        // "set to false to disable seed mutations in NEI");
        // boolean enableProductHandler = ConfigurationHandler.config.getBoolean(
        // "NEI Products",
        // ConfigurationHandler.Categories.CATEGORY_COMPATIBILITY,
        // true,
        // "set to false to disable seed products in NEI");
        // handlerStatuses
        // .put("com.gtnewhorizon.cropsnh.compatibility.NEI.NEICropMutationHandler", enableMutationHandler);
        // handlerStatuses
        // .put("com.gtnewhorizon.cropsnh.compatibility.NEI.NEICropProductHandler", enableProductHandler);
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public static void setHandlerStatus(String className, boolean status) {
        if (!ModHelper.allowIntegration(Mods.NotEnoughItems.ID)) return;
        // try {
        // CropsNHNEIHandler.setActive(((Class<? extends CropsNHNEIHandler>) Class.forName(className)), status);
        // } catch (ClassNotFoundException e) {
        // LogHelper.printStackTrace(e);
        // }
    }

    public static void sendSettingsToClient(EntityPlayerMP player) {
        if (!ModHelper.allowIntegration(Mods.NotEnoughItems.ID)) return;
        for (Map.Entry<String, Boolean> entry : handlerStatuses.entrySet()) {
            // NetworkWrapperCropsNH.wrapper
            // .sendTo(new MessageSendNEISetting(entry.getKey(), entry.getValue()), player);
        }
    }
}
