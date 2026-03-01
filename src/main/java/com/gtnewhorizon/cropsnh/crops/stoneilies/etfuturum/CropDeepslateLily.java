package com.gtnewhorizon.cropsnh.crops.stoneilies.etfuturum;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropDeepslateLily extends CropBaseStoneLily {

    public CropDeepslateLily() {
        super("deepslate", new Color(57, 57, 57), new Color(87, 87, 87));

        this.addBlockUnderRequirement("deepslate");

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }

    private boolean dropsLoaded = false;

    @Override
    public void onLoadComplete() {
        super.onLoadComplete();
        // needed because this is from the core mod.
        if (!dropsLoaded) {
            dropsLoaded = true;
            this.addDrop(OreDictHelper.getCopiedOreStack("dustDeepslate", 9), 100_00);
        }
    }
}
