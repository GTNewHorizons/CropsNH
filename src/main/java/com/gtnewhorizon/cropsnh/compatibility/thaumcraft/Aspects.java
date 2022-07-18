package com.gtnewhorizon.cropsnh.compatibility.thaumcraft;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.Blocks;
import com.gtnewhorizon.cropsnh.init.Crops;
import com.gtnewhorizon.cropsnh.init.CustomCrops;
import com.gtnewhorizon.cropsnh.init.Items;
import com.gtnewhorizon.cropsnh.init.ResourceCrops;
import com.gtnewhorizon.cropsnh.items.ItemModSeed;
import com.gtnewhorizon.cropsnh.reference.Names;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class Aspects {
    public static void registerAspects() {
        //seeds
        for(ItemModSeed seed : Crops.seeds) {
            ThaumcraftApi.registerObjectTag(new ItemStack(seed, 1, 0), new AspectList().add(Aspect.PLANT, 1));
        }
        //resource crops
        if(ConfigurationHandler.resourcePlants) {
            for(ItemModSeed seed : ResourceCrops.vanillaSeeds) {
                ThaumcraftApi.registerObjectTag(new ItemStack(seed, 1, 0), new AspectList().add(Aspect.PLANT, 1).add(Aspect.GREED, 1));
            }
            for(ItemModSeed seed : ResourceCrops.modSeeds) {
                ThaumcraftApi.registerObjectTag(new ItemStack(seed, 1, 0), new AspectList().add(Aspect.PLANT, 1).add(Aspect.GREED, 1));
            }
        }
        
        //thaumcraft crops
        if(ModHelper.allowIntegration(Names.Mods.thaumcraft)) {
            for(ItemModSeed seed : ThaumcraftHelper.thaumcraftSeeds) {
                if(seed.getUnlocalizedName().equals("cropsnh:seedTaintedRoot")) {
                    ThaumcraftApi.registerObjectTag(new ItemStack(seed, 1, 0), new AspectList().add(Aspect.PLANT, 1).add(Aspect.MAGIC, 1).add(Aspect.TAINT, 1));
                } else {
                    ThaumcraftApi.registerObjectTag(new ItemStack(seed, 1, 0), new AspectList().add(Aspect.PLANT, 1).add(Aspect.MAGIC, 1));
                }
            }
        }
        //custom crops
        if(ConfigurationHandler.customCrops) {
            for (ItemModSeed seed : CustomCrops.customSeeds) {
                ThaumcraftApi.registerObjectTag(new ItemStack(seed, 1, 0), new AspectList().add(Aspect.PLANT, 1));
            }
        }
        //seed analyzer
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.blockSeedAnalyzer, 1, 0), new AspectList().add(Aspect.MIND, 2).add(Aspect.PLANT, 2).add(Aspect.CROP, 2));
        //journal
        ThaumcraftApi.registerObjectTag(new ItemStack(Items.journal, 1, 0), new AspectList().add(Aspect.MIND, 2).add(Aspect.PLANT, 2).add(Aspect.CROP, 2));
    }
}
