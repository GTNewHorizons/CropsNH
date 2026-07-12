package com.gtnewhorizon.cropsnh.tileentity.singleblock;

import static com.gtnewhorizon.cropsnh.init.CropsNHBlockTextures.OVERLAY_FRONT_SEED_GENERATOR;
import static com.gtnewhorizon.cropsnh.init.CropsNHBlockTextures.OVERLAY_FRONT_SEED_GENERATOR_ACTIVE;
import static com.gtnewhorizon.cropsnh.init.CropsNHBlockTextures.OVERLAY_FRONT_SEED_GENERATOR_ACTIVE_GLOW;
import static com.gtnewhorizon.cropsnh.init.CropsNHBlockTextures.OVERLAY_FRONT_SEED_GENERATOR_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_BOTTOM_SCANNER;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_BOTTOM_SCANNER_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_BOTTOM_SCANNER_ACTIVE_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_BOTTOM_SCANNER_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_SIDE_SCANNER;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_SIDE_SCANNER_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_SIDE_SCANNER_ACTIVE_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_SIDE_SCANNER_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_TOP_SCANNER;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_TOP_SCANNER_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_TOP_SCANNER_ACTIVE_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_TOP_SCANNER_GLOW;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.init.CropsNHFluids;
import com.gtnewhorizon.cropsnh.init.CropsNHUITextures;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import gregtech.api.enums.SoundResource;
import gregtech.api.enums.TierEU;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.MTEBasicMachine;
import gregtech.api.recipe.BasicUIProperties;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GTUtility;
import gregtech.api.util.tooltip.TooltipHelper;
import gregtech.common.gui.modularui.singleblock.base.MTEBasicMachineBaseGui;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;

public class MTESeedGenerator extends MTEBasicMachine {

    public static final int BASE_RECIPE_DURATION = 8 * SECONDS;
    public static final int BASE_RECIPE_EUT = (int) TierEU.RECIPE_LV;
    public static final int FERTILIZER_PER_STAT = 36;
    private static final int AMPERAGE = 1;
    private static final int INPUT_SLOT_COUNT = 2;
    private static final int OUTPUT_SLOT_COUNT = 1;
    public static final Object2FloatOpenHashMap<Fluid> ALLOWED_LIQUID_FERTILIZER = new Object2FloatOpenHashMap<>();

    public static void init() {
        // allowed liquid fertilizer
        ALLOWED_LIQUID_FERTILIZER.putIfAbsent(CropsNHFluids.enrichedFertilizer, 1.0f);
    }

