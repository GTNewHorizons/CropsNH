package com.gtnewhorizon.cropsnh.init;

import com.gtnewhorizon.cropsnh.api.IMutationPool;
import com.gtnewhorizon.cropsnh.farming.registries.MutationRegistry;

public class CropsNHMutationPools {

    public static final IMutationPool flower = MutationRegistry.instance.register("flower");
    public static final IMutationPool food = MutationRegistry.instance.register("food");
    public static final IMutationPool decorative = MutationRegistry.instance.register("decorative");
    public static final IMutationPool passiveMob = MutationRegistry.instance.register("passiveMob");
    public static final IMutationPool aggresiveMob = MutationRegistry.instance.register("aggresiveMob");
    public static final IMutationPool mushroom = MutationRegistry.instance.register("mushroom");
    public static final IMutationPool wood = MutationRegistry.instance.register("wood");
    public static final IMutationPool cactus = MutationRegistry.instance.register("cactus");
    public static final IMutationPool nether = MutationRegistry.instance.register("nether");
    public static final IMutationPool lowTierOreBerries = MutationRegistry.instance.register("lowTierOreBerries");
    public static final IMutationPool stoneLilies = MutationRegistry.instance.register("stoneLilies");
    public static final IMutationPool end = MutationRegistry.instance.register("end");
    public static final IMutationPool desert = MutationRegistry.instance.register("desert");
    public static final IMutationPool jungle = MutationRegistry.instance.register("jungle");
}
