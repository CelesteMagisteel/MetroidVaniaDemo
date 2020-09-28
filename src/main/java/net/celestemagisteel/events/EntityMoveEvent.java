package net.celestemagisteel.events;

import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.map.Location;

public class EntityMoveEvent implements Event {

    private final Entity entity;
    private Location from;
    private Location to;
    private boolean cancelled = false;

    public EntityMoveEvent(Entity entity, Location oldLocation, Location newLocation) {
        this.from = oldLocation;
        this.to = newLocation;
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public Location getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public Location getTo() {
        return to;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public void finalProcessing() {}
}
