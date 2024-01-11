package com.gtnewhorizon.cropsnh.compatibility.bloodmagic;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.blocks.BlockCrop;
import com.gtnewhorizon.cropsnh.reference.Names;

import WayofTime.alchemicalWizardry.api.harvest.IHarvestHandler;
import cpw.mods.fml.common.Optional;

@Optional.Interface(modid = Names.Mods.bloodMagic, iface = "WayofTime.alchemicalWizardry.api.harvest.IHarvestHandler")
public class HarvestHandler implements IHarvestHandler {

    @Override
    public boolean harvestAndPlant(World world, int x, int y, int z, Block block, int meta) {
        if (!(block instanceof BlockCrop)) {
            return false;
        }
        if (meta < 7) {
            return false;
        }
        ((BlockCrop) block).harvest(world, x, y, z, null, null);
        return true;
    }
}
