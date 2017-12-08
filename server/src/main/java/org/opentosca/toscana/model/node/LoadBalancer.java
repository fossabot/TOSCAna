package org.opentosca.toscana.model.node;

import java.util.Objects;
import java.util.Optional;

import org.opentosca.toscana.model.capability.EndpointCapability;
import org.opentosca.toscana.model.capability.PublicEndpointCapability;
import org.opentosca.toscana.model.capability.PublicEndpointCapability.PublicEndpointCapabilityBuilder;
import org.opentosca.toscana.model.datatype.Range;
import org.opentosca.toscana.model.operation.StandardLifecycle;
import org.opentosca.toscana.model.relation.RoutesTo;
import org.opentosca.toscana.model.requirement.ApplicationRequirement;
import org.opentosca.toscana.model.requirement.Requirement;
import org.opentosca.toscana.model.requirement.Requirement.RequirementBuilder;
import org.opentosca.toscana.model.visitor.NodeVisitor;

import lombok.Builder;
import lombok.Data;

/**
 * Represents logical function that be used in conjunction with a Floating Address
 * to distribute an application’s traffic across a number of instances of the application
 * (e.g., for a clustered or scaled application).
 * (TOSCA Simple Profile in YAML Version 1.1, p.177)
 */
@Data
public class LoadBalancer extends RootNode {

    private final String algorithm;

    private final PublicEndpointCapability client;

    private final ApplicationRequirement application;

    @Builder
    public LoadBalancer(String algorithm,
                        PublicEndpointCapability client,
                        ApplicationRequirement application,
                        String nodeName,
                        StandardLifecycle standardLifecycle,
                        String description) {
        super(nodeName, standardLifecycle, description);
        client.setOccurrence(Range.ANY);
        this.client = Objects.requireNonNull(client);
        application.setOccurrence(Range.ANY);
        this.application = ApplicationRequirement.getFallback(application);
        this.algorithm = Objects.requireNonNull(algorithm);

        capabilities.add(this.client);
        requirements.add(this.application);
    }

    /**
     * @param nodeName {@link #nodeName}
     * @param client   {@link #client}
     */
    public static LoadBalancerBuilder builder(String nodeName,
                                              PublicEndpointCapability client) {
        return new LoadBalancerBuilder()
            .nodeName(nodeName)
            .client(client);
    }

    /**
     * @return {@link #algorithm}
     */
    public Optional<String> getAlgorithm() {
        return Optional.ofNullable(algorithm);
    }

    @Override
    public void accept(NodeVisitor v) {
        v.visit(this);
    }

    public static class LoadBalancerBuilder extends RootNodeBuilder {
    }
}
