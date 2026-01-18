package com.gtnewhorizon.cropsnh.handler;

import java.util.Arrays;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.IAdditionalCropData;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.crops.CropMigrator;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.SeedData;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.requirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;
import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.loaders.MTELoader;
import com.gtnewhorizon.cropsnh.loaders.MaterialLeafLoader;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizon.cropsnh.utility.NBTHelper;
import com.gtnewhorizon.cropsnh.utility.XSTR;
import com.gtnewhorizons.postea.api.ItemStackReplacementManager;
import com.gtnewhorizons.postea.api.TileEntityReplacementManager;
import com.gtnewhorizons.postea.utility.BlockInfo;

import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.GregTechAPI;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTRecipe;
import gregtech.api.util.GTUtility;

public class MigrationHandler {

    /**
     * Handles postea migrations
     */
    public static void postInit() {
        if (!ConfigurationHandler.enableMigrations) return;
        addCropsPlusPlusPosteaMigrations();
        addIC2PosteaMigrations();
        addMTEMigrations();
    }

    private static void addMTEMigrations() {
        TileEntityReplacementManager
            .tileEntityTransformer("BaseMetaTileEntity", (oldNBT, world, chunk) -> switch (oldNBT.getInteger("mID")) {
            case 31111 -> migrateCropManager(MTELoader.CROP_MANAGER_LV_MTE_ID);
            case 31112 -> migrateCropManager(MTELoader.CROP_MANAGER_MV_MTE_ID);
            case 31113 -> migrateCropManager(MTELoader.CROP_MANAGER_HV_MTE_ID);
            case 31114 -> migrateCropManager(MTELoader.CROP_MANAGER_EV_MTE_ID);
            case 31115 -> migrateCropManager(MTELoader.CROP_MANAGER_IV_MTE_ID);
            case 31116 -> migrateCropManager(MTELoader.CROP_MANAGER_LuV_MTE_ID);
            case 31117 -> migrateCropManager(MTELoader.CROP_MANAGER_ZPM_MTE_ID);
            case 31118 -> migrateCropManager(MTELoader.CROP_MANAGER_UV_MTE_ID);
            default -> null;
            });
    }

    private static BlockInfo migrateCropManager(int newMetaID) {
        return new BlockInfo(GregTechAPI.sBlockMachines, 1, oldNBT -> {
            NBTTagCompound newNBT = (NBTTagCompound) oldNBT.copy();
            newNBT.setInteger("mID", newMetaID);
            if (newNBT.hasKey("mFluid", Data.NBTType._object)) {
                FluidStack stack = FluidStack.loadFluidStackFromNBT(newNBT.getCompoundTag("mFluid"));
                if (stack.getFluid() == CropsNHUtils.getWeedEXFluid()) {
                    newNBT.setInteger("mWeedEx", stack.amount);
                } else {
                    newNBT.setInteger("mWater", stack.amount);
                }
                newNBT.removeTag("mFluid");
            }
            if (newNBT.hasKey("mModeAlternative")) {
                boolean altsEnabled = NBTHelper.getBoolean(newNBT, "mModeAlternative", false);
                newNBT.setBoolean("mFertilizerEnabled", altsEnabled);
                newNBT.setBoolean("mWaterEnabled", altsEnabled);
                newNBT.setBoolean("mWeedExEnabled", altsEnabled);
                newNBT.removeTag("mModeAlternative");
            }
            return newNBT;
        });
    }

    /**
     * Handles recipe removals
     */
    public static void postLoadComplete() {
        if (!ConfigurationHandler.enableMigrations) return;
        if (ModUtils.CropsPlusPlus.isModLoaded()) {
            GTModHandler.removeRecipeByOutput(GTModHandler.getModItem(ModUtils.CropsPlusPlus.getID(), "itemLens", 1));
            GTModHandler.removeRecipeByOutput(GTModHandler.getModItem(ModUtils.CropsPlusPlus.getID(), "itemSpade", 1));
        }

        if (ModUtils.IndustrialCraft2.isModLoaded() && ModUtils.NewHorizonsCoreMod.isModLoaded()) {
            ItemStack cropSticks = GTModHandler.getModItem(ModUtils.IndustrialCraft2.getID(), "blockCrop", 1);
            GTModHandler.removeRecipeByOutput(cropSticks);
            for (GTRecipe recipe : RecipeMaps.assemblerRecipes.getAllRecipes()) {
                if (recipe.mOutputs != null && recipe.mOutputs.length >= 1
                    && Arrays.stream(recipe.mOutputs)
                        .anyMatch(s -> GTUtility.areStacksEqual(s, cropSticks) && s.stackSize == 16)) {
                    RecipeMaps.assemblerRecipes.getBackend()
                        .removeRecipe(recipe);
                }
            }
        }
    }

