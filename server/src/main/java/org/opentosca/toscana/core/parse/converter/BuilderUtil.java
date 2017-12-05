package org.opentosca.toscana.core.parse.converter;

import java.lang.reflect.Constructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuilderUtil {

    private static final Logger logger = LoggerFactory.getLogger(BuilderUtil.class);

    static <NodeT> NodeT newInstance(Class clazz) {
        try {
            Constructor<NodeT> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception e) {
            logger.error("Failed to retrieve builder via reflection");
            return null;
        }
    }
}
