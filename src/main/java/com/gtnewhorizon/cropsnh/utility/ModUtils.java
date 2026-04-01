package com.gtnewhorizon.cropsnh.utility;

import java.util.Locale;

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
        if (item == null)
            throw new IllegalStateException("Item with id \"" + this.ID + ":" + name + "\" could not be found!");
        return item;
    }

    public Block getBlock(String name) {
        Block block = GameRegistry.findBlock(this.ID, name);
        if (block == null)
            throw new IllegalStateException("Block with id \"" + this.ID + ":" + name + "\" could not be found!");
        return block;
    }

    public ItemStack getStack(String name, int amount, int meta) {
        return CropsNHUtils.getModItem(this, name, amount, meta);
    }

    public static class ModIDs {

        public final static String Angelica = "angelica";
        public final static String Avaritia = "Avaritia";
        public final static String BiomesOPlenty = "BiomesOPlenty";
        public final static String BloodMagic = "AWWayofTime";
        public final static String Botania = "Botania";
        public final static String CropsPlusPlus = "berriespp";
        public final static String EtFuturumRequiem = "etfuturum";
        public final static String ExtraUtilities = "ExtraUtilities";
        public final static String ForbiddenMagic = "ForbiddenMagic";
        public final static String Forestry = "Forestry";
        public final static String GalacticraftCore = "GalacticraftCore";
        public final static String GalacticraftMars = "GalacticraftMars";
        public final static String GalaxySpace = "GalaxySpace";
        public final static String GoodGenerator = "GoodGenerator";
        public final static String GregTech = "gregtech";
        public final static String HodgePodge = "hodgepodge";
        public final static String IndustrialCraft2 = "IC2";
        public final static String MagicBees = "MagicBees";
        public final static String Natura = "Natura";
        public final static String NewHorizonsCoreMod = "dreamcraft";
        public final static String NotEnoughItems = "NotEnoughItems";
        public final static String PamsHarvestCraft = "harvestcraft";
        public final static String RandomThings = "RandomThings";
        public final static String TaintedMagic = "TaintedMagic";
        public final static String Thaumcraft = "Thaumcraft";
        public final static String ThaumicBases = "thaumicbases";
        public final static String ThaumicTinkerer = "ThaumicTinkerer";
        public final static String TinkerConstruct = "TConstruct";
        public final static String TwilightForest = "TwilightForest";
        public final static String UtilitiesInExcess = "utilitiesinexcess";
        public final static String WAILA = "Waila";
        public final static String Witchery = "witchery";
        public final static String WitchingGadgets = "WitchingGadgets";
        public final static String Ztones = "Ztones";

    }
}
