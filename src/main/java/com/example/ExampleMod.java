package com.example;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.item.ModItems; // import your Fairy Charm item
import com.example.entity.ModEntities; // import your Fairy entity

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;

public class ExampleMod implements ModInitializer {
    public static final String MOD_ID = "modid";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");

        // Register your Fairy Charm item
        ModItems.registerItems();

        // Add Fairy spawn to flower biomes
        BiomeModifications.addSpawn(
            BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FLOWER_FOREST),
            SpawnGroup.CREATURE,
            ModEntities.FAIRY,
            5, // weight (spawn chance)
            1, // min group
            2  // max group
        );
    }
}
