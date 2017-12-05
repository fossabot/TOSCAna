package org.opentosca.toscana.core.parse.converter;

import org.opentosca.toscana.core.BaseIntegrationTest;
import org.opentosca.toscana.core.parse.CsarParseService;
import org.opentosca.toscana.core.parse.CsarParseServiceImpl;
import org.opentosca.toscana.model.EffectiveModel;

import org.junit.Test;

import static org.opentosca.toscana.core.parse.converter.NodeConverterResources.SOFTWARE_COMPONENT;

public class NodeConverterSoftwareComponentIT extends BaseIntegrationTest {

    private CsarParseService parser = new CsarParseServiceImpl();

    @Test
    public void softwareComponent() throws Exception {
        EffectiveModel model = parser.parse(SOFTWARE_COMPONENT);
    }
}
