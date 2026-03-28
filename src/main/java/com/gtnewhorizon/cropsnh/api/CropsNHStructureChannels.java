package com.gtnewhorizon.cropsnh.api;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.structurelib.StructureLibAPI;

import gregtech.api.enums.Mods;
import gregtech.api.structure.IStructureChannels;

public enum CropsNHStructureChannels implements IStructureChannels {

    /** Controls IF Length/Tier */
    IFTier("if_tier"),
    /** Enables auto-adding IF upgrades */
    IFUpgrades("if_upgrade"),
    /** For controlling the tier of the OC upgrade since it's very jank */
    IFOCUpgradeTier("if_oc_upgrade");

    final String mName;
    final String mTooltip;

    CropsNHStructureChannels(String aName) {
        this.mName = aName;
        this.mTooltip = Reference.MOD_ID + "_tooltip.structure_channels." + aName;
    }

    @Override
    public String get() {
        return this.mName;
    }

    @Override
    public String getDefaultTooltip() {
        return StatCollector.translateToLocal(this.mTooltip);
    }

    @Override
    public void registerAsIndicator(ItemStack indicator, int channelValue) {
        StructureLibAPI.registerChannelItem(get(), Mods.ModIDs.CROPS_NH, channelValue, indicator);
    }
}
