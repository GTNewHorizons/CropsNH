package com.gtnewhorizon.cropsnh.crops.cropnh;

import java.awt.Color;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.Items;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;

import gregtech.api.objects.XSTR;

public class CropGoldfish extends NHCropCard {

    public CropGoldfish() {
        super("goldfish", new Color(0xF8910F), new Color(0xFAC815));
        this.addDrop(new ItemStack(Items.goldfish, 1), 100_00);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 450;
    }

    @Override
    public String getFlavourText() {
        return ConfigurationHandler.putAnEndToExistentialDread ? null : "cropsnh_crops.goldfish.flavour";
    }

    @Override
    public void onGrowthTick(ICropStickTile crop) {
        if (crop instanceof TileEntityCrop && XSTR.XSTR_INSTANCE.nextInt(512) == 42) {
            this.scream((TileEntityCrop) crop);
        }
    }

    @Override
    public void registerSprites(IIconRegister register) {
        super.registerSprites(register);
    }

    @Override
    public void onEntityCollision(ICropStickTile crop, Entity entity) {
        if (ConfigurationHandler.goldfishScreamWhenSteppedOn && entity instanceof EntityPlayer
            && crop instanceof TileEntityCrop) {
            this.scream((TileEntityCrop) crop);
        }
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }

    private void scream(TileEntityCrop crop) {
        if (ConfigurationHandler.putAnEndToExistentialDread) return;
        crop.getWorldObj()
            .playSoundEffect(
                crop.xCoord,
                crop.yCoord,
                crop.zCoord,
                ConfigurationHandler.goldfishScream,
                crop.isMature() ? 5.0f : 3.0f * crop.getGrowthPercent(),
                crop.isMature() ? 0.5f : (4.0f - crop.getGrowthPercent()));
    }
}
