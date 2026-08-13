package com.gtnewhorizon.cropsnh.farming.registries;

import java.util.stream.Collectors;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.IBootProtectionRegistry;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ICropsNHBootProtectionHandler;
import com.gtnewhorizon.cropsnh.farming.bootprotection.ConstantBootProtectionHandler;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.DebugHelper;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.MetaMap;

public class BootProtectionRegistry implements IBootProtectionRegistry {

    /** The value that represents no protection being given. */
    public static final float NO_PROTECTION = 0.0F;
    /** The value that represents the point at which all effects should be prevented. */
    public static final float FULL_PROTECTION = 1.0F;
    /** A default handler to provide full immunity at base. */
    public static final ICropsNHBootProtectionHandler FULL_PROTECTION_HANDLER = new ConstantBootProtectionHandler(
        FULL_PROTECTION);

    /** The main instance for the registry. */
    public static final BootProtectionRegistry instance = new BootProtectionRegistry();

    /** The map of item -> handler */
    private final MetaMap<Item, ICropsNHBootProtectionHandler> registry = new MetaMap<>();

    public BootProtectionRegistry() {}

    @Override
    public void register(final Item item, final int meta, final ICropsNHBootProtectionHandler handler) {
        if (item == null) {
            if (CropsNHUtils.shouldPanicIfNullFound()) {
                throw new IllegalStateException("Attempted to add a null item to a boot protection registry!");
            } else {
                try {
                    throw new Exception("CROPS NH ATTEMPTED TO ADD NULL ITEM TO BOOT PROTECTION REGISTRY");
                } catch (Exception e) {
                    LogHelper.warn(e.getMessage());
                    e.printStackTrace();
                }
                return;
            }
        }
        if (handler == null) {
            throw new IllegalArgumentException("handler cannot be null!s");
        }
        this.registry.put(item, meta, handler);
    }

    @Override
    public boolean registerIfAbsent(final Item item, final int meta, final ICropsNHBootProtectionHandler handler) {
        if (item == null) {
            if (CropsNHUtils.shouldPanicIfNullFound()) {
                throw new IllegalStateException("Attempted to add a null item to a boot protection registry!");
            } else {
                try {
                    throw new Exception("CROPS NH ATTEMPTED TO ADD NULL ITEM TO BOOT PROTECTION REGISTRY");
                } catch (Exception e) {
                    LogHelper.warn(e.getMessage());
                    e.printStackTrace();
                }
                return false;
            }
        }
        if (handler == null) {
            throw new IllegalArgumentException("handler cannot be null!s");
        }
        return this.registry.putIfAbsent(item, meta, handler, false);
    }

    @Override
    public float getProtection(final ItemStack stack, final EntityLivingBase entity, final ICropStickTile cropTE) {
        // invalid items get no protection
        if (CropsNHUtils.isStackInvalid(stack)) return NO_PROTECTION;

        // no handler, no protection
        final ICropsNHBootProtectionHandler handler = this.registry
            .get(stack.getItem(), CropsNHUtils.getItemMeta(stack));
        if (handler == null) return NO_PROTECTION;

        return handler.getProtection(stack, entity, cropTE);
    }

    /**
     * Registers a handler providing full protection while wearing the given boots.
     *
     * @param item The item for the boots.
     * @param meta The meta value of the boots or {@link net.minecraftforge.oredict.OreDictionary#WILDCARD_VALUE} to
     *             ignore meta values.
     */
    public void registerFullProtectionHandler(final Item item, final int meta) {
        this.register(item, meta, FULL_PROTECTION_HANDLER);
    }

    /**
     * Registers a handler providing full protection while wearing the given boots except when a handler already exists.
     *
     * @param item The item being worn.
     * @param meta The meta value of the boots or {@link net.minecraftforge.oredict.OreDictionary#WILDCARD_VALUE} to
     *             ignore meta values.
     * @return True if the handler was registered.
     */
    public boolean registerFullProtectionHandlerIfAbsent(final Item item, final int meta) {
        return this.registerIfAbsent(item, meta, FULL_PROTECTION_HANDLER);
    }

    /**
     * @return A csv dump of the registry's contents for NEI dump purposes.
     */
    public String dump() {
        return DebugHelper.makeCSVLine("Item", "Handler Type", "Parameters") + System.lineSeparator()
            + this.registry.getStream()
                .map(
                    x -> DebugHelper.makeCSVLine(
                        DebugHelper.dumpStack(new ItemStack(x.key, 1, x.meta == null ? 0 : x.meta), true)) + ","
                        + x.value.getDumpText())
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
