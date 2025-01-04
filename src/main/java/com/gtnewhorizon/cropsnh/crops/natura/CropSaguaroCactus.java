package com.gtnewhorizon.cropsnh.crops.natura;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import mods.natura.common.NContent;

public class CropSaguaroCactus extends CropFood {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("sand");

    public CropSaguaroCactus() {
        super("saguaroCactus", new Color(0x3D401B), new Color(0x828839));
        this.addDrop(new ItemStack(NContent.saguaro, 2, 0), 50_00);
        this.addDrop(new ItemStack(NContent.seedFood, 3, 0), 50_00);
        this.addAlternateSeed(new ItemStack(NContent.seedFood, 1, 0));
        this.addAlternateSeed(new ItemStack(NContent.saguaro, 1, 0));
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
        return 450;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.grains;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
