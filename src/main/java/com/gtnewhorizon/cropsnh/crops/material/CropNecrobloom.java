package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.XSTR;

public class CropNecrobloom extends NHCropCard {

    public CropNecrobloom() {
        super("necrobloom", new Color(0x440F69), new Color(0x8921CC));
        this.addDrop(CropsNHItemList.poisonPowder.get(1), 95_00);
        this.addDrop(new ItemStack(Items.dye, 1, 5), 5_00);
        // brings ruin
        this.addLikedBiomes(BiomeDictionary.Type.DEAD, BiomeDictionary.Type.WASTELAND);
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
    public int startsSpreadingWeedsAt() {
        return 8;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
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
