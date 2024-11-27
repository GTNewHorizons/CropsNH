package com.gtnewhorizon.cropsnh.tileentity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.gtnewhorizon.cropsnh.api.IAdditionalCropData;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.crops.CropWeed;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.FertilizerRegistry;
import com.gtnewhorizon.cropsnh.init.Blocks;
import com.gtnewhorizon.cropsnh.init.Items;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.objects.XSTR;

public class TileEntityCrop extends TileEntityCropsNH implements ICropStickTile {

    public final static int TICK_RATE = 256;
    private int ticker;
    private boolean isDirty = true;

    // seed status
    private ISeedStats stats = null;
    private ICropCard crop = null;
    private boolean isCrossCrop = false;
    private IAdditionalCropData additionalCropData = null;

    // nutrient storage
    private boolean isSick = false;
    private int growthProgress = 0;
    private int waterStorage = 0;
    private int weedexStorage = 0;
    private int fertilizerStorage = 0;
    private int spriteIndex = 0;
    // used to tell waila wtf is wrong with the crop.
    private @Nullable List<IWorldGrowthRequirement> failedChecks = null;

    public TileEntityCrop() {
        this.ticker = 0;//XSTR.XSTR_INSTANCE.nextInt(256);
    }

    @Override
    public boolean isRotatable() {
        return false;
    }

    @Override
    public void updateState() {
        this.isDirty = true;
    }

    // ICropTile status checks
    @Override
    public boolean hasCrop() {
        return this.crop != null && this.stats != null;
    }

    @Override
    public boolean hasWeed() {
        return this.crop instanceof CropWeed;
    }

    @Override
    public boolean canUpgrade() {
        return this.crop == null && !this.isCrossCrop;
    }

    @Override
    public boolean isCrossCrop() {
        return isCrossCrop;
    }

    @Override
    public boolean canPlantSeed() {
        return this.crop == null && !this.isCrossCrop;
    }

    @Override
    public boolean isMature() {
        return this.crop != null && this.crop.getGrowthDuration() <= this.growthProgress;
    }

    // ICropTile quick getters
    @Override
    public ICropCard getCrop() {
        return this.crop;
    }

    @Override
    public @Nullable ISeedStats getStats() {
        return this.crop == null || this.hasWeed() ? null : stats.copy();
    }

    @Override
    public int getFertilizerStorage() {
        return this.fertilizerStorage;
    }

    @Override
    public int getWaterStorage() {
        return this.waterStorage;
    }

    @Override
    public IAdditionalCropData getAdditionalCropData() {
        return this.additionalCropData;
    }

    @Override
    public int getGrowthProgress() {
        return this.crop != null ? this.growthProgress : 0;
    }

    @Override
    public float getGrowthPercent() {
        return this.crop != null
            ? Math.max(0.0f, Math.min(1.0f, (float) this.growthProgress / (float) crop.getGrowthDuration()))
            : 0.0f;
    }

    // ICropTile quick setters
    @Override
    public void setAdditionalCropData(IAdditionalCropData data) {
        this.additionalCropData = data;
    }

    @Override
    public void setCrossCrop(boolean status) {
        if (status != this.isCrossCrop) {
            this.clear();
            this.isCrossCrop = status;
            this.isDirty = true;
            if (!worldObj.isRemote && isCrossCrop) {
                // play a plank noise
                worldObj.playSoundEffect(
                    (double) ((float) xCoord + 0.5F),
                    (double) ((float) yCoord + 0.5F),
                    (double) ((float) zCoord + 0.5F),
                    net.minecraft.init.Blocks.planks.stepSound.func_150496_b(),
                    (net.minecraft.init.Blocks.leaves.stepSound.getVolume() + 1.0F) / 2.0F,
                    net.minecraft.init.Blocks.leaves.stepSound.getPitch() * 0.8F);
            }
        }
    }

    @Override
    public void plantSeed(ICropCard crop, byte gr, byte ga, byte re, boolean isAnalized) {
        plantSeed(crop, new SeedStats(gr, ga, re, isAnalized));
    }

    @Override
    public void plantSeed(ICropCard crop, ISeedStats stats) {
        this.clear();
        this.crop = crop;
        this.stats = stats;
        this.isDirty = true;
    }

    @Override
    public void clear() {
        ICropCard oldCrop = this.crop;
        this.crop = null;
        this.stats = null;
        this.additionalCropData = null;
        this.isCrossCrop = false;
        this.isSick = false;
        this.failedChecks = null;
        this.growthProgress = 0;

        this.markForUpdate();
        if (oldCrop != null) {
            oldCrop.onRemoved(this);
        }
    }

