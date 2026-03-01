package com.gtnewhorizon.cropsnh.renderers.blocks;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.cropsnh.CropsNH;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.renderers.TessellatorV2;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class RenderBlockBase extends TileEntitySpecialRenderer
    implements ISimpleBlockRenderingHandler, IItemRenderer {

    private static final HashMap<Block, Integer> renderIds = new HashMap<>();
    public static final int COLOR_MULTIPLIER_STANDARD = 16777215;

    private final Block block;

    protected RenderBlockBase(Block block, boolean inventory) {
        this(block, null, inventory);
    }

    protected RenderBlockBase(Block block, TileEntity te, boolean inventory) {
        this.block = block;
        if (!renderIds.containsKey(block)) {
            this.registerRenderer(block, te);
        }
        if (inventory) {
            MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(block), this);
        }
    }

    private void registerRenderer(Block block, TileEntity te) {
        if (te != null && this.shouldBehaveAsTESR()) {
            ClientRegistry.bindTileEntitySpecialRenderer(te.getClass(), this);
            renderIds.put(block, -1);
        }
        if (this.shouldBehaveAsISBRH()) {
            int id = RenderingRegistry.getNextAvailableRenderId();
            RenderingRegistry.registerBlockHandler(id, this);
            renderIds.put(block, id);
        }
    }

    // WORLD
    // -----
    private boolean renderBlock(Tessellator tessellator, IBlockAccess world, double x, double y, double z, Block block,
        TileEntity tile, float f, int modelId, RenderBlocks renderer, boolean callFromTESR) {
        if (callFromTESR) {
            GL11.glPushMatrix();
            GL11.glTranslated(x, y, z);
        } else {
            if (tessellator instanceof TessellatorV2) {
                ((TessellatorV2) tessellator).setRotation(0, 0, 0, 0);
            }
            tessellator.addTranslation((float) x, (float) y, (float) z);
        }

        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, (int) x, (int) y, (int) z));
        tessellator.setColorRGBA_F(1, 1, 1, 1);

        boolean result = doWorldRender(tessellator, world, x, y, z, tile, block, f, modelId, renderer, callFromTESR);

        if (callFromTESR) {
            GL11.glTranslated(-x, -y, -z);
            GL11.glPopMatrix();
        } else {
            if (tessellator instanceof TessellatorV2) {
                ((TessellatorV2) tessellator).setRotation(0, 0, 0, 0);
            }
            tessellator.addTranslation((float) -x, (float) -y, (float) -z);
        }

        return result;
    }

    @Override
    public final void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
        renderBlock(
            Tessellator.instance,
            tileEntity.getWorldObj(),
            x,
            y,
            z,
            tileEntity.getBlockType(),
            tileEntity,
            f,
            0,
            RenderBlocks.getInstance(),
            true);
    }

    @Override
    public final boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        return renderBlock(
            TessellatorV2.instance,
            world,
            x,
            y,
            z,
            block,
            world.getTileEntity(x, y, z),
            0,
            modelId,
            renderer,
            false);
    }

    protected abstract boolean doWorldRender(Tessellator tessellator, IBlockAccess world, double x, double y, double z,
        TileEntity tile, Block block, float f, int modelId, RenderBlocks renderer, boolean callFromTESR);

    // INVENTORY
    // ---------
    @Override
    public final void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}

    @Override
    public final boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public final boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public final boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public final void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        doInventoryRender(type, item, data);
        GL11.glPopMatrix();
    }

    protected abstract void doInventoryRender(ItemRenderType type, ItemStack item, Object... data);

    // HELPER METHODS
    // --------------
    public final Block getBlock() {
        return this.block;
    }

    public abstract boolean shouldBehaveAsTESR();

    public abstract boolean shouldBehaveAsISBRH();

    @Override
    public final int getRenderId() {
        return CropsNH.proxy.getRenderId(this.getBlock());
    }

    public static int getRenderId(Block block) {
        return renderIds.getOrDefault(block, -1);
    }

    // UTILITY METHODS
    // ---------------

    // adds a vertex to the tessellator scaled with 1/16th of a block

    // same as above method, but does not require the correct texture to be bound
    protected void addScaledVertexWithUV(Tessellator tessellator, float x, float y, float z, float u, float v,
        IIcon icon) {
        float unit = Constants.UNIT;
        tessellator.addVertexWithUV(x * unit, y * unit, z * unit, icon.getInterpolatedU(u), icon.getInterpolatedV(v));
    }

    // draws a rectangular prism
    protected void drawScaledPrism(Tessellator tessellator, float minX, float minY, float minZ, float maxX, float maxY,
        float maxZ, IIcon icon, int colorMultiplier) {
        // bottom
        drawScaledFaceBackXZ(tessellator, minX, minZ, maxX, maxZ, icon, minY / 16.0F, colorMultiplier);
        // top
        drawScaledFaceFrontXZ(tessellator, minX, minZ, maxX, maxZ, icon, maxY / 16.0F, colorMultiplier);
        // back
        drawScaledFaceBackXY(tessellator, minX, minY, maxX, maxY, icon, minZ / 16.0F, colorMultiplier);
        // front
        drawScaledFaceFrontXY(tessellator, minX, minY, maxX, maxY, icon, maxZ / 16.0F, colorMultiplier);
        // left
        drawScaledFaceBackYZ(tessellator, minY, minZ, maxY, maxZ, icon, minX / 16.0F, colorMultiplier);
        // right
        drawScaledFaceFrontYZ(tessellator, minY, minZ, maxY, maxZ, icon, maxX / 16.0F, colorMultiplier);

    }

    protected void drawScaledFaceFrontXY(Tessellator tessellator, float minX, float minY, float maxX, float maxY,
        IIcon icon, float z, int colorMultiplier) {
        z = z * 16.0F;
        float minV = 16 - maxY;
        float maxV = 16 - minY;
        applyColorMultiplier(tessellator, colorMultiplier, ForgeDirection.SOUTH);
        addScaledVertexWithUV(tessellator, maxX, maxY, z, maxX, minV, icon);
        addScaledVertexWithUV(tessellator, minX, maxY, z, minX, minV, icon);
        addScaledVertexWithUV(tessellator, minX, minY, z, minX, maxV, icon);
        addScaledVertexWithUV(tessellator, maxX, minY, z, maxX, maxV, icon);
    }

    protected void drawScaledFaceFrontXZ(Tessellator tessellator, float minX, float minZ, float maxX, float maxZ,
        IIcon icon, float y, int colorMultiplier) {
        y = y * 16.0F;
        applyColorMultiplier(tessellator, colorMultiplier, ForgeDirection.UP);
        addScaledVertexWithUV(tessellator, maxX, y, maxZ, maxX, maxZ, icon);
        addScaledVertexWithUV(tessellator, maxX, y, minZ, maxX, minZ, icon);
        addScaledVertexWithUV(tessellator, minX, y, minZ, minX, minZ, icon);
        addScaledVertexWithUV(tessellator, minX, y, maxZ, minX, maxZ, icon);
    }

    protected void drawScaledFaceFrontYZ(Tessellator tessellator, float minY, float minZ, float maxY, float maxZ,
        IIcon icon, float x, int colorMultiplier) {
        x = x * 16.0F;
        float minV = 16 - maxY;
        float maxV = 16 - minY;
        applyColorMultiplier(tessellator, colorMultiplier, ForgeDirection.EAST);
        addScaledVertexWithUV(tessellator, x, maxY, maxZ, maxZ, minV, icon);
        addScaledVertexWithUV(tessellator, x, minY, maxZ, maxZ, maxV, icon);
        addScaledVertexWithUV(tessellator, x, minY, minZ, minZ, maxV, icon);
        addScaledVertexWithUV(tessellator, x, maxY, minZ, minZ, minV, icon);
    }

    protected void drawScaledFaceBackXY(Tessellator tessellator, float minX, float minY, float maxX, float maxY,
        IIcon icon, float z, int colorMultiplier) {
        z = z * 16.0F;
        float minV = 16 - maxY;
        float maxV = 16 - minY;
        applyColorMultiplier(tessellator, colorMultiplier, ForgeDirection.NORTH);
        addScaledVertexWithUV(tessellator, maxX, maxY, z, maxX, minV, icon);
        addScaledVertexWithUV(tessellator, maxX, minY, z, maxX, maxV, icon);
        addScaledVertexWithUV(tessellator, minX, minY, z, minX, maxV, icon);
        addScaledVertexWithUV(tessellator, minX, maxY, z, minX, minV, icon);
    }

    protected void drawScaledFaceBackXZ(Tessellator tessellator, float minX, float minZ, float maxX, float maxZ,
        IIcon icon, float y, int colorMultiplier) {
        y = y * 16.0F;
        applyColorMultiplier(tessellator, colorMultiplier, ForgeDirection.DOWN);
        addScaledVertexWithUV(tessellator, maxX, y, maxZ, maxX, maxZ, icon);
        addScaledVertexWithUV(tessellator, minX, y, maxZ, minX, maxZ, icon);
        addScaledVertexWithUV(tessellator, minX, y, minZ, minX, minZ, icon);
        addScaledVertexWithUV(tessellator, maxX, y, minZ, maxX, minZ, icon);
    }

    protected void drawScaledFaceBackYZ(Tessellator tessellator, float minY, float minZ, float maxY, float maxZ,
        IIcon icon, float x, int colorMultiplier) {
        x = x * 16.0F;
        float minV = 16 - maxY;
        float maxV = 16 - minY;
        applyColorMultiplier(tessellator, colorMultiplier, ForgeDirection.WEST);
        addScaledVertexWithUV(tessellator, x, maxY, maxZ, maxZ, minV, icon);
        addScaledVertexWithUV(tessellator, x, maxY, minZ, minZ, minV, icon);
        addScaledVertexWithUV(tessellator, x, minY, minZ, minZ, maxV, icon);
        addScaledVertexWithUV(tessellator, x, minY, maxZ, maxZ, maxV, icon);
    }

    protected void applyColorMultiplier(Tessellator tessellator, int colorMultiplier, ForgeDirection side) {
        float preMultiplier;
        if (tessellator instanceof TessellatorV2) {
            preMultiplier = getMultiplier(transformSide((TessellatorV2) tessellator, side));
        } else {
            preMultiplier = getMultiplier(side);
        }
        float r = preMultiplier * ((float) (colorMultiplier >> 16 & 255) / 255.0F);
        float g = preMultiplier * ((float) (colorMultiplier >> 8 & 255) / 255.0F);
        float b = preMultiplier * ((float) (colorMultiplier & 255) / 255.0F);
        tessellator.setColorOpaque_F(r, g, b);
    }

    protected ForgeDirection transformSide(TessellatorV2 tessellator, ForgeDirection dir) {
        if (dir == ForgeDirection.UNKNOWN) {
            return dir;
        }
        double[] coords = tessellator.getTransformationMatrix()
            .transform(dir.offsetX, dir.offsetY, dir.offsetZ);
        double[] translation = tessellator.getTransformationMatrix()
            .getTranslation();
        coords[0] = coords[0] - translation[0];
        coords[1] = coords[1] - translation[1];
        coords[2] = coords[2] - translation[2];
        double x = Math.abs(coords[0]);
        double y = Math.abs(coords[1]);
        double z = Math.abs(coords[2]);
        if (x > z) {
            if (x > y) {
                return coords[0] > 0 ? ForgeDirection.EAST : ForgeDirection.WEST;
            }
        } else {
            if (z > y) {
                return coords[2] > 0 ? ForgeDirection.SOUTH : ForgeDirection.NORTH;
            }
        }
        return coords[1] > 0 ? ForgeDirection.UP : ForgeDirection.DOWN;
    }

    protected float getMultiplier(ForgeDirection side) {
        switch (side) {
            case DOWN:
                return 0.5F;
            case NORTH:
            case SOUTH:
                return 0.8F;
            case EAST:
            case WEST:
                return 0.6F;
            default:
                return 1;
        }
    }

    /**
     * utility method used for debugging rendering
     */
    @SuppressWarnings("unused")
    protected void drawAxisSystem(boolean startDrawing) {
        Tessellator tessellator = Tessellator.instance;

        if (startDrawing) {
            tessellator.startDrawingQuads();
        }

        tessellator.addVertexWithUV(-0.005F, 2, 0, 1, 0);
        tessellator.addVertexWithUV(0.005F, 2, 0, 0, 0);
        tessellator.addVertexWithUV(0.005F, -1, 0, 0, 1);
        tessellator.addVertexWithUV(-0.005F, -1, 0, 1, 1);

        tessellator.addVertexWithUV(2, -0.005F, 0, 1, 0);
        tessellator.addVertexWithUV(2, 0.005F, 0, 0, 0);
        tessellator.addVertexWithUV(-1, 0.005F, 0, 0, 1);
        tessellator.addVertexWithUV(-1, -0.005F, 0, 1, 1);

        tessellator.addVertexWithUV(0, -0.005F, 2, 1, 0);
        tessellator.addVertexWithUV(0, 0.005F, 2, 0, 0);
        tessellator.addVertexWithUV(0, 0.005F, -1, 0, 1);
        tessellator.addVertexWithUV(0, -0.005F, -1, 1, 1);

        if (startDrawing) {
            tessellator.draw();
        }
    }
}
