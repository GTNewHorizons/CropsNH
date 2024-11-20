package com.gtnewhorizon.cropsnh.farming.cropplant;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.v1.ICrop;
import com.gtnewhorizon.cropsnh.api.v1.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.farming.growthrequirement.GrowthRequirementHandler;
import com.gtnewhorizon.cropsnh.reference.Constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CropPlantVanilla extends CropPlant {

    private final BlockCrops plant;
    private final ItemSeeds seed;

    public CropPlantVanilla(BlockCrops crop, ItemSeeds seed) {
        this.plant = crop;
        this.seed = seed;
    }

    @Override
    public int tier() {
        return 1;
    }

    @Override
    public ItemStack getSeed() {
        return new ItemStack(seed);
    }

    @Override
    public Block getBlock() {
        return plant;
    }

    @Override
    public ArrayList<ItemStack> getAllFruits() {
        ArrayList<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(plant.getItemDropped(7, null, 0)));
        return list;
    }

    @Override
    public ItemStack getRandomFruit(Random rand) {
        return new ItemStack(plant.getItemDropped(7, rand, 0));
    }

    @Override
    public ArrayList<ItemStack> getFruitsOnHarvest(int gain, Random rand) {
        int amount = (int) (Math.ceil((gain + 0.00) / 3));
        ArrayList<ItemStack> list = new ArrayList<>();
        while (amount > 0) {
            list.add(getRandomFruit(rand));
            amount--;
        }
        return list;
    }

    @Override
    public boolean canBonemeal() {
        return true;
    }

    @Override
    protected IGrowthRequirement initGrowthRequirement() {
        return GrowthRequirementHandler.getNewBuilder()
            .build();
    }

    @Override
    public boolean onAllowedGrowthTick(World world, int x, int y, int z, int oldGrowthStage, ICrop crop) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getHeight(int meta) {
        return Constants.UNIT * 13;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getPlantIcon(int growthStage) {
        return plant.getIcon(0, growthStage);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean renderAsFlower() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getInformation() {
        String name = seed.getUnlocalizedName();
        int index = name.indexOf('_');
        if (index < 0) {
            index = name.indexOf('.');
        }
        name = name.substring(index + 1);
        return "cropsnh_journal." + name;
    }
}
