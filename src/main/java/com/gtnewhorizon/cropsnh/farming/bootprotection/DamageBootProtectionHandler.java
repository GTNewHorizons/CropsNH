package com.gtnewhorizon.cropsnh.farming.bootprotection;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldServer;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ICropsNHBootProtectionHandler;
import com.gtnewhorizon.cropsnh.utility.DebugHelper;
import com.gtnewhorizon.cropsnh.utility.XSTR;

/**
 * An abstractable boot protection handler that damages the worn boots.
 */
public class DamageBootProtectionHandler implements ICropsNHBootProtectionHandler {

    /** The upper boundary of damage rolls (exclusive). */
    public static final int DAMAGE_CHANCE_MAX = 100_00;

    /** The amount to damage the item by. */
    protected final int itemDamage;
    /** The chance to damage the item when it can take damage. */
    protected final int damageChance;
    /** The protection provided by the boots */
    protected final float protection;

    /**
     * @param itemDamage   The amount to damage the item by.
     * @param damageChance The chance to damage the item when it can be damaged.
     * @param protection   The amount of protection provided by the boots.
     */
    public DamageBootProtectionHandler(final int itemDamage, final int damageChance, final float protection) {
        this.itemDamage = itemDamage;
        this.damageChance = damageChance;
        this.protection = protection;
    }

    @Override
    public float getProtection(@NotNull final ItemStack stack, @NotNull final EntityLivingBase entity,
        @NotNull final ICropStickTile cropTE) {
        if (this.canDamageItem(stack, entity, cropTE)
            && XSTR.XSTR_INSTANCE.nextInt(DAMAGE_CHANCE_MAX) <= this.damageChance) {
            makeParticles(entity, stack);
            this.damageItem(stack, entity, cropTE);

            // if the boots broke make some noise and yeet it from the player's inventory.
            if (stack.stackSize <= 0) {
                entity.worldObj.playSoundAtEntity(
                    entity,
                    "random.break",
                    0.8F,
                    0.8F + entity.getRNG()
                        .nextFloat() * 0.4F);

                // make sure it's the same stack (ref equal) so we don't nuke a random item by mistake.
                if (entity.getEquipmentInSlot(1) == stack) {
                    entity.setCurrentItemOrArmor(1, null);
                }
            }
        }
        return this.protection;
    }

    private static void makeParticles(EntityLivingBase ent, ItemStack itemStack) {
        WorldServer s = (WorldServer) ent.worldObj;
        Vec3 vec31 = Vec3.createVectorHelper(ent.posX, (ent.posY + 1) - (double) ent.getEyeHeight() / 2, ent.posZ);
        s.func_147487_a(
            "iconcrack_" + Item.getIdFromItem(itemStack.getItem()),
            vec31.xCoord,
            vec31.yCoord,
            vec31.zCoord,
            3,
            0,
            0,
            0,
            0.1);
    }

    /**
     * Checks if an entity's boots can be damaged.
     *
     * @implNote does not include the percentage chance to damage the item.
     *
     * @param stack  The boots worn by the entity.
     * @param entity The entity wearing the boots.
     * @param cropTE The crop stick tile being collided with.
     * @return True if the entity's boots can be damaged.
     */
    public boolean canDamageItem(@NotNull final ItemStack stack, @NotNull final EntityLivingBase entity,
        @NotNull final ICropStickTile cropTE) {
        // don't do the damage on the client side
        if (entity.worldObj.isRemote) return false;

        // don't damage boots of creative players
        if (entity instanceof EntityPlayer player && player.capabilities.isCreativeMode) return false;

        // Only damage the boots when the player is moving
        return entity.prevPosX != entity.posX || entity.prevPosY != entity.posY || entity.prevPosZ != entity.posZ;
    }

    /**
     * Applies damage to the boots.
     *
     * @param stack  The boots to apply damage to.
     * @param entity The entity wearing the boots.
     * @param cropTE The crop stick tile being collided with.
     */
    public void damageItem(@NotNull final ItemStack stack, @NotNull final EntityLivingBase entity,
        @NotNull final ICropStickTile cropTE) {
        stack.damageItem(this.itemDamage, entity);
    }

    @Override
    public String getDumpText() {
        return DebugHelper.makeCSVLine(
            "Type: item damage",
            String.format("Protection: %1.2f", this.protection),
            String.format("Item damage: %d", this.itemDamage),
            String.format("Item damage chance: %d", this.damageChance));
    }
}
