package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

public class CropSugarBeet extends NHCropCard {

    public CropSugarBeet() {
        super("sugarBeet", new Color(0xB3B3B3), new Color(0xFEFEFE));
        this.addDrop(new ItemStack(CropsNHItems.berry, 1, 1), 100_00);
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.food.register(this);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 450;
    }

    @Override
    protected IIcon[] registerTextures(IIconRegister register) {
        // yes it uses the same textures as wild carrots, no idk why
        return new IIcon[] { register.registerIcon("carrots_stage_0"), register.registerIcon("carrots_stage_1"),
            register.registerIcon("carrots_stage_2"), register.registerIcon("cropsnh:crops/wildCarrot/3") };
    }
}
