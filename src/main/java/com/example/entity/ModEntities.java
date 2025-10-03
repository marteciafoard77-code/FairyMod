package com.example.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.entity.SpawnRestriction;

import com.example.ExampleMod;

public class ModEntities {

    // Register Fairy entity type
    public static final EntityType<FairyEntity> FAIRY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(ExampleMod.MOD_ID, "fairy"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FairyEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.9f)) // small size
                    .build()
    );

    // Register spawn rules and natural spawning
