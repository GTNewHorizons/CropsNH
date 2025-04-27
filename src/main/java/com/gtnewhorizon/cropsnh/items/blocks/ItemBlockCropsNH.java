package com.gtnewhorizon.cropsnh.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import com.gtnewhorizon.cropsnh.creativetab.CropsNHTab;
import com.gtnewhorizon.cropsnh.renderers.items.RenderItemBase;

/**
 * The root item for all CropsNH ItemBlocks.
 * <p>
 * This class may appear pointless, but it is key in setting up a proper inheritance chain for the mod.
 * This class allows for all of the mod's ItemBlocks to refer to the same thing, as well as allow for the future adding
 * of common elements.
 * </p>
 */
public class ItemBlockCropsNH extends ItemBlock {

    /**
     * The default constructor.
     * A super call to this is generally all that is needed in subclasses.
     *
     * @param block the block associated with this item.
     */
    public ItemBlockCropsNH(Block block) {
        super(block);
        this.setCreativeTab(CropsNHTab.cropsNHTab);
    }

    public RenderItemBase getItemRenderer() {
        return null;
    }

}
