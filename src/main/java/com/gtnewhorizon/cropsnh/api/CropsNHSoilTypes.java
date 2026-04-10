package com.gtnewhorizon.cropsnh.api;

import com.gtnewhorizon.cropsnh.farming.registries.CompoundSoilList;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public final class CropsNHSoilTypes {

    private CropsNHSoilTypes() {}

    /** For crops that generally grow on farm land like food crops. */
    public static final ISoilList farmland = SoilRegistry.instance.get("farmland");
    /** For crops that grow exclusively on sand like cactus */
    public static final ISoilList sand = SoilRegistry.instance.get("sand");
    /** For wart and oil related crops */
    public static final ISoilList soulsand = SoilRegistry.instance.get("soulsand");
    /** For flowers, trees and any grass-like item that is generally only on grass or dirt */
    public static final ISoilList dirtGrass = SoilRegistry.instance.get("dirt");
    /** For mushrooms */
    public static final ISoilList mycelium = SoilRegistry.instance.get("mycelium");
    /** For end related crops */
    public static final ISoilList end = SoilRegistry.instance.get("end");
    /** For ore-related crops, and stone lilies */
    public static final ISoilList stone = SoilRegistry.instance.get("stone");
    /** For nether related crops */
    public static final ISoilList netherrack = SoilRegistry.instance.get("netherrack");
    /** Trololololol lololol lololol lololol */
    public static final ISoilList brick = SoilRegistry.instance.get("brick");
    /** Mana Bean */
    public static final ISoilList thaumLogs = SoilRegistry.instance.get("thaumLogs");
    /** Zombie/Undead related crops */
    public static final ISoilList graveyard = SoilRegistry.instance.get("graveyard");
    /** Slime related crops */
    public static final ISoilList slimy = SoilRegistry.instance.get("slimy");

    public static final ISoilList mushroom = new CompoundSoilList(stone, dirtGrass, mycelium);
    public static final ISoilList netherMushroom = new CompoundSoilList(
        CropsNHSoilTypes.mushroom,
        CropsNHSoilTypes.netherrack);
    public static final ISoilList sugarcane = new CompoundSoilList(sand, dirtGrass);
    public static final ISoilList slimyDirt = new CompoundSoilList(CropsNHSoilTypes.dirtGrass, CropsNHSoilTypes.slimy);

}
