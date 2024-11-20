package com.gtnewhorizon.cropsnh.loaders;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictLoader {
    public static void init() {
        // Register vanilla plants. Now with less duplication.
        OreDictionary.registerOre("seedMelon", Items.melon_seeds);
        OreDictionary.registerOre("cropMelon", Items.melon);
        OreDictionary.registerOre("seedPumpkin", Items.pumpkin_seeds);
        OreDictionary.registerOre("cropPumpkin", Blocks.pumpkin);
    }
}
