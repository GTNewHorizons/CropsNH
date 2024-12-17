package com.gtnewhorizon.cropsnh.crops.bomesoplenty;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

import biomesoplenty.api.content.BOPCBlocks;

public class CropGlowshroom extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.getCompound("dirt", "netherrack");

    public CropGlowshroom() {
        super("glowshroom", new Color(0x45AD32), new Color(0x63FA48));
        this.addDrop(new ItemStack(BOPCBlocks.mushrooms, 1, 3), 100_00);
        this.addAlternateSeed(new ItemStack(BOPCBlocks.mushrooms, 1, 3));
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getGrowthDuration() {
        return 600;
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.spore;
    }

    @Override
    public int getRenderShape() {
        return PlantRenderer.RENDER_X;
    }

    @Override
    public int getMaxGrowthStage() {
        return 5;
    }
}
