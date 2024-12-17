package com.gtnewhorizon.cropsnh.crops.natura.nether;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import mods.natura.common.NContent;

public class CropThornvine extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("netherrack");

    public CropThornvine() {
        super("thornvine", new Color(0x987005), new Color(0xDFE485));
        this.addDrop(new ItemStack(NContent.thornVines, 2), 100_00);
        this.addAlternateSeed(new ItemStack(NContent.thornVines, 1));
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public int getGrowthDuration() {
        return 450;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
