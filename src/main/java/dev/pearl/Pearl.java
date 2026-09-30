package dev.pearl;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Pearl {
    public static final String MOD_ID = "pearl";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private Pearl() {}

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
