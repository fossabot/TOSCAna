package org.opentosca.toscana.plugins.model;

import java.util.LinkedList;
import java.util.Set;

import org.opentosca.toscana.core.BaseUnitTest;
import org.opentosca.toscana.model.node.RootNode;
import org.opentosca.toscana.plugins.testdata.TestEffectiveModels;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeStackFinderTest extends BaseUnitTest {
    private final static Logger logger = LoggerFactory.getLogger(NodeStackFinderTest.class);

    @Test
    public void generateNodeStacksTest() {
        NodeStackFinder finder = new NodeStackFinder(LoggerFactory.getLogger("Dummy Logger"));
        Set<NodeStack> stacks = finder.generateNodeStacks(TestEffectiveModels.getLampModel());
        logger.info(String.valueOf(stacks.size()));
        logger.info("produced stacks:");
        for (NodeStack stack : stacks) {
            LinkedList<RootNode> rootNodes = stack.get();
            logger.info("----------------");
            for (RootNode rootNode : rootNodes) {
                logger.info(rootNode.getNodeName());
            }
        }
    }
}
