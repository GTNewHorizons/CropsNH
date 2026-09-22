package com.gtnewhorizon.cropsnh.farming.registries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import org.apache.commons.lang3.NotImplementedException;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.reference.Reference;

public class CompoundSoilList implements ISoilList {

    private final String name;
    private final ArrayList<ISoilList> soils;

    public CompoundSoilList(String name, ISoilList... soilList) {
        if (name == null || name.length() <= 0) throw new IllegalArgumentException("Name cannot be null or empty");
        this.name = name;
        this.soils = new ArrayList<>(Arrays.asList(soilList));
    }

    public void add(ISoilList soilList) {
        this.soils.add(soilList);
    }

    @Override
    public String getId() {
        return this.name + "("
            + soils.stream()
                .map(ISoilList::getId)
                .collect(Collectors.joining("+"))
            + ")";
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
    public ISoilList registerBlock(BlockWithMeta... soils) {
        throw new NotImplementedException("Compound soil list cannot contain soils of their own.");
    }

    @Override
    public ISoilList registerOreDict(String... oreDicts) {
        throw new NotImplementedException("Compound soil list cannot contain soils of their own.");
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

    @Override
    public String getUnlocalizedItemTooltip() {
        return Reference.MOD_ID + "_soilList." + this.name + ".tooltip";
    }

    @Override
    public String getUnlocalizedWrongSoilMessage() {
        return Reference.MOD_ID + "_soilList." + this.name + ".wrongSoil";
    }
}
