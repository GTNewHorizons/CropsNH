package com.gtnewhorizon.cropsnh.init;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizon.cropsnh.reference.Reference;

import gregtech.api.GregTechAPI;
import gregtech.api.interfaces.IIconContainer;

public class CropsNHBlockTextures {

    public static final IIconContainer Casing_CropHarvester_Cutter = new CustomIcon("tileEntities/gt4/OVERLAY_CROP");
    public static final IIconContainer Casing_CropHarvester_Boxes = new CustomIcon("tileEntities/gt4/OVERLAY_BOXES");
    public static final IIconContainer Casing_Bricked_Agricultural_Casing = new CustomIcon(
        "industrialFarm/brickedAgriculturalCasing");

    public static final IIconContainer OVERLAY_FRONT_CROP_SYNTHESIZER_ACTIVE = new CustomIcon("tileEntities/machines/crop_synthesizer/OVERLAY_CROP_SYNTHESIZER_FRONT_ACTIVE");
    public static final IIconContainer OVERLAY_FRONT_CROP_SYNTHESIZER_ACTIVE_GLOW = new CustomIcon("tileEntities/machines/crop_synthesizer/OVERLAY_CROP_SYNTHESIZER_FRONT_ACTIVE_GLOW");
    public static final IIconContainer OVERLAY_FRONT_CROP_SYNTHESIZER = new CustomIcon("tileEntities/machines/crop_synthesizer/OVERLAY_CROP_SYNTHESIZER_FRONT");
    public static final IIconContainer OVERLAY_FRONT_CROP_SYNTHESIZER_GLOW = new CustomIcon("tileEntities/machines/crop_synthesizer/OVERLAY_CROP_SYNTHESIZER_FRONT_GLOW");

    public static final IIconContainer OVERLAY_FRONT_SEED_GENERATOR_ACTIVE = new CustomIcon("tileEntities/machines/seed_generator/OVERLAY_SEED_GENERATOR_FRONT_ACTIVE");
    public static final IIconContainer OVERLAY_FRONT_SEED_GENERATOR_ACTIVE_GLOW = new CustomIcon("tileEntities/machines/seed_generator/OVERLAY_SEED_GENERATOR_FRONT_ACTIVE_GLOW");
    public static final IIconContainer OVERLAY_FRONT_SEED_GENERATOR = new CustomIcon("tileEntities/machines/seed_generator/OVERLAY_SEED_GENERATOR_FRONT");
    public static final IIconContainer OVERLAY_FRONT_SEED_GENERATOR_GLOW = new CustomIcon("tileEntities/machines/seed_generator/OVERLAY_SEED_GENERATOR_FRONT_GLOW");

    public static final IIconContainer OVERLAY_FRONT_CROP_BREEDER_ACTIVE = new CustomIcon("tileEntities/machines/crop_breeder/OVERLAY_CROP_BREEDER_FRONT_ACTIVE");
    public static final IIconContainer OVERLAY_FRONT_CROP_BREEDER_ACTIVE_GLOW = new CustomIcon("tileEntities/machines/crop_breeder/OVERLAY_CROP_BREEDER_FRONT_ACTIVE_GLOW");
    public static final IIconContainer OVERLAY_FRONT_CROP_BREEDER = new CustomIcon("tileEntities/machines/crop_breeder/OVERLAY_CROP_BREEDER_FRONT");
    public static final IIconContainer OVERLAY_FRONT_CROP_BREEDER_GLOW = new CustomIcon("tileEntities/machines/crop_breeder/OVERLAY_CROP_BREEDER_FRONT_GLOW");


    public static class CustomIcon implements IIconContainer, Runnable {

        protected IIcon mIcon;
        protected String mIconName;
        protected String mModID;

        public CustomIcon(String aIconName) {
            this(Reference.MOD_ID, aIconName);
        }

        public CustomIcon(String aModID, String aIconName) {
            this.mIconName = aIconName;
            this.mModID = aModID;
            GregTechAPI.sGTBlockIconload.add(this);
        }

        public IIcon getIcon() {
            return this.mIcon;
        }

        public IIcon getOverlayIcon() {
            return null;
        }

        public void run() {
            this.mIcon = GregTechAPI.sBlockIcons.registerIcon(this.mModID + ":" + this.mIconName);
        }

        public ResourceLocation getTextureFile() {
            return TextureMap.locationBlocksTexture;
        }
    }
}
