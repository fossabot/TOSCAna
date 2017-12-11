package org.opentosca.toscana.core.parse.converter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.opentosca.toscana.core.parse.converter.visitor.RepositoryVisitor;
import org.opentosca.toscana.model.EffectiveModel;
import org.opentosca.toscana.model.artifact.Repository;
import org.opentosca.toscana.model.node.RootNode;

import org.eclipse.winery.model.tosca.yaml.TNodeTemplate;
import org.eclipse.winery.model.tosca.yaml.TServiceTemplate;
import org.eclipse.winery.model.tosca.yaml.TTopologyTemplateDefinition;
import org.eclipse.winery.yaml.common.validator.support.Parameter;
import org.slf4j.Logger;

/**
 Contains logic to convertStandard a {@link TServiceTemplate} to a {@link EffectiveModel}
 */
public class ModelConverter {

    private final Logger logger;

    public ModelConverter(Logger logger) {
        this.logger = logger;
    }

    public EffectiveModel convert(TServiceTemplate serviceTemplate) throws UnknownNodeTypeException {
        logger.debug("Convert service template to normative model");
        Set<Repository> repositories = getRepositories(serviceTemplate);
        Set<RootNode> nodes = convertNodeTemplates(serviceTemplate.getTopologyTemplate(), repositories);
        fulfillRequirements(nodes);
        return new EffectiveModel(nodes);
    }

    private Set<Repository> getRepositories(TServiceTemplate serviceTemplate) {
        RepositoryVisitor visitor = new RepositoryVisitor(logger);
        Set<Repository> repositories = visitor.visit(serviceTemplate, new Parameter()).get();
        return repositories;
    }

    private Set<RootNode> convertNodeTemplates(TTopologyTemplateDefinition topology, Set<Repository> repositories) throws UnknownNodeTypeException {
        Map<String, TNodeTemplate> templateMap;
        if (topology != null) {
            templateMap = topology.getNodeTemplates();
        } else {
            logger.warn("Topology template of service template does not contain any node templates");
            templateMap = new HashMap<>();
        }

        Set<RootNode> nodes = new HashSet<>();
        NodeConverter nodeConverter = new NodeConverter(repositories, logger);
        for (Map.Entry<String, TNodeTemplate> entry : templateMap.entrySet()) {
            RootNode node = nodeConverter.convert(entry.getKey(), entry.getValue());
            nodes.add(node);
        }
        return nodes;
    }

    private void fulfillRequirements(Set<RootNode> nodes) {
        
    }
}
