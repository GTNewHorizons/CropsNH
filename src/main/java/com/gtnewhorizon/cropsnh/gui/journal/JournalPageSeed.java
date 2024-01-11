package com.gtnewhorizon.cropsnh.gui.journal;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.api.v1.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.v1.IMutation;
import com.gtnewhorizon.cropsnh.farming.CropPlantHandler;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;
import com.gtnewhorizon.cropsnh.farming.mutation.MutationHandler;
import com.gtnewhorizon.cropsnh.gui.Component;
import com.gtnewhorizon.cropsnh.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class JournalPageSeed extends JournalPage {

    private static final ResourceLocation ICON_FRAME = new ResourceLocation(
            Reference.MOD_ID.toLowerCase(),
            "textures/gui/journal/GuiJournalSeedFrame.png");
    private static final ResourceLocation MUTATION_TEMPLATE = new ResourceLocation(
            Reference.MOD_ID.toLowerCase(),
            "textures/gui/journal/GuiJournalMutationTemplate.png");
    private static final ResourceLocation QUESTION_MARK = new ResourceLocation(
            Reference.MOD_ID.toLowerCase(),
            "textures/gui/journal/GuiJournalQuestionMark.png");
    private static final ResourceLocation BRIGHTNESS_BAR = new ResourceLocation(
            Reference.MOD_ID.toLowerCase(),
            "textures/gui/journal/GuiJournalBrightnessBar.png");
    private static final ResourceLocation BRIGHTNESS_FRAME = new ResourceLocation(
            Reference.MOD_ID.toLowerCase(),
            "textures/gui/journal/GuiJournalBrightnessFrame.png");

    private final ArrayList<ItemStack> discoveredSeeds;
    private final int page;

    private CropPlant plant;

    private ArrayList<Component<ItemStack>> fruits;
    private final ArrayList<Component<ItemStack>> seeds;

    public JournalPageSeed(ArrayList<ItemStack> discoveredSeeds, int page) {
        this.discoveredSeeds = discoveredSeeds;
        this.page = page;
        this.plant = CropPlantHandler.getPlantFromStack(discoveredSeeds.get(page));
        this.fruits = getFruits();
        this.seeds = getSeeds();
    }

    @Override
    public ResourceLocation getForeground() {
        return new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/gui/journal/GuiJournalSeedPage.png");
    }

    @Override
    public ArrayList<String> getTooltip(int x, int y) {
        for (Component<ItemStack> component : fruits) {
            if (component.isOverComponent(x, y)) {
                ArrayList<String> toolTip = new ArrayList<>();
                toolTip.add(component.getComponent().getDisplayName());
                return toolTip;
            }
        }
        for (Component<ItemStack> component : seeds) {
            if (component.isOverComponent(x, y)) {
                ArrayList<String> toolTip = new ArrayList<>();
                toolTip.add(component.getComponent().getDisplayName());
                return toolTip;
            }
        }
        return null;
    }

    @Override
    public int getPagesToBrowseOnMouseClick(int x, int y) {
        for (Component<ItemStack> component : seeds) {
            if (component.isOverComponent(x, y)) {
                ItemStack selected = component.getComponent();
                for (int i = 0; i < discoveredSeeds.size(); i++) {
                    ItemStack current = discoveredSeeds.get(i);
                    if (selected.getItem() == current.getItem()
                            && selected.getItemDamage() == current.getItemDamage()) {
                        return i - page;
                    }
                }
                break;
            }
        }
        return 0;
    }

    // *************************** //
    // TEXT TO RENDER ON THIS PAGE //
    // *************************** //

    @Override
    public ArrayList<Component<String>> getTextComponents() {
        ArrayList<Component<String>> textComponents = new ArrayList<>();
        textComponents.add(getTitle());
        textComponents.add(getDescriptionHead());
        textComponents.add(getSeedInformation());
        textComponents.add(getTier());
        textComponents.add(getBrightnessTitle());
        textComponents.add(getFruitTitle());
        textComponents.add(getGrowthTitle());
        textComponents.addAll(getMutationTitles());
        return textComponents;
    }

    private Component<String> getTitle() {
        String text = plant.getSeed().getDisplayName();
        int x = 82;
        int y = 17;
        float scale = 0.8F;
        while (Minecraft.getMinecraft().fontRenderer.getStringWidth(text) * scale > 74) {
            scale = scale - 0.1F;
        }
        return new Component<>(text, x, y, scale, true);
    }

    private Component<String> getDescriptionHead() {
        String text = StatCollector.translateToLocal("cropsnh_journal.information") + ": ";
        int x = 29;
        int y = 31;
        float scale = 0.5F;
        return new Component<>(text, x, y, scale);
    }

    private Component<String> getSeedInformation() {
        String text = StatCollector.translateToLocal(plant.getInformation());
        int x = 29;
        int y = 38;
        float scale = 0.5F;
        return new Component<>(text, x, y, scale);
    }

    private Component<String> getTier() {
        String text = StatCollector.translateToLocal("cropsnh_journal.tier") + ": " + plant.getTier();
        int x = 29;
        int y = 66;
        float scale = 0.5F;
        return new Component<>(text, x, y, scale);
    }

    private Component<String> getBrightnessTitle() {
        String text = StatCollector.translateToLocal("cropsnh_journal.brightness") + ": ";
        int x = 29;
        int y = 76;
        float scale = 0.5F;
        return new Component<>(text, x, y, scale);
    }

    private Component<String> getFruitTitle() {
        String text = StatCollector.translateToLocal("cropsnh_journal.fruits") + ": ";
        int x = 29;
        int y = 95;
        float scale = 0.5F;
        return new Component<>(text, x, y, scale);
    }

    private Component<String> getGrowthTitle() {
        String text = StatCollector.translateToLocal("cropsnh_journal.growthStages") + ": ";
        int x = 29;
        int y = 122;
        float scale = 0.5F;
        return new Component<>(text, x, y, scale);
    }

    private ArrayList<Component<String>> getMutationTitles() {
        String text = StatCollector.translateToLocal("cropsnh_journal.mutations") + ": ";
        int x = 132;
        int y = 13;
        float scale = 0.5F;
        ArrayList<Component<String>> list = new ArrayList<>();
        list.add(new Component<>(text, x, y, scale));
        return list;
    }

    // **************************** //
    // ITEMS TO RENDER ON THIS PAGE //
    // **************************** //

    @Override
    public ArrayList<Component<ItemStack>> getItemComponents() {
        ArrayList<Component<ItemStack>> itemComponents = new ArrayList<>();
        itemComponents.add(getSeed());
        itemComponents.addAll(fruits);
        itemComponents.addAll(seeds);
        return itemComponents;
    }

    private Component<ItemStack> getSeed() {
        ItemStack stack = plant.getSeed();
        int x = 26;
        int y = 11;
        return new Component<>(stack, x, y);
    }

    private ArrayList<Component<ItemStack>> getFruits() {
        if (this.plant == null) {
            this.plant = CropPlantHandler.getPlantFromStack(discoveredSeeds.get(page));
        }
        ArrayList<Component<ItemStack>> fruits = new ArrayList<>();
        ArrayList<ItemStack> allFruits = plant.getAllFruits();
        if (allFruits != null) {
            for (int i = 0; i < allFruits.size(); i++) {
                ItemStack stack = allFruits.get(i);
                if (stack != null && stack.getItem() != null) {
                    int x = 30 + 24 * i;
                    int y = 102;
                    fruits.add(new Component<>(stack, x, y, 16, 16));
                }
            }
        }
        return fruits;
    }

    private ArrayList<Component<ItemStack>> getSeeds() {
        ArrayList<IMutation> completedMutations = getCompletedMutations();
        ArrayList<IMutation> uncompletedMutations = getUncompleteMutations();
        ArrayList<Component<ItemStack>> seeds = new ArrayList<>();
        int y = 1;
        int x = 132;
        for (IMutation mutation : completedMutations) {
            y = y + 20;
            ItemStack resultStack = mutation.getResult();
            ItemStack parent1Stack = mutation.getParents()[0];
            ItemStack parent2Stack = mutation.getParents()[1];
            seeds.add(new Component<>(parent1Stack, x, y, 16, 16));
            seeds.add(new Component<>(parent2Stack, x + 35, y, 16, 16));
            seeds.add(new Component<>(resultStack, x + 69, y, 16, 16));
        }
        for (IMutation mutation : uncompletedMutations) {
            y = y + 20;
            ItemStack parent1Stack = mutation.getParents()[0];
            ItemStack parent2Stack = mutation.getParents()[1];
            seeds.add(new Component<>(parent1Stack, x, y, 16, 16));
            seeds.add(new Component<>(parent2Stack, x + 35, y, 16, 16));
        }
        return seeds;
    }

    private ArrayList<IMutation> getCompletedMutations() {
        ArrayList<IMutation> mutations = getDiscoveredParentMutations();
        mutations.addAll(getDiscoveredChildMutations());
        return mutations;
    }

    private ArrayList<IMutation> getDiscoveredParentMutations() {
        ArrayList<IMutation> allMutations = new ArrayList<>();
        ArrayList<IMutation> mutations = new ArrayList<>();
        allMutations
                .addAll(Arrays.asList(MutationHandler.getInstance().getMutationsFromParent(discoveredSeeds.get(page))));
        for (IMutation mutation : allMutations) {
            if (isMutationDiscovered(mutation)) {
                mutations.add(mutation);
            }
        }
        return mutations;
    }

    private ArrayList<IMutation> getDiscoveredChildMutations() {
        ArrayList<IMutation> allMutations = new ArrayList<>();
        ArrayList<IMutation> mutations = new ArrayList<>();
        allMutations
                .addAll(Arrays.asList(MutationHandler.getInstance().getMutationsFromChild(discoveredSeeds.get(page))));
        for (IMutation mutation : allMutations) {
            if (isMutationDiscovered(mutation)) {
                mutations.add(mutation);
            }
        }
        return mutations;
    }

    private ArrayList<IMutation> getUncompleteMutations() {
        ArrayList<IMutation> allMutations = new ArrayList<>();
        ArrayList<IMutation> mutations = new ArrayList<>();
        allMutations
                .addAll(Arrays.asList(MutationHandler.getInstance().getMutationsFromParent(discoveredSeeds.get(page))));
        for (IMutation mutation : allMutations) {
            if (isMutationHalfDiscovered(mutation)) {
                mutations.add(mutation);
            }
        }
        return mutations;
    }

    private boolean isMutationDiscovered(IMutation mutation) {
        ItemStack resultStack = mutation.getResult();
        return areParentsDiscovered(mutation) && isSeedDiscovered(resultStack);
    }

    private boolean isMutationHalfDiscovered(IMutation mutation) {
        ItemStack resultStack = mutation.getResult();
        return areParentsDiscovered(mutation) && !isSeedDiscovered(resultStack);

    }

    private boolean areParentsDiscovered(IMutation mutation) {
        ItemStack parent1Stack = mutation.getParents()[0];
        ItemStack parent2Stack = mutation.getParents()[1];
        return isSeedDiscovered(parent1Stack) && isSeedDiscovered(parent2Stack);
    }

    private boolean isSeedDiscovered(ItemStack seed) {
        for (ItemStack current : discoveredSeeds) {
            if (current.getItem() == seed.getItem() && current.getItemDamage() == seed.getItemDamage()) {
                return true;
            }
        }
        return false;
    }

    // ******************************* //
    // TEXTURES TO RENDER ON THIS PAGE //
    // ******************************* //

    @Override
    public ArrayList<Component<ResourceLocation>> getTextureComponents() {
        ArrayList<Component<ResourceLocation>> textureComponents = new ArrayList<>();
        textureComponents.add(getSoil());
        textureComponents.addAll(getBrightnessTextures());
        textureComponents.addAll(getFruitIconFrames());
        textureComponents.addAll(getMutationTemplates());
        return textureComponents;
    }

    private Component<ResourceLocation> getSoil() {
        ItemStack seed = plant.getSeed();
        BlockWithMeta soil = CropPlantHandler.getGrowthRequirement(seed.getItem(), seed.getItemDamage()).getSoil();
        ResourceLocation texture;
        if (soil != null) {
            texture = getBlockResource(soil.getBlock().getIcon(1, soil.getMeta()));
        } else {
            texture = getBlockResource(Blocks.farmland.getIcon(1, 7));
        }
        int x = 26;
        int y = 11;
        return new Component<>(texture, x, y, 16, 16);
    }

    /**
     * Retrieves a resource location from a <em>block</em> icon.
     *
     * @param icon the icon to get the resource location from.
     * @return the resource location for the icon, or null.
     */
    // TODO: get rid of this method and do it the legit way
    private ResourceLocation getBlockResource(IIcon icon) {
        if (icon == null) {
            return null;
        }
        String path = icon.getIconName();
        String domain = path.substring(0, path.indexOf(":") + 1);
        String file = path.substring(path.indexOf(':') + 1);
        return new ResourceLocation(domain + "textures/blocks/" + file + ".png");
    }

    private ArrayList<Component<ResourceLocation>> getBrightnessTextures() {
        ArrayList<Component<ResourceLocation>> textures = new ArrayList<>();
        int x = 29;
        int y = 81;
        int u = 4;
        int v = 8;
        int[] brightnessRange = plant.getGrowthRequirement().getBrightnessRange();
        textures.add(new Component<>(BRIGHTNESS_BAR, x, y, 2 + 16 * u, v));
        textures.add(new Component<>(BRIGHTNESS_FRAME, x + u * brightnessRange[0], y, 1, v));
        textures.add(new Component<>(BRIGHTNESS_FRAME, x + u * brightnessRange[1] + 1, y, 1, v));
        textures.add(
                new Component<>(
                        BRIGHTNESS_FRAME,
                        x + u * brightnessRange[0] + 1,
                        y,
                        u * (brightnessRange[1] - brightnessRange[0]),
                        1));
        textures.add(
                new Component<>(
                        BRIGHTNESS_FRAME,
                        x + u * brightnessRange[0] + 1,
                        y + v - 1,
                        u * (brightnessRange[1] - brightnessRange[0]),
                        1));
        return textures;
    }

    private ArrayList<Component<ResourceLocation>> getFruitIconFrames() {
        if (this.fruits == null) {
            this.fruits = getFruits();
        }
        ArrayList<Component<ResourceLocation>> components = new ArrayList<>();
        for (int i = 0; i < fruits.size(); i++) {
            components.add(new Component<>(ICON_FRAME, 29 + 24 * i, 101, 18, 18));
        }
        return components;
    }

    private ArrayList<Component<ResourceLocation>> getMutationTemplates() {
        int n = getCompletedMutations().size();
        int l = getUncompleteMutations().size();
        ArrayList<Component<ResourceLocation>> components = new ArrayList<>();
        int y = 0;
        for (int i = 0; i < n; i++) {
            y = y + 20;
            components.add(new Component<>(MUTATION_TEMPLATE, 132, y, 86, 18));
        }
        for (int i = 0; i < l; i++) {
            y = y + 20;
            components.add(new Component<>(MUTATION_TEMPLATE, 132, y, 86, 18));
            components.add(new Component<>(QUESTION_MARK, 201, y + 1, 16, 16));
        }
        return components;
    }

    // ******************************* //
    // TEXTURES TO RENDER ON THIS PAGE //
    // ******************************* //

    @Override
    public ArrayList<ResourceLocation> getTextureMaps() {
        ArrayList<ResourceLocation> list = new ArrayList<>();
        list.add(TextureMap.locationBlocksTexture);
        return list;
    }

    @Override
    public ArrayList<Component<IIcon>> getIconComponents(ResourceLocation textureMap) {
        if (textureMap != TextureMap.locationBlocksTexture) {
            return null;
        }
        return getGrowthStageIcons();
    }

    private ArrayList<Component<IIcon>> getGrowthStageIcons() {
        ArrayList<Component<IIcon>> growthStages = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int x = 30 + 24 * (i % 4);
            int y = 129 + 24 * (i / 4);
            growthStages.add(new Component<>(plant.getPlantIcon(i), x, y, 16, 16));
        }
        return growthStages;
    }
}
