package com.gtnewhorizon.cropsnh.crops.abstracts;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.IAdditionalCropData;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class CropCard implements ICropCard {

    // DEFAULTS
    private final ISoilList DEFAULT_SOIL = SoilRegistry.instance.get("farmland");
    private final Set<BiomeDictionary.Type> DEFAULT_LIKED_BIOMES = Collections.emptySet();
    private final Iterable<IWorldGrowthRequirement> DEFAULT_GROWTH_REQUIREMENTS = Collections.emptyList();
    private final int DEFAULT_TIER = 1;

    protected final String id;
    private IIcon[] sprites = null;
    protected HashMap<ItemStack, Float> dropTable = new HashMap<ItemStack, Float>();

    protected CropCard(String id) {
        this.id = Reference.MOD_ID + ":" + id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUnlocalizedName() {
        return "cropnh_crop:" + this.id;
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
    public int getTier() {
        return DEFAULT_TIER;
    }

    @Override
    public int getGrowthDuration() {
        return 600 * this.getTier();
    }

    @Override
    public int getMinSeedBedTier() {
        return -1;
    }

    @Override
    public int getPlayerDamage() {
        return -1;
    }

    @Override
    public Set<BiomeDictionary.Type> getLikedBiomeTags() {
        return DEFAULT_LIKED_BIOMES;
    }

    @Override
    public ISoilList getSoilTypes() {
        return DEFAULT_SOIL;
    }

    @Override
    public Iterable<IWorldGrowthRequirement> getWorldGrowthRequirements() {
        return DEFAULT_GROWTH_REQUIREMENTS;
    }

    @Override
    public Map<ItemStack, Float> getDropTable() {
        return this.dropTable;
    }

    @Override
    public float getDropChance() {
        return (float) Math.pow(0.95, this.getTier());
    }

    @Override
    public ItemStack[] getAlternateSeeds() {
        return null;
    }

    @Override
    public boolean renderAsFlower() {
        return false;
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
        return false;
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

    @Override
    public void registerSprites(IIconRegister register) {
        this.sprites = this.getTextures(register);
    }

    public abstract IIcon[] getTextures(IIconRegister register);

    /**
     * Overridable for stuff like venomilia.
     *
     * @return The last growth stage before weeds start sprouting because growth is too high.
     */
    public int startsSpreadingWeedsAt() {
        return Constants.SPREAD_WEED_WHEN_GROWTH_ABOVE;
    }

    @Override
    @SideOnly(value = Side.CLIENT)
    public IIcon getSprite(ICropStickTile te) {
        if (this.sprites == null || this.sprites.length < 2) return null;
        int maxSpriteIndex = this.sprites.length - 1;
        if (te.isMature()) return this.sprites[maxSpriteIndex];
        // no more randomly sized growth stages (if you want that feel free to repeat a stage in your icon array)
        int spriteIndex = Math
            .max(0, Math.min(maxSpriteIndex, (int) Math.floor(te.getGrowthProgress() * maxSpriteIndex)));
        return this.sprites[spriteIndex];
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
        PlantRenderer.renderPlantLayer(world, x, y, z, renderAsFlower() ? 1 : 6, sprite, 0);
    }

    @Override
    public IAdditionalCropData LoadAdditionalData(NBTTagCompound nbt) {
        return null;
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

    /**
     * A quick shorthand to get textures for crops that don't need to care too much about it.
     *
     * @param register The register for all the icons.
     * @param name     The base name of the textures (should be in the format of "name#.png"
     * @param count    The number of textures to load, min should be 2;
     * @return The list of textures.
     */
    protected static IIcon[] getTextures(IIconRegister register, String name, int count) {
        return getTextures(register, name, 1, count);
    }

    /**
     * A quick shorthand to get textures for crops that don't need to care too much about it.
     * This is a more arbitrary version that makes referencing vanilla textures easier.
     *
     * @param register The icon register to pull from.
     * @param name     The name of the texture.
     * @param min      The minimum index value inclusive.
     * @param max      The maximum index value inclusive.
     * @return The list of textures.
     */
    protected static IIcon[] getTextures(IIconRegister register, String name, int min, int max) {
        IIcon[] ret = new IIcon[(max + 1) - min];
        int j = 0;
        for (int i = min; i <= max; i++) {
            ret[j++] = register.registerIcon(name + i);
        }
        return ret;
    }
}
