package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.node.MysqlDbms;
import org.opentosca.toscana.model.node.MysqlDbms.MysqlDbmsBuilder;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;

public class MysqlDbmsVisitor<NodeT extends MysqlDbms, BuilderT extends MysqlDbmsBuilder> extends DbmsVisitor<NodeT, BuilderT> {

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