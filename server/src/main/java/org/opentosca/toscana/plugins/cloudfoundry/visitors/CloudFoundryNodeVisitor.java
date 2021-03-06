package org.opentosca.toscana.plugins.cloudfoundry.visitors;

import org.opentosca.toscana.model.node.Apache;
import org.opentosca.toscana.model.node.Compute;
import org.opentosca.toscana.model.node.MysqlDatabase;
import org.opentosca.toscana.model.node.MysqlDbms;
import org.opentosca.toscana.model.node.RootNode;
import org.opentosca.toscana.model.node.WebApplication;
import org.opentosca.toscana.model.operation.Operation;
import org.opentosca.toscana.model.operation.OperationVariable;
import org.opentosca.toscana.model.visitor.StrictNodeVisitor;
import org.opentosca.toscana.plugins.cloudfoundry.CloudFoundryApplication;

import static org.opentosca.toscana.plugins.cloudfoundry.CloudFoundryManifestAttribute.DISKSIZE;
import static org.opentosca.toscana.plugins.cloudfoundry.CloudFoundryManifestAttribute.DOMAIN;
import static org.opentosca.toscana.plugins.cloudfoundry.CloudFoundryManifestAttribute.MEMORY;

public class CloudFoundryNodeVisitor implements StrictNodeVisitor {

    private CloudFoundryApplication myApp;

    public CloudFoundryNodeVisitor(CloudFoundryApplication myApp) {
        this.myApp = myApp;
    }

    public CloudFoundryApplication getFilledApp() {
        return myApp;
    }

    @Override
    public void visit(Compute node) {
        /*
        os: do nothing -> CF uses linux based containers
        private_address: do nothing -> automatically
        public_address: add value to manifest
        networks: do nothing -> automatically
        ports: do nothing -> automatically
        */

        if (node.getPublicAddress().isPresent()) {
            myApp.addAttribute(DOMAIN.getName(), node.getPublicAddress().get());
        }

        if (node.getHost().getDiskSizeInMB().isPresent()) {
            myApp.addAttribute(DISKSIZE.getName(), node.getHost().getDiskSizeInMB().get() + " MB");
        }

        if (node.getHost().getMemSizeInMB().isPresent()) {
            myApp.addAttribute(MEMORY.getName(), node.getHost().getMemSizeInMB().get() + " MB");
        }
    }

    @Override
    public void visit(MysqlDatabase node) {
        /*
        create service
        ignore password and port
         */
        myApp.addService(node.getNodeName());
    }

    @Override
    public void visit(MysqlDbms node) {
        // TODO: check how to configure database
        handleStandardLifecycle(node);
    }

    @Override
    public void visit(Apache node) {
        // do nothing yet but override to prevent a UnsupportedTypeException
    }

    @Override
    public void visit(WebApplication node) {
        myApp.setName(node.getNodeName());
        handleStandardLifecycle(node);
    }

    private void handleStandardLifecycle(RootNode node) {
        // get StandardLifecycle inputs
        for (OperationVariable lifecycleInput : node.getStandardLifecycle().getInputs()) {
           addEnvironmentVariable(lifecycleInput);
        }

        // get operation inputs
        for (Operation operation : node.getStandardLifecycle().getOperations()) {
            // artifact path
            if (operation.getArtifact().isPresent()) {
                myApp.addFilePath(operation.getArtifact().get().getFilePath());
            }

            // add implementationArtifact path if not null
            if (operation.getImplementationArtifact().isPresent()) {
                myApp.addFilePath(operation.getImplementationArtifact().get());
            }

            // add dependencies paths
            for (String dependency : operation.getDependencies()) {
                myApp.addFilePath(dependency);
            }

            // add inputs to environment list
            for (OperationVariable input : operation.getInputs()) {
                addEnvironmentVariable(input);
            }
            // TODO: investigate what to do with outputs?
        }
    }
    
    private void addEnvironmentVariable(OperationVariable input) {
        if (input.getValue().isPresent()) {
            myApp.addEnvironmentVariables(input.getKey(), input.getValue().get());
        } else {
            myApp.addEnvironmentVariables(input.getKey());
        }
    }
}
