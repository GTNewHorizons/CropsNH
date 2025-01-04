package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.Materials;

public class CropDiareed extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropDiareed() {
        super("diareed", new Color(0x34BFA8), new Color(0x8CF4E2));
        // TODO: PONDER IF DIAREED SHOULD STILL BE ALLOWED TO DROP STRAIGHT DIAMONDS
        this.addDrop(new ItemStack(Items.diamond, 1, 0), 75_00);
        this.addDrop(Materials.Diamond.getDust(1), 25_00);
        this.addBlockUnderRequirement("diamond");
    }

    @Override
    public String getCreator() {
        return "Direwolf20";
    }

    @Override
    public int getTier() {
        return 12;
    }

    @Override
    public int getGrowthDuration() {
        return 7200;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.grains;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
