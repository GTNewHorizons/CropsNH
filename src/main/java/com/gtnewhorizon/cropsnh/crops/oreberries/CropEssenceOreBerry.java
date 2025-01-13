package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

import tconstruct.world.TinkerWorld;

public class CropEssenceOreBerry extends CropOreBerry {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropEssenceOreBerry() {
        super("essence", new Color(0xFF6BB324, true), new Color(0x99FF33));
        this.addDrop(new ItemStack(TinkerWorld.oreBerries, 6, 5), 100_00);
        this.addAlternateSeed(new ItemStack(TinkerWorld.oreBerries, 1, 5));
        this.addBlockUnderRequirement("skull");
    }

    @Override
    public void registerToPools() {
        CropsNHMutationPools.lowTierOreBerries.register(this);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }
}
