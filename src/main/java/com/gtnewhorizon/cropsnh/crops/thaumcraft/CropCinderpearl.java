package com.gtnewhorizon.cropsnh.crops.thaumcraft;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

import thaumcraft.common.config.ConfigBlocks;

public class CropCinderpearl extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("dirt");

    public CropCinderpearl() {
        super("cinderpearl", new Color(0xFF9611), new Color(0xFFD86F));
        this.addDrop(new ItemStack(ConfigBlocks.blockCustomPlant, 1, 3), 100_00);
        this.addAlternateSeed(new ItemStack(ConfigBlocks.blockCustomPlant, 1, 3));
        this.addBlockUnderRequirement("blaze");
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.flower.register(this);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek and mitchej123";
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getGrowthDuration() {
        return 4000;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.magic;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }

}
