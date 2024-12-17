package com.gtnewhorizon.cropsnh.crops.bomesoplenty;

import biomesoplenty.api.content.BOPCItems;
import biomesoplenty.common.core.BOPItems;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;

import java.awt.Color;

public class CropTurnip extends NHCropCard {
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
        return new IIcon[] {
            register.registerIcon("carrots_stage_0"),
            register.registerIcon("carrots_stage_1"),
            register.registerIcon("carrots_stage_2"),
            register.registerIcon("cropsnh:crops/" + this.internalId + "/3")
        };
    }
}
