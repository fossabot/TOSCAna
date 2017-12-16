package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.node.Dbms;
import org.opentosca.toscana.model.node.Dbms.DbmsBuilder;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;

public class DbmsVisitor<NodeT extends Dbms, BuilderT extends DbmsBuilder> extends SoftwareComponentVisitor<NodeT, BuilderT> {

    private final static String ROOT_PASSWORD_PROPERTY = "root_password";
    private final static String PORT_PROPERTY = "port";

    @Override
    protected void handleProperty(TPropertyAssignment node, NodeContext<BuilderT> parameter, BuilderT builder, Object value) {
        switch (parameter.getKey()) {
            case ROOT_PASSWORD_PROPERTY:
                builder.rootPassword((String) value);
                break;
            case PORT_PROPERTY:
                builder.port((Integer) value);
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
