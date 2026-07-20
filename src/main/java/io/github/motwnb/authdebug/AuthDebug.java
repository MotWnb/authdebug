package io.github.motwnb.authdebug;

import net.fabricmc.api.ClientModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthDebug implements ClientModInitializer {
	public static final String MOD_ID = "authdebug";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	@Override
	public void onInitializeClient() {

	}
}
