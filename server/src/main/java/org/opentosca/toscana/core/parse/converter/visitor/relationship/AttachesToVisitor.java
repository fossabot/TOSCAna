package org.opentosca.toscana.core.parse.converter.visitor.relationship;

import org.opentosca.toscana.model.relation.AttachesTo;
import org.opentosca.toscana.model.relation.AttachesTo.AttachesToBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttachesToVisitor<RelationshipT extends AttachesTo, BuilderT extends AttachesToBuilder> extends RootRelationshipVisitor<RelationshipT, BuilderT> {

    private final static Logger logger = LoggerFactory.getLogger(AttachesToVisitor.class.getName());
}
