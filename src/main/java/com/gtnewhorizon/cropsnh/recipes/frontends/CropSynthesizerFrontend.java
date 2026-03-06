package com.gtnewhorizon.cropsnh.recipes.frontends;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTECropSynthesizer;
import com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil;
import com.gtnewhorizons.modularui.api.math.Alignment;

import gregtech.api.recipe.BasicUIPropertiesBuilder;
import gregtech.api.recipe.NEIRecipePropertiesBuilder;
import gregtech.api.recipe.RecipeMapFrontend;
import gregtech.api.util.MethodsReturnNonnullByDefault;
import gregtech.nei.GTNEIDefaultHandler;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CropSynthesizerFrontend extends RecipeMapFrontend {

    public CropSynthesizerFrontend(BasicUIPropertiesBuilder uiPropertiesBuilder,
        NEIRecipePropertiesBuilder neiPropertiesBuilder) {
        super(uiPropertiesBuilder, neiPropertiesBuilder);
    }

    @Override
    protected List<String> handleNEIItemInputTooltip(List<String> currentTip,
        GTNEIDefaultHandler.FixedPositionedStack pStack) {
        currentTip = super.handleNEIItemInputTooltip(currentTip, pStack);
        if (!pStack.isFluid()) return currentTip;

        // spotless:off
        currentTip.add(EnumChatFormatting.RESET + StatCollector.translateToLocalFormatted(
            Reference.MOD_ID + "_nei.cropSynthesizer.tooltip.fluidCost",
            NumberFormatUtil.formatNumber(MTECropSynthesizer.UUM_PER_TIER),
            NumberFormatUtil.formatNumber(MTECropSynthesizer.UUM_PER_STAT)
        ));
        // spotless:on

        return currentTip;
    }

    @Override
    protected List<String> handleNEIItemOutputTooltip(List<String> currentTip,
        GTNEIDefaultHandler.FixedPositionedStack pStack) {
        if (!pStack.isFluid()) {
            // spotless:off
            currentTip.add(EnumChatFormatting.RESET + StatCollector.translateToLocal(
                Reference.MOD_ID + "_nei.cropSynthesizer.tooltip.output"
            ));
            // spotless:on
            return currentTip;
        }
        return super.handleNEIItemOutputTooltip(currentTip, pStack);
    }

    @Override
    protected void drawNEIOverlayForInput(GTNEIDefaultHandler.FixedPositionedStack stack) {
        if (stack.isFluid()) {
            drawNEIOverlayText(
                "+",
                stack,
                colorOverride.getTextColorOrDefault("nei_overlay_yellow", 0xFDD835),
                0.5f,
                true,
                Alignment.TopRight);
            return;
        }
        super.drawNEIOverlayForInput(stack);
    }

    @Override
    protected void drawNEIOverlayForOutput(GTNEIDefaultHandler.FixedPositionedStack stack) {
        if (!stack.isFluid()) {
            drawNEIOverlayText(
                "+",
                stack,
                colorOverride.getTextColorOrDefault("nei_overlay_yellow", 0xFDD835),
                0.5f,
                true,
                Alignment.TopRight);
            return;
        }
        super.drawNEIOverlayForOutput(stack);
    }
}
