/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 * File Created @ [Jan 14, 2014, 6:48:41 PM (GMT)]
 */
package vazkii.botania.api.internal;

import net.minecraft.client.gui.GuiButton;
import vazkii.botania.api.lexicon.LexiconEntry;

import java.util.List;

/**
 * Internal interface for the Lexicon Entry GUI. This contains
 * everything that can be accessed from it. It's safe to cast
 * this type to GuiScreen.
 */
public interface IGuiLexiconEntry {

	/**
	 * Gets the entry currently portrayed in this gui.
	 */
    LexiconEntry getEntry();

	/**
	 * Gets the current page the lexicon GUI is browsing.
	 */
    int getPageOn();

	/**
	 * Gets the leftmost part of the GUI.
	 */
    int getLeft();

	/**
	 * Gets the topmost part of the GUI.
	 */
    int getTop();

	/**
	 * Gets the GUI's width.
	 */
    int getWidth();

	/**
	 * Gets the GUI's height
	 */
    int getHeight();

	/**
	 * Gets the GUI's Z level for rendering.
	 */
    float getZLevel();

	/**
	 * Gets the list of buttons in this gui.
	 */
    List<GuiButton> getButtonList();

	/**
	 * Gets the total amount of ticks (+ partial ticks) the player
	 * has been in this gui.
	 */
    float getElapsedTicks();

	/**
	 * Gets the current partial ticks.
	 */
    float getPartialTicks();

	/**
	 * Gets the delta (1F = 1 tick) between this render call
	 * and the last one.
	 */
    float getTickDelta();
}
