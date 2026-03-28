package com.gtnewhorizon.cropsnh.blocks;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.CropsNHStructureChannels;
import com.gtnewhorizon.cropsnh.blocks.abstracts.CropsNHBlockIndustrialFarmTiredComponent;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;

import gregtech.api.enums.VoltageIndex;
import gregtech.api.util.tooltip.TooltipHelper;

public class BlockEnvironmentalEnhancementUnit extends CropsNHBlockIndustrialFarmTiredComponent {

    public final static int MAX_UPGRADE_COUNT = 2;
    public final static double BASE_POWER_INCREASE = 0.5d;
    public final static int MIN_TIER = VoltageIndex.MV;

    public BlockEnvironmentalEnhancementUnit() {
        super(
            Names.Objects.environmentalEnhancementUnit,
            CropsNHStructureChannels.IFTier,
            CropsNHItemList.EnvironmentalEnhancementUnit_MV,
            CropsNHItemList.EnvironmentalEnhancementUnit_HV,
            CropsNHItemList.EnvironmentalEnhancementUnit_EV,
            CropsNHItemList.EnvironmentalEnhancementUnit_IV,
            CropsNHItemList.EnvironmentalEnhancementUnit_LuV,
            CropsNHItemList.EnvironmentalEnhancementUnit_ZPM,
            CropsNHItemList.EnvironmentalEnhancementUnit_UV,
            CropsNHItemList.EnvironmentalEnhancementUnit_UHV,
            CropsNHItemList.EnvironmentalEnhancementUnit_UEV,
            CropsNHItemList.EnvironmentalEnhancementUnit_UIV,
            CropsNHItemList.EnvironmentalEnhancementUnit_UMV,
            CropsNHItemList.EnvironmentalEnhancementUnit_UXV);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advancedTooltips) {
        super.addInformation(stack, player, tooltip, advancedTooltips);

        String powerIncreaseText = TooltipHelper
            .coloredText(TooltipHelper.percentageFormat.format(BASE_POWER_INCREASE), TooltipHelper.EU_VOLT_COLOR);

        // specific
        tooltip.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.environmentalEnhancementUnit.0"));
        tooltip.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.environmentalEnhancementUnit.1"));
        if (advancedTooltips) {
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.environmentalEnhancementUnit.2.adv",
                    powerIncreaseText));
        } else {
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.environmentalEnhancementUnit.2",
                    powerIncreaseText));
        }
        // generic
        tooltip.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.upgradeTierMustMatchSeedBed"));
        tooltip.add(
            StatCollector
                .translateToLocalFormatted(Reference.MOD_ID + "_tooltip.upgradeCountLimited", MAX_UPGRADE_COUNT));
    }

    @Override
    public void registerBlockIcons(IIconRegister aIconRegister) {
        super.registerBlockIcons(aIconRegister);
        this.registerIconArray(
            aIconRegister,
            Reference.MOD_ID + ":industrialFarm/environmentalEnhancementUnit/",
            this.mTopIcons);
        System.arraycopy(mTopIcons, 0, this.mBottomIcons, 0, this.mTopIcons.length);
    }
}
