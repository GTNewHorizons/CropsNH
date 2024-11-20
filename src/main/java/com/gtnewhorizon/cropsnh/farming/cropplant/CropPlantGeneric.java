package com.gtnewhorizon.cropsnh.farming.cropplant;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.v1.ICrop;
import com.gtnewhorizon.cropsnh.api.v1.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.farming.growthrequirement.GrowthRequirementHandler;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Generic abstract implementation of the cropPlant, will work for most crops that follow the vanilla item seeds
 */
public abstract class CropPlantGeneric extends CropPlant {

    private final ItemSeeds seed;
    private final Block plant;
    private final ArrayList<ItemStack> fruits;

    public CropPlantGeneric(ItemSeeds seed) {
        super();
        this.seed = seed;
        this.plant = seed.getPlant(null, 0, 0, 0);
        this.fruits = OreDictHelper.getFruitsFromOreDict(getSeed(), modSpecificFruits());
    }

    protected boolean modSpecificFruits() {
        return ConfigurationHandler.modSpecifDrops;
    }

    public abstract int transformMeta(int growthStage);

    @Override
    public int tier() {
        return 2;
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
        return fruits;
    }

    @Override
    public ItemStack getRandomFruit(Random rand) {
        ArrayList<ItemStack> list = getAllFruits();
        if (list != null && list.size() > 0) {
            return list.get(rand.nextInt(list.size()))
                .copy();
        }
        return null;
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

    public boolean canBonemeal() {
        return getTier() < 4;
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
        // for the Vanilla SeedItem class the arguments for this method are not used
        return getBlock().getIcon(0, transformMeta(growthStage));
    }
}
