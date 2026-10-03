package com.gtnewhorizon.cropsnh.test;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.commons.io.output.CloseShieldOutputStream;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.reporting.legacy.xml.LegacyXmlReportGeneratingListener;

import com.gtnewhorizon.cropsnh.utility.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;

// Most of these don't matter as this mod never gets published
@Mod(modid = "cropsnhtestmod", name = "CropsNH Dev Tests", version = "1.0", dependencies = "required-after:cropsnh")
public class CropsNHTestMod {

    @EventHandler
    public void preInit(FMLPreInitializationEvent ev) {}

    @EventHandler
    public void onServerStarted(FMLServerStartedEvent startedEv) {
        LogHelper.debug("Running CropsNH Unit Tests");
        runTests();
        LogHelper.debug("Running CropsNH Unit Tests completed");
    }

    public void runTests() {
        // https://junit.org/junit5/docs/current/user-guide/#launcher-api
        System.setProperty("junit.platform.reporting.open.xml.enabled", "false");
        final Path testsXmlOutDir = FileSystems.getDefault()
            .getPath("./junit-out/")
            .toAbsolutePath();
        final File testsXmlOutDirFile = testsXmlOutDir.toFile();
        testsXmlOutDirFile.mkdirs();
        {
            File[] fileList = testsXmlOutDirFile.listFiles();
            if (fileList != null) {
                for (File child : fileList) {
                    if (child.isFile() && child.getName()
                        .endsWith(".xml")) {
                        child.delete();
                    }
                }
            }
        }
        final LauncherDiscoveryRequest discovery = LauncherDiscoveryRequestBuilder.request()
            .selectors(DiscoverySelectors.selectPackage("com.gtnewhorizon.cropsnh.test"))
            .build();
        final SummaryGeneratingListener summaryGenerator = new SummaryGeneratingListener();
        final TestExecutionSummary summary;
        try (PrintWriter stderrWriter = new PrintWriter(new CloseShieldOutputStream(System.err), true)) {
            final LegacyXmlReportGeneratingListener xmlGenerator = new LegacyXmlReportGeneratingListener(
                testsXmlOutDir,
                stderrWriter);
            try (LauncherSession session = LauncherFactory.openSession()) {
                final Launcher launcher = session.getLauncher();
                final TestPlan plan = launcher.discover(discovery);
                launcher.registerTestExecutionListeners(summaryGenerator, xmlGenerator);
                launcher.execute(plan);
            }
            summary = summaryGenerator.getSummary();

            summary.printFailuresTo(stderrWriter, 32);
            summary.printTo(stderrWriter);
            stderrWriter.flush();
        }
        // Throw an exception if running via `runServer`
        if (summary.getTotalFailureCount() > 0 && FMLCommonHandler.instance()
            .getSide()
            .isServer()) {
            throw new RuntimeException("Some of the unit tests failed to execute, check the log for details");
        }
    }
}