    /**
     * Handles missing mapping events.
     */
    public static void onMissingMigration(FMLMissingMappingsEvent event) {
        if (!ConfigurationHandler.enableMigrations) return;
        for (FMLMissingMappingsEvent.MissingMapping mapping : event.getAll()) {
            if (mapping.type != GameRegistry.Type.ITEM) {
                continue;
            }

            // Remaps the old "UnfiredSlimeSoulBrick" (with a typo) to the new, correct "UnfiredSlimeSoilBrick".
            if (!ModUtils.CropsPlusPlus.isModLoaded() && migrateMissingBPPItemMapping(mapping)) continue;
            if (!ModUtils.IndustrialCraft2.isModLoaded() && migrateMissingIC2ItemMappings(mapping)) continue;
        }
    }

    private static void addIC2PosteaMigrations() {
        TileEntityReplacementManager.tileEntityTransformer(
            "TECrop",
            (nbt, world, chunk) -> new BlockInfo(CropsNHBlocks.blockCropSticks, 0, oldNBT -> {
                NBTTagCompound newNBT = new NBTTagCompound();
                newNBT.setTag("x", oldNBT.getTag("x"));
                newNBT.setTag("y", oldNBT.getTag("y"));
                newNBT.setTag("z", oldNBT.getTag("z"));
                newNBT.setString(
                    "id",
                    Reference.MOD_ID + ":" + Names.TileEntity.tileEntity + "_" + Names.Objects.cropSticksTE);
                boolean isCrossCrop = NBTHelper.getBoolean(oldNBT, "upgraded", false);
                int fertilizerStorage = NBTHelper.getInteger(oldNBT, "nutrientStorage", 0);
                int waterStorage = NBTHelper.getInteger(oldNBT, "waterStorage", 0);
                int weedEXStorage = NBTHelper.getInteger(oldNBT, "exStorage", 0);
                TileEntityCrop
                    .writeStatusNBT(newNBT, waterStorage, fertilizerStorage, weedEXStorage, false, isCrossCrop);

                if (!isCrossCrop && oldNBT.hasKey("cropOwner") && oldNBT.hasKey("cropName")) {
                    ICropCard cc = getMigratedCrop(oldNBT.getString("cropOwner"), oldNBT.getString("cropName"));
                    if (cc == null) cc = CropsNHCrops.Carrot;

                    ISeedStats stats = new SeedStats(
                        (byte) Math.max(
                            Constants.MIN_SEED_STAT,
                            Math.min(
                                Constants.MAX_SEED_STAT,
                                NBTHelper.getIntgegerNumber(oldNBT, "statGrowth", Constants.MIN_SEED_STAT))),
                        (byte) Math.max(
                            Constants.MIN_SEED_STAT,
                            Math.min(
                                Constants.MAX_SEED_STAT,
                                NBTHelper.getIntgegerNumber(oldNBT, "statGain", Constants.MIN_SEED_STAT))),
                        (byte) Math.max(
                            Constants.MIN_SEED_STAT,
                            Math.min(
                                Constants.MAX_SEED_STAT,
                                NBTHelper.getIntgegerNumber(oldNBT, "statResistance", Constants.MIN_SEED_STAT))),
                        NBTHelper.getIntgegerNumber(oldNBT, "scanLevel", 0) >= 1);
                    IAdditionalCropData extra = null;
                    // check if the crop can grow on the current farmland.
                    if (ConfigurationHandler.alwaysMigrateUsingMigrationCrop || !cc.getSoilTypes()
                        .isRegistered(Blocks.farmland, 7)) {
                        extra = new CropMigrator.AdditionalData(new SeedData(cc, stats));
                        cc = CropsNHCrops.Migrator;
                    }
                    // reset growth progress to force growth checks and what not
                    TileEntityCrop.writeSeedNBT(newNBT, new SeedData(cc, stats), extra, 0);
                }
                return newNBT;
            }));

        ItemStackReplacementManager.addItemReplacement(ModUtils.IndustrialCraft2.ID + ":itemCropSeed", stack -> {
            // if tag is invalid
            if (!stack.hasKey("tag", Data.NBTType._object)) return null;
            NBTTagCompound oldTag = stack.getCompoundTag("nbt");
            // if tag is invalid abort
            if (!oldTag.hasKey("owner", Data.NBTType._string) || !oldTag.hasKey("name", Data.NBTType._string))
                return null;
            String owner = oldTag.getString("owner");
            String name = oldTag.getString("name");
            // if crop can't be found default to carrot to keep stat progression.
            ICropCard cc = getMigratedCrop(owner, name);
            if (cc == null) cc = CropsNHCrops.Carrot;
            // load the stats
            ISeedStats stats = new SeedStats(
                (byte) Math.max(
                    Constants.MIN_SEED_STAT,
                    Math.min(
                        Constants.MAX_SEED_STAT,
                        NBTHelper.getIntgegerNumber(oldTag, "growth", Constants.MIN_SEED_STAT))),
                (byte) Math.max(
                    Constants.MIN_SEED_STAT,
                    Math.min(
                        Constants.MAX_SEED_STAT,
                        NBTHelper.getIntgegerNumber(oldTag, "gain", Constants.MIN_SEED_STAT))),
                (byte) Math.max(
                    Constants.MIN_SEED_STAT,
                    Math.min(
                        Constants.MAX_SEED_STAT,
                        NBTHelper.getIntgegerNumber(oldTag, "resistance", Constants.MIN_SEED_STAT))),
                NBTHelper.getIntgegerNumber(oldTag, "scan", 0) >= 1);
            // update the stack
            stack.setShort("id", (short) Item.getIdFromItem(CropsNHItems.materialLeaf));
            stack.setShort("Damage", (short) MaterialLeafLoader.spaceFlower.getId());
            stack.setTag("tag", NHCropCard.writeNBT(cc, stats));
            return stack;
        });
    }

