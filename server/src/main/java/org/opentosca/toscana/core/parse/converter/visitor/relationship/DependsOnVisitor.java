package org.opentosca.toscana.core.parse.converter.visitor.relationship;

import org.opentosca.toscana.model.relation.DependsOn;
import org.opentosca.toscana.model.relation.DependsOn.DependsOnBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DependsOnVisitor<RelationshipT extends DependsOn, BuilderT extends DependsOnBuilder> extends RootRelationshipVisitor<RelationshipT, BuilderT> {

    private final static Logger logger = LoggerFactory.getLogger(DependsOnVisitor.class.getName());
}
