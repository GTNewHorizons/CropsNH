package com.gtnewhorizon.cropsnh.compatibility.harvestthenether;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.v1.ICrop;
import com.gtnewhorizon.cropsnh.api.v1.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;
import com.gtnewhorizon.cropsnh.farming.growthrequirement.GrowthRequirementHandler;
import com.gtnewhorizon.cropsnh.reference.Constants;

public class CropPlantHarvestTheNether extends CropPlant {

    private final Item seed;
    private final Block plant;
    private final Item fruit;

    protected CropPlantHarvestTheNether(Item seed, Block plant, Item fruit) {
        super();
        this.seed = seed;
        this.plant = plant;
        this.fruit = fruit;
    }

    @Override
    public int tier() {
        return 3;
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
        ArrayList<ItemStack> fruits = new ArrayList<>();
        fruits.add(new ItemStack(fruit));
        return fruits;
    }

    @Override
    public ItemStack getRandomFruit(Random rand) {
        return new ItemStack(fruit);
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
        return this.getTier() <= 3;
    }

    @Override
    protected IGrowthRequirement initGrowthRequirement() {
        return GrowthRequirementHandler.getNewBuilder().build();
    }

    @Override
    public boolean onAllowedGrowthTick(World world, int x, int y, int z, int oldGrowthStage, ICrop crop) {
        return true;
    }

    @Override
    public float getHeight(int meta) {
        return Constants.UNIT * 13;
    }

    @Override
    public IIcon getPlantIcon(int growthStage) {
        return plant.getIcon(0, growthStage);
    }

    @Override
    public boolean renderAsFlower() {
        return false;
    }

    @Override
    public String getInformation() {
        String name = seed.getUnlocalizedName();
        int index = name.indexOf("seedItem");
        name = index > 0 ? name.substring(0, index) : name;
        index = name.indexOf('.');
        name = index > 0 ? name.substring(index + 1) : name;
        return "cropsnh_journal.harvestTheNether_" + name;
    }
}
