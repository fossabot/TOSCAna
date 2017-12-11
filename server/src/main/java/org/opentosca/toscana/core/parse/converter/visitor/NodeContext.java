package org.opentosca.toscana.core.parse.converter.visitor;

import java.util.HashSet;
import java.util.Set;

import org.opentosca.toscana.model.DescribableEntity.DescribableEntityBuilder;
import org.opentosca.toscana.model.artifact.Artifact;
import org.opentosca.toscana.model.artifact.Repository;

import org.eclipse.winery.model.tosca.yaml.visitor.AbstractParameter;

public class NodeContext<NodeBuilderT extends DescribableEntityBuilder> extends AbstractParameter<NodeContext<NodeBuilderT>> {

    private final String nodeName;
    private final NodeBuilderT nodeBuilder;
    private final Set<Repository> repositories;
    private final Set<Artifact> artifacts = new HashSet<>();

    public NodeContext(String name, NodeBuilderT builder, Set<Repository> repositories) {
        this.nodeName = name;
        this.nodeBuilder = builder;
        this.repositories = repositories;
    }

    @Override
    public NodeContext copy() {
        return this;
    }

    @Override
    public NodeContext self() {
        return this;
    }

    public String getNodeName() {
        return nodeName;
    }

    public NodeBuilderT getNodeBuilder() {
        return nodeBuilder;
    }

    public Set<Repository> getRepositories() {
        return repositories;
    }

    public Set<Artifact> getArtifacts() {
        return artifacts;
    }
}
