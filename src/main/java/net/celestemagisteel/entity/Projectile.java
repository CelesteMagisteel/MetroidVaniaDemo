package net.celestemagisteel.entity;

public abstract class Projectile extends Entity {
    public Projectile(String sprite, int maxHealth) {
        super(sprite, maxHealth);
    }

    public Projectile(String sprite, int maxHealth, int x, int y) {
        super(sprite, maxHealth, x, y);
    }
}
