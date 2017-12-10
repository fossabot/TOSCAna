package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.model.node.Apache;
import org.opentosca.toscana.model.node.Apache.ApacheBuilder;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;

public class ApacheVisitor<NodeT extends Apache, BuilderT extends ApacheBuilder> extends WebServerVisitor<NodeT, BuilderT> {

    @Override
    public ConversionResult<NodeT> visit(TPropertyAssignment node, NodeContext<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        Object value = node.getValue();
        switch (parameter.getKey()) {
            default:
                super.visit(node, parameter);
        }
        return null;
    }
}