    private static @Nullable ICropCard getMigratedCrop(String owner, String name) {
        return switch (owner + ":" + name) {
            case "Gtplusplus:Hemp" -> CropsNHCrops.Hemp;
            case "GoodGenerator:saltroot" -> CropsNHCrops.SaltyRoot;
            case "IC2:stagnium" -> CropsNHCrops.Cassitine;
            case "IC2:redwheat" -> CropsNHCrops.RedStraw;
            case "IC2:dandelion" -> CropsNHCrops.Dandelion;
            case "IC2:venomilia" -> CropsNHCrops.Necrobloom;
            case "IC2:potato" -> CropsNHCrops.Potato;
            case "IC2:eatingplant" -> CropsNHCrops.Cactus;
            case "IC2:blackthorn" -> CropsNHCrops.InkBloom;
            case "IC2:weed" -> CropsNHCrops.Weed;
            case "IC2:wheat" -> CropsNHCrops.Wheat;
            case "IC2:carrots" -> CropsNHCrops.Cactus;
            case "IC2:ferru" -> CropsNHCrops.Ferrofern;
            case "IC2:terraWart" -> CropsNHCrops.GaiaWart;
            case "IC2:melon" -> CropsNHCrops.Melon;
            case "IC2:stickreed" -> CropsNHCrops.StickyCane;
            case "IC2:plumbiscus" -> CropsNHCrops.Plumbshade;
            case "IC2:netherWart" -> CropsNHCrops.Netherwart;
            case "IC2:aurelia" -> CropsNHCrops.Auronia;
            case "IC2:redMushroom" -> CropsNHCrops.RedMushroom;
            case "IC2:reed" -> CropsNHCrops.SugarCane;
            case "IC2:cyprium" -> CropsNHCrops.Malaxia;
            case "IC2:tulip" -> CropsNHCrops.PurpleTulip;
            case "IC2:cocoa" -> CropsNHCrops.Cocoa;
            case "IC2:cyazint" -> CropsNHCrops.Dayflower;
            case "IC2:brownMushroom" -> CropsNHCrops.BrownMushroom;
            case "IC2:coffee" -> CropsNHCrops.Coffee;
            case "IC2:shining" -> CropsNHCrops.Silviscus;
            case "IC2:rose" -> CropsNHCrops.Poppy;
            case "IC2:hops" -> CropsNHCrops.Hops;
            case "IC2:pumpkin" -> CropsNHCrops.Pumpkin;
            case "gregtech:Slimeplant" -> CropsNHCrops.Slimeplant;
            case "gregtech:Tearstalks" -> CropsNHCrops.Tearstalks;
            case "gregtech:God of Thunder" -> CropsNHCrops.GodOfThunder;
            case "gregtech:Tomato" -> CropsNHCrops.Tomato;
            case "gregtech:Lemon" -> CropsNHCrops.Lemon;
            case "gregtech:Diareed" -> CropsNHCrops.Diareed;
            case "gregtech:Meatrose" -> CropsNHCrops.Meatrose;
            case "gregtech:Plumbilia" -> CropsNHCrops.Plumbilia;
            case "gregtech:Corpseplant" -> CropsNHCrops.Corpseplant;
            case "gregtech:Reactoria" -> CropsNHCrops.Reactoria;
            case "gregtech:Glowheat" -> CropsNHCrops.Glowheat;
            case "gregtech:Rape" -> CropsNHCrops.Canola;
            case "gregtech:Onion" -> CropsNHCrops.Onion;
            case "gregtech:Starwart" -> CropsNHCrops.StarWart;
            case "gregtech:Evil Ore" -> CropsNHCrops.EvilOre;
            case "gregtech:Tine" -> CropsNHCrops.Tine;
            case "gregtech:Galvania" -> CropsNHCrops.Galvania;
            case "gregtech:Micadia" -> CropsNHCrops.Micadia;
            case "gregtech:Grape" -> CropsNHCrops.Grape;
            case "gregtech:Lazulia" -> CropsNHCrops.Lazulia;
            case "gregtech:Indigo" -> CropsNHCrops.Indigo;
            case "gregtech:Flax" -> CropsNHCrops.Flax;
            case "gregtech:Corium" -> CropsNHCrops.Corium;
            case "gregtech:Cucumber" -> CropsNHCrops.Cucumber;
            case "gregtech:Quantaria" -> XSTR.XSTR_INSTANCE.nextInt(4) == 0 ? CropsNHCrops.Osmianth
                : CropsNHCrops.Iridine;
            case "gregtech:Trollplant" -> CropsNHCrops.Trollplant;
            case "gregtech:Enderbloom" -> CropsNHCrops.Enderbloom;
            case "gregtech:Milkwart" -> CropsNHCrops.MilkWart;
            case "gregtech:Steeleafranks" -> CropsNHCrops.Steeleafranks;
            case "gregtech:Pyrolusium" -> CropsNHCrops.Pyrolusium;
            case "gregtech:Chilly" -> CropsNHCrops.Chilly;
            case "gregtech:Creeperweed" -> CropsNHCrops.Creeperweed;
            case "gregtech:Titania" -> CropsNHCrops.Titania;
            case "gregtech:Coppon" -> CropsNHCrops.Coppon;
            case "gregtech:Eggplant" -> CropsNHCrops.EggPlant;
            case "gregtech:Transformium" -> CropsNHCrops.Transformium;
            case "gregtech:Fertilia" -> CropsNHCrops.Fertilia;
            case "gregtech:Liveroots" -> CropsNHCrops.Liveroot;
            case "gregtech:Stargatium" -> CropsNHCrops.Stargatium;
            case "gregtech:Scheelinium" -> CropsNHCrops.Scheelinium;
            case "gregtech:Platina" -> CropsNHCrops.Platina;
            case "gregtech:Blazereed" -> CropsNHCrops.Blazereed;
            case "gregtech:Zomplant" -> CropsNHCrops.Zomplant;
            case "gregtech:Nickelback" -> CropsNHCrops.Nickelback;
            case "gregtech:Bobsyeruncleranks" -> CropsNHCrops.BobsYerUncleRanks;
            case "gregtech:Oilberries" -> CropsNHCrops.OilBerry;
            case "gregtech:Olivia" -> CropsNHCrops.Olivia;
            case "gregtech:Tea" -> CropsNHCrops.Tea;
            case "gregtech:Argentia" -> CropsNHCrops.Argentia;
            case "gregtech:Bauxia" -> CropsNHCrops.Bauxia;
            case "gregtech:Brown Mushrooms" -> CropsNHCrops.BrownMushroom;
            case "gregtech:Spidernip" -> CropsNHCrops.Spidernip;
            case "gregtech:Red Mushrooms" -> CropsNHCrops.RedMushroom;
            case "gregtech:Withereed" -> CropsNHCrops.Withereed;
            case "gregtech:Sapphirum" -> CropsNHCrops.Sapphirum;
            case "berriespp:Vines" -> CropsNHCrops.Vine;
            case "berriespp:Moss" -> CropsNHCrops.Moss;
            case "berriespp:Stingberry" -> CropsNHCrops.Stingberry;
            case "berriespp:Copper Oreberry" -> CropsNHCrops.CopperOreBerry;
            case "berriespp:Grass" -> CropsNHCrops.Weed;
            case "berriespp:Glowshroom" -> CropsNHCrops.Glowshroom;
            case "berriespp:Oak Bonsai" -> CropsNHCrops.BonsaiOak;
            case "berriespp:Black Stonelilly" -> CropsNHCrops.BlackGraniteLily;
            case "berriespp:Garnydinia" -> CropsNHCrops.Garnydinia;
            case "berriespp:Belladonna" -> CropsNHCrops.Belladonna;
            case "berriespp:Essence Berry" -> CropsNHCrops.EssenceOreBerry;
            case "berriespp:Strawberry" -> CropsNHCrops.Strawberry;
            case "berriespp:Blueberry" -> CropsNHCrops.Blueberry;
            case "berriespp:Cactus" -> CropsNHCrops.Cactus;
            case "berriespp:Torchberry" -> CropsNHCrops.Torchberry;
            case "berriespp:Acacia Bonsai" -> CropsNHCrops.BonsaiAcacia;
            case "berriespp:Green Glowshroom" -> CropsNHCrops.GreenGlowshroom;
            case "berriespp:Primordial Berry" -> CropsNHCrops.PrimordialBerry;
            case "berriespp:Berry" -> CropsNHCrops.BoPBerry;
            case "berriespp:Blightberry" -> CropsNHCrops.Blightberry;
            case "berriespp:Waterlilly" -> CropsNHCrops.Waterlily;
            case "berriespp:Aluminium Oreberry" -> CropsNHCrops.AluminiumOreBerry;
            case "berriespp:Papyrus" -> CropsNHCrops.Papyrus;
            case "berriespp:Saguaro Cactus" -> CropsNHCrops.SaguaroCactus;
            case "berriespp:Snowbell" -> CropsNHCrops.Snowbell;
            case "berriespp:Wild Carrots" -> CropsNHCrops.WildCarrot;
            case "berriespp:Magical Nightshade" -> CropsNHCrops.MagicalNightshade;
            case "berriespp:Spruce Bonsai" -> CropsNHCrops.BonsaiSpruce;
            case "berriespp:White Stonelilly" -> CropsNHCrops.MarbleLily;
            case "berriespp:Maloberry" -> CropsNHCrops.Maloberry;
            case "berriespp:Gold Oreberry" -> CropsNHCrops.GoldOreBerry;
            case "berriespp:Ember Moss" -> CropsNHCrops.EmberMoss;
            case "berriespp:Bamboo" -> CropsNHCrops.Bamboo;
            case "berriespp:Blue Glowshroom" -> CropsNHCrops.BlueGlowshroom;
            case "berriespp:Wolf's Bane" -> CropsNHCrops.Wolfsbane;
            case "berriespp:Duskberry" -> CropsNHCrops.Duskberry;
            case "berriespp:Blackberry" -> CropsNHCrops.Blackberry;
            case "berriespp:Space Plant" -> CropsNHCrops.SpaceFlower;
            case "berriespp:Dark Oak Bonsai" -> CropsNHCrops.BonsaiDarkOak;
            case "berriespp:Glint Weed" -> CropsNHCrops.GlintWeed;
            case "berriespp:Nether Stonelilly" -> CropsNHCrops.NetherStoneLily;
            case "berriespp:Sugar Beet" -> CropsNHCrops.SugarBeet;
            case "berriespp:Magic Metal Berry" -> CropsNHCrops.ThaumiumOreBerry;
            case "berriespp:Cotton" -> CropsNHCrops.Cotton;
            case "berriespp:Ivy" -> CropsNHCrops.Ivy;
            case "berriespp:Huckleberry" -> CropsNHCrops.Huckleberry;
            case "berriespp:Flowering Vines" -> CropsNHCrops.FloweringVine;
            case "berriespp:Goldfish Plant" -> CropsNHCrops.Goldfish;
            case "berriespp:Glowflower" -> CropsNHCrops.Glowflower;
            case "berriespp:Red Stonelilly" -> CropsNHCrops.RedGraniteLily;
            case "berriespp:Mana Bean" -> CropsNHCrops.ManaBean;
            case "berriespp:Yellow Stonelilly" -> CropsNHCrops.SandLily;
            case "berriespp:Cobalt Berry" -> CropsNHCrops.CobaltOreBerry;
            case "berriespp:Gray Stonelilly" -> CropsNHCrops.StoneLily;
            case "berriespp:Birch Bonsai" -> CropsNHCrops.BonsaiBirch;
            case "berriespp:Eyebulb" -> CropsNHCrops.Eyebulb;
            case "berriespp:Barley" -> CropsNHCrops.Barley;
            case "berriespp:Purple Glowshroom" -> CropsNHCrops.PurpleGlowshroom;
            case "berriespp:Iron Oreberry" -> CropsNHCrops.IronOreBerry;
            case "berriespp:Ardite Berry" -> CropsNHCrops.ArditeOreBerry;
            case "berriespp:Spanish Moss" -> CropsNHCrops.SpanishMoss;
            case "berriespp:Thornvines" -> CropsNHCrops.Thornvine;
            case "berriespp:Glowing Earth Coral" -> CropsNHCrops.GlowingCoral;
            case "berriespp:Knightly Oreberry" -> CropsNHCrops.KnightmetalBerry;
            case "berriespp:Cinderpearl" -> CropsNHCrops.Cinderpearl;
            case "berriespp:Jungle Bonsai" -> CropsNHCrops.BonsaiJungle;
            case "berriespp:Mandragora" -> CropsNHCrops.Mandrake;
            case "berriespp:Artichoke" -> CropsNHCrops.WaterArtichoke;
            case "berriespp:Raspberry" -> CropsNHCrops.Raspberry;
            case "berriespp:Shimmerleaf" -> CropsNHCrops.Shimmerleaf;
            case "berriespp:Turnip" -> CropsNHCrops.Turnip;
            case "berriespp:Garlic" -> CropsNHCrops.Garlic;
            case "berriespp:Skyberry" -> CropsNHCrops.Skyberry;
            case "berriespp:Tin Oreberry" -> CropsNHCrops.TinOreBerry;
            default -> null;
        };
    }

