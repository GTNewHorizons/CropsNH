package com.gtnewhorizon.cropsnh.crops.stoneilies.botania;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

import java.awt.Color;

public class CropDioriteLily extends CropBaseStoneLily {

    public CropDioriteLily() {
        super("diorite", new Color(0x909091), new Color(0xCECED0));
        this.addDrop(OreDictHelper.getCopiedOreStack("stoneDiorite", 1), 100_00);
        this.addBlockUnderRequirement("botaniaDiorite");
    }
}
