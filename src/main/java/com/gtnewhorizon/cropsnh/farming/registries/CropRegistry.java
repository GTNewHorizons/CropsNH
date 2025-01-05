package com.gtnewhorizon.cropsnh.farming.registries;

import java.util.HashMap;
import java.util.LinkedList;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropRegistry;
import com.gtnewhorizon.cropsnh.items.ItemGenericSeed;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.MetaMap;

public class CropRegistry implements ICropRegistry {

    public final static CropRegistry instance = new CropRegistry();
    // START AT ONE SO THAT 0 CAN BE ASSUMED AS NO PARENT FOR BREEDING CHECKS
    private int cropIdCounter = 1;

    /**
     * Contains the mappings for stuff like vanilla wheat seeds.
     */
    private MetaMap<Item, ICropCard> alternateSeedList = new MetaMap<>();
    /**
     * Contain the id mappings for crops, used during nbt rehydration.
     */
    private final HashMap<String, ICropCard> cropRegistry = new HashMap<>();

    private final LinkedList<ICropCard> registrationOrder = new LinkedList<>();

    public LinkedList<ICropCard> getAllInRegistrationOrder() {
        return this.registrationOrder;
    }

    @Override
    public @Nullable ICropCard get(String id) {
        if (id == null) return null;
        return cropRegistry.getOrDefault(id, null);
    }

    @Override
    public ICropCard fromAlternateSeed(ItemStack stack) {
        return alternateSeedList.getOrDefault(stack.getItem(), stack.getItemDamage(), null);
    }

    @Override
    public @Nullable ICropCard get(ItemStack stack) {
        if (stack == null) return null;
        // check if it's a generic seed first.
        if (stack.getItem() instanceof ItemGenericSeed && stack.hasTagCompound()
            && stack.getTagCompound()
                .hasKey(Names.NBT.crop, 8)) {
            return cropRegistry.getOrDefault(
                stack.getTagCompound()
                    .getString(Names.NBT.crop),
                null);
        }
        // else just run a check on the seed registry
        return fromAlternateSeed(stack);
    }

    @Override
    public void register(ICropCard crop) {
        if (this.cropRegistry.containsKey(crop.getId())) {
            throw new RuntimeException("Duplicate crop id, crop not registered: " + crop.getId());
        }
        this.cropRegistry.put(crop.getId(), crop);
        this.registrationOrder.add(crop);
        crop.setNumericId(cropIdCounter++);
        registerAlternateSeeds(alternateSeedList, crop);
    }

    public void registerIcons(IIconRegister register) {
        for (ICropCard crop : this.cropRegistry.values()) {
            crop.registerSprites(register);
        }
    }

    @Override
    public void updateAlternateSeedList() {
        MetaMap<Item, ICropCard> newList = new MetaMap<>();
        for (ICropCard crop : cropRegistry.values()) {
            registerAlternateSeeds(newList, crop);
        }
        alternateSeedList = newList;
    }

    private static void registerAlternateSeeds(MetaMap<Item, ICropCard> map, ICropCard crop) {
        Iterable<ItemStack> alternateSeeds = crop.getAlternateSeeds();
        if (alternateSeeds == null) return;
        for (ItemStack alternateSeed : alternateSeeds) {
            map.putIfAbsent(alternateSeed.getItem(), alternateSeed.getItemDamage(), crop);
        }
    }
}
