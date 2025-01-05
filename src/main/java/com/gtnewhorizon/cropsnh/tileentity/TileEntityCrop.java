package com.gtnewhorizon.cropsnh.tileentity;

import java.util.ArrayList;
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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.common.util.ForgeDirection;

import com.gtnewhorizon.cropsnh.api.IAdditionalCropData;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropMutation;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.api.IMutationPool;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.crops.CropWeed;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.FertilizerRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.MutationRegistry;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;
import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.items.ItemGenericSeed;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.WorldUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.objects.XSTR;
import gregtech.api.util.GTUtility;

public class TileEntityCrop extends TileEntityCropsNH implements ICropStickTile {

    public final static int TICK_RATE = 256;
    private int ticker = 0;
    private boolean isDirty = true;
    private int spriteIndex = 0;

    // seed status
    private ISeedStats stats = null;
    private ICropCard crop = null;
    private boolean isCrossCrop = false;
    private IAdditionalCropData additionalCropData = null;

    // crop status
    private boolean isSick = false;
    private int growthProgress = 0;
    private int waterStorage = 0;
    private int weedexStorage = 0;
    private int fertilizerStorage = 0;
    // used to tell waila why the crop ain't growing.
    private List<IGrowthRequirement> failedChecks = null;

    public TileEntityCrop() {
        this.ticker = XSTR.XSTR_INSTANCE.nextInt(256);
    }

    @Override
    public boolean isRotatable() {
        return false;
    }

    // region status checks

    @Override
    public void updateState() {
        this.isDirty = true;
    }

    @Override
    public boolean hasCrop() {
        return this.crop != null && this.stats != null;
    }

    @Override
    public boolean hasWeed() {
        return this.crop instanceof CropWeed;
    }

    @Override
    public boolean isSick() {
        return this.hasCrop() && !this.hasWeed() && this.isSick;
    }

    @Override
    public boolean canUpgrade() {
        return this.crop == null && !this.isCrossCrop;
    }

    @Override
    public boolean isCrossCrop() {
        return this.isCrossCrop;
    }

    @Override
    public boolean canPlantSeed() {
        return this.crop == null && !this.isCrossCrop;
    }

    @Override
    public boolean isMature() {
        return this.crop != null && this.crop.getGrowthDuration() <= this.growthProgress;
    }

    @Override
    public ICropCard getCrop() {
        return this.crop;
    }

    @Override
    public ISeedStats getStats() {
        return this.crop == null || this.hasWeed() ? null : stats.copy();
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
        if (this.crop == null || this.isSick) {
            this.failedChecks = null;
            return false;
        }
        // Weeds don't care where you are, they will grow
        if (this.crop instanceof CropWeed) {
            this.failedChecks = null;
            return true;
        }
        // Check the world growth requirements
        Iterable<IGrowthRequirement> reqs = this.crop.getGrowthRequirements();
        // abort early if no reqs
        if (reqs == null) {
            this.failedChecks = null;
            return true;
        }

        LinkedList<IGrowthRequirement> failedReqs = null;
        for (IGrowthRequirement req : reqs) {
            if (!(req instanceof IWorldGrowthRequirement)) continue;
            if (!((IWorldGrowthRequirement) req).canGrow(this.worldObj, this, this.xCoord, this.yCoord, this.zCoord)) {
                if (failedReqs == null) failedReqs = new LinkedList<>();
                failedReqs.add(req);
            }
        }
        return (this.failedChecks = failedReqs) == null;
    }

