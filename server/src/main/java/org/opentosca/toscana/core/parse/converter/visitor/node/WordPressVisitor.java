package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.node.WordPress;
import org.opentosca.toscana.model.node.WordPress.WordPressBuilder;
import org.opentosca.toscana.model.relation.ConnectsTo;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;

public class WordPressVisitor<NodeT extends WordPress, BuilderT extends WordPressBuilder> extends WebApplicationVisitor<NodeT, BuilderT> {
    
    private final static String HOST_REQUIREMENT = "host";

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
            case HOST_REQUIREMENT:
                builder.databaseEndpoint(provideRequirement(requirement, context, ConnectsTo.class));
                break;
            default:
                super.handleRequirement(requirement, context, builder);
        }
    }
}
