package org.opentosca.toscana.core.parse.converter.visitor;

import org.opentosca.toscana.model.node.RootNode;

import org.eclipse.winery.model.tosca.yaml.TNodeTemplate;

public class RootNodeVisitor<NodeT extends RootNode, BuilderT extends RootNode.RootNodeBuilder> extends DescribableVisitor<NodeT, BuilderT> {
    
    @Override
    public ConversionResult<NodeT> visit(TNodeTemplate node, Context<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        builder.nodeName(parameter.getNodeName());
        return super.visit(node, parameter);
    }
}
