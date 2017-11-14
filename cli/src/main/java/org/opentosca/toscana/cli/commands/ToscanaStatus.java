package org.opentosca.toscana.cli.commands;

import java.io.IOException;

import org.opentosca.toscana.cli.ApiController;

import picocli.CommandLine.Command;

@Command(name = "status",
    description = {"Show the current State of the System"},
    customSynopsis = "@|bold toscana status|@ [@|yellow -mv|@]%n")
public class ToscanaStatus extends AbstractCommand implements Runnable {

    /**
     Get's called if the current state of the system is requested
     */
    public ToscanaStatus() {
    }

    @Override
    public void run() {
        ApiController api = startApi();

        try {
            System.out.println(api.showStatus());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}