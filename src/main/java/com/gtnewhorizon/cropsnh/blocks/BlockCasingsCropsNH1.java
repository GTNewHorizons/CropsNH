package com.gtnewhorizon.cropsnh.blocks;

import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.init.CropsNHBlockTextures;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Names;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.common.blocks.BlockCasingsAbstract;
import gregtech.common.blocks.ItemCasings;
import gregtech.common.blocks.MaterialCasings;

public class BlockCasingsCropsNH1 extends BlockCasingsAbstract {

    public BlockCasingsCropsNH1() {
        super(ItemCasings.class, Names.Objects.casings1, MaterialCasings.INSTANCE, 1);
        register(0, CropsNHItemList.BrickedAgriculturalCasing);
    }

    @Override
    public int getTextureIndex(int aMeta) {
        // Page 20, 0-15 (only using 0 rn)
        return (Constants.GT_CASING_PAGE << 7) | (aMeta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int aSide, int aMeta) {
        return CropsNHBlockTextures.Casing_Bricked_Agricultural_Casing.getIcon();
    }

}
