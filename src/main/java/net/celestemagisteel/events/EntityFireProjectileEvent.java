package net.celestemagisteel.events;

import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.entity.Projectile;

public class EntityFireProjectileEvent implements Event {

    private boolean cancelled = false;
    private final Entity whoFired;
    private Entity projectile;

    public EntityFireProjectileEvent(Entity whoFired, Projectile projectile) {
        this.whoFired = whoFired;
        this.projectile = projectile;
    }

    public Entity getWhoFired() {
        return whoFired;
    }

    public Entity getProjectile() {
        return projectile;
    }

    public void setProjectile(Entity projectile) {
        this.projectile = projectile;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public void finalProcessing() {}
}
