package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

public class CropTearstalks extends NHCropCard {

    public CropTearstalks() {
        super("tearstalks", new Color(0xBFBFBF), new Color(0xF7F7F7));
        this.addDrop(new ItemStack(Items.ghast_tear, 1, 0), 66_66);
    }

    @Override
    public String getCreator() {
        return "Neowulf";
    }

    @Override
    public int getTier() {
        return 8;
    }

    @Override
    public int getGrowthDuration() {
        return 4800;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.grains;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
