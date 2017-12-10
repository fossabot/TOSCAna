package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.artifact.Artifact;
import org.opentosca.toscana.model.artifact.Repository;
import org.opentosca.toscana.model.node.RootNode;

import org.eclipse.winery.model.tosca.yaml.TArtifactDefinition;
import org.eclipse.winery.model.tosca.yaml.TNodeTemplate;

public abstract class RootNodeVisitor<NodeT extends RootNode, BuilderT extends RootNode.RootNodeBuilder> extends DescribableVisitor<NodeT, BuilderT> {

    @Override
    public ConversionResult<NodeT> visit(TNodeTemplate node, NodeContext<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        builder.nodeName(parameter.getNodeName());
        return super.visit(node, parameter);
    }

    @Override
    public ConversionResult<NodeT> visit(TArtifactDefinition node, NodeContext<BuilderT> parameter) {
        // TODO TArtifactDefinition has field `files`, according to TOSCA spec 3.5.6.1 this should be a single `file`, bug?
        Repository repository = null;
        if (node.getRepository() != null) {
            repository = parameter.getRepositories().stream()
                .filter(repo -> node.getRepository().equals(repo.getName()))
                .findFirst()
                .orElse(null);
        }
        String file = (node.getFiles().size() > 0) ? node.getFiles().get(0) : null;
        Artifact artifact = Artifact
            .builder(parameter.getKey(), file)
            .description(node.getDescription())
            .deployPath(node.getDeployPath())
            .repository(repository)
            .build();
        parameter.getArtifacts().add(artifact);
        return null;
    }
}
