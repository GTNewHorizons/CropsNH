package com.gtnewhorizon.cropsnh.items;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.creativetab.CropsNHTab;
import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemEnvironmentalModule extends Item {

    private final static HashMap<Integer, ModuleData> VARIANTS = new HashMap<>();
    private final static String NAME_BASE = "item." + Reference.MOD_ID + ":environmentalModule";
    private final static String NAME_TEMPLATE = NAME_BASE + ".template";
    private final static String NAME_BLANK = NAME_BASE + ".blank";
    private IIcon missingIcon = null;
    private IIcon cardIcon = null;

    private static class ModuleData {

        public IIcon icon = null;
        public BiomeDictionary.Type tag;
        public String unlocalizedName;

        public ModuleData(BiomeDictionary.Type tag) {
            this.tag = tag;
            this.unlocalizedName = Reference.MOD_ID + "_tooltip.biomeTag."
                + tag.name()
                    .toUpperCase();
        }

        public void registerIcon(IIconRegister register) {
            this.icon = register.registerIcon(
                Reference.MOD_ID + ":environmentalModule/"
                    + tag.name()
                        .toUpperCase());
        }

    }

    public static ItemStack registerVariant(int meta, BiomeDictionary.Type tag) {
        if (VARIANTS.containsKey(meta)) {
            throw new IllegalArgumentException(
                "index " + meta + " has already been registered by " + VARIANTS.get(meta).tag.name());
        }
        VARIANTS.put(meta, new ModuleData(tag));
        return new ItemStack(CropsNHItems.environmentalModule, 1, meta);
    }

    public static @Nullable BiomeDictionary.Type getBiomeTag(int meta) {
        ModuleData data = VARIANTS.getOrDefault(meta, null);
        return data == null ? null : data.tag;
    }

    public ItemEnvironmentalModule() {
        this.setCreativeTab(CropsNHTab.cropsNHTab);
        this.bFull3D = false;
        this.hasSubtypes = true;
        GameRegistry.registerItem(this, "environmentalModule");
    }

    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        // add the default one.
        list.add(new ItemStack(item, 1, 0));
        // add the tagged cards.
        for (int meta : VARIANTS.keySet()) {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        if (CropsNHUtils.getItemMeta(itemStack) == 0) {
            return StatCollector.translateToLocal(NAME_BLANK);
        }
        return StatCollector.translateToLocalFormatted(
            NAME_TEMPLATE,
            StatCollector.translateToLocal(this.getUnlocalizedName(itemStack)));
    }

    @Override
    public String getUnlocalizedName() {
        return NAME_TEMPLATE;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        ModuleData tag = VARIANTS.getOrDefault(CropsNHUtils.getItemMeta(stack), null);
        // default if unknown
        if (tag == null) {
            return "???";
        }
        // else create the correct tag
        return tag.unlocalizedName;
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public int getRenderPasses(int metadata) {
        return metadata == 0 ? 1 : 2;
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        // load the crop card
        if (pass == 0) {
            return cardIcon;
        }
        ModuleData data = VARIANTS.getOrDefault(CropsNHUtils.getItemMeta(stack), null);
        return data == null ? missingIcon : data.icon;
    }

    @Override
    public IIcon getIconIndex(ItemStack stack) {
        return getIcon(stack, 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        this.missingIcon = register.registerIcon("missingno");
        this.cardIcon = register.registerIcon(Reference.MOD_ID + ":environmentalModule/blank");
        for (ModuleData data : VARIANTS.values()) {
            data.registerIcon(register);
        }
    }

}
