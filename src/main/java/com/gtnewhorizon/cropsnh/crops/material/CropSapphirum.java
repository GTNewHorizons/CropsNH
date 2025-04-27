package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;

public class CropSapphirum extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropSapphirum() {
        super("sapphirum", new Color(0x47478F), new Color(0x5959B3));
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sapphire, 1L), 75_00);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.gem, Materials.Sapphire, 1L), 25_00);
        this.addBlockUnderRequirement("sapphire");
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 800;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
