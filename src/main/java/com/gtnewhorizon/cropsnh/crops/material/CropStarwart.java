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
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;

public class CropStarwart extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropStarwart() {
        super("starwart", new Color(0x8985BE), new Color(0xFFFFFF));
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 1L), 58_34);
        this.addDrop(new ItemStack(Items.skull, 1, 1), 16_67);
        this.addDrop(new ItemStack(Items.skull, 1, 0), 8_33);
        this.addDrop(new ItemStack(Items.coal, 1, 0), 8_33);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.NetherStar, 1L), 8_33);
        this.addBlockUnderRequirement("netherStar");
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
        return SeedShape.spore;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
