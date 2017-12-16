package org.opentosca.toscana.model.node;

import org.opentosca.toscana.model.capability.AdminEndpointCapability;
import org.opentosca.toscana.model.capability.ContainerCapability;
import org.opentosca.toscana.model.capability.EndpointCapability;
import org.opentosca.toscana.model.datatype.Credential;
import org.opentosca.toscana.model.operation.StandardLifecycle;
import org.opentosca.toscana.model.relation.HostedOn;
import org.opentosca.toscana.model.requirement.HostRequirement;
import org.opentosca.toscana.model.requirement.Requirement;
import org.opentosca.toscana.model.visitor.NodeVisitor;

import lombok.Builder;
import lombok.Data;

/**
 (TOSCA Simple Profile in YAML Version 1.1, p. 222)
 */
@Data
public class Apache extends WebServer {

    @Builder
    protected Apache(String componentVersion,
                     Credential adminCredential,
                     Requirement<ContainerCapability, Compute, HostedOn> host,
                     ContainerCapability containerHost,
                     EndpointCapability databaseEndpoint,
                     AdminEndpointCapability adminEndpoint,
                     String nodeName,
                     StandardLifecycle lifecycle,
                     String description) {
        super(componentVersion, adminCredential, HostRequirement.getFallback(host), containerHost,
            databaseEndpoint, adminEndpoint, nodeName, lifecycle, description);
    }

    /**
     @param nodeName      {@link #nodeName}
     */
    public static ApacheBuilder builder(String nodeName){
        return new ApacheBuilder()
            .nodeName(nodeName);
    }

    @Override
    public void accept(NodeVisitor v) {
        v.visit(this);
    }

    public static class ApacheBuilder extends WebServerBuilder {
    }
}
