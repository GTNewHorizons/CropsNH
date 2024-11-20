package com.gtnewhorizon.cropsnh.apiimpl.v1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.google.common.collect.Lists;
import com.gtnewhorizon.cropsnh.api.API;
import com.gtnewhorizon.cropsnh.api.APIBase;
import com.gtnewhorizon.cropsnh.api.APIStatus;
import com.gtnewhorizon.cropsnh.api.v1.APIv1;
import com.gtnewhorizon.cropsnh.api.v1.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.v1.ICrop;
import com.gtnewhorizon.cropsnh.api.v1.ICropPlant;
import com.gtnewhorizon.cropsnh.api.v1.ICropsNHPlant;
import com.gtnewhorizon.cropsnh.api.v1.IFertiliser;
import com.gtnewhorizon.cropsnh.api.v1.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.api.v1.IGrowthRequirementBuilder;
import com.gtnewhorizon.cropsnh.api.v1.IJournal;
import com.gtnewhorizon.cropsnh.api.v1.IMutation;
import com.gtnewhorizon.cropsnh.api.v1.IMutationHandler;
import com.gtnewhorizon.cropsnh.api.v1.IRake;
import com.gtnewhorizon.cropsnh.api.v1.ISeedStats;
import com.gtnewhorizon.cropsnh.api.v1.IStatCalculator;
import com.gtnewhorizon.cropsnh.api.v1.IStatStringDisplayer;
import com.gtnewhorizon.cropsnh.api.v1.ItemWithMeta;
import com.gtnewhorizon.cropsnh.api.v1.SeedRequirementStatus;
import com.gtnewhorizon.cropsnh.blocks.BlockCrop;
import com.gtnewhorizon.cropsnh.blocks.BlockModPlant;
import com.gtnewhorizon.cropsnh.farming.CropRegistry;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlantAPIv1;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlantCropsNH;
import com.gtnewhorizon.cropsnh.farming.growthrequirement.GrowthRequirementHandler;
import com.gtnewhorizon.cropsnh.farming.mutation.Mutation;
import com.gtnewhorizon.cropsnh.farming.mutation.MutationHandler;
import com.gtnewhorizon.cropsnh.farming.mutation.statcalculator.StatCalculator;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.Blocks;
import com.gtnewhorizon.cropsnh.init.Items;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;
import com.gtnewhorizon.cropsnh.utility.exception.MissingArgumentsException;
import com.gtnewhorizon.cropsnh.utility.statstringdisplayer.StatStringDisplayer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class APIimplv1 implements APIv1 {

    private final int version;
    private final APIStatus status;

    public APIimplv1(int version, APIStatus status) {
        this.version = version;
        this.status = status;
    }

    @Override
    public APIBase getAPI(int maxVersion) {
        if (maxVersion == version && status == APIStatus.OK) {
            return this;
        } else {
            return API.getAPI(maxVersion);
        }
    }

    @Override
    public APIStatus getStatus() {
        return status;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public boolean isActive(World world) {
        return true;
    }

    @Override
    public List<ItemStack> getCropsItems() {
        return Lists.newArrayList(new ItemStack(Items.crops));
    }

    @Override
    public List<ItemStack> getRakeItems() {
        return Lists.newArrayList(new ItemStack(Items.handRake, 1, 0), new ItemStack(Items.handRake, 1, 1));
    }

    @Override
    public List<Block> getCropsBlocks() {
        return Lists.newArrayList((Block) Blocks.blockCrop);
    }

    @Override
    public boolean isNativePlantingDisabled(ItemStack seed) {
        return ConfigurationHandler.disableVanillaFarming;
    }

    @Override
    public boolean isHandledByCropsNH(ItemStack seed) {
        return CropRegistry.isValidSeed(seed);
    }

    @Override
    public void registerCropPlant(ICropPlant plant) {
        CropRegistry.addCropToRegister(new CropPlantAPIv1(plant));
    }

    @Override
    public void registerCropPlant(ICropsNHPlant plant) {
        CropRegistry.addCropToRegister(new CropPlantCropsNH(plant));
    }

    @Override
    public boolean registerGrowthRequirement(ItemWithMeta seed, IGrowthRequirement requirement) {
        return CropRegistry.setGrowthRequirement(seed, requirement);
    }

    @Override
    public boolean registerDefaultSoil(BlockWithMeta soil) {
        return GrowthRequirementHandler.addDefaultSoil(soil);
    }

    @Override
    public IGrowthRequirement getGrowthRequirement(ItemStack seed) {
        if (!CropRegistry.isValidSeed(seed)) {
            return null;
        }
        return CropRegistry.getGrowthRequirement(seed);
    }

    @Override
    public boolean canPlaceCrops(World world, int x, int y, int z, ItemStack crops) {
        if (crops == null || crops.getItem() == null || crops.getItem() != Items.crops) {
            return false;
        } else if (GrowthRequirementHandler.isSoilValid(world, x, y - 1, z) && world.isAirBlock(x, y, z)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean placeCrops(World world, int x, int y, int z, ItemStack crops) {
        if (canPlaceCrops(world, x, y, z, crops) && crops.stackSize >= 1) {
            if (!world.isRemote) {
                world.setBlock(x, y, z, Blocks.blockCrop, 0, 3);
                crops.stackSize--;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isCrops(World world, int x, int y, int z) {
        return world.getBlock(x, y, z) == Blocks.blockCrop;
    }

    @Override
    public boolean isMature(World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityCrop) {
            TileEntityCrop crop = (TileEntityCrop) te;
            return crop.hasPlant() && crop.isMature();
        }
        return false;
    }

    @Override
    public boolean isWeeds(World world, int x, int y, int z) {
        if (!ConfigurationHandler.enableWeeds) {
            return false;
        }
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityCrop) {
            TileEntityCrop crop = (TileEntityCrop) te;
            return crop.hasWeed();
        }
        return false;
    }

    @Override
    public boolean isEmpty(World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityCrop) {
            TileEntityCrop crop = (TileEntityCrop) te;
            return !(crop.isCrossCrop() || crop.hasWeed() || crop.hasPlant());
        }
        return false;
    }

    @Override
    public ItemStack getPlantedSeed(World world, int x, int y, int z) {
        if (!isCrops(world, x, y, z)) {
            return null;
        }
        return ((TileEntityCrop) world.getTileEntity(x, y, z)).getSeedStack();
    }

    @Override
    public Block getPlantedBlock(World world, int x, int y, int z) {
        if (!isCrops(world, x, y, z)) {
            return null;
        }
        return ((TileEntityCrop) world.getTileEntity(x, y, z)).getPlantBlock();
    }

    @Override
    public ICropPlant getCropPlant(World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (!(te instanceof TileEntityCrop)) {
            return null;
        }
        return ((TileEntityCrop) te).getPlant();
    }

    @Override
    public boolean canGrow(World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityCrop) {
            TileEntityCrop crop = (TileEntityCrop) te;
            return crop.hasPlant() && crop.isFertile();
        }
        return false;
    }

    @Override
    public boolean isAnalyzed(World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te == null || !(te instanceof TileEntityCrop)) {
            return false;
        }
        TileEntityCrop crop = (TileEntityCrop) te;
        return crop.hasPlant() && crop.isAnalyzed();
    }

    @Override
    public ISeedStats getStats(World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te == null || !(te instanceof TileEntityCrop)) {
            return new SeedStats(-1, -1, -1);
        }
        TileEntityCrop crop = (TileEntityCrop) te;
        return crop.getStats();
    }

    @Override
    public boolean isRakeRequiredForWeeding() {
        return ConfigurationHandler.enableHandRake;
    }

    @Override
    public boolean removeWeeds(World world, int x, int y, int z, boolean byHand) {
        if (!ConfigurationHandler.enableWeeds || (byHand && ConfigurationHandler.enableHandRake)) {
            return false;
        }
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityCrop) {
            TileEntityCrop crop = (TileEntityCrop) te;
            if (!crop.hasWeed()) {
                return false;
            }
            if (!world.isRemote) {
                crop.updateWeed(0);
            }
            return true;
        }
        return false;
    }

    private static final Random random = new Random();

    @Override
    public boolean removeWeeds(World world, int x, int y, int z, ItemStack rake) {
        if (world.isRemote) {
            return false;
        }
        if (!ConfigurationHandler.enableWeeds) {
            return false;
        }
        if (rake == null || rake.getItem() == null || !(rake.getItem() instanceof IRake)) {
            return false;
        }
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityCrop) {
            TileEntityCrop crop = (TileEntityCrop) te;
            if (!crop.hasWeed()) {
                return false;
            }
            return ((IRake) rake.getItem()).removeWeeds(crop, rake);
        }
        return false;
    }

    @Override
    public boolean placeCrossCrops(World world, int x, int y, int z, ItemStack crops) {
        if (world.isRemote) {
            return false;
        }
        if (crops == null || crops.getItem() == null || crops.getItem() != Items.crops || crops.stackSize < 1) {
            return false;
        }
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityCrop) {
            TileEntityCrop crop = (TileEntityCrop) te;
            if (!crop.hasWeed() && !crop.isCrossCrop() && !crop.hasPlant()) {
                crop.setCrossCrop(true);
                crops.stackSize--;
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack removeCrossCrops(World world, int x, int y, int z) {
        if (world.isRemote) {
            return null;
        }
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityCrop) {
            TileEntityCrop crop = (TileEntityCrop) te;
            if (crop.isCrossCrop()) {
                crop.setCrossCrop(false);
                return new ItemStack(Items.crops, 1);
            }
        }
        return null;
    }

    @Override
    public SeedRequirementStatus canApplySeeds(World world, int x, int y, int z, ItemStack seed) {
        if (CropRegistry.isValidSeed(seed)) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof TileEntityCrop) {
                TileEntityCrop crop = (TileEntityCrop) te;
                if (crop.isCrossCrop() || crop.hasPlant() || crop.hasWeed()) {
                    return SeedRequirementStatus.BAD_LOCATION;
                }
                IGrowthRequirement growthRequirement = CropRegistry.getGrowthRequirement(seed);
                if (!growthRequirement.isValidSoil(world, x, y - 1, z)) {
                    return SeedRequirementStatus.WRONG_SOIL;
                }
                if (!growthRequirement.canGrow(world, x, y, z)) {
                    return SeedRequirementStatus.MISSING_REQUIREMENTS;
                }
                return SeedRequirementStatus.CAN_APPLY;
            } else {
                return SeedRequirementStatus.BAD_LOCATION;
            }
        } else {
            return SeedRequirementStatus.BAD_SEED;
        }
    }

    @Override
    public boolean applySeeds(World world, int x, int y, int z, ItemStack seed) {
        if (!world.isRemote) {
            if (CropRegistry.isValidSeed(seed)) {
                TileEntity te = world.getTileEntity(x, y, z);
                if (te instanceof TileEntityCrop) {
                    TileEntityCrop crop = (TileEntityCrop) te;
                    if (crop.isCrossCrop() || crop.hasPlant()
                        || crop.hasWeed()
                        || !CropRegistry.getGrowthRequirement(seed)
                            .canGrow(world, x, y, z)) {
                        return false;
                    }
                    if (seed.stackTagCompound != null && seed.stackTagCompound.hasKey(Names.NBT.growth)) {
                        crop.setPlant(
                            seed.stackTagCompound.getInteger(Names.NBT.growth),
                            seed.stackTagCompound.getInteger(Names.NBT.gain),
                            seed.stackTagCompound.getInteger(Names.NBT.strength),
                            seed.stackTagCompound.getBoolean(Names.NBT.analyzed),
                            seed.getItem(),
                            seed.getItemDamage());
                    } else {
                        crop.setPlant(
                            Constants.DEFAULT_GROWTH,
                            Constants.DEFAULT_GAIN,
                            Constants.DEFAULT_STRENGTH,
                            false,
                            seed.getItem(),
                            seed.getItemDamage());
                    }
                    seed.stackSize--;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<ItemStack> harvest(World world, int x, int y, int z) {
        if (world.isRemote) {
            return null;
        }
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityCrop) {
            TileEntityCrop crop = (TileEntityCrop) te;
            if (crop.allowHarvest(null)) {
                crop.getWorldObj()
                    .setBlockMetadataWithNotify(crop.xCoord, crop.yCoord, crop.zCoord, 2, 2);
                return crop.getPlant()
                    .getFruitsOnHarvest(crop.getGain(), world.rand);
            }
        }
        return null;
    }

    @Override
    public List<ItemStack> destroy(World world, int x, int y, int z) {
        if (world.isRemote || !isCrops(world, x, y, z)) {
            return null;
        }
        List<ItemStack> result = Blocks.blockCrop.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        world.setBlockToAir(x, y, z);
        world.removeTileEntity(x, y, z);
        return result;
    }

    @Override
    public boolean isSupportedFertilizer(ItemStack fertilizer) {
        if (fertilizer == null || fertilizer.getItem() == null) {
            return false;
        }
        if (fertilizer.getItem() == net.minecraft.init.Items.dye && fertilizer.getItemDamage() == 15) {
            return true;
        }
        if (fertilizer.getItem() instanceof IFertiliser) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidFertilizer(World world, int x, int y, int z, ItemStack fertilizer) {
        if (fertilizer == null || fertilizer.getItem() == null) {
            return false;
        }
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityCrop) {
            TileEntityCrop crop = (TileEntityCrop) te;
            if (fertilizer.getItem() == net.minecraft.init.Items.dye && fertilizer.getItemDamage() == 15) {
                return crop.canBonemeal();
            } else if (fertilizer.getItem() instanceof IFertiliser) {
                return crop.allowFertiliser((IFertiliser) fertilizer.getItem());
            }
        }
        return false;
    }

    @Override
    public boolean applyFertilizer(World world, int x, int y, int z, ItemStack fertilizer) {
        if (world.isRemote || !isValidFertilizer(world, x, y, z, fertilizer)) {
            return false;
        }
        if (fertilizer.getItem() == net.minecraft.init.Items.dye && fertilizer.getItemDamage() == 15) {
            ((BlockCrop) Blocks.blockCrop).func_149853_b(world, random, x, y, z);
            fertilizer.stackSize--;
            world.playAuxSFX(2005, x, y, z, 0);
            return true;
        } else if (fertilizer.getItem() instanceof IFertiliser) {
            ((TileEntityCrop) world.getTileEntity(x, y, z))
                .applyFertiliser((IFertiliser) fertilizer.getItem(), world.rand);
            fertilizer.stackSize--;
            world.playAuxSFX(2005, x, y, z, 0);
            return true;
        }
        return false;
    }

    @Override
    public IMutation[] getRegisteredMutations() {
        return MutationHandler.getInstance()
            .getMutations();
    }

    @Override
    public IMutation[] getRegisteredMutationsForParent(ItemStack parent) {
        return MutationHandler.getInstance()
            .getMutationsFromParent(parent);
    }

    @Override
    public IMutation[] getRegisteredMutationsForChild(ItemStack child) {
        return MutationHandler.getInstance()
            .getMutationsFromChild(child);
    }

    @Override
    public boolean registerMutation(ItemStack result, ItemStack parent1, ItemStack parent2) {
        MutationHandler.getInstance()
            .add(new Mutation(result, parent1, parent2));
        return false;
    }

    @Override
    public boolean registerMutation(ItemStack result, ItemStack parent1, ItemStack parent2, double d) {
        MutationHandler.getInstance()
            .add(new Mutation(result, parent1, parent2, d));
        return true;
    }

    @Override
    public boolean removeMutation(ItemStack result) {
        MutationHandler.getInstance()
            .removeMutationsByResult(result);
        return true;
    }

    @Override
    public ICropsNHPlant createNewCrop(Object... args) {
        try {
            return new BlockModPlant(args);
        } catch (MissingArgumentsException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ICrop getCrop(World world, int x, int y, int z) {
        final TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof ICrop) {
            return (ICrop) te;
        }
        return null;
    }

    @Override
    public void setStatCalculator(IStatCalculator calculator) {
        StatCalculator.setStatCalculator(calculator);
    }

    @Override
    public IMutationHandler getMutationHandler() {
        return MutationHandler.getInstance();
    }

    @Override
    public boolean registerValidSoil(BlockWithMeta soil) {
        GrowthRequirementHandler.addSoil(soil);
        return true;
    }

    @Override
    public short getStatCap() {
        return (short) ConfigurationHandler.cropStatCap;
    }

    @Override
    public ISeedStats getSeedStats(ItemStack seed) {
        if (!isHandledByCropsNH(seed)) {
            return null;
        }
        if (seed.stackTagCompound != null && seed.stackTagCompound.hasKey(Names.NBT.growth)
            && seed.stackTagCompound.getBoolean(Names.NBT.analyzed)) {
            return SeedStats.readFromNBT(seed.stackTagCompound);
        } else {
            return new SeedStats(-1, -1, -1);
        }
    }

    @Override
    public ICropPlant getCropPlant(ItemStack seed) {
        return CropRegistry.getPlantFromStack(seed);
    }

    @Override
    public void analyze(ItemStack seed) {
        if (CropRegistry.isValidSeed(seed)) {
            if (seed.hasTagCompound()) {
                NBTTagCompound tag = seed.getTagCompound();
                String[] keys = { Names.NBT.growth, Names.NBT.gain, Names.NBT.strength };
                for (String key : keys) {
                    if (!tag.hasKey(key)) {
                        tag.setShort(key, (short) 1);
                    }
                }
                tag.setBoolean(Names.NBT.analyzed, true);
            } else {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setShort(Names.NBT.growth, (short) 1);
                tag.setShort(Names.NBT.gain, (short) 1);
                tag.setShort(Names.NBT.strength, (short) 1);
                tag.setBoolean(Names.NBT.analyzed, true);
                seed.setTagCompound(tag);
            }
        }
    }

    @Override
    public IGrowthRequirementBuilder createGrowthRequirementBuilder() {
        return GrowthRequirementHandler.getNewBuilder();
    }

    @Override
    public IGrowthRequirement createDefaultGrowthRequirement() {
        return GrowthRequirementHandler.getNewBuilder()
            .build();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setStatStringDisplayer(IStatStringDisplayer displayer) {
        StatStringDisplayer.setStatStringDisplayer(displayer);
    }

    @Override
    public boolean isSeedDiscoveredInJournal(ItemStack journal, ItemStack seed) {
        if (journal == null || journal.getItem() == null || !(journal.getItem() instanceof IJournal)) {
            return false;
        }
        return ((IJournal) journal.getItem()).isSeedDiscovered(journal, seed);
    }

    @Override
    public void addEntryToJournal(ItemStack journal, ItemStack seed) {
        if (journal == null || journal.getItem() == null || !(journal.getItem() instanceof IJournal)) {
            return;
        }
        ((IJournal) journal.getItem()).addEntry(journal, seed);
    }

    @Override
    public ArrayList<ItemStack> getDiscoveredSeedsFromJournal(ItemStack journal) {
        if (journal == null || journal.getItem() == null || !(journal.getItem() instanceof IJournal)) {
            return new ArrayList<>();
        }
        return ((IJournal) journal.getItem()).getDiscoveredSeeds(journal);
    }

    @Override
    public boolean isSeedBlackListed(ItemStack seed) {
        return CropRegistry.isSeedBlackListed(seed);
    }

    @Override
    public void addToSeedBlackList(ItemStack seed) {
        CropRegistry.addSeedToBlackList(seed);
    }

    @Override
    public void addToSeedBlacklist(Collection<? extends ItemStack> seeds) {
        CropRegistry.addAllToSeedBlacklist(seeds);
    }

    @Override
    public void removeFromSeedBlackList(ItemStack seed) {
        CropRegistry.removeFromSeedBlackList(seed);
    }

    @Override
    public void removeFromSeedBlacklist(Collection<? extends ItemStack> seeds) {
        CropRegistry.removeAllFromSeedBlacklist(seeds);
    }
}
