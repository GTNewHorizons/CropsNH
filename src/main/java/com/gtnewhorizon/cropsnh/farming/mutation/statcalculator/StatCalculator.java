package com.gtnewhorizon.cropsnh.farming.mutation.statcalculator;

import java.util.List;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.v1.ICrop;
import com.gtnewhorizon.cropsnh.api.v1.ISeedStats;
import com.gtnewhorizon.cropsnh.api.v1.IStatCalculator;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;

public abstract class StatCalculator implements IStatCalculator {

    private static IStatCalculator instance;

    protected StatCalculator() {}

    public static IStatCalculator getInstance() {
        if (instance == null) {
            if (ConfigurationHandler.hardCoreStats) {
                instance = new StatCalculatorHardcore();
            } else {
                instance = new StatCalculatorNormal();
            }
        }
        return instance;
    }

    public static void setStatCalculator(IStatCalculator calculator) {
        instance = calculator;
    }

    public abstract ISeedStats calculateStats(ItemStack result, List<? extends ICrop> input, boolean mutation);
}
