package com.gtnewhorizon.cropsnh.crops.stoneilies.etfuturum;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropDeepslateLily extends CropBaseStoneLily {

    public CropDeepslateLily() {
        super("deepslate", new Color(57, 57, 57), new Color(87, 87, 87));

        this.addDrop(ModUtils.NewHorizonsCoreMod.getStack("DeepslateDust", 9, 0), 100_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.deepslate);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
