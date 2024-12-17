package com.gtnewhorizon.cropsnh.handler;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.items.ItemGenericSeed;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@SuppressWarnings("unused")
public class ItemToolTipHandler {

    /** Adds tooltips for seed stats */
    @SubscribeEvent
    public void addSeedStatsTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.itemStack;
        if (stack == null || !(stack.getItem() instanceof ItemGenericSeed) || !stack.hasTagCompound()) {
            return;
        }
        ICropCard crop = CropRegistry.instance.get(stack);
        if (crop == null) return;

        ISeedStats stats = SeedStats.readFromNBT(stack.getTagCompound());
        if (stats.isAnalyzed()) {
            event.toolTip.add(
                String.format(
                    "%s- %s: %d%s",
                    EnumChatFormatting.GREEN,
                    StatCollector.translateToLocal("cropsnh_tooltip.growth"),
                    stats.getGrowth(),
                    EnumChatFormatting.RESET));
            event.toolTip.add(
                String.format(
                    "%s- %s: %d%s",
                    EnumChatFormatting.GREEN,
                    StatCollector.translateToLocal("cropsnh_tooltip.gain"),
                    stats.getGain(),
                    EnumChatFormatting.RESET));
            event.toolTip.add(
                String.format(
                    "%s- %s: %d%s",
                    EnumChatFormatting.GREEN,
                    StatCollector.translateToLocal("cropsnh_tooltip.resistance"),
                    stats.getResistance(),
                    EnumChatFormatting.RESET));
            Iterable<IWorldGrowthRequirement> reqs = crop.getWorldGrowthRequirements();
            if (reqs != null) {
                for (IWorldGrowthRequirement req : reqs) {
                    event.toolTip.add(req.getDescription());
                }
            }
        } else {
            event.toolTip.add(" " + StatCollector.translateToLocal("cropsnh_tooltip.unidentified"));
        }
    }
}
