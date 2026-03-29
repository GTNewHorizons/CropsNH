package com.gtnewhorizon.cropsnh.api;

import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public final class CropsNHSoilTypes {

    private CropsNHSoilTypes() {}

    public static final ISoilList farmland = SoilRegistry.instance.get("farmland");
    public static final ISoilList sugarcane = SoilRegistry.instance.get("sugarcane");
    public static final ISoilList sand = SoilRegistry.instance.get("sand");
    public static final ISoilList mushroom = SoilRegistry.instance.get("mushroom");
    public static final ISoilList soulsand = SoilRegistry.instance.get("soulsand");
    public static final ISoilList dirt = SoilRegistry.instance.get("dirt");
    public static final ISoilList mycelium = SoilRegistry.instance.get("mycelium");
    public static final ISoilList nether = SoilRegistry.instance.get("nether");
    public static final ISoilList end = SoilRegistry.instance.get("end");
    public static final ISoilList stone = SoilRegistry.instance.get("stone");
    public static final ISoilList snow = SoilRegistry.instance.get("snow");
    public static final ISoilList netherrack = SoilRegistry.instance.get("netherrack");
    public static final ISoilList brick = SoilRegistry.instance.get("brick");
    public static final ISoilList thaumLogs = SoilRegistry.instance.get("thaumLogs");
    public static final ISoilList silverwoodLog = SoilRegistry.instance.get("silverwoodLog");
    public static final ISoilList graveyard = SoilRegistry.instance.get("graveyard");
    public static final ISoilList slimy = SoilRegistry.instance.get("slimy");

}
