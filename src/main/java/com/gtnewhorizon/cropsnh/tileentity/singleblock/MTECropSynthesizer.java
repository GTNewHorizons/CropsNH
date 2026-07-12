package com.gtnewhorizon.cropsnh.tileentity.singleblock;

import static com.gtnewhorizon.cropsnh.init.CropsNHBlockTextures.OVERLAY_FRONT_CROP_SYNTHESIZER;
import static com.gtnewhorizon.cropsnh.init.CropsNHBlockTextures.OVERLAY_FRONT_CROP_SYNTHESIZER_ACTIVE;
import static com.gtnewhorizon.cropsnh.init.CropsNHBlockTextures.OVERLAY_FRONT_CROP_SYNTHESIZER_ACTIVE_GLOW;
import static com.gtnewhorizon.cropsnh.init.CropsNHBlockTextures.OVERLAY_FRONT_CROP_SYNTHESIZER_GLOW;
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

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;

import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHUITextures;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.SoundResource;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.MTEBasicMachine;
import gregtech.api.modularui2.GTGuiTextures;
import gregtech.api.recipe.BasicUIProperties;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GTRecipeBuilder;
import gregtech.api.util.GTUtility;
import gregtech.api.util.tooltip.TooltipHelper;
import gregtech.common.gui.modularui.singleblock.base.MTEBasicMachineBaseGui;
import gregtech.common.items.behaviors.BehaviourDataOrb;

public class MTECropSynthesizer extends MTEBasicMachine {

    public static final int UUM_PER_STAT = 100;
    public static final int UUM_PER_TIER = 750;
    public static final int AMPERAGE = 3;
    private static final int INPUT_SLOT_COUNT = 4;
    private static final int OUTPUT_SLOT_COUNT = 1;

    public static void init() {}

    public MTECropSynthesizer(int id, int tier) {
        super(
            id,
            String.format("basicmachine.cropsynthesizer.tier.%02d", tier),
            StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.cropSynthesizer.name." + tier),
            tier,
            AMPERAGE,
            new String[] {
                // spotless:off
                CropsNHUtils.getMachineTypeText("cropSynthesizer"),
                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.cropSynthesizer.0"),
                StatCollector.translateToLocalFormatted(Reference.MOD_ID + "_tooltip.cropSynthesizer.1",
                    TooltipHelper.fluidText(UUM_PER_TIER)
                ),
                StatCollector.translateToLocalFormatted(Reference.MOD_ID + "_tooltip.cropSynthesizer.2",
                    TooltipHelper.fluidText(UUM_PER_STAT)
                ),
                StatCollector.translateToLocalFormatted(Reference.MOD_ID + "_tooltip.cropSynthesizer.3",
                    TooltipHelper.ampText(AMPERAGE)
                )
                // spotless:on
            },
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
                TextureFactory.of(OVERLAY_FRONT_CROP_SYNTHESIZER_ACTIVE),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_CROP_SYNTHESIZER_ACTIVE_GLOW)
                    .glow()
                    .build()),
            TextureFactory.of(
                TextureFactory.of(OVERLAY_FRONT_CROP_SYNTHESIZER),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_CROP_SYNTHESIZER_GLOW)
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

    public MTECropSynthesizer(String name, byte tier, String[] descriptionArray, ITexture[][][] textures) {
        super(name, tier, AMPERAGE, descriptionArray, textures, INPUT_SLOT_COUNT, OUTPUT_SLOT_COUNT);
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
            ItemStack stack = this.getInputAt(i);
            if (!ItemList.Tool_DataOrb.isStackEqual(stack, false, true)) continue;
            String key = BehaviourDataOrb.getDataTitle(stack);
            switch (key) {
                case Names.DataOrb.specimen -> {
                    if (cc != null) continue;
                    cc = CropRegistry.instance.get(BehaviourDataOrb.getDataName(stack));
                }
                case Names.DataOrb.growth -> {
                    if (growth >= 1) continue;
                    Byte stat = getOrbStat(stack);
                    if (stat != null) growth = stat;
                }
                case Names.DataOrb.gain -> {
                    if (gain >= 1) continue;
                    Byte stat = getOrbStat(stack);
                    if (stat != null) gain = stat;
                }
                case Names.DataOrb.resistance -> {
                    if (resistance >= 1) continue;
                    Byte stat = getOrbStat(stack);
                    if (stat != null) resistance = stat;
                }
            }
        }

        // check we found all the orbs.
        if (cc == null || growth < 1 || gain < 1 || resistance < 1) {
            return DID_NOT_FIND_RECIPE;
        }

        // check machine tier against recipe
        int minMachineTier = MTECropGeneExtractor.getVoltageTierForCrop(cc);
        if (this.mTier < minMachineTier) {
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

    public static Byte getOrbStat(ItemStack stack) {
        try {
            return Byte.parseByte(BehaviourDataOrb.getDataName(stack));
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    public static int getFluidAmount(ICropCard cc, byte gr, byte ga, byte re) {
        return cc.getTier() * UUM_PER_TIER + (gr + ga + re) * UUM_PER_STAT;
    }

    public static int getRecipeEUt(ICropCard cc) {
        return (int) (GTValues.VP[MTECropGeneExtractor.getVoltageTierForCrop(cc)] * AMPERAGE);
    }

    private static final int BASE_RECIPE_TIME = 300 * GTRecipeBuilder.SECONDS;
    private static final double BASE_RECIPE_TIME_LOG_BASE = Math.log(16.0d);

    /**
     * Starts at 5 minutes at tier 1, ends at 10 minutes at tier 16
     */
    public static int getRecipeDuration(ICropCard cc) {
        return (int) (BASE_RECIPE_TIME + BASE_RECIPE_TIME * Math.log(cc.getTier()) / BASE_RECIPE_TIME_LOG_BASE);
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
        return fluid != null && fluid.getFluid() == Materials.UUMatter.mFluid;
    }

    @Override
    public int getCapacity() {
        return getCapacityForTier(mTier);
    }

    @Override
    protected BasicUIProperties getUIProperties() {
        return super.getUIProperties().toBuilder()
            .slotOverlaysMUI2((index, isFluid, isOutput, isSpecial) -> {
                if (isFluid || isSpecial) return null;
                if (isOutput) return CropsNHUITextures.OVERLAY_SLOT_SEED_STANDARD;
                return GTGuiTextures.OVERLAY_SLOT_DATA_ORB;
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
