package com.gtnewhorizon.cropsnh.gui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@SuppressWarnings("unchecked")
public class CropsNHGuiConfig extends GuiConfig {

    public CropsNHGuiConfig(GuiScreen guiScreen) {
        super(
                guiScreen,
                getConfigElements(),
                Reference.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.config.toString()));
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> configElements = new ArrayList<>();
        for (Field field : ConfigurationHandler.Categories.class.getDeclaredFields()) {
            if (field.getType() == String.class) {
                try {
                    String category = (String) field.get(null);
                    String descr = "CropsNH " + category + " Settings";
                    String name = "cropsnh.configgui.ctgy." + category;
                    configElements.add(
                            new DummyConfigElement.DummyCategoryElement(
                                    descr,
                                    name,
                                    new ConfigElement(ConfigurationHandler.config.getCategory(category))
                                            .getChildElements()));
                } catch (Exception e) {
                    LogHelper.printStackTrace(e);
                }
            }
        }
        return configElements;
    }
}
