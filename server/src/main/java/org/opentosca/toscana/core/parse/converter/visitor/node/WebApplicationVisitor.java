package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.node.WebApplication;
import org.opentosca.toscana.model.node.WebApplication.WebApplicationBuilder;
import org.opentosca.toscana.model.relation.HostedOn;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;

public class WebApplicationVisitor<NodeT extends WebApplication, BuilderT extends WebApplicationBuilder> extends RootNodeVisitor<NodeT, BuilderT> {

    private final static String CONTEXT_ROOT_PROPERTY = "context_root";
    private final static String HOST_REQUIREMENT = "host";

    @Override
    protected void handleProperty(TPropertyAssignment node, NodeContext<BuilderT> parameter, BuilderT builder, Object value) {
        switch (parameter.getKey()) {
            case CONTEXT_ROOT_PROPERTY:
                builder.contextRoot((String) value);
                break;
            default:
                super.handleProperty(node, parameter, builder, value);
        }
    }

    @Override
    protected void handleRequirement(TRequirementAssignment requirement, NodeContext<BuilderT> context, BuilderT builder) {
        switch (context.getKey()) {
            case HOST_REQUIREMENT:
                builder.host(provideRequirement(requirement, context, HostedOn.class));
                break;
            default:
                super.handleRequirement(requirement, context, builder);
        }
    }
}
