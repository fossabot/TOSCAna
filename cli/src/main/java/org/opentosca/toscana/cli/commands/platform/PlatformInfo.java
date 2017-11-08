package org.opentosca.toscana.cli.commands.platform;

import java.io.IOException;

import org.opentosca.toscana.cli.ApiController;
import org.opentosca.toscana.cli.commands.AbstractCommand;
import org.opentosca.toscana.cli.commands.Constants;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "info",
    description = {"Information about the specified Platform"},
    customSynopsis = "@|bold toscana platform info|@ @|yellow -p=<name>|@ [@|yellow -mv|@]%n",
    optionListHeading = "%nOptions:%n")
public class PlatformInfo extends AbstractCommand implements Runnable {

    @Option(names = {"-p", "--platform"}, required = true, paramLabel = Constants.PARAM_PLATFORM, description = "Information about the Platform")
    private String platformInfo;

    /**
     * show's information about the wanted platform
     */
    public PlatformInfo() {
    }

    @Override
    public void run() {
        ApiController api = startApi();

        try {
            System.out.println(api.infoPlatform(platformInfo));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
