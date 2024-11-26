package com.gtnewhorizon.cropsnh.crops.vanilla;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.IAdditionalCropData;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropCard;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;

import gregtech.api.objects.XSTR;

public class CropNetherwart extends CropCard {

    private final ItemStack[] alternateSeeds;
    private final ISoilList soil = SoilRegistry.instance.get("soulsand");
    private final HashSet<BiomeDictionary.Type> likedBiomes = new HashSet<BiomeDictionary.Type>() {

        {
            add(BiomeDictionary.Type.NETHER);
            add(BiomeDictionary.Type.HOT);
        }
    };

    public CropNetherwart() {
        super("netherwart");
        this.dropTable.put(new ItemStack(Items.nether_wart, 1), 1.0f);
        this.alternateSeeds = new ItemStack[] { new ItemStack(Items.nether_wart, 1) };
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
    public Set<BiomeDictionary.Type> getLikedBiomeTags() {
        return this.likedBiomes;
    }

    @Override
    public IIcon[] getTextures(IIconRegister register) {
        return getTextures(register, "nether_wart_stage_", 0, 2);
    }

    @Override
    public ItemStack[] getAlternateSeeds() {
        return this.alternateSeeds;
    }

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
        // if right click with snow blocks netherwart may turn into terrawart.
        if (heldItem.getItem() == Item.getItemFromBlock(Blocks.snow)) {
            if (data.getChance() > XSTR.XSTR_INSTANCE.nextInt(10000)) {
                ISeedStats stats = te.getStats();
                te.clear();
                te.plantSeed(CropRegistry.instance.get(Reference.MOD_ID + ":" + Names.Objects.terraWart), stats);
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

    @Override
    public IAdditionalCropData LoadAdditionalData(NBTTagCompound nbt) {
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

}
