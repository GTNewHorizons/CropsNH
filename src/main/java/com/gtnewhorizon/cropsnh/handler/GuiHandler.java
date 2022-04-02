package com.gtnewhorizon.cropsnh.handler;

import com.gtnewhorizon.cropsnh.container.ContainerPeripheral;
import com.gtnewhorizon.cropsnh.container.ContainerSeedAnalyzer;
import com.gtnewhorizon.cropsnh.container.ContainerSeedStorage;
import com.gtnewhorizon.cropsnh.container.ContainerSeedStorageController;
import com.gtnewhorizon.cropsnh.gui.GuiPeripheral;
import com.gtnewhorizon.cropsnh.gui.GuiSeedAnalyzer;
import com.gtnewhorizon.cropsnh.gui.GuiSeedStorage;
import com.gtnewhorizon.cropsnh.gui.GuiSeedStorageController;
import com.gtnewhorizon.cropsnh.gui.journal.GuiJournal;
import com.gtnewhorizon.cropsnh.tileentity.TileEntitySeedAnalyzer;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.TileEntityPeripheral;
import com.gtnewhorizon.cropsnh.tileentity.storage.TileEntitySeedStorage;
import com.gtnewhorizon.cropsnh.tileentity.storage.TileEntitySeedStorageController;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler{
    public static final int seedAnalyzerID = 1;
    public static final int journalID = 2;
    public static final int seedStorageID = 3;
    public static final int seedStorageControllerID = 4;
    public static final int peripheralID = 5;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        switch(ID) {
            case(seedAnalyzerID):
                if(te != null && te instanceof TileEntitySeedAnalyzer) {
                    return new ContainerSeedAnalyzer(player.inventory, (TileEntitySeedAnalyzer) te);
                }
            case(journalID): break;
            case(seedStorageID):
                if(te != null && te instanceof TileEntitySeedStorage) {
                    return new ContainerSeedStorage(player.inventory, (TileEntitySeedStorage) te);
                }
            case(seedStorageControllerID):
                if(te != null && te instanceof TileEntitySeedStorageController) {
                    return new ContainerSeedStorageController(player.inventory, (TileEntitySeedStorageController) te);
                }
            case(peripheralID):
                if(te!= null && te instanceof TileEntityPeripheral) {
                    return new ContainerPeripheral(player.inventory, (TileEntityPeripheral) te);
                }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        switch (ID) {
            case (seedAnalyzerID):
                if (te != null && te instanceof TileEntitySeedAnalyzer) {
                    return new GuiSeedAnalyzer(player.inventory, (TileEntitySeedAnalyzer) te);
                }
            case (journalID):
                ItemStack journal = player.getCurrentEquippedItem();
                return new GuiJournal(journal);
            case (seedStorageID):
                if (te != null && te instanceof TileEntitySeedStorage) {
                    return new GuiSeedStorage(player.inventory, (TileEntitySeedStorage) te);
                }
            case (seedStorageControllerID):
                if (te != null && te instanceof TileEntitySeedStorageController) {
                    return new GuiSeedStorageController(player.inventory, (TileEntitySeedStorageController) te);
                }
            case (peripheralID)   :
                if(te!= null && te instanceof TileEntityPeripheral) {
                    return new GuiPeripheral(player.inventory, (TileEntityPeripheral) te);
                }
            default:
                return null;
        }
    }
}
