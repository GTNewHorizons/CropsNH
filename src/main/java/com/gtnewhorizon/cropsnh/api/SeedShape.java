package com.gtnewhorizon.cropsnh.api;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;

public enum SeedShape implements ISeedShape {

    oreberry,
    // TODO: PAM SEED SHAPE
    bonsai,
    // TODO: STONE LILY SEED SHAPE
    // TODO: MOB CROP SEED SHAPE
    botania,
    flower,
    grains,
    magic,
    spore,
    vanilla,
    troll;

    final String texturePath;
    IIcon darkIcon = null;
    IIcon lightIcon = null;

    SeedShape() {
        this.texturePath = Reference.MOD_ID + ":"
            + Names.Objects.genericSeed
            + "/"
            + this.name()
                .toLowerCase();
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public void registerIcons(IIconRegister reg) {
        this.darkIcon = reg.registerIcon(texturePath + "1");
        this.lightIcon = reg.registerIcon(texturePath + "2");
    }

    @Override
    public IIcon getDarkIcon() {
        return this.darkIcon;
    }

    @Override
    public IIcon getLightIcon() {
        return this.lightIcon;
    }

}
