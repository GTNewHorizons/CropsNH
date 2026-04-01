package com.gtnewhorizon.cropsnh.crops.TiC;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBonsai;
import com.gtnewhorizon.cropsnh.farming.registries.CompoundSoilList;

public class CropSlimyBonsai extends CropBonsai {

    private static final ISoilList soil = new CompoundSoilList(CropsNHSoilTypes.dirt, CropsNHSoilTypes.slimy);

    public CropSlimyBonsai(String id, Color color1, Color color2, String creator, int tier, ItemStack sapling,
        ItemStack log, int logCount) {
        super(id, color1, color2, creator, tier, sapling, log, logCount);
    }

    @Override
    public ISoilList getSoilTypes() {
        return soil;
    }

}
