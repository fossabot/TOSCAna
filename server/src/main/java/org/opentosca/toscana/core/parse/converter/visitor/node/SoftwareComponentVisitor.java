package org.opentosca.toscana.core.parse.converter.visitor.node;

import java.util.Map;

import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.datatype.Credential;
import org.opentosca.toscana.model.node.SoftwareComponent;
import org.opentosca.toscana.model.node.SoftwareComponent.SoftwareComponentBuilder;
import org.opentosca.toscana.model.relation.HostedOn;

import org.eclipse.winery.model.tosca.yaml.TCapabilityAssignment;
import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;

public class SoftwareComponentVisitor<NodeT extends SoftwareComponent, BuilderT extends SoftwareComponentBuilder> extends RootNodeVisitor<NodeT, BuilderT> {

    private static final String ADMIN_CREDENTIAL = "admin_credential";
    private static final String COMPONENT_VERSION = "component_version";
    private static final String HOST_REQUIREMENT = "host";
    
    @Override
    public ConversionResult<NodeT> visit(TPropertyAssignment node, NodeContext<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        Object value = node.getValue();
        switch (parameter.getKey()) {
            case ADMIN_CREDENTIAL:
                // TODO build dedicated credential visitor
                Map<String, String> credentialMap = (Map<String, String>) value;
                Credential credential = Credential
                    .builder(credentialMap.get("token"))
                    .user(credentialMap.get("user"))
                    .build();
                builder.adminCredential(credential);
                break;
            case COMPONENT_VERSION:
                String componentVersion = (String) value;
                builder.componentVersion(componentVersion);
                break;
            default:
                super.visit(node, parameter);
        }

        return null;
    }

    @Override
    public ConversionResult<NodeT> visit(TCapabilityAssignment node, NodeContext<BuilderT> parameter) {
        return super.visit(node, parameter);
    }

    @Override
    protected void handleRequirement(TRequirementAssignment requirement, NodeContext<BuilderT> context, BuilderT builder) {
        switch (context.getKey()){
            case HOST_REQUIREMENT:
                builder.host(provideRequirement(requirement, context, HostedOn.class));
                break;
            default:
                super.handleRequirement(requirement, context, builder);
        }
    }
}
