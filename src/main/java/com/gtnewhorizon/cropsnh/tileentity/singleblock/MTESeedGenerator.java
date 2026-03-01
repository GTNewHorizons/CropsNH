package com.gtnewhorizon.cropsnh.tileentity.singleblock;

import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_BOTTOM_SCANNER;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_BOTTOM_SCANNER_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_BOTTOM_SCANNER_ACTIVE_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_BOTTOM_SCANNER_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_SCANNER;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_SCANNER_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_SCANNER_ACTIVE_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_SCANNER_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_SIDE_SCANNER;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_SIDE_SCANNER_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_SIDE_SCANNER_ACTIVE_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_SIDE_SCANNER_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_TOP_SCANNER;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_TOP_SCANNER_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_TOP_SCANNER_ACTIVE_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_TOP_SCANNER_GLOW;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.init.CropsNHFluids;
import com.gtnewhorizon.cropsnh.init.CropsNHUITextures;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizons.modularui.api.drawable.IDrawable;
import com.gtnewhorizons.modularui.api.math.Pos2d;
import com.gtnewhorizons.modularui.common.widget.SlotWidget;

import gregtech.api.enums.SoundResource;
import gregtech.api.enums.TierEU;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.MTEBasicMachine;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GTUtility;

public class MTESeedGenerator extends MTEBasicMachine {

    private static final int AMPERAGE = 1;
    private static final int INPUT_SLOT_COUNT = 2;
    private static final int OUTPUT_SLOT_COUNT = 1;
    public static final ConcurrentHashMap<Fluid, Integer> ALLOWED_LIQUID_FERTILIZER = new ConcurrentHashMap<>();

    public static void init() {
        // allowed liquid fertilizer
        ALLOWED_LIQUID_FERTILIZER.putIfAbsent(CropsNHFluids.enrichedFertilizer, 100);
    }

    public MTESeedGenerator(int aID, int aTier, String aNameRegional) {
        super(
            aID,
            String.format("basicmachine.seedgenerator.tier.%02d", aTier),
            aNameRegional,
            aTier,
            AMPERAGE,
            new String[] { CropsNHUtils.getMachineTypeText("seedGenerator"), "It can duplicate seeds!",
                "Uses 100L of Enriched Fertiliser per stat point on the seed." },
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
                TextureFactory.of(OVERLAY_FRONT_SCANNER_ACTIVE),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_SCANNER_ACTIVE_GLOW)
                    .glow()
                    .build()),
            TextureFactory.of(
                TextureFactory.of(OVERLAY_FRONT_SCANNER),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_SCANNER_GLOW)
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

