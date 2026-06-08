package com.gtnewhorizon.cropsnh.tileentity.singleblock;

import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.formatNumber;
import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.getFluidUnit;
import static gregtech.api.metatileentity.BaseTileEntity.TOOLTIP_DELAY;

import java.util.function.IntSupplier;

import net.minecraft.util.StatCollector;

import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.api.widget.IWidget;
import com.cleanroommc.modularui.drawable.UITexture;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.utils.Alignment;
import com.cleanroommc.modularui.value.sync.BooleanSyncValue;
import com.cleanroommc.modularui.value.sync.DoubleSyncValue;
import com.cleanroommc.modularui.value.sync.IntSyncValue;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.widget.ParentWidget;
import com.cleanroommc.modularui.widgets.ProgressWidget;
import com.cleanroommc.modularui.widgets.ToggleButton;
import com.cleanroommc.modularui.widgets.layout.Flow;
import com.cleanroommc.modularui.widgets.layout.Grid;
import com.cleanroommc.modularui.widgets.slot.ItemSlot;
import com.cleanroommc.modularui.widgets.slot.ModularSlot;
import com.gtnewhorizon.cropsnh.init.CropsNHUITexturesMUI2;
import com.gtnewhorizon.cropsnh.reference.Reference;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.modularui2.GTGuiTextures;
import gregtech.api.modularui2.GTGuis;
import gregtech.api.modularui2.common.CommonButtons;
import gregtech.common.gui.modularui.singleblock.base.MTETieredMachineBlockBaseGui;
import gregtech.common.modularui2.widget.GTProgressWidget;

public class MTECropManagerGUI {

    private static final int LEFT_GRID_SLOT_START = MTECropManagerMUI2.SLOT_WEEDEX_START;
    private static final int LEFT_GRID_SLOT_COUNT = MTECropManagerMUI2.WEEDEX_SLOT_COUNT
        + MTECropManager.FERTILIZER_SLOT_COUNT;
    private static final int LEFT_GRID_COLS = 2;
    private static final int LEFT_GRID_ROWS = (int) Math.ceil((float) LEFT_GRID_SLOT_COUNT / LEFT_GRID_COLS);

    private static final int RIGHT_GRID_SLOT_START = MTECropManagerMUI2.SLOT_OUTPUT_START;
    private static final int RIGHT_GRID_SLOT_COUNT = MTECropManagerMUI2.OUTPUT_SLOT_COUNT;
    private static final int RIGHT_GRID_COLS = 5;
    private static final int RIGHT_GRID_ROWS = (int) Math.ceil((float) RIGHT_GRID_SLOT_COUNT / RIGHT_GRID_COLS);

    private static final String SYNC_INV_HANDLER_NAME = "item_inv";
    private static final String SYNC_WATER_HANDLER_NAME = "water";
    private static final String SYNC_WEEDEX_HANDLER_NAME = "weedex";
    private static final String SYNC_FERT_HANDLER_NAME = "fert";
    // the base gui for all Steam Boilers of all types
    protected final MTECropManagerMUI2 base;
    protected final IGregTechTileEntity baseMetaTileEntity;

    public MTECropManagerGUI(MTECropManagerMUI2 base) {
        this.base = base;
        this.baseMetaTileEntity = base.getBaseMetaTileEntity();
    }

    private ItemSlot createSlot(int aIndex) {
        ModularSlot modularSlot = new ModularSlot(base.inventoryHandler, aIndex)
            .changeListener(
                (newItem, onlyAmountChanged, client, init) -> {
                    if (!client && !init) baseMetaTileEntity.markInventoryBeenModified();
                })
            .filter(aStack -> MTECropManagerMUI2.allowPutStack(aIndex, aStack));
        ItemSlot itemSlot = new ItemSlot().slot(modularSlot);

        // add overlay for weed-ex slots
        if (aIndex >= MTECropManagerMUI2.SLOT_WEEDEX_START && aIndex <= MTECropManagerMUI2.SLOT_WEEDEX_END) {
            itemSlot.backgroundOverlay(CropsNHUITexturesMUI2.OVERLAY_SLOT_WEED_EX_STANDARD);
        }
        // add overlay for fertilizer slots
        else if (aIndex >= MTECropManagerMUI2.SLOT_FERT_START && aIndex <= MTECropManagerMUI2.SLOT_FERT_END) {
            itemSlot.backgroundOverlay(CropsNHUITexturesMUI2.OVERLAY_SLOT_FERTILIZER_STANDARD);
        }
        // prevent inserting into output slots
        else if (aIndex >= MTECropManagerMUI2.SLOT_OUTPUT_START && aIndex <= MTECropManagerMUI2.SLOT_OUTPUT_END) {
            modularSlot.accessibility(false, true);
        }

        return itemSlot;
    }

