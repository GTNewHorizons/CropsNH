package com.gtnewhorizon.cropsnh.blocks.abstracts;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.util.IIcon;

import org.apache.commons.lang3.tuple.Pair;

import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.GregTechAPI;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.interfaces.IItemContainer;
import gregtech.common.blocks.BlockCasingsAbstract;
import gregtech.common.blocks.ItemCasings;
import gregtech.common.blocks.MaterialCasings;

public abstract class CropsNHBlockIndustrialFarmTiredComponent extends BlockCasingsAbstract {

    protected IIcon mTopIcon = null;
    protected IIcon mBottomIcon = null;
    private final int mMinTier;
    private final int mMaxTier;
    private final List<Pair<Block, Integer>> mStructureComponents;

    protected CropsNHBlockIndustrialFarmTiredComponent(String aName, IItemContainer... aItems) {
        this(aName, VoltageIndex.MV, VoltageIndex.UXV, aItems);
    }

    protected CropsNHBlockIndustrialFarmTiredComponent(String aName, int aMinTier, int aMaxTier,
        IItemContainer... aItems) {
        super(ItemCasings.class, aName, MaterialCasings.INSTANCE);
        // validateParams
        if (aMinTier > aMaxTier) {
            throw new IllegalArgumentException("minTier is greater than maxTier");
        }
        this.mMinTier = aMinTier;
        this.mMaxTier = aMaxTier;
        int tExpectedLength = this.mMaxTier - this.mMinTier + 1;
        if (tExpectedLength != aItems.length) {
            throw new IllegalArgumentException("item list isn't of expected length: " + tExpectedLength);
        }
        this.mStructureComponents = new ArrayList<>(tExpectedLength);
        // register metas
        int i = 0, tMeta = this.mMinTier;
        for (IItemContainer item : aItems) {
            register(tMeta, item);
            this.mStructureComponents.add(Pair.of(this, tMeta));
            i++;
            tMeta++;
        }
    }

    public List<Pair<Block, Integer>> getStructureBlocks() {
        return mStructureComponents;
    }

    @Nullable
    public Integer getTier(int aMeta) {
        if (aMeta < this.mMinTier || this.mMaxTier < aMeta) return null;
        return aMeta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int aSide, int aMeta) {
        // default handler + basic bricks
        if (aMeta < this.mMinTier || this.mMaxTier < aMeta) {
            return CropsNHUtils.getMissingTexture();
        }
        switch (aSide) {
            case 0:
                return this.mBottomIcon == null ? CropsNHUtils.getMissingTexture() : this.mBottomIcon;
            case 1:
                return this.mTopIcon == null ? CropsNHUtils.getMissingTexture() : this.mTopIcon;
            default:
                if (aMeta <= VoltageIndex.UHV) {
                    return GregTechAPI.sBlockCasings1.getIcon(aSide, aMeta);
                }
                return GregTechAPI.sBlockCasingsNH.getIcon(aSide, aMeta);
        }
    }
}
