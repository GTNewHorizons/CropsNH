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

import gregtech.api.enums.VoltageIndex;
import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;

public class CropRubyne extends NHCropCard {

    private final static ISoilList soilTypes = CropsNHSoilTypes.stone;

    public CropRubyne() {
        super("rubyne", new Color(0x931212), new Color(0xA64747));

        this.addDrop(MaterialLibAPI.getStack(Materials.Ruby, Shapes.dust, 1), 75_00);
        this.addDrop(MaterialLibAPI.getStack(Materials.Ruby, Shapes.gem, 1), 25_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.ruby);

        this.addDuplicationCatalyst("gemRuby", 1);
        this.addDuplicationCatalyst("dustRuby", 1);

        // Rhubarb is native to the mountains in Central Asia (Tibet, Siberia, etc.). It was originally cutltivated by
        // Russians along the Volga River.
        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.RIVER, BiomeDictionary.Type.COLD);
    }

    @Override
    public int getTier() {
        return 8;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 1200;
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
