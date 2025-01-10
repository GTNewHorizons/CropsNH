package com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.gtnewhorizon.cropsnh.farming.registries.MutationRegistry;

import codechicken.nei.config.DataDumper;

public class DeterministicMutationRegistryDumper extends DataDumper {

    public DeterministicMutationRegistryDumper() {
        super("tools.dump.cropsnh.deterministicMutations");
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
        w.print(MutationRegistry.instance.dumpDeterministicMutations());
        w.flush();
        w.close();
    }
}
