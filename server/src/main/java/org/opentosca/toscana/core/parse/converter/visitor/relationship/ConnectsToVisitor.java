package org.opentosca.toscana.core.parse.converter.visitor.relationship;

import org.opentosca.toscana.model.relation.ConnectsTo;
import org.opentosca.toscana.model.relation.ConnectsTo.ConnectsToBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectsToVisitor<RelationshipT extends ConnectsTo, BuilderT extends ConnectsToBuilder> extends RootRelationshipVisitor<RelationshipT, BuilderT> {

    private final static Logger logger = LoggerFactory.getLogger(ConnectsToVisitor.class.getName());
}
