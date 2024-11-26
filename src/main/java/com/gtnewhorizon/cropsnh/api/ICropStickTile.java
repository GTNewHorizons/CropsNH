package com.gtnewhorizon.cropsnh.api;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * The basic interface for a crop that is on a crop stick.
 */
public interface ICropStickTile {

    void updateState();

    /**
     * @return true if this crop has a plant
     */
    boolean hasCrop();

    /**
     * @return true if the crop has weeds
     */
    boolean hasWeed();

    /**
     * @return the ICrop instance planted on this crop
     */
    ICropCard getCrop();

    /**
     * @return the stats for this crop
     */
    ISeedStats getStats();

    /**
     * @return Any additional data this crop might hold
     */
    IAdditionalCropData getAdditionalCropData();

    /**
     * Sets the additional crop data for the current crop.
     *
     * @param data The data to save.
     */
    void setAdditionalCropData(IAdditionalCropData data);

    /**
     * @return true if the block can be upgraded to a cross crop.
     */
    boolean canUpgrade();

    /**
     * @return if this crop is a crosscrop
     */
    boolean isCrossCrop();

    /**
     * Converts this crop to a crosscrop or a regular crop
     *
     * @param status true for crosscrop, false for regular crop
     */
    void setCrossCrop(boolean status);

    /**
     * @return if a plant can be planted here, meaning the crop is empty and is not a cross crop
     */
    boolean canPlantSeed();

    /**
     * Sets the plant onto this crop
     *
     * @param crop       The crop to be planted
     * @param gr         The growth stat for the plant
     * @param ga         The gain stat for the plant
     * @param re         The resistance stat for the plant
     * @param isAnalized True if the plant is analyzed
     */
    void plantSeed(ICropCard crop, byte gr, byte ga, byte re, boolean isAnalized);

    /**
     * Sets the plant onto this crop
     *
     * @param crop  The crop to be planted
     * @param stats The stats of the seed.
     */
    void plantSeed(ICropCard crop, ISeedStats stats);

    /**
     * Clears the plant from this crop
     */
    void clear();

    /**
     * @return if this crop is fertile and thus can grow
     */
    boolean canGrow();

    /**
     * @return if this crop is fully grown
     */
    boolean isMature();

    /**
     * @return an ItemStack containing the seed planted on this crop
     */
    ItemStack getSeedStack();

    /**
     * Spawns weeds on this crop
     */
    void spawnWeed();

    /**
     * Adds fertilizer to this crop stick up to a specific amount.
     *
     * @param amount     The amount of fertilizer to apply.
     * @param threshold  Fertilizer won't be applied unless the fertilizer level is below this value.
     * @param maxStorage The cap of the fertilizer storage.
     * @return True if the fertilizer was consumed.
     */
    boolean addFertilizer(int amount, int threshold, int maxStorage);

    /**
     * @return The amount of fertilizer currently stored in the crop sticks.
     */
    int getFertilizerStorage();

    /**
     * Adds water to this crop stick.
     *
     * @param amount     The amount of the water to add.
     * @param threshold  Water won't be applied unless the water level is below this value.
     * @param maxStorage The cap of the water storage.
     * @return True if the fertilizer was consumed.
     */
    boolean addWater(int amount, int threshold, int maxStorage);

    /**
     * @return The amount of water currently stored in the crop sticks.
     */
    int getWaterStorage();

    /**
     * Harvests this crop and returns the items that were dropped.
     *
     * @return The items that were dropped
     */
    @Nullable
    ItemStack[] harvest();

    /**
     * @return a list of ICrop objects containing all neighbouring crops
     */
    List<ICropStickTile> getNeighbours();

    /**
     * @return a list of ICrop objects containing all neighbouring crops which are mature
     */
    List<ICropStickTile> getMatureNeighbours();

    /**
     * @return The growth progress of the seed.
     */
    float getGrowthProgress();

    /**
     * Fired when a player right-clicks the crop block.
     *
     * @param player   The player who did the right click.
     * @param heldItem The item held by the player or a nullable item.
     * @return True if an item interaction occured.
     */
    boolean onRightClick(EntityPlayer player, @Nullable ItemStack heldItem);

    /**
     * Fired when a player left-clicks the crop block.
     *
     * @param player   The player who did the right click.
     * @param heldItem The item held by the player or a nullable item.
     * @return True if an item interaction occured.
     */
    boolean onLeftClick(EntityPlayer player, @Nullable ItemStack heldItem);

    /**
     * Fired when the crop block is destroyed.
     */
    void onDestroyed();

    /**
     * Fired when an entity collides with the crop.
     *
     * @param entity The entity that collided with the block.
     */
    void onEntityCollision(Entity entity);

    /**
     * Fired when the neighbour blocks change but the crop sticks don't break.
     */
    void onNeighbourChange();

    /**
     * Fired when a growth tick occurs
     */
    void onGrowthTick();
}
