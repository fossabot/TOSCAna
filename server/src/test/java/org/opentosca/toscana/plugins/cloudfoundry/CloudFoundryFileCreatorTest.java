package org.opentosca.toscana.plugins.cloudfoundry;

import java.io.File;

import org.opentosca.toscana.core.BaseUnitTest;
import org.opentosca.toscana.core.plugin.PluginFileAccess;
import org.opentosca.toscana.core.transformation.logging.Log;
import org.opentosca.toscana.plugins.lifecycle.AbstractLifecycle;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.opentosca.toscana.plugins.cloudfoundry.CloudFoundryFileCreator.MANIFEST;
import static org.opentosca.toscana.plugins.cloudfoundry.CloudFoundryFileCreator.FILEPRAEFIX_DEPLOY;
import static org.opentosca.toscana.plugins.cloudfoundry.CloudFoundryFileCreator.FILESUFFIX_DEPLOY;
import static org.opentosca.toscana.plugins.cloudfoundry.CloudFoundryFileCreator.BUILDPACK_FILEPATH_PHP;
import static org.opentosca.toscana.plugins.cloudfoundry.CloudFoundryFileCreator.NAMEBLOCK;
import static org.opentosca.toscana.plugins.cloudfoundry.CloudFoundryManifestAttribute.APPLICATIONS_SECTION;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.opentosca.toscana.plugins.cloudfoundry.CloudFoundryManifestAttribute.ENVIRONMENT;
import static org.opentosca.toscana.plugins.cloudfoundry.CloudFoundryManifestAttribute.SERVICE;

public class CloudFoundryFileCreatorTest extends BaseUnitTest {
    private static CloudFoundryFileCreator fileCreator;
    private static CloudFoundryApplication testApp;

    @Mock
    private Log log;
    private File targetDir;
    private String appName;
    private final String outputPath = AbstractLifecycle.SCRIPTS_DIR_PATH;

    @Before
    public void setUp() {
        appName = "testApp";
        testApp = new CloudFoundryApplication();
        testApp.setName(appName);
        File sourceDir = new File(tmpdir, "sourceDir");
        targetDir = new File(tmpdir, "targetDir");
        sourceDir.mkdir();
        targetDir.mkdir();
        PluginFileAccess fileAccess = new PluginFileAccess(sourceDir, targetDir, log);
        fileCreator = new CloudFoundryFileCreator(fileAccess, testApp);
    }

    @Test
    public void createFiles() throws Exception {
        fileCreator.createFiles();
        File targetFile = new File(targetDir, MANIFEST);
        File deployFile = new File(targetDir, outputPath + FILEPRAEFIX_DEPLOY + appName + FILESUFFIX_DEPLOY);
        File buildPackAdditions = new File(targetDir, BUILDPACK_FILEPATH_PHP);

        assertTrue(targetFile.exists());
        assertTrue(deployFile.exists());
        assertTrue(buildPackAdditions.exists());
    }

    @Test
    public void contentManifest() throws Exception {
        fileCreator.createFiles();
        File targetFile = new File(targetDir, MANIFEST);
        String manifestContent = FileUtils.readFileToString(targetFile);
        String expectedManifestContent = String.format("---\n%s:\n- %s: %s\n  %s:\n  %s:\n", 
            APPLICATIONS_SECTION.getName(), NAMEBLOCK, appName, ENVIRONMENT.getName(), SERVICE.getName());

        assertEquals(expectedManifestContent, manifestContent);
    }

    @Test
    public void contentDeploy() throws Exception {
        fileCreator.createFiles();
        File targetFile = new File(targetDir, outputPath + FILEPRAEFIX_DEPLOY + appName + FILESUFFIX_DEPLOY);
        String manifestContent = FileUtils.readFileToString(targetFile);
        String expectedDeployContent = "#!/bin/sh\n" +
            "source util/*\ncheck \"cf\"\ncf push " + appName + "\n";
        assertEquals(expectedDeployContent, manifestContent);
    }
}
