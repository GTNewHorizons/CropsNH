package com.gtnewhorizon.cropsnh.crops.natura.nether;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import mods.natura.common.NContent;

public class CropDuskberry extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("netherrack");

    public CropDuskberry() {
        super("duskberry", new Color(0x595959), new Color(0xB4B4B4));
        this.addDrop(new ItemStack(NContent.netherBerryItem, 2, 1), 100_00);
        this.addAlternateSeed(new ItemStack(NContent.netherBerryItem, 1, 1));
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
