package com.gtnewhorizon.cropsnh.recipes.frontends;

import java.util.List;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizons.modularui.api.math.Alignment;

import gregtech.api.recipe.BasicUIPropertiesBuilder;
import gregtech.api.recipe.NEIRecipePropertiesBuilder;
import gregtech.api.recipe.RecipeMapFrontend;
import gregtech.nei.GTNEIDefaultHandler;

public class CropBreederFrontend extends RecipeMapFrontend {

    public CropBreederFrontend(BasicUIPropertiesBuilder uiPropertiesBuilder,
        NEIRecipePropertiesBuilder neiPropertiesBuilder) {
        super(uiPropertiesBuilder, neiPropertiesBuilder);
    }

    @Override
    protected void drawNEIOverlayForInput(GTNEIDefaultHandler.FixedPositionedStack stack) {
        super.drawNEIOverlayForInput(stack);
        if (stack.isFluid()) {
            drawNEIOverlayText(
                "+",
                stack,
                colorOverride.getTextColorOrDefault("nei_overlay_yellow", 0xFDD835),
                0.5f,
                true,
                Alignment.TopRight);
        }
    }

    @Override
    protected List<String> handleNEIItemInputTooltip(List<String> currentTip,
        GTNEIDefaultHandler.FixedPositionedStack pStack) {
        currentTip = super.handleNEIItemInputTooltip(currentTip, pStack);
        if (pStack.isFluid()) {
            currentTip.add(
                EnumChatFormatting.RESET
                    + StatCollector.translateToLocal(Reference.MOD_ID + "_nei.cropBreeder.tooltip.fluidCost"));
        }
        return currentTip;
    }

    @Override
    protected List<String> handleNEIItemOutputTooltip(List<String> currentTip,
        GTNEIDefaultHandler.FixedPositionedStack pStack) {
        currentTip = super.handleNEIItemOutputTooltip(currentTip, pStack);
        currentTip.add(
            EnumChatFormatting.RESET
                + StatCollector.translateToLocal(Reference.MOD_ID + "_nei.cropBreeder.tooltip.outputStats"));
        currentTip.add(
            EnumChatFormatting.RESET
                + StatCollector.translateToLocal(Reference.MOD_ID + "_nei.cropBreeder.tooltip.successChance"));
        return currentTip;
    }

    @Override
    protected void drawNEIOverlayForOutput(GTNEIDefaultHandler.FixedPositionedStack stack) {
        super.drawNEIOverlayForOutput(stack);
        drawNEIOverlayText(
            "+",
            stack,
            colorOverride.getTextColorOrDefault("nei_overlay_yellow", 0xFDD835),
            0.5f,
            true,
            Alignment.TopRight);
    }

}
