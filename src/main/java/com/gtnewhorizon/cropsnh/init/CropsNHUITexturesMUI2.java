package com.gtnewhorizon.cropsnh.init;

import com.cleanroommc.modularui.drawable.UITexture;
import com.gtnewhorizon.cropsnh.reference.Reference;

public class CropsNHUITexturesMUI2 {

    public static final UITexture OVERLAY_SLOT_SEED_STANDARD = UITexture.builder()
        .location(Reference.MOD_ID, "gui/overlay_slot/seed")
        .imageSize(18, 18)
        .adaptable(1)
        .canApplyTheme()
        .name(Reference.MOD_ID + ":overlay_slot_seed_standard")
        .build();

    public static final UITexture OVERLAY_SLOT_ENVIRONMENTAL_MODULE_STANDARD = UITexture.builder()
        .location(Reference.MOD_ID, "gui/overlay_slot/environmentalModule")
        .imageSize(18, 18)
        .adaptable(1)
        .canApplyTheme()
        .name(Reference.MOD_ID + ":overlay_slot_environmental_module_standard")
        .build();
}
