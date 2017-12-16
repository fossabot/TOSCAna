package org.opentosca.toscana.core.parse.converter.visitor.node;

import org.opentosca.toscana.core.parse.converter.visitor.NodeContext;
import org.opentosca.toscana.model.node.BlockStorage;
import org.opentosca.toscana.model.node.BlockStorage.BlockStorageBuilder;

import org.eclipse.winery.model.tosca.yaml.TPropertyAssignment;
import org.eclipse.winery.model.tosca.yaml.TRequirementAssignment;

public class BlockStorageVisitor<NodeT extends BlockStorage, BuilderT extends BlockStorageBuilder> extends RootNodeVisitor<NodeT, BuilderT> {

    private final static String SIZE_PROPERTY = "size";
    private final static String VOLUME_ID_PROPERTY = "volume_id";
    private final static String SNAPSHOT_ID_PROPERTY = "snapshot_id";

    @Override
    protected void handleProperty(TPropertyAssignment node, NodeContext<BuilderT> parameter, BuilderT builder, Object value) {
        switch (parameter.getKey()) {
            case SIZE_PROPERTY:
                builder.sizeInMB((Integer) value);
                break;
            case VOLUME_ID_PROPERTY:
                builder.volumeId((String) value);
                break;
            case SNAPSHOT_ID_PROPERTY:
                builder.snapshotId((String) value);
                break;
            default:
                super.handleProperty(node, parameter, builder, value);
        }
    }

    @Override
    protected void handleRequirement(TRequirementAssignment requirement, NodeContext<BuilderT> context, BuilderT builder) {
        switch (context.getKey()) {
            default:
                super.handleRequirement(requirement, context, builder);
        }
    }
}
