package com.gtnewhorizon.cropsnh.api.v1;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.IPlantable;

public interface ICropsNHSeed extends IPlantable {
    /** Gets the plant for this seed */
    ICropsNHPlant getPlant();

    /** Gets the tier for this seed */
    int tier();

    /** Gets the information for the journal for this seed */
    @SideOnly(Side.CLIENT)
    String getInformation();

    /** Gets the Icon for this seed */
    @SideOnly(Side.CLIENT)
    IIcon getIcon(ItemStack stack);
}
