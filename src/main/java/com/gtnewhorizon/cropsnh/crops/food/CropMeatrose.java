package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public class CropMeatrose extends CropFood {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("dirt");

    public CropMeatrose() {
        super("meatrose", new Color(0xDD3830), new Color(0xEF7070));
        // TODO: MOVE ITEM DROP TO CROPS NH OR SOMETHING
        this.addDrop(new ItemStack(Items.dye, 1, 9), 60_00);
        this.addDrop(new ItemStack(Items.chicken, 1, 0), 10_00);
        this.addDrop(new ItemStack(Items.fish, 1, 0), 10_00);
        this.addDrop(new ItemStack(Items.beef, 1, 0), 10_00);
        this.addDrop(new ItemStack(Items.porkchop, 1, 0), 10_00);

        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.MAGICAL);
    }

    @Override
    public String getCreator() {
        return "VintageBeef";
    }

    @Override
    public int getTier() {
        return 7;
    }

    @Override
    public int getGrowthDuration() {
        return 4200;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.flower;
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
