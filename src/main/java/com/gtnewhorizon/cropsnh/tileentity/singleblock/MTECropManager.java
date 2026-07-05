package com.gtnewhorizon.cropsnh.tileentity.singleblock;

import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.formatNumber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.function.IntConsumer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

import org.apache.commons.lang3.ArrayUtils;

import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.farming.registries.FertilizerRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.HydrationRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.WeedEXRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHBlockTextures;
import com.gtnewhorizon.cropsnh.init.CropsNHFluids;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.NBTHelper;
import com.gtnewhorizon.gtnhlib.util.map.ItemStackMap;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.MTETieredMachineBlock;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTUtility;
import gregtech.api.util.tooltip.TooltipHelper;

public class MTECropManager extends MTETieredMachineBlock {

    public static final int WEEDEX_SLOT_COUNT = 2;
    public static final int FERTILIZER_SLOT_COUNT = 4;
    public static final int OUTPUT_SLOT_COUNT = 15;
    public static final int BATTERY_SLOT_COUNT = 1;
    public static final int TOTAL_SLOT_COUNT = WEEDEX_SLOT_COUNT + FERTILIZER_SLOT_COUNT
        + OUTPUT_SLOT_COUNT
        + BATTERY_SLOT_COUNT;

    public static final int SLOT_WEEDEX_START = 0;
    public static final int SLOT_WEEDEX_END = SLOT_WEEDEX_START - 1 + WEEDEX_SLOT_COUNT;
    public static final int SLOT_FERT_START = SLOT_WEEDEX_END + 1;
    public static final int SLOT_FERT_END = SLOT_FERT_START - 1 + FERTILIZER_SLOT_COUNT;
    public static final int SLOT_OUTPUT_START = SLOT_FERT_END + 1;
    public static final int SLOT_OUTPUT_END = SLOT_OUTPUT_START - 1 + OUTPUT_SLOT_COUNT;
    public static final int SLOT_BATTERY = SLOT_OUTPUT_END + 1;

    /** How often the crop manager runs it's work loop. */
    private final static int GLOBAL_UPDATE_RATE = 2 * 20 + 10;
    /** How often the crop manager updates it's crop cache when said cache is empty. */
    private final static int CACHE_REFRESH_EMPTY = GLOBAL_UPDATE_RATE * 2;
    /** How often the crop manager updates it's crop cache when said cache contains crops. */
    private final static int CACHE_REFRESH_ANY = GLOBAL_UPDATE_RATE * 12;

    /** Whether the crop manager is allowed to harvest crops. */
    public boolean mHarvestEnabled = true;
    /** Whether the crop manager is allowed to apply Weed-EX to crops. */
    public boolean mWeedEXEnabled = false;
    /** Whether the crop manager is allowed to fertilize crops. */
    public boolean mFertilizerEnabled = false;
    /** Whether the crop manager is allowed to water crops. */
    public boolean mWaterEnabled = false;

    public boolean mCharge = false;
    public boolean mDecharge = false;

    /** Water potency stored in the crop manager. */
    private int mWater = 0;
    /** The maximum amount of water potency that can be stored in the crop manager. */
    private final int mWaterCap;
    /** Weed-EX potency stored in the crop manager. */
    private int mWeedEX = 0;
    /** The maximum amount of Weed-EX potency that can be stored in the crop manager. */
    private final int mWeedEXCap;
    /** Liquid fertilizer potency stored in the crop manager. */
    private int mLiquidFertilizer = 0;
    /** The maximum amount of liquid fertilizer potency that can be stored in the crop manager. */
    private final int mLiquidFertilizerCap;

    /** A cache of all known crops within the crop manager's range. */
    private final HashSet<ICropStickTile> mCropCache = new HashSet<>();
    /** Whether the crop cache appears to contain invalid data. */
    private boolean mInvalidCache = false;
    /** A holder for drops when a single harvest cycle would overflow the manager's inventory. */
    private final ItemStackMap<Integer> mDropOverflow = new ItemStackMap<>(true);
    /** A cache holding synchronized tank information for waila. */
    private final FluidTankInfo[] mWailaFluidTankInfos;

    public MTECropManager(final int aID, final int aTier) {
        super(
            aID,
            String.format("basicmachine.cropManager.tier.%02d", aTier),
            StatCollector.translateToLocalFormatted(Reference.MOD_ID + "_tooltip.cropManager.name." + aTier),
            aTier,
            TOTAL_SLOT_COUNT,
            StatCollector.translateToLocal("cropsnh_tooltip.cropManager.description"));
        this.mWaterCap = calcWaterCap();
        this.mWeedEXCap = calcWeedEXCap();
        this.mLiquidFertilizerCap = calcLiquidFertilizerCap();
        this.mWailaFluidTankInfos = getDefaultWailaFluidTankInfos();
    }

