package com.gtnewhorizon.cropsnh.crops.gregtech;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.init.Items;
import com.gtnewhorizon.cropsnh.reference.Names;

public class CropTerraWart extends CropCard {

    private final ItemStack[] alternateSeeds;
    private final ISoilList soils = SoilRegistry.instance.get(Names.Objects.terraWart);
    private final HashSet<BiomeDictionary.Type> likedBiomes = new HashSet<BiomeDictionary.Type>() {

        {
            add(BiomeDictionary.Type.SNOWY);
            add(BiomeDictionary.Type.COLD);
        }
    };

    public CropTerraWart() {
        super(Names.Objects.terraWart);
        this.dropTable.put(new ItemStack(Items.terraWart, 1), 1.0f);
        this.alternateSeeds = new ItemStack[] { new ItemStack(Items.terraWart, 1) };
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
    public Set<BiomeDictionary.Type> getLikedBiomeTags() {
        return this.likedBiomes;
    }

    @Override
    public ISoilList getSoilTypes() {
        return this.soils;
    }

    @Override
    public ItemStack[] getAlternateSeeds() {
        return this.alternateSeeds;
    }

    @Override
    public String getUnlocalizedName() {
        return "item.cropsnh:terraWart.name";
    }

    @Override
    public IIcon[] getTextures(IIconRegister register) {
        return getTextures(register, "cropsnh:cropTerraWart", 3);
    }
}
