package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.VoltageIndex;

public class CropGalvania extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropGalvania() {
        super("galvania", new Color(0x6FDC5C), new Color(0x9BE78D));

        this.addDrop(CropsNHItemList.galvaniaLeaf.get(1), 100_00);

        this.addBlockUnderRequirement("zinc");

        this.addDuplicationCatalyst("dustZinc", 1);

        // probably won't rust
        this.addLikedBiomes(BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT);
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
        return soilTypes;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
