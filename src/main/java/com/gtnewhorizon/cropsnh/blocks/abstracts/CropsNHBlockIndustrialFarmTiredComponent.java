package com.gtnewhorizon.cropsnh.blocks.abstracts;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import org.apache.commons.lang3.tuple.Pair;

import com.gtnewhorizon.cropsnh.api.CropsNHStructureChannels;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.interfaces.IItemContainer;
import gregtech.common.blocks.BlockCasingsAbstract;
import gregtech.common.blocks.ItemCasings;
import gregtech.common.blocks.MaterialCasings;

public abstract class CropsNHBlockIndustrialFarmTiredComponent extends BlockCasingsAbstract {

    protected final IIcon[] sideIcons = new IIcon[16];
    protected final IIcon[] bottomIcons = new IIcon[16];
    protected final IIcon[] topIcons = new IIcon[16];
    public final int minTier;
    public final int maxTier;
    protected final List<Pair<Block, Integer>> structureComponents;

    protected CropsNHBlockIndustrialFarmTiredComponent(String name, IItemContainer... items) {
        this(name, VoltageIndex.MV, VoltageIndex.UXV, items);
    }

    protected CropsNHBlockIndustrialFarmTiredComponent(String name, int minTier, int maxTier, IItemContainer... items) {
        super(ItemCasings.class, name, MaterialCasings.INSTANCE);
        // validateParams
        if (minTier > maxTier) {
            throw new IllegalArgumentException("minTier is greater than maxTier");
        }
        this.minTier = minTier;
        this.maxTier = maxTier;
        int expectedLength = this.maxTier - this.minTier + 1;
        if (expectedLength != items.length) {
            throw new IllegalArgumentException("item list isn't of expected length: " + expectedLength);
        }
        this.structureComponents = new ArrayList<>(expectedLength);
        // register metas
        int meta = this.minTier;
        for (IItemContainer item : items) {
            register(meta, item);
            if (ModUtils.StructureLib.isModLoaded()) {
                CropsNHStructureChannels.IFTier.registerAsIndicator(new ItemStack(this, 1, meta), meta - 1);
            }
            this.structureComponents.add(Pair.of(this, meta));
            meta++;
        }
    }

    public List<Pair<Block, Integer>> getStructureBlocks() {
        return structureComponents;
    }

    @Nullable
    public Integer getTier(int meta) {
        if (meta < this.minTier || this.maxTier < meta) return null;
        return meta;
    }

    public void registerBlockIcons(IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);
        this.registerIconArray(iconRegister, Reference.MOD_ID + ":industrialFarm/sides/", this.sideIcons);
    }

    protected void registerIconArray(IIconRegister iconRegister, String name, IIcon[] icons) {
        for (int tier = this.minTier; tier <= this.maxTier; tier++) {
            icons[tier] = iconRegister.registerIcon(name + tier);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        // default handler + basic bricks
        if (meta < this.minTier || this.maxTier < meta) {
            return CropsNHUtils.getMissingTexture();
        }
        return switch (side) {
            case 0 -> this.bottomIcons[meta] == null ? CropsNHUtils.getMissingTexture() : this.bottomIcons[meta];
            case 1 -> this.topIcons[meta] == null ? CropsNHUtils.getMissingTexture() : this.topIcons[meta];
            default -> this.sideIcons[meta] == null ? CropsNHUtils.getMissingTexture() : this.sideIcons[meta];
        };
    }
}
