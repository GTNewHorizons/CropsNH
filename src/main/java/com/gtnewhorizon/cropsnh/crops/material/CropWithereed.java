package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;

public class CropWithereed extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropWithereed() {
        super("withereed", new Color(0x161616), new Color(0x2C2C2C));
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 1), 66_67);
        this.addDrop(new ItemStack(Items.coal, 1, 0), 33_33);
        // TODO: CONSIDER DROPPING SKULL CHUNKS FROM EXTRA BEES INSTEAD
        this.addDrop(new ItemStack(Items.skull, 1, 1), 16_67);
        this.addDrop(new ItemStack(Items.skull, 1, 0), 8_33);
        this.addBlockUnderRequirement("coal");
    }

    @Override
    public String getCreator() {
        return "CovertJaguar";
    }

    @Override
    public int getTier() {
        return 8;
    }

    @Override
    public int getGrowthDuration() {
        return 4800;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
