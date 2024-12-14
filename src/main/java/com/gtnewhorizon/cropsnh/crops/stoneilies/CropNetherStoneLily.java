package com.gtnewhorizon.cropsnh.crops.stoneilies;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;
import gregtech.api.enums.Materials;

import java.awt.Color;

public class CropNetherStoneLily extends CropBaseStoneLily {

    public CropNetherStoneLily() {
        super("netherStone", new Color(0x911717), new Color(0xC21F1F));
        this.addDrop(Materials.Netherrack.getDust(9), 100_00);
        this.addBlockUnderRequirement("netherrack");
    }
}
