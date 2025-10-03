package com.example.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.data.DataTracker;

import com.example.item.ModItems;

public class FairyEntity extends TameableEntity {

    private static final TrackedData<Integer> COLOR = DataTracker.registerData(FairyEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private int healCooldown = 0; // cooldown for healing

    public FairyEntity(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
        this.setTamed(false);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new SitGoal(this));
        this.goalSelector.add(3, new FollowOwnerGoal(this, 1.0D, 3.0F, 10.0F, false));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(COLOR, 0);
    }

    public void setColor(int color) {
        this.dataTracker.set(COLOR, color);
    }

    public int getColor() {
        return this.dataTracker.get(COLOR);
    }

    @Override
    public void tick() {
        super.tick();

        // Floating animation while sitting
        if (this.isSitting()) {
            double floatHeight = 0.05 * Math.sin(this.age * 0.1);
            this.setPos(this.getX(), this.getY() + floatHeight, this.getZ());
        }

        // Spawn particles if tamed
        if (this.world.isClient && this.isTamed()) {
            for (int i = 0; i < 2; i++) {
                double offsetX = (this.random.nextDouble() - 0.5D) * 0.5;
                double offsetY = this.random.nextDouble() * 0.5 + 0.5;
                double offsetZ = (this.random.nextDouble() - 0.5D) * 0.5;

                if (getColor() == 0) {
                    this.world.addParticle(ParticleTypes.HEART, this.getX() + offsetX, this.getY() + offsetY, this.getZ() + offsetZ, 0, 0, 0);
                } else if (getColor() == 1) {
                    this.world.addParticle(ParticleTypes.ENCHANT, this.getX() + offsetX, this.getY() + offsetY, this.getZ() + offsetZ, 0, 0, 0);
                } else {
                    this.world.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getX() + offsetX, this.getY() + offsetY, this.getZ() + offsetZ, 0, 0, 0);
                }
            }
        }

        // Heal owner while following
        if (!this.world.isClient && this.isTamed() && !this.isSitting()) {
            healCooldown++;
            if (healCooldown