    public MTESeedGenerator(String mName, byte mTier, String[] mDescriptionArray, ITexture[][][] mTextures) {
        super(mName, mTier, AMPERAGE, mDescriptionArray, mTextures, INPUT_SLOT_COUNT, OUTPUT_SLOT_COUNT);
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
        int drainedPerStat = ALLOWED_LIQUID_FERTILIZER.getOrDefault(this.mFluid.getFluid(), -1);
        if (drainedPerStat < 0) {
            return DID_NOT_FIND_RECIPE;
        }

        // try to identify a usable seed
        ItemStack tSeedStack = null;
        ISeedData tSeedData = null;
        int[] tItemsToConsume = new int[this.mInputSlotCount];
        Arrays.fill(tItemsToConsume, 0);
        int tFluidToConsume = 0;
        outer: for (int i = 0; i < this.mInputSlotCount; i++) {
            ItemStack tStack = this.getInputAt(i);
            tSeedData = CropsNHUtils.getAnalyzedSeedData(tStack);
            if (tSeedData != null) {
                // check if we have enough fluid to duplicate the seed.
                tFluidToConsume = drainedPerStat * (tSeedData.getStats()
                    .getGrowth()
                    + tSeedData.getStats()
                        .getGain()
                    + tSeedData.getStats()
                        .getResistance());
                if (tFluidToConsume > this.mFluid.amount) {
                    continue;
                }
                // if a catalyst is required try to find a slot we can consume it from
                if (tSeedData.getCrop()
                    .getDuplicationCatalysts()
                    .size() > 0) {
                    for (ItemStack catalyst : tSeedData.getCrop()
                        .getDuplicationCatalysts()) {
                        int remaining = catalyst.stackSize;
                        for (int j = 0; remaining > 0 && j < this.mInputSlotCount; j++) {
                            ItemStack input = getInputAt(j);
                            if (GTUtility.areStacksEqual(catalyst, input)) {
                                tItemsToConsume[j] = Math.min(input.stackSize, remaining);
                                remaining -= tItemsToConsume[j];
                            }
                        }
                        if (remaining <= 0) {
                            // update the source stack if we find enough stuff
                            tSeedStack = tStack.copy();
                            tSeedStack.stackSize = 1;
                            break outer;
                        } else {
                            // reset the consumption tracker if we don't find what we want.
                            Arrays.fill(tItemsToConsume, 0);
                        }
                    }
                } else {
                    // if no catalyst is required we are good to proceed to start consuming
                    tSeedStack = tStack.copy();
                    tSeedStack.stackSize = 1;
                }
                break;
            }
        }

        // both should be set if we did everything right
        if (tSeedData == null || tSeedStack == null || !canOutput(tSeedStack)) {
            return DID_NOT_FIND_RECIPE;
        }

        // calculate power usage
        if (!skipOC) {
            this.calculateOverclockedNess((int) TierEU.RECIPE_LV, 20 * SECONDS);
            // In case recipe is too OP for that machine
            if (mMaxProgresstime == Integer.MAX_VALUE - 1 && mEUt == Integer.MAX_VALUE - 1)
                return FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS;
        }

        // consume inputs
        this.mFluid.amount -= tFluidToConsume;
        for (int i = 0; i < tItemsToConsume.length; i++) {
            if (tItemsToConsume[i] > 0) {
                this.getInputAt(i).stackSize -= tItemsToConsume[i];
            }
        }
        this.mOutputItems[0] = tSeedStack;

        return FOUND_AND_SUCCESSFULLY_USED_RECIPE;
    }

    @Override
    public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
        super.startSoundLoop(aIndex, aX, aY, aZ);
        if (aIndex == 1) {
            GTUtility.doSoundAtClient(SoundResource.GTCEU_LOOP_REPLICATOR, 10, 1.0F, aX, aY, aZ);
        }
    }

    @Override
    public void startProcess() {
        sendLoopStart((byte) 1);
    }

    @Override
    public boolean isFluidInputAllowed(FluidStack aFluid) {
        return super.isFluidInputAllowed(aFluid) && aFluid.getFluid() == CropsNHFluids.enrichedFertilizer;
    }

    @Override
    public int getCapacity() {
        return getCapacityForTier(mTier);
    }

    @Override
    protected SlotWidget createItemInputSlot(int index, IDrawable[] backgrounds, Pos2d pos) {
        if (index == 0) {
            return (SlotWidget) super.createItemInputSlot(index, backgrounds, pos)
                .setBackground(getGUITextureSet().getItemSlot(), CropsNHUITextures.OVERLAY_SLOT_SEED);
        } else {
            return (SlotWidget) super.createItemInputSlot(index, backgrounds, pos)
                .setBackground(getGUITextureSet().getItemSlot());
        }
    }

    @Override
    protected SlotWidget createItemOutputSlot(int index, IDrawable[] backgrounds, Pos2d pos) {
        if (index == 0) {
            return (SlotWidget) super.createItemOutputSlot(index, backgrounds, pos)
                .setBackground(getGUITextureSet().getItemSlot(), CropsNHUITextures.OVERLAY_SLOT_SEED);
        } else {
            return (SlotWidget) super.createItemOutputSlot(index, backgrounds, pos)
                .setBackground(getGUITextureSet().getItemSlot());
        }
    }
}
