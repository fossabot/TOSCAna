package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.eclipse.winery.model.tosca.yaml.TNodeTemplate;
import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.node.DockerApplication;
import org.opentosca.toscana.model.node.DockerApplication.DockerApplicationBuilder;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;

public class DockerApplicationVisitor<NodeT extends DockerApplication, BuilderT extends DockerApplicationBuilder> extends ContainerApplicationVisitor<NodeT, BuilderT> {

    private static final String HOST_REQUIREMENT = "host";
    private static final String NETWORK_REQUIREMENT = "network";

    @Override
    public ConversionResult<NodeT> visit(TNodeTemplate node, NodeContext<BuilderT> parameter) {
        return super.visit(node, parameter);
    }

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
    public ConversionResult<NodeT> visit(TRequirementAssignment node, NodeContext<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        switch (parameter.getKey()) {
            // TODO make requirements work in general
            case HOST_REQUIREMENT:
                break;
            case NETWORK_REQUIREMENT:
                break;
            default:
                super.visit(node, parameter);
        }
        return null;
    }
}
