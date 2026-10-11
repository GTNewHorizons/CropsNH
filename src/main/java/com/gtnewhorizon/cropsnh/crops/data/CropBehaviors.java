package com.gtnewhorizon.cropsnh.crops.data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.Constants;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.IAdditionalCropData;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.farming.SeedData;
import com.gtnewhorizon.cropsnh.farming.registries.BootProtectionRegistry;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropSticks;
import com.gtnewhorizon.cropsnh.utility.XSTR;

/**
 * Behaviours that crops pass to {@link CropBuilder} as method references.
 */
public final class CropBehaviors {

    private CropBehaviors() {}

    // region goldfish

    /**
     * The goldfish's flavour text, unless existential dread has been put to an end in the config.
     */
    public static String goldfishFlavour() {
        return ConfigurationHandler.putAnEndToExistentialDread ? null : Reference.MOD_ID + "_crops.goldfish.flavour";
    }

    /**
     * Screams now and then while growing.
     */
    public static void screamSometimes(ICropStickTile crop) {
        if (crop instanceof TileEntityCropSticks && XSTR.XSTR_INSTANCE.nextInt(512) == 42) {
            scream((TileEntityCropSticks) crop);
        }
    }

    /**
     * Screams when a player steps on it.
     */
    public static void screamWhenSteppedOn(ICropStickTile crop, Entity entity) {
        if (entity.worldObj.isRemote) return;
        // Wearing armored shoes won't reduce the amount of screams.
        // If anything it should increase them, but I'm not that mean.
        // Just don't step on em or use an IF and they won't need to scream much ;)
        // also screaming probably shouldn't be breaking boots.
        if (ConfigurationHandler.goldfishScreamWhenSteppedOn && entity instanceof EntityPlayer
            && crop instanceof TileEntityCropSticks) {
            scream((TileEntityCropSticks) crop);
        }
    }

    private static void scream(TileEntityCropSticks crop) {
        if (ConfigurationHandler.putAnEndToExistentialDread) return;
        crop.getWorldObj()
            .playSoundEffect(
                crop.xCoord,
                crop.yCoord,
                crop.zCoord,
                ConfigurationHandler.goldfishScream,
                crop.isMature() ? 5.0f : 3.0f * crop.getGrowthPercent(),
                crop.isMature() ? 0.5f : (4.0f - crop.getGrowthPercent()));
    }

    // endregion goldfish

    // region pyrolusium

    /**
     * Pyrolusium's flavour text is an easter egg, shown only while easter eggs are enabled in the config.
     */
    public static String pyrolusiumFlavour() {
        return ConfigurationHandler.enableEasterEggs ? Reference.MOD_ID + "_crops.pyrolusium.flavour" : null;
    }

    // endregion pyrolusium

    // region necrobloom

    /**
     * Poisons living entities that walk into it once it's mature, unless their boots protect them.
     */
    public static void poisonOnContact(ICropStickTile te, Entity entity) {
        if (entity.worldObj.isRemote) return;
        if (te.isMature() && entity instanceof EntityLivingBase elb) {
            // creative players don't get affected
            if ((entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.disableDamage)) {
                return;
            }
            // check if the entity's boots protect them
            final float protection = te.getBootProtection(elb);
            if (protection >= BootProtectionRegistry.FULL_PROTECTION) return;
            // reduce the duration by the multiplier
            int duration = (XSTR.XSTR_INSTANCE.nextInt(10) + 5) * 20;
            float multiplier = 1 - (protection / BootProtectionRegistry.FULL_PROTECTION);
            // explicit casting because intellij be dumb
            duration = (int) (duration * multiplier);
            if (duration <= 0) return;
            // add potion effect of defined duration
            elb.addPotionEffect(new PotionEffect(Potion.poison.id, duration, 0));
        }
    }

    // endregion necrobloom

    // region gaia wart transformation

    /**
     * Right-clicking nether wart with a snow block may turn it into gaia wart.
     */
    public static boolean turnIntoGaiaWart(ICropStickTile te, EntityPlayer player) {
        IAdditionalCropData dataUnparsed = te.getAdditionalCropData();
        GaiaWartChance data;
        if (!(dataUnparsed instanceof GaiaWartChance)) {
            data = new GaiaWartChance();
            te.setAdditionalCropData(data);
        } else {
            data = (GaiaWartChance) dataUnparsed;
        }
        ItemStack heldItem = player.getHeldItem();
        if (heldItem == null || heldItem.getItem() == null) return false;
        // if right click with snow blocks netherwart may turn into gaia wart.
        if (heldItem.getItem() == Item.getItemFromBlock(Blocks.snow)) {
            if (data.getChance() > XSTR.XSTR_INSTANCE.nextInt(10_000)) {
                ISeedStats stats = te.getSeed()
                    .getStats();
                te.clear();
                te.plantSeed(new SeedData(CropsNHCrops.GaiaWart, stats));
            } else {
                // Increase the chance just to make it slightly more likely and to act a bad luck protection.
                // At most, I'd like players to spend a stack of snow blocks on this.
                data.increaseChance();
            }
            if (!player.capabilities.isCreativeMode) {
                player.getHeldItem().stackSize--;
            }
            return true;
        }
        return false;
    }

    public static IAdditionalCropData readGaiaWartChance(NBTTagCompound nbt) {
        return new GaiaWartChance(nbt);
    }

    static class GaiaWartChance implements IAdditionalCropData {

        private final static String CHANCE_NBT_KEY = "chance";
        private final static int DEFAULT_CONVERSION_CHANCE = 100;
        private final static int MAX_CONVERSION_CHANCE = 500;
        private final static int CONVERSION_INCREASE = 10;

        private int conversionChance;

        public GaiaWartChance(int conversionChance) {
            this.conversionChance = Math.max(0, Math.min(MAX_CONVERSION_CHANCE, conversionChance));
        }

        public GaiaWartChance() {
            this(DEFAULT_CONVERSION_CHANCE);
        }

        public GaiaWartChance(NBTTagCompound nbt) {
            this(
                nbt.hasKey(CHANCE_NBT_KEY, Constants.NBT.TAG_INT) ? nbt.getInteger(CHANCE_NBT_KEY)
                    : DEFAULT_CONVERSION_CHANCE);
        }

        public int getChance() {
            return this.conversionChance;
        }

        public void increaseChance() {
            this.conversionChance = Math.min(MAX_CONVERSION_CHANCE, this.conversionChance + CONVERSION_INCREASE);
        }

        @Override
        public NBTTagCompound writeToNBT() {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger(CHANCE_NBT_KEY, this.conversionChance);
            return nbt;
        }
    }

    // endregion gaia wart transformation
}
