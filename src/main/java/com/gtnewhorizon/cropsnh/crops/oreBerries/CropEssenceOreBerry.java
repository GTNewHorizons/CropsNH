package com.gtnewhorizon.cropsnh.crops.oreBerries;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import net.minecraft.item.ItemStack;
import tconstruct.world.TinkerWorld;

import java.awt.Color;

public class CropEssenceOreBerry extends CropOreBerry {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("soulsand");

    public CropEssenceOreBerry() {
        super("essence", new Color(0xFF6BB324, true), new Color(0x99FF33));
        this.addDrop(new ItemStack(TinkerWorld.oreBerries, 6, 5), 100_00);
        this.addAlternateSeeds(new ItemStack(TinkerWorld.oreBerries, 1, 5));
        this.addBlockUnderRequirement("skull");
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