    private static String[] getToolTip(int tier) {
        List<String> tt = new ArrayList<>();
        tt.add(CropsNHUtils.getMachineTypeText("seedGenerator"));
        tt.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.seedGenerator.0"));
        tt.add(
            StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.seedGenerator.1",
                TooltipHelper.fluidText(FERTILIZER_PER_STAT)));
        tt.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.seedGenerator.2"));
        tt.add(
            StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.seedGenerator.3",
                TooltipHelper.fluidText(getCustomFluidCapacity(tier))));
        if (tier <= VoltageIndex.MV) {
            tt.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.seedGenerator.mv_warn"));
        }
        return tt.toArray(new String[0]);
    }

    public MTESeedGenerator(int id, int tier) {
        super(
            id,
            String.format("basicmachine.seedgenerator.tier.%02d", tier),
            StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.seedGenerator.name." + tier),
            tier,
            AMPERAGE,
            getToolTip(tier),
            INPUT_SLOT_COUNT,
            OUTPUT_SLOT_COUNT,
            TextureFactory.of(
                TextureFactory.of(OVERLAY_SIDE_SCANNER_ACTIVE),
                TextureFactory.builder()
                    .addIcon(OVERLAY_SIDE_SCANNER_ACTIVE_GLOW)
                    .glow()
                    .build()),
            TextureFactory.of(
                TextureFactory.of(OVERLAY_SIDE_SCANNER),
                TextureFactory.builder()
                    .addIcon(OVERLAY_SIDE_SCANNER_GLOW)
                    .glow()
                    .build()),
            TextureFactory.of(
                TextureFactory.of(OVERLAY_FRONT_SEED_GENERATOR_ACTIVE),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_SEED_GENERATOR_ACTIVE_GLOW)
                    .glow()
                    .build()),
            TextureFactory.of(
                TextureFactory.of(OVERLAY_FRONT_SEED_GENERATOR),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_SEED_GENERATOR_GLOW)
                    .glow()
                    .build()),
            TextureFactory.of(
                TextureFactory.of(OVERLAY_TOP_SCANNER_ACTIVE),
                TextureFactory.builder()
                    .addIcon(OVERLAY_TOP_SCANNER_ACTIVE_GLOW)
                    .glow()
                    .build()),
            TextureFactory.of(
                TextureFactory.of(OVERLAY_TOP_SCANNER),
                TextureFactory.builder()
                    .addIcon(OVERLAY_TOP_SCANNER_GLOW)
                    .glow()
                    .build()),
            TextureFactory.of(
                TextureFactory.of(OVERLAY_BOTTOM_SCANNER_ACTIVE),
                TextureFactory.builder()
                    .addIcon(OVERLAY_BOTTOM_SCANNER_ACTIVE_GLOW)
                    .glow()
                    .build()),
            TextureFactory.of(
                TextureFactory.of(OVERLAY_BOTTOM_SCANNER),
                TextureFactory.builder()
                    .addIcon(OVERLAY_BOTTOM_SCANNER_GLOW)
                    .glow()
                    .build()));
    }

    public MTESeedGenerator(String name, byte tier, String[] descriptionArray, ITexture[][][] textures) {
        super(name, tier, AMPERAGE, descriptionArray, textures, INPUT_SLOT_COUNT, OUTPUT_SLOT_COUNT);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity arg0) {
        return new MTESeedGenerator(this.mName, this.mTier, this.mDescriptionArray, this.mTextures);
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return CropsNHGTRecipeMaps.fakeSeedGeneratorRecipes;
    }

    @Override
    public int checkRecipe(boolean skipOC) {
        // ensure that the fertilizer fluid exists and that it's the right kind.
        if (this.mFluid == null || this.mFluid.getFluid() == null) {
            return DID_NOT_FIND_RECIPE;
        }

        // allow zero drain for future proofing.
        final float drainMultiplier = ALLOWED_LIQUID_FERTILIZER.getOrDefault(this.mFluid.getFluid(), -1.0f);
        if (drainMultiplier < 0) {
            return DID_NOT_FIND_RECIPE;
        }

        // try to identify a usable seed
        ItemStack seedStack = null;
        ISeedData seedData = null;
        int[] itemsToConsume = new int[this.mInputSlotCount];
        Arrays.fill(itemsToConsume, 0);
        int fluidToConsume = 0;
        outer: for (int i = 0; i < this.mInputSlotCount; i++) {
            ItemStack stackInSlot = this.getInputAt(i);
            seedData = CropsNHUtils.getAnalyzedSeedData(stackInSlot);
            if (seedData != null) {
                // don't replicate seeds that require the synthesizer in the seed generator
                if (seedData.getCrop()
                    .getCrossingThreshold() < 0.0f) {
                    continue;
                }
                ISeedStats stats = seedData.getStats();
                // check if we have enough fluid to duplicate the seed.
                fluidToConsume = getFluidAmount(
                    stats.getGrowth(),
                    stats.getGain(),
                    stats.getResistance(),
                    drainMultiplier);
                if (fluidToConsume > this.mFluid.amount) {
                    continue;
                }
                // if a catalyst is required try to find a slot we can consume it from
                if (!seedData.getCrop()
                    .getDuplicationCatalysts()
                    .isEmpty()) {
                    for (ItemStack catalyst : seedData.getCrop()
                        .getDuplicationCatalysts()) {
                        int remaining = catalyst.stackSize;
                        for (int j = 0; remaining > 0 && j < this.mInputSlotCount; j++) {
                            ItemStack input = getInputAt(j);
                            if (GTUtility.areStacksEqual(catalyst, input)) {
                                itemsToConsume[j] = Math.min(input.stackSize, remaining);
                                remaining -= itemsToConsume[j];
                            }
                        }
                        if (remaining <= 0) {
                            // update the source stack if we find enough stuff
                            seedStack = stackInSlot.copy();
                            seedStack.stackSize = 1;
                            break outer;
                        } else {
                            // reset the consumption tracker if we don't find what we want.
                            Arrays.fill(itemsToConsume, 0);
                        }
                    }
                } else {
                    // if no catalyst is required we are good to proceed to start consuming
                    seedStack = stackInSlot.copy();
                    seedStack.stackSize = 1;
                }
                break;
            }
        }

        // both should be set if we did everything right
        if (seedData == null || seedStack == null || !canOutput(seedStack)) {
            return DID_NOT_FIND_RECIPE;
        }

        // calculate power usage
        if (!skipOC) {
            this.calculateOverclockedNess(BASE_RECIPE_EUT, BASE_RECIPE_DURATION);
            // In case recipe is too OP for that machine
            if (mMaxProgresstime == Integer.MAX_VALUE - 1 && mEUt == Integer.MAX_VALUE - 1)
                return FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS;
        }

        // consume inputs
        this.mFluid.amount -= fluidToConsume;
        for (int i = 0; i < itemsToConsume.length; i++) {
            if (itemsToConsume[i] > 0) {
                this.getInputAt(i).stackSize -= itemsToConsume[i];
            }
        }
        this.mOutputItems[0] = seedStack;

        return FOUND_AND_SUCCESSFULLY_USED_RECIPE;
    }

    public static int getFluidAmount(byte gr, byte ga, byte re, float multiplier) {
        return Math.max(1, (int) ((gr + ga + re) * FERTILIZER_PER_STAT * multiplier));
    }

    @Override
    public void startSoundLoop(byte index, double x, double y, double z) {
        super.startSoundLoop(index, x, y, z);
        if (index == 1) {
            GTUtility.doSoundAtClient(SoundResource.GTCEU_LOOP_REPLICATOR, 10, 1.0F, x, y, z);
        }
    }

    @Override
    public void startProcess() {
        sendLoopStart((byte) 1);
    }

    @Override
    public boolean isFluidInputAllowed(FluidStack fluid) {
        return super.isFluidInputAllowed(fluid) && fluid.getFluid() == CropsNHFluids.enrichedFertilizer;
    }

    @Override
    public int getCapacity() {
        // iv and below gets low capacity, iv and above get more to allow for higher speed processing at higher tier.
        return getCustomFluidCapacity(this.mTier);
    }

    public static int getCustomFluidCapacity(int tier) {
        return tier < VoltageIndex.IV ? getCapacityForTier(tier) / 10 : getCapacityForTier(tier);
    }

    @Override
    protected BasicUIProperties getUIProperties() {
        return super.getUIProperties().toBuilder()
            .slotOverlaysMUI2((index, isFluid, isOutput, isSpecial) -> {
                if (isFluid || isSpecial || index != 0) return null;
                return CropsNHUITextures.OVERLAY_SLOT_SEED_STANDARD;
            })
            .build();
    }

    @Override
    public ModularPanel buildUI(PosGuiData data, PanelSyncManager syncManager, UISettings uiSettings) {
        return new MTEBasicMachineBaseGui(this, this.getUIProperties()).build(data, syncManager, uiSettings);
    }

    @Override
    protected boolean useMui2() {
        return true;
    }
}
