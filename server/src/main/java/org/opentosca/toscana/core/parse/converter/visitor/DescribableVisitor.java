package org.opentosca.toscana.core.parse.converter.visitor;

import org.opentosca.toscana.model.DescribableEntity;
import org.opentosca.toscana.model.DescribableEntity.DescribableEntityBuilder;

import org.eclipse.winery.model.tosca.yaml.TNodeTemplate;

public class DescribableVisitor<NodeT extends DescribableEntity, BuilderT extends DescribableEntityBuilder> extends ConverterVisitor<NodeT, BuilderT> {

    @Override
    public ConversionResult<NodeT> visit(TNodeTemplate node, Context<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        builder.description(node.getDescription());
        super.visit(node, parameter);
        ConversionResult<NodeT> result = new ConversionResult<NodeT>((NodeT) builder.build());
        return result;
    }
}
