package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.Context;
import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.model.node.DockerApplication;
import org.opentosca.toscana.model.node.DockerApplication.DockerApplicationBuilder;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;

public class DockerApplicationVisitor<NodeT extends DockerApplication, BuilderT extends DockerApplicationBuilder> extends ContainerApplicationVisitor<NodeT, BuilderT> {

    private static final String HOST_REQUIREMENT = "host";

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

    @Override
    public ConversionResult<NodeT> visit(TRequirementAssignment node, Context<BuilderT> parameter) {
        switch (parameter.getKey()) {
            case HOST_REQUIREMENT:

                break;
            default:
                super.visit(node, parameter);
        }
        return null;
    }
}
