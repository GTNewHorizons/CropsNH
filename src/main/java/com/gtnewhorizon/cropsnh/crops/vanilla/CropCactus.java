package com.gtnewhorizon.cropsnh.crops.vanilla;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public class CropCactus extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("sand");

    public CropCactus() {
        super("cactus", new Color(0x094210), new Color(0x117F20));

        this.addDrop(new ItemStack(Blocks.cactus, 1), 10_000);

        this.addAlternateSeed(new ItemStack(Blocks.cactus, 1));

        this.addLikedBiomes(BiomeDictionary.Type.SANDY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.cactus.name";
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.grains;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }

    @Override
    public int getGrowthDuration() {
        return 450;
    }

    @Override
    public float getEntityDamage() {
        return 1.0F;
    }
}
