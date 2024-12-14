package com.gtnewhorizon.cropsnh.crops.stoneilies;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;
import gregtech.api.enums.Materials;

import java.awt.Color;

public class CropStoneLily extends CropBaseStoneLily {

    public CropStoneLily() {
        super("stone", new Color(0x919191), new Color(0xCDCDCD));
        this.addDrop(Materials.Stone.getDust(9), 100_00);
        this.addBlockUnderRequirement("stone");
    }
}
