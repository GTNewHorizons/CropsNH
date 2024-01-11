package com.gtnewhorizon.cropsnh.compatibility.thaumcraft;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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

public class ThaumcraftHelper extends ModHelper {

    public static ArrayList<BlockModPlant> thaumcraftCrops = new ArrayList<>();
    public static ArrayList<ItemModSeed> thaumcraftSeeds = new ArrayList<>();

    @Override
    protected void onInit() {
        FMLInterModComms.sendMessage(
                Names.Mods.thaumcraft,
                "harvestClickableCrop",
                new ItemStack(Blocks.blockCrop, 1, Constants.MATURE));
    }

    @Override
    protected void initPlants() {
        Item thaumcraftPlant = (Item) Item.itemRegistry.getObject("Thaumcraft:blockCustomPlant");
        Item thaumcraftTaintPlant = (Item) Item.itemRegistry.getObject("Thaumcraft:ItemResource");
        Block blockTaint = (Block) Block.blockRegistry.getObject("Thaumcraft:blockTaint");
        int cinderpearlMeta = 3;
        int shimmerleafMeta = 2;
        int vishroomMeta = 5;
        int taintPlantMeta = 12;

        // cinderpearl
        try {
            BlockModPlant cropCinderpearl = new BlockModPlant(
                    "Cinderpearl",
                    new ItemStack(thaumcraftPlant, 1, cinderpearlMeta),
                    new BlockWithMeta(net.minecraft.init.Blocks.sand),
                    3,
                    RenderMethod.CROSSED);
            thaumcraftCrops.add(cropCinderpearl);
            thaumcraftSeeds.add(cropCinderpearl.getSeed());
        } catch (Exception e) {
            LogHelper.printStackTrace(e);
        }

        // shimmerleaf
        try {
            BlockModPlant cropShimmerleaf = new BlockModPlant(
                    "Shimmerleaf",
                    new ItemStack(thaumcraftPlant, 1, shimmerleafMeta),
                    3,
                    RenderMethod.CROSSED);
            Block log = (Block) Block.blockRegistry.getObject("Thaumcraft:blockMagicalLog");
            if (log != null) {
                cropShimmerleaf.getGrowthRequirement()
                        .setRequiredBlock(new BlockWithMeta(log, 1), RequirementType.NEARBY, false);
            }
            thaumcraftCrops.add(cropShimmerleaf);
            thaumcraftSeeds.add(cropShimmerleaf.getSeed());
        } catch (Exception e) {
            LogHelper.printStackTrace(e);
        }

        // vishroom
        try {
            BlockModPlant cropVishroom = new BlockModPlant(
                    "Vishroom",
                    new ItemStack(thaumcraftPlant, 1, vishroomMeta),
                    new BlockWithMeta(net.minecraft.init.Blocks.mycelium),
                    3,
                    RenderMethod.CROSSED);
            thaumcraftCrops.add(cropVishroom);
            thaumcraftSeeds.add(cropVishroom.getSeed());
            cropVishroom.getGrowthRequirement().setBrightnessRange(0, 8);
        } catch (Exception e) {
            LogHelper.printStackTrace(e);
        }

        // tainted root
        try {
            BlockModPlant cropTaintedRoot = new BlockModPlant(
                    "TaintedRoot",
                    new ItemStack(thaumcraftTaintPlant, 1, taintPlantMeta),
                    new BlockWithMeta(blockTaint),
                    RequirementType.BELOW,
                    new BlockWithMeta(blockTaint, 0),
                    4,
                    RenderMethod.CROSSED);
            thaumcraftCrops.add(cropTaintedRoot);
            thaumcraftSeeds.add(cropTaintedRoot.getSeed());
            cropTaintedRoot.getGrowthRequirement().setSoil(new BlockWithMeta(blockTaint, 1));
        } catch (Exception e) {
            LogHelper.printStackTrace(e);
        }
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
