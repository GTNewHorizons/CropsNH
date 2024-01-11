package com.gtnewhorizon.cropsnh.api.v1;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.reference.Constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface ICropPlant {

    /**
     * This method returns the default tier of this plant, tiers can be overridden with the configs. This should be in
     * the interval [1, 5].
     *
     * @return the default tier
     */
    int tier();

    /**
     * Gets a stack of the seed for this plant
     */
    ItemStack getSeed();

    /**
     * This method should return the Block for your in world crop, it is used to read data from.
     *
     * @return the Block implementation for this crop
     */
    Block getBlock();

    /**
     * This method should return all possible fruits for this crop. It is used for the NEI handler and the journal
     *
     * @return an ArrayList holding all possible fruit drops for this crop, regardless of its stats
     */
    ArrayList<ItemStack> getAllFruits();

    /**
     * Returns a new ItemStack with a random fruit
     *
     * @param rand a Random object
     * @return a randomly selected fruit
     */
    ItemStack getRandomFruit(Random rand);

    /**
     * This method is called to determine the fruit drops when this plant is harvested. All ItemStacks passed in the
     * ArrayList will be dropped in the world.
     *
     * @param gain the gain level of the crop harvested
     * @param rand a Random object
     * @return an ArrayList containing all fruits to be dropped in the world
     */
    ArrayList<ItemStack> getFruitsOnHarvest(int gain, Random rand);

    /**
     * This method is called when this crop is harvested, but before any default CropsNH harvest logic has been
     * executed. It can be used as a notification to keep track of when one of your crops is harvested. It can also be
     * used to override CropsNH's harvesting behaviour. By returning false from this method, you prevent CropsNH from
     * doing any further harvesting operations, effectively cancelling the harvest, you will then need to perform your
     * own custom operations in this method.
     *
     * @param world  the World object for the crop
     * @param x      the x coordinate for the crop
     * @param y      the y coordinate for the crop
     * @param z      the z coordinate for the crop
     * @param crop   the ICrop instance being harvested
     * @param player the player harvesting the crop, this can be null if harvested through automation
     * @return true to allow the harvest or false to cancel the harvest.
     */
    boolean onHarvest(World world, int x, int y, int z, ICrop crop, EntityPlayer player);

    /**
     * This method is called when the seed for this crop is planted on a crop. This can happen when the seed is planted,
     * spread from a neighbour or mutated from two parents
     *
     * @param world the world object
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @param z     the z coordinate
     * @param crop  the ICrop instance
     */
    void onSeedPlanted(World world, int x, int y, int z, ICrop crop);

    /**
     * This is called right before this plant is removed from crop sticks, or when the crop sticks are broken.
     *
     * @param world the World object
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @param z     the z coordinate
     * @param crop  the ICrop instance
     */
    void onPlantRemoved(World world, int x, int y, int z, ICrop crop);

    /**
     * This method determines if bonemeal may be applied to this crop By default, CropsNH does not allow bonemeal on
     * crops higher than tier 3
     *
     * @return if bonemeal may be applied to this plant
     */
    boolean canBonemeal();

    /**
     * If you want your crop to have additional data, this is called when the plant is first applied to crop sticks,
     * either trough planting, spreading or mutation
     *
     * @param world the world object for the crop
     * @param x     the x-coordinate
     * @param y     the y-coordinate
     * @param z     the z-coordinate
     * @param crop  the crop where this plant is planted on
     * @return initial IAdditionalCropData object (can be null if you don't need additional data)
     */
    IAdditionalCropData getInitialCropData(World world, int x, int y, int z,
            com.gtnewhorizon.cropsnh.api.v1.ICrop crop);

    /**
     * If this CropPlant should track additional data, this method will be called when the crop containing such a
     * CropPlant is reading from NBT
     *
     * @param tag the same tag returned from the IAdditionalCropData.writeToNBT() method
     * @return an object holding the data
     */
    IAdditionalCropData readCropDataFromNBT(NBTTagCompound tag);

    /**
     * Called when the TileEntity with this plant has its validate() method called
     *
     * @param world the World object for the TileEntity
     * @param x     the x coordinate for the TileEntity
     * @param y     the y coordinate for the TileEntity
     * @param z     the z coordinate for the TileEntity
     * @param crop  the ICrop instance of the TileEntity (is the same object as the TileEntity, but is for convenience)
     */
    void onValidate(World world, int x, int y, int z, ICrop crop);

    /**
     * Called when the TileEntity with this plant has its invalidate() method called
     *
     * @param world the World object for the TileEntity
     * @param x     the x coordinate for the TileEntity
     * @param y     the y coordinate for the TileEntity
     * @param z     the z coordinate for the TileEntity
     * @param crop  the ICrop instance of the TileEntity (is the same object as the TileEntity, but is for convenience)
     */
    void onInvalidate(World world, int x, int y, int z, ICrop crop);

    /**
     * Called when the TileEntity with this plant has its onChunkUnload() method called
     *
     * @param world the World object for the TileEntity
     * @param x     the x coordinate for the TileEntity
     * @param y     the y coordinate for the TileEntity
     * @param z     the z coordinate for the TileEntity
     * @param crop  the ICrop instance of the TileEntity (is the same object as the TileEntity, but is for convenience)
     */
    void onChunkUnload(World world, int x, int y, int z, ICrop crop);

    /**
     * Gets the growth requirement for this plant, this is used to check if the plant can be planted or grow in certain
     * locations
     * <p>
     * If you don't want to create your own class for this, you can use APIv1.getGrowthRequirementBuilder() to get a
     * Builder object to build IGrowthRequirements If you just want to have vanilla crop behaviour, you can use
     * APIv1.getDefaultGrowthRequirement() to get a growth requirement with default behaviour
     */
    IGrowthRequirement getGrowthRequirement();

    /**
     * Deprecated, use the method below which also passes the ICrop object. This way you don't need to retrieve the
     * TileEntity from the world and coordinates which slows the method down
     */
    @Deprecated
    boolean onAllowedGrowthTick(World world, int x, int y, int z, int oldGrowthStage);

    /**
     * This is called when a growth tick has been allowed. At this point the growth tick can no longer be cancelled
     * Returning true from this method will make the crop being rendered again client side. Return false if the new
     * growth stage has the same icon as the old growth stage.
     *
     * @param world          the World object
     * @param x              the x coordinate
     * @param y              the y coordinate
     * @param z              the z coordinate
     * @param oldGrowthStage the old growth stage
     * @param crop           the ICrop instance
     * @return true to rerender the crop client side
     */
    boolean onAllowedGrowthTick(World world, int x, int y, int z, int oldGrowthStage, ICrop crop);

    /**
     * Checks if the plant is mature
     *
     * @param world the world object
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @param z     the z coordinate
     * @return true if the plant is fully grown, false if not
     */
    boolean isMature(IBlockAccess world, int x, int y, int z);

    /**
     * This gets the height of the crop, this is used to render the bounding boxes client side. In CropsNH, for default,
     * 1-block tall crops this is 13/16th of a block
     *
     * @param meta the growth stage of the plant
     * @return the height for the bounding box
     */
    float getHeight(int meta);

    /**
     * Gets the icon for the plant, as a function of the plant's growth stage.
     *
     * @param growthStage the growth stage of the plant may range from 0 to 7.
     * @return the icon to render the crop with
     */
    IIcon getPlantIcon(int growthStage);

    /**
     * Determines how the plant is rendered. Returning true will render the texture as four crosses ('x') on each
     * corner, similar to flowers. Returning false will render the texture as a hash tag ('#') parallel to each side,
     * similar to wheat
     *
     * @return false to render the plant in a hash tag shape, true for a cross shape.
     */
    boolean renderAsFlower();

    /**
     * Retrieves information about the plant for the seed journal. It's possible to pass an unlocalized String, the
     * returned value will be localized if possible.
     *
     * @return a string describing the plant for use by the seed journal.
     */
    String getInformation();

    /**
     * Return true if you want to render the plant yourself, else cropsnh will render the plant based on the data
     * returned by the getIcon and renderAsFlower methods
     */
    @SideOnly(Side.CLIENT)
    boolean overrideRendering();

    /**
     * A function to render the crop. Called when the plant is rendered. This is never called when returning false from
     * overrideRendering()
     *
     * @param world    the world the plant is in.
     * @param x        the x coordinate of the plant.
     * @param y        the y coordinate of the plant.
     * @param z        the z coordinate of the plant.
     * @param renderer the renderer to use in the rendering of the plant.
     */
    @SideOnly(Side.CLIENT)
    void renderPlantInCrop(IBlockAccess world, int x, int y, int z, RenderBlocks renderer);

    /**
     *
     *
     * These methods are here for reading purpose only, These methods will never be called in your implementation of
     * this interface, Their behaviour is handled by CropsNH, the configuration file and the implementation of the above
     * methods.
     *
     *
     */

    /**
     * @return the growth rate of this plant as an int
     */
    int getGrowthRate();

    /**
     * Returns the tier of the seed as represented as an integer value, or the overriding value. The overriding value
     * may be set in the configuration files.
     * <p>
     * Does not always have same output as {@link #tier()}.
     * <p>
     * Should fall within the range of {@link Constants#GROWTH_TIER}.
     *
     * @return the tier of the seed.
     */
    int getTier();

    /**
     * Sets the tier for this crop plant
     */
    void setTier(int tier);

    /**
     * Gets the spread chance in percent for this plant
     */
    int getSpreadChance();

    /**
     * Sets the spread chance in percent for this plant
     */
    void setSpreadChance(int spreadChance);

    /**
     * @return if the plant is blacklisted
     */
    boolean isBlackListed();

    /**
     * Sets the blacklist status for this plant
     *
     * @param status true if it should be blacklisted, false if not
     */
    void setBlackListStatus(boolean status);

    /**
     * Checks if this plant ignores the rule to disabled vanilla planting true means that the seed for this plant can
     * still be planted even though vanilla planting is disabled
     *
     * @return if this ignores the vanilla planting rule or not
     */
    boolean ignoresVanillaPlantingRule();

    /**
     * Sets if this plant should ignore the rule to disabled vanilla planting true means that the seed for this plant
     * can still be planted even though vanilla planting is disabled
     *
     * @param value if this ignores the vanilla planting rule or not
     */
    void setIgnoreVanillaPlantingRule(boolean value);
}
