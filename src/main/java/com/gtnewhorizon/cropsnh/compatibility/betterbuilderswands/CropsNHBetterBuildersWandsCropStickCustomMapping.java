package com.gtnewhorizon.cropsnh.compatibility.betterbuilderswands;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;

import portablejim.bbw.basics.Point3d;
import portablejim.bbw.core.conversion.ICustomMapping;
import portablejim.bbw.shims.IPlayerShim;
import portablejim.bbw.shims.IWorldShim;

public class CropsNHBetterBuildersWandsCropStickCustomMapping implements ICustomMapping {

    @Override
    public Block getLookBlock() {
        return CropsNHBlocks.blockCropSticks;
    }

    @Override
    public int getMeta() {
        return 0;
    }

    @Override
    public ItemStack getItems(IWorldShim world, IPlayerShim player, Point3d point) {
        return CropsNHItemList.cropSticks.get(
            player.getPlayer()
                .isSneaking() ? 2 : 1);
    }

    @Override
    public boolean shouldCopyTileNBT() {
        return false;
    }
}
