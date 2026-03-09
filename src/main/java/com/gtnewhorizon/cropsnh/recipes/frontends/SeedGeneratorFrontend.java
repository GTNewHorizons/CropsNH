package com.gtnewhorizon.cropsnh.recipes.frontends;

import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.formatNumber;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTESeedGenerator;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizons.modularui.api.math.Alignment;

import gregtech.api.recipe.BasicUIPropertiesBuilder;
import gregtech.api.recipe.NEIRecipePropertiesBuilder;
import gregtech.api.recipe.RecipeMapFrontend;
import gregtech.api.util.MethodsReturnNonnullByDefault;
import gregtech.nei.GTNEIDefaultHandler;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SeedGeneratorFrontend extends RecipeMapFrontend {

    public SeedGeneratorFrontend(BasicUIPropertiesBuilder uiPropertiesBuilder,
        NEIRecipePropertiesBuilder neiPropertiesBuilder) {
        super(uiPropertiesBuilder, neiPropertiesBuilder);
    }

    @Override
    protected List<String> handleNEIItemInputTooltip(List<String> currentTip,
        GTNEIDefaultHandler.FixedPositionedStack pStack) {
        currentTip = super.handleNEIItemInputTooltip(currentTip, pStack);

        // check if is a fluid
        if (!pStack.isFluid()) return currentTip;
        final Fluid fluid = FluidRegistry.getFluid(CropsNHUtils.getItemMeta(pStack.item));
        if (fluid == null) return currentTip;

        // calc drain
        final float mult = MTESeedGenerator.ALLOWED_LIQUID_FERTILIZER.getOrDefault(fluid, -1.0f);
        if (mult < 0) return currentTip;
        int statDrain = Math.max(1, (int) (MTESeedGenerator.FERTILIZER_PER_STAT * mult));

        // add tooltip
        // spotless:off
        currentTip.add(EnumChatFormatting.RESET + StatCollector.translateToLocalFormatted(
            Reference.MOD_ID + "_nei.seedGenerator.tooltip.fluidCost",
            formatNumber(statDrain)
        ));
        // spotless:on

        return currentTip;
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
}
