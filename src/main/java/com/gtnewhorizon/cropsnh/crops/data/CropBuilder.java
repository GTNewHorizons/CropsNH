package com.gtnewhorizon.cropsnh.crops.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.IAdditionalCropData;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.MutationRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.SubSoilRequirement;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MinLightLevelGrowthRequirement;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

/**
 * Defines a crop in one place: values, drops, seeds, pools and mutations.
 * <p>
 * {@link #register()} registers the crop right away, so it keeps its place in the registration order. Pools and
 * mutations wait for {@link #registerBreeding()}, since a parent may be registered later.
 * <p>
 * Whole crops that need a mod stay inside a plain {@code if} in the loader: Java evaluates call arguments eagerly, so
 * a builder flag couldn't stop a {@code getModItem} of a missing mod. Parts that need a mod go through
 * {@link #when(boolean, Consumer)}, which only runs its lambda when the condition holds.
 */
public final class CropBuilder {

    private static final List<CropBuilder> PENDING_BREEDING = new ArrayList<>();

    private final DataCrop crop;
    private final List<String> pools = new ArrayList<>();
    private final List<MutationSpec> mutations = new ArrayList<>();

    private CropBuilder(String id, int color1, int color2) {
        this.crop = new DataCrop(id, color1, color2);
    }

    public static CropBuilder crop(String id, int color1, int color2) {
        return new CropBuilder(id, color1, color2);
    }

    // region conditional parts

    public CropBuilder when(boolean condition, Consumer<CropBuilder> part) {
        if (condition) part.accept(this);
        return this;
    }

    public CropBuilder when(boolean condition, Consumer<CropBuilder> part, Consumer<CropBuilder> otherwise) {
        if (condition) {
            part.accept(this);
        } else {
            otherwise.accept(this);
        }
        return this;
    }

    public CropBuilder when(ModUtils mod, Consumer<CropBuilder> part) {
        return this.when(mod.isModLoaded(), part);
    }

    // endregion

    // region names and looks

    public CropBuilder creator(String creator) {
        this.crop.creator = creator;
        return this;
    }

    /**
     * @param unlocalizedName The unlocalized name, if it isn't the default {@code cropsnh_crops.<id>}.
     */
    public CropBuilder name(String unlocalizedName) {
        this.crop.unlocalizedName = unlocalizedName;
        return this;
    }

    public CropBuilder flavour(String flavourText) {
        return this.flavour(() -> flavourText);
    }

    /**
     * For a flavour text that depends on something that can change while the game runs, like a config option.
     */
    public CropBuilder flavour(Supplier<String> flavourText) {
        this.crop.flavour = flavourText;
        return this;
    }

    public CropBuilder seedShape(ISeedShape shape) {
        this.crop.seedShape = shape;
        return this;
    }

    public CropBuilder renderShape(IPlantRenderShape shape) {
        this.crop.renderShape = shape;
        return this;
    }

    public CropBuilder stages(int min, int max) {
        this.crop.minGrowthStage = min;
        this.crop.maxGrowthStage = max;
        return this;
    }

    public CropBuilder enchantedSeed() {
        this.crop.seedEnchanted = true;
        return this;
    }

    public CropBuilder hiddenFromNEI() {
        this.crop.hiddenFromNEI = true;
        return this;
    }

    /**
     * Uses the textures {@code basePath + stage} for each visible stage.
     */
    public CropBuilder textures(String basePath) {
        this.crop.baseTexturePath = basePath;
        return this;
    }

    /**
     * Uses the textures {@code basePath + stage} for each stage from min to max.
     */
    public CropBuilder textures(String basePath, int minStage, int maxStage) {
        this.crop.baseTexturePath = basePath;
        return this.stages(minStage, maxStage);
    }

    /**
     * Uses exactly these icons, one per visible stage.
     */
    public CropBuilder textureIcons(String... icons) {
        this.crop.textureIcons = icons;
        return this;
    }

    // endregion

    // region growth

    public CropBuilder tier(int tier) {
        this.crop.tier = tier;
        return this;
    }

    /**
     * @param ticks The growth duration, if it isn't the default 600 * tier.
     */
    public CropBuilder growth(int ticks) {
        this.crop.growthDuration = ticks;
        return this;
    }

    public CropBuilder minLight(int level) {
        return this.requirement(new MinLightLevelGrowthRequirement(level));
    }

    public CropBuilder maxLight(int level) {
        return this.requirement(new MaxLightLevelGrowthRequirement(level));
    }

    public CropBuilder requirement(IWorldGrowthRequirement requirement) {
        this.crop.addGrowthRequirement(requirement);
        return this;
    }

    /**
     * @param requirement The block that must be under the soil.
     */
    public CropBuilder subSoil(SubSoilRequirement requirement) {
        this.crop.addSubSoilRequirement(requirement);
        return this;
    }

    public CropBuilder soil(ISoilList soilTypes) {
        this.crop.soilTypes = soilTypes;
        return this;
    }

    public CropBuilder minSeedBedTier(int tier) {
        this.crop.minSeedBedTier = tier;
        return this;
    }

