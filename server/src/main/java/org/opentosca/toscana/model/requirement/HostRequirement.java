package org.opentosca.toscana.model.requirement;

import java.util.Set;

import org.opentosca.toscana.model.capability.ContainerCapability;
import org.opentosca.toscana.model.datatype.Range;
import org.opentosca.toscana.model.node.Compute;
import org.opentosca.toscana.model.relation.HostedOn;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import static org.opentosca.toscana.model.capability.ContainerCapability.getFallback;
import static org.opentosca.toscana.model.relation.HostedOn.getFallback;

@Data
public class HostRequirement extends Requirement<ContainerCapability, Compute, HostedOn> {

    @Builder
    protected HostRequirement(ContainerCapability capability,
                              Range occurrence,
                              @Singular Set<Compute> fulfillers,
                              HostedOn relationship) {
        super(getFallback(capability), occurrence, fulfillers, getFallback(relationship));
    }
}
