package net.celestemagisteel.entity;

import javafx.scene.image.Image;
import net.celestemagisteel.Game;

public abstract class Entity {

    private final String sprite;
    private int maxHealth;
    private int health;

    public Entity(String sprite, int maxHealth) {
        this.sprite = sprite;
        this.maxHealth = maxHealth;
        health = maxHealth;
    }

    public Image get16Sprite(EntityState state) {
        return new Image(Game.class.getResourceAsStream("./entity/" + sprite + "/" + state + "/16.png"));
    }

    public Image get64Sprite(EntityState state) {
        return new Image(Game.class.getResourceAsStream("./entity/" + sprite + "/" + state + "/64.png"));
    }

    public Image get256Sprite(EntityState state) {
        return new Image(Game.class.getResourceAsStream("./entity/" + sprite + "/" + state + "/256.png"));
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
}

