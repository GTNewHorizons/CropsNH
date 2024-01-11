package com.gtnewhorizon.cropsnh.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.compatibility.tconstruct.TinkersConstructHelper;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.renderers.blocks.RenderBlockBase;
import com.gtnewhorizon.cropsnh.renderers.blocks.RenderWaterPad;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWaterPad extends BlockCropsNH {

    public BlockWaterPad() {
        this(Material.ground);
    }

    protected BlockWaterPad(Material mat) {
        super(mat);
        this.setHardness(0.5F);
        this.setStepSound(soundTypeGravel);
        this.maxY = Constants.UNIT * (Constants.WHOLE / 2);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderBlockBase getRenderer() {
        return new RenderWaterPad(this);
    }

    @Override
    protected Class<? extends ItemBlock> getItemBlockClass() {
        return ItemBlockWaterPad.class;
    }

    @Override
    protected String getInternalName() {
        return Names.Objects.waterPad;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fX, float fY,
            float fZ) {
        ItemStack stack = player.getCurrentEquippedItem();
        if (stack == null || stack.getItem() == null) {
            return false;
        }
        if (FluidContainerRegistry.isContainer(stack)) {
            FluidStack waterBucket = new FluidStack(FluidRegistry.WATER, 1000);
            if (!FluidContainerRegistry.containsFluid(stack, waterBucket)) {
                return false;
            }
            if (!world.isRemote) {
                if (!player.capabilities.isCreativeMode) {
                    ItemStack copy = stack.copy();
                    player.getCurrentEquippedItem().stackSize = player.getCurrentEquippedItem().stackSize - 1;
                    if (player.getCurrentEquippedItem().stackSize == 0) {
                        player.inventory.setInventorySlotContents(
                                player.inventory.currentItem,
                                FluidContainerRegistry.drainFluidContainer(copy));
                    } else {
                        ItemStack drained = FluidContainerRegistry.drainFluidContainer(copy);
                        if (!player.inventory.addItemStackToInventory(drained)) {
                            if (world.getGameRules().getGameRuleBooleanValue("doTileDrops")
                                    && !world.restoringBlockSnapshots) {
                                float f = 0.7F;
                                double d0 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                                double d1 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                                double d2 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                                EntityItem entityitem = new EntityItem(
                                        world,
                                        (double) x + d0,
                                        (double) y + d1,
                                        (double) z + d2,
                                        drained);
                                entityitem.delayBeforeCanPickup = 10;
                                world.spawnEntityInWorld(entityitem);
                            }
                        }
                    }
                }
                world.setBlock(x, y, z, com.gtnewhorizon.cropsnh.init.Blocks.blockWaterPadFull, 7, 3);
            }
            return false;
        } else if (stack.getItem() instanceof ItemSpade
                || (ModHelper.allowIntegration(Names.Mods.tconstruct) && TinkersConstructHelper.isShovel(stack))) {
                    world.setBlock(x, y, z, Blocks.dirt);
                }
        return false;
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float f, int i) {
        if (!world.isRemote) {
            ItemStack drop = new ItemStack(Blocks.dirt, 1);
            this.dropBlockAsItem(world, x, y, z, drop);
        }
    }

    // creative item picking
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        return new ItemStack(Blocks.dirt);
    }

    @Override
    public int damageDropped(int meta) {
        return 0;
    }

    // render methods
    // --------------
    @Override
    public boolean isOpaqueCube() {
        return false;
    } // tells minecraft that this is not a block (no levers can be placed on it, it's transparent, ...)

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    } // tells minecraft that this has custom rendering

    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int i) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return Blocks.dirt.getIcon(side, meta);
    }

    // register icons
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        // NOOP
    }

    public static class ItemBlockWaterPad extends net.minecraft.item.ItemBlock {

        public ItemBlockWaterPad(Block block) {
            super(block);
        }

        @Override
        @SideOnly(Side.CLIENT)
        @SuppressWarnings("unchecked")
        public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
            list.add(StatCollector.translateToLocal("cropsnh_tooltip.waterPadDry"));
        }
    }
}
