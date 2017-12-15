package org.opentosca.toscana.plugins.model;

import java.util.LinkedList;

import org.opentosca.toscana.model.node.RootNode;

public class NodeStack {
    private final LinkedList<RootNode> nodes;

    public NodeStack(LinkedList<RootNode> nodes) {
        this.nodes = nodes;
    }

    public LinkedList<RootNode> get() {
        return nodes;
    }
}
