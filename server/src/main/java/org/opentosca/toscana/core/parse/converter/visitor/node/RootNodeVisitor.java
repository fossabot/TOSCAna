package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.node.RootNode;

import org.eclipse.winery.model.tosca.yaml.TNodeTemplate;

public abstract class RootNodeVisitor<NodeT extends RootNode, BuilderT extends RootNode.RootNodeBuilder> extends DescribableVisitor<NodeT, BuilderT> {

    @Override
    public ConversionResult<NodeT> visit(TNodeTemplate node, NodeContext<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        builder.nodeName(parameter.getNodeName());
        return super.visit(node, parameter);
    }
}
