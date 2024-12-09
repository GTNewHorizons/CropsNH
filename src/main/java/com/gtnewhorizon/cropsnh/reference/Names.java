package com.gtnewhorizon.cropsnh.reference;

public final class Names {

    public static class L10N {

        private static final String modPrefix = Reference.MOD_ID.toLowerCase() + "_";
        private static final String genericSeedPrefix = modPrefix + "genericSeeds.";
        private static final String growthReqPrefix = modPrefix + "growthReq.";
        public static final String unknownSeed = genericSeedPrefix + "unknown";
        public static final String invalidSeed = genericSeedPrefix + "invalid";
        public static final String genericSeedFormat = genericSeedPrefix + "format";
        public static final String blockUnderReqFormat = growthReqPrefix + "format";
    }

    // NBT tags
    public static class NBT {

        // #region seed stuff
        public static final String crop = "crop";
        public static final String growth = "gr";
        public static final String gain = "ga";
        public static final String resistance = "re";
        public static final String analyzed = "scan";
        // #endregio seed stuff

        // #region crop stick stuff
        public static final String failedChecks = "issues";
        public static final String crossCrop = "crossCrop";
        public static final String sick = "sick";
        public static final String progress = "progress";
        public static final String extra = "extra";
        public static final String water = "water";
        public static final String fertilizer = "fert";
        public static final String weedEx = "weedex";
        // #endregion crop stick stuff

        public static final String material = "material";
        public static final String materialMeta = "matMeta";
        public static final String x = "cropsnhX";
        public static final String y = "cropsnhY";
        public static final String z = "cropsnhZ";
        public static final String x2 = "cropsnhX2";
        public static final String y2 = "cropsnhY2";
        public static final String z2 = "cropsnhZ2";
        public static final String direction = "direction";
        public static final String multiBlock = "cropsnh_MultiBlock";
    }

    // mod objects
    public static class Objects {

        public static final String cropSticksTE = "cropSticksTE";
        public static final String cropSticks = "cropSticks";
        public static final String seed = "seed";
        public static final String magnifyingGlass = "magnifyingGlass";
        public static final String spade = "spade";
        public static final String genericSeed = "genericSeed";

        // crops
        public static final String terraWart = "terraWart";
    }

    // tile entities
    public static class TileEntity {

        public static final String tileEntity = "TileEntity";
    }

    // ore dictionary entries
    public static class OreDict {

        public static final String listAllseed = "listAllseed";
        public static final String plankWood = "plankWood";
        public static final String slabWood = "slabWood";
    }

    // mod ids
    public static class Mods {

        public static final String ancientWarfare = "AncientWarfare";
        public static final String appleCore = "AppleCore";
        public static final String arsMagica = "arsmagica2";
        public static final String bloodMagic = "AWWayofTime";
        public static final String bluePower = "bluepower";
        public static final String biomesOPlenty = "BiomesOPlenty";
        public static final String botania = "Botania";
        public static final String chococraft = "chococraft";
        public static final String computerCraft = "ComputerCraft";
        public static final String etFuturum = "etfuturum";
        public static final String extraUtilities = "ExtraUtilities";
        public static final String exNihilo = "exnihilo";
        public static final String forestry = "Forestry";
        public static final String ganysNether = "ganysnether";
        public static final String ganysSurface = "ganyssurface";
        public static final String harderWildLife = "HarderWildlife";
        public static final String harvestcraft = "harvestcraft";
        public static final String harvestTheNether = "harvestthenether";
        public static final String hungerOverhaul = "HungerOverhaul";
        public static final String mcMultipart = "McMultipart";
        public static final String mfr = "MineFactoryReloaded";
        public static final String millenaire = "millenaire";
        public static final String minetweaker = "MineTweaker3";
        public static final String natura = "Natura";
        public static final String nei = "NotEnoughItems";
        public static final String openComputers = "OpenComputers";
        public static final String plantMegaPack = "plantmegapack";
        public static final String psychedelicraft = "psychedelicraft";
        public static final String railcraft = "Railcraft";
        public static final String tconstruct = "TConstruct";
        public static final String thaumcraft = "Thaumcraft";
        public static final String waila = "Waila";
        public static final String weeeFlowers = "weeeflowers";
        public static final String witchery = "witchery";
    }
}
