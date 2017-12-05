package org.opentosca.toscana.core.parse.converter;

import org.opentosca.toscana.core.parse.CsarParseService;
import org.opentosca.toscana.core.parse.CsarParseServiceImpl;
import org.opentosca.toscana.core.testdata.TestCsars;
import org.opentosca.toscana.model.EffectiveModel;

import org.junit.Test;

/**
 Tests the conversion of the minimal-docker csar to an effective model
 */
public class DockerConverterIT {

    @Test
    public void dockerConverter() throws Exception {
        CsarParseService parser = new CsarParseServiceImpl();

        EffectiveModel model = parser.parse(TestCsars.VALID_MINIMAL_DOCKER_TEMPLATE);
    }
}
