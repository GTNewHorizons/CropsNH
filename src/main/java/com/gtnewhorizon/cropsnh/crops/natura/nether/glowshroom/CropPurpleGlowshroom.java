package com.gtnewhorizon.cropsnh.crops.natura.nether.glowshroom;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropPurpleGlowshroom extends NHCropCard {

    private final static ISoilList soilTypes = CropsNHSoilTypes.netherrack;

    public CropPurpleGlowshroom() {
        super("purpleGlowshroom", new Color(0x511FB6), new Color(0x8B2CD2));

        ItemStack glowshroom = CropsNHUtils.getModItem(ModUtils.Natura, "Glowshroom", 1, 1);
        this.addDrop(glowshroom.copy(), 100_00);

        this.addAlternateSeed(glowshroom.copy());

        this.addDuplicationCatalyst("dustGlowstone", 1);

        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.MUSHROOM, BiomeDictionary.Type.MAGICAL);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public int getGrowthDuration() {
        return 600;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.spore;
    }

    @Override
    public int getMaxGrowthStage() {
        return 2;
    }
}
