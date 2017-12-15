package org.opentosca.toscana.core.parse.converter.visitor.relationship;

import org.opentosca.toscana.model.relation.RoutesTo;
import org.opentosca.toscana.model.relation.RoutesTo.RoutesToBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoutesToVisitor<RelationshipT extends RoutesTo, BuilderT extends RoutesToBuilder> extends RootRelationshipVisitor<RelationshipT, BuilderT> {

    private final static Logger logger = LoggerFactory.getLogger(RoutesToVisitor.class.getName());
}
