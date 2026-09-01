package com.gtnewhorizon.cropsnh.compatibility.betterbuilderswands;

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

        // diamond wand
        BlockCropSticks.BLOCK_INTERACTION_WITH
            .add(ModUtils.BetterBuildersWands.getItem("wandDiamond"), OreDictionary.WILDCARD_VALUE);

        // stone wand
        BlockCropSticks.BLOCK_INTERACTION_WITH
            .add(ModUtils.BetterBuildersWands.getItem("wandStone"), OreDictionary.WILDCARD_VALUE);

        // iron wand
        BlockCropSticks.BLOCK_INTERACTION_WITH
            .add(ModUtils.BetterBuildersWands.getItem("wandIron"), OreDictionary.WILDCARD_VALUE);
    }
}
