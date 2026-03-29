package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;

public class CropGarnydinia extends NHCropCard {

    private static final ISoilList soilTypes = CropsNHSoilTypes.stone;

    public CropGarnydinia() {
        super("garnydinia", new Color(0xA24141), new Color(0xA3A341));
        // 45% garnet dust
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.GarnetRed, 1L), 22_50);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.GarnetYellow, 1L), 22_50);
        // 45% garnet gem dust
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.gem, Materials.GarnetRed, 1L), 22_50);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.gem, Materials.GarnetYellow, 1L), 22_50);
        // 5% garnet exquisite gem
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.gemExquisite, Materials.GarnetRed, 1L), 2_50);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.gemExquisite, Materials.GarnetYellow, 1L), 2_50);
        // 5$ crushed pure ore
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.crushedPurified, Materials.GarnetRed, 1L), 2_50);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.crushedPurified, Materials.GarnetYellow, 1L), 2_50);

        this.addBlockUnderRequirement("garnetGem");

        this.addDuplicationCatalyst("dustGarnetRed", 1);
        this.addDuplicationCatalyst("dustGarnetYellow", 1);
        this.addDuplicationCatalyst("dustGarnetSand", 1);
        this.addDuplicationCatalyst("gemGarnetRed", 1);
        this.addDuplicationCatalyst("gemGarnetYellow", 1);

        this.addLikedBiomes(BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.MESA);
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
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
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
