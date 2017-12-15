package org.opentosca.toscana.model.node;

import org.opentosca.toscana.model.capability.ContainerCapability;
import org.opentosca.toscana.model.capability.DatabaseEndpointCapability;
import org.opentosca.toscana.model.operation.StandardLifecycle;
import org.opentosca.toscana.model.relation.HostedOn;
import org.opentosca.toscana.model.requirement.MysqlDbmsRequirement;
import org.opentosca.toscana.model.requirement.Requirement;
import org.opentosca.toscana.model.visitor.NodeVisitor;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class MysqlDatabase extends Database {

    @Getter(AccessLevel.NONE)
    public final Requirement<ContainerCapability, MysqlDbms, HostedOn> host;

    @Builder
    public MysqlDatabase(String databaseName,
                         Integer port,
                         String user,
                         String password,
                         Requirement<ContainerCapability, MysqlDbms, HostedOn> host,
                         DatabaseEndpointCapability databaseEndpoint,
                         String nodeName,
                         StandardLifecycle standardLifecycle,
                         String description) {
        super(databaseName, port, user, password, databaseEndpoint, nodeName, standardLifecycle, description);

        this.host = (host == null) ? MysqlDbmsRequirement.builder().build() : host;

        requirements.add(this.host);
    }

    /**
     @param nodeName         {@link #nodeName}
     @param databaseName     {@link #databaseEndpoint}
     @param databaseEndpoint {@link #databaseEndpoint}
     */
    public static MysqlDatabaseBuilder builder(String nodeName,
                                               String databaseName,
                                               DatabaseEndpointCapability databaseEndpoint) {
        return (MysqlDatabaseBuilder) new MysqlDatabaseBuilder()
            .nodeName(nodeName)
            .databaseName(databaseName)
            .databaseEndpoint(databaseEndpoint);
    }

    @Override
    public void accept(NodeVisitor v) {
        v.visit(this);
    }

    public static class MysqlDatabaseBuilder extends DatabaseBuilder {

        @Override
        public DatabaseBuilder host(Requirement<ContainerCapability, Dbms, HostedOn> host) {
            // this is a hack - shall enforce the usage of mysqlHost() (root of all evil is generic type erasure)
            throw new IllegalStateException();
        }
        
        public DatabaseBuilder mysqlHost(Requirement<ContainerCapability, MysqlDbms, HostedOn> host) {
            this.host = host;
            return this;
        }

    }
}
