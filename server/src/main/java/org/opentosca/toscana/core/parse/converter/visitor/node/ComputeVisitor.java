package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.node.Compute;
import org.opentosca.toscana.model.node.Compute.ComputeBuilder;
import org.opentosca.toscana.model.relation.AttachesTo;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;

public class ComputeVisitor<NodeT extends Compute, BuilderT extends ComputeBuilder> extends RootNodeVisitor<NodeT, BuilderT> {
    
    private final static String LOCAL_STORAGE_REQUIREMENT = "local_storage";

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
            case LOCAL_STORAGE_REQUIREMENT:
                builder.localStorage(provideRequirement(requirement, context, AttachesTo.class));
                break;
            default:
                super.handleRequirement(requirement, context, builder);
        }
    }
}
