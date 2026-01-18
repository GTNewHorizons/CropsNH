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
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import gregtech.api.enums.VoltageIndex;

public class BlockFertilizerUnit extends CropsNHBlockIndustrialFarmTiredComponent {

    public final static int MAX_UPGRADE_COUNT = 1;
    public final static double BASE_POWER_INCREASE = 0.5d;
    public final static double GROWTH_SPEED_MULTIPLIER = 0.5d;
    public final static double HARVEST_ROUND_BONUS = 0.5d;
    public final static int MIN_TIER = VoltageIndex.MV;

    public BlockFertilizerUnit() {
        super(
            Names.Objects.fertilizerUnit,
            "Fertilizer Unit",
            CropsNHItemList.FertilizerUnit_MV,
            CropsNHItemList.FertilizerUnit_HV,
            CropsNHItemList.FertilizerUnit_EV,
            CropsNHItemList.FertilizerUnit_IV,
            CropsNHItemList.FertilizerUnit_LuV,
            CropsNHItemList.FertilizerUnit_ZPM,
            CropsNHItemList.FertilizerUnit_UV,
            CropsNHItemList.FertilizerUnit_UHV,
            CropsNHItemList.FertilizerUnit_UEV,
            CropsNHItemList.FertilizerUnit_UIV,
            CropsNHItemList.FertilizerUnit_UMV,
            CropsNHItemList.FertilizerUnit_UXV);
    }

    public static int getFertilizerConsumptionPerCycle(int aTier) {
        return BlockSeedBed.getWaterConsumption(aTier);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advancedTooltips) {
        super.addInformation(stack, player, tooltip, advancedTooltips);
        // specific
        tooltip.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.fertilizerUnit.0"));
        tooltip.add(
            StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.fertilizerUnit.1",
                getFertilizerConsumptionPerCycle(CropsNHUtils.getItemMeta(stack))));
        if (advancedTooltips) {
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.fertilizerUnit.2.adv",
                    GROWTH_SPEED_MULTIPLIER * 100));
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.fertilizerUnit.3.adv",
                    HARVEST_ROUND_BONUS * 100));
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.fertilizerUnit.4.adv",
                    BASE_POWER_INCREASE * 100));
        } else {
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.fertilizerUnit.2",
                    GROWTH_SPEED_MULTIPLIER * 100));
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.fertilizerUnit.3",
                    HARVEST_ROUND_BONUS * 100));
            tooltip.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.fertilizerUnit.4",
                    BASE_POWER_INCREASE * 100));
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
        this.mBottomIcon = this.mTopIcon = aIconRegister
            .registerIcon(Reference.MOD_ID + ":industrialFarm/fertilizerUnit");
    }
}