    private IWidget createTankBar(PanelSyncManager syncManager, IntSupplier storageSupplier, IntSupplier capSupplier,
        String syncHandlerNameBase, UITexture texture, String tooltipFormat) {
        // create syncs
        IntSyncValue stored = new IntSyncValue(storageSupplier);
        IntSyncValue cap = new IntSyncValue(storageSupplier);
        DoubleSyncValue perc = new DoubleSyncValue(() -> (double) storageSupplier.getAsInt() / capSupplier.getAsInt());
        // register syncs
        syncManager.syncValue(syncHandlerNameBase + "Stored", stored);
        syncManager.syncValue(syncHandlerNameBase + "Cap", cap);
        String percSyncHandlerName = syncHandlerNameBase + "Perc";
        syncManager.syncValue(percSyncHandlerName, perc);

        final int height = Math.max(LEFT_GRID_ROWS, RIGHT_GRID_ROWS) * MTETieredMachineBlockBaseGui.SLOT_SIZE;
        // create widget
        var asd = new GTProgressWidget().syncHandler(percSyncHandlerName)
            .tooltipDynamic((a) -> {
                a.add(
                    StatCollector.translateToLocalFormatted(
                        tooltipFormat,
                        formatNumber(storageSupplier.getAsInt()),
                        formatNumber(capSupplier.getAsInt()),
                        getFluidUnit()));
            })
            .direction(ProgressWidget.Direction.UP)
            .texture(GTGuiTextures.SLOT_ITEM_STANDARD, texture, height)
            .size(10, height);

        return asd;
    }

    protected ToggleButton createMufflerButton() {
        return CommonButtons.createMuffleButton("mufflerSyncer")
            .disableThemeBackground(true)
            .disableHoverThemeBackground(true);
    }

    protected ToggleButton createPowerSwitchButton() {
        return CommonButtons.createSmallPowerSwitchButton("powerSwitch")
            .disableThemeBackground(true)
            .disableHoverThemeBackground(true)
            .tooltipShowUpTimer(TOOLTIP_DELAY);
    }

