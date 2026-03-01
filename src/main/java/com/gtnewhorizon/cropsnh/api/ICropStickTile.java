package com.gtnewhorizon.cropsnh.api;

import java.util.ArrayList;
import java.util.List;

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
     * @return true if the crop has weeds
     */
    boolean isSick();

    /**
     * @return the ICrop instance planted on this crop
     */
    ISeedData getSeed();

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
     * Attempts to plant a crop using the provided stack.
     *
     * @param seedStack The seed being planted.
     */
    boolean tryPlantSeed(ItemStack seedStack);

    /**
     * Attempts to plant a crop.
     *
     * @param cc    The seed being planted.
     * @param stats The stats of the seed being planted.
     */
    boolean tryPlantSeed(ISeedData seedData);

    /**
     * Sets the crop in the crop stick, and resets the growth progress.
     *
     * @param cc    The crop to plant.
     * @param stats The stats of the new crop.
     */
    void plantSeed(ISeedData seedData);

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
     * @param simulate   True to not actually apply the fertilizer.
     * @return True if the fertilizer was consumed.
     */
    boolean addFertilizer(int amount, int threshold, int maxStorage, boolean simulate);

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
     * @param simulate   True to not actually apply the water.
     * @return True if the water was consumed.
     */
    boolean addWater(int amount, int threshold, int maxStorage, boolean simulate);

    /**
     * @return The amount of water currently stored in the crop sticks.
     */
    int getWaterStorage();

    /**
     * Adds weed-ex to this crop stick.
     *
     * @param amount     The amount of the weed-ex to add.
     * @param threshold  Weed-ex won't be applied unless the weed-ex level is below this value.
     * @param maxStorage The cap of the weed-ex storage.
     * @param simulate   True to not actually apply the weed-ex.
     * @return True if the weed-ex was consumed.
     */
    boolean addWeedEx(int amount, int threshold, int maxStorage, boolean simulate);

    /**
     * @return The amount of weed-ex currently stored in the crop sticks.
     */
    int getWeedExStorage();

    /**
     * @return True if the crop can be harvested.
     */
    boolean canHarvest();

    /**
     * Harvests this crop and returns the items that were dropped.
     *
     * @return The items that were dropped
     */
    ArrayList<ItemStack> harvest();

    /**
     * Harvests the crop and drops the items in the TE's world.
     *
     * @return True if the crop was harvested.
     */
    boolean doPlayerHarvest();

    /**
     * Drops and item at the position of the crop sticks.
     */
    void dropItem(ItemStack drop);

    /**
     * @return a list of ICrop objects containing all neighbouring crops
     */
    List<ICropStickTile> getNeighbours();

    /**
     * @return a list of ICrop objects containing all neighbouring crops which are mature
     */
    List<ICropStickTile> getMatureNeighbours();

    /**
     * @return True if the crop in the crop stick can cross
     */
    boolean canCross();

    /**
     * @return True if the crop in the crop stick can breed
     */
    boolean canBreed();

    /**
     * Called when a crop should be given a disease from another surrounding crop.
     */
    void transferDisease();

    /**
     * Attempts to cure the crop
     * 
     * @return True if the crop was cured.
     */
    boolean cureDisease();

    /**
     * @return The current growth progress.
     */
    int getGrowthProgress();

    /**
     * Sets the current growth progress.
     *
     * @param prog How many progress points to set the crop to.
     */
    void setGrowthProgress(int prog);

    /**
     * @return The growth progress of the seed.
     */
    float getGrowthPercent();

    /**
     * @return The growth progress to add on the next growth tick, if <= 0 the crop should get sick.
     */
    int calcGrowthRate();

    /**
     * Fired when a player right-clicks the crop block.
     *
     * @param player   The player who did the right click.
     * @param heldItem The item held by the player or a nullable item.
     * @return True if an item interaction occured.
     */
    boolean onRightClick(EntityPlayer player, ItemStack heldItem);

    /**
     * Fired when a player left-clicks the crop block.
     *
     * @param player   The player who did the right click.
     * @param heldItem The item held by the player or a nullable item.
     * @return True if an item interaction occured.
     */
    boolean onLeftClick(EntityPlayer player, ItemStack heldItem);

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
