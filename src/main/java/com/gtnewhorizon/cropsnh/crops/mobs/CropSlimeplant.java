package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

public class CropSlimeplant extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("dirt");

    public CropSlimeplant() {
        super("slimeplant", new Color(0x5A3F1E), new Color(0x9DF28A));
        this.addDrop(new ItemStack(Items.slime_ball, 1, 0), 100_00);
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.aggresiveMob.register(this);
    }

    @Override
    public String getCreator() {
        return "Neowulf";
    }

    @Override
    public int getTier() {
        return 6;
    }

    @Override
    public int getGrowthDuration() {
        return 1200;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.bonsai;
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
