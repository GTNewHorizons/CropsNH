package com.gtnewhorizon.cropsnh.crops.stoneilies;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;
import gregtech.api.enums.Materials;

import java.awt.Color;

public class CropEndStoneLily extends CropBaseStoneLily {

    public CropEndStoneLily() {
        super("endStone", new Color(0xABA67E), new Color(0xDFD9A5));
        this.addDrop(Materials.Endstone.getDust(2), 100_00);
        this.addBlockUnderRequirement("endStone");
    }

    @Override
    public int getGrowthDuration() {
        return 850;
    }
}
