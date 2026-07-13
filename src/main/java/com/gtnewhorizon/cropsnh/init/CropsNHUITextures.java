package com.gtnewhorizon.cropsnh.init;

import com.cleanroommc.modularui.drawable.UITexture;
import com.gtnewhorizon.cropsnh.reference.Reference;

public class CropsNHUITextures {

    public static void init() {}

    public static final UITexture OVERLAY_SLOT_SEED_STANDARD = UITexture.builder()
        .location(Reference.MOD_ID, "gui/overlay_slot/seed")
        .imageSize(18, 18)
        .adaptable(1)
        .canApplyTheme()
        .build();

    public static final UITexture OVERLAY_SLOT_WEED_EX_STANDARD = UITexture.builder()
        .location(Reference.MOD_ID, "gui/overlay_slot/weed_ex")
        .imageSize(18, 18)
        .adaptable(1)
        .canApplyTheme()
        .build();

    public static final UITexture OVERLAY_SLOT_FERTILIZER_STANDARD = UITexture.builder()
        .location(Reference.MOD_ID, "gui/overlay_slot/fertilizer")
        .imageSize(18, 18)
        .adaptable(1)
        .canApplyTheme()
        .build();;

    public static final UITexture OVERLAY_SLOT_ENVIRONMENTAL_MODULE_STANDARD = UITexture.builder()
        .location(Reference.MOD_ID, "gui/overlay_slot/environmentalModule")
        .imageSize(18, 18)
        .adaptable(1)
        .canApplyTheme()
        .build();

    // crop breeder textures
    public static final UITexture PROGRESSBAR_CROP_BREEDER = UITexture.builder()
        .location(Reference.MOD_ID, "gui/progressbar/crop_breeder")
        .canApplyTheme()
        .fullImage()
        .build();

    public static final UITexture OVERLAY_SLOT_CROP_STICK_STANDARD = UITexture.builder()
        .location(Reference.MOD_ID, "gui/overlay_slot/crop_stick")
        .imageSize(18, 18)
        .adaptable(1)
        .canApplyTheme()
        .build();

    // crop manager progress bars
    public static final UITexture PROGRESSBAR_CROP_MANAGER_WATER = UITexture.builder()
        .location(Reference.MOD_ID, "gui/progressbar/cropmanager_water")
        .canApplyTheme()
        .fullImage()
        .build();

    public static final UITexture PROGRESSBAR_CROP_MANAGER_WEED_EX = UITexture.builder()
        .location(Reference.MOD_ID, "gui/progressbar/cropmanager_weed_ex")
        .canApplyTheme()
        .fullImage()
        .build();

    public static final UITexture PROGRESSBAR_CROP_MANAGER_LIQUID_FERTILIZER = UITexture.builder()
        .location(Reference.MOD_ID, "gui/progressbar/cropmanager_liquid_fertilizer")
        .canApplyTheme()
        .fullImage()
        .build();

    // crop manager buttons
    public static final UITexture BUTTON_OVERLAY_TOGGLE_WATER = UITexture.builder()
        .location(Reference.MOD_ID, "gui/overlay_button/water_toggle")
        .canApplyTheme()
        .fullImage()
        .build();
    public static final UITexture BUTTON_OVERLAY_TOGGLE_WEED_EX = UITexture.builder()
        .location(Reference.MOD_ID, "gui/overlay_button/weed_ex_toggle")
        .canApplyTheme()
        .fullImage()
        .build();
    public static final UITexture BUTTON_OVERLAY_TOGGLE_FERTILIZER = UITexture.builder()
        .location(Reference.MOD_ID, "gui/overlay_button/fertilizer_toggle")
        .canApplyTheme()
        .fullImage()
        .build();
    public static final UITexture BUTTON_OVERLAY_TOGGLE_HARVEST = UITexture.builder()
        .location(Reference.MOD_ID, "gui/overlay_button/harvest_toggle")
        .canApplyTheme()
        .fullImage()
        .build();

    // TODO: REMOVE ONCE NEI HANDLERS START USING MUI2 FOR OVERLAY SOURCES
    public static final com.gtnewhorizons.modularui.api.drawable.UITexture OVERLAY_SLOT_SEED_MUI1 = com.gtnewhorizons.modularui.api.drawable.UITexture
        .fullImage(Reference.MOD_ID, "gui/overlay_slot/seed");

    public static final com.gtnewhorizons.modularui.api.drawable.UITexture OVERLAY_SLOT_CROP_STICK_MUI1 = com.gtnewhorizons.modularui.api.drawable.UITexture
        .fullImage(Reference.MOD_ID, "gui/overlay_slot/crop_stick");

    public static final com.gtnewhorizons.modularui.api.drawable.UITexture PROGRESSBAR_CROP_BREEDER_MUI1 = com.gtnewhorizons.modularui.api.drawable.UITexture
        .fullImage(Reference.MOD_ID, "gui/progressbar/crop_breeder");
}
