package com.gtnewhorizon.cropsnh.crops.definitions;

import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BEAN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BLAZE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BROWN;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EMISSIVE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.GRAY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.MAGICAL;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.ORANGE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.POISONOUS;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.SILVER;
import static net.minecraftforge.common.BiomeDictionary.Type.DRY;
import static net.minecraftforge.common.BiomeDictionary.Type.END;
import static net.minecraftforge.common.BiomeDictionary.Type.FOREST;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.LUSH;

import java.util.Objects;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.data.CropBuilder;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.ItemManaBean;

public final class ThaumcraftCrops {

    private ThaumcraftCrops() {}

    public static void register() {
        if (!ModUtils.Thaumcraft.isModLoaded()) return;
        CropsNHCrops.Cinderpearl = CropBuilder.crop("cinderpearl", 0xFF9611, 0xFFD86F)
            .drop(CropsNHUtils.getModItem(ModUtils.Thaumcraft, "blockCustomPlant", 1, 3), 100_00)
            .seed(CropsNHUtils.getModItem(ModUtils.Thaumcraft, "blockCustomPlant", 1, 3))
            .subSoil(CropsNHSubSoilTypes.blaze)
            .duplicationCatalyst("dustBlaze", 2)
            .likes(HOT, DRY, BiomeDictionary.Type.MAGICAL)
            .creator("bartimaeusnek and mitchej123")
            .tier(5)
            .growth(4000)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.dirtGrass)
            .seedShape(SeedShape.magic)
            .pools(ORANGE, MAGICAL, BLAZE)
            .mutation("emberMoss", "glintWeed")
            .register();
        CropsNHCrops.Shimmerleaf = CropBuilder.crop("shimmerleaf", 0x78A59C, 0xA9C6C1)
            .drop(CropsNHUtils.getModItem(ModUtils.Thaumcraft, "blockCustomPlant", 1, 2), 100_00)
            .seed(CropsNHUtils.getModItem(ModUtils.Thaumcraft, "blockCustomPlant", 1, 2))
            .subSoil(CropsNHSubSoilTypes.quicksilver)
            .duplicationCatalyst("nuggetMercury", 1)
            .likes(BiomeDictionary.Type.MAGICAL, LUSH)
            .creator("bartimaeusnek and DreamMasterXXL")
            .tier(5)
            .growth(4000)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.dirtGrass)
            .seedShape(SeedShape.magic)
            // prim perl can't be bred, only planted with an actual prim perl
            // redstraw because shimmerleaf is a quickilver/mercury source and redstone is also that
            // not really silver dropping, but mercury is close enough™
            .pools(GRAY, EMISSIVE, MAGICAL, POISONOUS, SILVER)
            .mutation("whiteTulip", "redstraw")
            .register();
        CropsNHCrops.ManaBean = CropBuilder.crop("manaBean", 0x135C43, 0x2AAF83)
            .drop(manaBean(Aspect.AIR), 16_66)
            .drop(manaBean(Aspect.FIRE), 16_66)
            .drop(manaBean(Aspect.WATER), 16_66)
            .drop(manaBean(Aspect.EARTH), 16_66)
            .drop(manaBean(Aspect.ORDER), 16_66)
            .drop(manaBean(Aspect.ENTROPY), 16_66)
            .subSoil(CropsNHSubSoilTypes.mixedCrystalCluster)
            .duplicationCatalyst("shardAir", 1)
            .duplicationCatalyst("shardFire", 1)
            .duplicationCatalyst("shardWater", 1)
            .duplicationCatalyst("shardEarth", 1)
            .duplicationCatalyst("shardOrder", 1)
            .duplicationCatalyst("shardEntropy", 1)
            .likes(BiomeDictionary.Type.MAGICAL, FOREST)
            .creator("kuba6000")
            .tier(5)
            .growth(2000)
            .stages(1, 3)
            .soil(CropsNHSoilTypes.thaumLogs)
            .seedShape(SeedShape.magic)
            .renderShape(PlantRenderShape.X)
            .pools(BROWN, BEAN, MAGICAL)
            .mutation("cocoa", "cinderpearl", "shimmerleaf")
            .register();
        CropsNHCrops.PrimordialBerry = CropBuilder.crop("primordialBerry", 0x652150, 0xA1D3A9)
            .drop(CropsNHUtils.getModItem(ModUtils.Thaumcraft, "ItemEldritchObject", 1, 3), 10000)
            .seed(CropsNHUtils.getModItem(ModUtils.Thaumcraft, "ItemEldritchObject", 1, 3))
            .likes(END, BiomeDictionary.Type.MAGICAL)
            // original idea came from crops++
            .creator("bartimaeusnek and ForTheHorde01")
            .tier(16)
            .growth(375_000)
            .seedShape(SeedShape.magic)
            .enchantedSeed()
            .renderShape(PlantRenderShape.X)
            .dropChance(0.5f)
            // cannot cross
            // cannot breed
            .breedingThresholds(-1.0f, -1.0f)
            .register();
    }

    private static ItemStack manaBean(Aspect aspect) {
        ItemStack bean = CropsNHUtils.getModItem(ModUtils.Thaumcraft, "ItemManaBean", 1);
        ((ItemManaBean) Objects.requireNonNull(bean.getItem())).setAspects(bean, (new AspectList()).add(aspect, 1));
        return bean;
    }
}
