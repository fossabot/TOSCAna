package org.opentosca.toscana.cli.commands;

import org.opentosca.toscana.cli.ApiController;
import org.opentosca.toscana.cli.CliProperties;
import org.opentosca.toscana.retrofit.util.LoggingMode;

import picocli.CommandLine.Option;

public abstract class AbstractApiCall implements Runnable {

    protected ApiController api;
    @Option(names = {"-v", "--verbose"}, description = "Enable Info Level Process Output")
    private boolean showVerbose;
    @Option(names = {"-m", "--moreverbose"}, description = "Enable Debug Level Process Output")
    private boolean showMVerbose;

    protected AbstractApiCall() {
        CliProperties prop = new CliProperties();
        api = new ApiController(prop.getApiUrl(), getLoggingMode(showVerbose, showMVerbose));
    }

    /**
     Get's and returns the LoggingMode which is wanted for the CLI to output more Information

     @param verbose     need for an output that is more then default
     @param moreVerbose need for a detailed output of what is processed in the CLI
     @return the Loggingmode for the API
     */
    private LoggingMode getLoggingMode(boolean verbose, boolean moreVerbose) {
        if (verbose) {
            return LoggingMode.LOW;
        } else if (moreVerbose) {
            return LoggingMode.HIGH;
        } else {
            return LoggingMode.OFF;
        }
    }
}
