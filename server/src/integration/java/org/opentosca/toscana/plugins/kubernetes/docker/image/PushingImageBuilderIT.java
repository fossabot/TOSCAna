package org.opentosca.toscana.plugins.kubernetes.docker.image;

import org.opentosca.toscana.core.transformation.TransformationContext;
import org.opentosca.toscana.plugins.kubernetes.docker.util.DockerRegistryCredentials;

import org.junit.Assume;

public class PushingImageBuilderIT  extends ExportingImageBuilderIT {

    private DockerRegistryCredentials creds;

    @Override
    public void init() {
        Assume.assumeNotNull(
            System.getenv("DH_REPOSITORY"),
            System.getenv("DH_USERNAME"),
            System.getenv("DH_PASSWORD"),
            System.getenv("DH_URL")
        );

        creds = new DockerRegistryCredentials(
            System.getenv("DH_URL"),
            System.getenv("DH_USERNAME"),
            System.getenv("DH_PASSWORD"),
            System.getenv("DH_REPOSITORY")
        );
    }

    @Override
    public ImageBuilder instantiateImageBuilder(TransformationContext context) throws Exception {
        return new PushingImageBuilder(
            creds,
            //Make the Image tag kind of unique to prevent collisions when pushing to a proper registry
            "sha-test-" + Long.toString(System.currentTimeMillis(), 16),
            WORKING_DIR_SUBFOLDER_NAME,
            context
        );
    }

    @Override
    public void validate() throws Exception {
        
    }
}
