package net.celestemagisteel.entity;

import java.io.IOException;

public abstract class Projectile extends Entity {
    public Projectile(String sprite, int maxHealth) throws IOException {
        super(sprite, maxHealth);
    }

    public Projectile(String sprite, int maxHealth, int x, int y) throws IOException {
        super(sprite, maxHealth, x, y);
    }
}
