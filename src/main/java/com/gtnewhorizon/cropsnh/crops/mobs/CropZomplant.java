package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public class CropZomplant extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("graveyard");

    public CropZomplant() {
        super("zomplant", new Color(0x3A6529), new Color(0x679056));
        this.addDrop(new ItemStack(Items.rotten_flesh, 1, 0), 100_00);
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public int getGrowthDuration() {
        return 1800;
    }

    @Override
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
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
