package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.Context;
import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.model.node.MysqlDatabase;
import org.opentosca.toscana.model.node.MysqlDatabase.MysqlDatabaseBuilder;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;

public class MysqlDatabaseVisitor<NodeT extends MysqlDatabase, BuilderT extends MysqlDatabaseBuilder> extends DatabaseVisitor<NodeT, BuilderT> {

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
