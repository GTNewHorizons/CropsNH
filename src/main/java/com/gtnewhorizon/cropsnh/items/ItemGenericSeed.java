package com.gtnewhorizon.cropsnh.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropRightClickHandler;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.renderers.items.RenderItemBase;

public class ItemGenericSeed extends ItemCropsNH implements ICropRightClickHandler {

    @Override
    protected String getInternalName() {
        return Names.Objects.genericSeed;
    }

    @Override
    public RenderItemBase getItemRenderer() {
        return null;
    }

    @Override
    public String getUnlocalizedName() {
        return this.getUnlocalizedName(null);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        // Something went wrong so assume it's invalid
        if (stack == null) return Names.L10N.invalidSeed;

        // load the crop card
        ICropCard cropCard = CropRegistry.instance.get(stack);
        if (cropCard == null) return Names.L10N.invalidSeed;

        // if the seed hasn't been analyzed don't name the seeds.
        ISeedStats stats = SeedStats.getStatsFromStack(stack);
        if (stats == null || !stats.isAnalyzed()) return Names.L10N.unknownSeed;

        // else we parse the name
        return cropCard.getUnlocalizedName();
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return super.getIcon(stack, pass);
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        return StatCollector.translateToLocalFormatted(
            Names.L10N.genericSeedFormat,
            StatCollector.translateToLocal(this.getUnlocalizedName(itemStack)));
    }

    @Override
    public boolean onRightClick(ICropStickTile te, EntityPlayer player, ItemStack heldItem) {
        if (heldItem == null || heldItem.stackSize <= 0) return false;
        // attempt to load the seed
        ICropCard cropCard = CropRegistry.instance.get(heldItem);
        if (cropCard == null) return false;
        // attempt to load the stats
        ISeedStats stats = SeedStats.getStatsFromStack(heldItem);
        if (stats == null) return false;

        te.plantSeed(cropCard, stats);
        if (!player.capabilities.isCreativeMode) {
            heldItem.stackSize--;
        }
        return true;
    }
}
