package com.gtnewhorizon.cropsnh.api;

import net.minecraft.item.ItemStack;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;

public interface IMachineGrowthRequirement extends IGrowthRequirement {

    /**
     * Checks if the crop can grow in the current machine.
     * 
     * @param seedData  The data of the seed to be grown.
     * @param te        The tile entity that contains the seed.
     * @param catalysts The catalysts that should help the crop grow.
     * @return True if the crop can grow using the provided catalysts.
     */
    boolean canGrow(ISeedData seedData, IGregTechTileEntity te, ItemStack[] catalysts);

}
