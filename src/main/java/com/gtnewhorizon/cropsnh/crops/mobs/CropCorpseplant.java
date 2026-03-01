package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public class CropCorpseplant extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("graveyard");

    public CropCorpseplant() {
        super("corpseplant", new Color(0x551515), new Color(0xE32F2F));
        this.addDrop(new ItemStack(Items.dye, 1, 15), 62_50);
        this.addDrop(new ItemStack(Items.rotten_flesh, 1, 0), 25_00);
        this.addDrop(new ItemStack(Items.bone, 1, 0), 12_50);

        this.addLikedBiomes(BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY);
    }

    @Override
    public String getCreator() {
        return "Mr. Kenny";
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getGrowthDuration() {
        return 800;
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
