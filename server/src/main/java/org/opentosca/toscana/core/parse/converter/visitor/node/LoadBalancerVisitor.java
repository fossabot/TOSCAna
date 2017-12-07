package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.Context;
import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.model.node.LoadBalancer;
import org.opentosca.toscana.model.node.LoadBalancer.LoadBalancerBuilder;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;

public class LoadBalancerVisitor<NodeT extends LoadBalancer, BuilderT extends LoadBalancerBuilder> extends RootNodeVisitor<NodeT, BuilderT> {

    @Override
    public ConversionResult<NodeT> visit(TPropertyAssignment node, Context<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        Object value = node.getValue();
        switch (parameter.getKey()) {
            default:
                super.visit(node, parameter);
        }
        return null;
    }
}
