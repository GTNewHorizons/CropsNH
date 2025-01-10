package com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import codechicken.nei.config.DataDumper;

public class SoilRegistryDumper extends DataDumper {

    public SoilRegistryDumper() {
        super("tools.dump.cropsnh.soilRegistry");
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
        return ".txt";
    }

    @Override
    public void dumpTo(File file) throws IOException {
        PrintWriter w = new PrintWriter(file);
        w.print(SoilRegistry.instance.dump());
        w.flush();
        w.close();
    }
}