    public ModularPanel build(PosGuiData data, PanelSyncManager syncManager, UISettings uiSettings) {
        syncManager.registerSlotGroup(SYNC_INV_HANDLER_NAME, 0);

        BooleanSyncValue powerSwitchSyncer = new BooleanSyncValue(baseMetaTileEntity::isAllowedToWork, bool -> {
            if (bool) baseMetaTileEntity.enableWorking();
            else baseMetaTileEntity.disableWorking();
        }).allowC2S();
        syncManager.syncValue("powerSwitch", powerSwitchSyncer);

        BooleanSyncValue mufflerSyncer = new BooleanSyncValue(
            baseMetaTileEntity::isMuffled,
            baseMetaTileEntity::setMuffler).allowC2S();
        syncManager.syncValue("mufflerSyncer", mufflerSyncer);

        // create left slots
        IWidget leftGrid = new ParentWidget<>()
            .size(
                LEFT_GRID_COLS * MTETieredMachineBlockBaseGui.SLOT_SIZE,
                LEFT_GRID_ROWS * MTETieredMachineBlockBaseGui.SLOT_SIZE)
            .child(
                new Grid().coverChildren()
                    .gridOfSizeWidth(
                        LEFT_GRID_SLOT_COUNT,
                        LEFT_GRID_COLS,
                        ($x, $y, i) -> createSlot(i + LEFT_GRID_SLOT_START))
                    .horizontalCenter()
                    .verticalCenter());

        IWidget indicators = Flow.row()
            .coverChildren()
            .crossAxisAlignment(Alignment.CrossAxis.CENTER)
            .childPadding(0)
            .child(
                createTankBar(
                    syncManager,
                    () -> base.mWater,
                    () -> base.mWaterCap,
                    SYNC_WATER_HANDLER_NAME,
                    CropsNHUITexturesMUI2.PROGRESSBAR_CROP_MANAGER_WATER,
                    Reference.MOD_ID + "_tooltip.cropManager.waterStorage"))
            .child(
                createTankBar(
                    syncManager,
                    () -> base.mWater,
                    () -> base.mWaterCap,
                    SYNC_WEEDEX_HANDLER_NAME,
                    CropsNHUITexturesMUI2.PROGRESSBAR_CROP_MANAGER_WEED_EX,
                    Reference.MOD_ID + "_tooltip.cropManager.weedEXStorage"))
            .child(
                createTankBar(
                    syncManager,
                    () -> base.mWater,
                    () -> base.mWaterCap,
                    SYNC_FERT_HANDLER_NAME,
                    CropsNHUITexturesMUI2.PROGRESSBAR_CROP_MANAGER_LIQUID_FERTILIZER,
                    Reference.MOD_ID + "_tooltip.cropManager.liquidFertilizerStorage"));

        IWidget rightGrid = new ParentWidget<>()
            .size(
                RIGHT_GRID_COLS * MTETieredMachineBlockBaseGui.SLOT_SIZE,
                RIGHT_GRID_ROWS * MTETieredMachineBlockBaseGui.SLOT_SIZE)
            .child(
                new Grid().coverChildren()
                    .gridOfSizeWidth(
                        RIGHT_GRID_SLOT_COUNT,
                        RIGHT_GRID_COLS,
                        ($x, $y, i) -> createSlot(i + RIGHT_GRID_SLOT_START))
                    .horizontalCenter()
                    .verticalCenter());

        IWidget topLayer = Flow.row()
            .horizontalCenter()
            .coverChildren()
            .childPadding(3)
            .child(leftGrid)
            .child(indicators)
            .child(rightGrid);

        BooleanSyncValue waterSync = new BooleanSyncValue(() -> base.mWaterEnabled, (v) -> base.mWaterEnabled = v)
            .allowC2S();
        BooleanSyncValue weedEXSync = new BooleanSyncValue(() -> base.mWeedEXEnabled, (v) -> base.mWeedEXEnabled = v)
            .allowC2S();
        BooleanSyncValue fertSync = new BooleanSyncValue(
            () -> base.mFertilizerEnabled,
            (v) -> base.mFertilizerEnabled = v).allowC2S();
        BooleanSyncValue harvestSync = new BooleanSyncValue(() -> base.mHarvestEnabled, (v) -> base.mHarvestEnabled = v)
            .allowC2S();
        syncManager.syncValue("waterEnabled", waterSync);
        syncManager.syncValue("weedEXEnabled", weedEXSync);
        syncManager.syncValue("fertEnabled", fertSync);
        syncManager.syncValue("harvestEnabled", harvestSync);

        IWidget toggleRow = Flow.row()
            .leftRel(0)
            .coverChildren()
            .childPadding(0)
            .child(
                new ToggleButton().value(waterSync)
                    .tooltip(
                        true,
                        tooltip -> tooltip.addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.water"))
                            .addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.enabled")))
                    .tooltip(
                        false,
                        tooltip -> tooltip.addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.water"))
                            .addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.disabled")))
                    .overlay(CropsNHUITexturesMUI2.BUTTON_OVERLAY_TOGGLE_WATER)
                    .size(18))
            .child(
                new ToggleButton().value(weedEXSync)
                    .tooltip(
                        true,
                        tooltip -> tooltip.addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.weedEX"))
                            .addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.enabled")))
                    .tooltip(
                        false,
                        tooltip -> tooltip.addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.weedEX"))
                            .addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.disabled")))
                    .overlay(CropsNHUITexturesMUI2.BUTTON_OVERLAY_TOGGLE_WEED_EX)
                    .size(18))
            .child(
                new ToggleButton().value(fertSync)
                    .tooltip(
                        true,
                        tooltip -> tooltip
                            .addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.fertilizer"))
                            .addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.enabled")))
                    .tooltip(
                        false,
                        tooltip -> tooltip
                            .addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.fertilizer"))
                            .addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.disabled")))
                    .overlay(CropsNHUITexturesMUI2.BUTTON_OVERLAY_TOGGLE_FERTILIZER)
                    .size(18))
            .child(
                new ToggleButton().value(harvestSync)
                    .tooltip(
                        true,
                        tooltip -> tooltip.addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.harvest"))
                            .addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.enabled")))
                    .tooltip(
                        false,
                        tooltip -> tooltip.addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.harvest"))
                            .addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.disabled")))
                    .overlay(CropsNHUITexturesMUI2.BUTTON_OVERLAY_TOGGLE_HARVEST)
                    .size(18));

        IWidget bottomLayer = new ParentWidget<>().fullWidth()
            .paddingLeft(7)
            .paddingRight(7)
            .coverChildrenHeight()
            .child(toggleRow);

        return GTGuis.mteTemplatePanelBuilder(base, data, syncManager, uiSettings)
            .build()
            .child(
                Flow.column()
                    .horizontalCenter()
                    .top(7)
                    .childPadding(2)
                    .child(topLayer)
                    .child(bottomLayer));
    }

}
