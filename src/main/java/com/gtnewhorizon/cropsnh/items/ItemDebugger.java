package com.gtnewhorizon.cropsnh.items;

import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.renderers.items.RenderItemBase;
import com.gtnewhorizon.cropsnh.utility.DebugHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDebugger extends ItemCropsNH {

    public ItemDebugger() {
        super();
        this.setMaxStackSize(1);
    }

    @Override
    protected String getInternalName() {
        return "debugger";
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
            float hitX, float hitY, float hitZ) {
        if (!player.isSneaking()) {
            DebugHelper.debug(player, world, x, y, z);
        } else if (world.getBlock(x, y, z) instanceof IGrowable) {
            if (player.isSneaking()) {
                int meta = world.getBlockMetadata(x, y, z);
                world.setBlockMetadataWithNotify(x, y, z, (meta + 1) % 16, 3);
                // world.getBlock(x, y, z).updateTick(world, x, y, z, world.rand);
            }
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        this.itemIcon = reg
                .registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf('.') + 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderItemBase getItemRenderer() {
        return null;
    }
}
