package com.gtnewhorizon.cropsnh.crops.natura.nether;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

import mods.natura.common.NContent;

public class CropGreenGlowshroom extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("netherrack");

    public CropGreenGlowshroom() {
        super("greenGlowshroom", new Color(0x7CFF1B), new Color(0xC6FF50));
        this.addDrop(new ItemStack(NContent.glowshroom, 1, 0), 100_00);
        this.addAlternateSeed(new ItemStack(NContent.glowshroom, 1, 0));
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.nether.register(this);
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
