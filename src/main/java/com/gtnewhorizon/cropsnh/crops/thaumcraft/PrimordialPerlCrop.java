package com.gtnewhorizon.cropsnh.crops.thaumcraft;

import java.util.ArrayList;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropCard;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.BlockUnderRequirement;

import gregtech.api.enums.Materials;

public class PrimordialPerlCrop extends CropCard {

    private final ArrayList<IWorldGrowthRequirement> growthReqs = new ArrayList<>();
    private final ItemStack[] altSeeds;

    public PrimordialPerlCrop() {
        super("primordialBerry");
        // add ichorium gorwth req
        this.growthReqs.add(
            new BlockUnderRequirement("Ichorium").addMaterial(Materials.Ichorium)
                .addOreDict("oreIchorium", "blockIchorium"));
        // add drops
        ItemStack primPerl = thaumcraft.api.ItemApi.getItem("itemEldritchObject", 3)
            .copy();
        primPerl.stackSize = 1;
        this.dropTable.put(primPerl, 1.0f);
        // add alt seed
        this.altSeeds = new ItemStack[] { primPerl };
    }

    @Override
    public int getTier() {
        return 16;
    }

    @Override
    public ItemStack[] getAlternateSeeds() {
        return this.altSeeds;
    }

    @Override
    public Iterable<IWorldGrowthRequirement> getWorldGrowthRequirements() {
        return this.growthReqs;
    }

    @Override
    public int getGrowthDuration() {
        // slow ass crop
        return 375_000;
    }

    @Override
    public float getDropChance() {
        return 0.5f;
    }

    @Override
    public String getCreator() {
        // original idea came from crops++
        return "bartimaeusnek and ForTheHorde01";
    }

    @Override
    public float getCrossingThreshold() {
        // cannot cross
        return -1.0f;
    }

    @Override
    public float getBreedingThreshold() {
        // cannot breed
        return -1.0f;
    }

    @Override
    public IIcon[] getTextures(IIconRegister register) {
        return getTextures(register, "cropsnh:cropPrimordialBerry", 4);
    }
}
