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

public class CropOlivia extends NHCropCard {

    public CropOlivia() {
        super("olivia", new Color(0x79CD79), new Color(0x96FF96));

        this.addDrop(MaterialLibAPI.getStack(Materials.Olivine, Shapes.dust, 1), 75_00);
        this.addDrop(MaterialLibAPI.getStack(Materials.Olivine, Shapes.gem, 1), 25_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.olivine);

        this.addDuplicationCatalyst("gemOlivine", 1);
        this.addDuplicationCatalyst("dustOlivine", 1);
        // olives like hilly climates with usually dry seasons
        this.addLikedBiomes(BiomeDictionary.Type.HILLS, BiomeDictionary.Type.DRY);
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 400;
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
