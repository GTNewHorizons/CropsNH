package com.gtnewhorizon.cropsnh.compatibility.opencomputers;

import com.gtnewhorizon.cropsnh.init.Blocks;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.TileEntityPeripheral;
import li.cil.oc.api.driver.EnvironmentProvider;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverSidedBlock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class CropsNHEnvironment extends DriverSidedBlock implements EnvironmentProvider {

    protected CropsNHEnvironment() {
        super(new ItemStack(Blocks.blockPeripheral));
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, int x, int y, int z, ForgeDirection side) {
        return null;
    }

    @Override
    public Class<?> getEnvironment(ItemStack stack) {
        if (stack.getItem() instanceof ItemBlock) {
            ItemBlock itemBlock = (ItemBlock) stack.getItem();
            Block block = itemBlock.field_150939_a;
            if (block!=null && block==Blocks.blockPeripheral){
                return TileEntityPeripheral.class;
            }
        }
        return null;
    }
}
