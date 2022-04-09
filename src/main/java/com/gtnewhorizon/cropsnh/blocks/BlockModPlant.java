package com.gtnewhorizon.cropsnh.blocks;

import com.gtnewhorizon.cropsnh.api.v1.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.v1.ICropsNHPlant;
import com.gtnewhorizon.cropsnh.api.v1.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.api.v1.IGrowthRequirementBuilder;
import com.gtnewhorizon.cropsnh.api.v1.RenderMethod;
import com.gtnewhorizon.cropsnh.api.v1.RequirementType;
import com.gtnewhorizon.cropsnh.compatibility.applecore.AppleCoreHelper;
import com.gtnewhorizon.cropsnh.farming.CropPlantHandler;
import com.gtnewhorizon.cropsnh.farming.CropProduce;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlantCropsNHShearable;
import com.gtnewhorizon.cropsnh.farming.growthrequirement.GrowthRequirement;
import com.gtnewhorizon.cropsnh.farming.growthrequirement.GrowthRequirementHandler;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.items.ItemModSeed;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.RegisterHelper;
import com.gtnewhorizon.cropsnh.utility.exception.MissingArgumentsException;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.IResource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class BlockModPlant extends BlockCrops implements ICropsNHPlant {
    private final IGrowthRequirement growthRequirement;
    public CropProduce products = new CropProduce();
    private final ItemModSeed seed;
    public int tier;
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;
    private final RenderMethod renderType;

    /** Parameters can be given in any order, parameters can be: @param args:
     * Arguments can be given in any order, parameters can be:
     *               String name (needed)
     *               ItemStack fruit(needed)
     *               BlockWithMeta soil (optional, pass this argument before the RequirementType, else it will be interpreted as a baseblock)
     *               RequirementType (optional)
     *               BlockWithMeta baseBlock (optional, only if a RequirementType is specified first, else it will be set a a soil)
     *               int tier (necessary)
     *               RenderMethod renderType (necessary)
     *               ItemStack shearDrop (optional, first ItemStack argument will be the regular fruit, second ItemStack argument is the shear drop)
     *               int[] brightness (optional, if not given it will default to {8, 16}. Only works if the array is size 2: {minBrightness, maxBrightness})
     * Will throw MissingArgumentsException if the needed arguments are not given.
     * This constructor creates the seed for this plant which can be gotten via blockModPlant.getSeed().
     * This constructor also registers this block and the item for the seed to the minecraft item/block registry and to the CropsNH CropPlantHandler.
     * */
    

    @Override
    public ItemModSeed getSeed() {return this.seed;}

    @Override
    public Block getBlock() {
        return this;
    }

    @Override
    public ItemStack getSeedStack(int amount) {
        return new ItemStack(this.seed, amount);
    }

    @Override
    public ArrayList<ItemStack> getAllFruits() {return this.products.getAllProducts();}

    @Override
    public ItemStack getRandomFruit(Random rand) {
        ArrayList<ItemStack> fruits = this.getFruit(1, rand);
        return fruits.size()>0?fruits.get(0):null;
    }

    @Override
    public ArrayList<ItemStack> getFruit(int nr, Random rand) {return this.products.getProduce(nr, rand);}

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int meta) {
        return this.getIcon(0, meta);
    }

    @Override
    public boolean renderAsFlower() {
        return this.renderType==RenderMethod.CROSSED;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Crop;
    }

    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean isRemote) {
        return this.tier<=3 && super.func_149851_a(world, x, y, z, isRemote) && this.isFertile(world, x, y, z);
    }

    //register icons
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        //LogHelper.debug("registering icon for: " + this.getUnlocalizedName());
        this.icons = new IIcon[this.growthStages.length];
        for(int i=0;i<this.growthStages.length;i++) {
        	Vector<ResourceLocation> locations = new Vector<ResourceLocation>();
        	String fileName = "blockCrop."+this.name+"."+(i+1);
        	
        	locations.add(new ResourceLocation("cropsnh", "textures/blocks/"+fileName+".png"));
        	locations.add(new ResourceLocation("ic2", "textures/blocks/crop/"+this.name+"."+(i+1)+".png"));
        	locations.add(new ResourceLocation("bpp", "textures/blocks/crop/"+fileName+".png"));
        	locations.add(new ResourceLocation("gregtech", "textures/blocks/crop/"+fileName+".png"));
        	locations.add(new ResourceLocation("ic2", "textures/blocks/crop/"+fileName+".png"));
        	
        	for(int j = 0; j < locations.size(); j++)
        	{
        		try {
            		IResource res = Minecraft.getMinecraft().getResourceManager().getResource(locations.get(j));
    				if(res != null)
    				{
    					String resPath = locations.get(j).getResourcePath();
    					String resName = resPath.substring(resPath.lastIndexOf("blocks/")+7, resPath.lastIndexOf("."));
    					
    					this.icons[i] = reg.registerIcon(locations.get(j).getResourceDomain()+":"+resName);
    					break;
    				}
    			} catch (IOException e) {
    				//e.printStackTrace();
    			}
        	}
        	
        	if(this.icons[i] == null) this.icons[i] = reg.registerIcon("cropsnh:cropBlock.default."+(i+1));
        }
    }

    //growing
    @Override
    public void updateTick(World world, int x, int y, int z, Random rnd) {
        /*int meta = this.getPlantMetadata(world, x, y, z);
        if (meta < Constants.MATURE && this.isFertile(world, x, y ,z)) {
            //Base growth rate
            int growthRate = (tier > 0 && tier <= Constants.GROWTH_TIER.length)?Constants.GROWTH_TIER[tier]:Constants.GROWTH_TIER[0];
            //Bonus for growth stat (because these crops are not planted on crop sticks, growth of 1 is applied)
            double bonus = 1.0 + (1 + 0.00) / 10;
            //Global multiplier as defined in the config
            float global = ConfigurationHandler.growthMultiplier;
            int newMeta = (rnd.nextDouble() > (growthRate * bonus * global) / 100) ? meta : meta + 1;
            if (newMeta != meta) {
                world.setBlockMetadataWithNotify(x, y, z, newMeta, 2);
                AppleCoreHelper.announceGrowthTick(this, world, x, y, z);
            }
        }*/
    }

    //check if the plant is mature
    public boolean isMature(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z) >= Constants.MATURE;
    }

    //render different stages
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        /*switch(meta) {
            case 0:
            case 1: return this.icons[0];
            case 2:
            case 3:
            case 4: return this.icons[1];
            case 5:
            case 6: return this.icons[2];
            case 7: return this.icons[3];
        }
        return this.icons[meta/5];*/
    	if(meta < this.icons.length && meta >= 0) return this.icons[meta];
    	return this.icons[0];
    }

    //item drops
    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float f, int i) {
        super.dropBlockAsItemWithChance(world, x, y, z, meta, f, 0);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(this.seed, 1, 0));
        if(metadata==7) {
            list.add(this.getRandomFruit(world.rand));
        }
        return list;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int side) {
        return meta >= Constants.MATURE ? this.func_149865_P() : this.func_149866_i();
    }

    //fruit gain
    @Override
    public int quantityDropped(Random rand) {
        return 1;
    }

    //neighboring blocks get updated
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        //check if crops can stay
        if(!this.canBlockStay(world,x,y,z)) {
            //the crop will be destroyed
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    //see if the block can stay
    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return (this.growthRequirement.isValidSoil(world, x, y-1, z));
    }

    //check if the plant can grow
    @Override
    public boolean isFertile(World world, int x, int y, int z) {
        return this.growthRequirement.canGrow(world, x, y, z);
    }

    //return the seeds
    @Override
    protected Item func_149866_i() {
        return this.seed;
    }

    //return the fruit
    @Override
    protected Item func_149865_P() {
        Item randomFruit = this.getRandomFruit(new Random()).getItem();
        return randomFruit==null?null:randomFruit;
    }

    @Override
    public int getRenderType() {
        return this.renderType.renderId();
    }

    @Override
    public IGrowthRequirement getGrowthRequirement() {
        return growthRequirement;
    }
    
    
    
    
    /*
     * CropsNH
     */
    
    public static Vector<BlockModPlant> plants = new Vector<BlockModPlant>();
    
    
    public String name;
    public int[] growthStages;
    public int growthStageAfterHarvest;
    public int harvestStage;
    public ItemStack alternateSeed;

    public BlockModPlant(String name, String[] drops, int[] dropMetas, String[] foundationBlocks, int[] growthStages, int harvestStage, int growthStageAfterHarvest, int tier, int minBrightness, int maxBrightness)
    {
        ItemStack shearable = null;
    	
        this.tier = tier;
        this.growthStages = growthStages;
        this.harvestStage = harvestStage;
        this.growthStageAfterHarvest = growthStageAfterHarvest;
        
        this.name = name;
        for(int i = 0; i < drops.length; i++)
        {
        	//ItemStack item = OreDictHelper.getFirstOredictItem(itemName);
        	//ItemStack = Block.blockRegistry.getObject(itemName);
        	Item itemType = (Item)Item.itemRegistry.getObject(drops[i]);
        	if(itemType == null) continue;
        	ItemStack item = new ItemStack(itemType, 1, dropMetas[i]);
        	if(item != null)
        	{
        		this.products.addProduce(item);
        	}
        }
        
        this.growthRequirement = new GrowthRequirement(foundationBlocks);
        //this.growthRequirement = GrowthRequirementHandler.getNewBuilder().build();

        this.setTickRandomly(true);
        this.useNeighborBrightness = true;
        this.renderType = RenderMethod.HASHTAG;
        
        
        //RegisterHelper.registerCrop(this, name);
        //RegisterHelper.registerBlock(this, Names.Objects.crop + name);
        //this.seed = new ItemModSeed(this, "cropsnh_journal."+Character.toLowerCase(name.charAt(0))+name.substring(1));
        //this.plants.add(this);
        
      //register this plant
        RegisterHelper.registerCrop(this, name);
        //create seed for this plant
        this.seed = new ItemModSeed(this, "cropsnh_journal."+Character.toLowerCase(name.charAt(0))+name.substring(1));
        plants.add(this);
        //register this plant to the CropPlantHandler
        try {
            if(shearable == null) {
                CropPlantHandler.registerPlant(this);
            } else {
                CropPlantHandler.registerPlant(new CropPlantCropsNHShearable(this, shearable));
            }
        } catch (Exception e) {
            LogHelper.printStackTrace(e);
        }
    }
    
    public BlockModPlant(String name, String[] drops, int[] dropMetas, String[] foundationBlocks, int[] growthStages, int harvestStage, int growthStageAfterHarvest, int tier)
    {
    	this(name, drops, dropMetas, foundationBlocks, growthStages, harvestStage, growthStageAfterHarvest, tier, 0, 16);
    }
    
    public static BlockModPlant getByItemStack(ItemStack seed) {
    	if(seed == null) return null;
    	for(BlockModPlant plant : plants)
    	{
    		ItemStack plantSeed = plant.getSeedStack(1);
    		if(seed.getItem().getUnlocalizedName().equals(plantSeed.getItem().getUnlocalizedName()) )
			{
				return plant;
			}
    	}
    	return null;
    }
    
    public void setAlternateSeed(String alternateSeedName, int meta)
    {
    	Item item = (Item)Item.itemRegistry.getObject(alternateSeedName);
    	setAlternateSeed(item, meta);
    }
    
    public void setAlternateSeed(Item item, int meta)
    {
    	if(item == null) return;
    	ItemStack itemStack = new ItemStack(item, 1, meta);
    	
    	if(itemStack == null) return;
    	
    	this.alternateSeed = itemStack;
    }
    
    public static boolean itemIsAlternateSeed(ItemStack item)
    {
    	return getAlternateSeedPlant(item.getItem(), item.getItemDamage()) != null;
    }
    
    public static BlockModPlant getAlternateSeedPlant(Item seed, int meta)
    {
    	for(BlockModPlant plant : plants)
    	{
    		if(plant.alternateSeed != null)
    		{
    			if(plant.alternateSeed.getItem() == seed && plant.alternateSeed.getItemDamage() == meta)
    			{
    				return plant;
    			}
    		}
    	}
    	return null;
    }
}