package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import gregtech.api.enums.VoltageIndex;

public class CropThiosulfine extends NHCropCard {

    public CropThiosulfine() {
        super("thiosulfine", new Color(0xA49035), new Color(0xE1B33D));

        this.addDrop(CropsNHItemList.thiosulfineFlower.get(1), 100_00);

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.sulfur);

        this.addDuplicationCatalyst("dustSulfur", 1);

        // volcanic areas tend to be very sulfur rich
        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.HOT);
    }

    @Override
    public int getTier() {
        return 6;
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
    public ISoilList getSoilTypes() {
        return CropsNHSoilTypes.stone;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
