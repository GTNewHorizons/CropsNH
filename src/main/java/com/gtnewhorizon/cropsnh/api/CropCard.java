package com.gtnewhorizon.cropsnh.api;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class CropCard implements ICropCard {

    protected final String id;
    protected final HashMap<ItemStack, Integer> dropTable = new HashMap<>();
    protected final ArrayList<IWorldGrowthRequirement> growthRequirements = new ArrayList<>();
    protected final HashSet<BiomeDictionary.Type> likedBiomes = new HashSet<>();
    protected final ArrayList<ItemStack> alternateSeeds = new ArrayList<>();
    protected final Color[] colors;

    public CropCard(String modId, String id, Color color) {
        this(modId, id, color, color);
    }

    public CropCard(String modId, String id, Color color1, Color color2) {
        this.id = modId + ":" + id;
        this.colors = new Color[] { color1, color2 };
    }

    @Override
    public String getId() {
        return id;
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
    public Color getPrimarySeedColor() {
        return this.colors[0];
    }

    @Override
    public Color getSecondarySeedColor() {
        return this.colors[1];
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.VANILLA;
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
    public int getEntityDamage() {
        return -1;
    }

    @Override
    public Set<BiomeDictionary.Type> getLikedBiomeTags() {
        return this.likedBiomes;
    }

    @Override
    public Iterable<IWorldGrowthRequirement> getWorldGrowthRequirements() {
        return this.growthRequirements;
    }

    @Override
    public Map<ItemStack, Integer> getDropTable() {
        return this.dropTable;
    }

    @Override
    public Iterable<ItemStack> getAlternateSeeds() {
        return this.alternateSeeds;
    }

    @Override
    public int getRenderShape() {
        return PlantRenderer.RENDER_HASHTAG;
    }

    @Override
    public float getCrossingThreshold() {
        return 1.0f;
    }

    @Override
    public float getBreedingThreshold() {
        return 0.8f;
    }

    @Override
    public boolean isAllowedInGoBlyn() {
        return true;
    }

    @Override
    public float getDropChance() {
        return (float) Math.pow(0.95, this.getTier());
    }

    @Override
    public boolean spreadsWeeds(ICropStickTile te) {
        if (!te.isMature()) return false;
        ISeedStats stats = te.getStats();
        if (stats == null) return false;
        // if resistance is greater than or equal to growth we don't spread seeds.
        if (stats.getResistance() >= stats.getGrowth()) return false;
        // else if growth stat is greater than 23 we spread weeds.
        return stats.getGrowth() > startsSpreadingWeedsAt();
    }

    /**
     * Overridable for stuff like venomilia.
     *
     * @return The last growth stage before weeds start sprouting because growth is too high.
     */
    public int startsSpreadingWeedsAt() {
        return Constants.SPREAD_WEED_WHEN_GROWTH_ABOVE;
    }

    private static IIcon MISSING_TEXTURE = null;

    @SideOnly(Side.CLIENT)
    private static IIcon getMissingTexture() {
        if (MISSING_TEXTURE == null) {
            MISSING_TEXTURE = ((TextureMap) Minecraft.getMinecraft()
                .getTextureManager()
                .getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
        }
        return MISSING_TEXTURE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(IBlockAccess world, int x, int y, int z, ICropStickTile te, RenderBlocks renderer) {
        IIcon sprite = this.getSprite(te);
        if (sprite == null) sprite = getMissingTexture();
        PlantRenderer.renderPlantLayer(world, x, y, z, this.getRenderShape(), sprite, 0, te.isSick());
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
        this.dropTable.put(stack, chance);
        return this;
    }

    public CropCard addAlternateSeeds(ItemStack... alternateSeeds) {
        this.alternateSeeds.addAll(Arrays.asList(alternateSeeds));
        return this;
    }

    public CropCard addGrowthRequirements(IWorldGrowthRequirement... growthRequirements) {
        this.growthRequirements.addAll(Arrays.asList(growthRequirements));
        return this;
    }

    // event handlers

    @Override
    public void onPlanted(ICropStickTile te) {}

    @Override
    public void onSick(ICropStickTile te) {}

    @Override
    public void onCure(ICropStickTile te) {}

    @Override
    public void onTick(ICropStickTile te) {}

    @Override
    public void onGrowthTick(ICropStickTile te) {}

    @Override
    public void onMaturityReached(ICropStickTile te) {}

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