    @Override
    public boolean canGrow() {
        // You can't grow something that doesn't exist.
        if (this.crop == null) {
            this.failedChecks = null;
            return false;
        }
        // Weeds don't care where you are, they will grow
        if (this.crop instanceof CropWeed) {
            this.failedChecks = null;
            return true;
        } ;
        // Check the world growth requirements
        Iterable<IWorldGrowthRequirement> reqs = this.crop.getWorldGrowthRequirements();
        // abort early if no reqs
        if (reqs == null) {
            this.failedChecks = null;
            return true;
        } ;

        LinkedList<IWorldGrowthRequirement> failedReqs = null;
        for (IWorldGrowthRequirement req : reqs) {
            if (!req.canGrow(this.worldObj, this, this.xCoord, this.yCoord, this.zCoord)) {
                if (failedReqs == null) failedReqs = new LinkedList<>();
                failedReqs.addLast(req);
            }
        }
        // update the list of failed checks if necessary.
        if (failedReqs != null) this.failedChecks = failedReqs;
        return this.failedChecks != null;
    }

    @Override
    public @Nullable ItemStack getSeedStack() {
        // validate if seed is valid
        if (this.crop == null || this.stats == null) return null;

        // save crop info
        NBTTagCompound tag = new NBTTagCompound();
        this.writeSeedNBT(tag);
        // create seed with tags
        ItemStack seed = new ItemStack(Items.genericSeed, 1);
        seed.setTagCompound(tag);
        return seed;
    }

    @Override
    public void spawnWeed() {
        // if we have weed-ex remaining, stop the weeding.
        if (this.weedexStorage >= 0) {
            this.weedexStorage--;
            return;
        }
        // else weed this thing
        this.clear();
        this.plantSeed(
            CropRegistry.instance.get(Reference.MOD_ID + ":weeds"),
            new SeedStats((byte) 31, (byte) 1, (byte) 1, true));
    }

    @Override
    public boolean addFertilizer(int amount, int threshold, int maxStorage) {
        if (this.fertilizerStorage >= threshold) return false;
        this.fertilizerStorage = Math.min(maxStorage, this.fertilizerStorage + amount);
        return true;
    }

    @Override
    public boolean addWater(int amount, int threshold, int maxStorage) {
        if (this.waterStorage >= threshold) return false;
        this.waterStorage = Math.min(maxStorage, this.waterStorage + amount);
        return true;
    }

    @Override
    public @Nullable ItemStack[] harvest() {
        // must be fully grown to harvest
        if (this.crop == null || this.stats == null || !this.isMature()) return null;
        double chance = this.crop.getDropChance();
        int dropCount = (int) Math.max(
            0L,
            Math.round(
                XSTR.XSTR_INSTANCE.nextGaussian() * (chance *= Math.pow(1.03, this.stats.getGain())) * 0.6827
                    + chance));
        // TODO: IMPLEMENT PROPPER DROP TABLES
        // ItemStack[] ret = new ItemStack[dropCount];
        for (int i = 0; i < dropCount; ++i) {
            // ret[i] = this.crop.getGain(this);
            // if (ret[i] == null || IC2.random.nextInt(100) > this.statGain) continue;
            // ++ret[i].stackSize;
        }
        crop.onHarvest(this);
        this.growthProgress = 0;
        this.isDirty = true;
        return null;
    }

    public boolean doPlayerHarvest() {
        // check if we can harvest this crop
        if (this.crop == null || this.stats == null || !this.isMature()) return false;
        ItemStack[] drops = harvest();
        if (drops == null) return false;
        for (ItemStack drop : drops) {
            if (drop == null || drop.getItem() == null) {
                continue;
            }
            dropItem(this.worldObj, this.xCoord, this.yCoord, this.zCoord, drop);
        }
        return true;
    }

    private static void dropItem(World world, int x, int y, int z, ItemStack drop) {
        double f = 0.7;
        double dx = (double) world.rand.nextFloat() * f + (1.0 - f) * 0.5;
        double dy = (double) world.rand.nextFloat() * f + (1.0 - f) * 0.5;
        double dz = (double) world.rand.nextFloat() * f + (1.0 - f) * 0.5;
        EntityItem entityItem = new EntityItem(world, x + dx, y + dy, z + dz, drop);
        entityItem.delayBeforeCanPickup = 10;
        world.spawnEntityInWorld(entityItem);
    }

