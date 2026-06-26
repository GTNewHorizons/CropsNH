package com.gtnewhorizon.cropsnh.tileentity.singleblock;

import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.formatNumber;
import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.getFluidUnit;

import java.util.function.IntSupplier;

import net.minecraft.util.StatCollector;

import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.api.widget.IWidget;
import com.cleanroommc.modularui.drawable.UITexture;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.utils.Alignment;
import com.cleanroommc.modularui.value.sync.BooleanSyncValue;
import com.cleanroommc.modularui.value.sync.DoubleSyncValue;
import com.cleanroommc.modularui.value.sync.IntSyncValue;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.widget.ParentWidget;
import com.cleanroommc.modularui.widget.scroll.VerticalScrollData;
import com.cleanroommc.modularui.widgets.ListWidget;
import com.cleanroommc.modularui.widgets.ProgressWidget;
import com.cleanroommc.modularui.widgets.ToggleButton;
import com.cleanroommc.modularui.widgets.layout.Flow;
import com.cleanroommc.modularui.widgets.layout.Grid;
import com.cleanroommc.modularui.widgets.slot.ItemSlot;
import com.cleanroommc.modularui.widgets.slot.ModularSlot;
import com.gtnewhorizon.cropsnh.init.CropsNHUITextures;
import com.gtnewhorizon.cropsnh.reference.Reference;

import gregtech.api.modularui2.GTGuiTextures;
import gregtech.common.gui.modularui.singleblock.base.MTETieredMachineBlockBaseGui;
import gregtech.common.gui.modularui.util.MachineModularSlot;

public class MTECropManagerGui extends MTETieredMachineBlockBaseGui<MTECropManager> {

    private static final int LEFT_GRID_SLOT_START = MTECropManager.SLOT_WEEDEX_START;
    private static final int LEFT_GRID_SLOT_COUNT = MTECropManager.WEEDEX_SLOT_COUNT
        + MTECropManager.FERTILIZER_SLOT_COUNT;
    private static final int LEFT_GRID_COLS = 2;
    private static final int LEFT_GRID_ROWS = (int) Math.ceil((float) LEFT_GRID_SLOT_COUNT / LEFT_GRID_COLS);

    private static final int RIGHT_GRID_SLOT_START = MTECropManager.SLOT_OUTPUT_START;
    private static final int RIGHT_GRID_COLS = 5;
    // How many output rows the original (LV) layout fit; the panel grows from this baseline.
    private static final int RIGHT_GRID_BASELINE_ROWS = 3;
    // Cap on rows shown at once; beyond this the output grid scrolls instead of growing the panel.
    private static final int RIGHT_GRID_MAX_VISIBLE_ROWS = 6;
    // Scrollbar width, reserved as a gutter to the right of the slots so it stays clickable.
    private static final int SCROLLBAR_THICKNESS = 6;

    // The output region scales with tier, so these depend on the machine instance.
    private final int rightGridSlotCount;
    private final int rightGridRows;
    private final int rightGridVisibleRows;
    // Non-zero only when the output grid actually scrolls, so non-scrolling tiers keep the original width.
    private final int scrollbarGutter;

    private static final String SYNC_INV_HANDLER_NAME = "item_inv";
    private static final String SYNC_WATER_HANDLER_NAME = "water";
    private static final String SYNC_WEEDEX_HANDLER_NAME = "weedex";
    private static final String SYNC_FERT_HANDLER_NAME = "fert";

    public MTECropManagerGui(MTECropManager machine) {
        super(machine);
        this.rightGridSlotCount = machine.mOutputSlotCount;
        this.rightGridRows = (int) Math.ceil((float) this.rightGridSlotCount / RIGHT_GRID_COLS);
        this.rightGridVisibleRows = Math.min(this.rightGridRows, RIGHT_GRID_MAX_VISIBLE_ROWS);
        this.scrollbarGutter = this.rightGridRows > this.rightGridVisibleRows ? SCROLLBAR_THICKNESS : 0;
    }

    @Override
    protected int getBasePanelHeight() {
        // Grow the panel to fit the visible output rows, up to the scroll cap.
        int extraRows = Math.max(0, rightGridVisibleRows - RIGHT_GRID_BASELINE_ROWS);
        return super.getBasePanelHeight() + extraRows * MTETieredMachineBlockBaseGui.SLOT_SIZE;
    }

    @Override
    protected int getBasePanelWidth() {
        // Widen the panel to make room for the scrollbar gutter when the output grid scrolls.
        return super.getBasePanelWidth() + scrollbarGutter;
    }

