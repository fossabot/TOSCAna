package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.node.Database;
import org.opentosca.toscana.model.node.Database.DatabaseBuilder;
import org.opentosca.toscana.model.relation.HostedOn;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;

public class DatabaseVisitor<NodeT extends Database, BuilderT extends DatabaseBuilder> extends RootNodeVisitor<NodeT, BuilderT> {

    private final static String NAME_PROPERTY = "name";
    private final static String PORT_PROPERTY = "port";
    private final static String USER_PROPERTY = "user";
    private final static String PASSWORD_PROPERTY = "password";
    private final static String HOST_REQUIREMENT = "host";

    @Override
    protected void handleProperty(TPropertyAssignment node, NodeContext<BuilderT> parameter, BuilderT builder, Object value) {
        switch (parameter.getKey()) {
            case NAME_PROPERTY:
                builder.databaseName((String) value);
                break;
            case PORT_PROPERTY:
                builder.port((Integer) value);
                break;
            case USER_PROPERTY:
                builder.user((String) value);
                break;
            case PASSWORD_PROPERTY:
                builder.password((String) value);
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
