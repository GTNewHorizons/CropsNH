package com.gtnewhorizon.cropsnh.tileentity.singleblock;

import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.formatNumber;
import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.getFluidUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.apache.commons.lang3.ArrayUtils;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.farming.registries.FertilizerRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.HydrationRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.WeedEXRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHBlockTextures;
import com.gtnewhorizon.cropsnh.init.CropsNHUITextures;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.NBTHelper;
import com.gtnewhorizon.gtnhlib.util.map.ItemStackMap;
import com.gtnewhorizons.modularui.api.drawable.UITexture;
import com.gtnewhorizons.modularui.api.screen.ModularWindow;
import com.gtnewhorizons.modularui.api.screen.UIBuildContext;
import com.gtnewhorizons.modularui.common.widget.CycleButtonWidget;
import com.gtnewhorizons.modularui.common.widget.FakeSyncWidget;
import com.gtnewhorizons.modularui.common.widget.ProgressBar;
import com.gtnewhorizons.modularui.common.widget.SlotGroup;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.Textures;
import gregtech.api.gui.modularui.GTUIInfos;
import gregtech.api.gui.modularui.GTUITextures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.modularui.IAddUIWidgets;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.MTETieredMachineBlock;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GTUtility;
import gregtech.api.util.tooltip.TooltipHelper;
import gtPlusPlus.core.util.math.MathUtils;
import gtPlusPlus.xmod.gregtech.api.gui.GTPPUITextures;

public class MTECropManager extends MTETieredMachineBlock implements IAddUIWidgets {

    private static final int WEEDEX_SLOT_COUNT = 2;
    private static final int FERTILIZER_SLOT_COUNT = 4;
    private static final int OUTPUT_SLOT_COUNT = 15;
    private static final int TOTAL_SLOT_COUNT = WEEDEX_SLOT_COUNT + FERTILIZER_SLOT_COUNT + OUTPUT_SLOT_COUNT;

    private static final int SLOT_WEEDEX_START = 0;
    private static final int SLOT_WEEDEX_END = SLOT_WEEDEX_START - 1 + WEEDEX_SLOT_COUNT;
    private static final int SLOT_FERT_START = SLOT_WEEDEX_END + 1;
    private static final int SLOT_FERT_END = SLOT_FERT_START - 1 + FERTILIZER_SLOT_COUNT;
    private static final int SLOT_OUTPUT_START = SLOT_FERT_END + 1;
    private static final int SLOT_OUTPUT_END = SLOT_OUTPUT_START - 1 + OUTPUT_SLOT_COUNT;

    // run ever 2.5s, refresh cache every other run when empty, else wait 60s to refresh cache
    private final static int GLOBAL_UPDATE_RATE = 2 * 20 + 10;
    private final static int CACHE_REFRESH_EMPTY = GLOBAL_UPDATE_RATE * 2;
    private final static int CACHE_REFRESH_ANY = GLOBAL_UPDATE_RATE * 12;

    public boolean mHarvestEnabled = true;
    public boolean mWeedEXEnabled = false;
    public boolean mFertilizerEnabled = false;
    public boolean mWaterEnabled = false;

    private int mWater;
    private final int mWaterCap;
    private int mWeedEX;
    private final int mWeedEXCap;
    private int mLiquidFertilizer;
    private final int mLiquidFertilizerCap;

    private final HashSet<ICropStickTile> mCropCache = new HashSet<>();
    private boolean mInvalidCache = false;
    private final ItemStackMap<Integer> mDropOverflow = new ItemStackMap<>(true);

    public MTECropManager(final int aID, final int aTier) {
        super(
            aID,
            String.format("basicmachine.cropManager.tier.%02d", aTier),
            StatCollector.translateToLocalFormatted(Reference.MOD_ID + "_tooltip.cropManager.name." + aTier),
            aTier,
            TOTAL_SLOT_COUNT,
            StatCollector.translateToLocal("cropsnh_tooltip.cropManager.description"));
        this.mWater = 0;
        this.mWaterCap = this.mTier * 32000;
        this.mWeedEX = 0;
        this.mWeedEXCap = this.mTier * 750 * 2;
        this.mLiquidFertilizer = 0;
        this.mLiquidFertilizerCap = this.mTier * 144 * 64 * 4;

    }

