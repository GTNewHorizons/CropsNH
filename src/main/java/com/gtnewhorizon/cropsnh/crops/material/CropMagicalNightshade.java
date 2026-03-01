package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MachineOnlyGrowthRequirement;

import gregtech.api.enums.VoltageIndex;

public class CropMagicalNightshade extends NHCropCard {

    public CropMagicalNightshade() {
        super("magicalNightshade", new Color(0x20001B), new Color(0xB4009C));

        this.addDrop(CropsNHItemList.magicEssence.get(1), 100_00);

        this.addBlockUnderRequirement("ichorium");

        this.addDuplicationCatalyst(CropsNHItemList.magicEssence.get(1));

        this.addGrowthRequirement(new MachineOnlyGrowthRequirement());

        // this tag combination shouldn't exist.
        this.addLikedBiomes(BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.COLD);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 13;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.HV;
    }

    @Override
    public int getMinSeedBedTier() {
        return VoltageIndex.HV;
    }

    @Override
    public int getGrowthDuration() {
        return 23436;
    }

    @Override
    public float getDropChance() {
        return super.getDropChance() * 0.75f;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.magic;
    }

    @Override
    public boolean isSeedEnchanted() {
        return true;
    }
}
