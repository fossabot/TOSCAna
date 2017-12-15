package org.opentosca.toscana.plugins.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.opentosca.toscana.model.EffectiveModel;
import org.opentosca.toscana.model.node.Compute;
import org.opentosca.toscana.model.node.RootNode;
import org.opentosca.toscana.plugins.kubernetes.util.KubernetesNodeContainer;
import org.opentosca.toscana.plugins.kubernetes.util.NodeStack;

import org.slf4j.Logger;

import static org.opentosca.toscana.plugins.kubernetes.util.GraphOperations.buildTopologyStacks;
import static org.opentosca.toscana.plugins.kubernetes.util.GraphOperations.determineTopLevelNodes;

public class NodeStackFinder {
    private final Logger logger;
    private Map<String, KubernetesNodeContainer> nodes = new HashMap<>();
    private Set<KubernetesNodeContainer> computeNodes = new HashSet<>();
    private Set<NodeStack> stacks = new HashSet<>();

    public NodeStackFinder(Logger logger) {
        this.logger = logger;
    }

    public void findNodes(EffectiveModel model) {
        logger.debug("Collecting Compute Nodes in topology");
        ComputeNodeFindingVisitor computeFinder = new ComputeNodeFindingVisitor();
        model.getNodes().forEach(e -> {
            e.accept(computeFinder);
            KubernetesNodeContainer container = new KubernetesNodeContainer(e);
            nodes.put(e.getNodeName(), container);
        });
        computeFinder.getComputeNodes().forEach(e -> computeNodes.add(nodes.get(e.getNodeName())));

        logger.debug("Finding top Level Nodes");
        Set<RootNode> topLevelNodes = determineTopLevelNodes(
            model,
            computeFinder.getComputeNodes().stream().map(Compute.class::cast).collect(Collectors.toList()),
            e -> nodes.get(e.getNodeName()).activateParentComputeNode()
        );

        logger.debug("Building complete Topology stacks");
        buildTopologyStacks(model, topLevelNodes, nodes);
    }
}
