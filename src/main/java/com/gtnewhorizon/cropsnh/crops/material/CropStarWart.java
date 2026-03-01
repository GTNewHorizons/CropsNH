package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MachineOnlyGrowthRequirement;

import gregtech.api.enums.VoltageIndex;

public class CropStarWart extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropStarWart() {
        super("starWart", new Color(0x8985BE), new Color(0xFFFFFF));

        this.addDrop(CropsNHItemList.starWart.get(1), 100_00);

        this.addBlockUnderRequirement("netherStar");

        this.addDuplicationCatalyst("dustNetherStar", 1);
        this.addDuplicationCatalyst("netherStar", 1);
        this.addDuplicationCatalyst(new ItemStack(Items.skull, 3, 1));

        this.addGrowthRequirement(new MachineOnlyGrowthRequirement());

        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.DEAD);
    }

    @Override
    public int getTier() {
        return 12;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.EV;
    }

    @Override
    public int getMinSeedBedTier() {
        return VoltageIndex.EV;
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
