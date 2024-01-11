package com.gtnewhorizon.cropsnh.compatibility.harvestcraft;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

import com.gtnewhorizon.cropsnh.api.v1.BlockWithMeta;
import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.farming.CropPlantHandler;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.Blocks;
import com.gtnewhorizon.cropsnh.reference.Names;

public final class HarvestcraftHelper extends ModHelper {

    @Override
    protected String modId() {
        return Names.Mods.harvestcraft;
    }

    @Override
    protected void initPlants() {
        Class hc_ItemRegistry = null;
        try {
            hc_ItemRegistry = Class.forName("com.pam.harvestcraft.ItemRegistry");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert hc_ItemRegistry != null;
        Field[] fields = hc_ItemRegistry.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                try {
                    Object obj = field.get(null);
                    if (obj instanceof ItemSeeds) {
                        ItemSeeds seed = (ItemSeeds) obj;
                        if (seed == Item.itemRegistry.getObject("harvestcraft:cottonItem")) {
                            // cotton, the produce of cotton seeds is also instance of ItemSeeds, but we want to ignore
                            // it
                            continue;
                        }
                        CropPlant plant = new CropPlantHarvestCraft(seed);
                        CropPlantHandler.registerPlant(plant);
                        if (seed == Item.itemRegistry.getObject("harvestcraft:seaweedseedItem")
                                || seed == Item.itemRegistry.getObject("harvestcraft:riceseedItem")
                                || seed == Item.itemRegistry.getObject("harvestcraft:waterchestnutseedItem")
                                || seed == Item.itemRegistry.getObject("harvestcraft:cranberryseedItem")) {
                            plant.getGrowthRequirement().setSoil(new BlockWithMeta(Blocks.blockWaterPadFull));
                        }
                    }
                } catch (Exception e) {
                    if (ConfigurationHandler.debug) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