    private static @Nullable BlockUnderRequirement.BlockUnderTarget getBlockUnderTarget(World world, int x, int y,
        int z) {
        if (y < 0) return null;
        if (world.isAirBlock(x, y, z)) return null;

        // get the block under
        Block block = world.getBlock(x, y, z);
        if (block.getMaterial() == Material.air) return null;
        int meta = world.getBlockMetadata(x, y, z);
        TileEntity te = world.getTileEntity(x, y, z);
        return new BlockUnderRequirement.BlockUnderTarget(block, meta, te);
    }

    private static void addCropsPlusPlusPosteaMigrations() {
        if (!ModUtils.CropsPlusPlus.isModLoaded()) return;

        ItemStackReplacementManager.addItemReplacement("berriespp:Modifier", tag -> {
            short dmg = tag.getShort("Damage");
            switch (dmg) {
                case 0:
                    tag.setShort("id", (short) Item.getIdFromItem(CropsNHItems.materialLeaf));
                    tag.setShort("Damage", (short) MaterialLeafLoader.spaceFlower.getId());
                    return tag;
                case 1:
                    tag.setShort("id", (short) Item.getIdFromItem(CropsNHItems.materialLeaf));
                    tag.setShort("Damage", (short) MaterialLeafLoader.magicEssence.getId());
                    return tag;
                default:
                    return tag;
            }
        });
    }

    private static boolean migrateMissingBPPItemMapping(FMLMissingMappingsEvent.MissingMapping mapping) {
        switch (mapping.name) {
            case "berriespp:itemSpade":
                mapping.remap(CropsNHItems.spade);
                return true;
            case "berriespp:itemLens":
                mapping.remap(CropsNHItems.plantLens);
                return true;
            case "berriespp:foodBerries":
                mapping.remap(CropsNHItems.berry);
                return true;
            case "berriespp:BppPotions":
                mapping.remap(CropsNHItems.bottledAlcohol);
                return true;
            default:
                return false;
        }
    }

    private static boolean migrateMissingIC2ItemMappings(FMLMissingMappingsEvent.MissingMapping mapping) {
        switch (mapping.name) {
            case "IC2:itemFertilizer":
                mapping.remap(CropsNHItems.fertilizer);
                return true;
            case "IC2:itemTerraWart":
                mapping.remap(CropsNHItems.gaiaWart);
                return true;
            case "IC2:blockCrop":
                mapping.remap(CropsNHUtils.getItemFromBlock(CropsNHBlocks.blockCropSticks));
                return true;
            default:
                return false;
        }
    }
}
