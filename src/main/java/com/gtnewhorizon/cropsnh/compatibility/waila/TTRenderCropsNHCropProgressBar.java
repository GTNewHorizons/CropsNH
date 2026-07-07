package com.gtnewhorizon.cropsnh.compatibility.waila;

import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.formatNumber;

import java.awt.Dimension;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.reference.Reference;

import gregtech.GTMod;
import mcp.mobius.waila.api.IWailaCommonAccessor;
import mcp.mobius.waila.api.IWailaVariableWidthTooltipRenderer;
import mcp.mobius.waila.overlay.DisplayUtil;
import mcp.mobius.waila.overlay.OverlayConfig;

// mostly based on the GT machine progress bar
public class TTRenderCropsNHCropProgressBar implements IWailaVariableWidthTooltipRenderer {

    int maxStringW;

    // Is usually shorter than the rest of the tooltip, so calculate only once for a 3 digit progress number
    private static final int width = DisplayUtil.getDisplayWidth("0,000 / 0,000 (100.0%)");

    @Override
    public Dimension getSize(String[] params, IWailaCommonAccessor accessor) {
        return new Dimension(width, 12);
    }

    @Override
    public void draw(String[] params, IWailaCommonAccessor accessor) {
        // abort if values are wrong
        if (params.length != 3) return;
        // can sometimes overshoot if i use /nbtedit to do things so better safe than sorry
        int growthDuration = Math.max(1, Integer.parseInt(params[1]));
        int cropProgress = Math.min(Math.max(0, Integer.parseInt(params[0])), growthDuration);
        double progressPerc = (double) cropProgress / growthDuration;
        int progress = (int) ((maxStringW - 1) * progressPerc);

        drawThickBeveledBox(
            0,
            0,
            maxStringW,
            12,
            1,
            GTMod.proxy.wailaProgressBorderColor1,
            GTMod.proxy.wailaProgressBorderColor2,
            -1);

        for (int xx = 1; xx < progress; xx++) {
            int color = (xx & 1) == 0 ? GTMod.proxy.wailaProgressBarColor1 : GTMod.proxy.wailaProgressBarColor2;
            drawVerticalLine(xx, 1, 12 - 1, color);
        }

        if (params[2].equals("1")) {
            DisplayUtil.drawString(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.waila.cropStick.progressBar.scanned",
                    formatNumber(cropProgress),
                    formatNumber(growthDuration),
                    progressPerc * 100),
                2,
                2,
                OverlayConfig.fontcolor,
                true);
        } else {
            DisplayUtil.drawString(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.waila.cropStick.progressBar.notScanned",
                    progressPerc * 100),
                2,
                2,
                OverlayConfig.fontcolor,
                true);
        }
    }

    public static void drawThickBeveledBox(int x1, int y1, int x2, int y2, int thickness, int topleftcolor,
        int botrightcolor, int fillcolor) {
        if (fillcolor != -1) {
            Gui.drawRect(x1 + 1, y1 + 1, x2 - 1, y2 - 1, fillcolor);
        }
        Gui.drawRect(x1, y1, x2 - 1, y1 + thickness, topleftcolor);
        Gui.drawRect(x1, y1, x1 + thickness, y2 - 1, topleftcolor);
        Gui.drawRect(x2 - thickness, y1, x2, y2 - 1, botrightcolor);
        Gui.drawRect(x1, y2 - thickness, x2, y2, botrightcolor);
    }

    public static void drawVerticalLine(int x1, int y1, int y2, int color) {
        Gui.drawRect(x1, y1, x1 + 1, y2, color);
    }

    @Override
    public void setMaxLineWidth(int width) {
        maxStringW = width + 2;
    }

    @Override
    public int getMaxLineWidth() {
        return maxStringW;
    }
}
