package com.gtnewhorizon.cropsnh.reference;

public final class Reference {

    public static final String MOD_NAME = "CropsNH";
    public static final String MOD_ID = "cropsnh";

    public static final String VER_MAJOR = "@MAJOR@";
    public static final String VER_MINOR = "@MINOR@";
    public static final String VER_REVIS = "@REVIS@";
    public static final String MOD_VERSION = VER_MAJOR + "." + VER_MINOR + "." + VER_REVIS;
    public static final String VERSION = "1.7.10-" + MOD_VERSION;

    public static final String AUTHOR = "InfinityRaider & C0bra5";
    public static final String CLIENT_PROXY_CLASS = "com.gtnewhorizon.cropsnh.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "com.gtnewhorizon.cropsnh.proxy.ServerProxy";

    /** Set to false when running test harnesses, should not be used in runtime code, only in loading code. */
    public static boolean IS_GAME_LOADED = true;
}
