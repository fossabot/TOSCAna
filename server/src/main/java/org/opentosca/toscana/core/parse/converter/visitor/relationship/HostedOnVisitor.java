package org.opentosca.toscana.core.parse.converter.visitor.relationship;

import org.opentosca.toscana.model.relation.HostedOn;
import org.opentosca.toscana.model.relation.HostedOn.HostedOnBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HostedOnVisitor<RelationshipT extends HostedOn, BuilderT extends HostedOnBuilder> extends RootRelationshipVisitor<RelationshipT, BuilderT> {

    private final static Logger logger = LoggerFactory.getLogger(HostedOnVisitor.class.getName());
}
