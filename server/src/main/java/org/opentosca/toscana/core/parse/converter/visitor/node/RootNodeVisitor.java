package org.opentosca.toscana.core.parse.converter.visitor.node;

import java.util.HashMap;
import java.util.Map;

import org.opentosca.toscana.core.parse.converter.RequirementConversion;
import org.opentosca.toscana.core.parse.converter.RequirementConverter;
import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.core.parse.converter.visitor.LifecycleConverter;
import org.opentosca.toscana.model.DescribableEntity;
import org.opentosca.toscana.model.DescribableEntity.DescribableEntityBuilder;
import org.opentosca.toscana.model.artifact.Artifact;
import org.opentosca.toscana.model.artifact.Repository;
import org.opentosca.toscana.model.capability.Capability;
import org.opentosca.toscana.model.node.RootNode;
import org.opentosca.toscana.model.operation.StandardLifecycle;
import org.opentosca.toscana.model.relation.RootRelationship;
import org.opentosca.toscana.model.requirement.Requirement;

import org.eclipse.winery.model.tosca.yaml.TArtifactDefinition;
import org.eclipse.winery.model.tosca.yaml.TInterfaceDefinition;
import org.eclipse.winery.model.tosca.yaml.TNodeTemplate;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;

public abstract class RootNodeVisitor<NodeT extends RootNode, BuilderT extends RootNode.RootNodeBuilder> extends DescribableVisitor<NodeT, BuilderT> {

    protected final Map<String, Class> requirementMappings = new HashMap<>();
    
    @Override
    public ConversionResult<NodeT> visit(TNodeTemplate node, NodeContext<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        builder.nodeName(parameter.getNodeName());
        return super.visit(node, parameter);
    }

    @Override
    public ConversionResult<NodeT> visit(TArtifactDefinition node, NodeContext<BuilderT> parameter) {
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

    @Override
    public ConversionResult<NodeT> visit(TInterfaceDefinition node, NodeContext<BuilderT> parameter) {
        switch (parameter.getKey()){
            case "Standard":
                StandardLifecycle lifecycle = new LifecycleConverter(parameter.getArtifacts()).convertStandard(node);
                parameter.getNodeBuilder().standardLifecycle(lifecycle);
                break;
            default:
                // TODO handle custom interfaces
        }
        return super.visit(node, parameter);
    }
    
    @Override
    public ConversionResult<NodeT> visit(TRequirementAssignment node, NodeContext<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        handleRequirement(node, parameter, builder);
        return null;
    }
    
    protected void handleRequirement(TRequirementAssignment requirement, NodeContext<BuilderT> context, BuilderT builder){
       // overwrite in classes with requirements 
    }
    
    protected <CapabilityT extends Capability, RequirementNodeT extends RootNode, RelationshipT extends RootRelationship>
    Requirement<CapabilityT, RequirementNodeT, RelationshipT> provideRequirement(TRequirementAssignment node, NodeContext parameter, Class<RelationshipT> relationshipClass) {
        RequirementConversion conversion = new RequirementConverter().<CapabilityT, RequirementNodeT, RelationshipT>convert(node, relationshipClass);
        parameter.addRequirementConversion(conversion);
        return conversion.requirement;
    }
    
}
