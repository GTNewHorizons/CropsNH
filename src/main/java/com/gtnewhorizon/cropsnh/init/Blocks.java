package com.gtnewhorizon.cropsnh.init;

import com.gtnewhorizon.cropsnh.blocks.BlockCrop;
import com.gtnewhorizon.cropsnh.blocks.BlockPeripheral;
import com.gtnewhorizon.cropsnh.blocks.BlockSeedAnalyzer;
import com.gtnewhorizon.cropsnh.blocks.BlockSeedStorage;
import com.gtnewhorizon.cropsnh.blocks.BlockWaterPad;
import com.gtnewhorizon.cropsnh.blocks.BlockWaterPadFull;
import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import net.minecraft.block.Block;

public class Blocks {
    public static Block blockCrop;
    public static Block blockSeedAnalyzer;
    public static Block blockWaterPad;
    public static Block blockWaterPadFull;
    public static Block blockSeedStorage;
    public static Block blockPeripheral;

    public static void init() {
        blockCrop = new BlockCrop();
        blockSeedAnalyzer = new BlockSeedAnalyzer();
        blockWaterPad = new BlockWaterPad();
        blockWaterPadFull = new BlockWaterPadFull();
        if(!ConfigurationHandler.disableSeedStorage) {
            blockSeedStorage = new BlockSeedStorage();
        }
        if(ModHelper.allowIntegration(Names.Mods.computerCraft) || ModHelper.allowIntegration(Names.Mods.openComputers)) {
            blockPeripheral = new BlockPeripheral();
        }
        LogHelper.debug("Blocks registered");
    }
}
