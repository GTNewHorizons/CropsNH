package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.enums.Materials;
import gregtech.api.enums.VoltageIndex;

public class CropVoidOreBerry extends CropOreBerry {

    public CropVoidOreBerry() {
        super("void", new Color(0x130426), new Color(0x1C0639));

        this.addDrop(Materials.Void.getNuggets(1), 100_00);

        ItemStack voidSeed = CropsNHUtils.getModItem(ModUtils.Thaumcraft, "ItemResource", 1, 17);
        this.addAlternateSeed(voidSeed);

        this.addBlockUnderRequirement("void");

        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));

        this.addDuplicationCatalyst(Materials.Void.getNuggets(1));

        // tainted land (this makes me feel evil)
        this.addLikedBiomes(BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.WASTELAND);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
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
        return 4500;
    }

}
