package org.opentosca.toscana.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 Inherit from this if there's need to describe the entity.
 */
@Data
public class DescribableEntity implements Serializable {

    /**
     The optional description of this.
     */
    private final String description;

    public DescribableEntity() {
        description = "";
    }

    @Builder
    public DescribableEntity(String description) {
        this.description = description == null ? "" : description;
    }

    public static class DescribableEntityBuilder implements Serializable {

        public DescribableEntityBuilder() {
        }
    }
}
