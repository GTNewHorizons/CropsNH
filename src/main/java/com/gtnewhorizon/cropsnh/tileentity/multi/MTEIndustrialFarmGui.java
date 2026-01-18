package com.gtnewhorizon.cropsnh.tileentity.multi;

import java.util.function.Supplier;

import net.minecraft.util.StatCollector;

import com.cleanroommc.modularui.api.drawable.IDrawable;
import com.cleanroommc.modularui.api.widget.IWidget;
import com.cleanroommc.modularui.drawable.DynamicDrawable;
import com.cleanroommc.modularui.drawable.Rectangle;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.screen.viewport.GuiContext;
import com.cleanroommc.modularui.theme.WidgetTheme;
import com.cleanroommc.modularui.value.sync.IntSyncValue;
import com.cleanroommc.modularui.value.sync.LongSyncValue;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.widgets.layout.Flow;
import com.cleanroommc.modularui.widgets.slot.ItemSlot;
import com.cleanroommc.modularui.widgets.slot.ModularSlot;
import com.gtnewhorizon.cropsnh.init.CropsNHUITexturesMUI2;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import gregtech.api.modularui2.GTGuiTextures;
import gregtech.common.gui.modularui.multiblock.base.MTEMultiBlockBaseGui;

public class MTEIndustrialFarmGui extends MTEMultiBlockBaseGui<MTEIndustrialFarm> {

    public MTEIndustrialFarmGui(MTEIndustrialFarm multiblock) {
        super(multiblock);
    }

    @Override
    protected boolean shouldDisplayInputSeparation() {
        return false;
    }

    @Override
    protected boolean shouldDisplayRecipeLock() {
        return false;
    }

    @Override
    protected boolean shouldDisplayBatchMode() {
        return false;
    }

    @Override
    public ModularPanel build(PosGuiData guiData, PanelSyncManager syncManager, UISettings uiSettings) {
        uiSettings.customContainer(MTEIndustrialFarmModularContainer::new);
        return super.build(guiData, syncManager, uiSettings);
    }

    @Override
    protected void registerSyncValues(PanelSyncManager syncManager) {
        super.registerSyncValues(syncManager);

        // upgrade counts
        syncManager.syncValue(
            "mEnvironmentalEnhancementUnitCount",
            new IntSyncValue(
                () -> multiblock.mEnvironmentalEnhancementUnitCount,
                val -> multiblock.mEnvironmentalEnhancementUnitCount = val));
        syncManager.syncValue(
            "mFertilizerUnitCount",
            new IntSyncValue(() -> multiblock.mFertilizerUnitCount, val -> multiblock.mFertilizerUnitCount = val));
        syncManager.syncValue(
            "mGrowthAccelerationUnitCount",
            new IntSyncValue(
                () -> multiblock.mGrowthAccelerationUnitCount,
                val -> multiblock.mGrowthAccelerationUnitCount = val));
        syncManager.syncValue(
            "mAdvancedHarvestingUnitCount",
            new IntSyncValue(
                () -> multiblock.mAdvancedHarvestingUnitCount,
                val -> multiblock.mAdvancedHarvestingUnitCount = val));
        syncManager.syncValue(
            "mOverclockedGrowthAccelerationUnitCount",
            new IntSyncValue(
                () -> multiblock.mOverclockedGrowthAccelerationUnitCount,
                val -> multiblock.mOverclockedGrowthAccelerationUnitCount = val));

        // Status
        syncManager.syncValue(
            "mSeedCapacity",
            new IntSyncValue(() -> multiblock.mSeedCapacity, val -> multiblock.mSeedCapacity = val));
        syncManager.syncValue(
            "mUpgradeTier",
            new IntSyncValue(() -> multiblock.mUpgradeTier, val -> multiblock.mUpgradeTier = val));
        syncManager.syncValue(
            "mExpectedOCs",
            new IntSyncValue(() -> multiblock.mExpectedOCs, val -> multiblock.mExpectedOCs = val));
        syncManager.syncValue(
            "mExpectedEUt",
            new LongSyncValue(() -> multiblock.mExpectedEUt, val -> multiblock.mExpectedEUt = val));

        syncManager.registerSlotGroup("if_inv", 4);

    }

    // quick special drawable to help with the dynamic overlay for the slots
    private static class DisabledSlotOverlay extends DynamicDrawable {

        public static final IDrawable GRAY_TEXTURE = new Rectangle() {

            @Override
            public void draw(GuiContext context, int x0, int y0, int width, int height, WidgetTheme widgetTheme) {
                // adds 1 pixel of padding to only gray out slot contents and not the slot outline
                super.draw(context, x0 + 1, y0 + 1, width - 2, height - 2, widgetTheme);
            }
        }
            // #80000000 (ARGB)
            .setColor(-2147483648);

