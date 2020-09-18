package net.celestemagisteel.map;

import javafx.scene.image.Image;
import net.celestemagisteel.MetroidVaniaDemo;
import net.celestemagisteel.entity.Entity;

import java.io.File;
import java.io.Serializable;

public abstract class Tile implements Serializable {

    private String texture;

    public Tile(String texture) {
        this.texture = texture;
    }

    public Image get16Texture() { return new Image(MetroidVaniaDemo.class.getResourceAsStream("./block/" + texture + "/16.png")); }
    public Image get64Texture() { return new Image(MetroidVaniaDemo.class.getResourceAsStream("./block/" + texture + "/64.png")); }
    public Image get256Texture() { return new Image(MetroidVaniaDemo.class.getResourceAsStream("./block/" + texture + "/256.png")); }
    public char getShortCode() { return texture.charAt(0); }

    /**
     * Process a player walking or jumping into a tile
     * @param entity The entity colliding with the tile
     * @param face The face the entity collided with
     * @return whether the entity should pass through the tile
     */
    public abstract boolean onCollide(Entity entity, TileFace face);

    /**
     * Process a player stepping or falling onto a tile
     * @param entity The entity colliding with the tile
     *               @param face The face the entity collided with
     * @return whether the entity should pass through the tile
     */
    public abstract boolean onStepOn(Entity entity, TileFace face);
}
