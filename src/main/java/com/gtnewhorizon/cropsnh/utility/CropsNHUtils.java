package com.gtnewhorizon.cropsnh.utility;

import java.util.HashSet;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.farming.SeedData;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.items.ItemGenericSeed;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.util.GTRecipeBuilder;

public abstract class CropsNHUtils {

    /**
     * Removes duplicate items from an item list, and also kicks out invalid stacks from said list.
     * 
     * @param stacks The list to deduplicate.
     */
    public static void deduplicateItemList(List<ItemStack> stacks) {
        if (stacks.isEmpty()) return;
        // yank out null items for good measure
        final HashSet<String> deduplicator = new HashSet<>();
        stacks.removeIf(x -> CropsNHUtils.isStackInvalidIgnoreStackSize(x) || !deduplicator.add(x.toString()));
    }

    /**
     * Does its best to turn an item into a block.
     *
     * @param block the block to convert
     * @return The item or null if none is found.
     */
    public static Item getItemFromBlock(Block block) {
        Item item = Item.getItemFromBlock(block);
        if (item != null) return item;
        // bloddy skulls
        GameRegistry.UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(block);
        return GameRegistry.findItem(id.modId, id.name);
    }

    /**
     * Does its best to turn a block into an item.
     *
     * @param stack The stack containing the item to convert.
     * @return The block or null if none is found.
     */
    public static Block getBlockFromItem(ItemStack stack) {
        if (stack == null) return null;
        return getBlockFromItem(stack.getItem());
    }

    /**
     * Does its best to turn a block into an item.
     *
     * @param item the block to convert
     * @return The block or null if none is found.
     */
    public static Block getBlockFromItem(Item item) {
        if (item == null) {
            return null;
        } else if (item instanceof ItemSkull) {
            return Blocks.skull;
        } else if (item instanceof ItemBlock) {
            return ((ItemBlock) item).field_150939_a;
        } else {
            return Block.getBlockFromItem(item);
        }
    }

    /**
     * Attempts to detect if the stack contains analyzed seeds.
     *
     * @param aStack The stack to validate
     * @return Null if nothing was found else the seed data for the stack.
     */
    public static @Nullable ISeedData getAnalyzedSeedData(ItemStack aStack) {
        if (CropsNHUtils.isStackInvalid(aStack) || !(aStack.getItem() instanceof ItemGenericSeed)) return null;
        // check that it's a crop card and that it can cross.
        ICropCard cc = CropRegistry.instance.get(aStack);
        if (cc == null) return null;
        // fail if the crop isn't analyzed
        SeedStats stats = SeedStats.getStatsFromStack(aStack);
        if (stats == null || !stats.isAnalyzed()) return null;
        return new SeedData(cc, stats, aStack);
    }

    private static IIcon MISSING_TEXTURE = null;

