package com.gtnewhorizon.cropsnh.tileentity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.util.ForgeDirection;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.IAdditionalCropData;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropMutation;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.api.IMutationPool;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.crops.CropMigrator;
import com.gtnewhorizon.cropsnh.crops.CropWeed;
import com.gtnewhorizon.cropsnh.farming.SeedData;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.FertilizerRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.MutationRegistry;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;
import com.gtnewhorizon.cropsnh.items.ItemGenericSeed;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizon.cropsnh.utility.WorldUtils;
import com.gtnewhorizon.cropsnh.utility.XSTR;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityCrop extends TileEntityCropsNH implements ICropStickTile {

    public final static int TICK_RATE = 256;

    // growth formula balancing
    // current settings quirks:
    // tier 5+ need something to give them extra nutrient points they will get sick.
    /** The minimum scalar value for the growth speed. */
    public final static int BASE_GROWTH_SPEED = 6;
    /** The minimum number of nutrient points a crop may have. */
    public final static int BASE_NUTRIENT_VALUE = 5;
    /** The number after which water storage stops awarding nutrient points. */
    public final static int MAX_WATER_BONUS_AT = 100;
    /** The number after which fertilizer storage stops awarding nutrient points. */
    public final static int MAX_FERTILIZER_BONUS_AT = 100;
    /** The number of nutrient points awarded if a crop can see the sky. */
    public final static int SKY_ACCESS_BONUS = 2;
    /** The maximum amount of times the liked biome bonus can be awarded. */
    public final static int MAX_LIKED_BIOME_TAG_COUNT = 2;
    /**
     * The number of nutrient points awarded per linked biome tags in the current crop's biome (awarded a max of 2
     * times).
     */
    public final static int LIKED_BIOME_BONUS = 14;
    /** The minimum humidity threshold in order to gain liked biome bonuses. */
    public final static float LOW_HUMIDITY_THRESHOLD = 0.5f;
    /** The maximum humidity threshold in order to gain liked biome bonuses. */
    public final static float HIGH_HUMIDITY_THRESHOLD = 0.8f;
    /**
     * The number of liked biome tags that high humidity can "simulate".
     * If the number of liked biomes tags is below this value and the biome has a high humidity
     * this value will replace the liked biome count.
     */
    public final static int HIGH_HUMIDITY_BONUS = LIKED_BIOME_BONUS;
    /** A value by which nutrient points are multiplied. */
    public final static int NUTRIENT_POINT_SCALE = 5;
    /** The minimum number of nutrient points that are needed per tier (applies to the scaled nutrient value). */
    public final static int NUTRIENTS_NEEDED_PER_TIER = 10;
    /** The maximum nutrient score */
    public final static int MAX_NUTRIENT_SCORE = getNutrientsPerCycle(
        MAX_LIKED_BIOME_TAG_COUNT,
        0.5f,
        true,
        MAX_WATER_BONUS_AT,
        MAX_FERTILIZER_BONUS_AT);

    private int ticker;
    private boolean isDirty = true;
    private int spriteIndex = 0;

    // seed status
    private ISeedData seed = null;
    private boolean isCrossCrop = false;
    private IAdditionalCropData additionalCropData = null;

    // crop status
    private boolean isSick = false;
    private int growthProgress = 0;
    private int waterStorage = 0;
    private int weedEXStorage = 0;
    private int fertilizerStorage = 0;
    // used to tell waila why the crop ain't growing.
    private List<IGrowthRequirement> failedChecks = null;

    public TileEntityCrop() {
        this.ticker = XSTR.XSTR_INSTANCE.nextInt(256);
    }

    // region status checks

    @Override
    public void updateState() {
        this.isDirty = true;
    }

    @Override
    public boolean hasCrop() {
        return this.seed != null;
    }

    @Override
    public boolean hasWeed() {
        return this.hasCrop() && this.seed.getCrop() instanceof CropWeed;
    }

    @Override
    public boolean isSick() {
        return this.hasCrop() && !this.hasWeed() && this.isSick;
    }

    @Override
    public boolean canUpgrade() {
        return !this.hasCrop() && !this.isCrossCrop;
    }

    @Override
    public boolean isCrossCrop() {
        return this.isCrossCrop;
    }

    @Override
    public boolean canPlantSeed() {
        return this.seed == null && !this.isCrossCrop;
    }

    @Override
    public boolean isMature() {
        return this.hasCrop() && this.seed.getCrop()
            .getGrowthDuration() <= this.growthProgress;
    }

    @Override
    public ISeedData getSeed() {
        return this.seed;
    }

    @Override
    public IAdditionalCropData getAdditionalCropData() {
        return this.additionalCropData;
    }

    @Override
    public int getGrowthProgress() {
        return this.seed != null ? this.growthProgress : 0;
    }

    @Override
    public void setGrowthProgress(int prog) {
        if (!this.hasCrop()) return;
        this.growthProgress = Math.max(
            0,
            Math.min(
                this.seed.getCrop()
                    .getGrowthDuration(),
                prog));
        this.isDirty = true;
    }

    @Override
    public float getGrowthPercent() {
        return this.hasCrop() ? Math.max(
            0.0f,
            Math.min(
                1.0f,
                (float) this.growthProgress / (float) this.seed.getCrop()
                    .getGrowthDuration()))
            : 0.0f;
    }

    public List<IGrowthRequirement> getFailedChecks() {
        return this.failedChecks;
    }

    @Override
    public void setAdditionalCropData(IAdditionalCropData data) {
        this.additionalCropData = data;
    }

    @Override
    public boolean canGrow() {
        // You can't grow something that doesn't exist.
        if (!this.hasCrop() || this.isSick) {
            this.failedChecks = null;
            return false;
        }
        // Weeds don't care where you are, they will grow
        if (this.getSeed()
            .getCrop() instanceof CropWeed) {
            this.failedChecks = null;
            return true;
        }
        // Check the world growth requirements
        Iterable<IGrowthRequirement> reqs = this.seed.getCrop()
            .getGrowthRequirements();
        // abort early if no reqs
        if (reqs == null) {
            this.failedChecks = null;
            return true;
        }

        boolean success = !this.isMature();
        LinkedList<IGrowthRequirement> failedReqs = null;
        for (IGrowthRequirement req : reqs) {
            // only check world growth requirements.
            if (!(req instanceof IWorldGrowthRequirement worldReq)) continue;
            // check if the crop can grow
            if (worldReq.canGrow(this.worldObj, this, this.xCoord, this.yCoord, this.zCoord)) continue;
            success &= worldReq.onlyPreventsHarvest();
            if (failedReqs == null) failedReqs = new LinkedList<>();
            failedReqs.add(req);
        }
        this.failedChecks = failedReqs;
        return success;
    }

    @SideOnly(Side.CLIENT)
    public void getPlantLensStatus(List<String> information) {
        if (this.hasCrop()) {
            if (this.hasWeed()) {
                information.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.weeds"));
            } else {
                String header, value;

                // Add the seed name
                header = StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.seed");
                value = StatCollector.translateToLocal(
                    this.seed.getCrop()
                        .getUnlocalizedName());
                information.add(header + ": " + value);

                // do not display crop stats for weeds
                if (!(this.seed.getCrop() instanceof CropWeed)) {
                    if (this.isSick) {
                        information.add(
                            EnumChatFormatting.RED
                                + StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.isSick")
                                + EnumChatFormatting.RESET);
                    }

                    List<IGrowthRequirement> failedReqs = this.failedChecks;
                    if (failedReqs != null) {
                        for (IGrowthRequirement req : failedReqs) {
                            information.add(EnumChatFormatting.RED + req.getDescription() + EnumChatFormatting.RESET);
                        }
                    }

                    // write out the stats of the crop if the stats exist
                    if (this.seed.getStats()
                        .isAnalyzed()) {
                        information.add(
                            String.format(
                                "%s -- %s: %d  %s: %d  %s: %d",
                                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.stats"),
                                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.growth"),
                                this.seed.getStats()
                                    .getGrowth(),
                                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.gain"),
                                this.seed.getStats()
                                    .getGain(),
                                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.resistance"),
                                this.seed.getStats()
                                    .getResistance()));
                    }
                }
            }
        } else {
            information.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.empty"));
        }

        information.add(
            String.format(
                "%s -- %s: %d  %s: %d  %s: %d",
                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.soil"),
                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.fertilizer"),
                this.fertilizerStorage,
                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.water"),
                this.waterStorage,
                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.weedEx"),
                this.weedEXStorage));
    }

    // endregion status checks

    // region resource storage

    @Override
    public int getFertilizerStorage() {
        return this.fertilizerStorage;
    }

    @Override
    public boolean addFertilizer(int amount, int threshold, int maxStorage, boolean simulate) {
        if (this.fertilizerStorage > threshold) return false;
        if (!simulate) {
            this.fertilizerStorage = Math.min(maxStorage, this.fertilizerStorage + amount);
            this.isDirty = true;
        }
        return true;
    }

    @Override
    public int getWaterStorage() {
        return this.waterStorage;
    }

    @Override
    public boolean addWater(int amount, int threshold, int maxStorage, boolean simulate) {
        if (this.waterStorage > threshold) return false;
        if (!simulate) {
            this.waterStorage = Math.min(maxStorage, this.waterStorage + amount);
            this.isDirty = true;
        }
        return true;
    }

    @Override
    public int getWeedExStorage() {
        return this.weedEXStorage;
    }

    @Override
    public boolean addWeedEx(int amount, int threshold, int maxStorage, boolean simulate) {
        if (this.weedEXStorage > threshold) return false;
        if (!simulate) {
            this.weedEXStorage = Math.min(maxStorage, this.weedEXStorage + amount);
            this.isDirty = true;
        }
        return true;
    }

    // endregion resource storage

    // region seed planting

    @Override
    public boolean tryPlantSeed(ItemStack seedStack) {
        // can't plant nothing
        if (!this.canPlantSeed() || seedStack == null || seedStack.getItem() == null || seedStack.stackSize <= 0)
            return false;

        // check if it's a valid seed
        ICropCard cc = CropRegistry.instance.get(seedStack);
        if (cc == null) {
            return false;
        }

        // alternate seeds get 1/1/1 analyzed seeds
        ISeedStats stats = seedStack.getItem() instanceof ItemGenericSeed ? SeedStats.getStatsFromStack(seedStack)
            : new SeedStats((byte) 1, (byte) 1, (byte) 1, true);

        // run the crop specific checks next
        return this.tryPlantSeed(new SeedData(cc, stats));
    }

    @Override
    public boolean tryPlantSeed(ISeedData seedData) {
        // check if it can be planted on this soil.
        if (seedData == null || seedData.getCrop() == null || !isValidSoilForCrop(seedData.getCrop())) return false;
        // all good we can plant the seed
        this.plantSeed(seedData);
        return true;
    }

    public boolean isValidSoilForCrop(ICropCard cc) {
        Block block = this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord);
        int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord - 1, this.zCoord);
        return cc.getSoilTypes()
            .isRegistered(block, meta);
    }

    public boolean wouldCropBeAbleToGrow(ICropCard cc) {
        // Check the world growth requirements
        Iterable<IGrowthRequirement> reqs = cc.getGrowthRequirements();
        // abort early if no reqs
        if (reqs == null) return true;

        for (IGrowthRequirement req : reqs) {
            // only check world growth requirements.
            if (!(req instanceof IWorldGrowthRequirement worldReq)) continue;
            // check if the crop can grow
            if (worldReq.canGrow(this.worldObj, this, this.xCoord, this.yCoord, this.zCoord)) continue;
            if (!worldReq.onlyPreventsHarvest()) return false;
        }
        return true;
    }

    @Override
    public void plantSeed(ISeedData seedData) {
        this.clear();
        // this should be the only place stats and crop are ever set to a non-null value.
        this.seed = seedData;
        this.isDirty = true;
    }

    @Override
    public void clear() {
        ISeedData oldCrop = this.seed;
        if (oldCrop != null) {
            oldCrop.getCrop()
                .onRemoved(this);
        }
        this.seed = null;
        this.additionalCropData = null;
        this.isCrossCrop = false;
        this.isSick = false;
        this.failedChecks = null;
        this.growthProgress = 0;

        this.isDirty = true;
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
                    ((double) xCoord + 0.5d),
                    ((double) yCoord + 0.5d),
                    ((double) zCoord + 0.5d),
                    net.minecraft.init.Blocks.planks.stepSound.func_150496_b(),
                    (net.minecraft.init.Blocks.leaves.stepSound.getVolume() + 1.0f) / 2.0f,
                    net.minecraft.init.Blocks.leaves.stepSound.getPitch() * 0.8f);
            }
        }
    }

    // endregion seed planting

    // region harvesting

    public static double getAvgDropRounds(ICropCard cc, int gain) {
        // should be a fairly accurate representation of the old curve
        return cc.getDropChance() * Math.pow(1.03D, gain);
    }

    public static double getAvgDropCountIncrease(int gain) {
        return (gain + 1) / 100.0d;
    }

    @Override
    public boolean canHarvest() {
        return !this.worldObj.isRemote && this.hasCrop()
            && !this.hasWeed()
            && this.isMature()
            && this.failedChecks == null;
    }

    @Override
    public ArrayList<ItemStack> harvest() {
        // must be fully grown to harvest
        if (!canHarvest()) return null;

        this.seed.getCrop()
            .onHarvest(this);
        this.growthProgress = 0;
        this.spriteIndex = 0;
        this.isDirty = true;

        // avg number of drop rounds
        double avgDropRounds = getAvgDropRounds(
            seed.getCrop(),
            this.seed.getStats()
                .getGain());
        int dropCount = (int) avgDropRounds;
        if (XSTR.XSTR_INSTANCE.nextDouble() <= (avgDropRounds % 1.0d)) {
            dropCount++;
        }

        if (dropCount <= 0) return null;
        // check if we got a drop
        Map<ItemStack, Integer> dropTable = this.seed.getCrop()
            .getDropTable();
        if (dropTable == null) return null;
        // roll drop table
        ArrayList<ItemStack> ret = new ArrayList<>();
        for (Map.Entry<ItemStack, Integer> drop : dropTable.entrySet()) {
            int count = 0;
            int gainBonus = 0;
            // merge re-rolls into the same stack.
            for (int i = 0; i < dropCount; i++) {
                if (XSTR.XSTR_INSTANCE.nextInt(10000) < drop.getValue()) {
                    if (XSTR.XSTR_INSTANCE.nextInt(100) <= this.seed.getStats()
                        .getGain()) {
                        gainBonus += 1;
                    }
                    count++;
                }
            }
            if (count > 0) {
                ItemStack stack = drop.getKey()
                    .copy();
                stack.stackSize *= count;
                stack.stackSize += gainBonus;
                ret.add(stack);
            }
        }
        return ret;
    }

    @Override
    public boolean doPlayerHarvest() {
        // check if we can harvest this crop
        if (!canHarvest()) return false;
        ArrayList<ItemStack> drops = harvest();
        if (drops != null) {
            for (ItemStack drop : drops) {
                if (CropsNHUtils.isStackInvalid(drop)) continue;
                this.dropItem(drop);
            }
        }
        return true;
    }

    @Override
    public ItemStack getSeedStack() {
        // validate if seed is valid
        if (!this.hasCrop() && this.seed.getCrop() instanceof CropMigrator) return null;

        // save crop info
        return this.seed.getStack()
            .copy();
    }

    // endregion harvesting

    // region nbt handling

    // this saves the data on the tile entity
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        // save crop state
        writeSeedNBT(tag, this.seed, this.additionalCropData, this.growthProgress);
        writeStatusNBT(
            tag,
            this.waterStorage,
            this.fertilizerStorage,
            this.weedEXStorage,
            this.isSick,
            this.isCrossCrop);
    }

    public static void writeStatusNBT(NBTTagCompound tag, int waterStorage, int fertilizerStorage, int weedEXStorage,
        boolean isSick, boolean isCrossCrop) {
        if (isCrossCrop) {
            tag.setBoolean(Names.NBT.crossCrop, true);
        } else if (isSick) {
            tag.setBoolean(Names.NBT.sick, true);
        }
        tag.setInteger(Names.NBT.water, waterStorage);
        tag.setInteger(Names.NBT.fertilizer, fertilizerStorage);
        tag.setInteger(Names.NBT.weedEX, weedEXStorage);
    }

    public static void writeSeedNBT(NBTTagCompound tag, ISeedData seed, IAdditionalCropData additionalCropData,
        int growthProgress) {
        // save crop information if a crop is planted exists
        if (seed != null) {
            tag.setTag(Names.NBT.crop, seed.writeToNBT());
            if (additionalCropData != null) {
                tag.setTag(Names.NBT.extra, additionalCropData.writeToNBT());
            }
            tag.setInteger(Names.NBT.progress, growthProgress);
        }
    }

    // this loads the saved data for the tile entity
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        if (tag.hasKey(Names.NBT.crossCrop, Data.NBTType._boolean) && tag.getBoolean(Names.NBT.crossCrop)) {
            this.isCrossCrop = true;
        } else {
            this.isCrossCrop = false;
            // load crop data if it's not a cross crop
            this.loadCropNBT(tag);
            // only update the growth progress
            if (this.hasCrop()) {
                if (tag.hasKey(Names.NBT.progress, Data.NBTType._int)) {
                    this.growthProgress = tag.getInteger(Names.NBT.progress);
                }
                if (tag.hasKey(Names.NBT.sick, Data.NBTType._boolean)) {
                    this.isSick = tag.getBoolean(Names.NBT.sick);
                }
            }
        }

        // get crop status stuff
        this.waterStorage = tag.hasKey(Names.NBT.water, Data.NBTType._int) ? tag.getInteger(Names.NBT.water) : 0;
        this.fertilizerStorage = tag.hasKey(Names.NBT.fertilizer, Data.NBTType._int)
            ? tag.getInteger(Names.NBT.fertilizer)
            : 0;
        this.weedEXStorage = tag.hasKey(Names.NBT.weedEX, Data.NBTType._int) ? tag.getInteger(Names.NBT.weedEX) : 0;
        this.isDirty = true;
    }

    public void loadCropNBT(NBTTagCompound tag) {
        if (tag == null || !tag.hasKey(Names.NBT.crop, Data.NBTType._object)) {
            this.clear();
            return;
        }
        ISeedData seedData = new SeedData(tag.getCompoundTag(Names.NBT.crop));
        this.plantSeed(seedData);
        if (tag.hasKey(Names.NBT.extra, Data.NBTType._object)) {
            this.additionalCropData = this.seed.getCrop()
                .readAdditionalData(tag.getCompoundTag(Names.NBT.extra));
        }
    }

    // endregion nbt handling

    // region neighbour checking

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

    // endregion neighbour checking

    // region rendering stuff

    // get the plant icon
    @SideOnly(Side.CLIENT)
    public IIcon getPlantIcon() {
        IIcon icon = null;
        if (this.hasCrop()) {
            icon = this.seed.getCrop()
                .getSprite(this);
        }
        return icon;
    }

    // endregion rendering stuff

    // region growth rate calc

    @Override
    public int calcGrowthRate() {

        // compute growth rate
        return getGrowthRate(
            getNutrientScore(),
            this.seed.getCrop()
                .getTier(),
            this.seed.getStats()
                .getGrowth());
    }

    public int getNutrientScore() {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoordsBody(this.xCoord, this.zCoord);
        BiomeDictionary.Type[] biomeTags = BiomeDictionary.getTypesForBiome(biome);
        // check number of liked biomes.
        int likedBiomes = this.hasCrop() ? (int) this.seed.getCrop()
            .getLikedBiomeTags()
            .stream()
            .filter(
                liked -> Arrays.stream(biomeTags)
                    .anyMatch(tag -> liked == tag))
            .count() : 0;
        // check if block can see the sky.
        boolean canSeeSky = this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord);
        // calc available nutrients
        return getNutrientsPerCycle(likedBiomes, biome.rainfall, canSeeSky, this.waterStorage, this.fertilizerStorage);
    }

    /**
     * Calculates the nutrient points for a crop stick given a set of environmental factors.
     *
     * @param likedBiomeTagsCount The number of liked biome tags in the current biome.
     * @param biomeHumidity       The humidity of the current biome.
     * @param canSeeSky           True if the crop can see the sky.
     * @param waterStorage        How much water is stored in the crop stick.
     * @param fertilizerStorage   How much water is stored in the crop stick.
     * @return The number of nutrients available to the crop in the crop stick.
     */
    public static int getNutrientsPerCycle(int likedBiomeTagsCount, float biomeHumidity, boolean canSeeSky,
        int waterStorage, int fertilizerStorage) {
        // spotless:off
        // LaTeX for the current growth formula.
        // n_{utrients}=n_{utrientPointScale}\cdot\left(b_{aseNutrientValue}+\operatorname{floor}\left(\frac{\left(\min\left(m_{axWaterBonusAt},w_{ater}\right)+9\right)}{10}\right)+\operatorname{floor}\left(\frac{\left(\min\left(m_{axFertilizerBonusAt},f_{ertilizer}\right)+9\right)}{10}\right)+\left\{s_{kyAccess}>0:s_{kyAccessBonus},0\right\}+\max\left(\operatorname{floor}\left(\min\left(1,\frac{B_{iomeHumidity}-l_{owHumidityThreshold}}{h_{ighHumidityThreshold}-l_{owHumidityThreshold}}\right)\cdot l_{ikedBiomeBonus}\right),l_{ikedBiomeTags}\cdot l_{ikedBiomeBonus}\right)\right)
        // spotless:on
        likedBiomeTagsCount = Math.min(MAX_LIKED_BIOME_TAG_COUNT, likedBiomeTagsCount);
        waterStorage = Math.min(MAX_WATER_BONUS_AT, waterStorage);
        fertilizerStorage = Math.min(MAX_FERTILIZER_BONUS_AT, fertilizerStorage);

        int nutrients = BASE_NUTRIENT_VALUE;
        nutrients += ((waterStorage + 9) / 10);
        nutrients += ((fertilizerStorage + 9) / 10);
        nutrients += (canSeeSky ? SKY_ACCESS_BONUS : 0);
        float humidityBonus = (biomeHumidity - LOW_HUMIDITY_THRESHOLD)
            / (HIGH_HUMIDITY_THRESHOLD - LOW_HUMIDITY_THRESHOLD);
        humidityBonus = Math.max(0.0f, Math.min(1.0f, humidityBonus)) * HIGH_HUMIDITY_BONUS;
        nutrients += Math.max((int) humidityBonus, (likedBiomeTagsCount * LIKED_BIOME_BONUS));
        return nutrients;
    }

    /**
     * Calculates the growth speed of a crop in a crop stick.
     *
     * @param nutrientPoints The number of nutrient points available to the crop.
     * @param tier           The tier of the crop.
     * @param growth         The growth stat of the crop.
     * @return The speed at which the crop should grow, if the value is <= 0 the crop should get sick.
     */
    public static int getGrowthRate(int nutrientPoints, int tier, int growth) {
        nutrientPoints *= NUTRIENT_POINT_SCALE;
        // this should mean that crops of tier 5 and up will require some sort of bonus in order to grow
        int need = tier * NUTRIENTS_NEEDED_PER_TIER;
        // failsafe
        if (need < 0) return 0;

        int baseSpeed = BASE_GROWTH_SPEED + growth;
        if (nutrientPoints >= need) {
            return baseSpeed * (100 + (nutrientPoints - need)) / 100;
        } else {
            return Math.max(baseSpeed * (100 - (need - nutrientPoints) * 4) / 100, 0);
        }
    }

    // endregion growth rate calc

    // region weed generation

    @Override
    public void spawnWeed() {
        // crop with 31 resistance survive getting weeded.
        if (this.hasCrop() && !(this.seed.getCrop() instanceof CropMigrator)
            && (!ConfigurationHandler.weedsWipePlants || this.seed.getStats()
                .getResistance() >= Constants.MAX_SEED_STAT)) {
            return;
        }
        // if we have weed-ex remaining, stop the weeding.
        if (this.weedEXStorage > 0) {
            this.weedEXStorage = Math.max(this.weedEXStorage - 5, 0);
            return;
        }
        // else weed this thing
        ISeedData seedData = new SeedData(CropsNHCrops.Weed, SeedStats.DEFAULT_ANALYZED);
        this.plantSeed(seedData);
        this.isDirty = true;
    }

    public void spreadWeed() {
        // when non-weed crops have a resistance stat equal or above their growth stat, the stats
        if (!this.hasWeed() && this.seed.getStats()
            .getResistance()
            >= this.seed.getStats()
                .getGrowth())
            if (this.weedEXStorage > 0) {
                this.weedEXStorage = Math.max(this.weedEXStorage - 5, 0);
                return;
            }
        // pick a random nearby block
        int x = this.xCoord;
        int y = this.yCoord;
        int z = this.zCoord;
        switch (XSTR.XSTR_INSTANCE.nextInt(4)) {
            case 0 -> ++x;
            case 1 -> --x;
            case 2 -> ++z;
            case 3 -> --z;
        }
        // If the block is a crop stick, try to spawn a weed in it.
        if (this.worldObj.getTileEntity(x, y, z) instanceof ICropStickTile neighbourTE) {
            // don't override weeds with weeds.
            if (neighbourTE.hasCrop()) {
                // sicken crop instead of deleting them.
                if (!neighbourTE.hasWeed() && !neighbourTE.isSick()) {
                    neighbourTE.transferDisease();
                }
            } else if (!neighbourTE.hasWeed()) {
                // it will handle the weed-ex drain on its own.
                neighbourTE.spawnWeed();
            }
        }
        // If the block is air, try putting some tall grass on it.
        else if (this.worldObj.isAirBlock(x, y, z)) {
            Block block = this.worldObj.getBlock(x, y - 1, z);
            // Only possible when the air block is over grass, dirt or farmland, might want to make a loader to add more
            // replaceable blocks on the fly.
            if (block == net.minecraft.init.Blocks.grass || block == net.minecraft.init.Blocks.dirt
                || block == net.minecraft.init.Blocks.farmland) {
                this.worldObj.setBlock(x, y - 1, z, net.minecraft.init.Blocks.grass, 0, 7);
                this.worldObj.setBlock(x, y, z, net.minecraft.init.Blocks.tallgrass, 1, 7);
            }
        }
    }

    // endregion weed generation

    // region breeding and crossing

    private boolean attemptBreeding() {
        if (XSTR.XSTR_INSTANCE.nextInt(ConfigurationHandler.breedingChance) != 0) return false;
        // find a crop we can turn into
        List<ICropStickTile> neighbours = this.getNeighbours();
        if (neighbours == null) return false;
        neighbours.removeIf(n -> n == null || !n.hasCrop() || n.hasWeed());
        if (neighbours.isEmpty()) return false;
        ICropCard result = this.getBreedingResult(neighbours);
        if (result == null || neighbours.isEmpty()
            || !this.isValidSoilForCrop(result)
            || !this.wouldCropBeAbleToGrow(result)) {
            return false;
        }

        // if all parents have fertilizer in them, stats cannot go down.
        boolean onlyGoUp = neighbours.stream()
            .allMatch(x -> x.getFertilizerStorage() > 0);
        // find all the parent's stats.
        Collection<ISeedStats> parentStats = neighbours.stream()
            .map(
                t -> t.getSeed()
                    .getStats())
            .collect(Collectors.toList());
        byte ga = variateStat(onlyGoUp, parentStats, ISeedStats::getGain);
        byte re = variateStat(onlyGoUp, parentStats, ISeedStats::getResistance);
        byte gr = variateStat(onlyGoUp, parentStats, ISeedStats::getGrowth);

        // plant it
        this.plantSeed(new SeedData(result, new SeedStats(gr, ga, re, false)));
        return true;
    }

    private ICropCard getBreedingResult(List<ICropStickTile> neighbours) {
        // 50% chance it will attempt to cross instead of breeding
        if (XSTR.XSTR_INSTANCE.nextBoolean()) {
            // filter out crops that can't cross
            ArrayList<ICropCard> crossingParents = neighbours.stream()
                .filter(ICropStickTile::canCross)
                .map(
                    t -> t.getSeed()
                        .getCrop())
                .collect(Collectors.toCollection(ArrayList::new));
            // check if we got enough parents that can cross.
            if (!crossingParents.isEmpty()) {
                ICropCard chosen = crossingParents.get(XSTR.XSTR_INSTANCE.nextInt(crossingParents.size()));
                // Ensure only crops that participated in the mutation contrinute to the new baseline stats
                neighbours.removeIf(
                    s -> s.getSeed()
                        .getCrop() != chosen);
                return chosen;
            }
        }

        // then attempt to breed deterministically.
        ArrayList<ICropCard> breedingParents = neighbours.stream()
            .filter(ICropStickTile::canBreed)
            .map(
                t -> t.getSeed()
                    .getCrop())
            .collect(Collectors.toCollection(ArrayList::new));
        // check if we got enough parents that can breed.
        if (breedingParents.size() < 2) return null;
        // find all matching mutations
        List<ICropMutation> deterministicMutations = MutationRegistry.instance
            .getPossibleDeterministicMutations(breedingParents);
        if (deterministicMutations != null && deterministicMutations.size() > 0) {
            // pick a random matching mutation
            ICropMutation chosenMutation = deterministicMutations
                .get(XSTR.XSTR_INSTANCE.nextInt(deterministicMutations.size()));
            if (chosenMutation.canBreed(breedingParents, this.worldObj, this, this.xCoord, this.yCoord, this.zCoord)) {
                Collection<ICropCard> chosenMutationParents = chosenMutation.getParents();
                // Ensure only crops that participated in the mutation contrinute to the new baseline stats
                neighbours.removeIf(
                    s -> !chosenMutationParents.contains(
                        s.getSeed()
                            .getCrop()));
                return chosenMutation.getOutput();
            }
        }

        // find all matching pools
        List<IMutationPool> pooledMutations = MutationRegistry.instance.getPossiblePoolMutations(breedingParents);
        if (pooledMutations != null && !pooledMutations.isEmpty()) {
            // pick a random matching pool
            IMutationPool chosenPool = pooledMutations.get(XSTR.XSTR_INSTANCE.nextInt(pooledMutations.size()));
            // Ensure only crops that participated in the mutation contrinute to the new baseline stats
            neighbours.removeIf(
                s -> !chosenPool.contains(
                    s.getSeed()
                        .getCrop()));
            // pick a random crop in the pool.
            ArrayList<ICropCard> potentialResults = new ArrayList<>(chosenPool.getMembers());
            return potentialResults.get(XSTR.XSTR_INSTANCE.nextInt(potentialResults.size()));
        }

        return null;
    }

    @Override
    public boolean canCross() {
        return this.hasCrop() && !this.hasWeed()
            && this.seed.getCrop()
                .getCrossingThreshold() >= 0.0f
            && this.getGrowthPercent() >= this.seed.getCrop()
                .getCrossingThreshold();
    }

    @Override
    public boolean canBreed() {
        return this.hasCrop() && !this.hasWeed()
            && this.seed.getCrop()
                .getBreedingThreshold() >= 0.0f
            && this.getGrowthPercent() >= this.seed.getCrop()
                .getBreedingThreshold();
    }

    public static byte variateStat(boolean onlyGoUp, Collection<ISeedStats> parentStats,
        ToIntFunction<ISeedStats> collector) {
        // average parents
        int newValue = parentStats.stream()
            .mapToInt(collector)
            .reduce(0, Integer::sum) / parentStats.size();
        // variate
        int variation = ConfigurationHandler.breedingHigh + 1 - ConfigurationHandler.breedingLow;
        variation = XSTR.XSTR_INSTANCE.nextInt(variation) + ConfigurationHandler.breedingLow;
        if (onlyGoUp) variation = Math.max(0, variation);

        // clamp
        return (byte) Math.max(Constants.MIN_SEED_STAT, Math.min(Constants.MAX_SEED_STAT, newValue + variation));
    }

    // endregion breeding and crossing

    // region event handling

    @Override
    public void updateEntity() {
        super.updateEntity();
        this.ticker = ++this.ticker % TICK_RATE;
        if (this.ticker == 0) {
            this.onGrowthTick();
        }
        if (this.isDirty) {
            this.isDirty = false;
            this.markDirty();
            this.markForUpdate();
            this.worldObj.updateLightByType(EnumSkyBlock.Block, this.xCoord, this.yCoord, this.zCoord);
        }
    }

    @Override
    public void transferDisease() {
        if (!this.hasCrop() || this.isSick || this.hasWeed()) return;
        if (XSTR.XSTR_INSTANCE.nextInt(Constants.MAX_SEED_STAT) > this.seed.getStats()
            .getResistance()) {
            this.isSick = true;
            this.isDirty = true;
            this.markDirty();
        }
    }

    @Override
    public boolean cureDisease() {
        if (!this.hasCrop() || !this.isSick) return false;
        this.isSick = false;
        this.isDirty = true;
        this.markDirty();
        return true;
    }

    public void spreadDisease() {
        List<ICropStickTile> neighbours = this.getNeighbours();
        if (neighbours == null) return;
        neighbours.removeIf(
            x -> !x.hasCrop() || x.getSeed()
                .getCrop() instanceof CropMigrator);
        if (neighbours.isEmpty()) return;
        neighbours.get(XSTR.XSTR_INSTANCE.nextInt(neighbours.size()))
            .transferDisease();
    }

    private void doGrowth() {
        if (this.growthProgress < this.seed.getCrop()
            .getGrowthDuration()) {
            int growthRate = this.calcGrowthRate();
            // check if the crop should get sick
            if (growthRate <= 0) {
                this.isSick = true;
                this.isDirty = true;
                this.markDirty();
                return;
            }

            // run crop growth tick handler
            this.seed.getCrop()
                .onGrowthTick(this);
            // increase growth progress
            this.growthProgress += growthRate * ConfigurationHandler.growthMultiplier;
            if (this.growthProgress > this.seed.getCrop()
                .getGrowthDuration()) {
                this.growthProgress = this.seed.getCrop()
                    .getGrowthDuration();
            }
            // something's changed
            this.markDirty();
        }
        // only request re-render when the crop is changing state to be rendered.
        int spriteIndex = this.seed.getCrop()
            .getSpriteIndex(this);
        if (spriteIndex != this.spriteIndex) {
            this.spriteIndex = spriteIndex;
            this.isDirty = true;
        }
    }

    @Override
    public void onGrowthTick() {
        // let the can grow logic run on the client so that it's able to tell the player why a crop isn't growing.
        boolean canGrow = this.canGrow();
        if (worldObj.isRemote) return;
        if (this.hasCrop()) {
            // abort early if the migrator crop is present (it should only be harvested by the player)
            if (this.seed.getCrop() instanceof CropMigrator) {
                this.growthProgress = this.seed.getCrop()
                    .getGrowthDuration();
                return;
            }
            // spread disease if sick
            if (this.isSick) {
                spreadDisease();
                canGrow &= this.isSick;
            }
            // run the growth check on client too so that the player can see why it's not growing.
            if (canGrow) {
                doGrowth();
            }
            if (ConfigurationHandler.enableWeeds && this.seed.getCrop()
                .spreadsWeeds(this)
                && XSTR.XSTR_INSTANCE.nextInt(ConfigurationHandler.weedSpreadChance) - this.seed.getStats()
                    .getGrowth() <= 2) {
                this.spreadWeed();
            }
        } else {
            // if it's empty spawn a weed 1% of the time, the spawn weed function handles the weed-ex drain.
            if (!this.isCrossCrop || !this.attemptBreeding()) {
                if (ConfigurationHandler.enableWeeds
                    && XSTR.XSTR_INSTANCE.nextInt(ConfigurationHandler.weedSpawnChance) == 0) {
                    this.spawnWeed();
                }
            }
        }
        // consume resources if available
        if (this.fertilizerStorage > 0) this.fertilizerStorage--;
        if (this.waterStorage > 0) this.waterStorage--;
    }

    private static Item EXTRA_UTILS_WATERING_CAN = null;
    private static Class<?> UTILITY_IN_EXCESS_WATERING_CAN = null;

    @Override
    public boolean onRightClick(EntityPlayer player, ItemStack heldItem) {
        if (worldObj.isRemote) return true;
        // items that implement ICropRightClickHandler will be able to
        if (CropsNHUtils.isStackValid(heldItem)) {
            // check if it's a fertilizer
            int fertilizerPotency = FertilizerRegistry.instance.getPotency(heldItem);
            if (fertilizerPotency > 0) {
                if (this.addFertilizer(fertilizerPotency, 100 - fertilizerPotency, 100, false)) {
                    if (!player.capabilities.isCreativeMode) {
                        heldItem.stackSize--;
                    }
                    return true;
                }
            }
            // try planting it
            if (tryPlantSeed(heldItem)) {
                if (!player.capabilities.isCreativeMode) {
                    heldItem.stackSize--;
                }
                return true;
            }
            // technically temporary since It's getting replaced with UiE
            // mixin would work better but, this is a temp impl that I expect to remove as soon as UiE is in the pack.
            if (ModUtils.ExtraUtilities.isModLoaded()) {
                if (EXTRA_UTILS_WATERING_CAN == null) {
                    EXTRA_UTILS_WATERING_CAN = CropsNHUtils.getModItem(ModUtils.ExtraUtilities, "watering_can", 1, 0)
                        .getItem();
                }
                if (heldItem.getItem() == EXTRA_UTILS_WATERING_CAN && CropsNHUtils.getItemMeta(heldItem) != 2) {
                    this.addWater(10, 90, 100, false);
                    return true;
                }
            }
            // TODO: REMOVE UIE WATERING CAN COMPAT WHEN EVENT IS PROPERLY IMPLEMENTED
            if (ModUtils.UtilitiesInExcess.isModLoaded()) {
                if (UTILITY_IN_EXCESS_WATERING_CAN == null) {
                    Item can = CropsNHUtils.getModItem(ModUtils.UtilitiesInExcess, "watering_can_basic", 1, 0)
                        .getItem();
                    UTILITY_IN_EXCESS_WATERING_CAN = can.getClass();
                }
                if (UTILITY_IN_EXCESS_WATERING_CAN.isInstance(heldItem.getItem())) {
                    this.addWater(10, 90, 100, false);
                    return true;
                }
            }
        }
        if (this.seed != null) {
            if (this.seed.getCrop()
                .onRightClick(this, player)) {
                return true;
            }
        } else if (this.isCrossCrop) {
            this.setCrossCrop(false);
            if (!player.capabilities.isCreativeMode) {
                dropItem(new ItemStack(CropsNHBlocks.blockCropSticks, 1));
            }
            return true;
        }
        // attempt to harvest
        if (this.isMature()) {
            this.doPlayerHarvest();
        }
        return true;
    }

    public ItemStack getSeedDrop() {
        if (this.hasCrop() && !this.hasWeed()
            && this.seed.getStats()
                .getResistance() > XSTR.XSTR_INSTANCE.nextInt(Constants.MAX_SEED_STAT)) {
            return this.getSeedStack();
        }
        return null;
    }

    @Override
    public boolean onLeftClick(EntityPlayer player, ItemStack heldItem) {
        if (this.hasCrop() && !this.hasWeed()) {
            if (this.isMature()) {
                this.doPlayerHarvest();
            }
            ItemStack seedDrop = getSeedDrop();
            if (seedDrop != null) {
                dropItem(seedDrop);
            }

            this.clear();
        }
        if (this.isCrossCrop) {
            this.setCrossCrop(false);
            if (!player.capabilities.isCreativeMode) {
                dropItem(new ItemStack(CropsNHBlocks.blockCropSticks, 1));
            }
            return true;
        }
        return true;
    }

    @Override
    public void onDestroyed() {

    }

    private boolean shouldTrample(float fallDistance) {
        if (fallDistance > 0) {
            // chance is slightly adjusted since this gets fired a couple times for some reason.
            return XSTR.XSTR_INSTANCE.nextFloat() < fallDistance - 0.75f;
        }
        if (this.hasCrop()) {
            // weeds cannot be trampled
            if (this.hasWeed()) return false;
            // max resistance prevents trampling
            if (this.seed.getStats()
                .getResistance() >= Constants.MAX_SEED_STAT) return false;
            // chance of rolling for trampling increases with tier;
            double maxRoll = 100.0d * Math.pow(
                0.95d,
                this.seed.getCrop()
                    .getTier());
            int roll = XSTR.XSTR_INSTANCE.nextInt((int) maxRoll);
            if (roll > 0) return false;
            // higher resistance means higher chance of surviving the trampling.
            return XSTR.XSTR_INSTANCE.nextInt(Constants.MAX_SEED_STAT) > this.seed.getStats()
                .getResistance();
        }
        return XSTR.XSTR_INSTANCE.nextInt(100) <= 0;
    }

    private static boolean hasFallen(EntityLivingBase entity) {
        return entity.onGround && entity.fallDistance > 0.0f;
    }

    @Override
    public void onEntityCollision(Entity target) {
        // only on living entities plz
        if (!(target instanceof EntityLivingBase entity)) {
            return;
        }
        if (CropsNHUtils.isServer() && (entity.isSprinting() || hasFallen(entity))
            && shouldTrample(hasFallen(entity) ? entity.fallDistance : 0.0f)) {
            // drop seed
            ItemStack seedDrop = getSeedDrop();
            ArrayList<ItemStack> toDrop = this.harvest();
            if (toDrop == null) toDrop = new ArrayList<>(2);
            if (seedDrop != null) toDrop.add(seedDrop);
            toDrop.add(CropsNHItemList.cropSticks.get(this.isCrossCrop ? 2 : 1));
            // no more crop in here.
            this.clear();
            // replace farmland with dirt if block under is farmland
            int y = this.yCoord - 1;
            if (this.worldObj.getBlock(this.xCoord, y, this.zCoord) == Blocks.farmland) {
                this.worldObj.setBlock(this.xCoord, y, this.zCoord, Blocks.dirt, 0, 7);
            }
            // remove cropsticks
            this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, Blocks.air, 0, 7);

            // drop the items once the cropstick is gone
            for (ItemStack drop : toDrop) {
                dropItem(drop);
            }

            // self terminate
            this.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
            return;
        }

        // always prevent collision events if the entity is sneaking
        if (this.hasCrop() && !entity.isSneaking()) {
            if (this.seed.getCrop()
                .getEntityDamage() > 0) {
                damageEntity(
                    entity,
                    this.seed.getCrop()
                        .getEntityDamage());
            }
            seed.getCrop()
                .onEntityCollision(this, target);
        }
    }

    /**
     * Used to apply contact damage from crops.
     *
     * @param entity The entity to damage.
     * @param damage The damage to apply.
     */
    private void damageEntity(EntityLivingBase entity, float damage) {
        // don't damage immune targets
        if (entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.disableDamage) {
            return;
        }
        // We could probably use a custom damage source so that when you die to crops you don't get confused.
        entity.attackEntityFrom(DamageSource.cactus, damage);
    }

    @Override
    public void onNeighbourChange() {

    }

    // endregion event handling

    @Override
    public void dropItem(ItemStack drop) {
        if (this.worldObj.isRemote) return;
        WorldUtils.dropItem(this.worldObj, this.xCoord, this.yCoord, this.zCoord, drop);
    }

}
