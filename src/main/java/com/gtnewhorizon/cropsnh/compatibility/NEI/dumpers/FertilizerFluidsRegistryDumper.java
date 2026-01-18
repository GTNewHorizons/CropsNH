package com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.gtnewhorizon.cropsnh.farming.registries.FertilizerRegistry;
import com.gtnewhorizon.cropsnh.reference.Reference;

import codechicken.nei.config.DataDumper;

public class FertilizerFluidsRegistryDumper extends DataDumper {

    public FertilizerFluidsRegistryDumper() {
        super("tools.dump." + Reference.MOD_ID + ".fertilizerFluids");
    }

    @Override
    public String renderName() {
        return translateN(this.name);
    }

    @Override
    public String modeButtonText() {
        return "";
    }

    @Override
    public String[] header() {
        return new String[0];
    }

    @Override
    public Iterable<String[]> dump(int mode) {
        return null;
    }

    @Override
    public String getFileExtension() {
        return ".csv";
    }

    @Override
    public void dumpTo(File file) throws IOException {
        PrintWriter w = new PrintWriter(file);
        w.print(FertilizerRegistry.instance.dumpFluidsCSV());
        w.flush();
        w.close();
    }

    @Override
    public int modeCount() {
        return 0;
    }
}