    private int calcWaterCap() {
        return this.mTier * 32000;
    }

    private int calcWeedEXCap() {
        return this.mTier * 750 * 2;
    }

    private int calcLiquidFertilizerCap() {
        return this.mTier * 144 * 64 * 4;
    }

    private FluidTankInfo[] getDefaultWailaFluidTankInfos() {
        return new FluidTankInfo[] {
            new FluidTankInfo(new FluidStack(FluidRegistry.WATER, this.mWater), this.mWaterCap),
            new FluidTankInfo(CropsNHUtils.getWeedEXFluid(this.mWeedEX), this.mWeedEXCap),
            new FluidTankInfo(new FluidStack(CropsNHFluids.fertilizer, this.mWeedEX), this.mLiquidFertilizerCap) };
    }

    // region TE creation

    private MTECropManager(final String aName, final int aTier, final String[] aDescription,
        final ITexture[][][] aTextures) {
        super(aName, aTier, TOTAL_SLOT_COUNT, aDescription, aTextures);
        this.mWaterCap = calcWaterCap();
        this.mWeedEXCap = calcWeedEXCap();
        this.mLiquidFertilizerCap = calcLiquidFertilizerCap();
        this.mWailaFluidTankInfos = getDefaultWailaFluidTankInfos();
    }

    private void updateFluidTanksForWaila() {
        this.mWailaFluidTankInfos[0].fluid.amount = this.mWater;
        this.mWailaFluidTankInfos[1].fluid.amount = this.mWeedEX;
        this.mWailaFluidTankInfos[2].fluid.amount = this.mLiquidFertilizer;
    }

