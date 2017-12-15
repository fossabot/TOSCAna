package org.opentosca.toscana.core.parse.converter.visitor;

import java.util.Set;

import org.opentosca.toscana.core.parse.converter.RequirementConversion;

import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;

public class ConversionResult<R> extends AbstractResult<ConversionResult<R>> {
    
    private final R result;
    private final Set<RequirementConversion> requirementConversions;
    
    public ConversionResult(R result, Set<RequirementConversion> requirementConversions) {
        this.result = result;
        this.requirementConversions = requirementConversions;
        
    }

    // dunno how we need this. lets see
    @Override
    public ConversionResult add(ConversionResult o) {
        return this;
    }
    
    public R getResult(){
        return result;
    }

    public Set<RequirementConversion> getRequirementConversions() {
        return requirementConversions;
    }
}
