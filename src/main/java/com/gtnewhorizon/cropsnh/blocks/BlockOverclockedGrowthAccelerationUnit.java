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

public class BlockOverclockedGrowthAccelerationUnit extends CropsNHBlockIndustrialFarmTiredComponent {

    public final static int MAX_UPGRADE_COUNT = 1;
    public final static int MIN_TIER = VoltageIndex.ZPM;

    public BlockOverclockedGrowthAccelerationUnit() {
        super(
            Names.Objects.OverclockGrowthAccelerationUnit,
            MIN_TIER,
            VoltageIndex.UXV,
            CropsNHItemList.OverclockedGrowthAccelerationUnit_ZPM,
            CropsNHItemList.OverclockedGrowthAccelerationUnit_UV,
            CropsNHItemList.OverclockedGrowthAccelerationUnit_UHV,
            CropsNHItemList.OverclockedGrowthAccelerationUnit_UEV,
            CropsNHItemList.OverclockedGrowthAccelerationUnit_UIV,
            CropsNHItemList.OverclockedGrowthAccelerationUnit_UMV,
            CropsNHItemList.OverclockedGrowthAccelerationUnit_UXV);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advancedTooltips) {
        super.addInformation(stack, player, tooltip, advancedTooltips);
        // specific
        tooltip.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.overclockedGrowthAccelerationUnit"));
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
            .registerIcon(Reference.MOD_ID + ":industrialFarm/overclockedGrowthAccelerationUnit");
    }
}
