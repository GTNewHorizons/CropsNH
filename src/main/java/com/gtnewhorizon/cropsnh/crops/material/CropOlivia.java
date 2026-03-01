package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.util.GTOreDictUnificator;

public class CropOlivia extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropOlivia() {
        super("olivia", new Color(0x79CD79), new Color(0x96FF96));

        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Olivine, 1L), 75_00);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.gem, Materials.Olivine, 1L), 25_00);

        this.addBlockUnderRequirement("olivine");

        this.addDuplicationCatalyst("gemOlivine", 1);
        this.addDuplicationCatalyst("dustOlivine", 1);
        // olives like hilly climates with usually dry seasons
        this.addLikedBiomes(BiomeDictionary.Type.HILLS, BiomeDictionary.Type.DRY);
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 400;
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