    // region TE creation

    public MTECropManager(final String aName, final int aTier, final String[] aDescription,
        final ITexture[][][] aTextures) {
        super(aName, aTier, TOTAL_SLOT_COUNT, aDescription, aTextures);
        this.mWater = 0;
        this.mWaterCap = this.mTier * 32000;
        this.mWeedEX = 0;
        this.mWeedEXCap = this.mTier * 750 * 2;
        this.mLiquidFertilizer = 0;
        this.mLiquidFertilizerCap = this.mTier * 144 * 64 * 4;

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
        return 8;
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

    // endregion Base MTE Params

    // region crop manager params

    public long powerUsage() {
        return this.maxEUInput() / 8;
    }

    public long powerUsageSecondary() {
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

    private int getHarvestBonusChance() {
        return this.mTier * 5;
    }

    private static int getBonusStackIncrease(int gain) {
        return gain / 10;
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
        GTUIInfos.openGTTileEntityUI(aBaseMetaTileEntity, aPlayer);
        return true;
    }

    @Override
    public void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {
        super.onPostTick(aBaseMetaTileEntity, aTick);
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

    public boolean doesInventoryHaveSpace() {
        for (int i = SLOT_OUTPUT_START; i < this.getSizeInventory(); i++) {
            if (this.mInventory[i] == null || this.mInventory[i].stackSize < 64) {
                return true;
            }
        }
        return false;
    }

    public void harvest() {
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
            ArrayList<ItemStack> aHarvest = crop.harvest();
            if (aHarvest == null) continue;
            for (ItemStack aStack : aHarvest) {
                if (GTUtility.isStackInvalid(aStack)) continue;
                if (this.getHarvestBonusChance() > MathUtils.randInt(1, 100)) {
                    aStack.stackSize += getBonusStackIncrease(
                        crop.getSeed()
                            .getStats()
                            .getGain());
                }
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
            if (remaining >= 0) {
                this.mDropOverflow.merge(dropEntry.getKey(), dropEntry.getValue(), Integer::sum);
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

    public void processSecondaryFunctions() {
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
    }

    // region water apply

    public final static int WATER_CAP = 200;
    private final static int WATER_THRESHOLD = 180;

    public boolean applyHydration(ICropStickTile aCrop, boolean simulate) {
        if (this.getFluidAmount() == 0 || aCrop.getWaterStorage() > WATER_THRESHOLD) return false;

        int drain = Math.min(this.getFluidAmount(), WATER_CAP - aCrop.getWaterStorage());
        if (!simulate) {
            this.mWater -= drain;
        }
        return aCrop.addWater(drain, WATER_THRESHOLD, WATER_CAP, simulate);
    }

    // endregion water apply

    // region weed ex apply

    public void refillWeedEX() {
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

    private static final int WEEDEX_CAP = 150;
    private static final int WEEDEX_THRESHOLD = 75;
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

    public static final int FERTILIZER_CAP = 200;
    private static final int FERTILIZER_ITEM_THRESHOLD_MIN = FERTILIZER_CAP / 2;
    private static final int FERTILIZER_LIQUID_THRESHOLD = 180;

    public boolean applyFertilizer(ICropStickTile aCrop, boolean aSimulate) {
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
    }

    // endregion liquid fertilizer status

    // endregion tank status

    // region IO

    // region item IO

    private static boolean isWeedEXCan(ItemStack aStack) {
        if (aStack == null || aStack.getItem() == null) return false;
        return CropsNHItemList.weedEX.getItem() == aStack.getItem();
    }

    @Override
    public boolean allowPullStack(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, ForgeDirection side,
        ItemStack aStack) {
        return aStack != null && aIndex >= SLOT_OUTPUT_START && aIndex <= SLOT_OUTPUT_END;
    }

    @Override
    public boolean allowPutStack(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, ForgeDirection side,
        ItemStack aStack) {
        if (aStack != null) {
            if (FertilizerRegistry.instance.isRegistered(aStack)) {
                return aIndex >= SLOT_FERT_START && aIndex <= SLOT_FERT_END;
            } else if (isWeedEXCan(aStack)) {
                return aIndex >= SLOT_WEEDEX_START && aIndex <= SLOT_WEEDEX_END;
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
        public final Consumer<Integer> setter;

        public FluidCheckResult(int cur, int cap, int potency, Consumer<Integer> setter) {
            this.cur = cur;
            this.cap = cap;
            this.potency = potency;
            this.setter = setter;
        }
    }

    private FluidCheckResult canFill(Fluid fluid) {
        int potency, cur, cap;
        Consumer<Integer> setter;
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
        this.mWater = NBTHelper.getInteger(aNBT, "mWater", 0);
        this.mWeedEX = NBTHelper.getInteger(aNBT, "mWeedEx", 0);
        this.mLiquidFertilizer = NBTHelper.getInteger(aNBT, "mLiquidFertilizer", 0);
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
        if (aNBT.hasKey("mDropOverflow", Data.NBTType._list)) {
            NBTHelper.loadItemStackMap(
                this.mDropOverflow,
                aNBT.getTagList("mDropOverflow", Data.NBTType._object),
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
                TooltipHelper.tierText(formatNumber(this.getVerticalRadius()))),
            StatCollector.translateToLocalFormatted(
                "cropsnh_tooltip.cropManager.tooltip.5",
                TooltipHelper.tierText(formatNumber(this.getHarvestBonusChance()))),
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

    public static final UITexture PROGRESSBAR_WATER = GTUITextures.PROGRESSBAR_BOILER_WATER;
    public static final UITexture PROGRESSBAR_WEED_EX = UITexture
        .fullImage(Reference.MOD_ID, "gui/progressbar/cropmanager_weed_ex");
    public static final UITexture PROGRESSBAR_LIQUID_FERTILIZER = UITexture
        .fullImage(Reference.MOD_ID, "gui/progressbar/cropmanager_liquid_fertilizer");

    public static final UITexture BUTTON_OVERLAY_TOGGLE_WATER = UITexture
        .fullImage(Reference.MOD_ID, "gui/overlay_button/water_toggle");
    public static final UITexture BUTTON_OVERLAY_TOGGLE_WEED_EX = UITexture
        .fullImage(Reference.MOD_ID, "gui/overlay_button/weed_ex_toggle");
    public static final UITexture BUTTON_OVERLAY_TOGGLE_FERTILIZER = UITexture
        .fullImage(Reference.MOD_ID, "gui/overlay_button/fertilizer_toggle");
    public static final UITexture BUTTON_OVERLAY_TOGGLE_HARVEST = UITexture
        .fullImage(Reference.MOD_ID, "gui/overlay_button/harvest_toggle");

    private static void addToggleWidget(ModularWindow.Builder builder, Supplier<Boolean> supplier,
        Consumer<Boolean> consumer, UITexture texture, String baseTooltip, int x, int y) {
        builder.widget(
            new CycleButtonWidget().setToggle(supplier, consumer)
                .setTexture(texture)
                .addTooltip(state -> StatCollector.translateToLocal(baseTooltip + state))
                .setBackground(GTUITextures.BUTTON_STANDARD)
                .setPos(x, y)
                .setSize(18, 18));
    }

    private static void addTankBar(ModularWindow.Builder builder, UITexture texture, Supplier<Integer> amountGetter,
        Supplier<Integer> capacityGetter, String tooltipFormat, Consumer<Integer> amountSetter, int x, int y) {
        builder.widget(
            new ProgressBar().setTexture(GTPPUITextures.PROGRESSBAR_BOILER_EMPTY, texture, 54)
                .setDirection(ProgressBar.Direction.UP)
                .setProgress(
                    () -> Math.max(0.0f, Math.min(1.0f, (float) amountGetter.get() / (float) capacityGetter.get())))
                .setSynced(false, false)
                .dynamicTooltip(
                    () -> Collections.singletonList(
                        StatCollector.translateToLocalFormatted(
                            tooltipFormat,
                            formatNumber(amountGetter.get()),
                            formatNumber(capacityGetter.get()),
                            getFluidUnit())))
                .setUpdateTooltipEveryTick(true)
                .setPos(x, y)
                .setSize(10, 54))
            .widget(new FakeSyncWidget.IntegerSyncer(amountGetter, amountSetter));
    }

    @Override
    public void addUIWidgets(ModularWindow.Builder builder, UIBuildContext buildContext) {

        // region toggles

        // hydration toggle
        addToggleWidget(
            builder,
            () -> mWaterEnabled,
            val -> mWaterEnabled = val,
            BUTTON_OVERLAY_TOGGLE_WATER,
            Reference.MOD_ID + "_tooltip.cropManager.water.",
            7,
            63);

        // weed ex toggle
        addToggleWidget(
            builder,
            () -> mWeedEXEnabled,
            val -> mWeedEXEnabled = val,
            BUTTON_OVERLAY_TOGGLE_WEED_EX,
            Reference.MOD_ID + "_tooltip.cropManager.weedEX.",
            27,
            63);

        // fertilization toggle
        addToggleWidget(
            builder,
            () -> mFertilizerEnabled,
            val -> mFertilizerEnabled = val,
            BUTTON_OVERLAY_TOGGLE_FERTILIZER,
            Reference.MOD_ID + "_tooltip.cropManager.fertilizer.",
            47,
            63);

        // harvest toggle
        addToggleWidget(
            builder,
            () -> mHarvestEnabled,
            val -> mHarvestEnabled = val,
            BUTTON_OVERLAY_TOGGLE_HARVEST,
            Reference.MOD_ID + "_tooltip.cropManager.harvest.",
            67,
            63);

        // endregion toggles

        // region slots

        // weed ex slots
        builder.widget(
            SlotGroup.ofItemHandler(inventoryHandler, 2)
                .startFromSlot(SLOT_WEEDEX_START)
                .endAtSlot(SLOT_WEEDEX_END)
                .applyForWidget(
                    widget -> widget.setFilter(MTECropManager::isWeedEXCan)
                        .setBackground(getGUITextureSet().getItemSlot(), CropsNHUITextures.OVERLAY_SLOT_WEED_EX))
                .build()
                .setPos(7, 7));

        // fertilizer slots
        builder.widget(
            SlotGroup.ofItemHandler(inventoryHandler, 2)
                .startFromSlot(SLOT_FERT_START)
                .endAtSlot(SLOT_FERT_END)
                .applyForWidget(
                    widget -> widget.setFilter(FertilizerRegistry.instance::isRegistered)
                        .setBackground(getGUITextureSet().getItemSlot(), CropsNHUITextures.OVERLAY_SLOT_FERTILIZER))
                .build()
                .setPos(7, 25));

        // output slots
        builder.widget(
            SlotGroup.ofItemHandler(inventoryHandler, 5)
                .startFromSlot(SLOT_OUTPUT_START)
                .endAtSlot(SLOT_OUTPUT_END)
                .canInsert(false)
                .build()
                .setPos(79, 7));

        // endregion slots

        // region tank bars

        // water
        addTankBar(
            builder,
            PROGRESSBAR_WATER,
            this::getWaterAmount,
            this::getWaterCapacity,
            Reference.MOD_ID + "_tooltip.cropManager.water",
            this::setWaterAmount,
            47,
            7);

        // weed-ex
        addTankBar(
            builder,
            PROGRESSBAR_WEED_EX,
            this::getWeedEXAmount,
            this::getWeedEXCapacity,
            Reference.MOD_ID + "_tooltip.cropManager.weedEX",
            this::setWeedEXAmount,
            57,
            7);

        // liquid fertilizer
        addTankBar(
            builder,
            PROGRESSBAR_LIQUID_FERTILIZER,
            this::getLiquidFertilizerAmount,
            this::getLiquidFertilizerCapacity,
            Reference.MOD_ID + "_tooltip.cropManager.liquidFertilizer",
            this::setLiquidFertilizerAmount,
            67,
            7);

        // endregion tank bars
    }

    // endregion ui
}
