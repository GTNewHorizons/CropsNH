package com.gtnewhorizon.cropsnh.tileentity;

import com.gtnewhorizon.cropsnh.api.v1.IAdditionalCropData;
import com.gtnewhorizon.cropsnh.api.v1.ICrop;
import com.gtnewhorizon.cropsnh.api.v1.ICrossOverResult;
import com.gtnewhorizon.cropsnh.api.v1.IDebuggable;
import com.gtnewhorizon.cropsnh.api.v1.IFertiliser;
import com.gtnewhorizon.cropsnh.api.v1.ISeedStats;
import com.gtnewhorizon.cropsnh.api.v1.ITrowel;
import com.gtnewhorizon.cropsnh.blocks.BlockCrop;
import com.gtnewhorizon.cropsnh.blocks.BlockModPlant;
import com.gtnewhorizon.cropsnh.compatibility.applecore.AppleCoreHelper;
import com.gtnewhorizon.cropsnh.farming.CropPlantHandler;
import com.gtnewhorizon.cropsnh.farming.PlantStats;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;
import com.gtnewhorizon.cropsnh.farming.mutation.MutationEngine;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.Blocks;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.statstringdisplayer.StatStringDisplayer;

import ic2.api.crops.CropCard;
import ic2.api.crops.ICropTile;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TileEntityCrop extends TileEntityCropsNH implements ICrop, IDebuggable, ICropTile {
    private PlantStats stats = new PlantStats();
    private boolean crossCrop=false;
    private boolean weed=false;
    private CropPlant plant;
    private IAdditionalCropData data;

    private final MutationEngine mutationEngine;

    public TileEntityCrop() {
        this.mutationEngine = new MutationEngine(this);
    }

    @Override
    public boolean isRotatable() {
        return false;
    }

    @Override
    public CropPlant getPlant() {return plant;}

    @Override
    public ISeedStats getStats() {
        return this.hasPlant()?stats.copy():new PlantStats(-1, -1, -1);
    }

    @Override
    public byte getGrowth() {return this.weed ? (byte) ConfigurationHandler.weedGrowthMultiplier : (byte)stats.getGrowth();} //Pardon the cast.

    @Override
    public byte getGain() {return (byte)stats.getGain();}

    @Override
    public byte getStrength() {return (byte)stats.getStrength();}

    @Override
    public boolean isAnalyzed() {return stats.isAnalyzed();}

    @Override
    public boolean hasWeed() {return weed;}

    @Override
    public boolean isCrossCrop() {return crossCrop;}

    @Override
    public void setCrossCrop(boolean status) {
        if(status!=this.crossCrop) {
            this.crossCrop = status;
            if(!worldObj.isRemote && crossCrop) {
                worldObj.playSoundEffect((double)((float) xCoord + 0.5F), (double)((float) yCoord + 0.5F), (double)((float) zCoord + 0.5F), net.minecraft.init.Blocks.planks.stepSound.func_150496_b(), (net.minecraft.init.Blocks.leaves.stepSound.getVolume() + 1.0F) / 2.0F, net.minecraft.init.Blocks.leaves.stepSound.getPitch() * 0.8F);
            }
            this.markForUpdate();
        }
    }

    /** get growthrate */
    public int getGrowthRate() {return this.weed ? ConfigurationHandler.weedGrowthRate : plant.getGrowthRate();}

    /** check to see if there is a plant here */
    @Override
    public boolean hasPlant() {return this.plant!=null;}

    /** check to see if a seed can be planted */
    @Override
    public boolean canPlant() {
        return !this.hasPlant() && !this.hasWeed() && !this.isCrossCrop();
    }

    /** sets the plant in the crop */
    private void setPlant(int growth, int gain, int strength, boolean analyzed, CropPlant plant, BlockModPlant blockModPlant) {
        if( (!this.crossCrop) && (!this.hasPlant())) {
            if(plant!=null) {
                this.plant = plant;
            	this.blockModPlant = blockModPlant;
                this.stats = new PlantStats(growth, gain, strength, analyzed);
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 0, 3);
                plant.onSeedPlanted(worldObj, xCoord, yCoord, zCoord, this);
                IAdditionalCropData data = plant.getInitialCropData(worldObj, xCoord, yCoord, zCoord, this);
                if(data != null) {
                    this.data = data;
                }
                
                
                this.markForUpdate();
            }
        }
    }

    /** sets the plant in the crop */
    @Override
    public void setPlant(int growth, int gain, int strength, boolean analyzed, Item seed, int seedMeta) {
    	ItemStack itemStack = new ItemStack(seed, 1, seedMeta);
        this.setPlant(growth, gain, strength, analyzed, CropPlantHandler.getPlantFromStack(itemStack), BlockModPlant.getByItemStack(itemStack));
        if(plant != null) {
            plant.onSeedPlanted(this.getWorldObj(), xCoord, yCoord, zCoord, this);
        }
    }

    /** clears the plant in the crop */
    @Override
    public void clearPlant() {
        CropPlant oldPlant = getPlant();
        this.stats = new PlantStats();
        this.plant = null;
        this.blockModPlant = null;
        this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 0, 3);
        this.markForUpdate();
        if (oldPlant != null) {
            oldPlant.onPlantRemoved(worldObj, xCoord, yCoord, zCoord, this);
        }
        this.data = null;
    }

    /** check if the crop is fertile */
    @Override
    public boolean isFertile() {
        return this.weed || worldObj.isAirBlock(xCoord, yCoord +1, zCoord) && hasPlant() && plant.getGrowthRequirement().canGrow(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    /** gets the height of the crop */
    @SideOnly(Side.CLIENT)
    public float getCropHeight() {
        return hasPlant()? plant.getHeight(getBlockMetadata()): Constants.UNIT*13;
    }

    /** check if bonemeal can be applied */
    @Override
    public boolean canBonemeal() {
        if(this.crossCrop) {
            return ConfigurationHandler.bonemealMutation;
        }
        if(this.hasPlant() && !this.isMature()) {
            if(!this.isFertile()) {
                return false;
            }
            return plant.canBonemeal();
        }
        if(this.hasWeed() && !this.isMature()) {
        	return true;
        }
        return false;
    }

    /** check the block if the plant is mature (can spread/mutate, 1 stage below max stage but not stage 1) */
    @Override
    public boolean isMature() {
        //
    	if(this.getBlockModPlant() != null && this.getBlockModPlant().growthStages != null && (this.stats.getStage()+1) >= this.getBlockModPlant().growthStages.length-1 && this.stats.getStage() > 1)
    	{
    		return true;
    	}
    	return false;
    }

    /** gets the fruits for this plant */
    public ArrayList<ItemStack> getFruits() {return plant.getFruitsOnHarvest(getGain(), worldObj.rand);}

    /** allow harvesting */
    public boolean allowHarvest(EntityPlayer player) {
    	if(getBlockModPlant() == null) return false;
        return hasPlant() && this.stats.getStage() >= this.getBlockModPlant().harvestStage-1 && plant.onHarvest(worldObj, xCoord, yCoord, zCoord, this, player);
    }

    /** returns an ItemStack holding the seed currently planted, initialized with an NBT tag holding the stats */
    @Override
    public ItemStack getSeedStack() {
        if(plant == null) {
            return null;
        }
        ItemStack seed = plant.getSeed().copy();
        NBTTagCompound tag = new NBTTagCompound();
        CropPlantHandler.setSeedNBT(tag, getGrowth(), getGain(), getStrength(), stats.isAnalyzed());
        seed.setTagCompound(tag);
        return seed;
    }

    /** returns the Block instance of the plant currently planted on the crop */
    @Override
    public Block getPlantBlock() {
        return plant==null?null:plant.getBlock();
    }

    /** spawns weed in the crop */
    @Override
    public void spawnWeed() {
        this.crossCrop=false;
        this.weed=true;
        this.clearPlant();
        this.markForUpdate();
    }

    /** spread the weed */
    @Override
    public void spreadWeed() {
        List<ICrop> neighbours = this.getNeighbours();
        for(ICrop crop : neighbours) {
            if(crop!=null && (!crop.hasWeed()) && Math.random() < this.getWeedSpawnChance(crop)) {
                crop.spawnWeed();
                break;
            }
        }
    }

    @Override
    public void updateWeed(int growthStage) {
        if(this.hasWeed()) {
            growthStage = growthStage>7?7:growthStage<0?0:growthStage;
            if (growthStage == 0) {
                this.weed = false;
            }
            this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, growthStage, 3);
        }
    }

    //clear the weed
    @Override
    public void clearWeed() {updateWeed(0);}

    //weed spawn chance
    private double getWeedSpawnChance(ICrop crop) {
        if(this.hasPlant()) {
            return ConfigurationHandler.weedsWipePlants ? ((double) (10 - crop.getStrength())) / 10 : 0;
        }
        else {
            return crop.hasWeed() ? 0 : 1;
        }
    }

    //trowel usage
    public void onTrowelUsed(ITrowel trowel, ItemStack trowelStack) {
        if(this.hasPlant()) {
            if(!trowel.hasSeed(trowelStack)) {
                trowel.setSeed(trowelStack, this.getSeedStack(), worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
                this.clearPlant();
            }
        } else if(!this.hasWeed() && !this.crossCrop){
            if(trowel.hasSeed(trowelStack)) {
                ItemStack seed = trowel.getSeed(trowelStack);
                int growthStage = trowel.getGrowthStage(trowelStack);
                NBTTagCompound tag = seed.getTagCompound();
                short growth = tag.getShort(Names.NBT.growth);
                short gain = tag.getShort(Names.NBT.gain);
                short strength = tag.getShort(Names.NBT.strength);
                boolean analysed = tag.getBoolean(Names.NBT.analyzed);
                this.setPlant(growth, gain, strength, analysed, seed.getItem(), seed.getItemDamage());
                this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, growthStage, 3);
                trowel.clearSeed(trowelStack);
            }
        }
    }

    //is fertilizer allowed
    @Override
    public boolean allowFertiliser(IFertiliser fertiliser) {
        if(this.crossCrop) {
            return ConfigurationHandler.bonemealMutation && fertiliser.canTriggerMutation();
        }
        if(this.hasWeed()) {
            return true;
        }
        if(this.hasPlant()) {
            return fertiliser.isFertiliserAllowed(plant.getTier());
        }
        return false;
    }

    //when fertiliser is applied
    @Override
    public void applyFertiliser(IFertiliser fertiliser, Random rand) {
        if(fertiliser.hasSpecialBehaviour()) {
            fertiliser.onFertiliserApplied(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord, rand);
        }
        if(this.hasPlant() || this.hasWeed()) {
            ((BlockCrop) Blocks.blockCrop).func_149853_b(this.worldObj, rand, this.xCoord, this.yCoord, this.zCoord);
        }
        else if(this.isCrossCrop() && ConfigurationHandler.bonemealMutation) {
            this.crossOver();
        }
    }

    @Override
    public boolean harvest(@Nullable EntityPlayer player) {
        //return ((BlockCrop) worldObj.getBlock(xCoord, yCoord, zCoord)).harvest(worldObj, xCoord, yCoord, zCoord, player, this);
    	return false;
    }

    @Override
    public TileEntity getTileEntity() {
        return this;
    }

    @Override
    public IAdditionalCropData getAdditionalCropData() {
        return this.data;
    }

    @Override
    public void validate() {
        super.validate();
        if(this.hasPlant()) {
            plant.onValidate(worldObj, xCoord, yCoord, zCoord, this);
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        if(this.hasPlant()) {
            plant.onInvalidate(worldObj, xCoord, yCoord, zCoord, this);
        }
    }

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();
        if(this.hasPlant()) {
            plant.onChunkUnload(worldObj, xCoord, yCoord, zCoord, this);
        }
    }

    //TileEntity is just to store data on the crop
    @Override
    public boolean canUpdate() {
        return true;
    }
    
    @Override
    public void updateEntity()
    {
    	ticks++;
    	if(ticks >= 2)
    	{
    		ticks = 0;
    		if(this.canGrow())
        	{
        		this.grow();
        	}
        	
        	if(this.hasPlant())
        	{
        		stats.setFertilizer(Math.max(0, stats.getFertilizer()-1));
        		stats.setHydration(Math.max(0, stats.getHydration()-1));
        		stats.setWeedEx(Math.max(0, stats.getWeedEx()-1));
        	}
    	}
    	
    	if(!worldObj.isRemote) {
    		if(Math.random() <= 0.01)
        	{
        		if(hasPlant() || hasWeed()) {
                	if (isMature() && hasWeed() && ConfigurationHandler.enableWeeds){
                    	//spreadWeed();
                    }
                } else {
                    //15% chance to spawn weeds
                    if(ConfigurationHandler.enableWeeds && (Math.random() < ConfigurationHandler.weedSpawnChance)) {
                        spawnWeed();
                    }
                    else if(isCrossCrop()) {
                        crossOver();
                    }
                }
        	}
    	}
    }

    private boolean canGrow() {
		if(this.getBlockModPlant() != null && this.getBlockModPlant().growthStages != null && (this.stats.getStage()+1) < this.getBlockModPlant().growthStages.length) return true;
		return false;
	}

	//this saves the data on the tile entity
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        stats.writeToNBT(tag);
        tag.setBoolean(Names.NBT.crossCrop,crossCrop);
        tag.setBoolean(Names.NBT.weed, weed);
        if(this.plant != null) {
            tag.setTag(Names.NBT.seed, CropPlantHandler.writePlantToNBT(plant));
            if(getAdditionalCropData() != null) {
                tag.setTag(Names.NBT.inventory, getAdditionalCropData().writeToNBT());
            }
        }
    }

    //this loads the saved data for the tile entity
    @Override
    @SuppressWarnings("deprecation")
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.stats = PlantStats.readFromNBT(tag);
        this.crossCrop=tag.getBoolean(Names.NBT.crossCrop);
        this.weed=tag.getBoolean(Names.NBT.weed);
        if(tag.hasKey(Names.NBT.seed) && !tag.hasKey(Names.NBT.meta)) {
            this.plant = CropPlantHandler.readPlantFromNBT(tag.getCompoundTag(Names.NBT.seed));
        }
        else {
            this.plant=null;
        }
        if(tag.hasKey(Names.NBT.inventory) && this.plant != null) {
            this.data = plant.readCropDataFromNBT(tag.getCompoundTag(Names.NBT.inventory));
        }
    }

    /** Apply a growth increment */
    public void applyGrowthTick() {
        /*int flag = 2;
        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        if(hasPlant()) {
            flag = plant.onAllowedGrowthTick(worldObj, xCoord, yCoord, zCoord, meta, this) ? 2 : 6;
        }
        if(ConfigurationHandler.renderCropPlantsAsTESR) {
            flag = 6;
        }
        if (hasWeed() || !plant.isMature(worldObj, xCoord, yCoord, zCoord)) {
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta + 1, flag);
            AppleCoreHelper.announceGrowthTick(this.getBlockType(), worldObj, xCoord, yCoord, zCoord);
        }*/
    }

    /** the code that makes the crop cross with neighboring crops */
    public void  crossOver() {mutationEngine.executeCrossOver();}

    /** Called by the mutation engine to apply the result of a cross over */
    public void applyCrossOverResult(ICrossOverResult result, ISeedStats stats) {
        crossCrop = false;
        setPlant(stats.getGrowth(), stats.getGain(), stats.getStrength(), false, result.getSeed(), result.getMeta());
    }

    /**
     * @return a list with all neighbours of type <code>TileEntityCrop</code> in the
     *          NORTH, SOUTH, EAST and WEST direction
     */
    @Override
    public List<ICrop> getNeighbours() {
        List<ICrop> neighbours = new ArrayList<>();
        addNeighbour(neighbours, ForgeDirection.NORTH);
        addNeighbour(neighbours, ForgeDirection.SOUTH);
        addNeighbour(neighbours, ForgeDirection.EAST);
        addNeighbour(neighbours, ForgeDirection.WEST);
        return neighbours;
    }

    private void addNeighbour(List<ICrop> neighbours, ForgeDirection direction) {
        TileEntity te = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
        if (te == null || !(te instanceof TileEntityCrop)) {
            return;
        }
        neighbours.add((TileEntityCrop) te);
    }

    /** @return a list with only mature neighbours of type <code>TileEntityCrop</code> */
    @Override
    public List<ICrop> getMatureNeighbours() {
        List<ICrop> neighbours = getNeighbours();
        neighbours.removeIf(crop -> !crop.hasPlant() || !crop.isMature());
        return neighbours;
    }

    //get the plant icon
    @SideOnly(Side.CLIENT)
    public IIcon getPlantIcon() {
        IIcon icon = null;
        if(this.hasPlant()) {
            //icon = plant.getPlantIcon(this.getBlockMetadata());
        	icon = getBlockModPlant().getIcon(this.stats.getStage());
        }
        else if(this.weed) {
            icon = ((BlockCrop) this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord)).getWeedIcon(this.getBlockMetadata());
        }
        return icon;
    }

    @Override
    public void addDebugInfo(List<String> list) {
        list.add("CROP:");
        if(this.crossCrop) {
            list.add(" - This is a crosscrop");
        }
        else if(this.hasPlant()) {
            list.add(" - This crop has a plant");
            list.add(" - Seed: " + (this.plant.getSeed().getItem()).getUnlocalizedName());
            list.add(" - Seed class: "+this.plant.getSeed().getItem().getClass().getName());
            list.add(" - RegisterName: " + Item.itemRegistry.getNameForObject((this.plant.getSeed().getItem())) + ":" + this.plant.getSeed().getItemDamage());
            list.add(" - Tier: "+plant.getTier());
            list.add(" - Meta: " + this.getBlockMetadata());
            list.add(" - Growth: " + getGrowth());
            list.add(" - Gain: " + getGain());
            list.add(" - Strength: " + getStrength());
            list.add(" - Fertile: " + this.isFertile());
            list.add(" - Mature: " + this.isMature());
        }
        else if(this.weed) {
            list.add(" - This crop has weeds");
            list.add(" - Meta: " + this.getBlockMetadata());
        }
        else {
            list.add(" - This crop has no plant");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addWailaInformation(List information) {
    	if(this.hasPlant()) {
    		//Add the seed name.
    		information.add(StatCollector.translateToLocal("cropsnh_tooltip.seed") + ": " + this.getSeedStack().getDisplayName());
    		//Add the growth.

    		
    		
    		if(getBlockModPlant() != null)
    		{
    			information.add(StatCollector.translateToLocal("cropsnh_tooltip.growthStage")+": " + (this.stats.getStage()+1) + " / " + blockModPlant.growthStages.length);
    			if(this.stats.getStage()+1 < blockModPlant.growthStages.length)
    			{
    				information.add("Growth progress: " + this.stats.getGrowthProgress() + " / " + blockModPlant.growthStages[this.stats.getStage()]);
    			}
    		}
    		
    		//Add the analyzed data.
    		if(this.isAnalyzed()) {
    			information.add(" - Nutrients: " + nutrients + " Humidity: " + humidity + " Air Quality: " + airQuality);
    			information.add(" - Fertilizer: " + stats.getFertilizer() + " Hydration: " + stats.getHydration() + " WeedEx: " + stats.getWeedEx());
    			information.add(" - " + StatCollector.translateToLocal("cropsnh_tooltip.growth") + ": " + StatStringDisplayer.instance().getStatDisplayString(this.getGrowth(), ConfigurationHandler.cropStatCap));
                information.add(" - " + StatCollector.translateToLocal("cropsnh_tooltip.gain") + ": " + StatStringDisplayer.instance().getStatDisplayString(this.getGain(), ConfigurationHandler.cropStatCap));
    			information.add(" - " + StatCollector.translateToLocal("cropsnh_tooltip.strength") + ": " + StatStringDisplayer.instance().getStatDisplayString(this.getStrength(), ConfigurationHandler.cropStatCap));
    		}
    		else {
    			information.add(StatCollector.translateToLocal("cropsnh_tooltip.analyzed"));
    		}
    		//Add the fertility information.
    		information.add(StatCollector.translateToLocal(this.isFertile()?"cropsnh_tooltip.fertile":"cropsnh_tooltip.notFertile"));
    	}
    	else if(this.hasWeed()) {
    		information.add(StatCollector.translateToLocal("cropsnh_tooltip.weeds"));
    	}
    	else {
    		information.add(StatCollector.translateToLocal("cropsnh_tooltip.empty"));
        }
    }
    
    
    
    /*
     * CropsNH
     */
    
    private BlockModPlant blockModPlant;
    
    private int nutrients = 0, humidity = 0, airQuality = 0;
    private int ticks = 0;
    private final int tickRate = 128;
    
    
    private BlockModPlant getBlockModPlant()
    {
    	if(this.blockModPlant != null) return this.blockModPlant;
    	this.blockModPlant = BlockModPlant.getByItemStack(this.getSeedStack());
    	return this.blockModPlant;
    }

	public void harvestManual(World world, int x, int y, int z) {
		ItemStack[] items = harvestAutomated();
        for(ItemStack item : items)
        {
        	EntityItem entityItem = new EntityItem(world, x, y, z, item);
            entityItem.delayBeforeCanPickup = 10;
            world.spawnEntityInWorld(entityItem);
        }
	}
	
	public ItemStack[] harvestAutomated()
	{
		ArrayList<ItemStack> drops = getFruits();
        ArrayList<ItemStack> droppedItems = new ArrayList<ItemStack>();
        
        double dropChance = Math.pow(0.95, this.getBlockModPlant().tier);
        dropChance *= Math.pow(1.03, this.stats.getGain());
        
        while(dropChance > 0.0 && drops.size() > 0)
        {
        	if(ThreadLocalRandom.current().nextDouble() <= dropChance)
        	{
        		int itemNum = ThreadLocalRandom.current().nextInt(drops.size());
        		ItemStack drop = drops.get(itemNum);
        		if(drop==null || drop.getItem()==null) {
                    continue;
                }
        		
        		if(ThreadLocalRandom.current().nextInt(100) <= this.stats.getGain()) drop.stackSize++;
        		
                droppedItems.add(drop);
        	}
        	dropChance -= 1.0;
        }
        
        
        stats.setStage(this.getBlockModPlant().growthStageAfterHarvest-1);
        this.markForUpdate();
        
        return droppedItems.toArray(new ItemStack[0]);
	}
    
	private void updateEnvironmentalStats()
	{
		String soilName = Block.blockRegistry.getNameForObject(this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord));
		
		
		float temperature = worldObj.getBiomeGenForCoords(xCoord, zCoord).temperature;
		nutrients = 10 - (int) (Math.abs(temperature - 1.0f) * 16.0f);
		if(nutrients >= 8) nutrients = 10;
		
		if(soilName.equals("Ztones:cleanDirt") || soilName.equals("RandomThings:fertilizedDirt") || soilName.equals("RandomThings:fertilizedDirt_tilled"))
        {
			nutrients += 3;
        }
		else
		{
			for(int i = 0; i < 3; i++)
			{
				if(Block.blockRegistry.getNameForObject(this.worldObj.getBlock(this.xCoord, this.yCoord - (2+i), this.zCoord)) == "minecraft:dirt")
				{
					nutrients++;
				}
			}
		}
		
		
		float rainfall = worldObj.getBiomeGenForCoords(xCoord, zCoord).rainfall;
		humidity = 10 - (int) (Math.abs(rainfall - 1.0f) * 16.0f);
		if(humidity >= 7) humidity = 10;
		
		if(worldObj.getBlockMetadata(xCoord, yCoord-1, zCoord) >= 7 ||
				soilName.equals("Ztones:cleanDirt") || soilName.equals("RandomThings:fertilizedDirt") || soilName.equals("RandomThings:fertilizedDirt_tilled"))
        {
				humidity += 2;
        }
		
		if (stats.getHydration() >= 5) {
			humidity += 2;
		}
		humidity += (stats.getHydration() + 24) / 25;
		
		
		airQuality = Math.min(4, Math.max(0, (yCoord - 64) / 15));
		if(worldObj.canBlockSeeTheSky(xCoord, yCoord+1, zCoord)) airQuality += 2;
		
		int emptyBlocks = 9;
		
		for(int x = xCoord - 1; x <= xCoord + 1; x++)
		{
			for(int z = zCoord - 1; z <= zCoord + 1; z++)
			{
				if(worldObj.isBlockNormalCubeDefault(x, yCoord, z, false) ||
						worldObj.getTileEntity(x, yCoord, z) instanceof TileEntityCrop)
				{
					emptyBlocks--;
				}
			}
		}
		
		airQuality += emptyBlocks / 2;
	}
	
	private void grow()
	{
		this.updateEnvironmentalStats();
		
		int baseEnvStats = humidity + nutrients + airQuality * 5;
    	int finalEnvStats = baseEnvStats - ((getBlockModPlant().tier - 1) * 4 + stats.getGrowth() + stats.getGain() + stats.getStrength());
		
    	int growth = 6 + stats.getGrowth();
    	if(finalEnvStats >= 0) growth = (int)(growth * (1.0f + (finalEnvStats/100.0f)));
    	if(finalEnvStats < 0) growth = (int)(growth * Math.max(0.25, 1.0f - (finalEnvStats/25.0f)));
		
		this.stats.setGrowthProgress(this.stats.getGrowthProgress()+growth);
    	if(this.stats.getGrowthProgress() > this.getBlockModPlant().growthStages[this.stats.getStage()])
    	{
    		this.stats.setStage(this.stats.getStage()+1);
    		this.stats.setGrowthProgress(0);
    		if(worldObj.isRemote) {
                markForRenderUpdate();
            }
    		this.markDirty();
    	}
	}
	
	public boolean canHarvest()
	{
		return hasPlant() && this.stats.getStage() >= this.getBlockModPlant().harvestStage-1;
	}
	
	public boolean fertilize()
	{
		if(stats.getFertilizer() <= 100)
		{
			stats.setFertilizer(stats.getFertilizer()+100);
			markForUpdate();
			return true;
		}
		return false;
	}
	
	public int hydrate(int maxHydration)
	{
		int toHydrate = Math.min(200 - stats.getHydration(), maxHydration);
		stats.setHydration(stats.getHydration()+toHydrate);
		if(toHydrate > 0) markForUpdate();
		return toHydrate;
	}
	
	public boolean weedEx()
	{
		if(stats.getWeedEx() <= 50)
		{
			stats.setWeedEx(stats.getWeedEx()+50);
			markForUpdate();
			return true;
		}
		return false;
	}

	@Override
	public CropCard getCrop() {
		return new FakeCropCard(this.canGrow(), getBlockModPlant().name, this.canHarvest(), this.weed);
	}

	@Override
	public void setCrop(CropCard cropCard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public short getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setID(short id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSize(byte size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGrowth(byte growth) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGain(byte gain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte getResistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setResistance(byte resistance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte getScanLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setScanLevel(byte scanLevel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NBTTagCompound getCustomData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNutrientStorage() {
		return stats.getFertilizer();
	}

	@Override
	public void setNutrientStorage(int nutrientStorage) {
		stats.setFertilizer(nutrientStorage);
		markForUpdate();
	}

	@Override
	public int getHydrationStorage() {
		return stats.getHydration();
	}

	@Override
	public void setHydrationStorage(int hydrationStorage) {
		stats.setHydration(hydrationStorage);
		markForUpdate();
	}

	@Override
	public int getWeedExStorage() {
		return stats.getWeedEx();
	}

	@Override
	public void setWeedExStorage(int weedExStorage) {
		stats.setWeedEx(weedExStorage);
		markForUpdate();
	}

	@Override
	public byte getHumidity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte getNutrients() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte getAirQuality() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public World getWorld() {
		return this.worldObj;
	}

	@Override
	public ChunkCoordinates getLocation() {
		return new ChunkCoordinates(xCoord, yCoord, zCoord);
	}

	@Override
	public int getLightLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean pick(boolean manual) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean harvest(boolean manual) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack[] harvest_automated(boolean Optimal) {
		//if(Optimal && stats.getStage() != getBlockModPlant().growthStages.length) return null;
		return harvestAutomated();
	}

	@Override
	public void reset() {
		this.clearPlant();
		this.clearWeed();
	}

	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isBlockBelow(Block block) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBlockBelow(String oreDictionaryName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack generateSeeds(CropCard crop, byte growth, byte gain, byte resis, byte scan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack generateSeeds(short plant, byte growth, byte gain, byte resis, byte scan) {
		// TODO Auto-generated method stub
		return null;
	}
}



class FakeCropCard extends CropCard {
	boolean canGrowValue = false;
	String displayNameValue = "";
	boolean canBeHarvestedValue = false;
	boolean isWeedValue = false;
	
	public FakeCropCard(boolean canGrow, String displayName, boolean canBeHarvested, boolean isWeed){
		canGrowValue = canGrow;
		displayNameValue = displayName;
		canBeHarvestedValue = canBeHarvested;
		isWeedValue = isWeed;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int tier() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int stat(int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] attributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int maxSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canGrow(ICropTile crop) {
		return false;
	}

	@Override
	public int getOptimalHavestSize(ICropTile crop) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canBeHarvested(ICropTile crop) {
		return canBeHarvestedValue;
	}

	@Override
	public ItemStack getGain(ICropTile crop) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String displayName()
	{
		return displayNameValue;
	}
	
	@Override
	public boolean isWeed(ICropTile tile)
	{
		return isWeedValue;
	}
}

