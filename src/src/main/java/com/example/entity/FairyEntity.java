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

    // Data tracker for color
    private static final TrackedData<Integer> COLOR = DataTracker.registerData(FairyEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public FairyEntity(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
        this.setTamed(false);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new SitGoal(this)); // handles sitting
        this.goalSelector.add(3, new FollowOwnerGoal(this, 1.0D, 3.0F, 10.0F, false));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(COLOR, 0); // default color = 0 (pink)
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

        // Only spawn sparkles when tamed
        if (this.world.isClient && this.isTamed()) {
            for (int i = 0; i < 2; i++) {
                double offsetX = (this.random.nextDouble() - 0.5D) * 0.5;
                double offsetY = this.random.nextDouble() * 0.5 + 0.5;
                double offsetZ = (this.random.nextDouble() - 0.5D) * 0.5;

                // Choose particle type based on color
                if (getColor() == 0) { // pink
                    this.world.addParticle(ParticleTypes.HEART, this.getX() + offsetX, this.getY() + offsetY, this.getZ() + offsetZ, 0, 0, 0);
                } else if (getColor() == 1) { // blue
                    this.world.addParticle(ParticleTypes.ENCHANT, this.getX() + offsetX, this.getY() + offsetY, this.getZ() + offsetZ, 0, 0, 0);
                } else { // mixed
                    this.world.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getX() + offsetX, this.getY() + offsetY, this.getZ() + offsetZ, 0, 0, 0);
                }
            }
        }
    }

    @Override
    public boolean interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        // Right-click with Fairy Charm to tame
        if (!this.world.isClient && stack.getItem() == ModItems.FAIRY_CHARM && !this.isTamed()) {
            this.setOwner(player);
            this.setTamed(true);
            stack.decrement(1);

            this.world.sendEntityStatus(this, (byte)7); // heart particles

            int color = this.random.nextInt(3); // 0=pink, 1=blue, 2=mixed
            this.setColor(color);
            return true;
        }

        // Right-click with empty hand to sit/unsit
        if (!this.world.isClient && stack.isEmpty() && this.isTamed()) {
            this.getNavigation().stop();
            this.setSitting(!this.isSitting());
            return true;
        }

        return super.interactMob(player, hand);
    }
}
