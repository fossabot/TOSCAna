package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.node.LoadBalancer;
import org.opentosca.toscana.model.node.LoadBalancer.LoadBalancerBuilder;
import org.opentosca.toscana.model.relation.RoutesTo;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;

public class LoadBalancerVisitor<NodeT extends LoadBalancer, BuilderT extends LoadBalancerBuilder> extends RootNodeVisitor<NodeT, BuilderT> {
    
    private static final String APPLICATION_REQUIREMENT = "application";
    
    
    @Override
    public ConversionResult<NodeT> visit(TPropertyAssignment node, NodeContext<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        Object value = node.getValue();
        switch (parameter.getKey()) {
            default:
                super.visit(node, parameter);
        }
        return null;
    }

    @Override
    protected void handleRequirement(TRequirementAssignment requirement, NodeContext<BuilderT> context, BuilderT builder) {
        switch (context.getKey()){
            case "application":
                builder.application(provideRequirement(requirement, context, RoutesTo.class));
                break;
            default:
                super.handleRequirement(requirement, context, builder);
        }
    }
}
