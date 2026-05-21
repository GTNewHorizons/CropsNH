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

public class CropAndesiteLily extends CropBaseStoneLily {

    public CropAndesiteLily() {
        super("andesite", new Color(0x6B6B6A), new Color(0x989896));

        final int DROP_COUNT = 1;
        final int DROP_CHANCE = 100_00;
        if (ModUtils.Botania.isModLoaded() || ModUtils.EtFuturumRequiem.isModLoaded()) {
            this.addDrop(OreDictHelper.getCopiedOreStack("stoneAndesite", DROP_COUNT), DROP_CHANCE);
        } else if (ModUtils.Chisel.isModLoaded()) {
            // gotta check if the block was registered since it's a config.
            Block efrStone = GameRegistry.findBlock(ModUtils.EtFuturumRequiem.ID, "stone");
            if (efrStone != null) {
                this.addDrop(new ItemStack(efrStone, DROP_COUNT, 5), DROP_CHANCE);
            }
        }

        this.addBlockUnderRequirement(CropsNHBlockUnderTypes.modernAndesite);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
