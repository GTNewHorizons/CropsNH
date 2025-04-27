package com.gtnewhorizon.cropsnh.crops.stoneilies.botania;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropGraniteLily extends CropBaseStoneLily {

    public CropGraniteLily() {
        super("granite", new Color(0x82675B), new Color(0xBA9583));
        this.addDrop(OreDictHelper.getCopiedOreStack("stoneGranite", 1), 100_00);
        this.addBlockUnderRequirement("botaniaGranite");
    }
}
