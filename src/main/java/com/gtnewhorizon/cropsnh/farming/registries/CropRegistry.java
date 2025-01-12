package com.gtnewhorizon.cropsnh.farming.registries;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropRegistry;
import com.gtnewhorizon.cropsnh.api.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.items.ItemGenericSeed;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.DebugHelper;
import com.gtnewhorizon.cropsnh.utility.MetaMap;

import cpw.mods.fml.common.registry.GameRegistry;

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
    public boolean isAlternateSeed(ItemStack stack) {
        if (stack.getItem() instanceof ItemGenericSeed || stack.getItem() == null) return false;
        return this.alternateSeedList.containsKey(stack.getItem(), stack.getItemDamage());
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
            map.put(alternateSeed.getItem(), alternateSeed.getItemDamage(), crop);
        }
    }

    /**
     * @return a csv dump of all the alternate seeds
     */
    public String dumpAlternateSeeds() {
        StringBuilder csv = new StringBuilder();
        csv.append(DebugHelper.makeCSVLine("Item", "Meta", "CropCard"));
        csv.append(System.lineSeparator());
        if (this.alternateSeedList.isEmpty()) {
            csv.append("empty");
        } else {
            csv.append(
                this.alternateSeedList.getStream()
                    .sorted(Comparator.comparing(e -> e.meta == null ? -1 : e.meta))
                    .sorted(
                        Comparator.comparing(
                            e -> GameRegistry.findUniqueIdentifierFor(e.key)
                                .toString()))
                    .map(
                        e -> DebugHelper.makeCSVLine(
                            GameRegistry.findUniqueIdentifierFor(e.key)
                                .toString(),
                            e.meta == null ? "*" : e.meta.toString(),
                            e.value.getId()))
                    .collect(Collectors.joining(System.lineSeparator())));
        }
        return csv.toString();
    }

    /**
     * @return a csv dump of all the registered crops
     */
    public String dumpCrops() {
        StringBuilder csv = new StringBuilder();
        csv.append(
            DebugHelper.makeCSVLine(
                // identification
                "ID",
                // nomenclature
                "Name",
                "Unlocalised Name",
                // cosmetics
                "Flavour Text",
                "Unlocalized Flavour Text",
                "Creator",
                "Color 1",
                "Color 2",
                "Seed Shape",
                "Render Shape",
                // growth related things
                "Tier",
                "Growth Duration",
                "Breeding Threshold",
                "Crossing Threshold",
                "Starts Spreading Weeds If Growth Above",
                "Growth Requirements",
                // drop related things
                "Drop Count Multiplier",
                "Drop Table",
                // alternate seeds
                "Alternate Seeds"));
        csv.append(System.lineSeparator());
        // If it's empty at least say so.
        if (this.cropRegistry.isEmpty()) {
            csv.append("empty");
        } else {
            csv.append(
                this.cropRegistry.entrySet()
                    .stream()
                    .map(e -> {
                        String id = e.getKey();
                        ICropCard cc = e.getValue();
                        // spotless:off
                        return DebugHelper.makeCSVLine(
                            // identification
                            // display the id it's registered with since that's what the registry identifies it with.
                            id,
                            // nomenclature
                            cc.getUnlocalizedName() == null ? "" : StatCollector.translateToLocal(cc.getUnlocalizedName()),
                            cc.getUnlocalizedName(),
                            // cosmetics
                            cc.getFlavourText() == null ? "" : StatCollector.translateToLocal(cc.getFlavourText()),
                            cc.getFlavourText(),
                            cc.getCreator(),
                            String.format("0x%08X", cc.getPrimarySeedColor().getRGB()),
                            String.format("0x%08X", cc.getSecondarySeedColor().getRGB()),
                            cc.getSeedShape().getName(),
                            cc.getRenderShape().getName(),
                            // growth related things
                            cc.getTier(),
                            cc.getGrowthDuration(),
                            cc.getBreedingThreshold(),
                            cc.getCrossingThreshold(),
                            cc.startsSpreadingWeedsAt(),
                            dumpGrowthReqs(cc.getGrowthRequirements()),
                            // drop related things
                            cc.getDropChance(),
                            dumpDropTable(cc.getDropTable()),
                            // alternate seeds
                            dumpAltSeeds(cc.getAlternateSeeds())
                        );
                        //spotless:on
                    })
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.joining(System.lineSeparator())));
        }
        return csv.toString();
    }

    private static String dumpGrowthReqs(Collection<IGrowthRequirement> reqs) {
        return reqs.stream()
            .map(IGrowthRequirement::getDescription)
            .collect(Collectors.joining(" | "));
    }

    private static String dumpDropTable(Map<ItemStack, Integer> map) {
        return map.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .map(e -> {
                StringBuilder sb = new StringBuilder();
                sb.append(DebugHelper.dumpStack(e.getKey(), false));
                // chance
                sb.append(" @ ");
                sb.append(e.getValue() / 100);
                sb.append(".");
                sb.append(e.getValue() % 100);
                sb.append("%");

                return sb.toString();
            })
            .collect(Collectors.joining(" | "));
    }

    private static String dumpAltSeeds(Collection<ItemStack> alternateSeeds) {
        return alternateSeeds.stream()
            .map(x -> DebugHelper.dumpStack(x, true))
            .collect(Collectors.joining(" | "));
    }
}
