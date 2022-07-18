package com.gtnewhorizon.cropsnh.init;

import com.gtnewhorizon.cropsnh.api.v1.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.v1.RenderMethod;
import com.gtnewhorizon.cropsnh.api.v1.RequirementType;
import com.gtnewhorizon.cropsnh.blocks.BlockModPlant;
import com.gtnewhorizon.cropsnh.farming.CropPlantHandler;
import com.gtnewhorizon.cropsnh.farming.mutation.Mutation;
import com.gtnewhorizon.cropsnh.farming.mutation.MutationHandler;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.items.ItemModSeed;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;
import com.gtnewhorizon.cropsnh.utility.RegisterHelper;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class ResourceCrops {
    //Resource crops
    public static ArrayList<BlockModPlant> vanillaCrops = new ArrayList<>();
    public static ArrayList<ItemModSeed> vanillaSeeds = new ArrayList<>();

    //Metal crops
    public static ArrayList<BlockModPlant> modCrops = new ArrayList<>();
    public static ArrayList<ItemModSeed> modSeeds = new ArrayList<>();

    public static void init() {
    	OreDictHelper.getRegisteredOres();
        
    	
    	BlockModPlant Hemp = new BlockModPlant("Hemp", new String[] {"miscutils:itemRope"}, new int[] {0}, new String[] {}, new int[] {550, 550, 550}, 2, 1, 2);
    	BlockModPlant Force = new BlockModPlant("Force", new String[] {"miscutils:itemNuggetForce"}, new int[] {0}, new String[] {}, new int[] {800, 800, 800}, 2, 1, 4);
    	BlockModPlant saltroot = new BlockModPlant("saltroot", new String[] {"GoodGenerator:saltyRoot"}, new int[] {0}, new String[] {}, new int[] {800, 800, 800}, 2, 1, 4);
    	BlockModPlant stagnium = new BlockModPlant("stagnium", new String[] {"IC2:itemDustSmall"}, new int[] {3}, new String[] {}, new int[] {800, 800, 2000, 800}, 3, 2, 6);
    	BlockModPlant redwheat = new BlockModPlant("redwheat", new String[] {"minecraft:redstone"}, new int[] {0}, new String[] {}, new int[] {600, 600, 600, 600, 600, 600, 600}, 6, 2, 6);
    	BlockModPlant dandelion = new BlockModPlant("dandelion", new String[] {"minecraft:dye"}, new int[] {11}, new String[] {}, new int[] {400, 400, 600, 400}, 3, 3, 2);
    	BlockModPlant venomilia = new BlockModPlant("venomilia", new String[] {"minecraft:dye"}, new int[] {5}, new String[] {}, new int[] {400, 400, 600, 600, 600, 600}, 3, 3, 3);
    	BlockModPlant potato = new BlockModPlant("potato", new String[] {"minecraft:poisonous_potato"}, new int[] {0}, new String[] {}, new int[] {400, 400, 400, 400}, 2, 1, 2);
    	BlockModPlant eatingplant = new BlockModPlant("eatingplant", new String[] {"minecraft:cactus"}, new int[] {0}, new String[] {}, new int[] {750, 750, 750, 750, 750, 750}, 3, 1, 6);
    	BlockModPlant blackthorn = new BlockModPlant("blackthorn", new String[] {"minecraft:dye"}, new int[] {0}, new String[] {}, new int[] {400, 400, 600, 400}, 3, 3, 2);
    	BlockModPlant weed = new BlockModPlant("weed", new String[] {""}, new int[] {}, new String[] {}, new int[] {300, 300, 300, 300, 300}, 99, 1, 0);
    	BlockModPlant wheat = new BlockModPlant("wheat", new String[] {"minecraft:wheat"}, new int[] {0}, new String[] {}, new int[] {200, 200, 200, 200, 200, 200, 200}, 6, 2, 1);
    	BlockModPlant carrots = new BlockModPlant("carrots", new String[] {"minecraft:carrot"}, new int[] {0}, new String[] {}, new int[] {400, 400, 400}, 2, 1, 2);
    	BlockModPlant ferru = new BlockModPlant("ferru", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32503}, new String[] {}, new int[] {800, 800, 2000, 800}, 3, 2, 6);
    	BlockModPlant terraWart = new BlockModPlant("terraWart", new String[] {"IC2:itemTerraWart"}, new int[] {0}, new String[] {}, new int[] {1000, 1000, 1000}, 2, 1, 5);
    	BlockModPlant melon = new BlockModPlant("melon", new String[] {"minecraft:melon"}, new int[] {0}, new String[] {}, new int[] {250, 250, 700, 250}, 3, 3, 2);
    	BlockModPlant stickreed = new BlockModPlant("stickreed", new String[] {"IC2:itemHarz"}, new int[] {0}, new String[] {}, new int[] {100, 100, 100, 400}, 1, 1, 4);
    	BlockModPlant plumbiscus = new BlockModPlant("plumbiscus", new String[] {"IC2:itemDustSmall"}, new int[] {5}, new String[] {}, new int[] {800, 800, 2000, 800}, 3, 2, 6);
    	BlockModPlant netherWart = new BlockModPlant("netherWart", new String[] {"minecraft:nether_wart"}, new int[] {0}, new String[] {}, new int[] {1000, 1000, 1000}, 2, 1, 5);
    	BlockModPlant aurelia = new BlockModPlant("aurelia", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32504}, new String[] {}, new int[] {750, 750, 750, 2200, 750}, 4, 2, 8);
    	BlockModPlant redMushroom = new BlockModPlant("redMushroom", new String[] {""}, new int[] {}, new String[] {}, new int[] {200, 200, 200}, 2, 1, 2);
    	BlockModPlant reed = new BlockModPlant("reed", new String[] {"minecraft:reeds"}, new int[] {0}, new String[] {}, new int[] {200, 200, 200}, 1, 1, 2);
    	BlockModPlant cyprium = new BlockModPlant("cyprium", new String[] {"IC2:itemDustSmall"}, new int[] {1}, new String[] {}, new int[] {800, 800, 2000, 800}, 3, 2, 6);
    	BlockModPlant tulip = new BlockModPlant("tulip", new String[] {"minecraft:dye"}, new int[] {5}, new String[] {}, new int[] {400, 400, 600, 400}, 3, 3, 2);
    	BlockModPlant cocoa = new BlockModPlant("cocoa", new String[] {"minecraft:dye"}, new int[] {3}, new String[] {}, new int[] {400, 400, 900, 400}, 3, 3, 3);
    	BlockModPlant cyazint = new BlockModPlant("cyazint", new String[] {"minecraft:dye"}, new int[] {6}, new String[] {}, new int[] {400, 400, 600, 400}, 3, 3, 2);
    	BlockModPlant brownMushroom = new BlockModPlant("brownMushroom", new String[] {""}, new int[] {}, new String[] {}, new int[] {200, 200, 200}, 2, 1, 2);
    	BlockModPlant coffee = new BlockModPlant("coffee", new String[] {"IC2:itemCofeeBeans"}, new int[] {0}, new String[] {}, new int[] {1400, 1400, 700, 2100, 1400}, 3, 3, 7);
    	BlockModPlant shining = new BlockModPlant("shining", new String[] {"IC2:itemDustSmall"}, new int[] {4}, new String[] {}, new int[] {750, 750, 750, 2200, 750}, 4, 2, 8);
    	BlockModPlant rose = new BlockModPlant("rose", new String[] {"minecraft:dye"}, new int[] {1}, new String[] {}, new int[] {400, 400, 600, 400}, 3, 3, 2);
    	BlockModPlant hops = new BlockModPlant("hops", new String[] {"IC2:itemHops"}, new int[] {0}, new String[] {}, new int[] {600, 600, 600, 600, 600, 600, 600}, 6, 3, 5);
    	BlockModPlant pumpkin = new BlockModPlant("pumpkin", new String[] {"minecraft:pumpkin"}, new int[] {0}, new String[] {}, new int[] {200, 200, 600, 200}, 3, 3, 1);
    	BlockModPlant Slimeplant = new BlockModPlant("Slimeplant", new String[] {"minecraft:slime_ball"}, new int[] {0}, new String[] {}, new int[] {1200, 1200, 1200, 1200}, 3, 3, 6);
    	BlockModPlant Tearstalks = new BlockModPlant("Tearstalks", new String[] {"minecraft:ghast_tear"}, new int[] {0}, new String[] {}, new int[] {1600, 1600, 1600, 1600}, 3, 1, 8);
    	BlockModPlant GodOfThunder = new BlockModPlant("God of Thunder", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32526}, new String[] {}, new int[] {1800, 1800, 1800, 1800}, 3, 2, 9);
    	BlockModPlant Tomato = new BlockModPlant("Tomato", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32552}, new String[] {}, new int[] {800, 800, 800, 800}, 3, 1, 4);
    	BlockModPlant Lemon = new BlockModPlant("Lemon", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32551}, new String[] {}, new int[] {800, 800, 800, 800}, 3, 1, 4);
    	BlockModPlant Diareed = new BlockModPlant("Diareed", new String[] {"gregtech:gt.metaitem.01"}, new int[] {500}, new String[] {}, new int[] {2400, 2400, 2400, 2400}, 3, 1, 12);
    	BlockModPlant Meatrose = new BlockModPlant("Meatrose", new String[] {"minecraft:dye"}, new int[] {9}, new String[] {}, new int[] {1400, 1400, 1400, 1400}, 3, 1, 7);
    	BlockModPlant Plumbilia = new BlockModPlant("Plumbilia", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32500}, new String[] {}, new int[] {1200, 1200, 1200, 1200}, 3, 3, 6);
    	BlockModPlant Corpseplant = new BlockModPlant("Corpseplant", new String[] {"minecraft:rotten_flesh"}, new int[] {0}, new String[] {}, new int[] {1000, 1000, 1000, 1000}, 3, 1, 5);
    	BlockModPlant Reactoria = new BlockModPlant("Reactoria", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32523}, new String[] {}, new int[] {2400, 2400, 2400, 2400}, 3, 2, 12);
    	BlockModPlant Glowheat = new BlockModPlant("Glowheat", new String[] {"gregtech:gt.metaitem.01"}, new int[] {811}, new String[] {}, new int[] {2000, 2000, 2000, 2000, 2000, 2000, 2000}, 6, 5, 10);
    	BlockModPlant Rape = new BlockModPlant("Rape", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32557}, new String[] {}, new int[] {800, 800, 800, 800}, 3, 1, 4);
    	BlockModPlant Onion = new BlockModPlant("Onion", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32555}, new String[] {}, new int[] {800, 800, 800, 800}, 3, 1, 4);
    	BlockModPlant Starwart = new BlockModPlant("Starwart", new String[] {"gregtech:gt.metaitem.01"}, new int[] {1535}, new String[] {}, new int[] {2400, 2400, 2400, 2400}, 3, 1, 12);
    	BlockModPlant EvilOre = new BlockModPlant("Evil Ore", new String[] {"gregtech:gt.metaitem.01"}, new int[] {522}, new String[] {}, new int[] {1600, 1600, 1600, 1600}, 3, 3, 8);
    	BlockModPlant Tine = new BlockModPlant("Tine", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32540}, new String[] {}, new int[] {1000, 1000, 1000}, 2, 2, 5);
    	BlockModPlant Galvania = new BlockModPlant("Galvania", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32528}, new String[] {}, new int[] {1200, 1200, 1200}, 2, 2, 6);
    	BlockModPlant Micadia = new BlockModPlant("Micadia", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32538}, new String[] {}, new int[] {1800, 1800, 1800}, 2, 2, 9);
    	BlockModPlant Grape = new BlockModPlant("Grape", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32554}, new String[] {}, new int[] {800, 800, 800, 800}, 3, 1, 4);
    	BlockModPlant Lazulia = new BlockModPlant("Lazulia", new String[] {"gregtech:gt.metaitem.01"}, new int[] {526}, new String[] {}, new int[] {1400, 1400, 1400, 1400}, 3, 2, 7);
    	BlockModPlant Indigo = new BlockModPlant("Indigo", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32502}, new String[] {}, new int[] {400, 400, 400, 400}, 3, 1, 2);
    	BlockModPlant Flax = new BlockModPlant("Flax", new String[] {"minecraft:string"}, new int[] {0}, new String[] {}, new int[] {400, 400, 400, 400}, 3, 1, 2);
    	BlockModPlant Corium = new BlockModPlant("Corium", new String[] {"minecraft:leather"}, new int[] {0}, new String[] {}, new int[] {1200, 1200, 1200, 1200}, 3, 1, 6);
    	BlockModPlant Cucumber = new BlockModPlant("Cucumber", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32556}, new String[] {}, new int[] {800, 800, 800, 800}, 3, 1, 4);
    	BlockModPlant Quantaria = new BlockModPlant("Quantaria", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32534}, new String[] {}, new int[] {2400, 2400, 2400, 2400}, 3, 1, 12);
    	BlockModPlant Trollplant = new BlockModPlant("Trollplant", new String[] {"gregtech:gt.metaitem.01"}, new int[] {8512}, new String[] {}, new int[] {1200, 1200, 1200, 1200, 1200}, 3, 1, 6);
    	BlockModPlant Enderbloom = new BlockModPlant("Enderbloom", new String[] {"gregtech:gt.metaitem.01"}, new int[] {2532}, new String[] {}, new int[] {2000, 2000, 2000, 2000}, 3, 1, 10);
    	BlockModPlant Milkwart = new BlockModPlant("Milkwart", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32520}, new String[] {}, new int[] {1200, 1200, 1200}, 2, 1, 6);
    	BlockModPlant Steeleafranks = new BlockModPlant("Steeleafranks", new String[] {"gregtech:gt.metaitem.01"}, new int[] {2339}, new String[] {}, new int[] {2000, 2000, 2000, 2000}, 3, 1, 10);
    	BlockModPlant Pyrolusium = new BlockModPlant("Pyrolusium", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32529}, new String[] {}, new int[] {2400, 2400, 2400}, 2, 2, 12);
    	BlockModPlant Chilly = new BlockModPlant("Chilly", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32550}, new String[] {}, new int[] {800, 800, 800, 800}, 3, 1, 4);
    	BlockModPlant Creeperweed = new BlockModPlant("Creeperweed", new String[] {"minecraft:gunpowder"}, new int[] {0}, new String[] {}, new int[] {1400, 1400, 1400, 1400}, 3, 1, 7);
    	BlockModPlant Titania = new BlockModPlant("Titania", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32522}, new String[] {}, new int[] {1800, 1800, 1800}, 2, 2, 9);
    	BlockModPlant Coppon = new BlockModPlant("Coppon", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32530}, new String[] {}, new int[] {1200, 1200, 1200}, 2, 2, 6);
    	BlockModPlant Eggplant = new BlockModPlant("Eggplant", new String[] {"minecraft:egg"}, new int[] {0}, new String[] {}, new int[] {1200, 1200, 1200}, 2, 2, 6);
    	BlockModPlant Transformium = new BlockModPlant("Transformium", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32513}, new String[] {}, new int[] {2400, 2400, 2400, 2400}, 3, 1, 12);
    	BlockModPlant Fertilia = new BlockModPlant("Fertilia", new String[] {"gregtech:gt.metaitem.01"}, new int[] {823}, new String[] {}, new int[] {600, 600, 600, 600}, 3, 1, 3);
    	BlockModPlant Liveroots = new BlockModPlant("Liveroots", new String[] {"gregtech:gt.metaitem.01"}, new int[] {2832}, new String[] {}, new int[] {1600, 1600, 1600, 1600}, 3, 1, 8);
    	BlockModPlant Stargatium = new BlockModPlant("Stargatium", new String[] {"gregtech:gt.metaitem.01"}, new int[] {808}, new String[] {}, new int[] {2400, 2400, 2400, 2400}, 3, 1, 12);
    	BlockModPlant Scheelinium = new BlockModPlant("Scheelinium", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32531}, new String[] {}, new int[] {2400, 2400, 2400}, 2, 2, 12);
    	BlockModPlant Platina = new BlockModPlant("Platina", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32532}, new String[] {}, new int[] {2200, 2200, 2200, 2200}, 3, 1, 11);
    	BlockModPlant Blazereed = new BlockModPlant("Blazereed", new String[] {"minecraft:blaze_powder"}, new int[] {0}, new String[] {}, new int[] {1200, 1200, 1200, 1200}, 3, 1, 6);
    	BlockModPlant Zomplant = new BlockModPlant("Zomplant", new String[] {"minecraft:rotten_flesh"}, new int[] {0}, new String[] {}, new int[] {600, 600, 600, 600}, 3, 1, 3);
    	BlockModPlant Nickelback = new BlockModPlant("Nickelback", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32527}, new String[] {}, new int[] {1000, 1000, 1000}, 2, 2, 5);
    	BlockModPlant Bobsyeruncleranks = new BlockModPlant("Bobsyeruncleranks", new String[] {"minecraft:emerald"}, new int[] {0}, new String[] {}, new int[] {2200, 2200, 2200, 2200}, 3, 1, 11);
    	BlockModPlant Oilberries = new BlockModPlant("Oilberries", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32510}, new String[] {}, new int[] {1800, 1800, 1800, 1800}, 3, 1, 9);
    	BlockModPlant Olivia = new BlockModPlant("Olivia", new String[] {"gregtech:gt.metaitem.01"}, new int[] {8505}, new String[] {}, new int[] {400, 400, 400, 400}, 3, 3, 2);
    	BlockModPlant Tea = new BlockModPlant("Tea", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32505}, new String[] {}, new int[] {800, 800, 800, 800}, 3, 1, 4);
    	BlockModPlant Argentia = new BlockModPlant("Argentia", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32501}, new String[] {}, new int[] {1400, 1400, 1400, 1400}, 3, 3, 7);
    	BlockModPlant Bauxia = new BlockModPlant("Bauxia", new String[] {"gregtech:gt.metaitem.02"}, new int[] {32521}, new String[] {}, new int[] {1200, 1200, 1200}, 2, 2, 6);
    	BlockModPlant BrownMushrooms = new BlockModPlant("Brown Mushrooms", new String[] {"minecraft:brown_mushroom"}, new int[] {0}, new String[] {}, new int[] {200, 200, 200}, 2, 1, 1);
    	BlockModPlant Spidernip = new BlockModPlant("Spidernip", new String[] {"minecraft:web"}, new int[] {0}, new String[] {}, new int[] {800, 800, 800, 800}, 3, 1, 4);
    	BlockModPlant RedMushrooms = new BlockModPlant("Red Mushrooms", new String[] {"minecraft:red_mushroom"}, new int[] {0}, new String[] {}, new int[] {200, 200, 200}, 2, 1, 1);
    	BlockModPlant Withereed = new BlockModPlant("Withereed", new String[] {"gregtech:gt.metaitem.01"}, new int[] {2535}, new String[] {}, new int[] {1600, 1600, 1600, 1600}, 3, 1, 8);
    	BlockModPlant Sapphirum = new BlockModPlant("Sapphirum", new String[] {"gregtech:gt.metaitem.01"}, new int[] {503}, new String[] {}, new int[] {800, 800, 800, 800}, 3, 3, 4);
    	BlockModPlant Vines = new BlockModPlant("Vines", new String[] {"minecraft:vine"}, new int[] {0}, new String[] {}, new int[] {225, 225, 225}, 2, 1, 2);
    	BlockModPlant Moss = new BlockModPlant("Moss", new String[] {"BiomesOPlenty:moss"}, new int[] {0}, new String[] {}, new int[] {225, 225, 225}, 2, 1, 4);
    	BlockModPlant Stingberry = new BlockModPlant("Stingberry", new String[] {"Natura:berry.nether"}, new int[] {3}, new String[] {}, new int[] {700, 300, 700}, 2, 2, 4);
    	BlockModPlant CopperOreberry = new BlockModPlant("Copper Oreberry", new String[] {"TConstruct:oreBerries"}, new int[] {2}, new String[] {}, new int[] {500, 3000, 3000, 3000}, 2, 2, 5);
    	BlockModPlant Grass = new BlockModPlant("Grass", new String[] {"minecraft:deadbush"}, new int[] {0}, new String[] {}, new int[] {225, 225, 225, 225}, 1, 3, 0);
    	BlockModPlant Glowshroom = new BlockModPlant("Glowshroom", new String[] {"BiomesOPlenty:mushrooms"}, new int[] {3}, new String[] {}, new int[] {600, 600}, 1, 1, 3);
    	BlockModPlant OakBonsai = new BlockModPlant("Oak Bonsai", new String[] {""}, new int[] {}, new String[] {}, new int[] {600, 600, 600}, 2, 1, 1);
    	BlockModPlant BlackStonelilly = new BlockModPlant("Black Stonelilly", new String[] {""}, new int[] {}, new String[] {}, new int[] {300, 300, 300}, 2, 1, 1);
    	BlockModPlant Garnydinia = new BlockModPlant("Garnydinia", new String[] {"gregtech:gt.metaitem.01"}, new int[] {2527}, new String[] {}, new int[] {300, 550, 300}, 2, 1, 7);
    	BlockModPlant Belladonna = new BlockModPlant("Belladonna", new String[] {"witchery:ingredient"}, new int[] {21}, new String[] {}, new int[] {550, 550, 550}, 2, 1, 4);
    	BlockModPlant EssenceBerry = new BlockModPlant("Essence Berry", new String[] {"TConstruct:oreBerries"}, new int[] {5}, new String[] {}, new int[] {500, 3000, 3000, 3000}, 2, 2, 5);
    	BlockModPlant Strawberry = new BlockModPlant("Strawberry", new String[] {"harvestcraft:strawberryItem"}, new int[] {0}, new String[] {}, new int[] {700, 200, 700}, 2, 2, 2);
    	BlockModPlant Blueberry = new BlockModPlant("Blueberry", new String[] {"Natura:berry"}, new int[] {1}, new String[] {}, new int[] {700, 200, 700}, 2, 2, 2);
    	BlockModPlant Cactus = new BlockModPlant("Cactus", new String[] {"minecraft:cactus"}, new int[] {0}, new String[] {}, new int[] {225, 225, 225}, 1, 1, 3);
    	BlockModPlant AcaciaBonsai = new BlockModPlant("Acacia Bonsai", new String[] {"minecraft:sapling"}, new int[] {4}, new String[] {}, new int[] {600, 600, 600}, 2, 1, 1);
    	BlockModPlant GreenGlowshroom = new BlockModPlant("Green Glowshroom", new String[] {"Natura:Glowshroom"}, new int[] {0}, new String[] {}, new int[] {600, 600}, 1, 1, 3);
    	BlockModPlant PrimordialBerry = new BlockModPlant("Primordial Berry", new String[] {"Thaumcraft:ItemEldritchObject"}, new int[] {3}, new String[] {}, new int[] {125000, 125000, 125000, 125000}, 3, 1, 16);
    	BlockModPlant Berry = new BlockModPlant("Berry", new String[] {"BiomesOPlenty:food"}, new int[] {0}, new String[] {}, new int[] {700, 200, 700}, 2, 2, 2);
    	BlockModPlant Blightberry = new BlockModPlant("Blightberry", new String[] {"Natura:berry.nether"}, new int[] {0}, new String[] {}, new int[] {700, 300, 700}, 2, 2, 4);
    	BlockModPlant Waterlilly = new BlockModPlant("Waterlilly", new String[] {"minecraft:waterlily"}, new int[] {0}, new String[] {}, new int[] {550, 550, 550}, 2, 1, 2);
    	BlockModPlant AluminiumOreberry = new BlockModPlant("Aluminium Oreberry", new String[] {"TConstruct:oreBerries"}, new int[] {4}, new String[] {}, new int[] {500, 3000, 3000, 3000}, 2, 2, 5);
    	BlockModPlant Papyrus = new BlockModPlant("Papyrus", new String[] {"minecraft:paper"}, new int[] {0}, new String[] {}, new int[] {225, 225, 225}, 2, 1, 5);
    	BlockModPlant SaguaroCactus = new BlockModPlant("Saguaro Cactus", new String[] {"Natura:saguaro.fruit"}, new int[] {0}, new String[] {}, new int[] {225, 225, 450}, 1, 2, 4);
    	BlockModPlant Snowbell = new BlockModPlant("Snowbell", new String[] {"witchery:ingredient"}, new int[] {78}, new String[] {}, new int[] {550, 550, 550}, 2, 1, 4);
    	BlockModPlant WildCarrots = new BlockModPlant("Wild Carrots", new String[] {"BiomesOPlenty:food"}, new int[] {2}, new String[] {}, new int[] {225, 225, 225}, 2, 1, 2);
    	BlockModPlant MagicalNightshade = new BlockModPlant("Magical Nightshade", new String[] {"berriespp:Modifier"}, new int[] {1}, new String[] {}, new int[] {7812, 7812, 7812, 7812}, 3, 1, 13);
    	BlockModPlant SpruceBonsai = new BlockModPlant("Spruce Bonsai", new String[] {"minecraft:log"}, new int[] {1}, new String[] {}, new int[] {600, 600, 600}, 2, 1, 1);
    	BlockModPlant WhiteStonelilly = new BlockModPlant("White Stonelilly", new String[] {""}, new int[] {}, new String[] {}, new int[] {300, 300, 300}, 2, 1, 1);
    	BlockModPlant Maloberry = new BlockModPlant("Maloberry", new String[] {"Natura:berry"}, new int[] {3}, new String[] {}, new int[] {700, 200, 700}, 2, 2, 2);
    	BlockModPlant GoldOreberry = new BlockModPlant("Gold Oreberry", new String[] {"TConstruct:oreBerries"}, new int[] {1}, new String[] {}, new int[] {500, 3000, 3000, 3000}, 2, 2, 5);
    	BlockModPlant EmbeMoss = new BlockModPlant("Ember Moss", new String[] {"witchery:embermoss"}, new int[] {0}, new String[] {}, new int[] {550, 550, 550}, 2, 1, 7);
    	BlockModPlant BlueGlowshroom = new BlockModPlant("Blue Glowshroom", new String[] {"Natura:Glowshroom"}, new int[] {2}, new String[] {}, new int[] {600, 600}, 1, 1, 3);
    	BlockModPlant WolfsBane = new BlockModPlant("Wolf's Bane", new String[] {"witchery:ingredient"}, new int[] {156}, new String[] {}, new int[] {550, 550, 550}, 2, 1, 4);
    	BlockModPlant Duskberry = new BlockModPlant("Duskberry", new String[] {"Natura:berry.nether"}, new int[] {1}, new String[] {}, new int[] {700, 300, 700}, 2, 2, 4);
    	BlockModPlant Blackberry = new BlockModPlant("Blackberry", new String[] {"Natura:berry"}, new int[] {2}, new String[] {}, new int[] {700, 200, 700}, 2, 2, 2);
    	BlockModPlant SpacePlant = new BlockModPlant("Space Plant", new String[] {"berriespp:Modifier"}, new int[] {0}, new String[] {}, new int[] {5000, 5000, 5000, 5000}, 3, 1, 13);
    	BlockModPlant DarkOakBonsai = new BlockModPlant("Dark Oak Bonsai", new String[] {"minecraft:sapling"}, new int[] {5}, new String[] {}, new int[] {600, 600, 600}, 2, 1, 1);
    	BlockModPlant GlintWeed = new BlockModPlant("Glint Weed", new String[] {"witchery:glintweed"}, new int[] {0}, new String[] {}, new int[] {550, 550, 550}, 2, 1, 4);
    	BlockModPlant NetherStonelilly = new BlockModPlant("Nether Stonelilly", new String[] {""}, new int[] {}, new String[] {}, new int[] {300, 300, 300}, 2, 1, 1);
    	BlockModPlant SugarBeet = new BlockModPlant("Sugar Beet", new String[] {"berriespp:foodBerries"}, new int[] {1}, new String[] {}, new int[] {225, 225, 225}, 2, 1, 4);
    	BlockModPlant MagicMetalBerry = new BlockModPlant("Magic Metal Berry", new String[] {""}, new int[] {}, new String[] {}, new int[] {500, 1200, 500, 500}, 3, 2, 7);
    	BlockModPlant Cotton = new BlockModPlant("Cotton", new String[] {"Natura:barleyFood"}, new int[] {3}, new String[] {}, new int[] {225, 225, 225, 225, 225}, 4, 1, 3);
    	BlockModPlant Ivy = new BlockModPlant("Ivy", new String[] {"BiomesOPlenty:ivy"}, new int[] {0}, new String[] {}, new int[] {225, 225, 225}, 2, 1, 2);
    	BlockModPlant Huckleberry = new BlockModPlant("Huckleberry", new String[] {"berriespp:foodBerries"}, new int[] {0}, new String[] {}, new int[] {700, 200, 700}, 2, 2, 2);
    	BlockModPlant FloweringVines = new BlockModPlant("Flowering Vines", new String[] {"minecraft:vine"}, new int[] {0}, new String[] {}, new int[] {225, 225, 225, 225}, 2, 1, 3);
    	BlockModPlant GoldfishPlant = new BlockModPlant("Goldfish Plant", new String[] {"berriespp:foodGoldfish"}, new int[] {0}, new String[] {}, new int[] {225, 225, 225}, 2, 1, 4);
    	BlockModPlant Glowflower = new BlockModPlant("Glowflower", new String[] {"BiomesOPlenty:flowers"}, new int[] {3}, new String[] {}, new int[] {225, 225, 225}, 2, 1, 3);
    	BlockModPlant RedStonelilly = new BlockModPlant("Red Stonelilly", new String[] {""}, new int[] {}, new String[] {}, new int[] {300, 300, 300}, 2, 1, 1);
    	BlockModPlant YellowStonelilly = new BlockModPlant("Yellow Stonelilly", new String[] {"minecraft:sand"}, new int[] {0}, new String[] {}, new int[] {300, 300, 300}, 2, 1, 1);
    	BlockModPlant CobaltBerry = new BlockModPlant("Cobalt Berry", new String[] {""}, new int[] {}, new String[] {}, new int[] {500, 3000, 3000, 3000}, 2, 2, 7);
    	BlockModPlant GrayStonelilly = new BlockModPlant("Gray Stonelilly", new String[] {""}, new int[] {}, new String[] {}, new int[] {300, 300, 300}, 2, 1, 1);
    	BlockModPlant BirchBonsai = new BlockModPlant("Birch Bonsai", new String[] {"minecraft:sapling"}, new int[] {2}, new String[] {}, new int[] {600, 600, 600}, 2, 1, 1);
    	BlockModPlant Eyebulb = new BlockModPlant("Eyebulb", new String[] {"BiomesOPlenty:flowers"}, new int[] {13}, new String[] {}, new int[] {225, 225, 225}, 2, 1, 1);
    	BlockModPlant Barley = new BlockModPlant("Barley", new String[] {"harvestcraft:barleyItem"}, new int[] {0}, new String[] {}, new int[] {225, 225, 225, 225}, 3, 1, 2);
    	BlockModPlant PurpleGlowshroom = new BlockModPlant("Purple Glowshroom", new String[] {"Natura:Glowshroom"}, new int[] {1}, new String[] {}, new int[] {600, 600}, 1, 1, 3);
    	BlockModPlant IronOreberry = new BlockModPlant("Iron Oreberry", new String[] {"TConstruct:oreBerries"}, new int[] {0}, new String[] {}, new int[] {500, 3000, 3000, 3000}, 2, 2, 5);
    	BlockModPlant ArditeBerry = new BlockModPlant("Ardite Berry", new String[] {""}, new int[] {}, new String[] {}, new int[] {500, 3000, 3000, 3000}, 2, 2, 7);
    	BlockModPlant SpanishMoss = new BlockModPlant("Spanish Moss", new String[] {"witchery:spanishmoss"}, new int[] {0}, new String[] {}, new int[] {225, 225, 225, 225}, 2, 1, 7);
    	BlockModPlant Thornvines = new BlockModPlant("Thornvines", new String[] {"Natura:Thornvines"}, new int[] {0}, new String[] {}, new int[] {225, 225, 225}, 2, 1, 3);
    	BlockModPlant GlowingEarthCoral = new BlockModPlant("Glowing Earth Coral", new String[] {"BiomesOPlenty:coral1"}, new int[] {15}, new String[] {}, new int[] {225, 225, 225}, 2, 1, 5);
    	BlockModPlant KnightlyOreberry = new BlockModPlant("Knightly Oreberry", new String[] {"TwilightForest:item.armorShards"}, new int[] {0}, new String[] {}, new int[] {1000, 4500, 4500, 4500}, 2, 2, 8);
    	BlockModPlant Cinderpearl = new BlockModPlant("Cinderpearl", new String[] {"Thaumcraft:blockCustomPlant"}, new int[] {3}, new String[] {}, new int[] {2250, 1750, 1750}, 2, 1, 5);
    	BlockModPlant JungleBonsai = new BlockModPlant("Jungle Bonsai", new String[] {"minecraft:log"}, new int[] {3}, new String[] {}, new int[] {600, 600, 600}, 2, 1, 1);
    	BlockModPlant Mandragora = new BlockModPlant("Mandragora", new String[] {"witchery:ingredient"}, new int[] {22}, new String[] {}, new int[] {550, 550, 550}, 2, 1, 4);
    	BlockModPlant Artichoke = new BlockModPlant("Artichoke", new String[] {"witchery:ingredient"}, new int[] {69}, new String[] {}, new int[] {550, 550, 550}, 2, 1, 4);
    	BlockModPlant Raspberry = new BlockModPlant("Raspberry", new String[] {"Natura:berry"}, new int[] {0}, new String[] {}, new int[] {700, 200, 700}, 2, 2, 2);
    	BlockModPlant Shimmerleaf = new BlockModPlant("Shimmerleaf", new String[] {"Thaumcraft:blockCustomPlant"}, new int[] {2}, new String[] {}, new int[] {2250, 1750, 1750}, 2, 1, 5);
    	BlockModPlant Turnip = new BlockModPlant("Turnip", new String[] {"harvestcraft:turnipItem"}, new int[] {0}, new String[] {}, new int[] {225, 225, 225}, 2, 1, 2);
    	BlockModPlant Garlic = new BlockModPlant("Garlic", new String[] {"witchery:garlic"}, new int[] {32767}, new String[] {}, new int[] {225, 225, 225}, 2, 1, 3);
    	BlockModPlant Skyberry = new BlockModPlant("Skyberry", new String[] {"Natura:berry.nether"}, new int[] {2}, new String[] {}, new int[] {700, 300, 700}, 2, 2, 4);
    	BlockModPlant TinOreberry = new BlockModPlant("Tin Oreberry", new String[] {"TConstruct:oreBerries"}, new int[] {3}, new String[] {}, new int[] {500, 3000, 3000, 3000}, 2, 2, 4);
    }

    public static void addMutation(BlockModPlant result, BlockModPlant parent1, BlockModPlant parent2)
    {
    	ItemStack resultStack = result.getSeedStack(1);
    	ItemStack parent1Stack = parent1.getSeedStack(1);
    	ItemStack parent2Stack = parent2.getSeedStack(1);
    	
    	if(resultStack != null && parent1Stack != null && parent2Stack != null)
    	{
    		Mutation mutation = new Mutation(resultStack, parent1Stack, parent2Stack, Math.pow(0.95, result.tier));
    		MutationHandler.getInstance().add(mutation);
    	}
    }
}
