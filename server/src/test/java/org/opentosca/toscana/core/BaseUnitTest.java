package org.opentosca.toscana.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 If files need to get written on disk during tests, use {@link #temporaryFolder}
 */
@RunWith(JUnit4.class)
public abstract class BaseUnitTest extends BaseTest {

    /**
     Grants disk access. Is reset before every test method.
     */
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder(PROJECT_ROOT);
    private static final String STATIC_TMPDIR = "testsuite-tmpdir-static";
    protected File tmpdir;
    protected static File staticTmpDir;

    @BeforeClass
    public final static void offerStaticTmpDir() throws IOException {
        staticTmpDir = new File(PROJECT_ROOT, STATIC_TMPDIR);
        FileUtils.deleteDirectory(staticTmpDir);
        staticTmpDir.mkdir();
    }

    @Before
    public final void initTmpdir() throws IOException {
        tmpdir = temporaryFolder.newFolder();
    }

    @AfterClass
    public static void cleanUpDisk() throws IOException, InterruptedException {
        File[] files = PROJECT_ROOT.listFiles((file1, s) -> s.matches("junit[0-9]+"));
        for (File file : files) {
            FileUtils.deleteDirectory(file);
        }
    }

    @AfterClass
    public final static void cleanupStaticTmpDir() throws IOException {
        FileUtils.deleteDirectory(staticTmpDir);
    }
}
