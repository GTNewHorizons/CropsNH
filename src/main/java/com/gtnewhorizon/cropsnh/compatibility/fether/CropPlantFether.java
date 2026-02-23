package com.gtnewhorizon.cropsnh.compatibility.fether;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.gtnewhorizon.cropsnh.compatibility.harvestthenether.CropPlantHarvestTheNether;

public class CropPlantFether extends CropPlantHarvestTheNether {

    public CropPlantFether(Item seed, Block plant, Item fruit) {
        super(seed, plant, fruit);
    }

    @Override
    public String getInformation() {
        String name = this.seed.getUnlocalizedName();
        int index = name.indexOf("_seeds");
        name = index > 0 ? name.substring(0, index) : name;
        index = name.indexOf('.');
        name = index > 0 ? name.substring(index + 1) : name;
        return "cropsnh_journal.fether_" + name;
    }
}
