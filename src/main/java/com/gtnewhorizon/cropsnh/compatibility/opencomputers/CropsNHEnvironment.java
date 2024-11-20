package com.gtnewhorizon.cropsnh.compatibility.opencomputers;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.init.Blocks;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.TileEntityPeripheral;

import li.cil.oc.api.driver.EnvironmentAware;
import li.cil.oc.api.network.Environment;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverBlock;

public class CropsNHEnvironment extends DriverBlock implements EnvironmentAware {

    protected CropsNHEnvironment() {
        super(new ItemStack(Blocks.blockPeripheral));
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, int x, int y, int z) {
        return null;
    }

    /** Used for OpenComputer's NEI plugin */
    @Override
    @SuppressWarnings("unchecked")
    public Class<? extends Environment> providedEnvironment(ItemStack stack) {
        if (stack.getItem() instanceof ItemBlock) {
            ItemBlock itemBlock = (ItemBlock) stack.getItem();
            Block block = itemBlock.field_150939_a;
            if (block != null && block == Blocks.blockPeripheral) {
                return (Class<? extends Environment>) (Object) TileEntityPeripheral.class;
            }
        }
        return null;
    }
}
