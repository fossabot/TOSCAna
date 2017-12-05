package org.opentosca.toscana.core.parse.converter.visitor;

import org.opentosca.toscana.model.node.RootNode;

import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;

public class ConversionResult<NodeT> extends AbstractResult<ConversionResult<NodeT>> {
    
    private final NodeT node;
    
    ConversionResult(NodeT node){
        this.node = node;
    }
    
    // dunno how we need this. lets see
    @Override
    public ConversionResult add(ConversionResult o) {
        return this;
    }
    
    public NodeT getNode(){
        return node;
    }
}
