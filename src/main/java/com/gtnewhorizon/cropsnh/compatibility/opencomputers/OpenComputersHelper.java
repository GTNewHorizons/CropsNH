package com.gtnewhorizon.cropsnh.compatibility.opencomputers;

import java.lang.reflect.Method;

import net.minecraft.block.Block;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

public class OpenComputersHelper extends ModHelper {

    @Override
    protected String modId() {
        return Names.Mods.openComputers;
    }

    @SuppressWarnings("unchecked")
    protected void onPostInit() {
        try {
            Class<?> driverClass = Class.forName("li.cil.oc.api.Driver");
            Class<?> blockClass = Class.forName("li.cil.oc.api.driver.Block");
            Class<?> envClass = Class
                .forName("com.gtnewhorizon.cropsnh.compatibility.opencomputers.CropsNHEnvironment");
            Method method = driverClass.getDeclaredMethod("add", blockClass);
            Object environment = envClass.getDeclaredConstructor()
                .newInstance();
            method.invoke(null, environment);
            LogHelper.debug("CropsNH Environment registered with OpenComputers");
        } catch (Exception e) {
            LogHelper.printStackTrace(e);
        }
    }

    public static Block getComputerBlock() {
        return (Block) Block.blockRegistry.getObject("OpenComputers:case1");
    }
}
