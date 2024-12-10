package com.gtnewhorizon.cropsnh.crops.gregtech;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.farming.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.init.Items;
import com.gtnewhorizon.cropsnh.reference.Names;

public class CropTerraWart extends NHCropCard {

    private final ISoilList soil = SoilRegistry.instance.get("soulsand");

    public CropTerraWart() {
        super(Names.Objects.terraWart, new Color(0x0F213A), new Color(0x2664A1));
        this.addDrop(new ItemStack(Items.terraWart, 1), 10000);
        this.addLikedBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.COLD);
        this.addAlternateSeeds(new ItemStack(Items.terraWart, 1));
        this.addGrowthRequirements(new BlockUnderRequirement("snow").addBlock(new BlockWithMeta(Blocks.snow)));
    }

    @Override
    public Color getPrimarySeedColor() {
        return new Color(0x0F213A);
    }

    @Override
    public Color getSecondarySeedColor() {
        return new Color(0x2664A1);
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.SPORE;
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public float getDropChance() {
        return 0.8f;
    }

    @Override
    public ISoilList getSoilTypes() {
        return this.soil;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }

    @Override
    public String getUnlocalizedName() {
        return "item.cropsnh:terraWart.name";
    }
}
