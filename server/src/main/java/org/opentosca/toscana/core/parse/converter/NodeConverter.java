package org.opentosca.toscana.core.parse.converter;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

import org.opentosca.toscana.core.parse.converter.visitor.Context;
import org.opentosca.toscana.core.parse.converter.visitor.ConversionResult;
import org.opentosca.toscana.core.parse.converter.visitor.node.ContainerRuntimeVisitor;
import org.opentosca.toscana.core.parse.converter.visitor.node.DescribableVisitor;
import org.opentosca.toscana.core.parse.converter.visitor.node.DockerApplicationVisitor;
import org.opentosca.toscana.core.parse.converter.visitor.node.SoftwareComponentVisitor;
import org.opentosca.toscana.model.DescribableEntity;
import org.opentosca.toscana.model.DescribableEntity.DescribableEntityBuilder;
import org.opentosca.toscana.model.node.Apache;
import org.opentosca.toscana.model.node.BlockStorage;
import org.opentosca.toscana.model.node.Compute;
import org.opentosca.toscana.model.node.ContainerApplication;
import org.opentosca.toscana.model.node.ContainerRuntime;
import org.opentosca.toscana.model.node.ContainerRuntime.ContainerRuntimeBuilder;
import org.opentosca.toscana.model.node.Database;
import org.opentosca.toscana.model.node.Dbms;
import org.opentosca.toscana.model.node.DockerApplication;
import org.opentosca.toscana.model.node.DockerApplication.DockerApplicationBuilder;
import org.opentosca.toscana.model.node.LoadBalancer;
import org.opentosca.toscana.model.node.MysqlDbms;
import org.opentosca.toscana.model.node.Nodejs;
import org.opentosca.toscana.model.node.ObjectStorage;
import org.opentosca.toscana.model.node.RootNode;
import org.opentosca.toscana.model.node.SoftwareComponent;
import org.opentosca.toscana.model.node.SoftwareComponent.SoftwareComponentBuilder;
import org.opentosca.toscana.model.node.WebApplication;
import org.opentosca.toscana.model.node.WebServer;
import org.opentosca.toscana.model.node.WordPress;

import com.google.common.collect.Sets;
import org.eclipse.winery.model.tosca.yaml.TNodeTemplate;
import org.slf4j.Logger;

/**
 Contains logic to convert TOSCA node templates into nodes of the EffectiveModel
 */
class NodeConverter {

    static final String TOSCA_PREFIX = "tosca.nodes.";
    private Logger logger;

    private Map<String, BiFunction<String, TNodeTemplate, RootNode>> conversionMap = new HashMap<>();

    NodeConverter(Logger logger) {
        this.logger = logger;
        addRule("Compute", this::toCompute);
        addRule("Container.Application", this::toContainerApplication);
        addRule("Container.Runtime", this::toContainerRuntime);
        addRule("Storage", "BlockStorage", this::toBlockStorage);
        addRule("Database", this::toDatabase);
        addRule("Database.MySQL", this::toMysqlDbms);
        addRule("Container", "Application.Docker", this::toDockerApplication);
        addRule("DBMS", this::toDbms);
        addRule("DBMS.MySQL", this::toMysqlDbms);
        addRule("LoadBalancer", this::toLoadBalancer);
        addRule("Storage", "ObjectStorage", this::toObjectStorage);
        addRule("SoftwareComponent", this::toSoftwareComponent);
        addRule("WebApplication", this::toWebApplication);
        addRule("WordPress", "WebApplication", this::toWordPress);
        addRule("WebServer", this::toWebServer);
        addRule("WebServer", "Apache", this::toApache);
        addRule("WebServer", "Nodejs", this::toNodejs);
    }

    /**
     Establishes a correlation between a node type (string) and its construction method.

     @param simpleType the shorthand type of a node (e.g.: "Compute")
     @param conversion the conversion method which is used to construct an (EffectiveModel) node
     */
    private void addRule(String simpleType, BiFunction<String, TNodeTemplate, RootNode> conversion) {
        addRule(null, simpleType, conversion);
    }

