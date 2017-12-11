package org.opentosca.toscana.core.parse.converter.visitor;

import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;

import java.util.HashSet;
import java.util.Set;

public class SetResult<R> extends AbstractResult<SetResult<R>> {

    private final Set<R> elements = new HashSet<>();
    
    public SetResult(R element){
        elements.add(element);
    }

    @Override
    public SetResult<R> add(SetResult o) {
        elements.addAll(o.get());
        return this;
    }

    public Set<R> get() {
        return elements;
    }
}
