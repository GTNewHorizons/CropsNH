package com.gtnewhorizon.cropsnh.farming.cropplant;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.v1.ICrop;
import com.gtnewhorizon.cropsnh.api.v1.ICropsNHPlant;

public class CropPlantCropsNHShearable extends CropPlantCropsNH {

    private final Item item;
    private final int meta;

    public CropPlantCropsNHShearable(ICropsNHPlant plant, ItemStack shearingResult) {
        super(plant);
        this.item = shearingResult.getItem();
        this.meta = shearingResult.getItemDamage();
    }

    @Override
    public boolean onHarvest(World world, int x, int y, int z, ICrop crop, EntityPlayer player) {
        if (player == null) {
            return true;
        }
        if (player.getCurrentEquippedItem() == null) {
            return true;
        }
        if (player.getCurrentEquippedItem().getItem() == null) {
            return true;
        }
        if (!(player.getCurrentEquippedItem().getItem() instanceof ItemShears)) {
            return true;
        }

        TileEntity tile = crop.getTileEntity();
        tile.getWorldObj().setBlockMetadataWithNotify(tile.xCoord, tile.yCoord, tile.zCoord, 2, 2);
        int amount = ((int) (Math.ceil((crop.getGain() + 0.00) / 3))) / 2;
        if (amount > 0) {
            ItemStack drop = new ItemStack(item, amount, meta);
            if (world.getGameRules().getGameRuleBooleanValue("doTileDrops") && !world.restoringBlockSnapshots) {
                float f = 0.7F;
                double d0 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                double d1 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                double d2 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                EntityItem entityitem = new EntityItem(world, (double) x + d0, (double) y + d1, (double) z + d2, drop);
                entityitem.delayBeforeCanPickup = 10;
                world.spawnEntityInWorld(entityitem);
            }
        }
        player.getCurrentEquippedItem().damageItem(1, player);
        return false;
    }

}
