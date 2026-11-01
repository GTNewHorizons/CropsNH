package com.gtnewhorizon.cropsnh.crops.fether;

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

public class CropGlowflower extends NHCropCard {

    public CropGlowflower() {
        super("glowflower", new Color(0xA23e00), new Color(0xF59a00));

        ItemStack glowflower = CropsNHUtils.getModItem(ModUtils.Fether, "glow_flower", 2);
        ItemStack glowaltseed = CropsNHUtils.getModItem(ModUtils.Fether, "glow_flower_seeds", 1);
        this.addDrop(glowflower.copy(), 100_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(glowaltseed, 1));

        this.addDuplicationCatalyst("dustGlowstone", 1);
        // hell tags
        this.addLikedBiomes(BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.NETHER);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public ISoilList getSoilTypes() {
        return CropsNHSoilTypes.netherrack;
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
