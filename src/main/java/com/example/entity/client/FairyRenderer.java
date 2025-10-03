package com.example.entity.client;

import com.example.ExampleMod;
import com.example.entity.FairyEntity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class FairyRenderer extends MobEntityRenderer<FairyEntity, BipedEntityModel<FairyEntity>> {

    private static final Identifier PINK_TEXTURE = new Identifier(ExampleMod.MOD_ID, "textures/entity/fairy/fairy_pink.png");
    private static final Identifier BLUE_TEXTURE = new Identifier(ExampleMod.MOD_ID, "textures/entity/fairy/fairy_blue.png");
    private static final Identifier MIXED_TEXTURE = new Identifier(ExampleMod.MOD_ID, "textures/entity/fairy/fairy_mixed.png");

    public FairyRenderer(EntityRendererFactory.Context context) {
        super(context, new BipedEntityModel<>(context.getPart(EntityModelLayer.CREEPER)), 0.5f); // size
        this.addFeature(new HeldItemFeatureRenderer<>(this));
    }

    @Override
    public Identifier getTexture(FairyEntity entity) {
        int color = entity.getColor();
        if (color == 0) return PINK_TEXTURE;
        if (color == 1) return BLUE_TEXTURE;
        return MIXED_TEXTURE;
    }
}

