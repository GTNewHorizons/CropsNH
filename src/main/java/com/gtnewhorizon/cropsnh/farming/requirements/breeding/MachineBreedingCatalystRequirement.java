package com.gtnewhorizon.cropsnh.farming.requirements.breeding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.IMachineBreedingRequirement;
import com.gtnewhorizon.gtnhlib.util.map.ItemStackMap;

import cpw.mods.fml.common.LoaderException;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GTUtility;

public class MachineBreedingCatalystRequirement implements IMachineBreedingRequirement {

    public void validate() {
        if (this.oreDictionaries.isEmpty() && this.items.isEmpty()) {
            throw new IllegalStateException("Empty breeding machine catalyst requirement detected!");
        }
    }

    private final HashMap<String, Integer> oreDictionaries = new HashMap<>();
    private final ItemStackMap<Integer> items = new ItemStackMap<>(true);

    public MachineBreedingCatalystRequirement() {}

    public MachineBreedingCatalystRequirement addOreDict(String oreDict, int count) {
        this.oreDictionaries.put(oreDict, count);
        return this;
    }

    public MachineBreedingCatalystRequirement addItem(ItemStack... args) {
        for (ItemStack arg : args) {
            if (GTUtility.isStackInvalid(arg)) {
                throw new LoaderException(
                    "attempted to add an invalid stack to a machine breeding catalyst requirement");
            }
            this.items.put(arg, arg.stackSize);
        }
        return this;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean canBreed(ArrayList<ICropCard> parents, IGregTechTileEntity te, ItemStack[] catalysts,
        int[] consumptionTracker) {
        for (int i = 0; i < catalysts.length; i++) {
            ItemStack stack = catalysts[i];

            // if stack is bad or if we can't consume it, abort early
            if (GTUtility.isStackInvalid(stack) || stack.stackSize - consumptionTracker[i] <= 0) continue;

            // Ore dict check
            int toConsume = this.getOreDictCache()
                .getOrDefault(stack, -1);
            if (toConsume >= 0 && stack.stackSize - toConsume >= 0) {
                consumptionTracker[i] += toConsume;
                return true;
            }

            // specific item check
            toConsume = this.items.getOrDefault(stack, -1);
            if (toConsume >= 0 && stack.stackSize - toConsume >= 0) {
                consumptionTracker[i] += toConsume;
                return true;
            }
        }
        return false;
    }

    private ItemStackMap<Integer> cachedOreDictResult = null;

    private ItemStackMap<Integer> getOreDictCache() {
        if (this.cachedOreDictResult != null) return this.cachedOreDictResult;
        ItemStackMap<Integer> ret = new ItemStackMap<>(true);
        for (Map.Entry<String, Integer> kv : this.oreDictionaries.entrySet()) {
            for (ItemStack stack : OreDictionary.getOres(kv.getKey(), false)) {
                stack = stack.copy();
                stack.stackSize = kv.getValue();
                ret.put(stack, kv.getValue());
            }
        }
        return this.cachedOreDictResult = ret;
    }

    @Override
    public @Nullable List<ItemStack> getMachineOnlyCatalystsForNEI() {
        ArrayList<ItemStack> ret = new ArrayList<>();
        ret.addAll(
            this.items.entrySet()
                .stream()
                .map(x -> {
                    ItemStack temp = x.getKey()
                        .copy();
                    temp.stackSize = x.getValue();
                    return temp;
                })
                .collect(Collectors.toList()));
        // might contain duplicates but we always deduplicate after, so it should work out™
        ret.addAll(
            this.getOreDictCache()
                .entrySet()
                .stream()
                .map(x -> {
                    ItemStack temp = x.getKey()
                        .copy();
                    temp.stackSize = x.getValue();
                    return temp;
                })
                .collect(Collectors.toList()));
        return ret;
    }

}
