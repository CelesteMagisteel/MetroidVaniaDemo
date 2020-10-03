package net.celestemagisteel.map.tiles;

import net.celestemagisteel.Game;
import net.celestemagisteel.entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.Serializable;

public abstract class Tile implements Serializable {

    private transient java.awt.Image texture;
    private final String textureName;

    public Tile(String texture) throws IOException {
        this.textureName = texture;
        this.texture = ImageIO.read(Game.class.getResourceAsStream("block/" + texture + ".png"));
    }


    public java.awt.Image getTexture(int width, int height) {
        return texture.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
    }

    public void loadTexture() throws IOException {
        this.texture = ImageIO.read(Game.class.getResourceAsStream("block/" + textureName + ".png"));
    }

    /**
     * Process a player walking or jumping into a tile
     *
     * @param entity The entity colliding with the tile
     * @param face   The face the entity collided with
     * @return whether the entity should pass through the tile
     */
    public abstract boolean onCollide(Entity entity, TileFace face);
}
