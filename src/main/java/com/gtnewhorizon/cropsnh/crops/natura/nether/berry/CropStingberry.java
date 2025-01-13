package com.gtnewhorizon.cropsnh.crops.natura.nether.berry;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

import mods.natura.common.NContent;

public class CropStingberry extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("netherrack");

    public CropStingberry() {
        super("stingberry", new Color(0x727F3D), new Color(0x8BDC3C));
        this.addDrop(new ItemStack(NContent.netherBerryItem, 2, 3), 100_00);
        this.addAlternateSeed(new ItemStack(NContent.netherBerryItem, 1, 3));
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
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 300;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
