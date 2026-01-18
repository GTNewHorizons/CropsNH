package com.gtnewhorizon.cropsnh.blocks;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.blocks.abstracts.CropsNHBlockIndustrialFarmTiredComponent;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;

import gregtech.api.enums.VoltageIndex;

public class BlockGrowthAccelerationUnit extends CropsNHBlockIndustrialFarmTiredComponent {

    public final static double GROWTH_SPEED_BONUS = 1.0d;
    public final static double BASE_POWER_INCREASE = 1.25d;
    public final static int MIN_TIER = VoltageIndex.MV;

    public BlockGrowthAccelerationUnit() {
        super(
            Names.Objects.growthAccelerationUnit,
            "Growth Acceleration Unit",
            CropsNHItemList.GrowthAccelerationUnit_MV,
            CropsNHItemList.GrowthAccelerationUnit_HV,
            CropsNHItemList.GrowthAccelerationUnit_EV,
            CropsNHItemList.GrowthAccelerationUnit_IV,
            CropsNHItemList.GrowthAccelerationUnit_LuV,
            CropsNHItemList.GrowthAccelerationUnit_ZPM,
            CropsNHItemList.GrowthAccelerationUnit_UV,
            CropsNHItemList.GrowthAccelerationUnit_UHV,
            CropsNHItemList.GrowthAccelerationUnit_UEV,
            CropsNHItemList.GrowthAccelerationUnit_UIV,
            CropsNHItemList.GrowthAccelerationUnit_UMV,
            CropsNHItemList.GrowthAccelerationUnit_UXV);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advancedTooltips) {
        super.addInformation(stack, player, tooltip, advancedTooltips);
        // specific
        tooltip.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.growthAccelerationUnit.0"));
        if (advancedTooltips) {
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.growthAccelerationUnit.1.adv",
                    GROWTH_SPEED_BONUS * 100));
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.growthAccelerationUnit.2.adv",
                    BASE_POWER_INCREASE * 100));
        } else {
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.growthAccelerationUnit.1",
                    GROWTH_SPEED_BONUS * 100));
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.growthAccelerationUnit.2",
                    BASE_POWER_INCREASE * 100));
        }
        // generic
        tooltip.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.upgradeTierMustMatchSeedBed"));
    }

    @Override
    public void registerBlockIcons(IIconRegister aIconRegister) {
        super.registerBlockIcons(aIconRegister);
        this.mBottomIcon = this.mTopIcon = aIconRegister
            .registerIcon(Reference.MOD_ID + ":industrialFarm/growthAccelerationUnit");
    }
}
