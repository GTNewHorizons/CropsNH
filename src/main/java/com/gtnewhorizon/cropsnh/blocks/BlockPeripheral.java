package com.gtnewhorizon.cropsnh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.handler.GuiHandler;
import com.gtnewhorizon.cropsnh.network.MessagePeripheralCheckNeighbours;
import com.gtnewhorizon.cropsnh.network.NetworkWrapperCropsNH;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.renderers.blocks.RenderBlockBase;
import com.gtnewhorizon.cropsnh.renderers.blocks.RenderPeripheral;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.TileEntityPeripheral;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;

@Optional.Interface(modid = Names.Mods.computerCraft, iface = "dan200.computercraft.api.peripheral.IPeripheralProvider")
public class BlockPeripheral extends BlockSeedAnalyzer implements IPeripheralProvider {

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockPeripheral() {
        super(Material.iron);
    }

    @Override
    protected void setBlockProps() {
        // set mining statistics
        this.setHardness(1);
        this.setResistance(1);
    }

    @Override
    protected int getGuiID() {
        return GuiHandler.peripheralID;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityPeripheral();
    }

    @Override
    protected String getTileEntityName() {
        return Names.Objects.peripheral;
    }

    @Override
    @Optional.Method(modid = Names.Mods.computerCraft)
    public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te == null || !(te instanceof TileEntityPeripheral)) {
            return null;
        }
        return (TileEntityPeripheral) te;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        IMessage msg = new MessagePeripheralCheckNeighbours(x, y, z);
        NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(world.provider.dimensionId, x, y, z, 32);
        NetworkWrapperCropsNH.wrapper.sendToAllAround(msg, point);
    }

    // rendering stuff
    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int i) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.icons = new IIcon[4];
        this.icons[0] = reg
                .registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf('.') + 1) + "Top");
        this.icons[1] = reg
                .registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf('.') + 1) + "Side");
        this.icons[2] = reg.registerIcon(
                this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf('.') + 1) + "Bottom");
        this.icons[3] = reg.registerIcon(
                this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf('.') + 1) + "Inner");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        side = side >= icons.length ? icons.length - 1 : side;
        side = side < 0 ? 0 : side;
        return icons[side];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderBlockBase getRenderer() {
        return new RenderPeripheral();
    }

    @Override
    protected String getInternalName() {
        return Names.Objects.peripheral;
    }
}
