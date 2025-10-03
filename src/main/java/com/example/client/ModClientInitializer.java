package com.example.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import com.example.ExampleMod;
import com.example.entity.FairyEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModClientInitializer implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Register the Fairy renderer
        EntityRendererRegistry.register(ExampleMod.FAIRY_ENTITY_TYPE, (context) -> new FairyRenderer(context));
    }

    // Custom Fairy renderer
    public static class FairyRenderer extends MobEntityRenderer<FairyEntity, BipedEntityModel<FairyEntity>> {

        private static final Identifier PINK = new Identifier("modid", "textures/entity/fairy/fairy_pink.png");
        private static final Identifier BLUE = new Identifier("modid", "textures/entity/fairy/fairy_blue.png");
        private static final Identifier MIXED = new Identifier("modid", "textures/entity/fairy/fairy_mixed.png");

        public FairyRenderer(EntityRendererRegistry.Context context) {
            super(context, new BipedEntityModel<>(context.getPart(EntityModelLayer.CREEPER)), 0.5f);
        }

        @Override
        public Identifier getTexture(FairyEntity entity) {
            switch (entity.getColor()) {
                case 1:
                    return BLUE;
                case 2:
                    return MIXED;
                default:
                    return PINK;
            }
        }
    }
}
