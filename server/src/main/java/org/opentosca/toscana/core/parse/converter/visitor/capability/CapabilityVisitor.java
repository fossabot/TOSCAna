package org.opentosca.toscana.core.parse.converter.visitor.capability;

import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.core.parse.converter.visitor.node.DescribableVisitor;
import org.opentosca.toscana.model.capability.Capability;
import org.opentosca.toscana.model.node.RootNode;

import org.eclipse.winery.model.tosca.yaml.TCapabilityAssignment;

public class CapabilityVisitor<CapabilityT extends Capability, BuilderT extends RootNode.RootNodeBuilder> extends DescribableVisitor<CapabilityT, BuilderT> {

    @Override
    public ConversionResult<CapabilityT> visit(TCapabilityAssignment node, NodeContext<BuilderT> parameter) {
        return super.visit(node, parameter);
    }
}
