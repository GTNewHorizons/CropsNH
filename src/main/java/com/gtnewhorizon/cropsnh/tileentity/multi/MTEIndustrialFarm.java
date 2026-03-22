package com.gtnewhorizon.cropsnh.tileentity.multi;

import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.formatNumber;
import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.getFluidUnit;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlocksTiered;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.onElementPass;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
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
import static net.minecraft.util.StatCollector.translateToLocal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import com.cleanroommc.modularui.utils.item.CombinedInvWrapper;
import com.cleanroommc.modularui.utils.item.IItemHandlerModifiable;
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
import com.gtnewhorizon.cropsnh.farming.requirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;
import com.gtnewhorizon.cropsnh.items.ItemEnvironmentalModule;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropSticks;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.IFDropTable;
import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
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
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.MTEExtendedPowerMultiBlockBase;
import gregtech.api.metatileentity.implementations.MTEHatch;
import gregtech.api.modularui2.GTGuiTextures;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.recipe.check.SimpleCheckRecipeResult;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTUtility;
import gregtech.api.util.ItemEjectionHelper;
import gregtech.api.util.MultiblockTooltipBuilder;
import gregtech.api.util.OverclockCalculator;
import gregtech.common.gui.modularui.multiblock.base.MTEMultiBlockBaseGui;
import gregtech.common.misc.GTStructureChannels;

