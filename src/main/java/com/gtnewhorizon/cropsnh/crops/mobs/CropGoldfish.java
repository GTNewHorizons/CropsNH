package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropSticks;
import com.gtnewhorizon.cropsnh.utility.XSTR;

public class CropGoldfish extends NHCropCard {

    public CropGoldfish() {
        super("goldfish", new Color(0xF8910F), new Color(0xFAC815));
        this.addDrop(new ItemStack(CropsNHItems.goldfish, 1), 60_00);
        // spooky because existential fish dread.
        this.addLikedBiomes(BiomeDictionary.Type.RIVER, BiomeDictionary.Type.WET, BiomeDictionary.Type.SPOOKY);
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
        return ConfigurationHandler.putAnEndToExistentialDread ? null : Reference.MOD_ID + "_crops.goldfish.flavour";
    }

    @Override
    public void onGrowthTick(ICropStickTile crop) {
        if (crop instanceof TileEntityCropSticks && XSTR.XSTR_INSTANCE.nextInt(512) == 42) {
            this.scream((TileEntityCropSticks) crop);
        }
    }

    @Override
    public void registerSprites(IIconRegister register) {
        super.registerSprites(register);
    }

    @Override
    public void onEntityCollision(ICropStickTile crop, Entity entity) {
        if (ConfigurationHandler.goldfishScreamWhenSteppedOn && entity instanceof EntityPlayer
            && crop instanceof TileEntityCropSticks) {
            this.scream((TileEntityCropSticks) crop);
        }
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }

    private void scream(TileEntityCropSticks crop) {
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
