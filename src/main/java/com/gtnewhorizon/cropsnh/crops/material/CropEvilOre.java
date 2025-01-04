package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.Materials;

public class CropEvilOre extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("netherrack");

    public CropEvilOre() {
        super("evilOre", new Color(0x897B73), new Color(0xEFEBE7));
        // TODO: CHECK IF WE WANT TO KEEP THE RAW QUARTS DROPS
        this.addDrop(Materials.NetherQuartz.getDust(1), 66_66);
        this.addDrop(Materials.CertusQuartz.getDust(1), 16_67);
        this.addDrop(new ItemStack(Items.quartz, 1, 0), 16_67);
    }

    @Override
    public int getTier() {
        return 8;
    }

    @Override
    public int getGrowthDuration() {
        return 1600;
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
