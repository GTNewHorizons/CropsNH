package com.gtnewhorizon.cropsnh.blocks.abstracts;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import org.apache.commons.lang3.tuple.Pair;

import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.interfaces.IItemContainer;
import gregtech.common.blocks.BlockCasingsAbstract;
import gregtech.common.blocks.ItemCasings;
import gregtech.common.blocks.MaterialCasings;

public abstract class CropsNHBlockIndustrialFarmTiredComponent extends BlockCasingsAbstract {

    protected final IIcon[] mSideIcons = new IIcon[16];
    protected final IIcon[] mBottomIcons = new IIcon[16];
    protected final IIcon[] mTopIcons = new IIcon[16];
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

    public void registerBlockIcons(IIconRegister aIconRegister) {
        super.registerBlockIcons(aIconRegister);
        this.registerIconArray(aIconRegister, Reference.MOD_ID + ":industrialFarm/sides/", this.mSideIcons);
    }

    protected void registerIconArray(IIconRegister aIconRegister, String name, IIcon[] icons) {
        for (int tier = this.mMinTier; tier <= this.mMaxTier; tier++) {
            icons[tier] = aIconRegister.registerIcon(name + tier);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int aSide, int aMeta) {
        // default handler + basic bricks
        if (aMeta < this.mMinTier || this.mMaxTier < aMeta) {
            return CropsNHUtils.getMissingTexture();
        }
        return switch (aSide) {
            case 0 -> this.mBottomIcons[aMeta] == null ? CropsNHUtils.getMissingTexture() : this.mBottomIcons[aMeta];
            case 1 -> this.mTopIcons[aMeta] == null ? CropsNHUtils.getMissingTexture() : this.mTopIcons[aMeta];
            default -> this.mSideIcons[aMeta] == null ? CropsNHUtils.getMissingTexture() : this.mSideIcons[aMeta];
        };
    }
}
