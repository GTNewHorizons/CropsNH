package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.BlockUnderRequirement;

import gregtech.api.enums.Materials;

public class CropCassitine extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropCassitine() {
        super("cassitine", new Color(0x6C6C6C), new Color(0x7F7F7F));
        this.addDrop(Materials.Tin.getDustTiny(1), 100_00);
        this.addGrowthRequirement(BlockUnderRequirement.get("tin"));
        this.addDuplicationCatalyst("dustTin", 1);
        // going by the word stagnate, something cold and un-changing.
        this.addLikedBiomes(BiomeDictionary.Type.COLD, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.DRY);
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
