package com.gtnewhorizon.cropsnh.compatibility.weeeflowers;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.farming.CropPlantHandler;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

public class WeeeFlowersHelper extends ModHelper {

    @Override
    protected void initPlants() {
        Class wf_ItemRegistry;
        Method getMetaMethod;
        try {
            wf_ItemRegistry = Class.forName("com.pam.weeeflowers.weeeflowers");
            getMetaMethod = getMetaMethod(Class.forName("com.pam.weeeflowers.BlockPamFlowerCrop"));
        } catch (Exception e) {
            LogHelper.printStackTrace(e);
            return;
        }
        if (getMetaMethod == null) {
            return;
        }
        Field[] fields = wf_ItemRegistry.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                try {
                    Object obj = field.get(null);
                    if (obj instanceof ItemSeeds) {
                        ItemSeeds seed = (ItemSeeds) obj;
                        String color = Item.itemRegistry.getNameForObject(seed).substring(12);
                        color = color.substring(0, color.indexOf(" Flower"));
                        Block flower = (Block) Block.blockRegistry.getObject("weeeflowers:" + color + " Flower Crop");
                        int meta = (Integer) getMetaMethod.invoke(flower, 0);
                        CropPlantHandler.registerPlant(new CropPlantWeeeFlower(seed, meta));
                    }
                } catch (Exception e) {
                    LogHelper.printStackTrace(e);
                }
            }
        }
    }

    private Method getMetaMethod(Class blockClass) {
        for (Method method : blockClass.getDeclaredMethods()) {
            if (method.getReturnType() == int.class) {
                return method;
            }
        }
        return null;
    }

    @Override
    protected String modId() {
        return Names.Mods.weeeFlowers;
    }
}