public class MTEIndustrialFarm extends MTEExtendedPowerMultiBlockBase<MTEIndustrialFarm>
    implements ISurvivalConstructable {

    /** The duration of the production cycle in seconds. */
    public static final int CYCLE_DURATION = 5 * SECONDS;
    /** The amount of water that should be stored in the crop stick when calculating the growth speed */
    public static final int SIMULATED_WATER_STORAGE = 200;
    /** Whether the crop can see the sky when calculating the growth speed. (true because uv lamps or something) */
    public static final boolean SIMULATED_CAN_SEE_SKY = true;
    /**
     * The amount of fertilizer that should be stored in the crop stick when calculating the growth sped while there is
     * no fertilizer unit installed
     */
    public static final int SIMULATED_FERTILIZER_STORAGE_WHEN_FERTILIZER_UNIT_MISSING = 20;
    /**
     * The amount of fertilizer that should be stored in the crop stick when calculating the growth sped while there is
     * a fertilizer unit installed
     */
    public static final int SIMULATED_FERTILIZER_STORAGE_WHEN_FERTILIZER_UNIT_INSTALLED = 200;

    private final static String NBT_INVENTORY_TAG = "mIFInventory";
    private final static String NBT_OUTPUT_TRACKER = "mOutputTracker";

    /** The default mode, used to insert seed and under-block */
    public static final int MODE_INPUT = 0;
    /** The mode that generates resources. */
    public static final int MODE_FARM = 1;
    /** Used to safely eject the seeds and under-blocks to the output bus. */
    public static final int MODE_OUTPUT = 2;

    /** Slot index of the seed slot in the custom inventory */
    public final static int SLOT_SEED = 0;
    /** Slot index of the under-block slot in the custom inventory */
    public final static int SLOT_BLOCK_UNDER = 1;
    /** Starting slot index of the environmental slots in the custom inventory */
    public final static int SLOT_ENV_CARD_START = 2;

    /** Amount of fertilizer per seed slot in the machine */
    public static final double CYCLE_TICK_RATE_SCALAR = (double) CYCLE_DURATION / TileEntityCropSticks.TICK_RATE;

    /** How many times the output and water/fertilizer consumption of the multi should be doubled. */
    public int mExpectedOCs = 0;
    /** How much power each recipe is expected to use */
    public long mExpectedEUt = 0;
    /** The tier of glass used to build the multi, used to limit the power hatch tiers. */
    public int mGlassTier = -1;
    /** The tier of the upgrades applied to the multi. */
    public int mUpgradeTier = -1;
    /** The number of seeds and under-blocks that can be stored in the controller. */
    public int mSeedCapacity = 0;
    /** The number of environmental enhancement units installed on the multi. */
    public int mEnvironmentalEnhancementUnitCount = 0;
    /** The number of growth acceleration units installed on the multi. */
    public int mGrowthAccelerationUnitCount = 0;
    /** The number of fertilizer units installed on the multi. */
    public int mFertilizerUnitCount = 0;
    /** The number of advanced harvesting units installed on the multi. */
    public int mAdvancedHarvestingUnitCount = 0;
    /** The number of overclocked growth acceleration units installed on the multi. */
    public int mOverclockedGrowthAccelerationUnitCount = 0;

    /** The tracker for the drop progress */
    public IFDropTable mOutputTracker = new IFDropTable();
    /** ItemStack handler for the custom slots. */
    public MTEIndustrialFarmItemStackHandler mIFStackHandler = new MTEIndustrialFarmItemStackHandler(this);
    /** Multi-Inv wrapper since it needs to respond to both the controller slot and the custom slots. */
    private final IItemHandlerModifiable mInvWrapper;

    // region structure
    private static final String STRUCTURE_PIECE_FIRST = "first";
    private static final String STRUCTURE_PIECE_LATER = "later";
    private static final String STRUCTURE_PIECE_LAST = "last";
    private static final int CASING_INDEX = Constants.GT_CASING_PAGE << 7;
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
                { " cCc " },
                { "cCCCc" },
                { "cCCCc" },
                { "c   c" }
                // spotless:on
                }))
        .addElement(
            'C',
            buildHatchAdder(MTEIndustrialFarm.class)
                .anyOf(InputBus, InputHatch, OutputBus, Maintenance, MultiAmpEnergy.or(Energy))
                .casingIndex(GTUtility.getCasingTextureIndex(CropsNHBlocks.blockCasings1, 0))
                .hint(1)
                .buildAndChain(ofBlock(CropsNHBlocks.blockCasings1, 0)))
        .addElement('c', ofBlock(CropsNHBlocks.blockCasings1, 0))
        .addElement('g', chainAllGlasses(-1, (te, t) -> te.mGlassTier = t, te -> te.mGlassTier))
        .addElement(
            'U',
            ofChain(
                ofFrame(Materials.Wood),
                onElementPass(
                    te -> te.mEnvironmentalEnhancementUnitCount++,
                    chainAllTiredComponents(CropsNHBlocks.blockEnvironmentalEnhancementUnit)),
                onElementPass(
                    te -> te.mGrowthAccelerationUnitCount++,
                    chainAllTiredComponents(CropsNHBlocks.blockGrowthAccelerationUnit)),
                onElementPass(
                    te -> te.mFertilizerUnitCount++,
                    chainAllTiredComponents(CropsNHBlocks.blockFertilizerUnit)),
                onElementPass(
                    te -> te.mAdvancedHarvestingUnitCount++,
                    chainAllTiredComponents(CropsNHBlocks.blockAdvancedHarvestingUnit)),
                onElementPass(
                    te -> te.mOverclockedGrowthAccelerationUnitCount++,
                    chainAllTiredComponents(CropsNHBlocks.blockOverclockedGrowthAccelerationUnit))))
        .addElement('s', chainAllTiredComponents(CropsNHBlocks.blockSeedBed))
        .build();

    private static IStructureElement<MTEIndustrialFarm> chainAllTiredComponents(Block block) {
        Class c = block.getClass();
        return lazy(() -> ofBlocksTiered((aBlock, aMeta) -> {
            if (c.isInstance(aBlock) && aBlock instanceof CropsNHBlockIndustrialFarmTiredComponent tComponent) {
                return tComponent.getTier(aMeta);
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
        te.mUpgradeTier = tier;
    }

    private static Integer getUpgradeTier(MTEIndustrialFarm te) {
        return te.mUpgradeTier;
    }

    @Override
    public IStructureDefinition<MTEIndustrialFarm> getStructureDefinition() {
        return STRUCTURE_DEFINITION;
    }

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        buildPiece(STRUCTURE_PIECE_FIRST, stackSize, hintsOnly, 2, 2, 0);
        int tSlices = GTUtility.clamp(stackSize.stackSize, MIN_SLICES, MAX_SLICES);
        for (int tSliceIndex = 0; tSliceIndex < tSlices; tSliceIndex++) {
            buildPiece(STRUCTURE_PIECE_LATER, stackSize, hintsOnly, 2, 2, -tSliceIndex - 1);
        }
        buildPiece(STRUCTURE_PIECE_LAST, stackSize, hintsOnly, 2, 2, -tSlices - 1);
    }

    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, ISurvivalBuildEnvironment env) {
        int tBuilt = survivalBuildPiece(STRUCTURE_PIECE_FIRST, stackSize, 2, 2, 0, elementBudget, env, false, true);
        if (tBuilt != -1) return tBuilt;
        int tSlices = GTUtility.clamp(stackSize.stackSize, MIN_SLICES, MAX_SLICES);
        for (int tSliceIndex = 0; tSliceIndex < tSlices; tSliceIndex++) {
            tBuilt = survivalBuildPiece(
                STRUCTURE_PIECE_LATER,
                stackSize,
                2,
                2,
                -tSliceIndex - 1,
                elementBudget,
                env,
                false,
                true);
            if (tBuilt != -1) return tBuilt;
        }
        return survivalBuildPiece(STRUCTURE_PIECE_LAST, stackSize, 2, 2, -tSlices - 1, elementBudget, env, false, true);
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        this.mUpgradeTier = -1;
        this.mGlassTier = -1;
        this.mEnvironmentalEnhancementUnitCount = 0;
        this.mGrowthAccelerationUnitCount = 0;
        this.mFertilizerUnitCount = 0;
        this.mAdvancedHarvestingUnitCount = 0;
        this.mOverclockedGrowthAccelerationUnitCount = 0;
        this.mSeedCapacity = 0;

        boolean tSuccess = checkPiece(STRUCTURE_PIECE_FIRST, 2, 2, 0);
        if (!tSuccess) return false;

        // check the first slice
        tSuccess = checkPiece(STRUCTURE_PIECE_LATER, 2, 2, -1);
        if (!tSuccess || this.mGlassTier < MIN_CASING_TIER || this.mUpgradeTier < MIN_CASING_TIER) return false;

        int tSlices = GTUtility.clamp(this.mUpgradeTier - MIN_CASING_TIER + MIN_SLICES, MIN_SLICES, MAX_SLICES);
        for (int tSliceIndex = 1; tSliceIndex < tSlices; tSliceIndex++) {
            tSuccess = checkPiece(STRUCTURE_PIECE_LATER, 2, 2, -tSliceIndex - 1);
            if (!tSuccess || this.mGlassTier < MIN_CASING_TIER || this.mUpgradeTier < MIN_CASING_TIER) return false;
        }

        tSuccess = checkPiece(STRUCTURE_PIECE_LAST, 2, 2, -tSlices - 1);
        if (!tSuccess || this.mGlassTier < MIN_CASING_TIER || this.mUpgradeTier < MIN_CASING_TIER) return false;

        if (this.mOutputBusses.size() < 1) return false;
        if (this.mInputHatches.size() < 1) return false;
        if (this.mMaintenanceHatches.size() != 1) return false;
        if (this.mEnergyHatches.size() + this.mExoticEnergyHatches.size() < 1) return false;

        // validate upgrade counts
        if (this.mEnvironmentalEnhancementUnitCount > BlockEnvironmentalEnhancementUnit.MAX_UPGRADE_COUNT
            || this.mFertilizerUnitCount > BlockFertilizerUnit.MAX_UPGRADE_COUNT
            || this.mAdvancedHarvestingUnitCount > BlockAdvancedHarvestingUnit.MAX_UPGRADE_COUNT
            || this.mOverclockedGrowthAccelerationUnitCount > BlockOverclockedGrowthAccelerationUnit.MAX_UPGRADE_COUNT
            || (this.mGrowthAccelerationUnitCount > 0 && this.mOverclockedGrowthAccelerationUnitCount > 0)) {
            return false;
        }

        // validate hatches depending on the presence of the oc upgrade.
        if (this.mOverclockedGrowthAccelerationUnitCount > 0) {
            for (MTEHatch hatch : this.mExoticEnergyHatches) {
                if (hatch.getConnectionType() == MTEHatch.ConnectionType.LASER) {
                    return false;
                }
                // validate the tier while we're at it
                if (this.mGlassTier < VoltageIndex.UMV && hatch.mTier > this.mGlassTier) {
                    return false;
                }
            }
        } else if (this.mExoticEnergyHatches.size() != 0) {
            return false;
        }

        // validate normal energy hatch tiers
        for (MTEHatch hatch : this.mEnergyHatches) {
            // probably superfluous but eh. it's not like this will be the perf bottleneck of this machine
            if (hatch.getConnectionType() == MTEHatch.ConnectionType.LASER) {
                return false;
            }
            if (this.mGlassTier < VoltageIndex.UMV && hatch.mTier > this.mGlassTier) {
                return false;
            }
        }

        // calculate power usage
        // base eu/t should be based on the seedbed/upgrade tier.
        long basePower = GTValues.VP[this.mUpgradeTier], powerUsage = basePower;
        if (this.mEnvironmentalEnhancementUnitCount > 0) {
            powerUsage += basePower * BlockEnvironmentalEnhancementUnit.BASE_POWER_INCREASE
                * this.mEnvironmentalEnhancementUnitCount;
        }
        if (this.mGrowthAccelerationUnitCount > 0) {
            powerUsage += basePower * BlockGrowthAccelerationUnit.BASE_POWER_INCREASE
                * this.mGrowthAccelerationUnitCount;
        }
        if (this.mFertilizerUnitCount > 0) {
            powerUsage += basePower * BlockFertilizerUnit.BASE_POWER_INCREASE * this.mFertilizerUnitCount;
        }
        if (this.mAdvancedHarvestingUnitCount > 0) {
            powerUsage += basePower * BlockAdvancedHarvestingUnit.BASE_POWER_INCREASE
                * this.mAdvancedHarvestingUnitCount;
        }

        if (this.mOverclockedGrowthAccelerationUnitCount > 0) {
            OverclockCalculator calculator = new OverclockCalculator().setRecipeEUt(powerUsage)
                .setEUt(this.getMaxInputEu())
                .setDuration(Integer.MAX_VALUE)
                .calculate();
            this.mExpectedOCs = calculator.getPerformedOverclocks();
            this.mExpectedEUt = calculator.getConsumption();
        } else {
            this.mExpectedOCs = 0;
            this.mExpectedEUt = powerUsage;
        }

        this.mSeedCapacity = BlockSeedBed.getCapacity(this.mUpgradeTier);

        return true;
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection sideDirection,
        ForgeDirection facingDirection, int colorIndex, boolean active, boolean redstoneLevel) {
        ITexture casingTexture = Textures.BlockIcons.casingTexturePages[Constants.GT_CASING_PAGE][0];
        if (sideDirection == facingDirection) {
            if (active) return new ITexture[] { casingTexture, TextureFactory.builder()
                .addIcon(OVERLAY_FRONT_ASSEMBLY_LINE_ACTIVE)
                .extFacing()
                .build(),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_ASSEMBLY_LINE_ACTIVE_GLOW)
                    .extFacing()
                    .glow()
                    .build() };
            return new ITexture[] { casingTexture, TextureFactory.builder()
                .addIcon(OVERLAY_FRONT_ASSEMBLY_LINE)
                .extFacing()
                .build(),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_ASSEMBLY_LINE_GLOW)
                    .extFacing()
                    .glow()
                    .build() };
        }
        return new ITexture[] { casingTexture };
    }
    // endregion structure

    // region ctor
    public MTEIndustrialFarm(int aID) {
        super(
            aID,
            "multimachine.industrialfarm",
            StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.name"));
        this.mInvWrapper = new CombinedInvWrapper(this.mIFStackHandler, this.inventoryHandler);
    }

    public MTEIndustrialFarm(String aName) {
        super(aName);
        this.mInvWrapper = new CombinedInvWrapper(this.mIFStackHandler, this.inventoryHandler);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
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
            .addInfo(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.2"));

        String hatchHint = StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.structure.hatch");
        tt.beginVariableStructureBlock(5, 5, 4, 4, 2 + MIN_SLICES, 2 + MAX_SLICES, false)
            .addGlassEnergyLimitInfo()
            .addInfo(
                EnumChatFormatting.GREEN
                    + StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.MBTT.multiAmpsWithUpgrade")
                    + EnumChatFormatting.RESET)
            .addCasingInfoRange(
                StatCollector.translateToLocal(Reference.MOD_ID + ".casings1.0.name"),
                8 * 2 + MIN_SLICES * 2,
                8 * 2 + MAX_SLICES * 2,
                false)
            .addCasingInfoRange(
                GTOreDictUnificator.get(OrePrefixes.frameGt, Materials.Wood, 1)
                    .getDisplayName(),
                0,
                MAX_SLICES,
                false)
            .addCasingInfoRange(
                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.structure.seedBed"),
                3 * MIN_SLICES,
                3 * MAX_SLICES,
                true)
            .addOtherStructurePart(
                translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.structure.upgrades.name"),
                translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.structure.upgrades.info"))
            .addEnergyHatch(hatchHint, 1)
            .addInputBus(hatchHint, 1)
            .addInputHatch(hatchHint, 1)
            .addMaintenanceHatch(hatchHint, 1)
            .addOutputBus(hatchHint, 1)
            .addSubChannelUsage(GTStructureChannels.BOROGLASS)
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
                formatNumber(this.mUpgradeTier)));
        // capacity
        ret.add(
            StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.industrialFarm.scanner.1",
                formatNumber(this.mSeedCapacity)));
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
        // Water Usage per Cycle
        int waterUsage = this.getConsumablePotencyNeededPerCycle();
        ret.add(
            StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.industrialFarm.scanner.4",
                formatNumber(waterUsage) + getFluidUnit(),
                formatNumber(CYCLE_DURATION)));
        // Fertilizer Usage per Cycle
        int fertUsage = this.mFertilizerUnitCount > 0 ? waterUsage : 0;
        ret.add(
            StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.industrialFarm.scanner.5",
                formatNumber(fertUsage) + getFluidUnit(),
                formatNumber(CYCLE_DURATION)));
        // nutrient score
        ISeedData tSeedData = CropsNHUtils.getAnalyzedSeedData(this.getSeedStack());
        if (tSeedData != null) {
            ret.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.industrialFarm.scanner.6",
                    formatNumber(this.getNutrientScore(tSeedData)),
                    formatNumber(TileEntityCropSticks.MAX_NUTRIENT_SCORE)));
        }
        return ret.toArray(new String[0]);
    }

    // endregion tooltips

    // region inv stuff

    @Override
    public IItemHandlerModifiable getInventoryHandler() {
        return this.mInvWrapper;
    }

    public ItemStack getSeedStack() {
        return this.mIFStackHandler.getStackInSlot(SLOT_SEED);
    }

    public void setSeedStack(ItemStack aStack) {
        this.mIFStackHandler.setStackInSlot(SLOT_SEED, aStack);
    }

    public boolean canInsertIntoSeedSlot(ItemStack aStack) {
        return this.mIFStackHandler.isItemValid(SLOT_SEED, aStack);
    }

    public ItemStack getBlockUnderStack() {
        return this.mIFStackHandler.getStackInSlot(SLOT_BLOCK_UNDER);
    }

    public void setBlockUnderStack(ItemStack aStack) {
        this.mIFStackHandler.setStackInSlot(SLOT_BLOCK_UNDER, aStack);
    }

    public ItemStack getEnvironmentalModuleStack(int aSlot) {
        return this.mIFStackHandler.getStackInSlot(SLOT_ENV_CARD_START + aSlot);
    }

    // endregion inv stuff

    // region NBT
    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        if (aNBT.hasKey(NBT_INVENTORY_TAG, Data.NBTType._object)) {
            this.mIFStackHandler.deserializeNBT(aNBT.getCompoundTag(NBT_INVENTORY_TAG));
        }
        this.mOutputTracker = new IFDropTable(aNBT, NBT_OUTPUT_TRACKER);
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        aNBT.setTag(NBT_INVENTORY_TAG, this.mIFStackHandler.serializeNBT());
        aNBT.setTag(NBT_OUTPUT_TRACKER, this.mOutputTracker.save());
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
    public void setMachineMode(int aIndex) {
        switch (aIndex) {
            case MODE_INPUT, MODE_FARM, MODE_OUTPUT -> this.machineMode = aIndex;
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
    /** Can't insert a seed because the existing block under didn't match the new seed. */
    @Nonnull
    public static final CheckRecipeResult CHECK_RECIPE_RESULT_BLOCK_UNDER_MISMATCH_INPUT = SimpleCheckRecipeResult
        .ofFailure(Reference.MOD_ID + ".industrialFarm.blockUnderMismatch.input");
    /** Can't insert a seed because the existing block under didn't match the new seed. */
    @Nonnull
    public static final CheckRecipeResult CHECK_RECIPE_RESULT_BLOCK_UNDER_MISMATCH_FARM = SimpleCheckRecipeResult
        .ofFailure(Reference.MOD_ID + ".industrialFarm.blockUnderMismatch.farm");
    /** Can't insert a seed because the required under-block wasn't found */
    @Nonnull
    public static final CheckRecipeResult CHECK_RECIPE_RESULT_BLOCK_UNDER_NOT_FOUND = SimpleCheckRecipeResult
        .ofFailure(Reference.MOD_ID + ".industrialFarm.blockUnderNotFound");
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

    @Override
    public @Nonnull CheckRecipeResult checkProcessing() {
        switch (this.machineMode) {
            case MODE_INPUT:
                this.mOutputTracker.clear();
                return this.checkProcessingInputMode();
            case MODE_FARM:
                CheckRecipeResult result = this.checkProcessingFarmMode();
                if (!result.wasSuccessful()) {
                    this.mOutputTracker.clear();
                }
                return result;
            case MODE_OUTPUT:
                this.mOutputTracker.clear();
                return this.checkProcessingOutputMode();
            default:
                return CheckRecipeResultRegistry.NO_RECIPE;
        }
    }

    // region input mode
    private CheckRecipeResult checkProcessingInputMode() {
        if (this.mSeedCapacity <= 0) return CheckRecipeResultRegistry.NONE;
        ItemStack tExisting = this.getSeedStack();
        List<ItemStack> tInputs = this.getStoredInputs();

        // the path is going to differ if the multi already contains seeds.
        CheckRecipeResult tResult = CropsNHUtils.isStackValid(tExisting) ? tryAddSeedsToExisting(tInputs, tExisting)
            : tryAddNewSeeds(tInputs);

        if (tResult.wasSuccessful()) {
            this.mMaxProgresstime = 5;
            this.lEUt = 0;
            this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
            this.mEfficiencyIncrease = 10000;
            return tResult;
        }
        return tResult;
    }

    private CheckRecipeResult tryAddSeedsToExisting(List<ItemStack> aInputs, ItemStack aExisting) {
        if (aInputs.isEmpty()) return CheckRecipeResultRegistry.NO_RECIPE;
        // if it's full abort early
        if (aExisting.stackSize >= this.mSeedCapacity) return CHECK_RECIPE_RESULT_SEEDS_FULL;
        // Find how many matching seeds are in the inputs.
        int tAvailableSeeds = consumeMatchingStacks(aExisting, aInputs, 0, this.mSeedCapacity, true);
        if (tAvailableSeeds == 0) return CheckRecipeResultRegistry.NO_RECIPE;
        int tInsertionMax = aExisting.stackSize + tAvailableSeeds;
        // if we have an under-block check how many to consume
        ItemStack tBlockUnder = this.getBlockUnderStack();
        tBlockUnder = CropsNHUtils.isStackValid(tBlockUnder) ? tBlockUnder : null;
        if (tBlockUnder != null && tInsertionMax - tBlockUnder.stackSize > 0) {
            int tBlockUndersToConsume = consumeMatchingStacks(tBlockUnder, aInputs, 0, tInsertionMax, true);
            if (tBlockUndersToConsume <= 0) {
                return CHECK_RECIPE_RESULT_BLOCK_UNDER_NOT_FOUND;
            }
            tInsertionMax = Math.min(tInsertionMax, tBlockUnder.stackSize + tBlockUndersToConsume);
        }

        // consume the items, and the relevant stacks should all get updated automatically.
        consumeMatchingStacks(aExisting, aInputs, 0, tInsertionMax, false);
        if (tBlockUnder != null) {
            consumeMatchingStacks(tBlockUnder, aInputs, 0, tInsertionMax, false);
        }
        return CheckRecipeResultRegistry.SUCCESSFUL;
    }

    private CheckRecipeResult tryAddNewSeeds(List<ItemStack> aInputs) {
        int tSeedIndex = 0;
        int tBlockUnderIndex = 0;
        ItemStack tNewSeedStack = null;
        ItemStack tNewBlockUnderStack = null;
        for (; tSeedIndex < aInputs.size(); tSeedIndex++) {
            // the seed must be a valid item and an analyzed seed.
            final ItemStack tSeedCandidate = aInputs.get(tSeedIndex);
            // check if it's an analyzed crop
            final ISeedData tSeedData = CropsNHUtils.getAnalyzedSeedData(tSeedCandidate);
            if (tSeedData == null) continue;
            if (tSeedData.getCrop()
                .getMinSeedBedTier() > this.mUpgradeTier) return CHECK_RECIPE_RESULT_SEED_BED_TIER_TOO_LOW;
            // check if the crop can grow
            if (getGrowthSpeedUnscaled(tSeedData) <= 0) return CHECK_RECIPE_RESULT_CANNOT_GROW;
            // if it has a block under try to consume it
            reqs: for (IGrowthRequirement tRequirement : tSeedData.getCrop()
                .getGrowthRequirements()) {
                if (tRequirement instanceof BlockUnderRequirement tBlockUnderReq) {
                    ItemStack tExistingBlockUnderStack = this.getBlockUnderStack();
                    if (CropsNHUtils.isStackValid(tExistingBlockUnderStack)) {
                        // check if the existing block under matches the new crop.
                        if (!tBlockUnderReq.isValidBlockUnder(tExistingBlockUnderStack)) {
                            return CHECK_RECIPE_RESULT_BLOCK_UNDER_MISMATCH_INPUT;
                        }
                        tNewBlockUnderStack = tExistingBlockUnderStack;
                    } else {
                        for (tBlockUnderIndex = 0; tBlockUnderIndex < aInputs.size(); tBlockUnderIndex++) {
                            ItemStack tBlockUnderCandidate = aInputs.get(tBlockUnderIndex);
                            // abort early if it's the seed candidate or the not a valid under-block.
                            if (tBlockUnderIndex == tSeedIndex
                                || !tBlockUnderReq.isValidBlockUnder(tBlockUnderCandidate)) continue;
                            // else save the stack for later.
                            tNewBlockUnderStack = tBlockUnderCandidate.copy();
                            tNewBlockUnderStack.stackSize = 0;
                            break reqs;
                        }
                    }

                    // under block not found, assume incorrect input instead of checking for other seeds.
                    return CHECK_RECIPE_RESULT_BLOCK_UNDER_NOT_FOUND;
                }
            }
            // put the seed searcher back by 1 slot and save the new search targets.
            tNewSeedStack = tSeedCandidate.copy();
            tNewSeedStack.stackSize = 0;
            break;
        }
        // if nothing is in the list, nothing was found
        if (tNewSeedStack == null) return CheckRecipeResultRegistry.NO_RECIPE;

        // find the maximum amount of items we can consume.
        if (tNewBlockUnderStack == null) {
            // just consume up to the capacity, no need for any other complex checks.
            consumeMatchingStacks(tNewSeedStack, aInputs, tSeedIndex, this.mSeedCapacity, false);
        } else {
            // check how many under-blocks will be in the machine if we try to consume everything.
            int tAdditionalBlockUnderAvailable = consumeMatchingStacks(
                tNewBlockUnderStack,
                aInputs,
                tBlockUnderIndex,
                this.mSeedCapacity,
                true);
            int tBlockUnderInMachineIfAllConsumed = tNewBlockUnderStack.stackSize + tAdditionalBlockUnderAvailable;
            // Check how many seeds are available
            int tAvailableSeeds = consumeMatchingStacks(
                tNewSeedStack,
                aInputs,
                tSeedIndex,
                tBlockUnderInMachineIfAllConsumed,
                true);
            // Update the max under-block consumption based on how many seeds we're inserting in case there were some
            // blocks already in the machine.
            int tMaxAmountAfterIngest = Math.min(tAvailableSeeds, tBlockUnderInMachineIfAllConsumed);
            // consume
            consumeMatchingStacks(tNewSeedStack, aInputs, tSeedIndex, tMaxAmountAfterIngest, false);
            consumeMatchingStacks(tNewBlockUnderStack, aInputs, tBlockUnderIndex, tMaxAmountAfterIngest, false);
        }

        // update the inventory
        this.setSeedStack(tNewSeedStack);
        if (tNewBlockUnderStack != null) {
            this.setBlockUnderStack(tNewBlockUnderStack);
        }

        return CheckRecipeResultRegistry.SUCCESSFUL;
    }

    /**
     * Attempts to transfer all matching items form the list until the existing stack reaches a given item limit.
     *
     * @param aExisting    The item to look for and to add the amount to.
     * @param aProvider    A list of items to consume from.
     * @param startAt      Where to start reading the tracker from (optimisation)
     * @param aMaxCapacity The maximum of number of items that can be stored.
     * @param simulate     Set to true to prevent the transfer and validate the consumption.
     * @return The amount of items that were consumed.
     */
    private static int consumeMatchingStacks(@Nonnull ItemStack aExisting, @Nonnull List<ItemStack> aProvider,
        int startAt, int aMaxCapacity, boolean simulate) {
        // if the existing stack is already reached max, abort early.
        if (aExisting.stackSize >= aMaxCapacity) return 0;
        // else search for the matching stacks
        int tNewStackSize = aExisting.stackSize;
        for (int i = startAt; i < aProvider.size() && tNewStackSize < aMaxCapacity; i++) {
            ItemStack tStack = aProvider.get(i);
            // check if item is valid
            if (!isItemStackValidAndCanItStackWithExisting(tStack, aExisting)) continue;
            int toConsume = Math.min(aMaxCapacity - tNewStackSize, tStack.stackSize);
            tNewStackSize += toConsume;
            if (!simulate) {
                // IIRC setting the stack to null is bad, if it's zero the multi will deal with it correctly.
                tStack.stackSize -= toConsume;
            }
        }
        int consumed = tNewStackSize - aExisting.stackSize;
        if (!simulate) {
            aExisting.stackSize = tNewStackSize;
        }
        return consumed;
    }

    /**
     * Checks if an item is valid, has a stack size greater than 0, and if it can stack with another existing stack
     *
     * @param aItemStack The item stack to validate
     * @param aExisting  The existing item stack
     * @return True if all checks pass
     */
    private static boolean isItemStackValidAndCanItStackWithExisting(ItemStack aItemStack,
        @Nonnull ItemStack aExisting) {
        return CropsNHUtils.isStackValid(aItemStack) && GTUtility.areStacksEqual(aItemStack, aExisting, false);
    }
    // endregion input mode

    /**
     * @implNote The output mode should never void anything,
     *           this is mainly due to the fact that you can't
     *           extract seeds with under-blocks without output mode
     *           due to limitations in MUI2.
     */
    private CheckRecipeResult checkProcessingOutputMode() {
        ItemStack seedStack = this.getSeedStack();
        ItemStack blockUnderStack = this.getBlockUnderStack();
        List<ItemStack> simulated = new ArrayList<>(2);
        // add seed if present
        if (CropsNHUtils.isStackValid(seedStack)) {
            simulated.add(CropsNHUtils.copyStackWithSize(seedStack, 1));
        } else {
            seedStack = null;
        }
        // add block under if present
        if (CropsNHUtils.isStackValid(blockUnderStack)) {
            simulated.add(CropsNHUtils.copyStackWithSize(blockUnderStack, 1));
        } else {
            blockUnderStack = null;
        }
        // check if anything remains
        if (simulated.isEmpty()) {
            return CheckRecipeResultRegistry.NO_RECIPE;
        }
        // calc max parallel based on min stack size.
        int maxParallels = (seedStack != null && blockUnderStack != null)
            ? Math.min(seedStack.stackSize, blockUnderStack.stackSize)
            : (seedStack != null ? seedStack.stackSize : blockUnderStack.stackSize);
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
        if (blockUnderStack != null) {
            blockUnderStack.stackSize -= maxParallels;
            this.setBlockUnderStack(blockUnderStack.stackSize <= 0 ? null : blockUnderStack);
        }
        // eject seeds and blocks
        ejectionHelper.commit();

        // notify success
        this.mMaxProgresstime = 5;
        this.lEUt = 0;
        this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
        this.mEfficiencyIncrease = 10000;
        return CheckRecipeResultRegistry.SUCCESSFUL;
    }

    // region farm mode

    private int getConsumablePotencyNeededPerCycle() {
        return (int) Math.ceil(
            this.mSeedCapacity * CYCLE_TICK_RATE_SCALAR
                * (this.mExpectedOCs <= 63 ? 1L << this.mExpectedOCs : GTUtility.powInt(2.0d, this.mExpectedOCs)));
    }

    private int getAmountToConsumeBasedOnPotency(int aMissingPotency, int aInputPotency, int aInputAmount) {
        if (aMissingPotency <= 0 || aInputPotency <= 0 || aInputAmount <= 0) return 0;
        // Prefer over-consuming in case something with a stupid high potency gets introduced.
        int tMaxConsume = aMissingPotency / aInputPotency + ((aMissingPotency % aInputPotency) > 0 ? 1 : 0);
        return (int) Math.min(tMaxConsume, aInputAmount);
    }

    private CheckRecipeResult checkProcessingFarmMode() {
        // state checks
        if (this.mUpgradeTier < MIN_CASING_TIER) return CheckRecipeResultRegistry.INTERNAL_ERROR;

        // get seed
        ISeedData tSeedData = CropsNHUtils.getAnalyzedSeedData(this.getSeedStack());
        if (tSeedData == null) return CheckRecipeResultRegistry.NO_RECIPE;

        // check if the seed can grow in the machine.
        if (tSeedData.getStack().stackSize > this.mSeedCapacity) return CHECK_RECIPE_RESULT_SEED_OVERFLOW;
        if (tSeedData.getCrop()
            .getMinSeedBedTier() > this.mUpgradeTier) return CHECK_RECIPE_RESULT_SEED_BED_TIER_TOO_LOW;

        // check the machine growth requirements
        ItemStack[] tGrowthCatalysts;
        ItemStack tUnderBlockStack = this.getBlockUnderStack();
        if (CropsNHUtils.isStackValid(tUnderBlockStack)) {
            tGrowthCatalysts = new ItemStack[] { tUnderBlockStack };
        } else {
            tGrowthCatalysts = new ItemStack[0];
        }
        for (IGrowthRequirement req : tSeedData.getCrop()
            .getGrowthRequirements()) {
            if (req instanceof IMachineGrowthRequirement tMachineGrowthReq) {
                if (!tMachineGrowthReq.canGrow(tSeedData, this.getBaseMetaTileEntity(), tGrowthCatalysts)) {
                    // custom logic for this one to display a custom message in case some update changes something.
                    if (req instanceof BlockUnderRequirement) {
                        return CHECK_RECIPE_RESULT_BLOCK_UNDER_MISMATCH_FARM;
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
        List<Pair<FluidStack, Integer>> tFluidsToConsume = new ArrayList<>();
        int tWaterPotencyMissing = this.getConsumablePotencyNeededPerCycle();
        int tFertilizerPotencyMissing = this.mFertilizerUnitCount > 0 ? tWaterPotencyMissing : 0;
        for (FluidStack tFluidStack : this.getStoredFluids()) {
            if (CropsNHUtils.isStackInvalid(tFluidStack)) continue;
            Fluid tFluid = tFluidStack.getFluid();
            int tRemaining = tFluidStack.amount;
            int tPotency;
            // consume water if needed
            if (tWaterPotencyMissing > 0 && (tPotency = HydrationRegistry.instance.getPotency(tFluid)) > 0) {
                int tAmountToConsume = getAmountToConsumeBasedOnPotency(tWaterPotencyMissing, tPotency, tRemaining);
                tRemaining -= tAmountToConsume;
                tWaterPotencyMissing -= tAmountToConsume * tPotency;
            }
            // consume fertilizer if needed
            if (tFertilizerPotencyMissing > 0 && tRemaining > 0
                && (tPotency = FertilizerRegistry.instance.getPotency(tFluid)) > 0) {
                int tAmountToConsume = getAmountToConsumeBasedOnPotency(
                    tFertilizerPotencyMissing,
                    tPotency,
                    tRemaining);
                tRemaining -= tAmountToConsume;
                tFertilizerPotencyMissing -= tAmountToConsume * tPotency;
            }
            // if we consumed something add it to the list for later consumption.
            if (tRemaining > 0L) {
                tFluidsToConsume.add(Pair.of(tFluidStack, tRemaining));
                if (tFertilizerPotencyMissing <= 0 && tWaterPotencyMissing <= 0) break;
            }
        }
        if (tWaterPotencyMissing > 0 || tFertilizerPotencyMissing > 0) return CheckRecipeResultRegistry.NO_RECIPE;

        // calc drops
        IFDropTable tDropProgess = getDropsPerCycle(tSeedData);
        if (tDropProgess == null) return CHECK_RECIPE_RESULT_CANNOT_GROW;
        tDropProgess.addTo(this.mOutputTracker, tSeedData.getStack().stackSize);
        // check if output void protection is enabled
        if (this.voidingMode.protectItem) {
            ItemEjectionHelper tHelper = new ItemEjectionHelper(this.getOutputBusses(), true);
            ItemStack[] tDrops = this.mOutputTracker.getDrops(true);
            if (tDrops.length != 0 && tHelper.ejectItems(Arrays.asList(tDrops), 1) <= 0) {
                // remove the added items
                tDropProgess.addTo(this.mOutputTracker, -tSeedData.getStack().stackSize);
                // return output full.
                return CheckRecipeResultRegistry.ITEM_OUTPUT_FULL;
            }
        }
        // consume fluids
        for (Pair<FluidStack, Integer> tFluidToConsume : tFluidsToConsume) {
            tFluidToConsume.getLeft().amount = tFluidToConsume.getRight();
        }
        // output if everything is safe
        this.mOutputItems = this.mOutputTracker.getDrops(false);

        // calc power cost
        this.lEUt = -this.mExpectedEUt;
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
     * @param aCrop The crop to calculate the growth speed of.
     * @return The number of growth points per crop stick growth cycle, if the value is <= 0 the crop would get sick or
     *         cannot grow.
     */
    public int getNutrientScore(ISeedData aCrop) {
        if (aCrop == null) return 0;
        // that thing really shouldn't be null when we are calculating this.
        IGregTechTileEntity tBaseTE = this.getBaseMetaTileEntity();
        if (tBaseTE == null) return 0;

        // check number of liked biome tags in current biome.
        BiomeGenBase tBiome = tBaseTE.getBiome();
        Set<BiomeDictionary.Type> tBiomeTags = new HashSet<>(Arrays.asList(BiomeDictionary.getTypesForBiome(tBiome)));
        // add extra tags from biome cards
        for (int i = 0; i < this.mEnvironmentalEnhancementUnitCount; i++) {
            ItemStack tStack = this.getEnvironmentalModuleStack(i);
            if (CropsNHUtils.isStackInvalid(tStack) || !(tStack.getItem() instanceof ItemEnvironmentalModule)) continue;
            tBiomeTags.add(ItemEnvironmentalModule.getBiomeTag(CropsNHUtils.getItemMeta(tStack)));
        }
        int tLikedBiomes = (int) aCrop.getCrop()
            .getLikedBiomeTags()
            .stream()
            .filter(tBiomeTags::contains)
            .count();
        // calc fertilizer storage to simulate
        int tFertilizerStorage = this.mFertilizerUnitCount <= 0
            ? SIMULATED_FERTILIZER_STORAGE_WHEN_FERTILIZER_UNIT_MISSING
            : SIMULATED_FERTILIZER_STORAGE_WHEN_FERTILIZER_UNIT_INSTALLED;
        // calc available nutrient points for growth speed calculation
        return TileEntityCropSticks.getNutrientsPerCycle(
            tLikedBiomes,
            tBiome.rainfall,
            SIMULATED_CAN_SEE_SKY,
            SIMULATED_WATER_STORAGE,
            tFertilizerStorage);
    }

    /**
     * Calculates the number of growth points a crop will gain each growth tick if it was on a crop stick.
     *
     * @param aCrop The crop to calculate the growth speed of.
     * @return The number of growth points per crop stick growth cycle, if the value is <= 0 the crop would get sick or
     *         cannot grow.
     */
    public int getGrowthSpeedUnscaled(ISeedData aCrop) {
        // calculate the base growth speed
        return TileEntityCropSticks.getGrowthRate(
            this.getNutrientScore(aCrop),
            aCrop.getCrop()
                .getTier(),
            aCrop.getStats()
                .getGrowth());
    }

    /**
     * @return The growth speed multiplier based on the installed upgrades.
     */
    private double getGrowthSpeedMultiplier() {
        double multiplier = 1.0d;
        // apply additive bonuses
        multiplier += this.mGrowthAccelerationUnitCount * BlockGrowthAccelerationUnit.GROWTH_SPEED_BONUS;
        // apply multiplicative bonuses
        multiplier *= 1.0d + (this.mFertilizerUnitCount * BlockFertilizerUnit.GROWTH_SPEED_MULTIPLIER);
        // apply overclocks
        multiplier *= this.mExpectedOCs <= 63 ? 1L << this.mExpectedOCs : GTUtility.powInt(2.0d, this.mExpectedOCs);
        return multiplier;
    }

    /**
     * Calculates the amount of progress towards maturity that a crop will gain each IF cycle.
     *
     * @param aCrop The crop to simulate.
     * @return The percentage of the crop's overall growth cycle completed each cycle, can be above 100%.
     */
    public double getGrowthProgressPerCycle(ISeedData aCrop) {
        // calc unscaled growth speed of crop.
        int tUnscaledGrowthSpeed = this.getGrowthSpeedUnscaled(aCrop);
        if (tUnscaledGrowthSpeed <= 0) return -1;
        // calculate growth points per cycle
        double tGrowthPerCycle = (((double) tUnscaledGrowthSpeed) / TileEntityCropSticks.TICK_RATE) * CYCLE_DURATION;
        // apply growth speed multipliers
        tGrowthPerCycle *= this.getGrowthSpeedMultiplier();
        if (tGrowthPerCycle <= 0) return -1;
        // calculate percentage grown each tick.
        return Math.min(
            1.0d,
            1.0d / Math.ceil(
                aCrop.getCrop()
                    .getGrowthDuration() / tGrowthPerCycle));
    }

    /**
     * @return The growth speed multiplier based on the installed upgrades.
     */
    private double getHarvestRoundMultiplier() {
        double multiplier = 1.0d;
        // additive bonuses
        // Yes it's intended to start at 40% more harvests (due to min tier.
        // This is to make the multi inherently better than the equivalent crop manager)
        multiplier += BlockSeedBed.getHarvestRoundBonus(this.mUpgradeTier);
        multiplier += this.mFertilizerUnitCount * BlockFertilizerUnit.HARVEST_ROUND_BONUS;
        // multiplicative bonuses
        multiplier *= 1.0d + (this.mAdvancedHarvestingUnitCount * BlockAdvancedHarvestingUnit.HARVEST_ROUND_MULTIPLIER);
        return multiplier;
    }

    /**
     * Calculates the drops that should be outputted each cycle.
     *
     * @param aCrop The crop to simulate
     * @return The drop table or null if it can't grow.
     */
    public @Nullable IFDropTable getDropsPerCycle(ISeedData aCrop) {
        // calculate how much progress is done each cycle
        double tProgressPerCycle = getGrowthProgressPerCycle(aCrop);
        if (tProgressPerCycle <= 0) return null;

        // calc avg drop stack size increase
        double avgDropIncrease = TileEntityCropSticks.getAvgDropCountIncrease(
            aCrop.getStats()
                .getGain());

        // calc average number of created drops per harvest
        double avgDropCount = TileEntityCropSticks.getAvgDropRounds(
            aCrop.getCrop(),
            aCrop.getStats()
                .getGain());
        avgDropCount *= this.getHarvestRoundMultiplier();

        // create drop table
        IFDropTable drops = new IFDropTable();
        for (Map.Entry<ItemStack, Integer> entry : aCrop.getCrop()
            .getDropTable()
            .entrySet()) {
            ItemStack stack = entry.getKey();
            double chance = entry.getValue() / 10_000d;
            // scale by chance and progress completed each cycle.
            double unscaled = (stack.stackSize + avgDropIncrease) * chance * avgDropCount;
            drops.addDrop(stack, unscaled * tProgressPerCycle);
        }

        return drops;
    }

    // endregion simulations
}
