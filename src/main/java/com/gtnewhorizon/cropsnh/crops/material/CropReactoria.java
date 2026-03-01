package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MachineOnlyGrowthRequirement;

import gregtech.api.enums.VoltageIndex;

public class CropReactoria extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropReactoria() {
        super("reactoria", new Color(0x565301), new Color(0x989309));

        this.addDrop(CropsNHItemList.reactoriaLeaf.get(1), 75_00);
        this.addDrop(CropsNHItemList.reactoriaStem.get(1), 25_00);

        this.addBlockUnderRequirement("uranium");

        this.addDuplicationCatalyst("dustUranium", 1);
        this.addDuplicationCatalyst("dustUranium235", 1);
        this.addDuplicationCatalyst("dustUraninite", 2);

        this.addGrowthRequirement(new MachineOnlyGrowthRequirement());

        // very "spicy"
        this.addLikedBiomes(BiomeDictionary.Type.COLD, BiomeDictionary.Type.HOT);
    }

    @Override
    public int getTier() {
        return 12;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.EV;
    }

    @Override
    public int getMinSeedBedTier() {
        return VoltageIndex.EV;
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
    public ISeedShape getSeedShape() {
        return SeedShape.grains;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
