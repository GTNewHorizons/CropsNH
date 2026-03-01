package com.gtnewhorizon.cropsnh.items.produce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.api.IMaterialLeafVariant;
import com.gtnewhorizon.cropsnh.creativetab.CropsNHTab;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMaterialLeaf extends Item {

    private final static HashMap<Integer, IMaterialLeafVariant> variants = new HashMap<>();
    private IIcon missingIcon = null;

    public static void registerVariant(IMaterialLeafVariant variant) {
        if (variants.containsKey(variant.getId())) {
            throw new IllegalArgumentException(
                "index " + variant.getId()
                    + " has already been registered by "
                    + variants.get(variant.getId())
                        .getUnlocalizedName());
        }
        variants.put(variant.getId(), variant);
    }

    public static Iterable<IMaterialLeafVariant> getRegisteredVariants() {
        return variants.values();
    }

    public ItemMaterialLeaf() {
        this.setCreativeTab(CropsNHTab.cropsNHTab);
        this.bFull3D = false;
        this.hasSubtypes = true;
        GameRegistry.registerItem(this, "materialLeaf");
    }

    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        for (Map.Entry<Integer, IMaterialLeafVariant> entry : variants.entrySet()) {
            list.add(new ItemStack(item, 1, entry.getKey()));
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean showAdvancedItemTooltips) {
        if (variants.containsKey(CropsNHUtils.getItemMeta(stack))) {
            variants.get(CropsNHUtils.getItemMeta(stack))
                .getTooltip(list);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (variants.containsKey(CropsNHUtils.getItemMeta(stack))) {
            return variants.get(CropsNHUtils.getItemMeta(stack))
                .getUnlocalizedName();
        }
        return "unknown leaf";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        if (variants.containsKey(damage)) {
            return variants.get(damage)
                .getIcon();
        }
        return this.missingIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        this.missingIcon = register.registerIcon("missingno");
        for (IMaterialLeafVariant variant : variants.values()) {
            variant.RegisterIcon(register);
        }
    }

}
