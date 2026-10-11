package com.gtnewhorizon.cropsnh.crops.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

import com.gtnewhorizon.cropsnh.api.IAdditionalCropData;

class CropBuilderValuesTest {

    @Test
    void defaultsMatchCropCard() {
        DataCrop crop = CropBuilder.crop("plain", 0, 0)
            .tier(4)
            .build();
        assertEquals(1, crop.getMachineBreedingRecipeTier());
        assertEquals(-1, crop.getMinSeedBedTier());
        assertEquals((float) Math.pow(0.95, 4), crop.getDropChance());
        assertEquals(-1f, crop.getEntityDamage());
        assertEquals(0.8f, crop.getCrossingThreshold());
        assertEquals(0.8f, crop.getBreedingThreshold());
        assertFalse(crop.isSeedEnchanted());
        assertFalse(crop.hideFromNEI());
        assertNull(crop.getFlavourText());
        assertNull(crop.readAdditionalData(null));
        assertFalse(crop.onRightClick(null, null));
    }

    @Test
    void setsValues() {
        DataCrop crop = CropBuilder.crop("special", 0, 0)
            .machineBreedingTier(3)
            .minSeedBedTier(2)
            .dropChance(1.5f)
            .entityDamage(1.0f)
            .weedsAbove(8)
            .enchantedSeed()
            .hiddenFromNEI()
            .breedingThresholds(-1.0f, -0.5f)
            .flavour("cropsnh_crops.special.flavour")
            .build();
        assertEquals(3, crop.getMachineBreedingRecipeTier());
        assertEquals(2, crop.getMinSeedBedTier());
        assertEquals(1.5f, crop.getDropChance());
        assertEquals(1.0f, crop.getEntityDamage());
        assertEquals(8, crop.startsSpreadingWeedsAt());
        assertTrue(crop.isSeedEnchanted());
        assertTrue(crop.hideFromNEI());
        assertEquals(-1.0f, crop.getCrossingThreshold());
        assertEquals(-0.5f, crop.getBreedingThreshold());
        assertEquals("cropsnh_crops.special.flavour", crop.getFlavourText());
    }

    @Test
    void flavourSupplierIsReadEveryTime() {
        AtomicReference<String> text = new AtomicReference<>("first");
        DataCrop crop = CropBuilder.crop("dynamic", 0, 0)
            .flavour(text::get)
            .build();
        assertEquals("first", crop.getFlavourText());
        text.set(null);
        assertNull(crop.getFlavourText());
    }

    @Test
    void textureBaseKeepsTheDefaultStages() {
        DataCrop crop = CropBuilder.crop("lily", 0, 0)
            .textures("cropsnh:crops/stoneLilies/stone/")
            .build();
        assertEquals("cropsnh:crops/stoneLilies/stone/", crop.getBaseTexturePath());
        assertEquals(1, crop.getMinGrowthStage());
        assertEquals(4, crop.getMaxGrowthStage());
    }

    @Test
    void behavioursAreCalled() {
        List<String> calls = new ArrayList<>();
        IAdditionalCropData data = () -> null;
        DataCrop crop = CropBuilder.crop("busy", 0, 0)
            .onGrowthTick(te -> calls.add("tick"))
            .onEntityCollision((te, entity) -> calls.add("collision"))
            .onRightClick((te, player) -> calls.add("click"))
            .additionalData(nbt -> data)
            .build();
        crop.onGrowthTick(null);
        crop.onEntityCollision(null, null);
        assertTrue(crop.onRightClick(null, null));
        assertSame(data, crop.readAdditionalData(null));
        assertEquals(Arrays.asList("tick", "collision", "click"), calls);
    }
}
