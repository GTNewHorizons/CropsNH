package com.gtnewhorizon.cropsnh.crops.data;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;

import org.junit.jupiter.api.Test;

import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;

class DataCropTest {

    @Test
    void defaultsMatchNHCropCard() {
        DataCrop crop = CropBuilder.crop("example", 0x112233, 0x445566)
            .build();
        assertEquals("cropsnh:example", crop.getId());
        assertEquals("CropsNH Team", crop.getCreator());
        assertEquals("cropsnh_crops.example", crop.getUnlocalizedName());
        assertEquals(1, crop.getTier());
        assertEquals(600, crop.getGrowthDuration());
        assertEquals(1, crop.getMinGrowthStage());
        assertEquals(4, crop.getMaxGrowthStage());
        assertEquals(SeedShape.vanilla, crop.getSeedShape());
        assertEquals(PlantRenderShape.Hash, crop.getRenderShape());
        assertEquals("cropsnh:crops/example/", crop.getBaseTexturePath());
        assertEquals(new Color(0x112233).getRGB(), crop.getPrimarySeedColor());
        assertEquals(new Color(0x445566).getRGB(), crop.getSecondarySeedColor());
    }

    @Test
    void overriddenValuesAreReturned() {
        DataCrop crop = new DataCrop("garlic", 0, 0);
        crop.creator = "bartimaeusnek";
        crop.unlocalizedName = "tile.witchery:garlicplant.name";
        crop.tier = 3;
        crop.growthDuration = 450;
        crop.minGrowthStage = 0;
        crop.maxGrowthStage = 5;
        crop.seedShape = SeedShape.magic;
        crop.renderShape = PlantRenderShape.X;
        crop.baseTexturePath = "witchery:garlic_stage_";
        assertEquals("bartimaeusnek", crop.getCreator());
        assertEquals("tile.witchery:garlicplant.name", crop.getUnlocalizedName());
        assertEquals(3, crop.getTier());
        assertEquals(450, crop.getGrowthDuration());
        assertEquals(0, crop.getMinGrowthStage());
        assertEquals(5, crop.getMaxGrowthStage());
        assertEquals(SeedShape.magic, crop.getSeedShape());
        assertEquals(PlantRenderShape.X, crop.getRenderShape());
        assertEquals("witchery:garlic_stage_", crop.getBaseTexturePath());
    }

    @Test
    void growthDurationFollowsTierUntilSet() {
        DataCrop crop = new DataCrop("tiered", 0, 0);
        crop.tier = 4;
        assertEquals(2400, crop.getGrowthDuration());
        crop.growthDuration = 800;
        assertEquals(800, crop.getGrowthDuration());
    }

    @Test
    void registersOneTexturePerStageByDefault() {
        DataCrop crop = new DataCrop("garlic", 0, 0);
        crop.baseTexturePath = "witchery:garlic_stage_";
        crop.minGrowthStage = 0;
        crop.maxGrowthStage = 5;
        assertArrayEquals(
            new String[] { "witchery:garlic_stage_0", "witchery:garlic_stage_1", "witchery:garlic_stage_2",
                "witchery:garlic_stage_3", "witchery:garlic_stage_4", "witchery:garlic_stage_5" },
            registeredIcons(crop));
    }

    @Test
    void registersExplicitTextureIcons() {
        DataCrop crop = new DataCrop("turnip", 0, 0);
        crop.textureIcons = new String[] { "carrots_stage_0", "carrots_stage_1", "carrots_stage_2",
            "cropsnh:crops/turnip/3" };
        assertArrayEquals(crop.textureIcons, registeredIcons(crop));
    }

    private static String[] registeredIcons(DataCrop crop) {
        List<String> names = new ArrayList<>();
        IIconRegister register = name -> {
            names.add(name);
            return null;
        };
        crop.registerTextures(register);
        return names.toArray(new String[0]);
    }
}
