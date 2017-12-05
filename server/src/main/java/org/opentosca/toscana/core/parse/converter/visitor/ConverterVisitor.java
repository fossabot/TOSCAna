package org.opentosca.toscana.core.parse.converter.visitor;

import org.opentosca.toscana.model.DescribableEntity;
import org.opentosca.toscana.model.DescribableEntity.DescribableEntityBuilder;

import org.eclipse.winery.model.tosca.yaml.TNodeTemplate;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractVisitor;

public abstract class ConverterVisitor<NodeT extends DescribableEntity, BuilderT extends DescribableEntityBuilder> extends AbstractVisitor<ConversionResult<NodeT>, Context<BuilderT>> {
}
