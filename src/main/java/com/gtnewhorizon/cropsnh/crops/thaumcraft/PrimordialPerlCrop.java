package com.gtnewhorizon.cropsnh.crops.thaumcraft;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.farming.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.BlockUnderRequirement;

import gregtech.api.enums.Materials;

public class PrimordialPerlCrop extends NHCropCard {

    public PrimordialPerlCrop() {
        super("primordialBerry");
        ItemStack primPerl = thaumcraft.api.ItemApi.getItem("itemEldritchObject", 3)
            .copy();
        primPerl.stackSize = 1;
        this.addDrop(primPerl, 10000);
        this.addLikedBiomes(BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY);
        this.addAlternateSeeds(primPerl);
        this.addGrowthRequirements(
            new BlockUnderRequirement("ichorium").addMaterial(Materials.Ichorium)
                .addOreDict("oreIchorium", "blockIchorium"));
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
