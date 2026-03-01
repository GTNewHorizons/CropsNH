package com.gtnewhorizon.cropsnh.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;

public interface ICropMutation {

    ICropCard getOutput();

    /**
     * @return The number of parents used for a deterministic mutation.
     */
    int getParentCount();

    /**
     * @return The parents used for a deterministic mutation.
     */
    Collection<ICropCard> getParents();

    /**
     * Checks if the crop can breed in the world.
     * 
     * @param parents The parents that surround the crop.
     * @param world   The world in which the destination crop stick is in.
     * @param tile    The tile entity for the destination crop stick.
     * @param x       The x coordinate of the destination crop stick.
     * @param y       The y coordinate of the destination crop stick.
     * @param z       The z coordinate of the destination crop stick.
     * @return True if the crop can breed.
     */
    boolean canBreed(ArrayList<ICropCard> parents, World world, ICropStickTile tile, int x, int y, int z);

    /**
     * Checks if the crop can breed in the breeding machine.
     * 
     * @param parents   The parents that can be used for a mutation.
     * @param te        The base tile entity of the breeding machine.
     * @param catalysts The catalysts that can be consumed from the breeding machine's inventory.
     * @return null if the crop can't breed, an array of int representing how many items should be consumed from each
     *         stack in the catalyst array if the recipe goes though.
     */
    @Nullable
    int[] canBreed(ArrayList<ICropCard> parents, IGregTechTileEntity te, ItemStack[] catalysts);

    /**
     * @return the list of breeding requirements for this mutation.
     */
    List<IBreedingRequirement> getRequirements();

    /**
     * Gets the list of usable underblocks for this mutation.
     * 
     * @param useCache True to used the result of the last succesful request.
     */
    List<ItemStack> getBlocksUnderForNEI(boolean useCache);

    /**
     * Gets the list of catalysts needed to breed using the breeding machine.
     * 
     * @param useCache True to used the result of the last succesful request.
     */
    public List<List<ItemStack>> getBreedingMachineCatalystsForNEI(boolean useCache);

    /**
     * @return The recipe duration in the breeding machine.
     */
    int getBreedingMachineRecipeDuration();

    /**
     * @return The recipe eu/t usage in the breeding machine.
     */
    int getBreedingMachineRecipeEUt();
}
