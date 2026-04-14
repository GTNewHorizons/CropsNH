package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import gregtech.api.enums.Materials;

public class CropPlumbshade extends NHCropCard {

    public CropPlumbshade() {
        super("plumbshade", new Color(0x523B52), new Color(0x6D4E6D));
        this.addDrop(Materials.Lead.getDustTiny(1), 100_00);
        this.addGrowthRequirement(CropsNHBlockUnderTypes.lead);
        this.addDuplicationCatalyst("dustLead", 1);
        // hibiscus likes warm temperate/tropical areas.
        this.addLikedBiomes(BiomeDictionary.Type.HOT, BiomeDictionary.Type.WET);
    }

    @Override
    public int getTier() {
        return 6;
    }

    @Override
    public int getGrowthDuration() {
        return 2800;
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
