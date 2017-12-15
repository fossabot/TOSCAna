package org.opentosca.toscana.plugins.kubernetes.docker.image;

import org.opentosca.toscana.core.transformation.TransformationContext;
import org.opentosca.toscana.plugins.kubernetes.docker.util.DockerRegistryCredentials;

import com.spotify.docker.client.DockerClient;

public class RegistryImagebuilder extends ImageBuilder {
    private final DockerRegistryCredentials credentials;

    public RegistryImagebuilder(
        DockerRegistryCredentials credentials,
        String tag,
        String dockerWorkDir,
        TransformationContext context
    ) {
        super(tag, dockerWorkDir, context);
        this.credentials = credentials;
    }

    @Override
    public void storeImage() throws Exception {
        DockerClient client = getDockerClient();
        client.auth(credentials.toAuthConfig());
        
        client.push(getTag(), this);
    }
}
