package com.gtnewhorizon.cropsnh.api.example;

import com.gtnewhorizon.cropsnh.api.API;
import com.gtnewhorizon.cropsnh.api.APIBase;
import com.gtnewhorizon.cropsnh.api.v1.APIv1;
import com.gtnewhorizon.cropsnh.api.v1.ISeedStats;
import net.minecraft.world.World;

public class ExampleCropsNHAPIimplementation extends ExampleCropsNHAPIwrapper {
    private APIv1 api;
    private final boolean ok;

    /**
     * Constructor grabs the wanted API version and checks if it is correctly initialized
     */
    protected ExampleCropsNHAPIimplementation() {
        super();
        APIBase apiObj = API.getAPI(1);
        if((apiObj instanceof APIv1)) {
            api = (APIv1) apiObj;
            ok = api.getStatus().isOK();
        } else {
            ok = false;
        }
    }


    /**
     * Here are the actual implementations for the methods you need to call API methods
     * Note the isOk() calls, this is not necessary, but guarantees that your implementation will not start returning values you don't expect,
     * for instance when the api version you use becomes {@Link APIStatus.ERROR} or {@Link APIStatus.BACKLEVEL_UNSUPPORTED}
     */

    /**
     * Method to check if the API is properly wrapped
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * Example method, this one gets the stats of a crop
     */
    public SeedStatsExample exampleMethodGetSeedStats(World world, int x, int y, int z) {
        if(isOk()) {
            ISeedStats stats = api.getStats(world, x, y , z);
            return new SeedStatsExample(stats.getGrowth(), stats.getGain(), stats.getStrength(), stats.isAnalyzed());
        } else {
            return super.exampleMethodGetSeedStats(world, x, y, z);
        }
    }

    /**
     * Example method, this one gets the stat cap imposed to cropsnh
     */
    public short exampleMethodGetSeedStatsCap() {
        return isOk()?api.getStatCap():super.exampleMethodGetSeedStatsCap();
    }
}
