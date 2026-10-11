package com.gtnewhorizon.cropsnh.crops.data;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

/**
 * Builders that start with the values a family of crops shares. Each crop then sets what it does differently.
 */
public final class CropFamilies {

    private CropFamilies() {}

    /**
     * Food crops need a light level of 9 or higher.
     */
    public static CropBuilder food(String id, int color1, int color2) {
        return CropBuilder.crop(id, color1, color2)
            .minLight(9);
    }

    /**
     * Mushrooms grow in the dark, on mushroom soils.
     */
    public static CropBuilder mushroom(String id, int color1, int color2) {
        return CropBuilder.crop(id, color1, color2)
            .maxLight(9)
            .soil(CropsNHSoilTypes.mushroom);
    }

    public static CropBuilder vanillaFlower(String id, int color1, int color2) {
        return CropBuilder.crop(id, color1, color2)
            .tier(2)
            .growth(600)
            .soil(CropsNHSoilTypes.dirtGrass)
            .seedShape(SeedShape.flower);
    }

    /**
     * @param material The stone, the crop id is {@code <material>Lily}.
     */
    public static CropBuilder stoneLily(String material, int color1, int color2) {
        return CropBuilder.crop(material + "Lily", color1, color2)
            .creator("bartimaeusnek")
            .tier(1)
            .growth(600)
            .soil(CropsNHSoilTypes.stone)
            .renderShape(PlantRenderShape.X)
            .textures(Reference.MOD_ID + ":crops/stoneLilies/" + material + "/");
    }

    /**
     * @param material The metal, the crop id is {@code <material>OreBerry}.
     */
    public static CropBuilder oreBerry(String material, int color1, int color2) {
        return CropBuilder.crop(material + "OreBerry", color1, color2)
            .seedShape(SeedShape.oreberry)
            .soil(CropsNHSoilTypes.stone)
            .tier(5)
            .growth(6000)
            .entityDamage(1.0F)
            .textures(Reference.MOD_ID + ":crops/oreBerries/" + material + "/");
    }

    public static CropBuilder bonsai(String id, int color1, int color2, String creator, int tier, ItemStack sapling,
        ItemStack log) {
        return bonsai(id, color1, color2, creator, tier, sapling, log, 10);
    }

    /**
     * A tree in a pot: drops its sapling and logs, and is planted with the sapling.
     */
    public static CropBuilder bonsai(String id, int color1, int color2, String creator, int tier, ItemStack sapling,
        ItemStack log, int logCount) {
        ItemStack logDrop = log.copy();
        logDrop.stackSize = logCount;
        return CropBuilder.crop(id, color1, color2)
            .creator(creator)
            .tier(tier)
            .drop(sapling, 30_00)
            .drop(logDrop, 60_00)
            .seed(sapling)
            .soil(CropsNHSoilTypes.dirtGrass)
            .seedShape(SeedShape.bonsai)
            .stages(1, 3)
            .growth(1200)
            .renderShape(PlantRenderShape.X);
    }

    /**
     * @param meta The metadata of the Natura glowshroom item.
     */
    public static CropBuilder naturaGlowshroom(String id, int color1, int color2, int meta) {
        ItemStack glowshroom = CropsNHUtils.getModItem(ModUtils.Natura, "Glowshroom", 1, meta);
        return CropBuilder.crop(id, color1, color2)
            .drop(glowshroom.copy(), 100_00)
            .seed(glowshroom.copy())
            .duplicationCatalyst("dustGlowstone", 1)
            .likes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.MUSHROOM, BiomeDictionary.Type.MAGICAL)
            .creator("bartimaeusnek")
            .tier(3)
            .growth(600)
            .soil(CropsNHSoilTypes.netherMushroom)
            .seedShape(SeedShape.spore)
            .stages(1, 2);
    }
}
