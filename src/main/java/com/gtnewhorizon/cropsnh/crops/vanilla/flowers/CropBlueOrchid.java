package com.gtnewhorizon.cropsnh.crops.vanilla.flowers;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFlower;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

public class CropBlueOrchid extends CropVanillaFlower {

    public CropBlueOrchid() {
        super("blueOrchid", new Color(0x2F5488), new Color(0x8FB9F4));
        this.addDrop(new ItemStack(Items.dye, 1, 12), 10_000);
        this.addAlternateSeed(new ItemStack(Blocks.red_flower, 1, 1));
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.flower2.blueOrchid.name";
    }

    @Override
    public int getRenderShape() {
        return PlantRenderer.RENDER_X;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
