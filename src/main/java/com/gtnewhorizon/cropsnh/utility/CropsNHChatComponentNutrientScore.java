package com.gtnewhorizon.cropsnh.utility;

import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropSticks;
import com.gtnewhorizon.gtnhlib.chat.AbstractChatComponentCustom;
import com.gtnewhorizon.gtnhlib.chat.customcomponents.AbstractChatComponentNumber;
import com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil;

public class CropsNHChatComponentNutrientScore extends AbstractChatComponentNumber {

    public CropsNHChatComponentNutrientScore() {
        super();
    }

    public CropsNHChatComponentNutrientScore(Number number) {
        super(number);
    }

    @Override
    protected String formatNumber(Number value) {
        return StatCollector.translateToLocalFormatted(
            Reference.MOD_ID + "_tooltip.industrialFarm.scanner.6",
            NumberFormatUtil.formatNumber(value),
            NumberFormatUtil.formatNumber(TileEntityCropSticks.MAX_NUTRIENT_SCORE));
    }

    @Override
    public String getID() {
        return Reference.MOD_ID + ":NutrientScore";
    }

    @Override
    protected AbstractChatComponentCustom copySelf() {
        return new CropsNHChatComponentNutrientScore(number);
    }

}
