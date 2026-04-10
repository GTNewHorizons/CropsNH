package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.reference.Reference;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.util.GTOreDictUnificator;

public class CropTrollplant extends NHCropCard {

    public CropTrollplant() {
        super("trollplant", new Color(0x000000), new Color(0xFFFFFF));

        // TODO: WHEN IC2 GETS REMOVED, UPDATE PLANTBALL AND SCRAP DROPS
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.gem, Materials.Spinel, 1), 62_50);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Plutonium241, 1), 12_50);
        this.addDrop(ItemList.IC2_Plantball.get(1), 12_50);
        this.addDrop(ItemList.IC2_Scrap.get(1), 12_50);

        // all of these are intended to be a bit trollish in one way or another
        // just because it requires bricks as a soil
        this.addDuplicationCatalyst(new ItemStack(Items.brick, 2));
        // one more than the extruder number of bolts you get for one extruder recipe
        this.addDuplicationCatalyst("screwFoolsRuby", 5);
        // A somewhat easier "streamed-lined" output since they make a lot o fthis stuff.
        this.addDuplicationCatalyst(ItemList.IC2_Scrap.get(8));

        // a certain ogre really likes this place
        this.addLikedBiomes(BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.SPOOKY);
    }

    @Override
    public String getFlavourText() {
        return Reference.MOD_ID + "_crops.trollplant.flavour";
    }

    @Override
    public int getTier() {
        return 6;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.EV;
    }

    @Override
    public int getGrowthDuration() {
        return 4800;
    }

    @Override
    public ISoilList getSoilTypes() {
        return CropsNHSoilTypes.brick;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.troll;
    }

    @Override
    public int getMaxGrowthStage() {
        return 8;
    }

}
