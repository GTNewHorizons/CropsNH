package com.gtnewhorizon.cropsnh.loaders;

import net.minecraft.init.Items;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.api.ICropsNHBootProtectionHandler;
import com.gtnewhorizon.cropsnh.farming.bootprotection.DamageBootProtectionHandler;
import com.gtnewhorizon.cropsnh.farming.registries.BootProtectionRegistry;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class BootProtectionLoader {

    public static void postInit() {
        // general rules:
        // - if it provides only hazmat and isn't electric: takes damage, immune
        // - if it provides only hazmat but is electric or provides full space suit protection: no damage, immune

        // TODO: update this when hazmat suit cleanroom happens.
        // reduce the leather boot protection value when IC2/rubber boots is installed
        float leatherBootDamageProtection = BootProtectionRegistry.FULL_PROTECTION;
        if (ModUtils.IndustrialCraft2.isModLoaded()) leatherBootDamageProtection *= 0.75f;

        ICropsNHBootProtectionHandler leatherBootDamageHandler = new DamageBootProtectionHandler(
            1,
            1_00,
            leatherBootDamageProtection);

        ICropsNHBootProtectionHandler defaultBootDamageHandler = new DamageBootProtectionHandler(
            1,
            1_00,
            BootProtectionRegistry.FULL_PROTECTION);

        BootProtectionRegistry.instance
            .register(Items.leather_boots, OreDictionary.WILDCARD_VALUE, leatherBootDamageHandler);

        if (ModUtils.Avaritia.isModLoaded()) {
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.Avaritia.getItem("Infinity_Shoes"),
                OreDictionary.WILDCARD_VALUE);
        }
        if (ModUtils.BloodMagic.isModLoaded()) {
            BootProtectionRegistry.instance
                .registerFullProtectionHandler(ModUtils.BloodMagic.getItem("boundBoots"), OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.BloodMagic.getItem("boundBootsEarth"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.BloodMagic.getItem("boundBootsFire"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.BloodMagic.getItem("boundBootsWater"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.BloodMagic.getItem("boundBootsWind"),
                OreDictionary.WILDCARD_VALUE);
        }
        if (ModUtils.DraconicEvolution.isModLoaded()) {
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.DraconicEvolution.getItem("draconicBoots"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.DraconicEvolution.getItem("wyvernBoots"),
                OreDictionary.WILDCARD_VALUE);
        }
        if (ModUtils.ElectroMagicTools.isModLoaded()) {
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.ElectroMagicTools.getItem("NanoBootsTraveller"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.ElectroMagicTools.getItem("QuantumBootsTraveller"),
                OreDictionary.WILDCARD_VALUE);
        }
        if (ModUtils.ElectroMagicTools.isModLoaded()) {
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.ElectroMagicTools.getItem("NanoBootsTraveller"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.ElectroMagicTools.getItem("QuantumBootsTraveller"),
                OreDictionary.WILDCARD_VALUE);
        }
        if (ModUtils.EnderIO.isModLoaded()) {
            BootProtectionRegistry.instance.register(
                ModUtils.EnderIO.getItem("item.endSteel_boots"),
                OreDictionary.WILDCARD_VALUE,
                defaultBootDamageHandler);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.EnderIO.getItem("item.stellar_boots"),
                OreDictionary.WILDCARD_VALUE);
        }
        if (ModUtils.GalaxySpace.isModLoaded()) {
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.GalaxySpace.getItem("item.spacesuit_boots"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.GalaxySpace.getItem("item.spacesuit_gravityboots"),
                OreDictionary.WILDCARD_VALUE);
        }
        if (ModUtils.GTPlusPlus.isModLoaded()) {
            // adv rubber boots take damage
            BootProtectionRegistry.instance.register(
                ModUtils.GTPlusPlus.getItem("itemArmorRubBootsEx"),
                OreDictionary.WILDCARD_VALUE,
                defaultBootDamageHandler);
        }
        if (ModUtils.IndustrialCraft2.isModLoaded()) {
            // rubber boots take damage
            BootProtectionRegistry.instance.register(
                ModUtils.IndustrialCraft2.getItem("itemArmorRubBoots"),
                OreDictionary.WILDCARD_VALUE,
                defaultBootDamageHandler);

            // nano/quantum boots have full protection
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.IndustrialCraft2.getItem("itemArmorNanoBoots"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.IndustrialCraft2.getItem("itemArmorQuantumBoots"),
                OreDictionary.WILDCARD_VALUE);
        }
        if (ModUtils.Natura.isModLoaded()) {
            BootProtectionRegistry.instance.register(
                ModUtils.Natura.getItem("natura.armor.impboots"),
                OreDictionary.WILDCARD_VALUE,
                leatherBootDamageHandler);
        }
        if (ModUtils.PamsHarvestCraft.isModLoaded()) {
            BootProtectionRegistry.instance.register(
                ModUtils.PamsHarvestCraft.getItem("hardenedleatherbootsItem"),
                OreDictionary.WILDCARD_VALUE,
                leatherBootDamageHandler);
        }
        if (ModUtils.TaintedMagic.isModLoaded()) {
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.TaintedMagic.getItem("ItemVoidwalkerBoots"),
                OreDictionary.WILDCARD_VALUE);
        }
        if (ModUtils.ThaumicBoots.isModLoaded()) {
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.ThaumicBoots.getItem("item.ItemElectricVoid"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.ThaumicBoots.getItem("item.ItemNanoComet"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.ThaumicBoots.getItem("item.ItemNanoMeteor"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.ThaumicBoots.getItem("item.ItemNanoVoid"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.ThaumicBoots.getItem("item.ItemQuantumComet"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.ThaumicBoots.getItem("item.ItemQuantumMeteor"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.ThaumicBoots.getItem("item.ItemQuantumVoid"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.ThaumicBoots.getItem("item.ItemVoidComet"),
                OreDictionary.WILDCARD_VALUE);
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.ThaumicBoots.getItem("item.ItemVoidMeteor"),
                OreDictionary.WILDCARD_VALUE);
        }
        if (ModUtils.ThaumicTinkerer.isModLoaded()) {
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.ThaumicTinkerer.getItem("ichorclothBootsGem"),
                OreDictionary.WILDCARD_VALUE);
        }
        if (ModUtils.WitchingGadgets.isModLoaded()) {
            BootProtectionRegistry.instance.registerFullProtectionHandler(
                ModUtils.WitchingGadgets.getItem("item.WG_PrimordialBoots"),
                OreDictionary.WILDCARD_VALUE);
        }
    }
}
