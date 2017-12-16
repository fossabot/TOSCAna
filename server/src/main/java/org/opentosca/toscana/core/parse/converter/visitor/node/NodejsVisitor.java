package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.node.Nodejs;
import org.opentosca.toscana.model.node.Nodejs.NodejsBuilder;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;

public class NodejsVisitor<NodeT extends Nodejs, BuilderT extends NodejsBuilder> extends WebServerVisitor<NodeT, BuilderT> {

    private final static String GITHUB_URL_PROPERTY = "github_url";

    @Override
    protected void handleProperty(TPropertyAssignment node, NodeContext<BuilderT> parameter, BuilderT builder, Object value) {
        switch (parameter.getKey()) {
            case GITHUB_URL_PROPERTY:
                builder.githubUrl((String) value);
                break;
            default:
                super.handleProperty(node, parameter, builder, value);
        }
    }

    @Override
    protected void handleRequirement(TRequirementAssignment requirement, NodeContext<BuilderT> context, BuilderT builder) {
        switch (context.getKey()) {
            default:
                super.handleRequirement(requirement, context, builder);
        }
    }
}
