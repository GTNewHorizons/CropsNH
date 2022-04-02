package com.gtnewhorizon.cropsnh.items;

import com.gtnewhorizon.cropsnh.api.v1.ISeedStats;
import com.gtnewhorizon.cropsnh.blocks.BlockCrop;
import com.gtnewhorizon.cropsnh.farming.CropPlantHandler;
import com.gtnewhorizon.cropsnh.farming.PlantStats;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.renderers.items.RenderItemBase;
import com.gtnewhorizon.cropsnh.renderers.items.RenderItemClipping;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemClipping extends ItemCropsNH {
    public ItemClipping() {
        super();
        this.setCreativeTab(null);
    }

    @Override
    protected String getInternalName() {
        return Names.Objects.clipping;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderItemBase getItemRenderer() {
        return new RenderItemClipping(this);
    }

    @Override
    public boolean canItemEditBlocks() {return true;}

    //this is called when you right click with this item in hand
    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if(world.isRemote) {
            return false;
        }
        if(stack==null || stack.getItem()==null || stack.stackTagCompound==null) {
            return false;
        }
        Block block = world.getBlock(x, y, z);
        if(!(block instanceof BlockCrop)) {
            return false;
        }
        TileEntity te = world.getTileEntity(x, y, z);
        if(te == null || !(te instanceof TileEntityCrop)) {
            return true;
        }
        TileEntityCrop crop = (TileEntityCrop) te;
        BlockCrop blockCrop = (BlockCrop) block;
        if(crop.isCrossCrop()) {
            blockCrop.harvest(world, x, y, z, player, crop);
        }
        if(!crop.canPlant()) {
            return true;
        }
        ItemStack seed = ItemStack.loadItemStackFromNBT(stack.getTagCompound());
        ISeedStats stats = PlantStats.getStatsFromStack(seed);
        if(stats == null) {
            return false;
        }
        if(world.rand.nextInt(10)<=stats.getStrength()) {
            blockCrop.plantSeed(seed, world, x, y, z);
        }
        stack.stackSize = stack.stackSize-1;
        return true;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String text = StatCollector.translateToLocal("item.cropsnh:clipping.name");
        if(stack==null || stack.stackTagCompound==null) {
            return text;
        }
        ItemStack seed = ItemStack.loadItemStackFromNBT(stack.stackTagCompound);
        if(seed==null || seed.getItem()==null || !CropPlantHandler.isValidSeed(seed)) {
            return text;
        }
        CropPlant plant = CropPlantHandler.getPlantFromStack(seed);
        if(plant == null) {
            return text;
        }
        List<ItemStack> fruits =  plant.getAllFruits();
        ItemStack fruit = fruits.size() > 0 ? fruits.get(0) : null;
        return (fruit == null ? "" : fruit.getDisplayName()) + " " + text;
    }
}
