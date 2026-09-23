package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.ruling_0.materiallib.api.MaterialLibAPI;

import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;

public class CropGarnydinia extends NHCropCard {

    public CropGarnydinia() {
        super("garnydinia", new Color(0xA24141), new Color(0xA3A341));
        // 45% garnet dust
        this.addDrop(MaterialLibAPI.getStack(Materials.GarnetRed, Shapes.dust, 1), 22_50);
        this.addDrop(MaterialLibAPI.getStack(Materials.GarnetYellow, Shapes.dust, 1), 22_50);
        // 45% garnet gem dust
        this.addDrop(MaterialLibAPI.getStack(Materials.GarnetRed, Shapes.gem, 1), 22_50);
        this.addDrop(MaterialLibAPI.getStack(Materials.GarnetYellow, Shapes.gem, 1), 22_50);
        // 5% garnet exquisite gem
        this.addDrop(MaterialLibAPI.getStack(Materials.GarnetRed, Shapes.gemExquisite, 1), 2_50);
        this.addDrop(MaterialLibAPI.getStack(Materials.GarnetYellow, Shapes.gemExquisite, 1), 2_50);
        // 5$ crushed pure ore
        this.addDrop(MaterialLibAPI.getStack(Materials.GarnetRed, Shapes.crushedPurified, 1), 2_50);
        this.addDrop(MaterialLibAPI.getStack(Materials.GarnetYellow, Shapes.crushedPurified, 1), 2_50);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.garnetGem);

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
        return CropsNHSoilTypes.stone;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
