/**
 * This class was created by <Vazkii>. It's distributed as part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the Botania License: http://botaniamod.net/license.php
 *
 * File Created @ [Jun 27, 2015, 7:48:47 PM (GMT)]
 */
package vazkii.botania.api.lexicon.multiblock;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import vazkii.botania.api.lexicon.multiblock.component.MultiblockComponent;

/**
 * A hook for rendering blocks in the multiblock display.
 */
public interface IMultiblockRenderHook {

    Map<Block, IMultiblockRenderHook> renderHooks = new HashMap();

    void renderBlockForMultiblock(IBlockAccess world, Multiblock mb, Block block, int meta, RenderBlocks renderBlocks,
            MultiblockComponent comp, float alpha);

    boolean needsTranslate(Block block);

}
