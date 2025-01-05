package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

public class CropZomplant extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("graveyard");

    public CropZomplant() {
        super("zomplant", new Color(0x3A6529), new Color(0x679056));
        this.addDrop(new ItemStack(Items.rotten_flesh, 1, 0), 100_00);
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.aggresiveMob.register(this);
        CropsNHMutationPools.nether.register(this);
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
    public int getRenderShape() {
        return PlantRenderer.RENDER_X;
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
