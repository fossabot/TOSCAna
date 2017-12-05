package org.opentosca.toscana.core.parse.converter.visitor;

import org.opentosca.toscana.model.node.RootNode;

import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;

public class ConversionResult extends AbstractResult<ConversionResult> {
    
    private final RootNode node;
    
    ConversionResult(RootNode node){
        this.node = node;
    }
    
    // dunno how we need this. lets see
    @Override
    public ConversionResult add(ConversionResult o) {
        return this;
    }
    
    public RootNode getNode(){
        return node;
    }
}
