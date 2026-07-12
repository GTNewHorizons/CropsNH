package com.gtnewhorizon.cropsnh.blocks;

import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.formatNumber;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.blocks.abstracts.CropsNHBlockIndustrialFarmTiredComponent;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.multi.MTEIndustrialFarm;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTECropManager;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import cpw.mods.fml.common.LoaderException;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.util.tooltip.TooltipHelper;

public class BlockSeedBed extends CropsNHBlockIndustrialFarmTiredComponent {

    private final static double SLOT_MULTIPLIER = 1.0d;
    public final static double HARVEST_ROUND_BONUS = 0.2d;

    public BlockSeedBed() {
        super(
            Names.Objects.seedBed,
            CropsNHItemList.SeedBed_MV,
            CropsNHItemList.SeedBed_HV,
            CropsNHItemList.SeedBed_EV,
            CropsNHItemList.SeedBed_IV,
            CropsNHItemList.SeedBed_LuV,
            CropsNHItemList.SeedBed_ZPM,
            CropsNHItemList.SeedBed_UV,
            CropsNHItemList.SeedBed_UHV,
            CropsNHItemList.SeedBed_UEV,
            CropsNHItemList.SeedBed_UIV,
            CropsNHItemList.SeedBed_UMV,
            CropsNHItemList.SeedBed_UXV);
    }

    public final static int MIN_TIER = VoltageIndex.MV;
    public final static int MAX_TIER = VoltageIndex.UXV;

    private final static int[] WATER_CONSUMPTION_LOOKUP;

    static {
        // runtime validation for future proofing
        if (MAX_TIER < MIN_TIER) {
            throw new LoaderException(
                String.format("MIN_TIER (%d) should be lower than MAX_TIER (%s)", MIN_TIER, MAX_TIER));
        }
        WATER_CONSUMPTION_LOOKUP = new int[MAX_TIER - MIN_TIER + 1];
        // calculate the consumption rate scalar
        for (int i = 0; i < WATER_CONSUMPTION_LOOKUP.length; i++) {
            // scalar amount is computed from max seed capacity.
            WATER_CONSUMPTION_LOOKUP[i] = (int) Math
                .ceil(BlockSeedBed.getCapacity(i + MIN_TIER) * MTEIndustrialFarm.CYCLE_TICK_RATE_SCALAR);
        }
    }

    public static int getWaterConsumption(int tier) {
        if (tier < MIN_TIER || MAX_TIER < tier) {
            throw new IndexOutOfBoundsException(
                String.format("tier should be between %d and %d (was %d)", MIN_TIER, MAX_TIER, tier));
        }
        return WATER_CONSUMPTION_LOOKUP[tier - MIN_TIER];
    }

    public static int getFertilizerConsumption(int tier) {
        return getWaterConsumption(tier);
    }

    public static int getCapacity(int tier) {
        int ret = MTECropManager.getHorizontalRadius(tier) * 2 + 1;
        return (int) ((ret * ret) * SLOT_MULTIPLIER);
    }

    public static int getMultiLength(int tier) {
        return tier - MIN_TIER + 1;
    }

    public static double getHarvestRoundBonus(int tier) {
        return tier * HARVEST_ROUND_BONUS;
    }

    public static long getBaseEUt(int tier) {
        return GTValues.VP[tier];
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advancedTooltips) {
        super.addInformation(stack, player, tooltip, advancedTooltips);
        int meta = CropsNHUtils.getItemMeta(stack);

        String roundIncreaseText = TooltipHelper
            .coloredText(TooltipHelper.percentageFormat.format(getHarvestRoundBonus(meta)), TooltipHelper.EFF_COLOR);

        tooltip.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.seedBed.0"));
        tooltip.add(
            StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.seedBed.1",
                TooltipHelper.euRateText(getBaseEUt(meta)),
                TooltipHelper.voltageTierText(meta, false)));
        tooltip.add(
            StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.seedBed.2",
                TooltipHelper.parallelText(getCapacity(meta))));
        tooltip.add(
            StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.seedBed.3",
                TooltipHelper.fluidText(getWaterConsumption(meta))));
        tooltip.add(
            StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.seedBed.4",
                TooltipHelper.fluidText(getFertilizerConsumption(meta))));
        if (advancedTooltips) {
            tooltip.add(
                StatCollector
                    .translateToLocalFormatted(Reference.MOD_ID + "_tooltip.seedBed.5.adv", roundIncreaseText));
        } else {
            tooltip.add(
                StatCollector.translateToLocalFormatted(Reference.MOD_ID + "_tooltip.seedBed.5", roundIncreaseText));
        }

        int length = getMultiLength(meta);
        String lengthText = TooltipHelper.tierText(formatNumber(length));
        if (length == 1) {
            tooltip.add(
                StatCollector.translateToLocalFormatted(Reference.MOD_ID + "_tooltip.seedBed.6.single", lengthText));
        } else {
            tooltip.add(
                StatCollector.translateToLocalFormatted(Reference.MOD_ID + "_tooltip.seedBed.6.plural", lengthText));
        }

    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1) return this.topIcons[0];
        return super.getIcon(2, meta);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);
        this.topIcons[0] = iconRegister.registerIcon(Reference.MOD_ID + ":industrialFarm/seedBed");
    }
}
