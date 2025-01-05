package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

import gregtech.api.enums.Materials;

public class CropRedGraniteLily extends CropBaseStoneLily {

    public CropRedGraniteLily() {
        super("redGranite", new Color(0x871C52), new Color(0xD42C80));
        this.addDrop(Materials.GraniteRed.getDust(9), 100_00);
        this.addBlockUnderRequirement("redGranite");
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.stoneLilies.register(this);
    }
}
