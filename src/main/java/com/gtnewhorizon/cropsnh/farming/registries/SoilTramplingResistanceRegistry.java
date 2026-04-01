package com.gtnewhorizon.cropsnh.farming.registries;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.ISoilTramplingResistanceRegistry;
import com.gtnewhorizon.cropsnh.utility.MetaMap;
import com.gtnewhorizon.cropsnh.utility.XSTR;

public class SoilTramplingResistanceRegistry implements ISoilTramplingResistanceRegistry {

    public static final int IMMUNE = 100_00;
    public static final SoilTramplingResistanceRegistry instance = new SoilTramplingResistanceRegistry();

    private final MetaMap<Block, Integer> registry = new MetaMap<>();

    @Override
    public void setResistance(@Nonnull BlockWithMeta blockWithMeta, int resistance) {
        if (blockWithMeta.ignoreMeta()) {
            this.registry.putWildcard(blockWithMeta.getBlock(), resistance, false);
        } else {
            this.registry.put(blockWithMeta.getBlock(), blockWithMeta.getMeta(), resistance);
        }
    }

    @Override
    public boolean shouldTrample(@Nonnull Block block, int meta) {
        int resistance = this.registry.getOrDefault(block, meta, 0);
        if (resistance <= 0) return true;
        if (resistance >= IMMUNE) return false;
        return XSTR.XSTR_INSTANCE.nextInt(IMMUNE) >= resistance;
    }

}
