package com.gtnewhorizon.cropsnh.farming.mutation;

import com.gtnewhorizon.cropsnh.api.v1.IMutationEngine;
import com.gtnewhorizon.cropsnh.api.v1.IStatCalculator;
import com.gtnewhorizon.cropsnh.farming.mutation.statcalculator.StatCalculator;

public abstract class BaseStrategy {

    protected IMutationEngine engine;
    protected final IStatCalculator calculator;

    public BaseStrategy(IMutationEngine mutationEngine) {
        this.engine = mutationEngine;
        this.calculator = StatCalculator.getInstance();
    }
}
