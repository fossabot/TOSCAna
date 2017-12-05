package org.opentosca.toscana.core.parse.converter.visitor;

import java.util.Map;

import org.opentosca.toscana.model.datatype.Credential;
import org.opentosca.toscana.model.node.SoftwareComponent;
import org.opentosca.toscana.model.node.SoftwareComponent.SoftwareComponentBuilder;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;

public class SoftwareComponentVisitor<NodeT extends SoftwareComponent, BuilderT extends SoftwareComponentBuilder> extends RootNodeVisitor<NodeT, BuilderT> {

    private static final String ADMIN_CREDENTIAL = "admin_credential";
    private static final String COMPONENT_VERSION = "component_version";

    @Override
    public ConversionResult<NodeT> visit(TPropertyAssignment node, Context<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        switch (parameter.getKey()) {
            case ADMIN_CREDENTIAL:
                // TODO build sophisticated credential visitor
                Map<String, String> credentialMap = (Map<String, String>) node.getValue();
                Credential credential = Credential.builder(credentialMap.get("token")).build();
                parameter.getNodeBuilder().adminCredential(credential);
                break;
            case COMPONENT_VERSION:
                break;
            default:
                super.visit(node, parameter);
        }
       
        return null;
    }
}
