package com.gtnewhorizon.cropsnh.compatibility.botania;

import com.gtnewhorizon.cropsnh.api.v1.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.v1.RenderMethod;
import com.gtnewhorizon.cropsnh.blocks.BlockModPlant;
import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.items.ItemModSeed;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class BotaniaHelper extends ModHelper {
    public static ArrayList<BlockModPlant> botaniaCrops = new ArrayList<>();
    public static ArrayList<ItemModSeed> botaniaSeeds = new ArrayList<>();

    @Override
    protected void initPlants() {
        for (int i = 0; i < 16; i++) {
            Object[] args = {Data.botania[i], new ItemStack((Item) Item.itemRegistry.getObject("Botania:petal"), 1, i), new BlockWithMeta(Blocks.dirt, 2), 3, RenderMethod.CROSSED, new ItemStack((Block) Block.blockRegistry.getObject("Botania:flower"), 1, i)};
            BlockModPlant plant;
            try {
                plant = new BlockModPlantBotania(args);
            } catch (Exception e) {
                if (ConfigurationHandler.debug) {
                    e.printStackTrace();
                }
                return;
            }
            botaniaCrops.add(plant);
            botaniaSeeds.add(plant.getSeed());
        }
        LogHelper.info("Botania crops registered");
    }

    @Override
    protected String modId() {
        return Names.Mods.botania;
    }

    static boolean useAlternateTextures() {
        try {
            Class<?> configClass = Class.forName("vazkii.botania.common.core.handler.ConfigHandler");
            return (Boolean) configClass.getField("altFlowerTextures").get(null);
        } catch(Exception e) {
            LogHelper.printStackTrace(e);
            return false;
        }
    }
}
