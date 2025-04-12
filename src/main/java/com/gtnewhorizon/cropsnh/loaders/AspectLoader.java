package com.gtnewhorizon.cropsnh.loaders;

import com.gtnewhorizon.cropsnh.api.CropOreDuplicationRecipe;
import com.gtnewhorizon.cropsnh.api.CropsNHItemList;

import com.gtnewhorizon.cropsnh.api.IMaterialLeafVariant;
import com.gtnewhorizon.cropsnh.items.produce.ItemMaterialLeaf;
import gregtech.api.enums.Mods;
import gregtech.api.enums.TCAspects;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class AspectLoader {

    public static void postInit() {
        if (!Mods.Thaumcraft.isModLoaded()) return;
        // spotless:off
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.cropSticks.get(1),
            new AspectList()
                .add(Aspect.FIRE, 3)
                .add(Aspect.HEAL, 2)
                .add(Aspect.PLANT, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.terraWart.get(1),
            new AspectList()
                .add(Aspect.AURA, 8)
                .add(Aspect.MAGIC, 4)
                .add(Aspect.LIFE, 4)
        );
        // spotless:on
        AspectList materialLeafAspectList = new AspectList().add(Aspect.CROP, 1).add(Aspect.PLANT, 1);
        outer:
        for (IMaterialLeafVariant variant : ItemMaterialLeaf.getRegisteredVariants()){
            for (CropOreDuplicationRecipe r : variant.getDuplicationRecipes()) {
                ThaumcraftApi.registerObjectTag(variant.get(1), materialLeafAspectList.copy().add(Aspect.METAL, 1));
                continue outer;
            }
            ThaumcraftApi.registerObjectTag(variant.get(1), materialLeafAspectList.copy());
        }
        // spotless:off
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.canolaFLower.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.PLANT, 1)
                .add(Aspect.ENERGY, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.indigoBlossom.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.SENSES, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.magicEssence.get(1),
            new AspectList()
                .add(Aspect.PLANT, 1)
                .add(Aspect.EXCHANGE, 1)
                .add(Aspect.LIGHT, 1)
                .add(Aspect.MAGIC, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.micadiaFlower.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.ARMOR, 1)
                .add(Aspect.TREE, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.milkwart.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.WATER, 1)
                .add(Aspect.HEAL, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.oilBerry.get(1),
            new AspectList()
                .add(Aspect.CROP, 2)
                .add(Aspect.WATER, 1)
                .add(Aspect.ENERGY, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.spaceFlower.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.VOID, 1)
                .add(Aspect.ENTROPY, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.reactoriaLeaf.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add((Aspect) TCAspects.RADIO.mAspect, 1)
                .add(Aspect.PLANT, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.reactoriaStem.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add((Aspect) TCAspects.RADIO.mAspect, 1)
                .add(Aspect.TREE, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.thunderLeaf.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.ENTROPY, 1)
                .add(Aspect.PLANT, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.tineTwig.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.METAL, 1)
                .add(Aspect.TREE, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.uuaBerry.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.WATER, 1)
                .add(Aspect.ENERGY, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.uumBerry.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.WATER, 1)
                .add(Aspect.ENERGY, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.plumbiliaLeaf.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.METAL, 1)
                .add(Aspect.ORDER, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.argentiaLeaf.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.METAL, 1)
                .add(Aspect.GREED, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.ferruLeaf.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.METAL, 1)
                .add(Aspect.GREED, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.teaLeaf.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.HEAL, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.bobsYerUncleBerry.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.CRYSTAL, 1)
                .add(Aspect.GREED, 1)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.hops.get(1),
            new AspectList()
                .add(Aspect.PLANT, 4)
        );
        // spotless:on
    }
}
