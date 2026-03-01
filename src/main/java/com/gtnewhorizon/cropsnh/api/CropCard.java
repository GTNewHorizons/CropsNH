package com.gtnewhorizon.cropsnh.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.util.GTUtility;

public abstract class CropCard implements ICropCard {

    /**
     * This value is the string id of the crop, it takes the form of modID:cropName
     */
    protected final String id;
    /**
     * This value is auto-assigned, it should not be used to identify the crop for recipes.
     * This value only exists for post-load lookups like in the registries.
     */
    private int numericId = 0;
    /**
     * The drop table of the crop.
     */
    protected final HashMap<ItemStack, Integer> dropTable = new HashMap<>();
    /**
     * The list of growth requirements associated with the crop.
     */
    protected final ArrayList<IGrowthRequirement> growthRequirements = new ArrayList<>();
    /**
     * The list of liked biome tags for the crop.
     * If the biome the crop is in contains 2 of the tags it will gain a massive nutrient boost.
     */
    protected final HashSet<BiomeDictionary.Type> likedBiomes = new HashSet<>();
    /**
     * List of items that can be used to plant the crop on a crop stick at 1/1/1 stats.
     */
    protected final ArrayList<ItemStack> alternateSeeds = new ArrayList<>();
    /**
     * List of items that can be used to create new seeds in a duplicator machine, leave empty if no catalyst is
     * required.
     */
    protected final ArrayList<ItemStack> duplicationCatalysts = new ArrayList<>();
    /**
     * The list of duplication catalysts that are shown in NEI, this includes the ore dicts.
     */
    protected final ArrayList<Object> duplicationCatalystsForNEI = new ArrayList<>();
    /**
     * The colors to use when decorating the item icon of the seed.
     */
    protected final int[] colors;

    public CropCard(String modId, String id, int color1, int color2) {
        this.id = modId + ":" + id;
        this.colors = new int[] { color1, color2 };
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setNumericId(int numericId) {
        if (this.numericId != 0) {
            throw new RuntimeException("Attempted to re-initialize the numeric id of a crop.");
        }
        this.numericId = numericId;
    }

    @Override
    public int getNumericId() {
        return this.numericId;
    }

    @Override
    public String getCreator() {
        return "Unknown";
    }

    @Override
    public String getFlavourText() {
        return null;
    }

    @Override
    public int getPrimarySeedColor() {
        return this.colors[0];
    }

    @Override
    public int getSecondarySeedColor() {
        return this.colors[1];
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.vanilla;
    }

    @Override
    public boolean isSeedEnchanted() {
        return false;
    }

    @Override
    public int getTier() {
        return 1;
    }

    @Override
    public int getMinSeedBedTier() {
        return -1;
    }

    @Override
    public float getEntityDamage() {
        return -1;
    }

    @Override
    public Set<BiomeDictionary.Type> getLikedBiomeTags() {
        return this.likedBiomes;
    }

    @Override
    public Collection<IGrowthRequirement> getGrowthRequirements() {
        return this.growthRequirements;
    }

    @Override
    public Map<ItemStack, Integer> getDropTable() {
        return this.dropTable;
    }

    @Override
    public Collection<ItemStack> getAlternateSeeds() {
        return this.alternateSeeds;
    }

    @Override
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.Hash;
    }

    @Override
    public Collection<ItemStack> getDuplicationCatalysts() {
        return this.duplicationCatalysts;
    }

    @Override
    public Collection<Object> getDuplicationCatalystsForNEI() {
        return this.duplicationCatalystsForNEI;
    }

    @Override
    public boolean hideFromNEI() {
        return false;
    }

    @Override
    public float getCrossingThreshold() {
        return 0.8f;
    }

    @Override
    public float getBreedingThreshold() {
        return 0.8f;
    }

    @Override
    public float getDropChance() {
        return (float) Math.pow(0.95, this.getTier());
    }

    @Override
    public boolean spreadsWeeds(ICropStickTile te) {
        if (!te.isMature()) return false;
        ISeedStats stats = te.getSeed()
            .getStats();
        if (stats == null) return false;
        // if resistance is greater than or equal to growth we don't spread seeds.
        if (stats.getResistance() >= stats.getGrowth()) return false;
        // else if growth stat is greater than 23 we spread weeds.
        return stats.getGrowth() > startsSpreadingWeedsAt();
    }

    @Override
    public int startsSpreadingWeedsAt() {
        return Constants.SPREAD_WEED_WHEN_GROWTH_AT_DEFAULT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(IBlockAccess world, int x, int y, int z, ICropStickTile te, RenderBlocks renderer) {
        IIcon icon = this.getSprite(te);
        if (icon == null) icon = CropsNHUtils.getMissingTexture();
        PlantRenderer.renderPlantLayer(world, x, y, z, this.getRenderShape(), icon, te.isSick());
    }

    @Override
    public IAdditionalCropData readAdditionalData(NBTTagCompound nbt) {
        return null;
    }

    // builder functions
    public CropCard addLikedBiomes(BiomeDictionary.Type... tags) {
        this.likedBiomes.addAll(Arrays.asList(tags));
        return this;
    }

    public CropCard addDrop(ItemStack stack, int chance) {
        if (stack == null) {
            LogHelper.warn("Attempted to add a null drop to " + this.getId());
            return this;
        }
        this.dropTable.put(stack, chance);
        return this;
    }

    public CropCard addAlternateSeed(ItemStack alternateSeed) {
        this.alternateSeeds.add(alternateSeed.copy());
        return this;
    }

    public CropCard addAlternateSeed(String oreDict) {
        for (int i = 0; i < OreDictionary.getOres(oreDict)
            .size(); i++) {
            ItemStack stack = OreDictionary.getOres(oreDict)
                .get(i)
                .copy();
            stack.stackSize = 1;
            this.addAlternateSeed(stack);
        }
        return this;
    }

    public CropCard addGrowthRequirement(IWorldGrowthRequirement growthRequirement) {
        this.growthRequirements.add(growthRequirement);
        return this;
    }

    private void addDuplicationCatalystInternal(ItemStack stack) {
        if (GTUtility.isStackInvalid(stack)) {
            LogHelper.warn("Attempted to add a null duplication catalyst to " + this.getId());
        }
        this.duplicationCatalysts.add(stack.copy());
    }

    public CropCard addDuplicationCatalyst(ItemStack stack) {
        this.duplicationCatalystsForNEI.add(stack);
        addDuplicationCatalystInternal(stack);
        return this;
    }

    public CropCard addDuplicationCatalyst(String oreDict, int count) {
        this.duplicationCatalystsForNEI.add(new Object[] { oreDict, count });
        ArrayList<ItemStack> oreDictEntries = OreDictionary.getOres(oreDict);
        for (ItemStack stack : oreDictEntries) {
            ItemStack copy = stack.copy();
            copy.stackSize = count;
            this.addDuplicationCatalystInternal(copy);
        }
        return this;
    }

    // event handlers

    @Override
    public void onGrowthTick(ICropStickTile te) {}

    @Override
    public void onLoadComplete() {}

    @Override
    public boolean onRightClick(ICropStickTile te, EntityPlayer player) {
        return false;
    }

    @Override
    public void onEntityCollision(ICropStickTile te, Entity entity) {}

    @Override
    public void onHarvest(ICropStickTile te) {}

    @Override
    public void onRemoved(ICropStickTile te) {}

}
