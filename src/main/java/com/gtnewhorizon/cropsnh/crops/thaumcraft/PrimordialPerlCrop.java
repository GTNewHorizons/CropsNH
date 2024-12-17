package com.gtnewhorizon.cropsnh.crops.thaumcraft;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

public class PrimordialPerlCrop extends NHCropCard {

    public PrimordialPerlCrop() {
        super("primordialBerry", new Color(0x652150), new Color(0xA1D3A9));
        ItemStack primPerl = thaumcraft.api.ItemApi.getItem("itemEldritchObject", 3)
            .copy();
        primPerl.stackSize = 1;
        this.addDrop(primPerl.copy(), 10000);
        this.addLikedBiomes(BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY);
        this.addAlternateSeed(primPerl.copy());
        this.addGrowthRequirement(BlockUnderRequirement.get("ichorium"));
    }

    @Override
    public int getRenderShape() {
        return PlantRenderer.RENDER_X;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.magic;
    }

    @Override
    public boolean isSeedEnchanted() {
        return true;
    }

    @Override
    public int getTier() {
        return 16;
    }

    @Override
    public int getGrowthDuration() {
        return 375_000;
    }

    @Override
    public float getDropChance() {
        return 0.5f;
    }

    @Override
    public String getCreator() {
        // original idea came from crops++
        return "bartimaeusnek and ForTheHorde01";
    }

    @Override
    public float getCrossingThreshold() {
        // cannot cross
        return -1.0f;
    }

    @Override
    public float getBreedingThreshold() {
        // cannot breed
        return -1.0f;
    }

}
