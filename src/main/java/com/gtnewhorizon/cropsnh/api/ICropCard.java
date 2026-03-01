package com.gtnewhorizon.cropsnh.api;

import java.util.Collection;
import java.util.List;
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
     * Sets the numeric Id of the crop, this is only used for breeding recipe checks.
     *
     * @param numericId The numeric id of the crop.
     */
    void setNumericId(int numericId);

    /**
     * @return The numeric id of the crop, this is only used for breeding recipe checks.
     */
    int getNumericId();

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
     * @return The color to tint the seeds by.
     */
    int getPrimarySeedColor();

    /**
     * @return The color to tint the seeds by.
     */
    int getSecondarySeedColor();

    /**
     * @return The color to tint the seeds by.
     */
    ISeedShape getSeedShape();

    /**
     * @return True if the seed should have the enchanted item glint.
     */
    boolean isSeedEnchanted();

    /**
     * @return The tier of the crop, used to increase the growth requirement of some crops or to indicate how hard it is
     *         to obtain.
     */
    int getTier();

    /**
     * This also applies for the synthesizer's minimum voltage tier.
     *
     * @return The {@link gregtech.api.enums.VoltageIndex} that should be used as the minimum voltage for breeding
     *         recipes.
     */
    int getMachineBreedingRecipeTier();

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
    float getEntityDamage();

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
     * @return The growth requirements that must be met for the crop to grow.
     */
    Collection<IGrowthRequirement> getGrowthRequirements();

    /**
     * @return The drop table for the crop. 1.0 is a guarenteed drop.
     */
    Map<ItemStack, Integer> getDropTable();

    /**
     * @return The items that can be used as seeds to plant this crop aside from the generic seeds.
     */
    Collection<ItemStack> getAlternateSeeds();

    /**
     * @return An item that must be used in order to duplicate the seed in the seed duplicator.
     */
    Collection<ItemStack> getDuplicationCatalysts();

    /**
     * @return Gets The duplication catalyst displayed in NEI, values should either be a string or an itemStack.
     */
    Collection<Object> getDuplicationCatalystsForNEI();

    /**
     * @return True will hide the crop from NEI.
     */
    boolean hideFromNEI();

    /**
     * @return A multiplier for the number of drops at base.
     */
    float getDropChance();

    /**
     * @return The id of the render shape you want 0 = #, 1 = X, 2 = # but it's 4 crops in the coners
     */
    IPlantRenderShape getRenderShape();

    /**
     * Called during game load to register the sprites.
     *
     * @param register The sprite register.
     * @return The list of icons for this sprites, min of 2 sprites required;
     */
    void registerSprites(IIconRegister register);

    /**
     * Used to determine when the te will be marked for a render update.
     *
     * @return The number of sprites states.
     */
    int getSpriteIndex(ICropStickTile tile);

    /**
     * @param te The cropTE in which this crop is growing.
     * @return The sprite to display for this crop.
     */
    IIcon getSprite(ICropStickTile te);

    /**
     * @return All the potential sprites of the crop
     */
    IIcon[] getSprites();

    /**
     * @return The % of growth a crop must reach in order to self-replicate, Negative values prevent crossing.
     */
    float getCrossingThreshold();

    /**
     * @return The % of growth a crop must reach in order to be able to breed. Negative values prevent breeding.
     */
    float getBreedingThreshold();

    /**
     * @return The growth value at which the crop starts spreading weeds.
     */
    int startsSpreadingWeedsAt();

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
    IAdditionalCropData readAdditionalData(NBTTagCompound nbt);

    // event handlers;

    /**
     * Fired when the FMLLoadCompleted event is fired.
     */
    void onLoadComplete();

    /**
     * Fired on every growth tick.
     * Ignored when in the GoBlyn.
     *
     * @param te The cropTE in which this crop is growing.
     */
    void onGrowthTick(ICropStickTile te);

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

    /**
     * @return A list of all the blocks that go under the crop card.
     */
    List<ItemStack> getSoilsForNEI(boolean useCache);

    /**
     * @return A list of all the blocks that are required to be under the crop for it to grow.
     */
    List<ItemStack> getBlocksUnderForNEI(boolean useCache);

    /**
     * @return Returns a generic seed for this crop card.
     */
    ItemStack getSeedItem(ISeedStats stats);
}
