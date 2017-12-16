package org.opentosca.toscana.core.parse.converter;

import org.opentosca.toscana.core.parse.CsarParseService;
import org.opentosca.toscana.core.parse.CsarParseServiceImpl;
import org.opentosca.toscana.core.testdata.TestCsars;
import org.opentosca.toscana.model.EffectiveModel;

import org.junit.Test;

/**
 Tests the conversion of the minimal-docker csar to an effective model
 */
public class ModelInstanceConverterIT {
    
    private final CsarParseService parser = new CsarParseServiceImpl();

    @Test
    public void dockerConverter() throws Exception {
        EffectiveModel model = parser.parse(TestCsars.VALID_MINIMAL_DOCKER_TEMPLATE);
    }
    
    @Test
    public void lampNoInputConverter() throws Exception {
        EffectiveModel model = parser.parse(TestCsars.VALID_LAMP_NO_INPUT_TEMPLATE);
        return;
    }
    
}