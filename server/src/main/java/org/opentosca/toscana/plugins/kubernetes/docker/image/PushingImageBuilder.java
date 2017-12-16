package org.opentosca.toscana.plugins.kubernetes.docker.image;

import org.opentosca.toscana.core.transformation.TransformationContext;
import org.opentosca.toscana.plugins.kubernetes.docker.util.DockerRegistryCredentials;

import com.spotify.docker.client.DockerClient;

import static org.opentosca.toscana.plugins.kubernetes.docker.mapper.MapperConstants.DOCKER_HUB_URL;

public class PushingImageBuilder extends ImageBuilder {
    private final DockerRegistryCredentials credentials;

    public PushingImageBuilder(
        DockerRegistryCredentials credentials,
        String tag,
        String dockerWorkDir,
        TransformationContext context
    ) {
        super(tag, dockerWorkDir, context);
        this.credentials = credentials;
    }

    @Override
    protected String getTag() {
        String tag = String.format(
            "%s%s/%s:%s",
            credentials.getRegistryURL(),
            credentials.getUsername(),
            credentials.getRepository(),
            super.getTag()
        );

        logger.info("The tag of the image built is '{}'", tag);

        return tag;
    }

    @Override
    public void storeImage() throws Exception {
        DockerClient client = getDockerClient();
        client.auth(credentials.toAuthConfig());

        client.push(getTag(), this);
    }
}
