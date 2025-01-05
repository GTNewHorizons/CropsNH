package com.gtnewhorizon.cropsnh.crops.stoneilies.botania;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropAndesiteLily extends CropBaseStoneLily {

    public CropAndesiteLily() {
        super("andesite", new Color(0x6B6B6A), new Color(0x989896));
        this.addDrop(OreDictHelper.getCopiedOreStack("stoneAndesite", 1), 100_00);
        this.addBlockUnderRequirement("botaniaAndesite");
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.stoneLilies.register(this);
    }
}
