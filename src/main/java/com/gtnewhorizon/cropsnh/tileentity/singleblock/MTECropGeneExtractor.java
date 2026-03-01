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

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.init.CropsNHUITextures;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizons.modularui.api.drawable.IDrawable;
import com.gtnewhorizons.modularui.api.math.Pos2d;
import com.gtnewhorizons.modularui.common.widget.SlotWidget;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.SoundResource;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.gui.modularui.GTUITextures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.MTEBasicMachine;
import gregtech.api.recipe.BasicUIProperties;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GTRecipeBuilder;
import gregtech.api.util.GTUtility;
import gregtech.common.items.behaviors.BehaviourDataOrb;

public class MTECropGeneExtractor extends MTEBasicMachine {

    public static final int DURATION_SPECIMEN = 2 * GTRecipeBuilder.MINUTES;
    public static final int DURATION_STAT = 30 * GTRecipeBuilder.SECONDS;

    public MTECropGeneExtractor(int aID, int aTier, String aNameRegional) {
        super(
            aID,
            String.format("basicmachine.cropgeneextractor.tier.%02d", aTier),
            aNameRegional,
            aTier,
            1,
            new String[] { CropsNHUtils.getMachineTypeText("cropGeneExtractor"), "It can extract Crop Genes",
                "Use a circuit to determine the genes you want to extract,",
                "1 for Specimen, 2 for Growth, 3 for Gain, 4 for Resistance" },
            1,
            1,
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

    public MTECropGeneExtractor(String mName, byte mTier, String[] mDescriptionArray, ITexture[][][] mTextures) {
        super(mName, mTier, 1, mDescriptionArray, mTextures, 1, 1);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity arg0) {
        return new MTECropGeneExtractor(this.mName, this.mTier, this.mDescriptionArray, this.mTextures);
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return CropsNHGTRecipeMaps.fakeCropGeneExtractorRecipeMap;
    }

    @Override
    public int checkRecipe(boolean skipOC) {
        ISeedData seedData = CropsNHUtils.getAnalyzedSeedData(getInputAt(0));
        ItemStack circuitStack = getStackInSlot(getCircuitSlot());
        ItemStack dataOrb = getSpecialSlot();

        if (seedData != null && isDataOrb(dataOrb) && circuitStack != null) {

            // find the data to inject into the orb
            int circuitNo = CropsNHUtils.getItemMeta(circuitStack);
            String title, name;
            switch (circuitNo) {
                case 1 -> {
                    title = Names.DataOrb.specimen;
                    name = seedData.getCrop()
                        .getId();
                }
                case 2 -> {
                    title = Names.DataOrb.growth;
                    name = Byte.toString(
                        seedData.getStats()
                            .getGrowth());
                }
                case 3 -> {
                    title = Names.DataOrb.gain;
                    name = Byte.toString(
                        seedData.getStats()
                            .getGain());
                }
                case 4 -> {
                    title = Names.DataOrb.resistance;
                    name = Byte.toString(
                        seedData.getStats()
                            .getResistance());
                }
                default -> {
                    return DID_NOT_FIND_RECIPE;
                }
            }

            // check machine tier against recipe
            int minMachineTier = getVoltageTierForCrop(seedData.getCrop());
            if (this.mTier < minMachineTier) {
                return DID_NOT_FIND_RECIPE;
            }

            // generate the output
            ItemStack outputStack = ItemList.Tool_DataOrb.get(1L);
            BehaviourDataOrb.setDataTitle(outputStack, title);
            BehaviourDataOrb.setDataName(outputStack, name);

            // check if we can output
            if (!canOutput(outputStack)) return DID_NOT_FIND_RECIPE;

            // check power usage.
            if (!skipOC) {
                calculateOverclockedNess((int) GTValues.VP[minMachineTier], getRecipeDuration(circuitNo));
                if (mMaxProgresstime == Integer.MAX_VALUE - 1 && mEUt == Integer.MAX_VALUE - 1)
                    return FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS;
            }

            // consume from the orb and data orb
            seedData.getStack().stackSize -= 1;
            dataOrb.stackSize -= 1;

            // save output
            this.mOutputItems[0] = outputStack;

            return FOUND_AND_SUCCESSFULLY_USED_RECIPE;
        }
        return DID_NOT_FIND_RECIPE;
    }

    public static int getRecipeDuration(int circuit) {
        return circuit == 1 ? DURATION_SPECIMEN : DURATION_STAT;
    }

    public static int getRecipeEUt(ICropCard cc) {
        return (int) (GTValues.VP[MTECropGeneExtractor.getVoltageTierForCrop(cc)]);
    }

    public static int getVoltageTierForCrop(ICropCard cc) {
        return Math.min(VoltageIndex.UMV, Math.max(VoltageIndex.EV, cc.getMachineBreedingRecipeTier()));
    }

    public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
        super.startSoundLoop(aIndex, aX, aY, aZ);
        if (aIndex == 1) {
            GTUtility.doSoundAtClient(SoundResource.GTCEU_LOOP_REPLICATOR, 10, 1.0F, aX, aY, aZ);
        }
    }

    public void startProcess() {
        sendLoopStart((byte) 1);
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStack, int ordinalSide) {
        return super.canInsertItem(index, itemStack, ordinalSide);
    }

    @Override
    protected boolean allowPutStackValidated(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, ForgeDirection side,
        ItemStack aStack) {
        if (aIndex == this.getSpecialSlotIndex()) {
            return isDataOrb(aStack);
        }
        if (aIndex == this.getInputSlot()) {
            return CropsNHUtils.getAnalyzedSeedData(aStack) != null;
        }
        return super.allowPutStackValidated(aBaseMetaTileEntity, aIndex, side, aStack);
    }

    private static boolean isDataOrb(ItemStack aStack) {
        return ItemList.Tool_DataOrb.isStackEqual(aStack, false, true) && aStack.stackSize > 0;
    }

    @Override
    public boolean allowSelectCircuit() {
        return true;
    }

    @Override
    protected SlotWidget createItemInputSlot(int index, IDrawable[] backgrounds, Pos2d pos) {
        return (SlotWidget) super.createItemInputSlot(index, backgrounds, pos)
            .setBackground(getGUITextureSet().getItemSlot(), CropsNHUITextures.OVERLAY_SLOT_SEED);
    }

    @Override
    protected SlotWidget createSpecialSlot(IDrawable[] backgrounds, Pos2d pos, BasicUIProperties uiProperties) {
        return (SlotWidget) super.createSpecialSlot(backgrounds, pos, uiProperties)
            .setGTTooltip(() -> mTooltipCache.getData("bpp.machines.special_slot.tooltip"))
            .setBackground(getGUITextureSet().getItemSlot(), GTUITextures.OVERLAY_SLOT_DATA_ORB);
    }
}
