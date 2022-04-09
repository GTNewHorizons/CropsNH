package com.gtnewhorizon.cropsnh.init;

import com.gtnewhorizon.cropsnh.api.v1.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.v1.RenderMethod;
import com.gtnewhorizon.cropsnh.api.v1.RequirementType;
import com.gtnewhorizon.cropsnh.blocks.BlockModPlant;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.items.ItemModSeed;
import com.gtnewhorizon.cropsnh.utility.IOHelper;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;

import java.lang.reflect.Field;
import java.util.List;

public class CustomCrops {
    public static BlockModPlant[] customCrops;
    public static ItemModSeed[] customSeeds;

    @SuppressWarnings("deprecation")
    public static void init() {
        
    }

    public static void initGrassSeeds() {
        if(ConfigurationHandler.wipeTallGrassDrops) {
            List seedList = null;
            boolean error = false;
            try {
                Field fieldSeedList = (ForgeHooks.class).getDeclaredField("seedList");
                fieldSeedList.setAccessible(true);
                seedList = (List) fieldSeedList.get(null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                error = true;
            }
            if(error) {
                LogHelper.info("Error when wiping tall grass drops: couldn't get seed list");
            } else {
                seedList.clear();
                LogHelper.info("Wiped seed entries");
            }
        }
        String[] rawData = IOHelper.getLinesArrayFromData(ConfigurationHandler.readGrassDrops());
        for(String data: rawData) {
            String[] dropData = IOHelper.getData(data);
            boolean success = dropData.length==2;
            String errorMsg = "Incorrect amount of arguments";
            LogHelper.debug("parsing "+data);
            if(success) {
                ItemStack seedStack = IOHelper.getStack(dropData[0], false);
                Item drop = seedStack!=null?seedStack.getItem():null;
                success = drop!=null;
                errorMsg = "Invalid fruit";
                if(success) {
                    int meta = seedStack.getItemDamage();
                    int weight = Integer.parseInt(dropData[1]);
                    MinecraftForge.addGrassSeed(new ItemStack(drop, 1, meta), weight);
                    LogHelper.info(new StringBuffer("Registered ").append(Item.itemRegistry.getNameForObject(drop)).append(":").append(meta).append(" as a drop from grass (weight: ").append(weight).append(')'));
                }
            }
            if(!success) {
                LogHelper.info(new StringBuffer("Error when adding grass drop: ").append(errorMsg).append(" (line: ").append(data).append(")"));
            }
        }
    }
}
