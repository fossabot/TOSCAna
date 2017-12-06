package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.Context;
import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.model.node.DockerApplication;
import org.opentosca.toscana.model.node.DockerApplication.DockerApplicationBuilder;

import org.eclipse.winery.model.tosca.yaml.TCapabilityAssignment;
import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;

public class DockerApplicationVisitor<NodeT extends DockerApplication, BuilderT extends DockerApplicationBuilder> extends RootNodeVisitor<NodeT, BuilderT> {

    private static final String ADMIN_CREDENTIAL = "admin_credential";
    private static final String COMPONENT_VERSION = "component_version";

    @Override
    public ConversionResult<NodeT> visit(TPropertyAssignment node, Context<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        Object value = node.getValue();
        switch (parameter.getKey()) {
            case ADMIN_CREDENTIAL:
            case COMPONENT_VERSION:
            default:
                super.visit(node, parameter);
        }

        return null;
    }

    @Override
    public ConversionResult<NodeT> visit(TCapabilityAssignment node, Context<BuilderT> parameter) {
        return super.visit(node, parameter);
    }
}
