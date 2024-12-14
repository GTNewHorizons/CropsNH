package com.gtnewhorizon.cropsnh.crops.stoneilies;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;
import gregtech.api.enums.Materials;

import java.awt.Color;

public class CropRedGraniteLily extends CropBaseStoneLily {

    public CropRedGraniteLily() {
        super("redGranite", new Color(0x871C52), new Color(0xD42C80));
        this.addDrop(Materials.GraniteRed.getDust(9), 100_00);
        this.addBlockUnderRequirement("redGranite");
    }
}
