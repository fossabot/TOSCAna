package org.opentosca.toscana.core.parse.converter.visitor;

import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;

import java.util.ArrayList;
import java.util.List;

public class ListResult<R> extends AbstractResult<ListResult<R>> {

    private final List<R> elementList = new ArrayList<>();
    
    public ListResult(R element){
        elementList.add(element);
    }

    @Override
    public ListResult<R> add(ListResult o) {
        elementList.addAll(o.get());
        return this;
    }

    public List<R> get() {
        return elementList;
    }
}
