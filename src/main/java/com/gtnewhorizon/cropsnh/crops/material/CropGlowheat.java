package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.VoltageIndex;

public class CropGlowheat extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("netherrack");

    // this is meant to be an upgrade to the glowflower
    public CropGlowheat() {
        super("glowheat", new Color(0xABAB00), new Color(0xD2D200));

        this.addDrop(new ItemStack(Items.glowstone_dust, 1, 0), 100_00);

        this.addBlockUnderRequirement("glowstone");

        this.addDuplicationCatalyst("dustGlowstone", 1);

        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT, BiomeDictionary.Type.NETHER);
    }

    @Override
    public float getDropChance() {
        return 1.5f;
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 1500;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getMaxGrowthStage() {
        return 7;
    }

}
