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

public class BlockAdvancedHarvestingUnit extends CropsNHBlockIndustrialFarmTiredComponent {

    public final static int MAX_UPGRADE_COUNT = 2;
    public final static double HARVEST_ROUND_MULTIPLIER = 0.2d;
    public final static double BASE_POWER_INCREASE = 0.5d;
    public final static int MIN_TIER = VoltageIndex.MV;

    public BlockAdvancedHarvestingUnit() {
        super(
            Names.Objects.advancedHarvestingUnit,
            CropsNHStructureChannels.IFTier,
            CropsNHItemList.AdvancedHarvestingUnit_MV,
            CropsNHItemList.AdvancedHarvestingUnit_HV,
            CropsNHItemList.AdvancedHarvestingUnit_EV,
            CropsNHItemList.AdvancedHarvestingUnit_IV,
            CropsNHItemList.AdvancedHarvestingUnit_LuV,
            CropsNHItemList.AdvancedHarvestingUnit_ZPM,
            CropsNHItemList.AdvancedHarvestingUnit_UV,
            CropsNHItemList.AdvancedHarvestingUnit_UHV,
            CropsNHItemList.AdvancedHarvestingUnit_UEV,
            CropsNHItemList.AdvancedHarvestingUnit_UIV,
            CropsNHItemList.AdvancedHarvestingUnit_UMV,
            CropsNHItemList.AdvancedHarvestingUnit_UXV);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advancedTooltips) {
        super.addInformation(stack, player, tooltip, advancedTooltips);

        String roundIncreaseText = TooltipHelper
            .coloredText(TooltipHelper.percentageFormat.format(HARVEST_ROUND_MULTIPLIER), TooltipHelper.EFF_COLOR);
        String powerIncreaseText = TooltipHelper
            .coloredText(TooltipHelper.percentageFormat.format(BASE_POWER_INCREASE), TooltipHelper.EU_VOLT_COLOR);

        // specific
        tooltip.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.advancedHarvestingUnit.0"));
        if (advancedTooltips) {
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.advancedHarvestingUnit.1.adv",
                    roundIncreaseText));
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.advancedHarvestingUnit.2.adv",
                    powerIncreaseText));
        } else {
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.advancedHarvestingUnit.1",
                    roundIncreaseText));
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.advancedHarvestingUnit.2",
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
            Reference.MOD_ID + ":industrialFarm/advancedHarvestingUnit/",
            this.mTopIcons);
        System.arraycopy(mTopIcons, 0, this.mBottomIcons, 0, this.mTopIcons.length);
    }
}
