package com.example;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.item.ModItems; // import your Fairy Charm item
import com.example.entity.ModEntities; // import your Fairy entity

public class ExampleMod implements ModInitializer {
    public static final String MOD_ID = "modid";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");

        // Register your Fairy Charm item
        ModItems.registerItems();

        // Register Fairy entity spawn rules
        ModEntities.registerSpawns();
    }
}
