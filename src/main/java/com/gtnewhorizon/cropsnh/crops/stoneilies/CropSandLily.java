package com.gtnewhorizon.cropsnh.crops.stoneilies;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.awt.Color;

public class CropSandLily extends CropBaseStoneLily {

    public CropSandLily() {
        super("sand", new Color(0x87836B), new Color(0xC4BD97));
        this.addDrop(new ItemStack(Blocks.sand, 4), 100_00);
        this.addBlockUnderRequirement("sand");
    }
}
