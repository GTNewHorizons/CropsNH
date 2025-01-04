package com.gtnewhorizon.cropsnh.farming;

import java.util.List;

import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CopponFiberVariant extends MaterialLeafVariant {

    public CopponFiberVariant(int id, String unlocalizedName, String materialType) {
        super(id, unlocalizedName, materialType);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getTooltip(List list) {
        super.getTooltip(list);
        if (ConfigurationHandler.enableEasterEggs) {
            list.add(StatCollector.translateToLocal("cropsnh_tooltip.copponFiber"));
        }
    }
}
