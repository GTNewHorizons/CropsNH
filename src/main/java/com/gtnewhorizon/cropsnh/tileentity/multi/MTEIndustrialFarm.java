package com.gtnewhorizon.cropsnh.tileentity.multi;

import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.formatNumber;
import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.getFluidUnit;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlocksTiered;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.onElementPass;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.withChannel;
import static gregtech.api.enums.HatchElement.Energy;
import static gregtech.api.enums.HatchElement.InputBus;
import static gregtech.api.enums.HatchElement.InputHatch;
import static gregtech.api.enums.HatchElement.Maintenance;
import static gregtech.api.enums.HatchElement.MultiAmpEnergy;
import static gregtech.api.enums.HatchElement.OutputBus;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_ASSEMBLY_LINE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_ASSEMBLY_LINE_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_ASSEMBLY_LINE_ACTIVE_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_ASSEMBLY_LINE_GLOW;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTStructureUtility.buildHatchAdder;
import static gregtech.api.util.GTStructureUtility.chainAllGlasses;
import static gregtech.api.util.GTStructureUtility.ofFrame;
import static net.minecraftforge.common.util.Constants.NBT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import com.cleanroommc.modularui.utils.item.CombinedInvWrapper;
import com.cleanroommc.modularui.utils.item.IItemHandlerModifiable;
import com.gtnewhorizon.cropsnh.api.CropsNHStructureChannels;
import com.gtnewhorizon.cropsnh.api.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.api.IMachineGrowthRequirement;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.blocks.BlockAdvancedHarvestingUnit;
import com.gtnewhorizon.cropsnh.blocks.BlockEnvironmentalEnhancementUnit;
import com.gtnewhorizon.cropsnh.blocks.BlockFertilizerUnit;
import com.gtnewhorizon.cropsnh.blocks.BlockGrowthAccelerationUnit;
import com.gtnewhorizon.cropsnh.blocks.BlockOverclockedGrowthAccelerationUnit;
import com.gtnewhorizon.cropsnh.blocks.BlockSeedBed;
import com.gtnewhorizon.cropsnh.blocks.abstracts.CropsNHBlockIndustrialFarmTiredComponent;
import com.gtnewhorizon.cropsnh.farming.registries.FertilizerRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.HydrationRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.SubSoilRequirement;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;
import com.gtnewhorizon.cropsnh.init.CropsNHFluids;
import com.gtnewhorizon.cropsnh.items.ItemEnvironmentalModule;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropSticks;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.IFDropTable;
import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.gtnewhorizon.structurelib.structure.AutoPlaceEnvironment;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.IStructureElement;
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.Textures;
import gregtech.api.enums.VoidingMode;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.ICasingTextureProvider;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.MTEExtendedPowerMultiBlockBase;
import gregtech.api.metatileentity.implementations.MTEHatch;
import gregtech.api.modularui2.GTGuiTextures;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.recipe.check.SimpleCheckRecipeResult;
import gregtech.api.structure.error.StructureError;
import gregtech.api.structure.error.StructureErrorRegistry;
import gregtech.api.structure.error.StructureErrors;
import gregtech.api.structure.error.TranslatableText;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTUtility;
import gregtech.api.util.ItemEjectionHelper;
import gregtech.api.util.MultiblockTooltipBuilder;
import gregtech.api.util.OverclockCalculator;
import gregtech.common.gui.modularui.multiblock.base.MTEMultiBlockBaseGui;
import gregtech.common.misc.GTStructureChannels;

