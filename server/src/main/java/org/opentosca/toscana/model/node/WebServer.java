package org.opentosca.toscana.model.node;

import java.util.Objects;

import org.opentosca.toscana.model.capability.AdminEndpointCapability;
import org.opentosca.toscana.model.capability.ContainerCapability;
import org.opentosca.toscana.model.capability.DatabaseEndpointCapability;
import org.opentosca.toscana.model.capability.EndpointCapability;
import org.opentosca.toscana.model.datatype.Credential;
import org.opentosca.toscana.model.operation.StandardLifecycle;
import org.opentosca.toscana.model.relation.HostedOn;
import org.opentosca.toscana.model.requirement.Requirement;
import org.opentosca.toscana.model.visitor.NodeVisitor;

import lombok.Builder;
import lombok.Data;

/**
 Represents an abstract software component or service that is capable of hosting and providing management operations
 for one or more {@link WebApplication} nodes.
 <p>
 This node SHALL export both a secure endpoint capability ({@link #adminEndpoint}), typically for
 administration, as well as a regular endpoint ({@link #dataEndpoint}) for serving data.
 (TOSCA Simple Profile in YAML Version 1.1, p.171)
 */
@Data
public class WebServer extends SoftwareComponent {

    private final EndpointCapability dataEndpoint;

    private final AdminEndpointCapability adminEndpoint;

    private final ContainerCapability containerHost;

    @Builder
    protected WebServer(String componentVersion,
                        Credential adminCredential,
                        Requirement<ContainerCapability, Compute, HostedOn> host,
                        ContainerCapability containerHost,
                        EndpointCapability dataEndpoint,
                        AdminEndpointCapability adminEndpoint,
                        String nodeName,
                        StandardLifecycle standardLifecycle,
                        String description) {
        super(componentVersion, adminCredential, host, nodeName, standardLifecycle, description);
        this.containerHost = ContainerCapability.getFallback(containerHost);
        this.dataEndpoint = DatabaseEndpointCapability.getFallback(dataEndpoint);
        this.adminEndpoint = AdminEndpointCapability.getFallback(adminEndpoint);

        capabilities.add(this.containerHost);
        capabilities.add(this.dataEndpoint);
        capabilities.add(this.adminEndpoint);
    }

    /**
     @param nodeName      {@link #nodeName}
     @param dataEndpoint  {@link #dataEndpoint}
     @param adminEndpoint {@link #adminEndpoint}
     */
    public static WebServerBuilder builder(String nodeName,
                                           EndpointCapability dataEndpoint,
                                           AdminEndpointCapability adminEndpoint) {
        return new WebServerBuilder()
            .nodeName(nodeName)
            .dataEndpoint(dataEndpoint)
            .adminEndpoint(adminEndpoint);
    }

    @Override
    public void accept(NodeVisitor v) {
        v.visit(this);
    }

    public static class WebServerBuilder extends SoftwareComponentBuilder {
    }
}
