package com.gtnewhorizon.cropsnh.api;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public interface ISeedShape {

    /**
     * Lets you save your gray-scale icon.
     *
     * @param reg The texture registry to pool from.
     */
    void registerIcons(IIconRegister reg);

    /**
     * Gets the base gray-scale texture for an seed shape.
     *
     * @return The gray-scale icon to use for this seed shape.
     */
    IIcon getDarkIcon();

    /**
     * Gets the base gray-scale texture for an seed shape.
     *
     * @return The gray-scale icon to use for this seed shape.
     */
    IIcon getLightIcon();

    /**
     * @return The name of the shape.
     */
    String getName();
}
