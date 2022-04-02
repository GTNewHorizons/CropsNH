package com.gtnewhorizon.cropsnh.init;

import com.gtnewhorizon.cropsnh.blocks.BlockChannelValve;
import com.gtnewhorizon.cropsnh.blocks.BlockCrop;
import com.gtnewhorizon.cropsnh.blocks.BlockFence;
import com.gtnewhorizon.cropsnh.blocks.BlockFenceGate;
import com.gtnewhorizon.cropsnh.blocks.BlockGrate;
import com.gtnewhorizon.cropsnh.blocks.BlockPeripheral;
import com.gtnewhorizon.cropsnh.blocks.BlockSeedAnalyzer;
import com.gtnewhorizon.cropsnh.blocks.BlockSeedStorage;
import com.gtnewhorizon.cropsnh.blocks.BlockSprinkler;
import com.gtnewhorizon.cropsnh.blocks.BlockWaterChannel;
import com.gtnewhorizon.cropsnh.blocks.BlockWaterChannelFull;
import com.gtnewhorizon.cropsnh.blocks.BlockWaterPad;
import com.gtnewhorizon.cropsnh.blocks.BlockWaterPadFull;
import com.gtnewhorizon.cropsnh.blocks.BlockWaterTank;
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
    public static Block blockWaterTank;
    public static Block blockWaterChannel;
    public static Block blockWaterChannelFull;
    public static Block blockChannelValve;
    public static Block blockSprinkler;
    public static Block blockSeedStorage;
    public static Block blockSeedStorageController;
    public static Block blockPeripheral;
    public static Block blockFence;
    public static Block blockFenceGate;
    public static Block blockGrate;

    public static void init() {
        blockCrop = new BlockCrop();
        blockSeedAnalyzer = new BlockSeedAnalyzer();
        blockWaterPad = new BlockWaterPad();
        blockWaterPadFull = new BlockWaterPadFull();
        if(!ConfigurationHandler.disableIrrigation) {
            blockWaterTank = new BlockWaterTank();
            blockWaterChannel = new BlockWaterChannel();
            blockWaterChannelFull = new BlockWaterChannelFull();
            blockChannelValve = new BlockChannelValve();
            blockSprinkler = new BlockSprinkler();
        }
        if(!ConfigurationHandler.disableSeedStorage) {
            blockSeedStorage = new BlockSeedStorage();
            if(!ConfigurationHandler.disableSeedWarehouse) {
                //blockSeedStorageController = new BlockSeedStorageController();
            }
        }
        if(ModHelper.allowIntegration(Names.Mods.computerCraft) || ModHelper.allowIntegration(Names.Mods.openComputers)) {
            blockPeripheral = new BlockPeripheral();
        }
        if(!ConfigurationHandler.disableFences) {
            blockFence = new BlockFence();
            blockFenceGate = new BlockFenceGate();
        }
        if(!ConfigurationHandler.disableGrates) {
            blockGrate = new BlockGrate();
        }
        LogHelper.debug("Blocks registered");
    }
}
