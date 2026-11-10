package com.gtnewhorizon.cropsnh;

import java.util.concurrent.locks.ReentrantLock;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.gtnewhorizon.cropsnh.api.CropCard;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

public class BeforeAllHook implements BeforeAllCallback {

    // Add to the class
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static boolean INITIALIZED = false;

    public static final String CROPID_INVALID = Reference.MOD_ID + "test:invalid_id";
    public static final Item ITEM_ALT_SEED = new Item();
    public static final ItemStack STACK_ALT_SEED = new ItemStack(ITEM_ALT_SEED, 1, 0);
    public static final CropCard CROP_ALT_SEED = new TestCrop(Reference.MOD_ID + "test:with_alt_seed");
    public static final CropCard CROP_ANALYZED = new TestCrop(Reference.MOD_ID + "test:analyzed");
    public static final CropCard CROP_NOT_ANALYZED = new TestCrop(Reference.MOD_ID + "test:not_analyzed");
    public static final CropCard CROP_FALLBACK = new TestCrop(Reference.MOD_ID + "test:fallback");
    public static final SeedStats STATS_ANALYZED = new SeedStats((byte) 10, (byte) 15, (byte) 20, true);
    public static final SeedStats STATS_NOT_ANALYZED = new SeedStats((byte) 11, (byte) 16, (byte) 21, false);
    public static final ItemStack STACK_ANALYZED = CROP_ANALYZED.getSeedItem(STATS_ANALYZED);
    public static final ItemStack STACK_NOT_ANALYZED = CROP_NOT_ANALYZED.getSeedItem(STATS_NOT_ANALYZED);

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        LOCK.lock();
        try {
            if (!INITIALIZED) {
                Reference.IS_GAME_LOADED = false;
                CROP_ALT_SEED.addAlternateSeed(STACK_ALT_SEED);
                CropRegistry.instance.register(CROP_ALT_SEED);
                CropRegistry.instance.register(CROP_NOT_ANALYZED);
                CropRegistry.instance.register(CROP_ANALYZED);
                CropsNHUtils.DEBUG_FALLBACK_CROP = CROP_FALLBACK;
                CropRegistry.instance.register(CROP_FALLBACK);
            }
            INITIALIZED = true;
        } finally {
            LOCK.unlock();
        }
    }
}
