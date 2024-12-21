package com.gtnewhorizon.cropsnh.crops.thaumcraft;

import java.awt.Color;
import java.util.Objects;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemManaBean;

public class CropManaBean extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("silverwoodLog");

    // TODO: ADD THE NON MIXED MANA BEANS

    public CropManaBean() {
        super("manaBean", new Color(0x135C43), new Color(0x2AAF83));
        this.addDrop(getBean(Aspect.AIR), 16_66);
        this.addDrop(getBean(Aspect.FIRE), 16_66);
        this.addDrop(getBean(Aspect.WATER), 16_66);
        this.addDrop(getBean(Aspect.EARTH), 16_66);
        this.addDrop(getBean(Aspect.ORDER), 16_66);
        this.addDrop(getBean(Aspect.ENTROPY), 16_66);
        this.addBlockUnderRequirement("mixedCrystalCluster");
    }

    private static ItemStack getBean(Aspect aspect) {
        ItemStack bean = new ItemStack(ConfigItems.itemManaBean, 1);
        ((ItemManaBean) Objects.requireNonNull(bean.getItem())).setAspects(bean, (new AspectList()).add(aspect, 1));
        return bean;
    }

    @Override
    public String getCreator() {
        return "kuba6000";
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getGrowthDuration() {
        return 2000;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getRenderShape() {
        return PlantRenderer.RENDER_X;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.magic;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