    private ItemSlot createSlot(int aIndex) {
        ModularSlot modularSlot = new MachineModularSlot(machine.inventoryHandler, aIndex, baseMetaTileEntity);
        ItemSlot itemSlot = new ItemSlot().slot(modularSlot);

        // add overlay for weed-ex slots
        if (aIndex >= MTECropManager.SLOT_WEEDEX_START && aIndex <= MTECropManager.SLOT_WEEDEX_END) {
            itemSlot.backgroundOverlay(CropsNHUITextures.OVERLAY_SLOT_WEED_EX_STANDARD);
            modularSlot.slotGroup(SYNC_INV_HANDLER_NAME);
        }
        // add overlay for fertilizer slots
        else if (aIndex >= MTECropManager.SLOT_FERT_START && aIndex <= MTECropManager.SLOT_FERT_END) {
            itemSlot.backgroundOverlay(CropsNHUITextures.OVERLAY_SLOT_FERTILIZER_STANDARD);
            modularSlot.slotGroup(SYNC_INV_HANDLER_NAME);
        }
        // add overlay for fertilizer slots
        else if (aIndex == machine.mSlotBattery) {
            itemSlot.backgroundOverlay(GTGuiTextures.OVERLAY_SLOT_CHARGER);
            modularSlot.slotGroup(SYNC_INV_HANDLER_NAME);
        }
        // prevent inserting into output slots
        else if (aIndex >= MTECropManager.SLOT_OUTPUT_START && aIndex <= machine.mSlotOutputEnd) {
            modularSlot.accessibility(false, true);
        }

        return itemSlot;
    }

    private IWidget createTankBar(PanelSyncManager syncManager, IntSupplier storageSupplier, IntSupplier capSupplier,
        String syncHandlerNameBase, UITexture texture, String tooltipFormat) {
        // create syncs
        IntSyncValue stored = new IntSyncValue(storageSupplier);
        IntSyncValue cap = new IntSyncValue(capSupplier);
        DoubleSyncValue perc = new DoubleSyncValue(() -> (double) storageSupplier.getAsInt() / capSupplier.getAsInt());
        // register syncs
        syncManager.syncValue(syncHandlerNameBase + "Stored", stored);
        syncManager.syncValue(syncHandlerNameBase + "Cap", cap);
        String percSyncHandlerName = syncHandlerNameBase + "Perc";
        syncManager.syncValue(percSyncHandlerName, perc);

        final int height = Math.max(LEFT_GRID_ROWS, rightGridVisibleRows) * MTETieredMachineBlockBaseGui.SLOT_SIZE;
        // create widget
        return new ProgressWidget().syncHandler(percSyncHandlerName)
            .tooltipDynamic(
                (a) -> a.add(
                    StatCollector.translateToLocalFormatted(
                        tooltipFormat,
                        formatNumber(stored.getIntValue()),
                        formatNumber(cap.getIntValue()),
                        getFluidUnit())))
            .direction(ProgressWidget.Direction.UP)
            .texture(GTGuiTextures.SLOT_ITEM_STANDARD, texture, height)
            .size(10, height);
    }

    @Override
    protected void registerSyncValues(PanelSyncManager syncManager) {
        super.registerSyncValues(syncManager);
        syncManager.registerSlotGroup(SYNC_INV_HANDLER_NAME, LEFT_GRID_COLS, true);
    }

    @Override
    protected boolean supportsMuffler() {
        return false;
    }

    @Override
    protected boolean supportsTopRightCornerFlow() {
        return false;
    }

