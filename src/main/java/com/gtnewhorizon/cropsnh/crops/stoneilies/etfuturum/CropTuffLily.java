package com.gtnewhorizon.cropsnh.crops.stoneilies.etfuturum;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropTuffLily extends CropBaseStoneLily {

    public CropTuffLily() {
        super("tuff", new Color(0x57574D), new Color(0x95978D));

        this.addDrop(ModUtils.NewHorizonsCoreMod.getStack("TuffDust", 9, 0), 100_00);

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.tuff);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }

}
