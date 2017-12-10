package org.opentosca.toscana.core.parse.converter.visitor;

import org.eclipse.winery.model.tosca.yaml.visitor.AbstractParameter;
import org.opentosca.toscana.model.DescribableEntity.DescribableEntityBuilder;

public class NodeContext<NodeBuilderT extends DescribableEntityBuilder> extends AbstractParameter<NodeContext<NodeBuilderT>> {

    private final String nodeName;
    private final NodeBuilderT nodeBuilder;

    public NodeContext(String name, NodeBuilderT node) {
        this.nodeName = name;
        this.nodeBuilder = node;
    }

    @Override
    public NodeContext copy() {
        return this;
    }

    @Override
    public NodeContext self() {
        return this;
    }

    public String getNodeName() {
        return nodeName;
    }

    public NodeBuilderT getNodeBuilder() {
        return nodeBuilder;
    }
}