    @Override
    protected ParentWidget<?> createContentSection(ModularPanel panel, PanelSyncManager syncManager) {
        // create left slots
        Grid leftGrid = new Grid().coverChildren()
            .gridOfSizeWidth(LEFT_GRID_SLOT_COUNT, LEFT_GRID_COLS, ($x, $y, i) -> createSlot(i + LEFT_GRID_SLOT_START));

        Flow indicators = Flow.row()
            .coverChildren()
            .child(
                createTankBar(
                    syncManager,
                    () -> machine.mWater,
                    () -> machine.mWaterCap,
                    SYNC_WATER_HANDLER_NAME,
                    CropsNHUITextures.PROGRESSBAR_CROP_MANAGER_WATER,
                    Reference.MOD_ID + "_tooltip.cropManager.waterStorage"))
            .child(
                createTankBar(
                    syncManager,
                    () -> machine.mWeedEX,
                    () -> machine.mWeedEXCap,
                    SYNC_WEEDEX_HANDLER_NAME,
                    CropsNHUITextures.PROGRESSBAR_CROP_MANAGER_WEED_EX,
                    Reference.MOD_ID + "_tooltip.cropManager.weedEXStorage"))
            .child(
                createTankBar(
                    syncManager,
                    () -> machine.mLiquidFertilizer,
                    () -> machine.mLiquidFertilizerCap,
                    SYNC_FERT_HANDLER_NAME,
                    CropsNHUITextures.PROGRESSBAR_CROP_MANAGER_LIQUID_FERTILIZER,
                    Reference.MOD_ID + "_tooltip.cropManager.liquidFertilizerStorage"));

        // The output grid scales with tier; wrap it in a fixed-height scroll box so it never overflows the panel.
        // The slots stay left-aligned and the scrollbar lives in the reserved gutter on the right.
        ListWidget<IWidget, ?> rightGrid = new ListWidget<>()
            .size(
                RIGHT_GRID_COLS * MTETieredMachineBlockBaseGui.SLOT_SIZE + scrollbarGutter,
                rightGridVisibleRows * MTETieredMachineBlockBaseGui.SLOT_SIZE)
            .crossAxisAlignment(Alignment.CrossAxis.START)
            .scrollDirection(new VerticalScrollData(false, SCROLLBAR_THICKNESS));
        rightGrid.child(
            new Grid().coverChildren()
                .gridOfSizeWidth(
                    rightGridSlotCount,
                    RIGHT_GRID_COLS,
                    ($x, $y, i) -> createSlot(i + RIGHT_GRID_SLOT_START)));

        Flow topLayer = Flow.row()
            .horizontalCenter()
            .coverChildren()
            .childPadding(3)
            .child(leftGrid)
            .child(indicators)
            .child(rightGrid);

        return super.createContentSection(panel, syncManager).child(topLayer);
    }

    @Override
    protected Flow createBottomLeftCornerFlow(ModularPanel panel, PanelSyncManager syncManager) {
        BooleanSyncValue waterSync = new BooleanSyncValue(() -> machine.mWaterEnabled, (v) -> machine.mWaterEnabled = v)
            .allowC2S();
        BooleanSyncValue weedEXSync = new BooleanSyncValue(
            () -> machine.mWeedEXEnabled,
            (v) -> machine.mWeedEXEnabled = v).allowC2S();
        BooleanSyncValue fertSync = new BooleanSyncValue(
            () -> machine.mFertilizerEnabled,
            (v) -> machine.mFertilizerEnabled = v).allowC2S();
        BooleanSyncValue harvestSync = new BooleanSyncValue(
            () -> machine.mHarvestEnabled,
            (v) -> machine.mHarvestEnabled = v).allowC2S();

        return super.createBottomLeftCornerFlow(panel, syncManager).child(
            new ToggleButton().value(waterSync)
                .tooltip(
                    true,
                    tooltip -> tooltip.addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.water"))
                        .addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.enabled")))
                .tooltip(
                    false,
                    tooltip -> tooltip.addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.water"))
                        .addLine(IKey.lang(Reference.MOD_ID + "_tooltip.cropManager.toggle.disabled")))
                .overlay(CropsNHUITextures.BUTTON_OVERLAY_TOGGLE_WATER))
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
                    .overlay(CropsNHUITextures.BUTTON_OVERLAY_TOGGLE_WEED_EX))
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
                    .overlay(CropsNHUITextures.BUTTON_OVERLAY_TOGGLE_FERTILIZER))
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
                    .overlay(CropsNHUITextures.BUTTON_OVERLAY_TOGGLE_HARVEST))
            .child(
                Flow.row()
                    .width(MTETieredMachineBlockBaseGui.SLOT_SIZE)
                    .marginLeft(MTETieredMachineBlockBaseGui.SLOT_SIZE)
                    .mainAxisAlignment(Alignment.MainAxis.CENTER)
                    .childIf(this.supportsMuffler(), this::createMufflerButton))
            .child(createSlot(machine.mSlotBattery))
            .child(
                Flow.row()
                    .width(MTETieredMachineBlockBaseGui.SLOT_SIZE)
                    .mainAxisAlignment(Alignment.MainAxis.CENTER)
                    .childIf(this.supportsPowerSwitch(), this::createPowerSwitchButton));
    }

}
