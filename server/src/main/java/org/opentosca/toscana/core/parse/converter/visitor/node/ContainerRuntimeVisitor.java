package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.Context;
import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.model.node.ContainerRuntime;
import org.opentosca.toscana.model.node.ContainerRuntime.ContainerRuntimeBuilder;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;

public class ContainerRuntimeVisitor<NodeT extends ContainerRuntime, BuilderT extends ContainerRuntimeBuilder> extends SoftwareComponentVisitor<NodeT, BuilderT> {

    private static final String ADMIN_CREDENTIAL = "admin_credential";
    private static final String COMPONENT_VERSION = "component_version";

    @Override
    public ConversionResult<NodeT> visit(TPropertyAssignment node, Context<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        Object value = node.getValue();
        switch (parameter.getKey()) {
            default:
                super.visit(node, parameter);
        }
        return null;
    }
}
