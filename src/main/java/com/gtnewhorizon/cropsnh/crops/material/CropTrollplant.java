package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;

public class CropTrollplant extends NHCropCard {

    // Yes, this is entirely arbitrary and specifically exists to mess with people
    private final static ISoilList soilTypes = SoilRegistry.instance.get("brick");

    public CropTrollplant() {
        super("trollplant", new Color(0x000000), new Color(0xFFFFFF));
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.gem, Materials.FoolsRuby, 1), 62_50);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Plutonium241, 1), 12_50);
        this.addDrop(ItemList.IC2_Plantball.get(1), 12_50);
        this.addDrop(ItemList.IC2_Scrap.get(1), 12_50);
    }

    @Override
    public String getFlavourText() {
        return "cropsnh_crops.trollplant.flavour";
    }

    @Override
    public int getTier() {
        return 6;
    }

    @Override
    public int getGrowthDuration() {
        return 4800;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.troll;
    }

    @Override
    public int getMaxGrowthStage() {
        return 8;
    }

}
