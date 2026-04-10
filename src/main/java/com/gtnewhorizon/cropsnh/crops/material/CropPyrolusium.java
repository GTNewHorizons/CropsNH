package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.reference.Reference;

import gregtech.api.enums.VoltageIndex;

public class CropPyrolusium extends NHCropCard {

    public CropPyrolusium() {
        super("pyrolusium", new Color(0xB51900), new Color(0xCF1E11));

        this.addDrop(CropsNHItemList.pyrolusiumLeaf.get(1), 100_00);

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.manganese);

        this.addDuplicationCatalyst("dustManganese", 1);
        // manganese burns very bright
        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.HOT);
    }

    @Override
    public String getFlavourText() {
        return ConfigurationHandler.enableEasterEggs ? Reference.MOD_ID + "_crops.pyrolusium.flavour"
            : super.getFlavourText();
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
        return 2400;
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
