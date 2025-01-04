package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public class CropBlazereed extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("netherrack");

    public CropBlazereed() {
        super("blazereed", new Color(0xB38C00), new Color(0xFFC800));
        this.addDrop(new ItemStack(Items.blaze_powder, 1, 0), 75_00);
        this.addDrop(new ItemStack(Items.blaze_rod, 1, 0), 25_00);
        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.HOT);
    }

    @Override
    public String getCreator() {
        return "Mr. Brain";
    }

    @Override
    public int getTier() {
        return 6;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getGrowthDuration() {
        return 3600;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.grains;
    }
}
