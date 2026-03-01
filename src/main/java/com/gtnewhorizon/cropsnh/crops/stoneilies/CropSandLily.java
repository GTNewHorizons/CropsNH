package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;

public class CropSandLily extends CropBaseStoneLily {

    public CropSandLily() {
        super("sand", new Color(0x87836B), new Color(0xC4BD97));

        this.addDrop(new ItemStack(Blocks.sand, 4), 100_00);

        this.addBlockUnderRequirement("sand");

        this.addLikedBiomes(BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT);
    }
}
