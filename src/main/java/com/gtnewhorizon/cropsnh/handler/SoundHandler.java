package com.gtnewhorizon.cropsnh.handler;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;

import com.gtnewhorizon.cropsnh.CropsNH;
import com.gtnewhorizon.cropsnh.blocks.BlockCrop;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SoundHandler {

    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onSoundPlayed(PlaySoundEvent17 event) {
        if (!ConfigurationHandler.disableSounds) {
            return;
        }
        if (event.sound == null) {
            return;
        }
        World world = CropsNH.proxy.getClientWorld();
        int x = (int) (event.sound.getXPosF() - 0.5F);
        int y = (int) (event.sound.getYPosF() - 0.5F);
        int z = (int) (event.sound.getZPosF() - 0.5F);
        if (world != null) {
            Block block = world.getBlock(x, y, z);
            if (block instanceof BlockCrop) {
                event.setResult(Event.Result.DENY);
                event.result = null;
            }
        }
    }
}
