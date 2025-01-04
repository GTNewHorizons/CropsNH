package com.gtnewhorizon.cropsnh.crops.ic2;

import java.awt.Color;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import gregtech.api.enums.ItemList;
import gregtech.api.objects.XSTR;

public class CropVenomilia extends NHCropCard {

    public CropVenomilia() {
        super("venomilia", new Color(0x440F69), new Color(0x8921CC));
        this.addDrop(ItemList.IC2_Grin_Powder.get(1L), 100_00);
        this.addDrop(new ItemStack(Items.dye, 1, 5), 5_00);
    }

    @Override
    public String getCreator() {
        return "raGan";
    }

    @Override
    public int getGrowthDuration() {
        return 1800;
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public boolean spreadsWeeds(ICropStickTile te) {
        return super.spreadsWeeds(te);
    }

    @Override
    public int startsSpreadingWeedsAt() {
        return 8;
    }

    @Override
    public int getMaxGrowthStage() {
        return 6;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.flower;
    }

    @Override
    public void onEntityCollision(ICropStickTile te, Entity entity) {
        if (te.isMature() && entity instanceof EntityLivingBase) {
            int duration = (XSTR.XSTR_INSTANCE.nextInt(10) + 5) * 20;
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, duration, 0));
        }
    }
}
