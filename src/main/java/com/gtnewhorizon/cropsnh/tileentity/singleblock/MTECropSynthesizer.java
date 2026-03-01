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

import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHFluids;
import com.gtnewhorizon.cropsnh.init.CropsNHUITextures;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizons.modularui.api.drawable.IDrawable;
import com.gtnewhorizons.modularui.api.math.Pos2d;
import com.gtnewhorizons.modularui.common.widget.SlotWidget;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.SoundResource;
import gregtech.api.gui.modularui.GTUITextures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.MTEBasicMachine;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GTRecipeBuilder;
import gregtech.api.util.GTUtility;
import gregtech.common.items.behaviors.BehaviourDataOrb;

public class MTECropSynthesizer extends MTEBasicMachine {

    public static final int AMPERAGE = 3;
    private static final int INPUT_SLOT_COUNT = 4;
    private static final int OUTPUT_SLOT_COUNT = 1;
    public static final ConcurrentHashMap<Fluid, Integer> ALLOWED_LIQUID_FERTILIZER = new ConcurrentHashMap<>();

    public static void init() {
        // allowed liquid fertilizer
        ALLOWED_LIQUID_FERTILIZER.putIfAbsent(CropsNHFluids.enrichedFertilizer, 100);
    }

    public MTECropSynthesizer(int aID, int aTier, String aNameRegional) {
        super(
            aID,
            String.format("basicmachine.cropsynthesizer.tier.%02d", aTier),
            aNameRegional,
            aTier,
            AMPERAGE,
            new String[] { CropsNHUtils.getMachineTypeText("cropSynthesizer"), "It can make Crops from Data Orbs",
                "It needs the crop's " + EnumChatFormatting.LIGHT_PURPLE
                    + "tier * 750L + (growth + gain + resistance) * 100L"
                    + EnumChatFormatting.RESET
                    + " of UUM per seed",
                "Takes in 3A" },
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

    public MTECropSynthesizer(String mName, byte mTier, String[] mDescriptionArray, ITexture[][][] mTextures) {
        super(mName, mTier, AMPERAGE, mDescriptionArray, mTextures, INPUT_SLOT_COUNT, OUTPUT_SLOT_COUNT);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity arg0) {
        return new MTECropSynthesizer(this.mName, this.mTier, this.mDescriptionArray, this.mTextures);
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return CropsNHGTRecipeMaps.fakeCropSynthesizerRecipeMap;
    }

    @Override
    public int checkRecipe(boolean skipOC) {
        // check if the fluid is correct.
        if (this.mFluid == null || Materials.UUMatter.mFluid != this.mFluid.getFluid()) {
            return DID_NOT_FIND_RECIPE;
        }

        // identify the new seed data.
        ICropCard cc = null;
        byte growth = -1, gain = -1, resistance = -1;
        for (int i = 0; i < INPUT_SLOT_COUNT; i++) {
            ItemStack tStack = this.getInputAt(i);
            if (!ItemList.Tool_DataOrb.isStackEqual(tStack, false, true)) continue;
            String key = BehaviourDataOrb.getDataTitle(tStack);
            switch (key) {
                case Names.DataOrb.specimen -> {
                    if (cc != null) continue;
                    cc = CropRegistry.instance.get(BehaviourDataOrb.getDataName(tStack));
                }
                case Names.DataOrb.growth -> {
                    if (growth >= 1) continue;
                    Byte stat = getOrbStat(tStack);
                    if (stat != null) growth = stat;
                }
                case Names.DataOrb.gain -> {
                    if (gain >= 1) continue;
                    Byte stat = getOrbStat(tStack);
                    if (stat != null) gain = stat;
                }
                case Names.DataOrb.resistance -> {
                    if (resistance >= 1) continue;
                    Byte stat = getOrbStat(tStack);
                    if (stat != null) resistance = stat;
                }
            }
        }

        // check we found all the orbs.
        if (cc == null || growth < 1 || gain < 1 || resistance < 1) {
            return DID_NOT_FIND_RECIPE;
        }

        // check if we got enough UUM.
        int fluidToconsume = getFluidAmount(cc, growth, gain, resistance);
        if (this.mFluid.amount < fluidToconsume) {
            return DID_NOT_FIND_RECIPE;
        }

        // check if we have space for a new seed
        ItemStack outputStack = cc.getSeedItem(new SeedStats(growth, gain, resistance, true));
        outputStack.stackSize = 1;
        if (!canOutput(outputStack)) {
            return DID_NOT_FIND_RECIPE;
        }

        // check power usage
        if (!skipOC) {
            this.calculateOverclockedNess(getRecipeEUt(cc), getRecipeDuration(cc));
            if (mMaxProgresstime == Integer.MAX_VALUE - 1 && mEUt == Integer.MAX_VALUE - 1)
                return FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS;
        }

        // consume and output
        this.mFluid.amount -= fluidToconsume;
        this.mOutputItems[0] = outputStack;

        return FOUND_AND_SUCCESSFULLY_USED_RECIPE;
    }

    public static Byte getOrbStat(ItemStack aStack) {
        try {
            return Byte.parseByte(BehaviourDataOrb.getDataName(aStack));
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    public static int getFluidAmount(ICropCard cc, byte gr, byte ga, byte re) {
        return cc.getTier() * 750 + (gr + ga + re) * 100;
    }

    public static int getRecipeEUt(ICropCard cc) {
        return (int) (GTValues.VP[MTECropGeneExtractor.getVoltageTierForCrop(cc)] * AMPERAGE);
    }

    public static int getRecipeDuration(ICropCard cc) {
        return (int) (GTRecipeBuilder.SECONDS * 20 * Math.pow(1.5, cc.getTier()));
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
        return aFluid != null && aFluid.getFluid() == Materials.UUMatter.mFluid;
    }

    @Override
    public int getCapacity() {
        return getCapacityForTier(mTier);
    }

    @Override
    protected SlotWidget createItemInputSlot(int index, IDrawable[] backgrounds, Pos2d pos) {
        return (SlotWidget) super.createItemInputSlot(index, backgrounds, pos)
            .setBackground(getGUITextureSet().getItemSlot(), GTUITextures.OVERLAY_SLOT_DATA_ORB);
    }

    @Override
    protected SlotWidget createItemOutputSlot(int index, IDrawable[] backgrounds, Pos2d pos) {
        return (SlotWidget) super.createItemOutputSlot(index, backgrounds, pos)
            .setBackground(getGUITextureSet().getItemSlot(), CropsNHUITextures.OVERLAY_SLOT_SEED);
    }
}
