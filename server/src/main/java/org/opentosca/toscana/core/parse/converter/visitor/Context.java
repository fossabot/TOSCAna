package org.opentosca.toscana.core.parse.converter.visitor;

import org.opentosca.toscana.model.DescribableEntity.DescribableEntityBuilder;

import org.apache.commons.lang.SerializationUtils;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractParameter;

public class Context<NodeBuilderT extends DescribableEntityBuilder> extends AbstractParameter<Context<? extends DescribableEntityBuilder>> {

    private final String name;
    private final NodeBuilderT nodeBuilder;

    Context(String name, NodeBuilderT node) {
        this.name = name;
        this.nodeBuilder = node;
    }

    @Override
    public Context copy() {
        return new Context(name, (NodeBuilderT) SerializationUtils.clone(nodeBuilder));
    }

    @Override
    public Context self() {
        return this;
    }

    public String getName() {
        return name;
    }

    public NodeBuilderT getNodeBuilder() {
        return nodeBuilder;
    }
}
