package com.gtnewhorizon.cropsnh.crops.data;

import java.awt.Color;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.api.IAdditionalCropData;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

/**
 * A crop whose properties are plain values set by {@link CropBuilder}. A null field means "use the
 * {@link NHCropCard} default".
 */
public class DataCrop extends NHCropCard {

    String creator;
    String unlocalizedName;
    Supplier<String> flavour;
    Integer tier;
    Integer growthDuration;
    Integer minGrowthStage;
    Integer maxGrowthStage;
    ISeedShape seedShape;
    IPlantRenderShape renderShape;
    String baseTexturePath;
    String[] textureIcons;
    ISoilList soilTypes;
    Integer machineBreedingTier;
    Integer minSeedBedTier;
    Float dropChance;
    Float entityDamage;
    Integer weedsAbove;
    boolean seedEnchanted;
    boolean hiddenFromNEI;
    Float crossingThreshold;
    Float breedingThreshold;

    // behaviours
    Consumer<ICropStickTile> growthTick;
    BiConsumer<ICropStickTile, Entity> entityCollision;
    BiPredicate<ICropStickTile, EntityPlayer> rightClick;
    Function<NBTTagCompound, IAdditionalCropData> additionalDataReader;

    DataCrop(String id, int color1, int color2) {
        super(id, new Color(color1), new Color(color2));
    }

    // region values

    @Override
    public String getCreator() {
        return this.creator != null ? this.creator : super.getCreator();
    }

    @Override
    public String getUnlocalizedName() {
        return this.unlocalizedName != null ? this.unlocalizedName : super.getUnlocalizedName();
    }

    @Override
    public String getFlavourText() {
        return this.flavour != null ? this.flavour.get() : super.getFlavourText();
    }

    @Override
    public int getTier() {
        return this.tier != null ? this.tier : super.getTier();
    }

    @Override
    public int getGrowthDuration() {
        return this.growthDuration != null ? this.growthDuration : super.getGrowthDuration();
    }

    @Override
    public int getMinGrowthStage() {
        return this.minGrowthStage != null ? this.minGrowthStage : super.getMinGrowthStage();
    }

    @Override
    public int getMaxGrowthStage() {
        return this.maxGrowthStage != null ? this.maxGrowthStage : super.getMaxGrowthStage();
    }

    @Override
    public ISeedShape getSeedShape() {
        return this.seedShape != null ? this.seedShape : super.getSeedShape();
    }

    @Override
    public IPlantRenderShape getRenderShape() {
        return this.renderShape != null ? this.renderShape : super.getRenderShape();
    }

    @Override
    public ISoilList getSoilTypes() {
        return this.soilTypes != null ? this.soilTypes : super.getSoilTypes();
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return this.machineBreedingTier != null ? this.machineBreedingTier : super.getMachineBreedingRecipeTier();
    }

    @Override
    public int getMinSeedBedTier() {
        return this.minSeedBedTier != null ? this.minSeedBedTier : super.getMinSeedBedTier();
    }

    @Override
    public float getDropChance() {
        return this.dropChance != null ? this.dropChance : super.getDropChance();
    }

    @Override
    public float getEntityDamage() {
        return this.entityDamage != null ? this.entityDamage : super.getEntityDamage();
    }

    @Override
    public int startsSpreadingWeedsAt() {
        return this.weedsAbove != null ? this.weedsAbove : super.startsSpreadingWeedsAt();
    }

    @Override
    public boolean isSeedEnchanted() {
        return this.seedEnchanted || super.isSeedEnchanted();
    }

    @Override
    public boolean hideFromNEI() {
        return this.hiddenFromNEI || super.hideFromNEI();
    }

    @Override
    public float getCrossingThreshold() {
        return this.crossingThreshold != null ? this.crossingThreshold : super.getCrossingThreshold();
    }

    @Override
    public float getBreedingThreshold() {
        return this.breedingThreshold != null ? this.breedingThreshold : super.getBreedingThreshold();
    }

    // endregion

    // region textures

    @Override
    protected String getBaseTexturePath() {
        return this.baseTexturePath != null ? this.baseTexturePath : super.getBaseTexturePath();
    }

    @Override
    protected IIcon[] registerTextures(IIconRegister register) {
        if (this.textureIcons == null) return super.registerTextures(register);
        IIcon[] icons = new IIcon[this.textureIcons.length];
        for (int i = 0; i < this.textureIcons.length; i++) {
            icons[i] = register.registerIcon(this.textureIcons[i]);
        }
        return icons;
    }

    // endregion

    // region behaviours

    @Override
    public void onGrowthTick(ICropStickTile te) {
        if (this.growthTick != null) {
            this.growthTick.accept(te);
        } else {
            super.onGrowthTick(te);
        }
    }

    @Override
    public void onEntityCollision(ICropStickTile te, Entity entity) {
        if (this.entityCollision != null) {
            this.entityCollision.accept(te, entity);
        } else {
            super.onEntityCollision(te, entity);
        }
    }

    @Override
    public boolean onRightClick(ICropStickTile te, EntityPlayer player) {
        return this.rightClick != null ? this.rightClick.test(te, player) : super.onRightClick(te, player);
    }

    @Override
    public IAdditionalCropData readAdditionalData(NBTTagCompound nbt) {
        return this.additionalDataReader != null ? this.additionalDataReader.apply(nbt) : super.readAdditionalData(nbt);
    }

    // endregion
}
