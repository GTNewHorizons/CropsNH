package com.gtnewhorizon.cropsnh.init;

import com.cleanroommc.modularui.api.IThemeApi;
import com.cleanroommc.modularui.theme.WidgetTheme;
import com.cleanroommc.modularui.theme.WidgetThemeKey;
import com.gtnewhorizon.cropsnh.reference.Reference;

import gregtech.api.modularui2.GTGuiTextures;
import gregtech.api.modularui2.GTGuiTheme;
import gregtech.api.modularui2.GTGuiThemes;
import gregtech.api.modularui2.GTTextureIds;
import gregtech.common.modularui2.theme.ProgressbarWidgetTheme;

public abstract class CropsNHWidgetThemes {

    private static final IThemeApi themeApi = IThemeApi.get();

    public static WidgetThemeKey<WidgetTheme> PROGRESSBAR_CROP_MANAGER_WATER = themeApi
        .widgetThemeKeyBuilder("progressbarCropManagerWater", WidgetTheme.class)
        .defaultTheme(
            new ProgressbarWidgetTheme(
                GTGuiTextures.SLOT_ITEM_STANDARD,
                CropsNHUITexturesMUI2.PROGRESSBAR_CROP_MANAGER_WATER,
                54))
        .defaultHoverTheme(null)
        .register();

    public static WidgetThemeKey<WidgetTheme> PROGRESSBAR_CROP_MANAGER_WEED_EX = themeApi
        .widgetThemeKeyBuilder("progressbarCropManagerWeedEX", WidgetTheme.class)
        .defaultTheme(
            new ProgressbarWidgetTheme(
                GTGuiTextures.SLOT_ITEM_STANDARD,
                CropsNHUITexturesMUI2.PROGRESSBAR_CROP_MANAGER_WEED_EX,
                54))
        .defaultHoverTheme(null)
        .register();

    public static WidgetThemeKey<WidgetTheme> PROGRESSBAR_CROP_MANAGER_LIQUID_FERTILIZER = themeApi
        .widgetThemeKeyBuilder("progressbarCropManagerLiquidFertilizer", WidgetTheme.class)
        .defaultTheme(
            new ProgressbarWidgetTheme(
                GTGuiTextures.SLOT_ITEM_STANDARD,
                CropsNHUITexturesMUI2.PROGRESSBAR_CROP_MANAGER_LIQUID_FERTILIZER,
                54))
        .defaultHoverTheme(null)
        .register();

    public static final GTGuiTheme STANDARD = GTGuiTheme.builder(Reference.MOD_ID + ":standard")
        .parent(GTGuiThemes.STANDARD)
        .progressbar(
            PROGRESSBAR_CROP_MANAGER_WATER.getFullName(),
            GTTextureIds.SLOT_FLUID_STANDARD,
            CropsNHUITexturesMUI2.PROGRESSBAR_CROP_MANAGER_WATER_ID,
            54)
        .progressbar(
            PROGRESSBAR_CROP_MANAGER_WEED_EX.getFullName(),
            GTTextureIds.SLOT_FLUID_STANDARD,
            CropsNHUITexturesMUI2.PROGRESSBAR_CROP_MANAGER_WEED_EX_ID,
            54)
        .progressbar(
            PROGRESSBAR_CROP_MANAGER_LIQUID_FERTILIZER.getFullName(),
            GTTextureIds.SLOT_FLUID_STANDARD,
            CropsNHUITexturesMUI2.PROGRESSBAR_CROP_MANAGER_LIQUID_FERTILIZER_ID,
            54)
        .build();
}
