package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.node.Compute;
import org.opentosca.toscana.model.node.Compute.ComputeBuilder;
import org.opentosca.toscana.model.relation.AttachesTo;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;

public class ComputeVisitor<NodeT extends Compute, BuilderT extends ComputeBuilder> extends RootNodeVisitor<NodeT, BuilderT> {

    private final static String PRIVATE_ADDRESS_PROPERTY = "private_address";
    private final static String PUBLIC_ADDRESS_PROPERTY = "public_address";
    private final static String NETWORKS_PROPERTY = "networks";
    private final static String PORTS_PROPERTY = "ports";
    private final static String LOCAL_STORAGE_REQUIREMENT = "local_storage";

    @Override
    protected void handleProperty(TPropertyAssignment node, NodeContext<BuilderT> parameter, BuilderT builder, Object value) {
        switch (parameter.getKey()) {
            case PRIVATE_ADDRESS_PROPERTY:
                builder.privateAddress((String) value);
                break;
            case PUBLIC_ADDRESS_PROPERTY:
                builder.publicAddress((String) value);
                break;
            case NETWORKS_PROPERTY:
                // TODO
                throw new UnsupportedOperationException();
            case PORTS_PROPERTY:
                // TODO
                throw new UnsupportedOperationException();
            default:
                super.handleProperty(node, parameter, builder, value);
        }
    }

    @Override
    protected void handleRequirement(TRequirementAssignment requirement, NodeContext<BuilderT> context, BuilderT builder) {
        switch (context.getKey()) {
            case LOCAL_STORAGE_REQUIREMENT:
                builder.localStorage(provideRequirement(requirement, context, AttachesTo.class));
                break;
            default:
                super.handleRequirement(requirement, context, builder);
        }
    }
}
