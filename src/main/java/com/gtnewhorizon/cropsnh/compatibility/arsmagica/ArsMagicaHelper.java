package com.gtnewhorizon.cropsnh.compatibility.arsmagica;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.v1.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.v1.RenderMethod;
import com.gtnewhorizon.cropsnh.api.v1.RequirementType;
import com.gtnewhorizon.cropsnh.blocks.BlockModPlant;
import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.items.ItemModSeed;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

public class ArsMagicaHelper extends ModHelper {

    public static ArrayList<BlockModPlant> arsMagicaCrops = new ArrayList<>();
    public static ArrayList<ItemModSeed> arsMagicaSeeds = new ArrayList<>();

    @Override
    protected void initPlants() {
        // aum
        Item aum = (Item) Item.itemRegistry.getObject("arsmagica2:Aum");
        BlockModPlant cropAum = null;
        try {
            cropAum = new BlockModPlant("Aum", new ItemStack(aum), 3, RenderMethod.CROSSED);
        } catch (Exception e) {
            LogHelper.printStackTrace(e);
        }
        if (cropAum != null) {
            Block log = (Block) Block.blockRegistry.getObject("arsmagica2:WitchwoodLog");
            if (log != null) {
                cropAum.getGrowthRequirement().setRequiredBlock(new BlockWithMeta(log), RequirementType.NEARBY, false);
            }
            arsMagicaCrops.add(cropAum);
            arsMagicaSeeds.add(cropAum.getSeed());
        }

        // tarma root
        Item tarmaRoot = (Item) Item.itemRegistry.getObject("arsmagica2:TarmaRoot");
        BlockModPlant cropTarmaRoot = null;
        try {
            cropTarmaRoot = new BlockModPlant("TarmaRoot", new ItemStack(tarmaRoot), 3, RenderMethod.CROSSED);
        } catch (Exception e) {
            LogHelper.printStackTrace(e);
        }
        if (cropTarmaRoot != null) {
            cropTarmaRoot.getGrowthRequirement().setBrightnessRange(0, 8);
            arsMagicaCrops.add(cropTarmaRoot);
            arsMagicaSeeds.add(cropTarmaRoot.getSeed());
        }

        // cerublossom
        Item cerublossom = (Item) Item.itemRegistry.getObject("arsmagica2:blueOrchid");
        BlockModPlant cropCerublossom = null;
        try {
            cropCerublossom = new BlockModPlant("Cerublossom", new ItemStack(cerublossom), 3, RenderMethod.CROSSED);
        } catch (Exception e) {
            LogHelper.printStackTrace(e);
        }
        if (cropCerublossom != null) {
            arsMagicaCrops.add(cropCerublossom);
            arsMagicaSeeds.add(cropCerublossom.getSeed());
        }

        // desert nova
        Item desertNova = (Item) Item.itemRegistry.getObject("arsmagica2:desertNova");
        BlockModPlant cropDesertNova = null;
        try {
            cropDesertNova = new BlockModPlant("DesertNova", new ItemStack(desertNova), 3, RenderMethod.CROSSED);
        } catch (Exception e) {
            LogHelper.printStackTrace(e);
        }
        if (cropDesertNova != null) {
            cropDesertNova.getGrowthRequirement().setSoil(new BlockWithMeta(Blocks.sand));
            arsMagicaCrops.add(cropDesertNova);
            arsMagicaSeeds.add(cropDesertNova.getSeed());
        }

        // wakebloom
        Item wakebloom = (Item) Item.itemRegistry.getObject("arsmagica2:wakebloom");
        BlockModPlant cropWakebloom = null;
        try {
            cropWakebloom = new BlockModPlant("Wakebloom", new ItemStack(wakebloom), 3, RenderMethod.CROSSED);
        } catch (Exception e) {
            LogHelper.printStackTrace(e);
        }
        if (cropWakebloom != null) {
            cropWakebloom.getGrowthRequirement()
                    .setSoil(new BlockWithMeta(com.gtnewhorizon.cropsnh.init.Blocks.blockWaterPadFull));
            arsMagicaCrops.add(cropWakebloom);
            arsMagicaSeeds.add(cropWakebloom.getSeed());
        }
    }

    @Override
    protected String modId() {
        return Names.Mods.arsMagica;
    }
}
