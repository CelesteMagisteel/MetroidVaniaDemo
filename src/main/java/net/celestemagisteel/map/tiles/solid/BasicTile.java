package net.celestemagisteel.map.tiles.solid;

import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.map.Tile;
import net.celestemagisteel.map.TileFace;

public class BasicTile extends Tile {

    public BasicTile(String texture) {
        super(texture);
    }

    @Override
    public boolean onCollide(Entity entity, TileFace face) {
        return false;
    }

    @Override
    public boolean onStepOn(Entity entity, TileFace face) {
        return false;
    }
}
