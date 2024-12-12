package com.gtnewhorizon.cropsnh.loaders;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.BlockUnderRequirement;

import gregtech.api.enums.Materials;

public class BlockUnderRequirementLoader {

    public static void init() {
        // spotless:off
        // ores
        BlockUnderRequirement.get("iron").addBlockAndOreDict().addMaterial(Materials.Iron);
        BlockUnderRequirement.get("gold").addBlockAndOreDict().addMaterial(Materials.Gold);
        BlockUnderRequirement.get("diamond").addBlockAndOreDict().addMaterial(Materials.Diamond);
        BlockUnderRequirement.get("redstone").addBlockAndOreDict().addMaterial(Materials.Redstone);
        BlockUnderRequirement.get("lapis").addBlockAndOreDict().addMaterial(Materials.Lapis);
        BlockUnderRequirement.get("tin").addBlockAndOreDict().addMaterial(Materials.Tin);
        BlockUnderRequirement.get("copper").addBlockAndOreDict().addMaterial(Materials.Copper);
        BlockUnderRequirement.get("nickel").addBlockAndOreDict().addMaterial(Materials.Nickel);
        BlockUnderRequirement.get("lead").addBlockAndOreDict().addMaterial(Materials.Lead);
        BlockUnderRequirement.get("silver").addBlockAndOreDict().addMaterial(Materials.Silver);
        BlockUnderRequirement.get("ichorium").addBlockAndOreDict().addMaterial(Materials.Ichorium);
        // others
        BlockUnderRequirement.get("snow").addOreDict("blockSnow").addBlock(new BlockWithMeta(net.minecraft.init.Blocks.snow));
        // spotless:on
    }
}
