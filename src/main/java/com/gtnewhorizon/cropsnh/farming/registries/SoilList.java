package com.gtnewhorizon.cropsnh.farming.registries;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.MetaSet;

import cpw.mods.fml.common.registry.GameRegistry;

public class SoilList implements ISoilList {

    private final String name;
    private final MetaSet<Block> validSoils = new MetaSet<>();

    /**
     * Which registry the soil type is linked to
     */
    private final SoilRegistry registry;
    /**
     * True if the list is the "global" list for the register.
     */
    private final boolean isGlobal;

    SoilList(String name, SoilRegistry registry, boolean isGlobal) {
        this.name = name;
        this.registry = registry;
        this.isGlobal = isGlobal;
    }

    @Override
    public boolean isRegistered(Block block, int meta) {
        return this.validSoils.contains(block, meta);
    }

    @Override
    public ISoilList registerBlock(BlockWithMeta... soils) {
        // abort early if param bad
        if (soils == null) {
            if (CropsNHUtils.shouldPanicIfNullFound())
                throw new IllegalArgumentException("Passed an null array to registerSoil!");
            return this;
        }
        // this ensures that any registered soil is also registered to the global tracker
        if (!this.isGlobal) this.registry.registerGlobalSoil(soils);
        // then add the soil regularly
        for (BlockWithMeta soil : soils) {
            if (soil == null || soil.getBlock() == null) {
                if (CropsNHUtils.shouldPanicIfNullFound())
                    throw new IllegalArgumentException("Passed an null array to registerSoil!");
                continue;
            }
            this.validSoils.add(soil.getBlock(), soil.ignoreMeta() ? OreDictionary.WILDCARD_VALUE : soil.getMeta());
        }
        return this;
    }

    @Override
    public ISoilList registerOreDict(String... oreDicts) {
        // abort early if param bad
        if (oreDicts == null) {
            if (CropsNHUtils.shouldPanicIfNullFound())
                throw new IllegalArgumentException("Passed an null array to registerOreDict!");
            return this;
        }

        for (String oreDictName : oreDicts) {
            // abort is entry is invalid, panic if value is null
            if (oreDictName == null) {
                if (CropsNHUtils.shouldPanicIfNullFound())
                    throw new IllegalArgumentException("Passed an array with null oreId to registerOreDict!");
                continue;
            }
            // get candidates, panic if ore dict has no entries
            List<ItemStack> candidates = OreDictionary.getOres(oreDictName, false);
            if (candidates == null) {
                if (CropsNHUtils.shouldPanicIfNullFound())
                    throw new IllegalArgumentException("Passed an array with null oreId to registerOreDict!");
                continue;
            } ;
            boolean foundBlock = false;
            for (ItemStack stack : candidates) {
                // Abort if stack is valid
                if (stack == null || stack.getItem() == null) continue;
                // Check if candidate can be translated to block, and that the translated block isn't an air block
                // since crop sticks are explicitly not allowed on those.
                Block block = CropsNHUtils.getBlockFromItem(stack);
                if (block == null || block.getMaterial() == Material.air) continue;
                // all good, register as soil
                this.registerBlock(new BlockWithMeta(block, CropsNHUtils.getItemMeta(stack)));
                foundBlock = true;
            }
            if (!foundBlock && CropsNHUtils.shouldPanicIfNullFound()) {
                throw new IllegalArgumentException(
                    String.format("Requested ore dict \"%s\" did not contain a valid block!", oreDictName));
            }
        }

        return this;
    }

    @Override
    public String getId() {
        return this.name;
    }

    public void dump(StringBuilder sb) {
        // note that it's empty if it is empty
        if (this.validSoils.isEmpty()) {
            sb.append("# empty");
            return;
        }
        sb.append(
            this.validSoils.getStream()
                .map(e -> {
                    StringBuilder sbm = new StringBuilder();
                    sbm.append("- ");
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

    @Override
    public Stream<ItemStack> getNEIItemList() {
        return this.validSoils.getStream()
            .map(s -> {
                Item item = CropsNHUtils.getItemFromBlock(s.key);
                if (item == null) return null;
                int meta = s.meta == null ? OreDictionary.WILDCARD_VALUE : s.meta;
                return new ItemStack(item, 1, meta);
            })
            .filter(Objects::nonNull);
    }
}
