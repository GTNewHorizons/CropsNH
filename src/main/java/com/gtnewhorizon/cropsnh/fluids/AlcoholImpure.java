package com.gtnewhorizon.cropsnh.fluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.GregTechAPI;

public class AlcoholImpure extends Fluid implements Runnable {

    public long percentage;
    private final String texture;

    public AlcoholImpure(String name, long percentage, String texture) {
        super(name);
        this.percentage = percentage;
        this.texture = texture;
        this.viscosity = 1125;
        this.isGaseous = false;
        this.temperature = 295;
        this.density = this.viscosity;
        GregTechAPI.sGTBlockIconload.add(this);
        FluidRegistry.registerFluid(this);
    }

    @Override
    public String getUnlocalizedName() {
        return this.getName();
    }

    @Override
    public void run() {
        String ResourcePath = ModUtils.GregTech.getResourceLocation("fluids/fluid." + texture)
            .toString();
        setIcons(GregTechAPI.sBlockIcons.registerIcon(ResourcePath));
    }

    public long getPercentage() {
        return percentage;
    }
}
