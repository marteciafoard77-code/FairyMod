package com.example.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;

import com.example.ExampleMod;

public class ModEntities {

    public static final EntityType<FairyEntity> FAIRY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(ExampleMod.MOD_ID, "fairy"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FairyEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.9f))
                    .build()
    );

    // Call this method from ExampleMod to register spawn rules
    public static void registerSpawns() {
        BiomeModifications.addSpawn(
            BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FLOWER_FOREST),
            SpawnGroup.CREATURE,
            FAIRY,
            5, // spawn weight
            1, // min group
            2  // max group
        );
    }
}
