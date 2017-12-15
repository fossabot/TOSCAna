package org.opentosca.toscana.model.node;

import org.opentosca.toscana.model.capability.ContainerCapability;
import org.opentosca.toscana.model.capability.DockerContainerCapability;
import org.opentosca.toscana.model.capability.EndpointCapability;
import org.opentosca.toscana.model.capability.StorageCapability;
import org.opentosca.toscana.model.operation.StandardLifecycle;
import org.opentosca.toscana.model.relation.HostedOn;
import org.opentosca.toscana.model.relation.RootRelationship;
import org.opentosca.toscana.model.requirement.DockerHostRequirement;
import org.opentosca.toscana.model.requirement.Requirement;
import org.opentosca.toscana.model.visitor.NodeVisitor;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class DockerApplication extends ContainerApplication {

    // public access due to hiding of parent field (and therefore getter conflicts..)
    @Getter(AccessLevel.NONE)
    public final Requirement<DockerContainerCapability, ContainerRuntime, HostedOn> host;

    @Builder
    private DockerApplication(Requirement<DockerContainerCapability, ContainerRuntime, HostedOn> host,
                              Requirement<StorageCapability, RootNode, RootRelationship> storage,
                              Requirement<EndpointCapability, RootNode, RootRelationship> network,
                              String nodeName,
                              StandardLifecycle standardLifecycle,
                              String description) {
        super(storage, network, nodeName, standardLifecycle, description);
        this.host = DockerHostRequirement.getFallback(host);
        requirements.add(this.host);
    }

    /**
     @param nodeName {@link #nodeName}
     */
    public static DockerApplicationBuilder builder(String nodeName) {
        return new DockerApplicationBuilder()
            .nodeName(nodeName);
    }

    @Override
    public void accept(NodeVisitor v) {
        v.visit(this);
    }

    public static class DockerApplicationBuilder extends ContainerApplicationBuilder {

        DockerApplicationBuilder() {
        }

        public ContainerApplicationBuilder host(Requirement<ContainerCapability, ContainerRuntime, HostedOn> host){
            // this is a hack.. this "enforces" usage of dockerHost() instead of host() (generic type erasure is the root of all evil)
            throw new IllegalArgumentException();
            
        }
        public DockerApplicationBuilder dockerHost(Requirement<DockerContainerCapability, ContainerRuntime, HostedOn> host) {
            this.host = host;
            return this;
        }
    }
}
