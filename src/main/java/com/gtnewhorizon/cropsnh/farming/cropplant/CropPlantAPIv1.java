package com.gtnewhorizon.cropsnh.farming.cropplant;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.v1.IAdditionalCropData;
import com.gtnewhorizon.cropsnh.api.v1.ICrop;
import com.gtnewhorizon.cropsnh.api.v1.ICropPlant;
import com.gtnewhorizon.cropsnh.api.v1.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CropPlantAPIv1 extends CropPlant implements ICropPlant {

    protected ICropPlant plant;

    public CropPlantAPIv1(ICropPlant plant) {
        this.plant = plant;
        this.setTier(plant.tier());
        this.setGrowthRequirement(plant.getGrowthRequirement());
    }

    @Override
    public int tier() {
        return plant == null ? 1 : plant.tier();
    }

    @Override
    public ItemStack getSeed() {
        return plant.getSeed();
    }

    @Override
    public Block getBlock() {
        return plant.getBlock();
    }

    @Override
    public ArrayList<ItemStack> getAllFruits() {
        return plant.getAllFruits();
    }

    @Override
    public ItemStack getRandomFruit(Random rand) {
        return plant.getRandomFruit(rand);
    }

    @Override
    public ArrayList<ItemStack> getFruitsOnHarvest(int gain, Random rand) {
        return plant.getFruitsOnHarvest(gain, rand);
    }

    @Override
    public boolean canBonemeal() {
        return plant.canBonemeal();
    }

    @Override
    public float getHeight(int meta) {
        return plant.getHeight(meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getPlantIcon(int growthStage) {
        return plant.getPlantIcon(growthStage);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean renderAsFlower() {
        return plant.renderAsFlower();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getInformation() {
        return plant.getInformation();
    }

    @SideOnly(Side.CLIENT)
    public void renderPlantInCrop(IBlockAccess world, int x, int y, int z, RenderBlocks renderer) {
        if (plant.overrideRendering()) {
            plant.renderPlantInCrop(world, x, y, z, renderer);
        } else {
            PlantRenderer.renderPlantLayer(
                    world,
                    x,
                    y,
                    z,
                    renderAsFlower() ? 1 : 6,
                    getPlantIcon(world.getBlockMetadata(x, y, z)),
                    0);
        }
    }

    @Override
    public IAdditionalCropData getInitialCropData(World world, int x, int y, int z, ICrop crop) {
        return ((ICropPlant) plant).getInitialCropData(world, x, y, z, crop);
    }

    @Override
    public IAdditionalCropData readCropDataFromNBT(NBTTagCompound tag) {
        return ((ICropPlant) plant).readCropDataFromNBT(tag);
    }

    @Override
    public IGrowthRequirement initGrowthRequirement() {
        return plant == null ? null : ((ICropPlant) plant).getGrowthRequirement();
    }

    @Override
    public void onValidate(World world, int x, int y, int z, ICrop crop) {
        ((ICropPlant) plant).onValidate(world, x, y, z, crop);
    }

    @Override
    public void onInvalidate(World world, int x, int y, int z, ICrop crop) {
        ((ICropPlant) plant).onInvalidate(world, x, y, z, crop);
    }

    @Override
    public void onChunkUnload(World world, int x, int y, int z, ICrop crop) {
        ((ICropPlant) plant).onChunkUnload(world, x, y, z, crop);
    }

    @Override
    public boolean onHarvest(World world, int x, int y, int z, ICrop crop, EntityPlayer player) {
        return ((ICropPlant) plant).onHarvest(world, x, y, z, crop, player);
    }

    @Override
    public void onSeedPlanted(World world, int x, int y, int z, ICrop crop) {
        ((ICropPlant) plant).onSeedPlanted(world, x, y, z, crop);
    }

    @Override
    public void onPlantRemoved(World world, int x, int y, int z, ICrop crop) {
        ((ICropPlant) plant).onPlantRemoved(world, x, y, z, crop);
    }

    @Override
    public boolean onAllowedGrowthTick(World world, int x, int y, int z, int oldGrowthStage, ICrop crop) {
        return ((ICropPlant) plant).onAllowedGrowthTick(world, x, y, z, oldGrowthStage, crop);
    }
}
