package net.celestemagisteel.map.tiles;

import javafx.scene.image.Image;
import net.celestemagisteel.Game;
import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.map.tiles.TileFace;

import java.io.Serializable;

public abstract class Tile implements Serializable {

    private final String texture;

    public Tile(String texture) {
        this.texture = texture;
    }

    public Image getTexture(int width, int height) {
        return new Image(Game.class.getResourceAsStream("block/" + texture + ".png"), width, height, false, false);
    }
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
     * @param face The face the entity collided with
     * @return whether the entity should pass through the tile
     */
    public abstract boolean onStepOn(Entity entity, TileFace face);
}