package net.celestemagisteel.events;

public interface Event {

    boolean isCancelled();

    void setCancelled(boolean cancelled);

    void finalProcessing();
}
