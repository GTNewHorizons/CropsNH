package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.BlockUnderRequirement;

import gregtech.api.enums.Materials;

public class CropPlumbshade extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropPlumbshade() {
        super("plumbshade", new Color(0x523B52), new Color(0x6D4E6D));
        this.addDrop(Materials.Lead.getDustTiny(1), 100_00);
        this.addGrowthRequirement(BlockUnderRequirement.get("lead"));
        this.addDuplicationCatalyst("dustLead", 1);
        // hibiscus likes warm temperate/tropical areas.
        this.addLikedBiomes(BiomeDictionary.Type.HOT, BiomeDictionary.Type.WET);
    }

    @Override
    public int getTier() {
        return 6;
    }

    @Override
    public float getDropChance() {
        return super.getDropChance() / 2.0f;
    }

    @Override
    public int getGrowthDuration() {
        return 2800;
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
