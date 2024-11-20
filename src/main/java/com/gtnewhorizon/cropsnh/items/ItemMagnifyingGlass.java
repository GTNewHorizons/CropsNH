package com.gtnewhorizon.cropsnh.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.blocks.BlockCrop;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.renderers.items.RenderItemBase;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMagnifyingGlass extends ItemCropsNH {

    public ItemMagnifyingGlass() {
        super();
        this.setMaxStackSize(1);
    }

    @Override
    protected String getInternalName() {
        return Names.Objects.magnifyingGlass;
    }

    // I'm overriding this just to be sure
    @Override
    public boolean canItemEditBlocks() {
        return true;
    }

    // this is called when you right click with this item in hand
    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
        float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            ArrayList<String> list = new ArrayList<>();
            if (world.getBlock(x, y, z) != null && world.getBlock(x, y, z) instanceof BlockCrop
                && world.getTileEntity(x, y, z) != null
                && world.getTileEntity(x, y, z) instanceof TileEntityCrop) {
                TileEntityCrop crop = (TileEntityCrop) world.getTileEntity(x, y, z);
                if (crop.hasPlant()) {
                    int growth = crop.getGrowth();
                    int gain = crop.getGain();
                    int strength = crop.getStrength();
                    boolean analyzed = crop.isAnalyzed();
                    ItemStack seed = crop.getSeedStack();
                    String seedName = seed.getItem()
                        .getItemStackDisplayName(seed);
                    int meta = world.getBlockMetadata(x, y, z);
                    float growthPercentage = ((float) meta) / ((float) 7) * 100.0F;
                    list.add(StatCollector.translateToLocal("cropsnh_tooltip.cropWithPlant"));
                    list.add(StatCollector.translateToLocal("cropsnh_tooltip.seed") + ": " + seedName);
                    if (analyzed) {
                        list.add(" - " + StatCollector.translateToLocal("cropsnh_tooltip.growth") + ": " + growth);
                        list.add(" - " + StatCollector.translateToLocal("cropsnh_tooltip.gain") + ": " + gain);
                        list.add(" - " + StatCollector.translateToLocal("cropsnh_tooltip.strength") + ": " + strength);
                    } else {
                        list.add(StatCollector.translateToLocal("cropsnh_tooltip.analyzed"));
                    }
                    list.add(
                        StatCollector.translateToLocal(
                            crop.isFertile() ? "cropsnh_tooltip.fertile" : "cropsnh_tooltip.notFertile"));
                    if (growthPercentage < 100.0) {
                        list.add(String.format("Growth : %.0f %%", growthPercentage));
                    } else {
                        list.add("Growth : Mature");
                    }
                } else if (crop.isCrossCrop()) {
                    list.add(StatCollector.translateToLocal("cropsnh_tooltip.crossCrop"));
                } else if (crop.hasWeed()) {
                    list.add(StatCollector.translateToLocal("cropsnh_tooltip.weeds"));
                } else {
                    list.add(StatCollector.translateToLocal("cropsnh_tooltip.cropWithoutPlant"));
                }
            } else {
                list.add(StatCollector.translateToLocal("cropsnh_tooltip.notCrop"));
            }
            for (String msg : list) {
                player.addChatComponentMessage(new ChatComponentText(msg));
            }
        }
        return true; // return true so nothing else happens
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        list.add(StatCollector.translateToLocal("cropsnh_tooltip.magnifyingGlass"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        LogHelper.debug("registering icon for: " + this.getUnlocalizedName());
        this.itemIcon = reg.registerIcon(
            this.getUnlocalizedName()
                .substring(
                    this.getUnlocalizedName()
                        .indexOf('.') + 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderItemBase getItemRenderer() {
        return null;
    }
}
