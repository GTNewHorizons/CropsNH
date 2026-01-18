package com.gtnewhorizon.cropsnh.init;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.blocks.BlockAdvancedHarvestingUnit;
import com.gtnewhorizon.cropsnh.blocks.BlockCasingsCropsNH1;
import com.gtnewhorizon.cropsnh.blocks.BlockCropSticks;
import com.gtnewhorizon.cropsnh.blocks.BlockEnvironmentalEnhancementUnit;
import com.gtnewhorizon.cropsnh.blocks.BlockFertilizerUnit;
import com.gtnewhorizon.cropsnh.blocks.BlockGrowthAccelerationUnit;
import com.gtnewhorizon.cropsnh.blocks.BlockOverclockedGrowthAccelerationUnit;
import com.gtnewhorizon.cropsnh.blocks.BlockSeedBed;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

public class CropsNHBlocks {

    public static Block blockCropSticks;
    public static Block blockCasings1;
    public static Block blockSeedBed;
    public static Block blockFertilizerUnit;
    public static Block blockAdvancedHarvestingUnit;
    public static Block blockOverclockedGrowthAccelerationUnit;
    public static Block blockEnvironmentalEnhancementUnit;
    public static Block blockGrowthAccelerationUnit;

    public static void preInit() {
        blockCropSticks = new BlockCropSticks();
        CropsNHItemList.cropSticks.set(new ItemStack(blockCropSticks, 1, 0));
        blockCasings1 = new BlockCasingsCropsNH1();
        blockSeedBed = new BlockSeedBed();
        blockFertilizerUnit = new BlockFertilizerUnit();
        blockAdvancedHarvestingUnit = new BlockAdvancedHarvestingUnit();
        blockEnvironmentalEnhancementUnit = new BlockEnvironmentalEnhancementUnit();
        blockGrowthAccelerationUnit = new BlockGrowthAccelerationUnit();
        blockOverclockedGrowthAccelerationUnit = new BlockOverclockedGrowthAccelerationUnit();
        LogHelper.debug("Blocks registered");
    }
}
