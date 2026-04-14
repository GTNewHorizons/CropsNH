package com.gtnewhorizon.cropsnh.items.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

import com.google.common.collect.Sets;
import com.gtnewhorizon.cropsnh.api.ICropLeftClickHandler;
import com.gtnewhorizon.cropsnh.api.ICropRightClickHandler;
import com.gtnewhorizon.cropsnh.api.ICropSeedDropOverride;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IHarvestDropModifier;
import com.gtnewhorizon.cropsnh.creativetab.CropsNHTab;
import com.gtnewhorizon.cropsnh.crops.CropMigrator;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.RegisterHelper;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Tool to uproot weeds.
 * Comes in a wooden and iron variant.
 */
public abstract class ItemSpadeNH extends ItemTool
    implements ICropLeftClickHandler, ICropRightClickHandler, ICropSeedDropOverride, IHarvestDropModifier {

    public static final Set<Block> BLOCKS_AFFECTED = Sets.newHashSet(
        Blocks.grass,
        Blocks.dirt,
        Blocks.snow_layer,
        Blocks.farmland,
        Blocks.mycelium,
        CropsNHBlocks.blockCropSticks);

    public ItemSpadeNH(float damage, ToolMaterial mat) {
        super(damage, mat, BLOCKS_AFFECTED);
        this.setMaxStackSize(1);
        this.setTextureName(Reference.MOD_ID + ":" + getInternalName());
        this.setCreativeTab(CropsNHTab.cropsNHTab);
        this.setMaxDamage(0);
        this.setHarvestLevel("spade", 1);
        RegisterHelper.registerItem(this, getInternalName());
    }

    protected abstract String getInternalName();

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        list.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip." + this.getInternalName()));
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
        float hitX, float hitY, float hitZ) {
        if (!player.canPlayerEdit(x, y, z, side, stack)) {
            return super.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
        }

        var event = new UseHoeEvent(player, stack, world, x, y, z);
        if (MinecraftForge.EVENT_BUS.post(event)) {
            return super.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
        }

        if (event.getResult() == Event.Result.ALLOW) {
            return true;
        }

        if (side != 0 && world.getBlock(x, y + 1, z)
            .getMaterial() == Material.air) {
            if (world.getBlock(x, y, z) == Blocks.grass || world.getBlock(x, y, z) == Blocks.dirt
                || world.getBlock(x, y, z) == Blocks.mycelium
                || world.getBlock(x, y, z) == Blocks.farmland) {
                var spadeBlock = Blocks.farmland;
                world.playSoundEffect(
                    x + 0.5f,
                    y + 0.5f,
                    z + 0.5f,
                    spadeBlock.stepSound.getStepResourcePath(),
                    (spadeBlock.stepSound.getVolume() + 1f) / 2f,
                    spadeBlock.stepSound.getPitch() * 0.8f);
                if (!world.isRemote) {
                    world.setBlock(
                        x,
                        y,
                        z,
                        (world.getBlock(x, y, z) == Blocks.farmland) ? Blocks.dirt : Blocks.farmland);
                }
                return true;
            }
        }

        return super.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    @Override
    public boolean onLeftClick(ICropStickTile te, EntityPlayer player, ItemStack heldItem) {
        return this.doWork(te, true);
    }

    @Override
    public boolean onRightClick(World world, ICropStickTile te, EntityPlayer player, ItemStack heldItem) {
        if (world.isRemote) return true;
        return this.doWork(te, false);
    }

    @Override
    public boolean isFull3D() {
        return true;
    }

    @Override
    public boolean overrideSeedDrop(EntityPlayer player, ItemStack heldItem, Collection<ItemStack> dropTracker,
        ICropStickTile cropTE, boolean hasHarvested) {
        return hasHarvested;
    }

    @Override
    public void modifyCropDrops(EntityPlayer player, ItemStack heldItem, Collection<ItemStack> dropTracker,
        ICropStickTile cropTE) {
        this.addSeedDrop(cropTE, dropTracker);
    }

    private boolean doWork(ICropStickTile cropTE, boolean clearAfter) {
        if (cropTE.isCrossCrop()) return false;
        // if it's weeds clear them
        if (cropTE.hasWeed()) {
            // drop tall grass if it's mature
            if (cropTE.isMature()) {
                cropTE.dropItem(CropsNHUtils.getWeedDrop(1));
            }
            cropTE.clear();
            return true;
        }

        // If the crop is mature, has all it's growth requirements met, but can't be harvested, the spades still lets
        // players farm seeds.
        if (cropTE.isMature() && !(cropTE.getSeed()
            .getCrop() instanceof CropMigrator) && !cropTE.canHarvest() && cropTE.areGrowthRequirementsMet()) {
            ArrayList<ItemStack> dropTracker = new ArrayList<>(1);
            addSeedDrop(cropTE, dropTracker);
            dropTracker.forEach(cropTE::dropItem);
            if (clearAfter) cropTE.clear();
            else cropTE.setGrowthProgress(0);
            return true;
        }
        return false;
    }

    protected abstract void addSeedDrop(ICropStickTile cropTE, Collection<ItemStack> dropTracker);
}
