package com.gtnewhorizon.cropsnh.farming.materialleaf;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.objects.XSTR;

public class PyrolusiumLeafVariant extends MaterialLeafVariant {

    private final String unlocalizedExtraTooltip;

    public PyrolusiumLeafVariant(int id, String unlocalizedName, String materialType) {
        super(id, unlocalizedName, materialType);

        if (ConfigurationHandler.enableEasterEggs) {
            Calendar calendar = GregorianCalendar.getInstance();
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int rand = XSTR.XSTR_INSTANCE.nextInt(100);
            // canada day = canada leaf
            if (month == Calendar.JULY && day == 1) {
                rand = 1;
            }
            // april first = banana
            else if (month == Calendar.APRIL && day == 1) {
                rand = 2;
            }
            switch (rand) {
                case 1:
                    this.iconName += ".canada";
                    this.unlocalizedExtraTooltip = "cropsnh_tooltip.pyrolusiumLeaf.canada";
                    break;
                case 2:
                    this.iconName += ".banada";
                    this.unlocalizedExtraTooltip = "cropsnh_tooltip.pyrolusiumLeaf.banana";
                    break;
                default:
                    this.iconName += "." + XSTR.XSTR_INSTANCE.nextInt(4);
                    this.unlocalizedExtraTooltip = "cropsnh_tooltip.pyrolusiumLeaf.normal";
                    break;
            }
        } else {
            this.iconName = ".1";
            this.unlocalizedExtraTooltip = "cropsnh_tooltip.pyrolusiumLeaf.normal";
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getTooltip(List list) {
        super.getTooltip(list);
        if (ConfigurationHandler.enableEasterEggs) {
            list.add(StatCollector.translateToLocal(this.unlocalizedExtraTooltip));
        }
    }
}
