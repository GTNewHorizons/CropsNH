package com.gtnewhorizon.cropsnh.crops.bomesoplenty;

import biomesoplenty.api.content.BOPCItems;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.awt.Color;

public class CropWildCarrot extends NHCropCard {
    public CropWildCarrot() {
        super("wildCarrot", new Color(0x96927E), new Color(0xD9D2B7));
        this.addDrop(new ItemStack(BOPCItems.food, 1, 2), 100_00);
        this.addAlternateSeed(new ItemStack(BOPCItems.food, 1, 2));
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
        return new IIcon[] {
            register.registerIcon("carrots_stage_0"),
            register.registerIcon("carrots_stage_1"),
            register.registerIcon("carrots_stage_2"),
            register.registerIcon("cropsnh:crops/" + this.internalId + "/3")
        };
    }
}
