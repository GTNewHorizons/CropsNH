package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.util.GTOreDictUnificator;

public class CropLazulia extends NHCropCard {

    public CropLazulia() {
        super("lazulia", new Color(0x142EAF), new Color(0x7497EA));

        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Lapis, 1), 66_67);
        this.addDrop(new ItemStack(Items.dye, 1, 4), 33_33);

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.lapis);

        this.addDuplicationCatalyst("gemLapis", 1);
        this.addDuplicationCatalyst("dustLapis", 1);
        // used since the ancient times
        this.addLikedBiomes(BiomeDictionary.Type.HOT, BiomeDictionary.Type.SANDY);
    }

    @Override
    public int getTier() {
        return 7;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 2800;
    }

    @Override
    public ISoilList getSoilTypes() {
        return CropsNHSoilTypes.stone;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
