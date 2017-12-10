package org.opentosca.toscana.core.parse.converter.visitor;

import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;

public class ConversionResult<R> extends AbstractResult<ConversionResult<R>> {
    
    private final R result;
    
    public ConversionResult(R result){
        this.result = result;
    }
    
    // dunno how we need this. lets see
    @Override
    public ConversionResult add(ConversionResult o) {
        return this;
    }
    
    public R getResult(){
        return result;
    }
}
