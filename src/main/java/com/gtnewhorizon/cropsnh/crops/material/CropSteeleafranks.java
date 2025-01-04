package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;
import twilightforest.item.TFItems;

public class CropSteeleafranks extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropSteeleafranks() {
        super("steeleafranks", new Color(0x163916), new Color(0x327F32));
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Steeleaf, 1L), 25_00);
        this.addDrop(new ItemStack(TFItems.steeleafIngot, 1, 0), 25_00);
    }

    @Override
    public String getCreator() {
        return "Benimatic";
    }

    @Override
    public int getTier() {
        return 10;
    }

    @Override
    public int getGrowthDuration() {
        return 6000;
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
