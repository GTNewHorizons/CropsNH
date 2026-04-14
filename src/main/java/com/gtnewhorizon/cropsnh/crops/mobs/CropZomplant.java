package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropZomplant extends NHCropCard {

    public CropZomplant() {
        super("zomplant", new Color(0x3A6529), new Color(0x679056));

        ItemStack rottenFlesh = new ItemStack(Items.rotten_flesh, 1, 0);
        if (ModUtils.Thaumcraft.isModLoaded()) {
            this.addDrop(rottenFlesh, 98_00);
            // low chance but that's because with high gain you've basically got a 1/25 per harvest
            this.addDrop(ModUtils.Thaumcraft.getStack("ItemZombieBrain", 1, 0), 1_50);
        } else {
            this.addDrop(rottenFlesh, 99_50);
        }
        // TODO: remove zombie head from drop table when the mob head crop is added.
        this.addDrop(new ItemStack(Items.skull, 1, 2), 50);

        this.addLikedBiomes(BiomeDictionary.Type.DEAD, BiomeDictionary.Type.WASTELAND);
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public int getGrowthDuration() {
        return 1800;
    }

    @Override
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
    }

    @Override
    public ISoilList getSoilTypes() {
        return CropsNHSoilTypes.graveyard;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
