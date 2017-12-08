package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.eclipse.winery.model.tosca.yaml.TNodeTemplate;
import org.eclipse.winery.model.tosca.yaml.support.TMapRequirementAssignment;
import org.opentosca.toscana.core.parse.converter.visitor.Context;
import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.model.node.DockerApplication;
import org.opentosca.toscana.model.node.DockerApplication.DockerApplicationBuilder;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;
import org.opentosca.toscana.model.requirement.DockerHostRequirement;

public class DockerApplicationVisitor<NodeT extends DockerApplication, BuilderT extends DockerApplicationBuilder> extends ContainerApplicationVisitor<NodeT, BuilderT> {

    private static final String HOST_REQUIREMENT = "host";
    private static final String NETWORK_REQUIREMENT = "network";
    private static final String[] requirements = { HOST_REQUIREMENT, NETWORK_REQUIREMENT };

    @Override
    public ConversionResult<NodeT> visit(TNodeTemplate node, Context<BuilderT> parameter) {
        for (TMapRequirementAssignment requirementMap : node.getRequirements()){
            requirementMap.getMap();
            break;
        }
        return super.visit(node, parameter);
    }

    @Override
    public ConversionResult<NodeT> visit(TPropertyAssignment node, Context<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        Object value = node.getValue();
        switch (parameter.getKey()) {
            default:
                super.visit(node, parameter);
        }

        return null;
    }

    @Override
    public ConversionResult<NodeT> visit(TRequirementAssignment node, Context<BuilderT> parameter) {
        BuilderT builder = parameter.getNodeBuilder();
        switch (parameter.getKey()) {
            case HOST_REQUIREMENT:
                String reqName = parameter.getKey();
                DockerHostRequirement host = DockerHostRequirement.builder().build();
                builder.host(host);
                
//                DockerApplication app = DockerApplication.builder();
                
                
                break;
            case NETWORK_REQUIREMENT:
                

                
                break;
            default:
                super.visit(node, parameter);
        }
        return null;
    }
}
