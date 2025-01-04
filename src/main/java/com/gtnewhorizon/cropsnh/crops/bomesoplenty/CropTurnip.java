package com.gtnewhorizon.cropsnh.crops.bomesoplenty;

import java.awt.Color;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropTurnip extends CropFood {

    public CropTurnip() {
        super("turnip", new Color(0x321F1C), new Color(0x6C3E32));
        this.addDrop(OreDictHelper.getCopiedOreStack("cropTurnip", 1), 100_00);
        this.addAlternateSeed("seedTurnip");
        this.addAlternateSeed("cropTurnip");
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
            register.registerIcon("cropsnh:crops/" + this.internalId + "/3") };
    }
}
