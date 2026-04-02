package com.gtnewhorizon.cropsnh.utility;

import java.util.Locale;

import gregtech.api.util.GTRecipeBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizon.gtnhlib.util.data.IMod;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public enum ModUtils implements IMod {

    Angelica(ModIDs.Angelica),
    Avaritia(ModIDs.Avaritia),
    BiomesOPlenty(ModIDs.BiomesOPlenty),
    BloodMagic(ModIDs.BloodMagic),
    Botania(ModIDs.Botania),
    Chisel(ModIDs.Chisel),
    CropsPlusPlus(ModIDs.CropsPlusPlus),
    EtFuturumRequiem(ModIDs.EtFuturumRequiem),
    ExtraUtilities(ModIDs.ExtraUtilities),
    ForbiddenMagic(ModIDs.ForbiddenMagic),
    Forestry(ModIDs.Forestry),
    GalacticraftCore(ModIDs.GalacticraftCore),
    GalacticraftMars(ModIDs.GalacticraftMars),
    GalaxySpace(ModIDs.GalaxySpace),
    GoodGenerator(ModIDs.GoodGenerator),
    GregTech(ModIDs.GregTech),
    HodgePodge(ModIDs.HodgePodge),
    IndustrialCraft2(ModIDs.IndustrialCraft2),
    MagicBees(ModIDs.MagicBees),
    Natura(ModIDs.Natura),
    NewHorizonsCoreMod(ModIDs.NewHorizonsCoreMod),
    NotEnoughItems(ModIDs.NotEnoughItems),
    PamsHarvestCraft(ModIDs.PamsHarvestCraft),
    RandomThings(ModIDs.RandomThings),
    TaintedMagic(ModIDs.TaintedMagic),
    Thaumcraft(ModIDs.Thaumcraft),
    ThaumicBases(ModIDs.ThaumicBases),
    ThaumicTinkerer(ModIDs.ThaumicTinkerer),
    TinkerConstruct(ModIDs.TinkerConstruct),
    TwilightForest(ModIDs.TwilightForest),
    UtilitiesInExcess(ModIDs.UtilitiesInExcess),
    Waila(ModIDs.WAILA),
    Witchery(ModIDs.Witchery),
    WitchingGadgets(ModIDs.WitchingGadgets),
    Ztones(ModIDs.Ztones);

    public final String ID;
    public final String resourceDomain;
    protected boolean checked, modLoaded;

    ModUtils(String ID) {
        this.ID = ID;
        this.resourceDomain = ID.toLowerCase(Locale.ENGLISH);
    }

    // isModLoaded is final to allow the JIT to inline this
    @Override
    public final boolean isModLoaded() {
        if (!this.checked) {
            this.modLoaded = Loader.isModLoaded(ID);
            this.checked = true;
        }
        return this.modLoaded;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getResourceLocation() {
        return resourceDomain;
    }

    public String getResourcePath(String path) {
        return this.getResourceLocation(path)
            .toString();
    }

    public String getResourcePath(String... path) {
        return this.getResourceLocation(path)
            .toString();
    }

    public ResourceLocation getResourceLocation(String path) {
        return new ResourceLocation(this.resourceDomain, path);
    }

    public ResourceLocation getResourceLocation(String... path) {
        return new ResourceLocation(this.resourceDomain, String.join("/", path));
    }

    public Item getItem(String name) {
        Item item = GameRegistry.findItem(this.ID, name);
        if (item == null) {
            String fullId = "\"" + this.ID + ":" + name + "\"";
            if (GTRecipeBuilder.PANIC_MODE_NULL) {
                throw new IllegalStateException("Item with id " + fullId + " could not be found!");
            }
            else {
                try {
                    throw new Exception("CROPS NH MISSING ITEM: " + fullId);
                }
                catch (Exception e) {
                    LogHelper.warn( e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return item;
    }

    public Block getBlock(String name) {
        Block block = GameRegistry.findBlock(this.ID, name);

        if (block == null) {
            String fullId = "\"" + this.ID + ":" + name + "\"";
            if (GTRecipeBuilder.PANIC_MODE_NULL) {
                throw new IllegalStateException("block with id " + fullId + " could not be found!");
            }
            else {
                try {
                    throw new Exception("CROPS NH MISSING BLOCK: " + fullId);
                }
                catch (Exception e) {
                    LogHelper.warn(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return block;
    }

    public ItemStack getStack(String name, int amount, int meta) {
        return CropsNHUtils.getModItem(this, name, amount, meta);
    }

    public static class ModIDs {

        public static final String Angelica = "angelica";
        public static final String Avaritia = "Avaritia";
        public static final String BiomesOPlenty = "BiomesOPlenty";
        public static final String BloodMagic = "AWWayofTime";
        public static final String Botania = "Botania";
        public static final String Chisel = "chisel";
        public static final String CropsPlusPlus = "berriespp";
        public static final String EtFuturumRequiem = "etfuturum";
        public static final String ExtraUtilities = "ExtraUtilities";
        public static final String ForbiddenMagic = "ForbiddenMagic";
        public static final String Forestry = "Forestry";
        public static final String GalacticraftCore = "GalacticraftCore";
        public static final String GalacticraftMars = "GalacticraftMars";
        public static final String GalaxySpace = "GalaxySpace";
        public static final String GoodGenerator = "GoodGenerator";
        public static final String GregTech = "gregtech";
        public static final String HodgePodge = "hodgepodge";
        public static final String IndustrialCraft2 = "IC2";
        public static final String Natura = "Natura";
        public static final String MagicBees = "MagicBees";
        public static final String NewHorizonsCoreMod = "dreamcraft";
        public static final String NotEnoughItems = "NotEnoughItems";
        public static final String PamsHarvestCraft = "harvestcraft";
        public static final String RandomThings = "RandomThings";
        public static final String TaintedMagic = "TaintedMagic";
        public static final String Thaumcraft = "Thaumcraft";
        public static final String ThaumicBases = "thaumicbases";
        public static final String ThaumicTinkerer = "ThaumicTinkerer";
        public static final String TinkerConstruct = "TConstruct";
        public static final String TwilightForest = "TwilightForest";
        public static final String UtilitiesInExcess = "utilitiesinexcess";
        public static final String WAILA = "Waila";
        public static final String Witchery = "witchery";
        public static final String WitchingGadgets = "WitchingGadgets";
        public static final String Ztones = "Ztones";

    }
}