    @SideOnly(Side.CLIENT)
    public void getMagnifyingGlassStatus(List<String> information) {
        if (this.crop != null) {
            if (this.hasWeed()) {
                information.add(StatCollector.translateToLocal("cropsnh_tooltip.weeds"));
            } else {
                String header, value;

                // Add the seed name
                header = StatCollector.translateToLocal("cropsnh_tooltip.seed");
                value = StatCollector.translateToLocal(this.crop.getUnlocalizedName());
                information.add(header + ": " + value);

                // do not display crop stats for weeds
                if (!(this.crop instanceof CropWeed)) {
                    if (this.isSick) {
                        information.add(
                            EnumChatFormatting.RED + StatCollector.translateToLocal("cropsnh_tooltip.isSick")
                                + EnumChatFormatting.RESET);
                    }

                    List<IGrowthRequirement> failedReqs = this.failedChecks;
                    if (failedReqs != null) {
                        for (IGrowthRequirement req : failedReqs) {
                            information.add(EnumChatFormatting.RED + req.getDescription() + EnumChatFormatting.RESET);
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

    // endregion status checks

    // region resource storage

    @Override
    public int getFertilizerStorage() {
        return this.fertilizerStorage;
    }

    @Override
    public boolean addFertilizer(int amount, int threshold, int maxStorage) {
        if (this.fertilizerStorage > threshold) return false;
        this.fertilizerStorage = Math.min(maxStorage, this.fertilizerStorage + amount);
        this.isDirty = true;
        return true;
    }

    @Override
    public int getWaterStorage() {
        return this.waterStorage;
    }

    @Override
    public boolean addWater(int amount, int threshold, int maxStorage) {
        if (this.waterStorage >= threshold) return false;
        this.waterStorage = Math.min(maxStorage, this.waterStorage + amount);
        this.isDirty = true;
        return true;
    }

    @Override
    public int getWeedExStorage() {
        return this.weedexStorage;
    }

    @Override
    public boolean addWeedEx(int amount, int threshold, int maxStorage) {
        if (this.weedexStorage >= threshold) return false;
        this.weedexStorage = Math.min(maxStorage, this.weedexStorage + amount);
        this.isDirty = true;
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
        return this.tryPlantSeed(cc, stats);
    }

    @Override
    public boolean tryPlantSeed(ICropCard cc, ISeedStats stats) {
        // check if it can be planted on this soil.
        Block block = this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord);
        int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord - 1, this.zCoord);
        if (!cc.getSoilTypes()
            .isRegistered(block, meta)) {
            return false;
        }
        // all good we can plant the seed
        this.plantSeed(cc, stats);
        return true;
    }

    @Override
    public void plantSeed(ICropCard cc, ISeedStats stats) {
        this.clear();
        // this should be the only place stats and crop are ever set to a non-null value.
        this.crop = cc;
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

        this.isDirty = true;
        if (oldCrop != null) {
            oldCrop.onRemoved(this);
        }
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

    // endregion seed planting

    // region harvesting

    @Override
    public ArrayList<ItemStack> harvest() {
        // TODO: IMPLEMENT NEW DROP COUNT CALCULATION
        // must be fully grown to harvest
        if (this.crop == null || this.stats == null || !this.isMature()) return null;

        crop.onHarvest(this);
        this.growthProgress = 0;
        this.spriteIndex = 0;
        this.isDirty = true;

        double chance = this.crop.getDropChance();
        int dropCount = (int) Math.max(
            0L,
            Math.round(
                XSTR.XSTR_INSTANCE.nextGaussian() * (chance *= Math.pow(1.03, this.stats.getGain())) * 0.6827
                    + chance));

        if (dropCount <= 0) return null;
        // check if we got a drop
        Map<ItemStack, Integer> dropTable = this.crop.getDropTable();
        if (dropTable == null) return null;
        // roll drop table
        ArrayList<ItemStack> ret = new ArrayList<>();
        for (Map.Entry<ItemStack, Integer> drop : dropTable.entrySet()) {
            int count = 0;
            int gainBonus = 0;
            // merge re-rolls into the same stack.
            for (int i = 0; i < dropCount; i++) {
                if (XSTR.XSTR_INSTANCE.nextInt(10000) < drop.getValue()) {
                    if (XSTR.XSTR_INSTANCE.nextInt(100) <= this.stats.getGain()) {
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
        if (this.worldObj.isRemote || this.crop == null || this.stats == null || this.hasWeed() || !this.isMature())
            return false;
        ArrayList<ItemStack> drops = harvest();
        if (drops == null) return true;
        for (ItemStack drop : drops) {
            if (drop == null || drop.getItem() == null) {
                continue;
            }
            this.dropItem(drop);
        }
        return true;
    }

    @Override
    public ItemStack getSeedStack() {
        // validate if seed is valid
        if (this.crop == null || this.stats == null) return null;

        // save crop info
        NBTTagCompound tag = new NBTTagCompound();
        this.writeSeedNBT(tag);
        // create seed with tags
        ItemStack seed = new ItemStack(CropsNHItems.genericSeed, 1);
        seed.setTagCompound(tag);
        return seed;
    }

    // endregion harvesting

    // region nbt handling

    // this saves the data on the tile entity
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        if (this.crop != null) {
            // save crop specific empty
            writeSeedNBT(tag);
            if (this.crop != null && this.isSick) tag.setBoolean(Names.NBT.sick, true);
            tag.setInteger(Names.NBT.progress, this.growthProgress);
        } else if (this.isCrossCrop) {
            // ignore crop state if it's a cross
            tag.setBoolean(Names.NBT.crossCrop, true);
        }
        // save crop state
        tag.setInteger(Names.NBT.water, this.waterStorage);
        tag.setInteger(Names.NBT.fertilizer, this.fertilizerStorage);
        tag.setInteger(Names.NBT.weedEx, this.weedexStorage);
    }

    public void writeSeedNBT(NBTTagCompound tag) {
        // save crop information if a crop is planted exists
        if (this.crop != null) {
            tag.setString(Names.NBT.crop, this.crop.getId());
            if (this.stats != null) {
                this.stats.writeToNBT(tag);
            }
            if (this.additionalCropData != null) {
                tag.setTag(Names.NBT.extra, additionalCropData.writeToNBT());
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
            this.isCrossCrop = false;
            // load crop data if it's not a cross crop
            this.loadCropNBT(tag);
            // only update the growth progress
            if (this.crop != null) {
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
        this.weedexStorage = tag.hasKey(Names.NBT.weedEx, Data.NBTType._int) ? tag.getInteger(Names.NBT.weedEx) : 0;
        this.isDirty = true;
    }

    public void loadCropNBT(NBTTagCompound tag) {
        if (tag == null || !tag.hasKey(Names.NBT.crop, Data.NBTType._string)) {
            this.clear();
            return;
        }
        String name = tag.getString(Names.NBT.crop);
        ICropCard cc = CropRegistry.instance.get(name);
        if (cc == null) {
            clear();
        } else {
            ISeedStats stats = SeedStats.readFromNBT(tag);
            this.plantSeed(cc, stats);
            if (tag.hasKey(Names.NBT.extra, Data.NBTType._object)) {
                this.additionalCropData = crop.readAdditionalData(tag.getCompoundTag(Names.NBT.extra));
            }
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
            icon = this.crop.getSprite(this);
        }
        return icon;
    }

    // endregion rendering stuff

    // region growth rate calc

    public int calcGrowthRate() {
        // TODO: CREATE CUSTOM GROWTH FORMULA
        return 100;
    }

    public static int getGrowthRate(boolean inLikedBiome, boolean onLikedSoil, boolean canSeeSky, int waterStorage,
        int fertilizerStorage, int tier, int growth) {
        // calculate growth modifier for environment values
        double mult = 1.0D;
        // max of 2
        mult += inLikedBiome ? 2.0D : 0.0D;
        // max of 2
        mult += (double) ((waterStorage + 24) / 25) * 0.25D;
        // max of 1
        mult += canSeeSky ? 1.0D : 0.0D;
        // max of 1
        mult += onLikedSoil ? 1.0D : 0.0D;
        // max of 4
        mult += (double) ((fertilizerStorage + 24) / 25) * 0.5D;
        // apply tier debuff
        mult *= Math.pow(0.95D, tier);

        double base = 10 * mult + growth;
        return (int) base;
    }

    // endregion growth rate calc

    // region weed generation

    @Override
    public void spawnWeed() {
        // crop with 31 resistance survive getting weeded.
        if (this.crop != null && this.stats != null
            && (!ConfigurationHandler.weedsWipePlants || this.stats.getResistance() >= Constants.MAX_SEED_STAT)) {
            return;
        }
        // if we have weed-ex remaining, stop the weeding.
        if (this.weedexStorage > 0) {
            this.weedexStorage = Math.max(this.weedexStorage - 5, 0);
            return;
        }
        // else weed this thing
        ICropCard weed = CropRegistry.instance.get(Reference.MOD_ID + ":weed");
        ISeedStats stats = new SeedStats((byte) 1, (byte) 1, (byte) 1, true);
        this.plantSeed(weed, stats);
        this.isDirty = true;
    }

    public void spreadWeed() {
        // when non-weed crops have a resistance stat equal or above their growth stat, the stats
        if (!this.hasWeed() && this.stats != null && this.stats.getResistance() >= this.stats.getGrowth())
            if (this.weedexStorage > 0) {
                this.weedexStorage = Math.max(this.weedexStorage - 5, 0);
                return;
            }
        // pick a random nearby block
        int x = this.xCoord;
        int y = this.yCoord;
        int z = this.zCoord;
        switch (XSTR.XSTR_INSTANCE.nextInt(4)) {
            case 0:
                ++x;
                break;
            case 1:
                --x;
                break;
            case 2:
                ++z;
                break;
            case 3:
                --z;
                break;
        }
        // If the block is a crop stick, try to spawn a weed in it.
        if (this.worldObj.getTileEntity(x, y, z) instanceof ICropStickTile) {
            ICropStickTile neighbourTE = (ICropStickTile) this.worldObj.getTileEntity(x, y, z);
            // don't override weeds with weeds.
            if (!neighbourTE.hasWeed()) {
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

    private void attemptBreeding() {
        // find a crop we can turn into
        List<ICropStickTile> neighbours = this.getNeighbours();
        if (neighbours == null || neighbours.isEmpty()) return;
        ICropCard result = this.getBreedingResult(neighbours);
        if (result == null || neighbours.isEmpty()) {
            return;
        }

        // variate the stats
        Collection<ISeedStats> parentStats = neighbours.stream()
            .map(ICropStickTile::getStats)
            .collect(Collectors.toList());
        byte gr = variateStat(parentStats, ISeedStats::getGrowth);
        byte ga = variateStat(parentStats, ISeedStats::getGain);
        byte re = variateStat(parentStats, ISeedStats::getResistance);

        // try planting it
        this.tryPlantSeed(result, new SeedStats(gr, ga, re, false));
    }

    private ICropCard getBreedingResult(List<ICropStickTile> neighbours) {
        // 50% chance it will attempt to cross instead of breeding
        if (XSTR.XSTR_INSTANCE.nextBoolean()) {
            ArrayList<ICropCard> crossingParents = neighbours.stream()
                .filter(ICropStickTile::canCross)
                .map(ICropStickTile::getCrop)
                .collect(Collectors.toCollection(ArrayList::new));
            ICropCard chosen = crossingParents.get(XSTR.XSTR_INSTANCE.nextInt(crossingParents.size()));
            // Ensure only crops that participated in the mutation contrinute to the new baseline stats
            neighbours.removeIf(s -> s.getCrop() != chosen);
            return chosen;
        }

        // then attempt to breed deterministically.
        ArrayList<ICropCard> breedingParents = neighbours.stream()
            .filter(ICropStickTile::canCross)
            .map(ICropStickTile::getCrop)
            .collect(Collectors.toCollection(ArrayList::new));
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
                neighbours.removeIf(s -> !chosenMutationParents.contains(s.getCrop()));
                return chosenMutation.getOutput();
            }
        }

        // find all matching pools
        List<IMutationPool> pooledMutations = MutationRegistry.instance.getPossiblePoolMutations(breedingParents);
        if (pooledMutations != null && !pooledMutations.isEmpty()) {
            // pick a random matching pool
            IMutationPool chosenPool = pooledMutations.get(XSTR.XSTR_INSTANCE.nextInt(pooledMutations.size()));
            // Ensure only crops that participated in the mutation contrinute to the new baseline stats
            neighbours.removeIf(s -> !chosenPool.contains(s.getCrop()));
            // pick a random crop in the pool.
            ArrayList<ICropCard> potentialResults = new ArrayList<>(chosenPool.getMembers());
            return potentialResults.get(XSTR.XSTR_INSTANCE.nextInt(potentialResults.size()));
        }

        return null;
    }

    @Override
    public boolean canCross() {
        return this.hasCrop() && !this.hasWeed() && this.getGrowthPercent() >= this.crop.getCrossingThreshold();
    }

    @Override
    public boolean canBreed() {
        return this.hasCrop() && !this.hasWeed() && this.getGrowthPercent() >= this.crop.getBreedingThreshold();
    }

    public static byte variateStat(Collection<ISeedStats> parentStats, ToIntFunction<ISeedStats> collector) {
        // average parents
        int newValue = parentStats.stream()
            .mapToInt(ISeedStats::getGrowth)
            .reduce(0, Integer::sum) / parentStats.size();
        // variate
        newValue += XSTR.XSTR_INSTANCE.nextInt(ConfigurationHandler.breedingHigh + 1 - ConfigurationHandler.breedingLow)
            + ConfigurationHandler.breedingLow;
        // clamp
        return (byte) Math.min(Constants.MAX_SEED_STAT, Math.max(Constants.MIN_SEED_STAT, newValue));
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
            this.markForUpdate();
            this.worldObj.updateLightByType(EnumSkyBlock.Block, this.xCoord, this.yCoord, this.zCoord);
        }
    }

    @Override
    public void onGrowthTick() {
        // let the can grow logic run on the client so that it's able to tell the player why a crop isn't growing.
        boolean canGrow = this.canGrow();
        if (worldObj.isRemote) return;
        if (this.hasCrop()) {
            // run the growth check on client too so that the player can see why it's not growing.
            if (canGrow) {
                crop.onGrowthTick(this);
                if (this.growthProgress < this.crop.getGrowthDuration()) {
                    this.growthProgress += GTUtility
                        .safeInt((long) (this.calcGrowthRate() * ConfigurationHandler.growthMultiplier));
                    if (this.growthProgress > this.crop.getGrowthDuration()) {
                        this.growthProgress = this.crop.getGrowthDuration();
                    }
                }
                // only request re-render when the crop is changing state to be rendered.
                int spriteIndex = this.crop.getSpriteIndex(this);
                if (spriteIndex != this.spriteIndex) {
                    this.spriteIndex = spriteIndex;
                    this.isDirty = true;
                }
            }
            if (ConfigurationHandler.enableWeeds && this.crop.spreadsWeeds(this)
                && XSTR.XSTR_INSTANCE.nextInt(ConfigurationHandler.weedSpreadChance) - this.stats.getGrowth() <= 2) {
                this.spreadWeed();
            }
        } else {
            // if it's empty spawn a weed 1% of the time, the spawn weed function handles the weed-ex drain.
            if (ConfigurationHandler.enableWeeds
                && XSTR.XSTR_INSTANCE.nextInt(ConfigurationHandler.weedSpawnChance) == 0) {
                this.spawnWeed();
            } else if (this.isCrossCrop && XSTR.XSTR_INSTANCE.nextInt(ConfigurationHandler.breedingChance) == 0) {
                this.attemptBreeding();
            }
        }

        // consume resources if available
        if (this.fertilizerStorage > 0) this.fertilizerStorage--;
        if (this.waterStorage > 0) this.waterStorage--;
    }

    @Override
    public boolean onRightClick(EntityPlayer player, ItemStack heldItem) {
        if (worldObj.isRemote) return true;
        // items that implement ICropRightClickHandler will be able to
        if (heldItem != null && heldItem.stackSize > 0) {
            // check if it's a fertilizer
            int fertilizerPotency = FertilizerRegistry.instance.getPotnecy(heldItem);
            if (fertilizerPotency > 0) {
                if (this.addFertilizer(fertilizerPotency, 90, 100)) {
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
        }
        if (this.crop != null) {
            if (this.crop.onRightClick(this, player)) {
                return true;
            }
        } else if (this.isCrossCrop) {
            this.setCrossCrop(false);
            if (!player.capabilities.isCreativeMode) {
                dropItem(new ItemStack(CropsNHBlocks.blockCrop, 1));
            }
            return true;
        }
        // attempt to harvest
        if (this.isMature()) {
            this.doPlayerHarvest();
        }
        // // TODO: DELETE IC2 DROP SIMULATOR ONCE ALL CROPS ARE PORTED OVER
        // if (heldItem != null) {
        // ic2.api.crops.CropCard cc = Crops.instance.getCropCard(heldItem);
        // if (cc == null) return true;
        // FakeTileEntityCrop te = new FakeTileEntityCrop(cc, this.xCoord, this.yCoord, this.zCoord, this.worldObj);
        // te.setSize((byte) 1);
        // long duration = 0;
        // final int maxRounds = 1_000_000;
        // for (int round = 0; round < maxRounds; round++) {
        // te.setSize((byte) cc.maxSize());
        // for (byte i = cc.getSizeAfterHarvest(te); i < cc.maxSize(); i++) {
        // te.setSize(i);
        // duration += cc.growthDuration(te);
        // }
        // }
        // te.setSize((byte) cc.maxSize());
        // ItemStackMap<Integer[]> drops = new ItemStackMap<>();
        // for (int i = 0; i < maxRounds; i++) {
        // ItemStack drop = cc.getGain(te);
        // if (drop != null) {
        // ItemStack key = drop.copy();
        // key.stackSize = 1;
        // drops.merge(
        // key,
        // new Integer[] { 1, drop.stackSize },
        // (o, n) -> new Integer[] { o[0] + n[0], o[1] + n[1] });
        // }
        // }
        // System.out.println("--------------------------");
        // System.out.println("name: " + heldItem.getDisplayName());
        // System.out.println("creator: " + cc.discoveredBy());
        // System.out.println("tier: " + cc.tier());
        // System.out.println("Growth duration: " + (int) ((double) duration / (double) maxRounds));
        // System.out.println("drop gain chance: " + cc.dropGainChance());
        // for (Map.Entry<ItemStack, Integer[]> entry : drops.entrySet()) {
        // String format = String.format(
        // "%s : %.2f%% | %.2f",
        // entry.getKey()
        // .getDisplayName(),
        // (double) entry.getValue()[0] / (double) maxRounds * 100,
        // (double) entry.getValue()[1] / (double) entry.getValue()[0]);
        // System.out.println(format);
        // }
        // }
        return true;
    }

    // private static class FakeTileEntityCrop extends ic2.core.crop.TileEntityCrop {
    //
    // public Set<Block> reqBlockSet = new HashSet<>();
    // public Set<String> reqBlockOreDict = new HashSet<>();
    //
    // public FakeTileEntityCrop(ic2.api.crops.CropCard cc, int x, int y, int z, World world) {
    // super();
    // this.ticker = 1;
    //
    // // put seed in crop stick
    // this.setWorldObj(world);
    // this.setCrop(cc);
    // this.setGrowth((byte) 1);
    // this.setGain((byte) 1);
    // this.setResistance((byte) 1);
    //
    // this.xCoord = x;
    // this.yCoord = y;
    // this.zCoord = z;
    // this.blockType = Block.getBlockFromItem(Ic2Items.crop.getItem());
    // this.blockMetadata = 0;
    //
    // this.waterStorage = 200;
    // this.humidity = 22;
    // this.nutrientStorage = 200;
    // this.nutrients = 23;
    // this.airQuality = 10;
    // }
    //
    // @Override
    // public void updateEntity() {}
    //
    // @Override
    // public World getWorld() {
    // return this.getWorldObj();
    // }
    //
    // @Override
    // public boolean isBlockBelow(Block reqBlock) {
    // this.reqBlockSet.add(reqBlock);
    // return super.isBlockBelow(reqBlock);
    // }
    //
    // @Override
    // public boolean isBlockBelow(String oreDictionaryName) {
    // this.reqBlockOreDict.add(oreDictionaryName);
    // return super.isBlockBelow(oreDictionaryName);
    // }
    //
    // // region environment simulation
    //
    // @Override
    // public int getLightLevel() {
    // // 9 should allow most light dependent crops to grow
    // // the only exception I know of the eating plant which checks
    // return 15;
    // }
    //
    // @Override
    // public byte getHumidity() {
    // return this.humidity;
    // }
    //
    // @Override
    // public byte updateHumidity() {
    // return this.humidity;
    // }
    //
    // @Override
    // public byte getNutrients() {
    // return this.nutrients;
    // }
    //
    // @Override
    // public byte updateNutrients() {
    // return this.nutrients;
    // }
    //
    // @Override
    // public byte getAirQuality() {
    // return this.airQuality;
    // }
    //
    // @Override
    // public byte updateAirQuality() {
    // return this.nutrients;
    // }
    //
    // }

    @Override
    public boolean onLeftClick(EntityPlayer player, ItemStack heldItem) {
        if (this.hasCrop() && !this.hasWeed()) {
            if (this.isMature()) {
                this.doPlayerHarvest();
            }
            if (this.stats.getResistance() > XSTR.XSTR_INSTANCE.nextInt(Constants.MAX_SEED_STAT)) {
                dropItem(this.getSeedStack());
            }
            this.clear();
        }
        if (this.isCrossCrop) {
            this.setCrossCrop(false);
            if (!player.capabilities.isCreativeMode) {
                dropItem(new ItemStack(CropsNHBlocks.blockCrop, 1));
            }
            return true;
        }
        return true;
    }

    @Override
    public void onDestroyed() {

    }

    @Override
    public void onEntityCollision(Entity target) {
        // only on living entities plz
        if (!(target instanceof EntityLivingBase)) {
            return;
        }
        EntityLivingBase entity = (EntityLivingBase) target;
        // always prevent collision events if the entity is sneaking
        if (this.crop != null && !entity.isSneaking()) {
            if (this.crop.getEntityDamage() > 0) {
                damageEntity(entity, this.crop.getEntityDamage());
            }
            crop.onEntityCollision(this, target);
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
