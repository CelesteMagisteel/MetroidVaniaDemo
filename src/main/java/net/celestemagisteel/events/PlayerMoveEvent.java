package net.celestemagisteel.events;

import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.map.Location;

public class PlayerMoveEvent implements Event {

    private boolean cancelled;
    private Entity player;
    private Location from;
    private Location to;

    public PlayerMoveEvent(Entity player, Location oldLocation, Location newLocation) {
        this.from = oldLocation;
        this.to = newLocation;
        this.player = player;
    }

    public Entity getPlayer() {
        return player;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public void finalProcessing() {

    }
}
