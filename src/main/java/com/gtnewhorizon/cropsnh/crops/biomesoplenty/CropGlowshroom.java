package com.gtnewhorizon.cropsnh.crops.biomesoplenty;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropGlowshroom extends NHCropCard {

    public CropGlowshroom() {
        super("glowshroom", new Color(0x45AD32), new Color(0x63FA48));

        ItemStack glowshroom = CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "mushrooms", 1, 3);
        this.addDrop(glowshroom.copy(), 100_00);

        this.addAlternateSeed(glowshroom.copy());

        this.addDuplicationCatalyst("dustGlowstone", 1);
        // part of mushroom forest + nether
        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.MUSHROOM);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public ISoilList getSoilTypes() {
        return CropsNHSoilTypes.netherMushroom;
    }

    @Override
    public int getGrowthDuration() {
        return 600;
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.spore;
    }

    @Override
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
    }

    @Override
    public int getMaxGrowthStage() {
        return 5;
    }
}
