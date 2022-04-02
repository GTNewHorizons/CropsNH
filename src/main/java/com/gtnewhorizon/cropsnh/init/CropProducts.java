package com.gtnewhorizon.cropsnh.init;

import com.gtnewhorizon.cropsnh.blocks.BlockModPlant;
import com.gtnewhorizon.cropsnh.farming.CropProduce;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CropProducts {
    //Add special cases for plants here
    public static void init() {
        //poisonous potato
        CropProduce potato = ((BlockModPlant) Block.blockRegistry.getObject("CropsNH:cropPotato")).products;
        potato.addProduce(new ItemStack(Items.poisonous_potato), 10);
    }
}
