package com.gtnewhorizon.cropsnh.farming.registries;

import net.minecraft.block.Block;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.utility.MetaSet;

public class SoilList implements ISoilList {

    private final MetaSet<Block> validSoils = new MetaSet<>();

    /**
     * Which registry the soil type is linked to
     */
    private final SoilRegistry registry;
    /**
     * True if the list is the "global" list for the register.
     */
    private final boolean isGlobal;

    SoilList(SoilRegistry registry, boolean isGlobal) {
        this.registry = registry;
        this.isGlobal = isGlobal;
    }

    @Override
    public boolean isRegistered(Block block, int meta) {
        return this.validSoils.contains(block, meta);
    }

    @Override
    public void registerSoil(BlockWithMeta... soils) {
        // this ensures that any registered soil is also registered to the global tracker
        if (!this.isGlobal) this.registry.registerGlobalSoil(soils);
        // then add the soil regularly
        for (BlockWithMeta soil : soils) {
            if (soil == null || soil.getBlock() == null) continue;
            this.validSoils.add(soil.getBlock(), soil.ignoreMeta() ? -1 : soil.getMeta());
        }
    }
}
