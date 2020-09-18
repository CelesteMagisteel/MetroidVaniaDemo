package net.celestemagisteel.entity;

public enum EntityState {
    DEFAULT,
    HURT,
    VICTORY,
    DEAD;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
