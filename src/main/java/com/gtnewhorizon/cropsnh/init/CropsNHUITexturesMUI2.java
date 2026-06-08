package com.gtnewhorizon.cropsnh.init;

import static com.cleanroommc.modularui.drawable.UITexture.fullImage;

import com.cleanroommc.modularui.drawable.UITexture;
import com.gtnewhorizon.cropsnh.reference.Reference;

public class CropsNHUITexturesMUI2 {

    public static void init() {}

    public static final UITexture OVERLAY_SLOT_SEED_STANDARD = UITexture.builder()
        .location(Reference.MOD_ID, "gui/overlay_slot/seed")
        .imageSize(18, 18)
        .adaptable(1)
        .canApplyTheme()
        .name(Reference.MOD_ID + ":overlay_slot_seed_standard")
        .build();

    public static final UITexture OVERLAY_SLOT_WEED_EX_STANDARD = UITexture.builder()
        .location(Reference.MOD_ID, "gui/overlay_slot/weed_ex")
        .imageSize(18, 18)
        .adaptable(1)
        .canApplyTheme()
        .name(Reference.MOD_ID + ":overlay_slot_weed_ex_standard")
        .build();

    public static final UITexture OVERLAY_SLOT_FERTILIZER_STANDARD = UITexture.builder()
        .location(Reference.MOD_ID, "gui/overlay_slot/fertilizer")
        .imageSize(18, 18)
        .adaptable(1)
        .canApplyTheme()
        .name(Reference.MOD_ID + ":overlay_slot_fertilizer_standard")
        .build();;

    public static final UITexture OVERLAY_SLOT_ENVIRONMENTAL_MODULE_STANDARD = UITexture.builder()
        .location(Reference.MOD_ID, "gui/overlay_slot/environmentalModule")
        .imageSize(18, 18)
        .adaptable(1)
        .canApplyTheme()
        .name(Reference.MOD_ID + ":overlay_slot_environmental_module_standard")
        .build();

    // crop manager progress bars
    public static final String PROGRESSBAR_CROP_MANAGER_WATER_ID = Reference.MOD_ID + ":progressbar_crop_manager_water";
    public static final UITexture PROGRESSBAR_CROP_MANAGER_WATER = UITexture.builder()
        .location(Reference.MOD_ID, "gui/progressbar/cropmanager_water")
        .canApplyTheme()
        .fullImage()
        .name(PROGRESSBAR_CROP_MANAGER_WATER_ID)
        .build();

    public static final String PROGRESSBAR_CROP_MANAGER_WEED_EX_ID = Reference.MOD_ID
        + ":progressbar_crop_manager_weed_ex";
    public static final UITexture PROGRESSBAR_CROP_MANAGER_WEED_EX = UITexture.builder()
        .location(Reference.MOD_ID, "gui/progressbar/cropmanager_weed_ex")
        .canApplyTheme()
        .fullImage()
        .name(PROGRESSBAR_CROP_MANAGER_WEED_EX_ID)
        .build();

    public static final String PROGRESSBAR_CROP_MANAGER_LIQUID_FERTILIZER_ID = Reference.MOD_ID
        + ":progressbar_crop_manager_liquid_fertilizer";
    public static final UITexture PROGRESSBAR_CROP_MANAGER_LIQUID_FERTILIZER = UITexture.builder()
        .location(Reference.MOD_ID, "gui/progressbar/cropmanager_liquid_fertilizer")
        .canApplyTheme()
        .fullImage()
        .name(PROGRESSBAR_CROP_MANAGER_LIQUID_FERTILIZER_ID)
        .build();

    // crop manager buttons
    public static final UITexture BUTTON_OVERLAY_TOGGLE_WATER = fullImage(
        Reference.MOD_ID,
        "gui/overlay_button/water_toggle");
    public static final UITexture BUTTON_OVERLAY_TOGGLE_WEED_EX = fullImage(
        Reference.MOD_ID,
        "gui/overlay_button/weed_ex_toggle");
    public static final UITexture BUTTON_OVERLAY_TOGGLE_FERTILIZER = fullImage(
        Reference.MOD_ID,
        "gui/overlay_button/fertilizer_toggle");
    public static final UITexture BUTTON_OVERLAY_TOGGLE_HARVEST = fullImage(
        Reference.MOD_ID,
        "gui/overlay_button/harvest_toggle");

}
