package com.gtnewhorizon.cropsnh.farming.registries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.ISoilList;

public class CompoundSoilList implements ISoilList {

    private final ArrayList<ISoilList> soils;

    CompoundSoilList(Collection<ISoilList> soilList) {
        this.soils = new ArrayList<>(soilList);
    }

    CompoundSoilList(ISoilList... soilList) {
        this.soils = new ArrayList<>(Arrays.asList(soilList));
    }

    CompoundSoilList(int initialCapacity) {
        this.soils = new ArrayList<>(initialCapacity);
    }

    public void add(ISoilList soilList) {
        this.soils.add(soilList);
    }

    @Override
    public String getId() {
        return soils.stream()
            .map(s -> s.getId())
            .collect(Collectors.joining("+"));
    }

    @Override
    public boolean isRegistered(Block block, int meta) {
        for (ISoilList soil : this.soils) {
            if (soil.isRegistered(block, meta)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void registerSoil(BlockWithMeta... soils) {
        // empty on purpose
    }

    @Override
    public void dump(StringBuilder sb) {
        // note that it's empty if it is empty
        if (this.soils.isEmpty()) {
            sb.append("# empty");
            return;
        }
        int i = 0;
        sb.append("# compound list");
        for (ISoilList soil : this.soils) {
            sb.append("# inner list ");
            sb.append(i++);
            sb.append(System.lineSeparator());
            soil.dump(sb);
            sb.append(System.lineSeparator());
        }
        // trim excess newline
        sb.delete(
            sb.length() - System.lineSeparator()
                .length(),
            sb.length());
    }

    @Override
    public Stream<ItemStack> getNEIItemList() {
        return this.soils.stream()
            .flatMap(ISoilList::getNEIItemList);
    }
}
