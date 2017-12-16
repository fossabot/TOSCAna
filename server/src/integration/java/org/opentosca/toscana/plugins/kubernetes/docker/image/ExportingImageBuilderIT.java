package org.opentosca.toscana.plugins.kubernetes.docker.image;

import java.io.File;

import org.opentosca.toscana.IntegrationTest;
import org.opentosca.toscana.core.transformation.TransformationContext;
import org.opentosca.toscana.plugins.kubernetes.docker.BaseDockerfileTest;
import org.opentosca.toscana.plugins.kubernetes.docker.DockerTestUtils;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assume.assumeTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Category(IntegrationTest.class)
public class ExportingImageBuilderIT extends BaseDockerfileTest {

    private static final Logger logger = LoggerFactory.getLogger(ExportingImageBuilderIT.class);
    private static final String SHA_TEST_FILE_NAME = "sha-test.tar.gz";

    @Test
    public void testBuildShaImage() throws Exception {
        assumeTrue(DockerTestUtils.isDockerAvailable());
        //Create Dockerfile and the corresponding Binary file
        buildSHADockerfile();

        init();
        
        TransformationContext ctx = mock(TransformationContext.class);
        when(ctx.getPluginFileAccess()).thenReturn(access);
        when(ctx.getLogger((Class<?>) any(Class.class))).thenReturn(LoggerFactory.getLogger("Mock Logger"));
        
        ImageBuilder imageBuilder = instantiateImageBuilder(ctx);
        logger.info("Building Image");
        imageBuilder.buildImage();
        logger.info("Storing Image");
        imageBuilder.storeImage();
        logger.info("Cleanup");
        imageBuilder.cleanup();
        validate();
    }
    
    public void init() {
        // noop for this test
    }
    
    public ImageBuilder instantiateImageBuilder(TransformationContext context) throws Exception {
        return new ExportingImageBuilder(
            SHA_TEST_FILE_NAME,
            "toscana/sha256-test:test",
            WORKING_DIR_SUBFOLDER_NAME,
            context
        );
    }
    
    public void validate() throws Exception {
        File d = new File(access.getAbsolutePath(SHA_TEST_FILE_NAME));

        assertTrue(d.length() > 120 * 1024 * 1024);
    }
}
