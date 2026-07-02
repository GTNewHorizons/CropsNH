package com.gtnewhorizon.cropsnh.api;

public abstract class CropsNHMutationPools {

    // the string value is what's used for lang keys

    // Should only use one at a time
    // region color
    /** 1. Color: Is it mostly blue? */
    public final static String BLUE = "blue";
    /** 1. Color: Is it mostly black? */
    public final static String BLACK = "black";
    /** 1. Color: Is it mostly brown? */
    public final static String BROWN = "brown";
    /** 1. Color: Is it mostly gray? */
    public final static String GRAY = "gray";
    /** 1. Color: Is it mostly green? */
    public final static String GREEN = "green";
    /** 1. Color: Is it mostly orange? */
    public final static String ORANGE = "orange";
    /** 1. Color: Is it mostly purple? */
    public final static String PURPLE = "purple";
    /** 1. Color: Is it mostly red? */
    public final static String RED = "red";
    /** 1. Color: Is it mostly white? */
    public final static String WHITE = "white";
    /** 1. Color: Is it mostly Yellow? */
    public final static String YELLOW = "yellow";
    // endregion color

    // Should usually have at most 1 of these
    // region Origin
    /** 2. Origin: Is it form the end or space? */
    public final static String ALIEN = "alien";
    /** 2. Origin: Does it come from the nether? */
    public final static String NETHER = "nether";
    /** 2. Origin: Does it come from the twilight forest? */
    public final static String TWILIGHT_FOREST = "twilightForest";
    // endregion Origin

    // Shouldn't need more than 2
    // region Crop Type
    /** 3. Type: Is it a bean? */
    public final static String BEAN = "bean";
    /** 3. Type: Is it a berry? */
    public final static String BERRY = "berry";
    /** 3. Type: Is it a cacti? */
    public final static String CACTUS = "cactus";
    /** 3. Type: Is it a bush? */
    public final static String BUSH = "bush";
    /** 3. Type: Is it a flower? */
    public final static String FLOWER = "flower";
    /** 3. Type: Is it a mushroom? */
    public final static String MUSHROOM = "mushroom";
    /** 3. Type: Is it an ore berry? */
    public final static String ORE_BERRY = "oreBerry";
    /** 3. Type: Is it a reed of some kind? */
    public final static String REED = "reed";
    /** 3. Type: Is the product the root of the crop? */
    public final static String ROOT = "root";
    /** 3. Type: Does it grow from a stem? */
    public final static String STEM = "stem";
    /** 3. Type: Is it a tree? */
    public final static String TREE = "tree";
    /** 3. Type: Is it a tulip? */
    public final static String TULIP = "tulip";
    /** 3. Type: Is it a wheat of some kind? */
    public final static String WHEAT = "wheat";
    // endregion Crop Type

    // include all applicable
    // region Crop Property
    /** 4. Property: Could the player climb on it? */
    public final static String CLIMBABLE = "climbable";
    /** 4. Property: Is it crystalline in nature? */
    public final static String CRYSTALLINE = "crystal";
    /** 4. Property: Could it hurt the player or other entities? */
    public final static String DANGEROUS = "danger";
    /** 4. Property: Is it of a dark color or prefer dark environments? */
    public final static String DARK = "dark";
    /** 4. Property: Is the thing it's made out of dense? */
    public final static String DENSE = "dense";
    /** 4. Property: Will it attack the player or other entities or does it just look evil? */
    public final static String EVIL = "evil";
    /** 4. Property: Could it light things on fire or is it fireproof? */
    public final static String FIERY = "fire";
    /** 4. Property: Is it's body mostly leafy? (eg: trees, ferns, lettuce) */
    public final static String LEAFY = "leafy";
    /** 4. Property: Does the thing it drops make light? */
    public final static String EMISSIVE = "light";
    /** 4. Property: Does it have metallic properties? */
    public final static String METALLIC = "metal";
    /** 4. Property: Is it poisonous? */
    public final static String POISONOUS = "poison";
    /** 4. Property: Is it shiny? */
    public final static String SHINY = "shiny";
    /** 4. Property: Is it sticky to the touch? */
    public final static String STICKY = "sticky";
    /** 4. Property: Does it have tendrils? */
    public final static String TENDRILLY = "tendrilly";
    /** 4. Property: Is it undead or does it relate to the undead? */
    public final static String UNDEAD = "undead";
    /** 4. Property: If you peer into the void, does it stare back? */
    public final static String VOID_TOUCHED = "void";
    /** 4. Property: Is it related to water or does it grow in water? */
    public final static String WATERY = "water";
    /** 4. Property: Is it mostly wood? */
    public final static String WOODEN = "wood";
    // endregion Crop Property

    // include all applicable
    // region Drop Property
    /** 5. Drop: Does it drop something with addictive properties? */
    public final static String ADDICTIVE = "addictive";
    /** 5. Drop: Does it drop something blaze related? */
    public final static String BLAZE = "blaze";
    /** 5. Drop: Is it chicken related or does it make eggs? */
    public final static String CHICKEN = "chicken";
    /** 5. Drop: Does it contain coal? */
    public final static String COAL = "coal";
    /** 5. Drop: Does it contain copper? */
    public final static String COPPER = "copper";
    /** 5. Drop: Can you make wool with it? */
    public final static String COTTON = "cotton";
    /** 5. Drop: Are the drops cow related? */
    public final static String COW = "cow";
    /** 5. Drop: Could you fish it up? */
    public final static String FISH = "fish";
    /** 5. Drop: Is it edible? */
    public final static String EDIBLE = "food";
    /** 5. Drop: Does it contain gold? */
    public final static String GOLD = "gold";
    /** 5. Drop: Can it heal the player or does it have restorative properties? */
    public final static String HEALING = "healing";
    /** 5. Drop: Does it contain iron? */
    public final static String IRON = "iron";
    /** 5. Drop: Does it contain lead? */
    public final static String LEAD = "lead";
    /** 5. Drop: Does it have magical properties? */
    public final static String MAGICAL = "magic";
    /** 5. Drop: Can you make oil out of it? */
    public final static String OIL = "oil";
    /** 5. Drop: Can you make a potion out of it? */
    public final static String POTION_INGREDIENT = "ingredient";
    /** 5. Drop: Does it contain saltpeter? */
    public final static String SALTPETER = "saltpeter";
    /** 5. Drop: Can you make silk with it? */
    public final static String SILK = "silk";
    /** 5. Drop: Does it contain silver? */
    public final static String SILVER = "silver";
    /** 5. Drop: Does it contain some sort of stone? */
    public final static String STONE = "stone";
    /** 5. Drop: Does it contain sulfur? */
    public final static String SULFUR = "sulfur";
    /** 5. Drop: Does it contain tin? */
    public final static String TIN = "tin";
    // endregion Drop Property

}
