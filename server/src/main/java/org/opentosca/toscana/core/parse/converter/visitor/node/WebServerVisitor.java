package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.node.WebServer;
import org.opentosca.toscana.model.node.WebServer.WebServerBuilder;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;

public class WebServerVisitor<NodeT extends WebServer, BuilderT extends WebServerBuilder> extends SoftwareComponentVisitor<NodeT, BuilderT> {

    @Override
    protected void handleRequirement(TRequirementAssignment requirement, NodeContext<BuilderT> context, BuilderT builder) {
        switch (context.getKey()){
            default:
                super.handleRequirement(requirement, context, builder);
        }
    }
}