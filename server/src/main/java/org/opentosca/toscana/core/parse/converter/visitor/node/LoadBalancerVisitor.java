package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.node.LoadBalancer;
import org.opentosca.toscana.model.node.LoadBalancer.LoadBalancerBuilder;
import org.opentosca.toscana.model.relation.RoutesTo;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;

public class LoadBalancerVisitor<NodeT extends LoadBalancer, BuilderT extends LoadBalancerBuilder> extends RootNodeVisitor<NodeT, BuilderT> {

    private static final String ALGORITHM_PROPERTY = "algorithm";
    private static final String APPLICATION_REQUIREMENT = "application";

    @Override
    protected void handleProperty(TPropertyAssignment node, NodeContext<BuilderT> parameter, BuilderT builder, Object value) {
        switch (parameter.getKey()) {
            case ALGORITHM_PROPERTY:
                builder.algorithm((String) value);
            default:
                super.handleProperty(node, parameter, builder, value);
        }
    }

    @Override
    protected void handleRequirement(TRequirementAssignment requirement, NodeContext<BuilderT> context, BuilderT builder) {
        switch (context.getKey()) {
            case "application":
                builder.application(provideRequirement(requirement, context, RoutesTo.class));
                break;
            default:
                super.handleRequirement(requirement, context, builder);
        }
    }
}
