package com.gtnewhorizon.cropsnh.farming.materialleaf;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.api.IMaterialLeafVariant;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MaterialLeafVariant implements IMaterialLeafVariant {

    private final int id;
    private final String unlocalizedName;
    private final String unlocalizedTooltip;
    protected String iconName;
    protected IIcon icon = null;

    public MaterialLeafVariant(int id, String unlocalizedName, String materialType) {
        this.id = id;
        this.iconName = "cropsnh:materialLeaf/" + unlocalizedName;
        this.unlocalizedName = "item.cropsnh:materialLeaf." + unlocalizedName;
        this.unlocalizedTooltip = "cropsnh_tooltip.leaf." + materialType;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getUnlocalizedName() {
        return this.unlocalizedName;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon() {
        return this.icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getTooltip(List list) {
        list.add(StatCollector.translateToLocal(this.unlocalizedTooltip));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void RegisterIcon(IIconRegister register) {
        this.icon = register.registerIcon(this.iconName);
    }
}
