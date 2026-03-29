package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.enums.VoltageIndex;

public class CropEssenceOreBerry extends CropOreBerry {

    private final static ISoilList soilTypes = CropsNHSoilTypes.stone;

    public CropEssenceOreBerry() {
        super("essence", new Color(0xFF6BB324, true), new Color(0x99FF33));

        ItemStack oreBerries = CropsNHUtils.getModItem(ModUtils.TinkerConstruct, "oreBerries", 6, 5);
        this.addDrop(oreBerries.copy(), 100_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(oreBerries, 1));

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.skull);

        this.addLikedBiomes(BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.DEAD);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }
}
