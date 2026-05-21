package com.gtnewhorizon.cropsnh.crops.stoneilies.modern;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHBlockUnderTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

import cpw.mods.fml.common.registry.GameRegistry;

public class CropGraniteLily extends CropBaseStoneLily {

    public CropGraniteLily() {
        super("granite", new Color(0x82675B), new Color(0xBA9583));

        final int DROP_COUNT = 1;
        final int DROP_CHANCE = 100_00;
        if (ModUtils.Chisel.isModLoaded() || ModUtils.Botania.isModLoaded()) {
            this.addDrop(OreDictHelper.getCopiedOreStack("stoneGranite", DROP_COUNT), DROP_CHANCE);
        } else if (ModUtils.EtFuturumRequiem.isModLoaded()) {
            // gotta check if the block was registered since it's a config.
            Block efrStone = GameRegistry.findBlock(ModUtils.EtFuturumRequiem.ID, "stone");
            if (efrStone != null) {
                this.addDrop(new ItemStack(efrStone, DROP_COUNT, 1), DROP_CHANCE);
            }
        }

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.modernGranite);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