    /**
     * @return A default texture icon to be used for all missing textures.
     */
    @SideOnly(Side.CLIENT)
    public static IIcon getMissingTexture() {
        if (MISSING_TEXTURE == null) {
            MISSING_TEXTURE = ((TextureMap) Minecraft.getMinecraft()
                .getTextureManager()
                .getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
        }
        return MISSING_TEXTURE;
    }

    /**
     * @param aStack The stack to verify.
     * @return True if the stack is not null, its contained item isn't null, and it's stack size is > 0.
     */
    @Contract("null -> false")
    public static boolean isStackValid(@Nullable ItemStack aStack) {
        return (aStack != null) && aStack.getItem() != null && aStack.stackSize > 0;
    }

    /**
     * @param aStack The stack to verify.
     * @return True if the stack is null, its contained item null, or it's stack size is <= 0.
     */
    @Contract("null -> true")
    public static boolean isStackInvalid(@Nullable ItemStack aStack) {
        return aStack == null || aStack.getItem() == null || aStack.stackSize <= 0;
    }

    /**
     * @param aStack The stack to verify.
     * @return True if the stack is not null, its contained item isn't null, and it's amount is > 0.
     */
    @Contract("null -> false")
    public static boolean isStackValid(@Nullable FluidStack aStack) {
        return (aStack != null) && aStack.getFluid() != null && aStack.amount > 0;
    }

    /**
     * @param aStack The stack to verify.
     * @return True if the stack is null, its contained fluid null, or it's amount is <= 0.
     */
    @Contract("null -> true")
    public static boolean isStackInvalid(@Nullable FluidStack aStack) {
        return aStack == null || aStack.getFluid() == null || aStack.amount <= 0;
    }

    /**
     * Copies the stack and returns a new stack with the given stack size. Will reject if the incoming stack has an
     * invalid stack size.
     *
     * @param aStack The stack to copy.
     * @param aSize  The size of the new stack.
     * @return The copied stack with the requested size.
     */
    @Contract("null, _ -> null")
    public static @Nullable ItemStack copyStackWithSize(@Nullable ItemStack aStack, int aSize) {
        if (isStackInvalid(aStack)) return null;
        ItemStack ret = aStack.copy();
        ret.stackSize = aSize;
        return ret;
    }

    // a bit of duplicated code to not have to deal with compiling inlining not working right

    /**
     * @param aStack The stack to verify.
     * @return True if the stack is not null and its contained item isn't null.
     */
    @Contract("null -> false")
    public static boolean isStackValidIgnoreStackSize(@Nullable ItemStack aStack) {
        return (aStack != null) && aStack.getItem() != null;
    }

    /**
     * @param aStack The stack to verify.
     * @return True if the stack is null or its contained item null.
     */
    @Contract("null -> true")
    public static boolean isStackInvalidIgnoreStackSize(@Nullable ItemStack aStack) {
        return aStack == null || aStack.getItem() == null;
    }

    /**
     * @param aStack The stack to verify.
     * @return True if the stack is not null and its contained item isn't null.
     */
    @Contract("null -> false")
    public static boolean isStackValidIgnoreStackSize(@Nullable FluidStack aStack) {
        return (aStack != null) && aStack.getFluid() != null;
    }

    /**
     * @param aStack The stack to verify.
     * @return True if the stack is null or its contained fluid null.
     */
    @Contract("null -> true")
    public static boolean isStackInvalidIgnoreStackSize(@Nullable FluidStack aStack) {
        return aStack == null || aStack.getFluid() == null;
    }

    /**
     * Copies the stack and returns a new stack with the given stack size, doesn't care if the incoming stack has an
     * invalid stack size.
     *
     * @param aStack The stack to copy.
     * @param aSize  The size of the new stack.
     * @return The copied stack with the requested size.
     */
    @Contract("null, _ -> null")
    public static @Nullable ItemStack copyStackWithSizeIgnoreInvalidStackSize(@Nullable ItemStack aStack, int aSize) {
        if (isStackInvalidIgnoreStackSize(aStack)) return null;
        ItemStack ret = aStack.copy();
        ret.stackSize = aSize;
        return ret;
    }

    /**
     * @return Whether loader functions should panic/throw if they find invalid or null items.
     */
    public static boolean shouldPanicIfNullFound() {
        return ConfigurationHandler.panicIfNull || GTRecipeBuilder.PANIC_MODE_NULL;
    }

    /**
     * Gets a stack from another mod.
     * 
     * @param mod    The mod the item is from.
     * @param name   The string ID of the item to fetch.
     * @param amount The amount of to set the stack size to.
     * @param meta   The meta valud of the item to fetch.
     * @return The item from the other mod or null if it doesn't exist.
     */
    public static ItemStack getModItem(ModUtils mod, String name, int amount, int meta) {
        ItemStack ret = CropsNHUtils.getModItem(mod, name, amount);
        if (ret == null) return null;
        Items.feather.setDamage(ret, meta);
        return ret;
    }

    /**
     * Gets a stack from another mod.
     * 
     * @param mod    The mod the item is from.
     * @param name   The string ID of the item to fetch.
     * @param amount The amount of to set the stack size to.
     * @return The item from the other mod or null if it doesn't exist.
     */
    public static ItemStack getModItem(ModUtils mod, String name, int amount) {
        ItemStack ret = GameRegistry.findItemStack(mod.ID, name, amount);
        if (CropsNHUtils.isStackInvalidIgnoreStackSize(ret)) {
            String fullId = "\"" + mod.ID + ":" + name + "\"";
            if (CropsNHUtils.shouldPanicIfNullFound()) {
                throw new IllegalStateException("stack with id " + fullId + " could not be found!");
            } else {
                try {
                    throw new Exception("CROPS NH MISSING GET MOD ITEM FOR ID: " + fullId);
                } catch (Exception e) {
                    LogHelper.warn(e.getMessage());
                    e.printStackTrace();
                }
            }
            return null;
        }
        return CropsNHUtils.copyStackWithSizeIgnoreInvalidStackSize(ret, amount);
    }

    /**
     * Gets the damage/meta value of an item directly, bypassing any item specific getDamage logic.
     * 
     * @param aStack The stack to get the meta of.
     * @return The raw meta value of the stack.
     */
    public static int getItemMeta(ItemStack aStack) {
        return Items.feather.getDamage(aStack);
    }

    /**
     * @return The default fluid used for liquid Weed-EX.
     */
    public static Fluid getWeedEXFluid() {
        return FluidRegistry.getFluid("potion.poison.strong");
    }

    /**
     * @param amount The amount to set the stack to.
     * @return A stack of the fluid being used for liquid Weed-EX
     */
    public static FluidStack getWeedEXFluid(int amount) {
        return new FluidStack(getWeedEXFluid(), amount);
    }

    /**
     * @param amount The stack size of the weed drop.
     * @return A weed drop of the given stack size.
     */
    public static ItemStack getWeedDrop(int amount) {
        return new ItemStack(Blocks.tallgrass, amount, 1);
    }

    /**
     * @return True if the calling code is executing on the server side.
     */
    public static boolean isServer() {
        return FMLCommonHandler.instance()
            .getEffectiveSide()
            .isServer();
    }

    /**
     * @return True if the calling code is executing on the client side.
     */
    public static boolean isClient() {
        return FMLCommonHandler.instance()
            .getEffectiveSide()
            .isClient();
    }

    /**
     * @param machineType The lang key for the machine type
     * @return A formatted and colorized text for the machine type section of a GT machine tooltip.
     */
    public static String getMachineTypeText(String machineType) {
        return StatCollector.translateToLocal("GT5U.MBTT.MachineType") + ": "
            + EnumChatFormatting.YELLOW
            + StatCollector.translateToLocal("cropsnh.recipes." + machineType)
            + EnumChatFormatting.RESET;
    }
}
