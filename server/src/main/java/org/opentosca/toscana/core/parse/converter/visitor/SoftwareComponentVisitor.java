package org.opentosca.toscana.core.parse.converter.visitor;

import org.opentosca.toscana.model.node.SoftwareComponent;
import org.opentosca.toscana.model.node.SoftwareComponent.SoftwareComponentBuilder;

import org.eclipse.winery.model.tosca.yaml.TNodeTemplate;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractVisitor;

public class SoftwareComponentVisitor extends AbstractVisitor<ConversionResult<SoftwareComponent>, Context<SoftwareComponentBuilder>> {

    @Override
    public ConversionResult<SoftwareComponent> visit(TNodeTemplate node, Context<SoftwareComponentBuilder> parameter) {
        parameter.getNodeBuilder().description(node.getDescription());
        return super.visit(node, parameter);
    }
}
