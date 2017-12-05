package org.opentosca.toscana.core.parse.converter.visitor;

import org.opentosca.toscana.model.DescribableEntity.DescribableEntityBuilder;

import org.eclipse.winery.model.tosca.yaml.TNodeTemplate;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractVisitor;

public class EntityVisitor extends AbstractVisitor<ConversionResult, Context<? extends DescribableEntityBuilder>> {

    @Override
    public ConversionResult visit(TNodeTemplate node, Context<? extends DescribableEntityBuilder> parameter) {
        DescribableEntityBuilder builder = parameter.getNodeBuilder();
        builder.description(node.getDescription());
        return super.visit(node, parameter);
    }
}
