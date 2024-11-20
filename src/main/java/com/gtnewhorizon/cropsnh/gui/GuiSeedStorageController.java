package com.gtnewhorizon.cropsnh.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.cropsnh.container.ContainerSeedStorageController;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.storage.TileEntitySeedStorageController;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSeedStorageController extends GuiSeedStorageBase {

    private static final ResourceLocation texture = new ResourceLocation(
        Reference.MOD_ID.toLowerCase(),
        "textures/gui/GuiSeedStorageController.png");
    private static final int sizeX = 250;
    private static final int sizeY = 176;

    public GuiSeedStorageController(InventoryPlayer inventory, TileEntitySeedStorageController te) {
        super(new ContainerSeedStorageController(inventory, te), 10, 6, 184, 7, 7, 8, 82, 8);
        this.xSize = sizeX;
        this.ySize = sizeY;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft()
            .getTextureManager()
            .bindTexture(texture);
        drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    // draw foreground
    @Override
    public void drawGuiContainerForegroundLayer(int x, int y) {
        if (this.activeSeed != null) {
            this.drawActiveEntries(texture, 82, 35);
        }
    }
}
