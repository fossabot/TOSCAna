package org.opentosca.toscana.plugins.kubernetes;

import java.io.File;

import org.opentosca.toscana.core.transformation.Transformation;
import org.opentosca.toscana.core.transformation.properties.PropertyInstance;
import org.opentosca.toscana.model.EffectiveModel;
import org.opentosca.toscana.plugins.BaseTransformTest;
import org.opentosca.toscana.plugins.kubernetes.docker.DockerTestUtils;
import org.opentosca.toscana.plugins.kubernetes.docker.mapper.MapperConstants;
import org.opentosca.toscana.plugins.kubernetes.docker.mapper.MapperTest;
import org.opentosca.toscana.plugins.testdata.KubernetesLampApp;

import org.apache.commons.io.FileUtils;

import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.mock;

public class KubernetesLampIT extends BaseTransformTest {

    public KubernetesLampIT() throws Exception {
        super(new KubernetesPlugin(MapperTest.init()));
    }

    @Override
    protected EffectiveModel getModel() {
        return new EffectiveModel(KubernetesLampApp.getLampModel());
    }

    @Override
    protected void checkAssumptions() {
        //Skip the test if a docker daemon is not available
        assumeTrue(DockerTestUtils.isDockerAvailable());
    }

    @Override
    protected void onSuccess(File outputDir) throws Exception {
        //Do Nothing
    }

    @Override
    protected void onFailure(File outputDir, Exception e) {
        fail();
    }

    @Override
    protected void copyArtifacts(File contentDir) throws Exception {
        File inputDir = new File(getClass().getResource("/kubernetes/csars/lamp").getFile());
        FileUtils.copyDirectory(inputDir, contentDir);
    }

    @Override
    protected PropertyInstance getProperties() {
        PropertyInstance props = new PropertyInstance(plugin.getPlatform().properties, mock(Transformation.class));

        props.setPropertyValue(KubernetesPlugin.DOCKER_REGISTRY_URL_PROPERTY_KEY, MapperConstants.DOCKER_HUB_URL);
        props.setPropertyValue(KubernetesPlugin.DOCKER_PUSH_TO_REGISTRY_PROPERTY_KEY, "true");
        props.setPropertyValue(KubernetesPlugin.DOCKER_REGISTRY_REPOSITORY_PROPERTY_KEY, System.getenv("DH_REPOSITORY"));
        props.setPropertyValue(KubernetesPlugin.DOCKER_REGISTRY_USERNAME_PROPERTY_KEY, System.getenv("DH_USERNAME"));
        props.setPropertyValue(KubernetesPlugin.DOCKER_REGISTRY_PASSWORD_PROPERTY_KEY, System.getenv("DH_PASSWORD"));

        return props;
    }
}
