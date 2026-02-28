package com.gtnewhorizon.cropsnh.blocks;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.blocks.abstracts.CropsNHBlockIndustrialFarmTiredComponent;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;
import com.gtnewhorizon.cropsnh.tileentity.multi.MTEIndustrialFarm;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTECropManager;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import cpw.mods.fml.common.LoaderException;
import gregtech.api.enums.VoltageIndex;

public class BlockSeedBed extends CropsNHBlockIndustrialFarmTiredComponent {

    private final static double SLOT_MULTIPLIER = 2.5d;

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
            WATER_CONSUMPTION_LOOKUP[i] = (int) Math.ceil(
                BlockSeedBed.getCapacity(i + MIN_TIER)
                    * (MTEIndustrialFarm.CYCLE_DURATION / (double) TileEntityCrop.TICK_RATE));
        }
    }

    public static int getWaterConsumption(int aTier) {
        if (aTier < MIN_TIER || MAX_TIER < aTier) {
            throw new IndexOutOfBoundsException(
                String.format("tier should be between %d and %d (was %d)", MIN_TIER, MAX_TIER, aTier));
        }
        return WATER_CONSUMPTION_LOOKUP[aTier - MIN_TIER];
    }

    public static int getCapacity(int aTier) {
        int ret = MTECropManager.getHorizontalRadius(aTier) * 2 + 1;
        return (int) ((ret * ret) * SLOT_MULTIPLIER);
    }

    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List<String> aTooltip,
        boolean aAdvancedTooltips) {
        super.addInformation(aStack, aPlayer, aTooltip, aAdvancedTooltips);
        aTooltip.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.seedBed.0"));
        int tMeta = CropsNHUtils.getItemMeta(aStack);
        aTooltip
            .add(StatCollector.translateToLocalFormatted(Reference.MOD_ID + "_tooltip.seedBed.1", getCapacity(tMeta)));
        aTooltip.add(
            StatCollector
                .translateToLocalFormatted(Reference.MOD_ID + "_tooltip.seedBed.2", getWaterConsumption(tMeta)));
    }

    @Override
    public IIcon getIcon(int aSide, int aMeta) {
        return switch (aSide) {
            case 0 -> CropsNHBlocks.blockCasings1.getIcon(1, 0);
            case 1 -> Blocks.farmland.getIcon(1, 7);
            default -> super.getIcon(aSide, aMeta);
        };
    }
}
