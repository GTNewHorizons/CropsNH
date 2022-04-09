package com.gtnewhorizon.cropsnh.compatibility.thaumcraft;

import com.gtnewhorizon.cropsnh.api.v1.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.v1.RenderMethod;
import com.gtnewhorizon.cropsnh.api.v1.RequirementType;
import com.gtnewhorizon.cropsnh.blocks.BlockModPlant;
import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.init.Blocks;
import com.gtnewhorizon.cropsnh.items.ItemModSeed;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class ThaumcraftHelper extends ModHelper {
    public static ArrayList<BlockModPlant> thaumcraftCrops = new ArrayList<>();
    public static ArrayList<ItemModSeed> thaumcraftSeeds = new ArrayList<>();

    @Override
    protected void onInit() {
        FMLInterModComms.sendMessage(Names.Mods.thaumcraft, "harvestClickableCrop", new ItemStack(Blocks.blockCrop, 1, Constants.MATURE));
    }

    @Override
    protected void initPlants() {
        
    }


    @Override
    protected void onPostInit() {
        Aspects.registerAspects();
    }

    @Override
    protected String modId() {
        return Names.Mods.thaumcraft;
    }
}
