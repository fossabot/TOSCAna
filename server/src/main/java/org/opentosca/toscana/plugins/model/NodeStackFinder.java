package org.opentosca.toscana.plugins.model;

import java.util.Set;
import java.util.stream.Collectors;

import org.opentosca.toscana.model.EffectiveModel;
import org.opentosca.toscana.model.node.Compute;
import org.opentosca.toscana.model.node.RootNode;

import org.slf4j.Logger;

import static org.opentosca.toscana.plugins.model.GraphOperations.buildTopologyStacks;
import static org.opentosca.toscana.plugins.model.GraphOperations.determineTopLevelNodes;

public class NodeStackFinder {
    private final Logger logger;

    public NodeStackFinder(Logger logger) {
        this.logger = logger;
    }

    public Set<NodeStack> generateNodeStacks(EffectiveModel model) {
        logger.debug("Collecting Compute Nodes in topology");
        ComputeNodeFindingVisitor computeFinder = new ComputeNodeFindingVisitor();
        model.getNodes().forEach(e -> {
            e.accept(computeFinder);
        });

        logger.debug("Finding top Level Nodes");
        Set<RootNode> topLevelNodes = determineTopLevelNodes(
            model,
            computeFinder.getComputeNodes().stream().map(Compute.class::cast).collect(Collectors.toList())
        );

        logger.debug("Building complete Topology stacks");
        return buildTopologyStacks(model, topLevelNodes);
    }
}
