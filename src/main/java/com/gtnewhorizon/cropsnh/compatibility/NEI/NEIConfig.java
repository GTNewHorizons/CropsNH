package com.gtnewhorizon.cropsnh.compatibility.NEI;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.gtnewhorizon.cropsnh.CropsNH;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.Items;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import codechicken.nei.api.IConfigureNEI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class NEIConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        // register NEI recipe handler
        LogHelper.debug("Registering NEI recipe handlers");
        // mutation handler
        // NEICropMutationHandler mutationHandler = new NEICropMutationHandler();
        // API.registerRecipeHandler(mutationHandler);
        // API.registerUsageHandler(mutationHandler);
        // crop product handler
        // NEICropProductHandler productHandler = new NEICropProductHandler();
        // API.registerRecipeHandler(productHandler);
        // API.registerUsageHandler(productHandler);
        // hide crop blocks in NEI
        hideItems();
    }

    private static void hideItems() {
        // hide invalid seed
        CropsNH.proxy.hideItemInNEI(new ItemStack(Items.genericSeed));
        // add registered seeds
        SeedStats stats = new SeedStats((byte) 1, (byte) 1, (byte) 1, true);
        for (ICropCard cc : Arrays.stream(CropRegistry.instance.getAll()).sorted(Comparator.comparing(ICropCard::getId)).toArray(ICropCard[]::new)) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString(Names.NBT.crop, cc.getId());
            stats.writeToNBT(tag);
            ItemStack toRegister = new ItemStack(Items.genericSeed, 1, 1);
            toRegister.setTagCompound(tag);
            CropsNH.proxy.addItemInNEI(toRegister);
        }
        LogHelper.debug("Hiding stuff in nei");
        for (int i = 0; i < 16; i++) {
            // hide debugger
            if (!ConfigurationHandler.debug) {
                CropsNH.proxy.hideItemInNEI(new ItemStack(Items.debugItem, 1, i));
            }
        }
    }

    @Override
    public String getName() {
        return Reference.MOD_ID + "_NEI";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

}
