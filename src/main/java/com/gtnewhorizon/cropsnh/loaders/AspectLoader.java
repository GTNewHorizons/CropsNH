package com.gtnewhorizon.cropsnh.loaders;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.IMaterialLeafVariant;
import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.items.produce.ItemMaterialLeaf;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.enums.Materials;
import gregtech.api.enums.TCAspects;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class AspectLoader {

    public static void postInit() {
        if (!ModUtils.Thaumcraft.isModLoaded()) return;
        loadItemAspects();
        loadCropAspects();
        loadAlchoolAspects();
    }

    private static void loadItemAspects() {
        ThaumcraftApi.registerObjectTag(
            new ItemStack(CropsNHItems.genericSeed, 1, 0),
            new AspectList().add(Aspect.CROP, 1)
                .add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.cropSticks.get(1),
            new AspectList().add(Aspect.FIRE, 3)
                .add(Aspect.HEAL, 2)
                .add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.fertilizer.get(1),
            new AspectList().add(Aspect.FIRE, 3)
                .add(Aspect.HEAL, 2)
                .add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.poisonPowder.get(1),
            new AspectList().add(Aspect.POISON, 3)
                .add(Aspect.CROP, 2));
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.weedEX.get(1),
            new AspectList().add(Aspect.POISON, 8)
                .add(Aspect.MIND, 2));
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.weedEX.get(1),
            new AspectList().add(Aspect.HEAL, 8)
                .add(Aspect.MIND, 2));
    }

    private static void loadAlchoolAspects() {
        AspectList aspects = new AspectList().add(Aspect.CROP, 1)
            .add(Aspect.PLANT, 1)
            .add(Aspect.HUNGER, 1);
        if (ModUtils.ForbiddenMagic.isModLoaded()) {
            aspects.add(Aspect.getAspect("gula"), 1);
        }
        ThaumcraftApi.registerObjectTag(CropsNHItemList.fermentedWheatBottle.get(1), aspects);
        ThaumcraftApi.registerObjectTag(CropsNHItemList.kornBottle.get(1), aspects);
        ThaumcraftApi.registerObjectTag(CropsNHItemList.doppelkornBottle.get(1), aspects);
        ThaumcraftApi.registerObjectTag(CropsNHItemList.fermentedReedwaterBottle.get(1), aspects);
        ThaumcraftApi.registerObjectTag(CropsNHItemList.sugarWhineBottle.get(1), aspects);
        ThaumcraftApi.registerObjectTag(CropsNHItemList.mashBottle.get(1), aspects);
        ThaumcraftApi.registerObjectTag(CropsNHItemList.washBottle.get(1), aspects);
        ThaumcraftApi.registerObjectTag(CropsNHItemList.highProofBottle.get(1), aspects);
        ThaumcraftApi.registerObjectTag(CropsNHItemList.realJagermeisterBottle.get(1), aspects);
        ThaumcraftApi.registerObjectTag(CropsNHItemList.fakeJagermeisterBottle.get(1), aspects);
    }

    private static void loadCropAspects() {
        // default aspects for all crops
        // spotless:off
        AspectList defaultLeafAspects = new AspectList()
            .add(Aspect.CROP, 1)
            .add(Aspect.PLANT, 1);
        AspectList defaultTwigAspects = new AspectList()
            .add(Aspect.CROP, 1)
            .add(Aspect.TREE, 1);
        // spotless:on
        for (IMaterialLeafVariant variant : ItemMaterialLeaf.getRegisteredVariants()) {
            ThaumcraftApi.registerObjectTag(variant.get(1), defaultLeafAspects.copy());
        }

        // custom tuning
        // spotless:off

        // region food crops
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.gaiaWart.get(1),
            new AspectList()
                .add(Aspect.AURA, 8)
                .add(Aspect.MAGIC, 4)
                .add(Aspect.LIFE, 4)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.milkWart.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.WATER, 1)
                .add(Aspect.HEAL, 1)
        );
        addCropAspectsBasedOnMaterial(CropsNHItemList.sugarBeet.get(1), Materials.Sugar, defaultLeafAspects);
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.hops.get(1),
            new AspectList()
                .add(Aspect.PLANT, 4)
        );
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.goldfish.get(1),
            new AspectList()
                .add(Aspect.FLESH, 3)
                .add(Aspect.LIFE, 1)
                .add(Aspect.WATER, 1)
        );

        /* for when the GT food crops are moved over here
        ItemList.Crop_Drop_Chilly.set(
            addItem(
                Crop_Drop_Chilly.ID,
                "Chilly Pepper",
                "It is red and hot",
                "cropChilipepper",
                new GTFoodStat(1, 0.3F, EnumAction.eat, null, false, true, false, Potion.confusion.id, 200, 1, 40),
                new TCAspects.TC_AspectStack(TCAspects.MESSIS, 1L),
                new TCAspects.TC_AspectStack(TCAspects.IGNIS, 1L),
                new TCAspects.TC_AspectStack(TCAspects.FAMES, 1L)));
        ItemList.Crop_Drop_Lemon.set(
            addItem(
                Crop_Drop_Lemon.ID,
                "Lemon",
                "Don't make Lemonade",
                "cropLemon",
                new GTFoodStat(1, 0.3F, EnumAction.eat, null, false, true, false),
                new TCAspects.TC_AspectStack(TCAspects.MESSIS, 1L),
                new TCAspects.TC_AspectStack(TCAspects.HERBA, 1L),
                new TCAspects.TC_AspectStack(TCAspects.FAMES, 1L)));
        ItemList.Crop_Drop_Tomato.set(
            addItem(
                Crop_Drop_Tomato.ID,
                "Tomato",
                "Solid Ketchup",
                "cropTomato",
                new GTFoodStat(1, 0.2F, EnumAction.eat, null, false, true, false),
                new TCAspects.TC_AspectStack(TCAspects.MESSIS, 1L),
                new TCAspects.TC_AspectStack(TCAspects.HERBA, 1L),
                new TCAspects.TC_AspectStack(TCAspects.FAMES, 1L)));
        ItemList.Crop_Drop_MTomato.set(
            addItem(
                Crop_Drop_MTomato.ID,
                "Max Tomato",
                "Full Health in one Tomato",
                "cropTomato",
                new GTFoodStat(
                    9,
                    1.0F,
                    EnumAction.eat,
                    null,
                    false,
                    true,
                    false,
                    Potion.regeneration.id,
                    100,
                    100,
                    100),
                new TCAspects.TC_AspectStack(TCAspects.MESSIS, 1L),
                new TCAspects.TC_AspectStack(TCAspects.SANO, 3L),
                new TCAspects.TC_AspectStack(TCAspects.FAMES, 1L)));
        ItemList.Crop_Drop_Grapes.set(
            addItem(
                Crop_Drop_Grapes.ID,
                "Grapes",
                "Source of Wine",
                "cropGrape",
                new GTFoodStat(2, 0.3F, EnumAction.eat, null, false, true, false),
                new TCAspects.TC_AspectStack(TCAspects.MESSIS, 1L),
                new TCAspects.TC_AspectStack(TCAspects.HERBA, 1L),
                new TCAspects.TC_AspectStack(TCAspects.FAMES, 1L)));
        ItemList.Crop_Drop_Onion.set(
            addItem(
                Crop_Drop_Onion.ID,
                "Onion",
                "Taking over the whole Taste",
                "cropOnion",
                new GTFoodStat(2, 0.2F, EnumAction.eat, null, false, true, false),
                new TCAspects.TC_AspectStack(TCAspects.MESSIS, 1L),
                new TCAspects.TC_AspectStack(TCAspects.HERBA, 1L),
                new TCAspects.TC_AspectStack(TCAspects.FAMES, 1L)));
        ItemList.Crop_Drop_Cucumber.set(
            addItem(
                Crop_Drop_Cucumber.ID,
                "Cucumber",
                "Not a Sea Cucumber!",
                "cropCucumber",
                new GTFoodStat(1, 0.2F, EnumAction.eat, null, false, true, false),
                new TCAspects.TC_AspectStack(TCAspects.MESSIS, 1L),
                new TCAspects.TC_AspectStack(TCAspects.HERBA, 1L),
                new TCAspects.TC_AspectStack(TCAspects.FAMES, 1L)));
         */
        // endregion food crops

        // region material crops
        addCropAspectsBasedOnMaterial(CropsNHItemList.bauxiaLeaf.get(1), Materials.Aluminium, defaultLeafAspects);
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.canolaFLower.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.PLANT, 1)
                .add(Aspect.ENERGY, 1)
        );
        addCropAspectsBasedOnMaterial(CropsNHItemList.copponFiber.get(1), Materials.Copper, defaultLeafAspects);
        addCropAspectsBasedOnMaterial(CropsNHItemList.galvaniaLeaf.get(1), Materials.Zinc, defaultLeafAspects);
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.indigoBlossom.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.SENSES, 1)
        );
        addCropAspectsBasedOnMaterial(CropsNHItemList.iridineFlower.get(1), Materials.Iridium, defaultLeafAspects);
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.magicEssence.get(1),
            new AspectList()
                .add(Aspect.PLANT, 1)
                .add(Aspect.EXCHANGE, 1)
                .add(Aspect.LIGHT, 1)
                .add(Aspect.MAGIC, 1)
        );
        addCropAspectsBasedOnMaterial(CropsNHItemList.iridineFlower.get(1), Materials.Mica, defaultLeafAspects);
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.oilBerry.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.WATER, 1)
                .add(Aspect.ENERGY, 1)
        );
        addCropAspectsBasedOnMaterial(CropsNHItemList.osmianthFlower.get(1), Materials.Osmium, defaultLeafAspects);
        addCropAspectsBasedOnMaterial(CropsNHItemList.platinaLeaf.get(1), Materials.Platinum, defaultLeafAspects);
        addCropAspectsBasedOnMaterial(CropsNHItemList.pyrolusiumLeaf.get(1), Materials.Manganese, defaultLeafAspects);
        addCropAspectsBasedOnMaterial(CropsNHItemList.reactoriaLeaf.get(1), Materials.Uranium, defaultLeafAspects);
        addCropAspectsBasedOnMaterial(CropsNHItemList.reactoriaStem.get(1), Materials.Uranium, defaultTwigAspects);
        addCropAspectsBasedOnMaterial(CropsNHItemList.scheeliniumLeaf.get(1), Materials.Tungsten, defaultLeafAspects);
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.spaceFlower.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.VOID, 1)
                .add(Aspect.ENTROPY, 1)
        );
        addCropAspectsBasedOnMaterial(CropsNHItemList.stargatiumLeaf.get(1), Materials.Naquadah, defaultLeafAspects);
        addCropAspectsBasedOnMaterial(CropsNHItemList.thunderLeaf.get(1), Materials.Thorium, defaultLeafAspects);
        addCropAspectsBasedOnMaterial(CropsNHItemList.tineTwig.get(1), Materials.Tin, defaultLeafAspects);
        addCropAspectsBasedOnMaterial(CropsNHItemList.titaniaLeaf.get(1), Materials.Titanium, defaultLeafAspects);
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
        addCropAspectsBasedOnMaterial(CropsNHItemList.saltyRoot.get(1), Materials.Salt, defaultTwigAspects);
        addCropAspectsBasedOnMaterial(CropsNHItemList.plumbiliaLeaf.get(1), Materials.Lead, defaultLeafAspects);
        addCropAspectsBasedOnMaterial(CropsNHItemList.argentiaLeaf.get(1), Materials.Silver, defaultLeafAspects);
        addCropAspectsBasedOnMaterial(CropsNHItemList.ferrofernLeaf.get(1), Materials.Iron, defaultLeafAspects);
        addCropAspectsBasedOnMaterial(CropsNHItemList.auroniaLeaf.get(1), Materials.Gold, defaultLeafAspects);
        ThaumcraftApi.registerObjectTag(
            CropsNHItemList.bobsYerUncleBerry.get(1),
            new AspectList()
                .add(Aspect.CROP, 1)
                .add(Aspect.CRYSTAL, 1)
                .add(Aspect.GREED, 1)
        );
        {
            AspectList starWartAspects = new AspectList()
                .add(defaultLeafAspects)
                .add(Aspect.ELDRITCH, 1)
                .add(Aspect.MAGIC, 1)
                .add(Aspect.ORDER, 1)
                .add(Aspect.LIGHT, 1);
            if (ModUtils.ForbiddenMagic.isModLoaded()) {
                starWartAspects
                    .add(Aspect.getAspect("infernus"), 1)
                    .add(Aspect.getAspect("superbia"), 1);
            }
            ThaumcraftApi.registerObjectTag(
                CropsNHItemList.starWart.get(1),
                new AspectList()
                    .add(defaultLeafAspects)
                    .add(Aspect.CRYSTAL, 1)
                    .add(Aspect.GREED, 1)
            );
        }

        // endregion material crops

        // spotless:on
    }

    private static void addCropAspectsBasedOnMaterial(ItemStack stack, Materials material, AspectList extraAspects) {
        AspectList aspects = new AspectList().add(extraAspects);
        for (TCAspects.TC_AspectStack aspect : material.mAspects) {
            aspects.add((Aspect) aspect.mAspect.mAspect, (int) aspect.mAmount);
        }
        ThaumcraftApi.registerObjectTag(stack, aspects);
    }
}