    /**
     Overloading {@link #addRule}

     @param simpleType {@link #addRule}
     @param typePrefix the prefix needed to construct the full type name: {@code tosca.nodes.<typePrefix>.<simpleType>}
     @param conversion {@link #addRule}
     */
    private void addRule(String typePrefix, String simpleType, BiFunction<String, TNodeTemplate, RootNode> conversion) {
        Set<String> typeSet = getTypes(simpleType, typePrefix);
        typeSet.forEach(typeName -> conversionMap.put(typeName, conversion));
    }

    private Set<String> getTypes(String simpleName, String prefix) {
        String typePrefix = (prefix == null) ? "" : prefix + ".";
        return Sets.newHashSet(simpleName, TOSCA_PREFIX + typePrefix + simpleName);
    }

    RootNode convert(String name, TNodeTemplate template) throws UnknownNodeTypeException {
        String nodeType = template.getType().getLocalPart();
        logger.debug("> Convert node template '{}' (type: '{}')", name, nodeType);
        BiFunction<String, TNodeTemplate, RootNode> conversion = conversionMap.get(nodeType);
        if (conversion == null) {
            throw new UnknownNodeTypeException(String.format(
                "Node type '%s' is not supported by the internal model", template.getType()));
        }
        try {
            return conversion.apply(name, template);
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException(
                String.format("Converting node templates of type '%s' not yet supported",
                    template.getType()));
        }
    }

    private <NodeT extends DescribableEntity,
        BuilderT extends DescribableEntityBuilder,
        VisitorT extends DescribableVisitor<NodeT, BuilderT>>
    NodeT toNode(String name, TNodeTemplate template, Class<BuilderT> builderType, VisitorT visitor) {
        Context<BuilderT> context = new Context<>(name, newInstance(builderType));
        ConversionResult<NodeT> result = visitor.visit(template, context);
        return result.getNode();
    }

    <NodeT> NodeT newInstance(Class clazz) {
        try {
            Constructor<NodeT> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception e) {
            logger.error("Failed to retrieve builder via reflection");
            return null;
        }
    }

    private Apache toApache(String name, TNodeTemplate template) {
        throw new UnsupportedOperationException();
    }

    private BlockStorage toBlockStorage(String name, TNodeTemplate template) {
        throw new UnsupportedOperationException();
    }

    private Compute toCompute(String name, TNodeTemplate template) {
        throw new UnsupportedOperationException();
    }

    private ContainerApplication toContainerApplication(String name, TNodeTemplate template) {
        throw new UnsupportedOperationException();
    }

    private ContainerRuntime toContainerRuntime(String name, TNodeTemplate template) {
        return toNode(name, template, ContainerRuntimeBuilder.class, new ContainerRuntimeVisitor());
    }

    private Database toDatabase(String name, TNodeTemplate template) {
        throw new UnsupportedOperationException();
    }

    private Dbms toDbms(String name, TNodeTemplate template) {
        throw new UnsupportedOperationException();
    }

    private DockerApplication toDockerApplication(String name, TNodeTemplate template) {
        return toNode(name, template, DockerApplicationBuilder.class, new DockerApplicationVisitor());
    }

    private LoadBalancer toLoadBalancer(String name, TNodeTemplate template) {
        throw new UnsupportedOperationException();
    }

    private MysqlDbms toMysqlDbms(String name, TNodeTemplate template) {
        throw new UnsupportedOperationException();
    }

    private Nodejs toNodejs(String name, TNodeTemplate template) {
        throw new UnsupportedOperationException();
    }

    private ObjectStorage toObjectStorage(String name, TNodeTemplate template) {
        throw new UnsupportedOperationException();
    }

    private SoftwareComponent toSoftwareComponent(String name, TNodeTemplate template) {
        return toNode(name, template, SoftwareComponentBuilder.class, new SoftwareComponentVisitor());
    }

    private WebApplication toWebApplication(String name, TNodeTemplate template) {
        throw new UnsupportedOperationException();
    }

    private WebServer toWebServer(String name, TNodeTemplate template) {
        throw new UnsupportedOperationException();
    }

    private WordPress toWordPress(String name, TNodeTemplate template) {
        throw new UnsupportedOperationException();
    }
}
