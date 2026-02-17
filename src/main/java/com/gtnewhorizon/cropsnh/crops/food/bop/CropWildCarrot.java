package com.gtnewhorizon.cropsnh.crops.food.bop;

import java.awt.Color;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropWildCarrot extends CropFood {

    public CropWildCarrot() {
        super("wildCarrot", new Color(0x96927E), new Color(0xD9D2B7));

        ItemStack wildCarrot = CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "food", 1, 2);
        this.addDrop(wildCarrot.copy(), 100_00);

        this.addAlternateSeed(wildCarrot.copy());
        // prefers sandier soils
        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT, BiomeDictionary.Type.SANDY);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getGrowthDuration() {
        return 450;
    }

    @Override
    protected IIcon[] registerTextures(IIconRegister register) {
        return new IIcon[] { register.registerIcon("carrots_stage_0"), register.registerIcon("carrots_stage_1"),
            register.registerIcon("carrots_stage_2"),
            register.registerIcon(Reference.MOD_ID + ":crops/" + this.internalId + "/3") };
    }
}