        public DisabledSlotOverlay(Supplier<Boolean> supplier) {
            super(() -> supplier.get() ? GRAY_TEXTURE : IDrawable.EMPTY);
        }
    }

    private final IDrawable SEED_SLOT_DISABLED_OVERLAY = new DisabledSlotOverlay(
        () -> multiblock.mMaxProgresstime != 0 || multiblock.isAllowedToWork()
            || CropsNHUtils.isStackValid(multiblock.getBlockUnderStack()));

    private ItemSlot createEnvModuleSlot(int index) {
        return new ItemSlot()
            .slot(
                new ModularSlot(multiblock.mIFStackHandler, MTEIndustrialFarm.SLOT_ENV_CARD_START + index) {}
                    .slotGroup("if_inv"))
            .tooltipDynamic(x -> {
                x.addLine(
                    StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.environmentalModule"));
                x.addLine(
                    StatCollector.translateToLocal(
                        multiblock.mEnvironmentalEnhancementUnitCount >= 1 + index
                            ? Reference.MOD_ID + "_tooltip.industrialFarm.environmentalModule.enabled"
                            : Reference.MOD_ID + "_tooltip.industrialFarm.environmentalModule.disabled"));
            })
            .background(
                GTGuiTextures.SLOT_ITEM_STANDARD,
                CropsNHUITexturesMUI2.OVERLAY_SLOT_ENVIRONMENTAL_MODULE_STANDARD)
            .overlay(
                new DisabledSlotOverlay(
                    () -> multiblock.mMaxProgresstime != 0 || multiblock.isAllowedToWork()
                        || multiblock.mEnvironmentalEnhancementUnitCount < 1 + index));
    }

    @Override
    protected IWidget makeTitleTextWidget() {
        return super.makeTitleTextWidget();
    }

    @Override
    protected Flow createLeftPanelGapRow(ModularPanel parent, PanelSyncManager syncManager) {
        return super.createLeftPanelGapRow(parent, syncManager).childIf(
            multiblock.doesBindPlayerInventory(),
            new ItemSlot()
                .slot(
                    new ModularSlot(multiblock.mIFStackHandler, MTEIndustrialFarm.SLOT_SEED).ignoreMaxStackSize(true)
                        .slotGroup("if_inv"))
                .tooltipDynamic(x -> {
                    x.addLine(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.seedSlot"));
                    x.addLine(
                        StatCollector.translateToLocalFormatted(
                            Reference.MOD_ID + "_tooltip.industrialFarm.capacity",
                            multiblock.mSeedCapacity));
                    if (CropsNHUtils.isStackValid(multiblock.getBlockUnderStack())) {
                        x.addLine(
                            StatCollector.translateToLocal(
                                Reference.MOD_ID + "_tooltip.industrialFarm.seedWithBlockExtractionWarning"));
                    }
                })
                .background(GTGuiTextures.SLOT_ITEM_STANDARD, CropsNHUITexturesMUI2.OVERLAY_SLOT_SEED_STANDARD)
                .overlay(SEED_SLOT_DISABLED_OVERLAY))
            .childIf(
                multiblock.doesBindPlayerInventory(),
                new ItemSlot()
                    .slot(
                        new ModularSlot(multiblock.mIFStackHandler, MTEIndustrialFarm.SLOT_BLOCK_UNDER)
                            .ignoreMaxStackSize(true)
                            .slotGroup("if_inv"))
                    .tooltipDynamic(x -> {
                        x.addLine(
                            StatCollector
                                .translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.blockUnderSlot"));
                        x.addLine(
                            StatCollector.translateToLocalFormatted(
                                Reference.MOD_ID + "_tooltip.industrialFarm.capacity",
                                multiblock.mSeedCapacity));
                        if (CropsNHUtils.isStackValid(multiblock.getBlockUnderStack())) {
                            x.addLine(
                                StatCollector
                                    .translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.blockExtraction"));
                        } else {
                            x.addLine(
                                StatCollector
                                    .translateToLocal(Reference.MOD_ID + "_tooltip.industrialFarm.blockInsertion"));
                        }
                    })
                    .background(GTGuiTextures.SLOT_ITEM_STANDARD, GTGuiTextures.OVERLAY_SLOT_BLOCK_STANDARD)
                    .overlay(SEED_SLOT_DISABLED_OVERLAY))
            .childIf(multiblock.doesBindPlayerInventory(), createEnvModuleSlot(0))
            .childIf(multiblock.doesBindPlayerInventory(), createEnvModuleSlot(1));
    }
}
