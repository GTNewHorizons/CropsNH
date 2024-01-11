package com.gtnewhorizon.cropsnh.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.CropsNH;
import com.gtnewhorizon.cropsnh.container.ContainerSeedAnalyzer;
import com.gtnewhorizon.cropsnh.creativetab.CropsNHTab;
import com.gtnewhorizon.cropsnh.handler.GuiHandler;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.renderers.blocks.RenderBlockBase;
import com.gtnewhorizon.cropsnh.renderers.blocks.RenderSeedAnalyzer;
import com.gtnewhorizon.cropsnh.tileentity.TileEntitySeedAnalyzer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSeedAnalyzer extends BlockContainerCropsNH {

    public BlockSeedAnalyzer(Material material) {
        super(material);

        this.setCreativeTab(CropsNHTab.cropsNHTab);
        this.isBlockContainer = true;
        this.setTickRandomly(false);

        this.setBlockProps();
    }

    public BlockSeedAnalyzer() {
        this(Material.ground);
    }

    protected void setBlockProps() {
        // set mining statistics
        this.setHardness(1);
        this.setResistance(1);
        // set the bounding box dimensions
        this.maxX = Constants.UNIT * (Constants.WHOLE - 1);
        this.minX = Constants.UNIT;
        this.maxZ = this.maxX;
        this.minZ = this.minX;
        this.maxY = Constants.UNIT * Constants.QUARTER;
        this.minY = 0;
    }

    protected int getGuiID() {
        return GuiHandler.seedAnalyzerID;
    }

    // creates a new tile entity every time a block of this type is placed
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntitySeedAnalyzer();
    }

    @Override
    protected String getTileEntityName() {
        return Names.Objects.seedAnalyzer;
    }

    // called when the block is broken
    @Override
    public void breakBlock(World world, int x, int y, int z, Block b, int meta) {
        if (!world.isRemote) {
            world.removeTileEntity(x, y, z);
            world.setBlockToAir(x, y, z);
        }
    }

    // override this to delay the removal of the tile entity until after harvestBlock() has been called
    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        return !player.capabilities.isCreativeMode || super.removedByPlayer(world, player, x, y, z, willHarvest);
    }

    // this gets called when the block is mined
    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
        if (!world.isRemote) {
            if (!player.capabilities.isCreativeMode) {
                this.dropBlockAsItem(world, x, y, z, meta, 0);
            }
            this.breakBlock(world, x, y, z, world.getBlock(x, y, z), meta);
        }
    }

    // get a list with items dropped when the block is broken
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> items = new ArrayList<>();
        items.add(new ItemStack(this, 1, 0));
        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntitySeedAnalyzer) {
            TileEntitySeedAnalyzer analyzer = (TileEntitySeedAnalyzer) world.getTileEntity(x, y, z);
            if (analyzer.getStackInSlot(ContainerSeedAnalyzer.seedSlotId) != null) {
                items.add(analyzer.getStackInSlot(ContainerSeedAnalyzer.seedSlotId));
            }
            if (analyzer.getStackInSlot(ContainerSeedAnalyzer.journalSlotId) != null) {
                items.add(analyzer.getStackInSlot(ContainerSeedAnalyzer.journalSlotId));
            }
        }
        return items;
    }

    // open the gui when the block is activated
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fX, float fY,
            float fZ) {
        if (player.isSneaking()) {
            return false;
        }
        if (!world.isRemote) {
            player.openGui(CropsNH.instance, getGuiID(), world, x, y, z);
        }
        return true;
    }

    // rendering stuff
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
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
        return false;
    } // no particles when this block gets hit

    @Override
    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
        return false;
    } // no particles when destroyed

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {}

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return net.minecraft.init.Blocks.planks.getIcon(0, 0);
    }

    @Override
    public boolean onBlockEventReceived(World world, int x, int y, int z, int id, int data) {
        super.onBlockEventReceived(world, x, y, z, id, data);
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        return (tileEntity != null) && (tileEntity.receiveClientEvent(id, data));
    }

    @Override
    public boolean isMultiBlock() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderBlockBase getRenderer() {
        return new RenderSeedAnalyzer();
    }

    @Override
    protected Class<? extends ItemBlock> getItemBlockClass() {
        return null;
    }

    @Override
    protected String getInternalName() {
        return Names.Objects.seedAnalyzer;
    }
}
