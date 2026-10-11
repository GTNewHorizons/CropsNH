package com.gtnewhorizon.cropsnh.crops.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;

class CropFamiliesTest {

    @Test
    void foodNeedsLight() {
        DataCrop crop = CropFamilies.food("onion", 0, 0)
            .build();
        assertEquals(
            1,
            crop.getGrowthRequirements()
                .size());
    }

    @Test
    void stoneLily() {
        DataCrop crop = CropFamilies.stoneLily("basalt", 0, 0)
            .build();
        assertEquals("cropsnh:basaltLily", crop.getId());
        assertEquals("bartimaeusnek", crop.getCreator());
        assertEquals(1, crop.getTier());
        assertEquals(600, crop.getGrowthDuration());
        assertEquals(PlantRenderShape.X, crop.getRenderShape());
        assertEquals("cropsnh:crops/stoneLilies/basalt/", crop.getBaseTexturePath());
    }

    @Test
    void oreBerry() {
        DataCrop crop = CropFamilies.oreBerry("iron", 0, 0)
            .build();
        assertEquals("cropsnh:ironOreBerry", crop.getId());
        assertEquals(SeedShape.oreberry, crop.getSeedShape());
        assertEquals(5, crop.getTier());
        assertEquals(6000, crop.getGrowthDuration());
        assertEquals(1.0F, crop.getEntityDamage());
        assertEquals("cropsnh:crops/oreBerries/iron/", crop.getBaseTexturePath());
    }

    @Test
    void vanillaFlower() {
        DataCrop crop = CropFamilies.vanillaFlower("poppy", 0, 0)
            .build();
        assertEquals(2, crop.getTier());
        assertEquals(600, crop.getGrowthDuration());
        assertEquals(SeedShape.flower, crop.getSeedShape());
    }

    @Test
    void mushroomNeedsDarkness() {
        DataCrop crop = CropFamilies.mushroom("redMushroom", 0, 0)
            .build();
        assertEquals(
            1,
            crop.getGrowthRequirements()
                .size());
    }
}
