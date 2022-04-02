package com.gtnewhorizon.cropsnh.init;

import com.gtnewhorizon.cropsnh.blocks.BlockModPlant;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.items.ItemModSeed;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import java.util.ArrayList;

public class Crops {
    public static ArrayList<BlockModPlant> crops;
    public static ArrayList<ItemModSeed> seeds;

    public static void init() {
        crops = new ArrayList<>();
        seeds = new ArrayList<>();
        for(Object[] data: Data.defaults) {
            BlockModPlant plant;
            try {
                plant = new BlockModPlant(data);
            } catch (Exception e) {
                if(ConfigurationHandler.debug) {
                    LogHelper.printStackTrace(e);
                }
                return;
            }
            crops.add(plant);
            seeds.add(plant.getSeed());
        }
        LogHelper.info("Crops registered");
    }
}
