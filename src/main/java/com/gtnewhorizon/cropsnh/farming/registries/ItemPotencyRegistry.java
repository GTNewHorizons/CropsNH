package com.gtnewhorizon.cropsnh.farming.registries;

import java.util.Comparator;
import java.util.stream.Collectors;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.Nullable;

import com.gtnewhorizon.cropsnh.api.IItemPotencyRegistry;
import com.gtnewhorizon.cropsnh.api.IPotencyData;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.DebugHelper;
import com.gtnewhorizon.cropsnh.utility.MetaMap;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemPotencyRegistry implements IItemPotencyRegistry {

    /**
     * A list of fertilizers along with their potency.
     */
    public MetaMap<Item, IPotencyData> registry = new MetaMap<>();

    @Override
    public void register(Item item, int meta, IPotencyData data) {
        if (data.getPotency() <= 0) throw new IllegalArgumentException("potency must be greater then 0");
        if (data.getUnitsConsumedPerApplication() <= 0)
            throw new IllegalArgumentException("Units per application must be greater than 0");
        this.registry.putIfAbsent(item, meta, data, true);
    }

    @Override
    public boolean isRegistered(ItemStack stack) {
        if (stack == null || stack.getItem() == null) return false;
        return this.isRegistered(stack.getItem(), CropsNHUtils.getItemMeta(stack));
    }

    @Override
    public boolean isRegistered(Item item, int meta) {
        if (item == null) return false;
        return this.registry.containsKey(item, meta);
    }

    @Override
    public @Nullable IPotencyData getPotency(ItemStack item) {
        return this.registry.getOrDefault(item.getItem(), CropsNHUtils.getItemMeta(item), null);
    }

    @Override
    public @Nullable IPotencyData getPotency(Item item, int meta) {
        return this.registry.getOrDefault(item, meta, null);
    }

    public String dumpCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(DebugHelper.makeCSVLine("Item", "Potency", "Max Storage", "Consumed per Application"));
        sb.append(System.lineSeparator());
        sb.append(
            this.registry.getStream()
                .sorted(Comparator.comparing(a -> a.value))
                .map(e -> {
                    StringBuilder sbm = new StringBuilder();
                    sbm.append(
                        GameRegistry.findUniqueIdentifierFor(e.key)
                            .toString());
                    if (e.meta != null) {
                        sbm.append(":");
                        sbm.append(e.meta);
                    }
                    return DebugHelper.makeCSVLine(e.value, sbm.toString());
                })
                .collect(Collectors.joining(System.lineSeparator())));
        return sb.toString();
    }
}
