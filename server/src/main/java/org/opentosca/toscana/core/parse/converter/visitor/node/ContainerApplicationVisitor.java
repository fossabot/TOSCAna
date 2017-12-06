package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.Context;
import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.model.node.ContainerApplication;
import org.opentosca.toscana.model.node.ContainerApplication.ContainerApplicationBuilder;

import org.eclipse.winery.model.tosca.yaml.TCapabilityAssignment;

public class ContainerApplicationVisitor<NodeT extends ContainerApplication, BuilderT extends ContainerApplicationBuilder> extends RootNodeVisitor<NodeT, BuilderT> {

    @Override
    public ConversionResult<NodeT> visit(TCapabilityAssignment node, Context<BuilderT> parameter) {
        return super.visit(node, parameter);
    }
}
