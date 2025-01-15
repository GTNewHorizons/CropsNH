package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public class CropBlackthorn extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("dirt");

    public CropBlackthorn() {
        super("blackthorn", new Color(0x000000), new Color(0x2E1F27));
        this.addDrop(new ItemStack(Items.dye, 1, 0), 100_00);
        this.addLikedBiomes(BiomeDictionary.Type.WET, BiomeDictionary.Type.OCEAN);
    }

    @Override
    public String getCreator() {
        return "IC2 Team";
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getGrowthDuration() {
        return 600;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
