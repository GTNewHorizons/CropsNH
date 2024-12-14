package com.gtnewhorizon.cropsnh.crops.stoneilies;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;
import gregtech.api.enums.Materials;

import java.awt.Color;

public class CropBlackGraniteLily extends CropBaseStoneLily {

    public CropBlackGraniteLily() {
        super("blackGranite", new Color(0,0,0), new Color(10,10,10));
        this.addDrop(Materials.GraniteBlack.getDust(9), 100_00);
        this.addBlockUnderRequirement("blackGranite");
    }
}
