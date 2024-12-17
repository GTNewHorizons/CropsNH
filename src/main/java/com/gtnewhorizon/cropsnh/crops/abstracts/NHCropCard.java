package com.gtnewhorizon.cropsnh.crops.abstracts;

import java.awt.Color;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.api.CropCard;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class NHCropCard extends CropCard {

    // DEFAULTS
    private static final ISoilList DEFAULT_SOIL = SoilRegistry.instance.get("farmland");
    private IIcon[] sprites = null;
    protected final String internalId;

    public NHCropCard(String id, Color color1, Color color2) {
        super(Reference.MOD_ID, id, color1, color2);
        this.internalId = id;
    }

    @Override
    public String getCreator() {
        return "CropsNH Team";
    }

    @Override
    public String getUnlocalizedName() {
        return "cropsnh_crops." + internalId;
    }

    @Override
    public int getGrowthDuration() {
        return 600 * this.getTier();
    }

    @Override
    public ISoilList getSoilTypes() {
        return DEFAULT_SOIL;
    }

    public CropCard addBlockUnderRequirement(String id) {
        this.growthRequirements.add(BlockUnderRequirement.get(id));
        return this;
    }

    @Override
    public void registerSprites(IIconRegister register) {
        this.sprites = this.registerTextures(register);
    }

    // region texturing

    /**
     * @return The number of distinct growth stages that are visible to the player.
     */
    public int getMinGrowthStage() {
        return 1;
    }

    /**
     * @return The number of distinct growth stages that are visible to the player.
     */
    public int getMaxGrowthStage() {
        return 4;
    }

    /**
     * @return The root path for all textures related to this crop.
     */
    protected String getBaseTexturePath() {
        return "cropsnh:crops/" + this.internalId + "/";
    }

    /**
     * Creates the texture array for this crop.
     *
     * @param register The icon register to pull from.
     * @return The list of textures.
     */
    protected IIcon[] registerTextures(IIconRegister register) {
        int min = this.getMinGrowthStage();
        int max = this.getMaxGrowthStage();
        IIcon[] ret = new IIcon[(max + 1) - min];
        int j = 0;
        for (int i = min; i <= max; i++) {
            ret[j++] = register.registerIcon(this.getBaseTexturePath() + i);
        }
        return ret;
    }

    @Override
    public int getSpriteIndex(ICropStickTile te) {
        if (this.sprites == null) return 0;
        int max = this.sprites.length - 1;
        int prog = te.getGrowthProgress();
        int dur = this.getGrowthDuration();
        if (prog >= dur) return max;
        return Math.max(0, prog * max / dur);
    }

    @Override
    @SideOnly(value = Side.CLIENT)
    public IIcon getSprite(ICropStickTile te) {
        if (this.sprites == null || this.sprites.length < 2) return null;
        int maxSpriteIndex = this.sprites.length - 1;
        if (te.isMature()) return this.sprites[maxSpriteIndex];
        // no more randomly sized growth stages (if you want that feel free to repeat a stage in your icon array)
        int spriteIndex = Math
            .max(0, Math.min(maxSpriteIndex, (int) Math.floor(te.getGrowthPercent() * maxSpriteIndex)));
        return this.sprites[spriteIndex];
    }

    // endregion texturing

}
