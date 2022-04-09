package com.gtnewhorizon.cropsnh.farming.growthrequirement;

import com.gtnewhorizon.cropsnh.api.v1.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.v1.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.api.v1.ISoilContainer;
import com.gtnewhorizon.cropsnh.api.v1.RequirementType;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Encodes all requirements a plant needs to mutate and grow
 * Uses the Builder class inside to construct instances.
 */
public class GrowthRequirement implements IGrowthRequirement {
    GrowthRequirement() {}

    public static final int NEARBY_DEFAULT_RANGE = 4;

    //brightness
    /** Maximum allowed brightness, exclusive **/
    private int maxBrightness = 16;
    /** Minimum allowed brightness, inclusive **/
    private int minBrightness = 0;

    private BlockWithMeta soil = null;

    //block requirement
    private BlockWithMeta requiredBlock = null;
    private boolean oreDict = false;
    private RequirementType requiredType = RequirementType.NONE;

    public List<BlockWithMeta> getSoilBlocks() {
        if(this.requiresSpecificSoil()) {
            List<BlockWithMeta> list = new ArrayList<>();
            list.add(soil);
            return list;
        }
        return GrowthRequirementHandler.defaultSoils;
    }

    public List<BlockWithMeta> getBelowBlocks() {
        List<BlockWithMeta> list = new ArrayList<>();
        if(this.requiredType==RequirementType.BELOW) {
            list.add(requiredBlock);
        }
        return list;
    }

    public List<BlockWithMeta> getNearBlocks() {
        List<BlockWithMeta> list = new ArrayList<>();
        if(this.requiredType==RequirementType.NEARBY) {
            list.add(requiredBlock);
        }
        return list;
    }

    //Methods to check if a seed can grow
    //-----------------------------------
	public boolean canGrow(World world, int x, int y, int z) {
        return this.isValidSoil(world, x, y-1, z) && this.isBrightnessGood(world, x, y, z) && this.isBaseBlockPresent(world, x, y, z);
    }

    @Override
    public boolean isBaseBlockPresent(World world, int x, int y, int z) {
        if(this.requiresBaseBlock()) {
            switch(this.requiredType) {
                case BELOW: return this.isBaseBlockBelow(world, x, y, z);
                case NEARBY: return this.isBaseBlockNear(world, x, y, z);
            }
        }
        return true;
    }

    /** @return true, if the correct base block is below **/
    private boolean isBaseBlockBelow(World world, int x, int y, int z) {
        if(this.requiresBaseBlock() && this.requiredType==RequirementType.BELOW) {
            return this.isBlockAdequate(world, x, y - 2, z) || 
            		this.isBlockAdequate(world, x, y - 3, z) || 
            		this.isBlockAdequate(world, x, y - 4, z);
        }
        return true;
    }

    /** @return true, if the correct base block is below **/
    private boolean isBaseBlockNear(World world, int x, int y, int z) {
        if(this.requiresBaseBlock() && this.requiredType==RequirementType.NEARBY) {
            int range = NEARBY_DEFAULT_RANGE;
            for (int xPos = x - range; xPos <= x + range; xPos++) {
                for (int yPos = y - range; yPos <= y + range; yPos++) {
                    for (int zPos = z - range; zPos <= z + range; zPos++) {
                        if(this.isBlockAdequate(world, xPos, yPos, zPos)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    /** @return true, if this block corresponds to the required block **/
    private boolean isBlockAdequate(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        if(foundationBlocks != null && foundationBlocks.length >= 1)
        {        	
        	for(String foundationName : foundationBlocks)
        	{
        		OreDictionary.getOreNames();
        		if(Block.blockRegistry.getNameForObject(block).equals(foundationName) ||
        				OreDictHelper.isBlockInOredictStrings(block, meta, foundationName))
        		{
        			return true;
        		}
        	}
        	return false;
        }
        return true;
    }

    public boolean isBrightnessGood(World world, int x, int y, int z) {
        int lvl = world.getSavedLightValue(EnumSkyBlock.Block, x, y + 1, z);
        boolean day = world.isDaytime();
        if(day) {
            int light_sky = world.getSavedLightValue(EnumSkyBlock.Sky, x, y + 1, z);
            lvl = Math.max(light_sky, lvl);
        }
        return lvl < this.maxBrightness && lvl >= this.minBrightness;
    }

    @Override
    public boolean isValidSoil(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        BlockWithMeta soil = new BlockWithMeta(block, meta);
        if (block instanceof ISoilContainer) {
            soil = new BlockWithMeta(((ISoilContainer) block).getSoil(world, x, y, z), ((ISoilContainer) block).getSoilMeta(world, x, y, z));
        }
        return isValidSoil(soil);
    }

    @Override
    public boolean isValidSoil(BlockWithMeta soil) {
        if(this.requiresSpecificSoil()) {
            return this.soil.equals(soil);
        } else {
            return GrowthRequirementHandler.defaultSoils.contains(soil);
        }
    }

    public boolean requiresSpecificSoil() {
        return this.soil!=null;
    }

    public boolean requiresBaseBlock() {
        return requiredType != RequirementType.NONE;
    }

    @Override
    public ItemStack requiredBlockAsItemStack() {
        return new ItemStack(requiredBlock.getBlock(), 1, requiredBlock.getMeta());
    }

    @Override
    public RequirementType getRequiredType() {
        return requiredType;
    }

    //Methods to change specific requirements
    //--------------------------------------
    @Override
    public BlockWithMeta getSoil() {return this.soil;}

    @Override
    public void setSoil(BlockWithMeta soil) {
        this.soil = soil;
        GrowthRequirementHandler.addSoil(soil);
    }

    @Override
    public int[] getBrightnessRange() {return new int[] {minBrightness, maxBrightness};}

    @Override
    public void setBrightnessRange(int min, int max) {
        this.minBrightness = min;
        this.maxBrightness = max;
    }

    @Override
    public void setRequiredBlock(BlockWithMeta requiredBlock, RequirementType requirementType, boolean oreDict) {
        this.requiredBlock = requiredBlock;
        this.requiredType = requirementType;
        this.oreDict = oreDict;
    }

    @Override
    public BlockWithMeta getRequiredBlock() {
        return requiredBlock;
    }

    @Override
    public boolean isOreDict() {
        return oreDict;
    }
    
    
    
    /*
     * cropsNH
     */
    
    private String[] foundationBlocks = null;
    
    public GrowthRequirement(String[] foundationBlocks) {
    	this.foundationBlocks = foundationBlocks;
    	requiredType = RequirementType.BELOW;
    }
    
    public GrowthRequirement(String[] foundationBlocks, int minBrightness, int maxBrightness) {
    	this.foundationBlocks = foundationBlocks;
    	requiredType = RequirementType.BELOW;
    	this.minBrightness = minBrightness;
    	this.maxBrightness = maxBrightness;
    }
    
    public ItemStack[] requiredBlocksAsItemStacks()
    {
    	Vector<ItemStack> itemStacks = new Vector<ItemStack>();
    	for(String requiredBlock : foundationBlocks)
    	{
    		Item itemType = (Item)Item.itemRegistry.getObject(requiredBlock);
        	if(itemType!= null)
        	{
        		ItemStack item = new ItemStack(itemType, 1);
            	if(item != null)
            	{
            		itemStacks.add(item);
            	}
        	}
        	
        	itemStacks.addAll(OreDictionary.getOres(requiredBlock));        	
    	}
    	return itemStacks.toArray(new ItemStack[0]);
    }

}
