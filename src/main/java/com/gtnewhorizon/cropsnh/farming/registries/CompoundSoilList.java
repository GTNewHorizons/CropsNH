package com.gtnewhorizon.cropsnh.farming.registries;

import java.util.ArrayList;

import net.minecraft.block.Block;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.ISoilList;

public class CompoundSoilList implements ISoilList {

    private final ArrayList<ISoilList> soils;

    CompoundSoilList(int initialCapacity) {
        this.soils = new ArrayList<>(initialCapacity);
    }

    public void add(ISoilList soilList) {
        this.soils.add(soilList);
    }

    @Override
    public boolean isRegistered(Block block, int meta) {
        for (ISoilList soil : this.soils) {
            if (soil.isRegistered(block, meta)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void registerSoil(BlockWithMeta... soils) {
        // empty on purpose
    }
}
