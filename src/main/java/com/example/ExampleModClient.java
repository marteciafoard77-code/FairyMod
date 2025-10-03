
package com.example;

import com.example.entity.FairyEntity;
import com.example.entity.client.FairyRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ExampleModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register Fairy renderer so Minecraft knows how to draw it
        EntityRendererRegistry.register(FairyEntity.class, (context) -> new FairyRenderer(context));
    }
}
