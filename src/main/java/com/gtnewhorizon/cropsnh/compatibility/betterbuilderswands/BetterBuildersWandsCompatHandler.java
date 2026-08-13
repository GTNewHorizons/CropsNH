package com.gtnewhorizon.cropsnh.compatibility.betterbuilderswands;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.blocks.BlockCropSticks;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import portablejim.bbw.BetterBuildersWandsMod;

public class BetterBuildersWandsCompatHandler {

    public static void postInit() {
        if (!ModUtils.BetterBuildersWands.isModLoaded()) return;

        BetterBuildersWandsMod.instance.mappingManager
            .setMapping(new CropsNHBetterBuildersWandsCropStickCustomMapping());

        // unbreakable wand
        BlockCropSticks.BLOCK_INTERACTION_WITH
            .add(ModUtils.BetterBuildersWands.getItem("wandUnbreakable"), OreDictionary.WILDCARD_VALUE);

        // diamond wand, can be disabled
        Item item = ModUtils.BetterBuildersWands.getItemAllowNull("wandDiamond");
        if (item != null) {
            BlockCropSticks.BLOCK_INTERACTION_WITH.add(item, OreDictionary.WILDCARD_VALUE);
        }

        // stone wand, can be disabled
        item = ModUtils.BetterBuildersWands.getItemAllowNull("wandStone");
        if (item != null) {
            BlockCropSticks.BLOCK_INTERACTION_WITH.add(item, OreDictionary.WILDCARD_VALUE);
        }

        // iron wand, can be disabled
        item = ModUtils.BetterBuildersWands.getItemAllowNull("wandIron");
        if (item != null) {
            BlockCropSticks.BLOCK_INTERACTION_WITH.add(item, OreDictionary.WILDCARD_VALUE);
        }
    }
}