    /**
     * @param stat The growth stat above which the crop spreads weeds.
     */
    public CropBuilder weedsAbove(int stat) {
        this.crop.weedsAbove = stat;
        return this;
    }

    public CropBuilder likes(BiomeDictionary.Type... types) {
        this.crop.addLikedBiomes(types);
        return this;
    }

    // endregion

    // region drops and seeds

    public CropBuilder drop(String oreDict, int chance) {
        return this.drop(oreDict, 1, chance);
    }

    public CropBuilder drop(String oreDict, int count, int chance) {
        return this.drop(OreDictHelper.getCopiedOreStack(oreDict, count), chance);
    }

    public CropBuilder drop(ItemStack stack, int chance) {
        this.crop.addDrop(stack, chance);
        return this;
    }

    /**
     * Every item of these ore dict entries also plants this crop.
     */
    public CropBuilder seeds(String... oreDicts) {
        for (String oreDict : oreDicts) {
            this.crop.addAlternateSeed(oreDict);
        }
        return this;
    }

    public CropBuilder seed(ItemStack stack) {
        this.crop.addAlternateSeed(stack);
        return this;
    }

    /**
     * @param chance The drop count multiplier, if it isn't the default 0.95 ^ tier.
     */
    public CropBuilder dropChance(float chance) {
        this.crop.dropChance = chance;
        return this;
    }

    public CropBuilder entityDamage(float damage) {
        this.crop.entityDamage = damage;
        return this;
    }

    public CropBuilder duplicationCatalyst(String oreDict, int count) {
        this.crop.addDuplicationCatalyst(oreDict, count);
        return this;
    }

    public CropBuilder duplicationCatalyst(ItemStack stack) {
        this.crop.addDuplicationCatalyst(stack);
        return this;
    }

    // endregion

    // region breeding

    public CropBuilder machineBreedingTier(int tier) {
        this.crop.machineBreedingTier = tier;
        return this;
    }

    /**
     * A negative threshold means the crop can't cross or breed.
     */
    public CropBuilder breedingThresholds(float crossing, float breeding) {
        this.crop.crossingThreshold = crossing;
        this.crop.breedingThreshold = breeding;
        return this;
    }

    public CropBuilder pools(String... poolIds) {
        this.pools.addAll(Arrays.asList(poolIds));
        return this;
    }

    public CropBuilder mutation(String... parentIds) {
        return this.mutation(spec -> spec.parents(parentIds));
    }

    public CropBuilder mutation(Consumer<MutationSpec> definition) {
        MutationSpec spec = new MutationSpec();
        definition.accept(spec);
        if (spec.getParentIds().length == 0) {
            throw new IllegalArgumentException("A mutation of " + this.crop.getId() + " has no parents");
        }
        this.mutations.add(spec);
        return this;
    }

    List<String> pools() {
        return Collections.unmodifiableList(this.pools);
    }

    List<MutationSpec> mutations() {
        return Collections.unmodifiableList(this.mutations);
    }

    // endregion

    // region behaviours

    public CropBuilder onGrowthTick(Consumer<ICropStickTile> behaviour) {
        this.crop.growthTick = behaviour;
        return this;
    }

    public CropBuilder onEntityCollision(BiConsumer<ICropStickTile, Entity> behaviour) {
        this.crop.entityCollision = behaviour;
        return this;
    }

    /**
     * @param behaviour Returns true if the click was handled.
     */
    public CropBuilder onRightClick(BiPredicate<ICropStickTile, EntityPlayer> behaviour) {
        this.crop.rightClick = behaviour;
        return this;
    }

    /**
     * @param reader Reads the crop's additional data saved by a behaviour.
     */
    public CropBuilder additionalData(Function<NBTTagCompound, IAdditionalCropData> reader) {
        this.crop.additionalDataReader = reader;
        return this;
    }

    // endregion

    DataCrop build() {
        return this.crop;
    }

    /**
     * Registers the crop now and keeps its pools and mutations for {@link #registerBreeding()}.
     */
    public ICropCard register() {
        CropRegistry.instance.register(this.crop);
        PENDING_BREEDING.add(this);
        return this.crop;
    }

    /**
     * Registers the pools and mutations of every crop registered with a builder. A mutation with a parent that isn't
     * registered (its mod is missing) is skipped.
     */
    public static void registerBreeding() {
        for (CropBuilder builder : PENDING_BREEDING) {
            if (!builder.pools.isEmpty()) {
                MutationRegistry.instance.register(builder.crop, builder.pools.toArray(new String[0]));
            }
            for (MutationSpec spec : builder.mutations) {
                ICropCard[] parents = spec.resolveParents(CropRegistry.instance::get);
                if (parents == null) {
                    LogHelper.warn(
                        "Skipped a mutation of " + builder.crop.getId()
                            + ", not all parents are registered: "
                            + Arrays.toString(spec.getParentIds()));
                    continue;
                }
                spec.create(builder.crop, parents)
                    .register();
            }
        }
        PENDING_BREEDING.clear();
    }
}
