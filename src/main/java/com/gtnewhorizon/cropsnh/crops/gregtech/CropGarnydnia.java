package com.gtnewhorizon.cropsnh.crops.gregtech;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;

public class CropGarnydnia extends NHCropCard {

    private static final ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropGarnydnia() {
        super("garnydinia", new Color(0xA24141), new Color(0xA3A341));
        this.addBlockUnderRequirement("garnetGem");
        // 5% exquisite gem
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.gemExquisite, Materials.GarnetRed, 1L), 2_50);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.gemExquisite, Materials.GarnetYellow, 1L), 2_50);
        // 15% gem
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.gem, Materials.GarnetRed, 1L), 7_50);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.gem, Materials.GarnetYellow, 1L), 7_50);
        // 39% dust
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.GarnetRed, 1L), 19_50);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.GarnetYellow, 1L), 19_50);
        // 19% small dust
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dustSmall, Materials.GarnetRed, 1L), 8_50);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dustSmall, Materials.GarnetYellow, 1L), 8_50);
        // 18% small tiny
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dustTiny, Materials.GarnetRed, 1L), 9_00);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dustTiny, Materials.GarnetYellow, 1L), 9_00);
        // 3% crushed pure ore
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.crushedPurified, Materials.GarnetRed, 1L), 1_50);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.crushedPurified, Materials.GarnetYellow, 1L), 1_50);
        // 1% purified garnet sand
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.crushedPurified, Materials.GarnetSand, 1L), 8_00);
    }

    @Override
    public String getCreator() {
        return "moronwmachinegun";
    }

    @Override
    public int getTier() {
        return 7;
    }

    @Override
    public int getGrowthDuration() {
        return 850;
    }

    @Override
    public int getRenderShape() {
        return PlantRenderer.RENDER_X;
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
