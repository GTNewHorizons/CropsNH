package com.gtnewhorizon.cropsnh.crops.vanilla;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public class CropSugarCane extends NHCropCard {

    private final static ISoilList soil = SoilRegistry.instance.get("sugarcane");

    public CropSugarCane() {
        super("sugarCane", new Color(0x698747), new Color(0xAADB74));

        this.addDrop(new ItemStack(Items.reeds, 2), 10_000);

        this.addAlternateSeed(new ItemStack(Items.reeds, 1));

        this.addLikedBiomes(BiomeDictionary.Type.WET, BiomeDictionary.Type.HOT);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public ISoilList getSoilTypes() {
        return soil;
    }

    @Override
    public int getGrowthDuration() {
        return 400;
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.grains;
    }

    @Override
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
    }
}