    // this saves the data on the tile entity
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        if (this.crop != null) {
            // save crop specific state
            writeSeedNBT(tag);
            if (this.crop != null && this.isSick) tag.setBoolean("sick", true);
            tag.setInteger("progress", this.growthProgress);
        } else if (this.isCrossCrop) {
            // ignore crop state if it's a cross
            tag.setBoolean(Names.NBT.crossCrop, true);
        }
        // save crop state
        tag.setInteger("water", this.waterStorage);
        tag.setInteger("fert", this.fertilizerStorage);
        tag.setInteger("weedex", this.weedexStorage);
    }

    public void writeSeedNBT(NBTTagCompound tag) {
        // save crop information if a crop is planted exists
        if (crop != null) {
            tag.setString(Names.NBT.crop, this.crop.getId());
            if (this.stats != null) {
                stats.writeToNBT(tag);
            }
            if (this.additionalCropData != null) {
                tag.setTag("extra", additionalCropData.writeToNBT());
            }
        }
    }

    // this loads the saved data for the tile entity
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        if (tag.hasKey(Names.NBT.crossCrop, Data.NBTType._boolean) && tag.getBoolean(Names.NBT.crossCrop)) {
            this.setCrossCrop(true);
        } else {
            isCrossCrop = false;
            // load crop data if it's not a cross crop
            loadCropNBT(tag);
            // only update the growth progress
            if (this.crop != null) {
                if (tag.hasKey("progress", Data.NBTType._int)) {
                    this.growthProgress = tag.getInteger("progress");
                }
                if (tag.hasKey("sick", Data.NBTType._boolean)) {
                    this.isSick = tag.getBoolean("sick");
                }
            }
        }

        // get crop status stuff
        this.waterStorage = tag.hasKey("water", Data.NBTType._int) ? tag.getInteger("water") : this.waterStorage;
        this.fertilizerStorage = tag.hasKey("fert", Data.NBTType._int) ? tag.getInteger("fert")
            : this.fertilizerStorage;
        this.weedexStorage = tag.hasKey("weedex", Data.NBTType._int) ? tag.getInteger("weedex") : this.weedexStorage;
        this.failedChecks = null;
        this.isDirty = true;
    }

    public void loadCropNBT(NBTTagCompound tag) {
        if (tag == null || !tag.hasKey(Names.NBT.crop, Data.NBTType._string)) {
            this.clear();
            return;
        }
        String name = tag.getString(Names.NBT.crop);
        this.crop = CropRegistry.instance.get(name);
        if (this.crop == null) {
            clear();
        } else {
            this.stats = SeedStats.readFromNBT(tag);
            if (tag.hasKey("extra", Data.NBTType._object)) {
                this.additionalCropData = crop.LoadAdditionalData(tag.getCompoundTag("extra"));
            }
        }

        this.failedChecks = null;
        this.isSick = false;
        this.growthProgress = 0;
        this.isDirty = true;
    }

    /**
     * @return a list with all neighbours of type <code>TileEntityCrop</code> in the
     *         NORTH, SOUTH, EAST and WEST direction
     */
    @Override
    public List<ICropStickTile> getNeighbours() {
        List<ICropStickTile> neighbours = new ArrayList<>();
        addNeighbour(neighbours, ForgeDirection.NORTH);
        addNeighbour(neighbours, ForgeDirection.SOUTH);
        addNeighbour(neighbours, ForgeDirection.EAST);
        addNeighbour(neighbours, ForgeDirection.WEST);
        return neighbours;
    }

    private void addNeighbour(List<ICropStickTile> neighbours, ForgeDirection direction) {
        TileEntity te = worldObj
            .getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
        if (!(te instanceof ICropStickTile)) {
            return;
        }
        neighbours.add((ICropStickTile) te);
    }

    /** @return a list with only mature neighbours of type <code>TileEntityCrop</code> */
    @Override
    public List<ICropStickTile> getMatureNeighbours() {
        List<ICropStickTile> neighbours = getNeighbours();
        neighbours.removeIf(crop -> !crop.hasCrop() || !crop.isMature());
        return neighbours;
    }

    // get the plant icon
    @SideOnly(Side.CLIENT)
    public IIcon getPlantIcon() {
        IIcon icon = null;
        if (this.hasCrop()) {
            icon = this.crop.getSprite(this);
        }
        return icon;
    }

    // event handlers

    @Override
    public void updateEntity() {
        super.updateEntity();
        this.ticker = ++this.ticker % TICK_RATE;
        if (this.ticker == 0) {
            this.onGrowthTick();
        }
        if (this.isDirty) {
            this.isDirty = false;
            this.markForUpdate();
            this.worldObj.updateLightByType(EnumSkyBlock.Block, this.xCoord, this.yCoord, this.zCoord);
        }
    }

    @Override
    public void onGrowthTick() {
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) return;
        if (this.hasCrop() && this.canGrow()) {
            crop.onGrowthTick(this);
            this.growthProgress += 125000;
            if (this.growthProgress > this.crop.getGrowthDuration()) {
                this.growthProgress = this.crop.getGrowthDuration();
            }

            // only request re-render when the crop is changing state to be rendered.
            int spriteIndex = this.crop.getSpriteIndex(this);
            if (spriteIndex != this.spriteIndex) {
                this.spriteIndex = spriteIndex;
                this.isDirty = true;
            }
        }
    }

    @Override
    public boolean onRightClick(EntityPlayer player, @Nullable ItemStack heldItem) {
        // items that implement ICropRightClickHandler will be able to
        if (heldItem != null && heldItem.stackSize > 0) {
            // check if it's a fertilizer
            int fertilizerPotency = FertilizerRegistry.instance.getPotnecy(heldItem);
            if (fertilizerPotency > 0) {
                this.addFertilizer(fertilizerPotency, 90, 100);
                if (!player.capabilities.isCreativeMode) {
                    heldItem.stackSize--;
                }
                return true;
            }
            // check if it's a known seed alternate
            ICropCard crop = CropRegistry.instance.fromAlternateSeed(heldItem);
            if (this.canPlantSeed()) {
                stats = new SeedStats((byte) 1, (byte) 1, (byte) 1, true);
                this.plantSeed(crop, stats);
                if (!player.capabilities.isCreativeMode) {
                    heldItem.stackSize--;
                }
                return true;
            }
        }
        if (this.crop != null) {
            if (this.crop.onRightClick(this, player)) {
                return true;
            }
        } else if (this.isCrossCrop) {
            this.setCrossCrop(false);
            if (!player.capabilities.isCreativeMode) {
                dropItem(this.worldObj, this.xCoord, this.yCoord, this.zCoord, new ItemStack(Blocks.blockCrop, 1));
            }
            return true;
        }
        // attempt to harvest
        if (this.isMature()) {
            this.doPlayerHarvest();
        }
        return true;
    }

    @Override
    public boolean onLeftClick(EntityPlayer player, @Nullable ItemStack heldItem) {
        if (this.hasCrop() && !this.hasWeed()) {
            this.clear();
            // TODO: IMPLEMENT SEED DROP LOGIC
        }
        return true;
    }

    @Override
    public void onDestroyed() {

    }

    @Override
    public void onEntityCollision(Entity entity) {

    }

    @Override
    public void onNeighbourChange() {

    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addWailaInformation(List information) {
        if (this.crop != null) {
            if (this.hasWeed()) {
                information.add(StatCollector.translateToLocal("cropsnh_tooltip.weeds"));
            } else {
                String header, value;

                // Add the seed name
                header = StatCollector.translateToLocal("cropsnh_tooltip.seed");
                value = StatCollector.translateToLocal(this.crop.getUnlocalizedName());
                information.add(header + ": " + value);

                // add growth progress
                header = StatCollector.translateToLocal("cropsnh_tooltip.progress");
                value = String.format("%3.2f", this.getGrowthPercent() * 100.0f);
                information.add(header + ": " + value + "%");

                // do not display crop stats for weeds
                if (!(this.crop instanceof CropWeed)) {
                    if (this.isSick) {
                        information.add(StatCollector.translateToLocal("cropsnh_tooltip.isSick"));
                    }

                    List<IWorldGrowthRequirement> failedReqs = this.failedChecks;
                    if (failedReqs != null) {
                        for (IWorldGrowthRequirement req : failedReqs) {
                            information.add(req.getDescription());
                        }
                    }

                    // write out the stats of the crop if the stats exist
                    if (this.stats != null && this.stats.isAnalyzed()) {
                        information.add(
                            String.format(
                                "%s -- %s: %d  %s: %d  %s: %d",
                                StatCollector.translateToLocal("cropsnh_tooltip.stats"),
                                StatCollector.translateToLocal("cropsnh_tooltip.growth"),
                                this.stats.getGrowth(),
                                StatCollector.translateToLocal("cropsnh_tooltip.gain"),
                                this.stats.getGain(),
                                StatCollector.translateToLocal("cropsnh_tooltip.resistance"),
                                this.stats.getResistance()));
                    }
                }
            }
        } else {
            information.add(StatCollector.translateToLocal("cropsnh_tooltip.empty"));
        }

        information.add(
            String.format(
                "%s -- %s: %d  %s: %d  %s: %d",
                StatCollector.translateToLocal("cropsnh_tooltip.soil"),
                StatCollector.translateToLocal("cropsnh_tooltip.fertilizer"),
                this.fertilizerStorage,
                StatCollector.translateToLocal("cropsnh_tooltip.water"),
                this.waterStorage,
                StatCollector.translateToLocal("cropsnh_tooltip.weedEx"),
                this.weedexStorage));
    }
}
