package com.gtnewhorizon.cropsnh.compatibility.NEI;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import com.gtnewhorizon.cropsnh.CropsNH;
import com.gtnewhorizon.cropsnh.blocks.BlockCustomWood;
import com.gtnewhorizon.cropsnh.blocks.BlockModPlant;
import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.compatibility.botania.BotaniaHelper;
import com.gtnewhorizon.cropsnh.compatibility.thaumcraft.ThaumcraftHelper;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.Blocks;
import com.gtnewhorizon.cropsnh.init.Crops;
import com.gtnewhorizon.cropsnh.init.CustomCrops;
import com.gtnewhorizon.cropsnh.init.Items;
import com.gtnewhorizon.cropsnh.init.ResourceCrops;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class NEIConfig implements IConfigureNEI {
    @Override
    public void loadConfig() {
        //register NEI recipe handler
        LogHelper.debug("Registering NEI recipe handlers");
        //mutation handler
        NEICropMutationHandler mutationHandler = new NEICropMutationHandler();
        API.registerRecipeHandler(mutationHandler);
        API.registerUsageHandler(mutationHandler);
        //crop product handler
        NEICropProductHandler productHandler = new NEICropProductHandler();
        API.registerRecipeHandler(productHandler);
        API.registerUsageHandler(productHandler);
        //hide crop blocks in NEI
        hideItems();
    }

    private static void hideItems() {
        LogHelper.debug("Hiding crops in NEI");
        for (int i = 0; i < 16; i++) {
            //hide crops block
            CropsNH.proxy.hideItemInNEI(new ItemStack(Blocks.blockCrop, 1, i));
            //hide water pad
            CropsNH.proxy.hideItemInNEI(new ItemStack(Blocks.blockWaterPad, 1, i));
            CropsNH.proxy.hideItemInNEI(new ItemStack(Blocks.blockWaterPadFull, 1, i));
            //hide clippings
            CropsNH.proxy.hideItemInNEI(new ItemStack(Items.clipping, 1, i));
            //hide debugger
            if(!ConfigurationHandler.debug) {
                CropsNH.proxy.hideItemInNEI(new ItemStack(Items.debugItem, 1, i));
            }
            //hide plant blocks
            for(BlockModPlant plant : Crops.crops) {
                CropsNH.proxy.hideItemInNEI(new ItemStack(plant, 1, i));
            }
            //hide botania crops
            if(ModHelper.allowIntegration(Names.Mods.botania)) {
                for(BlockModPlant plant : BotaniaHelper.botaniaCrops) {
                    CropsNH.proxy.hideItemInNEI(new ItemStack(plant, 1, i));
                }
            }
            //hide thaumcraft crops
            if(ModHelper.allowIntegration(Names.Mods.thaumcraft)) {
                for(BlockModPlant plant : ThaumcraftHelper.thaumcraftCrops) {
                    CropsNH.proxy.hideItemInNEI(new ItemStack(plant, 1, i));
                }
            }
            //hide resource crops
            if(ConfigurationHandler.resourcePlants) {
                for(BlockModPlant plant : ResourceCrops.vanillaCrops) {
                    CropsNH.proxy.hideItemInNEI(new ItemStack(plant, 1, i));
                }
                for(BlockModPlant plant : ResourceCrops.modCrops) {
                    CropsNH.proxy.hideItemInNEI(new ItemStack(plant, 1, i));
                }
            }
            //hide custom crops
            if(ConfigurationHandler.customCrops) {
                for (BlockModPlant customCrop: CustomCrops.customCrops) {
                    CropsNH.proxy.hideItemInNEI(new ItemStack(customCrop, 1, i));
                }
            }
        }
        if(ConfigurationHandler.condenseCustomWoodInNei) {
            LogHelper.debug("Hiding custom wood objects");
            Field[] blocks = Blocks.class.getDeclaredFields();
            for (Field field : blocks) {
                try {
                    Object obj = field.get(null);
                    if(obj == null) {
                        continue;
                    }
                    if (BlockCustomWood.class.isAssignableFrom(obj.getClass())) {
                        Block block = (Block) obj;
                        ItemStack stack = new ItemStack(block);
                        ArrayList<ItemStack> list = new ArrayList<>();
                        list.add(stack);
                        API.setItemListEntries(stack.getItem(), list);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getName() {
        return Reference.MOD_ID+"_NEI";
    }

    @Override
    public String getVersion() {
        return  "1.0";
    }

}
