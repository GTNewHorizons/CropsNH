package com.gtnewhorizon.cropsnh.farming.requirements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IMachineBreedingRequirement;
import com.gtnewhorizon.cropsnh.api.IMachineGrowthRequirement;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.api.IWorldBreedingRequirement;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.MetaSet;

import gregtech.api.GregTechAPI;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.StoneType;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.objects.ItemData;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.GTBlockOre;
import gregtech.common.blocks.TileEntityOres;
import gregtech.common.ores.GTOreAdapter;
import gregtech.common.ores.OreInfo;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

/**
 * Used to prevent a crop from growing unless there is a specific type of block(s) is directly under its soil.
 */
public class SubSoilRequirement implements IWorldGrowthRequirement, IWorldBreedingRequirement,
    IMachineBreedingRequirement, IMachineGrowthRequirement {

    private static final Object2ObjectOpenHashMap<String, SubSoilRequirement> registrations = new Object2ObjectOpenHashMap<>();

    public static SubSoilRequirement get(String name) {
        if (!registrations.containsKey(name)) {
            registrations.put(name, new SubSoilRequirement(name));
        }
        return registrations.get(name);
    }

    private final String materialDescription;
    private final Set<Materials> materials = new ObjectOpenHashSet<>();
    private final Set<String> oreDictionaries = new ObjectOpenHashSet<>();
    private final MetaSet<Block> blocks = new MetaSet<>();
    private final Pair<String, String[]> unlocalizedDesc;

    private SubSoilRequirement(String materialDescription) {
        this.materialDescription = materialDescription;
        this.unlocalizedDesc = Pair.of(Reference.MOD_ID + "_growthReq.subSoil." + this.materialDescription, null);
    }

    public SubSoilRequirement addMaterial(Materials... args) {
        this.materials.addAll(Arrays.asList(args));
        return this;
    }

    public SubSoilRequirement addOreDict(String... args) {
        this.oreDictionaries.addAll(Arrays.asList(args));
        return this;
    }

    public SubSoilRequirement addBlockAndOreDict() {
        // just capitalize the mat description
        return this
            .addOreDict(Character.toUpperCase(this.materialDescription.charAt(0)) + materialDescription.substring(1));
    }

    public SubSoilRequirement addBlockAndOreDict(String... names) {
        for (String name : names) {
            this.addOreDict("block" + name, "ore" + name);
        }
        return this;
    }

    public SubSoilRequirement addBlock(BlockWithMeta... args) {
        for (BlockWithMeta arg : args) {
            this.blocks.add(arg.getBlock(), arg.ignoreMeta() ? OreDictionary.WILDCARD_VALUE : arg.getMeta());
        }
        return this;
    }

    @Override
    public @NotNull String getDescription() {
        return StatCollector
            .translateToLocalFormatted(this.unlocalizedDesc.getLeft(), (Object[]) unlocalizedDesc.getRight());
    }

    @Override
    public @NotNull Pair<@NotNull String, @Nullable String[]> getUnlocalizedDescription() {
        return this.unlocalizedDesc;
    }

    @Override
    public boolean canGrow(World world, ICropStickTile tile, int x, int y, int z) {
        // pre-flight checks
        SubSoilTarget target = getSubSoil(world, x, y, z);
        if (target == null) return false;
        return canGrow(target.block, target.meta, target.te);
    }

    @Override
    public boolean onlyPreventsHarvest() {
        return false;
    }

    @Override
    public boolean canBreed(ArrayList<ICropCard> parents, World world, ICropStickTile tile, int x, int y, int z) {
        return canGrow(world, tile, x, y, z);
    }

    public static SubSoilTarget getSubSoil(IBlockAccess world, int x, int y, int z) {
        // pre-flight check
        y -= 2;
        if (y < 0) return null;
        if (world.isAirBlock(x, y, z)) return null;

        // get the sub-soil
        Block block = world.getBlock(x, y, z);
        if (CropsNHUtils.isAirBlock(block)) return null;
        int meta = world.getBlockMetadata(x, y, z);
        TileEntity te = world.getTileEntity(x, y, z);
        return new SubSoilTarget(block, meta, te);
    }

    @Override
    public boolean canGrow(ISeedData seedData, IGregTechTileEntity te, ItemStack[] catalysts) {
        return Arrays.stream(catalysts)
            .anyMatch(this::isValidSubSoil);
    }

    public static class SubSoilTarget {

        public final Block block;
        public final int meta;
        public final TileEntity te;

        public SubSoilTarget(Block block, int meta, TileEntity te) {
            this.block = block;
            this.meta = meta;
            this.te = te;
        }
    }

    @Override
    public boolean canBreed(ArrayList<ICropCard> parents, IGregTechTileEntity te, ItemStack @NotNull [] catalysts,
        int[] consumptionTracker) {
        for (int i = 0; i < catalysts.length; i++) {
            // if stack is bad or if we can't consume it, abort early
            ItemStack stack = catalysts[i];
            if (GTUtility.isStackInvalid(stack) || stack.stackSize - consumptionTracker[i] <= 0) continue;
            // consume if valid
            if (isValidSubSoil(stack)) {
                consumptionTracker[i] += 1;
                return true;
            }
        }
        return false;
    }

    public @Nullable ItemStack findIndustrialFarmInsertionCatalyst(@NotNull List<ItemStack> catalysts) {
        for (ItemStack stack : catalysts) {
            // if stack is bad or if we can't consume it, abort early
            if (GTUtility.isStackInvalid(stack) || stack.stackSize <= 0) continue;
            // else check if it's a valid sub-soil
            if (isValidSubSoil(stack)) {
                return stack;
            }
        }
        return null;
    }

    /**
     * @param toValidate The stack to validate
     * @return True if the stack contains a valid sub-soil.
     */
    public boolean isValidSubSoil(ItemStack toValidate) {
        // ensure valid stack
        if (CropsNHUtils.isStackInvalid(toValidate)) return false;

        Block block = CropsNHUtils.getBlockFromItem(toValidate);
        int meta = CropsNHUtils.getItemMeta(toValidate);

        // GT Material check
        if (this.checkGTOreDictAssociation(toValidate) || this.checkGTOreAssociation(block, meta)) {
            return true;
        }

        // Ore dict check
        for (String oreDict : this.oreDictionaries) {
            if (checkOreDict(toValidate, oreDict)) {
                return true;
            }
        }

        // Block conversion
        return !CropsNHUtils.isAirBlock(block) && blocks.contains(block, meta);
    }

    public boolean canGrow(Block block, int meta, TileEntity te) {
        // non-world dependent check for the GoBlyn
        ItemStack stack = new ItemStack(Item.getItemFromBlock(block), 1, meta);

        // gt material check
        if (this.checkGTOreTE(block, te) || this.checkGTOreDictAssociation(stack)
            || this.checkGTOreAssociation(block, meta)) {
            return true;
        }

        // ore dict checks
        for (String oreDict : this.oreDictionaries) {
            if (checkOreDict(stack, oreDict)) return true;
        }

        // direct block access
        return blocks.contains(block, meta);
    }

    private boolean checkGTOreTE(Block block, TileEntity te) {
        if (block instanceof GTBlockOre && te instanceof TileEntityOres oreTE) {
            Materials generatedMaterial = GregTechAPI.sGeneratedMaterials[oreTE.mMetaData % 1000];
            if (generatedMaterial != null && generatedMaterial != Materials._NULL
                && this.materials.contains(generatedMaterial)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkGTOreDictAssociation(ItemStack stack) {
        ItemData association = GTOreDictUnificator.getAssociation(stack);
        // spotless:off
        return association != null
            && (association.mPrefix.toString().startsWith("ore") || association.mPrefix == OrePrefixes.block)
            && (this.materials.contains(association.mMaterial.mMaterial));
        //spotless:on
    }

    private boolean checkGTOreAssociation(Block block, int meta) {
        if (CropsNHUtils.isAirBlock(block)) return false;
        OreInfo<Materials> oreInfo = GTOreAdapter.INSTANCE.getOreInfo(block, meta);
        if (oreInfo == null) return false;
        return !oreInfo.isSmall && this.materials.contains(oreInfo.material);
    }

    private boolean checkOreDict(ItemStack stack, String oreDict) {
        // ore dictionary
        for (int aux = 0; aux < OreDictionary.getOres(oreDict)
            .size(); ++aux) {
            ItemStack oreDictStack = OreDictionary.getOres(oreDict)
                .get(aux);
            if (oreDictStack.getItem() != stack.getItem()) continue;
            int dmg = CropsNHUtils.getItemMeta(oreDictStack);
            // check for both the old and the new wildcard value, this helps with a couple things
            if (CropsNHUtils.getItemMeta(stack) == dmg || OreDictionary.WILDCARD_VALUE == dmg || -1 == dmg) return true;
        }
        return false;
    }

    @Override
    public @Nullable List<ItemStack> getMachineOnlyCatalystsForNEI() {
        return getItemsForNEI();
    }

    public List<ItemStack> getItemsForNEI() {
        List<ItemStack> ret = new LinkedList<>();
        // load up direct block mentions
        for (MetaSet.Entry<Block> e : this.blocks.getStream()
            .collect(Collectors.toList())) {
            Item item = CropsNHUtils.getItemFromBlock(e.key);
            if (item == null) continue;
            int meta = e.meta == null ? OreDictionary.WILDCARD_VALUE : e.meta;
            ret.add(new ItemStack(item, 1, meta));
        }

        // load up materials
        for (Materials mat : this.materials) {
            ret.addAll(GTOreDictUnificator.getOres(OrePrefixes.ore, mat));
            for (var type : StoneType.values()) {
                if (!type.isEnabled()) continue;
                ArrayList<ItemStack> ores = GTOreDictUnificator.getOres(type.getPrefix(), mat);
                if (ores == null || ores.isEmpty()) continue;
                ret.addAll(ores);
            }
            // compressed storage block
            ret.addAll(GTOreDictUnificator.getOres(OrePrefixes.block, mat));
        }

        // load up ore dicts
        for (String oreDict : this.oreDictionaries) {
            ret.addAll(OreDictionary.getOres(oreDict));
        }

        // the nei step will deduplicate stuff later, so we don't have to care about that.
        return ret;
    }

}
