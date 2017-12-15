package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.core.parse.converter.visitor.ConverterVisitor;
import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.DescribableEntity;
import org.opentosca.toscana.model.DescribableEntity.DescribableEntityBuilder;

import org.eclipse.winery.model.tosca.yaml.TNodeTemplate;

public abstract class DescribableVisitor<NodeT extends DescribableEntity, BuilderT extends DescribableEntityBuilder> extends ConverterVisitor<NodeT, BuilderT> {

    @Override
    public ConversionResult<NodeT> visit(TNodeTemplate node, NodeContext<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        builder.description(node.getDescription());
        super.visit(node, parameter);
        ConversionResult<NodeT> result = new ConversionResult<NodeT>((NodeT) builder.build(), parameter.getRequirementConversions());
        return result;
    }
}
