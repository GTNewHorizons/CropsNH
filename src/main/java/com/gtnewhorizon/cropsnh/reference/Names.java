package com.gtnewhorizon.cropsnh.reference;

public final class Names {

    public static class L10N {

        private static final String modPrefix = Reference.MOD_ID + "_";
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
        public static final String amount = "amount";
        public static final String growth = "gr";
        public static final String gain = "ga";
        public static final String resistance = "re";
        public static final String analyzed = "scan";
        // #endregio seed stuff

        // #region crop stick stuff
        public static final String crossCrop = "crossCrop";
        public static final String sick = "sick";
        public static final String progress = "progress";
        public static final String extra = "extra";
        public static final String water = "water";
        public static final String fertilizer = "fert";
        public static final String weedEX = "weedex";
        // #endregion crop stick stuff
    }

    public static class DataOrb {

        public static final String specimen = "Crop-Specimen-Scan";
        public static final String growth = "Crop-Growth-Scan";
        public static final String gain = "Crop-Gain-Scan";
        public static final String resistance = "Crop-Resistance-Scan";
    }

    // mod objects
    public static class Objects {

        public static final String cropSticksTE = "cropSticksTE";
        public static final String cropSticks = "cropSticks";
        public static final String seed = "seed";
        public static final String plantLens = "plantLens";
        public static final String spade = "spade";
        public static final String reinforcedSpade = "reinforcedSpade";
        public static final String genericSeed = "genericSeed";
        // needs to be prefixed to properly uniquify the l10n
        public static final String casings1 = Reference.MOD_ID + ".casings1";
        public static final String seedBed = Reference.MOD_ID + ".seedBed";
        public static final String environmentalEnhancementUnit = Reference.MOD_ID + ".environmentalEnhancementUnit";
        public static final String growthAccelerationUnit = Reference.MOD_ID + ".growthAccelerationUnit";
        public static final String fertilizerUnit = Reference.MOD_ID + ".fertilizerUnit";
        public static final String advancedHarvestingUnit = Reference.MOD_ID + ".advancedHarvestingUnit";
        public static final String OverclockGrowthAccelerationUnit = Reference.MOD_ID
            + ".overclockedGrowthAccelerationUnit";

        // produce
        public static final String gaiaWart = "gaiaWart";
        public static final String goldfish = "goldfish";
    }

    // tile entities
    public static class TileEntity {

        public static final String tileEntity = "TileEntity";
    }
}
