package org.opentosca.toscana.core.parse.converter.visitor;

import java.util.Map;

import org.opentosca.toscana.model.DescribableEntity;
import org.opentosca.toscana.model.DescribableEntity.DescribableEntityBuilder;
import org.opentosca.toscana.model.datatype.Credential;
import org.opentosca.toscana.model.node.RootNode;
import org.opentosca.toscana.model.node.SoftwareComponent;
import org.opentosca.toscana.model.node.SoftwareComponent.SoftwareComponentBuilder;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;

public class RootNodeVisitor extends DescribableVisitor<RootNode, DescribableEntityBuilder> {

    private static final String ADMIN_CREDENTIAL = "admin_credential";

    @Override
    public ConversionResult<SoftwareComponent> visit(TPropertyAssignment node, Context<SoftwareComponentBuilder> parameter) {
        if (parameter.getKey() == ADMIN_CREDENTIAL) {
            Map<String, String> credentialMap = (Map<String, String>) node.getValue();
            Credential credential = Credential.builder(credentialMap.get(ADMIN_CREDENTIAL)).build();
            parameter.getNodeBuilder().adminCredential(credential);
        }
        return null;
    }
}
