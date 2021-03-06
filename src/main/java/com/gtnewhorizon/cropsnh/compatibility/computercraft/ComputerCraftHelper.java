package com.gtnewhorizon.cropsnh.compatibility.computercraft;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.init.Blocks;
import com.gtnewhorizon.cropsnh.reference.Names;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.block.Block;

public class ComputerCraftHelper extends ModHelper {
    public static Block getComputerBlock() {
        return (Block) Block.blockRegistry.getObject("ComputerCraft:CC-Computer");
    }

    @Override
    protected void onPostInit() {
        dan200.computercraft.api.ComputerCraftAPI.registerPeripheralProvider((IPeripheralProvider) Blocks.blockPeripheral);
    }

    @Override
    protected String modId() {
        return Names.Mods.computerCraft;
    }
}
