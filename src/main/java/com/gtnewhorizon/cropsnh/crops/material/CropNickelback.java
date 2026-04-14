package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.reference.Reference;

import gregtech.api.enums.VoltageIndex;

public class CropNickelback extends NHCropCard {

    public CropNickelback() {
        super("nickelback", new Color(0x7E81AD), new Color(0xB7B8E9));

        this.addDrop(CropsNHItemList.nickelbackLeaf.get(1), 100_00);

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.nickel);

        this.addDuplicationCatalyst("dustNickel", 1);
        // Look at this photograph, every time I do, it makes me cry :(
        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.LUSH);
    }

    @Override
    public String getFlavourText() {
        return Reference.MOD_ID + "_crops.nickelback.flavour";
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 1000;
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