public class MTEIndustrialFarm extends MTEExtendedPowerMultiBlockBase<MTEIndustrialFarm>
    implements ISurvivalConstructable, ICasingTextureProvider {

    /** The duration of the production cycle in seconds. */
    public static final int CYCLE_DURATION = 5 * SECONDS;
    /** The amount of water that should be stored in the crop stick when calculating the growth speed */
    public static final int SIMULATED_WATER_STORAGE = 200;
    /** Whether the crop can see the sky when calculating the growth speed. (true because uv lamps or something) */
    public static final boolean SIMULATED_CAN_SEE_SKY = true;
    /**
     * The amount of fertilizer that should be stored in the crop stick when calculating the growth speed when
     * fertilizer is not provided.
     */
    public static final int SIMULATED_FERTILIZER_STORAGE_WHEN_FERTILIZER_NOT_PROVIDED = 0;
    /**
     * The amount of fertilizer that should be stored in the crop stick when calculating the growth speed when
     * fertilizer is provided.
     */
    public static final int SIMULATED_FERTILIZER_STORAGE_WHEN_FERTILIZER_PROVIDED = 200;
    /**
     * The maximum number of multi-amp hatches allowed when using an OC upgrade.
     */
    public static final int MAX_MULTIAMP_EHATCH_AMOUNT = 1;

    private final static String NBT_INVENTORY_TAG = "mIFInventory";
    private final static String NBT_OUTPUT_TRACKER = "mOutputTracker";
    private final static String NBT_HAS_FERTILIZER = "mHasFertilizer";

    /** The default mode, used to insert seed and sub-soil */
    public static final int MODE_INPUT = 0;
    /** The mode that generates resources. */
    public static final int MODE_FARM = 1;
    /** Used to safely eject the seeds and sub-soil to the output bus. */
    public static final int MODE_OUTPUT = 2;

    /** Slot index of the seed slot in the custom inventory */
    public final static int SLOT_SEED = 0;
    /** Slot index of the sub-soil slot in the custom inventory */
    public final static int SLOT_SUB_SOIL = 1;
    /** Starting slot index of the environmental slots in the custom inventory */
    public final static int SLOT_ENV_CARD_START = 2;

    /** Amount of fertilizer per seed slot in the machine */
    public static final double CYCLE_TICK_RATE_SCALAR = (double) CYCLE_DURATION / TileEntityCropSticks.TICK_RATE;

    /** How many times the output and water/fertilizer consumption of the multi should be doubled. */
    public int expectedOCs = 0;
    /** How much power each recipe is expected to use */
    public long expectedEUt = 0;
    /** The tier of glass used to build the multi, used to limit the power hatch tiers. */
    public int glassTier = -1;
    /** The tier of the upgrades applied to the multi. */
    public int upgradeTier = -1;
    /** The number of seeds and sub-soil that can be stored in the controller. */
    public int seedCapacity = 0;
    /** True if the current recipe has enough fertilizer to get the bonus. */
    public boolean hasFertilizer = false;
    /** The number of environmental enhancement units installed on the multi. */
    public int environmentalEnhancementUnitCount = 0;
    /** The number of growth acceleration units installed on the multi. */
    public int growthAccelerationUnitCount = 0;
    /** The number of fertilizer units installed on the multi. */
    public int fertilizerUnitCount = 0;
    /** The number of advanced harvesting units installed on the multi. */
    public int advancedHarvestingUnitCount = 0;
    /** The number of overclocked growth acceleration units installed on the multi. */
    public int overclockedGrowthAccelerationUnitCount = 0;

    /** The tracker for the drop progress */
    public IFDropTable outputTracker = new IFDropTable();
    /** ItemStack handler for the custom slots. */
    public MTEIndustrialFarmItemStackHandler ifStackHandler = new MTEIndustrialFarmItemStackHandler(this);
    /** Multi-Inv wrapper since it needs to respond to both the controller slot and the custom slots. */
    private final IItemHandlerModifiable invWrapper;

    // region structure
    private static final String STRUCTURE_PIECE_FIRST = "first";
    private static final String STRUCTURE_PIECE_LATER = "later";
    private static final String STRUCTURE_PIECE_LAST = "last";
    private static final int MIN_CASING_TIER = VoltageIndex.MV;
    private static final int MAX_CASING_TIER = VoltageIndex.UXV;
    private static final int MIN_SLICES = 1;
    private static final int MAX_SLICES = 1 + MAX_CASING_TIER - MIN_CASING_TIER;
    private static final IStructureDefinition<MTEIndustrialFarm> STRUCTURE_DEFINITION = StructureDefinition
        .<MTEIndustrialFarm>builder()
        .addShape(
            STRUCTURE_PIECE_FIRST,
            transpose(
                new String[][] {
                    // spotless:off
                { " cCc " },
                { "cCCCc" },
                { "cC~Cc" },
                { "c   c" }
                // spotless:on
                }))
        .addShape(
            STRUCTURE_PIECE_LATER,
            transpose(
                new String[][] {
                    // spotless:off
                { " gUg " },
                { "g   g" },
                { "csssc" },
                { "     " }
                // spotless:on
                }))
        .addShape(
            STRUCTURE_PIECE_LAST,
            transpose(
                new String[][] {
                    // spotless:off
                { " cDc " },
                { "cDDDc" },
                { "cDDDc" },
                { "c   c" }
                // spotless:on
                }))
        .addElement(
            'C',
            buildHatchAdder(MTEIndustrialFarm.class)
                .atLeast(InputBus, InputHatch, OutputBus, Maintenance, MultiAmpEnergy.or(Energy))
                .casingIndex(GTUtility.getCasingTextureIndex(CropsNHBlocks.blockCasings1, 0))
                .allowOnly(ForgeDirection.NORTH)
                .hint(1)
                .buildAndChain(ofBlock(CropsNHBlocks.blockCasings1, 0)))
        .addElement(
            'D',
            buildHatchAdder(MTEIndustrialFarm.class)
                .atLeast(InputBus, InputHatch, OutputBus, Maintenance, MultiAmpEnergy.or(Energy))
                .casingIndex(GTUtility.getCasingTextureIndex(CropsNHBlocks.blockCasings1, 0))
                .allowOnly(ForgeDirection.SOUTH)
                .shouldSkip((t, tile) -> true)
                .hint(1)
                .buildAndChain(ofBlock(CropsNHBlocks.blockCasings1, 0)))
        .addElement('c', ofBlock(CropsNHBlocks.blockCasings1, 0))
        .addElement('g', chainAllGlasses(-1, (te, t) -> te.glassTier = t, te -> te.glassTier))
        // spotless:off
        // turning spotless off here since it makes the nesting here a lot less readable
        .addElement(
            'U',
            ofChain(
                withChannel(CropsNHStructureChannels.IFTier.get(),
                    new IFUpgradeElement(
                        (CropsNHBlockIndustrialFarmTiredComponent) CropsNHBlocks.blockEnvironmentalEnhancementUnit,
                        te -> te.environmentalEnhancementUnitCount++
                    )
                ),
                withChannel(CropsNHStructureChannels.IFTier.get(),
                    new IFUpgradeElement(
                        (CropsNHBlockIndustrialFarmTiredComponent) CropsNHBlocks.blockGrowthAccelerationUnit,
                        te -> te.growthAccelerationUnitCount++
                    )
                ),
                withChannel(CropsNHStructureChannels.IFTier.get(),
                    new IFUpgradeElement(
                        (CropsNHBlockIndustrialFarmTiredComponent) CropsNHBlocks.blockFertilizerUnit,
                        te -> te.fertilizerUnitCount++
                    )
                ),
                withChannel(CropsNHStructureChannels.IFTier.get(),
                    new IFUpgradeElement(
                        (CropsNHBlockIndustrialFarmTiredComponent) CropsNHBlocks.blockAdvancedHarvestingUnit,
                        te -> te.advancedHarvestingUnitCount++
                    )
                ),
                withChannel(CropsNHStructureChannels.IFTier.get(),
                    new IFUpgradeElement(
                        (CropsNHBlockIndustrialFarmTiredComponent) CropsNHBlocks.blockOverclockedGrowthAccelerationUnit,
                        te -> te.overclockedGrowthAccelerationUnitCount++
                    )
                ),
                ofFrame(Materials.Wood)
            )
        )
        // spotless:on
        .addElement(
            's',
            withChannel(CropsNHStructureChannels.IFTier.get(), chainAllTiredComponents(CropsNHBlocks.blockSeedBed)))
        .build();

    private static class IFUpgradeElement implements IStructureElement<MTEIndustrialFarm> {

        private final IStructureElement<MTEIndustrialFarm> element;
        private final CropsNHBlockIndustrialFarmTiredComponent block;

        public IFUpgradeElement(CropsNHBlockIndustrialFarmTiredComponent block, Consumer<MTEIndustrialFarm> onPass) {
            this.element = onElementPass(onPass, chainAllTiredComponents(block));
            this.block = block;

        }

        @Override
        public boolean check(MTEIndustrialFarm t, World world, int x, int y, int z) {
            return this.element.check(t, world, x, y, z);
        }

        @Override
        public boolean couldBeValid(MTEIndustrialFarm t, World world, int x, int y, int z, ItemStack trigger) {
            return this.element.couldBeValid(t, world, x, y, z, trigger);
        }

        @Override
        public boolean placeBlock(MTEIndustrialFarm t, World world, int x, int y, int z, ItemStack trigger) {
            // don't place in creative
            return false;
        }

        @Override
        public boolean spawnHint(MTEIndustrialFarm t, World world, int x, int y, int z, ItemStack trigger) {
            return this.element.spawnHint(t, world, x, y, z, trigger);
        }

        @Nullable
        @Override
        public BlocksToPlace getBlocksToPlace(MTEIndustrialFarm t, World world, int x, int y, int z, ItemStack trigger,
            AutoPlaceEnvironment env) {
            return this.element.getBlocksToPlace(t, world, x, y, z, trigger, env);
        }

        @Override
        public PlaceResult survivalPlaceBlock(MTEIndustrialFarm t, World world, int x, int y, int z, ItemStack trigger,
            AutoPlaceEnvironment env) {
            // should lock out the fake player used by the block renderer mod for the NEI plugin
            if (env.getActor() instanceof EntityPlayerMP) {
                // skip if the upgrades are disabled
                if (!CropsNHStructureChannels.IFUpgrades.hasValue(trigger)) {
                    return PlaceResult.REJECT_CONTINUE;
                }
            }
            // check if we can place the upgrade at the current tier
            Integer tier = this.block.getTier(getStructureLengthFromTrigger(trigger) - MIN_SLICES + MIN_CASING_TIER);
            if (tier == null) {
                return PlaceResult.REJECT_CONTINUE;
            }
            // re-base the stack size as needed
            trigger = trigger.copy();
            trigger.stackSize = tier - this.block.minTier + 1;
            // skip if reject
            PlaceResult result = this.element.survivalPlaceBlock(t, world, x, y, z, trigger, env);
            return result == PlaceResult.REJECT ? PlaceResult.REJECT_CONTINUE : result;
        }
    }

    private static IStructureElement<MTEIndustrialFarm> chainAllTiredComponents(Block block) {
        Class<?> c = block.getClass();
        return lazy(() -> ofBlocksTiered((blockIn, metaIn) -> {
            if (c.isInstance(blockIn) && blockIn instanceof CropsNHBlockIndustrialFarmTiredComponent component) {
                return component.getTier(metaIn);
            }
            return null;
        },
            ((CropsNHBlockIndustrialFarmTiredComponent) block).getStructureBlocks(),
            -1,
            MTEIndustrialFarm::setUpgradeTier,
            MTEIndustrialFarm::getUpgradeTier));
    }

    @Override
    public boolean supportsVoidProtection() {
        return true;
    }

    @Override
    public Set<VoidingMode> getAllowedVoidingModes() {
        return VoidingMode.ITEM_ONLY_MODES;
    }

    @Override
    public VoidingMode getDefaultVoidingMode() {
        return VoidingMode.VOID_NONE;
    }

    @Override
    public void onContentsChanged(int slot) {
        super.onContentsChanged(slot);
    }

    private static void setUpgradeTier(MTEIndustrialFarm te, Integer tier) {
        te.upgradeTier = tier;
    }

    private static Integer getUpgradeTier(MTEIndustrialFarm te) {
        return te.upgradeTier;
    }

    @Override
    public IStructureDefinition<MTEIndustrialFarm> getStructureDefinition() {
        return STRUCTURE_DEFINITION;
    }

    private static int getStructureLengthFromTrigger(ItemStack trigger) {
        return CropsNHStructureChannels.IFTier.getValueClamped(trigger, MIN_SLICES, MAX_SLICES);
    }

    @Override
    public void construct(ItemStack trigger, boolean hintsOnly) {
        buildPiece(STRUCTURE_PIECE_FIRST, trigger, hintsOnly, 2, 2, 0);
        int slices = getStructureLengthFromTrigger(trigger);
        for (int sliceIndex = 0; sliceIndex < slices; sliceIndex++) {
            buildPiece(STRUCTURE_PIECE_LATER, trigger, hintsOnly, 2, 2, -sliceIndex - 1);
        }
        buildPiece(STRUCTURE_PIECE_LAST, trigger, hintsOnly, 2, 2, -slices - 1);
    }

    @Override
    public int survivalConstruct(ItemStack trigger, int elementBudget, ISurvivalBuildEnvironment env) {
        int built = survivalBuildPiece(STRUCTURE_PIECE_FIRST, trigger, 2, 2, 0, elementBudget, env, false, true);
        if (built != -1) return built;
        int slices = getStructureLengthFromTrigger(trigger);
        for (int sliceIndex = 0; sliceIndex < slices; sliceIndex++) {
            built = survivalBuildPiece(
                STRUCTURE_PIECE_LATER,
                trigger,
                2,
                2,
                -sliceIndex - 1,
                elementBudget,
                env,
                false,
                true);
            if (built != -1) return built;
        }
        return survivalBuildPiece(STRUCTURE_PIECE_LAST, trigger, 2, 2, -slices - 1, elementBudget, env, false, true);
    }

    private static final StructureError SE_COMPONENT_TIER_TOO_LOW = StructureErrors.of(
        Reference.MOD_ID + "_tooltip.industrialFarm.structure.error.ComponentTierTooLow",
        TranslatableText.literal(GTValues.VN[MIN_CASING_TIER]));
    private static final StructureError SE_GLASS_TIER_TOO_LOW = StructureErrors.of(
        Reference.MOD_ID + "_tooltip.industrialFarm.structure.error.GlassTierTooLow",
        TranslatableText.literal(GTValues.VN[MIN_CASING_TIER]));
    private static final StructureError SE_EEU_COUNT = StructureErrors.of(
        Reference.MOD_ID + "_tooltip.industrialFarm.structure.error.EEUCount",
        TranslatableText.literal(BlockEnvironmentalEnhancementUnit.MAX_UPGRADE_COUNT));
    private static final StructureError SE_FU_COUNT = StructureErrors
        .of(Reference.MOD_ID + "_tooltip.industrialFarm.structure.error.FUCount");
    private static final StructureError SE_AHU_COUNT = StructureErrors.of(
        Reference.MOD_ID + "_tooltip.industrialFarm.structure.error.AHUCount",
        TranslatableText.literal(BlockAdvancedHarvestingUnit.MAX_UPGRADE_COUNT));
    private static final StructureError SE_OCGAU_COUNT = StructureErrors
        .of(Reference.MOD_ID + "_tooltip.industrialFarm.structure.error.OCGAUCount");
    private static final StructureError SE_OCGAU_EXCLUSIVITY = StructureErrors
        .of(Reference.MOD_ID + "_tooltip.industrialFarm.structure.error.OCGAUExclusivity");
    private static final StructureError SE_OCGAU_MULTI_AMP_EHATCH_COUNT = StructureErrors
        .of(Reference.MOD_ID + "_tooltip.industrialFarm.structure.error.MultiAmpEHatchCount");
    private static final StructureError SE_EHATCH_TYPE_NO_MIX = StructureErrors
        .of(Reference.MOD_ID + "_tooltip.industrialFarm.structure.error.EHatchTypeNoMix");
    private static final StructureError SE_LASER_NOT_ALLOWED = StructureErrors
        .of(Reference.MOD_ID + "_tooltip.industrialFarm.structure.error.LaserNotAllowed");
    private static final StructureError SE_MULTI_AMP_NOT_ALLOWED = StructureErrors
        .of(Reference.MOD_ID + "_tooltip.industrialFarm.structure.error.MultiAmpNotAllowed");

    @Override
    public void checkMachine(IGregTechTileEntity baseMetaTileEntity, ItemStack stack, List<StructureError> errors) {
        this.upgradeTier = -1;
        this.glassTier = -1;
        this.environmentalEnhancementUnitCount = 0;
        this.growthAccelerationUnitCount = 0;
        this.fertilizerUnitCount = 0;
        this.advancedHarvestingUnitCount = 0;
        this.overclockedGrowthAccelerationUnitCount = 0;
        this.seedCapacity = 0;

        if (!checkPiece(STRUCTURE_PIECE_FIRST, 2, 2, 0, errors)) return;

        // check the first slice
        if (!checkPiece(STRUCTURE_PIECE_LATER, 2, 2, -1, errors)) return;
        // check if components are below the minimum tiers allowed
        if (hasUnderTiredComponents(errors)) return;

        int slices = GTUtility.clamp(this.upgradeTier - MIN_CASING_TIER + MIN_SLICES, MIN_SLICES, MAX_SLICES);
        for (int sliceIndex = 1; sliceIndex < slices; sliceIndex++) {
            if (!checkPiece(STRUCTURE_PIECE_LATER, 2, 2, -sliceIndex - 1, errors)) return;
            // check if components are below the minimum tiers allowed
            if (hasUnderTiredComponents(errors)) return;
        }

        if (!checkPiece(STRUCTURE_PIECE_LAST, 2, 2, -slices - 1, errors)) return;
        // check if components are below the minimum tiers allowed
        if (hasUnderTiredComponents(errors)) return;

        // input bus is optional since it's not needed for crops with no sub-soils
        checkHasAnyEnergy(errors);
        checkHasMaintenanceHatch(errors);
        checkHasInputHatch(errors);
        checkHasOutputBus(errors);

        if (!errors.isEmpty()) return;

        // validate upgrade counts
        if (this.environmentalEnhancementUnitCount > BlockEnvironmentalEnhancementUnit.MAX_UPGRADE_COUNT) {
            errors.add(SE_EEU_COUNT);
        }
        if (this.fertilizerUnitCount > BlockFertilizerUnit.MAX_UPGRADE_COUNT) {
            errors.add(SE_FU_COUNT);
        }
        if (this.advancedHarvestingUnitCount > BlockAdvancedHarvestingUnit.MAX_UPGRADE_COUNT) {
            errors.add(SE_AHU_COUNT);
        }
        if (this.overclockedGrowthAccelerationUnitCount > BlockOverclockedGrowthAccelerationUnit.MAX_UPGRADE_COUNT) {
            errors.add(SE_OCGAU_COUNT);
        }
        // can't have OCGAU and reg GAU at the same time
        if (this.growthAccelerationUnitCount > 0 && this.overclockedGrowthAccelerationUnitCount > 0) {
            errors.add(SE_OCGAU_EXCLUSIVITY);
        }
        if (!errors.isEmpty()) return;

        // validate exotic hatches depending on the presence of the oc upgrade.
        if (this.overclockedGrowthAccelerationUnitCount > 0) {
            // limit the number of multi-amp hatches
            if (this.mExoticEnergyHatches.size() > MAX_MULTIAMP_EHATCH_AMOUNT) {
                errors.add(SE_OCGAU_MULTI_AMP_EHATCH_COUNT);
            }
            // can't mix and match when using multi-amps
            if (!this.mExoticEnergyHatches.isEmpty() && !this.mEnergyHatches.isEmpty()) {
                errors.add(SE_EHATCH_TYPE_NO_MIX);
            }
            for (MTEHatch hatch : this.mExoticEnergyHatches) {
                // probably superfluous but eh. it's not like this will be the perf bottleneck of this machine
                if (hatch.getConnectionType() == MTEHatch.ConnectionType.LASER) {
                    errors.add(SE_LASER_NOT_ALLOWED);
                    break;
                }
                // hatch tier must be <= to glass tier
                if (this.glassTier < VoltageIndex.UMV && hatch.mTier > this.glassTier) {
                    errors.add(StructureErrorRegistry.ENERGY_TIER_EXCEED_GLASS);
                    break;
                }
            }
        } else if (!this.mExoticEnergyHatches.isEmpty()) {
            // when no OCGAU is installed, multi-amp energy hatches aren't allowed
            errors.add(SE_MULTI_AMP_NOT_ALLOWED);
        }

        // validate normal energy hatch tiers
        for (MTEHatch hatch : this.mEnergyHatches) {
            // probably superfluous but eh. it's not like this will be the perf bottleneck of this machine
            if (hatch.getConnectionType() == MTEHatch.ConnectionType.LASER) {
                errors.add(SE_LASER_NOT_ALLOWED);
                break;
            }
            // hatch tier must be <= to glass tier
            if (this.glassTier < VoltageIndex.UMV && hatch.mTier > this.glassTier) {
                errors.add(StructureErrorRegistry.ENERGY_TIER_EXCEED_GLASS);
                break;
            }
        }

        if (!errors.isEmpty()) return;

        // calculate power usage
        // base eu/t should be based on the seedbed/upgrade tier.
        long powerUsage = getPowerUsage();

        if (this.overclockedGrowthAccelerationUnitCount > 0) {
            OverclockCalculator calculator = new OverclockCalculator().setRecipeEUt(powerUsage)
                .setEUt(this.getMaxInputEu())
                .setDuration(Integer.MAX_VALUE)
                .calculate();
            this.expectedOCs = calculator.getPerformedOverclocks();
            this.expectedEUt = calculator.getConsumption();
        } else {
            this.expectedOCs = 0;
            this.expectedEUt = powerUsage;
        }

        this.seedCapacity = BlockSeedBed.getCapacity(this.upgradeTier);
    }

    private boolean hasUnderTiredComponents(List<StructureError> errors) {
        // Must be MV glass or higher
        if (this.glassTier < MIN_CASING_TIER) {
            errors.add(SE_GLASS_TIER_TOO_LOW);
        }
        // Must be MV upgrades or higher
        if (this.upgradeTier < MIN_CASING_TIER) {
            errors.add(SE_COMPONENT_TIER_TOO_LOW);
        }
        return !errors.isEmpty();
    }

    private long getPowerUsage() {
        long basePower = BlockSeedBed.getBaseEUt(this.upgradeTier);
        long powerUsage = basePower;
        if (this.environmentalEnhancementUnitCount > 0) {
            powerUsage += (long) (basePower * BlockEnvironmentalEnhancementUnit.BASE_POWER_INCREASE
                * this.environmentalEnhancementUnitCount);
        }
        if (this.growthAccelerationUnitCount > 0) {
            powerUsage += (long) (basePower * BlockGrowthAccelerationUnit.BASE_POWER_INCREASE
                * this.growthAccelerationUnitCount);
        }
        if (this.fertilizerUnitCount > 0) {
            powerUsage += (long) (basePower * BlockFertilizerUnit.BASE_POWER_INCREASE * this.fertilizerUnitCount);
        }
        if (this.advancedHarvestingUnitCount > 0) {
            powerUsage += (long) (basePower * BlockAdvancedHarvestingUnit.BASE_POWER_INCREASE
                * this.advancedHarvestingUnitCount);
        }
        return powerUsage;
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection side, ForgeDirection facing,
        int colorIndex, boolean active, boolean redstoneLevel) {
        return Textures.BlockIcons.createTextureWithCasing(
            this,
            side,
            facing,
            active,
            OVERLAY_FRONT_ASSEMBLY_LINE,
            OVERLAY_FRONT_ASSEMBLY_LINE_GLOW,
            OVERLAY_FRONT_ASSEMBLY_LINE_ACTIVE,
            OVERLAY_FRONT_ASSEMBLY_LINE_ACTIVE_GLOW);
    }

    @Override
    public ITexture getCasingTexture() {
        return Textures.BlockIcons.casingTexturePages[Constants.GT_CASING_PAGE][0];
    }
    // endregion structure

    // region ctor
    public MTEIndustrialFarm(int id) {
        super(
            id,
            "multimachine.industrialfarm",
            StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.name"));
        this.invWrapper = new CombinedInvWrapper(this.ifStackHandler, this.inventoryHandler);
    }

    public MTEIndustrialFarm(String name) {
        super(name);
        this.invWrapper = new CombinedInvWrapper(this.ifStackHandler, this.inventoryHandler);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity tileEntity) {
        return new MTEIndustrialFarm(this.mName);
    }

    // endregion ctor

    // region tooltips
    @Override
    protected MultiblockTooltipBuilder createTooltip() {
        final MultiblockTooltipBuilder tt = new MultiblockTooltipBuilder();
        tt.addMachineType(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.machineType"))
            .addInfo(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.0"))
            .addSeparator()
            .addInfo(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.1"))
            .addInfo(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.2"))
            .addSeparator()
            .addGlassEnergyLimitInfo()
            .addInfo(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.MBTT.multiAmpsWithUpgrade.0"))
            .addInfo(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.MBTT.multiAmpsWithUpgrade.1"));

        tt.beginVariableStructureBlock(2 + MIN_SLICES, 2 + MAX_SLICES, 5, 5, 4, 4, true)
            .addController("Front center, 2nd layer")
            .addEnergyHatch("1+", "Any center end casing", 1)
            .addMaintenanceHatch("1", "Any center end casing", 1)
            .addInputBus("0+", "Any center end casing", 1)
            .addInputHatch("1+", "Any center end casing", 1)
            .addOutputBus("1+", "Any center end casing", 1)
            .addStructureInfo("")
            .addStructureInfo(StatCollector.translateToLocal("GT5U.MBTT.Structure.Base"))
            .addCasing("16-27", StatCollector.translateToLocal(Reference.MOD_ID + ".casings1.0.name"), false)
            .addCasing("4", "Any Tiered Glass", true)
            .addCasing(
                "3",
                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.structure.seedBed"),
                true)
            .addCasing(
                "0-1",
                GTOreDictUnificator.get(OrePrefixes.frameGt, Materials.Wood, 1)
                    .getDisplayName(),
                false)
            .addMiscHatch(
                "0-1",
                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.structure.upgrades.name"),
                StatCollector.translateToLocal("cropsnh_tooltip.industrialFarm.structure.upgrades.info"),
                2)
            .addStructureInfo("")
            .addStructureInfo(StatCollector.translateToLocal("GT5U.MBTT.Structure.Slice"))
            .addCasing("4", "Any Tiered Glass", true)
            .addCasing(
                "3",
                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.structure.seedBed"),
                true)
            .addCasing("2", StatCollector.translateToLocal(Reference.MOD_ID + ".casings1.0.name"), false)
            .addCasing(
                "0-1",
                GTOreDictUnificator.get(OrePrefixes.frameGt, Materials.Wood, 1)
                    .getDisplayName(),
                false)
            .addMiscHatch(
                "0-1",
                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.structure.upgrades.name"),
                StatCollector.translateToLocal("cropsnh_tooltip.industrialFarm.structure.upgrades.info"),
                2)
            .addStructureInfo("")
            .addMasterChannel(StatCollector.translateToLocal("channels.gregtech.master.length"))
            .addSubChannel(GTStructureChannels.BOROGLASS)
            .addSubChannel(CropsNHStructureChannels.IFTier)
            .addSubChannel(CropsNHStructureChannels.IFUpgrades)
            .toolTipFinisher();
        return tt;
    }

    @Override
    public String[] getInfoData() {
        ArrayList<String> ret = new ArrayList<>(Arrays.asList(super.getInfoData()));
        // tier
        ret.add(
            StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.industrialFarm.scanner.0",
                formatNumber(this.upgradeTier)));
        // capacity
        ret.add(
            StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.industrialFarm.scanner.1",
                formatNumber(this.seedCapacity)));
        // Harvest Modifier
        ret.add(
            StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.industrialFarm.scanner.2",
                formatNumber(this.getHarvestRoundMultiplier() * 100.0f)));
        // Growth Modifier
        ret.add(
            StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.industrialFarm.scanner.3",
                formatNumber(this.getGrowthSpeedMultiplier() * 100.0f)));
        ret.add(
            StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.industrialFarm.scanner.4",
                formatNumber(this.getWaterPotencyNeededPerCycle()) + getFluidUnit(),
                formatNumber(CYCLE_DURATION)));
        // Fertilizer Usage per Cycle
        String fertLangKey = Reference.MOD_ID + "_tooltip.industrialFarm.scanner.5";
        if (this.fertilizerUnitCount > 0) fertLangKey += ".enriched";
        ret.add(
            StatCollector.translateToLocalFormatted(
                fertLangKey,
                formatNumber(this.getFertilizerPotencyNeededPerCycle()) + getFluidUnit(),
                formatNumber(CYCLE_DURATION)));
        // nutrient score
        ISeedData seedData = CropsNHUtils.getAnalyzedSeedData(this.getSeedStack());
        if (seedData != null) {
            ret.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.industrialFarm.scanner.6",
                    formatNumber(this.getNutrientScore(seedData)),
                    formatNumber(TileEntityCropSticks.MAX_NUTRIENT_SCORE)));
        }
        return ret.toArray(new String[0]);
    }

    // endregion tooltips

    // region inv stuff

    @Override
    public IItemHandlerModifiable getInventoryHandler() {
        return this.invWrapper;
    }

    public ItemStack getSeedStack() {
        return this.ifStackHandler.getStackInSlot(SLOT_SEED);
    }

    public void setSeedStack(ItemStack stack) {
        this.ifStackHandler.setStackInSlot(SLOT_SEED, stack);
    }

    public ItemStack getSubSoilStack() {
        return this.ifStackHandler.getStackInSlot(SLOT_SUB_SOIL);
    }

    public void setSubSoilStack(ItemStack stack) {
        this.ifStackHandler.setStackInSlot(SLOT_SUB_SOIL, stack);
    }

    public ItemStack getEnvironmentalModuleStack(int slot) {
        return this.ifStackHandler.getStackInSlot(SLOT_ENV_CARD_START + slot);
    }

    // endregion inv stuff

    // region NBT
    @Override
    public void loadNBTData(NBTTagCompound nbt) {
        super.loadNBTData(nbt);
        if (nbt.hasKey(NBT_INVENTORY_TAG, NBT.TAG_COMPOUND)) {
            this.ifStackHandler.deserializeNBT(nbt.getCompoundTag(NBT_INVENTORY_TAG));
        }
        this.outputTracker = new IFDropTable(nbt, NBT_OUTPUT_TRACKER);
        this.hasFertilizer = nbt.getBoolean(NBT_HAS_FERTILIZER);
    }

    @Override
    public void saveNBTData(NBTTagCompound nbt) {
        super.saveNBTData(nbt);
        nbt.setTag(NBT_INVENTORY_TAG, this.ifStackHandler.serializeNBT());
        nbt.setTag(NBT_OUTPUT_TRACKER, this.outputTracker.save());
        nbt.setBoolean(NBT_HAS_FERTILIZER, this.hasFertilizer);
    }
    // endregion NBT

    // region machine mode
    @Override
    public boolean supportsMachineModeSwitch() {
        return true;
    }

    @Override
    public int nextMachineMode() {
        return switch (this.machineMode) {
            case MODE_INPUT -> MODE_FARM;
            case MODE_FARM -> MODE_OUTPUT;
            default -> MODE_INPUT;
        };
    }

    @Override
    public void setMachineMode(int index) {
        switch (index) {
            case MODE_INPUT, MODE_FARM, MODE_OUTPUT -> this.machineMode = index;
            default -> this.machineMode = MODE_INPUT;
        }
    }

    @Override
    public String getMachineModeName() {
        return switch (this.machineMode) {
            case MODE_FARM -> StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.mode.farm");
            case MODE_OUTPUT -> StatCollector
                .translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.mode.output");
            default -> StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.mode.input");
        };
    }

    // endregion machine mode

    // region gui
    protected @NotNull MTEMultiBlockBaseGui<?> getGui() {
        return new MTEIndustrialFarmGui(this).withMachineModeIcons(
            GTGuiTextures.OVERLAY_BUTTON_ALLOW_INPUT,
            GTGuiTextures.OVERLAY_BUTTON_CYCLIC,
            GTGuiTextures.OVERLAY_BUTTON_ALLOW_OUTPUT);
    }
    // endregion gui

    // region processing
    /** Can't insert a seed because the existing sub-soil didn't match the new seed. */
    @Nonnull
    public static final CheckRecipeResult CHECK_RECIPE_RESULT_SUB_SOIL_MISMATCH_INPUT = SimpleCheckRecipeResult
        .ofFailure(Reference.MOD_ID + ".industrialFarm.subSoilMismatch.input");
    /** Can't farm a crop because the existing sub-soil didn't match the seed. */
    @Nonnull
    public static final CheckRecipeResult CHECK_RECIPE_RESULT_SUB_SOIL_MISMATCH_FARM = SimpleCheckRecipeResult
        .ofFailure(Reference.MOD_ID + ".industrialFarm.subSoilMismatch.farm");
    /** Can't insert a seed because the required sub-soil wasn't found */
    @Nonnull
    public static final CheckRecipeResult CHECK_RECIPE_RESULT_SUB_SOIL_NOT_FOUND = SimpleCheckRecipeResult
        .ofFailure(Reference.MOD_ID + ".industrialFarm.subSoilNotFound");
    /** The current tier of seed bed is too low for this seed. */
    @Nonnull
    public static final CheckRecipeResult CHECK_RECIPE_RESULT_SEED_BED_TIER_TOO_LOW = SimpleCheckRecipeResult
        .ofFailure(Reference.MOD_ID + ".industrialFarm.seedBedTierTooLow");
    /** Can't insert any more seeds because the IF is full. */
    @Nonnull
    public static final CheckRecipeResult CHECK_RECIPE_RESULT_SEEDS_FULL = SimpleCheckRecipeResult
        .ofFailure(Reference.MOD_ID + ".industrialFarm.seedsFull");
    /** Can't run because there are too many seeds in the machine. */
    @Nonnull
    public static final CheckRecipeResult CHECK_RECIPE_RESULT_SEED_OVERFLOW = SimpleCheckRecipeResult
        .ofFailure(Reference.MOD_ID + ".industrialFarm.seedOverflow");
    /** Can't generate resources because the growth requires aren't met */
    @Nonnull
    public static final CheckRecipeResult CHECK_RECIPE_RESULT_CANNOT_GROW = SimpleCheckRecipeResult
        .ofFailure(Reference.MOD_ID + ".industrialFarm.cannotGrow");
    /** Can't generate resources because there machine doesn't have enough water. */
    @Nonnull
    public static final CheckRecipeResult NOT_ENOUGH_WATER = SimpleCheckRecipeResult
        .ofFailure(Reference.MOD_ID + ".industrialFarm.notEnoughWater");
    /** Can't generate resources because there isn't enough enriched fertilizer. */
    @Nonnull
    public static final CheckRecipeResult NOT_ENOUGH_Enriched_FERTILIZER = SimpleCheckRecipeResult
        .ofFailure(Reference.MOD_ID + ".industrialFarm.notEnoughEnrichedFertilizer");

    @Override
    public @Nonnull CheckRecipeResult checkProcessing() {
        switch (this.machineMode) {
            case MODE_INPUT:
                this.outputTracker.clear();
                return this.checkProcessingInputMode();
            case MODE_FARM:
                CheckRecipeResult result = this.checkProcessingFarmMode();
                if (!result.wasSuccessful()) {
                    this.outputTracker.clear();
                }
                return result;
            case MODE_OUTPUT:
                this.outputTracker.clear();
                return this.checkProcessingOutputMode();
            default:
                return CheckRecipeResultRegistry.NO_RECIPE;
        }
    }

    // region input mode
    private CheckRecipeResult checkProcessingInputMode() {
        if (this.seedCapacity <= 0) return CheckRecipeResultRegistry.NONE;
        ItemStack existing = this.getSeedStack();
        List<ItemStack> inputs = this.getStoredInputs();

        // the path is going to differ if the multi already contains seeds.
        CheckRecipeResult result = CropsNHUtils.isStackValid(existing) ? tryAddSeedsToExisting(inputs, existing)
            : tryAddNewSeeds(inputs);

        if (result.wasSuccessful()) {
            this.mMaxProgresstime = 5;
            this.lEUt = 0;
            this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
            this.mEfficiencyIncrease = 10000;
            return result;
        }
        return result;
    }

    private CheckRecipeResult tryAddSeedsToExisting(List<ItemStack> inputs, ItemStack existing) {
        if (inputs.isEmpty()) return CheckRecipeResultRegistry.NO_RECIPE;
        // if it's full abort early
        if (existing.stackSize >= this.seedCapacity) return CHECK_RECIPE_RESULT_SEEDS_FULL;
        // Find how many matching seeds are in the inputs.
        int availableSeeds = consumeMatchingStacks(existing, inputs, 0, this.seedCapacity, true);
        if (availableSeeds == 0) return CheckRecipeResultRegistry.NO_RECIPE;
        int insertionMax = existing.stackSize + availableSeeds;
        // if we have a sub-soil check how many to consume
        ItemStack subSoilStack = this.getSubSoilStack();
        subSoilStack = CropsNHUtils.isStackValid(subSoilStack) ? subSoilStack : null;
        if (subSoilStack != null && insertionMax - subSoilStack.stackSize > 0) {
            int SubSoilsToConsume = consumeMatchingStacks(subSoilStack, inputs, 0, insertionMax, true);
            if (SubSoilsToConsume <= 0) {
                return CHECK_RECIPE_RESULT_SUB_SOIL_NOT_FOUND;
            }
            insertionMax = Math.min(insertionMax, subSoilStack.stackSize + SubSoilsToConsume);
        }

        // consume the items, and the relevant stacks should all get updated automatically.
        consumeMatchingStacks(existing, inputs, 0, insertionMax, false);
        if (subSoilStack != null) {
            consumeMatchingStacks(subSoilStack, inputs, 0, insertionMax, false);
        }
        return CheckRecipeResultRegistry.SUCCESSFUL;
    }

    private CheckRecipeResult tryAddNewSeeds(List<ItemStack> inputs) {
        int seedIndex = 0;
        int subSoilIndex = 0;
        ItemStack newSeedStack = null;
        ItemStack newSubSoilStack = null;
        for (; seedIndex < inputs.size(); seedIndex++) {
            // the seed must be a valid item and an analyzed seed.
            final ItemStack seedCandidate = inputs.get(seedIndex);
            // check if it's an analyzed crop
            final ISeedData seedData = CropsNHUtils.getAnalyzedSeedData(seedCandidate);
            if (seedData == null) continue;
            if (seedData.getCrop()
                .getMinSeedBedTier() > this.upgradeTier) return CHECK_RECIPE_RESULT_SEED_BED_TIER_TOO_LOW;
            // check if the crop can grow
            boolean oldHasFertilizer = this.hasFertilizer;
            // assume we have fertilizer when inserting to prevent issues with high tier crops that may require it.
            this.hasFertilizer = true;
            int growthSpeedUnscaled = this.getGrowthSpeedUnscaled(seedData);
            this.hasFertilizer = oldHasFertilizer;
            if (growthSpeedUnscaled <= 0) return CHECK_RECIPE_RESULT_CANNOT_GROW;
            // if it has a sub-soil try to consume it
            reqs: for (IGrowthRequirement requirement : seedData.getCrop()
                .getGrowthRequirements()) {
                if (requirement instanceof SubSoilRequirement subSoilReq) {
                    ItemStack existingSubSoilStack = this.getSubSoilStack();
                    if (CropsNHUtils.isStackValid(existingSubSoilStack)) {
                        // check if the existing sub-soil matches the new crop.
                        // this shouldn't happen much since sub-soil can't be manually accessed by the player.
                        if (!subSoilReq.isValidSubSoil(existingSubSoilStack, false)) {
                            return CHECK_RECIPE_RESULT_SUB_SOIL_MISMATCH_INPUT;
                        }
                        newSubSoilStack = existingSubSoilStack;
                        break;
                    } else {
                        for (subSoilIndex = 0; subSoilIndex < inputs.size(); subSoilIndex++) {
                            ItemStack subSoilCandidate = inputs.get(subSoilIndex);
                            // abort early if it's the seed candidate or the not a valid sub-soil.
                            if (subSoilIndex == seedIndex || !subSoilReq.isValidSubSoil(subSoilCandidate, false))
                                continue;
                            // else save the stack for later.
                            newSubSoilStack = subSoilCandidate.copy();
                            newSubSoilStack.stackSize = 0;
                            break reqs;
                        }
                    }

                    // sub-soil not found, assume incorrect input instead of checking for other seeds.
                    return CHECK_RECIPE_RESULT_SUB_SOIL_NOT_FOUND;
                }
            }
            // put the seed searcher back by 1 slot and save the new search targets.
            newSeedStack = seedCandidate.copy();
            newSeedStack.stackSize = 0;
            break;
        }
        // if nothing is in the list, nothing was found
        if (newSeedStack == null) return CheckRecipeResultRegistry.NO_RECIPE;

        // find the maximum amount of items we can consume.
        if (newSubSoilStack == null) {
            // just consume up to the capacity, no need for any other complex checks.
            consumeMatchingStacks(newSeedStack, inputs, seedIndex, this.seedCapacity, false);
        } else {
            // check how many sub-soil items will be in the machine if we try to consume everything.
            int additionalSubSoilAvailable = consumeMatchingStacks(
                newSubSoilStack,
                inputs,
                subSoilIndex,
                this.seedCapacity,
                true);
            int subSoilInMachineIfAllConsumed = newSubSoilStack.stackSize + additionalSubSoilAvailable;
            // Check how many seeds are available
            int availableSeeds = consumeMatchingStacks(
                newSeedStack,
                inputs,
                seedIndex,
                subSoilInMachineIfAllConsumed,
                true);
            // Update the max sub-soil consumption based on how many seeds we're inserting in case there were some
            // blocks already in the machine.
            int maxAmountAfterIngest = Math.min(availableSeeds, subSoilInMachineIfAllConsumed);
            // consume
            consumeMatchingStacks(newSeedStack, inputs, seedIndex, maxAmountAfterIngest, false);
            consumeMatchingStacks(newSubSoilStack, inputs, subSoilIndex, maxAmountAfterIngest, false);
        }

        // update the inventory
        this.setSeedStack(newSeedStack);
        if (newSubSoilStack != null) {
            this.setSubSoilStack(newSubSoilStack);
        }

        return CheckRecipeResultRegistry.SUCCESSFUL;
    }

    /**
     * Attempts to transfer all matching items form the list until the existing stack reaches a given item limit.
     *
     * @param existing    The item to look for and to add the amount to.
     * @param provider    A list of items to consume from.
     * @param startAt     Where to start reading the tracker from (optimisation)
     * @param maxCapacity The maximum of number of items that can be stored.
     * @param simulate    Set to true to prevent the transfer and validate the consumption.
     * @return The amount of items that were consumed.
     */
    private static int consumeMatchingStacks(@Nonnull ItemStack existing, @Nonnull List<ItemStack> provider,
        int startAt, int maxCapacity, boolean simulate) {
        // if the existing stack is already reached max, abort early.
        if (existing.stackSize >= maxCapacity) return 0;
        // else search for the matching stacks
        int newStackSize = existing.stackSize;
        for (int i = startAt; i < provider.size() && newStackSize < maxCapacity; i++) {
            ItemStack stack = provider.get(i);
            // check if item is valid
            if (!isItemStackValidAndCanItStackWithExisting(stack, existing)) continue;
            int toConsume = Math.min(maxCapacity - newStackSize, stack.stackSize);
            newStackSize += toConsume;
            if (!simulate) {
                // IIRC setting the stack to null is bad, if it's zero the multi will deal with it correctly.
                stack.stackSize -= toConsume;
            }
        }
        int consumed = newStackSize - existing.stackSize;
        if (!simulate) {
            existing.stackSize = newStackSize;
        }
        return consumed;
    }

    /**
     * Checks if an item is valid, has a stack size greater than 0, and if it can stack with another existing stack
     *
     * @param toValidate The item stack to validate
     * @param existing   The existing item stack
     * @return True if all checks pass
     */
    private static boolean isItemStackValidAndCanItStackWithExisting(ItemStack toValidate,
        @Nonnull ItemStack existing) {
        return CropsNHUtils.isStackValid(toValidate) && GTUtility.areStacksEqual(toValidate, existing, false);
    }
    // endregion input mode

    /**
     * @implNote The output mode should never void anything,
     *           this is mainly due to the fact that you can't
     *           extract seeds with sub-soil without output mode
     *           due to limitations in MUI2.
     */
    private CheckRecipeResult checkProcessingOutputMode() {
        ItemStack seedStack = this.getSeedStack();
        ItemStack subSoilStack = this.getSubSoilStack();
        List<ItemStack> simulated = new ArrayList<>(2);
        // add seed if present
        if (CropsNHUtils.isStackValid(seedStack)) {
            simulated.add(CropsNHUtils.copyStackWithSize(seedStack, 1));
        } else {
            seedStack = null;
        }
        // add sub-soil if present
        if (CropsNHUtils.isStackValid(subSoilStack)) {
            simulated.add(CropsNHUtils.copyStackWithSize(subSoilStack, 1));
        } else {
            subSoilStack = null;
        }
        // check if anything remains
        if (simulated.isEmpty()) {
            return CheckRecipeResultRegistry.NO_RECIPE;
        }
        // calc max parallel based on min stack size.
        int maxParallels = (seedStack != null && subSoilStack != null)
            ? Math.min(seedStack.stackSize, subSoilStack.stackSize)
            : (seedStack != null ? seedStack.stackSize : subSoilStack.stackSize);
        // do the output voiding checks
        ItemEjectionHelper ejectionHelper = new ItemEjectionHelper(getOutputBusses(), true);
        maxParallels = ejectionHelper.ejectItems(simulated, maxParallels);
        if (maxParallels <= 0) {
            return CheckRecipeResultRegistry.ITEM_OUTPUT_FULL;
        }
        // consume from machine
        if (seedStack != null) {
            seedStack.stackSize -= maxParallels;
            this.setSeedStack(seedStack.stackSize <= 0 ? null : seedStack);
        }
        if (subSoilStack != null) {
            subSoilStack.stackSize -= maxParallels;
            this.setSubSoilStack(subSoilStack.stackSize <= 0 ? null : subSoilStack);
        }
        // eject seeds and blocks
        for (ItemStack stack : simulated) {
            stack.stackSize = maxParallels;
        }
        this.mOutputItems = simulated.toArray(new ItemStack[0]);

        // notify success
        this.mMaxProgresstime = 5;
        this.lEUt = 0;
        this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
        this.mEfficiencyIncrease = 10000;
        return CheckRecipeResultRegistry.SUCCESSFUL;
    }

    // region farm mode

    private double getOCPotencyMultiplier() {
        return this.expectedOCs <= 63 ? 1L << this.expectedOCs : GTUtility.powInt(2.0d, this.expectedOCs);
    }

    private int getWaterPotencyNeededPerCycle() {
        return GTUtility.safeInt(
            (long) Math.ceil(BlockSeedBed.getWaterConsumption(this.upgradeTier) * this.getOCPotencyMultiplier()));
    }

    private int getFertilizerPotencyNeededPerCycle() {
        return GTUtility.safeInt(
            (long) Math.ceil(BlockSeedBed.getFertilizerConsumption(this.upgradeTier) * this.getOCPotencyMultiplier()));
    }

    private int getAmountToConsumeBasedOnPotency(int missingPotency, int inputPotency, int inputAmount) {
        if (missingPotency <= 0 || inputPotency <= 0 || inputAmount <= 0) return 0;
        // Prefer over-consuming in case something with a stupid high potency gets introduced.
        int maxConsume = missingPotency / inputPotency + ((missingPotency % inputPotency) > 0 ? 1 : 0);
        return Math.min(maxConsume, inputAmount);
    }

    private CheckRecipeResult checkProcessingFarmMode() {
        // state checks
        if (this.upgradeTier < MIN_CASING_TIER) return CheckRecipeResultRegistry.INTERNAL_ERROR;

        // get seed
        ISeedData seedData = CropsNHUtils.getAnalyzedSeedData(this.getSeedStack());
        if (seedData == null) return CheckRecipeResultRegistry.NO_RECIPE;

        // check if the seed can grow in the machine.
        if (seedData.getStack().stackSize > this.seedCapacity) return CHECK_RECIPE_RESULT_SEED_OVERFLOW;
        if (seedData.getCrop()
            .getMinSeedBedTier() > this.upgradeTier) return CHECK_RECIPE_RESULT_SEED_BED_TIER_TOO_LOW;

        // check the machine growth requirements
        ItemStack[] growthCatalysts;
        ItemStack subSoilStack = this.getSubSoilStack();
        if (CropsNHUtils.isStackValid(subSoilStack)) {
            growthCatalysts = new ItemStack[] { subSoilStack };
        } else {
            growthCatalysts = new ItemStack[0];
        }
        for (IGrowthRequirement req : seedData.getCrop()
            .getGrowthRequirements()) {
            if (req instanceof IMachineGrowthRequirement machineGrowthReq) {
                if (!machineGrowthReq.canGrow(seedData, this.getBaseMetaTileEntity(), growthCatalysts)) {
                    // custom logic for this one to display a custom message in case some update changes something.
                    if (req instanceof SubSoilRequirement) {
                        return CHECK_RECIPE_RESULT_SUB_SOIL_MISMATCH_FARM;
                    }
                    return CHECK_RECIPE_RESULT_CANNOT_GROW;
                }
            }
        }

        // note on fluid consumption:
        // it's intended to just scale per capacity and not per how many seeds are in the machine.
        // The power cost doesn't scale to seed count either which is on purpose.
        // the liquid consumption formula is:
        // Math.ceil(Math.ceil(this.mSeedCapacity * CYCLE_DURATION / (double)TileEntityCrop.TICK_RATE * (1 <<
        // this.mExpectedOCs)) / potencyPer1LOfLiquid)
        // Desmos visualizer: https://www.desmos.com/calculator/gmul5gq6cv
        List<Pair<FluidStack, Integer>> waterFluidsToConsume = new ArrayList<>();
        List<Pair<FluidStack, Integer>> fertilizerFluidsToConsume = new ArrayList<>();
        int waterPotencyMissing = this.getWaterPotencyNeededPerCycle();
        int fertilizerPotencyMissing = this.getFertilizerPotencyNeededPerCycle();
        for (FluidStack fluidStack : this.getStoredFluids()) {
            if (CropsNHUtils.isStackInvalid(fluidStack)) continue;
            Fluid fluid = fluidStack.getFluid();
            int remaining = fluidStack.amount;
            int potency;
            // consume water if needed
            if (waterPotencyMissing > 0 && remaining > 0
                && (potency = HydrationRegistry.instance.getPotency(fluid)) > 0) {
                int amountToConsume = getAmountToConsumeBasedOnPotency(waterPotencyMissing, potency, remaining);
                if (amountToConsume > 0) {
                    remaining -= amountToConsume;
                    waterPotencyMissing -= amountToConsume * potency;
                    waterFluidsToConsume.add(Pair.of(fluidStack, amountToConsume));
                }
            }
            // consume fertilizer if needed
            if (fertilizerPotencyMissing > 0 && remaining > 0) {
                if (this.fertilizerUnitCount > 0) {
                    // when a fertilizer unit is installed, it can only consume enriched fert,
                    // and there is no potency bonus applied.
                    potency = fluidStack.getFluid() == CropsNHFluids.enrichedFertilizer ? 1 : 0;
                } else {
                    // else you can use any liquid fertilizer you want.
                    potency = FertilizerRegistry.instance.getPotency(fluid);
                }
                if (potency > 0) {
                    int amountToConsume = getAmountToConsumeBasedOnPotency(
                        fertilizerPotencyMissing,
                        potency,
                        remaining);
                    if (amountToConsume > 0) {
                        // uncomment if we ever add other types of liquid inputs
                        // remaining -= amountToConsume;
                        fertilizerPotencyMissing -= amountToConsume * potency;
                        fertilizerFluidsToConsume.add(Pair.of(fluidStack, amountToConsume));
                    }
                }
            }
            // if we consumed something add it to the list for later consumption.
            if (fertilizerPotencyMissing <= 0 && waterPotencyMissing <= 0) break;
        }
        if (waterPotencyMissing > 0) return NOT_ENOUGH_WATER;
        if (this.fertilizerUnitCount > 0 && fertilizerPotencyMissing > 0) return NOT_ENOUGH_Enriched_FERTILIZER;
        this.hasFertilizer = fertilizerPotencyMissing <= 0;

        // calc drops
        IFDropTable dropProgess = this.getDropsPerCycle(seedData);
        if (dropProgess == null) return CHECK_RECIPE_RESULT_CANNOT_GROW;
        dropProgess.addTo(this.outputTracker, seedData.getStack().stackSize);
        // check if output void protection is enabled
        if (this.voidingMode.protectItem) {
            ItemEjectionHelper ejectionHelper = new ItemEjectionHelper(this.getOutputBusses(), true, true);
            ItemStack[] drops = this.outputTracker.getDrops(true);
            if (drops.length != 0 && ejectionHelper.ejectItems(Arrays.asList(drops), 1) <= 0) {
                // remove the added items
                dropProgess.addTo(this.outputTracker, -seedData.getStack().stackSize);
                // return output full.
                return CheckRecipeResultRegistry.ITEM_OUTPUT_FULL;
            }
        }
        // consume fluids
        for (Pair<FluidStack, Integer> waterFluidToConsume : waterFluidsToConsume) {
            waterFluidToConsume.getLeft().amount -= waterFluidToConsume.getRight();
        }
        if (this.hasFertilizer) {
            for (Pair<FluidStack, Integer> fertilizerFluidToConsume : fertilizerFluidsToConsume) {
                fertilizerFluidToConsume.getLeft().amount -= fertilizerFluidToConsume.getRight();
            }
        }
        // output if everything is safe
        this.mOutputItems = this.outputTracker.getDrops(false);

        // calc power cost
        this.lEUt = -this.expectedEUt;
        this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
        this.mEfficiencyIncrease = 10000;
        this.mMaxProgresstime = CYCLE_DURATION;
        return CheckRecipeResultRegistry.SUCCESSFUL;
    }

    // endregion farm mode

    // endregion processing

    // region simulations
    /**
     * Calculates the nutrient score for the crops inside the machine.
     *
     * @param crop The crop to calculate the growth speed of.
     * @return The number of growth points per crop stick growth cycle, if the value is <= 0 the crop would get sick or
     *         cannot grow.
     */
    public int getNutrientScore(ISeedData crop) {
        if (crop == null) return 0;
        // that thing really shouldn't be null when we are calculating this.
        IGregTechTileEntity baseTE = this.getBaseMetaTileEntity();
        if (baseTE == null) return 0;

        // check number of liked biome tags in current biome.
        BiomeGenBase biome = baseTE.getBiome();
        Set<BiomeDictionary.Type> biomeTags = new HashSet<>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        // add extra tags from biome cards
        for (int i = 0; i < this.environmentalEnhancementUnitCount; i++) {
            ItemStack stack = this.getEnvironmentalModuleStack(i);
            if (CropsNHUtils.isStackInvalid(stack) || !(stack.getItem() instanceof ItemEnvironmentalModule)) continue;
            biomeTags.add(ItemEnvironmentalModule.getBiomeTag(CropsNHUtils.getItemMeta(stack)));
        }
        int likedBiomes = (int) crop.getCrop()
            .getLikedBiomeTags()
            .stream()
            .filter(biomeTags::contains)
            .count();
        // calc fertilizer storage to simulate
        // mHasFert or fert unit installed to make the value consistent when he upgrade is installed since the value
        // won't change when it's installed.
        int fertilizerStorage = this.hasFertilizer || this.fertilizerUnitCount > 0
            ? SIMULATED_FERTILIZER_STORAGE_WHEN_FERTILIZER_PROVIDED
            : SIMULATED_FERTILIZER_STORAGE_WHEN_FERTILIZER_NOT_PROVIDED;
        // calc available nutrient points for growth speed calculation
        return TileEntityCropSticks.getNutrientsPerCycle(
            likedBiomes,
            biome.rainfall,
            SIMULATED_CAN_SEE_SKY,
            SIMULATED_WATER_STORAGE,
            fertilizerStorage);
    }

    /**
     * Calculates the number of growth points a crop will gain each growth tick if it was on a crop stick.
     *
     * @param crop The crop to calculate the growth speed of.
     * @return The number of growth points per crop stick growth cycle, if the value is <= 0 the crop would get sick or
     *         cannot grow.
     */
    public int getGrowthSpeedUnscaled(ISeedData crop) {
        // calculate the base growth speed
        return TileEntityCropSticks.getGrowthRate(
            this.getNutrientScore(crop),
            crop.getCrop()
                .getTier(),
            crop.getStats()
                .getGrowth());
    }

    /**
     * @return The growth speed multiplier based on the installed upgrades.
     */
    private double getGrowthSpeedMultiplier() {
        double multiplier = 1.0d;
        // apply additive bonuses
        multiplier += this.growthAccelerationUnitCount * BlockGrowthAccelerationUnit.GROWTH_SPEED_BONUS;
        // apply multiplicative bonuses
        multiplier *= 1.0d + (this.fertilizerUnitCount * BlockFertilizerUnit.GROWTH_SPEED_MULTIPLIER);
        // apply overclocks
        multiplier *= this.expectedOCs <= 63 ? 1L << this.expectedOCs : GTUtility.powInt(2.0d, this.expectedOCs);
        return multiplier;
    }

    /**
     * Calculates the amount of progress towards maturity that a crop will gain each IF cycle.
     *
     * @param crop The crop to simulate.
     * @return The percentage of the crop's overall growth cycle completed each cycle, can be above 100%.
     */
    public double getGrowthProgressPerCycle(ISeedData crop) {
        // calc unscaled growth speed of crop.
        int unscaledGrowthSpeed = this.getGrowthSpeedUnscaled(crop);
        if (unscaledGrowthSpeed <= 0) return -1;
        // calculate percentage grown each tick up to 100% since growth
        // don't carry over if you wait to harvest in world crops.
        // this is intentional balancing and shouldn't be included in the future mega multi.
        int cropGrowthDuration = crop.getCrop()
            .getGrowthDuration();
        int growthTicksPerHarvest = (cropGrowthDuration / unscaledGrowthSpeed)
            + (cropGrowthDuration % unscaledGrowthSpeed == 0 ? 0 : 1);
        // calculate percent progress per growth tick
        double growthPercentPerGrowthTick = 1.0d / growthTicksPerHarvest;
        // scale it to the cycle's rate and apply growth speed multipliers
        return growthPercentPerGrowthTick * CYCLE_TICK_RATE_SCALAR * getGrowthSpeedMultiplier();
    }

    /**
     * @return The growth speed multiplier based on the installed upgrades.
     */
    private double getHarvestRoundMultiplier() {
        double multiplier = 1.0d;
        // additive bonuses
        // Yes it's intended to start at 40% more harvests (due to min tier.
        // This is to make the multi inherently better than the equivalent crop manager)
        multiplier += BlockSeedBed.getHarvestRoundBonus(this.upgradeTier);
        multiplier += this.fertilizerUnitCount * BlockFertilizerUnit.HARVEST_ROUND_BONUS;
        // multiplicative bonuses
        multiplier *= 1.0d + (this.advancedHarvestingUnitCount * BlockAdvancedHarvestingUnit.HARVEST_ROUND_MULTIPLIER);
        return multiplier;
    }

    /**
     * Calculates the drops that should be outputted each cycle.
     *
     * @param crop The crop to simulate
     * @return The drop table or null if it can't grow.
     */
    public @Nullable IFDropTable getDropsPerCycle(ISeedData crop) {
        // calculate how much progress is done each cycle
        double progressPerCycle = getGrowthProgressPerCycle(crop);
        if (progressPerCycle <= 0) return null;

        // calc avg drop stack size increase
        double avgDropIncrease = TileEntityCropSticks.getAvgDropCountIncrease(
            crop.getStats()
                .getGain());

        // calc average number of created drops per harvest
        double avgDropCount = TileEntityCropSticks.getAvgDropRounds(
            crop.getCrop(),
            crop.getStats()
                .getGain());
        avgDropCount *= this.getHarvestRoundMultiplier();

        // create drop table
        IFDropTable drops = new IFDropTable();
        for (Map.Entry<ItemStack, Integer> entry : crop.getCrop()
            .getDropTable()
            .entrySet()) {
            ItemStack stack = entry.getKey();
            double chance = entry.getValue() / 10_000d;
            // scale by chance and progress completed each cycle.
            double unscaled = (stack.stackSize + avgDropIncrease) * chance * avgDropCount;
            drops.addDrop(stack, unscaled * progressPerCycle);
        }

        return drops;
    }

    // endregion simulations
}
