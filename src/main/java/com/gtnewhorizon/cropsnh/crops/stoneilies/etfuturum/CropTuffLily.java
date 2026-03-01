package com.gtnewhorizon.cropsnh.crops.stoneilies.etfuturum;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropTuffLily extends CropBaseStoneLily {

    public CropTuffLily() {
        super("tuff", new Color(0x57574D), new Color(0x95978D));

        this.addBlockUnderRequirement("tuff");

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }

    private boolean dropsLoaded = false;

    @Override
    public void onLoadComplete() {
        super.onLoadComplete();
        // needed because this is from the core mod.
        if (!dropsLoaded) {
            dropsLoaded = true;
            this.addDrop(OreDictHelper.getCopiedOreStack("dustTuff", 9), 100_00);
        }
    }
}
