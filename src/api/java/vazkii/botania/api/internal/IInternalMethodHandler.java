/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 * File Created @ [Jan 14, 2014, 6:34:34 PM (GMT)]
 */
package vazkii.botania.api.internal;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vazkii.botania.api.boss.IBotaniaBoss;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.api.recipe.RecipeBrew;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.api.subtile.SubTileEntity;

import java.util.List;

/**
 * Any methods that refer to internal methods in Botania are here.
 * This is defaulted to a dummy handler, whose methods do nothing.
 * This handler is set to a proper one on PreInit. Make sure to
 * make your mod load after Botania if you have any intention of
 * doing anythign with this on PreInit.
 */
public interface IInternalMethodHandler {

	LexiconPage textPage(String key);

	LexiconPage elfPaperTextPage(String key);

	LexiconPage imagePage(String key, String resource);

	LexiconPage craftingRecipesPage(String key, List<IRecipe> recipes);

	LexiconPage craftingRecipePage(String key, IRecipe recipe);

	LexiconPage petalRecipesPage(String key, List<RecipePetals> recipes);

	LexiconPage petalRecipePage(String key, RecipePetals recipe);

	LexiconPage runeRecipesPage(String key, List<RecipeRuneAltar> recipes);

	LexiconPage runeRecipePage(String key, RecipeRuneAltar recipe);

	LexiconPage manaInfusionRecipesPage(String key, List<RecipeManaInfusion> recipes);

	LexiconPage manaInfusionRecipePage(String key, RecipeManaInfusion recipe);

	LexiconPage elvenTradePage(String key, List<RecipeElvenTrade> recipes);

	LexiconPage elvenTradesPage(String key, RecipeElvenTrade recipe);

	LexiconPage brewPage(String key, String bottomText, RecipeBrew recipe);

	LexiconPage multiblockPage(String key, MultiblockSet mb);

	IManaNetwork getManaNetworkInstance();

	ItemStack getSubTileAsStack(String subTile);

	ItemStack getSubTileAsFloatingFlowerStack(String subTile);

	String getStackSubTileKey(ItemStack stack);

	IIcon getSubTileIconForName(String name);

	void registerBasicSignatureIcons(String name, IIconRegister register);

	boolean shouldForceCheck();

	int getPassiveFlowerDecay();

	IInventory getBaublesInventory(EntityPlayer player);

	void breakOnAllCursors(EntityPlayer player, Item item, ItemStack stack, int x, int y, int z, int side);

	@SideOnly(Side.CLIENT)
    void drawSimpleManaHUD(int color, int mana, int maxMana, String name, ScaledResolution res);

	@SideOnly(Side.CLIENT)
    void drawComplexManaHUD(int color, int mana, int maxMana, String name, ScaledResolution res, ItemStack bindDisplay, boolean properlyBound);

	@SideOnly(Side.CLIENT)
    ItemStack getBindDisplayForFlowerType(SubTileEntity e);

	@SideOnly(Side.CLIENT)
    void renderLexiconText(int x, int y, int width, int height, String unlocalizedText);

	@SideOnly(Side.CLIENT)
    ResourceLocation getDefaultBossBarTexture();

	@SideOnly(Side.CLIENT)
    void setBossStatus(IBotaniaBoss status);

	boolean isBuildcraftPipe(TileEntity tile);

	void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m);

	long getWorldElapsedTicks();

	boolean isBotaniaFlower(World world, int x, int y, int z);

	void sendBaubleUpdatePacket(EntityPlayer player, int slot);

}
