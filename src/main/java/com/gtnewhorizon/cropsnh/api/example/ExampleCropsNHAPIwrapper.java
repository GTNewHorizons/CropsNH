package com.gtnewhorizon.cropsnh.api.example;

import cpw.mods.fml.common.Loader;
import net.minecraft.world.World;

/**
 * This is an example implementation of how to use the CropsNH API, you don't have to do it this way,
 * but I highly recommend it doing this way if you don't know how to use or reference the API without shipping it with your mod.
 * If done correctly this will never crash if CropsNH is not loaded
 */
public class ExampleCropsNHAPIwrapper {
    private static ExampleCropsNHAPIwrapper instance;

    /**
     * This method returns the wrapper instance, and initializes it if hasn't been initialized yet
     * If CropsNH is not present, it sets it to a placeholder (which is this class) which returns default values and never references the CropsNH API.
     * If CropsNH is loaded, it sets it to an actual class forwarding the calls to the CropsNH API (see the  {@link ExampleCropsNHAPIimplementation} class)
     *
     * @return the CropsNHAPI wrapper
     */
    public static ExampleCropsNHAPIwrapper getInstance() {
        if(instance == null) {
            if(Loader.isModLoaded("CropsNH")) {
                instance = new ExampleCropsNHAPIimplementation();
            } else {
                instance = new ExampleCropsNHAPIwrapper();
            }
        }
        return instance;
    }

    protected ExampleCropsNHAPIwrapper() {}


    /**
     * Define methods here which return values that you need, override them in the class which does reference CropsNH API methods.
     * Here they should just return a default value whichever fits your application.
     *
     * If you need to work with implementations of interfaces, you can use the ever so handy {@Link Optional.Interface} annotation
     */

    /**
     * Method to check if the API is properly wrapped
     */
    public boolean isOk() {
        return false;
    }

    /**
     * Example method, this one gets the stats of a crop
     */
    public SeedStatsExample exampleMethodGetSeedStats(World world, int x, int y, int z) {
        return new SeedStatsExample((short) -1, (short) -1, (short) -1, false);
    }

    /**
     * Example method, this one gets the stat cap imposed to cropsnh
     */
    public short exampleMethodGetSeedStatsCap() {
        return -1;
    }
}
