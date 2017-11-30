package org.opentosca.toscana.model.relation;

import org.opentosca.toscana.model.DescribableEntity;
import org.opentosca.toscana.model.visitor.RelationshipVisitor;
import org.opentosca.toscana.model.visitor.VisitableRelationship;

import lombok.Builder;
import lombok.Data;

/**
 The default TOSCA Relationship Type that all other TOSCA Relationships derive from.
 (TOSCA Simple Profile in YAML Version 1.1, p. 159)
 */
@Data
public class RootRelationship extends DescribableEntity implements VisitableRelationship {

    @Builder
    public RootRelationship(String description) {
        super(description);
    }

    @Override
    public void accept(RelationshipVisitor v) {
        v.visit(this);
    }
}