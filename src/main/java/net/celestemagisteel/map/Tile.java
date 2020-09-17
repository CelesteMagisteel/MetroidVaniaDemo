package net.celestemagisteel.map;

import net.celestemagisteel.MetroidVaniaDemo;
import net.celestemagisteel.entity.Entity;

import java.io.File;
import java.io.Serializable;

public abstract class Tile implements Serializable {

    private String texture;

    public Tile(String texture) {
        this.texture = texture;
    }

    public File get16Texture() { return new File(String.valueOf(MetroidVaniaDemo.class.getResource("./block/texture/16.png"))); }
    public File get64Texture() { return new File(String.valueOf(MetroidVaniaDemo.class.getResource("./block/texture/64.png"))); }
    public File get256Texture() { return new File(String.valueOf(MetroidVaniaDemo.class.getResource("./block/texture/256.png"))); }

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
