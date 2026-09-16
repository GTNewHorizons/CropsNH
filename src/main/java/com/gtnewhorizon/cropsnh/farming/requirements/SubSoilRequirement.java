package com.gtnewhorizon.cropsnh.farming.requirements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IMachineBreedingRequirement;
import com.gtnewhorizon.cropsnh.api.IMachineGrowthRequirement;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.api.IWorldBreedingRequirement;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.MetaSet;

import gregtech.api.GregTechAPI;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.objects.ItemData;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.GTBlockOre;
import gregtech.common.blocks.TileEntityOres;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

/**
 * Used to prevent a crop from growing unless there is a specific type of block(s) is directly under its soil.
 */
public class SubSoilRequirement implements IWorldGrowthRequirement, IWorldBreedingRequirement,
    IMachineBreedingRequirement, IMachineGrowthRequirement {

    // region registry

    private static final Object2ObjectOpenHashMap<String, SubSoilRequirement> registrations = new Object2ObjectOpenHashMap<>();

    /**
     * Should only be called internally, do not use if you aren't part of crops nh or did a dumb move and added crops
     * after the post-load event.
     */
    public static void onLoadComplete() {
        registrations.values()
            .parallelStream()
            .forEach(SubSoilRequirement::buildPredicate);
    }

    /**
     * Gets a sub-soil requirement type, or creates it if it doesn't exist.
     *
     * @param name The unique identifier for the sub-soil type.
     * @return The sub-soil type to use.
     */
    public static SubSoilRequirement get(String name) {
        if (!registrations.containsKey(name)) {
            registrations.put(name, new SubSoilRequirement(name));
        }
        return registrations.get(name);
    }

    // endregion registry

    /** The individual key used to identify this sub-soil type. Also used to generate lang keys. */
    private final String materialDescription;
    /** key value pair used to generate localized display strings. */
    private final Pair<String, String[]> unlocalizedDesc;

    /** The list of whitelisted gt materials */
    private @Nullable Set<Materials> materialWhitelist = null;
    /** The list of whitelisted ore dictionaries */
    private @Nullable Set<String> oreDictWhitelist = null;
    /** The list of whitelisted blocks */
    private @Nullable MetaSet<Block> blockWhitelist = null;
    /** The list of blacklisted blocks */
    private @Nullable MetaSet<Block> blockBlacklist = null;
    /** Custom predicates in case the provided ones aren't good enough, always appended as an OR before the ANDs. */
    private @Nullable Predicate<SubSoilTarget> customOrPredicates = null;
    /** Custom predicates in case the provided ones aren't good enough, always appended as an AND after the ORs. */
    private @Nullable Predicate<SubSoilTarget> customAndPredicates = null;
    /** Used to contain runtime checks, if null, it means no blocks were assigned to this group (eg mising mods) */
    private @Nullable Predicate<SubSoilTarget> predicate = null;

    /**
     * @param materialDescription The individual key used to identify this sub-soil type. Also used to generate lang
     *                            keys.
     */
    private SubSoilRequirement(String materialDescription) {
        this.materialDescription = materialDescription;
        this.unlocalizedDesc = Pair.of(Reference.MOD_ID + "_growthReq.subSoil." + this.materialDescription, null);
    }

    /**
     * Builds an optimized predicate to use at runtime for checks.
     */
    private void buildPredicate() {
        Predicate<SubSoilTarget> newPredicate = null;
        if (this.blockWhitelist != null) {
            this.predicate = this::isWhitelistedBlock;
        }
        if (this.oreDictWhitelist != null) {
            this.predicate = predicate == null ? this::isWhitelistedOreDict : predicate.or(this::isWhitelistedOreDict);
        }
        if (this.materialWhitelist != null) {
            this.predicate = predicate == null ? this::isWhitelistedMaterial
                : predicate.or(this::isWhitelistedMaterial);
        }
        if (this.customOrPredicates != null) {
            this.predicate = this.predicate == null ? this.customOrPredicates
                : this.predicate.or(this.customOrPredicates);
        }
        if (this.predicate != null && this.blockBlacklist != null) {
            this.predicate = predicate.and(this::isNotBlacklistedBlock);
        }
        if (this.customAndPredicates != null) {
            this.predicate = this.predicate == null ? this.customAndPredicates
                : this.predicate.and(this.customAndPredicates);
        }
    }

    // region builder api

    /**
     * Whitelists a set of GT Material.
     *
     * @param args The materials to allow.
     * @return The calling object, this is a builder pattern.
     */
    public SubSoilRequirement addMaterial(Materials... args) {
        // ignore silently if nothing is provided
        if (args.length <= 0) return this;
        // check for null args
        for (int i = 0; i < args.length; i++) {
            // should never be null
            if (args[i] == null) {
                throw new IllegalStateException(
                    "Attemted to whitelist a null gt material to " + this.materialDescription + " @ " + i);
            }
        }
        // initialize if needed
        if (this.materialWhitelist == null) {
            this.materialWhitelist = new ObjectOpenHashSet<>();
        }
        // add values
        this.materialWhitelist.addAll(Arrays.asList(args));
        return this;
    }

    /**
     * Whitelists a set of ore dictionaries.
     *
     * @param args The ore dictionaries to allow.
     * @return The calling object, this is a builder pattern.
     */
    public SubSoilRequirement addOreDict(String... args) {
        // ignore silently if nothing is provided
        if (args.length <= 0) return this;
        // check for null args
        for (int i = 0; i < args.length; i++) {
            // should never be null
            if (args[i] == null) {
                throw new IllegalStateException(
                    "Attemted to whitelist a null ore dictl to " + this.materialDescription + " @ " + i);
            }
        }
        // initialize if needed
        if (this.oreDictWhitelist == null) {
            this.oreDictWhitelist = new ObjectOpenHashSet<>();
        }
        // add values
        this.oreDictWhitelist.addAll(Arrays.asList(args));
        return this;
    }

    /**
     * Whitelists the block and ore dictionaries associated with the requirement's key, eg iron adds {@code blockIron}
     * and {@code oreIron}
     *
     * @return The calling object, this is a builder pattern.
     */
    public SubSoilRequirement addBlockAndOreDict() {
        // needs to be capitalized, assume is camel or pascal cased by default
        this.addBlockAndOreDict(
            Character.toUpperCase(this.materialDescription.charAt(0)) + materialDescription.substring(1));
        return this;
    }

    /**
     * Whitelists the block and ore dictionaries associated with the given names, eg iron adds {@code blockIron} and
     * {@code oreIron}.
     *
     * @param names all values should be in {@code PascalCase}.
     *
     * @return The calling object, this is a builder pattern.
     */
    public SubSoilRequirement addBlockAndOreDict(String... names) {
        // check for nulls
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            // should never be null
            if (name == null) {
                throw new IllegalStateException(
                    "Attempted to whitelist null block as a sub-soil for " + this.materialDescription + " @ " + i);
            }
            // add values
            this.addOreDict("block" + name, "ore" + name);
        }
        return this;
    }

    /**
     * Whitelists a set of blocks.
     *
     * @param args The blocks to allow, use {@link OreDictionary#WILDCARD_VALUE} to whitelist all metas.
     * @return The calling object, this is a builder pattern.
     */
    public SubSoilRequirement addBlock(BlockWithMeta... args) {
        // ignore silently if nothing is passed
        if (args.length <= 0) return this;
        // check for nulls
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                if (CropsNHUtils.shouldPanicIfNullFound()) {
                    throw new IllegalStateException(
                        "Attempted to whitelist null block as a sub-soil for " + this.materialDescription + " @ " + i);
                } else {
                    try {
                        throw new Exception(
                            "CROPS NH ATTEMPTED TO WHITELIST NULL BLOCK AS A SUB-SOIL FOR " + this.materialDescription
                                + " @ "
                                + i);
                    } catch (Exception e) {
                        LogHelper.warn(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
        // initialize block whitelist if not set
        if (this.blockWhitelist == null) {
            this.blockWhitelist = new MetaSet<>();
        }
        // whitelist blocks
        for (BlockWithMeta arg : args) {
            if (arg == null) continue;
            this.blockWhitelist.add(arg.getBlock(), arg.ignoreMeta() ? OreDictionary.WILDCARD_VALUE : arg.getMeta());
        }
        return this;
    }

    /**
     * Blacklists a set of blocks.
     *
     * @param args The blocks to allow, use {@link OreDictionary#WILDCARD_VALUE} to blacklist all metas.
     * @return The calling object, this is a builder pattern.
     */
    public SubSoilRequirement blacklistBlock(BlockWithMeta... args) {
        // ignore silently if nothing is passed
        if (args.length <= 0) return this;
        // check for nulls
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                if (CropsNHUtils.shouldPanicIfNullFound()) {
                    throw new IllegalStateException(
                        "Attempted to blacklist null block as a sub-soil for " + this.materialDescription + " @ " + i);
                } else {
                    try {
                        throw new Exception(
                            "CROPS NH ATTEMPTED TO BLACKLIST NULL BLOCK AS A SUB-SOIL FOR " + this.materialDescription
                                + " @ "
                                + i);
                    } catch (Exception e) {
                        LogHelper.warn(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
        // initialize block whitelist if not set
        if (this.blockBlacklist == null) {
            this.blockBlacklist = new MetaSet<>();
        }
        // whitelist blocks
        for (BlockWithMeta arg : args) {
            if (arg == null) continue;
            this.blockBlacklist.add(arg.getBlock(), arg.ignoreMeta() ? OreDictionary.WILDCARD_VALUE : arg.getMeta());
        }
        return this;
    }

    /**
     * Adds a custom predicate that will be ORed with all the other whitelist values for this requirement.
     *
     * @param arg The OR predicate to add to this requirement.
     * @return The calling object, this is a builder pattern.
     */
    public SubSoilRequirement addOrPredicate(Predicate<SubSoilTarget> arg) {
        if (arg == null) {
            throw new IllegalStateException("Attempted to add a null OR predicate for " + this.materialDescription);
        }
        this.customOrPredicates = this.customOrPredicates == null ? arg : this.customOrPredicates.or(arg);
        return this;
    }

    /**
     * Adds a custom predicate that will be ANDed on top of all existing requirements.
     * If called multiple times, the existing AND predicated will be ANDed with the provided one.
     *
     * @param arg The AND predicate to add to this requirement.
     * @return The calling object, this is a builder pattern.
     */
    public SubSoilRequirement addAndPredicate(Predicate<SubSoilTarget> arg) {
        if (arg == null) {
            throw new IllegalStateException("Attempted to add a null AND predicate for " + this.materialDescription);
        }
        this.customAndPredicates = this.customAndPredicates == null ? arg : this.customAndPredicates.and(arg);
        return this;
    }

    // endregion builder api

    // region check handlers

    @Override
    public boolean onlyPreventsHarvest() {
        return false;
    }

    @Override
    public boolean canGrow(World world, ICropStickTile tile, int x, int y, int z) {
        if (this.predicate == null) return false;
        // pre-flight checks
        SubSoilTarget target = getSubSoil(world, x, y, z);
        if (target == null) return false;
        return predicate.test(target);
    }

    @Override
    public boolean canBreed(ArrayList<ICropCard> parents, World world, ICropStickTile tile, int x, int y, int z) {
        if (this.predicate == null) return false;
        return canGrow(world, tile, x, y, z);
    }

    @Override
    public boolean canGrow(ISeedData seedData, IGregTechTileEntity te, ItemStack[] catalysts) {
        if (this.predicate == null) return false;
        return Arrays.stream(catalysts)
            .anyMatch(x -> this.isValidSubSoil(x, false));
    }

    @Override
    public boolean canBreed(ArrayList<ICropCard> parents, IGregTechTileEntity te, ItemStack @NotNull [] catalysts,
        int[] consumptionTracker) {
        if (this.predicate == null) return false;
        for (int i = 0; i < catalysts.length; i++) {
            // if stack is bad or if we can't consume it, abort early
            ItemStack stack = catalysts[i];
            if (GTUtility.isStackInvalid(stack) || stack.stackSize - consumptionTracker[i] <= 0) continue;
            // consume if valid
            if (isValidSubSoil(stack, false)) {
                consumptionTracker[i] += 1;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a block is a valid sub-soil for this requirement.
     *
     * @param block The sub-soil block.
     * @param meta  The meta of the sub-soil block.
     * @param te    The TE at the position of the block.
     * @param isNEI True if the request originated from NEI.
     * @return true if it can grow.
     */
    public boolean isValidSubSoil(Block block, int meta, TileEntity te, boolean isNEI) {
        return predicate != null && predicate.test(new SubSoilTarget(block, meta, te, isNEI));
    }

    /**
     * Checks if a stack contains a valid sub-soil for this requirement.
     *
     * @param toValidate The stack to validate.
     * @param isNEI      True if the request originated from NEI.
     * @return True if the stack contains a valid sub-soil.
     */
    public boolean isValidSubSoil(ItemStack toValidate, boolean isNEI) {
        if (this.predicate == null) return false;
        // ensure valid stack
        if (CropsNHUtils.isStackInvalid(toValidate)) return false;

        SubSoilTarget query = new SubSoilTarget(toValidate, isNEI);
        // air as a stack will never be a valid block
        if (query.block != null && query.block.getMaterial() == Material.air) return false;

        return predicate.test(query);
    }

    // endregion check handlers

    // region NEI and localisations

    @Override
    public @NotNull String getDescription() {
        return StatCollector
            .translateToLocalFormatted(this.unlocalizedDesc.getLeft(), (Object[]) unlocalizedDesc.getRight());
    }

    @Override
    public @NotNull Pair<@NotNull String, @Nullable String[]> getUnlocalizedDescription() {
        return this.unlocalizedDesc;
    }

    @Override
    public @Nullable List<ItemStack> getMachineOnlyCatalystsForNEI() {
        return getItemsForNEI();
    }

    /**
     * @return A list of all block and items that are valid sub-soils.
     */
    public List<ItemStack> getItemsForNEI() {
        List<ItemStack> ret = new LinkedList<>();
        // load up direct block mentions
        if (this.blockWhitelist != null) {
            for (MetaSet.Entry<Block> e : this.blockWhitelist.getStream()
                .collect(Collectors.toList())) {
                Item item = CropsNHUtils.getItemFromBlock(e.key);
                if (item == null) continue;
                int meta = e.meta == null ? OreDictionary.WILDCARD_VALUE : e.meta;
                ret.add(new ItemStack(item, 1, meta));
            }
        }

        // load up materials
        if (this.materialWhitelist != null) {
            for (Materials mat : this.materialWhitelist) {
                // all the ore variations!
                ret.addAll(GTOreDictUnificator.getOres(OrePrefixes.ore, mat));
                ret.addAll(GTOreDictUnificator.getOres(OrePrefixes.oreNetherrack, mat));
                ret.addAll(GTOreDictUnificator.getOres(OrePrefixes.oreEndstone, mat));
                ret.addAll(GTOreDictUnificator.getOres(OrePrefixes.oreBlackgranite, mat));
                ret.addAll(GTOreDictUnificator.getOres(OrePrefixes.oreRedgranite, mat));
                ret.addAll(GTOreDictUnificator.getOres(OrePrefixes.oreMarble, mat));
                ret.addAll(GTOreDictUnificator.getOres(OrePrefixes.oreBasalt, mat));
                // compressed storage block
                ret.addAll(GTOreDictUnificator.getOres(OrePrefixes.block, mat));
            }
        }

        // load up ore dicts
        if (this.oreDictWhitelist != null) {
            for (String oreDict : this.oreDictWhitelist) {
                ret.addAll(OreDictionary.getOres(oreDict));
            }
        }

        // filter out blacklisted items
        if (this.blockBlacklist != null) {
            ret.removeIf(x -> {
                Block b = CropsNHUtils.getBlockFromItem(x);
                // eg armor shards which have the ore dict, this is intended btw.
                if (b == null) return false;
                return this.blockBlacklist.contains(b, CropsNHUtils.getItemMeta(x));
            });
        }

        // run predicates on it to effectively run all potential blacklists
        if (this.predicate != null) {
            ret.removeIf(x -> x == null || !this.predicate.test(new SubSoilTarget(x, true)));
        }

        // the nei step will deduplicate stuff later, so we don't have to care about that.
        return ret;
    }

    // endregion NEI and localisations

    // region runtime searching

    /**
     * A holder class used to query sub-soil validity at runtime.
     */
    public static class SubSoilTarget {

        /** The block being validated. */
        public final Block block;
        /** The meta of the item or block being validated. */
        public final int meta;
        /** Values of the stack should never be modified. */
        public final ItemStack stack;
        /** The tile entity at the position of the block being validated. */
        public final TileEntity te;
        /** True if the request originated from NEI. */
        public final boolean isNEI;

        /**
         * @param block The block being validated.
         * @param meta  The meta of the item or block being validated.
         * @param te    The tile entity at the position of the block being validated.
         * @param isNEI True if the request originated from NEI.
         */
        public SubSoilTarget(Block block, int meta, TileEntity te, boolean isNEI) {
            this.block = block;
            this.meta = meta;
            this.stack = new ItemStack(block, 1, meta);
            this.te = te;
            this.isNEI = isNEI;
        }

        /**
         *
         * @param stack The stack being validated.
         * @param isNEI True if the request originated from NEI.
         */
        public SubSoilTarget(ItemStack stack, boolean isNEI) {
            this.block = CropsNHUtils.getBlockFromItem(stack);
            this.meta = CropsNHUtils.getItemMeta(stack);
            this.stack = stack;
            this.te = null;
            this.isNEI = isNEI;
        }
    }

    /**
     * Gets the subsoil of the crop stick at position x,y,z
     *
     * @param world The world the crop stick is in.
     * @param x     The x coordinate of the crop stick.
     * @param y     The y coordinate of the crop stick.
     * @param z     The z coordinate of the crop stick.
     * @return The query to used for the sub-soil search.
     */
    private static SubSoilTarget getSubSoil(IBlockAccess world, int x, int y, int z) {
        // pre-flight check
        y -= 2;
        if (y < 0) return null;
        if (world.isAirBlock(x, y, z)) return null;

        // get the sub-soil
        Block block = world.getBlock(x, y, z);
        if (block.getMaterial() == Material.air) return null;
        int meta = world.getBlockMetadata(x, y, z);
        TileEntity te = world.getTileEntity(x, y, z);
        return new SubSoilTarget(block, meta, te, false);
    }

    /**
     * @param target The target to check against.
     * @return True if the target isn't blacklisted.
     */
    private boolean isNotBlacklistedBlock(SubSoilTarget target) {
        if (target.block == null) return true;
        assert this.blockBlacklist != null;
        return !this.blockBlacklist.contains(target.block, target.meta);
    }

    /**
     * @param target The target to check against.
     * @return True if the target is whitelisted.
     */
    private boolean isWhitelistedBlock(SubSoilTarget target) {
        if (target.block == null) return false;
        assert this.blockWhitelist != null;
        return this.blockWhitelist.contains(target.block, target.meta);
    }

    /**
     * @param target The target to check against.
     * @return True if the target is associated with a whitelisted ore dictionary.
     */
    private boolean isWhitelistedOreDict(SubSoilTarget target) {
        assert this.oreDictWhitelist != null;
        for (String oreDict : this.oreDictWhitelist) {
            for (int aux = 0; aux < OreDictionary.getOres(oreDict)
                .size(); ++aux) {
                ItemStack oreDictStack = OreDictionary.getOres(oreDict)
                    .get(aux);
                if (oreDictStack.getItem() != target.stack.getItem()) continue;
                int dmg = CropsNHUtils.getItemMeta(oreDictStack);
                // check for both the old and the new wildcard value, this helps with a couple things
                if (CropsNHUtils.getItemMeta(target.stack) == dmg || OreDictionary.WILDCARD_VALUE == dmg || -1 == dmg)
                    return true;
            }
        }
        return false;
    }

    /**
     * @param target The target to check against.
     * @return True if the target is associated with a whitelisted GT material.
     */
    private boolean isWhitelistedMaterial(SubSoilTarget target) {
        assert this.materialWhitelist != null;
        for (Materials material : this.materialWhitelist) {
            if (target.block instanceof GTBlockOre && target.te instanceof TileEntityOres teOre) {
                Materials generatedMaterial = GregTechAPI.sGeneratedMaterials[teOre.mMetaData % 1000];
                if (generatedMaterial != null && generatedMaterial != Materials._NULL
                    && generatedMaterial == material) {
                    return true;
                }
            } else {
                ItemData association = GTOreDictUnificator.getAssociation(target.stack);
                if (association != null && (association.mPrefix.toString()
                    .startsWith("ore") || association.mPrefix == OrePrefixes.block)
                    && (association.mMaterial.mMaterial == material)) {
                    return true;
                }
            }
        }
        return false;
    }

    // endregion runtime searching
}
