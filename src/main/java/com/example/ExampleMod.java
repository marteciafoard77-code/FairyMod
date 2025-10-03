package com.example;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer {
	public static final String MOD_ID = "modid";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Define the Fairy Charm item
	public static final Item FAIRY_CHARM = new FairyCharm();

	@Override
	public void onInitialize() {
		// Register the Fairy Charm item
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fairy_charm"), FAIRY_CHARM);

		LOGGER.info("Hello Fabric world! Fairy Charm has been registered.");
	}
}
