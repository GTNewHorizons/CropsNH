package com.gtnewhorizon.cropsnh.crops.stoneilies.modern;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

import cpw.mods.fml.common.registry.GameRegistry;

public class CropDioriteLily extends CropBaseStoneLily {

    public CropDioriteLily() {
        super("diorite", new Color(0x909091), new Color(0xCECED0));

        final int DROP_COUNT = 1;
        final int DROP_CHANCE = 100_00;
        if (ModUtils.Botania.isModLoaded() || ModUtils.EtFuturumRequiem.isModLoaded()) {
            this.addDrop(OreDictHelper.getCopiedOreStack("stoneDiorite", DROP_COUNT), DROP_CHANCE);
        } else if (ModUtils.Chisel.isModLoaded()) {
            // gotta check if the block was registered since it's a config.
            Block efrStone = GameRegistry.findBlock(ModUtils.EtFuturumRequiem.ID, "stone");
            if (efrStone != null) {
                this.addDrop(new ItemStack(efrStone, DROP_COUNT, 3), DROP_CHANCE);
            }
        }

        this.addSubSoilRequirement(CropsNHSubSoilTypes.modernDiorite);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
