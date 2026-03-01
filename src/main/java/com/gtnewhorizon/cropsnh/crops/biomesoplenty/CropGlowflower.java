package com.gtnewhorizon.cropsnh.crops.biomesoplenty;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropGlowflower extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("dirt");

    public CropGlowflower() {
        super("glowflower", new Color(0x004D4C), new Color(0x12A4A2));

        ItemStack glowflower = CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "flowers", 2, 3);
        this.addDrop(glowflower.copy(), 100_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(glowflower, 1));

        this.addDuplicationCatalyst("dustGlowstone", 1);
        // mystic grove tags
        this.addLikedBiomes(
            BiomeDictionary.Type.WET,
            BiomeDictionary.Type.LUSH,
            BiomeDictionary.Type.MAGICAL,
            BiomeDictionary.Type.FOREST);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public int getGrowthDuration() {
        return 2000;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.flower;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
