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
        public static final String weedEX = "weedex";
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

        // produce
        public static final String terraWart = "terraWart";
        public static final String goldfish = "goldfish";
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
}
