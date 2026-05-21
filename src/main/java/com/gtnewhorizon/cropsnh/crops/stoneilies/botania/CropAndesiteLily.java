package com.gtnewhorizon.cropsnh.crops.stoneilies.botania;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropAndesiteLily extends CropBaseStoneLily {

    public CropAndesiteLily() {
        super("andesite", new Color(0x6B6B6A), new Color(0x989896));

        final int DROP_COUNT = 1;
        final int DROP_CHANCE = 100_00;
        if (ModUtils.Botania.isModLoaded() || ModUtils.EtFuturumRequiem.isModLoaded()) {
            this.addDrop(OreDictHelper.getCopiedOreStack("stoneAndesite", DROP_COUNT), DROP_CHANCE);
        } else {
            this.addDrop(ModUtils.Chisel.getStack("andesite", DROP_COUNT, 0), DROP_CHANCE);
        }

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.botaniaAndesite);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
