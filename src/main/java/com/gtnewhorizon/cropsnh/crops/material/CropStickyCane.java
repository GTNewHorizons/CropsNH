package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.ItemList;

public class CropStickyCane extends NHCropCard {

    private final static ISoilList soil = SoilRegistry.instance.get("sugarcane");

    public CropStickyCane() {
        super("stickyCane", new Color(0x8DB560), new Color(0xD9D891));
        // TODO: REPLACE WITH RESIN REPLACEMENT DOWN THE LINE
        this.addDrop(ItemList.IC2_Resin.get(1L), 10_000);
        this.addDuplicationCatalyst(ItemList.IC2_Resin.get(1L));
        // going to treat it like sugar cane
        this.addLikedBiomes(BiomeDictionary.Type.WET, BiomeDictionary.Type.HOT, BiomeDictionary.Type.SANDY);
    }

    @Override
    public ISoilList getSoilTypes() {
        return soil;
    }

    @Override
    public int getGrowthDuration() {
        return 200;
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
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
