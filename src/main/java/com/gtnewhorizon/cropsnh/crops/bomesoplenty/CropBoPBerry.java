package com.gtnewhorizon.cropsnh.crops.bomesoplenty;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.awt.Color;

public class CropBoPBerry extends NHCropCard {
    public CropBoPBerry() {
        super("bopBerry", new Color(0xB33636), new Color(0xFF4C4C));
        this.addDrop(new ItemStack(biomesoplenty.api.content.BOPCItems.food, 3, 0), 100_00);
        this.addAlternateSeed(new ItemStack(biomesoplenty.api.content.BOPCItems.food, 1, 0));
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getGrowthDuration() {
        return 200;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
