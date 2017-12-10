package org.opentosca.toscana.core.parse.converter;

import org.opentosca.toscana.core.parse.InvalidCsarException;
import org.opentosca.toscana.core.transformation.logging.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidServiceTemplateException extends InvalidCsarException {

    private final static Logger logger = LoggerFactory.getLogger(InvalidServiceTemplateException.class.getName());

    public InvalidServiceTemplateException(Log log) {
        super(log);
    }
}
