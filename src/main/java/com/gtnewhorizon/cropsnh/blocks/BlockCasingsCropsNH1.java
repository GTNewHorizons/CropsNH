package com.gtnewhorizon.cropsnh.blocks;

import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.reference.Names;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.GregTechAPI;
import gregtech.common.blocks.BlockCasingsAbstract;
import gregtech.common.blocks.ItemCasings;
import gregtech.common.blocks.MaterialCasings;

public class BlockCasingsCropsNH1 extends BlockCasingsAbstract {

    public BlockCasingsCropsNH1() {
        super(ItemCasings.class, Names.Objects.casings1, MaterialCasings.INSTANCE);
        register(0, CropsNHItemList.BrickedAgriculturalCasing);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int aSide, int aMeta) {
        return GregTechAPI.sBlockCasings4.getIcon(aSide, 15);
    }
}
