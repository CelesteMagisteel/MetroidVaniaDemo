package net.celestemagisteel.entity;

import java.io.IOException;

public class Player extends Entity {
    private boolean crouch = false;

    public Player(String sprite, int maxHealth) throws IOException {
        super(sprite, maxHealth);
    }

    public Player(String sprite, int maxHealth, int x, int y) throws IOException {
        super(sprite, maxHealth, x, y);
    }

    @Override
    public boolean obeysGravity() {
        return true;
    }

    public void crouch() { this.crouch = true; }

    public void uncrouch() { this.crouch = false; }

    public boolean isCrouching() { return this.crouch; }
}
