package com.gtnewhorizon.cropsnh.crops.biomesoplenty;

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

public class CropEyebulb extends NHCropCard {

    private final static ISoilList soilTypes = CropsNHSoilTypes.netherrack;

    public CropEyebulb() {
        super("eyebulb", new Color(0x552323), new Color(0x875D5D));

        ItemStack eyeBulb = CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "flowers", 1, 13);
        this.addDrop(eyeBulb.copy(), 100_00);

        this.addAlternateSeed(eyeBulb.copy());

        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.SPOOKY);
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
        return 1;
    }

    @Override
    public int getGrowthDuration() {
        return 450;
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
