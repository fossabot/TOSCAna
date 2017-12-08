package org.opentosca.toscana.model.node;

import java.util.Objects;

import org.opentosca.toscana.model.capability.EndpointCapability;
import org.opentosca.toscana.model.operation.StandardLifecycle;
import org.opentosca.toscana.model.requirement.DatabaseEndpointRequirement;
import org.opentosca.toscana.model.requirement.WebServerRequirement;
import org.opentosca.toscana.model.visitor.NodeVisitor;

import lombok.Builder;
import lombok.Data;

/**
 (TOSCA Simple Profile in YAML Version 1.1, p. 222)
 */
@Data
public class WordPress extends WebApplication {

    private final String adminUser;

    private final String adminPassword;

    private final String dbHost;

    private final DatabaseEndpointRequirement databaseEndpoint;

    @Builder
    protected WordPress(String adminUser,
                        String adminPassword,
                        String dbHost,
                        DatabaseEndpointRequirement databaseEndpoint,
                        String contextRoot,
                        EndpointCapability endpoint,
                        WebServerRequirement host,
                        String nodeName,
                        StandardLifecycle standardLifecycle,
                        String description) {
        super(contextRoot, endpoint, host, nodeName, standardLifecycle, description);
        this.adminUser = Objects.requireNonNull(adminUser);
        this.adminPassword = Objects.requireNonNull(adminPassword);
        this.dbHost = Objects.requireNonNull(dbHost);
        this.databaseEndpoint = DatabaseEndpointRequirement.getFallback(databaseEndpoint);

        requirements.add(this.databaseEndpoint);
    }

    /**
     @param nodeName         {@link #nodeName}
     @param adminUser        {@link #adminUser}
     @param adminPassword    {@link #adminPassword}
     @param dbHost           {@link #dbHost}
     @param endpoint         {@link WebApplication#appEndpoint}
     */
    public static WordPressBuilder builder(String nodeName,
                                           String adminUser,
                                           String adminPassword,
                                           String dbHost,
                                           EndpointCapability endpoint){
        return (WordPressBuilder) new WordPressBuilder()
            .nodeName(nodeName)
            .adminUser(adminUser)
            .adminPassword(adminPassword)
            .dbHost(dbHost)
            .endpoint(endpoint);
    }

    public static WordPressBuilder builder() {
        return new WordPressBuilder();
    }

    @Override
    public void accept(NodeVisitor v) {
        v.visit(this);
    }

    public static class WordPressBuilder extends WebApplicationBuilder {
    }
}
