package com.gtnewhorizon.cropsnh.farming.registries;

import java.util.Comparator;
import java.util.stream.Collectors;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.IFertilizerRegistry;
import com.gtnewhorizon.cropsnh.utility.DebugHelper;
import com.gtnewhorizon.cropsnh.utility.MetaMap;

import cpw.mods.fml.common.registry.GameRegistry;

public class FertilizerRegistry implements IFertilizerRegistry {

    public static final FertilizerRegistry instance = new FertilizerRegistry();

    /**
     * A list of fertilizers along with their potency.
     */
    public MetaMap<Item, Integer> fertilisers = new MetaMap<>();

    @Override
    public void register(Item item, int meta, int potency) {
        this.fertilisers.putIfAbsent(item, meta, potency, true);
    }

    @Override
    public boolean isRegistered(ItemStack stack) {
        if (stack == null || stack.getItem() == null) return false;
        return this.isRegistered(stack.getItem(), stack.getItemDamage());
    }

    @Override
    public boolean isRegistered(Item item, int meta) {
        if (item == null) return false;
        return this.fertilisers.containsKey(item, meta);
    }

    @Override
    public int getPotnecy(ItemStack item) {
        return this.getPotnecy(item.getItem(), item.getItemDamage());
    }

    @Override
    public int getPotnecy(Item item, int meta) {
        return this.fertilisers.getOrDefault(item, meta, 0);
    }

    /**
     * @return a csv dump of all the registered fertilizers
     */
    public String dump() {
        StringBuilder sb = new StringBuilder();
        sb.append(DebugHelper.makeCSVLine("Potency", "Item"));
        sb.append(System.lineSeparator());
        if (this.fertilisers.isEmpty()) {
            sb.append("empty");
        } else {
            sb.append(
                this.fertilisers.getStream()
                    .sorted(Comparator.comparing(a -> a.value))
                    .map(e -> {
                        StringBuilder sbm = new StringBuilder();
                        sbm.append(e.value);
                        sbm.append(",");
                        sbm.append(
                            GameRegistry.findUniqueIdentifierFor(e.key)
                                .toString());
                        if (e.meta != null) {
                            sbm.append(":");
                            sbm.append(e.meta);
                        }
                        return sbm.toString();
                    })
                    .collect(Collectors.joining(System.lineSeparator())));
        }
        return sb.toString();
    }
}
