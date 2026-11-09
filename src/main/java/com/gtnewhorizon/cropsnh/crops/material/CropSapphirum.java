package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.ruling_0.materiallib.api.MaterialLibAPI;

import gregtech.api.enums.VoltageIndex;
import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;

public class CropSapphirum extends NHCropCard {

    public CropSapphirum() {
        super("sapphirum", new Color(0x47478F), new Color(0x5959B3));

        this.addDrop(MaterialLibAPI.getStack(Materials.Sapphire, Shapes.dust, 1), 75_00);
        this.addDrop(MaterialLibAPI.getStack(Materials.Sapphire, Shapes.gem, 1), 25_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.sapphire);

        this.addDuplicationCatalyst("dustSapphire", 1);
        this.addDuplicationCatalyst("gemSapphire", 1);

        this.addLikedBiomes(BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.COLD);
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 800;
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
