package com.gtnewhorizon.cropsnh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropLeftClickHandler;
import com.gtnewhorizon.cropsnh.api.ICropRightClickHandler;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.init.Blocks;
import com.gtnewhorizon.cropsnh.items.blocks.ItemCropSticks;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.renderers.blocks.RenderBlockBase;
import com.gtnewhorizon.cropsnh.renderers.blocks.RenderCrop;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropsNH;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * The most important block in the mod.
 */
public class BlockCropSticks extends BlockContainerCropsNH {

    /** The default constructor for the block. */
    public BlockCropSticks() {
        super(Material.plants);
        this.isBlockContainer = true;
        this.setStepSound(soundTypeGrass);
        this.setHardness(0.8F);
        this.setHardness(0.2F);
        this.disableStats();
    }

    @Override
    protected String getInternalName() {
        return Names.Objects.cropSticks;
    }

    @Override
    protected String getTileEntityName() {
        return Names.Objects.cropSticksTE;
    }

    /** Creates a new tile entity every time the block is placed. */
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityCrop();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fX, float fY,
        float fZ) {

        // else ensure that we have a crop tile
        final TileEntity te = world.getTileEntity(x, y, z);
        if (!(te instanceof ICropStickTile)) return true;
        final ICropStickTile crop = (ICropStickTile) te;
        final ItemStack heldItem = player.getCurrentEquippedItem();

        // if it can interact with crops deffer to that tool's logic.
        if (heldItem != null && heldItem.getItem() instanceof ICropRightClickHandler) {
            if (((ICropRightClickHandler) heldItem.getItem()).onRightClick(world, crop, player, heldItem)) {
                // only abort if the tool consumed the interaction.
                return true;
            }
        }

        // only make things happen serverside
        if (world.isRemote) return true;

        // else we let it go to the crop tile
        return crop.onRightClick(player, heldItem);
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        // only make things happen serverside
        if (world.isRemote) return;

        // else ensure that we have a crop tile
        final TileEntity te = world.getTileEntity(x, y, z);
        if (!(te instanceof ICropStickTile)) return;
        final ICropStickTile crop = (ICropStickTile) te;
        final ItemStack heldItem = player.getCurrentEquippedItem();

        // if it can interact with crops deffer to that tool's logic.
        if (heldItem != null && heldItem.getItem() instanceof ICropLeftClickHandler) {
            ((ICropLeftClickHandler) heldItem.getItem()).onLeftClick(crop, player, heldItem);
            return;
        }

        // else we let it go to the crop tile
        crop.onLeftClick(player, heldItem);
    }

    /**
     * Handles the block being harvested by calling {@link #onBlockClicked(World, int, int, int, EntityPlayer)}.
     */
    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
        this.onBlockClicked(world, x, y, z, player);
    }

    /**
     * Handles changes in the crops neighbors. Used to detect if the crops had the soil stolen from under them and they
     * should now break.
     */
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        // check if crops can stay
        final TileEntity te = world.getTileEntity(x, y, z);
        if (!this.canBlockStay(world, x, y, z)) {
            // Attempt to notify the TE of it's impending doom.
            if (te instanceof ICropStickTile) {
                ((ICropStickTile) te).onDestroyed();
            }
            // And break and drop the crop sticks.
            this.dropBlockAsItem(world, x, y, z, 0, 0);
            world.setBlockToAir(x, y, z);
            world.removeTileEntity(x, y, z);
            return;
        }

        // if it can stay just notify the crop.
        if (te instanceof ICropStickTile) {
            ((ICropStickTile) te).onNeighbourChange();
        }
    }

    /**
     * Tests to see if the crop is still on valid soil.
     *
     * @return if the crop is placed in a valid location.
     */
    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        y -= 1;
        if (world.isAirBlock(x, y, z)) return false;
        final Block block = world.getBlock(x, y, z);
        final int meta = world.getBlockMetadata(x, y, z);
        return SoilRegistry.instance.isRegistered(block, meta);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, int x, int y, int z) {
        return this.canBlockStay(worldIn, x, y, z);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z) {
        double d = 0.2;
        return AxisAlignedBB
            .getBoundingBox((double) d, (double) 0.0, (double) d, (double) (1.0 - d), (double) 0.7, (double) (1.0 - d));
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        final TileEntity te = world.getTileEntity(x, y, z);
        if (!(te instanceof ICropStickTile)) return;
        final ICropStickTile crop = (ICropStickTile) te;
        crop.onEntityCollision(entity);
    }

    /**
     * Determines if the block is a normal block, such as cobblestone.
     * This tells Minecraft if crops are not a normal block (meaning no levers can be placed on it, it's transparent,
     * ...).
     *
     * @return false - the block is not a normal block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * Determines if the crops should render as any normal block.
     * This tells Minecraft whether or not to call the custom renderer.
     *
     * @return false - the block has custom rendering.
     */
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * Determines if a side of the block should be rendered, such as one flush with a wall that wouldn't need rendering.
     *
     * @return false - all of the crop's sides need to be rendered.
     */
    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int i) {
        return true;
    }

    /**
     * Renders the hit effects, such as the flying particles when the block is hit.
     *
     * @return false - the block is one-shot and needs no hit particles.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
        return false;
    }

    /**
     * Tells Minecraft if there should be destroy effects, such as particles.
     *
     * @return false - there are no destroy particles.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
        return false;
    }

    /**
     * Registers the block's icons.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        CropRegistry.instance.registerIcons(reg);
        this.blockIcon = reg.registerIcon(
            this.getUnlocalizedName()
                .substring(
                    this.getUnlocalizedName()
                        .indexOf('.') + 1));
    }

    @Override
    public String getItemIconName() {
        return "cropsnh:cropSticks";
    }

    /**
     * Retrieve the icon for a side of the block.
     *
     * @param side the side to get the icon for.
     * @param meta the metadata of the block.
     * @return the icon representing the side of the block.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.blockIcon;
    }

    /**
     * Handles the block receiving events.
     *
     * @return if the event was received properly.
     */
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

    /**
     * Retrieves the custom renderer for the crops.
     *
     * @return the block's renderer.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public RenderBlockBase getRenderer() {
        return new RenderCrop();
    }

    @Override
    protected Class<? extends ItemBlock> getItemBlockClass() {
        return ItemCropSticks.class;
    }

    @Override
    public ItemStack getWailaStack(BlockCropsNH block, TileEntityCropsNH tea) {
        return new ItemStack(Blocks.blockCrop, 1, 0);
    }

}
