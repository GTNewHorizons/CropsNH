package com.gtnewhorizon.cropsnh.crops.stoneilies.botania;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

import java.awt.Color;

public class CropAndesiteLily extends CropBaseStoneLily {

    public CropAndesiteLily() {
        super("andesite", new Color(0x6B6B6A), new Color(0x989896));
        this.addDrop(OreDictHelper.getCopiedOreStack("stoneAndesite", 1), 100_00);
        this.addBlockUnderRequirement("botaniaAndesite");
    }
}
