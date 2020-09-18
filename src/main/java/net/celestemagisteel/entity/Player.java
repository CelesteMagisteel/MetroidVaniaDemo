package net.celestemagisteel.entity;

public class Player extends Entity {
    public Player(String sprite, int maxHealth) {
        super(sprite, maxHealth);
    }

    public Player(String sprite, int maxHealth, int x, int y) {
        super(sprite, maxHealth, x, y);
    }
}
