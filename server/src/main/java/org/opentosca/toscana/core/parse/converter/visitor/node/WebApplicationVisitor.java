package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.node.WebApplication;
import org.opentosca.toscana.model.node.WebApplication.WebApplicationBuilder;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;

import javax.xml.soap.Node;

public class WebApplicationVisitor<NodeT extends WebApplication, BuilderT extends WebApplicationBuilder> extends RootNodeVisitor<NodeT, BuilderT> {

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