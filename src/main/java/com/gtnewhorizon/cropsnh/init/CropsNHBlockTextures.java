package com.gtnewhorizon.cropsnh.init;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizon.cropsnh.reference.Reference;

import gregtech.api.GregTechAPI;
import gregtech.api.interfaces.IIconContainer;
import gtPlusPlus.api.objects.Logger;

public class CropsNHBlockTextures {

    public static final IIconContainer Casing_CropHarvester_Cutter = new CustomIcon("tileEntities/gt4/OVERLAY_CROP");
    public static final IIconContainer Casing_CropHarvester_Boxes = new CustomIcon("tileEntities/gt4/OVERLAY_BOXES");
    public static final IIconContainer Casing_Bricked_Agricultural_Casing = new CustomIcon(
        "industrialFarm/brickedAgriculturalCasing");

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
            Logger.WARNING("Constructing a Custom Texture. " + this.mIconName);
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
