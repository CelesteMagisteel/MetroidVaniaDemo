package net.celestemagisteel.entity;

import net.celestemagisteel.AwtStart;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Entity {

    private final String sprite;
    private final Map<EntityState, Image> stateImageMap = new HashMap<>();
    private int maxHealth;
    private int health;
    private int xCoordinate;
    private int yCoordinate;
    private boolean isOnGround;

    public Entity(String sprite, int maxHealth) throws IOException {
        this(sprite, maxHealth, 0, 0);
    }

    public Entity(String sprite, int maxHealth, int x, int y) throws IOException {
        this.sprite = sprite;
        this.maxHealth = maxHealth;
        health = maxHealth;
        xCoordinate = x;
        yCoordinate = y;
        for (EntityState state : EntityState.values()) {
            stateImageMap.put(state, ImageIO.read(AwtStart.class.getResourceAsStream("sprite/" + sprite + "/" + state + ".png")));
        }
    }

    public Image getSprite(EntityState state, int width, int height) {
        return stateImageMap.get(state).getScaledInstance(width, height, Image.SCALE_DEFAULT);
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

    public abstract boolean obeysGravity();

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

    public void setOnGround(boolean onGround) { this.isOnGround = true; }

    public boolean isOnGround() {
        return isOnGround;
    }
}

