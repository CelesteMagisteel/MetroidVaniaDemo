package net.celestemagisteel.entity;

import javafx.scene.image.Image;
import net.celestemagisteel.Game;

public abstract class Entity {

    private final String sprite;
    private int maxHealth;
    private int health;
    private int xCoordinate;
    private int yCoordinate;

    public Entity(String sprite, int maxHealth) {
        this.sprite = sprite;
        this.maxHealth = maxHealth;
        health = maxHealth;
        xCoordinate = 0;
        yCoordinate = 0;
    }

    public Entity(String sprite, int maxHealth, int x, int y) {
        this.sprite = sprite;
        this.maxHealth = maxHealth;
        health = maxHealth;
        xCoordinate = x;
        yCoordinate = y;
    }

    public Image getSprite(EntityState state, int width, int height) {
        return new Image(Game.class.getResourceAsStream("./sprite/" + sprite + "/" + state + ".png"), width, height, false, false);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return xCoordinate;
    }

    public void setX(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getY() {
        return yCoordinate;
    }

    public void setY(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}

