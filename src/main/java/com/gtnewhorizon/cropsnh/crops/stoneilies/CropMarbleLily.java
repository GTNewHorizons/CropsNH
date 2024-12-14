package com.gtnewhorizon.cropsnh.crops.stoneilies;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;
import gregtech.api.enums.Materials;

import java.awt.Color;

public class CropMarbleLily extends CropBaseStoneLily {

    public CropMarbleLily() {
        super("marble", new Color(0xC8C8C8), new Color(0xF0F0F0));
        this.addDrop(Materials.Marble.getDust(9), 100_00);
        this.addBlockUnderRequirement("marble");
    }
}
