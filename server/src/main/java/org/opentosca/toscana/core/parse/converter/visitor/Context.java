package org.opentosca.toscana.core.parse.converter.visitor;

import org.opentosca.toscana.model.DescribableEntity.DescribableEntityBuilder;

import org.apache.commons.lang.SerializationUtils;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractParameter;

public class Context<NodeBuilderT extends DescribableEntityBuilder> extends AbstractParameter<Context<NodeBuilderT>> {

    private final String nodeName;
    private final NodeBuilderT nodeBuilder;

    public Context(String name, NodeBuilderT node) {
        this.nodeName = name;
        this.nodeBuilder = node;
    }

    @Override
    public Context copy() {
        return new Context(nodeName, (NodeBuilderT) SerializationUtils.clone(nodeBuilder));
    }

    @Override
    public Context self() {
        return this;
    }

    public String getNodeName() {
        return nodeName;
    }

    public NodeBuilderT getNodeBuilder() {
        return nodeBuilder;
    }
}
