package com.gtnewhorizon.cropsnh.crops.natura.nether;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import mods.natura.common.NContent;

public class CropBlightberry extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("netherrack");

    public CropBlightberry() {
        super("blightberry", new Color(0x3BC956), new Color(0xA3F2A0));
        this.addDrop(new ItemStack(NContent.netherBerryItem, 2, 0), 100_00);
        this.addAlternateSeed(new ItemStack(NContent.netherBerryItem, 1, 0));
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 300;
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
