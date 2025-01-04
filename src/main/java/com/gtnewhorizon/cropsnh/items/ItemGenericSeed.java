package com.gtnewhorizon.cropsnh.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.renderers.items.RenderItemBase;

public class ItemGenericSeed extends ItemCropsNH {

    @Override
    protected String getInternalName() {
        return Names.Objects.genericSeed;
    }

    // region tooltip

    @Override
    public String getUnlocalizedName() {
        return this.getUnlocalizedName(null);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
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
    public String getItemStackDisplayName(ItemStack itemStack) {
        return StatCollector.translateToLocalFormatted(
            Names.L10N.genericSeedFormat,
            StatCollector.translateToLocal(this.getUnlocalizedName(itemStack)));
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        // load the crop card
        ICropCard cropCard = CropRegistry.instance.get(stack);
        if (cropCard == null) return super.getRarity(stack);

        int tier = cropCard.getTier();
        if (tier > 12) {
            return EnumRarity.epic;
        } else if (tier > 8) {
            return EnumRarity.rare;
        } else if (tier > 4) {
            return EnumRarity.uncommon;
        } else {
            return EnumRarity.common;
        }

    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List toolTip, boolean showAdvancedItemTooltips) {
        if (stack == null || !(stack.getItem() instanceof ItemGenericSeed) || !stack.hasTagCompound()) {
            return;
        }
        ICropCard crop = CropRegistry.instance.get(stack);
        if (crop == null) return;

        ISeedStats stats = SeedStats.readFromNBT(stack.getTagCompound());
        if (stats.isAnalyzed()) {
            toolTip.add(
                String.format(
                    "%s- %s: %d%s",
                    EnumChatFormatting.GREEN,
                    StatCollector.translateToLocal("cropsnh_tooltip.growth"),
                    stats.getGrowth(),
                    EnumChatFormatting.RESET));
            toolTip.add(
                String.format(
                    "%s- %s: %d%s",
                    EnumChatFormatting.GREEN,
                    StatCollector.translateToLocal("cropsnh_tooltip.gain"),
                    stats.getGain(),
                    EnumChatFormatting.RESET));
            toolTip.add(
                String.format(
                    "%s- %s: %d%s",
                    EnumChatFormatting.GREEN,
                    StatCollector.translateToLocal("cropsnh_tooltip.resistance"),
                    stats.getResistance(),
                    EnumChatFormatting.RESET));
            Iterable<IWorldGrowthRequirement> reqs = crop.getWorldGrowthRequirements();
            if (reqs != null) {
                for (IWorldGrowthRequirement req : reqs) {
                    toolTip.add(req.getDescription());
                }
            }
            if (crop.getFlavourText() == null) {
                toolTip.add(StatCollector.translateToLocal(crop.getFlavourText()));
            }
        } else {
            toolTip.add(" " + StatCollector.translateToLocal("cropsnh_tooltip.unidentified"));
        }
    }

    // endregion tooltip

    // region rendering

    @Override
    public RenderItemBase getItemRenderer() {
        return null;
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public int getRenderPasses(int metadata) {
        return 2;
    }

    @Override
    public void registerIcons(IIconRegister reg) {
        for (SeedShape shape : SeedShape.values()) {
            shape.registerIcons(reg);
        }
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        // load the crop card
        ICropCard cropCard = CropRegistry.instance.get(stack);
        if (cropCard == null) return pass == 0 ? 0x000000 : 0xffffff;
        return pass == 0 ? cropCard.getPrimarySeedColor()
            .getRGB()
            : cropCard.getSecondarySeedColor()
                .getRGB();
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        // load the crop card
        ICropCard cropCard = CropRegistry.instance.get(stack);
        ISeedShape shape = cropCard == null ? SeedShape.vanilla : cropCard.getSeedShape();
        return pass == 0 ? shape.getDarkIcon() : shape.getLightIcon();
    }

    @Override
    public IIcon getIconIndex(ItemStack stack) {
        return getIcon(stack, 0);
    }

    @Override
    public boolean hasEffect(ItemStack stack, int pass) {
        // load the crop card
        ICropCard cropCard = CropRegistry.instance.get(stack);
        return cropCard != null && cropCard.isSeedEnchanted();
    }

    // endregion rendering
}
