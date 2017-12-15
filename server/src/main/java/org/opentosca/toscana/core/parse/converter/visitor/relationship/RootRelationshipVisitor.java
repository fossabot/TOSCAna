package org.opentosca.toscana.core.parse.converter.visitor.relationship;

import org.opentosca.toscana.core.parse.converter.visitor.ConverterVisitor;
import org.opentosca.toscana.model.relation.RootRelationship;
import org.opentosca.toscana.model.relation.RootRelationship.RootRelationshipBuilder;

import org.eclipse.winery.model.tosca.yaml.TRelationshipTemplate;
import org.eclipse.winery.model.tosca.yaml.TRelationshipType;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractParameter;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RootRelationshipVisitor<RelationshipT extends RootRelationship, BuilderT extends RootRelationshipBuilder> extends ConverterVisitor<RelationshipT, BuilderT> {

    private final static Logger logger = LoggerFactory.getLogger(RootRelationshipVisitor.class.getName());

}
