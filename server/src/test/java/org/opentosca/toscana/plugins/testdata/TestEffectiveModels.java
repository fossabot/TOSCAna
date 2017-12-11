package org.opentosca.toscana.plugins.testdata;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.opentosca.toscana.model.EffectiveModel;
import org.opentosca.toscana.model.artifact.Artifact;
import org.opentosca.toscana.model.artifact.Repository;
import org.opentosca.toscana.model.capability.AdminEndpointCapability;
import org.opentosca.toscana.model.capability.ContainerCapability;
import org.opentosca.toscana.model.capability.DockerContainerCapability;
import org.opentosca.toscana.model.capability.OsCapability;
import org.opentosca.toscana.model.datatype.Port;
import org.opentosca.toscana.model.node.Compute;
import org.opentosca.toscana.model.node.ContainerRuntime;
import org.opentosca.toscana.model.node.DockerApplication;
import org.opentosca.toscana.model.node.RootNode;
import org.opentosca.toscana.model.operation.Operation;
import org.opentosca.toscana.model.operation.StandardLifecycle;
import org.opentosca.toscana.model.relation.AttachesTo;
import org.opentosca.toscana.model.requirement.BlockStorageRequirement;
import org.opentosca.toscana.model.requirement.DockerHostRequirement;

import com.google.common.collect.Sets;

public class TestEffectiveModels {

    public static EffectiveModel getSingleComputeNodeModel() {
        Set<Class<? extends RootNode>> validSourceTypes = new HashSet<>();
        validSourceTypes.add(Compute.class);
        ContainerCapability host = ContainerCapability.builder()
            .memSizeInMB(1024)
            .diskSizeInMB(2000)
            .numCpus(1)
            .validSourceTypes(validSourceTypes)
            .build();

        AdminEndpointCapability computeAdminEndpointCap = AdminEndpointCapability
            .builder("127.0.0.1", new Port(80))
            .build();
        AttachesTo attachesTo = AttachesTo
            .builder("mount")
            .build();
        BlockStorageRequirement blockStorageRequirement = BlockStorageRequirement
            .builder(attachesTo)
            .build();
        OsCapability os = OsCapability.builder()
            .type(OsCapability.Type.WINDOWS)
            .build();
        Compute computeNode = Compute
            .builder("server", os, computeAdminEndpointCap, blockStorageRequirement)
            .host(host)
            .build();
        return new EffectiveModel(Sets.newHashSet(computeNode));
    }

    public static Set<RootNode> getMinimalDockerApplication() throws MalformedURLException {
        DockerContainerCapability containerCapability = DockerContainerCapability.builder().build();
        ContainerRuntime dockerRuntime = ContainerRuntime
            .builder("dockerRuntime")
            .containerHost(containerCapability)
            .build();
        DockerHostRequirement host = DockerHostRequirement.builder()
            .fulfiller(dockerRuntime)
            .build();
        Repository repository = Repository
            .builder("docker_hub", new URL("https://registry.hub.docker.com/"))
            .build();
        Artifact artifact = Artifact
            .builder("my_image", "nfode/simpletaskapp:v1")
            .repository(repository)
            .build();
        Operation create = Operation.builder()
            .artifact(artifact)
            .build();
        StandardLifecycle standardLifecycle = StandardLifecycle.builder()
            .create(create)
            .build();

        DockerApplication simpleTaskApp = DockerApplication
            .builder("simpleTaskApp")
            .host(host)
            .standardLifecycle(standardLifecycle)
            .build();
        return Sets.newHashSet(dockerRuntime, simpleTaskApp);
    }

    public static EffectiveModel getMinimalDockerModel() throws MalformedURLException {
        return new EffectiveModel(Sets.newHashSet(getMinimalDockerApplication()));
    }

    public static EffectiveModel getLampModel() {
        return new EffectiveModel(new LampApp().getLampApp());
    }
}
