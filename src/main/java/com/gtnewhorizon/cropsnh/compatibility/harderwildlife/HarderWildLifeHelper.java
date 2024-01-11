package com.gtnewhorizon.cropsnh.compatibility.harderwildlife;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.farming.CropPlantHandler;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

public class HarderWildLifeHelper extends ModHelper {

    @Override
    protected void initPlants() {
        Item seed = (Item) Item.itemRegistry.getObject("HarderWildlife:winterWheatSeeds");
        Item fruit = (Item) Item.itemRegistry.getObject("minecraft:wheat");
        if (seed != null && seed instanceof ItemSeeds && fruit != null) {
            OreDictionary.registerOre("cropWinterWheat", fruit);
            OreDictionary.registerOre("seedWinterWheat", seed);
            CropPlant cropPlant = new CropPlantHarderWildLife((ItemSeeds) seed, "winterWheat");
            try {
                CropPlantHandler.registerPlant(cropPlant);
            } catch (Exception e) {
                LogHelper.printStackTrace(e);
            }
        }
    }

    @Override
    protected String modId() {
        return Names.Mods.harderWildLife;
    }
}
