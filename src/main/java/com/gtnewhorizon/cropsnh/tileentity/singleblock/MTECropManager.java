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

    /** How often the crop manager runs its work loop. */
    private final static int GLOBAL_UPDATE_RATE = 2 * 20 + 10;
    /** How often the crop manager updates its crop cache when said cache is empty. */
    private final static int CACHE_REFRESH_EMPTY = GLOBAL_UPDATE_RATE * 2;
    /** How often the crop manager updates its crop cache when said cache contains crops. */
    private final static int CACHE_REFRESH_ANY = GLOBAL_UPDATE_RATE * 12;

    /** Whether the crop manager is allowed to harvest crops. */
    public boolean harvestEnabled = true;
    /** Whether the crop manager is allowed to apply Weed-EX to crops. */
    public boolean weedEXEnabled = false;
    /** Whether the crop manager is allowed to fertilize crops. */
    public boolean fertilizerEnabled = false;
    /** Whether the crop manager is allowed to water crops. */
    public boolean waterEnabled = false;

    public boolean charge = false;
    public boolean decharge = false;

    /** Water potency stored in the crop manager. */
    private int waterStored = 0;
    /** The maximum amount of water potency that can be stored in the crop manager. */
    private final int waterCap;
    /** Weed-EX potency stored in the crop manager. */
    private int weedEXStored = 0;
    /** The maximum amount of Weed-EX potency that can be stored in the crop manager. */
    private final int weedEXCap;
    /** Liquid fertilizer potency stored in the crop manager. */
    private int liquidFertilizerStored = 0;
    /** The maximum amount of liquid fertilizer potency that can be stored in the crop manager. */
    private final int liquidFertilizerCap;

    /** A cache of all known crops within the crop manager's range. */
    private final HashSet<ICropStickTile> cropCache = new HashSet<>();
    /** Whether the crop cache appears to contain invalid data. */
    private boolean isCacheInvalid = false;
    /** A holder for drops when a single harvest cycle would overflow the manager's inventory. */
    private final ItemStackMap<Integer> dropOverflow = new ItemStackMap<>(true);
    /** A cache holding synchronized tank information for waila. */
    private final FluidTankInfo[] wailaFluidTankInfos;

    public MTECropManager(final int id, final int tier) {
        super(
            id,
            String.format("basicmachine.cropManager.tier.%02d", tier),
            StatCollector.translateToLocalFormatted(Reference.MOD_ID + "_tooltip.cropManager.name." + tier),
            tier,
            TOTAL_SLOT_COUNT,
            StatCollector.translateToLocal("cropsnh_tooltip.cropManager.description"));
        this.waterCap = calcWaterCap();
        this.weedEXCap = calcWeedEXCap();
        this.liquidFertilizerCap = calcLiquidFertilizerCap();
        this.wailaFluidTankInfos = getDefaultWailaFluidTankInfos();
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
            new FluidTankInfo(new FluidStack(FluidRegistry.WATER, this.waterStored), this.waterCap),
            new FluidTankInfo(CropsNHUtils.getWeedEXFluid(this.weedEXStored), this.weedEXCap),
            new FluidTankInfo(new FluidStack(CropsNHFluids.fertilizer, this.weedEXStored), this.liquidFertilizerCap) };
    }

    // region TE creation

    private MTECropManager(final String name, final int tier, final String[] description,
        final ITexture[][][] textures) {
        super(name, tier, TOTAL_SLOT_COUNT, description, textures);
        this.waterCap = calcWaterCap();
        this.weedEXCap = calcWeedEXCap();
        this.liquidFertilizerCap = calcLiquidFertilizerCap();
        this.wailaFluidTankInfos = getDefaultWailaFluidTankInfos();
    }

    private void updateFluidTanksForWaila() {
        this.wailaFluidTankInfos[0].fluid.amount = this.waterStored;
        this.wailaFluidTankInfos[1].fluid.amount = this.weedEXStored;
        this.wailaFluidTankInfos[2].fluid.amount = this.liquidFertilizerStored;
    }

    @Override
    public MetaTileEntity newMetaEntity(IGregTechTileEntity tileEntity) {
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
        return this.waterStored;
    }

    @Override
    public boolean allowCoverOnSide(ForgeDirection side, ItemStack stack) {
        return true;
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer player) {
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
        return charge ? BATTERY_SLOT_COUNT : 0;
    }

    @Override
    public int dechargerSlotCount() {
        return decharge ? BATTERY_SLOT_COUNT : 0;
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
        return getHorizontalRadius(this.mTier);
    }

    public static int getHorizontalRadius(int tier) {
        return 3 + Math.max(0, 2 * tier);
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
    public void onFirstTick(IGregTechTileEntity baseMetaTileEntity) {
        super.onFirstTick(baseMetaTileEntity);
        this.updateCropCache(baseMetaTileEntity);
    }

    @Override
    public boolean onRightclick(final IGregTechTileEntity baseMetaTileEntity, final EntityPlayer player) {
        if (super.onRightclick(baseMetaTileEntity, player)) return true;
        this.openGui(player);
        return true;
    }

    @Override
    public void onPostTick(IGregTechTileEntity baseMetaTileEntity, long tick) {
        super.onPostTick(baseMetaTileEntity, tick);

        boolean isServerSide = CropsNHUtils.isServer();
        if (isServerSide) {
            charge = baseMetaTileEntity.getStoredEU() / 2 > baseMetaTileEntity.getEUCapacity() / 3;
            decharge = baseMetaTileEntity.getStoredEU() < baseMetaTileEntity.getEUCapacity() / 3;
        }

        if (!isServerSide || !baseMetaTileEntity.isAllowedToWork()
            || (!baseMetaTileEntity.hasWorkJustBeenEnabled() && tick % GLOBAL_UPDATE_RATE != 0)) return;

        if (baseMetaTileEntity.getUniversalEnergyStored() < this.maxEUInput()) return;

        // update crop cache when needed or once per minute
        int cacheRefreshRate = this.cropCache.isEmpty() ? CACHE_REFRESH_EMPTY : CACHE_REFRESH_ANY;
        if (tick % cacheRefreshRate == 0 || this.isCacheInvalid) {
            this.updateCropCache(baseMetaTileEntity);
        }
        processSecondaryFunctions(baseMetaTileEntity);
        harvest(baseMetaTileEntity);
    }

    private void updateCropCache(IGregTechTileEntity baseMetaTileEntity) {
        // empty out the cache before we do anything
        if (!this.cropCache.isEmpty()) {
            this.cropCache.clear();
        }
        final int v = this.getVerticalRadius();
        final int h = this.getHorizontalRadius();
        // get to registering
        for (int y = -v; y <= v; y++) {
            for (int x = -h; x <= h; x++) {
                for (int z = -h; z <= h; z++) {
                    TileEntity tileEntity = baseMetaTileEntity.getTileEntityOffset(x, y, z);
                    if (tileEntity instanceof ICropStickTile cropTE) {
                        this.cropCache.add(cropTE);
                    }
                }
            }
        }

        this.isCacheInvalid = false;
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

    private void harvest(IGregTechTileEntity baseMetaTileEntity) {
        // if harvest isn't enabled, don't
        if (!this.harvestEnabled || !doesInventoryHaveSpace()) return;

        // first attempt to empty the drop overflow back into the machine
        // is empty and size, work around it
        if (!this.dropOverflow.isEmpty()) {
            this.dropOverflow.entrySet()
                .removeIf((overflowEntry) -> {
                    overflowEntry.setValue(
                        tryInsertOutputStack(baseMetaTileEntity, overflowEntry.getKey(), overflowEntry.getValue()));
                    return overflowEntry.getValue() <= 0;
                });
        }

        // if anything remains in the drop queue skip harvesting
        if (!this.dropOverflow.isEmpty()) return;

        // else collect all the drops
        Map<ItemStack, Integer> dropTracker = new ItemStackMap<>(true);
        for (ICropStickTile crop : this.cropCache) {
            if (crop == null) {
                this.isCacheInvalid = true;
                continue;
            }
            // skip if we don't have enough power
            if (baseMetaTileEntity.getUniversalEnergyStored() < this.powerUsage()) break;
            if (!crop.canHarvest()) continue;
            ArrayList<ItemStack> harvestDrops = crop.harvest(1.0d + this.getHarvestBonusChance());
            if (harvestDrops == null) continue;
            for (ItemStack stack : harvestDrops) {
                if (GTUtility.isStackInvalid(stack)) continue;
                dropTracker.merge(stack, stack.stackSize, Integer::sum);
            }
            baseMetaTileEntity.decreaseStoredEnergyUnits(this.powerUsage(), false);
        }

        // dump everything we can into the inventory
        for (Map.Entry<ItemStack, Integer> dropEntry : dropTracker.entrySet()) {
            ItemStack dropItem = dropEntry.getKey();
            int remaining = dropEntry.getValue();

            // how this can happen, idk
            if (dropItem == null) continue;

            remaining = tryInsertOutputStack(baseMetaTileEntity, dropItem, remaining);
            if (remaining > 0) {
                this.dropOverflow.merge(dropEntry.getKey(), remaining, Integer::sum);
            }
        }
    }

    private int tryInsertOutputStack(IGregTechTileEntity baseMetaTileEntity, ItemStack dropItem, int remaining) {
        for (int slot = SLOT_OUTPUT_START; slot <= SLOT_OUTPUT_END && remaining > 0; slot++) {
            // compute the max we can transfer at once.
            ItemStack invStack = mInventory[slot];
            int maxStackSize = Math.min(dropItem.getMaxStackSize(), this.getInventoryStackLimit());
            int maxConsume = Math.min(maxStackSize, remaining);
            if (maxConsume <= 0) return remaining;

            // If the slot is empty or invalid just override the slot with a stack of what ever we are carrying.
            if (GTUtility.isStackInvalid(invStack)) {
                ItemStack newStack = dropItem.copy();
                remaining -= newStack.stackSize = maxConsume;
                baseMetaTileEntity.setInventorySlotContents(slot, newStack);
            }
            // else if it's the same item type and it has space remaining, increase the existsing stack by what ever we
            // need
            else if (invStack.stackSize < maxStackSize && GTUtility.areStacksEqual(invStack, dropItem, false)) {
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

    private void processSecondaryFunctions(IGregTechTileEntity baseMetaTileEntity) {
        for (ICropStickTile crop : this.cropCache) {
            if (crop == null) {
                this.isCacheInvalid = true;
                continue;
            }
            // hydration
            if (baseMetaTileEntity.getUniversalEnergyStored() < this.powerUsageSecondary()) break;
            if (this.waterEnabled && this.applyHydration(crop, true)
                && baseMetaTileEntity.decreaseStoredEnergyUnits(powerUsageSecondary(), false)) {
                this.applyHydration(crop, false);
            }
            // weedex
            if (baseMetaTileEntity.getUniversalEnergyStored() < this.powerUsageSecondary()) break;
            if (this.weedEXEnabled) {
                this.refillWeedEX();
                if (this.applyWeedEX(crop, true)
                    && baseMetaTileEntity.decreaseStoredEnergyUnits(this.powerUsageSecondary(), false)) {
                    this.applyWeedEX(crop, false);
                }
            }
            // fertilizer
            if (baseMetaTileEntity.getUniversalEnergyStored() < this.powerUsageSecondary()) break;
            if (this.fertilizerEnabled && baseMetaTileEntity.getUniversalEnergyStored() >= this.powerUsageSecondary()
                && this.applyFertilizer(crop, true)
                && baseMetaTileEntity.decreaseStoredEnergyUnits(powerUsageSecondary(), false)) {
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

    private boolean applyHydration(ICropStickTile crop, boolean simulate) {
        if (this.getFluidAmount() == 0 || crop.getWaterStorage() > WATER_THRESHOLD) return false;

        int drain = Math.min(this.getFluidAmount(), WATER_CAP - crop.getWaterStorage());
        if (!simulate) {
            this.waterStored -= drain;
        }
        return crop.addWater(drain, WATER_THRESHOLD, WATER_CAP, simulate);
    }

    // endregion water apply

    // region weed ex apply

    private void refillWeedEX() {
        if (this.getWeedEXAmount() >= this.getWeedEXCapacity()) return;
        for (int i = SLOT_WEEDEX_START; i <= SLOT_WEEDEX_END; i++) {
            if (isWeedEXCan(this.mInventory[i])) {
                // consume the weed-ex from the can
                this.weedEXStored += consumeWeedexFromStack(
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

    public boolean applyWeedEX(ICropStickTile crop, boolean simulate) {
        if (crop.getWeedExStorage() > WEEDEX_THRESHOLD || this.weedEXStored < WEEDEX_COST) return false;

        if (!simulate) {
            this.weedEXStored -= WEEDEX_COST;
        }

        return crop.addWeedEx(75, WEEDEX_THRESHOLD, WEEDEX_CAP, simulate);
    }

    // endregion weed ex consumption

    // region fertilizer apply

    /** The maximum amount of fertilizer the crop manager is allowed to set in a crop. */
    public static final int FERTILIZER_CAP = 200;
    /** The minimum threshold at which a crop manager is allowed to start adding item fertilizers to a crop. */
    private static final int FERTILIZER_ITEM_THRESHOLD_MIN = FERTILIZER_CAP / 2;
    /** The minimum threshold at which a crop manager is allowed to start adding liquid fertilizers to a crop. */
    private static final int FERTILIZER_LIQUID_THRESHOLD = 180;

    private boolean applyFertilizer(ICropStickTile crop, boolean simulate) {
        int storedFert = crop.getFertilizerStorage();
        int amount = 0;
        int threshold = FERTILIZER_LIQUID_THRESHOLD;
        // always try liquid fertilizer first
        if (this.getLiquidFertilizerAmount() > 0) {
            if (storedFert > FERTILIZER_LIQUID_THRESHOLD) return false;
            // get max to consume
            int maxConsume = FERTILIZER_CAP - crop.getFertilizerStorage();
            amount = Math.min(this.liquidFertilizerStored, maxConsume);
            // consume if we aren't simulating
            if (!simulate) {
                this.liquidFertilizerStored -= amount;
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
                if (!simulate) {
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
        return crop.addFertilizer(amount, threshold, FERTILIZER_CAP, simulate);
    }

    // endregion fertilizer apply

    // endregion secondary actions

    // region tank status

    // region water status

    public int getWaterPotency(Fluid fluid) {
        return HydrationRegistry.instance.getPotency(fluid);
    }

    public int getWaterCapacity() {
        return Math.max(1, this.waterCap);
    }

    public int getWaterAmount() {
        return this.waterStored;
    }

    public void setWaterAmount(int amount) {
        this.waterStored = amount;
        this.wailaFluidTankInfos[0].fluid.amount = amount;
    }

    // endregion water status

    // region weed ex status

    public int getWeedEXPotency(Fluid fluid) {
        return WeedEXRegistry.instance.getPotency(fluid);
    }

    public int getWeedEXCapacity() {
        // tier * 2 cans of weed-ex
        return this.weedEXCap;
    }

    public int getWeedEXAmount() {
        return this.weedEXStored;
    }

    public void setWeedEXAmount(int a) {
        this.weedEXStored = a;
        this.wailaFluidTankInfos[1].fluid.amount = a;
    }

    // endregion weed ex status

    // region liquid fertilizer status

    public int getLiquidFertilizerPotency(Fluid fluid) {
        return FertilizerRegistry.instance.getPotency(fluid);
    }

    public int getLiquidFertilizerCapacity() {
        return this.liquidFertilizerCap;
    }

    public int getLiquidFertilizerAmount() {
        return this.liquidFertilizerStored;
    }

    public void setLiquidFertilizerAmount(int a) {
        this.liquidFertilizerStored = a;
        this.wailaFluidTankInfos[2].fluid.amount = a;
    }

    // endregion liquid fertilizer status

    // endregion tank status

    // region IO

    // region item IO

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return allowPutStack(index, stack);
    }

    public static boolean isFertilizerStack(ItemStack stack) {
        return FertilizerRegistry.instance.isRegistered(stack);
    }

    public static boolean isWeedEXCan(ItemStack stack) {
        if (stack == null || stack.getItem() == null) return false;
        return CropsNHItemList.weedEX.getItem() == stack.getItem();
    }

    public static boolean isBattery(ItemStack stack) {
        return GTModHandler.isElectricItem(stack);
    }

    @Override
    public boolean allowPullStack(IGregTechTileEntity baseMetaTileEntity, int index, ForgeDirection side,
        ItemStack stack) {
        return stack != null && index >= SLOT_OUTPUT_START && index <= SLOT_OUTPUT_END;
    }

    @Override
    public boolean allowPutStack(IGregTechTileEntity baseMetaTileEntity, int index, ForgeDirection side,
        ItemStack stack) {
        return allowPutStack(index, stack);
    }

    public static boolean allowPutStack(int index, ItemStack stack) {
        if (stack != null) {
            if (isFertilizerStack(stack)) {
                return index >= SLOT_FERT_START && index <= SLOT_FERT_END;
            } else if (isWeedEXCan(stack)) {
                return index >= SLOT_WEEDEX_START && index <= SLOT_WEEDEX_END;
            } else if (isBattery(stack)) {
                return index == SLOT_BATTERY;
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
    public boolean canDrain(ForgeDirection side, Fluid fluid) {
        return false;
    }

    @Override
    public FluidStack drain(ForgeDirection side, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection side, FluidStack fluid, boolean doDrain) {
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
        return this.wailaFluidTankInfos;
    }

    // endregion fluid io

    // endregion IO

    // region rendering

    @Override
    public ITexture[][][] getTextureSet(final ITexture[] textures) {
        final ITexture[][][] newTextures = new ITexture[10][17][];
        for (byte i = -1; i < 16; i++) {
            newTextures[0][i + 1] = this.getFront(i);
            newTextures[1][i + 1] = this.getBack(i);
            newTextures[2][i + 1] = this.getBottom(i);
            newTextures[3][i + 1] = this.getTop(i);
            newTextures[4][i + 1] = this.getSides(i);
            newTextures[5][i + 1] = this.getFront(i);
            newTextures[6][i + 1] = this.getBack(i);
            newTextures[7][i + 1] = this.getBottom(i);
            newTextures[8][i + 1] = this.getTop(i);
            newTextures[9][i + 1] = this.getSides(i);
        }
        return newTextures;
    }

    @Override
    public ITexture[] getTexture(final IGregTechTileEntity baseMetaTileEntity, final ForgeDirection side,
        final ForgeDirection facing, final int colorIndex, final boolean active, final boolean redstone) {
        if (side == ForgeDirection.DOWN || side == ForgeDirection.UP) {
            return this.mTextures[3][colorIndex + 1];
        } else {
            return this.mTextures[4][colorIndex + 1];
        }
    }

    public ITexture[] getFront(final byte color) {
        return new ITexture[] { Textures.BlockIcons.MACHINE_CASINGS[this.mTier][color + 1],
            TextureFactory.of(CropsNHBlockTextures.Casing_CropHarvester_Cutter) };
    }

    public ITexture[] getBack(final byte color) {
        return new ITexture[] { Textures.BlockIcons.MACHINE_CASINGS[this.mTier][color + 1],
            TextureFactory.of(CropsNHBlockTextures.Casing_CropHarvester_Cutter) };
    }

    public ITexture[] getBottom(final byte color) {
        return new ITexture[] { Textures.BlockIcons.MACHINE_CASINGS[this.mTier][color + 1],
            TextureFactory.of(CropsNHBlockTextures.Casing_CropHarvester_Boxes) };
    }

    public ITexture[] getTop(final byte color) {
        return new ITexture[] { Textures.BlockIcons.MACHINE_CASINGS[this.mTier][color + 1],
            TextureFactory.of(CropsNHBlockTextures.Casing_CropHarvester_Boxes) };
    }

    public ITexture[] getSides(final byte color) {
        return new ITexture[] { Textures.BlockIcons.MACHINE_CASINGS[this.mTier][color + 1],
            TextureFactory.of(CropsNHBlockTextures.Casing_CropHarvester_Cutter) };
    }

    // endregion rendering

    // region nbt

    @Override
    public void saveNBTData(NBTTagCompound nbt) {
        // save fluid tanks
        nbt.setInteger("mWater", this.waterStored);
        nbt.setInteger("mWeedEx", this.weedEXStored);
        nbt.setInteger("mLiquidFertilizer", this.liquidFertilizerStored);
        // save modes
        nbt.setBoolean("mHarvestEnabled", this.harvestEnabled);
        nbt.setBoolean("mWeedExEnabled", this.weedEXEnabled);
        nbt.setBoolean("mWaterEnabled", this.waterEnabled);
        nbt.setBoolean("mFertilizerEnabled", this.fertilizerEnabled);
        // save the item overflow queue
        if (!this.dropOverflow.isEmpty()) {
            nbt.setTag("mDropOverflow", NBTHelper.saveItemStackMap(this.dropOverflow));
        }
    }

    @Override
    public void loadNBTData(NBTTagCompound nbt) {
        // load tanks
        this.setWaterAmount(NBTHelper.getInteger(nbt, "mWater", 0));
        this.setWeedEXAmount(NBTHelper.getInteger(nbt, "mWeedEx", 0));
        this.setLiquidFertilizerAmount(NBTHelper.getInteger(nbt, "mLiquidFertilizer", 0));
        // load modes
        this.harvestEnabled = NBTHelper.getBoolean(nbt, "mHarvestEnabled", true);
        // versioning for upgrade to new system
        if (NBTHelper.getBoolean(nbt, "mModeAlternative", false)) {
            this.weedEXEnabled = NBTHelper.getBoolean(nbt, "mModeAlternative", false);
            this.waterEnabled = NBTHelper.getBoolean(nbt, "mModeAlternative", false);
            this.fertilizerEnabled = NBTHelper.getBoolean(nbt, "mModeAlternative", false);
        }
        this.weedEXEnabled = NBTHelper.getBoolean(nbt, "mWeedExEnabled", false);
        this.waterEnabled = NBTHelper.getBoolean(nbt, "mWaterEnabled", false);
        this.fertilizerEnabled = NBTHelper.getBoolean(nbt, "mFertilizerEnabled", false);
        // load the item overflow queue
        if (nbt.hasKey("mDropOverflow", Constants.NBT.TAG_LIST)) {
            NBTHelper.loadItemStackMap(
                this.dropOverflow,
                nbt.getTagList("mDropOverflow", Constants.NBT.TAG_COMPOUND),
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
