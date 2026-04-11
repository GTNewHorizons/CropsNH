package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import gregtech.api.enums.VoltageIndex;

public class CropRubyne extends NHCropCard {

    private final static ISoilList soilTypes = CropsNHSoilTypes.stone;

    public CropRubyne() {
        super("rubyne", new Color(0x931212), new Color(0xA64747));

        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Ruby, 1L), 75_00);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.gem, Materials.Ruby, 1L), 25_00);

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.ruby);

        this.addDuplicationCatalyst("gemRuby", 1);
        this.addDuplicationCatalyst("dustRuby", 1);

        // Rhubarb is native to the mountains in Central Asia (Tibet, Siberia, etc.). It was originally cutltivated by
        // Russians along the Volga River.
        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.RIVER);
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
        return soilTypes;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
