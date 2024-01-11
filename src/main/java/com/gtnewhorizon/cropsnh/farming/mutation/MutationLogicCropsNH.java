package com.gtnewhorizon.cropsnh.farming.mutation;

import com.gtnewhorizon.cropsnh.api.v1.ICrossOverResult;
import com.gtnewhorizon.cropsnh.api.v1.IMutationEngine;
import com.gtnewhorizon.cropsnh.api.v1.IMutationLogic;

public class MutationLogicCropsNH implements IMutationLogic {

    private static final MutationLogicCropsNH INSTANCE = new MutationLogicCropsNH();

    public static MutationLogicCropsNH getInstance() {
        return INSTANCE;
    }

    private MutationLogicCropsNH() {}

    @Override
    public ICrossOverResult getSpreadingResult(IMutationEngine engine) {
        return (new SpreadStrategy(engine)).executeStrategy();
    }

    @Override
    public ICrossOverResult getMutationResult(IMutationEngine engine) {
        return (new MutateStrategy(engine)).executeStrategy();
    }
}
