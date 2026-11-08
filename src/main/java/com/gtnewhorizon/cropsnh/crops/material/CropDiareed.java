package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import gregtech.api.enums.VoltageIndex;

public class CropDiareed extends NHCropCard {

    public CropDiareed() {
        super("diareed", new Color(0x34BFA8), new Color(0x8CF4E2));

        this.addDrop(CropsNHItemList.diareed.get(1), 100_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.diamond);

        this.addDuplicationCatalyst("dustDiamond", 1);
        this.addDuplicationCatalyst("gemDiamond", 1);

        // Gives you the gold shoulder
        this.addLikedBiomes(BiomeDictionary.Type.COLD, BiomeDictionary.Type.SPARSE);
    }

    @Override
    public String getCreator() {
        return "Direwolf20";
    }

    @Override
    public int getTier() {
        return 12;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 10_800;
    }

    @Override
    public ISoilList getSoilTypes() {
        return CropsNHSoilTypes.stone;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.grains;
    }

    @Override
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
