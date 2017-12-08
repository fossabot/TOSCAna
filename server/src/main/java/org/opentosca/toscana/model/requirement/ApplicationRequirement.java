package org.opentosca.toscana.model.requirement;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.opentosca.toscana.model.capability.AttachmentCapability;
import org.opentosca.toscana.model.capability.EndpointCapability;
import org.opentosca.toscana.model.datatype.Range;
import org.opentosca.toscana.model.node.BlockStorage;
import org.opentosca.toscana.model.node.RootNode;
import org.opentosca.toscana.model.relation.AttachesTo;
import org.opentosca.toscana.model.relation.RoutesTo;

import java.util.Set;

@Data
public class ApplicationRequirement extends Requirement<EndpointCapability, RootNode, RoutesTo> {

    @Builder
    protected ApplicationRequirement(EndpointCapability capability,
                                     Range occurrence,
                                     @Singular Set<RootNode> fulfillers,
                                     RoutesTo relationship) {
        super(capability, occurrence, fulfillers, relationship);
    }

    public static ApplicationRequirementBuilder builder(RoutesTo relationship) {
        return new ApplicationRequirementBuilder()
            .relationship(relationship);
    }

    public static ApplicationRequirement getFallback(ApplicationRequirement application) {
        return (application == null) ? ApplicationRequirement.builder(new RoutesTo()).build() : application;
    }

    public static class ApplicationRequirementBuilder extends RequirementBuilder<EndpointCapability, RootNode, RoutesTo> {
    }
}