    @Override
    public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new MTECropManager(this.mName, this.mTier, this.mDescriptionArray, this.mTextures);
    }

    // endregion TE creation

    // region Base MTE Params

    @Override
    public int getSizeInventory() {
        return TOTAL_SLOT_COUNT;
    }

    @Override
    public long maxAmperesIn() {
        return 1;
    }

    @Override
    public long getMinimumStoredEU() {
        return this.maxEUInput();
    }

    @Override
    public long maxEUStore() {
        return GTValues.V[this.mTier] * (this.mTier * GTValues.V[this.mTier]);
    }

    @Override
    public long maxEUInput() {
        return GTValues.V[this.mTier];
    }

    @Override
    public int getCapacity() {
        return 32000 * this.mTier;
    }

    @Override
    public int getFluidAmount() {
        return this.mWater;
    }

    @Override
    public boolean allowCoverOnSide(ForgeDirection side, ItemStack aStack) {
        return true;
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public boolean isInputFacing(ForgeDirection side) {
        return true;
    }

    @Override
    public boolean isEnetInput() {
        return true;
    }

    @Override
    public boolean isElectric() {
        return true;
    }

    @Override
    public int rechargerSlotStartIndex() {
        return SLOT_BATTERY;
    }

    @Override
    public int dechargerSlotStartIndex() {
        return SLOT_BATTERY;
    }

    @Override
    public int rechargerSlotCount() {
        return mCharge ? BATTERY_SLOT_COUNT : 0;
    }

    @Override
    public int dechargerSlotCount() {
        return mDecharge ? BATTERY_SLOT_COUNT : 0;
    }

    // endregion Base MTE Params

    // region crop manager params

    private long powerUsage() {
        return this.maxEUInput() / 8;
    }

    private long powerUsageSecondary() {
        return this.maxEUInput() / 32;
    }

    private int getHorizontalRadius() {
        return this.getHorizontalRadius(this.mTier);
    }

    public static int getHorizontalRadius(int aTier) {
        return 3 + Math.max(0, 2 * aTier);
    }

    private int getHorizontalDiameter() {
        return this.getHorizontalRadius() * 2 + 1;
    }

    private int getVerticalRadius() {
        return 2;
    }

    private int getVerticalDiameter() {
        return getVerticalRadius() * 2 + 1;
    }

    private double getHarvestBonusChance() {
        return 0.05d * this.mTier;
    }

    // endregion crop manager params

    // region event handlers

    @Override
    public void onFirstTick(IGregTechTileEntity aBaseMetaTileEntity) {
        super.onFirstTick(aBaseMetaTileEntity);
        this.updateCropCache();
    }

    @Override
    public boolean onRightclick(final IGregTechTileEntity aBaseMetaTileEntity, final EntityPlayer aPlayer) {
        if (super.onRightclick(aBaseMetaTileEntity, aPlayer)) return true;
        this.openGui(aPlayer);
        return true;
    }

    @Override
    public void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {
        super.onPostTick(aBaseMetaTileEntity, aTick);

        if (getBaseMetaTileEntity().isServerSide()) {
            mCharge = getBaseMetaTileEntity().getStoredEU() / 2 > getBaseMetaTileEntity().getEUCapacity() / 3;
            mDecharge = getBaseMetaTileEntity().getStoredEU() < getBaseMetaTileEntity().getEUCapacity() / 3;
        }

        if (!getBaseMetaTileEntity().isServerSide() || !getBaseMetaTileEntity().isAllowedToWork()
            || (!getBaseMetaTileEntity().hasWorkJustBeenEnabled() && aTick % GLOBAL_UPDATE_RATE != 0)) return;

        if (this.getBaseMetaTileEntity()
            .getUniversalEnergyStored() < this.maxEUInput()) return;

        // update crop cache when needed or once per minute
        int cacheRefreshRate = this.mCropCache.isEmpty() ? CACHE_REFRESH_EMPTY : CACHE_REFRESH_ANY;
        if (aTick % cacheRefreshRate == 0 || this.mInvalidCache) {
            this.updateCropCache();
        }
        processSecondaryFunctions();
        harvest();
    }

    private void updateCropCache() {
        // empty out the cache before we do anything
        if (!this.mCropCache.isEmpty()) {
            this.mCropCache.clear();
        }
        final int v = this.getVerticalRadius();
        final int h = this.getHorizontalRadius();
        // get to registering
        for (int y = -v; y <= v; y++) {
            for (int x = -h; x <= h; x++) {
                for (int z = -h; z <= h; z++) {
                    TileEntity tTileEntity = getBaseMetaTileEntity().getTileEntityOffset(x, y, z);
                    if (tTileEntity instanceof ICropStickTile) {
                        this.mCropCache.add((ICropStickTile) tTileEntity);
                    }
                }
            }
        }

        this.mInvalidCache = false;
    }

    // endregion event handlers

    // region harvesting

    private boolean doesInventoryHaveSpace() {
        for (int i = SLOT_OUTPUT_START; i <= SLOT_OUTPUT_END; i++) {
            if (this.mInventory[i] == null || this.mInventory[i].stackSize < 64) {
                return true;
            }
        }
        return false;
    }

    private void harvest() {
        // if harvest isn't enabled, don't
        if (!this.mHarvestEnabled || !doesInventoryHaveSpace()) return;

        // first attempt to empty the drop overflow back into the machine
        // is empty and size, work around it
        if (!this.mDropOverflow.isEmpty()) {
            this.mDropOverflow.entrySet()
                .removeIf((overflowEntry) -> {
                    overflowEntry.setValue(tryInsertOutputStack(overflowEntry.getKey(), overflowEntry.getValue()));
                    return overflowEntry.getValue() <= 0;
                });
        }

        // if anything remains in the drop queue skip harvesting
        if (!this.mDropOverflow.isEmpty()) return;

        // else collect all the drops
        Map<ItemStack, Integer> dropTracker = new ItemStackMap<>(true);
        for (ICropStickTile crop : this.mCropCache) {
            if (crop == null) {
                this.mInvalidCache = true;
                continue;
            }
            // skip if we don't have enough power
            if (this.getBaseMetaTileEntity()
                .getUniversalEnergyStored() < this.powerUsage()) break;
            if (!crop.canHarvest()) continue;
            ArrayList<ItemStack> tHarvest = crop.harvest(1.0d + this.getHarvestBonusChance());
            if (tHarvest == null) continue;
            for (ItemStack aStack : tHarvest) {
                if (GTUtility.isStackInvalid(aStack)) continue;
                dropTracker.merge(aStack, aStack.stackSize, Integer::sum);
            }
            this.getBaseMetaTileEntity()
                .decreaseStoredEnergyUnits(this.powerUsage(), false);
        }

        // dump everything we can into the inventory
        for (Map.Entry<ItemStack, Integer> dropEntry : dropTracker.entrySet()) {
            ItemStack dropItem = dropEntry.getKey();
            int remaining = dropEntry.getValue();

            // how this can happen, idk
            if (dropItem == null) continue;

            remaining = tryInsertOutputStack(dropItem, remaining);
            if (remaining > 0) {
                this.mDropOverflow.merge(dropEntry.getKey(), remaining, Integer::sum);
            }
        }
    }

    private int tryInsertOutputStack(ItemStack aDropItem, int remaining) {
        for (int slot = SLOT_OUTPUT_START; slot <= SLOT_OUTPUT_END && remaining > 0; slot++) {
            // compute the max we can transfer at once.
            ItemStack invStack = mInventory[slot];
            int maxStackSize = Math.min(aDropItem.getMaxStackSize(), this.getInventoryStackLimit());
            int maxConsume = Math.min(maxStackSize, remaining);
            if (maxConsume <= 0) return remaining;

            // If the slot is empty or invalid just override the slot with a stack of what ever we are carrying.
            if (GTUtility.isStackInvalid(invStack)) {
                ItemStack newStack = aDropItem.copy();
                remaining -= newStack.stackSize = maxConsume;
                this.getBaseMetaTileEntity()
                    .setInventorySlotContents(slot, newStack);
            }
            // else if it's the same item type and it has space remaining, increase the existsing stack by what ever we
            // need
            else if (invStack.stackSize < maxStackSize && GTUtility.areStacksEqual(invStack, aDropItem, false)) {
                int toConsume = Math.max(0, Math.min(maxConsume, maxStackSize - invStack.stackSize));
                invStack.stackSize += toConsume;
                remaining -= toConsume;
                this.markDirty();
            }
            if (remaining <= 0) break;
        }

        // tell em how much stuff they got remaining.
        return remaining;
    }

    // endregion harvesting

    // region secondary actions

    private void processSecondaryFunctions() {
        for (ICropStickTile crop : this.mCropCache) {
            if (crop == null) {
                this.mInvalidCache = true;
                continue;
            }
            // hydration
            if (this.getBaseMetaTileEntity()
                .getUniversalEnergyStored() < this.powerUsageSecondary()) break;
            if (this.mWaterEnabled && this.applyHydration(crop, true)
                && this.getBaseMetaTileEntity()
                    .decreaseStoredEnergyUnits(powerUsageSecondary(), false)) {
                this.applyHydration(crop, false);
            }
            // weedex
            if (this.getBaseMetaTileEntity()
                .getUniversalEnergyStored() < this.powerUsageSecondary()) break;
            if (this.mWeedEXEnabled) {
                this.refillWeedEX();
                if (this.applyWeedEX(crop, true) && this.getBaseMetaTileEntity()
                    .decreaseStoredEnergyUnits(this.powerUsageSecondary(), false)) {
                    this.applyWeedEX(crop, false);
                }
            }
            // fertilizer
            if (this.getBaseMetaTileEntity()
                .getUniversalEnergyStored() < this.powerUsageSecondary()) break;
            if (this.mFertilizerEnabled && this.getBaseMetaTileEntity()
                .getUniversalEnergyStored() >= this.powerUsageSecondary()
                && this.applyFertilizer(crop, true)
                && this.getBaseMetaTileEntity()
                    .decreaseStoredEnergyUnits(powerUsageSecondary(), false)) {
                applyFertilizer(crop, false);
            }
        }

        // update them last so we aren't constantly doing extra work we'll be overriding anyway.
        updateFluidTanksForWaila();
    }

    // region water apply

    /** The max amount of water the crop manager set in a crop manager. */
    public final static int WATER_CAP = 200;
    /** The minimum threshold at which a crop manager is allowed to start adding water to a crop. */
    private final static int WATER_THRESHOLD = 180;

    private boolean applyHydration(ICropStickTile aCrop, boolean simulate) {
        if (this.getFluidAmount() == 0 || aCrop.getWaterStorage() > WATER_THRESHOLD) return false;

        int drain = Math.min(this.getFluidAmount(), WATER_CAP - aCrop.getWaterStorage());
        if (!simulate) {
            this.mWater -= drain;
        }
        return aCrop.addWater(drain, WATER_THRESHOLD, WATER_CAP, simulate);
    }

    // endregion water apply

    // region weed ex apply

    private void refillWeedEX() {
        if (this.getWeedEXAmount() >= this.getWeedEXCapacity()) return;
        for (int i = SLOT_WEEDEX_START; i <= SLOT_WEEDEX_END; i++) {
            if (isWeedEXCan(this.mInventory[i])) {
                // consume the weed-ex from the can
                this.mWeedEX += consumeWeedexFromStack(
                    this.mInventory[i],
                    this.getWeedEXCapacity() - this.getWeedEXAmount());
                // if we damaged the item beyond its limits, remove it from the mortal realm.
                if (this.mInventory[i].getMaxDamage() < this.mInventory[i].getItemDamage()) {
                    this.mInventory[i] = null;
                }
                // back out if we are full
                if (this.getWeedEXAmount() >= this.getWeedEXCapacity()) return;
            }
        }
    }

    private static int consumeWeedexFromStack(ItemStack stack, int maxConsume) {
        // check if the can is already empty
        if (stack.getItemDamage() > stack.getMaxDamage()) return 0;
        // calculate how much we can consume of the can
        int damageToAdd = Math.min(maxConsume / 10, (stack.getMaxDamage() + 1) - stack.getItemDamage());
        stack.setItemDamage(stack.getItemDamage() + damageToAdd);
        return damageToAdd * 10;
    }

    /** The maximum amount of Weed-EX the crop manager is allowed to set in a crop. */
    private static final int WEEDEX_CAP = 150;
    /** The minimum threshold at which a crop manager is allowed to start adding Weed-EX to a crop. */
    private static final int WEEDEX_THRESHOLD = 75;
    /** The amount of liquid Weed-EX consumed per application. */
    private static final int WEEDEX_COST = 10;

    public boolean applyWeedEX(ICropStickTile aCrop, boolean aSimulate) {
        if (aCrop.getWeedExStorage() > WEEDEX_THRESHOLD || this.mWeedEX < WEEDEX_COST) return false;

        if (!aSimulate) {
            this.mWeedEX -= WEEDEX_COST;
        }

        return aCrop.addWeedEx(75, WEEDEX_THRESHOLD, WEEDEX_CAP, aSimulate);
    }

    // endregion weed ex consumption

    // region fertilizer apply

    /** The maximum amount of fertilizer the crop manager is allowed to set in a crop. */
    public static final int FERTILIZER_CAP = 200;
    /** The minimum threshold at which a crop manager is allowed to start adding item fertilizers to a crop. */
    private static final int FERTILIZER_ITEM_THRESHOLD_MIN = FERTILIZER_CAP / 2;
    /** The minimum threshold at which a crop manager is allowed to start adding liquid fertilizers to a crop. */
    private static final int FERTILIZER_LIQUID_THRESHOLD = 180;

    private boolean applyFertilizer(ICropStickTile aCrop, boolean aSimulate) {
        int storedFert = aCrop.getFertilizerStorage();
        int amount = 0;
        int threshold = FERTILIZER_LIQUID_THRESHOLD;
        // always try liquid fertilizer first
        if (this.getLiquidFertilizerAmount() > 0) {
            if (storedFert > FERTILIZER_LIQUID_THRESHOLD) return false;
            // get max to consume
            int maxConsume = FERTILIZER_CAP - aCrop.getFertilizerStorage();
            amount = Math.min(this.mLiquidFertilizer, maxConsume);
            // consume if we aren't simulating
            if (!aSimulate) {
                this.mLiquidFertilizer -= amount;
            }
        } else {
            for (int i = SLOT_FERT_START; i <= SLOT_FERT_END; i++) {
                // check if the item exists
                ItemStack stack = this.mInventory[i];
                if (stack == null) continue;
                // check stack size
                if (stack.getItem() == null || stack.stackSize <= 0) {
                    this.mInventory[i] = null;
                    continue;
                }
                // check if it's a valid fertilizer
                int fertPotency = FertilizerRegistry.instance.getPotency(stack);
                if (fertPotency <= 0) continue;
                // don't apply unless at least half the fertilizer given by the item can be applied.
                // or half the max of the manager's application limit, which ever is higher
                threshold = Math.max(FERTILIZER_ITEM_THRESHOLD_MIN, FERTILIZER_CAP - (fertPotency / 2));
                if (storedFert > threshold) continue;
                // consume if we aren't simulating
                if (!aSimulate) {
                    stack.stackSize--;
                    if (stack.stackSize <= 0) {
                        this.mInventory[i] = null;
                    }
                }
                // set amount to add and bail
                amount = fertPotency;
                break;
            }
        }
        // fail if we didn't find anything
        if (amount <= 0) return false;
        // the add fertilizer call should always be a success if it reaches this point.
        return aCrop.addFertilizer(amount, threshold, FERTILIZER_CAP, aSimulate);
    }

    // endregion fertilizer apply

    // endregion secondary actions

    // region tank status

    // region water status

    public int getWaterPotency(Fluid fluid) {
        return HydrationRegistry.instance.getPotency(fluid);
    }

    public int getWaterCapacity() {
        return Math.max(1, this.mWaterCap);
    }

    public int getWaterAmount() {
        return this.mWater;
    }

    public void setWaterAmount(int amount) {
        this.mWater = amount;
        this.mWailaFluidTankInfos[0].fluid.amount = amount;
    }

    // endregion water status

    // region weed ex status

    public int getWeedEXPotency(Fluid fluid) {
        return WeedEXRegistry.instance.getPotency(fluid);
    }

    public int getWeedEXCapacity() {
        // tier * 2 cans of weed-ex
        return this.mWeedEXCap;
    }

    public int getWeedEXAmount() {
        return this.mWeedEX;
    }

    public void setWeedEXAmount(int a) {
        this.mWeedEX = a;
        this.mWailaFluidTankInfos[1].fluid.amount = a;
    }

    // endregion weed ex status

    // region liquid fertilizer status

    public int getLiquidFertilizerPotency(Fluid fluid) {
        return FertilizerRegistry.instance.getPotency(fluid);
    }

    public int getLiquidFertilizerCapacity() {
        return this.mLiquidFertilizerCap;
    }

    public int getLiquidFertilizerAmount() {
        return this.mLiquidFertilizer;
    }

    public void setLiquidFertilizerAmount(int a) {
        this.mLiquidFertilizer = a;
        this.mWailaFluidTankInfos[2].fluid.amount = a;
    }

    // endregion liquid fertilizer status

    // endregion tank status

    // region IO

    // region item IO

    @Override
    public boolean isItemValidForSlot(int aIndex, ItemStack aStack) {
        return allowPutStack(aIndex, aStack);
    }

    public static boolean isFertilizerStack(ItemStack aStack) {
        return FertilizerRegistry.instance.isRegistered(aStack);
    }

    public static boolean isWeedEXCan(ItemStack aStack) {
        if (aStack == null || aStack.getItem() == null) return false;
        return CropsNHItemList.weedEX.getItem() == aStack.getItem();
    }

    public static boolean isBattery(ItemStack aStack) {
        return GTModHandler.isElectricItem(aStack);
    }

    @Override
    public boolean allowPullStack(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, ForgeDirection side,
        ItemStack aStack) {
        return aStack != null && aIndex >= SLOT_OUTPUT_START && aIndex <= SLOT_OUTPUT_END;
    }

    @Override
    public boolean allowPutStack(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, ForgeDirection side,
        ItemStack aStack) {
        return allowPutStack(aIndex, aStack);
    }

    public static boolean allowPutStack(int aIndex, ItemStack aStack) {
        if (aStack != null) {
            if (isFertilizerStack(aStack)) {
                return aIndex >= SLOT_FERT_START && aIndex <= SLOT_FERT_END;
            } else if (isWeedEXCan(aStack)) {
                return aIndex >= SLOT_WEEDEX_START && aIndex <= SLOT_WEEDEX_END;
            } else if (isBattery(aStack)) {
                return aIndex == SLOT_BATTERY;
            }
        }
        return false;
    }

    // endregion item IO

    // region fluid IO

    private static class FluidCheckResult {

        public final int cur;
        public final int cap;
        public final int potency;
        public final IntConsumer setter;

        public FluidCheckResult(int cur, int cap, int potency, IntConsumer setter) {
            this.cur = cur;
            this.cap = cap;
            this.potency = potency;
            this.setter = setter;
        }
    }

    private FluidCheckResult canFill(Fluid fluid) {
        int potency, cur, cap;
        IntConsumer setter;
        if ((potency = this.getWaterPotency(fluid)) > 0) {
            cur = this.getWaterAmount();
            cap = this.getWaterCapacity();
            setter = this::setWaterAmount;
        } else if ((potency = this.getWeedEXPotency(fluid)) > 0) {
            cur = this.getWeedEXAmount();
            cap = this.getWeedEXCapacity();
            setter = this::setWeedEXAmount;
        } else if ((potency = this.getLiquidFertilizerPotency(fluid)) > 0) {
            cur = this.getLiquidFertilizerAmount();
            cap = this.getLiquidFertilizerCapacity();
            setter = this::setLiquidFertilizerAmount;
        } else {
            return null;
        }
        // abort if overflow would occur
        if (cur > cap - potency) {
            return null;
        }
        return new FluidCheckResult(cur, cap, potency, setter);
    }

    @Override
    public boolean canFill(ForgeDirection side, Fluid fluid) {
        return canFill(fluid) != null;
    }

    @Override
    public int fill(ForgeDirection side, FluidStack resource, boolean doFill) {
        return this.fill(resource, doFill);
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        if (resource == null || resource.getFluid() == null || resource.amount <= 0) return 0;

        // find what we need to output to
        FluidCheckResult result = canFill(resource.getFluid());
        if (result == null) return 0;

        // calc how much we need to transfer
        int toConsume = Math.min(resource.amount, (result.cap - result.cur) / result.potency);
        if (doFill) {
            result.setter.accept(result.cur + toConsume * result.potency);
            this.markDirty();
        }
        return toConsume;
    }

    // prevent all output of fluids
    @Override
    public boolean isLiquidOutput(ForgeDirection side) {
        return false;
    }

    @Override
    public boolean canDrain(ForgeDirection side, Fluid aFluid) {
        return false;
    }

    @Override
    public FluidStack drain(ForgeDirection side, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection side, FluidStack aFluid, boolean doDrain) {
        return null;
    }

    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return null;
    }

    // report all three internal tanks so Waila shows a bar per fluid instead of the single empty water tank.
    // each tank only accepts a fixed set of fluids, so a representative fluid is used for display.
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection side) {
        return this.mWailaFluidTankInfos;
    }

    // endregion fluid io

    // endregion IO

    // region rendering

    /*
     * @Override public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) { if (aSide ==
     * aFacing) return 118+(aRedstone?8:0); if (GT_Utility.getOppositeSide(aSide) == aFacing) return
     * 113+(aRedstone?8:0); int tIndex = 128+(aRedstone?8:0); switch (aFacing) { case 0: return tIndex+64; case 1:
     * return tIndex+32; case 2: switch (aSide) { case 0: return tIndex+32; case 1: return tIndex+32; case 4: return
     * tIndex+16; case 5: return tIndex+48; } case 3: switch (aSide) { case 0: return tIndex+64; case 1: return
     * tIndex+64; case 4: return tIndex+48; case 5: return tIndex+16; } case 4: switch (aSide) { case 0: return
     * tIndex+16; case 1: return tIndex+16; case 2: return tIndex+48; case 3: return tIndex+16; } case 5: switch (aSide)
     * { case 0: return tIndex+48; case 1: return tIndex+48; case 2: return tIndex+16; case 3: return tIndex+48; } }
     * return tIndex; }
     */

    @Override
    public ITexture[][][] getTextureSet(final ITexture[] aTextures) {
        final ITexture[][][] rTextures = new ITexture[10][17][];
        for (byte i = -1; i < 16; i++) {
            rTextures[0][i + 1] = this.getFront(i);
            rTextures[1][i + 1] = this.getBack(i);
            rTextures[2][i + 1] = this.getBottom(i);
            rTextures[3][i + 1] = this.getTop(i);
            rTextures[4][i + 1] = this.getSides(i);
            rTextures[5][i + 1] = this.getFront(i);
            rTextures[6][i + 1] = this.getBack(i);
            rTextures[7][i + 1] = this.getBottom(i);
            rTextures[8][i + 1] = this.getTop(i);
            rTextures[9][i + 1] = this.getSides(i);
        }
        return rTextures;
    }

    @Override
    public ITexture[] getTexture(final IGregTechTileEntity aBaseMetaTileEntity, final ForgeDirection side,
        final ForgeDirection facing, final int aColorIndex, final boolean aActive, final boolean aRedstone) {
        if (side == ForgeDirection.DOWN || side == ForgeDirection.UP) {
            return this.mTextures[3][aColorIndex + 1];
        } else {
            return this.mTextures[4][aColorIndex + 1];
        }
        /*
         * return this.mTextures[(aActive ? 5 : 0) + (side == facing ? 0 : aSide == GT_Utility.getOppositeSide(aFacing)
         * ? 1 : side == ForgeDirection.DOWN ? 2 : side == ForgeDirection.UP ? 3 : 4)][aColorIndex + 1];
         */
    }

    public ITexture[] getFront(final byte aColor) {
        return new ITexture[] { Textures.BlockIcons.MACHINE_CASINGS[this.mTier][aColor + 1],
            TextureFactory.of(CropsNHBlockTextures.Casing_CropHarvester_Cutter) };
    }

    public ITexture[] getBack(final byte aColor) {
        return new ITexture[] { Textures.BlockIcons.MACHINE_CASINGS[this.mTier][aColor + 1],
            TextureFactory.of(CropsNHBlockTextures.Casing_CropHarvester_Cutter) };
    }

    public ITexture[] getBottom(final byte aColor) {
        return new ITexture[] { Textures.BlockIcons.MACHINE_CASINGS[this.mTier][aColor + 1],
            TextureFactory.of(CropsNHBlockTextures.Casing_CropHarvester_Boxes) };
    }

    public ITexture[] getTop(final byte aColor) {
        return new ITexture[] { Textures.BlockIcons.MACHINE_CASINGS[this.mTier][aColor + 1],
            TextureFactory.of(CropsNHBlockTextures.Casing_CropHarvester_Boxes) };
    }

    public ITexture[] getSides(final byte aColor) {
        return new ITexture[] { Textures.BlockIcons.MACHINE_CASINGS[this.mTier][aColor + 1],
            TextureFactory.of(CropsNHBlockTextures.Casing_CropHarvester_Cutter) };
    }

    // endregion rendering

    // region nbt

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        // save fluid tanks
        aNBT.setInteger("mWater", this.mWater);
        aNBT.setInteger("mWeedEx", this.mWeedEX);
        aNBT.setInteger("mLiquidFertilizer", this.mLiquidFertilizer);
        // save modes
        aNBT.setBoolean("mHarvestEnabled", this.mHarvestEnabled);
        aNBT.setBoolean("mWeedExEnabled", this.mWeedEXEnabled);
        aNBT.setBoolean("mWaterEnabled", this.mWaterEnabled);
        aNBT.setBoolean("mFertilizerEnabled", this.mFertilizerEnabled);
        // save the item overflow queue
        if (!this.mDropOverflow.isEmpty()) {
            aNBT.setTag("mDropOverflow", NBTHelper.saveItemStackMap(this.mDropOverflow));
        }
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        // load tanks
        this.setWaterAmount(NBTHelper.getInteger(aNBT, "mWater", 0));
        this.setWeedEXAmount(NBTHelper.getInteger(aNBT, "mWeedEx", 0));
        this.setLiquidFertilizerAmount(NBTHelper.getInteger(aNBT, "mLiquidFertilizer", 0));
        // load modes
        this.mHarvestEnabled = NBTHelper.getBoolean(aNBT, "mHarvestEnabled", true);
        // versioning for upgrade to new system
        if (NBTHelper.getBoolean(aNBT, "mModeAlternative", false)) {
            this.mWeedEXEnabled = NBTHelper.getBoolean(aNBT, "mModeAlternative", false);
            this.mWaterEnabled = NBTHelper.getBoolean(aNBT, "mModeAlternative", false);
            this.mFertilizerEnabled = NBTHelper.getBoolean(aNBT, "mModeAlternative", false);
        }
        this.mWeedEXEnabled = NBTHelper.getBoolean(aNBT, "mWeedExEnabled", false);
        this.mWaterEnabled = NBTHelper.getBoolean(aNBT, "mWaterEnabled", false);
        this.mFertilizerEnabled = NBTHelper.getBoolean(aNBT, "mFertilizerEnabled", false);
        // load the item overflow queue
        if (aNBT.hasKey("mDropOverflow", Constants.NBT.TAG_LIST)) {
            NBTHelper.loadItemStackMap(
                this.mDropOverflow,
                aNBT.getTagList("mDropOverflow", Constants.NBT.TAG_COMPOUND),
                Integer::sum);
        }
    }

    // endregion nbt

    // region ui

    @Override
    public String[] getDescription() {
        return ArrayUtils.addAll(
            this.mDescriptionArray,
            CropsNHUtils.getMachineTypeText("cropManager"),
            StatCollector.translateToLocal("cropsnh_tooltip.cropManager.tooltip.0"),
            StatCollector.translateToLocalFormatted(
                "cropsnh_tooltip.cropManager.tooltip.1",
                TooltipHelper.euText(this.powerUsage())),
            StatCollector.translateToLocalFormatted(
                "cropsnh_tooltip.cropManager.tooltip.2",
                TooltipHelper.euText(this.powerUsageSecondary())),
            StatCollector.translateToLocalFormatted(
                "cropsnh_tooltip.cropManager.tooltip.3",
                TooltipHelper.coloredText(formatNumber(this.getVerticalRadius()), EnumChatFormatting.WHITE)),
            StatCollector.translateToLocalFormatted(
                "cropsnh_tooltip.cropManager.tooltip.4",
                TooltipHelper.tierText(formatNumber(this.getHorizontalDiameter())),
                TooltipHelper.tierText(formatNumber(this.getVerticalDiameter()))),
            StatCollector.translateToLocalFormatted(
                "cropsnh_tooltip.cropManager.tooltip.5",
                TooltipHelper.coloredText(
                    TooltipHelper.percentageFormat.format(this.getHarvestBonusChance()),
                    TooltipHelper.EFF_COLOR)),
            StatCollector.translateToLocalFormatted(
                "cropsnh_tooltip.cropManager.tooltip.6",
                TooltipHelper.fluidText(this.getWaterCapacity())),
            StatCollector.translateToLocalFormatted(
                "cropsnh_tooltip.cropManager.tooltip.7",
                TooltipHelper.fluidText(this.getLiquidFertilizerCapacity())),
            StatCollector.translateToLocalFormatted(
                "cropsnh_tooltip.cropManager.tooltip.8",
                TooltipHelper.fluidText(this.getWeedEXCapacity())));
    }

    @Override
    protected boolean useMui2() {
        return true;
    }

    @Override
    public ModularPanel buildUI(PosGuiData data, PanelSyncManager syncManager, UISettings uiSettings) {
        return new MTECropManagerGui(this).build(data, syncManager, uiSettings);
    }

    // endregion ui
}
