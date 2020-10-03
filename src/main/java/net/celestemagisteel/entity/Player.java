package net.celestemagisteel.entity;

import java.io.IOException;

public class Player extends Entity {
    public Player(String sprite, int maxHealth) throws IOException {
        super(sprite, maxHealth);
    }

    public Player(String sprite, int maxHealth, int x, int y) throws IOException {
        super(sprite, maxHealth, x, y);
    }
}
