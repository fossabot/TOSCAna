package org.opentosca.toscana.core.parse.converter.visitor;

import org.opentosca.toscana.model.DescribableEntity;
import org.opentosca.toscana.model.DescribableEntity.DescribableEntityBuilder;

import org.eclipse.winery.model.tosca.yaml.TNodeTemplate;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractVisitor;

public class DescribableVisitor extends AbstractVisitor<ConversionResult<DescribableEntity>, Context<DescribableEntityBuilder>> {

    @Override
    public ConversionResult<DescribableEntity> visit(TNodeTemplate node, Context<DescribableEntityBuilder> parameter) {
        DescribableEntityBuilder builder = parameter.getNodeBuilder();
        builder.description(node.getDescription());
        return super.visit(node, parameter);
    }
}
