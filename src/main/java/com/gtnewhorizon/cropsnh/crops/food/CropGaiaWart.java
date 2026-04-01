package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;

public class CropGaiaWart extends NHCropCard {

    private final ISoilList soil = CropsNHSoilTypes.soulsand;

    public CropGaiaWart() {
        super(Names.Objects.gaiaWart, new Color(0x0F213A), new Color(0x2664A1));
        this.addDrop(new ItemStack(CropsNHItems.gaiaWart, 1), 10000);
        this.addAlternateSeed(new ItemStack(CropsNHItems.gaiaWart, 1));
        this.addGrowthRequirement(CropsNHBlockUnderTypes.snow);
        this.addDuplicationCatalyst(new ItemStack(Items.snowball, 1));
        // going to treat it like sugar cane
        this.addLikedBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.COLD);
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.spore;
    }

    @Override
    public int getGrowthDuration() {
        // half of what it used to be since it was getting 2x the growth on snow and now it requires snow under.
        return 1000;
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public float getDropChance() {
        return 0.8f;
    }

    @Override
    public ISoilList getSoilTypes() {
        return this.soil;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }

    @Override
    public String getUnlocalizedName() {
        return "item." + Reference.MOD_ID + ":" + Names.Objects.gaiaWart + ".name";
    }
}
