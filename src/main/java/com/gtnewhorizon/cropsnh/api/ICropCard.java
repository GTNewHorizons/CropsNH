package com.gtnewhorizon.cropsnh.api;

import java.util.Map;
import java.util.Set;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.BiomeDictionary;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface ICropCard {

    /**
     * @return The id of the crop, expected to be in the format of "modId:cropId".
     */
    String getId();

    /**
     * @return The unlocalized name of the crop.
     */
    String getUnlocalizedName();

    /**
     * @return The name of the player that discovered it.
     */
    String getCreator();

    /**
     * @return The description of the crop.
     */
    String getFlavourText();

    /**
     * @return The tier of the crop, used to increase the growth requirement of some crops or to indicate how hard it is
     *         to obtain.
     */
    int getTier();

    /**
     * @return The amount of growth points needed to reach maturity.
     */
    int getGrowthDuration();

    /**
     * @return The minimum seedbed required to grow if it can only grow in the GoBlyn.
     *         -1 if it can grow in the world any else a value from {@link gregtech.api.enums.VoltageIndex}
     */
    int getMinSeedBedTier();

    /**
     * @return The amount of damage a player takes when walking through this crop.
     */
    int getPlayerDamage();

    /**
     * @return The list of biome tags a crop will grow faster in.
     */
    Set<BiomeDictionary.Type> getLikedBiomeTags();

    /**
     * @return A list of blocks the crop can be grown on.
     *         Expected to be null or empty if the crop can only grow in the GoBlyn.
     */
    ISoilList getSoilTypes();

    /**
     * These checks are run every 1000 growth ticks and when the seed is initially planted.
     * Certain checks will be recognized by the GoBlyn, and some other will be ignored.
     *
     * @return The of in-world growth requirements that must be met for the crop to grow.
     */
    Iterable<IWorldGrowthRequirement> getWorldGrowthRequirements();

    /**
     * @return False if the crop isn't allowed to grow in the GoBlyn.
     */
    boolean isAllowedInGoBlyn();

    /**
     * @return The drop table for the crop. 1.0 is a guarenteed drop.
     */
    Map<ItemStack, Float> getDropTable();

    /**
     * @return The items that can be used as seeds to plant this crop aside from the generic seeds.
     */
    ItemStack[] getAlternateSeeds();

    /**
     * @return A multiplier for the number of drops at base.
     */
    float getDropChance();

    /**
     * @return If true, the crop will render the sprite as an X like flowers instead of the usual # of normal crops.
     */
    boolean renderAsFlower();

    /**
     * Called during game load to register the sprites.
     *
     * @param register The sprite register.
     * @return The list of icons for this sprites, min of 2 sprites required;
     */
    void registerSprites(IIconRegister register);

    /**
     * @param te The cropTE in which this crop is growing.
     * @return The sprite to display for this crop.
     */
    IIcon getSprite(ICropStickTile te);

    /**
     * @return The % of growth a crop must reach in order to self-replicate, Negative values prevent crossing.
     */
    float getCrossingThreshold();

    /**
     * @return The % of growth a crop must reach in order to be able to breed. Negative values prevent breeding.
     */
    float getBreedingThreshold();

    /**
     * @param te The cropTE in which this crop is growing.
     * @return true if the crop should spread weeds to it's neighbours.
     */
    boolean spreadsWeeds(ICropStickTile te);

    /**
     * Used to render the crop in the crop sticks
     *
     * @param world    The world it's being rendered in.
     * @param x        The x coordinate of the crop sticks.
     * @param y        The y coordinate of the crop sticks.
     * @param z        The z coordinate of the crop sticks.
     * @param te       The crop tile being rendered.
     * @param renderer The default renderer.
     */
    @SideOnly(Side.CLIENT)
    void render(IBlockAccess world, int x, int y, int z, ICropStickTile te, RenderBlocks renderer);

    /**
     * Used to rehydrate additional crop data, not called if no data is stored.
     *
     * @param nbt The unparsed nbt
     * @return The rehydrated additional crop data or null if no data needs to be loaded.
     */
    IAdditionalCropData LoadAdditionalData(NBTTagCompound nbt);

    // event handlers;

    /**
     * Fired when the crop is initially planted on the crop succesfully.
     * Ignored when in the GoBlyn.
     *
     * @param te The cropTE in which this crop is growing.
     */
    void onPlanted(ICropStickTile te);

    /**
     * Fired when the crop becomes sick.
     * Ignored when in the GoBlyn.
     *
     * @param te The cropTE in which this crop is growing.
     */
    void onSick(ICropStickTile te);

    /**
     * Fired when the crop becomes cured.
     * Ignored when in the GoBlyn.
     *
     * @param te The cropTE in which this crop is growing.
     */
    void onCure(ICropStickTile te);

    /**
     * Fired on every tick.
     * Ignored when in the GoBlyn.
     *
     * @param te The cropTE in which this crop is growing.
     */
    void onTick(ICropStickTile te);

    /**
     * Fired on every growth tick.
     * Ignored when in the GoBlyn.
     *
     * @param te The cropTE in which this crop is growing.
     */
    void onGrowthTick(ICropStickTile te);

    /**
     * Fired when the crop reaches maturity.
     *
     * @param te The cropTE in which this crop is growing.
     */
    void onMaturityReached(ICropStickTile te);

    /**
     * Fired when a player right-clicks this crop.
     * Not fired if player is using fertilizer, water, or known tools.
     * Ignored when in the GoBlyn.
     *
     * @param te     The cropTE in which this crop is growing.
     * @param player The player who interacted with the crop.
     */
    boolean onRightClick(ICropStickTile te, EntityPlayer player);

    /**
     * Fired when there is an entity collision.
     * Ignored when in the GoBlyn.
     *
     * @param te     The cropTE in which this crop is growing.
     * @param entity The entity that collided with this crop.
     */
    void onEntityCollision(ICropStickTile te, Entity entity);

    /**
     * Fired when the crop is harvested.
     * Ignored when in the GoBlyn.
     *
     * @param te The cropTE in which this crop is growing.
     */
    void onHarvest(ICropStickTile te);

    /**
     * Fired when the crop is removed from a crop stick.
     * Ignored when in the GoBlyn.
     *
     * @param te The cropTE in which this crop is growing.
     */
    void onRemoved(ICropStickTile te);
}
