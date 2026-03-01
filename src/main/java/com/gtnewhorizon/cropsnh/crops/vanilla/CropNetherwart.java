package com.gtnewhorizon.cropsnh.crops.vanilla;

import java.awt.Color;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.IAdditionalCropData;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.SeedData;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.utility.XSTR;

public class CropNetherwart extends NHCropCard {

    private final ISoilList soil = SoilRegistry.instance.get("soulsand");

    public CropNetherwart() {
        super("netherwart", new Color(0x4d1115), new Color(0xbe3f4a));

        this.addDrop(new ItemStack(Items.nether_wart, 1), 10000);

        this.addAlternateSeed(new ItemStack(Items.nether_wart, 1));

        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.HOT);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public int getGrowthDuration() {
        return 2000;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.spore;
    }

    @Override
    public String getUnlocalizedName() {
        return "item.netherStalkSeeds.name";
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soil;
    }

    @Override
    public float getDropChance() {
        return 2.0f;
    }

    @Override
    public int getMinGrowthStage() {
        return 0;
    }

    @Override
    public int getMaxGrowthStage() {
        return 2;
    }

    @Override
    public String getBaseTexturePath() {
        return "nether_wart_stage_";
    }

    // region gaia wart transformation

    public boolean onRightClick(ICropStickTile te, EntityPlayer player) {
        IAdditionalCropData dataUnparsed = te.getAdditionalCropData();
        AdditionalData data;
        if (!(dataUnparsed instanceof AdditionalData)) {
            data = new AdditionalData();
            te.setAdditionalCropData(data);
        } else {
            data = (AdditionalData) dataUnparsed;
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

    public IAdditionalCropData readAdditionalData(NBTTagCompound nbt) {
        return new AdditionalData(nbt);
    }

    static class AdditionalData implements IAdditionalCropData {

        private final static String CHANCE_NBT_KEY = "chance";

        private final static int DEFAULT_CONVERSION_CHANCE = 100;
        private final static int MAX_CONVERSION_CHANCE = 500;
        private final static int CONVERSION_INCREASE = 10;

        private int conversionChance;

        public AdditionalData(int conversionChance) {
            this.conversionChance = Math.max(0, Math.min(MAX_CONVERSION_CHANCE, conversionChance));
        }

        public AdditionalData() {
            this(DEFAULT_CONVERSION_CHANCE);
        }

        public AdditionalData(NBTTagCompound nbt) {
            this(
                nbt.hasKey(CHANCE_NBT_KEY, Data.NBTType._int) ? nbt.getInteger(CHANCE_NBT_KEY)
                    : DEFAULT_CONVERSION_CHANCE);
        }

        public int getChance() {
            return this.conversionChance;
        }

        public void increaseChance() {
            this.conversionChance = Math.max(MAX_CONVERSION_CHANCE, this.conversionChance + CONVERSION_INCREASE);
        }

        @Override
        public NBTTagCompound writeToNBT() {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger(CHANCE_NBT_KEY, this.conversionChance);
            return nbt;
        }
    }

    // endregion gaia transformation
}
