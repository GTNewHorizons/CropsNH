package com.gtnewhorizon.cropsnh.farming.requirements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IMachineBreedingRequirement;
import com.gtnewhorizon.cropsnh.api.IWorldBreedingRequirement;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.MetaSet;

import gregtech.api.GregTechAPI;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.objects.ItemData;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.common.blocks.BlockOresAbstract;
import gregtech.common.blocks.TileEntityOres;

/**
 * Used to prevent a crop from growing unless there is a specific block under it.
 */
public class BlockUnderRequirement
    implements IWorldGrowthRequirement, IWorldBreedingRequirement, IMachineBreedingRequirement {

    private static final HashMap<String, BlockUnderRequirement> registrations = new HashMap<>();

    public static BlockUnderRequirement get(String name) {
        if (!registrations.containsKey(name)) {
            registrations.put(name, new BlockUnderRequirement(name));
        }
        return registrations.get(name);
    }

    public static void validateRegistry() {
        registrations.values()
            .forEach(BlockUnderRequirement::validate);
    }

    public void validate() {
        if (this.materials.isEmpty() && this.oreDictionaries.isEmpty() && this.blocks.isEmpty()) {
            LogHelper
                .warn("Block under requirement hasn't been given anything to look for: " + this.materialDescription);
        }
    }

    private final String materialDescription;
    private final Set<Materials> materials = new HashSet<>();
    private final Set<String> oreDictionaries = new HashSet<String>();
    private final MetaSet<Block> blocks = new MetaSet<>();

    private BlockUnderRequirement(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public BlockUnderRequirement addMaterial(Materials... args) {
        this.materials.addAll(Arrays.asList(args));
        return this;
    }

    public BlockUnderRequirement addOreDict(String... args) {
        this.oreDictionaries.addAll(Arrays.asList(args));
        return this;
    }

    public BlockUnderRequirement addBlockAndOreDict() {
        // just capitalize the mat description
        return this
            .addOreDict(Character.toUpperCase(this.materialDescription.charAt(0)) + materialDescription.substring(1));
    }

    public BlockUnderRequirement addBlockAndOreDict(String... names) {
        for (String name : names) {
            this.addOreDict("block" + name, "ore" + name);
        }
        return this;
    }

    public BlockUnderRequirement addBlock(BlockWithMeta... args) {
        for (BlockWithMeta arg : args) {
            this.blocks.add(arg.getBlock(), arg.ignoreMeta() ? OreDictionary.WILDCARD_VALUE : arg.getMeta());
        }
        return this;
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocal("cropsnh_growthReq.blockUnder." + materialDescription);
    }

    @Override
    public boolean canGrow(World world, ICropStickTile tile, int x, int y, int z) {
        // pre-flight checks
        BlockUnderTarget target = getBlockUnder(world, x, y, z);
        if (target == null) return false;
        return canGrow(target.block, target.meta, target.te);
    }

    @Override
    public boolean canBreed(ArrayList<ICropCard> parents, World world, ICropStickTile tile, int x, int y, int z) {
        return canGrow(world, tile, x, y, z);
    }

    public static BlockUnderTarget getBlockUnder(IBlockAccess world, int x, int y, int z) {
        // pre-flight check
        y -= 2;
        if (y < 0) return null;
        if (world.isAirBlock(x, y, z)) return null;

        // get the block under
        Block block = world.getBlock(x, y, z);
        if (block.getMaterial() == Material.air) return null;
        int meta = world.getBlockMetadata(x, y, z);
        TileEntity te = world.getTileEntity(x, y, z);
        return new BlockUnderTarget(block, meta, te);
    }

    public static class BlockUnderTarget {

        public final Block block;
        public final int meta;
        public final TileEntity te;

        public BlockUnderTarget(Block block, int meta, TileEntity te) {
            this.block = block;
            this.meta = meta;
            this.te = te;
        }
    }

    @Override
    public boolean canBreed(ArrayList<ICropCard> parents, TileEntity te, ItemStack[] catalysts) {
        for (ItemStack stack : catalysts) {
            if (stack == null || stack.getItem() == null) continue;

            // GT Material check
            for (Materials material : this.materials) {
                if (checkGTBlockOrOreMaterial(stack, material)) return true;
            }

            // Ore dict check
            for (String oreDict : this.oreDictionaries) {
                if (checkOreDict(stack, oreDict)) return true;
            }

            // Block conversion
            Block block = Block.getBlockFromItem(stack.getItem());
            if (block.getMaterial() != Material.air && blocks.contains(block, stack.getItemDamage())) return true;
        }
        return false;
    }

    public boolean canGrow(Block block, int meta, TileEntity te) {
        // non-world dependent check for the GoBlyn
        ItemStack stack = new ItemStack(Item.getItemFromBlock(block), 1, meta);

        // gt material check
        for (Materials material : this.materials) {
            if (block instanceof BlockOresAbstract && te instanceof TileEntityOres) {
                Materials tMaterial = GregTechAPI.sGeneratedMaterials[((TileEntityOres) te).mMetaData % 1000];
                if (tMaterial != null && tMaterial != Materials._NULL && tMaterial == material) {
                    return true;
                }
            } else if (checkGTBlockOrOreMaterial(stack, material)) {
                return true;
            }
        }

        // ore dict checks
        for (String oreDict : this.oreDictionaries) {
            if (checkOreDict(stack, oreDict)) return true;
        }

        // direct block access
        return blocks.contains(block, meta);
    }

    private boolean checkGTBlockOrOreMaterial(ItemStack stack, Materials toMatch) {
        ItemData association = GTOreDictUnificator.getAssociation(stack);
        // spotless:off
        return association != null
            && (association.mPrefix.toString().startsWith("ore") || association.mPrefix == OrePrefixes.block)
            && (association.mMaterial.mMaterial == toMatch);
        //spotless:on
    }

    private boolean checkOreDict(ItemStack stack, String oreDict) {
        // ore dictionary
        for (int aux = 0; aux < OreDictionary.getOres(oreDict)
            .size(); ++aux) {
            ItemStack oreDictStack = OreDictionary.getOres(oreDict)
                .get(aux);
            if (oreDictStack.getItem() != stack.getItem()) continue;
            int dmg = oreDictStack.getItemDamage();
            // check for both the old and the new wildcard value, this helps with a couple things
            if (stack.getItemDamage() == dmg || OreDictionary.WILDCARD_VALUE == dmg || -1 == dmg) return true;
        }
        return false;
    }

}
