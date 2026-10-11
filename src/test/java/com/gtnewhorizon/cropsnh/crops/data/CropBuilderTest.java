package com.gtnewhorizon.cropsnh.crops.data;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;

class CropBuilderTest {

    @Test
    void setsValues() {
        DataCrop crop = CropBuilder.crop("garlic", 0xA88F7B, 0xF3E7DC)
            .creator("bartimaeusnek")
            .name("tile.witchery:garlicplant.name")
            .tier(3)
            .growth(450)
            .seedShape(SeedShape.magic)
            .renderShape(PlantRenderShape.X)
            .textures("witchery:garlic_stage_", 0, 5)
            .build();
        assertEquals("bartimaeusnek", crop.getCreator());
        assertEquals("tile.witchery:garlicplant.name", crop.getUnlocalizedName());
        assertEquals(3, crop.getTier());
        assertEquals(450, crop.getGrowthDuration());
        assertEquals(SeedShape.magic, crop.getSeedShape());
        assertEquals(PlantRenderShape.X, crop.getRenderShape());
        assertEquals("witchery:garlic_stage_", crop.getBaseTexturePath());
        assertEquals(0, crop.getMinGrowthStage());
        assertEquals(5, crop.getMaxGrowthStage());
    }

    @Test
    void whenTrueAppliesThePart() {
        DataCrop crop = CropBuilder.crop("a", 0, 0)
            .when(true, b -> b.creator("yes"))
            .build();
        assertEquals("yes", crop.getCreator());
    }

    @Test
    void whenFalseNeverRunsThePart() {
        DataCrop crop = CropBuilder.crop("a", 0, 0)
            .when(false, b -> { throw new AssertionError("must not run"); })
            .build();
        assertEquals("CropsNH Team", crop.getCreator());
    }

    @Test
    void whenFalseRunsTheOtherwisePart() {
        DataCrop crop = CropBuilder.crop("a", 0, 0)
            .when(false, b -> { throw new AssertionError("must not run"); }, b -> b.creator("otherwise"))
            .build();
        assertEquals("otherwise", crop.getCreator());
    }

    @Test
    void keepsPoolsAndMutationsForLater() {
        CropBuilder builder = CropBuilder.crop("tomato", 0, 0)
            .pools("red", "edible")
            .mutation("onion", "redTulip")
            .mutation(
                m -> m.parents("wheat", "carrot", "potato")
                    .machineOnly());
        assertEquals(Arrays.asList("red", "edible"), builder.pools());
        assertEquals(
            2,
            builder.mutations()
                .size());
        assertArrayEquals(
            new String[] { "cropsnh:wheat", "cropsnh:carrot", "cropsnh:potato" },
            builder.mutations()
                .get(1)
                .getParentIds());
    }

    @Test
    void rejectsAMutationWithoutParents() {
        assertThrows(
            IllegalArgumentException.class,
            () -> CropBuilder.crop("a", 0, 0)
                .mutation(MutationSpec::machineOnly));
    }
}
