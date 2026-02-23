package com.gtnewhorizon.cropsnh.reference;

import com.gtnewhorizon.cropsnh.Tags;

public final class Reference {
    public static final String MOD_NAME = "CropsNH";
    public static final String MOD_ID = "CropsNH";

    @Deprecated
    public static final String VER_MAJOR = "@MAJOR@";
    @Deprecated
    public static final String VER_MINOR = "@MINOR@";
    @Deprecated
    public static final String VER_REVIS = "@REVIS@";
    @Deprecated
    public static final String MOD_VERSION = VER_MAJOR + "." + VER_MINOR + "." + VER_REVIS;
    public static final String VERSION = Tags.VERSION;

    public static final String AUTHOR = "InfinityRaider";
    public static final String CLIENT_PROXY_CLASS = "com.gtnewhorizon.cropsnh.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "com.gtnewhorizon.cropsnh.proxy.ServerProxy";
    public static final String GUI_FACTORY_CLASS = "com.gtnewhorizon.cropsnh.gui.GuiFactory";
}
