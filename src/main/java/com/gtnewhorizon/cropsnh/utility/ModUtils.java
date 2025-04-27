package com.gtnewhorizon.cropsnh.utility;

import cpw.mods.fml.common.Loader;

public class ModUtils {

    public final static ModChecker Angelica = new ModChecker("angelica");

    public static class ModChecker {

        private Boolean isLoaded = null;
        private final String modId;

        public ModChecker(String modId) {
            this.modId = modId;
        }

        public boolean isLoaded() {
            return (this.isLoaded == null && (isLoaded = Loader.isModLoaded(this.modId))) || this.isLoaded;
        }
    }
}
