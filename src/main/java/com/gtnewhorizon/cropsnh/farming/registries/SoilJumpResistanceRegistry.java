package com.gtnewhorizon.cropsnh.farming.registries;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.ISoilJumpResistanceRegistry;
import com.gtnewhorizon.cropsnh.utility.MetaMap;
import com.gtnewhorizon.cropsnh.utility.XSTR;

public class SoilJumpResistanceRegistry implements ISoilJumpResistanceRegistry {

    public static final int JUMP_PROOF = 100_00;
    public static final SoilJumpResistanceRegistry instance = new SoilJumpResistanceRegistry();

    private final MetaMap<Block, Integer> registry = new MetaMap<>();

    @Override
    public void setResistance(@Nonnull BlockWithMeta blockWithMeta, int resistance) {
        if (blockWithMeta.ignoreMeta()) {
            this.registry.put(blockWithMeta.getBlock(), blockWithMeta.getMeta(), resistance);
        } else {
            this.registry.putWildcard(blockWithMeta.getBlock(), resistance, false);
        }
    }

    @Override
    public boolean shouldSurvive(@Nonnull Block block, int meta) {
        int resistance = this.registry.getOrDefault(block, meta, 0);
        if (resistance <= 0) return false;
        if (resistance >= JUMP_PROOF) return true;
        return XSTR.XSTR_INSTANCE.nextInt(JUMP_PROOF) < resistance;
    }

}
